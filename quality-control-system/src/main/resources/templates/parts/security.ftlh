<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isLecturer = user.isLecturer()
        isStudent = user.isStudent()
        currentUserId = user.getId()
    >
<#else>
    <#assign
        name = "unknown"
        isLecturer = false
        currentUserId = -1
    >
</#if>