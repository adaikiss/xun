Xun.view = {
	init : function() {
		Ext.QuickTips.init();
		Ext.getBody().on("contextmenu", Ext.emptyFn, null, {preventDefault: true}); 
		Ext.MessageBox.msgButtons['ok'].text = Ext.MessageBox.buttonText.ok = '确定';
		Ext.MessageBox.msgButtons['cancel'].text = Ext.MessageBox.buttonText.cancel = '取消';
		Ext.MessageBox.msgButtons['yes'].text = Ext.MessageBox.buttonText.yes = '是';
		Ext.MessageBox.msgButtons['no'].text = Ext.MessageBox.buttonText.no = '否';
		Ext.get('header').show();

		Xun.view.headerPanel = Ext.create('Ext.panel.Panel', {
			contentEl : 'header',
			region : 'north',
			margins : '0 0 0 0',
			border : false,
			autoHeight : true
		});

		Xun.view.tabRefreshBtn = Ext.create('Ext.button.Button', {
			width : 20,
			height : 20,
			margin : '0 2 0 0',
			iconCls : 'tab-refresh-btn',
			xtype : 'button',
			tooltip : '刷新',
			handler : function(btn, e) {
				btn.up('tabpanel').getActiveTab().loader.load();
			}
		});
		Xun.view.mainTabPanel = Ext.create('Xun.view.MainTabPanel', {
			id : 'st-main-tab-panel',
			region : 'center',
			margins : '0 5 5 0',
			deferredRender : false,
			resizeTabs : true,
			enableTabScroll : true,
			minTabWidth : 140,
			autoScroll : false, // default
			defaults : {
				autoScroll : false,
				closable : false
			},
			layoutOnTabChange : true,
			// border: false,
			// bodyBorder: true,
			tabBar : {
				items : [ {
					xtype : 'tbfill'
				}, Xun.view.tabRefreshBtn ]
			},
			listeners : {
				tabchange : function(oldTab, newTab) {
					if (newTab.loader) {
						Xun.view.tabRefreshBtn.enable();
					} else {
						Xun.view.tabRefreshBtn.disable();
					}
				}
			}
		});

		var viewport = Ext.create('Ext.container.Viewport', {
			layout : 'border',
			renderTo : Ext.getBody(),
			items : [ Xun.view.headerPanel, Xun.view.mainTabPanel ]
		});

		Xun.view.viewport = viewport;

		Xun.view.mainTabPanel.addOrShowTab('welcome', 'Xun.view.MainPanel', {
			title : 'Welcome',
			loader : {
				url : 'welcome.html',
				contentType : 'html',
				autoLoad : true,
				loadMask : true
			}
		}, true);

		if(Xun.view.template){
			Xun.view.templateTreePanel = Ext.create('Xun.view.TemplateTreePanel', {
				id : 'tpl_tree',
				title : '模板列表'
			});
			Xun.view.templateEditorTabPanel = Ext.create(
					'Xun.view.TemplateEditorTabPanel', {
						id : 'tpl_editor_tab_panel'
					});
			Xun.view.mainTabPanel.addOrShowTab('template_manager',
					'Ext.panel.Panel', {
				title : '模板管理',
				layout : 'border',
				listeners : {},
				items : [ Xun.view.templateTreePanel,
				          Xun.view.templateEditorTabPanel ]
			});
		}

		if(Xun.view.channel){
			Xun.view.channelTreePanel = Ext.create('Xun.view.ChannelTreePanel', {
				id : 'channel_tree',
				title : '栏目列表'
			});
			Xun.view.channelEditorTabPanel = Ext.create(
					'Xun.view.ChannelEditorTabPanel', {
						id : 'channel_editor_tab_panel'
					});
			Xun.view.mainTabPanel.addOrShowTab('channel_manager',
					'Ext.panel.Panel', {
				title : '栏目管理',
				layout : 'border',
				listeners : {},
				items : [ Xun.view.channelTreePanel,
				          Xun.view.channelEditorTabPanel ]
			});
		}

		if(Xun.view.article){
			Xun.view.articleTreePanel = Ext.create('Xun.view.ArticleTreePanel', {
				id : 'article_tree',
				title : '文章列表'
			});
			Xun.view.articleEditorTabPanel = Ext.create(
					'Xun.view.ArticleEditorTabPanel', {
						id : 'article_editor_tab_panel'
					});
			Xun.view.mainTabPanel.addOrShowTab('article_manager',
					'Ext.panel.Panel', {
				title : '文章管理',
				layout : 'border',
				listeners : {},
				items : [ Xun.view.articleTreePanel,
				          Xun.view.articleEditorTabPanel ]
			});
		}

		if(Xun.view.resource){
			Xun.view.resourceTreePanel = Ext.create('Xun.view.ResourceTreePanel', {
				id : 'resource_tree',
				title : '资源列表'
			});
			Xun.view.resourceShowTabPanel = Ext.create(
					'Xun.view.ResourceShowTabPanel', {
						id : 'resource_show_tab_panel'
					});
			Xun.view.mainTabPanel.addOrShowTab('resource_manager',
					'Ext.panel.Panel', {
				title : '资源管理',
				layout : 'border',
				listeners : {},
				items : [ Xun.view.resourceTreePanel,
				          Xun.view.resourceShowTabPanel ]
			});
		}

		if(Xun.view.security){
			Xun.view.securityNavPanel = Ext.create('Xun.view.SecurityNavPanel', {
				title : '安全管理'
			});
			Xun.view.securityTabPanel = Ext.create(
					'Xun.view.SecurityTabPanel', {
					});
			Xun.view.mainTabPanel.addOrShowTab('security_manager',
					'Ext.panel.Panel', {
				title : '安全管理',
				layout : 'border',
				listeners : {},
				items : [ Xun.view.securityNavPanel,
				          Xun.view.securityTabPanel ]
			});
		}

		if(Xun.view.punch){
			Xun.view.punchPanel = Xun.view.mainTabPanel.addOrShowTab('punch_manager',
					'Xun.view.PunchPanel', {
				title : '打卡管理'
			});
		}

		if(Xun.view.order){
			Xun.view.orderPanel = Xun.view.mainTabPanel.addOrShowTab('order_manager',
					'Xun.view.OrderPanel', {
				title : '预约管理'
			});
		}

		if(Xun.view.log){
			Xun.view.logPanel = Xun.view.mainTabPanel.addOrShowTab('log_manager',
					'Xun.view.LogPanel', {
				title : '日志管理'
			});
		}
	}
};

Ext.define('Xun.view.MainTabPanel', {
	extend : 'Ext.tab.Panel',
	addOrShowTab : function(id, panelClass, panelClassParams, show) {
		var panelClassParams = panelClassParams || {};
		var tab = this.getComponent(id);
		if (!tab) {
			tab = Ext.create(panelClass, Ext.apply({
				id : id
			}, panelClassParams));
			this.add(tab);
		}
		if (show) {
			this.setActiveTab(tab);
		}
		return tab;
	},
	initComponent : function() {

		this.callParent();
	}
});

Ext.define('Xun.view.MainPanel', {
	extend : 'Ext.panel.Panel',
	loaded : false,
	listeners : {
		activate : function(tab) {
			if (!this.loaded) {
				tab.loader.load();
				this.loaded = true;
			}
		}
	},
	initComponent : function() {
		this.callParent();
	}
});
