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
function subform(){
	var img = $("#imgfile").val();
	if(img == ""){
		alert("请选择上传图片");
		return;
	}
	document.imgform.submit();
}
</script>
</head>
<body>
	<h3>信息查询类接口</h3>
	<ul>
		<li>组列表查询:<a href="interface_grouplist.htm">interface_grouplist.htm</a></li>		
	</ul>
	<h3>功能类接口</h3>
	<ur>
		<li>
			<span>人脸识别(获取face_id)</span>
			<form action="imgDetect.htm" method="post" enctype="multipart/form-data" name="imgform" target="faceframe">
				<input type="file" name="imgfile" id="imgfile" />
				<a href="javascript:subform();">获取face_Id</a>
			</form>
			<iframe name="faceframe"></iframe>
		</li>
	</ur>
</body>
</html>