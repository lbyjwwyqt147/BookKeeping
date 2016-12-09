<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户角色</title>
    
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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/assets/global/plugins/bootstrap-table/extensions/editable/bootstrap-editable.css">
    
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pages/css/tally_common.css">
    
    
  </head>
  
  <body class="page-content">
      <div class="row">
            <!-- 未分配角色列表开始 -->         
            <div class="col-md-5">
                <!-- BEGIN SAMPLE TABLE PORTLET-->
                <div class="portlet box green">
                   <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-list" style="font-size: 18px;"></i>未分配角色 </div>
                       <!--  <div class="actions">
                            <a href="javascript:Fn.addRow();" class="btn btn-default btn-sm">
                                <i class="fa fa-plus"></i> 添加 </a>
                            <a href="javascript:;" class="btn btn-default btn-sm">
                                <i class="fa fa-trash-o"></i> 删除 </a>
                        </div> -->
                    </div> 
                       <div class="portlet-body">
                            <div class="row">
		                         <div class="col-md-12">
		               
		                             <!-- 数据列表 -->
		                            <table id="noRoleGrid" class="table table-striped"></table>
		                            <input type="hidden" id="userId" value="${userId }">
		                             
		                         </div>
		                     </div>
                       </div>
                      
                </div>
                <!-- END SAMPLE TABLE PORTLET-->
            </div>
            <!-- 未分配角色列表块结束 -->
   
            <div class="col-md-2" style="margin-top: 10%">
               <div class="actions">
                            <a href="javascript:UserRole.addRow()" class="btn btn-default btn-sm">
                                <i class="fa fa-plus"></i> 添加 </a>
                            <a href="javascript:;" class="btn btn-default btn-sm">
                                <i class="fa fa-trash-o"></i> 删除 </a>
                        </div>
            </div>
   
   
        <!-- 已配角色列表开始 -->         
            <div class="col-md-5">
                <!-- BEGIN SAMPLE TABLE PORTLET-->
                <div class="portlet box green">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-list" style="font-size: 18px;"></i>已分配角色 </div>
                       <!--  <div class="actions">
                            <a href="javascript:Fn.addRow();" class="btn btn-default btn-sm">
                                <i class="fa fa-plus"></i> 添加 </a>
                            <a href="javascript:;" class="btn btn-default btn-sm">
                                <i class="fa fa-trash-o"></i> 删除 </a>
                        </div> -->
                    </div> 
                       <div class="portlet-body">
                            <div class="row">
		                         <div class="col-md-12">
		               
		                             <!-- 数据列表 -->
		                            <table id="yesRoleGrid" class="table table-striped"></table>
		                             
		                             
		                         </div>
		                     </div>
                       </div>
                      
                </div>
                <!-- END SAMPLE TABLE PORTLET-->
            </div>
            <!-- 已分配角色列表块结束 -->
        </div>
    
    <jsp:include page="/WEB-INF/pages/commons/bottom.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/scripts/datatable.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/bootstrap-table/extensions/editable/bootstrap-table-editable.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/bootstrap-table/extensions/editable/bootstrap-editable.js"></script>
    
    <script type="text/javascript" src="<%=basePath%>resources/assets/global/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pages/scripts/settings/role/user_role.js"></script>
  </body>
</html>
