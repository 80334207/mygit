 //判断浏览器是否支持HTML5 Canvas           
$(function(){
	
	try {
		play();
	} catch (e) {
		alert("不支持HTML5");
	}
	
	$("#snap").click(function(){
		try {
			picture();
		} catch (e) {
			alert("不支持HTML5");
		}
		
	});
	
});
var video = null;
function play(){	
	video = document.getElementById("video");
	var videoObj = { "video": true };
	var errBack = function(error) {  
	      if("object" === typeof window.console){  
	          console.log("Video capture error: ", error.code);   
	      }  
	};
	// 针对标准的浏览器 
	if(navigator.getUserMedia) { // Standard 
		navigator.getUserMedia(videoObj, function(stream) {  
            video.src = stream;  
            video.play();  
        }, errBack);
	}else if(navigator.webkitGetUserMedia) { // WebKit-prefixed  
        navigator.webkitGetUserMedia(videoObj, function(stream){  
            video.src = window.webkitURL.createObjectURL(stream);  
            video.play();  
        }, errBack);  
    }  	 
}
var i = 0;
var base64Data;
function picture(){
	var canvas = document.getElementById("canvas");
	var context = canvas.getContext("2d");
	context.drawImage(video, 150, 100, 420, 540, 0 , 0, 320,240);
	
	var imgData = canvas.toDataURL();
	base64Data = imgData.substr(22);
	//base64Data = encodeURIComponent(imgData.substr(22));
	
	i = 5;
	showBg();
	getTime();
}

function showBg(){
	$(".buttonBg").show();
}
function closeBg(){
	$(".buttonBg").hide();
}


function getTime(){
	$("#miao").html("验证中"+i+"秒");
	if(i == 4){
		$.post("verityface.do",{ img : base64Data },function(result){			
			if(result.length > 2){
				var data = eval('(' + result + ')');
				$("#showName").html("你好" + data.person_name + "，系统只是测试一下你是否安好！想进入系统你还是要填写账号密码的，亲！");
				PageTransitions.next(PageTransitions.obj);
			}else{
				alert("图像不清晰，亲，无法识别！");
			}
			closeBg();
		},"text");
	}
	if(i > 0)
		setTimeout('getTime()',1000);
	
	if(i == 0)
		$("#miao").html("验证中...");

	i--;
}

function question(value){
	$("#result_wjf").val(value);
}

