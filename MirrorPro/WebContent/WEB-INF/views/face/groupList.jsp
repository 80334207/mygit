<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<title>组列表</title>
<script type="text/javascript">
function groupDel(id){
	$.post("groupdelete.htm",{group_id:id} , function(r) {
		var obj = eval('(' + r + ')');
		if(obj.resultCode == 0){
			alert("删除成功！");
			window.location.reload();
		}else{
			
		}
	});
}

</script>
</head>
<body>
	<div style="margin: 5px 0;">组列表</div>
	<table border="1"  style="width: 100%;">
		<tr>
			<th width="40%">group_id</th>
			<th width="20%">tag</th>
			<th width="30%">group_name</th>
			<th width="10%">action</th>
		</tr>
		<c:forEach items="${grouplist}" var="list">
			<tr>
				<td align="center">${list.group_id}</td>
				<td align="center">${list.tag}</td>
				<td align="center">${list.group_name}</td>
				<td align="center">
					<a href="groupinfo.htm?group_id=${list.group_id}">详细</a>
					<a href="javascript:groupDel('${list.group_id}');">删除</a>
				</td>
			</tr>	
		</c:forEach>
	</table>
</body>
</html>