<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口演示列表</title>
<script type="text/javascript">
function subformfun(obj){
	var form = document.subformcity;
	if(obj.name == "citylist"){
		form.action = "citylist.htm";

	}else if (obj.name == "cityinfo"){
		form.action = "cityinfo.htm";
	}
	form.submit();
}
</script>
</head>
<body>
	<div style="widows: 30%;float: left;">
		<form method="post" target="citylistframe" style="margin:0 10px;" name="subformcity">
			<div style="margin: 10px 0;">
				<span>城市名称(cityname):</span><input type="text" name="cityname" value="北京"/>
			</div>
			<div style="margin: 10px 0;">
				<button type="button" style="width: 100px;height: 20px;" name="citylist" onclick="subformfun(this);">获取城市列表</button>	
				<button type="button" style="width: 100px;height: 20px;" name="cityinfo" onclick="subformfun(this);">获取城市天气</button>
			</div>
		</form>
		<form action="recentWeathers.htm" method="post" target="citylistframe" style="margin:0 10px;" name="subformweather">
			<div style="margin: 10px 0;">
				<span>城市名称(cityname):</span><input type="text" name="cityname" value="北京"/>
			</div>
			<div style="margin: 10px 0;">
				<span>城市代码(cityid):</span><input type="text" name="cityid" value="101010100"/>
			</div>
			<div style="margin: 10px 0;">
				<button type="submit" style="width: 100px;height: 20px;">获取多日天气</button>			
			</div>
		</form>
	</div>
	<div style="float: right;width: 70%">
		<iframe width="100%" name="citylistframe" style="height: 500px;margin: 10px 0;border: none;"/>
	</div>
	
	
</body>
</html>