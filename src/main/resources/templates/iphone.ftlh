<#-- импортируем основной шаблон-контейнер который содержит в себе разметку для основной структуры страницы -->
<#import "partials/main.ftlh" as main />

<#-- обрабатываем его, и передаём ему список скриптов, которые необходимо подключить-->
<@main.renderWith scripts=["/scripts/product.js"]>

<#--
здесь находится содержимое этой страницы
и оно будет подставлено вместо <#nested> элемента
в макро-функции
https://freemarker.apache.org/docs/dgui_misc_userdefdir.html
-->

    <h2 id="product-type">Iphone</h2>
    <div class="row"></div>

<#--
здесь мы подставляем содержимое другого "частичного" шаблона
который отвечает за отображение элементов для постраничного просмотра данных
https://freemarker.apache.org/docs/ref_directive_include.html
 -->
<#--    <#include "partials/pagination.ftlh">-->

</@main.renderWith>