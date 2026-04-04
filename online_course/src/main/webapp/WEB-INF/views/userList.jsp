<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head><title><fmt:message key="user.list"/></title></head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2><fmt:message key="user.list"/></h2>
<table border="1">
    <tr><th><fmt:message key="id"/></th><th><fmt:message key="username"/></th><th><fmt:message key="fullname"/></th><th><fmt:message key="email"/></th><th><fmt:message key="role"/></th><th><fmt:message key="action"/></th></tr>
    <c:forEach items="${users}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.fullName}</td>
            <td>${u.email}</td>
            <td>${u.role}</td>
            <td>
                <a href="/admin/user/edit/${u.id}"><fmt:message key="edit"/></a>
                <a href="/admin/user/delete/${u.id}" onclick="return confirm('<fmt:message key="confirm.delete"/>')"><fmt:message key="delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/"><fmt:message key="home"/></a>
</body>
</html>