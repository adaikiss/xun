//type{0 : 普通行省, 1 : 直辖市, 2 : 特别行政区, 3 : 自治区}
function Province(country, code, name, options){
	options = options || {};
	this._type = 'Province';
	this.country = country;
	this._name = this.name = name;
	this._code = this.code = code;
	this.type = options.type == null ? 0 : options.type;
	this.fullName = options.fullName;
	this.cities = [];
	this.addCity = function(city){
		this.cities.push(city);
	};
	switch(this.type){
	case 0 : 
		this.name += '省';
		break;
	case 1 : 
		this.name += '市';
		break;
	}
	if(this.fullName == null){
		this.fullName = this.name;
	}
	if(this.name.length > 3){
		this.name = this.name.substring(0, 3);
	}
	this.getName = function(){
		return this.fullName;
	};
	provinceMap[code] = this;
}

function City(province, provinceName, code, name){
	this._type = 'City';
	this.province = province;
	this._name = this.name = name;
	this._code = code;
	this.code = province.code + '_' + code;
	this.wild = this._code == 'BX';
	province.addCity(this);
	this.counties = [];
	this.addCounty = function(county){
		this.counties.push(county);
	};
	this.getName = function(join){
		return province.fullName + join + this.name;
	};
	cityMap[this.code] = this;
}

function County(city, code, name){
	this._type = 'County';
	this.city = city;
	this._name = this.name = name;
	this._code = code;
	this.code = city.code + '_' + code;
	this.wild = this._code == 'BX';
	city.addCounty(this);
	this.getName = function(join, notDropCityOnMunicipal){
		if(!notDropCityOnMunicipal && this.city.province.type == 1){
			return this.city.province.fullName + join + this.name;
		}
		return city.getName(join) + join + this.name;
	};
	countyMap[this.code] = this;
}
if (typeof String.prototype.startsWith != 'function') {
	String.prototype.startsWith = function (str){
		return this.slice(0, str.length) == str;
	};
}
if (typeof String.prototype.endsWith != 'function') {
	String.prototype.endsWith = function (str){
		return this.slice(-str.length) == str;
	};
}
function findCityStartWith(name, count, wrapper){
	var result = [];
	for(var code in cityMap){
		if(cityMap[code].fullName.startsWith(name)){
			result.push(cityMap[code]);
		}
		if(result.length >= count){
			break;
		}
	}
	return result;
}
var provinceMap = {};
var cityMap = {};
var countyMap = {};
var province = new Province('CN','BJ','北京', {type : 1});
var c = new City(province, '北京','BJ','北京');
new County(c, 'BX','不限');
new County(c, 'DCQ','东城区');
new County(c, 'XCQ','西城区');
new County(c, 'CYQ','朝阳区');
new County(c, 'FTQ','丰台区');
new County(c, 'SJSQ','石景山区');
new County(c, 'HDQ','海淀区');
new County(c, 'MTGQ','门头沟区');
new County(c, 'FSQ','房山区');
new County(c, 'TZQ','通州区');
new County(c, 'SYQ','顺义区');
new County(c, 'CPQ','昌平区');
new County(c, 'MYX','密云县');
new County(c, 'YQX','延庆县');
new County(c, 'DXQ','大兴区');
new County(c, 'HRQ','怀柔区');
new County(c, 'PGQ','平谷区');

c = new City(province, '北京','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','QG','全国');
c = new City(province, '全国', 'BX', '不限');
new County(c, 'BX', '不限');


var province = new Province('CN','TJ','天津', {type : 1});
c = new City(province, '天津','TJ','天津');
new County(c, 'BX','不限');
new County(c, 'HPQ','和平区');
new County(c, 'HDQ','河东区');
new County(c, 'HXQ','河西区');
new County(c, 'NKQ','南开区');
new County(c, 'HBQ','河北区');
new County(c, 'HQQ','红桥区');
new County(c, 'TGQ','塘沽区');
new County(c, 'HGQ','汉沽区');
new County(c, 'DGQ','大港区');
new County(c, 'DLQ','东丽区');
new County(c, 'XQQ','西青区');
new County(c, 'JNQ','津南区');
new County(c, 'BCQ','北辰区');
new County(c, 'WQQ','武清区');
new County(c, 'BC','宝坻区');

c = new City(province, '天津','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','HEB','河北');
c = new City(province, '河北','SJZ','石家庄');
new County(c, 'BX','不限');
new County(c, 'ZAQ','长安区');
new County(c, 'QDQ','桥东区');
new County(c, 'QXQ','桥西区');
new County(c, 'XHQ','新华区');
new County(c, 'JXKQ','井陉矿区');
new County(c, 'YHQ','裕华区');
new County(c, 'JXX','井陉县');
new County(c, 'ZDX','正定县');
new County(c, 'LCX','栾城县');
new County(c, 'XTX','行唐县');
new County(c, 'LSX','灵寿县');
new County(c, 'GYX','高邑县');
new County(c, 'SZX','深泽县');
new County(c, 'ZHX','赞皇县');
new County(c, 'WJX','无极县');
new County(c, 'PSX','平山县');
new County(c, 'YSX','元氏县');
new County(c, 'ZX','赵县');
new County(c, 'XJ','辛集');
new County(c, 'GC','藁城');
new County(c, 'JZ','晋州');
new County(c, 'XL','新乐');
new County(c, 'LQ','鹿泉');

c = new City(province, '河北','TS','唐山');
new County(c, 'BX','不限');
new County(c, 'LNQ','路南区');
new County(c, 'LBQ','路北区');
new County(c, 'GYQ','古冶区');
new County(c, 'KPQ','开平区');
new County(c, 'FNQ','丰南区');
new County(c, 'FRQ','丰润区');
new County(c, 'LX','滦县');
new County(c, 'LNX','滦南县');
new County(c, 'LTX','乐亭县');
new County(c, 'QXX','迁西县');
new County(c, 'YTX','玉田县');
new County(c, 'THX','唐海县');
new County(c, 'ZH','遵化');
new County(c, 'QA','迁安');

c = new City(province, '河北','QHD','秦皇岛');
new County(c, 'BX','不限');
new County(c, 'HGQ','海港区');
new County(c, 'SHGQ','山海关区');
new County(c, 'BDHQ','北戴河区');
new County(c, 'QLX','青龙县');
new County(c, 'CLX','昌黎县');
new County(c, 'FNX','抚宁县');
new County(c, 'LLX','卢龙县');

c = new City(province, '河北','HD','邯郸');
new County(c, 'BX','不限');
new County(c, 'HSQ','邯山区');
new County(c, 'CTQ','丛台区');
new County(c, 'FXQ','复兴区');
new County(c, 'FFKQ','峰峰矿区');
new County(c, 'HDX','邯郸县');
new County(c, 'LZX','临漳县');
new County(c, 'CAX','成安县');
new County(c, 'DMX','大名县');
new County(c, 'SX','涉县');
new County(c, 'CX','磁县');
new County(c, 'FXX','肥乡县');
new County(c, 'YNX','永年县');
new County(c, 'QX','邱县');
new County(c, 'JZX','鸡泽县');
new County(c, 'GPX','广平县');
new County(c, 'GTX','馆陶县');
new County(c, 'WX','魏县');
new County(c, 'QZX','曲周县');
new County(c, 'WA','武安');

c = new City(province, '河北','XT','邢台');
new County(c, 'BX','不限');
new County(c, 'QDQ','桥东区');
new County(c, 'QXQ','桥西区');
new County(c, 'XTX','邢台县');
new County(c, 'LCX','临城县');
new County(c, 'NQX','内丘县');
new County(c, 'BXX','柏乡县');
new County(c, 'LYX','隆尧县');
new County(c, 'RX','任县');
new County(c, 'NHX','南和县');
new County(c, 'NJX','宁晋县');
new County(c, 'JLX','巨鹿县');
new County(c, 'XHX','新河县');
new County(c, 'GZX','广宗县');
new County(c, 'PXX','平乡县');
new County(c, 'WX','威县');
new County(c, 'QHX','清河县');
new County(c, 'LXX','临西县');
new County(c, 'NG','南宫');
new County(c, 'SH','沙河');

c = new City(province, '河北','BD','保定');
new County(c, 'BX','不限');
new County(c, 'XSQ','新市区');
new County(c, 'BSQ','北市区');
new County(c, 'NSQ','南市区');
new County(c, 'MCX','满城县');
new County(c, 'QYX','清苑县');
new County(c, 'LSX','涞水县');
new County(c, 'FPX','阜平县');
new County(c, 'XSX','徐水县');
new County(c, 'DXX','定兴县');
new County(c, 'TX','唐县');
new County(c, 'GYX','高阳县');
new County(c, 'RCX','容城县');
new County(c, 'LYX','涞源县');
new County(c, 'WDX','望都县');
new County(c, 'AXX','安新县');
new County(c, 'YX','易县');
new County(c, 'QYAX','曲阳县');
new County(c, 'LX','蠡县');
new County(c, 'SPX','顺平县');
new County(c, 'BYX','博野县');
new County(c, 'XX','雄县');
new County(c, 'ZZ','涿州');
new County(c, 'DZ','定州');
new County(c, 'AG','安国');
new County(c, 'GBD','高碑店');

c = new City(province, '河北','ZJK','张家口');
new County(c, 'BX','不限');
new County(c, 'QDQ','桥东区');
new County(c, 'QXQ','桥西区');
new County(c, 'XHQ','宣化区');
new County(c, 'XHYQ','下花园区');
new County(c, 'XHX','宣化县');
new County(c, 'ZBX','张北县');
new County(c, 'KBX','康保县');
new County(c, 'GYX','沽源县');
new County(c, 'SYX','尚义县');
new County(c, 'YX','蔚县');
new County(c, 'YYX','阳原县');
new County(c, 'HAX','怀安县');
new County(c, 'WQX','万全县');
new County(c, 'HLX','怀来县');
new County(c, 'ZLX','涿鹿县');
new County(c, 'CCX','赤城县');
new County(c, 'CLX','崇礼县');

c = new City(province, '河北','CD','承德');
new County(c, 'BX','不限');
new County(c, 'SQQ','双桥区');
new County(c, 'SLQ','双滦区');
new County(c, 'YSYZ','鹰手营子');
new County(c, 'CDX','承德县');
new County(c, 'XLX','兴隆县');
new County(c, 'PQX','平泉县');
new County(c, 'LPX','滦平县');
new County(c, 'LHX','隆化县');
new County(c, 'FNX','丰宁县');
new County(c, 'KCX','宽城县');
new County(c, 'WCX','围场县');

c = new City(province, '河北','CZ','沧州');
new County(c, 'BX','不限');
new County(c, 'XHQ','新华区');
new County(c, 'YHQ','运河区');
new County(c, 'CX','沧县');
new County(c, 'QX','青县');
new County(c, 'DGX','东光县');
new County(c, 'HXX','海兴县');
new County(c, 'YSX','盐山县');
new County(c, 'SNX','肃宁县');
new County(c, 'NPX','南皮县');
new County(c, 'WQX','吴桥县');
new County(c, 'XX','献县');
new County(c, 'MCX','孟村县');
new County(c, 'BT','泊头');
new County(c, 'RQ','任丘');
new County(c, 'HH','黄骅');
new County(c, 'HJ','河间');

c = new City(province, '河北','LF','廊坊');
new County(c, 'BX','不限');
new County(c, 'ACQ','安次区');
new County(c, 'GYQ','广阳区');
new County(c, 'GAX','固安县');
new County(c, 'YQX','永清县');
new County(c, 'XHX','香河县');
new County(c, 'DCX','大城县');
new County(c, 'WAX','文安县');
new County(c, 'DCHX','大厂县');
new County(c, 'BZ','霸州');
new County(c, 'SH','三河');

c = new City(province, '河北','HS','衡水');
new County(c, 'BX','不限');
new County(c, 'TCQ','桃城区');
new County(c, 'ZQX','枣强县');
new County(c, 'WYX','武邑县');
new County(c, 'WQX','武强县');
new County(c, 'RYX','饶阳县');
new County(c, 'APX','安平县');
new County(c, 'GCX','故城县');
new County(c, 'JX','景县');
new County(c, 'FCX','阜城县');
new County(c, 'JZ','冀州');

c = new City(province, '河北','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','SX','山西');
c = new City(province, '山西','TY','太原');
new County(c, 'BX','不限');
new County(c, 'XDQ','小店区');
new County(c, 'YZQ','迎泽区');
new County(c, 'XHLQ','杏花岭区');
new County(c, 'JCPQ','尖草坪区');
new County(c, 'WBLQ','万柏林区');
new County(c, 'JYQ','晋源区');
new County(c, 'QXX','清徐县');
new County(c, 'YQX','阳曲县');
new County(c, 'LFX','娄烦县');
new County(c, 'GJ','古交');

c = new City(province, '山西','DT','大同');
new County(c, 'BX','不限');
new County(c, 'CQ','城区');
new County(c, 'KQ','矿区');
new County(c, 'NJQ','南郊区');
new County(c, 'XRQ','新荣区');
new County(c, 'YGX','阳高县');
new County(c, 'TZX','天镇县');
new County(c, 'GLX','广灵县');
new County(c, 'LQX','灵丘县');
new County(c, 'HYX','浑源县');
new County(c, 'ZYX','左云县');
new County(c, 'DTX','大同县');

c = new City(province, '山西','YQ','阳泉');
new County(c, 'BX','不限');
new County(c, 'CQ','城区');
new County(c, 'KQ','矿区');
new County(c, 'JQ','郊区');
new County(c, 'PDX','平定县');
new County(c, 'YX','盂县');

c = new City(province, '山西','ZZ','长治');
new County(c, 'BX','不限');
new County(c, 'CQ','城区');
new County(c, 'JQ','郊区');
new County(c, 'CZX','长治县');
new County(c, 'XYX','襄垣县');
new County(c, 'TLX','屯留县');
new County(c, 'PSX','平顺县');
new County(c, 'LCX','黎城县');
new County(c, 'HGX','壶关县');
new County(c, 'ZZIX','长子县');
new County(c, 'WXX','武乡县');
new County(c, 'QX','沁县');
new County(c, 'QYX','沁源县');
new County(c, 'LC','潞城');

c = new City(province, '山西','JC','晋城');
new County(c, 'BX','不限');
new County(c, 'CQ','城区');
new County(c, 'QSX','沁水县');
new County(c, 'YCX','阳城县');
new County(c, 'LCX','陵川县');
new County(c, 'ZZX','泽州县');
new County(c, 'GP','高平');

c = new City(province, '山西','SZ','朔州');
new County(c, 'BX','不限');
new County(c, 'SCQ','朔城区');
new County(c, 'PLQ','平鲁区');
new County(c, 'SYX','山阴县');
new County(c, 'YX','应县');
new County(c, 'YYX','右玉县');
new County(c, 'HRX','怀仁县');

c = new City(province, '山西','JZ','晋中');
new County(c, 'BX','不限');
new County(c, 'YCQ','榆次区');
new County(c, 'YSX','榆社县');
new County(c, 'ZQX','左权县');
new County(c, 'HSX','和顺县');
new County(c, 'XYX','昔阳县');
new County(c, 'SYX','寿阳县');
new County(c, 'TGX','太谷县');
new County(c, 'QX','祁县');
new County(c, 'PYX','平遥县');
new County(c, 'LSX','灵石县');
new County(c, 'JX','介休');

c = new City(province, '山西','YC','运城');
new County(c, 'BX','不限');
new County(c, 'YHQ','盐湖区');
new County(c, 'LYX','临猗县');
new County(c, 'WRX','万荣县');
new County(c, 'WXX','闻喜县');
new County(c, 'JSX','稷山县');
new County(c, 'XJX','新绛县');
new County(c, 'JX','绛县');
new County(c, 'YQX','垣曲县');
new County(c, 'XX','夏县');
new County(c, 'PLX','平陆县');
new County(c, 'RCX','芮城县');
new County(c, 'YJ','永济');
new County(c, 'HJ','河津');

c = new City(province, '山西','XZ','忻州');
new County(c, 'BX','不限');
new County(c, 'XFQ','忻府区');
new County(c, 'DXX','定襄县');
new County(c, 'WTX','五台县');
new County(c, 'DX','代县');
new County(c, 'FZX','繁峙县');
new County(c, 'NWX','宁武县');
new County(c, 'JLX','静乐县');
new County(c, 'SCX','神池县');
new County(c, 'WZX','五寨县');
new County(c, 'KLX','岢岚县');
new County(c, 'HQX','河曲县');
new County(c, 'BDX','保德县');
new County(c, 'PGX','偏关县');
new County(c, 'YP','原平');

c = new City(province, '山西','LF','临汾');
new County(c, 'BX','不限');
new County(c, 'YDQ','尧都区');
new County(c, 'QWX','曲沃县');
new County(c, 'YCX','翼城县');
new County(c, 'XFX','襄汾县');
new County(c, 'HDX','洪洞县');
new County(c, 'GX','古县');
new County(c, 'AZX','安泽县');
new County(c, 'FSX','浮山县');
new County(c, 'J','吉');
new County(c, 'XNX','乡宁县');
new County(c, 'DNX','大宁县');
new County(c, 'XX','隰县');
new County(c, 'YHX','永和县');
new County(c, 'PX','蒲县');
new County(c, 'FXX','汾西县');
new County(c, 'HM','侯马');
new County(c, 'HZ','霍州');

c = new City(province, '山西','LL','吕梁');
new County(c, 'BX','不限');
new County(c, 'LSQ','离石区');
new County(c, 'WSX','文水县');
new County(c, 'JCX','交城县');
new County(c, 'XX','兴县');
new County(c, 'LX','临县');
new County(c, 'LLX','柳林县');
new County(c, 'SLX','石楼县');
new County(c, 'LAX','岚县');
new County(c, 'FSX','方山县');
new County(c, 'ZYX','中阳县');
new County(c, 'JKX','交口县');
new County(c, 'XY','孝义');
new County(c, 'FY','汾阳');

c = new City(province, '山西','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','NMG','内蒙古', {type : 3, fullName : '内蒙古自治区'});
c = new City(province, '内蒙古','HHHT','呼和浩特');
new County(c, 'BX','不限');
new County(c, 'XCQ','新城区');
new County(c, 'HMQ','回民区');
new County(c, 'YQQ','玉泉区');
new County(c, 'SHQ','赛罕区');
new County(c, 'TMTZQ','土默特左旗');
new County(c, 'TKTX','托克托县');
new County(c, 'HLGEX','和林格尔县');
new County(c, 'QSHX','清水河县');
new County(c, 'WCX','武川县');

c = new City(province, '内蒙古','BT','包头');
new County(c, 'BX','不限');
new County(c, 'DHQ','东河区');
new County(c, 'KDLQ','昆都仑区');
new County(c, 'QSQ','青山区');
new County(c, 'SGQ','石拐区');
new County(c, 'BYKQ','白云矿区');
new County(c, 'JYQ','九原区');
new County(c, 'TYQ','土右旗');
new County(c, 'GYX','固阳县');
new County(c, 'DMQ','达茂旗');

c = new City(province, '内蒙古','WH','乌海');
new County(c, 'BX','不限');
new County(c, 'HBWQ','海勃湾区');
new County(c, 'HNQ','海南区');
new County(c, 'WDQ','乌达区');

c = new City(province, '内蒙古','CF','赤峰');
new County(c, 'BX','不限');
new County(c, 'HSQ','红山区');
new County(c, 'YBSQ','元宝山区');
new County(c, 'SSQ','松山区');
new County(c, 'AQ','阿旗');
new County(c, 'BLZQ','巴林左旗');
new County(c, 'BLYQ','巴林右旗');
new County(c, 'LXX','林西县');
new County(c, 'KSKTQ','克什克腾旗');
new County(c, 'WNTQ','翁牛特旗');
new County(c, 'KLQQ','喀喇沁旗');
new County(c, 'NCX','宁城县');
new County(c, 'AHQ','敖汉旗');

c = new City(province, '内蒙古','TL','通辽');
new County(c, 'BX','不限');
new County(c, 'KEQQ','科尔沁区');
new County(c, 'KZZQ','科左中旗');
new County(c, 'KZHQ','科左后旗');
new County(c, 'KLX','开鲁县');
new County(c, 'KLQ','库伦旗');
new County(c, 'NMQ','奈曼旗');
new County(c, 'ZLTQ','扎鲁特旗');
new County(c, 'HLGL','霍林郭勒');

c = new City(province, '内蒙古','EEDS','鄂尔多斯');
new County(c, 'DSQ','东胜区');
new County(c, 'DLTQ','达拉特旗');
new County(c, 'ZGEQ','准格尔旗');
new County(c, 'ETKQQ','鄂托克前旗');
new County(c, 'ETKQ','鄂托克旗');
new County(c, 'HJQ','杭锦旗');
new County(c, 'WSQ','乌审旗');
new County(c, 'YJHLQ','伊金霍洛旗');

c = new City(province, '内蒙古','HLBE','呼伦贝尔');
new County(c, 'BX','不限');
new County(c, 'HLEQ','海拉尔区');
new County(c, 'ARQ','阿荣旗');
new County(c, 'MQ','莫旗');
new County(c, 'ELC','鄂伦春');
new County(c, 'EWKZ','鄂温克族');
new County(c, 'CBEHQ','陈巴尔虎旗');
new County(c, 'XZQ','新左旗');
new County(c, 'XYQ','新右旗');
new County(c, 'MZL','满洲里');
new County(c, 'YKS','牙克石');
new County(c, 'ZLT','扎兰屯');
new County(c, 'EEGN','额尔古纳');
new County(c, 'GH','根河');

c = new City(province, '内蒙古','BYNE','巴彦淖尔');
new County(c, 'BX','不限');
new County(c, 'LHQ','临河区');
new County(c, 'WYX','五原县');
new County(c, 'DKX','磴口县');
new County(c, 'WLTQQ','乌拉特前旗');
new County(c, 'WLTZQ','乌拉特中旗');
new County(c, 'WLTHQ','乌拉特后旗');
new County(c, 'HJHQ','杭锦后旗');

c = new City(province, '内蒙古','WLCB','乌兰察布');
new County(c, 'BX','不限');
new County(c, 'JNQ','集宁区');
new County(c, 'ZZX','卓资县');
new County(c, 'HDX','化德县');
new County(c, 'SDX','商都县');
new County(c, 'XHX','兴和县');
new County(c, 'LCX','凉城县');
new County(c, 'CYQQ','察右前旗');
new County(c, 'CYZQ','察右中旗');
new County(c, 'CYHQ','察右后旗');
new County(c, 'SZWQ','四子王旗');
new County(c, 'FZ','丰镇');

c = new City(province, '内蒙古','XAM','兴安盟');
new County(c, 'WLHT','乌兰浩特');
new County(c, 'AES','阿尔山');
new County(c, 'KYQQ','科右前旗');
new County(c, 'KYZQ','科右中旗');
new County(c, 'ZLTQ','扎赉特旗');
new County(c, 'TQX','突泉县');

c = new City(province, '内蒙古','XLGLM','锡林郭勒盟');
new County(c, 'ELHT','二连浩特');
new County(c, 'XLHT','锡林浩特');
new County(c, 'ABGQ','阿巴嘎旗');
new County(c, 'SNTZQ','苏尼特左旗');
new County(c, 'SNTYQ','苏尼特右旗');
new County(c, 'DWQ','东乌旗');
new County(c, 'XWQ','西乌旗');
new County(c, 'TPSQ','太仆寺旗');
new County(c, 'XHQ','镶黄旗');
new County(c, 'ZXBQ','正镶白旗');
new County(c, 'ZLQ','正蓝旗');
new County(c, 'DLX','多伦县');

c = new City(province, '内蒙古','ALSM','阿拉善盟');
new County(c, 'ALSZQ','阿拉善左旗');
new County(c, 'ALSYQ','阿拉善右旗');
new County(c, 'EJNQ','额济纳旗');

c = new City(province, '内蒙古','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','LN','辽宁');
c = new City(province, '辽宁','SY','沈阳');
new County(c, 'BX','不限');
new County(c, 'HPQ','和平区');
new County(c, 'SHQ','沈河区');
new County(c, 'DDQ','大东区');
new County(c, 'HGQ','皇姑区');
new County(c, 'TXQ','铁西区');
new County(c, 'SJTQ','苏家屯区');
new County(c, 'DLQ','东陵区');
new County(c, 'XCZQ','新城子区');
new County(c, 'YHQ','于洪区');
new County(c, 'LZX','辽中县');
new County(c, 'KPX','康平县');
new County(c, 'FKX','法库县');
new County(c, 'XM','新民');

c = new City(province, '辽宁','DL','大连');
new County(c, 'BX','不限');
new County(c, 'ZSQ','中山区');
new County(c, 'XGQ','西岗区');
new County(c, 'SHKQ','沙河口区');
new County(c, 'GJZQ','甘井子区');
new County(c, 'LSKQ','旅顺口区');
new County(c, 'JZQ','金州区');
new County(c, 'ZHX','长海县');
new County(c, 'WFD','瓦房店');
new County(c, 'PLD','普兰店');
new County(c, 'ZH','庄河');

c = new City(province, '辽宁','AS','鞍山');
new County(c, 'BX','不限');
new County(c, 'TDQ','铁东区');
new County(c, 'TXQ','铁西区');
new County(c, 'LSQ','立山区');
new County(c, 'QSQ','千山区');
new County(c, 'TAX','台安县');
new County(c, 'XYX','岫岩县');
new County(c, 'HC','海城');

c = new City(province, '辽宁','FS','抚顺');
new County(c, 'BX','不限');
new County(c, 'XFQ','新抚区');
new County(c, 'DZQ','东洲区');
new County(c, 'WHQ','望花区');
new County(c, 'SCQ','顺城区');
new County(c, 'FSX','抚顺县');
new County(c, 'XBX','新宾县');
new County(c, 'QYX','清原县');

c = new City(province, '辽宁','BXI','本溪');
new County(c, 'BX','不限');
new County(c, 'PSQ','平山区');
new County(c, 'XHQ','溪湖区');
new County(c, 'MSQ','明山区');
new County(c, 'NFQ','南芬区');
new County(c, 'BXX','本溪县');
new County(c, 'HRX','桓仁县');

c = new City(province, '辽宁','DD','丹东');
new County(c, 'BX','不限');
new County(c, 'YBQ','元宝区');
new County(c, 'ZXQ','振兴区');
new County(c, 'ZAQ','振安区');
new County(c, 'KDX','宽甸县');
new County(c, 'DG','东港');
new County(c, 'FC','凤城');

c = new City(province, '辽宁','JZ','锦州');
new County(c, 'BX','不限');
new County(c, 'GTQ','古塔区');
new County(c, 'LHQ','凌河区');
new County(c, 'THQ','太和区');
new County(c, 'HSX','黑山县');
new County(c, 'YX','义县');
new County(c, 'LH','凌海');
new County(c, 'BN','北宁');

c = new City(province, '辽宁','YK','营口');
new County(c, 'BX','不限');
new County(c, 'ZQQ','站前区');
new County(c, 'XSQ','西市区');
new County(c, 'BYQQ','鲅鱼圈区');
new County(c, 'LBQ','老边区');
new County(c, 'GZ','盖州');
new County(c, 'DSQ','大石桥');

c = new City(province, '辽宁','FX','阜新');
new County(c, 'BX','不限');
new County(c, 'HZQ','海州区');
new County(c, 'XQQ','新邱区');
new County(c, 'TPQ','太平区');
new County(c, 'QHMQ','清河门区');
new County(c, 'XHQ','细河区');
new County(c, 'FXX','阜新县');
new County(c, 'ZWX','彰武县');

c = new City(province, '辽宁','LY','辽阳');
new County(c, 'BX','不限');
new County(c, 'BTQ','白塔区');
new County(c, 'WSQ','文圣区');
new County(c, 'HWQ','宏伟区');
new County(c, 'GZLQ','弓长岭区');
new County(c, 'TZHQ','太子河区');
new County(c, 'LYX','辽阳县');
new County(c, 'DT','灯塔');

c = new City(province, '辽宁','PJ','盘锦');
new County(c, 'BX','不限');
new County(c, 'STZQ','双台子区');
new County(c, 'XLTQ','兴隆台区');
new County(c, 'DWX','大洼县');
new County(c, 'PSX','盘山县');

c = new City(province, '辽宁','TL','铁岭');
new County(c, 'BX','不限');
new County(c, 'YZQ','银州区');
new County(c, 'QHQ','清河区');
new County(c, 'TLX','铁岭县');
new County(c, 'XFX','西丰县');
new County(c, 'CTX','昌图县');
new County(c, 'DBS','调兵山');
new County(c, 'KY','开原');

c = new City(province, '辽宁','CY','朝阳');
new County(c, 'BX','不限');
new County(c, 'STQ','双塔区');
new County(c, 'LCQ','龙城区');
new County(c, 'CYX','朝阳县');
new County(c, 'JPX','建平县');
new County(c, 'KZX','喀左县');
new County(c, 'BP','北票');
new County(c, 'LY','凌源');

c = new City(province, '辽宁','HLD','葫芦岛');
new County(c, 'BX','不限');
new County(c, 'LSQ','连山区');
new County(c, 'LGQ','龙港区');
new County(c, 'NPQ','南票区');
new County(c, 'SZX','绥中县');
new County(c, 'JCX','建昌县');
new County(c, 'XC','兴城');

c = new City(province, '辽宁','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','JL','吉林');
c = new City(province, '吉林','ZC','长春');
new County(c, 'BX','不限');
new County(c, 'NGQ','南关区');
new County(c, 'KCQ','宽城区');
new County(c, 'CYQ','朝阳区');
new County(c, 'EDQ','二道区');
new County(c, 'LYQ','绿园区');
new County(c, 'SYQ','双阳区');
new County(c, 'NAX','农安县');
new County(c, 'JT','九台');
new County(c, 'YS','榆树');
new County(c, 'DH','德惠');

c = new City(province, '吉林','JL','吉林');
new County(c, 'BX','不限');
new County(c, 'CYIQ','昌邑区');
new County(c, 'LTQ','龙潭区');
new County(c, 'CYQ','船营区');
new County(c, 'FMQ','丰满区');
new County(c, 'YJX','永吉县');
new County(c, 'JH','蛟河');
new County(c, 'HD','桦甸');
new County(c, 'SL','舒兰');
new County(c, 'PS','磐石');

c = new City(province, '吉林','SP','四平');
new County(c, 'BX','不限');
new County(c, 'TXQ','铁西区');
new County(c, 'TDQ','铁东区');
new County(c, 'LSX','梨树县');
new County(c, 'YTX','伊通县');
new County(c, 'GZL','公主岭');
new County(c, 'SL','双辽');

c = new City(province, '吉林','LY','辽源');
new County(c, 'BX','不限');
new County(c, 'LSQ','龙山区');
new County(c, 'XAQ','西安区');
new County(c, 'DFX','东丰县');
new County(c, 'DLX','东辽县');

c = new City(province, '吉林','TH','通化');
new County(c, 'BX','不限');
new County(c, 'DCQ','东昌区');
new County(c, 'EDJQ','二道江区');
new County(c, 'THX','通化县');
new County(c, 'HNX','辉南县');
new County(c, 'LHX','柳河县');
new County(c, 'MHK','梅河口');
new County(c, 'JA','集安');

c = new City(province, '吉林','BS','白山');
new County(c, 'BX','不限');
new County(c, 'BDJQ','八道江区');
new County(c, 'FSX','抚松县');
new County(c, 'JYUX','靖宇县');
new County(c, 'ZBX','长白县');
new County(c, 'JYX','江源县');
new County(c, 'LJ','临江');

c = new City(province, '吉林','SY','松原');
new County(c, 'BX','不限');
new County(c, 'NJQ','宁江区');
new County(c, 'QGX','前郭县');
new County(c, 'ZLX','长岭县');
new County(c, 'QAX','乾安县');
new County(c, 'FYX','扶余县');

c = new City(province, '吉林','BC','白城');
new County(c, 'BX','不限');
new County(c, 'TBQ','洮北区');
new County(c, 'ZLX','镇赉县');
new County(c, 'TYX','通榆县');
new County(c, 'TN','洮南');
new County(c, 'DA','大安');

c = new City(province, '吉林','YBCZ','延边朝州');
new County(c, 'YJ','延吉');
new County(c, 'TM','图们');
new County(c, 'DH','敦化');
new County(c, 'HC','珲春');
new County(c, 'LJ','龙井');
new County(c, 'HL','和龙');
new County(c, 'WQX','汪清县');
new County(c, 'ATX','安图县');

c = new City(province, '吉林','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','HLJ','黑龙江');
c = new City(province, '黑龙江','HEB','哈尔滨');
new County(c, 'BX','不限');
new County(c, 'DALQ','道里区');
new County(c, 'NGQ','南岗区');
new County(c, 'DWQ','道外区');
new County(c, 'XFQ','香坊区');
new County(c, 'DLQ','动力区');
new County(c, 'PFQ','平房区');
new County(c, 'SBQ','松北区');
new County(c, 'HLQ','呼兰区');
new County(c, 'YLX','依兰县');
new County(c, 'FZX','方正县');
new County(c, 'BXI','宾县');
new County(c, 'BYX','巴彦县');
new County(c, 'MLX','木兰县');
new County(c, 'THX','通河县');
new County(c, 'YSX','延寿县');
new County(c, 'AC','阿城');
new County(c, 'SC','双城');
new County(c, 'SZ','尚志');
new County(c, 'WC','五常');

c = new City(province, '黑龙江','QQHE','齐齐哈尔');
new County(c, 'BX','不限');
new County(c, 'LSQ','龙沙区');
new County(c, 'JHQ','建华区');
new County(c, 'TFQ','铁锋区');
new County(c, 'AAXQ','昂昂溪区');
new County(c, 'FLEJQ','富拉尔基区');
new County(c, 'NZSQ','碾子山区');
new County(c, 'MLS','梅里斯');
new County(c, 'LJX','龙江县');
new County(c, 'YAX','依安县');
new County(c, 'TLX','泰来县');
new County(c, 'GNX','甘南县');
new County(c, 'FYX','富裕县');
new County(c, 'KSX','克山县');
new County(c, 'KDX','克东县');
new County(c, 'BQX','拜泉县');
new County(c, 'NH','讷河');

c = new City(province, '黑龙江','JX','鸡西');
new County(c, 'BX','不限');
new County(c, 'JGQ','鸡冠区');
new County(c, 'HSQ','恒山区');
new County(c, 'DDQ','滴道区');
new County(c, 'LSQ','梨树区');
new County(c, 'CZHQ','城子河区');
new County(c, 'MSQ','麻山区');
new County(c, 'JDX','鸡东县');
new County(c, 'HL','虎林');
new County(c, 'MS','密山');

c = new City(province, '黑龙江','HG','鹤岗');
new County(c, 'BX','不限');
new County(c, 'XYQ','向阳区');
new County(c, 'GNQ','工农区');
new County(c, 'NSQ','南山区');
new County(c, 'XAQ','兴安区');
new County(c, 'DSQ','东山区');
new County(c, 'XSQ','兴山区');
new County(c, 'LBX','萝北县');
new County(c, 'SBX','绥滨县');

c = new City(province, '黑龙江','SYS','双鸭山');
new County(c, 'BX','不限');
new County(c, 'JSQ','尖山区');
new County(c, 'LDQ','岭东区');
new County(c, 'SFTQ','四方台区');
new County(c, 'BSQ','宝山区');
new County(c, 'JXX','集贤县');
new County(c, 'YYX','友谊县');
new County(c, 'BQX','宝清县');
new County(c, 'RHX','饶河县');

c = new City(province, '黑龙江','DQ','大庆');
new County(c, 'BX','不限');
new County(c, 'SETQ','萨尔图区');
new County(c, 'LFQ','龙凤区');
new County(c, 'RHLQ','让胡路区');
new County(c, 'HGQ','红岗区');
new County(c, 'DTQ','大同区');
new County(c, 'ZZX','肇州县');
new County(c, 'ZYX','肇源县');
new County(c, 'LDX','林甸县');
new County(c, 'DEBTX','杜尔伯特县');

c = new City(province, '黑龙江','YC','伊春');
new County(c, 'BX','不限');
new County(c, 'YCQ','伊春区');
new County(c, 'NCQ','南岔区');
new County(c, 'YHQ','友好区');
new County(c, 'XLQ','西林区');
new County(c, 'CLQ','翠峦区');
new County(c, 'XQQ','新青区');
new County(c, 'MXQ','美溪区');
new County(c, 'JSTQ','金山屯区');
new County(c, 'WYQ','五营区');
new County(c, 'WMHQ','乌马河区');
new County(c, 'TWHQ','汤旺河区');
new County(c, 'DLQ','带岭区');
new County(c, 'WYLQ','乌伊岭区');
new County(c, 'HXQ','红星区');
new County(c, 'SGLQ','上甘岭区');
new County(c, 'JYX','嘉荫县');
new County(c, 'TL','铁力');

c = new City(province, '黑龙江','JMS','佳木斯');
new County(c, 'BX','不限');
new County(c, 'YHQ','永红区');
new County(c, 'XYQ','向阳区');
new County(c, 'QJQ','前进区');
new County(c, 'DFQ','东风区');
new County(c, 'JQ','郊区');
new County(c, 'HNX','桦南县');
new County(c, 'HCX','桦川县');
new County(c, 'TYX','汤原县');
new County(c, 'FYX','抚远县');
new County(c, 'TJ','同江');
new County(c, 'FJ','富锦');

c = new City(province, '黑龙江','QTH','七台河');
new County(c, 'BX','不限');
new County(c, 'XXQ','新兴区');
new County(c, 'TSQ','桃山区');
new County(c, 'QZHQ','茄子河区');
new County(c, 'BLX','勃利县');

c = new City(province, '黑龙江','MDJ','牡丹江');
new County(c, 'BX','不限');
new County(c, 'DAQ','东安区');
new County(c, 'YMQ','阳明区');
new County(c, 'AMQ','爱民区');
new County(c, 'XAQ','西安区');
new County(c, 'DNX','东宁县');
new County(c, 'LKX','林口县');
new County(c, 'SFH','绥芬河');
new County(c, 'HL','海林');
new County(c, 'NA','宁安');
new County(c, 'ML','穆棱');

c = new City(province, '黑龙江','HH','黑河');
new County(c, 'BX','不限');
new County(c, 'AHQ','爱辉区');
new County(c, 'NJX','嫩江县');
new County(c, 'XKX','逊克县');
new County(c, 'SWX','孙吴县');
new County(c, 'BA','北安');
new County(c, 'WDLC','五大连池');

c = new City(province, '黑龙江','SH','绥化');
new County(c, 'BX','不限');
new County(c, 'BLQ','北林区');
new County(c, 'WKX','望奎县');
new County(c, 'LXX','兰西县');
new County(c, 'QGX','青冈县');
new County(c, 'QAX','庆安县');
new County(c, 'MSX','明水县');
new County(c, 'SLX','绥棱县');
new County(c, 'AD','安达');
new County(c, 'ZD','肇东');
new County(c, 'HL','海伦');

c = new City(province, '黑龙江','DXAL','大兴安岭');
new County(c, 'HMX','呼玛县');
new County(c, 'THX','塔河县');
new County(c, 'MHX','漠河县');

c = new City(province, '黑龙江','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','SH','上海', {type : 1});
c = new City(province, '上海','SH','上海');
new County(c, 'BX','不限');
new County(c, 'HPQ','黄浦区');
new County(c, 'LWQ','卢湾区');
new County(c, 'XHQ','徐汇区');
new County(c, 'ZNQ','长宁区');
new County(c, 'JAQ','静安区');
new County(c, 'PTQ','普陀区');
new County(c, 'ZBQ','闸北区');
new County(c, 'HKQ','虹口区');
new County(c, 'YPQ','杨浦区');
new County(c, 'MXQ','闵行区');
new County(c, 'BSQ','宝山区');
new County(c, 'JDQ','嘉定区');
new County(c, 'PDXQ','浦东新区');
new County(c, 'JSQ','金山区');
new County(c, 'SJQ','松江区');
new County(c, 'QPQ','青浦区');
new County(c, 'NHQ','南汇区');
new County(c, 'FXQ','奉贤区');
new County(c, "CM", "崇明县");

c = new City(province, '上海','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','JS','江苏');
c = new City(province, '江苏','NJ','南京');
new County(c, 'BX','不限');
new County(c, 'XWQ','玄武区');
new County(c, 'BXQ','白下区');
new County(c, 'QHQ','秦淮区');
new County(c, 'JYQ','建邺区');
new County(c, 'GLQ','鼓楼区');
new County(c, 'XGQ','下关区');
new County(c, 'PKQ','浦口区');
new County(c, 'QXQ','栖霞区');
new County(c, 'YHTQ','雨花台区');
new County(c, 'JNQ','江宁区');
new County(c, 'LHQ','六合区');
new County(c, 'LSX','溧水县');
new County(c, 'GCX','高淳县');

c = new City(province, '江苏','WX','无锡');
new County(c, 'BX','不限');
new County(c, 'CAQ','崇安区');
new County(c, 'NZQ','南长区');
new County(c, 'BTQ','北塘区');
new County(c, 'XSQ','锡山区');
new County(c, 'HSQ','惠山区');
new County(c, 'BHQ','滨湖区');
new County(c, 'JY','江阴');
new County(c, 'YX','宜兴');

c = new City(province, '江苏','XZ','徐州');
new County(c, 'BX','不限');
new County(c, 'GLQ','鼓楼区');
new County(c, 'YLQ','云龙区');
new County(c, 'JLQ','九里区');
new County(c, 'JWQ','贾汪区');
new County(c, 'QSQ','泉山区');
new County(c, 'FX','丰县');
new County(c, 'PX','沛县');
new County(c, 'TSX','铜山县');
new County(c, 'SNX','睢宁县');
new County(c, 'XY','新沂');
new County(c, 'PZ','邳州');

c = new City(province, '江苏','CZ','常州');
new County(c, 'BX','不限');
new County(c, 'TNQ','天宁区');
new County(c, 'ZLQ','钟楼区');
new County(c, 'QSYQ','戚墅堰区');
new County(c, 'XBQ','新北区');
new County(c, 'WJQ','武进区');
new County(c, 'LY','溧阳');
new County(c, 'JT','金坛');

c = new City(province, '江苏','SZ','苏州');
new County(c, 'BX','不限');
new County(c, 'CLQ','沧浪区');
new County(c, 'PJQ','平江区');
new County(c, 'JCQ','金阊区');
new County(c, 'HQQ','虎丘区');
new County(c, 'WZQ','吴中区');
new County(c, 'XCQ','相城区');
new County(c, 'CS','常熟');
new County(c, 'ZJG','张家港');
new County(c, 'KS','昆山');
new County(c, 'WJ','吴江');
new County(c, 'TC','太仓');

c = new City(province, '江苏','NT','南通');
new County(c, 'BX','不限');
new County(c, 'CCQ','崇川区');
new County(c, 'GZQ','港闸区');
new County(c, 'HAX','海安县');
new County(c, 'RDX','如东县');
new County(c, 'QD','启东');
new County(c, 'RG','如皋');
new County(c, 'TZ','通州');
new County(c, 'HM','海门');

c = new City(province, '江苏','LYG','连云港');
new County(c, 'BX','不限');
new County(c, 'LYQ','连云区');
new County(c, 'XPQ','新浦区');
new County(c, 'HZQ','海州区');
new County(c, 'GYX','赣榆县');
new County(c, 'DHX','东海县');
new County(c, 'GYUNX','灌云县');
new County(c, 'GNX','灌南县');

c = new City(province, '江苏','HA','淮安');
new County(c, 'BX','不限');
new County(c, 'QHQ','清河区');
new County(c, 'CZQ','楚州区');
new County(c, 'HYQ','淮阴区');
new County(c, 'QPQ','清浦区');
new County(c, 'LSX','涟水县');
new County(c, 'HZX','洪泽县');
new County(c, 'XYX','盱眙县');
new County(c, 'JHX','金湖县');

c = new City(province, '江苏','YC','盐城');
new County(c, 'BX','不限');
new County(c, 'THQ','亭湖区');
new County(c, 'YDQ','盐都区');
new County(c, 'XSX','响水县');
new County(c, 'BHX','滨海县');
new County(c, 'FNX','阜宁县');
new County(c, 'SYX','射阳县');
new County(c, 'JHX','建湖县');
new County(c, 'DT','东台');
new County(c, 'DF','大丰');

c = new City(province, '江苏','YZ','扬州');
new County(c, 'BX','不限');
new County(c, 'GLQ','广陵区');
new County(c, 'HJQ','邗江区');
new County(c, 'JQ','郊区');
new County(c, 'BYX','宝应县');
new County(c, 'YZ','仪征');
new County(c, 'GY','高邮');
new County(c, 'JD','江都');

c = new City(province, '江苏','ZJ','镇江');
new County(c, 'BX','不限');
new County(c, 'JKQ','京口区');
new County(c, 'RZQ','润州区');
new County(c, 'DTQ','丹徒区');
new County(c, 'DY','丹阳');
new County(c, 'YZ','扬中');
new County(c, 'JR','句容');

c = new City(province, '江苏','TZ','泰州');
new County(c, 'BX','不限');
new County(c, 'HLQ','海陵区');
new County(c, 'GGQ','高港区');
new County(c, 'XH','兴化');
new County(c, 'JJ','靖江');
new County(c, 'TX','泰兴');
new County(c, 'JY','姜堰');

c = new City(province, '江苏','SQ','宿迁');
new County(c, 'BX','不限');
new County(c, 'SCQ','宿城区');
new County(c, 'SYQ','宿豫区');
new County(c, 'SHYX','沭阳县');
new County(c, 'SYX','泗阳县');
new County(c, 'SHX','泗洪县');

c = new City(province, '江苏','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','ZJ','浙江');
c = new City(province, '浙江','HZ','杭州');
new County(c, 'BX','不限');
new County(c, 'SCQ','上城区');
new County(c, 'XCQ','下城区');
new County(c, 'JGQ','江干区');
new County(c, 'GSQ','拱墅区');
new County(c, 'XHQ','西湖区');
new County(c, 'BJQ','滨江区');
new County(c, 'XSQ','萧山区');
new County(c, 'YHQ','余杭区');
new County(c, 'TLX','桐庐县');
new County(c, 'CAX','淳安县');
new County(c, 'JD','建德');
new County(c, 'FY','富阳');
new County(c, 'LA','临安');

c = new City(province, '浙江','NB','宁波');
new County(c, 'BX','不限');
new County(c, 'HSQ','海曙区');
new County(c, 'JDQ','江东区');
new County(c, 'JBQ','江北区');
new County(c, 'BLQ','北仑区');
new County(c, 'ZHQ','镇海区');
new County(c, 'YZQ','鄞州区');
new County(c, 'XSX','象山县');
new County(c, 'NHX','宁海县');
new County(c, 'YY','余姚');
new County(c, 'CX','慈溪');
new County(c, 'FH','奉化');

c = new City(province, '浙江','WZ','温州');
new County(c, 'BX','不限');
new County(c, 'LCQ','鹿城区');
new County(c, 'LWQ','龙湾区');
new County(c, 'OHQ','瓯海区');
new County(c, 'DTX','洞头县');
new County(c, 'YJX','永嘉县');
new County(c, 'PYX','平阳县');
new County(c, 'CNX','苍南县');
new County(c, 'WCX','文成县');
new County(c, 'TSX','泰顺县');
new County(c, 'RA','瑞安');
new County(c, 'LQ','乐清');

c = new City(province, '浙江','JX','嘉兴');
new County(c, 'BX','不限');
new County(c, 'XCQ','秀城区');
new County(c, 'XZQ','秀洲区');
new County(c, 'JSX','嘉善县');
new County(c, 'HYX','海盐县');
new County(c, 'HN','海宁');
new County(c, 'PH','平湖');
new County(c, 'TX','桐乡');
new County(c, 'NHQ','南湖区');

c = new City(province, '浙江','HUZ','湖州');
new County(c, 'BX','不限');
new County(c, 'WXQ','吴兴区');
new County(c, 'NXQ','南浔区');
new County(c, 'DQX','德清县');
new County(c, 'ZXX','长兴县');
new County(c, 'AJX','安吉县');

c = new City(province, '浙江','SX','绍兴');
new County(c, 'BX','不限');
new County(c, 'YCQ','越城区');
new County(c, 'SXX','绍兴县');
new County(c, 'XCX','新昌县');
new County(c, 'ZJ','诸暨');
new County(c, 'SY','上虞');
new County(c, 'SZ','嵊州');

c = new City(province, '浙江','JH','金华');
new County(c, 'BX','不限');
new County(c, 'WCQ','婺城区');
new County(c, 'JDQ','金东区');
new County(c, 'WYX','武义县');
new County(c, 'PJX','浦江县');
new County(c, 'PAX','磐安县');
new County(c, 'LX','兰溪');
new County(c, 'YW','义乌');
new County(c, 'DY','东阳');
new County(c, 'YK','永康');

c = new City(province, '浙江','QZ','衢州');
new County(c, 'BX','不限');
new County(c, 'KCQ','柯城区');
new County(c, 'QJQ','衢江区');
new County(c, 'CSX','常山县');
new County(c, 'KHX','开化县');
new County(c, 'LYX','龙游县');
new County(c, 'JS','江山');

c = new City(province, '浙江','ZS','舟山');
new County(c, 'BX','不限');
new County(c, 'DHQ','定海区');
new County(c, 'PTQ','普陀区');
new County(c, 'DSX','岱山县');
new County(c, 'SSX','嵊泗县');

c = new City(province, '浙江','TZ','台州');
new County(c, 'BX','不限');
new County(c, 'JJQ','椒江区');
new County(c, 'HYQ','黄岩区');
new County(c, 'LQQ','路桥区');
new County(c, 'YHX','玉环县');
new County(c, 'SMX','三门县');
new County(c, 'TTX','天台县');
new County(c, 'XJX','仙居县');
new County(c, 'WL','温岭');
new County(c, 'LH','临海');

c = new City(province, '浙江','LS','丽水');
new County(c, 'BX','不限');
new County(c, 'LDQ','莲都区');
new County(c, 'QTX','青田县');
new County(c, 'JYX','缙云县');
new County(c, 'SCX','遂昌县');
new County(c, 'SYX','松阳县');
new County(c, 'YHX','云和县');
new County(c, 'QYX','庆元县');
new County(c, 'JNX','景宁县');
new County(c, 'LQ','龙泉');

c = new City(province, '浙江','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','AH','安徽');
c = new City(province, '安徽','HF','合肥');
new County(c, 'BX','不限');
new County(c, 'YHQ','瑶海区');
new County(c, 'LYQ','庐阳区');
new County(c, 'SSQ','蜀山区');
new County(c, 'BHQ','包河区');
new County(c, 'ZFX','长丰县');
new County(c, 'FDX','肥东县');
new County(c, 'FXX','肥西县');

c = new City(province, '安徽','WH','芜湖');
new County(c, 'BX','不限');
new County(c, 'JHQ','镜湖区');
new County(c, 'MTQ','马塘区');
new County(c, 'XWQ','新芜区');
new County(c, 'JJQ','鸠江区');
new County(c, 'WHX','芜湖县');
new County(c, 'FCX','繁昌县');
new County(c, 'NLX','南陵县');

c = new City(province, '安徽','BB','蚌埠');
new County(c, 'BX','不限');
new County(c, 'LZHQ','龙子湖区');
new County(c, 'BSQ','蚌山区');
new County(c, 'YHQ','禹会区');
new County(c, 'HSQ','淮上区');
new County(c, 'HYX','怀远县');
new County(c, 'WHX','五河县');
new County(c, 'GZX','固镇县');

c = new City(province, '安徽','HN','淮南');
new County(c, 'BX','不限');
new County(c, 'DTQ','大通区');
new County(c, 'TJAQ','田家庵区');
new County(c, 'XJJQ','谢家集区');
new County(c, 'BGSQ','八公山区');
new County(c, 'PJQ','潘集区');
new County(c, 'FTX','凤台县');

c = new City(province, '安徽','MAS','马鞍山');
new County(c, 'BX','不限');
new County(c, 'JJZQ','金家庄区');
new County(c, 'HSQ','花山区');
new County(c, 'YSQ','雨山区');
new County(c, 'DTX','当涂县');

c = new City(province, '安徽','HB','淮北');
new County(c, 'BX','不限');
new County(c, 'DJQ','杜集区');
new County(c, 'XSQ','相山区');
new County(c, 'LSQ','烈山区');
new County(c, 'SXX','濉溪县');

c = new City(province, '安徽','TL','铜陵');
new County(c, 'BX','不限');
new County(c, 'TGSQ','铜官山区');
new County(c, 'SZSQ','狮子山区');
new County(c, 'JQ','郊区');
new County(c, 'TLX','铜陵县');

c = new City(province, '安徽','AQ','安庆');
new County(c, 'BX','不限');
new County(c, 'YJQ','迎江区');
new County(c, 'DGQ','大观区');
new County(c, 'JQ','郊区');
new County(c, 'HNX','怀宁县');
new County(c, 'ZYX','枞阳县');
new County(c, 'QSX','潜山县');
new County(c, 'THX','太湖县');
new County(c, 'SSX','宿松县');
new County(c, 'WJX','望江县');
new County(c, 'YXX','岳西县');
new County(c, 'TC','桐城');

c = new City(province, '安徽','HS','黄山');
new County(c, 'BX','不限');
new County(c, 'TXQ','屯溪区');
new County(c, 'HSQ','黄山区');
new County(c, 'HZQ','徽州区');
new County(c, 'SX','歙县');
new County(c, 'XNX','休宁县');
new County(c, 'YX','黟县');
new County(c, 'QMX','祁门县');

c = new City(province, '安徽','CHZ','滁州');
new County(c, 'BX','不限');
new County(c, 'LYQ','琅琊区');
new County(c, 'NQQ','南谯区');
new County(c, 'LAX','来安县');
new County(c, 'QJX','全椒县');
new County(c, 'DYX','定远县');
new County(c, 'FYX','凤阳县');
new County(c, 'TZ','天长');
new County(c, 'MG','明光');

c = new City(province, '安徽','FY','阜阳');
new County(c, 'BX','不限');
new County(c, 'YZQ','颍州区');
new County(c, 'YDQ','颍东区');
new County(c, 'YQQ','颍泉区');
new County(c, 'LQX','临泉县');
new County(c, 'THX','太和县');
new County(c, 'FNX','阜南县');
new County(c, 'YSX','颍上县');
new County(c, 'JS','界首');

c = new City(province, '安徽','SZ','宿州');
new County(c, 'BX','不限');
new County(c, 'YQQ','墉桥区');
new County(c, 'DSX','砀山县');
new County(c, 'XX','萧县');
new County(c, 'LBX','灵璧县');
new County(c, 'SX','泗县');

c = new City(province, '安徽','CH','巢湖');
new County(c, 'BX','不限');
new County(c, 'JCQ','居巢区');
new County(c, 'LJX','庐江县');
new County(c, 'WWX','无为县');
new County(c, 'HSX','含山县');
new County(c, 'HX','和县');

c = new City(province, '安徽','LA','六安');
new County(c, 'BX','不限');
new County(c, 'JAQ','金安区');
new County(c, 'YAQ','裕安区');
new County(c, 'SX','寿县');
new County(c, 'HQX','霍邱县');
new County(c, 'SCX','舒城县');
new County(c, 'JZX','金寨县');
new County(c, 'HSX','霍山县');

c = new City(province, '安徽','BZ','亳州');
new County(c, 'BX','不限');
new County(c, 'QCQ','谯城区');
new County(c, 'WYX','涡阳县');
new County(c, 'MCX','蒙城县');
new County(c, 'LXX','利辛县');

c = new City(province, '安徽','CZ','池州');
new County(c, 'BX','不限');
new County(c, 'GCQ','贵池区');
new County(c, 'DZX','东至县');
new County(c, 'STX','石台县');
new County(c, 'QYX','青阳县');

c = new City(province, '安徽','XC','宣城');
new County(c, 'BX','不限');
new County(c, 'XZQ','宣州区');
new County(c, 'LXX','郎溪县');
new County(c, 'GDX','广德县');
new County(c, 'JX','泾县');
new County(c, 'JXX','绩溪县');
new County(c, 'JDX','旌德县');
new County(c, 'NG','宁国');

c = new City(province, '安徽','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','FJ','福建');
c = new City(province, '福建','FZ','福州');
new County(c, 'BX','不限');
new County(c, 'GLQ','鼓楼区');
new County(c, 'TJQ','台江区');
new County(c, 'CSQ','仓山区');
new County(c, 'MWQ','马尾区');
new County(c, 'JAQ','晋安区');
new County(c, 'MHX','闽侯县');
new County(c, 'LJX','连江县');
new County(c, 'LYX','罗源县');
new County(c, 'MQX','闽清县');
new County(c, 'YTX','永泰县');
new County(c, 'PTX','平潭县');
new County(c, 'FQ','福清');
new County(c, 'ZL','长乐');

c = new City(province, '福建','XM','厦门');
new County(c, 'BX','不限');
new County(c, 'SMQ','思明区');
new County(c, 'HCQ','海沧区');
new County(c, 'HLQ','湖里区');
new County(c, 'JMQ','集美区');
new County(c, 'TAQ','同安区');
new County(c, 'XAQ','翔安区');

c = new City(province, '福建','PT','莆田');
new County(c, 'BX','不限');
new County(c, 'CXQ','城厢区');
new County(c, 'HJQ','涵江区');
new County(c, 'LCQ','荔城区');
new County(c, 'XYQ','秀屿区');
new County(c, 'XYX','仙游县');

c = new City(province, '福建','SM','三明');
new County(c, 'BX','不限');
new County(c, 'MLQ','梅列区');
new County(c, 'SYQ','三元区');
new County(c, 'MXX','明溪县');
new County(c, 'QLX','清流县');
new County(c, 'NHX','宁化县');
new County(c, 'DTX','大田县');
new County(c, 'YXX','尤溪县');
new County(c, 'SX','沙县');
new County(c, 'JLX','将乐县');
new County(c, 'TNX','泰宁县');
new County(c, 'JNX','建宁县');
new County(c, 'YA','永安');

c = new City(province, '福建','QZ','泉州');
new County(c, 'BX','不限');
new County(c, 'LCQ','鲤城区');
new County(c, 'FZQ','丰泽区');
new County(c, 'LJQ','洛江区');
new County(c, 'QGQ','泉港区');
new County(c, 'HAX','惠安县');
new County(c, 'AXX','安溪县');
new County(c, 'YCX','永春县');
new County(c, 'DHX','德化县');
new County(c, 'JMX','金门县');
new County(c, 'SS','石狮');
new County(c, 'JJ','晋江');
new County(c, 'NA','南安');

c = new City(province, '福建','ZZ','漳州');
new County(c, 'BX','不限');
new County(c, 'XCQ','芗城区');
new County(c, 'LWQ','龙文区');
new County(c, 'YXX','云霄县');
new County(c, 'ZPX','漳浦县');
new County(c, 'ZAX','诏安县');
new County(c, 'ZTX','长泰县');
new County(c, 'DSX','东山县');
new County(c, 'NJX','南靖县');
new County(c, 'PHX','平和县');
new County(c, 'HAX','华安县');
new County(c, 'LH','龙海');

c = new City(province, '福建','NP','南平');
new County(c, 'BX','不限');
new County(c, 'YPQ','延平区');
new County(c, 'SCX','顺昌县');
new County(c, 'PCX','浦城县');
new County(c, 'GZX','光泽县');
new County(c, 'SXX','松溪县');
new County(c, 'ZHX','政和县');
new County(c, 'SW','邵武');
new County(c, 'WYS','武夷山');
new County(c, 'JO','建瓯');
new County(c, 'JY','建阳');

c = new City(province, '福建','LY','龙岩');
new County(c, 'BX','不限');
new County(c, 'XLQ','新罗区');
new County(c, 'ZTX','长汀县');
new County(c, 'YDX','永定县');
new County(c, 'SHX','上杭县');
new County(c, 'WPX','武平县');
new County(c, 'LCX','连城县');
new County(c, 'ZP','漳平');

c = new City(province, '福建','ND','宁德');
new County(c, 'BX','不限');
new County(c, 'JCQ','蕉城区');
new County(c, 'XPX','霞浦县');
new County(c, 'GTX','古田县');
new County(c, 'PNX','屏南县');
new County(c, 'SNX','寿宁县');
new County(c, 'ZNX','周宁县');
new County(c, 'ZRX','柘荣县');
new County(c, 'FA','福安');
new County(c, 'FD','福鼎');

c = new City(province, '福建','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','JX','江西');
c = new City(province, '江西','NC','南昌');
new County(c, 'BX','不限');
new County(c, 'DHQ','东湖区');
new County(c, 'XHQ','西湖区');
new County(c, 'QYPQ','青云谱区');
new County(c, 'WLQ','湾里区');
new County(c, 'QSHQ','青山湖区');
new County(c, 'NCX','南昌县');
new County(c, 'XJX','新建县');
new County(c, 'AYX','安义县');
new County(c, 'JXX','进贤县');

c = new City(province, '江西','JDZ','景德镇');
new County(c, 'BX','不限');
new County(c, 'CJQ','昌江区');
new County(c, 'ZSQ','珠山区');
new County(c, 'FLX','浮梁县');
new County(c, 'LP','乐平');

c = new City(province, '江西','PX','萍乡');
new County(c, 'BX','不限');
new County(c, 'AYQ','安源区');
new County(c, 'XDQ','湘东区');
new County(c, 'LHX','莲花县');
new County(c, 'SLX','上栗县');
new County(c, 'LXX','芦溪县');

c = new City(province, '江西','JJ','九江');
new County(c, 'BX','不限');
new County(c, 'LSQ','庐山区');
new County(c, 'XYQ','浔阳区');
new County(c, 'JJX','九江县');
new County(c, 'WNX','武宁县');
new County(c, 'XSX','修水县');
new County(c, 'YXX','永修县');
new County(c, 'DAX','德安县');
new County(c, 'XZX','星子县');
new County(c, 'DCX','都昌县');
new County(c, 'HKX','湖口县');
new County(c, 'PZX','彭泽县');
new County(c, 'RC','瑞昌');

c = new City(province, '江西','XY','新余');
new County(c, 'BX','不限');
new County(c, 'YSQ','渝水区');
new County(c, 'FYX','分宜县');

c = new City(province, '江西','YT','鹰潭');
new County(c, 'BX','不限');
new County(c, 'YHQ','月湖区');
new County(c, 'YJX','余江县');
new County(c, 'GX','贵溪');

c = new City(province, '江西','GZ','赣州');
new County(c, 'BX','不限');
new County(c, 'ZGQ','章贡区');
new County(c, 'GX','赣县');
new County(c, 'XFX','信丰县');
new County(c, 'DYX','大余县');
new County(c, 'SYX','上犹县');
new County(c, 'CYX','崇义县');
new County(c, 'AYX','安远县');
new County(c, 'LNX','龙南县');
new County(c, 'DNX','定南县');
new County(c, 'QNX','全南县');
new County(c, 'NDX','宁都县');
new County(c, 'YDX','于都县');
new County(c, 'XGX','兴国县');
new County(c, 'HCX','会昌县');
new County(c, 'XWX','寻乌县');
new County(c, 'SCX','石城县');
new County(c, 'RJ','瑞金');
new County(c, 'NK','南康');

c = new City(province, '江西','JA','吉安');
new County(c, 'BX','不限');
new County(c, 'JZQ','吉州区');
new County(c, 'QYQ','青原区');
new County(c, 'JAX','吉安县');
new County(c, 'JSX','吉水县');
new County(c, 'XJX','峡江县');
new County(c, 'XGX','新干县');
new County(c, 'YFX','永丰县');
new County(c, 'THX','泰和县');
new County(c, 'SCX','遂川县');
new County(c, 'WAX','万安县');
new County(c, 'AFX','安福县');
new County(c, 'YXX','永新县');
new County(c, 'JGS','井冈山');

c = new City(province, '江西','YC','宜春');
new County(c, 'BX','不限');
new County(c, 'YZQ','袁州区');
new County(c, 'FXX','奉新县');
new County(c, 'WZX','万载县');
new County(c, 'SGX','上高县');
new County(c, 'YFX','宜丰县');
new County(c, 'JAX','靖安县');
new County(c, 'TGX','铜鼓县');
new County(c, 'FC','丰城');
new County(c, 'ZS','樟树');
new County(c, 'GA','高安');

c = new City(province, '江西','FZ','抚州');
new County(c, 'BX','不限');
new County(c, 'LCQ','临川区');
new County(c, 'NCX','南城县');
new County(c, 'LCX','黎川县');
new County(c, 'NFX','南丰县');
new County(c, 'CRX','崇仁县');
new County(c, 'LAX','乐安县');
new County(c, 'YHX','宜黄县');
new County(c, 'JXX','金溪县');
new County(c, 'ZXX','资溪县');
new County(c, 'DXX','东乡县');
new County(c, 'GCX','广昌县');

c = new City(province, '江西','SR','上饶');
new County(c, 'BX','不限');
new County(c, 'XZQ','信州区');
new County(c, 'SRX','上饶县');
new County(c, 'GFX','广丰县');
new County(c, 'YSX','玉山县');
new County(c, 'QSX','铅山县');
new County(c, 'HFX','横峰县');
new County(c, 'YYX','弋阳县');
new County(c, 'YGX','余干县');
new County(c, 'PYX','鄱阳县');
new County(c, 'WNX','万年县');
new County(c, 'WYX','婺源县');
new County(c, 'DX','德兴');

c = new City(province, '江西','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','SD','山东');
c = new City(province, '山东','JNA','济南');
new County(c, 'BX','不限');
new County(c, 'LXQ','历下区');
new County(c, 'SZQ','市中区');
new County(c, 'HYQ','槐荫区');
new County(c, 'TQQ','天桥区');
new County(c, 'LCQ','历城区');
new County(c, 'ZQQ','长清区');
new County(c, 'PYX','平阴县');
new County(c, 'JYX','济阳县');
new County(c, 'SHX','商河县');
new County(c, 'ZQ','章丘');

c = new City(province, '山东','QD','青岛');
new County(c, 'BX','不限');
new County(c, 'SNQ','市南区');
new County(c, 'SBQ','市北区');
new County(c, 'SFQ','四方区');
new County(c, 'HDQ','黄岛区');
new County(c, 'LSQ','崂山区');
new County(c, 'LCQ','李沧区');
new County(c, 'CYQ','城阳区');
new County(c, 'JZ','胶州');
new County(c, 'JM','即墨');
new County(c, 'PD','平度');
new County(c, 'JN','胶南');
new County(c, 'LX','莱西');

c = new City(province, '山东','ZB','淄博');
new County(c, 'BX','不限');
new County(c, 'ZCQ','淄川区');
new County(c, 'ZDQ','张店区');
new County(c, 'BSQ','博山区');
new County(c, 'LZQ','临淄区');
new County(c, 'ZCUQ','周村区');
new County(c, 'HTX','桓台县');
new County(c, 'GQX','高青县');
new County(c, 'YYX','沂源县');

c = new City(province, '山东','ZZ','枣庄');
new County(c, 'BX','不限');
new County(c, 'SZQ','市中区');
new County(c, 'XCQ','薛城区');
new County(c, 'YCQ','峄城区');
new County(c, 'TEZQ','台儿庄区');
new County(c, 'STQ','山亭区');
new County(c, 'TZ','滕州');

c = new City(province, '山东','DY','东营');
new County(c, 'BX','不限');
new County(c, 'DYQ','东营区');
new County(c, 'HKQ','河口区');
new County(c, 'KLX','垦利县');
new County(c, 'LJX','利津县');
new County(c, 'GRX','广饶县');

c = new City(province, '山东','YT','烟台');
new County(c, 'BX','不限');
new County(c, 'ZFQ','芝罘区');
new County(c, 'FSQ','福山区');
new County(c, 'MPQ','牟平区');
new County(c, 'LSQ','莱山区');
new County(c, 'ZDX','长岛县');
new County(c, 'LK','龙口');
new County(c, 'LY','莱阳');
new County(c, 'LZ','莱州');
new County(c, 'PL','蓬莱');
new County(c, 'ZY','招远');
new County(c, 'QX','栖霞');
new County(c, 'HY','海阳');

c = new City(province, '山东','WF','潍坊');
new County(c, 'BX','不限');
new County(c, 'WCQ','潍城区');
new County(c, 'HTQ','寒亭区');
new County(c, 'FZQ','坊子区');
new County(c, 'KWQ','奎文区');
new County(c, 'LQX','临朐县');
new County(c, 'CLX','昌乐县');
new County(c, 'QZ','青州');
new County(c, 'ZC','诸城');
new County(c, 'SG','寿光');
new County(c, 'AQ','安丘');
new County(c, 'GM','高密');
new County(c, 'CY','昌邑');

c = new City(province, '山东','JN','济宁');
new County(c, 'BX','不限');
new County(c, 'SZQ','市中区');
new County(c, 'RCQ','任城区');
new County(c, 'WEISX','微山县');
new County(c, 'YTX','鱼台县');
new County(c, 'JXX','金乡县');
new County(c, 'JIXX','嘉祥县');
new County(c, 'WSX','汶上县');
new County(c, 'SSX','泗水县');
new County(c, 'LSX','梁山县');
new County(c, 'QF','曲阜');
new County(c, 'YZ','兖州');
new County(c, 'ZC','邹城');

c = new City(province, '山东','TA','泰安');
new County(c, 'BX','不限');
new County(c, 'TSQ','泰山区');
new County(c, 'DYQ','岱岳区');
new County(c, 'NYX','宁阳县');
new County(c, 'DPX','东平县');
new County(c, 'XT','新泰');
new County(c, 'FC','肥城');

c = new City(province, '山东','WH','威海');
new County(c, 'BX','不限');
new County(c, 'HCQ','环翠区');
new County(c, 'WD','文登');
new County(c, 'RC','荣成');
new County(c, 'RS','乳山');

c = new City(province, '山东','RZ','日照');
new County(c, 'BX','不限');
new County(c, 'DGQ','东港区');
new County(c, 'LSQ','岚山区');
new County(c, 'WLX','五莲县');
new County(c, 'JX','莒县');

c = new City(province, '山东','LW','莱芜');
new County(c, 'BX','不限');
new County(c, 'LCQ','莱城区');
new County(c, 'GCQ','钢城区');

c = new City(province, '山东','LY','临沂');
new County(c, 'BX','不限');
new County(c, 'LSQ','兰山区');
new County(c, 'LZQ','罗庄区');
new County(c, 'HDQ','河东区');
new County(c, 'YNX','沂南县');
new County(c, 'TCX','郯城县');
new County(c, 'YSX','沂水县');
new County(c, 'CSX','苍山县');
new County(c, 'FX','费县');
new County(c, 'PYX','平邑县');
new County(c, 'JNX','莒南县');
new County(c, 'MYX','蒙阴县');
new County(c, 'LSX','临沭县');

c = new City(province, '山东','DZ','德州');
new County(c, 'BX','不限');
new County(c, 'DCQ','德城区');
new County(c, 'LX','陵县');
new County(c, 'NJX','宁津县');
new County(c, 'QYX','庆云县');
new County(c, 'LYX','临邑县');
new County(c, 'QHX','齐河县');
new County(c, 'PYX','平原县');
new County(c, 'XJX','夏津县');
new County(c, 'WCX','武城县');
new County(c, 'LL','乐陵');
new County(c, 'YC','禹城');

c = new City(province, '山东','LC','聊城');
new County(c, 'BX','不限');
new County(c, 'DCFQ','东昌府区');
new County(c, 'YGX','阳谷县');
new County(c, 'XX','莘县');
new County(c, 'CPX','茌平县');
new County(c, 'DAX','东阿县');
new County(c, 'GX','冠县');
new County(c, 'GTX','高唐县');
new County(c, 'LQ','临清');

c = new City(province, '山东','BZ','滨州');
new County(c, 'BX','不限');
new County(c, 'BCQ','滨城区');
new County(c, 'HMX','惠民县');
new County(c, 'YXX','阳信县');
new County(c, 'WDX','无棣县');
new County(c, 'ZHX','沾化县');
new County(c, 'BXX','博兴县');
new County(c, 'ZPX','邹平县');

c = new City(province, '山东','HZ','菏泽');
new County(c, 'BX','不限');
new County(c, 'MDQ','牡丹区');
new County(c, 'CX','曹县');
new County(c, 'DX','单县');
new County(c, 'CWX','成武县');
new County(c, 'JYX','巨野县');
new County(c, 'YCX','郓城县');
new County(c, 'JCX','鄄城县');
new County(c, 'DTX','定陶县');
new County(c, 'DMX','东明县');

c = new City(province, '山东','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','HEN','河南');
c = new City(province, '河南','ZZ','郑州');
new County(c, 'BX','不限');
new County(c, 'ZYQ','中原区');
new County(c, 'EQQ','二七区');
new County(c, 'GCQ','管城区');
new County(c, 'JSQ','金水区');
new County(c, 'SJQ','上街区');
new County(c, 'MSQ','邙山区');
new County(c, 'ZMX','中牟县');
new County(c, 'GY','巩义');
new County(c, 'YY','荥阳');
new County(c, 'XM','新密');
new County(c, 'XZ','新郑');
new County(c, 'DF','登封');

c = new City(province, '河南','KF','开封');
new County(c, 'BX','不限');
new County(c, 'LTQ','龙亭区');
new County(c, 'SHHZQ','顺河回族区');
new County(c, 'GLQ','鼓楼区');
new County(c, 'NGQ','南关区');
new County(c, 'JQ','郊区');
new County(c, 'QX','杞县');
new County(c, 'TXX','通许县');
new County(c, 'WSX','尉氏县');
new County(c, 'KFX','开封县');
new County(c, 'LKX','兰考县');

c = new City(province, '河南','LY','洛阳');
new County(c, 'BX','不限');
new County(c, 'LCQ','老城区');
new County(c, 'XGQ','西工区');
new County(c, 'CHHZQ','廛河回族区');
new County(c, 'JXQ','涧西区');
new County(c, 'JLQ','吉利区');
new County(c, 'LLQ','洛龙区');
new County(c, 'MJX','孟津县');
new County(c, 'XAX','新安县');
new County(c, 'LCX','栾川县');
new County(c, 'SX','嵩县');
new County(c, 'RYX','汝阳县');
new County(c, 'YYX','宜阳县');
new County(c, 'LNX','洛宁县');
new County(c, 'YCX','伊川县');
new County(c, 'YS','偃师');

c = new City(province, '河南','PDS','平顶山');
new County(c, 'BX','不限');
new County(c, 'XHQ','新华区');
new County(c, 'WDQ','卫东区');
new County(c, 'SLQ','石龙区');
new County(c, 'ZHQ','湛河区');
new County(c, 'BFX','宝丰县');
new County(c, 'YX','叶县');
new County(c, 'LSX','鲁山县');
new County(c, 'JX','郏县');
new County(c, 'WG','舞钢');
new County(c, 'RZ','汝州');

c = new City(province, '河南','AY','安阳');
new County(c, 'BX','不限');
new County(c, 'WFQ','文峰区');
new County(c, 'BGQ','北关区');
new County(c, 'YDQ','殷都区');
new County(c, 'LAQ','龙安区');
new County(c, 'AYX','安阳县');
new County(c, 'TYX','汤阴县');
new County(c, 'HX','滑县');
new County(c, 'NHX','内黄县');
new County(c, 'LZ','林州');

c = new City(province, '河南','HB','鹤壁');
new County(c, 'BX','不限');
new County(c, 'HSQ','鹤山区');
new County(c, 'SCQ','山城区');
new County(c, 'QBQ','淇滨区');
new County(c, 'JX','浚县');
new County(c, 'QX','淇县');

c = new City(province, '河南','XX','新乡');
new County(c, 'BX','不限');
new County(c, 'HQQ','红旗区');
new County(c, 'WBQ','卫滨区');
new County(c, 'FQQ','凤泉区');
new County(c, 'MYQ','牧野区');
new County(c, 'XXX','新乡县');
new County(c, 'HJX','获嘉县');
new County(c, 'YYX','原阳县');
new County(c, 'YJX','延津县');
new County(c, 'FQX','封丘县');
new County(c, 'ZYX','长垣县');
new County(c, 'WH','卫辉');
new County(c, 'HX','辉县');

c = new City(province, '河南','JZ','焦作');
new County(c, 'BX','不限');
new County(c, 'JFQ','解放区');
new County(c, 'ZZQ','中站区');
new County(c, 'MCQ','马村区');
new County(c, 'SYQ','山阳区');
new County(c, 'XWX','修武县');
new County(c, 'BAX','博爱县');
new County(c, 'WZX','武陟县');
new County(c, 'WX','温县');
new County(c, 'JY','济源');
new County(c, 'QY','沁阳');
new County(c, 'MZ','孟州');

c = new City(province, '河南','PY','濮阳');
new County(c, 'BX','不限');
new County(c, 'HLQ','华龙区');
new County(c, 'QFX','清丰县');
new County(c, 'NLX','南乐县');
new County(c, 'FX','范县');
new County(c, 'TQX','台前县');
new County(c, 'PYX','濮阳县');

c = new City(province, '河南','XC','许昌');
new County(c, 'BX','不限');
new County(c, 'WDQ','魏都区');
new County(c, 'XUCX','许昌县');
new County(c, 'YLX','鄢陵县');
new County(c, 'XCX','襄城县');
new County(c, 'YZ','禹州');
new County(c, 'ZG','长葛');

c = new City(province, '河南','LH','漯河');
new County(c, 'BX','不限');
new County(c, 'YHQ','源汇区');
new County(c, 'YCQ','郾城区');
new County(c, 'ZLQ','召陵区');
new County(c, 'WYX','舞阳县');
new County(c, 'LYX','临颍县');

c = new City(province, '河南','SMX','三门峡');
new County(c, 'BX','不限');
new County(c, 'HBQ','湖滨区');
new County(c, 'MCX','渑池县');
new County(c, 'SX','陕县');
new County(c, 'LSX','卢氏县');
new County(c, 'YM','义马');
new County(c, 'LB','灵宝');

c = new City(province, '河南','NY','南阳');
new County(c, 'BX','不限');
new County(c, 'WCQ','宛城区');
new County(c, 'WLQ','卧龙区');
new County(c, 'NZX','南召县');
new County(c, 'FCX','方城县');
new County(c, 'XXX','西峡县');
new County(c, 'ZPX','镇平县');
new County(c, 'NXX','内乡县');
new County(c, 'XCX','淅川县');
new County(c, 'SQX','社旗县');
new County(c, 'THX','唐河县');
new County(c, 'XYX','新野县');
new County(c, 'TBX','桐柏县');
new County(c, 'DZ','邓州');

c = new City(province, '河南','SQ','商丘');
new County(c, 'BX','不限');
new County(c, 'LYQ','梁园区');
new County(c, 'SYQ','睢阳区');
new County(c, 'MQX','民权县');
new County(c, 'SX','睢县');
new County(c, 'NLX','宁陵县');
new County(c, 'ZCX','柘城县');
new County(c, 'YCX','虞城县');
new County(c, 'XYX','夏邑县');
new County(c, 'YC','永城');

c = new City(province, '河南','XY','信阳');
new County(c, 'BX','不限');
new County(c, 'SHQ','师河区');
new County(c, 'PQQ','平桥区');
new County(c, 'LSX','罗山县');
new County(c, 'GSX','光山县');
new County(c, 'XX','新县');
new County(c, 'SCX','商城县');
new County(c, 'GUSX','固始县');
new County(c, 'HCX','潢川县');
new County(c, 'HBX','淮滨县');
new County(c, 'XIX','息县');

c = new City(province, '河南','ZK','周口');
new County(c, 'BX','不限');
new County(c, 'CHQ','川汇区');
new County(c, 'FGX','扶沟县');
new County(c, 'XHX','西华县');
new County(c, 'SSX','商水县');
new County(c, 'SQX','沈丘县');
new County(c, 'DCX','郸城县');
new County(c, 'HYX','淮阳县');
new County(c, 'TKX','太康县');
new County(c, 'LYX','鹿邑县');
new County(c, 'XC','项城');

c = new City(province, '河南','ZMD','驻马店');
new County(c, 'BX','不限');
new County(c, 'YCQ','驿城区');
new County(c, 'XPX','西平县');
new County(c, 'SCX','上蔡县');
new County(c, 'PYX','平舆县');
new County(c, 'ZYX','正阳县');
new County(c, 'QSX','确山县');
new County(c, 'MYX','泌阳县');
new County(c, 'RNX','汝南县');
new County(c, 'SPX','遂平县');
new County(c, 'XCX','新蔡县');

c = new City(province, '河南','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','HB','湖北');
c = new City(province, '湖北','WH','武汉');
new County(c, 'BX','不限');
new County(c, 'JAQ','江岸区');
new County(c, 'JHQ','江汉区');
new County(c, 'QKQ','乔口区');
new County(c, 'HYQ','汉阳区');
new County(c, 'WCQ','武昌区');
new County(c, 'QSQ','青山区');
new County(c, 'HSQ','洪山区');
new County(c, 'DXHQ','东西湖区');
new County(c, 'HENQ','汉南区');
new County(c, 'CDQ','蔡甸区');
new County(c, 'JXQ','江夏区');
new County(c, 'HPQ','黄陂区');
new County(c, 'XZQ','新洲区');

c = new City(province, '湖北','HS','黄石');
new County(c, 'BX','不限');
new County(c, 'HSGQ','黄石港区');
new County(c, 'XSSQ','西塞山区');
new County(c, 'XLQ','下陆区');
new County(c, 'TSQ','铁山区');
new County(c, 'YXX','阳新县');
new County(c, 'DY','大冶');

c = new City(province, '湖北','SY','十堰');
new County(c, 'BX','不限');
new County(c, 'MJQ','茅箭区');
new County(c, 'ZWQ','张湾区');
new County(c, 'YX','郧县');
new County(c, 'YXX','郧西县');
new County(c, 'ZSX','竹山县');
new County(c, 'ZXX','竹溪县');
new County(c, 'FX','房县');
new County(c, 'DJK','丹江口');

c = new City(province, '湖北','YC','宜昌');
new County(c, 'BX','不限');
new County(c, 'XLQ','西陵区');
new County(c, 'WJGQ','伍家岗区');
new County(c, 'DJQ','点军区');
new County(c, 'YTQ','猇亭区');
new County(c, 'YLQ','夷陵区');
new County(c, 'YAX','远安县');
new County(c, 'XSX','兴山县');
new County(c, 'ZGX','秭归县');
new County(c, 'ZYTJZ','长阳土家族');
new County(c, 'WFTJZ','五峰土家族');
new County(c, 'YD','宜都');
new County(c, 'DY','当阳');
new County(c, 'ZJ','枝江');

c = new City(province, '湖北','XF','襄樊');
new County(c, 'BX','不限');
new County(c, 'XCQ','襄城区');
new County(c, 'FCQ','樊城区');
new County(c, 'XYQ','襄阳区');
new County(c, 'NZX','南漳县');
new County(c, 'GCX','谷城县');
new County(c, 'BKX','保康县');
new County(c, 'LHK','老河口');
new County(c, 'ZY','枣阳');
new County(c, 'YC','宜城');

c = new City(province, '湖北','EZ','鄂州');
new County(c, 'BX','不限');
new County(c, 'LZHQ','梁子湖区');
new County(c, 'HRQ','华容区');
new County(c, 'ECQ','鄂城区');

c = new City(province, '湖北','JM','荆门');
new County(c, 'BX','不限');
new County(c, 'DBQ','东宝区');
new County(c, 'DDQ','掇刀区');
new County(c, 'JSX','京山县');
new County(c, 'SYX','沙洋县');
new County(c, 'ZX','钟祥');

c = new City(province, '湖北','XG','孝感');
new County(c, 'BX','不限');
new County(c, 'XNQ','孝南区');
new County(c, 'XCX','孝昌县');
new County(c, 'DWX','大悟县');
new County(c, 'YMX','云梦县');
new County(c, 'YC','应城');
new County(c, 'AL','安陆');
new County(c, 'HC','汉川');

c = new City(province, '湖北','JZ','荆州');
new County(c, 'BX','不限');
new County(c, 'SSQ','沙市区');
new County(c, 'JZQ','荆州区');
new County(c, 'GAX','公安县');
new County(c, 'JLIX','监利县');
new County(c, 'JLX','江陵县');
new County(c, 'SS','石首');
new County(c, 'HH','洪湖');
new County(c, 'SZ','松滋');

c = new City(province, '湖北','HG','黄冈');
new County(c, 'BX','不限');
new County(c, 'HZQ','黄州区');
new County(c, 'TFX','团风县');
new County(c, 'HAX','红安县');
new County(c, 'LTX','罗田县');
new County(c, 'YSX','英山县');
new County(c, 'XSX','浠水县');
new County(c, 'QCX','蕲春县');
new County(c, 'HMX','黄梅县');
new County(c, 'MC','麻城');
new County(c, 'WX','武穴');

c = new City(province, '湖北','XN','咸宁');
new County(c, 'BX','不限');
new County(c, 'XAQ','咸安区');
new County(c, 'JYX','嘉鱼县');
new County(c, 'TCX','通城县');
new County(c, 'CYX','崇阳县');
new County(c, 'TSX','通山县');
new County(c, 'CB','赤壁');

c = new City(province, '湖北','SZ','随州');
new County(c, 'BX','不限');
new County(c, 'CDQ','曾都区');
new County(c, 'GS','广水');

c = new City(province, '湖北','ESZ','恩施州');
new County(c, 'ES','恩施');
new County(c, 'LC','利川');
new County(c, 'JSX','建始县');
new County(c, 'BDX','巴东县');
new County(c, 'XEX','宣恩县');
new County(c, 'XFX','咸丰县');
new County(c, 'LFX','来凤县');
new County(c, 'HFX','鹤峰县');

c = new City(province, '湖北','QJ','潜江市');

c = new City(province, '湖北','SNJLQ','神农架林区');

c = new City(province, '湖北','TM','天门市');

c = new City(province, '湖北','XT','仙桃市');

c = new City(province, '湖北','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','HUN','湖南');
c = new City(province, '湖南','ZS','长沙');
new County(c, 'BX','不限');
new County(c, 'FRQ','芙蓉区');
new County(c, 'TXQ','天心区');
new County(c, 'YLQ','岳麓区');
new County(c, 'KFQ','开福区');
new County(c, 'YHQ','雨花区');
new County(c, 'ZSX','长沙县');
new County(c, 'WCX','望城县');
new County(c, 'NXX','宁乡县');
new County(c, 'LY','浏阳');

c = new City(province, '湖南','ZZ','株洲');
new County(c, 'BX','不限');
new County(c, 'HTQ','荷塘区');
new County(c, 'LSQ','芦淞区');
new County(c, 'SFQ','石峰区');
new County(c, 'TYQ','天元区');
new County(c, 'ZZX','株洲县');
new County(c, 'YX','攸县');
new County(c, 'CLX','茶陵县');
new County(c, 'YLX','炎陵县');
new County(c, 'LL','醴陵');

c = new City(province, '湖南','XT','湘潭');
new County(c, 'BX','不限');
new County(c, 'YHQ','雨湖区');
new County(c, 'YTQ','岳塘区');
new County(c, 'XTX','湘潭县');
new County(c, 'XX','湘乡');
new County(c, 'SS','韶山');

c = new City(province, '湖南','HY','衡阳');
new County(c, 'BX','不限');
new County(c, 'ZHQ','珠晖区');
new County(c, 'YFQ','雁峰区');
new County(c, 'SGQ','石鼓区');
new County(c, 'ZXQ','蒸湘区');
new County(c, 'NYQ','南岳区');
new County(c, 'HYX','衡阳县');
new County(c, 'HNX','衡南县');
new County(c, 'HSX','衡山县');
new County(c, 'HDX','衡东县');
new County(c, 'QDX','祁东县');
new County(c, 'LY','耒阳');
new County(c, 'CN','常宁');

c = new City(province, '湖南','SY','邵阳');
new County(c, 'BX','不限');
new County(c, 'SQQ','双清区');
new County(c, 'DXQ','大祥区');
new County(c, 'BTQ','北塔区');
new County(c, 'SDX','邵东县');
new County(c, 'XSX','新邵县');
new County(c, 'SYX','邵阳县');
new County(c, 'LHX','隆回县');
new County(c, 'DKX','洞口县');
new County(c, 'SNX','绥宁县');
new County(c, 'XNX','新宁县');
new County(c, 'CBMZ','城步苗族');
new County(c, 'WG','武冈');

c = new City(province, '湖南','YY','岳阳');
new County(c, 'BX','不限');
new County(c, 'YYLQ','岳阳楼区');
new County(c, 'YXQ','云溪区');
new County(c, 'JSQ','君山区');
new County(c, 'YYX','岳阳县');
new County(c, 'HRX','华容县');
new County(c, 'XYX','湘阴县');
new County(c, 'PJX','平江县');
new County(c, 'ML','汨罗');
new County(c, 'LX','临湘');

c = new City(province, '湖南','CD','常德');
new County(c, 'BX','不限');
new County(c, 'WLQ','武陵区');
new County(c, 'DCQ','鼎城区');
new County(c, 'AXX','安乡县');
new County(c, 'HSX','汉寿县');
new County(c, 'LX','澧县');
new County(c, 'LLX','临澧县');
new County(c, 'TYX','桃源县');
new County(c, 'SMX','石门县');
new County(c, 'JS','津市');

c = new City(province, '湖南','ZJJ','张家界');
new County(c, 'BX','不限');
new County(c, 'YDQ','永定区');
new County(c, 'WLYQ','武陵源区');
new County(c, 'CLX','慈利县');
new County(c, 'SZX','桑植县');

c = new City(province, '湖南','YIY','益阳');
new County(c, 'BX','不限');
new County(c, 'ZYQ','资阳区');
new County(c, 'HSQ','赫山区');
new County(c, 'NX','南县');
new County(c, 'TJX','桃江县');
new County(c, 'AHX','安化县');
new County(c, 'YJ','沅江');

c = new City(province, '湖南','CZ','郴州');
new County(c, 'BX','不限');
new County(c, 'BHQ','北湖区');
new County(c, 'SXQ','苏仙区');
new County(c, 'GYX','桂阳县');
new County(c, 'YZX','宜章县');
new County(c, 'YXX','永兴县');
new County(c, 'JHX','嘉禾县');
new County(c, 'LWX','临武县');
new County(c, 'RCX','汝城县');
new County(c, 'GDX','桂东县');
new County(c, 'ARX','安仁县');
new County(c, 'ZX','资兴');

c = new City(province, '湖南','YZ','永州');
new County(c, 'BX','不限');
new County(c, 'ZSQ','芝山区');
new County(c, 'LSTQ','冷水滩区');
new County(c, 'QYX','祁阳县');
new County(c, 'DAX','东安县');
new County(c, 'SPX','双牌县');
new County(c, 'DX','道县');
new County(c, 'JYX','江永县');
new County(c, 'NYX','宁远县');
new County(c, 'LSX','蓝山县');
new County(c, 'XTX','新田县');
new County(c, 'JHYZ','江华瑶族');
new County(c, 'LLQ','零陵区');

c = new City(province, '湖南','HH','怀化');
new County(c, 'BX','不限');
new County(c, 'HCQ','鹤城区');
new County(c, 'ZFX','中方县');
new County(c, 'YLX','沅陵县');
new County(c, 'CXX','辰溪县');
new County(c, 'XPX','溆浦县');
new County(c, 'HTX','会同县');
new County(c, 'MYMZ','麻阳苗族');
new County(c, 'XHDZ','新晃侗族');
new County(c, 'ZJDZ','芷江侗族');
new County(c, 'JZ','靖州');
new County(c, 'TDDZ','通道侗族');
new County(c, 'HJ','洪江');

c = new City(province, '湖南','LD','娄底');
new County(c, 'BX','不限');
new County(c, 'LXQ','娄星区');
new County(c, 'SFX','双峰县');
new County(c, 'XHX','新化县');
new County(c, 'LSJ','冷水江');
new County(c, 'LY','涟源');

c = new City(province, '湖南','XXZ','湘西州');
new County(c, 'JS','吉首');
new County(c, 'LXX','泸溪县');
new County(c, 'FHX','凤凰县');
new County(c, 'HYX','花垣县');
new County(c, 'BJX','保靖县');
new County(c, 'GZX','古丈县');
new County(c, 'YSX','永顺县');
new County(c, 'LSX','龙山县');

c = new City(province, '湖南','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','GD','广东');
c = new City(province, '广东','GZ','广州');
new County(c, 'BX','不限');
new County(c, 'DSQ','东山区');
new County(c, 'LWQ','荔湾区');
new County(c, 'YXQ','越秀区');
new County(c, 'HZQ','海珠区');
new County(c, 'THQ','天河区');
new County(c, 'FCQ','芳村区');
new County(c, 'BYQ','白云区');
new County(c, 'HPQ','黄埔区');
new County(c, 'FYQ','番禺区');
new County(c, 'HDQ','花都区');
new County(c, 'ZC','增城');
new County(c, 'CH','从化');

c = new City(province, '广东','SG','韶关');
new County(c, 'BX','不限');
new County(c, 'WJQ','武江区');
new County(c, 'ZJQ','浈江区');
new County(c, 'QJQ','曲江区');
new County(c, 'SXX','始兴县');
new County(c, 'RHX','仁化县');
new County(c, 'WYX','翁源县');
new County(c, 'RYYZ','乳源瑶族');
new County(c, 'XFX','新丰县');
new County(c, 'LC','乐昌');
new County(c, 'NX','南雄');

c = new City(province, '广东','SZ','深圳');
new County(c, 'BX','不限');
new County(c, 'LHQ','罗湖区');
new County(c, 'FTQ','福田区');
new County(c, 'NSQ','南山区');
new County(c, 'BAQ','宝安区');
new County(c, 'LGQ','龙岗区');
new County(c, 'YTQ','盐田区');

c = new City(province, '广东','ZH','珠海');
new County(c, 'BX','不限');
new County(c, 'XZQ','香洲区');
new County(c, 'DMQ','斗门区');
new County(c, 'JWQ','金湾区');

c = new City(province, '广东','ST','汕头');
new County(c, 'BX','不限');
new County(c, 'LHQ','龙湖区');
new County(c, 'JPQ','金平区');
new County(c, 'HJQ','濠江区');
new County(c, 'CYQ','潮阳区');
new County(c, 'CNQ','潮南区');
new County(c, 'CHQ','澄海区');
new County(c, 'NAX','南澳县');

c = new City(province, '广东','FS','佛山');
new County(c, 'BX','不限');
new County(c, 'SCQ','禅城区');
new County(c, 'NHQ','南海区');
new County(c, 'SDQ','顺德区');
new County(c, 'SSQ','三水区');
new County(c, 'GMQ','高明区');

c = new City(province, '广东','JM','江门');
new County(c, 'BX','不限');
new County(c, 'PJQ','蓬江区');
new County(c, 'JHQ','江海区');
new County(c, 'XHQ','新会区');
new County(c, 'TS','台山');
new County(c, 'KP','开平');
new County(c, 'HS','鹤山');
new County(c, 'EP','恩平');

c = new City(province, '广东','ZJ','湛江');
new County(c, 'BX','不限');
new County(c, 'CKQ','赤坎区');
new County(c, 'XSQ','霞山区');
new County(c, 'PTQ','坡头区');
new County(c, 'MZQ','麻章区');
new County(c, 'SXX','遂溪县');
new County(c, 'XWX','徐闻县');
new County(c, 'LJ','廉江');
new County(c, 'LZ','雷州');
new County(c, 'WC','吴川');

c = new City(province, '广东','MM','茂名');
new County(c, 'BX','不限');
new County(c, 'MNQ','茂南区');
new County(c, 'MGQ','茂港区');
new County(c, 'DBX','电白县');
new County(c, 'GZ','高州');
new County(c, 'HZ','化州');
new County(c, 'XY','信宜');

c = new City(province, '广东','ZQ','肇庆');
new County(c, 'BX','不限');
new County(c, 'DZQ','端州区');
new County(c, 'DHQ','鼎湖区');
new County(c, 'GNX','广宁县');
new County(c, 'HJX','怀集县');
new County(c, 'FKX','封开县');
new County(c, 'DQX','德庆县');
new County(c, 'GY','高要');
new County(c, 'SH','四会');

c = new City(province, '广东','HZ','惠州');
new County(c, 'BX','不限');
new County(c, 'HCQ','惠城区');
new County(c, 'HYQ','惠阳区');
new County(c, 'BLX','博罗县');
new County(c, 'HDX','惠东县');
new County(c, 'LMX','龙门县');

c = new City(province, '广东','MZ','梅州');
new County(c, 'BX','不限');
new County(c, 'MJQ','梅江区');
new County(c, 'MX','梅县');
new County(c, 'DPX','大埔县');
new County(c, 'FSX','丰顺县');
new County(c, 'WHX','五华县');
new County(c, 'PYX','平远县');
new County(c, 'JLX','蕉岭县');
new County(c, 'XN','兴宁');

c = new City(province, '广东','SW','汕尾');
new County(c, 'BX','不限');
new County(c, 'CQ','城区');
new County(c, 'HFX','海丰县');
new County(c, 'LHX','陆河县');
new County(c, 'LF','陆丰');

c = new City(province, '广东','HY','河源');
new County(c, 'BX','不限');
new County(c, 'YCQ','源城区');
new County(c, 'ZJX','紫金县');
new County(c, 'LCX','龙川县');
new County(c, 'LPX','连平县');
new County(c, 'HPX','和平县');
new County(c, 'DYX','东源县');

c = new City(province, '广东','YJ','阳江');
new County(c, 'BX','不限');
new County(c, 'JCQ','江城区');
new County(c, 'YXX','阳西县');
new County(c, 'YDX','阳东县');
new County(c, 'YC','阳春');

c = new City(province, '广东','QY','清远');
new County(c, 'BX','不限');
new County(c, 'QCQ','清城区');
new County(c, 'FGX','佛冈县');
new County(c, 'YSX','阳山县');
new County(c, 'LSZZYZ','连山壮族瑶族');
new County(c, 'LNYZ','连南瑶族');
new County(c, 'QXX','清新县');
new County(c, 'YD','英德');
new County(c, 'LZ','连州');

c = new City(province, '广东','DG','东莞');

c = new City(province, '广东','ZS','中山');

c = new City(province, '广东','CZ','潮州');
new County(c, 'BX','不限');
new County(c, 'XQQ','湘桥区');
new County(c, 'CAX','潮安县');
new County(c, 'RPX','饶平县');

c = new City(province, '广东','JY','揭阳');
new County(c, 'BX','不限');
new County(c, 'RCQ','榕城区');
new County(c, 'JDX','揭东县');
new County(c, 'JXX','揭西县');
new County(c, 'HLX','惠来县');
new County(c, 'PN','普宁');

c = new City(province, '广东','YF','云浮');
new County(c, 'BX','不限');
new County(c, 'YCQ','云城区');
new County(c, 'XXX','新兴县');
new County(c, 'YNX','郁南县');
new County(c, 'YAX','云安县');
new County(c, 'LD','罗定');

c = new City(province, '广东','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','GX','广西', {type : 3, fullName : '广西壮族自治区'});
c = new City(province, '广西','NN','南宁');
new County(c, 'BX','不限');
new County(c, 'XNQ','兴宁区');
new County(c, 'QXQ','青秀区');
new County(c, 'JNQ','江南区');
new County(c, 'XXTQ','西乡塘区');
new County(c, 'LQQ','良庆区');
new County(c, 'YNQ','邕宁区');
new County(c, 'WMX','武鸣县');
new County(c, 'LAX','隆安县');
new County(c, 'MSX','马山县');
new County(c, 'SLX','上林县');
new County(c, 'BYX','宾阳县');
new County(c, 'HX','横县');

c = new City(province, '广西','LZ','柳州');
new County(c, 'BX','不限');
new County(c, 'CZQ','城中区');
new County(c, 'YFQ','鱼峰区');
new County(c, 'LNQ','柳南区');
new County(c, 'LBQ','柳北区');
new County(c, 'LJX','柳江县');
new County(c, 'LCX','柳城县');
new County(c, 'LZX','鹿寨县');
new County(c, 'RAX','融安县');
new County(c, 'RSMZ','融水苗族');
new County(c, 'SJX','三江县');

c = new City(province, '广西','GL','桂林');
new County(c, 'BX','不限');
new County(c, 'XFQ','秀峰区');
new County(c, 'DCQ','叠彩区');
new County(c, 'XSQ','象山区');
new County(c, 'QXQ','七星区');
new County(c, 'YSQ','雁山区');
new County(c, 'YSX','阳朔县');
new County(c, 'LGX','临桂县');
new County(c, 'LCX','灵川县');
new County(c, 'QZX','全州县');
new County(c, 'XAX','兴安县');
new County(c, 'YFX','永福县');
new County(c, 'GYX','灌阳县');
new County(c, 'LSGZ','龙胜各族');
new County(c, 'ZYX','资源县');
new County(c, 'PLX','平乐县');
new County(c, 'LPX','荔蒲县');
new County(c, 'GCYZ','恭城瑶族');

c = new City(province, '广西','WZ','梧州');
new County(c, 'BX','不限');
new County(c, 'WXQ','万秀区');
new County(c, 'DSQ','蝶山区');
new County(c, 'ZZQ','长洲区');
new County(c, 'CWX','苍梧县');
new County(c, 'TX','藤县');
new County(c, 'MSX','蒙山县');
new County(c, 'CX','岑溪');

c = new City(province, '广西','BH','北海');
new County(c, 'BX','不限');
new County(c, 'HCQ','海城区');
new County(c, 'YHQ','银海区');
new County(c, 'TSGQ','铁山港区');
new County(c, 'HPX','合浦县');

c = new City(province, '广西','FCG','防城港');
new County(c, 'BX','不限');
new County(c, 'GKQ','港口区');
new County(c, 'FCQ','防城区');
new County(c, 'SSX','上思县');
new County(c, 'DX','东兴');

c = new City(province, '广西','QZ','钦州');
new County(c, 'BX','不限');
new County(c, 'QNQ','钦南区');
new County(c, 'QBQ','钦北区');
new County(c, 'LSX','灵山县');
new County(c, 'PBX','浦北县');

c = new City(province, '广西','GG','贵港');
new County(c, 'BX','不限');
new County(c, 'GBQ','港北区');
new County(c, 'GNQ','港南区');
new County(c, 'TTQ','覃塘区');
new County(c, 'PNX','平南县');
new County(c, 'GP','桂平');

c = new City(province, '广西','YL','玉林');
new County(c, 'BX','不限');
new County(c, 'YZQ','玉州区');
new County(c, 'RX','容县');
new County(c, 'LCX','陆川县');
new County(c, 'BBX','博白县');
new County(c, 'XYX','兴业县');
new County(c, 'BL','北流');

c = new City(province, '广西','BS','百色');
new County(c, 'BX','不限');
new County(c, 'YJQ','右江区');
new County(c, 'TYX','田阳县');
new County(c, 'TDX','田东县');
new County(c, 'PGX','平果县');
new County(c, 'DBX','德保县');
new County(c, 'JXX','靖西县');
new County(c, 'NPX','那坡县');
new County(c, 'LYX','凌云县');
new County(c, 'LEYEX','乐业县');
new County(c, 'TLX','田林县');
new County(c, 'XLX','西林县');
new County(c, 'LLGZ','隆林各族');

c = new City(province, '广西','HZ','贺州');
new County(c, 'BX','不限');
new County(c, 'BBQ','八步区');
new County(c, 'ZPX','昭平县');
new County(c, 'ZSX','钟山县');
new County(c, 'FCYZ','富川瑶族');

c = new City(province, '广西','HC','河池');
new County(c, 'BX','不限');
new County(c, 'JCJQ','金城江区');
new County(c, 'NDX','南丹县');
new County(c, 'TEX','天峨县');
new County(c, 'FSX','凤山县');
new County(c, 'DLX','东兰县');
new County(c, 'LCMLZ','罗城仫佬族');
new County(c, 'HJMNZ','环江毛南族');
new County(c, 'BMYZ','巴马瑶族');
new County(c, 'DAYZ','都安瑶族');
new County(c, 'DHYZ','大化瑶族');
new County(c, 'YZ','宜州');

c = new City(province, '广西','LB','来宾');
new County(c, 'BX','不限');
new County(c, 'XBQ','兴宾区');
new County(c, 'XCX','忻城县');
new County(c, 'XZX','象州县');
new County(c, 'WXX','武宣县');
new County(c, 'JXYZ','金秀瑶族');
new County(c, 'HS','合山');

c = new City(province, '广西','CZ','崇左');
new County(c, 'BX','不限');
new County(c, 'JZQ','江洲区');
new County(c, 'FSX','扶绥县');
new County(c, 'NMX','宁明县');
new County(c, 'LZX','龙州县');
new County(c, 'DXX','大新县');
new County(c, 'TDX','天等县');
new County(c, 'PX','凭祥');

c = new City(province, '广西','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','HN','海南');
c = new City(province, '海南','HK','海口');
new County(c, 'BX','不限');
new County(c, 'XYQ','秀英区');
new County(c, 'LHQ','龙华区');
new County(c, 'QSQ','琼山区');
new County(c, 'MLQ','美兰区');

c = new City(province, '海南','SY','三亚');
new County(c, 'BX','不限');

c = new City(province, '海南','SZXX','省直辖县');
new County(c, 'WZS','五指山');
new County(c, 'QH','琼海');
new County(c, 'DZ','儋州');
new County(c, 'WC','文昌');
new County(c, 'WN','万宁');
new County(c, 'DF','东方');
new County(c, 'DAX','定安县');
new County(c, 'TCX','屯昌县');
new County(c, 'CMX','澄迈县');
new County(c, 'LGX','临高县');
new County(c, 'BSLZ','白沙黎族');
new County(c, 'CJLZ','昌江黎族');
new County(c, 'LDLZ','乐东黎族');
new County(c, 'LSLZ','陵水黎族');
new County(c, 'BTX','保亭县');
new County(c, 'QZX','琼中县');
new County(c, 'XSQD','西沙群岛');
new County(c, 'NSQD','南沙群岛');
new County(c, 'ZSQD','中沙群岛');

c = new City(province, '海南','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','ZQ','重庆', {type : 1});
c = new City(province, '重庆','ZQ','重庆');
new County(c, 'BX','不限');
new County(c, 'WZQ','万州区');
new County(c, 'FLQ','涪陵区');
new County(c, 'YZQ','渝中区');
new County(c, 'DDKQ','大渡口区');
new County(c, 'JBQ','江北区');
new County(c, 'SPBQ','沙坪坝区');
new County(c, 'JLPQ','九龙坡区');
new County(c, 'NAQ','南岸区');
new County(c, 'BBQ','北碚区');
new County(c, 'WSQ','万盛区');
new County(c, 'SQQ','双桥区');
new County(c, 'YBQ','渝北区');
new County(c, 'BNQ','巴南区');
new County(c, 'QJQ','黔江区');
new County(c, 'ZSQ','长寿区');
new County(c, 'QJX','綦江县');
new County(c, 'TNX','潼南县');
new County(c, 'TLX','铜梁县');
new County(c, 'DZX','大足县');
new County(c, 'RCX','荣昌县');
new County(c, 'BSX','璧山县');
new County(c, 'LPX','梁平县');
new County(c, 'CKX','城口县');
new County(c, 'FDX','丰都县');
new County(c, 'DJX','垫江县');
new County(c, 'WLX','武隆县');
new County(c, 'ZX','忠县');
new County(c, 'KX','开县');
new County(c, 'YUYX','云阳县');
new County(c, 'FJX','奉节县');
new County(c, 'WSX','巫山县');
new County(c, 'WXX','巫溪县');
new County(c, 'SZX','石柱县');
new County(c, 'XSX','秀山县');
new County(c, 'YYX','酉阳县');
new County(c, 'PSX','彭水县');
new County(c, 'JJ','江津');
new County(c, 'HC','合川');
new County(c, 'YC','永川');
new County(c, 'NC','南川');

c = new City(province, '重庆','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','SC','四川');
c = new City(province, '四川','CD','成都');
new County(c, 'BX','不限');
new County(c, 'JJQ','锦江区');
new County(c, 'QYQ','青羊区');
new County(c, 'JNQ','金牛区');
new County(c, 'WHQ','武侯区');
new County(c, 'CHQ','成华区');
new County(c, 'LQYQ','龙泉驿区');
new County(c, 'QBJQ','青白江区');
new County(c, 'XDQ','新都区');
new County(c, 'WJQ','温江区');
new County(c, 'JTX','金堂县');
new County(c, 'SLX','双流县');
new County(c, 'PX','郫县');
new County(c, 'DYX','大邑县');
new County(c, 'PJX','蒲江县');
new County(c, 'XJX','新津县');
new County(c, 'DJY','都江堰');
new County(c, 'PZ','彭州');
new County(c, 'QL','邛崃');
new County(c, 'CZ','崇州');

c = new City(province, '四川','ZG','自贡');
new County(c, 'BX','不限');
new County(c, 'ZLJQ','自流井区');
new County(c, 'GJQ','贡井区');
new County(c, 'DAQ','大安区');
new County(c, 'YTQ','沿滩区');
new County(c, 'RX','荣县');
new County(c, 'FSX','富顺县');

c = new City(province, '四川','PZH','攀枝花');
new County(c, 'BX','不限');
new County(c, 'DQ','东区');
new County(c, 'XQ','西区');
new County(c, 'RHQ','仁和区');
new County(c, 'MYX','米易县');
new County(c, 'YBX','盐边县');

c = new City(province, '四川','LZ','泸州');
new County(c, 'BX','不限');
new County(c, 'JYQ','江阳区');
new County(c, 'NXQ','纳溪区');
new County(c, 'LMTQ','龙马潭区');
new County(c, 'LX','泸县');
new County(c, 'HJX','合江县');
new County(c, 'XYX','叙永县');
new County(c, 'GLX','古蔺县');

c = new City(province, '四川','DY','德阳');
new County(c, 'BX','不限');
new County(c, 'JYQ','旌阳区');
new County(c, 'ZJX','中江县');
new County(c, 'LJX','罗江县');
new County(c, 'GH','广汉');
new County(c, 'SF','什邡');
new County(c, 'MZ','绵竹');

c = new City(province, '四川','MY','绵阳');
new County(c, 'BX','不限');
new County(c, 'FCQ','涪城区');
new County(c, 'YXQ','游仙区');
new County(c, 'STX','三台县');
new County(c, 'YTX','盐亭县');
new County(c, 'AX','安县');
new County(c, 'ZTX','梓潼县');
new County(c, 'BCQZ','北川羌族');
new County(c, 'PWX','平武县');
new County(c, 'JY','江油');

c = new City(province, '四川','GY','广元');
new County(c, 'BX','不限');
new County(c, 'SZQ','市中区');
new County(c, 'YBQ','元坝区');
new County(c, 'CTQ','朝天区');
new County(c, 'WCX','旺苍县');
new County(c, 'QCX','青川县');
new County(c, 'JGX','剑阁县');
new County(c, 'CXX','苍溪县');
new County(c, 'LZQ','利州区');

c = new City(province, '四川','SN','遂宁');
new County(c, 'BX','不限');
new County(c, 'CSQ','船山区');
new County(c, 'AJQ','安居区');
new County(c, 'PXX','蓬溪县');
new County(c, 'SHX','射洪县');
new County(c, 'DYX','大英县');

c = new City(province, '四川','NJ','内江');
new County(c, 'BX','不限');
new County(c, 'SZQ','市中区');
new County(c, 'DXQ','东兴区');
new County(c, 'WYX','威远县');
new County(c, 'ZZX','资中县');
new County(c, 'LCX','隆昌县');

c = new City(province, '四川','LS','乐山');
new County(c, 'BX','不限');
new County(c, 'SZQ','市中区');
new County(c, 'SWQ','沙湾区');
new County(c, 'WTQQ','五通桥区');
new County(c, 'JKHQ','金口河区');
new County(c, 'JWX','犍为县');
new County(c, 'JYX','井研县');
new County(c, 'JJX','夹江县');
new County(c, 'MCX','沐川县');
new County(c, 'EBX','峨边县');
new County(c, 'MBX','马边县');
new County(c, 'EMS','峨眉山');

c = new City(province, '四川','NC','南充');
new County(c, 'BX','不限');
new County(c, 'SQQ','顺庆区');
new County(c, 'GPQ','高坪区');
new County(c, 'JLQ','嘉陵区');
new County(c, 'NBX','南部县');
new County(c, 'YSX','营山县');
new County(c, 'PAX','蓬安县');
new County(c, 'YLX','仪陇县');
new County(c, 'XCX','西充县');
new County(c, 'LZ','阆中');

c = new City(province, '四川','MS','眉山');
new County(c, 'BX','不限');
new County(c, 'DPQ','东坡区');
new County(c, 'RSX','仁寿县');
new County(c, 'PSX','彭山县');
new County(c, 'HYX','洪雅县');
new County(c, 'DLX','丹棱县');
new County(c, 'QSX','青神县');

c = new City(province, '四川','YB','宜宾');
new County(c, 'BX','不限');
new County(c, 'CPQ','翠屏区');
new County(c, 'YBX','宜宾县');
new County(c, 'NXX','南溪县');
new County(c, 'JAX','江安县');
new County(c, 'ZNX','长宁县');
new County(c, 'GAX','高县');
new County(c, 'GX','珙县');
new County(c, 'YLX','筠连县');
new County(c, 'XWX','兴文县');
new County(c, 'PSX','屏山县');

c = new City(province, '四川','GA','广安');
new County(c, 'BX','不限');
new County(c, 'GAQ','广安区');
new County(c, 'YCX','岳池县');
new County(c, 'WSX','武胜县');
new County(c, 'LSX','邻水县');
new County(c, 'HY','华莹');

c = new City(province, '四川','DZ','达州');
new County(c, 'BX','不限');
new County(c, 'TCQ','通川区');
new County(c, 'DX','达县');
new County(c, 'XHX','宣汉县');
new County(c, 'KJX','开江县');
new County(c, 'DZX','大竹县');
new County(c, 'QX','渠县');
new County(c, 'WY','万源');

c = new City(province, '四川','YA','雅安');
new County(c, 'BX','不限');
new County(c, 'YCQ','雨城区');
new County(c, 'MSX','名山县');
new County(c, 'YJX','荥经县');
new County(c, 'HYX','汉源县');
new County(c, 'SMX','石棉县');
new County(c, 'TQX','天全县');
new County(c, 'LSX','芦山县');
new County(c, 'BXX','宝兴县');

c = new City(province, '四川','BZ','巴中');
new County(c, 'BX','不限');
new County(c, 'BZQ','巴州区');
new County(c, 'TJX','通江县');
new County(c, 'NJX','南江县');
new County(c, 'PCX','平昌县');

c = new City(province, '四川','ZY','资阳');
new County(c, 'BX','不限');
new County(c, 'YJQ','雁江区');
new County(c, 'AYX','安岳县');
new County(c, 'LZX','乐至县');
new County(c, 'JY','简阳');

c = new City(province, '四川','ABZ','阿坝州');
new County(c, 'WCX','汶川县');
new County(c, 'LX','理县');
new County(c, 'MX','茂县');
new County(c, 'SPX','松潘县');
new County(c, 'JZGX','九寨沟县');
new County(c, 'JCX','金川县');
new County(c, 'XJX','小金县');
new County(c, 'HSX','黑水县');
new County(c, 'MEKX','马尔康县');
new County(c, 'RTX','壤塘县');
new County(c, 'ABX','阿坝县');
new County(c, 'REGX','若尔盖县');
new County(c, 'HYX','红原县');

c = new City(province, '四川','GZZZ','甘孜藏族');
new County(c, 'KDX','康定县');
new County(c, 'LDX','泸定县');
new County(c, 'DBX','丹巴县');
new County(c, 'JLX','九龙县');
new County(c, 'YJX','雅江县');
new County(c, 'DFX','道孚县');
new County(c, 'LHX','炉霍县');
new County(c, 'GZX','甘孜县');
new County(c, 'XLX','新龙县');
new County(c, 'DGX','德格县');
new County(c, 'BYX','白玉县');
new County(c, 'SQX','石渠县');
new County(c, 'SDX','色达县');
new County(c, 'LTX','理塘县');
new County(c, 'BTX','巴塘县');
new County(c, 'XCX','乡城县');
new County(c, 'DCX','稻城县');
new County(c, 'DRX','得荣县');

c = new City(province, '四川','LSYZ','凉山彝族');
new County(c, 'XC','西昌');
new County(c, 'MLZZ','木里藏族');
new County(c, 'YYX','盐源县');
new County(c, 'DCX','德昌县');
new County(c, 'HLX','会理县');
new County(c, 'HDX','会东县');
new County(c, 'NNX','宁南县');
new County(c, 'PGX','普格县');
new County(c, 'BTX','布拖县');
new County(c, 'JYX','金阳县');
new County(c, 'ZJX','昭觉县');
new County(c, 'XDX','喜德县');
new County(c, 'MNX','冕宁县');
new County(c, 'YXX','越西县');
new County(c, 'GLX','甘洛县');
new County(c, 'MGX','美姑县');
new County(c, 'LBX','雷波县');

c = new City(province, '四川','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','GZ','贵州');
c = new City(province, '贵州','GY','贵阳');
new County(c, 'BX','不限');
new County(c, 'NMQ','南明区');
new County(c, 'YYQ','云岩区');
new County(c, 'HXQ','花溪区');
new County(c, 'WDQ','乌当区');
new County(c, 'BYQ','白云区');
new County(c, 'XHQ','小河区');
new County(c, 'KYX','开阳县');
new County(c, 'XFX','息烽县');
new County(c, 'XWX','修文县');
new County(c, 'QZ','清镇');

c = new City(province, '贵州','LPS','六盘水');
new County(c, 'ZSQ','钟山区');
new County(c, 'LZTQ','六枝特区');
new County(c, 'SCX','水城县');
new County(c, 'PX','盘县');

c = new City(province, '贵州','ZY','遵义');
new County(c, 'BX','不限');
new County(c, 'HHGQ','红花岗区');
new County(c, 'HCQ','汇川区');
new County(c, 'ZYX','遵义县');
new County(c, 'TZX','桐梓县');
new County(c, 'SYX','绥阳县');
new County(c, 'ZAX','正安县');
new County(c, 'DZX','道真县');
new County(c, 'WCX','务川县');
new County(c, 'FGX','凤冈县');
new County(c, 'MTX','湄潭县');
new County(c, 'YQX','余庆县');
new County(c, 'XSX','习水县');
new County(c, 'CS','赤水');
new County(c, 'RH','仁怀');

c = new City(province, '贵州','AS','安顺');
new County(c, 'BX','不限');
new County(c, 'XXQ','西秀区');
new County(c, 'PBX','平坝县');
new County(c, 'PDX','普定县');
new County(c, 'ZNX','镇宁县');
new County(c, 'GLX','关岭县');
new County(c, 'ZYX','紫云县');

c = new City(province, '贵州','TRDQ','铜仁地区');
new County(c, 'TR','铜仁');
new County(c, 'JKX','江口县');
new County(c, 'YPDZ','玉屏侗族');
new County(c, 'SQX','石阡县');
new County(c, 'SNX','思南县');
new County(c, 'YJX','印江县');
new County(c, 'DJX','德江县');
new County(c, 'YHX','沿河县');
new County(c, 'STMZ','松桃苗族');
new County(c, 'WSTQ','万山特区');

c = new City(province, '贵州','QXNZ','黔西南州');
new County(c, 'XY','兴义');
new County(c, 'XRX','兴仁县');
new County(c, 'PAX','普安县');
new County(c, 'QLX','晴隆县');
new County(c, 'ZFX','贞丰县');
new County(c, 'WMX','望谟县');
new County(c, 'CHX','册亨县');
new County(c, 'ALX','安龙县');

c = new City(province, '贵州','BJDQ','毕节地区');
new County(c, 'BJ','毕节');
new County(c, 'DFX','大方县');
new County(c, 'QXX','黔西县');
new County(c, 'JSX','金沙县');
new County(c, 'ZJX','织金县');
new County(c, 'NYX','纳雍县');
new County(c, 'WNX','威宁县');
new County(c, 'HZX','赫章县');

c = new City(province, '贵州','QDNZ','黔东南州');
new County(c, 'KL','凯里');
new County(c, 'HPX','黄平县');
new County(c, 'SBX','施秉县');
new County(c, 'SSX','三穗县');
new County(c, 'ZYX','镇远县');
new County(c, 'CGX','岑巩县');
new County(c, 'TZX','天柱县');
new County(c, 'JPX','锦屏县');
new County(c, 'JHX','剑河县');
new County(c, 'TJX','台江县');
new County(c, 'LPX','黎平县');
new County(c, 'RJX','榕江县');
new County(c, 'CJX','从江县');
new County(c, 'LSX','雷山县');
new County(c, 'MJX','麻江县');
new County(c, 'DZX','丹寨县');

c = new City(province, '贵州','QNZ','黔南州');
new County(c, 'DY','都匀');
new County(c, 'FQ','福泉');
new County(c, 'LBX','荔波县');
new County(c, 'GDX','贵定县');
new County(c, 'WAX','瓮安县');
new County(c, 'DSX','独山县');
new County(c, 'PTX','平塘县');
new County(c, 'LDX','罗甸县');
new County(c, 'ZSX','长顺县');
new County(c, 'LLX','龙里县');
new County(c, 'HSX','惠水县');
new County(c, 'SDX','三都县');

c = new City(province, '贵州','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','YN','云南');
c = new City(province, '云南','KM','昆明');
new County(c, 'BX','不限');
new County(c, 'WHQ','五华区');
new County(c, 'PLQ','盘龙区');
new County(c, 'GDQ','官渡区');
new County(c, 'XSQ','西山区');
new County(c, 'DCQ','东川区');
new County(c, 'CGX','呈贡县');
new County(c, 'JNX','晋宁县');
new County(c, 'FMX','富民县');
new County(c, 'YLX','宜良县');
new County(c, 'SLYZ','石林彝族');
new County(c, 'SMX','嵩明县');
new County(c, 'LQX','禄劝县');
new County(c, 'XDX','寻甸县');
new County(c, 'AN','安宁');

c = new City(province, '云南','QJ','曲靖');
new County(c, 'BX','不限');
new County(c, 'QLQ','麒麟区');
new County(c, 'MLX','马龙县');
new County(c, 'LLX','陆良县');
new County(c, 'SZX','师宗县');
new County(c, 'LPX','罗平县');
new County(c, 'FYX','富源县');
new County(c, 'HZX','会泽县');
new County(c, 'ZYX','沾益县');
new County(c, 'XW','宣威');

c = new City(province, '云南','YX','玉溪');
new County(c, 'BX','不限');
new County(c, 'HTQ','红塔区');
new County(c, 'JCX','江川县');
new County(c, 'CJX','澄江县');
new County(c, 'THX','通海县');
new County(c, 'HNX','华宁县');
new County(c, 'YMX','易门县');
new County(c, 'ESYZ','峨山彝族');
new County(c, 'XPX','新平县');
new County(c, 'YJX','元江县');

c = new City(province, '云南','BS','保山');
new County(c, 'BX','不限');
new County(c, 'LYQ','隆阳区');
new County(c, 'SDX','施甸县');
new County(c, 'TCX','腾冲县');
new County(c, 'LLX','龙陵县');
new County(c, 'CNX','昌宁县');

c = new City(province, '云南','ZT','昭通');
new County(c, 'BX','不限');
new County(c, 'ZYQ','昭阳区');
new County(c, 'LDX','鲁甸县');
new County(c, 'QJX','巧家县');
new County(c, 'YJX','盐津县');
new County(c, 'DGX','大关县');
new County(c, 'YSX','永善县');
new County(c, 'SJX','绥江县');
new County(c, 'ZXX','镇雄县');
new County(c, 'YLX','彝良县');
new County(c, 'WXX','威信县');
new County(c, 'SFX','水富县');

c = new City(province, '云南','LJ','丽江');
new County(c, 'BX','不限');
new County(c, 'GCQ','古城区');
new County(c, 'YLX','玉龙县');
new County(c, 'YSX','永胜县');
new County(c, 'HPX','华坪县');
new County(c, 'NLYZ','宁蒗彝族');

c = new City(province, '云南','SM','思茅');
new County(c, 'BX','不限');
new County(c, 'CYQ','翠云区');
new County(c, 'PEX','普洱县');
new County(c, 'MJX','墨江县');
new County(c, 'JDYZ','景东彝族');
new County(c, 'JGX','景谷县');
new County(c, 'ZYX','镇沅县');
new County(c, 'JCX','江城县');
new County(c, 'MLX','孟连县');
new County(c, 'LCX','澜沧县');
new County(c, 'XMWZ','西盟佤族');

c = new City(province, '云南','LC','临沧');
new County(c, 'BX','不限');
new County(c, 'LXQ','临翔区');
new County(c, 'FQX','凤庆县');
new County(c, 'YX','云县');
new County(c, 'YDX','永德县');
new County(c, 'ZKX','镇康县');
new County(c, 'SJX','双江县');
new County(c, 'GMX','耿马县');
new County(c, 'CYX','沧源县');

c = new City(province, '云南','CXZ','楚雄州');
new County(c, 'CX','楚雄');
new County(c, 'SBX','双柏县');
new County(c, 'MDX','牟定县');
new County(c, 'NHX','南华县');
new County(c, 'YAX','姚安县');
new County(c, 'DYX','大姚县');
new County(c, 'YRX','永仁县');
new County(c, 'YMX','元谋县');
new County(c, 'WDX','武定县');
new County(c, 'LFX','禄丰县');

c = new City(province, '云南','HHZ','红河州');
new County(c, 'GJ','个旧');
new County(c, 'KY','开远');
new County(c, 'MZX','蒙自县');
new County(c, 'PBX','屏边县');
new County(c, 'JSX','建水县');
new County(c, 'SPX','石屏县');
new County(c, 'MLX','弥勒县');
new County(c, 'LXX','泸西县');
new County(c, 'YYX','元阳县');
new County(c, 'HHX','红河县');
new County(c, 'JPX','金平县');
new County(c, 'LCX','绿春县');
new County(c, 'HKX','河口县');

c = new City(province, '云南','WSZ','文山州');
new County(c, 'WSX','文山县');
new County(c, 'YSX','砚山县');
new County(c, 'XCX','西畴县');
new County(c, 'MLPX','麻栗坡县');
new County(c, 'MGX','马关县');
new County(c, 'QBX','丘北县');
new County(c, 'GNX','广南县');
new County(c, 'FNX','富宁县');

c = new City(province, '云南','XSBN','西双版纳');
new County(c, 'JH','景洪');
new County(c, 'MHX','勐海县');
new County(c, 'MLX','勐腊县');

c = new City(province, '云南','DLZ','大理州');
new County(c, 'DL','大理');
new County(c, 'YBX','漾濞县');
new County(c, 'XYX','祥云县');
new County(c, 'BCX','宾川县');
new County(c, 'MDX','弥渡县');
new County(c, 'NJX','南涧县');
new County(c, 'WSX','巍山县');
new County(c, 'YPX','永平县');
new County(c, 'YLX','云龙县');
new County(c, 'EYX','洱源县');
new County(c, 'JCX','剑川县');
new County(c, 'HQX','鹤庆县');

c = new City(province, '云南','DHZ','德宏州');
new County(c, 'RL','瑞丽');
new County(c, 'LX','潞西');
new County(c, 'LHX','梁河县');
new County(c, 'YJX','盈江县');
new County(c, 'LCX','陇川县');

c = new City(province, '云南','NJZ','怒江州');
new County(c, 'LSX','泸水县');
new County(c, 'FGX','福贡县');
new County(c, 'GSX','贡山县');
new County(c, 'LPX','兰坪县');

c = new City(province, '云南','DQZ','迪庆州');
new County(c, 'XGLLX','香格里拉县');
new County(c, 'DQX','德钦县');
new County(c, 'WXX','维西县');

c = new City(province, '云南','BX','不限');
new County(c, 'BX','不限');

c = new City(province, '云南','PE','普洱');
new County(c, 'SMQ','思茅区');


var province = new Province('CN','XZ','西藏', {type : 3, fullName : '西藏自治区'});
c = new City(province, '西藏','LS','拉萨');
new County(c, 'BX','不限');
new County(c, 'CGQ','城关区');
new County(c, 'LZX','林周县');
new County(c, 'DXX','当雄县');
new County(c, 'NMX','尼木县');
new County(c, 'QSX','曲水县');
new County(c, 'DLDQX','堆龙德庆县');
new County(c, 'DZX','达孜县');
new County(c, 'MZGKX','墨竹工卡县');

c = new City(province, '西藏','CDDQ','昌都地区');
new County(c, 'CDX','昌都县');
new County(c, 'JDX','江达县');
new County(c, 'GJX','贡觉县');
new County(c, 'LWQX','类乌齐县');
new County(c, 'DQX','丁青县');
new County(c, 'CYX','察雅县');
new County(c, 'BSX','八宿县');
new County(c, 'ZGX','左贡县');
new County(c, 'MKX','芒康县');
new County(c, 'LLX','洛隆县');
new County(c, 'BBX','边坝县');

c = new City(province, '西藏','SNDQ','山南地区');
new County(c, 'NDX','乃东县');
new County(c, 'ZNX','扎囊县');
new County(c, 'GGX','贡嘎县');
new County(c, 'SRX','桑日县');
new County(c, 'QJX','琼结县');
new County(c, 'QSX','曲松县');
new County(c, 'CMX','措美县');
new County(c, 'LOZX','洛扎县');
new County(c, 'JCX','加查县');
new County(c, 'LZX','隆子县');
new County(c, 'CNX','错那县');
new County(c, 'LKZX','浪卡子县');

c = new City(province, '西藏','RKZDQ','日喀则地区');
new County(c, 'RKZ','日喀则');
new County(c, 'NMLX','南木林县');
new County(c, 'JZX','江孜县');
new County(c, 'DRX','定日县');
new County(c, 'SJX','萨迦县');
new County(c, 'LZX','拉孜县');
new County(c, 'ARX','昂仁县');
new County(c, 'XTMX','谢通门县');
new County(c, 'BLX','白朗县');
new County(c, 'RBX','仁布县');
new County(c, 'KMX','康马县');
new County(c, 'DJX','定结县');
new County(c, 'ZBX','仲巴县');
new County(c, 'YDX','亚东县');
new County(c, 'JLX','吉隆县');
new County(c, 'NLMX','聂拉木县');
new County(c, 'SGX','萨嘎县');
new County(c, 'GBX','岗巴县');

c = new City(province, '西藏','NQDQ','那曲地区');
new County(c, 'NQX','那曲县');
new County(c, 'JLX','嘉黎县');
new County(c, 'BRX','比如县');
new County(c, 'NRX','聂荣县');
new County(c, 'ADX','安多县');
new County(c, 'SZX','申扎县');
new County(c, 'SX','索县');
new County(c, 'BGX','班戈县');
new County(c, 'BQX','巴青县');
new County(c, 'NMX','尼玛县');

c = new City(province, '西藏','ALDQ','阿里地区');
new County(c, 'PLX','普兰县');
new County(c, 'ZDX','札达县');
new County(c, 'GEX','噶尔县');
new County(c, 'RTX','日土县');
new County(c, 'GJX','革吉县');
new County(c, 'GZX','改则县');
new County(c, 'CQX','措勤县');

c = new City(province, '西藏','LZDQ','林芝地区');
new County(c, 'LZX','林芝县');
new County(c, 'GBJDX','工布江达县');
new County(c, 'MLX','米林县');
new County(c, 'MTX','墨脱县');
new County(c, 'BMX','波密县');
new County(c, 'CYX','察隅县');
new County(c, 'LX','朗县');

c = new City(province, '西藏','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','SHX','陕西');
c = new City(province, '陕西','XA','西安');
new County(c, 'BX','不限');
new County(c, 'XCQ','新城区');
new County(c, 'BLQ','碑林区');
new County(c, 'LHQ','莲湖区');
new County(c, 'BQQ','灞桥区');
new County(c, 'WYQ','未央区');
new County(c, 'YTQ','雁塔区');
new County(c, 'YLQ','阎良区');
new County(c, 'LTQ','临潼区');
new County(c, 'ZAQ','长安区');
new County(c, 'LTX','蓝田县');
new County(c, 'ZZX','周至县');
new County(c, 'HX','户县');
new County(c, 'GLX','高陵县');

c = new City(province, '陕西','TC','铜川');
new County(c, 'BX','不限');
new County(c, 'WYQ','王益区');
new County(c, 'YTQ','印台区');
new County(c, 'YZQ','耀州区');
new County(c, 'YJX','宜君县');

c = new City(province, '陕西','BJ','宝鸡');
new County(c, 'BX','不限');
new County(c, 'WBQ','渭滨区');
new County(c, 'JTQ','金台区');
new County(c, 'CCQ','陈仓区');
new County(c, 'FXX','凤翔县');
new County(c, 'QSHX','岐山县');
new County(c, 'FFX','扶风县');
new County(c, 'MX','眉县');
new County(c, 'LX','陇县');
new County(c, 'QYX','千阳县');
new County(c, 'LYX','麟游县');
new County(c, 'FX','凤县');
new County(c, 'TBX','太白县');

c = new City(province, '陕西','XY','咸阳');
new County(c, 'BX','不限');
new County(c, 'QDQ','秦都区');
new County(c, 'YLQ','杨凌区');
new County(c, 'WCQ','渭城区');
new County(c, 'SYX','三原县');
new County(c, 'JYX','泾阳县');
new County(c, 'QX','乾县');
new County(c, 'LQX','礼泉县');
new County(c, 'YSHX','永寿县');
new County(c, 'BIX','彬县');
new County(c, 'ZWX','长武县');
new County(c, 'XYX','旬邑县');
new County(c, 'CHX','淳化县');
new County(c, 'WGX','武功县');
new County(c, 'XP','兴平');

c = new City(province, '陕西','WN','渭南');
new County(c, 'BX','不限');
new County(c, 'LWQ','临渭区');
new County(c, 'HX','华县');
new County(c, 'TGX','潼关县');
new County(c, 'DLX','大荔县');
new County(c, 'HYX','合阳县');
new County(c, 'CCX','澄城县');
new County(c, 'PCX','蒲城县');
new County(c, 'BSX','白水县');
new County(c, 'FPX','富平县');
new County(c, 'HC','韩城');
new County(c, 'HY','华阴');

c = new City(province, '陕西','YA','延安');
new County(c, 'BX','不限');
new County(c, 'BTQ','宝塔区');
new County(c, 'YZX','延长县');
new County(c, 'YACX','延川县');
new County(c, 'ZZX','子长县');
new County(c, 'ASX','安塞县');
new County(c, 'ZDX','志丹县');
new County(c, 'WQX','吴旗县');
new County(c, 'GQX','甘泉县');
new County(c, 'FX','富县');
new County(c, 'LCX','洛川县');
new County(c, 'YCX','宜川县');
new County(c, 'HLOX','黄龙县');
new County(c, 'HLX','黄陵县');

c = new City(province, '陕西','HZ','汉中');
new County(c, 'BX','不限');
new County(c, 'HTQ','汉台区');
new County(c, 'NZX','南郑县');
new County(c, 'CGX','城固县');
new County(c, 'YX','洋县');
new County(c, 'XXX','西乡县');
new County(c, 'MX','勉县');
new County(c, 'NQX','宁强县');
new County(c, 'LYX','略阳县');
new County(c, 'ZBX','镇巴县');
new County(c, 'LBX','留坝县');
new County(c, 'FPX','佛坪县');

c = new City(province, '陕西','YL','榆林');
new County(c, 'BX','不限');
new County(c, 'YYQ','榆阳区');
new County(c, 'SMX','神木县');
new County(c, 'FGX','府谷县');
new County(c, 'HSX','横山县');
new County(c, 'JBX','靖边县');
new County(c, 'DBX','定边县');
new County(c, 'SDX','绥德县');
new County(c, 'MZX','米脂县');
new County(c, 'JX','佳县');
new County(c, 'WBX','吴堡县');
new County(c, 'QJX','清涧县');
new County(c, 'ZZX','子洲县');

c = new City(province, '陕西','AK','安康');
new County(c, 'BX','不限');
new County(c, 'HBQ','汉滨区');
new County(c, 'HYX','汉阴县');
new County(c, 'SQX','石泉县');
new County(c, 'NSX','宁陕县');
new County(c, 'ZYX','紫阳县');
new County(c, 'LGX','岚皋县');
new County(c, 'PLX','平利县');
new County(c, 'ZPX','镇坪县');
new County(c, 'XYX','旬阳县');
new County(c, 'BHX','白河县');

c = new City(province, '陕西','SL','商洛');
new County(c, 'BX','不限');
new County(c, 'SZQ','商州区');
new County(c, 'LNX','洛南县');
new County(c, 'DFX','丹凤县');
new County(c, 'SNX','商南县');
new County(c, 'SYX','山阳县');
new County(c, 'ZAX','镇安县');
new County(c, 'ZSHX','柞水县');

c = new City(province, '陕西','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','GS','甘肃');
c = new City(province, '甘肃','LZ','兰州');
new County(c, 'BX','不限');
new County(c, 'CGQ','城关区');
new County(c, 'QLHQ','七里河区');
new County(c, 'XGQ','西固区');
new County(c, 'ANQ','安宁区');
new County(c, 'HGQ','红古区');
new County(c, 'YDX','永登县');
new County(c, 'GLX','皋兰县');
new County(c, 'YZX','榆中县');

c = new City(province, '甘肃','JYG','嘉峪关');
new County(c, 'BX','不限');
new County(c, 'SXQ','市辖区');

c = new City(province, '甘肃','JC','金昌');
new County(c, 'BX','不限');
new County(c, 'JCQ','金川区');
new County(c, 'YCX','永昌县');

c = new City(province, '甘肃','BY','白银');
new County(c, 'BX','不限');
new County(c, 'BYQ','白银区');
new County(c, 'PCQ','平川区');
new County(c, 'JYX','靖远县');
new County(c, 'HNX','会宁县');
new County(c, 'JTX','景泰县');

c = new City(province, '甘肃','TS','天水');
new County(c, 'BX','不限');
new County(c, 'QCQ','秦城区');
new County(c, 'BDQ','北道区');
new County(c, 'QSX','清水县');
new County(c, 'QAX','秦安县');
new County(c, 'GGX','甘谷县');
new County(c, 'WSX','武山县');
new County(c, 'ZJCX','张家川县');
new County(c, 'QZQ','秦州区');

c = new City(province, '甘肃','WW','武威');
new County(c, 'BX','不限');
new County(c, 'LZQ','凉州区');
new County(c, 'MQX','民勤县');
new County(c, 'GLX','古浪县');
new County(c, 'TZX','天祝县');

c = new City(province, '甘肃','ZY','张掖');
new County(c, 'BX','不限');
new County(c, 'GZQ','甘州区');
new County(c, 'SNX','肃南县');
new County(c, 'MLX','民乐县');
new County(c, 'LZX','临泽县');
new County(c, 'GTX','高台县');
new County(c, 'SDX','山丹县');

c = new City(province, '甘肃','PL','平凉');
new County(c, 'BX','不限');
new County(c, 'KTQ','崆峒区');
new County(c, 'JCX','泾川县');
new County(c, 'LTX','灵台县');
new County(c, 'CXX','崇信县');
new County(c, 'HTX','华亭县');
new County(c, 'ZLX','庄浪县');
new County(c, 'JNX','静宁县');

c = new City(province, '甘肃','JQ','酒泉');
new County(c, 'BX','不限');
new County(c, 'SZQ','肃州区');
new County(c, 'JTX','金塔县');
new County(c, 'AXX','安西县');
new County(c, 'SBX','肃北县');
new County(c, 'AKS','阿克塞');
new County(c, 'YM','玉门');
new County(c, 'DH','敦煌');

c = new City(province, '甘肃','QY','庆阳');
new County(c, 'BX','不限');
new County(c, 'XFQ','西峰区');
new County(c, 'QCX','庆城县');
new County(c, 'HX','环县');
new County(c, 'HCX','华池县');
new County(c, 'HSX','合水县');
new County(c, 'ZNX','正宁县');
new County(c, 'NX','宁县');
new County(c, 'ZYX','镇原县');

c = new City(province, '甘肃','DX','定西');
new County(c, 'BX','不限');
new County(c, 'ADQ','安定区');
new County(c, 'TWX','通渭县');
new County(c, 'LXX','陇西县');
new County(c, 'WYX','渭源县');
new County(c, 'LTX','临洮县');
new County(c, 'ZX','漳县');
new County(c, 'MX','岷县');

c = new City(province, '甘肃','LN','陇南');
new County(c, 'BX','不限');
new County(c, 'WDQ','武都区');
new County(c, 'CX','成县');
new County(c, 'WX','文县');
new County(c, 'DCX','宕昌县');
new County(c, 'KX','康县');
new County(c, 'XHX','西和县');
new County(c, 'LX','礼县');
new County(c, 'HX','徽县');
new County(c, 'LDX','两当县');

c = new City(province, '甘肃','LXZ','临夏州');
new County(c, 'LX','临夏');
new County(c, 'LXX','临夏县');
new County(c, 'KLX','康乐县');
new County(c, 'YJX','永靖县');
new County(c, 'GHX','广河县');
new County(c, 'HZX','和政县');
new County(c, 'DXZ','东乡族');
new County(c, 'JSSX','积石山县');

c = new City(province, '甘肃','GNZ','甘南州');
new County(c, 'HZ','合作');
new County(c, 'LTX','临潭县');
new County(c, 'ZNX','卓尼县');
new County(c, 'ZQX','舟曲县');
new County(c, 'DBX','迭部县');
new County(c, 'MQX','玛曲县');
new County(c, 'LQX','碌曲县');
new County(c, 'XHX','夏河县');

c = new City(province, '甘肃','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','QH','青海');
c = new City(province, '青海','XN','西宁');
new County(c, 'BX','不限');
new County(c, 'CDQ','城东区');
new County(c, 'CZQ','城中区');
new County(c, 'CXQ','城西区');
new County(c, 'CBQ','城北区');
new County(c, 'DTX','大通县');
new County(c, 'HZX','湟中县');
new County(c, 'HYX','湟源县');

c = new City(province, '青海','HDDQ','海东地区');
new County(c, 'PAX','平安县');
new County(c, 'MHX','民和县');
new County(c, 'LDX','乐都县');
new County(c, 'HZX','互助县');
new County(c, 'HLX','化隆县');
new County(c, 'XHX','循化县');

c = new City(province, '青海','HBZ','海北州');
new County(c, 'MYX','门源县');
new County(c, 'QLX','祁连县');
new County(c, 'HYX','海晏县');
new County(c, 'GCX','刚察县');

c = new City(province, '青海','HUNZ','黄南州');
new County(c, 'TRX','同仁县');
new County(c, 'JZX','尖扎县');
new County(c, 'ZKX','泽库县');
new County(c, 'HNMGZ','河南蒙古族');

c = new City(province, '青海','HNZ','海南州');
new County(c, 'GHX','共和县');
new County(c, 'TDX','同德县');
new County(c, 'GDX','贵德县');
new County(c, 'XHX','兴海县');
new County(c, 'GNX','贵南县');

c = new City(province, '青海','GLZ','果洛州');
new County(c, 'MQX','玛沁县');
new County(c, 'BMX','班玛县');
new County(c, 'GDX','甘德县');
new County(c, 'DRX','达日县');
new County(c, 'JZX','久治县');
new County(c, 'MDX','玛多县');

c = new City(province, '青海','YSZ','玉树州');
new County(c, 'YSX','玉树县');
new County(c, 'ZADX','杂多县');
new County(c, 'CDX','称多县');
new County(c, 'ZDX','治多县');
new County(c, 'NQX','囊谦县');
new County(c, 'QMLX','曲麻莱县');

c = new City(province, '青海','HXZ','海西州');
new County(c, 'GEM','格尔木');
new County(c, 'DLH','德令哈');
new County(c, 'WLX','乌兰县');
new County(c, 'DLX','都兰县');
new County(c, 'TJX','天峻县');

c = new City(province, '青海','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','NX','宁夏', {type : 3, fullName : '宁夏回族自治区'});
c = new City(province, '宁夏','YC','银川');
new County(c, 'BX','不限');
new County(c, 'XQQ','兴庆区');
new County(c, 'XXQ','西夏区');
new County(c, 'JFQ','金凤区');
new County(c, 'YNX','永宁县');
new County(c, 'HLX','贺兰县');
new County(c, 'LW','灵武');

c = new City(province, '宁夏','SZS','石嘴山');
new County(c, 'BX','不限');
new County(c, 'DWKQ','大武口区');
new County(c, 'HNQ','惠农区');
new County(c, 'PLX','平罗县');

c = new City(province, '宁夏','WZ','吴忠');
new County(c, 'BX','不限');
new County(c, 'LTQ','利通区');
new County(c, 'YCX','盐池县');
new County(c, 'TXX','同心县');
new County(c, 'QTX','青铜峡');

c = new City(province, '宁夏','GY','固原');
new County(c, 'BX','不限');
new County(c, 'YZQ','原州区');
new County(c, 'XJX','西吉县');
new County(c, 'LDX','隆德县');
new County(c, 'JYX','泾源县');
new County(c, 'PYX','彭阳县');

c = new City(province, '宁夏','ZW','中卫');
new County(c, 'BX','不限');
new County(c, 'SPTQ','沙坡头区');
new County(c, 'ZNX','中宁县');
new County(c, 'HYX','海原县');

c = new City(province, '宁夏','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','XJ','新疆', {type : 3, fullName : '新疆维吾尔自治区'});
c = new City(province, '新疆','WLMQ','乌鲁木齐');
new County(c, 'BX','不限');
new County(c, 'TSQ','天山区');
new County(c, 'SYBKQ','沙依巴克区');
new County(c, 'XSQ','新市区');
new County(c, 'SMGQ','水磨沟区');
new County(c, 'TTHQ','头屯河区');
new County(c, 'DBCQ','达坂城区');
new County(c, 'DSQ','东山区');
new County(c, 'WLMQX','乌鲁木齐县');

c = new City(province, '新疆','KLMY','克拉玛依');
new County(c, 'BX','不限');
new County(c, 'DSZQ','独山子区');
new County(c, 'KLMYQ','克拉玛依区');
new County(c, 'BJTQ','白碱滩区');
new County(c, 'WEHQ','乌尔禾区');

c = new City(province, '新疆','TLFDQ','吐鲁番地区');
new County(c, 'TLF','吐鲁番');
new County(c, 'SSX','鄯善县');
new County(c, 'TKXX','托克逊县');

c = new City(province, '新疆','HMDQ','哈密地区');
new County(c, 'HM','哈密');
new County(c, 'BLK','巴里坤');
new County(c, 'YWX','伊吾县');

c = new City(province, '新疆','CJZ','昌吉州');
new County(c, 'CJ','昌吉');
new County(c, 'FK','阜康');
new County(c, 'MQ','米泉');
new County(c, 'HTBX','呼图壁县');
new County(c, 'MNSX','玛纳斯县');
new County(c, 'QTX','奇台县');
new County(c, 'JMSEX','吉木萨尔县');
new County(c, 'MLX','木垒县');

c = new City(province, '新疆','BETL','博尔塔拉');
new County(c, 'BL','博乐');
new County(c, 'JHX','精河县');
new County(c, 'WQX','温泉县');

c = new City(province, '新疆','BYGL','巴音郭楞');
new County(c, 'KEL','库尔勒');
new County(c, 'LTX','轮台县');
new County(c, 'WLX','尉犁县');
new County(c, 'RQX','若羌县');
new County(c, 'QMX','且末县');
new County(c, 'YQX','焉耆县');
new County(c, 'HJX','和静县');
new County(c, 'HSX','和硕县');
new County(c, 'BHX','博湖县');

c = new City(province, '新疆','AKSDQ','阿克苏地区');
new County(c, 'AKS','阿克苏');
new County(c, 'WSX','温宿县');
new County(c, 'KCX','库车县');
new County(c, 'SYX','沙雅县');
new County(c, 'XHX','新和县');
new County(c, 'BCX','拜城县');
new County(c, 'WUSX','乌什县');
new County(c, 'AWTX','阿瓦提县');
new County(c, 'KPX','柯坪县');

c = new City(province, '新疆','KZLS','克孜勒苏');
new County(c, 'ATS','阿图什');
new County(c, 'AKTX','阿克陶县');
new County(c, 'AHQX','阿合奇县');
new County(c, 'WQX','乌恰县');

c = new City(province, '新疆','KSDQ','喀什地区');
new County(c, 'KS','喀什');
new County(c, 'SFX','疏附县');
new County(c, 'SLX','疏勒县');
new County(c, 'YJSX','英吉沙县');
new County(c, 'ZPX','泽普县');
new County(c, 'SCX','莎车县');
new County(c, 'YCX','叶城县');
new County(c, 'MGTX','麦盖提县');
new County(c, 'YPHX','岳普湖县');
new County(c, 'JSX','伽师县');
new County(c, 'BCX','巴楚县');
new County(c, 'TSKEG','塔什库尔干');

c = new City(province, '新疆','HTDQ','和田地区');
new County(c, 'HT','和田');
new County(c, 'HTX','和田县');
new County(c, 'MYX','墨玉县');
new County(c, 'PSX','皮山县');
new County(c, 'LPX','洛浦县');
new County(c, 'CLX','策勒县');
new County(c, 'YTX','于田县');
new County(c, 'MFX','民丰县');

c = new City(province, '新疆','YLZ','伊犁州');
new County(c, 'YN','伊宁');
new County(c, 'KT','奎屯');
new County(c, 'YNX','伊宁县');
new County(c, 'CBCEX','察布查尔县');
new County(c, 'HCX','霍城县');
new County(c, 'GLX','巩留县');
new County(c, 'XYX','新源县');
new County(c, 'ZSX','昭苏县');
new County(c, 'TKSX','特克斯县');
new County(c, 'NLKX','尼勒克县');

c = new City(province, '新疆','TCDQ','塔城地区');
new County(c, 'TC','塔城');
new County(c, 'WS','乌苏');
new County(c, 'EMX','额敏县');
new County(c, 'SWX','沙湾县');
new County(c, 'TLX','托里县');
new County(c, 'YMX','裕民县');
new County(c, 'HBKSE','和布克赛尔');

c = new City(province, '新疆','ALTDQ','阿勒泰地区');
new County(c, 'ALT','阿勒泰');
new County(c, 'BEJX','布尔津县');
new County(c, 'FYX','富蕴县');
new County(c, 'FHX','福海县');
new County(c, 'HBHX','哈巴河县');
new County(c, 'QHX','青河县');
new County(c, 'JMNX','吉木乃县');

c = new City(province, '新疆','SXX','省辖县');
new County(c, 'SHZ','石河子');
new County(c, 'ALE','阿拉尔');
new County(c, 'TMSK','图木舒克');
new County(c, 'WJQ','五家渠');

c = new City(province, '新疆','BX','不限');
new County(c, 'BX','不限');


var province = new Province('CN','TW','台湾');
c = new City(province, '台湾','TW','台湾');
new County(c, 'TW','台湾');


var province = new Province('CN','XG','香港', {type : 2, fullName : '香港特别行政区'});
c = new City(province, '香港','XG','香港');
new County(c, 'XG','香港');


var province = new Province('CN','AM','澳门', {type : 2, fullName : '澳门特别行政区'});
c = new City(province, '澳门','AM','澳门');
new County(c, 'AM','澳门');