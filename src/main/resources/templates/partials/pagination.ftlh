<#import "/spring.ftl" as spring>
<div>
    <#if hasPrev>
        <a id="loadPrev" href="${prevPageLink!''}">&lt;&lt;&lt;<@spring.message "page.previous" /></a>
    <#else>
        <span id="loadPrev">Previous page</span>
    </#if>

    <#if hasNext>
        <a id="loadNext" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize!5}"><@spring.message "page.next" />&gt;&gt;&gt;</a>
    <#else>
        <span><@spring.message "page.next" /></span>
    </#if>
</div>