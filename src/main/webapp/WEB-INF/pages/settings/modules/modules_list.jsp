<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
response.setHeader("X-Frame-Options", "SAMEORIGIN");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>资源模块</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<jsp:include page="/WEB-INF/pages/commons/head.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/assets/global/plugins/bootstrap-table/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/assets/global/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pages/css/tally_common.css">
    

  </head>
  
  <body>
     <body class="page-content">
      <div class="row">
      
      <!-- 模块树开始 -->
             <div class="col-md-3">
                <!-- BEGIN SAMPLE TABLE PORTLET-->
                <div class="portlet box green">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-leaf" style="font-size: 18px;"></i>资源模块 </div>
   
                    </div>
                       <div class="portlet-body">
                            <div class="row">
		                         <div class="col-md-12">
		                               <!-- 树 -->
		               		           <div id="modulesTree" class="ztree"></div>
		               		           <input id="codeValue" value="1" type="hidden">
		                         </div>
		                     </div>
                       </div>
                      
                </div>
                <!-- END SAMPLE TABLE PORTLET-->
            </div>
            <!-- 模块树块结束 -->
      
            <!-- 资源列表开始 -->         
            <div class="col-md-9">
                <!-- BEGIN SAMPLE TABLE PORTLET-->
                <div class="portlet box green">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-list" style="font-size: 18px;"></i>资源列表 </div>
                        <div class="actions">
                            <a href="javascript:Modules.addRow();" class="btn btn-default btn-sm">
                                <i class="fa fa-plus"></i> 添加 </a>
                            <a href="javascript:;" class="btn btn-default btn-sm">
                                <i class="fa fa-trash-o"></i> 删除 </a>
                        </div>
                    </div>
                       <div class="portlet-body">
                            <div class="row">
		                         <div class="col-md-12">
		               
		                             <!-- 数据列表 -->
		                            <table id="modulesGrid" class="table table-striped"></table>
		                             
		                             
		                         </div>
		                     </div>
                       </div>
                      
                </div>
                <!-- END SAMPLE TABLE PORTLET-->
            </div>
            <!-- 模块列表块结束 -->
            
            
        </div>
    
    
    <jsp:include page="/WEB-INF/pages/commons/bottom.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/scripts/datatable.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/bootstrap-table/extensions/editable/bootstrap-table-editable.js"></script>
    
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/zTree_v3/js/jquery.ztree.core.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/zTree_v3/js/jquery.ztree.excheck.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/zTree_v3/js/zTreeUtil.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pages/scripts/settings/modules/modules_list.js"></script>
  </body>
</html>
