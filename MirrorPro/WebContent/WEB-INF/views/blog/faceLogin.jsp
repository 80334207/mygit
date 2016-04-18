<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>Page Transitions</title>
		<meta name="description" content="A Collection of Page Transitions with CSS Animations" />
		<meta name="keywords" content="page transition, css animation, website, effect, css3, jquery" />
		<meta name="author" content="Codrops" /> 
		<link rel="stylesheet" type="text/css" href="login/css/login.css" />
		<link rel="stylesheet" type="text/css" href="login/css/default.css" />
		<link rel="stylesheet" type="text/css" href="login/css/multilevelmenu.css" />
		<link rel="stylesheet" type="text/css" href="login/css/component.css" />
		<link rel="stylesheet" type="text/css" href="login/css/animations.css" />
		<script src="login/js/modernizr.custom.js"></script>
		
		<script src="login/js/jquery.min.js"></script>
		<script src="login/js/face.js"></script>
		
		<link href="css/jAlert-v3.css" rel="stylesheet"/>
	    <script src="js/jAlert-v3.js"></script>
	    <script src="js/jAlert-functions.js"></script>
			
		
	</head>
	<body>	
		<!-- /triggers -->

		<div id="pt-main" class="pt-perspective">
			  <div class="pt-page pt-page-1" style="text-align: center;">
				<h2>你会微积分么？</h2>
				<div class="question">
					函数f (x)=1＋x3＋x5,则f (x3＋x5)为 : <input type="text" id="result_wjf" style="width: 35px;"/>
				</div>
				<div  class="request">
					<div>
						<span style="width:100%;">
							<a href="javascript:question('A');" style="margin: 0 10px;">(A)</a>1＋x3＋x5
							<a href="javascript:question('B');" style="margin: 0 10px;">(B)</a>1＋2(x3＋x5)
						</span> 
					</div>
					<div>
						<span style="width:100%;">
							<a href="javascript:question('C');" style="margin: 0 10px;">(C)</a>1＋x6＋x10
							<a href="javascript:question('D');" style="margin: 0 10px;">(D)</a>1＋(x3＋x5)3＋(x3＋x5)5
						</span> 
					</div>		    
				</div>
				<div class="pt-triggers">
					<button id="iterateEffects" class="pt-touch-button">问题验证</button>
				</div>
				
			</div>
			<div class="pt-page pt-page-4">
				 <video id="video" class="video" autoplay></video>       
				 <canvas style="" class="picture" id="canvas"></canvas> 
				 <div class="pt-triggers" style="top:45%;">
					<button id="snap" class="pt-touch-button">脸部识别验证</button>
				</div>
				<div class="buttonBg"><span id="miao"></span></div>
			</div> 
			<div class="pt-page pt-page-5">
				<div id="showName" class="showName"></div>
				<div class="main center">
					<div class="left">
						<div>
							<span>UserName:</span>
							<input type="text" name="loginname" id="loginname" />
						</div>
						<div>
							<span>PassWord:</span>
							<input type="password" name="password" id="password" />
						</div>
					</div>
				</div>
				<div class="right">
						<p>Show Me</p>
						<p>Your Fucking</p>
						<p>Idears!!!</p>
				</div>
			</div>

		</div>
		
		<div class="pt-message">
			<p>亲，你的浏览器不支持 CSS 动画，请使用 Chrome,Firefox,Safari 等浏览器浏览.</p>
		</div>
		<script src="login/js/jquery.dlmenu.js"></script>
		<script src="login/js/pagetransitions.js"></script>
	</body>
</html>
