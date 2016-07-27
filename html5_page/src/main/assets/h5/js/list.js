/**
 * Created by Administrator on 2015/11/14.
 */
/*列表加载*/
var listLoad = function(){
    function listLoad () {
        this.canLoad = true
        this.canScroll = true;
        this.$page = null;
        this.other = false;
    };
    listLoad.prototype.init=function() {
        this.canLoad = true;
        this.canScroll = true;

        this.upDate();

        /* this.fnTab()*/
    };
    listLoad.prototype.upDate= function(){
        this.addData();
    };

    listLoad.prototype.addData=function(){
        var _this = this;
        this.JSON=this.JSON||JSON.parse(this.sendData.JSON)
        this.JSON.page || (this.JSON.page=0);
        this.JSON.pageRows || //(this.JSON.pageRows=Constant.PAGE_ROW);
        this.JSON.page++;
        var len = this.JSON.pageRows;
        var page = this.JSON.page;
        this.sendData.JSON = JSON.stringify(this.JSON);

//      fnBase.commonAjax(Constant.URL,this.sendData,function(data){
//
//          if(data.data.dataInfo === undefined||data.data.totalNum==0){
//
//              if(page==1){
//                  _this.$emport&&_this.$emport.show()
//              }
//
//              _this.canLoad = false;
//
//              return;
//          }else {
//
//              _this.$emport&&_this.$emport.hide();
//              if(parseInt(data.data.totalNum)<page*len){
//
//                  _this.canLoad = false;
//              };
//              if(data.data.dataInfo instanceof Array && data.data.dataInfo.length<len){
//
//                  _this.canLoad = false;
//              }
//              _this.addItem(data.data.dataInfo);
//          }
//          _this.other&&_this.other(data)
//
//
//      })
    };
    return listLoad;
}();
/*列表加载项结束*/