<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
    <form id="frm">
        <table class="login">
            <colgroup>
                <col width="30%">
                <col width="*"/>
            </colgroup>
            <caption>로그인</caption>
            <tbody>
                <tr>
                    <th scope="row">아이디</th>
                    <td><input type="text" id="userid" name="userid" class="wdp_40" /></td>
                </tr>
                <tr>
                    <th scope="row">비밀번호</th>
                    <td><input type="text" id="password" name="password" class="wdp_40" /></td>
                </tr>
                <tr>
                    <td style="text-align: right;"><a href="#this" class="btn" id="login" >로그인</a></td>
                    <td style="text-align: center;"><a href="join.do" class="btn" id="join" >회원가입</a></td>
                </tr>
            </tbody>
        </table>
         

    </form>
     
    <%@ include file="/WEB-INF/include/include-body.jspf" %>
    <script type="text/javascript">
        $(document).ready(function(){
        	$("#login").on("click", function(e){ //로그인체크 버튼
                e.preventDefault();
                fn_login_check();
            });
        });
        
        function fn_login_check(){
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='/views/logincheck.do' />");
            comSubmit.submit();
        }
    </script>
</body>
</html>