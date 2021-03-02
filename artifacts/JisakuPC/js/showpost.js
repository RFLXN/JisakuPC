$(() => {
    loadProductImages();
});

function loadProductImages() {
    const valueElements = $(".product-no");
    const pidList = [];
    let index = 0;
    for (let i = 0; i < valueElements.length; i++) {
        const pid = valueElements.get(i).innerText;
        console.log(pid);
        if (pid != undefined && pid != "" && pid != "undefined") {
            pidList.push(pid);
        }
    }

    loadProduct();

    function loadProduct() {
        const pid = pidList[index];
        if (pidList.length === index) {
            return console.log("Load Complete");
        } else {
            if (pid == undefined || pid == "" || pid == "undefined") {
                index = index + 1;
                loadProduct();
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
            $(`#image-${pid}`).get(0).src = imageUrl;
        }
    }
}

/*
function loadProductImages() {
    const valueElements = $(".product-no");
    const pidList = [];
    let index = 0;
    for (let i = 0; i < valueElements.length; i++) {
        const pid = valueElements.get(i).innerText;
        pidList.push(pid);
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
            }, 180);
        });
    }

    function applyImage(imageUrl, pid) {
        $(`#image-${pid}`).get(0).src = imageUrl;
    }
}
*/