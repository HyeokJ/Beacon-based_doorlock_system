<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="jsp.member.model.MemberDAO" %>
<%@ page import="jsp.member.model.GetIMEI" %>
<html>
<head>
	<title>로그인 처리 JSP</title>
</head>
<body>
	<%
		// 인코딩 처리
		request.setCharacterEncoding("euc-kr"); 
		GetIMEI gi = GetIMEI.getInstance();
		String imei = "12 345678 111111 0";//임시 imei
		// 로그인 화면에 입력된 방문자 번호를 가져온다
		String id= request.getParameter("id");
		// DB에서 방문자번호, imei 확인
		MemberDAO dao = MemberDAO.getInstance();
		int check = dao.loginCheck(id, imei);
		
		// URL 및 로그인관련 전달 메시지
		String msg = "";
		
		if(check == 1)	// 로그인 성공
		{ 
			// 세션에 현재 아이디 세팅
			session.setAttribute("sessionID", id);
			msg = "../../MainForm.jsp";
		}
		else if(check == 0)//방문자번호는 있으나 imei가 틀린경우
		{
			msg = "../view/LoginForm.jsp?msg=0";	
		}
		else	// 방문자번호가 틀릴경우
		{
			msg = "../view/LoginForm.jsp?msg=-1";
		}
		 
		// sendRedirect(String URL) : 해당 URL로 이동
		response.sendRedirect(msg);
	%>
</body>
</html>