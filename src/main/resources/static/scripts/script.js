
const baseUrl = 'http://localhost:8080';
const restUrl = 'http://localhost:8080/api';

async function getTypes() {
    const response = await fetch(restUrl + '/product-types');
    if (response.ok) {
        return await response.json();
    } else {
        alert("Error" + response.error());
    }
}
async function getProducts(){
    const response = await fetch(restUrl+'products');
    if(response.ok)
        return await response.json();
    else
        alert("Error" + response.error());
}
async function getProductsByText(text){
    const response = await fetch(restUrl+'/' +
        'products/search=' + text);
    if(response.ok)
        return await response.json();
    else
        alert("Error" + response.error());
}
async function load(){
    const types = await getTypes();
    types.forEach(type => {
        let product_link;
        let product_type_name = type.name.toLocaleLowerCase();
        if(product_type_name === 'iphone')
            product_link = 'iphones';
        else if(product_type_name === 'ipad')
            product_link = 'ipads';
        else if(product_type_name === 'mac')
            product_link = 'macs';
        else if(product_type_name === 'airpods')
            product_link = 'airpods';
        else if(product_type_name === 'apple watch')
            product_link = 'apple-watches';

        let card = document.createElement('a');
        card.href = product_link;
        card.className = 'card d-inline-block';
        card.style.width = '18rem';
        card.innerHTML =
            `<div class="card d-inline-block" style="width: 18rem">
                <img class="card-img-top" style="height: 65%" src="/images/${type.icon}" alt="Card image cap">
                <div class="card-body"">
                <h5 class="card-title">${type.name}</h5>
                </div>
            </div>`;
        document.getElementsByClassName('row')[0].append(card);
    })
}
async function search(e) {
    e.preventDefault();
    let text = document.querySelector('[name=search]').value;
    const products = await getProductsByText(text);
    document.getElementById('products').innerHTML = '';
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
        document.getElementById('products').append(card);
    })

}

const searchForm = document.getElementById('search-form');
searchForm.addEventListener('submit', search);

load();



// 'use strict';

const serverPath = 'http://localhost:8080/api';

const getCurrentPage = () => {
    const loc = (typeof window.location !== 'string') ? window.location.search : window.location;
    const index = loc.indexOf('page=');
    return index === -1 ? 1 : parseInt(loc[index + 5]) + 1;
};

const constructGetUrl = (url, queryParams) => {
    for (let key in queryParams) {
        if (queryParams.hasOwnProperty(key)) {
            console.log(key, queryParams[key]);
        }
    }
    // TODO
};

(function loadPlacesPageable() {

    const productTemplate = (product) => {
        const template = `<img class="card-img-top" style="height: 65%" src="/images/${product.image}" alt="Card image cap">
            <div class="card-body"">
            <h5 class="card-title">${product.name}</h5>
            <p class="card-text">${product.description}</p>
            <p class="card-text"><b>Price</b>: ${product.price}$</p>
<!--        <a href="#" class="btn btn-primary">Go somewhere</a>-->
        </div>`;

        const elem = document.createElement('div');
        elem.innerHTML = template.trim();

        // return inner div with classes flex etc
        return elem.children[0];
    };

    const fetchProducts = async (page, size) => {
        const placesPath = `${restUrl}/products?page=${page}&size=${size}`;
        const data = await fetch(placesPath, {cache: 'no-cache'});
        return data.json();
    };

    const loadNextProductsGenerator = (loadNextElement, page) => {
        return async (event) => {
            event.preventDefault();
            event.stopPropagation();

            const defaultPageSize = loadNextElement.getAttribute('data-default-page-size');
            const data = await fetchProducts(page, defaultPageSize);


            loadNextElement.hidden = data.length === 0;
            if (data.length === 0) {
                return;
            }
            const list = document.getElementById('products');
            for (let item of data) {
                list.append(productTemplate(item));
            }

            loadNextElement.addEventListener('click', loadNextProductsGenerator(loadNextElement, page + 1), {once: true});
            window.scrollTo(0, document.body.scrollHeight);
        };
    };
    document.getElementById('loadPrev').hidden = true;
    const loadNextElement = document.getElementById('loadNext');
    if (loadNextElement !== null) {
        loadNextElement.innerText = "Load more products";
        loadNextElement.addEventListener('click', loadNextProductsGenerator(loadNextElement, getCurrentPage()), {once: true});
    }

})();

