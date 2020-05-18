<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["scripts/product.js"]>

    <h2 id="product-type">${product}</h2>
    <div id="products" class="row">
        <#list items as item>
            <div class="card d-inline-block" style="width: 18rem;" id="${item.id}">
                <img class="card-img-top" src="/images/${item.image}" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">${item.name}</h5>
                    <p class="card-text">${item.description}</p>
                    <p class="card-text"><b>Price</b>: ${item.price}$</p>
                    <div>
                        <a href="#" class="addToCart btn btn-primary">Add to cart</a>
                        <input type="number" class="quantity" name="quantity" value="1">
                    </div>
                </div>
            </div>
        </#list>
    </div>

    <#include "partials/pagination.ftlh">

</@main.renderWith>