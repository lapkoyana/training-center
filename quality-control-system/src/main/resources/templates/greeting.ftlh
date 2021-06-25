<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<#include "parts/security.ftlh">
<h3>Привет, пользователь!</h3>
	<#if isLecturer><a href="/lections">Список лекций</a>
					<a href="/students">Список студентов</a>
	</#if>
	<#if isStudent><a href="/completed-lections/${currentUserId}">Список лекций</a></#if>
<div>
	<@l.logout />
</div>
</@c.page>