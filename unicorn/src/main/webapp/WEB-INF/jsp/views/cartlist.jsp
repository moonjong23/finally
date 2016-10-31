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
			<col width="5%" />
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
			<col width="10%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col" class="checkbox">
					<ul>
						<input type="checkbox" checked="checked" name="check_all"
							id="check_all" class="input_check" onclick="funcAllChk(func1)" />
					</ul>
				</th>
				<th scope="col">번호</th>
				<th scope="col">상품명</th>
				<th scope="col">수량</th>
				<th scope="col">가격</th>
				<th scope="col">소계</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${f:length(list) > 0}">
					<c:set var="tot" value="0" />
					<c:set var="totpay" value="1" />
					<c:set var="sel_number" value="1" />
					<c:set var="sub_pay" value="1" />
					<c:set var="stock_config" value="1" />
					<c:forEach items="${list }" var="row" varStatus="status">
						<tr style="text-align: center;">
							<td class="checkbox">
								<ul class="select_subject">
									<input type="checkbox" checked="checked" class="input_check"
										id="${totpay }" name="check_li" value="${status.current.p_id }"
										onclick="func1(this)" />
									<input type="hidden" id="sub_payment"
										value="${row.price * row.number }">
								</ul>
							</td>
							<td>${row.p_id }</td>
							<td class="title" style="text-align: center;"><a
								href="#this" name="title">${row.name }</a> <input type="hidden"
								id="id" value="${row.w_id }"> <input type="hidden"
								id="user_id" value="${userid }"></td>

							<td>
							<input type="number" id="number${sel_number }" name="number" max="100"
								min="1" value="${row.number }" class="wdp_38p" >
								<a href="#this" class="btn" id="number_mod" name="number_mod">수정</a> 	
								<input type="hidden" id="stock${stock_config }" value="${status.current.stock }"> 	
							</td>
							<td id="price_sel">${row.price}</td>
							<td id="sub_pay${sub_pay }">${row.price * row.number}</td>
							<td>
								<a href="#this" class="btn" id="go_empty" name="empty">비우기</a>
								<input type="hidden" id="empty_no" value="${status.current.p_id }">
							</td>
						</tr>
						<c:set var="tot" value="${tot + row.price * row.number }" />
						<c:set var="totpay" value="${totpay + 1 }" />
						<c:set var="sel_number" value="${sel_number + 1 }" />
						<c:set var="sub_pay" value="${sub_pay + 1 }" />
						<c:set var="stock_config" value="${stock_config + 1 }" />
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">조회된 결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<td colspan="5" />	
			<td colspan="1" style="text-align: center;">총 결제금액 :<span id="payment"></span><span> 원</span></td>
			<td/>
		</tbody>
	</table>
	<br>

	<a href="#this" class="btn" id="go_payment" name="payment">결제하기</a>	                        	

<a href="#this" class="btn" id="product_list" >상품목록</a>
<c:set var="session" value="<%=session.getAttribute(\"userid\")%>" />
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
        $(document).ready(function(){
        	$("#go_payment").on("click", function(e){
                e.preventDefault();
                fn_go_payment($(this));
            }); 
        	$("a[name='empty']").on("click", function(e){
                e.preventDefault();
                fn_go_empty($(this));
            }); 
        	$("a[name='number_mod']").on("click", function(e){
                e.preventDefault();
                fn_go_number_mod($(this));
            }); 
        	$("#product_list").on("click", function(e){
                e.preventDefault();
                fn_go_product();
            }); 
        	$("a[name='title']").on("click", function(e){
                e.preventDefault();
                fn_BoardDetail($(this));
            }); 
        	$("a[name='cart']").on("click", function(e){
                e.preventDefault();
                fn_go_cart($(this));
            }); 
        	 var chk = $("#check_all").is(":checked");
             if(chk) $("#payment").text(${tot})	            
        });
        
        function fn_go_payment(pl){   //결제
        	if(confirm("결제하시겠습니까?") == true){
	            var comSubmit = new ComSubmit();
	            var chksize = $("input:checkbox[name='check_li']").length;
	            var id_list = "";
	            var num_list = "";
	            var sub_pay = "";
				for (var i = 1; i < chksize + 1; i++) {
					if($("#" + i).prop("checked") == true){
	            		id_list += $("#" + i).val() + ",";
	            		num_list += $("#number" + i).val() + ",";					
			            sub_pay += $("#sub_pay" + i).text() + ",";
					}
        		
        		}
            comSubmit.setUrl("<c:url value='/views/PaymentList.do' />");
            comSubmit.addParam("user_id", $("#user_id").val());
            comSubmit.addParam("product_id", id_list);    
            comSubmit.addParam("number", num_list);
            comSubmit.addParam("price", sub_pay);
            comSubmit.submit();
			}
        }
        function fn_go_empty(no){
            var comSubmit = new ComSubmit();
            if(confirm("정말 삭제하시겠습니까?") == true){
            comSubmit.setUrl("<c:url value='/views/ProductDel.do' />");
            comSubmit.addParam("user_id", $("#user_id").val());    
            comSubmit.addParam("product_id", no.parent().find("#empty_no").val());
            comSubmit.submit();            	
            }
        }
        function fn_go_number_mod(no){
            var comSubmit = new ComSubmit();
            var number_mod = no.parent().parent().find("#number" + no.parent().parent().find("input[name='check_li']").attr("id"));
     
            if(confirm("수정 하시겠습니까?") == true){
            	if( number_mod.val() < 0){
            		alert("입력값 오류");
            		number_mod.val("1");
            		number_mod.focus();
            		return;
            	}else if(no.parent().parent().find("#stock" + no.parent().parent().find("input[name='check_li']").attr("id")).val() < number_mod.val()){
            		alert("재고부족");
            		number_mod.val(no.parent().parent().find("#stock" + no.parent().parent().find("input[name='check_li']").attr("id")).val()).focus()
            		return;
            	}
            $(no).parent().parent().find("#sub_pay" + $(no).parent().parent().find(".input_check").attr("id")).text($(no).val() * $(no).parent().parent().find("#price_sel").text());
            comSubmit.setUrl("<c:url value='/views/ProductUp.do' />");
            comSubmit.addParam("user_id", $("#user_id").val());    
            comSubmit.addParam("product_id", no.parent().parent().find("#empty_no").val());
            comSubmit.addParam("number", number_mod.val());
            comSubmit.submit();            	
            }
        }
        function fn_go_product(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/views/ProductList.do' />");
            comSubmit.submit();
        }
        function fn_go_cart(obj){ 
            var comSubmit = new ComSubmit();   
            comSubmit.setUrl("<c:url value='/views/WishList.do' />");
            comSubmit.addParam("product_id", obj.parent().parent().find("#product_id").val());
            comSubmit.submit();
        }
        function func1(no){		//ForEach 내부 checkbox 클릭시 호출
        	var check_all = $("input:checkbox[name='check_li']").length
        	var check_sel = $("input:checkbox[name='check_li']:checked").length
        	var sub_payment = parseInt($(no).parent().find("#sub_payment").val())
        	var post_payment = Number($("#payment").text())

        	if(check_all > check_sel){
        		$("#check_all").prop('checked', false)
        	}else{
        		$("#check_all").prop('checked', true)
        	}
        	
        	if($(no).is(":checked")){	
        		post_payment += sub_payment
        		$("#payment").text(post_payment)
			}else{
				$("#payment").text(Number($("#payment").text()) - sub_payment)
			}
        }

        function funcAllChk(b){	//ForEach 밖 최상위 체크박스 클릭시 호출
        	var chk = $("#check_all").is(":checked"); //boolean    		
        	if(chk){	
          	   $(".select_subject input").prop('checked', true); //checked attr == true 
          	   $("#payment").text(${tot})
            }else{ 	
          	   $(".select_subject input").prop('checked', false);	//checked attr == false
               $("#payment").text(Number(0))
            }
        }
</script>
</body>
</html>