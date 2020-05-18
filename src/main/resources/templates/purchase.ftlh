<#import "partials/main.ftlh" as main />

<@main.renderWith>

<h1>Ваши покупки</h1>
<div id="cart"></div>
<#if purchases??>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Quantity</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <#list purchases as purchase>
    <tbody>
    <tr id="${purchase.id}">
        <th scope="row">${purchase?counter}</th>
        <td>${purchase.productName}</td>
        <td>${purchase.quantity}</td>
        <td>${purchase.price}$</td>
    </tr>
    </tbody>
</#list>
</table>
<#else>
    <p>тут ничего нет!</p>
</#if>
<div>
    <a href="/">назад</a>
</div>

</@main.renderWith>