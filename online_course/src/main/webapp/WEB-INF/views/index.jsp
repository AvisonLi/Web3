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
    <title><fmt:message key="app.title"/></title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h1><fmt:message key="course.name"/></h1>
<p><fmt:message key="course.desc"/></p>

<h2><fmt:message key="lecture.list"/></h2>
<ul>
    <c:forEach items="${lectures}" var="lec">
        <li><a href="/lecture/${lec.id}">${lec.title}</a>
            <sec:authorize access="hasRole('TEACHER')">
                <a href="/admin/lecture/delete/${lec.id}" onclick="return confirm('<fmt:message key="confirm.delete"/>')">[<fmt:message key="delete"/>]</a>
            </sec:authorize>
        </li>
    </c:forEach>
</ul>

<h2><fmt:message key="poll.list"/></h2>
<ul>
    <c:forEach items="${polls}" var="poll">
        <li><a href="/poll/${poll.id}">${poll.question}</a>
            <sec:authorize access="hasRole('TEACHER')">
                <a href="/admin/poll/delete/${poll.id}" onclick="return confirm('<fmt:message key="confirm.delete"/>')">[<fmt:message key="delete"/>]</a>
            </sec:authorize>
        </li>
    </c:forEach>
</ul>

<sec:authorize access="isAnonymous()">
    <a href="/login"><fmt:message key="login"/></a> | <a href="/register"><fmt:message key="register"/></a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <fmt:message key="welcome"/> <sec:authentication property="name"/> |
    <a href="/profile"><fmt:message key="profile"/></a> |
    <a href="/history/votes"><fmt:message key="vote.history"/></a> |
    <a href="/history/comments"><fmt:message key="comment.history"/></a> |
    <sec:authorize access="hasRole('TEACHER')">
        <a href="/admin/users"><fmt:message key="manage.users"/></a> |
        <a href="/admin/lecture/add"><fmt:message key="add.lecture"/></a> |
        <a href="/admin/poll/add"><fmt:message key="add.poll"/></a> |
    </sec:authorize>
    <a href="/logout"><fmt:message key="logout"/></a>
</sec:authorize>
</body>
</html>