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
<title>班级信息管理</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="<%=path %>/resource/js/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function searchGrade(){
		$('#dg').datagrid('load',{
			bookName:$('#s_gradeName').val(),
			grade:$('#s_grade').val(),
			bookObject:$('#s_object').val()
		});
	}
	
	function deleteGrade(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push("\""+selectedRows[i].bookname+"\"");
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("/SchoolOldBook/deleteBook.mis",{bookname:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].gradeName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	
	function openGradeAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加书籍信息");
		url="/SchoolOldBook/addBook.mis";
	}
	
	function openGradeModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑书籍信息");
		$("#fm").form("load",row);
		$("#hiddenBookName").val(row.bookname);
		url="/SchoolOldBook/editBook.mis";
	}
	
	function closeGradeDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function resetValue(){
		$("#bookname").val("");
		$("#grade").val("");
		$("#bookobject").val("");
		$("#price").val("");
		$("#physical").val("");
		$("#description").val("");
		$("#picture").val("");
	}
	
	
	function saveGrade(){
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
	
	
</script>
</head>
<body style="margin: 5px;">
	<table id="dg" title="班级信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="/SchoolOldBook/managerBookList.mis" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="bookname" width="150" align="center">书名</th>
				<th field="grade" width="100" align="center">所属年级</th>
				<th field="bookobject" width="150" align="center">所属专业</th>
				<th field="price" width="50" align="center">价格</th>
				<th field="physical" width="50" align="center">新旧程度</th>
				<th field="picture" width="250" align="center">图片URL</th>
				<th field="description" width="350" align="center">图书描述</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openGradeAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openGradeModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteGrade()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div><input type="text" name="s_bookname" id="s_bookname" placeHolder="请输入书名" size="30"/>
			&nbsp;年级：&nbsp;<input type="text" name="s_grade" id="s_grade" size="10"/>
			&nbsp;专业：&nbsp;<input type="text" name="s_object" id="s_object" size="10"/>
		<a href="javascript:searchGrade()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 550px;height: 300px;padding: 10px 0px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm"  enctype="multipart/form-data" method="post">
			<table>
				<tr>
					<td style="width: 100px;">书名：</td>
					<td><input type="text" name="bookname" id="bookname" class="easyui-validatebox" required="true"/></td>
					<td style="width: 100px;">所属年级</td>
					<td><select class="easyui-combobox" id="grade" name="grade" panelHeight="auto">
		  			 	<option value="">请选择...</option>
						<option value="大一">大一</option>
						<option value="大二">大二</option>
						<option value="大三">大三</option>
						<option value="大四">大四</option>
						<option value="大五">大五</option>
					</select></td>
				</tr>
				<tr>
					<td style="width: 100px;">所属专业：</td>
					<td><input type="text" name="bookobject" id="bookobject" class="easyui-validatebox" required="true"/></td>
					<td style="width: 100px;">价格：</td>
					<td><input type="text" name="price" id="price" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td style="width: 100px;">新旧程度</td>
					<td><select class="easyui-combobox" id="physical" name="physical" editable="false" panelHeight="auto">
		  			 	<option value="">请选择...</option>
						<option value="全新">全新</option>
						<option value="七成新">七成新</option>
						<option value="五成新">五成新</option>
					</select></td>
					<td style="width: 100px;">图书封面:</td>
					<td><input type="file" name="picture" id="picture" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>描述：</td>
					<td colspan="3"><textarea name="description" id="description" rows="7" cols="40"></textarea></td>
					<input type="hidden" id="hiddenBookName" name="hiddenBookName" value="">
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveGrade()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeGradeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>