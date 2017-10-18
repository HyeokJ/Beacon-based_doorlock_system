<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
<%@ page import="jsp.member.model.MemberDAO" %>
<%@ page import="jsp.member.model.GetIMEI" %>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>

<%
		// 인코딩 처리
		request.setCharacterEncoding("utf-8");
	
		// 로그인 화면에 입력된 방문자 번호를 가져온다
		String id = request.getParameter("mem_id");
		String pw = request.getParameter("mem_pw");
		// DB에서 방문자번호, 비밀번호 확인
		MemberDAO dao = MemberDAO.getInstance();
		
		int check = dao.loginCheck(id, pw);
		// URL 및 로그인관련 전달 메시지
		
		
		if(check == 1)	// 로그인 성공
		{ 
			// 세션에 현재 아이디 세팅
			session.setAttribute("sessionID", id);
			out.print("success");
		}
		else if(check == 0) // 비밀번호가 틀릴 경우
		{
			out.print("wrong pw");	
		}
		else	// 아이디가 틀릴 경우
		{
			out.print("wrong id");
		}
	%>