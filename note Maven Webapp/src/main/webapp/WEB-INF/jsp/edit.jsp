<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
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
<style>
html {
	position: relative;
	min-height: 100%;
}

body {
	/* Margin bottom by footer height */
	margin-bottom: 60px;
	padding-top: 70px;
}

footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	background-color: #f5f5f5;
}

footer>.container {
	padding-right: 15px;
	padding-left: 15px;
}

.container .text-muted {
  margin: 20px 0;
}
</style>
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
					<li class="active"><a href="."><span class="glyphicon glyphicon-edit"></span> Write</a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search"
					action="search.html">
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="${note.url }">
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit">Go!</button>
							</span>
						</div>
						<!-- /input-group -->
					</div>
					<div class="form-group">
						<label class="radio-inline"> <input type="radio"
							name="language" id="inlineRadio1" value="plain">
							Plain
						</label> <label class="radio-inline"> <input type="radio"
							name="language" id="inlineRadio2" value="markdown">
							Markdown
						</label>
					</div>
					<div class="form-group">
						<a class="btn btn-default"><span class="glyphicon glyphicon-qrcode"></span></a>
					</div>
				</form>
			</div><!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">
		<textarea class="form-control" rows="20">${note.content }</textarea>
	</div><!-- /.container -->

	<footer>
		<div class="container">
			<p class="text-muted">
				&copy; 2010-2015 <a
					href=".">Honey Note</a> / <a
					href="help" title="如何使用  Honey Note">Help</a>
				/ <a href="about" title=" Honey Note 是什么">About</a>
				/ <a href="privacy" title="隐私和使用条款">Privacy</a>
				/ <a rel="external" href="https://me.alipay.com/dallaslu"
					title="通过支付宝捐助">Donate</a> <span>Powered by <a
					href="https://honeyhaw.com">Honey Haw</a> Hosted on <a
					href="http://www.linode.com/?r=ae55e4ac259593b7f0698627838194ba4451a878">Linode</a></span>
			</p>
		</div><!-- /.container -->
	</footer>
	
	<script src="static/js/jquery-2.1.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/common.js"></script>
	<script>
		(function($) {
			$(function() {

				$('#save').on('click', function() {
					var $btn = $(this).button('loading');
					setTimeout(function(){
						$btn.button('reset');
					},1000);
				});
				
				$.get('api/1.0/test.json'
						,function(json){
							alert(json);
					},'json');

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
