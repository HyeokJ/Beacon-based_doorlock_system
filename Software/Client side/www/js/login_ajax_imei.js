 $(document).ready(function(){"use strict";
	$("#btn").click(function(){
		logAjax();
	});
});
function logAjax(){"use strict";
	var id = $("#textinput").val();
	if($.trim(id).length === 0){
		alert("아이디를 입력해주세요!");
		$("#textinput").focus();
	}else{
		$.ajax({ 
			type:"POST",
			url:"http://192.168.200.147:8080/JSP_DEV/member/pro/ImeiLoginPro.jsp",
			data:{id:$("#textinput").val()},
			success:function(response){
				if(response.trim() === "success"){
					location.href = "#search_page";	
				}else if(response.trim() === "wrong imei"){
					alert("이 단말기로 로그인 할 수 없습니다.");
					$("#textinput").focus();
				}else{
					alert("방문자 번호를 확인해주세요.");
					$("#textinput").focus();
				}
			},error: function(){
				alert("실패");
				return false;
			}
		});
	}
}// JavaScript Document