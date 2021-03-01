$(async () => {
    try {
        loadSpecSearch();
    } catch (e) {
        console.log(e);
    }
    loadProductImages();
});

function loadProductImages() {
    const valueElements = $(".pid-value");
    const pidList = [];
    let index = 0;
    for (let i = 0; i < valueElements.length; i++) {
        const pid = valueElements.get(i).value;
        if (pid != undefined && pid != "") {
            pidList.push(pid);
        }
    }

    loadProduct();

    function loadProduct() {
        const pid = pidList[index];
        if (pidList.length === index) {
            return console.log("Load Complete");
        } else {
            getProduct(pid).then(result => {
                let imageUrl = result.data.mediumImageUrl;
                if (Array.isArray(imageUrl)) {
                    imageUrl = imageUrl[0];
                }

                applyImage(imageUrl, pid);
                loadProduct();
            }).catch(err => {
                console.log(err);
                index = index + 1;
                loadProduct();
            });
            index++;
        }
    }

    function getProduct(pid) {
        return new Promise((resolve, reject) => {
            setTimeout(async () => {
                try {
                    const result = await axios.get(`rakuten-product.json?productId=${String(pid)}`);
                    resolve(result);
                } catch (e) {
                    reject(e);
                }
            }, 300);
        });
    }

    function applyImage(imageUrl, pid) {
        if (!imageUrl.endsWith("undefined")) {
            $(`#product-img-${pid}`).get(0).src = imageUrl;
        }
    }
}

function showTypeSelect() {
    const display = document.getElementById("product-type-select").className.valueOf();

    const target = $("#product-type-select");

    if (display === "product-type-select-hidden") {
        target.removeClass("product-type-select-hidden");
        target.addClass("product-type-select-display");
    } else {
        target.removeClass("product-type-select-display");
        target.addClass("product-type-select-hidden");
    }
}

function showSpecSearch() {
    const display = document.getElementById("product-spec-search").className.valueOf();

    const target = $("#product-spec-search");

    if (display === "spec-search-hidden") {
        target.removeClass("spec-search-hidden");
        target.addClass("spec-search-display");
    } else {
        target.removeClass("spec-search-display");
        target.addClass("spec-search-hidden");
    }
}

function loadSpecSearch() {
    const productTypeName = getUrlParameter("productType");
    const specOptions = getSpecOptions(productTypeName);

    const specOptionNames = Object.keys(specOptions);

    for (const specOptionName of specOptionNames) {
        const selectedValue = getUrlParameter(specOptionName);
        $("#product-spec-search").append(["<br>",
            `<p id="option-${specOptionName}-value-view">${getTranslatedSpecOptionName(specOptionName)}</p>`,
            `<div id='spec-search-option-${specOptionName}'></div>`]);
        const specOptionValues = specOptions[specOptionName];
        if (typeof specOptionValues[0] === "number") {
            const maxValue = Math.max.apply(null, specOptionValues);
            const minValue = Math.min.apply(null, specOptionValues);

            let numSelectedMaxValue = maxValue;
            let numSelectedMinValue = minValue;
            if (selectedValue != null && selectedValue != undefined && selectedValue != "") {
                const buff = selectedValue.split(",");
                if (buff.length === 1) {
                    numSelectedMaxValue = buff[0];
                } else if (buff.length === 2) {
                    numSelectedMinValue = buff[0];
                    numSelectedMaxValue = buff[1];
                }
            }

            $(`#option-${specOptionName}-value-view`).html(`${getTranslatedSpecOptionName(specOptionName)}: ${numSelectedMinValue} - ${numSelectedMaxValue}`);

            $("#product-spec-search")
                .append(`<input type="hidden" id="option-${specOptionName}-value" name="${specOptionName}" />`);

            let sliderOption = {
                range: true,
                min: minValue,
                max: maxValue,
                step: 1,
                values: [parseInt(numSelectedMinValue), parseInt(numSelectedMaxValue)],
                slide: (event, ui) => {
                    $(`#option-${specOptionName}-value`).attr("value", `${ui.values[0]},${ui.values[1]}`);
                    $(`#option-${specOptionName}-value-view`).html(`${specOptionName}: ${ui.values[0]} - ${ui.values[1]}`);
                }
            };

            if (!Number.isInteger(maxValue) || !Number.isInteger(minValue)) {
                sliderOption.step = 0.1;
                sliderOption.values = [parseFloat(numSelectedMinValue), parseFloat(numSelectedMaxValue)]
            }

            $(`#spec-search-option-${specOptionName}`).slider(sliderOption);
        } else {
            for (const specOptionValue of specOptionValues) {
                if (selectedValue != null && selectedValue != undefined && selectedValue != "") {
                    if (selectedValue == specOptionValue) {
                        $(`#spec-search-option-${specOptionName}`)
                            .append(`<input type="radio" name="${specOptionName}" value="${specOptionValue}" ` +
                                `id="spec-${specOptionName}-${specOptionValue}" checked>` +
                                `<label class="sort" for="spec-${specOptionName}-${specOptionValue}">${specOptionValue}</label>`);
                    } else {
                        $(`#spec-search-option-${specOptionName}`)
                            .append(`<input type="radio" name="${specOptionName}" value="${specOptionValue}" ` +
                                `id="spec-${specOptionName}-${specOptionValue}">` +
                                `<label class="sort" for="spec-${specOptionName}-${specOptionValue}">${specOptionValue}</label>`);
                    }
                } else {
                    $(`#spec-search-option-${specOptionName}`)
                        .append(`<input type="radio" name="${specOptionName}" value="${specOptionValue}" ` +
                            `id="spec-${specOptionName}-${specOptionValue}">` +
                            `<label class="sort" for="spec-${specOptionName}-${specOptionValue}">${specOptionValue}</label>`);
                }
            }
        }
    }
}

function getSpecOptions(productType) {
    switch (productType) {
        case "cpu":
            return cpuSpecOptions;
        case "gpu":
            return gpuSpecOptions;
        case "ram":
            return ramSpecOptions;
        case "mother_board":
            return motherBoardOptions;
        case "storage":
            return storageOptions;
        case "case":
            return caseOptions;
        case "power_supply":
            return powerSupplyOptions;
        case "cpu_cooler":
            return cpuCoolerOptions;
        case "case_fan":
            return caseFanOptions;
        default:
            return null;
    }
}

function getUrlParameter(parameterName) {
    let result = "";
    location.search.substring(1).split("&").forEach((item) => {
        const param = item.split("=");
        if (param[0] === parameterName) {
            result = decodeURIComponent(param[1]).replace("+", " ");
        }
    });

    return result;
}

function getTranslatedSpecOptionName(specOptionName) {
    switch (specOptionName) {
        case "tdp":
            return "TDP";
        case "core":
            return "コア数";
        case "thread":
            return "スレッド数";
        case "socket":
            return "ソケット";
        case "frequency":
            return "クロック";
        case "gpu":
            return "GPU";
        case "memorySize":
            return "メモリーサイズ";
        case "memoryType":
            return "メモリータイプ";
        case "ddr":
            return "DDR タイプ";
        case "dimm":
            return "DIMM タイプ";
        case "clock":
            return "クロック";
        case "wifi":
            return "WIFI 対応";
        case "chipset":
            return "チップセット";
        case "usbTypeC":
            return "USB TYPE-C 対応";
        case "formfactor":
            return "フォームファクタ";
        case "thunderbolt":
            return "Thunderbolt 対応";
        case "size":
            return "サイズ";
        case "speed":
            return "回転数";
        case "serial":
            return "インターフェイス";
        case "volume":
            return "容量";
        case "flashMemoryType":
            return "フラッシュメモリータイプ";
        case "factor":
            return "ファクタ";
        case "80PLUS":
            return "80PLUS認証";
        case "type":
            return "タイプ";
        case "airVolume":
            return "ファン容量";
        case "rpm":
            return "回転数";
        case "noiseLevel":
            return "ノイズレベル";
        default:
            return "";
    }
}

const cpuSpecOptions = {
    tdp: [
        280,
        255,
        250,
        205,
        200,
        180,
        170,
        165,
        160,
        150,
        145,
        140,
        135,
        130,
        125,
        120,
        112,
        105,
        100,
        95,
        91,
        90,
        85,
        83,
        80,
        75,
        73,
        72,
        71,
        65,
        62,
        60,
        58,
        55,
        54,
        51,
        50,
        35,
        25,
    ],
    core: [
        64,
        32,
        28,
        26,
        24,
        22,
        20,
        18,
        16,
        14,
        12,
        10,
        8,
        6,
        4,
        2,
    ],
    thread: [
        128,
        64,
        56,
        52,
        48,
        44,
        40,
        36,
        32,
        28,
        24,
        20,
        16,
        12,
        8,
        6,
        4,
        2,
    ],
    socket: [
        "Socket sTRX4",
        "Socket TR4",
        "Socket SP3r2",
        "Socket SP3",
        "Socket FM2+",
        "Socket AM4",
        "Socket AM3+",
        "Socket AM1",
        "LGA 3647",
        "LGA 2066",
        "LGA 2011-3",
        "LGA 2011",
        "LGA 1200",
        "LGA 1151",
    ],
    frequency: [
        4.3,
        4.2,
        4.1,
        4,
        3.9,
        3.8,
        3.7,
        3.6,
        3.5,
        3.4,
        3.3,
        3.2,
        3.1,
        3,
        2.9,
        2.8,
        2.7,
        2.6,
        2.5,
        2.4,
        2.3,
        2.2,
        2.1,
        2,
        1.9,
        1.8,
        1.7,
        1.6,
    ]
};

const gpuSpecOptions = {
    gpu: [
        "Vega10",
        "Tesla T4",
        "Tesla P40",
        "Tesla P4",
        "Tesla P100",
        "Tesla M4",
        "Tesla K80",
        "Tesla K40",
        "Radeon Vega Frontier Edition",
        "Radeon VII",
        "Radeon RX Vega 64",
        "Radeon RX Vega 56",
        "Radeon RX 6900 XT",
        "Radeon RX 6800 XT",
        "Radeon RX 6800",
        "Radeon RX 590",
        "Radeon RX 580 XTR",
        "Radeon RX 580",
        "Radeon RX 5700 XT",
        "Radeon RX 5700",
        "Radeon RX 570",
        "Radeon RX 5600 XT",
        "Radeon RX 560 (14ユニット)",
        "Radeon RX 560",
        "Radeon RX 5500 XT",
        "Radeon RX 550",
        "Radeon RX 480",
        "Radeon RX 470",
        "Radeon RX 460",
        "Radeon R9 Nano",
        "Radeon R9 Fury X",
        "Radeon R9 Fury",
        "Radeon R9 380X",
        "Radeon R7 360",
        "Radeon R7 250",
        "Radeon R7 240",
        "Radeon R5 230",
        "Radeon Pro WX 9100",
        "Radeon Pro WX 8200",
        "Radeon Pro WX 7100",
        "Radeon Pro WX 5100",
        "Radeon Pro WX 4100",
        "Radeon Pro WX 3200",
        "Radeon Pro WX 3100",
        "Radeon Pro WX 2100",
        "Radeon Pro W5700X",
        "Radeon Pro W5500X",
        "Radeon Pro SSG",
        "RTX A6000",
        "RADEON HD 6450",
        "RADEON HD 5450",
        "Quadro RTX 8000",
        "Quadro RTX 6000",
        "Quadro RTX 5000",
        "Quadro RTX 4000",
        "Quadro P620",
        "Quadro P6000",
        "Quadro P600",
        "Quadro P5000",
        "Quadro P4000",
        "Quadro P400",
        "Quadro P2200",
        "Quadro P2000",
        "Quadro P1000",
        "Quadro M6000",
        "Quadro M5000",
        "Quadro M4000",
        "Quadro M2000",
        "Quadro K420",
        "Quadro K2200",
        "Quadro GV100",
        "Quadro GP100",
        "NVS 315",
        "NVS 310",
        "NVIDIA TITAN X",
        "GeForce RTX 3090",
        "GeForce RTX 3080",
        "GeForce RTX 3070",
        "GeForce RTX 3060 Ti",
        "GeForce RTX 2080 Ti",
        "GeForce RTX 2080 SUPER",
        "GeForce RTX 2080",
        "GeForce RTX 2070 SUPER",
        "GeForce RTX 2070",
        "GeForce RTX 2060 SUPER",
        "GeForce RTX 2060",
        "GeForce GTX 980 Ti",
        "GeForce GTX 980",
        "GeForce GTX 970",
        "GeForce GTX 960",
        "GeForce GTX 950",
        "GeForce GTX 750 Ti",
        "GeForce GTX 1660 Ti",
        "GeForce GTX 1660 SUPER",
        "GeForce GTX 1660",
        "GeForce GTX 1650 SUPER",
        "GeForce GTX 1650 (G6)",
        "GeForce GTX 1650 (G5)",
        "GeForce GTX 1080 Ti",
        "GeForce GTX 1080",
        "GeForce GTX 1070 Ti",
        "GeForce GTX 1070",
        "GeForce GTX 1060",
        "GeForce GTX 1050 Ti",
        "GeForce GTX 1050",
        "GeForce GT 740",
        "GeForce GT 730 (64-bit GDDR5)",
        "GeForce GT 730 (64-bit DDR3)",
        "GeForce GT 710",
        "GeForce GT 1030",
        "GV100",
        "A40",
        "A100",
    ],
    memorySize: [
        48,
        40,
        32,
        24,
        16,
        12,
        11,
        10,
        8,
        6,
        5,
        4,
        3,
        2,
        1,
    ],
    memoryType: [
        "GDDR6X",
        "GDDR6",
        "GDDR5X",
        "GDDR5",
        "GDDR3",
    ]
};

const ramSpecOptions = {
    ddr: [
        "DDR4",
        "DDR3",
        "DDR3L",
        "DDR2"
    ],
    dimm: [
        "DIMM",
        "S.O.DIMM"
    ],
    clock: [
        4600,
        4400,
        4266,
        4133,
        4000,
        3800,
        3733,
        3600,
        3466,
        3200,
        3000,
        2933,
        2800,
        2666,
        2400,
        2133,
        1600,
        1333,
        1066,
        800,
        667
    ]
};

const motherBoardOptions = {
    wifi: [true, false],
    socket: [
        "LGA 1200",
        "Socket AM4",
        "LGA 1151",
        "LGA 1155",
        "LGA 2066",
        "Socket sTRX4",
        "LGA 3647",
        "LGA 2011-3",
        "LGA 2011",
        "LGA 1150",
        "Socket TR4",
        "Socket FM2+/FM2",
        "Socket AM3+/AM3",
        "LGA 1366",
        "LGA 771",
        "LGA 775",
        "LGA 1356",
        "Socket SP3",
        "Socket G2",
        "LGA 1156"
    ],
    chipset: [
        "INTEL B460",
        "AMD B550",
        "INTEL H470",
        "AMD B450",
        "AMD X570",
        "INTEL Z490",
        "INTEL H410",
        "INTEL Z590",
        "AMD A320",
        "INTEL B250",
        "AMD A520",
        "INTEL H310",
        "INTEL Z390",
        "INTEL H61",
        "INTEL X299",
        "INTEL B365",
        "INTEL H370",
        "INTEL B560",
        "INTEL H110",
        "AMD TRX40",
        "INTEL B360",
        "INTEL W480",
        "AMD X470",
        "INTEL C246",
        "AMD B350",
        "INTEL Z370",
        "INTEL C232",
        "INTEL C422",
        "INTEL C621",
        "AMD X370",
        "INTEL C236",
        "INTEL C612",
        "INTEL C242",
        "INTEL C602J",
        "INTEL C226",
        "INTEL CM236",
        "INTEL C602",
        "AMD A70M",
        "INTEL B150",
        "INTEL H270",
        "INTEL H81",
        "AMD X399",
        "INTEL H170",
        "INTEL X99",
        "INTEL Z270",
        "INTEL B85",
        "AMD A88X",
        "AMD 970+SB950",
        "INTEL Q87",
        "INTEL 5520+ICH10R+IOH-36D",
        "INTEL 5100+ICH9R",
        "INTEL Z170",
        "INTEL C604",
        "INTEL H87",
        "INTEL C202",
        "INTEL 3200+ICH9R+PXH-V",
        "INTEL C222",
        "INTEL C622",
        "INTEL X48+ICH9R",
        "INTEL QM77",
        "AMD A50M",
        "INTEL C204",
        "INTEL C224",
        "INTEL C606",
        "INTEL 8903",
        "INTEL Z97",
        "INTEL 5520+ICH10R+2x IOH-36D",
        "INTEL QM67",
        "INTEL ICH9R",
        "INTEL H570",
        "INTEL C216",
        "INTEL Q170",
        "AMD A68H",
        "AMD 990FX+SB950",
        "INTEL 3420",
        "INTEL Q67",
        "INTEL Q270"
    ],
    usbTypeC: [true, false],
    formfactor: [
        "MicroATX",
        "ATX",
        "Mini ITX",
        "Extended",
        "CEB",
        "XL-ATX",
        "SSI EEB",
        "Proprietary",
        "FlexATX",
        "Mini STX",
        "Thin Mini-ITX"
    ],
    thunderbolt: [true, false]
};

const storageOptions = {
    size: [
        "2.5",
        "3.5",
        "M.2 (Type2280)",
        "M.2 (Type2242)",
        "1.8",
        "M.2 (Type2260)",
        "M.2 (Type22110)",
    ],
    speed: [
        "5400",
        "7200",
        "5700",
        "5900",
        "5640",
    ],
    serial: [
        "Serial ATA600",
        "Serial ATA300",
        "Serial ATA150",
        "Serial ATA 6Gb/s",
        "PCI-Express Gen3",
        "PCI-Express Gen4",
        "PCI-Express",
        "Serial ATA/USB"
    ],
    volume: [
        "2TB",
        "6TB",
        "1TB",
        "8TB",
        "500GB",
        "4TB",
        "14TB",
        "16TB",
        "10TB",
        "12TB",
        "18TB",
        "3TB",
        "5TB",
        "40GB",
        "320GB",
        "1.5TB",
        "250GB",
        "750GB",
        "512GB",
        "240GB",
        "120GB",
        "960GB",
        "480GB",
        "256GB",
        "128GB",
        "1.92TB",
        "1.024TB",
        "1.9TB",
        "64GB",
        "2.048TB",
        "15.36TB",
        "32GB",
        "118GB",
        "375GB",
        "2.05TB",
        "280GB",
        "720GB",
        "8.192TB",
        "16GB",
        "3.84TB",
        "400GB",
        "1.2TB",
        "60GB",
        "640GB",
        "180GB",
        "275GB",
        "360GB",
        "3.2TB",
        "7.68TB",
        "56GB",
        "58GB",
        "1.6TB",
        "525GB",
        "1.05TB",
        "800GB",
        "4.096TB",
        "380GB",
        "160GB",
        "450GB",
        "3.8TB",
    ],
    flashMemoryType: [
        "3D MLC",
        "3D QLC",
        "3D TLC",
        "MLC",
        "QLC",
        "TLC"
    ]
};

const caseOptions = {
    factor: [
        "ATX",
        "Extended ATX",
        "MicroATX",
        "Mini-DTX",
        "Mini-ITX",
        "Mini-STX",
        "SSI-CEB",
        "SSI-EEB"
    ]
};

const powerSupplyOptions = {
    "80PLUS": [
        "Bronze",
        "Gold",
        "Platinum",
        "Silver",
        "Standard",
        "Titanium"
    ]
};

const cpuCoolerOptions = {
    type: [
        "サイドフロー型",
        "トップフロー型",
        "ファンレス",
        "水冷型",
    ],
    airVolume: [
        99.2,
        97,
        96.7,
        94,
        93.4,
        93,
        92.5,
        91,
        90.4,
        90.3,
        90.1,
        9.2,
        88.3,
        87.6,
        85,
        83.7,
        81.4,
        81,
        80.7,
        80,
        8.9,
        79.8,
        79,
        78.7,
        78.4,
        77.3,
        77,
        76.4,
        76.2,
        76,
        75,
        74.3,
        73.6,
        73.3,
        72.1,
        72,
        71.3,
        70.8,
        70.7,
        70.6,
        69.3,
        69.2,
        69.1,
        69,
        68.9,
        67.8,
        67,
        66.7,
        66.5,
        66.3,
        66,
        65.5,
        65,
        64.5,
        64.4,
        64,
        63.9,
        63.2,
        63,
        62.3,
        62,
        61.9,
        61.5,
        61,
        60,
        59.3,
        59,
        57.3,
        57.2,
        57,
        56.5,
        56.1,
        56,
        55.8,
        55.4,
        55,
        54.4,
        54.3,
        53.4,
        53,
        52.4,
        52,
        51.4,
        51.2,
        50.8,
        50.4,
        50,
        49.5,
        49,
        48.9,
        48.4,
        48.3,
        48.2,
        47.6,
        47,
        46.2,
        46,
        45.8,
        45.2,
        45.1,
        45,
        44.5,
        44.3,
        43.3,
        43,
        42.8,
        42.3,
        42.2,
        42,
        41.7,
        41.3,
        40.6,
        40.5,
        40,
        39.4,
        39,
        38.8,
        38.6,
        38.5,
        37,
        36,
        35.5,
        35.1,
        35,
        34.3,
        34.1,
        34,
        33,
        32.8,
        32.4,
        31,
        30,
        29.3,
        27.9,
        24,
        229.6,
        22.6,
        20,
        19.5,
        171,
        167.3,
        16,
        153,
        138.7,
        130,
        121.8,
        113,
        104.7,
        103.9,
        102.2,
        101.5
    ]
};

const caseFanOptions = {
    rpm: [
        700,
        800,
        900,
        1000,
        1100,
        1150,
        1200,
        1250,
        1280,
        1300,
        1350,
        1400,
        1450,
        1500,
        1550,
        1600,
        1700,
        1725,
        1800,
        1850,
        1900,
        2000,
        2050,
        2150,
        2200,
        2250,
        2300,
        2400,
        2500,
        2600,
        2750,
        2800,
        3000,
        3200,
        3500,
        4000,
        4200,
        4400,
        4500,
        4800,
        5000,
        6000,
        6500,
        7000,
        8000,
        9000,
        10000,
        10200,
        20000
    ],
    size: [
        25,
        30,
        40,
        50,
        60,
        70,
        80,
        92,
        120,
        139,
        140,
        180,
        200,
        230
    ],
    noiseLevel: [
        6.8,
        12.1,
        12.6,
        13.2,
        13.8,
        14,
        14.5,
        14.9,
        15,
        15.2,
        15.5,
        15.8,
        16,
        16.4,
        16.6,
        17,
        17.6,
        17.7,
        17.8,
        17.9,
        18,
        18.1,
        18.2,
        18.6,
        18.8,
        18.9,
        19,
        19.2,
        19.3,
        19.4,
        19.5,
        19.6,
        19.8,
        19.9,
        20,
        20.2,
        20.4,
        21,
        21.1,
        21.2,
        21.6,
        21.9,
        22,
        22.3,
        22.4,
        22.6,
        22.8,
        22.9,
        23,
        23.2,
        23.3,
        23.5,
        23.6,
        23.7,
        23.8,
        23.9,
        24,
        24.3,
        24.4,
        24.6,
        24.7,
        24.8,
        24.9,
        25,
        25.1,
        25.2,
        25.3,
        25.4,
        25.5,
        25.6,
        25.8,
        26,
        26.1,
        26.2,
        26.3,
        26.4,
        26.5,
        27,
        27.2,
        27.5,
        27.8,
        28,
        28.1,
        28.2,
        28.3,
        28.6,
        28.8,
        29,
        29.2,
        29.3,
        29.6,
        29.7,
        29.8,
        30,
        30.1,
        30.3,
        30.4,
        31,
        31.2,
        31.3,
        31.4,
        31.5,
        31.7,
        32,
        32.1,
        32.2,
        32.3,
        32.5,
        32.6,
        32.7,
        33,
        33.2,
        33.4,
        33.5,
        33.6,
        33.7,
        34,
        34.3,
        34.5,
        34.8,
        35,
        35.6,
        35.8,
        36,
        37,
        37.5,
        37.9,
        38,
        38.9,
        41.3,
        41.5,
        41.7,
        43.5,
        48,
        59.5,
        65,
        65.5,
        68.5
    ]
};