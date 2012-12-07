if(!window.console){
	window.console = {};
}

$(function(){
	var dh = $('.dh');
	if(dh.length != 0){dh.prependTo($('#main')).show();}
	$($('script[src*="jquery-fmp.js"]')[0]).before(function(){
		var src = $(this).attr('src');
		var base = src.substring(0, src.indexOf('jquery-fmp.js'));
		var tag = '<link type="text/css" rel="stylesheet" href="' + base + 'fmp.css"/>';
		return tag;
	});
});