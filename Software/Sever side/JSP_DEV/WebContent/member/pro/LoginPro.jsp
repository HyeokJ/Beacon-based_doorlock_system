<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="jsp.member.model.MemberDAO" %>
<%@ page import="jsp.member.model.GetIMEI" %>
<html>
<head>
	<title>�α��� ó�� JSP</title>
</head>
<body>
	<%
		// ���ڵ� ó��
		request.setCharacterEncoding("euc-kr"); 
		GetIMEI gi = GetIMEI.getInstance();
		String imei = "12 345678 111111 0";//�ӽ� imei
		// �α��� ȭ�鿡 �Էµ� �湮�� ��ȣ�� �����´�
		String id= request.getParameter("id");
		// DB���� �湮�ڹ�ȣ, imei Ȯ��
		MemberDAO dao = MemberDAO.getInstance();
		int check = dao.loginCheck(id, imei);
		
		// URL �� �α��ΰ��� ���� �޽���
		String msg = "";
		
		if(check == 1)	// �α��� ����
		{ 
			// ���ǿ� ���� ���̵� ����
			session.setAttribute("sessionID", id);
			msg = "../../MainForm.jsp";
		}
		else if(check == 0)//�湮�ڹ�ȣ�� ������ imei�� Ʋ�����
		{
			msg = "../view/LoginForm.jsp?msg=0";	
		}
		else	// �湮�ڹ�ȣ�� Ʋ�����
		{
			msg = "../view/LoginForm.jsp?msg=-1";
		}
		 
		// sendRedirect(String URL) : �ش� URL�� �̵�
		response.sendRedirect(msg);
	%>
</body>
</html>