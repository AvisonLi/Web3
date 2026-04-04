<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="login"/></title>
</head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2><fmt:message key="login"/></h2>
<form method="post" action="/login">
    <fmt:message key="username"/>: <input type="text" name="username"/><br/>
    <fmt:message key="password"/>: <input type="password" name="password"/><br/>
    <button><fmt:message key="login"/></button>
</form>
<a href="/register"><fmt:message key="register"/></a>
</body>
</html>