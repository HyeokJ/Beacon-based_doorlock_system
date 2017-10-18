<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.member.model.MemberDAO" %>
<%@ page import="jsp.member.model.MemberBean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>회원가입 처리 JSP</title>
    
    <!-- css 파일 분리 -->
    <link href='../../css/join_style.css' rel='stylesheet' style='text/css'/>
    
</head>
<body>
    <%-- JoinForm.jsp에서 입력한 정보를 넘겨 받아 처리한다. --%>
    <% 
        // 한글 깨짐을 방지하기 위한 인코딩 처리
        request.setCharacterEncoding("utf-8"); 
    
    	MemberBean memberBean = new MemberBean();
    	memberBean.setMem_id(request.getParameter("mem_id"));
    	memberBean.setMem_id(request.getParameter("mem_pw"));
    	memberBean.setMem_id(request.getParameter("mem_pCode"));
    	memberBean.setMem_id(request.getParameter("mem_name"));
    	memberBean.setMem_id(request.getParameter("mem_email"));
  
   		MemberDAO dao = MemberDAO.getInstance();
    
    // 회원정보를 담고있는 memberBean을 dao의 insertMember() 메서드로 넘긴다.
    // insertMember()는 회원 정보를 JSP_MEMBER 테이블에 저장한다.
    
        dao.insertMember(memberBean);
    %>
    
    <div id="wrap">
        <br><br>
        <b><font size="5" color="gray">회원가입 정보를 확인하세요.</font></b>
        <br><br>
        <font color="blue"><%=memberBean.getMem_name() %></font>님 가입을 축하드립니다.
        <br><br>
        
        <%-- 자바빈에서 입력된 값을 추출한다. --%>
        <table>
            <tr>
                <td id="title">아이디</td>
                <td><%=memberBean.getMem_id() %></td>
            </tr>
                        
            <tr>
                <td id="title">비밀번호</td>
                <td><%=memberBean.getMem_pw() %></td>
            </tr>
             <tr>
                <td id="title">환자번호</td>
                <td><%=memberBean.getMem_pCode() %></td>
            </tr>                    
            <tr>
                <td id="title">이름</td>
                <td><%=memberBean.getMem_name() %></td>
            </tr>       
            <tr>
                <td id="title">이메일</td>
                <td>
                    <%=memberBean.getMem_email() %></td>
            </tr>         
        </table>
        
        <br>
        <input type="button" value="확인">
    </div>
</body>
</html>
