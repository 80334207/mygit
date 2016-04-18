<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<title>组详细</title>
<script type="text/javascript">
function formsubmit(){
	if($("input[name='name']").val()==""){
		alert("组名称不能为空！");
		$("input[name='name']").focus();
		return;
	}
	var ajax_data = $("#subform").serialize(); //表单数据
	
	$.post("groupsetinfo.htm",ajax_data , function(r) {
		var obj = eval('(' + r + ')');
		if(obj.resultCode == 0){
			alert("修改成功！");
			window.location.reload();
		}else{
			var errormsg = eval('(' + obj.result + ')');
			var str = "";
			switch(errormsg.error_code){
				case 1503:
					str = "名称已存在！";
					break;
				case 1502:
					str = "错误的标示(tag)！";
					break;
				case 1501:
					str = "错误的名称！";
					break;
				case 1202:
					str = "服务忙！";
					break;
				case 1006:
					str = "apikey错误！";
					break;
			}
			alert(str);
		}
	},"text"); 
}


function train(name) {
	
	var gid = $("input[name='group_id']").val();
	$.post("traingroup.htm",{group_id:gid},function(r){
		var obj = eval('('+r+')');
		if(obj.resultCode == "0"){
			var result = eval('('+obj.result+')');
			window.parent.showBg(name,result.session_id,gid);
		}
	},"text");
}
</script>
</head>
<body>
	<c:if test="${!empty groupinfo}">
		<div style="float: right;"><a href="javascript:formsubmit();" style="margin: 0 5px 0 0;">保存</a><a href="javascript:void(0);" onclick="train('${groupinfo.group_name}');" >训练</a></div>
		<form method="post" name="subform" id="subform">
			<h1>组名：<input type="text" value="${groupinfo.group_name}" name="name" 
			style="height: 30px;width: 200px;font-size: 30px;border: none;"/></h1>   	
			<h4>ID：<input type="text" value="${groupinfo.group_id}" name="group_id"
			style="widows: 265px;border: none;" readonly="readonly" /></h4>
			<h3>tag：<input type="text" value="${groupinfo.tag}" name="tag"
			style="widows: 245px;border: none;" /></h3>	
		</form>
	
		<h3>组内人员列表</h3>
		<table border="1" style="width: 100%;">
			<tr>
				<th width="40%">person_name</th>
				<th width="20%">tag</th>
				<th width="30%">person_id</th>
				<th width="10%">action</th>
			</tr>
			<c:forEach items="${groupinfo.person}" var="list">
				<tr>
					<td align="center">${list.person_name}</td>
					<td align="center">${list.tag}</td>
					<td align="center">${list.person_id}</td>
					<td align="center"><a href="personinfo.htm?person_id=${list.person_id}">详细</a></td>
				</tr>	
			</c:forEach>
		</table>
	</c:if>		
</body>
</html>