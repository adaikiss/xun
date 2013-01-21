(function($) {
	$.fn.cityPicker = function(options){
		var renderCommon = function(citypicker){
			if(!citypicker.defaults.common){
				return;
			}
			if(!citypicker.commonPanel){
				citypicker.commonPanel = $('<div style="display:none;" class="citycontentpanel" xun_type="commonPanel"></div>');
				for(var i = 0;i<citypicker.defaults.commonCity.length;i++){
					var pCode = citypicker.defaults.commonCity[i];
					var province = provinceMap[pCode];
					var cityTag = $('<span></span>').text(province.name).attr('xun_code', pCode);
					citypicker.commonPanel.append(cityTag);
				}
			}
			citypicker.commonPanel.insertAfter($.fn.cityPicker.tabPanel).show();
			citypicker.commonTab.show().addClass('cur');
		};
		var renderProvince = function(citypicker){
			if(!citypicker.provincePanel){
				citypicker.provincePanel = $('<div style="display:none;" class="citycontentpanel" xun_type="provincePanel"></div>');
				var regions = [];
				for(var i = 0;i < citypicker.defaults.provinceRegions.length;i++){
					$('<div class="provincegrouptag"></div>').text(citypicker.defaults.provinceRegions[i]).appendTo(citypicker.provincePanel);
					var regionContent = $('<div class="provincegroupcontent"></div>').appendTo(citypicker.provincePanel);
					var region = citypicker.defaults.provinceRegions[i].split('-');
					//handle one letter situation
					if(region.length == 1){
						region.push(region[0]);
					}
					regions.push([region, regionContent]);
				}
				for(var p in provinceMap){
					var province = provinceMap[p];
					for(var i = 0;i < regions.length;i++){
						if(isInRegion(regions[i][0], province._code)){
							var cityTag = $('<span></span>').text(province._name).attr('xun_code', province.code);
							regions[i][1].append(cityTag);
						}
					}
				}
			}
			citypicker.provincePanel.appendTo($.fn.cityPicker.pickerPanel).show();
			citypicker.provinceTab.show().addClass('cur');
			resetProvince(citypicker);
		};
		var renderCity = function(citypicker){
			var province = citypicker.selectedProvince;
			for(var i = 0;i<province.cities.length;i++){
				var city = province.cities[i];
				var cityTag = $('<span></span>').text(city.name).attr('xun_code', city.code);
				citypicker.cityPanel.append(cityTag);
			}
		};
		var isInRegion = function(region, code){
			var codeChar = code.charCodeAt(0);
			return codeChar >= region[0].charCodeAt(0) && codeChar <= region[1].charCodeAt(0);
		};
		var resetProvince = function(citypicker){
			citypicker.provincePanel.find('span.cur').removeClass('cur');
		};
		var resetCity = function(){
			$.fn.cityPicker.cityPanel.find('span.cur').removeClass('cur');
		};
		var showProvince = function(pCode, tab){
			if(tab){
				return $.fn.cityPicker.provincePanel.show();
			}
			resetProvince();
			$.fn.cityPicker.provincePanel.find('span[xun_code="' + pCode + '"]').addClass('cur');
		};
		var returnVal = function(name, code, citypicker){
			citypicker.hiddenInput.val(code);
			citypicker.input.val(name);
			$.fn.cityPicker.pickerPanel.hide();
		};
		var handleProvinceSelect = function(pCode, citypicker, el){
			var province = provinceMap[pCode];
			citypicker.selectedProvince = province;
			el.addClass('cur');
			if(citypicker.level == 2){
				showCity(citypicker);
			}else{
				returnVal(province.getName(), province.code, citypicker);
			}
			$.fn.cityPicker.provincePanel.hide();
		};
		var handleCitySelect = function(cCode, citypicker, el){
			var city = cityMap[cCode];
			citypicker.selectedCity = city;
			el.addClass('cur');
			returnVal(city.getName('-'), city.code, citypicker);
		};
		var show = function(citypicker){
			$.fn.cityPicker.tabPanel.find('li').removeClass('cur');
			$.fn.cityPicker.pickerPanel.find('.citycontentpanel').hide();
			var originalVal = citypicker.hiddenInput.val();
			$.fn.cityPicker.pickerPanel.show();
			citypicker.defaults.common ? citypicker.commonTab.show() : citypicker.commonTab.hide();
			citypicker.defaults.level > 1? citypicker.cityTab.show() : citypicker.cityTab.hide();
			if(originalVal != null && originalVal != ''){
				renderProvince(citypicker);
				var values = originalVal.split('_');
			}else{
				if(citypicker.defaults.common){
					renderCommon(citypicker);
				}else{
					renderProvince(citypicker);
				}
			}
		};
		return $(this).each(function(){
			var citypicker = {};
			citypicker.hiddenInput = $(this);
			citypicker.input = $('<input type="text">').insertAfter(citypicker.hiddenInput.hide());
			citypicker.input.bind('click', function(){
				show(citypicker);
			});
			citypicker.hiddenInput.data('citypicker', citypicker);
			var defaults = citypicker.defaults = $.extend({}, $.fn.cityPicker.options, options);
			defaults.commonCity = defaults.commonCity || $.fn.cityPicker.commonCity;
			defaults.provinceRegions = defaults.provinceRegions || $.fn.cityPicker.provinceRegions;
			if(null == $.fn.cityPicker.pickerPanel){
				var pickerPanel = $.fn.cityPicker.pickerPanel = $('<div class="citypicker" style="display:none;"></div>').appendTo('body');
				var tabPanel = $.fn.cityPicker.tabPanel = $('<ul class="citytabpanel"><li xun_for="commonPanel" style="display:none;">常用</li><li xun_for="provincePanel">省份</li><li xun_for="cityPanel" style="display:none;">城市</li></ul>').appendTo(pickerPanel);
				$.fn.cityPicker.cityPanel = $('<div style="display:none;" class="citycontentpanel" xun_type="cityPanel"></div>');
			}
			citypicker.commonTab = $.fn.cityPicker.tabPanel.find('[xun_for="commonPanel"]');
			citypicker.provinceTab = $.fn.cityPicker.tabPanel.find('[xun_for="provincePanel"]');
			citypicker.cityTab = $.fn.cityPicker.tabPanel.find('[xun_for="cityPanel"]');
			//renderCommon(citypicker);
			//renderProvince(citypicker);
			//renderCity(citypicker);
			return $(this);
		});
	};
	$.fn.cityPicker.cityPanel = null;
	$.fn.cityPicker.pickerPanel = null;
	$.fn.cityPicker.tabPanel = null;
	$.fn.cityPicker.options = {common : true, level : 2};
	$.fn.cityPicker.commonCity = ['ZJ', 'JS', 'AH', 'HUN', 'JX', 'HB', 'HEB', 'HEN', 'SH'];
	$.fn.cityPicker.provinceRegions = ['A-G', 'H-K', 'L-S', 'T-Z'];
})(jQuery);