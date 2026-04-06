<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head><title><fmt:message key="register"/></title></head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2><fmt:message key="register"/></h2>
<form:form method="post" modelAttribute="user">
    <fmt:message key="username"/>: <form:input path="username"/><br/>
    <fmt:message key="password"/>: <form:password path="password"/><br/>
    <fmt:message key="fullname"/>: <form:input path="fullName"/><br/>
    <fmt:message key="email"/>: <form:input path="email"/><br/>
    <fmt:message key="phone"/>: <form:input path="phone"/><br/>
    <fmt:message key="role"/>: <select name="role">
    <option value="student"><fmt:message key="student"/></option>
    <option value="teacher"><fmt:message key="teacher"/></option>
</select><br/>
    <button><fmt:message key="register"/></button>
</form:form>
<a href="/login"><fmt:message key="login"/></a>
<a href="/"><fmt:message key="home"/></a>
</body>
</html>