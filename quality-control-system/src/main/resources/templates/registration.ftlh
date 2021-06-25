<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
Добавление пользователя
${message?ifExists}
<@l.login "/registration" />
<a href="/greeting">Назад</a>
</@c.page>