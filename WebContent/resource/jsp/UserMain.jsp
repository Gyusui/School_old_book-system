<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.SOB.bean.*"%>
<%
	String path = request.getContextPath();
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/bootstrap-theme.css" />
    <script type="text/javascript" src="<%=path %>/resource/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/js/bootstrap3-validation.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/js/myJs.js"></script>
    <script type="text/javascript">
	    $(function(){
	    	 $("#oneA").hover(function(){
	    	  $("#yourCart").show();
	    	 },function(){
	    		 $("#yourCart").hide();
	    	 });
	    	 $("#upload").hover(function(){
		    	  $("#yourUpload").show();
		    	 },function(){
		    		 $("#yourUpload").hide();
		    	 });
	    	 $("#backTop").hover(function(){
		    	  $("#yourBack").show();
		    	 },function(){
		    		 $("#yourBack").hide();
		    	 });
	    	});
    </script>
    <style type="text/css">
    	body {
		  padding: 0 0 0 0;
		  background: transparent no-repeat top fixed;
		  background-position: left;
		}
    </style>
<title>Insert title here</title>
</head>
	<%String userId = (String)request.getSession().getAttribute("user_id");
		String uploadSuccess = (String) request.getAttribute("uploadSuccess");
		String noLogin = (String) request.getAttribute("noLogin");
		if(uploadSuccess!= null){
	%>
	<input type="hidden" value=<%=uploadSuccess %> id="uploadSuccess">
	<%}else{ %>
	<input type="hidden" value="" id="uploadSuccess">
	<%}
	if(noLogin!=null){%>
		<input type="hidden" value="noLogin" id="noLogin">
	<%}else{ %>
		<input type="hidden" value="" id="noLogin">
	<%}%>
<body style="background-image: url(<%=path %>/resource/pictures/search_meitu_1.jpg);">
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">交大旧书</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
<form class="navbar-form navbar-left" role="form" action="/SchoolOldBook/searchBook.mis">
  <div class="form-group">
     <select class="form-control" name="grade">
     	  <option value="none">请选择</option>
     	  <option value="大一">大一</option>
		  <option value="大二">大二</option>
		  <option value="大三">大三</option>
		  <option value="大四">大四</option>
		  <option value="大五">大五</option>
     </select>
  </div>
  <div class="form-group">
     <select class="form-control" name="bookobject">
     	  <option value="none">请选择</option>	
     	  <option value="交通运输">交通运输</option>
		  <option value="日语+软件">日语+软件</option>
		  <option value="经贸管理">经贸管理</option>
		  <option value="电气自动化">电气自动化</option>
		  <option value="管理+软件">管理+软件</option>
		  <option value="数学">数学</option>
		  <option value="全专业">全专业</option>
		  <option value="英语+软件">英语+软件</option>
     </select>
  </div>
  <div class="form-group">
 	<input type="text" class="form-control" name="bookname" size="50px" placeholder="请输入书名">
  </div>
  <button type="submit" class="btn btn-default">检索</button>
</form>
      <ul class="nav navbar-nav navbar-right">
      	<%if(userId != null){ %>
        	<li><a href="#">欢迎回来，<%=userId %></a></li>
        <%}else{ %>
        	<li><a href="#" data-toggle="modal" data-target="#login">请先登陆</a></li>
        <%} %>
		<li><a href="/SchoolOldBook/logout.mis">注销</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
	<div class="container-fluid" style="margin-top:70px;">
		<div class="row">
			<div class="col-md-2">
					<div class="list-group col-md-offset-1" data-spy="affix" style="margin-top:50px;">
					  <a href="/SchoolOldBook/myCart.mis" class="list-group-item btn btn-default btn-lg" id="oneA"><span class="glyphicon glyphicon-shopping-cart"></span></a>
					  <p class="bg-warning" id="yourCart">购物车!</p>
					  <a href="#" class="list-group-item btn btn-default btn-lg" id="upload"><span class="glyphicon glyphicon-upload"></span></a>
					  <p class="bg-warning" id="yourUpload">上传旧书</p>
					  <a href="javascript:scroll(0,0)" class="list-group-item btn btn-default btn-lg" id="backTop"><span class="glyphicon glyphicon-arrow-up"></a>
					  <p class="bg-warning" id="yourBack">返回顶部</p>
					</div>
			</div>
			<div class="col-md-8">
				<div class="row" id="makeCart">
				<%List<BookBean> list = (List<BookBean>)request.getSession().getAttribute("indexBookList");
				if(list !=null){
					Iterator it=list.iterator();
					while(it.hasNext())
					{
					    BookBean bb =(BookBean)it.next();
				%>
				  <div class="col-sm-6 col-md-4">
				    <div class="thumbnail">
				      		<img src="<%=path %>/<%=bb.getPictureUrl() %>" alt="..." class="img-rounded">
				      <div class="caption">
				        <h3><%=bb.getBookName() %> </h3>
				        <p><span class="label label-info" style="align:right;"><%=bb.getGrade() %></span>
				        	<span class="label label-success"><%=bb.getPhysical() %></span>
				       		<span class="label label-default"><%=bb.getBookObject() %></span>
				       		<button id="a1" type="button" class="btn btn-xs btn-danger"data-toggle="popover" data-placement="top" data-content=<%=bb.getDescription() %>>简介</button>
				       		</span class="text-right">￥：<%=bb.getPrice() %></span>
				       		</p>
				        <p><a href="#" class="btn btn-success btn-block" role="button" rel="<%=bb.getBookName() %>">加入购物车！</a></p>
				      </div>
				    </div>
				  </div>
				  <%} %>
				  <%} %>
				</div>
			</div>
		</div>
	</div>
	
			<!-- modal -->
		<div class="modal" id="cartSuccess" aria-hidden="false">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="mySmallModalLabel"></h4>
					</div>
			  		<div class="modal-footer">
						<button type="button" class="btn btn-warning" data-dismiss="modal">确定</button>
			  		</div>
			  	</div>
			</div>
		</div>

		<!-- modal -->
			<div class="modal fade" id="uploadBook" tabindex="-1" role="dialog" aria-hidden="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">上传你的旧书</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" enctype="multipart/form-data" action="/SchoolOldBook/userUpload.mis" role="form" method="post" id="bu">
							<div class="form-group">
								<label class="col-sm-3 control-label">书名</label>
								<div class="col-sm-7">
									<input size="45" type="text" check-type="required" class="control-form" name="bookname">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">年级</label>
								<div class="col-sm-7">
									<select class="control-form" name="grade">
										<option>请选择年级</option>
										<option value="大一">大一</option>
										<option value="大二">大二</option>
										<option value="大三">大三</option>
										<option value="大四">大四</option>
										<option value="大五">大五</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">专业</label>
								<div class="col-sm-7">
									<select class="control-form" name="bookobject">
										<option>请选择专业</option>
										<option value="日语+软件">日语+软件</option>
										<option value="英语+软件">英语+软件</option>
										<option value="数学">数学</option>
										<option value="交通运输">交通运输</option>
										<option value="经贸管理">经贸管理</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">价格</label>
								<div class="col-sm-7">
									<input  size="45" type="text" class="control-form" check-type="required" name="price" placeHolder="我们使用人民币，请输入精确到小数点后两位">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">新旧程度</label>
								<div class="col-sm-7">
									<select class="control-form" name="physical">
										<option>请选择新旧程度</option>
										<option value="全新">全新</option>
										<option value="七成新">七成新</option>
										<option value="五成新">五成新</option>
										<option value="三成新">三成新</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">书籍图片</label>
								<div class="col-sm-7">
									<input type="file" class="control-form" check-type="required" name="picture">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">书籍介绍</label>
								<div class="col-sm-7">
									<textarea rows="5" cols="40" name="description"></textarea>
								</div>
								<input type="hidden" value=<%=userId %> name="userid">
							</div>
							<div class="form-group">
							    <div class="col-sm-offset-1 col-sm-10">
							      <button type="submit" id="bookUps" class="btn btn-primary btn-lg btn-block">确认上传</button>
							    </div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
								<!-- modal -->
		<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" id="myModalLabel">用户登陆</h4>
			  </div>
			  <div class="modal-body">
				<form class="form-horizontal" action="/SchoolOldBook/login.mis" method="post" role="form" id="loginForm">
				  <div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">昵称:</label>
						<div class="col-sm-10">
						  <input type="text" class="form-control" id="input1" name="userId">
						</div>
				  </div>
				  <div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">密码:</label>
						<div class="col-sm-10">
						  <input type="password" class="form-control" id="input3" name="userPwd">
						</div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-1 col-sm-10">
				      <button type="submit" class="btn btn-info btn-lg btn-block">登陆！</button>
				    </div>
				  </div>
				</form>
			  </div>
			</div>
		  </div>
		</div>
		
				<!-- modal -->
		<div class="modal fade bs-example-modal-sm" id="loginFalse" aria-hidden="false">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="mySmallModalLabel">您尚未登陆，请您重新登录</h4>
					</div>
				  	<div class="modal-footer">
						<button type="button" class="btn btn-primary"  id="loginAgain">好的</button>
						<button type="button" class="btn btn-warning" data-dismiss="modal">不要</button>
				 	</div>
			 	</div>
			</div>
		</div>

</body>
</html>