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
<div><b>Редактировать лекцию:</b></div>
<#list lesson as les>
	<form method="post" enctype="multipart/form-data">
        <div>
            <input type="text"
                   value="${les.topic}" 
                   name="topic" placeholder="Введите название" />
        </div>
        <div>
            <input type="date"
                   value="${les.dateOfClass}" name="dateOfClass" placeholder="Введите дату">
        </div>
        <div>
            <input type="file" name="file">
        </div>
        <div>
        	<label><input type="checkbox"
        	name="signOfCompleteness" 
        	${les.signOfCompleteness?then('checked', '')}>Признак завершенности</label>
		</div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="hidden" name="id" value="${les.id}" />
        <div>
            <button type="submit">Сохранить изменения</button>
        </div>
    </form>
</#list>
</@c.page>