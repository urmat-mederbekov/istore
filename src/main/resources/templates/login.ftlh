<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>

<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/login.js"]>-->

    <div class="mx-auto mt-3 w-50">
        <form id="login-form" action="/login" method="post">
<#--    <form id="login-form">-->
            <div>
                <#if error!false >
                    <p style="color:red"><@spring.message "error.input_data" /></p>
                </#if>
            </div>

            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>

            <h3><@spring.message "nav.login" /></h3>
            <input type="email" name="username" class="form-control mb-1" value="${(dto.email)!''}" placeholder="<@spring.message "form.input.email" />" autofocus>
            <input type="password" name="password" class="form-control mb-1" placeholder="<@spring.message "form.input.password" />">
            <button class="btn btn-outline-primary btn-block"><@spring.message "nav.login" /></button>

        </form>
        <div class="bottom-box">
            <a href="/forgot-password"><@spring.message "link.restore_password" /></a>
            <a href="/"><@spring.message "link.back" /></a>
        </div>
    </div>
</@main.renderWith>
