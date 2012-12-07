/**
 * 
 * 城市选择, 复制原input表单的style,class,并隐藏之.
 * 重置选择框: input.reset()或input.trigger('reset'),
 * 取城市的name: <code>input.attr('fmp_description')</code>,
 * 取城市的cod: <code>input.val()</code>
 * 
 * @author HuLingwei
 */
(function($) {
	$.fn.citySelector = function(opt){
		opt = $.extend({
			}, opt);
		var level = $(this).attr('level');
		level = level || 3;
		var uniquee = $.nextVal();
		var hiddenInputId = $.CitySelector.idPrefix + 'hidden_' + uniquee;
		var hiddenInput = $(this).prop('readOnly', true).attr({
			'fmp_for': uniquee, 'fmp_level': level, 'fmp_id': hiddenInputId, 'fmp_type': 'city_selector'
			}).hide();
		var originalValue = hiddenInput.val();
		var input = $('<div class="city-selector inline-block input"><div class="city-selector-name inline-block">请选择</div><div class="city-selector-btn inline-block"></div></div>').attr('name', hiddenInput.attr('name')).attr('fmp_for', uniquee).insertBefore(hiddenInput);
		input.addClass(hiddenInput.attr('class')||'').attr('style', input.attr('style')||'' + hiddenInput.attr('style')).show();
		var w = parseInt(hiddenInput.width());
		input.width(w);
		input.find('div:first').width(w - 15);
		if(!$.CitySelector.provincePanel){
			$.CitySelector.initProvince();
		}
		input.bind('click', function(event){
			event.stopPropagation();
			if(!$.CitySelector.mainPanel){
				$.CitySelector.initMainPanel();
			}
			//clear
			$.CitySelector.mainPanel.find('.city-content').hide();
			$.CitySelector.mainPanel.find('.city-tool').children('span:not(.btn)').text('');
			var oldVal = hiddenInput.val();
			$.CitySelector.mainPanel.attr('fmp_for', uniquee).css({top : input.offset().top + input.height(), left : input.offset().left}).show();
			if(null != oldVal && '' != oldVal){
				$.CitySelector.setValue(oldVal, uniquee);
			}else{
				$.CitySelector.provincePanel.show();
			}
		});
		var displayInput = input.find('div:first');
		if(originalValue != null && originalValue != ''){
			if(!$.CitySelector.mainPanel){
				$.CitySelector.initMainPanel();
			}
			var data = $.CitySelector.setValue(originalValue, uniquee);
			var description = $.CitySelector.format([data.provinceName, data.cityName, data.countyName]);
			displayInput.text(description);
			hiddenInput.attr('fmp_description', description);
		}
		hiddenInput.bind('reset', function(){
			hiddenInput.val('');
			displayInput.text('请选择');
		});
		return hiddenInput;
	};
	$.CitySelector = {
		provincePanel : null,
		cityPanelCache : {},
		countyPanelCache : {},
		hideEventBinded : false,
		idPrefix : 'fmp_city_selector_',
		mainPanel : null,
		noDefault : function(str){
			return str == '不限'?'':str;
		},
		isWide : function(value){
			return value == 'QG' || value == 'QG_BX' || value == 'QG_BX_BX';
		},
		format : function(names){
			if(names[0] == '' || names[0] == null){
				//return '全国';
				return '请选择';
			}
			if(names.length == 1){
				return names[0];
			}
			var name = [names[0]];
			if(names.length >1){
				if(names[1] == '不限' || names[1] == null || names[1] == ''){
					return names[0];
				}
				if(names[1] != names[0]){
					name.push(names[1]);
				}
				if(names.length > 2){
					if(names[2] != '不限' && names[2] != null && names[2] != ''){
						name.push(names[2]);
					}
				}
			}
			return name.join('');
		},
		getDefaultValue : function(level){
			return '';
			/**if(level == 1){
				return 'QG';
			}
			if(level == 2){
				return 'QG_BX';
			}
			return 'QG_BX_BX';
			**/
		},
		initMainPanel : function(){
			var mainPanel = $.CitySelector.mainPanel = $('<div class="city-main"></div>').appendTo('body');
			var toolPanel = $('<div class="city-tool"></div>').appendTo(mainPanel);
			var provinceSpan = $('<span class="province"></span>').appendTo(toolPanel);
			var citySpan = $('<span class="city"></span>').appendTo(toolPanel);
			var countySpan = $('<span class="county"></span>').appendTo(toolPanel);
			$('<span class="btn">清除</span>').bind('click', function(){
				mainPanel.find('.city-content').hide();
				$.CitySelector.provincePanel.show();
				toolPanel.attr('fmp_value', '');
				provinceSpan.text('');
				citySpan.text('');
				countySpan.text('');
			}).appendTo(toolPanel);
			$('<span class="btn">确定</span>').bind('click', function(){
				var id = mainPanel.attr('fmp_for');
				var value = toolPanel.attr('fmp_value');
				var input = $('.city-selector[fmp_for="' + id + '"]').find('div:first');
				var description = $.CitySelector.format([provinceSpan.text(), citySpan.text(), countySpan.text()]);
				var hiddenInput = $('input[fmp_id="' + $.CitySelector.idPrefix + 'hidden_' + id + '"]');
				input.text(description);
				if(value == ''){
					value = $.CitySelector.getDefaultValue(hiddenInput.attr('fmp_level'));
				}
				if($.CitySelector.isWide(value)){
					hiddenInput.val('');
				}else{
					hiddenInput.val(value);
				}
				hiddenInput.attr('fmp_description', description);
				//trigger validation
				hiddenInput.trigger('keyup');
				mainPanel.find('.city-content').hide();
				mainPanel.hide();
			}).appendTo(toolPanel);
			$.CitySelector.initProvince();
		},
		setValue : function(value, id){
			if(null == value || '' == value){
				return;
			}
			var names = findCityName(value);
			names.push('不限');
			names.push('不限');
			var values = value.split('_');
			values.push('BX');
			values.push('BX');
			var hiddenInput = $('input[fmp_id="' + $.CitySelector.idPrefix + 'hidden_' + id + '"]');
			var level = hiddenInput.attr('fmp_level');
			var data = {provinceName : names[0], provinceCode : values[0]};
			$.CitySelector.setProvince(data);
			if(level == 1){
				$.CitySelector.provincePanel.show();
			}
			if(level > 1){
				data.cityName = names[1];
				data.cityCode = values[0] + '_' + values[1];
				$.CitySelector.setCity(data);
			}
			if(level == 2){				
				$.CitySelector.getCity(data).show();
			}
			if(level == 3){
				data.countyName = names[2];
				data.countyCode = values[0] + '_' + values[1] + '_' + values[2];
				$.CitySelector.setCounty(data);
				$.CitySelector.getCounty(data).show();
			}
			return data;
		},
		setProvince : function(data){
			var tool = $.CitySelector.mainPanel.find('.city-tool');
			tool.find('.province').text(data.provinceName);
			tool.attr('fmp_value', data.provinceCode);
		},
		setCity : function(data){
			var tool = $.CitySelector.mainPanel.find('.city-tool');
			tool.find('.city').text(data.cityName);
			tool.attr('fmp_value', data.cityCode);
		},
		setCounty : function(data){
			var tool = $.CitySelector.mainPanel.find('.city-tool');
			tool.find('.county').text(data.countyName);
			tool.attr('fmp_value', data.countyCode);
		},
		initProvince : function(){
			var provincePanel = $.CitySelector.provincePanel = $('<div class="city-content"></div>').appendTo($.CitySelector.mainPanel).show();
			var provinceUl = $('<ul></ul>').appendTo(provincePanel);
			for(var i = 0; i < cityLevel1Array.length; i ++){
				var code = cityLevel1Array[i].cityLevel1Id;
				if(code == 'QG'){continue;}
				var name = cityLevel1Array[i].cityLevel1Name;
				provinceUl.append($('<li></li>').attr('code', code).text(name)
						.bind('click', {provinceName : name, provinceCode : code}, function(event){
					event.stopPropagation();
					$.CitySelector.setProvince(event.data);
					var id = $.CitySelector.mainPanel.attr('fmp_for');
					var hiddenInput = $('input[fmp_id="' + $.CitySelector.idPrefix + 'hidden_' + id + '"]');
					if(hiddenInput.attr('fmp_level') > 1){
						$(this).parents('.city-content').hide();
						$.CitySelector.getCity(event.data).show();
					}
				}));
			}
			return provincePanel;
		},
		getCity : function(data){
			if($.CitySelector.cityPanelCache[data.provinceCode] != null){
				return $.CitySelector.cityPanelCache[data.provinceCode];
			}
			var cityPanel = $.CitySelector.cityPanelCache[data.provinceCode] = $('<div class="city-content"></div>').appendTo($.CitySelector.mainPanel);
			var cityUl = $('<ul></ul>').appendTo(cityPanel);
			var cityLevel2Array = findCityLevel1(data.provinceCode).cityLevel2Array;
			for(var i = 0; i <cityLevel2Array.length; i ++){
				var code = cityLevel2Array[i].cityLevel2Id;
				var name = cityLevel2Array[i].cityLevel2Name;
				cityUl.append($('<li></li>').attr('code', code).text(name)
						.bind('click', {cityName : name, cityCode : code}, function(event){
					event.stopPropagation();
					var d = event.data;
					d.provinceName = data.provinceName;
					d.provinceCode = data.provinceCode;
					$.CitySelector.setCity(d);
					var id = $.CitySelector.mainPanel.attr('fmp_for');
					var hiddenInput = $('input[fmp_id="' + $.CitySelector.idPrefix + 'hidden_' + id + '"]');
					if(hiddenInput.attr('fmp_level') > 2){
						$(this).parents('.city-content').hide();
						$.CitySelector.getCounty(d).show();
					}
				}));
			}
			return cityPanel;
		},
		getCounty : function(data){
			if($.CitySelector.countyPanelCache[data.cityCode] != null){
				return $.CitySelector.countyPanelCache[data.cityCode];
			}
			var countyPanel = $.CitySelector.countyPanelCache[data.cityCode] = $('<div class="city-content"></div>').appendTo($.CitySelector.mainPanel);
			var countyUl = $('<ul></ul>').appendTo(countyPanel);
			var cityLevel3Array = findCityLevel2(data.provinceCode, data.cityCode).cityLevel3Array;
			for(var i = 0; i <cityLevel3Array.length; i ++){
				var code = cityLevel3Array[i].cityId;
				var name = cityLevel3Array[i].cityName;
				countyUl.append($('<li></li>').attr('code', code).text(name)
						.bind('click', {countyName : name, countyCode : code}, function(event){
					event.stopPropagation();
					var d = event.data;
					d.provinceName = data.provinceName;
					d.provinceCode = data.provinceCode;
					d.cityName = data.cityName;
					d.cityCode = data.cityCode;
					$.CitySelector.setCounty(d);
				}));
			}
			return countyPanel;
		}
	};
})(jQuery);

jQuery(function(){
	//载入依赖资源
	jQuery.loadResources('jquery.city.js', ['jquery.city.css', 'city.js']);
	//对所有有fmp-city-selector样式的元素进行citySelector装饰
	jQuery('.fmp-city-selector').each(function(){jQuery(this).citySelector();});
});