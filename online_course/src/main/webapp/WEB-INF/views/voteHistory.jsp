<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head><title><fmt:message key="vote.history"/></title></head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2><fmt:message key="vote.records"/></h2>
<c:if test="${empty records}">
    <p><fmt:message key="no.votes"/></p>
</c:if>
<ul>
    <c:forEach items="${records}" var="rec">
        <li><fmt:message key="poll.question.label"/> ${rec.poll.question} → <fmt:message key="selected.option"/> ${rec.selectedOption.optionText} (<fmt:message key="voted.at"/> ${rec.votedAt})</li>
    </c:forEach>
</ul>
<a href="/"><fmt:message key="home"/></a>
</body>
</html>