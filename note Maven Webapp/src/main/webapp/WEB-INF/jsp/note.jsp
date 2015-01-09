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
<link rel="stylesheet" href="static/css/bootstrap-switch.min.css">
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
				<ul class="nav navbar-nav navbar-right">
					<%@ include file="includes/qr.jsp" %>
					<li class="navbar-container">
						<c:choose>
							<c:when test="${note.update_at ne null && note.update_at.time lt now}">
								<input type="checkbox" id="refresh-switcher"
									data-label-text="Auto Refresh" data-size="normal"
									checked="checked" autocomplete="off" data-label-width="83">
							</c:when>
							<c:otherwise>
								<input type="checkbox" id="refresh-switcher"
									data-label-text="Auto Refresh" data-size="normal"
									autocomplete="off" data-label-width="83">
							</c:otherwise>
						</c:choose>
					</li>
					</ul>
			</div><!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container" id="note">
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
	<script src="static/js/bootstrap-switch.min.js"></script>
	<script src="static/js/common.js"></script>
	<script>
		(function($) {
			$(function() {
				var lastUpdateAt = ${note.update_at eq null ? 0 : note.update_at.time};
				var delay = 5000; //ms
				var $note = $('#note');
				var timer = setTimeout(checkNote,delay);
				
				$(document).ajaxStart(function() {
					$('#submit').button('loading');
				}).ajaxComplete(function() {
					$('#submit').button('complete');
					clearTimeout(timer);
					timer = setTimeout(checkNote, delay);
				});
				
				function checkNote(){
					if( !$('#refresh-switcher').is(':checked')){
						clearTimeout(timer);
						setTimeout(checkNote, delay);
						return;
					}
					$.getJSON('api/1.0/${note.url}.json', function(json){
						if( json.update_at && json.update_at > lastUpdateAt ){
							lastUpdateAt = json.update_at;
							if( json.language == 'markdown' ){
								$note.html(json.content);
							}else{
								var $pre = $('<pre class="plain"></pre>').append(json.content);
								$note.html($pre.wrap('<div>').parent().html());
							}
						}
					});
				}
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
