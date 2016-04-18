<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天气信息（历史7天和未来4天）</title>
</head>
<body>
	<div>
		<div style="float: left;width: 30%;">
			<h1>${Weathers.city}(${Weathers.cityid})</h1>
			<h3>${Weathers.today.date}--${Weathers.today.week}</h3>
			<h3>当前温度：${Weathers.today.curTemp}</h3>
			<h3>PM值：${Weathers.today.aqi}</h3>
			<h3>风向：${Weathers.today.fengxiang}</h3>
			<h3>风力：${Weathers.today.fengli}</h3>
			<h3>最高温度：${Weathers.today.hightemp}</h3>
			<h3>最低温度：${Weathers.today.lowtemp}</h3>
			<h3>天气状态：${Weathers.today.type}</h3>
		</div>
		
		<div style="float: right;width:60%;margin: 10px 30px;">
			<h3>指数指标列表</h3>
			<table border="1">
				<tr>
					<th style="width: 20%;">名称</th>
					<th style="width: 10%;">编码</th>
					<th style="width: 10%;">等级</th>
					<th style="width: 50%;">描述</th>
					<th style="width: 10%;">其他</th>
				</tr>
				<c:forEach items="${Weathers.today.index}" var="cur">
					<tr>
						<td align="center" style="size: 14px;">${cur.name}</td>
						<td align="center" style="size: 14px;">${cur.code}</td>
						<td align="center" style="size: 14px;">${cur.index}</td>
						<td align="center" style="size: 14px;">${cur.details}</td>
						<td align="center" style="size: 14px;">${cur.otherName}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<div style="clear: both;">
		<h3>未来4天天气信息</h3>
		<table border="1">
			<tr>
				<th style="width: 30%;">日期（礼拜）</th>
				<th style="width: 20%;">风向</th>
				<th style="width: 20%;">风力</th>
				<th style="width: 10%;">最高温度</th>
				<th style="width: 10%;">最低温度</th>
				<th style="width: 10%;">天气状态</th>
			</tr>
			<c:forEach items="${Weathers.forecast}" var="cur">
				<tr>
					<td align="center" style="size: 14px;">${cur.date}(${cur.week})</td>
					<td align="center" style="size: 14px;">${cur.fengxiang}</td>
					<td align="center" style="size: 14px;">${cur.fengli}</td>
					<td align="center" style="size: 14px;">${cur.hightemp}</td>
					<td align="center" style="size: 14px;">${cur.lowtemp}</td>
					<td align="center" style="size: 14px;">${cur.type}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div>
		<h3>历史天气信息</h3>
		<table border="1">
			<tr>
				<th style="width: 30%;">日期（礼拜）</th>
				<th style="width: 20%;">风向</th>
				<th style="width: 20%;">风力</th>
				<th style="width: 10%;">最高温度</th>
				<th style="width: 10%;">最低温度</th>
				<th style="width: 10%;">天气状态</th>
			</tr>
			<c:forEach items="${Weathers.history}" var="cur">
				<tr>
					<td align="center" style="size: 14px;">${cur.date}(${cur.week})</td>
					<td align="center" style="size: 14px;">${cur.fengxiang}</td>
					<td align="center" style="size: 14px;">${cur.fengli}</td>
					<td align="center" style="size: 14px;">${cur.hightemp}</td>
					<td align="center" style="size: 14px;">${cur.lowtemp}</td>
					<td align="center" style="size: 14px;">${cur.type}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>