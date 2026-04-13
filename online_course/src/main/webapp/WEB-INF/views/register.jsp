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
<c:if test="${not empty error}">
    <div style="color: red; margin-bottom: 15px;">${error}</div>
</c:if>

<form:form method="post" modelAttribute="user">
    <fmt:message key="username"/>: <form:input path="username"/>
    <form:errors path="username" cssStyle="color:red"/><br/>

    <fmt:message key="password"/>: <form:password path="password"/>
    <form:errors path="password" cssStyle="color:red"/><br/>

    <fmt:message key="fullname"/>: <form:input path="fullName"/>
    <form:errors path="fullName" cssStyle="color:red"/><br/>

    <fmt:message key="email"/>: <form:input path="email"/>
    <form:errors path="email" cssStyle="color:red"/><br/>

    <fmt:message key="phone"/>: <form:input path="phone"/>
    <form:errors path="phone" cssStyle="color:red"/><br/>

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