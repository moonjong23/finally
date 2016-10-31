<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<title>first</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
<%@ include file="/WEB-INF/include/include-top.jspf" %>
<%@ include file="/WEB-INF/include/include-top-script.jspf" %>
<table style="border:1px solid #ccc" class="board_list">
    <colgroup>
        <col width="10%"/>
        <col width="*"/>
        <col width="15%"/>
        <col width="10%"/>
        <col width="20%"/>
    </colgroup>
    <thead>
        <tr>
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${f:length(list) > 0}">
                <c:forEach items="${list }" var="row">
                    <tr style="text-align: center;">
                        <td>${row.idx }</td>
                        <td class="title" style="text-align: center;">
                        	<a href="#this" name="title">${row.title }</a>
                        	<input type="hidden" id="idx" value="${row.idx }">	
                        </td>
                        <td>${row.user_id }</td>
                        <td>${row.hit_cnt }</td>
                        <td>${row.crea_date }</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4">조회된 결과가 없습니다.</td>
                </tr>
            </c:otherwise>
        </c:choose>
         
    </tbody>
</table>
<br>
<c:set var="session" value="<%=session.getAttribute(\"userid\") %>" />
<a href="#this" class="btn" id="write" >문의하기</a>

<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
         $(document).ready(function(){
        	$("#write").on("click", function(e){
                e.preventDefault();
                fn_BoardWrite();
            }); 
        	$("a[name='title']").on("click", function(e){
                e.preventDefault();
                fn_BoardDetail($(this));
            }); 
        });
        
        function fn_BoardWrite(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/views/BoardWrite.do' />");
            comSubmit.submit();
        }
        function fn_BoardDetail(obj){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/views/BoardDetail.do' />");
            comSubmit.addParam("idx", obj.parent().find("#idx").val());
            comSubmit.submit();
        } 
        
</script>
</body>
</html>