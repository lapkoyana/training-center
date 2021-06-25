<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<#include "parts/security.ftlh">
<div>
	<a href="/greeting">Главная</a>
	<a href="/completed-lections/${currentUserId}">Список лекций</a>
</div>
<div>
    <@l.logout />
</div>
<b>Список лекций:</b>
<#list lessons as lesson>
    <div>
        <span>${lesson.topic}</span>
        <span>${lesson.dateOfClass}</span>
        <#if lesson.signOfCompleteness>
        	<#if lesson.lectureFile??>
		        <span><a href="/files/${lesson.lectureFile}" download>Скачать файл</a></span>
	        </#if>
        </#if>
        <#if lesson.signOfCompleteness>
	        <div><a href="/completed-lections/${currentUserId}/interview/${lesson.id}">Пройти тестирование</a></div>
        </#if>
    </div>
<#else>
	No lessons
</#list>
</@c.page>