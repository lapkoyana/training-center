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
<div><b>Добавление лекции:</b></div>
	<div>
		<form method="post" enctype="multipart/form-data">
            <div>
                <input type="text"
                       value="<#if lesson??>${lesson.topic}</#if>" 
                       name="topic" placeholder="Введите название" />
            </div>
            <div>
                <input type="date"
                       value="<#if lesson??>${lesson.dateOfClass}</#if>" name="dateOfClass" placeholder="Введите дату">
            </div>
            <div>
                <input type="file" name="file">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if lesson??>${lesson.id}</#if>" />
            <div>
                <button type="submit">ОК</button>
            </div>
        </form>
	</div>
</@c.page>