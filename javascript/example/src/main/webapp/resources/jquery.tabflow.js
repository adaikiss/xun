(function($) {
	$.tabflow = function(tabPanel, contentPanel, options){
		var tabflow = {
			interval : 3000,
			duration : 600,
			curClass : 'cur',
			_curClass : null,
			direction : 1,
			handDuration : 0,
			tabFocusHandler : function(){},
			tabBlurHandler : function(){},
			flow : function(direction, times){
				if(!times){
					times = 1;
				}
				times = times - 1;
				var obj = this;
				if(this.tabPanel.children().length < 2){
					//less than 2 elements, no flow!
					return;
				}
				var curTab = this.tabPanel.children(this._curClass);
				var curContent = this.contentPanel.children(this._curClass);
				if(curTab.length == 0){
					//no tab with class cur, use first element as cur
					curTab = this.tabPanel.children(':first');
					curContent = this.contentPanel.children(':first');
				}
				if(curTab.length == 0){
					//no element
					return;
				}
				curTab.addClass(this.curClass);
				curContent.addClass(this.curClass);
				var duration = this.hand?this.handDuration:this.duration;
				//1:top, 2:right, 3:down, 4:left
				switch(direction){
				case 1 :
					var next = curContent.next();
					if(next.length == 0){
						//no more element!
						return;
					}
					var height = curContent.outerHeight(true);
					this.contentPanel.animate({
						scrollTop : height
					}, duration, function(){
						curContent.appendTo(obj.contentPanel.scrollTop(0));
						next.addClass(obj.curClass);
						curContent.removeClass(obj.curClass);
						curTab.removeClass(obj.curClass);
						obj.tabBlurHandler(curTab, obj);
						var nextTab = curTab.next();
						if(nextTab.length == 0){
							nextTab = obj.tabPanel.children(':first');
						}
						nextTab.addClass(obj.curClass);
						obj.tabFocusHandler(nextTab, obj);
						if(times > 0){
							obj.flow(direction, times);
						}
					});
					break;
				case 2 :
					var prev = this.contentPanel.children(':last');
					if(prev.length == 0 || prev[0] == curContent[0]){
						//no more element!
						return;
					}
					var width = curContent.outerWidth(true);
					prev.prependTo(this.contentPanel.scrollLeft(width));
					this.contentPanel.animate({
						scrollLeft : 0
					}, duration, function(){
						prev.addClass(obj.curClass);
						curContent.removeClass(obj.curClass);
						curTab.removeClass(obj.curClass);
						obj.tabBlurHandler(curTab, obj);
						var prevTab = curTab.prev();
						if(prevTab.length == 0){
							prevTab = obj.tabPanel.children(':last');
						}
						prevTab.addClass(obj.curClass);
						obj.tabFocusHandler(prevTab, obj);
						if(times > 0){
							obj.flow(direction, times);
						}
					});
					break;
				case 3 :
					var prev = this.contentPanel.children(':last');
					if(prev.length == 0 || prev[0] == curContent[0]){
						//no more element!
						return;
					}
					var height = curContent.outerHeight(true);
					prev.prependTo(this.contentPanel.scrollTop(height));
					this.contentPanel.animate({
						scrollTop : 0
					}, duration, function(){
						prev.addClass(obj.curClass);
						curContent.removeClass(obj.curClass);
						curTab.removeClass(obj.curClass);
						obj.tabBlurHandler(curTab, obj);
						var prevTab = curTab.prev();
						if(prevTab.length == 0){
							prevTab = obj.tabPanel.children(':last');
						}
						prevTab.addClass(obj.curClass);
						obj.tabFocusHandler(prevTab, obj);
						if(times > 0){
							obj.flow(direction, times);
						}
					});
					break;
				case 4 :
					var next = curContent.next();
					if(next.length == 0){
						//no next el
						return;
					}
					var width = curContent.outerWidth(true);
					this.contentPanel.animate({
						scrollLeft : width
					}, duration, function(){
						curContent.appendTo(obj.contentPanel.scrollLeft(0));
						next.addClass(obj.curClass);
						curContent.removeClass(obj.curClass);
						curTab.removeClass(obj.curClass);
						obj.tabBlurHandler(curTab, obj);
						var nextTab = curTab.next();
						if(nextTab.length == 0){
							nextTab = obj.tabPanel.children(':first');
						}
						nextTab.addClass(obj.curClass);
						obj.tabFocusHandler(nextTab, obj);
						if(times > 0){
							obj.flow(direction, times);
						}
					});
					break;
				}
			},
			autoflow : function(direction){
				var obj = this;
				this.flow(direction, 1);
				window.setTimeout(function(){
					if(obj.hand){
						return;
					}
					obj.autoflow(direction);
				}, obj.interval);
			},
			flowTo : function(tab){
				var obj = this;
				var cur_index = 0, tab_index = 0;
				this.tabPanel.children().each(function(index){
					if($(this).is(obj._curClass)){
						cur_index = index;
					}else if(this == tab[0]){
						tab_index = index;
					}
				});
				if(cur_index == tab_index){
					return;
				}
				var direction,times;
				if(cur_index > tab_index){
					times = cur_index - tab_index;
					if(obj.direction == 1 || obj.direction == 3){
						direction = 3;
					}else{
						direction = 4;
					}
				}else{
					times = tab_index - cur_index;
					if(obj.direction == 1 || obj.direction == 3){
						direction = 1;
					}else{
						direction = 2;
					}
				}
				this.flow(direction, times);
			},
			init : function(){
				$.extend(this, options);
				this.tabPanel = tabPanel;
				this.contentPanel = contentPanel;
				this.tabPanel.css({'overflow' : 'hidden'});
				this.contentPanel.css({'overflow' : 'hidden'});
				this._curClass = '.' + this.curClass;
				var obj = this;
				this.tabPanel.children().each(function(){
					$(this).bind('click', function(){
						if(obj.hand || $(this).is(obj._curClass)){
							return;
						}
						obj.hand = true;
						obj.contentPanel.stop(true, true);
						obj.flowTo($(this));
						obj.hand = false;
					});
				});
				this.autoflow(this.direction);
			}
		};
		tabflow.init();
		return tabflow;
	};
})(jQuery);