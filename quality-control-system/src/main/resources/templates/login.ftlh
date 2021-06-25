<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
Вход
<@l.login "/login" />
<a href="/registration">Добавить пользователя</a>
</@c.page>