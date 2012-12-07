/**
 * 以ajax方式引入页面
 * <form class="action"--(辅助标识,包含display:none样式以隐藏form表单) action="url"--(请求提交的url) ajax="AjaxType"--(可选, 默认为03)  type="ActionType"--(可选, waterfall:瀑布流)>
 * 	<input name="paramName" value="paramValue">--参数, 可选
 * 	<li class="nodata">暂无数据</li>--无数据时显示的元素, 可选
 * </form>
 * 
 * @author HuLingwei
 */
(function($) {
	$.fn.action = function(){
		//console.debug(this);
		return $(this).each(function(){
			var action = $(this);
			var url = action.attr('action');
			var type = action.attr('method')||'POST';
			var ajaxType = action.attr('ajax')||'03';
			var renderType = action.attr('type');
			var pageNoName = null;
			var pageSizeName = null;
			var params = {};
			var parent = action.parent();
			var moreBtn = $('<div class="ajax_more"><a>查看更多</a></div>');
			action.find('input').each(function(){
				var param = $(this);
				var name = param.attr('name');
				if(name == null || name == ''){
					return;
				}
				var value = param.attr('value');
				var forName = param.attr('for');
				if( forName == 'pageNo'){
					pageNoName = name;
					value = parseInt(value);
				}else if(forName == 'pageSize'){
					pageSizeName = name;
					value = parseInt(value);
				}
				params[name] = value;
			});
			var nodata = action.find('.nodata');
			if(nodata.length > 0){
				nodata.detach();
			}else{
				nodata = null;
			}
			action.remove();
			var render = function(datas, isWater){
				if(isWater){
					moreBtn.detach();
				}
				var wait = $('<div class="ajax_wait' + ajaxType + '"><img style="" src="' + $.getScriptLocation('jquery.action.js') + 'images/ajax_wait' + ajaxType + '.gif" alt="waiting..."></div>').appendTo(parent);
				$.ajax({
					url : url,
					type : type,
					dataType : 'html',
					data : datas,
					success : function(data, textStatus, jqXHR){
						if(data == null || data == ''){
							if(nodata != null){
								wait.after(nodata);
							}
							wait.remove();
							return;
						}
						if(renderType != 'waterfall'){
							wait.after(data).remove();
							return;
						}
						//从返回结果中找出记录列
						var list = $(data).find('tr:first').nextAll();
						if(isWater){
							wait.remove();
							if(list.length > 0){
								parent.find('table').append(list);
							}
						}else{
							wait.after(data).remove();
						}
						//没有更多记录
						if(list.length < datas[pageSizeName]){
							return;
						}
						//点击更多按钮
						moreBtn.appendTo(parent).one('click', function(){
							datas[pageNoName] = datas[pageNoName] + 1;
							render(datas, true);
						});
					},
					error : function(jqXHR, textStatus, errorThrown){
						
					}
				});
			};
			render(params);
		});
	};
})(jQuery);

jQuery(function(){
	//载入依赖资源
	jQuery.loadResources('jquery.action.js', ['jquery.action.css']);
	//对所有包含action样式的form元素进行ajax action装饰
	jQuery('form.action').action();
});