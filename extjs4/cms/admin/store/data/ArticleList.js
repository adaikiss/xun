Ext.define('CMS.data.ArticleList', {
        extend: 'Ext.data.Store',

        constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: false,
            model: 'CMS.model.Article',
            proxy: {
                type: 'ajax',
                directionParam: 'page.sort.dir',
                limitParam: 'page.size',
                simpleSortMode: true,
                sortParam: 'page.sort',
                url: 'admin/article/page.json',
                reader: {
                    type: 'json',
                    root: 'content',
                    totalProperty: 'totalElements'
                }
            },
            sorters: [
                {
                    direction: 'DESC',
                    property: 'date'
                }
            ]
        }, cfg)]);
    }
    });