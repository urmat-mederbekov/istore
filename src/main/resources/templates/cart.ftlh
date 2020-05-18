<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["scripts/cart.js"]>

    <h1>Ваша корзина</h1>
    <div id="cart"></div>
    <#if cartItems??>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col">Delete</th>
                    <th scope="col">Buy</th>
                </tr>
                </thead>
        <#list cartItems as item>
                <tbody>
                <tr id="${item.id}">
                    <th scope="row">${item?counter}</th>
                    <td>${item.productName}</td>
                    <td>${item.quantity}</td>
                    <td>${item.price}$</td>
                    <td><a href="#" class="delete btn btn-danger">Delete</a></td>
                    <td><a href="#" class="buy btn btn-danger">Buy</a></td>
                </tr>
                </tbody>
        </#list>
            </table>

        <form action="/cart/empty" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <button>Очистить корзину</button>
        </form>

    <#else>
        <p>тут ничего нет!</p>
    </#if>

    <div>
        <a href="/">назад</a>
    </div>

</@main.renderWith>