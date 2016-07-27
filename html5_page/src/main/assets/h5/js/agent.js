var myScroll;
$(function(){
    agentSort.init();
    agentSort.fnEvent();
    agentSort.addData.$list.html("");
//  agentSort.addData.init();

    myScroll = new IScroll(agentSort.iscrollWrap, {
        scrollbars: true,
        mouseWheel: true,
        interactiveScrollbars: true,
        shrinkScrollbars: 'scale',
        fadeScrollbars: true,
        click:true
    });

//  myScroll.on('scrollEnd', function(){
//      if (this.maxScrollY<0&&this.y <= this.maxScrollY)  {
//          console.log(proSort.addData.canScroll+"+"+proSort.addData.canLoad)
//          if(proSort.addData.canScroll&&proSort.addData.canLoad){
//              agentSort.addData.addData();
//          }
//      }
//  });
    $('body').on('click',function(){
		$('.a_hide').addClass('hide');
	})

});
var agentSort = {//方法名
	init:function(){//初始化
        var _this = this;
        this.pageInfo = {
            "fenrun":{CODE:125,hasSort:true,type:undefined,cateId:"",searchName:undefined},
            "yeji":{CODE:124,hasSort:false,type:1,cateId:undefined,searchName:undefined},
            "guanli":{CODE:123,hasSort:false,type:undefined,cateId:undefined,searchName:undefined},
//          "leXin":{CODE:124,hasSort:false,type:2,cateId:undefined,searchName:undefined},
//          "pro-list":{CODE:126,hasSort:false,type:undefined,cateId:undefined,searchName:decodeURIComponent(fnBase.request("searchKey"))/*Storage.getData('searchKey')*/}
        };
        this.tabType="";
        this.CODE = this.pageInfo[this.pageKey].CODE;
        this.type= this.pageInfo[this.pageKey].type;
        this.cateId=this.pageInfo[this.pageKey].cateId;
        this.searchKey=this.pageInfo[this.pageKey].searchName;
        this.priceSort="";
//      this.$priceSort = this.$page.find('.sort-opt-list');
//      this.$priceArrow = this.$page.find('.price-sort .arrow');

		//构造函数原型链
        function newList(){}
        newList.prototype = new listLoad();
        newList.prototype.reload = function(){ //重新开始，初始化
            this.JSON.page = 0;
            this.$list.html("");
            this.init();
            setTimeout(function(){myScroll.refresh()},0)
        };
        if(this.pageKey==="fenrun"){
//          newList.prototype.other = function(data){
//              var str = "";
//              var oData = data.data.cate;
//              for(var i =0; i<oData.length;i++){
//                  str+="<li class='nav-item' data-cate-id='"+oData[i].cate_id+"'>"+oData[i].name+"</li>"
//              }
//              $('.sort-nav').html(str)
//          }
        };
		//增加条目
        newList.prototype.addItem = function(data){
            var oData = data;//获取数据
            if(oData==""||oData==null){
                return;
            };
            var str = "";
            for(var i =0; i<oData.length;i++){
//              var time = _this.pageKey==="teJia"?"<p class='time' style=''>结束时间："+fnBase.getTime(oData[i].etime)+"</p>":"";
                //dom字符串
                str+= " <li class='box-pro' data-pid='"+oData[i].pid+"'> <img src='"+oData[i].format_image+"' /> <div class='p-info'> " +
                    "<p class='p-name'>"+oData[i].name+"</p> " +
                    "<p class='p-bt'> <em class='fl'>¥<span class='p-price'>"+oData[i].format_price+"</span></em> " +
                    "<span class='num'> <em>"+oData[i].sell_amount+"</em>件已售</span> </p> "+time+"</div> </li>";
            }
            


            this.$list.append(str);
            setTimeout(function(){
                myScroll.refresh()
            },0);

        };
        this.addData = new newList();//构造函数
        this.addData.$scrollP= this.$page.find('.scroll-wrap');//iscroll插件
        this.addData.$list =this.pageKey;//$list就是这个分页的Pagekey
//      this.addData.$emport = this.$page.find('.emport');
        this.addData.canLoad = true;
        this.addData.canScroll = true;
        //json字符串，之后自定义
        this.addData.JSON ={"sortType":1,type:this.type,"sort":1,"cateId":this.cateId,"searchName":this.searchKey}
        //传输的字段json
        this.addData.sendData = {"CODE":this.CODE,JSON:JSON.stringify(this.addData.JSON)};
    },
    addData:function(){},
	fnEvent:function(){
		var _this = this;
		this.$page.on('click','.tit_nav',function(){
			$(this).addClass('active').siblings().removeClass('active');
//			_this.$priceSort.removeClass('show');
            //ajax
            _this.tabType = $(this).index()+1;//这些都是自定义的
            _this.addData.JSON.sortType = _this.tabType;//这些都是自定义的
            _this.addData.reload();//重新来过
		});
		this.$page.on('click','.lcUli',function(){
			$(this).addClass('active').siblings().removeClass('active');
			_this.tabType =3;
            _this.priceSort = $(this).index()+1;
            //ajax 
//          _this.addData.JSON.sort = _this.priceSort;
//          _this.addData.JSON.sortType = _this.tabType;
            _this.addData.reload();
		})
	}
}
