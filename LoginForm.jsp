<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<%
		// 인코딩 처리
		request.setCharacterEncoding("euc-kr"); 
	%>
	<title>로그인 화면</title>
	
	<!-- css 파일 분리 -->
	
	<link href='../../css/join_style.css' rel='stylesheet' style='text/css'/>
	
	<script type="text/javascript">
	
		function checkValue()
		{
			inputForm = eval("document.loginInfo");
			if(!inputForm.mem_id.value)
			{
				alert("아이디를 입력하세요");	
				inputForm.mem_id.focus();
				return false;
			}
			if(!inputForm.mem_pw.value)
			{
				alert("비밀번호를 입력하세요");	
				inputForm.mem_pw.focus();
				return false;
			}
		}
		
		function goJoinForm() {
			location.href = "SignUp.jsp";
		}
	</script>

</head>
<body>
	<div id="wrap">
		<form name="loginInfo" method="post" action="../pro/LoginPro.jsp" 
				onsubmit="return checkValue()">
			<table>
				<tr>
					<td bgcolor="lightblue">ID</td>
					<td><input type="text" name="mem_id" maxlength="50"></td>
				</tr>
				<tr>
					<td bgcolor="lightblue">PW</td>
					<td><input type="text" name="mem_pw" maxlength="50"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="로그인"/>
			<input type="button" value="회원가입" onclick="goJoinForm()"/>
		</form>
		
		<% 
			
			// LoginPro.jsp에서 로그인 처리 결과에 따른 메시지를 보낸다.
			String msg=request.getParameter("msg");
			
			if(msg!=null && msg.equals("-1")) //request.getAttribute("error") == "-1"
			{	
				out.println("<br>");
				out.println("<font color='red' size='5'>아이디를 확인해주세요.</font>");
			}
			if(msg!=null && msg.equals("0"))
			{
				out.println("<br>");
				out.println("<font color='red' size='5'>비밀번호를 확인해주세요.</font>");
			}
		%>	
	</div>	
</body>
</html>