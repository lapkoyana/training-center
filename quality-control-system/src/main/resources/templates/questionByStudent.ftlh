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
<b>Список ответов</b>
<form method="post" action="/students">
    <input type="text" name="filter1" placeholder="Введите id студента">
    <input type="text" name="filter2" placeholder="Введите id лекции">
    <button type="submit">Вывод</button>
</form>
<br>
<#if user?? && lesson??>
	Ответы студента ${user.username} к лекции ${lesson.topic}:
</#if>
<#list answers as answer>
    <div>
        <div>${answer.question.content}</div>
    	<div>Ответ: ${answer.content}</div>
    	<div>Дата: ${answer.dateOfReply}</div>
    </div>
    <br>
<#else>
	Заполните поля сверху
</#list>
</@c.page>