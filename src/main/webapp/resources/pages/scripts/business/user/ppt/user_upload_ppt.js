/**
 * 上传PPT类
 * @type 
 */
var uplpadPPT = {
    
	 percent : 0,
	 speed : 200,
	 orange : 30,
	 yellow : 55, 
	 green : 85,
	 timer : null,
	 isSuccess : false,
	
	/**
	 * 获取文件后缀名
	 * @param {} fileName 文件名称
	 */
	getSuffix  : function(fileName){
		var index1 = fileName.lastIndexOf(".");   
        var index2 = fileName.length;  
        //后缀名 
        var postf = fileName.substring(index1,index2);
	    //var result =/^\.[\.]+/.exec(fileName);
        return postf;
	},
	
	/**
	 * 开始上传文件
	 */
	startUpload : function(){
		
	   var fileName = $("#fileInput").val();
	   var suffix = uplpadPPT.getSuffix(fileName);
	   if($.trim(suffix) != ".ppt" && $.trim(suffix) != ".pptx"){
	      uplpadPPT.alertMessage("请选择文件格式为ppt的文件.","warning","fa fa-warning");
	      return;
	   };
	   uplpadPPT.loadingTip();
	   $('#uploadButton').attr("disabled","disabled");
	   //请求后台上传
	   $("#uploadPPTForm").ajaxSubmit({
	       url : 'teacher/myUpfile/uploadPPT',
	       type : "post",
	       dataType : "json",
	       success : function(data,textStatus){
	       	
	         uplpadPPT.isSuccess = true;
	         if(data.success){
	         	uplpadPPT.percent = 100;
	         	$("#uploadPPTForm").resetForm();
	         	uplpadPPT.setImageList(data.list);
	         	uplpadPPT.alertMessage("ppt转换图片成功.","success","fa fa-check");
	         	
	         }else{
	            uplpadPPT.alertMessage("ppt转换图片失败.","danger","fa fa-warning");
	         }
	         uplpadPPT.unLoadingTip();
	         $('#uploadButton').removeAttr("disabled");
	       }
	   })
	},
	
	/**
	 * 设置图片预览
	 * @param {} list 图片路径集合
	 */
	setImageList : function(list){
	   var imageList = $("#iamgeList");
	   var iamgeListHtml = "";
	   $.each(list,function(i,v){
	   	if(v.attachSuffix != ".ppt" && v.attachSuffix != ".pptx"){
	   	   var imageHtml = ' <div class="preview-pane"><div class="preview-container"><a class="fancybox" href="'+v.ppFileUrl+'" data-fancybox-group="gallery" title=""><img src="'+v.ppFileUrl+'" alt="" width="159px" height="159px"/></a></div></div>';
	       iamgeListHtml += imageHtml;
	   	}
	   	   
	   });
	   $("#iamgeList").html(iamgeListHtml);
	   $('.fancybox').fancybox();
	},
	/**
	 * 提示信息 
	 * @param {} msg  提示信息
	 * @param {} type 提示类型
	 * @param {} icon 图标
	 */
	alertMessage : function(msg,type,icon){
	   App.alert({ 
	       container: "#bootstrap_alerts_demo",      //信息显示位置div 
	       type: type,                               //类型  success、danger、warning、info
	       message : msg,                            //显示的信息
	       closeInSeconds : 10,                      //10秒后自动关闭提示框
	       close: true,                              //显示关闭按钮
	       icon: icon                                //图标  
       });
	},
	
	loadingTip : function(){
		$(".jq22-content").show();
		
		setTimeout(function () {
		    $('.progress').addClass('progress--active');
		    uplpadPPT.updateProgress();
		}, 1000);
			
	      //App.blockUI({message: '文件正在转换中...。'});

          /*  window.setTimeout(function() {
                 App.stopPageLoading();
            }, 2000);*/
	},
	unLoadingTip : function(){
		uplpadPPT.isSuccess = false;
		//$(".jq22-content").hide();
		uplpadPPT.percent = 0;
	   // updateProgress();
	    uplpadPPT.resetColors();
	    $(".jq22-content").hide();
	   // App.unblockUI();
	},
	
	/**
	 * 设置进度条颜色变化
	 */
	resetColors : function(){
	   $('.progress__bar').removeClass('progress__bar--green');
	   $('.progress__bar').removeClass('progress__bar--yellow');
	   $('.progress__bar').removeClass('progress__bar--orange');
	   $('.progress__bar').removeClass('progress__bar--blue');
	   $('.progress').removeClass('progress--complete');
	   $('.progress').removeClass('progress--active');
	   
	},
	
	/**
	 * 更新进度
	 */
	updateProgress : function(){
	   uplpadPPT.timer = setTimeout(function () {
	        uplpadPPT.percent += Math.random() *  1.8;
	        uplpadPPT.percent = parseFloat(uplpadPPT.percent.toFixed(1));
	        $('.progress__text').find('em').text(uplpadPPT.percent + '%');
	        if (uplpadPPT.percent >= 100) {
	            uplpadPPT.percent = 100;
	            $('.progress').addClass('progress--complete');
	            $('.progress__bar').addClass('progress__bar--blue');
	            $('.progress__text').find('em').text('100'+'%');
	        }else if(!uplpadPPT.isSuccess && uplpadPPT.percent > 75){
	            $('.progress__bar').addClass('progress__bar--yellow');
	        }else {
	            if (uplpadPPT.percent >= uplpadPPT.green) {
	                $('.progress__bar').addClass('progress__bar--green');
	            } else if (uplpadPPT.percent >= uplpadPPT.yellow) {
	                $('.progress__bar').addClass('progress__bar--yellow');
	            } else if (uplpadPPT.percent >= uplpadPPT.orange) {
	                $('.progress__bar').addClass('progress__bar--orange');
	            }
	            uplpadPPT.speed = Math.floor(Math.random() * 300);
	            uplpadPPT.updateProgress();
	        }
	        
	        $('.progress__bar').css({ width: uplpadPPT.percent + '%' });
	    }, uplpadPPT.speed);
	},
	
	init : function(){
		//绑定上传按钮事件
		$('#uploadButton').click(function(){
			uplpadPPT.startUpload();
		});
		//文件上传配置
		$("#fileInput").fileinput({
		   mainClass: "input-group-lg"
		});
		
		$(".page-content").css({"min-height":$(window).height()-100});
	}
	
}

$(function(){
   uplpadPPT.init();  
   window.onresize = function () {
     $(".page-content").css({"min-height":$(window).height()-100});
   }
})