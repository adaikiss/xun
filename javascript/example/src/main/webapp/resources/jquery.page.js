/**
 * 
 * 辅助freemarker宏c.page的JS
 * 
 * @author HuLingwei
 */
(function($) {
	$.page = function(formId, criteriaName, pageNo, pageSize){
		var form = $('#' + formId);
		var pageNoName = criteriaName + '.page';
		var pageSizeName = criteriaName + '.pagesize';
		var pageNoInput = form.find('input[name="' + pageNoName + '"]');
		if(pageNoInput.length == 0){
			pageNoInput = $('<input type="hidden" name="' + pageNoName + '">').appendTo(form);
		}
		pageNoInput.val(pageNo);
		var pageSizeInput = form.find('input[name="' + pageSizeName + '"]');
		if(pageSizeInput.length == 0){
			pageSizeInput = $('<input type="hidden" name="' + pageSizeName + '">').appendTo(form);
		}
		pageSizeInput.val(pageSize);
		$('.page[formId="' + formId + '"]').each(function(){
			var pagePanel = $(this);
			if(formId == null){
				return;
			}
			pagePanel.children('span:not(.disabled)').each(function(){
				var btn = $(this);
				var pageNo = btn.attr('pageNo');
				if(null != pageNo){
					btn.bind('click', function(){
						pageNoInput.val(pageNo);
						form.submit();
					}).hover(function(){
						btn.addClass('hover');
					}, function(){
						btn.removeClass('hover');
					});
					return;
				}
				if(btn.is('.btn')){
					btn.bind('click', function(){
						pageNo = pagePanel.find('input.pageNo').val();
						if(isNaN(pageNo)){
							pageNo = 1;
						}
						pageNoInput.val(pageNo);
						form.submit();
					});
				}
			});
		});
	};
})(jQuery);