	$(function(){
  			$("#registForm").validation();
  			
  		    $("#registSubmit").on('click',function(event){
  		      if ($("#registForm").valid(this,'��д��Ϣ��������')==false){
  		        return false;
  		      }
  		    });
  			$("#yes").click(function(){
  				$("#fuckyou").modal("toggle");
  				$("#login").modal("toggle");
  			});
  			$("#loginAgain").click(function(){
  				$("#loginFalse").modal("toggle");
  				$("#login").modal("toggle");
  			});
  		    var hid = $("#hid")[0].value;
  		    if(hid == "registSuccess"){
  		    	$("#fuckyou").modal("toggle");	
  		    }
  		    if(hid == "loginFalse"){
  		    	$("#loginFalse").modal("toggle");
  		    }
  		})
		
		
