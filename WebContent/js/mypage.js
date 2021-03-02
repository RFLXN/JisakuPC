$(() => {
    $(".delete-button").on("click", (event) => {
        event.preventDefault();

        const cf = confirm("本当に削除しますか?");

        if (cf) {
            const form = event.target.form;

            const method = event.target.formMethod;
            const action = event.target.formAction;

            form.method = method;
            form.action = action;

            form.submit();
        }
    });
});

function checkLogout(btn) {
    const c = confirm("ログアウトしますか?");

    if (c) {
        btn.form.submit();
    }
}