var myScroll;
$(function() {
	agentSort.init();
	agentSort.fnEvent();
	//  agentSort.addData.$list.html("");                                                                                                                                     
	agentSort.addData.init();

	myScroll = new IScroll((agentSort.iscrollWrap + 0), {
		scrollbars: true, //滚动条支持                                                                                                                                          
		mouseWheel: true, //鼠标滑轮支持                                                                                                                                           
		interactiveScrollbars: true, //此属性可以让滚动条能拖动，用户可以与之交互。                                                                                                                                 
//		shrinkScrollbars: 'scale', //当在滚动区域外面滚动时滚动条是否可以收缩到较小的尺寸。                                                                                                                                  
//		fadeScrollbars: true, //不想使用滚动条淡入淡出方式时，需要设置此属性为false以便节省资源。                                                                                                                                   
		click: true, //应用程序响应click事件
		scrollX: true,
		scrollY: true
	});
	//取消分润规则弹框
	$('body').on('click', function() {
		$('.a_hide').addClass('hide');
	});
});
var agentSort = { //方法名                                                                                                                                                    
	init: function() { //初始化                                                                                                                                                 
		var _this = this;
		this.pageInfo = {
			"fenrun": {
				CODE: 125
//				hasSort: true,
//				type: undefined,
//				cateId: "",
//				searchName: undefined
			},
			"yeji": {
				CODE: 124
//				hasSort: false,
//				type: 1,
//				cateId: undefined,
//				searchName: undefined
			},
			"guanli": {
				CODE: 123
//				hasSort: false,
//				type: undefined,
//				cateId: undefined,
//				searchName: undefined
			}
		};
		this.tabType = "";
		this.CODE = this.pageInfo[this.pageKey].CODE;
//		this.type = this.pageInfo[this.pageKey].type;
//		this.cateId = this.pageInfo[this.pageKey].cateId;
//		this.searchKey = this.pageInfo[this.pageKey].searchName;
//		this.priceSort = "";
//		this.$priceSort = this.$page.find('.sort-opt-list');
//		this.$priceArrow = this.$page.find('.price-sort .arrow');
		this.allSwitch = true;

		//构造函数原型链                                                                                                                                                         
		function newList() {};
		newList.prototype = new listLoad();
		newList.prototype.reload = function() { //重新开始，初始化                                                                                                                 
			this.JSON.page = 0;
			this.$list.html("");
			this.init();
			setTimeout(function() {
				myScroll.refresh();
			}, 0)
		};
		if (this.pageKey === "fenrun") {
			newList.prototype.other = function(data) {
				var str = "";
				var oData = data.data.cate;
				for (var i = 0; i < oData.length; i++) {
					str += "<li class='nav-item' data-cate-id='" + oData[i].cate_id + "'>" + oData[i].name + "</li>"
				}
				$('.sort-nav').html(str)
			}
		};
		//增加条目                                                                                                                                                            
		newList.prototype.addItem = function(data) {
			var oData = data; //获取数据                                                                                                                                       
			if (oData == "" || oData == null) {
				return;
			};
			var str = "";
			for (var i = 0; i < oData.length; i++) {
				var time = _this.pageKey === "teJia" ? "<p class='time' style=''>结束时间：" + fnBase.getTime(oData[i].etime) + "</p>" : "";
				dom字符串
				str += " <li class='box-pro' data-pid='" + oData[i].pid + "'> <img src='" + oData[i].format_image + "' /> <div class='p-info'> " +
					"<p class='p-name'>" + oData[i].name + "</p> " +
					"<p class='p-bt'> <em class='fl'>¥<span class='p-price'>" + oData[i].format_price + "</span></em> " +
					"<span class='num'> <em>" + oData[i].sell_amount + "</em>件已售</span> </p> " + time + "</div> </li>";
			}

			this.$list.append(str);
			setTimeout(function() {
				myScroll.refresh();
			}, 0);

		};
		this.addData = new newList(); //构造函数                                                                                                                               
		this.addData.$scrollP = this.$page.find('.scroll-wrap'); //iscroll插件                                                                                                
		this.addData.$list = this.pageKey; //$list就是这个分页的Pagekey                                                                                                            
		this.addData.$emport = this.$page.find('.emport');
		this.addData.canLoad = true;
		this.addData.canScroll = true;
		//json字符串，之后自定义                                                                                                                                                   
		this.addData.JSON = {
				"sortType": 1,
				type: this.type,
				"sort": 1,
				"cateId": this.cateId,
				"searchName": this.searchKey
			}
			//传输的字段json                                                                                                                                                       
		this.addData.sendData = {
			"CODE": this.CODE,
			JSON: JSON.stringify(this.addData.JSON)
		};
	},
	addData: function() {},
	fnEvent: function() {
		var _this = this;
		this.$page.on('click', '.tit_nav', function() {
			$(this).addClass('active').siblings().removeClass('active');
			$('#listCon' + ($(this).index() + 1)).removeClass('hide').siblings('.listCon').addClass('hide');
			if ($(this).index() == 2) {
				$('.addStaff').removeClass('hide');
			} else {
				$('.addStaff').addClass('hide');
			}
			myScroll = new IScroll(agentSort.iscrollWrap + ($(this).index()), {
				click: true,
				scrollX: true,
				scrollY: true
			});
			myScroll.refresh();
			//			_this.$priceSort.removeClass('show');                                                                                                                         
			//ajax                                                                                                                                                        
			//          _this.tabType = $(this).index()+1;//这些都是自定义的                                                                                                                  
			//          _this.addData.JSON.sortType = _this.tabType;//这些都是自定义的                                                                                                        
			//          _this.addData.reload();//重新来过                                                                                                                                 
		});
		this.$page.on('click', '.lcUli', function() {
			$(this).addClass('active').siblings().removeClass('active');
			myScroll.refresh();
			//			_this.tabType =3;                                                                                                                                             
			//          _this.priceSort = $(this).index()+1;                                                                                                                          
			//ajax                                                                                                                                                        
			//          _this.addData.JSON.sort = _this.priceSort;                                                                                                                    
			//          _this.addData.JSON.sortType = _this.tabType;                                                                                                                  
			//          _this.addData.reload();                                                                                                                                       
		});
		this.$page.on('click', '.allselect', function() {
			if (agentSort.allSwitch) {
				$(".staffInp").attr("checked", true);
				agentSort.allSwitch = false;
			} else {
				$(".staffInp").attr("checked", false);
				agentSort.allSwitch = true;
			}
		});
		this.$page.on('click', '#sraffMove', function() {
			$(".staffInp:checked").parent().remove();
			myScroll.refresh();
		});
	}
}