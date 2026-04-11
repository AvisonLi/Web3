<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head><title>${lecture.title}</title></head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2>${lecture.title}</h2>
<h3><fmt:message key="download.materials"/></h3>
<ul>
    <c:forEach items="${lecture.materials}" var="mat">
        <li><a href="/files/${mat.filePath}">${mat.fileName}</a>
            <sec:authorize access="hasRole('TEACHER')">
                <a href="/admin/material/delete/${mat.id}">[<fmt:message key="delete"/>]</a>
            </sec:authorize>
        </li>
    </c:forEach>
</ul>
<sec:authorize access="hasRole('TEACHER')">
    <form method="post" action="/admin/lecture/${lecture.id}/upload" enctype="multipart/form-data">
        <input type="file" name="file" required>
        <button><fmt:message key="upload"/></button>
    </form>
</sec:authorize>

<h3><fmt:message key="lecture.summary"/></h3>
<p>${lecture.summary}</p>

<h3><fmt:message key="comments"/></h3>
<c:forEach items="${lecture.comments}" var="c">
    <div class="comment">
        <b>${c.author.fullName}</b> (${c.createdAt}): ${c.content}
        <sec:authorize access="hasRole('TEACHER')">
            <a href="/admin/lecture/comment/delete/${c.id}?lectureId=${lecture.id}"
               onclick="return confirm('<fmt:message key="confirm.delete"/>')"
               style="color: red; margin-left: 10px;">[<fmt:message key="delete"/>]</a>
        </sec:authorize>
    </div>
</c:forEach>

<sec:authorize access="hasAnyRole('STUDENT','TEACHER')">
    <form:form method="post" action="/lecture/${lecture.id}/comment" modelAttribute="newComment">
        <form:textarea path="content" rows="3" cols="50" placeholder="<fmt:message key='write.comment'/>"/>
        <br/><button><fmt:message key="submit.comment"/></button>
    </form:form>
</sec:authorize>
<a href="/"><fmt:message key="home"/></a>
</body>
</html>