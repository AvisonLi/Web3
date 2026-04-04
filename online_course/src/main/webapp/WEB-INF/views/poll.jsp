<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head><title><fmt:message key="poll.question"/></title></head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2>${poll.question}</h2>
<form method="post" action="/poll/${poll.id}/vote">
    <c:forEach items="${poll.options}" var="opt">
        <label>
            <input type="radio" name="optionId" value="${opt.id}"
                ${selectedOptionId == opt.id ? 'checked' : ''}>
                ${opt.optionText} (<fmt:message key="votes"/>: ${opt.voteCount})
        </label><br/>
    </c:forEach>
    <sec:authorize access="hasAnyRole('STUDENT','TEACHER')">
        <button type="submit"><fmt:message key="vote"/></button>
    </sec:authorize>
</form>

<h3><fmt:message key="comments"/></h3>
<c:forEach items="${poll.comments}" var="c">
    <div><b>${c.author.fullName}</b>: ${c.content}</div>
</c:forEach>

<sec:authorize access="hasAnyRole('STUDENT','TEACHER')">
    <form method="post" action="/poll/${poll.id}/comment">
        <textarea name="content" rows="3" cols="50" placeholder="<fmt:message key='write.comment'/>"></textarea><br/>
        <button><fmt:message key="submit.comment"/></button>
    </form>
</sec:authorize>
<a href="/"><fmt:message key="home"/></a>
</body>
</html>