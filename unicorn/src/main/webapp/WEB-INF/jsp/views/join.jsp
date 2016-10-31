<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
    <form id="frm">
        <table class="login">
            <colgroup>
                <col width="25%">
                <col width="*"/>
            </colgroup>
            <caption>회원가입</caption>
            <tbody>
                <tr>
                    <th scope="row">아이디</th>
                    <td><input type="text" id="userid" name="userid" class="wdp_90"></input></td>
                </tr>
                <tr>
                    <th scope="row">비밀번호</th>
                    <td><input type="text" id="password" name="password" class="wdp_90"></input></td>
                </tr>
                <tr>
                    <th scope="row">이름</th>
                    <td><input type="text" id="name" name="name" class="wdp_90"></input></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;"><a href="#this" class="btn" id="write" >회원가입</a></td>
                </tr>
                
            </tbody>
        </table>
    </form>
     
    <%@ include file="/WEB-INF/include/include-body.jspf" %>
    <script type="text/javascript">
        $(document).ready(function(){
        	$("#write").on("click", function(e){ //작성하기 버튼
                e.preventDefault();
                fn_join_ok();
            });
        });
        
        function fn_join_ok(){
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='/views/join.do' />");
            comSubmit.submit();
            alert("가입완료");
        }
    </script>
</body>
</html>