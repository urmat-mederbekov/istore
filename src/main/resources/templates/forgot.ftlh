<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>

<@main.renderWith>
    <h2><@spring.message "header.enter_email" /></h2>
    <div>
    <form action="/forgot-password" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>

        <div>
            <label class="flex flex-v-center">
<#--                <p class="label-input-text">Адрес почты:</p>-->
                <input type="text" name="email" placeholder="<@spring.message "form.input.email" />" >
            </label>
        </div>
        <div>
            <button type="submit"><@spring.message "form.button.submit" /></button>
        </div>
        <div>
            <#if errors?? >
                <p><@spring.message "error.input_data" /></p>
                <#list errors as fieldError>
                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                </#list>
            </#if>
        </div>
    </form>
    </div>
    <div class="bottom-box">
        <a href="/"><@spring.message "link.back" /></a>
    </div>
</@main.renderWith>