<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>

<@main.renderWith>
    <h2><@spring.message "header.enter_token" /></h2>
    <#if errorText?? >
        <div>
            <p>${errorText!'-- no message --'}</p>
        </div>
    </#if>
    <div>
    <form action="/reset-password" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>

        <div>
            <label class="flex flex-v-center">
<#--                <p class="label-input-text">Token:</p>-->
                <input type="text" name="token" placeholder="<@spring.message "form.input.token" />" >
            </label>
        </div>
        <div>
            <label class="flex flex-v-center">
<#--                <p class="label-input-text">New Password:</p>-->
                <input type="password" name="newPassword" placeholder="<@spring.message "form.input.new_password" />" >
            </label>
        </div>
        <div>
            <button type="submit"><@spring.message "form.button.submit" /></button>
        </div>
    </form>
    </div>
    <div class="bottom-box">
        <a href="/"><@spring.message "link.back" /></a>
    </div>
</@main.renderWith>