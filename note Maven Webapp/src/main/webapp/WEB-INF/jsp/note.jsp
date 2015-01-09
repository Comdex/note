<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@
taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%><%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%><!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>${note.title } - Honey Note</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/style.css">

</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href=".">Honey Note <sup>Alpha</sup></a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="."><span class="glyphicon glyphicon-edit"></span> Write</a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search"
					action="search.html">
					<div class="form-group">
						<a href="https://chart.googleapis.com/chart?cht=qr&chs=250x250&chl=https://pad.wf/${note.url }&chld=q|2" class="btn btn-default" id="qr"><span class="glyphicon glyphicon-qrcode"></span></a>
					</div>
				</form>
			</div><!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">
		<c:choose>
			<c:when test="${note.language eq 'markdown' }">
				${note.content }
			</c:when>
			<c:otherwise>
				<pre class="plain">${note.content }</pre>
			</c:otherwise>
		</c:choose>
	</div><!-- /.container -->

	<%@ include file="includes/footer.jsp" %>
	
	<script src="static/js/jquery-2.1.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/common.js"></script>
	<script>
		(function($) {
			$(function() {
			});
		})(jQuery);
	</script>
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-12034228-6' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>

	<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
	<!--[if lt IE 9]><script src="static/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
	<script src="static/assets/js/ie-emulation-modes-warning.js"></script>

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
      <script src="static/assets/js//html5shiv.js"></script>
      <script src="static/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</body>
</html>
