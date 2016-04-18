<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/tanchu1.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<title>人脸识别接口演示列表</title>
<script type="text/javascript">
$(function(){
	$("input[id='submitbtn']").on("click",function(){
		var url = "";
		var ajax_data; //表单数据
		var $proprt;
		if($(this).prop("name")=="groupbtn"){
			url = "groupcreate.htm";
			ajax_data = $("#subform").serialize();
			$proprt = $("#proprt");
		}else{
			url = "personCreate.htm";
			ajax_data = $("#personsubform").serialize();
			$proprt = $("#personproprt");
		}
		
		$.post(url,ajax_data , function(r) {
			var obj = eval('(' + r + ')');
			if(obj.resultCode == 0){
				$proprt.html("创建成功！");
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
				$proprt.html(str);
			}
		},"text"); 
	});
});

var sessionid;
var personid;
var personname;
//显示灰色 jQuery 遮罩层 
function showBg(text,sid,pid) {
	
	sessionid = sid;
	personid = pid;
	personname = text;
	var bh = $(window).height();
	var bw = $(window).width();
	$("#fullbg").css({
		height : bh,
		width : bw,
		color : "#E2F4FF",
		display : "block"
	});
	$("#fullbg").css("font-size","30px");
	$("#fullbg").css("line-height","13");
	
	$("#text").html(text+"训练中...");
	getTime();
	$("#check").prop("href","getSession.htm?session_id="+sid);
	$("#fullbg").show();
}

var i = 30;
function getTime(){
	$("#miao").html(i+"秒");
	if(i < 30){
		if(i%5 == 0){		
			$.post("sessionStatus.htm",{session_id:sessionid},function(r){
				var obj = eval('('+r+')');
				if(obj.resultCode == "0"){
					var result = eval('('+obj.result+')');
					var status = result.status;
					if(status == 'SUCC'){
						$("#text").html(personname+"训练成功！");
						i=0;
					}else if(status == 'INQUEUE'){
						$("#text").html(personname+"训练队列中...");
						var con = confirm("是否等待?");
						if(con){
							i = 30;
						}
					}else if(status == 'FAILED'){
						$("#text").html(personname+"训练失败，请重试！");
						i=0;
					}
				}
			});		
		}
	}
	if(i > 0)
		setTimeout('getTime()',1000);
	else
		closeBg();
	i--;
}

//关闭灰色 jQuery 遮罩 
function closeBg() {
	$("#fullbg").hide();
}
</script>
</head>
<body>
	<h3>功能接口</h3>
	<div>
		<form method="post" name="subform" id="subform">
			<div style="margin: 10px 0;float: left;">
					<span>组名称(group_name):</span><input type="text" name="group_name" value=""/>
			</div>
			<div style="margin: 10px 0;float: left;">
					<span>组标示(tag):</span><input type="text" name="tag" value=""/>
			</div>
			<div>
				<input type="button" value="创建"  style="margin: 10px 10px;" id="submitbtn" name="groupbtn"/>
				<!-- <a href="javascript:formsubmit();" style="margin: 10px 10px;line-height: 40px;">创建</a> -->
				<span id="proprt"></span>
			</div>
			<div style="clear: both;"></div>
			<span>提示:Group的Name信息，必须在App中全局唯一。Name不能包含^@,&=*'"等非法字符，且长度不得超过255。Name也可以不指定，此时系统将产生一个随机的name。
			Group的tag，不需要全局唯一，不能包含^@,&=*'"等非法字符，长度不能超过255</span>
		</form>		
	</div>
	<div>
		<form method="post" name="personsubform" id="personsubform">
			<div style="margin: 10px 0;float: left;">
					<span>人物姓名(person_name):</span><input type="text" name="person_name" value=""/>
			</div>
			<div style="margin: 10px 0;float: left;">
					<span>人物标识(tag):</span><input type="text" name="tag" value=""/>
			</div>
			<div style="margin: 10px 0;float: left;">
					<span>组名称(group_name):</span>
					<select name="group_id">
						<option value="0">--请选择--</option>
						<c:forEach items="${grouplist}" var="list">
							<option value="${list.group_id}">${list.group_name}</option>
						</c:forEach>
					</select>
			</div>
			<div>
				<input type="button" value="创建"  style="margin: 10px 10px;" id="submitbtn" name="personbtn" />
				<!-- <a href="javascript:formsubmit();" style="margin: 10px 10px;line-height: 40px;">创建</a> -->
				<span id="personproprt"></span>
			</div>
			<div style="clear: both;"></div>
			<span>提示:Person的Name信息，必须在App中全局唯一。Name不能包含^@,&=*'"等非法字符，且长度不得超过255。Name也可以不指定，此时系统将产生一个随机的name。
			Person相关的tag，不需要全局唯一，不能包含^@,&=*'"等非法字符，长度不能超过255。</span>
		</form>		
	</div>
	
	<div id="main">
		<div id="fullbg">
			<span id="text"></span><span id="miao"></span>
			<a id="check" target="view_window">点击这里查询训练结果</a>
		</div>  
	</div> 
	
	<h3>数据列表</h3>
	<div>
		<a href="grouplist.htm" target="dataframe">刷新</a>
	</div>
	<div>
		<iframe name="dataframe" width="100%" height="400px" src="grouplist.htm"/>
	</div>
	<div style="clear: both;"/>
	
</body>
</html>