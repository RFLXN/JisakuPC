let imgFiles = [];

function uploadImage() {
    loadFiles();
    $.ajax({
        type: "POST",
        enctype: "multipart/form-data",
        data: getFormData(),
        url: "postimage.upload",
        processData: false,
        contentType: false,
        cache: false,
        success: () => {
            console.log("complete!");
        },
        error: (err) => {
            console.log(err);
        }
    });
}

function getFormData() {
    const form = $("#image-form").get(0);
    return new FormData(form);
}

function loadFiles() {
    imgFiles = [];
    const inputs = $(".image-input");

    for (let i = 0; i < inputs.length; i++) {
        for (const f of inputs.get(i).files) {
            imgFiles.push(f);
        }
    }
}

function check() {
    let flag = false;

    if ($("#post-title-input").val() === ""
        || $("#post-desc-input").val() === "" || $("#post-build-input").val() === "") {
        flag = true;
    }

    if (flag) {
        window.alert('タイトルとコメントを入力してください');
    } else {
        $("#post-form").get(0).submit();
    }
}