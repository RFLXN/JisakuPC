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
            $("#product-spec-rakuten-url").html(`<a href="${data.productUrl}">${data.productUrl}</a>`);
            let imgUrl = data.mediumImageUrl;
            if (Array.isArray(imgUrl)) {
                imgUrl = imgUrl[0];
            }
            $("#product-image").get(0).src = imgUrl;
            if (dbPrice != data.minPrice) {
                console.log("Price Changed... -> Execute updatePrice");
                $("#product-spec-price").html(`PRICE : ${data.minPrice}`);
                updatePrice(pid, data.minPrice);
            }
        },
        error: (data) => {
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