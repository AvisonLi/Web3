<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${pageContext.response.locale}" />
<fmt:setBundle basename="messages" />
<html>
<head>
    <title><fmt:message key="add.poll"/></title>
    <form:errors path="question" cssStyle="color:red"/><br/>
    <form:errors path="options" cssStyle="color:red"/><br/>
    <script>
        function validateOptions() {
            let options = document.querySelectorAll('input[name^="options"]');
            for (let i = 0; i < options.length; i++) {
                if (options[i].value.trim() === "") {
                    let errorMsg = '';
                    <c:choose>
                    <c:when test="${pageContext.response.locale eq 'zh_HK'}">
                    errorMsg = '請填寫全部 5 個選項。';
                    </c:when>
                    <c:otherwise>
                    errorMsg = 'Please fill in all 5 options.';
                    </c:otherwise>
                    </c:choose>
                    alert(errorMsg);
                    options[i].focus();
                    return false;
                }
            }
            return true;
        }
    </script>
</head>
<body>
<div style="text-align: right;">
    <a href="?lang=en">English</a> | <a href="?lang=zh_HK">繁體中文</a>
</div>
<h2><fmt:message key="add.new.poll"/></h2>

<form:form method="post" modelAttribute="poll" onsubmit="return validateOptions()">
    <fmt:message key="poll.question"/>: <form:input path="question" required="true"/><br/>
    <fmt:message key="option"/> 1: <form:input path="options[0]" required="true"/><br/>
    <fmt:message key="option"/> 2: <form:input path="options[1]" required="true"/><br/>
    <fmt:message key="option"/> 3: <form:input path="options[2]" required="true"/><br/>
    <fmt:message key="option"/> 4: <form:input path="options[3]" required="true"/><br/>
    <fmt:message key="option"/> 5: <form:input path="options[4]" required="true"/><br/>
    <button><fmt:message key="save"/></button>
</form:form>
<a href="/"><fmt:message key="cancel"/></a>
</body>
</html>