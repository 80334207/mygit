<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" charset="utf-8" src="UEditor-utf8/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="UEditor-utf8/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="UEditor-utf8/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>

<style type="text/css">
.btndiv{
	height: 45px;
    padding: 10px 0;
}
.divbutton{
	width: 100px;
    height: 45px;
    line-height: 45px;
    margin-left:270px;
    text-align: center;
    background: #FF8F05;
    position: absolute;
}
.divbutton:hover{
	width: 100px;
    height: 45px;
    line-height: 45px;
    margin-left:270px;
    text-align: center;
    background: #EA8406;
    cursor: pointer;
}
.btndiv a{
	color: #000;
	font-family: 微软雅黑;
    font-size: 20px;
    font-weight: bold;
	text-decoration:none;
}
.btndiv a:hover{
	color: #fff;
	font-family: 微软雅黑;
    font-size: 20px;
    font-weight: bold;
    text-decoration:none;
}
.titlediv{
	margin: 0px 270px;
}
.titlediv h1{
	float: left;
}
.titlediv input{
	height: 30px;
	width: 500px;
	margin: 24px 20px;
	font-size: 20px;
	font-family: 微軟雅黑;
	font-weight: bold;
}
.publishbackground{
    position: absolute;
   	width: 100px;
    height: 45px;
    line-height: 45px;
    margin-left:270px;
    text-align: center;
    background-color: #5D5353;
    opacity: 0.8;
    color: #fff;
    font-size: 20px;
	font-family: 微軟雅黑;
	font-weight: bold;
    display: none;
}

</style>

<script type="text/javascript">
//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('editor');

var i = 0;
function submitform(){
	
	var title = $("#title").val();
	if(title == ""){
		alert("標題不能為空！");
		$("#title").focus();
		return;
	}
	var content = ue.getContent();
	if(content == ""){
		alert("內容不能為空！");
		ue.focus();
		return;
	}
	
	
	i = 3;
	$(".publishbackground").show();
	getTime();

}

function getTime(){
	$("#miao").html(i+"秒");
	if(i == 2){
		var data = $("#subform").serialize();
		$.ajax({
			url : 'saveblog.do',
			data : data,
			type : 'post',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
			success:function(result){
				if(result == "success"){
					alert("发布成功！");
					window.location.reload();
				}	
			},
			error:function(error){
				alert("操作超时!");
				window.location.reload();
			}
		});
	}
	if(i > 0)
		setTimeout('getTime()',1000);
	else
		closeBg();
	i--;
}

function closeBg(){
	$(".publishbackground").hide();
}

</script>
<title>信息发布</title>
</head>
<body>
	<form name="subform" id="subform">
		<div>
			<div class="titlediv">
				<h1>标题:</h1>
				<input type="text" name="title" id="title"/>
			</div>
	    	<div style="clear: both;"></div>
		</div>
	    <div id="editor" style="width:800px;height:320px;margin:0 auto;"></div>
	    <div class="btndiv" >
	    	<div class="divbutton"><a href="javascript:submitform();">提      交</a></div>
	    	<div class="publishbackground"><span id="miao"></span></div>
	    </div>
	</form>
</body>
</html>