<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="jsp.member.model.MemberDAO" %>
<%@ page import="jsp.member.model.MemberBean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>ȸ������ ó�� JSP</title>
    
    <!-- css ���� �и� -->
    <link href='../../css/join_style.css' rel='stylesheet' style='text/css'/>
    
</head>
<body>
    <%-- JoinForm.jsp���� �Է��� ������ �Ѱ� �޾� ó���Ѵ�. --%>
    <% 
        // �ѱ� ������ �����ϱ� ���� ���ڵ� ó��
        request.setCharacterEncoding("euc-kr"); 
    %>
    
    <%-- �ڹٺ� ���� �׼��±� ��� --%>
    <jsp:useBean id="memberBean" class="jsp.member.model.MemberBean" />
    <jsp:setProperty property="*" name="memberBean"/>    
    
    <%
   		MemberDAO dao = MemberDAO.getInstance();
    
    // ȸ�������� ����ִ� memberBean�� dao�� insertMember() �޼���� �ѱ��.
    // insertMember()�� ȸ�� ������ JSP_MEMBER ���̺� �����Ѵ�.
    
        dao.insertMember(memberBean);
    %>
    
    <div id="wrap">
        <br><br>
        <b><font size="5" color="gray">ȸ������ ������ Ȯ���ϼ���.</font></b>
        <br><br>
        <font color="blue"><%=memberBean.getMem_name() %></font>�� ������ ���ϵ帳�ϴ�.
        <br><br>
        
        <%-- �ڹٺ󿡼� �Էµ� ���� �����Ѵ�. --%>
        <table>
            <tr>
                <td id="title">���̵�</td>
                <td><%=memberBean.getMem_id() %></td>
            </tr>
                        
            <tr>
                <td id="title">��й�ȣ</td>
                <td><%=memberBean.getMem_pw() %></td>
            </tr>
             <tr>
                <td id="title">ȯ�ڹ�ȣ</td>
                <td><%=memberBean.getMem_pCode() %></td>
            </tr>                    
            <tr>
                <td id="title">�̸�</td>
                <td><%=memberBean.getMem_name() %></td>
            </tr>       
            <tr>
                <td id="title">�̸���</td>
                <td>
                    <%=memberBean.getMem_email() %></td>
            </tr>         
        </table>
        
        <br>
        <input type="button" value="Ȯ��">
    </div>
</body>
</html>
