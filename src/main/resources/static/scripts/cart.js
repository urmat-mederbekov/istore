const baseUrl = 'http://localhost:8080';
const csrfToken = document.querySelector('[name=_csrf_token]').getAttribute('content');

function deleteOne() {
    $(".delete").click(async function(e) {
        e.preventDefault();
        e.stopPropagation();

        let id = $(this).parent().parent().attr('id');
        const data = { id: id };

        const options = {
            method : 'post',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body: JSON.stringify(data)
        };
        await fetch(baseUrl+"/cart/id", options);
        window.location.href = baseUrl + "/cart";
    });
}
function buyOne() {
    $(".buy").click(async function(e) {
        e.preventDefault();
        e.stopPropagation();

        let id = $(this).parent().parent().attr('id');
        const data = { id: id };

        const options = {
            method : 'post',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body: JSON.stringify(data)
        };
        await fetch(baseUrl+"/purchase/id", options);
        window.location.href = baseUrl + "/purchases";
    });
}
deleteOne();
buyOne();
