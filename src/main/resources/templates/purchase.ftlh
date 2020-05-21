<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>

<@main.renderWith>

<h1>Ваши покупки</h1>
<div id="cart"></div>
<#if items??>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col"><@spring.message "col.name" /></th>
        <th scope="col"><@spring.message "col.quantity" /></th>
        <th scope="col"><@spring.message "col.price" /></th>
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
    <p><@spring.message "table.empty" /></p>
</#if>
    <#include "partials/pagination.ftlh">
<div>
    <a href="/"><@spring.message "link.back" /></a>
</div>

</@main.renderWith>