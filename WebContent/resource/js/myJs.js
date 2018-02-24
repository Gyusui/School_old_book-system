$(document).ready(function(){
	$("#makeCart a").click(function(){
		var bookName = $(this).attr("rel");
		var ma = $(this);
		$.ajax({
			type:'post',
			url:'/SchoolOldBook/doShopping.mis',
			data:{bookName:bookName, actionName:'add'},
			dataType:'json',
			success:function(data){
				if(data.error){
					$("#loginFalse").modal("toggle");	
				}else{
					$("#mySmallModalLabel").html(data.msg);
					$("#cartSuccess").modal('toggle');
					ma.addClass("disabled");
				}
			}, error: function(){
                alert("请求出错");
            }
			
		});
	});
	$("#upload").click(function(){
		$.ajax({
			type:'post',
			url:'/SchoolOldBook/userUploadAccess.mis',
			dataType:'json',
			success:function(data,ma){
				if(data.error){
					$("#loginFalse").modal("toggle");	
				}else{
					$("#uploadBook").modal("toggle");
				}
			}, error: function(){
                alert("请求出错");
            }
		});
	});
	$("#bu").validation();
		
	    $("#bookUps").on('click',function(event){
	      if ($("#bu").valid(this,'填写信息不完整。')==false){
	        return false;
	      }
	    });
		$("p button").popover();
		var uploadSuccess = $("#uploadSuccess")[0].value;
  		if(uploadSuccess != ""){
  			$("#mySmallModalLabel").html(uploadSuccess);
  		    $("#cartSuccess").modal("toggle");	
  		}
		var noLogin = $("#noLogin")[0].value;
  		if(noLogin != ""){
  		    $("#loginFalse").modal("toggle");	
  		}
		$("#loginAgain").click(function(){
  				$("#loginFalse").modal("toggle");
  				$("#login").modal("toggle");
  		});

})