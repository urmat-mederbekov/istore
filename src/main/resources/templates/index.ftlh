<#import "/spring.ftl" as spring>
<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["/scripts/script.js"]>

    <form id="search-form">
        <input type="text" placeholder="Search" name="search" pattern=".{2,}" required>
        <button type="submit"><@spring.message "form.button.search" /></button>
    </form>

    <section id="products" class="row"></section>

    <#include "partials/pagination.ftlh">

    <h2><@spring.message "header.our_products" /></h2>
    <div class="row types"></div>

</@main.renderWith>