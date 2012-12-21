(function($) {
	$.fn.cityPicker = function(options){
		var renderCommon = function(cp){
			cp.commonPanel = $('<div style="display:none;" class="citycontentpanel" xun_type="commonPanel"></div>').appendTo(cp.pickerPanel);
			for(var i = 0;i<cp.defaults.commonCity.length;i++){
				var pCode = cp.defaults.commonCity[i];
				var province = provinceMap[pCode];
				var cityTag = $('<span></span>').text(province.name).attr('xun_code', pCode).bind('click', {pCode : pCode}, function(event){
					event.stopPropagation();
					//already selected!
					if($(this).is('.cur')){
						return;
					}
					handleProvinceSelect(event.data.pCode, cp);
				});
				cp.commonPanel.append(cityTag);
			}
		};
		var renderProvince = function(cp){
			cp.provincePanel = $('<div style="display:none;" class="citycontentpanel" xun_type="provincePanel"></div>').appendTo(cp.pickerPanel);
			var regions = [];
			for(var i = 0;i < cp.defaults.provinceRegions.length;i++){
				$('<div class="provincegrouptag"></div>').text(cp.defaults.provinceRegions[i]).appendTo(cp.provincePanel);
				var regionContent = $('<div class="provincegroupcontent"></div>').appendTo(cp.provincePanel);
				var region = cp.defaults.provinceRegions[i].split('-');
				//handle one letter situation
				if(region.length == 1){
					region.push(region[0]);
				}
				regions.push([region, regionContent]);
			}
			for(var p in provinceMap){
				var province = provinceMap[p];
				//不要全国
				if(province._code == 'QG'){
					continue;
				}
				for(var i = 0;i < regions.length;i++){
					if(isInRegion(regions[i][0], province._code)){
						var cityTag = $('<span></span>').text(province._name).attr('xun_code', province.code).bind('click', {pCode : province.code}, function(event){
							event.stopPropagation();
							//already selected!
							if($(this).is('.cur')){
								return;
							}
							handleProvinceSelect(event.data.pCode, cp);
						});
						regions[i][1].append(cityTag);
					}
				}
			}
		};
		var renderCity = function(cp){
			cp.cityPanel = $('<div style="display:none;" class="citycontentpanel" xun_type="cityPanel"></div>').appendTo(cp.pickerPanel);
		};
		var showCity = function(cp){
			resetPanel(cp);
			cp.cityPanel.find('.citygroup').hide();
			cp.cityPanel.show();
			cp.cityTab.addClass('cur');
			var province = cp.selectedProvince;
			if(cp.cityGroups[province.code] == null){
				cp.cityGroups[province.code] = $('<div class="citygroup"></div>').appendTo(cp.cityPanel);
				var cities = province.cities;
				if(province.type == 1){
					//直辖市跳过市级
					cities = cities[0].counties;
				}
				for(var i = 0;i<cities.length;i++){
					var city = cities[i];
					//不要不限
					if(city._code == 'BX'){
						continue;
					}
					var name = city.name;
					if(name.length > 4){
						name = name.substring(0, 4);
					}
					var cityTag = $('<span></span>').text(name).attr('xun_code', city.code).bind('click', {cCode : city.code}, function(event){
						event.stopPropagation();
						//already selected!
						if($(this).is('.cur')){
							return;
						}
						handleCitySelect(event.data.cCode, cp, $(this));
					});
					cp.cityGroups[province.code].append(cityTag);
				}
			}
			cp.cityGroups[province.code].show();
			if(cp.selectedCity != null){
				cp.cityGroups[province.code].find('[xun_code="' + cp.selectedCity.code + '"]').addClass('cur');
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
		var setVal = function(name, code, cp){
			cp.hiddenInput.val(code);
			cp.input.val(name);
			cp.selectedVal = code;
			cp.selectedName = name;
		};
		var handleProvinceSelect = function(pCode, cp){
			cp.provincePanel.find('.cur').removeClass('cur');
			var province = provinceMap[pCode];
			cp.selectedProvince = province;
			resetPanel(cp);
			cp.provincePanel.find('[xun_code="' + pCode + '"]').addClass('cur');
			if(cp.defaults.common){
				cp.commonPanel.find('.cur').removeClass('cur');
				cp.commonPanel.find('[xun_code="' + pCode + '"]').addClass('cur');
			}
			setVal(province.getName(), province.code, cp);
			if(cp.defaults.level == 2){
				showCity(cp);
			}else{
				cp.pickerPanel.hide();
			}
		};
		var handleCitySelect = function(cCode, cp, el){
//			console.debug(cCode);
//			console.debug(cityMap);
			cp.cityPanel.find('.cur').removeClass('cur');
			var city;
			if(cp.selectedProvince.type == 1){
				//直辖市
				city = countyMap[cCode];
			}else{
				city = cityMap[cCode];
			}
			cp.selectedCity = city;
			el.addClass('cur');
			setVal(city.getName(cp.defaults.join), city.code, cp);
			cp.pickerPanel.hide();
		};
		var show = function(cp){
			cp.showing = true;
			resetPanel(cp);
			var originalVal = cp.hiddenInput.val();
			cp.pickerPanel.show();
			cp.provincePanel.find('.cur').removeClass('cur');
			if(cp.defaults.common){
				cp.commonPanel.find('.cur').removeClass('cur');
			}
			if(cp.defaults.level > 1){
				cp.cityPanel.find('.cur').removeClass('cur');
			}
			if(originalVal != null && originalVal != ''){
				if(originalVal.indexOf('_') == -1){
					cp.selectedProvince = provinceMap[originalVal];
					cp.provinceTab.addClass('cur');
					cp.provincePanel.find('[xun_code="' + originalVal + '"]').addClass('cur');
					if(cp.defaults.common){
						cp.commonPanel.find('[xun_code="' + originalVal + '"]').addClass('cur');
					}
					cp.provincePanel.show();
				}else{
					cp.selectedCity = cityMap[originalVal];
					if(cp.selectedCity != null){
						cp.selectedProvince = cp.selectedCity.province;
					}else{
						cp.selectedCity = countyMap[originalVal];
						cp.selectedProvince = cp.selectedCity.city.province;
					}
					cp.cityTab.addClass('cur');
					cp.provincePanel.find('[xun_code="' + cp.selectedProvince.code + '"]').addClass('cur');
					if(cp.defaults.common){
						cp.commonPanel.find('[xun_code="' + cp.selectedProvince.code + '"]').addClass('cur');
					}
					showCity(cp);
					cp.cityPanel.find('[xun_code="' + originalVal + '"]').addClass('cur');
					cp.cityPanel.show();
				}
			}else{
				if(cp.defaults.common){
					cp.commonTab.addClass('cur');
					cp.commonPanel.show();
				}else{
					cp.provinceTab.addClass('cur');
					cp.provincePanel.show();
				}
			}
		};
		var resetPanel = function(cp){
			cp.tabPanel.find('li').removeClass('cur');
			cp.pickerPanel.find('.citycontentpanel').hide();
		};
		var associate = function(cp){
			var val = cp.input.val();
			if(val == null || val == ''){
				return;
			}
			if(val.indexOf(cp.defaults.join) != -1){
				return;
			}
			var result = findCityStartWith(val);
			if(result.length == 0){
				//TODO:
			}
		};
		return $(this).each(function(){
			var cp = {};
			var defaults = cp.defaults = $.extend({}, $.fn.cityPicker.options, options);
			defaults.commonCity = defaults.commonCity || $.fn.cityPicker.commonCity;
			defaults.provinceRegions = defaults.provinceRegions || $.fn.cityPicker.provinceRegions;
			cp.hiddenInput = $(this);
			cp.input = $('<input type="text">').insertAfter(cp.hiddenInput.hide());
			if(defaults.associate){
				cp.input.bind('keyup', function(){
					cp.pickerPanel.hide();
					associate(cp);
				});
			}
			var originalVal = cp.hiddenInput.val();
			if(originalVal != null && originalVal != ''){
				cp.selectedVal = originalVal;
				if(originalVal.indexOf('_') == -1){
					cp.selectedName = provinceMap[originalVal].getName(cp.defaults.join);
				}else{
					cp.selectedName = (cityMap[originalVal] || countyMap[originalVal]).getName(cp.defaults.join);
				}
				cp.input.val(cp.selectedName);
			}
			if(!defaults.associate){
				cp.input.prop('readOnly', true);
			}
			cp.input.bind('click', function(event){
				event.stopPropagation();
				cp.input.select();
				show(cp);
			});
			cp.hiddenInput.data('citypicker', cp);
			cp.pickerPanel = $('<div class="citypicker" style="display:none;"></div>').appendTo('body');
			cp.pickerPanel.data('citypicker', cp);
			cp.tabPanel = $('<ul class="citytabpanel"></ul>').appendTo(cp.pickerPanel);
			if(defaults.common){				
				cp.commonTab = $('<li xun_for="commonPanel">常用</li>').appendTo(cp.tabPanel).bind('click', function(event){
					event.stopPropagation();
					if(cp.commonTab.is('.cur')){
						return;
					}
					resetPanel(cp);
					cp.commonTab.addClass('cur');
					cp.commonPanel.show();
				});
				renderCommon(cp);
			}
			cp.provinceTab = $('<li xun_for="provincePanel">省份</li>').appendTo(cp.tabPanel).bind('click', function(event){
				event.stopPropagation();
				if(cp.provinceTab.is('.cur')){
						return;
					}
					resetPanel(cp);
					cp.provinceTab.addClass('cur');
					cp.provincePanel.show();
			});
			renderProvince(cp);
			if(defaults.level > 1){
				cp.cityTab = $('<li xun_for="cityPanel">城市</li>').appendTo(cp.tabPanel).bind('click', function(event){
					event.stopPropagation();
					//already selected, all no province has been selected!
					if(cp.cityTab.is('.cur') || cp.selectedProvince == null){
						return;
					}
					showCity(cp);
				});
				renderCity(cp);
				cp.cityGroups = {};
			}
			cp.hide = function(){
				if(cp.input.val() == null || cp.input.val() == ''){
					cp.input.val(cp.selectedName);
				}
				cp.pickerPanel.hide();
			};
			cp.valid = function(){
				return cp.input.val() == cp.selectedName;
			};
			//renderCity(citypicker);
			return $(this);
		});
	};
	$.fn.cityPicker.options = {common : true, level : 2, join : '-', associate : false};
	$.fn.cityPicker.commonCity = ['ZJ', 'JS', 'AH', 'HUN', 'JX', 'HB', 'HEB', 'HEN', 'SH'];
	$.fn.cityPicker.provinceRegions = ['A-G', 'H-K', 'L-S', 'T-Z'];
})(jQuery);
jQuery(function($){
	$(document).bind('click', function(event){
		//console.debug(event);
		var target = $(event.target);
		if(target.is('.citypicker') || target.parents('.citypicker').length != 0){
			return;
		}
		$('.citypicker').each(function(){
			$(this).data('citypicker').hide();
		});
	});
});