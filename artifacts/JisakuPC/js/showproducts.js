$(() => {
    loadSpecSearch();
});

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
        $("#product-spec-search").append(["<br>",
            `<p id="option-${specOptionName}-value-view">${specOptionName}</p>`,
            `<div id='spec-search-option-${specOptionName}'></div>`]);
        const specOptionValues = specOptions[specOptionName];
        if (typeof specOptionValues[0] === "number") {
            const maxValue = Math.max.apply(null, specOptionValues);
            const minValue = Math.min.apply(null, specOptionValues);

            $(`#option-${specOptionName}-value-view`).html(`${specOptionName}: ${minValue} - ${maxValue}`);

            $("#product-spec-search")
                .append(`<input type="hidden" id="option-${specOptionName}-value" name="${specOptionName}" />`);

            let sliderOption = {
                range: true,
                min: minValue,
                max: maxValue,
                step: 1,
                values: [minValue, maxValue],
                slide: (event, ui) => {
                    $(`#option-${specOptionName}-value`).attr("value", `${ui.values[0]},${ui.values[1]}`);
                    $(`#option-${specOptionName}-value-view`).html(`${specOptionName}: ${ui.values[0]} - ${ui.values[1]}`);
                }
            };

            if (!Number.isInteger(maxValue) || !Number.isInteger(minValue)) {
                sliderOption.step = 0.1;
            }

            $(`#spec-search-option-${specOptionName}`).slider(sliderOption);
        } else {
            for (const specOptionValue of specOptionValues) {
                $(`#spec-search-option-${specOptionName}`)
                    .append(`<input type="radio" name="${specOptionName}" value="${specOptionValue}" ` +
                        `id="spec-${specOptionName}-${specOptionValue}">` +
                        `<label class="sort" for="spec-${specOptionName}-${specOptionValue}">${specOptionValue}</label>`);
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
        default:
            return null;
    }
}

function getUrlParameter(parameterName) {
    let result = "";
    location.search.substring(1).split("&").forEach((item) => {
        const param = item.split("=");
        if (param[0] === parameterName) {
            result = decodeURIComponent(param[1]);
        }
    });

    return result;
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