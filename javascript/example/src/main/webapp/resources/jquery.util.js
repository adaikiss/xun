/**
 * 一些辅助类方法
 * 
 * @author HuLingwei
 */
(function($) {
	/**
	 * 获取script脚本路径
	 */
	$.getScriptLocation = function(scriptName){
		var script = $('script[src*="' + scriptName + '"]');
		var attr = script.attr('src');
		var location = attr.substring(0, attr.indexOf(scriptName));
		return location;
	};
	$.isArray = function(obj){
		return typeof(obj) == 'object' && (obj instanceof Array);
	};
	/**
	 * 根据script脚本路径加载资源,以script脚本所有路径为根路径
	 */
	$.loadResources = function(scriptName, resources){
		var script = $('script[src*="' + scriptName + '"]');
		var attr = script.attr('src');
		var location = attr.substring(0, attr.indexOf(scriptName));
		var charset = script.attr('charset');
		if(!$.isArray(resources)){
			resources = [resources];
		}
		for(var i = 0;i < resources.length; i ++){
			var url = location + resources[i];
			if(resources[i].indexOf('.css') != -1){
				if($('link[href="' + url + '"]').length == 0){
					var tag = $('<link type="text/css" rel="stylesheet">');
					script.before(tag);
					tag.attr('href', url);
				}
			}else{
				if($('script[src="' + url + '"]').length == 0){
					var tag = $('<script type="text/javascript"></script>').attr('src', url);
					if(charset != null){
						tag.attr('charset', charset);
					}
					script.before(tag);
				}
			}
		}
	};
	var _oldShow = $.fn.show;
	$.fn.show = function(speed, callback) {
		return $(this).each(function() {
			$(this).trigger('beforeshow', [speed, callback]);
			var param = $(this).data('show_param');
			if(param){
				if(param.prevent){
					return;
				}
				if(param.speed != undefined){
					speed = param.speed;
				}
				if(param.callback != undefined){
					callback = param.callback;
				}
			}
			//trigger show event
			_oldShow.apply($(this), [speed, callback]);
		});
	};
	var _oldHide = $.fn.hide;
	$.fn.hide = function(speed, callback) {
		return $(this).each(function() {
			//trigger hide event
			_oldHide.apply($(this).trigger('hide'), [speed, callback]);
		});
	};
	$.extend($.fn, {
		swapClass : function(toRemove, toAdd){
			return $(this).each(function(){
				$(this).addClass(toAdd).removeClass(toRemove);
			});
		}
	});
	

	$._id = 0;
	$.nextVal = function(){
		return ++$._id;
	};
	$.toggle = function(tabs){
		tabs.each(function(){
			$(this).click(function(){
				var fmp_for = $(this).attr('fmp_for');
				for(var i = 0;i < tabs.length; i++){
					var tab = $(tabs[i]);
					var this_for = tab.attr('fmp_for');
					if(this_for == fmp_for){
						tab.addClass('cur');
						$('#' + this_for).show();
					}else{
						tab.removeClass('cur');
						$('#' + this_for).hide();
					}
				}
			});
		});
	};
	
	$.isCookieEnabled = function(){
		var cookieEnabled=(navigator.cookieEnabled)? true : false;
		//if not IE4+ nor NS6+
		if (typeof navigator.cookieEnabled=="undefined" && !cookieEnabled){ 
			document.cookie="testcookie";
			cookieEnabled=(document.cookie.indexOf("testcookie")!=-1)? true : false;
		}
		return cookieEnabled;
	};
})(jQuery);