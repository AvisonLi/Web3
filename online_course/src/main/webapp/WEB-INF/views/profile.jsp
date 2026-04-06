<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head><title><fmt:message key="profile"/></title></head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2><fmt:message key="update.profile"/></h2>
<form method="post" action="/profile/update">
    <fmt:message key="username"/>: <input type="text" value="${user.username}" disabled/><br/>
    <fmt:message key="fullname"/>: <input type="text" name="fullName" value="${user.fullName}"/><br/>
    <fmt:message key="email"/>: <input type="email" name="email" value="${user.email}"/><br/>
    <fmt:message key="phone"/>: <input type="text" name="phone" value="${user.phone}"/><br/>
    <fmt:message key="new.password"/>: <input type="password" name="password"/><br/>
    <button><fmt:message key="update.profile"/></button>
</form>
<a href="/"><fmt:message key="home"/></a>
</body>
</html>