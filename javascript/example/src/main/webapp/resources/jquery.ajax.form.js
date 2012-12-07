/**
 * 简单实现以ajax提交form表单,不处理file
 * 
 * @author HuLingwei
 */
(function($) {
	$.fn.ajaxForm = function(options){
		options = options ||{};
		return $(this).each(function(){
			$(this).bind('submit', function(){
				if(options.validate){
					if(!$(this).valid()){
						return false;
					}
				}
				var form = $(this);
				var data = form.serialize();
				var url = form.attr('action');
				var method = form.attr('method');
				if(method == 'POST' || method == 'post'){
					method = 'POST';
				} else {
					method = 'GET';
				}
				$.ajax($.extend(options, {data : data, url : url, type : method}));
				return false;
			});
		});
	};
})(jQuery);