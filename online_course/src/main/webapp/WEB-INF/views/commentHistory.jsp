<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head><title><fmt:message key="comment.history"/></title></head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2><fmt:message key="comment.records"/></h2>
<c:if test="${empty comments}">
    <p><fmt:message key="no.comments"/></p>
</c:if>
<ul>
    <c:forEach items="${comments}" var="c">
        <li>
            <c:if test="${c.lecture != null}">
                <fmt:message key="lecture.title"/> 《${c.lecture.title}》:
            </c:if>
            <c:if test="${c.poll != null}">
                <fmt:message key="poll.question"/> 《${c.poll.question}》:
            </c:if>
            <fmt:message key="comment.content"/> ${c.content} (<fmt:message key="on"/> ${c.createdAt})
        </li>
    </c:forEach>
</ul>
<a href="/"><fmt:message key="home"/></a>
</body>
</html>