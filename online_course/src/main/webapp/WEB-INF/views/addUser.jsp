<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head><title><fmt:message key="add.user.title"/></title></head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2><fmt:message key="register"/></h2>
<c:if test="${not empty error}">
    <div style="color: red; margin-bottom: 15px;">${error}</div>
</c:if>
<h2><fmt:message key="add.user.title"/></h2>

<form:form method="post" action="/admin/user/add" modelAttribute="user">
    <fmt:message key="username"/>: <form:input path="username"/><br/>
    <fmt:message key="password"/>: <form:password path="password"/><br/>
    <fmt:message key="fullname"/>: <form:input path="fullName"/><br/>
    <fmt:message key="email"/>: <form:input path="email"/><br/>
    <fmt:message key="phone"/>: <form:input path="phone"/><br/>
    <fmt:message key="role"/>:
    <select name="role">
        <option value="student"><fmt:message key="student"/></option>
        <option value="teacher"><fmt:message key="teacher"/></option>
    </select><br/>
    <button><fmt:message key="save"/></button>
</form:form>

<c:if test="${not empty error}">
    <div style="color: red;">${error}</div>
</c:if>

<a href="/admin/users"><fmt:message key="cancel"/></a>
</body>
</html>