<#import "partials/main.ftlh" as main />

<@main.renderWith>

<h1>Ваши покупки</h1>
<div id="cart"></div>
<#if items??>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Customer</th>
        <th scope="col">Review</th>
    </tr>
    </thead>
    <#list items as item>
    <tbody>
    <tr id="${item.id}">
        <th scope="row">${item?counter}</th>
        <td>${item.customer.fullname}</td>
        <td>${item.text}</td>

    </tr>
    </tbody>
</#list>
</table>
<#else>
<p>тут ничего нет!</p>
</#if>
<#--    <#if review??>-->
        <form action="/reviews/${product.id}" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>


            <input type="text" name="text">
            <button type="submit">Submit</button>
        </form>
<#--    </#if>-->
<div>
    <a href="/">назад</a>
</div>

</@main.renderWith>