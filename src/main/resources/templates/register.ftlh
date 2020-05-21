<#import "/spring.ftl" as spring>
<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["scripts/registration.js"]>

    <div class="mx-auto mt-3 w-50">
        <form id="registration-form">

            <h3><@spring.message "nav.register" /></h3>
            <input type="email" name="email" class="form-control mb-1" value="${(dto.email)!''}" placeholder="<@spring.message "form.input.email" />">
            <input type="text" name="name"  pattern=".{4,24}" class="form-control mb-1" value="${(dto.name)!''}" placeholder="<@spring.message "form.input.fullname" />">
            <input type="password" name="password" class="form-control mb-1" placeholder="<@spring.message "form.input.password" />">
            <button class="btn btn-outline-primary btn-block"><@spring.message "nav.register" /></button>

            <div>
                <#if errors?? >
                    <p><@spring.message "error.input_data" /></p>
                    <#list errors as fieldError>
                        <p>${fieldError.field} - <@spring.messageText fieldError.codes[0] fieldError.defaultMessage /> </p>

                    </#list>
                </#if>
            </div>
        </form>

        <div class="bottom-box">
            <a href="/"><@spring.message "link.back" /></a>
        </div>
    </div>
</@main.renderWith>