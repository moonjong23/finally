<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
	<title>Home</title>
</head>
<body>
<%@ include file="/WEB-INF/include/include-top.jspf" %>

<c:if test="${userid != null}">
${userid } 님 환영합니다
</c:if>

<%@ include file="/WEB-INF/include/include-body.jspf" %>
<input type="hidden" id="user_id" value="${userid }">
<%@ include file="/WEB-INF/include/include-top-script.jspf" %>

</body>
</html>
