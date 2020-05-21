<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>

<@main.renderWith>

<h1><@spring.message "header.your_purchases" /></h1>
<div id="cart"></div>
<#if items??>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col"><@spring.message "col.customer" /></th>
        <th scope="col"><@spring.message "col.review" /></th>
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
            <button type="submit"><@spring.message "form.button.submit" /></button>
        </form>
<#--    </#if>-->
<div>
    <a href="/"><@spring.message "link.back" /></a>
</div>

</@main.renderWith>