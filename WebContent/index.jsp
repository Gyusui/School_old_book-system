<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>                     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!--  style="background: #eaeaea;" -->
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/bootstrap.css" />
    <script type="text/javascript" src="<%=path %>/resource/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/js/bootstrap3-validation.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/js/index.js"></script>
<title>首页</title>
</head>
<body>
	<%
    	String registMessage = (String)request.getAttribute("registMessage");
		String loginMessage = (String)request.getAttribute("loginMessage");
     	if(registMessage != null){ %>
			<input type="hidden" value="registSuccess" id="hid">
		<%}else if(loginMessage != null){ %>
			<input type="hidden" value="loginFalse" id="hid">
		<%}else{%>
			<input type="hidden" value="nothing" id="hid">
		<%} %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron" style="background-image: url(<%=path %>/resource/pictures/search.jpg); height:780px;margin:0px;">
					<nav class="navbar" role="navigation" style="margin:-40px 0px 0px 0px;">
					  <div class="container-fluid">
					    <!-- Brand and toggle get grouped for better mobile display -->
					    <div class="navbar-header">
					      <a class="navbar-brand" href="#">交大旧书管理系统</a>
					    </div>
					
					    <!-- Collect the nav links, forms, and other content for toggling -->
					    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					      <ul class="nav navbar-nav navbar-right">
					        <li><a href="#" data-toggle="modal" data-target="#login">登录</a></li>
					        <li><a href="#" data-toggle="modal" data-target="#regist">注册</a></li>
					      </ul>
					    </div>
				  		</div>
					</nav>
					<div class="container-fluid">
				 	 	<h1 align=center span style="color:pink"><strong><em>酷炫旧书</em></strong></h1>
				 	 </div>
				  <div class="container-fluid">
				  	<div class="row">
				  		<div class="col-md-7 col-md-offset-3">
				  		<form class="form-horizontal" action="/SchoolOldBook/searchBook.mis" role="form" method="post">
							<div class="row">
							  <div class="col-lg-10">
							    <div class="input-group input-group-lg">
							      <input type="text" class="form-control" placeHolder="一日无书，百事荒芜。" name="bookname">
							      <span class="input-group-btn">
							        <button class="btn btn-primary btn-lg" type="submit">Go!</button>
							      </span>
							    </div><!-- /input-group -->
							  </div><!-- /.col-lg-6 -->
							</div><!-- /.row -->
				  		</form> 
				  		</div>
				  	</div>
				  </div>
				</div>
			</div>
		</div>
	</div>
	
						<!-- modal -->
		<div class="modal fade" id="regist" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" id="myModalLabel">注册新用户</h4>
			  </div>
			  <div class="modal-body">
				<form class="form-horizontal" action="/SchoolOldBook/regist.mis" role="form" id="registForm">
				  <div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">昵称:</label>
						<div class="col-sm-7">
						  <input type="text" class="form-control" name="userId" check-type="required" minlength="3" placeholder="请输入长度不少于3个字符">
						</div>
				  </div>
				  <div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">密码:</label>
						<div class="col-sm-7">
						  <input type="password" check-type="required" minlength="3" placeholder="请输入长度不少于3个字符" class="form-control" name="userPwd">
						</div>
				  </div>
				  <div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">邮箱:</label>
						<div class="col-sm-7">
						  <input type="email" class="form-control" name="email" 
						  placeholder="***@163.com" check-type="mail required">
						</div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-1 col-sm-10">
				      <button type="submit" class="btn btn-success btn-lg btn-block" id="registSubmit">注册！</button>
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
		<div class="modal fade bs-example-modal-sm" id="fuckyou" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="false">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="mySmallModalLabel">您已注册成功，是否现在登陆？</h4>
					</div>
			  	<div class="modal-footer">
					<button type="button" class="btn btn-primary"  id="yes">好的</button>
					<button type="button" class="btn btn-warning" data-dismiss="modal">不要</button>
			  	</div>
			  </div>
			</div>
		</div>
		<!-- modal -->
		<div class="modal fade bs-example-modal-sm" id="loginFalse" aria-hidden="false">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="mySmallModalLabel">您的登陆信息有误，请您重新登录</h4>
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