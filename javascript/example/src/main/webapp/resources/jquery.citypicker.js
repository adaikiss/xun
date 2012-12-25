/**
 * city picker<br>
 * $('#citypicker').cityPicker({options});<br>
 * options: <br>
 *     common : common panel, default true,<br>
 *     level : city level, default 2,<br>
 *     generic : whether to include BX, default false,<br>
 *     join : city names joiner, default '-', eg:ProvinceName-CityName-CountyName,<br>
 *     associate : whether to open associate function, default false(not implemented yet!)<br>
 * @author HuLingwei
 */
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
				if(!cp.defaults.generic && province._code == 'QG'){
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
		var renderCounty = function(cp){
			cp.countyPanel = $('<div style="display:none;" class="citycontentpanel" xun_type="countyPanel"></div>').appendTo(cp.pickerPanel);
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
				if(cp.defaults.level == 2 && (province.type == 1 ||province.type == 2)){
					//二级选择框直辖市跳过市级
					cities = cities[0].counties;
				}
				for(var i = 0;i<cities.length;i++){
					var city = cities[i];
					//不要不限
					if(!cp.defaults.generic && city._code == 'BX'){
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
		var showCounty = function(cp){
			resetPanel(cp);
			cp.countyPanel.find('.countygroup').hide();
			cp.countyPanel.show();
			cp.countyTab.addClass('cur');
			var city = cp.selectedCity;
			if(cp.countyGroups[city.code] == null){
				cp.countyGroups[city.code] = $('<div class="countygroup"></div>').appendTo(cp.countyPanel);
				var counties = city.counties;
				for(var i = 0;i<counties.length;i++){
					var county = counties[i];
					//不要不限
					if(!cp.defaults.generic && county._code == 'BX'){
						continue;
					}
					var name = county.name;
					if(name.length > 4){
						name = name.substring(0, 4);
					}
					var countyTag = $('<span></span>').text(name).attr('xun_code', county.code).bind('click', {cCode : county.code}, function(event){
						event.stopPropagation();
						//already selected!
						if($(this).is('.cur')){
							return;
						}
						handleCountySelect(event.data.cCode, cp, $(this));
					});
					cp.countyGroups[city.code].append(countyTag);
				}
			}
			cp.countyGroups[city.code].show();
			if(cp.selectedCounty != null){
				cp.countyGroups[city.code].find('[xun_code="' + cp.selectedCounty.code + '"]').addClass('cur');
			}
		};
		var isInRegion = function(region, code){
			var codeChar = code.charCodeAt(0);
			return codeChar >= region[0].charCodeAt(0) && codeChar <= region[1].charCodeAt(0);
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
			if(cp.defaults.level > 1){
				showCity(cp);
			}else{
				setVal(province.getName(), province.code, cp);
				cp.pickerPanel.hide();
			}
		};
		var handleCitySelect = function(cCode, cp, el){
			cp.cityPanel.find('.cur').removeClass('cur');
			var selectedCity, selectedName;
			if(cp.defaults.level == 2 && (cp.selectedProvince.type == 1 ||cp.selectedProvince.type == 2)){
				//二级选择框直辖市跳过市级
				selectedCity = countyMap[cCode];
				selectedName = selectedCity.getName(cp.defaults.join, true);
			}else{
				selectedCity = cityMap[cCode];
				selectedName = selectedCity.getName(cp.defaults.join);
			}
			cp.selectedCity = selectedCity;
			el.addClass('cur');
			setVal(selectedName, selectedCity.code, cp);
			if(cp.defaults.level > 2){
				showCounty(cp);
			}else{
				cp.pickerPanel.hide();
			}
		};
		var handleCountySelect = function(cCode, cp, el){
			cp.countyPanel.find('.cur').removeClass('cur');
			var county = countyMap[cCode];
			cp.selectedCounty = county;
			el.addClass('cur');
			setVal(county.getName(cp.defaults.join), county.code, cp);
			cp.pickerPanel.hide();
		};
		var show = function(cp){
			cp.showing = true;
			resetPanel(cp);
			var originalVal = cp.hiddenInput.val();
			var offset = cp.input.offset();
			cp.pickerPanel.css({top : offset.top + cp.input.outerHeight(), left : offset.left}).show();
			cp.provincePanel.find('.cur').removeClass('cur');
			if(cp.defaults.common){
				cp.commonPanel.find('.cur').removeClass('cur');
			}
			if(cp.defaults.level > 1){
				cp.cityPanel.find('.cur').removeClass('cur');
				if(cp.defaults.level > 2){
					cp.countyPanel.find('.cur').removeClass('cur');
				}
			}
			if(originalVal != null && originalVal != ''){
				var values = originalVal.split('_');
				var province = provinceMap[values[0]];
				var isMunicipal = province.type == 1 ||province.type == 2;
				var level = values.length;
				if(level > cp.defaults.level){
					if(cp.defaults.level == 2 && values.length == 3 && isMunicipal){
						//保存原样
					}else{
						originalVal = values.slice(0, cp.defaults.level).join('_');
					}
					level = cp.defaults.level;
				}
				if(level == 1){
					cp.selectedProvince = provinceMap[originalVal];
					cp.provinceTab.addClass('cur');
					cp.provincePanel.find('[xun_code="' + originalVal + '"]').addClass('cur');
					if(cp.defaults.common){
						cp.commonPanel.find('[xun_code="' + originalVal + '"]').addClass('cur');
					}
					cp.provincePanel.show();
				}else if(level == 2){
					cp.selectedCity = isMunicipal&&cp.defaults.level == 2?countyMap[originalVal]:cityMap[originalVal];
					cp.selectedProvince = province;
					cp.cityTab.addClass('cur');
					cp.provincePanel.find('[xun_code="' + cp.selectedProvince.code + '"]').addClass('cur');
					if(cp.defaults.common){
						cp.commonPanel.find('[xun_code="' + cp.selectedProvince.code + '"]').addClass('cur');
					}
					showCity(cp);
					cp.cityPanel.find('[xun_code="' + originalVal + '"]').addClass('cur');
					cp.cityPanel.show(); 
				}else {
					cp.selectedCounty = countyMap[originalVal];
					cp.selectedCity = cp.selectedCounty.city;
					cp.selectedProvince = cp.selectedCity.province;
					cp.countyTab.addClass('cur');
					cp.provincePanel.find('[xun_code="' + cp.selectedProvince.code + '"]').addClass('cur');
					if(cp.defaults.common){
						cp.commonPanel.find('[xun_code="' + cp.selectedProvince.code + '"]').addClass('cur');
					}
					cp.cityPanel.find('[xun_code="' + cp.selectedCity.code + '"]').addClass('cur');
					showCounty(cp);
					cp.countyPanel.find('[xun_code="' + originalVal + '"]').addClass('cur');
					cp.countyPanel.show();
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
			cp.input.attr('style', cp.input.attr('style')||'' + cp.hiddenInput.attr('style') || '').show();
			cp.input.attr('class', cp.input.attr('class')||'' + ' ' + cp.hiddenInput.attr('class')||'');
			if(cp.hiddenInput.attr('id') != null){
				cp.input.attr('id', 'citypicker_' + cp.hiddenInput.attr('id'));
			}
			if(defaults.associate){
				cp.input.bind('keyup', function(){
					cp.pickerPanel.hide();
					associate(cp);
				});
			}
			var originalVal = cp.hiddenInput.val();
			if(originalVal != null && originalVal != ''){
				var values = originalVal.split('_');
				var province = provinceMap[values[0]];
				var isMunicipal = province.type == 1  ||province.type == 2;
				var level = values.length;
				if(level > cp.defaults.level){
					if(cp.defaults.level == 2 && values.length == 3 && isMunicipal){
						//保存原样
					}else{
						originalVal = values.slice(0, cp.defaults.level).join('_');
					}
					level = cp.defaults.level;
				}
				cp.selectedVal = originalVal;
				cp.hiddenInput.val(originalVal);
				switch(level){
				case 1 :
					cp.selectedName = provinceMap[originalVal].getName(cp.defaults.join);
					break;
				case 2 :
					cp.selectedName = isMunicipal&&cp.defaults.level == 2?countyMap[originalVal].getName(cp.defaults.join, true) : cityMap[originalVal].getName(cp.defaults.join);
					break;
				case 3 :
					cp.selectedName = countyMap[originalVal].getName(cp.defaults.join);
					break;
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
				if(defaults.level > 2){
					cp.countyTab = $('<li xun_for="countyPanel">地区</li>').appendTo(cp.tabPanel).bind('click', function(event){
						event.stopPropagation();
						//already selected, all no province has been selected!
						if(cp.countyTab.is('.cur') || cp.selectedCity == null){
							return;
						}
						showCounty(cp);
					});
					renderCounty(cp);
					cp.countyGroups = {};
				}
			}
			if(defaults.clearable){
				cp.input.bind('keydown', function(event){
					if(event.keyCode == 8){
						cp.clear();
					}
				});
			}
//			if(defaults.clearBtn){
//				cp.clearBtn = $('<li class="clearbtn">清除</li>').appendTo(cp.tabPanel).bind('click', function(event){
//					event.stopPropagation();
//					cp.clear();
//				});
//			}
			cp.hide = function(){
				cp.showing = false;
				if(cp.input.val() == null || cp.input.val() == ''){
					cp.input.val(cp.selectedName);
				}
				cp.pickerPanel.hide();
			};
			cp.valid = function(){
				return cp.input.val() == cp.selectedName;
			};
			cp.show = function(){
				show(this);
			};
			cp.clear = function(){
				cp.selectedProvince = null;
				cp.selectedCity = null;
				cp.selectedCounty = null;
				cp.selectedName = '';
				cp.hiddenInput.val('');
				cp.input.val('');
				cp.show();
			};
			//renderCity(citypicker);
			return $(this);
		});
	};
	$.fn.cityPicker.options = {common : true, level : 2, join : '-', associate : false, generic : false,/** clearBtn : true,**/ clearable : true};
	$.fn.cityPicker.commonCity = ['ZJ', 'JS', 'AH', 'HUN', 'JX', 'HB', 'HEB', 'HEN', 'SH'];
	$.fn.cityPicker.provinceRegions = ['A-G', 'H-K', 'L-S', 'T-Z'];
})(jQuery);
jQuery(function($){
	$(document).bind('click', function(event){
		var target = $(event.target);
		if(target.is('.citypicker') || target.parents('.citypicker').length != 0){
			return;
		}
		$('.citypicker').each(function(){
			$(this).data('citypicker').hide();
		});
	});
});