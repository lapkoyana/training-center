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
<#list lessons as les, usles>
    <div>
        <span>${les.topic}</span>
        <span>${les.dateOfClass}</span>
        <#if les.signOfCompleteness>
        	<#if les.lectureFile??>
		        <span><a href="/files/${les.lectureFile}" download>Скачать файл</a></span>
	        </#if>
        </#if>
        <#if les.signOfCompleteness>
        	<#if usles??>
		    	Тестирование пройдено
			<#else>
	    		<div><a href="/completed-lections/${currentUserId}/interview/${les.id}">Пройти тестирование</a></div>
    		</#if>
    	<#else>
    		Лекция не завершена преподавателем
    	</#if>
    </div>
<#else>
		No lessons
</#list>
</@c.page>