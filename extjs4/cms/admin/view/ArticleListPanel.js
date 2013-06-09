/*
 * File: admin/view/ArticleListPanel.js
 *
 * This file was generated by Sencha Architect version 2.2.2.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.1.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.1.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('CMS.view.ArticleListPanel', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.articleListPanel',

    closable: true,
    title: 'My Grid Panel',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            columns: [
                {
                    xtype: 'numbercolumn',
                    width: 40,
                    align: 'right',
                    dataIndex: 'id',
                    text: '编号',
                    format: '0,000'
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'title',
                    text: '标题',
                    flex: 1
                },
                {
                    xtype: 'numbercolumn',
                    width: 50,
                    align: 'right',
                    dataIndex: 'visits',
                    text: '访问量',
                    flex: 1,
                    format: '0,000'
                },
                {
                    xtype: 'numbercolumn',
                    width: 40,
                    align: 'right',
                    dataIndex: 'weight',
                    text: '权重',
                    flex: 1,
                    format: '0,000'
                },
                {
                    xtype: 'datecolumn',
                    width: 150,
                    align: 'right',
                    dataIndex: 'date',
                    text: '创建时间',
                    flex: 1,
                    format: 'Y-m-d H:i:s'
                },
                {
                    xtype: 'gridcolumn',
                    width: 150,
                    align: 'right',
                    dataIndex: 'tpl',
                    text: '模板',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'status',
                    text: '状态'
                }
            ],
            selModel: Ext.create('Ext.selection.RowModel', {
                mode: 'SINGLE'
            }),
            listeners: {
                afterrender: {
                    fn: me.onGridpanelAfterRender,
                    scope: me
                }
            },
            dockedItems: [
                me.processMyPagingToolbar({
                    xtype: 'pagingtoolbar',
                    dock: 'bottom',
                    afterPageText: '共 {0}页',
                    beforePageText: '当前',
                    displayInfo: true,
                    displayMsg: '{0} - {1} 条, 共 {2} 条',
                    emptyMsg: '暂无数据',
                    firstText: '首页',
                    lastText: '尾页',
                    nextText: '下一页',
                    prevText: '上一页',
                    refreshText: '刷新',
                    items: [
                        {
                            xtype: 'tbseparator'
                        },
                        {
                            xtype: 'button',
                            handler: function(button, event) {
                                CMS.app.getController('Article').addArticle(me);
                            },
                            text: '新增',
                            tooltip: '新增文章'
                        },
                        {
                            xtype: 'tbseparator'
                        },
                        {
                            xtype: 'button',
                            handler: function(button, event) {
                                var selected = me.getSelectionModel().getLastSelected();
                                if(!selected){
                                    Ext.MessageBox.alert('提示', '请先选择一条记录!');
                                    return;
                                }
                                CMS.app.getController('Article').editArticle(selected.data.id, selected.data.title);
                            },
                            text: '修改',
                            tooltip: '修改文章'
                        },
                        {
                            xtype: 'tbseparator'
                        },
                        {
                            xtype: 'button',
                            handler: function(button, event) {
                                var selected = me.getSelectionModel().getLastSelected();
                                if(!selected){
                                    Ext.MessageBox.alert('提示', '请先选择一条记录!');
                                    return;
                                }
                                Ext.MessageBox.confirm('提示', '确定要删除[' + selected.data.title + ']?(<font color="red">无法恢复</font>)', function(btn){
                                    if(btn == 'yes'){
                                        CMS.app.getController('Article').removeArticle(selected.data.id, me);
                                    }
                                });
                            },
                            text: '删除',
                            tooltip: '删除文章'
                        }
                    ]
                })
            ]
        });

        me.processArticleListPanel(me);
        me.callParent(arguments);
    },

    processArticleListPanel: function(config) {
        return config;
    },

    processMyPagingToolbar: function(config) {
        this.store = config.store = Ext.create('CMS.data.ArticleList', {channelId : this.channelId});
        return config;
    },

    onGridpanelAfterRender: function(component, eOpts) {
        this.loadData();
    },

    loadData: function() {
        this.store.load({
            params : {id : this.channelId}
        });
    }

});