<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<%
		// ���ڵ� ó��
		request.setCharacterEncoding("euc-kr"); 
	%>
	<title>�α��� ȭ��</title>
	
	<!-- css ���� �и� -->
	
	<link href='../../css/join_style.css' rel='stylesheet' style='text/css'/>
	
	<script type="text/javascript">
	
		function checkValue()
		{
			inputForm = eval("document.loginInfo");
			if(!inputForm.mem_id.value)
			{
				alert("���̵� �Է��ϼ���");	
				inputForm.mem_id.focus();
				return false;
			}
			if(!inputForm.mem_pw.value)
			{
				alert("��й�ȣ�� �Է��ϼ���");	
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
			<input type="submit" value="�α���"/>
			<input type="button" value="ȸ������" onclick="goJoinForm()"/>
		</form>
		
		<% 
			
			// LoginPro.jsp���� �α��� ó�� ����� ���� �޽����� ������.
			String msg=request.getParameter("msg");
			
			if(msg!=null && msg.equals("-1")) //request.getAttribute("error") == "-1"
			{	
				out.println("<br>");
				out.println("<font color='red' size='5'>���̵� Ȯ�����ּ���.</font>");
			}
			if(msg!=null && msg.equals("0"))
			{
				out.println("<br>");
				out.println("<font color='red' size='5'>��й�ȣ�� Ȯ�����ּ���.</font>");
			}
		%>	
	</div>	
</body>
</html>