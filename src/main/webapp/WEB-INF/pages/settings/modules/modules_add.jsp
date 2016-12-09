<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增资源模块</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="/WEB-INF/pages/commons/head.jsp"></jsp:include>
    <style type="text/css">
       .form-group{
         margin: 0;
       }
    </style>
     
  </head>
  
  <body class="page-content">
    <div class="row">
            <div class="col-md-12">
                    <div class="portlet-body">
                        <!-- BEGIN FORM-->
                        <form action="" id="module-form" class="form-horizontal">
                            <div class="form-body">
                                <div class="alert alert-danger display-hide">
                                    <button class="close" data-close="alert"></button> You have some form errors. Please check below. </div>
                                <div class="alert alert-success display-hide">
                                    <button class="close" data-close="alert"></button> Your form validation is successful! </div>
                                <div class="form-group  margin-top-20">
                                    <label class="control-label col-md-3">所属上级
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-3">
                                        <div class="input-icon right">
                                            <i class="fa"></i>
                                           <!--  <input type="text" class="form-control" name="modulePidText" id="modulePidText" />  -->
                                            <input type="text" value="${pid }" readonly="readonly" class="form-control" name="modulePid" /> </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">模块名称
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-3">
                                        <div class="input-icon right">
                                            <i class="fa"></i>
                                            <input type="text" class="form-control" name="moduleName" maxlength="25" /> </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">序号
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-3">
                                        <div class="input-icon right">
                                            <i class="fa"></i>
                                            <input type="text" class="form-control" name="nodeLevel" maxlength="4"/> 
                                        </div>    
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">url
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-3">
                                        <div class="input-icon right">
                                            <i class="fa"></i>
                                            <input type="text" class="form-control" name="moduleUrl" maxlength="150"/> </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">图标1
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-3">
                                        <div class="input-icon right">
                                            <i class="fa"></i>
                                            <input type="text"  class="form-control" name="moduleIconOne" maxlength="15"/> </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">图标2
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-3">
                                        <div class="input-icon right">
                                            <i class="fa"></i>
                                            <input type="text" class="form-control" name="moduleIconTwo" maxlength="15"/> </div>
                                       
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label class="control-label col-md-3">描述

                                    </label>
                                    <div class="col-md-3">
                                        <div class="input-icon right">
                                            <i class="fa"></i>
                                            <textarea rows="2" cols="2" class="form-control" name="moduleDescription" maxlength="100"></textarea>
                                            <input type="hidden" name="task" value="add">
                                </div>
                                </div>
                                
                            </div>
                            <div class="form-actions">
                                <div class="row">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button type="button" id="moduleFormSaveBtn" class="btn green">保存</button>
                                        <button type="button" id="moduleFormResetBtn" class="btn default">取消</button>
                                    </div>
                                </div>
                            </div>
                      
                       
                    </div>
                      </form>
                       <!-- END FORM-->
                </div>
            </div>         
    </div>
    <jsp:include page="/WEB-INF/pages/commons/bottom.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath %>resources/pages/scripts/settings/modules/modules_add.js"></script>
  </body>
</html>
