/**
 * 服务站评分,无通用性!import
 * 
 * @author HuLingwei
 */
(function($) {
	$.fn.score = function(options){
		return $(this).each(function(){
			var defaults = $.extend($.fn.score.defaults, options);
			var isSelf = defaults.userId == defaults.sstId;
			var cookieStr = $.cookie(defaults.cookie_key);
			var cookie;
			if(!cookieStr){
				cookie = {};
			}else{
				cookie = JSON.parse(cookieStr);
			}
			var cookieKey = defaults.userId + '_' + defaults.sstId;
			var scored = cookie[cookieKey];
			var container = $(this);
			var width = defaults.star_size * defaults.max_score;
			var wrapper = $('<span class="inline-block star_no"></span>').width(width).height(defaults.star_size).css({'position' : 'relative', 'overflow' : 'hidden'}).appendTo(container);
			var score_bar = $('<span class="star_score"></span>').appendTo(container);
			var star_bar = $('<span class="inline-block star_yes" style="overflow:hidden;"></span>').appendTo(wrapper);
			var score_text = $('#score_text');
			star_bar.width(Math.max(1, parseFloat(defaults.score)/defaults.unit_score * defaults.star_size)).height(defaults.star_size);
			score_bar.text(new Number(defaults.score).toPrecision(2) + '分');
			score_text.find('span').text(defaults.total);
			var scorePanel = $('<span class="hide star_no" style="position:relative;top:-20px;"></span>').width(width).height(defaults.star_size).appendTo(wrapper);
			for(var i = 0; i < defaults.max_score; i ++){
				$('<span class="inline-block star_no" style="cursor:pointer;"></span>').attr('title', (i + 1) * defaults.unit_score + '分').attr('fmp_index', i + 1).width(defaults.star_size)
					.height(defaults.star_size).appendTo(scorePanel).hover(function(){
						$(this).prevAll().andSelf().swapClass('star_no', 'star_yes');
					}, function(){
						scorePanel.find('span').swapClass('star_yes', 'star_no');
					}).click(function(){
						if(scored || isSelf){
							$(this).unbind('click');
							return false;
						}
						$.get(base + '/ajax/grade-detail/score.htm', {score : $(this).attr('fmp_index') * defaults.unit_score, sstId : defaults.sstId}, function(result){
							star_bar.width(parseFloat(result.data.AVERAGE)/defaults.unit_score * defaults.star_size).height(defaults.star_size);
							score_bar.text(new Number(result.data.AVERAGE).toPrecision(2) + '分');
							score_text.find('span').text(result.data.TOTAL);
							score_text.find('span').css({color : 'green'});
							cookie[cookieKey] = true;
							scored = true;
							$.cookie(defaults.cookie_key, JSON.stringify(cookie), defaults.cookie_expires);
							scorePanel.swapClass('inline-block', 'hide');
						});
					});
			}
			if(scored || isSelf){
				return;
			}
			container.hover(function(){
				if(scored || isSelf){return false;}
				scorePanel.swapClass('hide', 'inline-block');
			}, function(){
				if(scored || isSelf){return false;}
				scorePanel.swapClass('inline-block', 'hide');
			});
		});
	};
	//评分
	$.fn.score.defaults = {
		cookie_expires : 1,
		cookie_key : 'sst_score',
		max_score : 5,
		unit_score : 2,
		star_size : 20,
		score : 0,
		total : 0
	};
})(jQuery);