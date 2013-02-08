var DEFAULT_VAL = '待输入';
/**$.extend($.validator.defaults, {
		errorPlacement: function(error, element) {   
			var errorId = $(element).attr("errorId");
			if(errorId){
				$("#"+errorId).html(error);
			}else if(element.parent().children(".validate-msg").length > 0){
   				element.parent().children(".validate-msg").append(error);
   			}else{
   				error.insertAfter(element); 
   			}
		}
} );**/
/**$(function(){
	$('label.error').live('beforeshow', function(event, speed, callback){
		if(speed === undefined){
			var prev = $(this).prev();
			var prevOffset = prev.offset();
			$(this).css({position:'absolute', left:prev.outerWidth() + prevOffset.left + 20, top : prevOffset.top}).data('show_param', {speed : 'slow', callback : function(){
				//$(this).hide();
				console.debug("h");
				}
			});
		}
	});
});
**/
$.extend($.validator.defaults, {
	ignore: "",
	showErrors: function() {
		var i, elements;
		for ( i = 0; this.errorList[i]; i++ ) {
			var error = this.errorList[i];
			var element = $(error.element);
			var el = error.element;
			if(!element.is(':visible')){
				var name = element.attr('name');
				//wrapped
				var wrapper = $('#for_' + name.replace('.', '\\.'));
				el = wrapper[0];
			}
			if ( this.settings.highlight ) {
				this.settings.highlight.call( this, el, this.settings.errorClass, this.settings.validClass );
			}
			this.showLabel( error.element, error.message );
		}
		if( this.errorList.length ) {
			this.toShow = this.toShow.add( this.containers );
		}
		if (this.settings.success) {
			for ( i = 0; this.successList[i]; i++ ) {
				this.showLabel( this.successList[i] );
			}
		}
		if (this.settings.unhighlight) {
			for ( i = 0, elements = this.validElements(); elements[i]; i++ ) {
				this.settings.unhighlight.call( this, elements[i], this.settings.errorClass, this.settings.validClass );
			}
		}
		this.toHide = this.toHide.not( this.toShow );
		this.hideErrors();
		this.addWrapper( this.toShow ).show();
	},
	unhighlight: function(element, errorClass, validClass) {
		if(!$(element).is(':visible')){
			var name = element.name;
			//wrapped
			var wrapper = $('#for_' + name.replace('.', '\\.'));
			element = wrapper[0];
		}
		if (element.type === 'radio') {
			this.findByName(element.name).removeClass(errorClass).addClass(validClass);
		} else {
			$(element).removeClass(errorClass).addClass(validClass);
		}
	}
});

$.validator.addMethod('tkno', function (value){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var regex = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	return regex.test(value);
	}, '车牌号码格式不正确');

$.validator.addMethod('calphanum', function (value){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var regex = /^[0-9a-zA-Z\u4e00-\u9fa5_]+$/;
	return regex.test(value);
	}, '只允许英文，中文，数字和下划线');

$.validator.addMethod('calphanum2', function (value){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var regex = /^[0-9a-zA-Z_]+$/;
	return regex.test(value);
	}, '只允许英文 、数字和下划线');

$.validator.addMethod('zh_cn', function (value){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var regex = /^[\u4e00-\u9fa5]+$/;
	return regex.test(value);
	}, '只允许输入中文');
	
$.validator.addMethod('fromToday', function (value) { 
	if(!value||!isDate(value))
		return true;
	var today = new Date();
	var dateArray = value.split('-');
	if((dateArray[0]<today.getFullYear())||
			( dateArray[0]==today.getFullYear()&&dateArray[1]<(today.getMonth()+1))||
			( dateArray[0]==today.getFullYear()&&dateArray[1]==(today.getMonth()+1)&&dateArray[2]<today.getDate())){
		return false;
	}
	return true;
}
, '日期不能小于今天');

$.validator.addMethod('fromDate', function (value,element, param){
	//以下情况忽略
	var c_v = $("input[name='"+param+"']").val();
	if(!value||!isDate(value) || !c_v || !isDate(c_v))
		return true;
	
	return fmp.util.parseDate(value) >= fmp.util.parseDate(c_v);
}, '日期不能小于最初起始时间');


$.validator.addMethod('dateAfter', function (value,element, param){
	//以下情况忽略
	var c_v = $("input[name='"+param+"']").val();
	if(!value||!isDate(value) || !c_v || !isDate(c_v))
		return true;
	
	return fmp.util.parseDate(value) > fmp.util.parseDate(c_v);
}, '结束日期必须大于起始日期');

$.validator.addMethod('dateEqualsAfter', function (value,element, param){
	//以下情况忽略
	var c_v = $("input[name='"+param+"']").val();
	if(!value||!isDate(value) || !c_v || !isDate(c_v))
		return true;
	
	return fmp.util.parseDate(value) >= fmp.util.parseDate(c_v);
}, '结束日期必须大于或等于起始日期');


function isAmount(value){
	var regex = /^-?\d+(\.\d{1,3})?$/;
	return regex.test(value);
}
$.validator.addMethod('amount', function (value){
	//以下情况忽略
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	
	return isAmount(value);
}, '只允许数字且小数位不能超过3位');

function isNum(value){
	var pattern=/^(([1-9]\d*)|(0))$/;
	return pattern.test(value);
	
}
$.validator.addMethod('num', function (value){
	//以下情况忽略
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	
	return isNum(value);
}, '请输入合法的整数');



//验证正数
$.validator.addMethod('positive', function(value){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	
	if(value > 0)
		return true;
	return false;
}, '必须大于0');

//小于等于另外一个字段
$.validator.addMethod('equalOrLessThan', function(value, element, param){
	
	if(value == null || value == '' || isNaN(value))
		return true;
	
	var biggerField = $(param[0]);
	var biggerValueType = param[1];
	var biggerValue = null;
	
	if('text' == biggerValueType){
		biggerValue = biggerField.text();
	}else if('value' == biggerValueType){
		biggerValue = biggerField.val();
	}
	
	biggerValue = parseInt(biggerValue);
	
	return parseInt(value) <= biggerValue ;
	
}, '值超出范围');

//验证金额大于等于0
$.validator.addMethod('vprice', function(value){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	if(value >= 0)
		return true;
	return false;
}, '必须大于等于0');

//验证
$.validator.addMethod('maxprice', function(value,element, param){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	if(value <= param[0])
		return true;
	return false;
}, '运价太高');

$.validator.addMethod('idno', function (num){
	if(num == '' || typeof(num) == 'undefined' || num == null)
		return true;
	
	num = num.toUpperCase();           //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。        
	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {     
	    return false;         
	} //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
	//下面分别分析出生日期和校验位
	var len, re; len = num.length; if (len == 15) {
	    re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
	    var arrSplit = num.match(re);  //检查生日日期是否正确
	    var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
	    var bGoodDay; bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
	    if (!bGoodDay) {         
	        return false;
	    } else { //将15位身份证转成18位 //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。        
	        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);         
	        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');      
	        var nTemp = 0, i;            
	        num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);           
	        for(i = 0; i < 17; i ++) {                 
	            nTemp += num.substr(i, 1) * arrInt[i];        
	        }
	        num += arrCh[nTemp % 11];
	        return true;
	    }
	}
	if (len == 18) {
	    re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
	    var arrSplit = num.match(re);  //检查生日日期是否正确
	    var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
	    var bGoodDay; bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
	    if (!bGoodDay) {
	        return false;
	    }
	    else { //检验18位身份证的校验码是否正确。 //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
	        var valnum;
	        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
	        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
	        var nTemp = 0, i;
	        for(i = 0; i < 17; i ++) {
	            nTemp += num.substr(i, 1) * arrInt[i];
	        }
	        valnum = arrCh[nTemp % 11];
	        if (valnum != num.substr(17, 1)) {
	            return false;
	        }
	        return true;
	    }
	} 
	return false;
}, '请正确填写身份证号码格式，如有“x”请填写大写“X”，您的身份证号码是诚信验证的重要凭证之一，560 将严格保密，请放心填写！');

$.validator.addMethod("byteRangeLength", function(value, element, param) {   
   var length = value.length;   
  for(var i = 0; i < value.length; i++){   
   if(value.charCodeAt(i) > 127){   
     length++;   
    }   
   }   
  return  ( length <= param );   
}, "字节长度超过限制");   


$.validator.addMethod('reg', function (value,element, param) { 
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var reg = new RegExp(param); 
	return reg.test(value);
}
, '不匹配输入的正则表达式');


$.validator.addMethod('dropDown', function (value,element, param) { 
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return false;
	return true;
}
, '请至少选择一个');

$.validator.addMethod('phoneOrMobile', function (value) {
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var phone = /^0(([1-9]\d)|([3-9]\d{2}))\d{8}$/;
	var mobile = /(^$)|(^1\d{10}$)$/; 
    return phone.test(value) ||  mobile.test(value); 
}
, '必须为手机或者座机');


$.validator.addMethod('fileType', function (value,element, param) {
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var regexp = "^.*?\.(";
	if(param.constructor != Array){
		regexp += param;
	}else{
		for(var i = 0; i < param.length; i++){
			regexp += param[i];
			if(i != (param.length -1)){
				regexp += "|";
			}
		}
	}
	regexp += ")$";
	var rp = new RegExp(regexp);
    return rp.test(value);
}
, '文件格式不正确');



function isDate(dateStr)
{ 
    var datePat = /^(\d{4})(\-)(\d{1,2})(\-)(\d{1,2})$/;
    var matchArray = dateStr.match(datePat);
    if (matchArray == null) return false; 
    var month = matchArray[3];
    var day = matchArray[5]; 
    var year = matchArray[1]; 
    if (month < 1 || month > 12) return false; 
    if (day < 1 || day > 31) return false; 
    if ((month==4 || month==6 || month==9 || month==11) && day==31) return false; 
    if (month == 2)
    {
        var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)); 
        if (day > 29 || (day==29 && !isleap)) return false; 
    } 
    return true;
}

$.validator.addMethod('avatar', function (value) {
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var patn = /\.jpg$|\.jpeg$|\.bmp$|\.gif$|\.png$/i;
    return patn.test(value); 
}
, '上传文件格式不正确');


/********************560字段规则********************************/
/**
 * 用户名(也可用于密码)
 */
$.validator.addMethod('userNameRule', function (value){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var regex = /^[0-9a-z_\-]{4,16}$/i;
	return regex.test(value);
	}, '账号由4-16 位半角字符（字母、数字、符号）组成，区分大小写。');
/**
 * 验证密码长度(6-16位)
 */
$.validator.addMethod('password', function (value){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var regex = /^[0-9a-z_\-]{6,16}$/;
	return regex.test(value);
	}, '密码由6-16 位半角字符（字母、数字、符号）组成，区分大小写。');
/**
 * 服务站名称（也可用于通讯地址）
 */
$.validator.addMethod('sstNameRule', function (value){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var regex = /^[0-9a-z\u4e00-\u9fa5](?:[0-9a-z\u4e00-\u9fa5\-_]*[0-9a-z\u4e00-\u9fa5])?$/i;
	return regex.test(value) && !isNum(value);
	}, '请正确填写服务站名称。');
/**
 * 真实姓名
 */
$.validator.addMethod('realName', function (value){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var regex = /^[\u4e00-\u9fa5]{2,6}$/;
	return regex.test(value);
	}, '请输入2-6 个汉字，真实姓名仅用于诚信验证和联系，560 将严格保密，请放心填写！');
/**
 * 服务站编号
 */
$.validator.addMethod('sstNORule', function (value){
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	var regex = /^[0-9a-z\-_]*$/i;
	return regex.test(value);
	}, '请正确填写编号格式！');
/**
 * 固定电话
 */
$.validator.addMethod('phone', function (value) {
	if(value == DEFAULT_VAL || value == '' || typeof(value) == 'undefined' || value == null){
		return true;
	}
	var pattern =/^0(([1-9]\d)|([3-9]\d{2}))\d{8}$/;
    return pattern.test(value); 
}
, '请正确填写您的固定电话，格式为：057128002560！');



/**
 * 运商联固定电话
 */
$.validator.addMethod('yslphone', function (value) {
	if(value == DEFAULT_VAL || value == '' || typeof(value) == 'undefined' || value == null){
		return true;
	}
	var pattern =/^0(([1-9]\d)|([3-9]\d{2}))\d{8}$|^(400|800)\d{7}$|^10000$/;
    return pattern.test(value); 
}
, '请正确填写您的固定电话，格式为：057128002560！');


/**
 * 手机
 */
$.validator.addMethod('mobile', function (value) {
	if(value == DEFAULT_VAL || value == '' || typeof(value) == 'undefined' || value == null){
		return true;
	}
	var pattern = /(^$)|(^1\d{10}$)$/; 
	return pattern.test(value); 
}, '请正确填写您的手机号码！');

/**
 * 手机和电话至少一个不为空
 */
$.validator.addMethod('require_from_group', function(value, element, options){
	var numberRequired = options[0];
	var selector = options[1];
	var numberFilled = $(selector, element.form).filter(function(){
		var val = $(this).val();
		return (val != DEFAULT_VAL && val != '' && typeof(val) != 'undefined' && val != null);
	}).length;
	var valid = numberFilled >= numberRequired;
	if(!$(element).data('reval')){
		var fields = $(selector, element.form);
		fields.data('reval', true);
		fields.valid();
		fields.data('reval', false);
	}
	return valid;
}, $.format('至少填写一个！'));

/**
 * QQ
 */
$.validator.addMethod('QQ', function (value) {
	if(value == '' || typeof(value) == 'undefined' || value == null)
		return true;
	
	var pattern = /^[1-9]\d{4,9}$/; 
    return pattern.test(value); 
}
, '请正确填写您的QQ 号码！');
/****************************************************/