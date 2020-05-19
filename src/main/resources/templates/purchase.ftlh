<#import "partials/main.ftlh" as main />

<@main.renderWith>

<h1>Ваши покупки</h1>
<div id="cart"></div>
<#if items??>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Quantity</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <#list items as item>
    <tbody>
    <tr id="${item.id}">
        <th scope="row">${item?counter}</th>
        <td>${item.productName}</td>
        <td>${item.quantity}</td>
        <td>${item.price}$</td>
    </tr>
    </tbody>
</#list>
</table>
<#else>
    <p>тут ничего нет!</p>
</#if>
    <#include "partials/pagination.ftlh">
<div>
    <a href="/">назад</a>
</div>

</@main.renderWith>