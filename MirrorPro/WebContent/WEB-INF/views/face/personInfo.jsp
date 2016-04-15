<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<title>人物详细</title>
<script type="text/javascript">
function formsubmit(){
	if($("input[name='person_name']").val()==""){
		alert("人物姓名不能为空！");
		$("input[name='person_name']").focus();
		return;
	}
	var ajax_data = $("#subform").serialize(); //表单数据
	
	$.post("personsetinfo.htm",ajax_data , function(r) {
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


//显示灰色 jQuery 遮罩层 
function train(name) {
	
	var pid = $("input[name='person_id']").val();
	$.post("trainperson.htm",{person_id:pid},function(r){
		var obj = eval('('+r+')');
		if(obj.resultCode == "0"){
			var result = eval('('+obj.result+')');
			window.parent.showBg(name,result.session_id,pid);
		}
	},"text");
}

</script>
</head>
<body>
	<div style="float: right;"><a href="javascript:formsubmit();" style="margin: 0 5px 0 0;">保存</a><a href="javascript:void(0);" onclick="train('${personinfo.person_name}');" >训练</a></div>
	<form method="post" name="subform" id="subform">
		<h1>人物姓名：<input type="text" value="${personinfo.person_name}" name="person_name" 
		style="height: 30px;width: 200px;font-size: 30px;border: none;"/></h1>   	
		<h4>ID：<input type="text" value="${personinfo.person_id}" name="person_id"
		style="widows: 265px;border: none;" readonly="readonly" /></h4>
		<h3>tag：<input type="text" value="${personinfo.tag}" name="tag"
		style="widows: 245px;border: none;" /></h3>	
	</form>

	<h3 style="float: left;">人脸列表</h3> <a style="float: right;line-height: 60px;" href="faceAdd.htm?person_id=${personinfo.person_id}">添加人脸</a>
	<table border="1" style="width: 100%;">
		<tr>
			<th width="40%">face_id</th>
			<th width="30%">tag</th>
			<th width="30%">action</th>
		</tr>
		<c:forEach items="${personinfo.face}" var="list">
			<tr>
				<td align="center">${list.face_id}</td>
				<td align="center">${list.tag}</td>
				<td align="center"><a href="#">详细</a></td>
			</tr>	
		</c:forEach>
	</table>	
	
	<h3>所在组列表</h3>
	<table border="1" style="width: 100%;">
		<tr>
			<th width="40%">group_name</th>
			<th width="20%">tag</th>
			<th width="30%">group_id</th>
			<th width="10%">action</th>
		</tr>
		<c:forEach items="${personinfo.group}" var="list">
			<tr>
				<td align="center">${list.group_name}</td>
				<td align="center">${list.tag}</td>
				<td align="center">${list.group_id}</td>
				<td align="center"><a href="#">详细</a></td>
			</tr>	
		</c:forEach>
	</table>
	 
</body>
</html>