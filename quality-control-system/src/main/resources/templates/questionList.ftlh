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
<b>Список вопросов:</b>
<#list questions as ques>
    <div>
        <form style="display:inline-block" action="/lections/${ques.lesson.id}/questions/${ques.id}" method="post">
	        <textarea name="content" rows="1" cols="20">${ques.content}</textarea>
        	<button type="submit">Редактировать</button>
        </form>
        <form style="display:inline-block" action="/lections/${ques.lesson.id}/questions/${ques.id}/remove" method="post">
        	<button type="submit">Удалить</button>
        </form>
    </div>
<#else>
	Нет вопросов
</#list>
<form action="/lections/${lesson.id}/questions" method="post">
	<p><b>Добавление вопроса</b></p>
	<p><textarea name ="content" rows="3" cols="30">Текст вопроса</textarea></p>
	<p><input type="submit" value="Добавить"></p>
</form>
</@c.page>