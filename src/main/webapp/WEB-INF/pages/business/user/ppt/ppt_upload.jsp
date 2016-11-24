<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>PPT转为图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <jsp:include page="/WEB-INF/pages/commons/head.jsp"></jsp:include>
	<link href="<%=basePath %>/resources/assets/global/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>/resources/assets/global/plugins/bootstrapfileinput/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />	
    <link href="<%=basePath %>/resources/assets/global/plugins/progress-bar-linear/css/styles.css" media="all" rel="stylesheet" type="text/css" />	
    <style type="text/css">
      .jq22-content{
         background:rgba(224, 220, 227, 0.8) none repeat scroll 0 0 !important;/*实现FF背景透明，文字不透明*/
         filter:Alpha(opacity=80); background:#fff;/*实现IE背景透明*/
      }
      
      .preview-pane {
	    display: inline-block;
	    width: 165px;
	    padding: 2;
	    border: 1px solid rgba(0,0,0,.4);
	    background:rgba(224, 220, 227, 0.8) none repeat scroll 0 0 !important;
	    -webkit-border-radius: 6px !important;
	    -moz-border-radius: 6px !important;
	    border-radius: 6px !important;
	    
	}
	
	.preview-pane .preview-container {
	    width: 159px;
	    height: 159px;
	    overflow: hidden;
	}
	
	.file-input-new{
	  width: 95%;
	}
	.file-input{
	  width: 95%;
	}
    </style>
    
  </head>
  
  <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white" style="background-color: white;">
     <div class="page-content">
                            <div class="col-md-12">
                              <!-- 上传文件表单开始 -->
                              <div class="portlet box green" style="margin-top: 20px;">
                                   <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-file-powerpoint-o" style="font-size: 20px;margin-top: 1px;"></i>上传PPT文件并转换为图片
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                     
                                        <!-- 提示信息div -->
		                                <div id="bootstrap_alerts_demo"> </div>
		                                
		                                <!-- 进度条 -->
		                                <div class="jq22-content" style="display: none">
			                                <div class="progress">
												  <b class="progress__bar">
												    <span class="progress__text">
												                上传进度: <em>0%</em>
												    </span>
												  </b>
										    </div>
                                        </div>
                                        
		                               <form action="" id="uploadPPTForm" enctype="multipart/form-data" method="post">
							               <div style="width: 90%;display: -webkit-box;">  
							                  <input id="fileInput" class="file-loading" type="file" name="file" data-show-preview="false" multiple data-show-upload="false"  >
									          <a href="javascript:;" id="uploadButton" class="btn btn-lg blue" style="height: 46px;margin-left: 10px;"> 
                                                 <i class="fa fa-upload"></i>开始上传
                                              </a> 
									       </div>       
                                       </form>
		                               
                                    </div>
                                </div>
                                <!-- 上传文件表单结束 -->
                            
                                <!-- 图片预览div开始 -->
                                <div class="portlet box green">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-picture-o" style="font-size: 20px;margin-top: 2px;"></i>图片预览
                                        </div>
                                    </div>
                                    <div class="portlet-body" id="iamgeList" >
                                          
                            
                                    </div>
                                
                                 </div>  
                                 <!-- 图片预览div结束 -->
                                        
                   </div>
                            
         </div>               

  
    
  
  
  <jsp:include page="/WEB-INF/pages/commons/bottom.jsp"></jsp:include>
  <script src="<%=basePath %>/resources/assets/global/plugins/jquery.form.js" type="text/javascript"></script>
 
  <!-- 附件上传js开始 -->
  <script src="<%=basePath %>/resources/assets/global/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
  <script src="<%=basePath %>/resources/assets/global/plugins/bootstrapfileinput/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
  <script src="<%=basePath %>/resources/assets/global/plugins/bootstrapfileinput/js/plugins/purify.min.js" type="text/javascript"></script>
  <script src="<%=basePath %>/resources/assets/global/plugins/bootstrapfileinput/js/plugins/sortable.min.js" type="text/javascript"></script>
  <script src="<%=basePath %>/resources/assets/global/plugins/bootstrapfileinput/js/fileinput.js" type="text/javascript"></script>
  <script src="<%=basePath %>/resources/assets/global/plugins/progress-bar-linear/js/modernizr.js" type="text/javascript"></script>
  <script src="<%=basePath %>/resources/assets/global/plugins/progress-bar-linear/js/stopExecutionOnTimeout.js?t=1" type="text/javascript"></script>
  
  <!-- 附件上传js结束 -->
  <script src="<%=basePath %>/resources/pages/scripts/business/user/ppt/user_upload_ppt.js" type="text/javascript"></script>
  </body>
</html>
