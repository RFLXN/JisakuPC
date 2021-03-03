$(() => {
    $(".add").on("click", (event) => {
        const form = event.target.form;
        const typeName = form.getElementsByTagName("input")[0].value;

        $.ajax({
            method: "GET",
            url: "sessionbuild.json",
            success: (data) => {
                try {
                    let isAlreadyAdded = false;
                    data.products.forEach(product => {
                        const productType = product.productType;
                        if (productType == typeName) {
                            if (productType == "cpu" || productType == "cpu_cooler"
                                || productType == "case" || productType == "mother_board"
                                || productType == "power_supply") {
                                if (product.stack >= 1) {
                                    isAlreadyAdded = true;
                                    alert("もう追加されています。");
                                }
                            }
                        }
                    });

                    if (!isAlreadyAdded) {
                        form.submit();
                    }
                } catch (e) {
                    form.submit();
                }

            },
            error: (err) => {
                form.submit();
            }
        });
    });

    var buildName;
    $("button.mitsu").on("click", (event) => {
        const form = event.target.form;
        buildName = form.getElementsByTagName("input")[0].value;
        console.log(buildName);
        buildName = buildName.replaceAll("　", " ").replaceAll(" ", "");
        console.log(typeof buildName);
        if (buildName == undefined || buildName == "") {
            alert("見積り名を入力してください。");
        } else {
            $.ajax({
                method: "GET",
                url: "sessionbuild.json",
                success: (data) => {
                    try {
                        if (data.products.length > 0) {
                            let isReallySave = confirm("この見積りを保存しますか?");
                            if (isReallySave) {
                                form.submit();
                            }
                        } else {
                            alert("パーツを追加してください。");
                        }
                    } catch (e) {
                        alert("パーツを追加してください。");
                    }
                },
                error: (error) => {
                    alert("パーツを追加してください。");
                }
            });
        }
    });
});

function resetBuild() {
    location.href = "selectbuild?buildNo=new";
}