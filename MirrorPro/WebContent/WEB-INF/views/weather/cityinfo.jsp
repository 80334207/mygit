<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>城市天气信息</title>
</head>
<body>
	<div>
		<h1>${cityinfo.city}(${cityinfo.pinyin})</h1>
		<h4>${cityinfo.citycode}</h4>
		<h3>日期：${cityinfo.date}</h3>
		<h3>发布时间：${cityinfo.time}</h3>
		<h3>邮编：${cityinfo.postCode}</h3>
		<h3>经纬度：${cityinfo.longitude},${cityinfo.latitude}</h3>
		<h3>海拔：${cityinfo.altitude}m</h3>
		<h3>天气情况：${cityinfo.weather}</h3>
		<h3>气温：${cityinfo.temp}</h3>
		<h3>最低气温：${cityinfo.l_tmp}</h3>
		<h3>最高气温：${cityinfo.h_tmp}</h3>
		<h3>风向：${cityinfo.WD}</h3>
		<h3>风力：${cityinfo.WS}</h3>
		<h3>日出时间：${cityinfo.sunrise}</h3>
		<h3>日落时间：${cityinfo.sunset}</h3>
	</div>		
</body>
</html>