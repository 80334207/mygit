<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta name="description" content="Raspi-Game">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Spermakert">
	<link rel="icon" href="blog_tm//favicon.ico">
    <title>Schizophrenia Laboratory -- My RetroPie</title>
	<!-- Bootstrap core CSS -->
    <link href="blog_tm/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="blog_tm/blog.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="blog_tm/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="blog_tm/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="blog_tm/html5shiv.min.js"></script>
      <script src="blog_tm/respond.min.js"></script>
    <![endif]-->
    <link href="css/jAlert-v3.css" rel="stylesheet"/>
    <script src="js/jquery-1.8.3.min.js"></script>
    <script src="js/jAlert-v3.js"></script>
    <script src="js/jAlert-functions.js"></script>
    
    <style type="text/css">
	   
    </style>
    <script type="text/javascript">
   
    
   	function signIn(){
   		
		$.jAlert({
			'title': '<i class="fa fa-envelope"></i> Login In',
			'iframe' : 'tologin.do',
			'iframeHeight' : '400px;',
	    	'theme': 'blue',
	    	'size' : 'lg'
		});
   	}
    </script>
  </head>
  <body>
  	<!-- top -->
    <iframe name="topframe" src="top.do" width="100%" height="45px"></iframe>
	<!-- top -->
    <div class="container">

      <div class="blog-header">
        <h1 class="blog-title">Spermakert's blog</h1>
        <p class="lead blog-description"></p>
      </div>

      <div class="row">

        <div class="col-sm-8 blog-main">

          <div class="blog-post">
            <h2 class="blog-post-title">${pageinfo.curinfo.title}</h2>
            <div style="float: left;margin-right: 40px;">
	            <p class="blog-post-meta">
	            ${fn:substring(pageinfo.curinfo.create_time,0,10)}
	            </p>
            </div>
            <div>
	            <p class="blog-post-meta">
	            ${pageinfo.curinfo.author}
	            </p>
            </div>
            <div style="clear: both;"></div>
			
			<div id="content">
				${pageinfo.curinfo.content}
			</div>
            
            <div class="line"></div>  
            <c:if test="${!empty pageinfo.perinfo}">         
				<div>
					<span>前一篇:</span><a href="blogdetail.do?id=${pageinfo.perinfo.id}">${pageinfo.perinfo.title}</a>
				</div>
			</c:if>
			<c:if test="${!empty pageinfo.nextinfo}"> 
				<div>
					<span>后一篇:</span><a href="blogdetail.do?id=${pageinfo.nextinfo.id}">${pageinfo.nextinfo.title}</a>
				</div>
			</c:if>
			<div class="line"></div>
          </div><!-- /.blog-post -->
        </div><!-- /.blog-main -->
        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
          <div class="sidebar-module sidebar-module-inset">
            <h4>关于</h4>
            <p>精分实验室  精分到底.</p>
          </div>
          <div class="sidebar-module">
            <h4>其它</h4>
            <ol class="list-unstyled">
              <c:forEach items="${bloglist.datalist}" var="list">
              	<li><a href="blogdetail.do?id=${list.id}" title="${list.title}">${fn:substring(list.title,0,28)}${fn:length(list.title)>28?'...':''}</a></li>
              </c:forEach>
            </ol>
          </div>
          <div class="sidebar-module">
            <h4>Elsewhere</h4>
            <ol class="list-unstyled">
              <li><a href="#"></a></li>
            </ol>
          </div>
        </div><!-- /.blog-sidebar -->
		
      </div><!-- /.row -->

    </div><!-- /.container -->
	<!-- footer -->
    <iframe name="footerframe" src="footer.do" width="100%"></iframe>
	<!-- footer -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- <script src="blog_tm/jquery.min.js"></script> -->
    <script src="blog_tm/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="blog_tm/ie10-viewport-bug-workaround.js"></script>
    
  </body>
</html>