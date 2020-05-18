const baseUrl = 'http://localhost:8080';
const csrfToken = document.querySelector('[name=_csrf_token]').getAttribute('content');

function addToCart() {
    $(".addToCart").click(async function(e) {
        e.preventDefault();
        e.stopPropagation();

        let id = $(this).parent().parent().parent().attr('id');
        let value = $(this).parent().children(".quantity").val();
        const data = { id: id,
                        quantity: value};

        const options = {
            method : 'post',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body: JSON.stringify(data)

        };
        await fetch(baseUrl+"/cart", options);
    });
}
addToCart();