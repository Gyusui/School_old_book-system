  		$(document).ready(function(){
			$("#bu").validation();
  			
  		    $("#bookUps").on('click',function(event){
  		      if ($("#bu").valid(this,'填写信息不完整。')==false){
  		        return false;
  		      }
  		    });
  			$("#upload").click(function(){
  				$("#login").modal('show');
  			});
  		})
		
		
