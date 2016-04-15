<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>可用城市列表</title>
</head>
<body>
	<div style="margin: 5px 0;">可用城市列表</div>
	<table border="1">
		<tr>
			<th width="15%">省</th>
			<th width="15%">市</th>
			<th width="20%">区县</th>
			<th width="30%">区县（拼音)</th>
			<th width="20%">城市代码</th>
		</tr>
		<c:forEach items="${citylist}" var="list">
			<tr>
				<td align="center">${list.province_cn}</td>
				<td align="center">${list.district_cn}</td>
				<td align="center">${list.name_cn}</td>
				<td align="center">${list.name_en}</td>
				<td align="center">${list.area_id}</td>
			</tr>	
		</c:forEach>
	</table>
	
	<%-- <ul>
		<c:forEach items="${citylist}" var="list">
			<li>
			<span style="margin: 0 20px;">省 : ${list.province_cn}</span>
			<span style="margin: 0 20px;">市 : ${list.district_cn}</span>
			<span style="margin: 0 20px;">区县 : ${list.name_cn}</span>
			<span style="margin: 0 20px;">区县（拼音） : ${list.name_en}</span>
			<span style="margin: 0 20px;">城市代码 : ${list.area_id}</span>
			</li>
		</c:forEach>
	</ul> --%>

	
</body>
</html>