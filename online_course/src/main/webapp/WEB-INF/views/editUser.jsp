<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head><title><fmt:message key="edit.user"/></title></head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2><fmt:message key="edit.user"/></h2>
<form:form method="post" modelAttribute="userDto" action="/admin/user/edit/${userId}">
    <fmt:message key="username"/>: <input type="text" value="${userDto.username}" disabled/><br/>
    <fmt:message key="fullname"/>: <form:input path="fullName"/><br/>
    <fmt:message key="email"/>: <form:input path="email"/><br/>
    <fmt:message key="phone"/>: <form:input path="phone"/><br/>
    <fmt:message key="new.password"/>: <form:password path="password"/><br/>
    <button><fmt:message key="save"/></button>
</form:form>
<a href="/admin/users"><fmt:message key="back"/></a>
</body>
</html>