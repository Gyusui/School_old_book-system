  		$(document).ready(function(){
			$("#bu").validation();
  			
  		    $("#bookUps").on('click',function(event){
  		      if ($("#bu").valid(this,'��д��Ϣ��������')==false){
  		        return false;
  		      }
  		    });
  			$("#upload").click(function(){
  				$("#login").modal('show');
  			});
  		})
		
		
