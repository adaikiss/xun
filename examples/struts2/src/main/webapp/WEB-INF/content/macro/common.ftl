<#global js_charset="gbk"/>

<#-- 分页 -->
<#macro page page formId criteriaName range=2>
    <#setting number_format="0">
    <#if (page.totalPage gte 1)>
    <div class="page" formId="${formId}" criteriaName="${criteriaName}">
        <#if (page.page != 1)>
            <span pageNo="1" title="首页"> &lt;&lt; </span>
        <#else>
            <span class="disabled" title="首页"> &lt;&lt; </span>
        </#if>
        <#if (page.page gt 1)>
            <span pageNo="${page.page - 1}" class="prev" title="上一页">&lt;</span>
        <#else>
            <span class="prev disabled" title="上一页">&lt;</span>
        </#if>
        <#local leftRange=range>
        <#local rightRange=range>
        <#if page.page lt range + 1>
            <#local leftRange=page.page - 1>
            <#local rightRange=range * 2 - page.page + 1>
        </#if>
        <#if (page.totalPage - page.page) lt range >
            <#local rightRange=page.totalPage - page.page>
            <#local leftRange=range * 2 - (page.totalPage - page.page)>
        </#if>
        <#list (page.page - leftRange)..(page.page + rightRange) as n>
            <#if n_index=0&&(n gt 1)>...</#if>
            <#if n gt 0 && n lte page.totalPage>
                <#if page.page == n>
                    <span class="cur">${n}</span>
                <#else>
                    <span pageNo="${n}">${n}</span>
                </#if>
            </#if>
            <#if !n_has_next&&(n lt page.totalPage)>...</#if>
        </#list>
        <#if (page.page lt page.totalPage)>
            <span pageNo="${page.page + 1}" class="next" title="下一页">&gt;</span>
        <#else>
            <span class="next disabled" title="下一页">&gt;</span>
        </#if>
        <#if (page.page != page.totalPage)>
            <span pageNo="${page.totalPage}" title="末页"> &gt;&gt; </span>
        <#else>
            <span class="disabled" title="末页"> &gt;&gt; </span>
        </#if>
        共${page.totalPage}页&nbsp; 到 <input class="pageNo" type="text"/> 页
        <span class="btn">确定</span>
        <link href="${static_web}/js/jquery.page.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="${static_web}/js/jquery.page.js"></script>
        <script type="text/javascript">
            $(function () {
                $.page('${formId}', '${criteriaName}', ${page.page}, ${page.pagesize});
            });
        </script>
    </div>
    </#if>
</#macro>

<#-- 下拉表单 -->
<#macro select map nameKey valueKey name="" id="" value="" class="" style="">
<select name="${name}" id="${id}" class="${class}" style="${style}">
    <option value="">请选择</option>
    <#list map as o>
        <option value="${o[valueKey]}" <#if value?exists && (value = o[valueKey])>selected</#if>>${o[nameKey]}</option>
    </#list>
</select>
</#macro>

<#-- 多选表单 -->
<#macro checkbox map nameKey valueKey value=[] name="" class="" style="" delimiter="&nbsp;">
<#if !value?is_sequence>
<#local value=[value]>
</#if>
<#list map as o>
<input autocomplete="off" class="${class}" style="${style}" type="checkbox" name="${name}" value="${o[valueKey]}" <#if value?seq_contains(o[valueKey])>checked</#if>>${o[nameKey]}<#if o_has_next>${delimiter}</#if>
</#list>
</#macro>