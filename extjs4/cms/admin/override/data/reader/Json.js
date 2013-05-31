Ext.define('CMS.override.data.reader.Json', {
    extend: 'Ext.data.reader.Json',
    alias: 'reader.channelTreeJsonReader',
    getResponseData: function(response) {
        var data, error;
 
        try {
            data = Ext.decode(response.responseText);
            var js = data;
            var json = [];
            for (var i = 0; i < js.length; i++) {
                json.push(this.readJson(js[i]));
            }
            var result =  this.readRecords(json);
            return result;
        } catch (ex) {
            error = new Ext.data.ResultSet({
                total  : 0,
                count  : 0,
                records: [],
                success: false,
                message: ex.message
            });

            this.fireEvent('exception', this, response, error);

            Ext.Logger.warn('Unable to parse the JSON returned by the server');

            return error;
        }
    },
    readJson : function (json) {
		var js = {};
		js.text = json.name;
		js.channelId = json.id;
		js.order = json.order;
        js.hasArticles = json.hasArticles;
		if (json.children) {
			js.children = [];
			for (var i = 0; i < json.children.length; i++) {
				js.children.push(this.readJson(json.children[i]));
			}
		} else {
			js.expanded = true;
			js.loaded = true;
		}
		return js;
	}
});