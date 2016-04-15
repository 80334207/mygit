<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/upload.js"></script>

<title>人脸上传</title>

</head>
<body>
	<div style="height: 40px;width: 100%;text-align: right;">
		<a style="padding: 0 50px 0 0;line-height: 40px;" href="javascript:addface();" id="add">上传人脸</a>
		<a style="padding: 0 5px;line-height: 40px;" href="personinfo.htm?person_id=${personinfo.person_id}" id="back">返回</a>
	</div>
	<div style="background-color: #B9B5B5;width: 100%;height: 2px;"></div>
	
	<div id="imglist" style="width: 100%;height: 300px;">
		<c:forEach items="${personinfo.face}" var="list" varStatus="num">
			<div style="width: 150px;height: 220px;float: left;text-align: center;margin: 0 5px;">
				<img src="uploadfile/face/${list.face_id}.jpg" style="width: 150px;height: 200px;"/>
				<span>脸${num.count}</span>
			</div>
		</c:forEach>	
	</div>
	
	
	<div style="position: absolute;width: 780px;height: 255px;top: 20px;right: 0;margin: 0 125px 0 0;
	border: 2px solid #8E8D8D;display: none;" id="uploadform">
		
		<iframe width="100%" height="100%" style="width: 775px;height: 250px;" src="toupload.htm?person_id=${personinfo.person_id}"></iframe>
		
	</div>
	
</body>
</html>