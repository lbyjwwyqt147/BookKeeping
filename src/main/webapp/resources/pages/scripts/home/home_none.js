
/***
 * 首页
 * @type 
 */
var HomePage = {
    
	/**
	 * 选择一级菜单事件
	 * @param {} obj
	 */
	selectClick:function(obj){
		console.log(obj);
	  $(".nav-item").removeClass("active");
	  $(".nav-item").removeClass("open");
	  $(obj).addClass("active");
	   $(obj).addClass("open");
	},
	
	/**
	 * 打开相关菜单项页面
	 * @param {} url
	 * @param {} params
	 */
	openIframePage : function(url,params){
		console.log(this);
	   $("#content_iframe").attr("src",url);
	},
	
	/**
	 * 退出系统
	 */
	loginOut : function(){
	   var memberId = commonUtil.memberId;
	   $.ajax({
	     url : "bookkeeping/user/logOut",
	     data : {userCode : memberId},
	     type : "post",
	     dataType : "json",
	     success : function(data){
	        if(data.success){
	        	//清空登录人信息
	        	commonUtil.memberId = "";
	        	commonUtil.memberInfo("");
	        	layer.msg(data.message, {icon: 6,time:1000},function(index){ 
					   window.location.href="/BookKeeping";
			    });
	        	
	        }else{
	             layer.msg(data.message, {icon: 5});
	        }
	     },
	     error : function(){
	         layer.msg("网络链接错误.", {icon: 5});
	     }
	   });
	},
	
	/**
	 * 初始化页面内容
	 */
	init : function(){
	  var userArray = commonUtil.memberInfo("1");
	  
	  //底部版本信息内容
	  commonUtil.pageMsgUitl.setPageBottomMsg("page-footer-inner");
	  $("#loginOut").click(function(){
	     HomePage.loginOut(); 
	  });
	}
}

$(function(){
   HomePage.init();
});