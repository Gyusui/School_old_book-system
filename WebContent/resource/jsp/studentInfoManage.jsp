<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>普通用户管理</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="<%=path %>/resource/js/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function deleteStudent(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push("\""+selectedRows[i].userid+"\"");
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("/SchoolOldBook/deleteUser.mis",{userid:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}

	function searchStudent(){
		$('#dg').datagrid('load',{
			userId:$('#userId').val(),
			userRight:$('#userRight').val(),
		});
	}
	
	
	function openStudentAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加学生信息");
		url="/SchoolOldBook/addUser.mis";
	}
	
	function saveStudent(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function resetValue(){
		$("#stuNo").val("");
		$("#stuName").val("");
		$("#sex").combobox("setValue","");
		$("#birthday").datebox("setValue","");
		$("#gradeId").combobox("setValue","");
		$("#email").val("");
		$("#stuDesc").val("");
	}
	
	function closeStudentDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openStudentModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑学生信息");
		$("#fm").form("load",row);
		url="/SchoolOldBook/editUser.mis?moto="+row.userid;
	}
</script>
</head>
<body style="margin: 5px;">
	<table id="dg" title="用户信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="/SchoolOldBook/userList.mis" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="userid" width="200" align="center">昵称（id）</th>
				<th field="userpwd" width="100" align="center">登录密码</th>
				<th field="email" width="300" align="center">邮箱</th>
				<th field="userright" width="100" align="center">用户权限</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openStudentAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openStudentModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteStudent()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;用户昵称：&nbsp;<input type="text" name="userId" id="userId" size="10"/>
			&nbsp;用户权限：&nbsp;<select class="easyui-combobox" id="userRight" name="userRight" editable="false" panelHeight="auto">
		    <option value="">请选择...</option>
			<option value="0">普通用户</option>
			<option value="1">管理员</option>
		</select>
		<a href="javascript:searchStudent()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 570px;height: 150px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>
					<td>用户昵称：</td>
					<td><input type="text" name="userid" id="userid" class="easyui-validatebox" required="true"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>登录密码：</td>
					<td><input type="text" name="userpwd" id="userpwd" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>Email：</td>
					<td><input type="text" class="easyui-validatebox" required="true" id="email" name="email"/></td>
					<td></td>
					<td>用户权限：</td>
					<td>
						<input type="radio" name="userright" id="userright" value="0"/>普通用户
						<input type="radio" name="userright" id="userright" value="1"/>管理员
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveStudent()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeStudentDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>