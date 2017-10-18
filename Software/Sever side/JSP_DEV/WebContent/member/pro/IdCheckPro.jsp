<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jsp.member.model.MemberDAO" %>

        <%
        request.setCharacterEncoding("utf-8");
      	String id = request.getParameter("idinput");
      	MemberDAO dao = MemberDAO.getInstance();
		
        boolean check = dao.duplicateIdCheck(id);
        if(check){
        	out.print("exist");
        }else{
        	out.print("able");
        }
        %>