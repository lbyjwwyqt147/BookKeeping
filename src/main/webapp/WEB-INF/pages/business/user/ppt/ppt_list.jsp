<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ppt列表页面</title>
    
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
	  
	<style type="text/css">
	  td.bs-checkbox{
	
		    vertical-align: middle;
			
		}
		.table>tbody>tr>td{
		    vertical-align: middle;
		} 
		html { overflow-x:hidden; }
	</style>
    
  </head>
  
  
  <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white" style="background-color: white;">
    
    <div class="row" style="margin-top: 20px;">
         <!-- PPT列表 div 开始 -->
         <div class="col-md-12" >
             <div class="portlet box green" >
                 <div class="portlet-title">
                     <div class="caption">
                         <i class="fa fa-list"></i>
                         PPT列表
                     </div>
                    <div class="actions">
                        <a href="javascript:;" id="uploadPpta" class="btn btn-sm purple">
                            <i class="fa fa-cloud-upload"></i> 上传PPT 
                        </a>
                    </div>
                 </div>
                 <div class="portlet-body contentdiv">
                     <div class="row">
                         <div class="col-md-12">
               
                             <!-- ppt文件数据列表 -->
                            <table id="pptDataGrid" class="table table-striped"></table>
                             
                             
                         </div>
                     </div>
                 </div>
             </div>
         </div>
         <!-- ppt文件数据列表 div 结束 -->
     
    </div>
        
    
    
    <jsp:include page="/WEB-INF/pages/commons/bottom.jsp"></jsp:include>    
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/scripts/datatable.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pages/scripts/business/user/ppt/ppt_list.js"></script>
    
  </body>
</html>
