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
<b>Опрос</b>
<form action="/completed-lections/${currentUserId}/interview/${lesson.id}" method="post">
	<#list questions as question>
	    <div>${question.content}</div>
		<div><input type="text"
                    name="answerContent" placeholder="Введите ответ" /></div>
<#else>
	Преподаватель ничего не добавил
</#list>
	<div>
		<button type="submit">Ответить</button>
		<a href="/completed-lections/${currentUserId}">Назад</a>
	</div>
</form>
</div>
</@c.page>