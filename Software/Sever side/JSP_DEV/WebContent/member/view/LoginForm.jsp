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
			if(!inputForm.id.value)
			{
				alert("�湮�� ��ȣ�� �Է��ϼ���");	
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
					<td bgcolor="skyblue">�湮�� ��ȣ</td>
					<td><input type="text" name="id" maxlength="50"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="�α���"/>
		</form>
		
		<% 
			// �α����� �߸��� ��� ȭ�鿡 �޽��� ǥ��
			// LoginPro.jsp���� �α��� ó�� ����� ���� �޽����� ������.
			String msg=request.getParameter("msg");
			
			if(msg!=null && msg.equals("-1")) //request.getAttribute("error") == "-1"
			{	
				out.println("<br>");
				out.println("<font color='red' size='5'>���̵� Ȯ���� �ּ���.</font>");
			}
			if(msg!=null && msg.equals("0"))
			{
				out.println("<br>");
				out.println("<font color='red' size='5'>�� ���� �α����Ҽ� �����ϴ�.</font>");
			}
		%>	
	</div>	
</body>
</html>