 $(document).ready(function(){"use strict";
	$("#btn").click(function(){
		logAjax();
	});
});
function logAjax(){"use strict";
	var id = $("#idinput").val();
	var pw = $("#pwinput").val();
	if($.trim(id).length === 0){
		alert("아이디를 입력해주세요!");
		$("#idinput").focus();
	}else if($.trim(pw).length === 0){
		alert("패스워드를 입력해주세요!");
		$("#pwinput").focus();
	}else{
		$.ajax({ 
			type:"POST",
			url:"http://localhost:8080/JSP_DEV/member/pro/FullLoginPro.jsp",
			data:{mem_id:$("#idinput").val(),mem_pw:$("#pwinput").val()},
			success:function(response){
				if(response.trim() === "success"){
					location.href = "#search_page";	
				}else if(response.trim() === "wrong id"){
					alert("아이디를 다시 확인해 주세요.");
					$("#textinput").focus();
				}else{
					alert("비밀번호를 다시 확인해 주세요.");
					$("#textinput").focus();
				}
			},error: function(){
				alert("실패");
				return false;
			}
		});
	}
}// JavaScript Document