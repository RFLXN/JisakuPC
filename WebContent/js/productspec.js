function productspec(pid, dbPrice) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "rakuten-product.json",
        data: {
            productId: String(pid)
        },
        success: (data) => {
            console.log("getRakutenProduct: Successfully Load Product!");
            if (data.productUrl !== undefined) {
                $("#product-spec-rakuten-url").html(`<a href="${data.productUrl}" target="_blank">楽天の商品ページに飛びます(外部サイト)</a>`);
            } else {
                $("#product-spec-rakuten-url").html("商品情報の収集に失敗しました。");
            }

            let imgUrl = data.mediumImageUrl;
            if (Array.isArray(imgUrl)) {
                imgUrl = imgUrl[0];
            }
            if (imgUrl !== undefined && !imgUrl.endsWith("undefined")) {
                $("#product-image").get(0).src = imgUrl;
            }

            if (data.minPrice != null && data.minPrice != undefined && data.minPrice != 0 && dbPrice != data.minPrice) {
                console.log("Price Changed... -> Execute updatePrice");
                $("#product-spec-price").html(`PRICE : ${data.minPrice}`);
                updatePrice(pid, data.minPrice);
            }
        },
        error: (data) => {
            $("#product-spec-rakuten-url").html("商品情報の収集に失敗しました。");
            console.log("getRakutenProduct: Some Error Occurred");
        }
    });
}

function updatePrice(pid, price) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "update-product-price.json",
        data: {
            productId: String(pid),
            price: String(price)
        },
        success: (data) => {
            if (data.isUpdated) {
                console.log("update Price: Successfully Updated!");
            } else {
                console.log("update: Price: Failed to Update");
            }
        },
        error: (data) => {
            console.log("update: Price: Failed to Update");
        }
    })
}