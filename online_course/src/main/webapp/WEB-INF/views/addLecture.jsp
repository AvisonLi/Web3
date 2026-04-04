<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head><title><fmt:message key="add.lecture"/></title></head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2><fmt:message key="add.new.lecture"/></h2>
<form:form method="post" modelAttribute="lecture">
    <fmt:message key="lecture.title"/>: <form:input path="title"/><br/>
    <fmt:message key="lecture.summary"/>: <form:textarea path="summary" rows="5" cols="40"/><br/>
    <button><fmt:message key="save"/></button>
</form:form>
<a href="/"><fmt:message key="cancel"/></a>
</body>
</html>