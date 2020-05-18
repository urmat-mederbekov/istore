<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["scripts/registration.js"]>
    <h2>Please register</h2>
    <div class="mx-3">
        <form id="registration-form">

            <h3>Регистрация</h3>
            <input type="email" name="email" class="form-control mb-1" value="${(dto.email)!''}" placeholder="Email">
            <input type="text" name="name"  pattern=".{4,24}" class="form-control mb-1" value="${(dto.name)!''}" placeholder="Полное имя">
            <input type="password" name="password" class="form-control mb-1" placeholder="Пароль">
            <button class="btn btn-outline-primary btn-block">Зарегистрироваться</button>

            <div>
                <#if errors?? >
                    <p>Входные данные с ошибками!</p>
                    <#list errors as fieldError>
                        <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                    </#list>
                </#if>
            </div>
        </form>

        <div class="bottom-box">
            <a href="/">На главную страницу</a>
        </div>
    </div>
</@main.renderWith>