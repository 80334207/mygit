<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人脸识别接口列表</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

function subform1(){
	var img = $("#imgfile1").val();
	if(img == ""){
		alert("请选择上传图片");
		return;
	}
	document.imgform1.submit();
}

function subform2(){
	var img = $("#imgfile2").val();
	if(img == ""){
		alert("请选择上传图片");
		return;
	}
	document.imgform2.submit();
}

function subform1(){
	var img = $("#imgfile").val();
	if(img == ""){
		alert("请选择上传图片");
		return;
	}
	document.imgform1.submit();
}
function verifysub(){
	var faceid = $("input[name='face_id']").val();
	if(faceid == ""){
		alert("face_id不能为空！");
		$("input[name='face_id']").focus();
		return;
	}
	var personname = $("input[name='person_name']").val();
	if(personname == ""){
		alert("对比人姓名不能为空！");
		$("input[name='person_name']").focus();
		return;
	}
	var params = $("#verifyform").serialize();
	
	$.post("verifySameperson.htm",params,function(r){
		var obj = eval('(' + r + ')');
		if(obj.resultCode == "0"){
			var re = eval('(' + obj.result + ')');
			if(re.is_same_person == true){
				alert("是同一个人，比较相似度为："+re.confidence);
			}else{
				alert("不是同一个人，相似度为："+re.confidence);
			}
		}
	});
}
</script>
</head>
<body>
	<h3>功能类接口</h3>
	<ur>
		<li>
			<span>传一张照片看看是谁？</span><span>interface_verify.htm</span>
			<form action="interface_verify.htm" method="post" enctype="multipart/form-data" name="imgform1" target="resultframe">
				<input type="file" name="imgfile" id="imgfile1" />
				<a href="javascript:subform1();">点击看看是谁</a>
			</form>		
		</li>
		<li>
			<span>验证是否是同一个人</span><span>verifySameperson.htm</span>
			<form action="imgDetect.htm" method="post" enctype="multipart/form-data" name="imgform2" target="resultframe">
				<input type="file" name="imgfile" id="imgfile2" />
				<a href="javascript:subform2();">获取face_Id</a>
			</form>
			<iframe name="resultframe"></iframe>
			<form method="get" name="verifyform" id="verifyform">
			face_id：<input type="text" name="face_id" />
			person_name：<input type="text" name="person_name" />
			<input type="button" value="验证" onclick="verifysub();"/>
			</form>	
		</li>
	</ur>
</body>
</html>