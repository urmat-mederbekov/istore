<#import "partials/main.ftlh" as main />

<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/login.js"]>-->

    <div class="mx-3">
        <h2>Please sign in</h2>

        <form id="login-form" action="/login" method="post">
<#--    <form id="login-form">-->
            <div>
                <#if error!false >
                    <p style="color:red">Неверные имя пользователя или пароль !</p>
                </#if>
            </div>

            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>

            <h3>Вход</h3>
            <input type="email" name="username" class="form-control mb-1" value="${(dto.email)!''}" placeholder="Email" autofocus>
            <input type="password" name="password" class="form-control mb-1" placeholder="Пароль">
            <button class="btn btn-outline-primary btn-block">Войти</button>

        </form>
        <div class="bottom-box">
            <a href="/forgot-password">Восстановить пароль</a>
            <a href="/">На главную страницу</a>
        </div>
    </div>
</@main.renderWith>
