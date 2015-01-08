(function($){
	$(function(){
		$('#qr').each(function(){
			$(this).data("content",'<img alt="" src="' + $(this).attr("href")+'" height="250" width="250" />');
		}).popover({
			html:true,
			trigger: "hover",
			placement: "bottom"
		});
	});
})(jQuery);				