<#import "/spring.ftl" as spring>
<#--
 объявляем макро-функцию используя которую мы создадим шаблон страницы
 https://freemarker.apache.org/docs/dgui_misc_userdefdir.html
 -->
<#macro renderWith dir="ltr" scripts=[] styles=[]>
    <!doctype html>
    <!doctype html>
    <html lang="${.locale}" dir="${dir}">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- csrf meta tags -->

        <meta name="_csrf_header" content="${_csrf.parameterName!'csrf'}">
        <meta name="_csrf_token" content="${_csrf.token!'no token'}">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <meta name="theme-color" content="#563d7c">

        <!-- Jquery -->
        <script
                src="https://code.jquery.com/jquery-3.5.0.js"
                integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
                crossorigin="anonymous"></script>


        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>
        <!-- Custom styles for this template -->
        <link href="product.css" rel="stylesheet">

        <title>iStore</title>
    </head>
    <body>
    <#--<h1>Hello, world!</h1>-->
    <nav class="site-header sticky-top py-1">
        <div class="container d-flex flex-column flex-md-row justify-content-between">
            <a class="py-2" href="/" aria-label="Product">
                <img src="/images/apple_logo.png" class="d-block mx-auto" role="img" height="24" width="24" alt="">
            </a>

            <a class="py-2 d-none d-md-inline-block" href="/register"><@spring.message "nav.register" /></a>
            <a class="py-2 d-none d-md-inline-block" href="/login"><@spring.message "nav.login" /></a>
            <a class="py-2 d-none d-md-inline-block" href="/cart"><@spring.message "nav.cart" /></a>
            <a class="py-2 d-none d-md-inline-block" href="/purchases"><@spring.message "nav.purchases" /></a>
            <a class="py-2 d-none d-md-inline-block" href="/profile"><@spring.message "nav.profile" /></a>

            <form class="flex box-128" action="/logout" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button class="py-2 d-none d-md-inline-block logout-button">
                    <@spring.message "nav.logout" />
                </button>
            </form>
            <form action="" method="get">
                <label for="lang_select">
                    <@spring.message "label.language" />:
                </label>
                <select name="lang" id="lang_select">
                    <option value="en">English</option>
                    <option value="ru">Русский</option>
                </select>
                <button type="submit">
                    <@spring.message "button.language-change.text" />
                </button>
            </form>
        </div>
    </nav>

    <#--
    например если бы у нашего сайта было какое-то верхнее меню,
    то мы могли бы его тут указать
    и оно появится сразу на всех страницах которые используют
    этот шаблон
    -->

    <div class="container">
        <#--
         элемент nested указывает что в этом место
         будет подставлено некоторое содержимое,
         которое передаётся как тело макро-функции при её вызове
        -->
        <#nested>

    </div>

    <!-- Optional JavaScript -->
    <#list scripts as scriptSource>
        <script src="${scriptSource}"></script>
    </#list>
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>

</#macro>