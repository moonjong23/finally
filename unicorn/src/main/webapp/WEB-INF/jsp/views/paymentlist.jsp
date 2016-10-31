<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<title>장바구니정보</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
<%@ include file="/WEB-INF/include/include-top.jspf" %>
<%@ include file="/WEB-INF/include/include-top-script.jspf" %>
	<table style="border: 1px solid #ccc" class="board_list">
		<colgroup>
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
			<col width="10%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">상품번호</th>
				<th scope="col">상품명</th>
				<th scope="col">수량</th>
				<th scope="col">가격</th>
				<th scope="col">소계</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${f:length(list) > 0}">
				<c:set var="tot" value="0" />
				<c:set var="modi_number" value="0" />
				<c:set var="num" value="0" />
					<c:forEach items="${list }" var="row" varStatus="status">
						<tr>
							<td>
								${row.product_id }
								<input type="hidden" id="user_id" value="${user_id }">
								<input type="hidden" id="product_id${num }" value="${row.product_id }">
								<input type="hidden" id="number${num }" value="${row.number }"> 
								<input type="hidden" id="sub_pay${num }" value="${row.number * row.price}"> 
							</td>
							<td>${row.name }</td>
							<td>${modi_num[modi_number] }</td> 
							<td>${row.price }</td>
							<td>${row.number * row.price }</td>
						</tr>
				<c:set var="tot" value="${tot + row.number * row.price }" />
				<c:set var="modi_number" value="${modi_number + 1 }" />
				<c:set var="num" value="${num + 1 }" />
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">조회된 결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<td colspan="4" />	
			<td colspan="1" style="text-align: center;">총 결제금액 :<span id="payment">${tot }</span><span> 원</span></td>
		</tbody>
	</table>
	<br>
	
	<table style="border: 1px solid #ccc" class="board_list">
		<colgroup>
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
			<col width="10%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col" colspan="2">주문정보</th>
			</tr>
			<tr>
				<th scope="col">카드</th>
				<th><input type="text" name=""></th>				
			</tr>
			<tr>
				<th scope="col">일시불</th>
				<th><input type="text" name=""></th>				
			</tr>
			<tr>
				<th scope="col">뭐</th>
				<th><input type="text" name=""></th>				
			</tr>
			<tr>
				<th scope="col">????</th>
				<th><input type="text" name=""></th>
			</tr>
			<tr>
				<th scope="col">주소</th>
				<th><input type="text" name=""></th>
			</tr>
		</thead>
	</table>
	<br/>
<div style="text-align: center;">
	<a href="#this" class="btn" id="go_payment" name="payment">결제하기</a>	                        	
	<a href="#this" class="btn" id="product_list" >결제취소</a>
</div>
<c:set var="session" value="<%=session.getAttribute(\"userid\")%>" />
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
        $(document).ready(function(){
        	$("#go_payment").on("click", function(e){
                e.preventDefault();
                fn_go_payment($(this));
            });            
        	$("#product_list").on("click", function(e){
                e.preventDefault();
                fn_go_product_list();
            });            
        });
        
        function fn_go_payment(pl){   //결제
            var comSubmit = new ComSubmit();	
            var p_id = "";
            var number = "";
            var sub_pay = "";
            
			for (var i = 0; i < ${modi_number}; i++) {
	          	p_id += $("#product_id" + i).val() + ",";
	          	number += $("#number" + i).val() + ",";									
	          	sub_pay += $("#sub_pay" + i).val() + ",";									
			}
            comSubmit.setUrl("<c:url value='/views/PaymentProcess.do' />");
            comSubmit.addParam("user_id", $("#user_id").val());    
            comSubmit.addParam("product_id", p_id);    
            comSubmit.addParam("number", number);
            comSubmit.addParam("price", sub_pay);
            comSubmit.submit();
        }
        function fn_go_product_list(){   //결제취소
        	var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/views/BasketView.do' />");
            comSubmit.addParam("user_id", $("#user_id").val());
            comSubmit.submit();
        }
</script>
</body>
</html>