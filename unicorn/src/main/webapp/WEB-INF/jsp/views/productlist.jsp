<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<title>상품정보</title>
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
        <col width="20%"/>
        <col width="15%"/>
        <col width="10%"/>
        <col width="10%"/>
    </colgroup>
    <thead>
        <tr>
            <th scope="col">상품번호</th>
            <th scope="col">상품명</th>
            <th scope="col">가격</th>
            <th scope="col">재고</th>
            <th scope="col"></th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${f:length(list) > 0}">
                <c:forEach items="${list }" var="row" varStatus="status">
                    <tr style="text-align: center;">
                        <td>${row.id }</td>
                        <td class="title" style="text-align: center;">
                        	<a href="#this" name="title" value="${status.current.name }">${row.name }</a>
                        	<input type="hidden" id="id" value="${row.id }">
                        	<input type="hidden" id="user_id" value="${userid }">	                        	
                        	<input type="hidden" id="stock" value="${status.current.stock }">	                        	
                        	<input type="number" id="number" name="number" max="100" min="1" value="1" class="wdp_38p" onchange="config_stock(this);">	
                        </td>
                        <td>${row.price }</td>
                        <td id="stock">${row.stock }</td>
                        <td>
                        	<a href="#this" class="btn" id="go_cart" name="cart">장바구니 담기</a>
                        	<input type="hidden" id="product_id" value="${status.current.id }">	                        	
                        </td>
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
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
        $(document).ready(function(){
        	$("a[name='title']").on("click", function(e){
                e.preventDefault();
                fn_BoardDetail($(this));
            }); 
        	$("a[name='cart']").on("click", function(e){
                e.preventDefault();
                fn_go_cart($(this));
            }); 
        });
        
        function fn_go_cart(obj){
		    var comSubmit = new ComSubmit();
            if(confirm("장바구니에 담겠습니까?") == true){
            	if($("#user_id").val() == ""){ 
            		alert("로그인 후 서비스 이용가능");
			        comSubmit.setUrl("<c:url value='/views/WishList.do' />");
			        comSubmit.addParam("history_back", "n");          	
		          	comSubmit.submit();
		          	return;
            	}else{
		            if(confirm("장바구니로 이동하시겠습니까?") == true){
			            comSubmit.addParam("history_back", "n");          	
	            	}else{
			            comSubmit.addParam("history_back", "y");          	
	            	}
		            comSubmit.setUrl("<c:url value='/views/WishList.do' />");
		            comSubmit.addParam("user_id", $("#user_id").val());    
		            comSubmit.addParam("number", obj.parent().parent().find("#number").val());
		            comSubmit.addParam("product_id", obj.parent().find("#product_id").val());
		           	comSubmit.submit();            	
            	}
           	}else{
            	return;
	        }
        }
        function config_stock(a){
        	   var number = $(a).val()
        	   var stock = $(a).parent().find("#stock").val()
        	   if(Number(number) > Number(stock)){
        		   alert("재고량 보다 많음!!");
        		   $(a).focus()
        		   $(a).val($(a).parent().find("#stock").val())
        	   }
        	   if(Number(number) <= 0){
        		   alert("입력값오류");
        		   $(a).focus()
        		   $(a).val(1)        		   
        	   }
        }
</script>
</body>
</html>