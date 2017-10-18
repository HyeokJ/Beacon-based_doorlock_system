 $(document).ready(function(){"use strict";
	$("#signUp_ok_btn").click(function(){
		signUpAjax();
	});
	$("#DupCheckBtn").click(function(){
		DupCheckAjax();
	});
	$("#DupCheckPnBtn").click(function(){
		DupCheckPnAjax();
	});
});
function signUpAjax(){"use strict";
	var id = $("#mem_id").val();
	var pw = $("#mem_pw").val();
	var cpw = $("#cpw").val();
	var pCode = $("#mem_pCode").val();
	var name = $("#mem_name").val();
	var email = $("#mem_email").val();
	var idDuplication = $("#idDuplication").val();
	if($.trim(id).length === 0){
		alert("아이디를 입력해주세요!");
		$("#mem_id").focus();
		return false;
	}else if($.trim(pw).length === 0){
		alert("패스워드를 입력해주세요!");
		$("#mem_pw").focus();
		return false;
	}else if(pw !== cpw){
        alert("비밀번호를 동일하게 입력하세요.");
 		$("#cpw").focus();
		return false;
	}else if($.trim(pCode).length === 0){
		alert("환자번호를 입력해주세요!");
		$("#mem_pCode").focus();
		return false;
	}else if($.trim(name).length === 0){
		alert("이름을 입력해주세요!");
		$("#mem_name").focus();
		return false;
	}else if($.trim(email).length === 0){
		alert("이메일을 입력해주세요!");
		$("#mem_email").focus();
		return false;
	}else if(idDuplication !== "idCheck"){
        alert("아이디 중복체크를 해주세요.");
		return false;
	}else{
		$.ajax({ 
			type:"POST",
			url:"http://localhost:8080/JSP_DEV/member/pro/JoinPro.jsp",
			data:{mem_id:id,mem_pw:pw,mem_pCode:pCode,mem_name:name,mem_email:email},
			success:function(response){
				alert("성공");
			},error: function(){
				alert("실패");
				return false;
			}
		});
	}
}
function DupCheckPnAjax(){"use strict";
	var pn = $("#mem_pCode").val();
	if($.trim(pn).length === 0){
		alert("환자번호를 입력하지 않았습니다.");
		return false;
	}else if((pn < "0" || pn > "9") && (pn < "A" || pn > "Z") && (pn < "a" || pn > "z")){
		alert("한글 및 특수문자는 사용하실 수 없습니다.");
		return false;
	}else{
		$.ajax({
		type:"POST",
		url:"http://localhost:8080/JSP_DEV/member/pro/PnCheckPro.jsp",
		data:{pcode:pn},
		success:function(response){
			if(response.trim() === "able"){
				alert("사용가능한 환자번호 입니다.");
				$("#PnDuplication").val("PnCheck");
			}else{
				alert("이미 존재하는 환자번호 입니다.");
				return false;
			}
			
		},error: function(){
			alert("실패");
			return false;
		}
	});

	}
}
function DupCheckAjax(){"use strict";
	var id = $("#mem_id").val();
	if($.trim(id).length === 0){
		alert("아이디를 입력하지 않았습니다.");
		return false;
	}else if((id < "0" || id > "9") && (id < "A" || id > "Z") && (id < "a" || id > "z")){
		alert("한글 및 특수문자는 아이디로 사용하실 수 없습니다.");
		return false;
	}else{
		$.ajax({
		type:"POST",
		url:"http://localhost:8080/JSP_DEV/member/pro/IdCheckPro.jsp",
		data:{idinput:id},
		success:function(response){
			if(response.trim() === "able"){
				alert("사용가능한 아이디 입니다.");
				$("#idDuplication").val("idCheck");
			}else{
				alert("이미 존재하는 아이디 입니다.");
				return false;
			}
			
		},error: function(){
			alert("실패");
			return false;
		}
	});

	}
}
	// JavaScript Document