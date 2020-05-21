<#import "/spring.ftl" as spring>
<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["scripts/cart.js"]>

    <h1>Ваша корзина</h1>
    <div id="cart"></div>
    <#if cartItems??>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><@spring.message "col.name" /></th>
                    <th scope="col"><@spring.message "col.quantity" /></th>
                    <th scope="col"><@spring.message "col.price" /></th>
                    <th scope="col"><@spring.message "col.delete" /></th>
                    <th scope="col"><@spring.message "col.buy" /></th>
                </tr>
                </thead>
        <#list cartItems as item>
                <tbody>
                <tr id="${item.id}">
                    <th scope="row">${item?counter}</th>
                    <td>${item.productName}</td>
                    <td>${item.quantity}</td>
                    <td>${item.price}$</td>
                    <td><a href="#" class="delete btn btn-danger">
                            <@spring.message "col.delete" />
                        </a></td>
                    <td><a href="#" class="buy btn btn-primary">
                            <@spring.message "col.buy" />
                        </a></td>
                </tr>
                </tbody>
        </#list>
            </table>

<#--        <form action="/cart/empty" method="post">-->
<#--            <#if _csrf??>-->
<#--                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>-->
<#--            </#if>-->
<#--            <button>Очистить корзину</button>-->
<#--        </form>-->

    <#else>
        <p><@spring.message "table.empty" /></p>
    </#if>

    <div>
        <a href="/"><@spring.message "link.back" /></a>
    </div>

</@main.renderWith>