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
			if(!inputForm.id.value)
			{
				alert("방문자 번호를 입력하세요");	
				inputForm.id.focus();
				return false;
			}
			
		}
	
	</script>

</head>
<body>
	<div id="wrap">
		<form name="loginInfo" method="post" action="../pro/LoginPro.jsp" 
				onsubmit="return checkValue()">
		

			
			<table>
				<tr>
					<td bgcolor="skyblue">방문자 번호</td>
					<td><input type="text" name="id" maxlength="50"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="로그인"/>
		</form>
		
		<% 
			// 로그인이 잘못될 경우 화면에 메시지 표시
			// LoginPro.jsp에서 로그인 처리 결과에 따른 메시지를 보낸다.
			String msg=request.getParameter("msg");
			
			if(msg!=null && msg.equals("-1")) //request.getAttribute("error") == "-1"
			{	
				out.println("<br>");
				out.println("<font color='red' size='5'>아이디를 확인해 주세요.</font>");
			}
			if(msg!=null && msg.equals("0"))
			{
				out.println("<br>");
				out.println("<font color='red' size='5'>이 기기로 로그인할수 없습니다.</font>");
			}
		%>	
	</div>	
</body>
</html>