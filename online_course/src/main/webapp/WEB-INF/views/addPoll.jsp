<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head><title><fmt:message key="add.poll"/></title></head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2><fmt:message key="add.new.poll"/></h2>
<form:form method="post" modelAttribute="poll">
    <fmt:message key="poll.question"/>: <form:input path="question"/><br/>
    <fmt:message key="option"/> 1: <form:input path="options[0]"/><br/>
    <fmt:message key="option"/> 2: <form:input path="options[1]"/><br/>
    <fmt:message key="option"/> 3: <form:input path="options[2]"/><br/>
    <fmt:message key="option"/> 4: <form:input path="options[3]"/><br/>
    <fmt:message key="option"/> 5: <form:input path="options[4]"/><br/>
    <button><fmt:message key="save"/></button>
</form:form>
<a href="/"><fmt:message key="cancel"/></a>
</body>
</html>