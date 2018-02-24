<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.SOB.bean.*"%>
<%@ page import="com.SOB.util.*"%>
<%@ page import="java.sql.Connection"%>
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
    <style type="text/css">
    	body {
		  padding: 0 0 0 0;
		  background: transparent no-repeat top fixed;
		  background-position: left;
		}
    </style>
    <script>
    </script>
<title>Insert title here</title>
</head>
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
      <ul class="nav navbar-nav navbar-right" style="margin-left:150px;">
        <%String userId = (String)request.getSession().getAttribute("user_id");
        	if(userId != null){ %>
	        	<li><a href="#">欢迎回来，<%=userId %></a></li>
	        <%}else{ %>
	        	<li><a href="#">请先登陆</a></li>
	        <%} %>
		<li><a href="/SchoolOldBook/logout.mis">注销</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<%String orderSuccess = (String)request.getAttribute("orderSuccess");
	if(orderSuccess==null){%>
	<div class="container" style="margin-top:70px;">
		<div class="row">
			<div class="page-header">
				<h1 style="color:white;">我的购物车<small>还没挑够？<a href="<%=path%>/resource/jsp/UserMain.jsp">点我</a>继续购买</small></h1>
			</div>
		</div>
		<% 
	    	Cart cart = (Cart)session.getAttribute("myCart");
			if(cart != null){
			List<BookBean> products = cart.getAllProductFromCart();
				Iterator<BookBean> it = products.iterator();
				while(it.hasNext()){
					BookBean bb = it.next();
		%>
		<div class="row" style="margin-bottom:10px;">
			<div class="col-md-1"></div>
			<div class="col-md-2">
				<img style="width:120px;height:80px;" alt="" src="<%=path %>/<%=bb.getPictureUrl() %>"  class="img-rounded">
			</div>
			<div class="col-md-7" style="margin-top:-15px;">
	  			<h3><%=bb.getBookName() %>
	  				<span class="label label-info"><%=bb.getGrade() %></span>
	        		<span class="label label-success"><%=bb.getPhysical() %></span>
	       			<span class="label label-default"><%=bb.getBookObject() %></span>
	  			</h3>
	  			<%=bb.getDescription() %>
			</div>
			<div class="col-md-2" style="margin-top:20px;">
				<a href="/SchoolOldBook/doShopping.mis?bookId=<%=bb.getId() %>&actionName=delete" class="btn btn-lg btn-danger btn-block" role="button">我不想要了！</a>
			</div>
		</div>
		<%}} %>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<form class="form-horizontal" action="/SchoolOldBook/submitOrder.mis" role="form">
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label" name="phone">联系方式</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="phone">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label" name="receiptAddress">收货地址</label>
				    <div class="col-sm-10">
				      <textarea class="form-control" rows="3" name="receiptAddress"></textarea>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" id="submitSuccess" class="btn btn-success btn-lg btn-block">提交订单</button>
				    </div>
				  </div>
				</form>
			</div>
		</div>
	</div>
	<%}else{ %>
	<div class="container" style="margin-top:70px;">
		<div class="row">
			<div class="page-header">
				<h1 style="color:white;">您已成功提交订单！！<small><a href="<%=path%>/resource/jsp/UserMain.jsp">返回主页</a></small></h1>
			</div>
		</div>
	</div>
	<%} %>
	

</body>
</html>