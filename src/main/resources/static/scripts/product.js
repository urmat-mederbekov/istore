const baseUrl = 'http://localhost:8080';
const restUrl = 'http://localhost:8080/api';
    async function getProductsById(id) {
    const response = await fetch(restUrl + "/product-types/" + id + '/products');
    if (response.ok) {
        return await response.json();
    } else {
        alert("Error" + response.error());
    }
}
async function load(){
    let product_type_id;
    let product_type_name = document.getElementById('product-type').innerHTML.trim().toLocaleLowerCase();
    if(product_type_name === 'iphone')
        product_type_id = 1;
    else if(product_type_name === 'ipad')
        product_type_id = 2;
    else if(product_type_name === 'mac')
        product_type_id = 3;
    else if(product_type_name === 'airpods')
        product_type_id = 4;
    else if(product_type_name === 'apple watch')
        product_type_id = 5;

    const products = await getProductsById(product_type_id);
    products.forEach(product => {

            let card = document.createElement('div');
            card.className = 'card d-inline-block';
            card.style.width = '18rem';
            card.style.margin = '0 1rem';
            card.innerHTML =
                `<img class="card-img-top" style="height: 65%" src="/images/${product.image}" alt="Card image cap">
            <div class="card-body"">
            <h5 class="card-title">${product.name}</h5>
            <p class="card-text">${product.description}</p>
            <p class="card-text"><b>Price</b>: ${product.price}$</p>
<!--        <a href="#" class="btn btn-primary">Go somewhere</a>-->
        </div>`;
            document.getElementsByClassName('row')[0].append(card);
    })
}

load();