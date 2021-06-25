<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div>
	<a href="/greeting">Главная</a>
	<a href="/lections">Список лекций</a>
	<a href="/students">Список студентов</a>
</div>
<div>
    <@l.logout />
</div>
<b>Список лекций:</b>
<#list lessons as les>
    <div>
        <span>${les.topic}</span>
        <span>${les.dateOfClass}</span>
        <span>${les.signOfCompleteness?then('завершена', 'не завершена')}</span>
        <a href="/lections/${les.id}">Редактировать</a>
        <form action="/lections/${les.id}/remove" method="post">
        	<button type="submit">Удалить</button>
        </form>
        <a href="/lections/${les.id}/questions">Список вопросов</a>
    </div>
<#else>
	No lessons
</#list>
<div><a href="/lections/new-lection">Добавить лекцию</a></div>
</@c.page>