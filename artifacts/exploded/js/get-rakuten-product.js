function getRakutenProduct(productId) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "rakuten-product.json",
        data: {
            productId: String(productId)
        },
        success: function (data) {

        }, error: function (data) {
            console.log("Some Error Occurred");
        }
    });
}