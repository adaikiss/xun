Ext.define('CMS.data.Article', {
        extend: 'Ext.data.Store',

        constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: false,
            model: 'CMS.model.Article',
            proxy: {
                type: 'ajax',
                url: 'article/edit',
                reader: {
                    type: 'json',
                    root : 'data',
					successProperty : 'success'
                    
                }
            },
            listeners: {
				load: function (store,record,e) {
                    console.debug(store.first(), record);
					this.container.getForm().loadRecord(store.first());
				}
			}
        }, cfg)]);
    }
    });