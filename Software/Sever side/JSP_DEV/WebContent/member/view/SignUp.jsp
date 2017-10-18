<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jsp.member.model.MemberDAO" %>
<%@ page import="jsp.member.model.MemberBean" %>
<html>
<head>
    <title>회원가입 화면</title>
    
    <!-- css 파일 분리 -->
    <link href='../../css/join_style.css' rel='stylesheet' style='text/css'/>
 
    <script type="text/javascript">
    
        // 필수 입력정보인 아이디, 비밀번호, 환자번호가 입력되었는지 확인하는 함수
        function checkValue()
        {
            if(!document.userInfo.mem_id.value){
                alert("아이디를 입력하세요.");
                return false;
            }    
            if(!document.userInfo.mem_pw.value){
                alert("비밀번호를 입력하세요.");
                return false;
            }
            // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
            if(document.userInfo.mem_pw.value != document.userInfo.passwordcheck.value ){
                alert("비밀번호를 동일하게 입력하세요.");
                return false;
            }
            if(!document.userInfo.mem_pCode.value){
                alert("환자번호를 입력하세요.");
                return false;
            }
            if(document.userInfo.idDuplication.value != "idCheck"){
                alert("아이디 중복체크를 해주세요.");
                return false;
            }
        }
        
        // 취소 버튼 클릭시 로그인 화면으로 이동
        function goLoginForm() {
            location.href="LoginForm.jsp";
        }
        function inputIdChk(){
        	document.userInfo.idDuplication.value = "idUncheck";
        }
        function openIdChk(){
        	window.name = "parentForm";
        	window.open("./IdCheckForm.jsp","chkForm","width=500, height=300, resizable = no, scrollbars = no");
        }
        
    </script>
    
</head>
<body>
    <!-- div 왼쪽, 오른쪽 바깥여백을 auto로 주면 중앙정렬된다.  -->
    <div id="wrap">
        <br><br>
        <b><font size="6" color="gray">회원가입</font></b>
        <br><br><br>
        
        
        <!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
        <!-- 값(파라미터) 전송은 POST 방식, 전송할 페이지는 JoinPro.jsp -->
        <form method="post" action="../pro/JoinPro.jsp" name="userInfo" 
                onsubmit="return checkValue()">
            <table>
                <tr>
                    <td id="title">아이디</td>
                    <td>
                        <input type="text" name="mem_id" maxlength="50" onkeydown="inputIdChk()">
                        <input type="button" value="중복확인" onclick="openIdChk()">
                        <input type="hidden" name="idDuplication" value="idUncheck">  
                    </td>
                </tr>
                        
                <tr>
                    <td id="title">비밀번호</td>
                    <td>
                        <input type="password" name="mem_pw" maxlength="50">
                    </td>
                </tr>
                
                <tr>
                    <td id="title">비밀번호 확인</td>
                    <td>
                        <input type="password" name="passwordcheck" maxlength="50">
                    </td>
                </tr>
                <tr>
                    <td id="title">환자번호</td>
                    <td>
                        <input type="text" name="mem_pCode" maxlength="50">
                        <input type="button" value="중복확인" >    
                    </td>
                </tr>   
                <tr>
                    <td id="title">이름</td>
                    <td>
                        <input type="text" name="mem_name" maxlength="50">
                    </td>
                </tr>   
                <tr>
                    <td id="title">이메일</td>
                    <td>
                        <input type="text" name="mem_email" maxlength="50">
                    </td>
                </tr>
            
               
                   
            </table>
            <br>
            <input type="submit" value="가입"/>  
            <input type="button" value="취소" onclick="goLoginForm()">
        </form>
    </div>
</body>
</html>