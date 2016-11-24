<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="/WEB-INF/pages/commons/head.jsp"></jsp:include>
    
    <script type="text/javascript">
        /**
         * 通过该方法与后台交互，确保推送时能找到指定用户
         * 和后台 DwrMessagePushController 中的 onPageLoad 名称  保持一致
         */
       /*  function onPageLoad(){  
           var userId = "${memberId}";  
           DwrMessagePush.onPageLoad(userId); 
        }  */
  
        /**
         * 显示推送信息
         * 函数名对应 后台 DwrMessagePushController 中的  script.appendCall("showMessage", autoMessage);
         * msg 推送的消息内容
         */
       /*  function showMessage(msg) {  
             layer.open({
				  title: '提示信息',
				 // offset: 'rb',
				  anim : 3, 
				  //shade: 0,
				  content: msg,
				  yes: function(index, layero){
				     //调用退出系统
				     commonUtil.logiOut(commonUtil.memberId);
                     layer.close(index); 
                  }
			   });  
            }  
     */
      
    
    </script>
    
    
  </head>
   <!-- 
       dwr.engine.setActiveReverseAjax(true)      启动该页面的ReverseAjax功能
       dwr.engine.setNotifyServerOnPageUnload(true,true)    这个就是当页面加载时，后台listen监听器去除失效的scriptSession，再创建新的scriptSession
       onPageLoad()    自定义的javascript函数
       dwr.engine.setErrorHandler(function(){})   这个方法 防止项目已经关闭，客户页面还未关闭，页面会谈Error的问题 
   -->
  <!--  <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" style="overflow: hidden" onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true,true);dwr.engine.setErrorHandler(function(){});">  -->  
      
 <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" style="overflow: hidden" >
     
        <div class="page-wrapper">
            <!-- 顶部内容开始 -->
            <div class="page-header navbar navbar-fixed-top">
                <!-- BEGIN HEADER INNER -->
                <div class="page-header-inner ">
                    <!-- LOGO 图标 -->
                    <!-- <div class="page-logo">
                        <a href="index.html">
                            <img src="../assets/layouts/layout/img/logo.png" alt="logo" class="logo-default" /> </a>
                        <div class="menu-toggler sidebar-toggler">
                            <span></span>
                        </div>
                    </div> -->
                    <!-- LOGO 图标结束 -->
                    <!-- 隐藏显示左边菜单栏 开始 -->
                    <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
                        <span></span>
                    </a>
                    <!-- 隐藏显示左边菜单栏 结束 -->
                    
                    <!-- 顶部栏中右边的小菜单项 开始 -->
                    <div class="top-menu">
                        <ul class="nav navbar-nav pull-right">
                            <!-- END TODO DROPDOWN -->
                            <!-- 用户头像菜单栏 开始 -->
                            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                            <li class="dropdown dropdown-user">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <img alt="" class="img-circle" id="user-headPhoto" src="" />
                                    <span class="username username-hide-on-mobile" id="userNikeName"></span>
                                    <i class="fa fa-angle-down"></i>
                                </a>
                               <!-- <ul class="dropdown-menu dropdown-menu-default">
                                   <!--  <li>
                                        <a href="page_user_profile_1.html">
                                            <i class="icon-user"></i> My Profile </a>
                                    </li>
                                    <li>
                                        <a href="app_calendar.html">
                                            <i class="icon-calendar"></i> My Calendar </a>
                                    </li>
                                    <li>
                                        <a href="app_inbox.html">
                                            <i class="icon-envelope-open"></i> My Inbox
                                            <span class="badge badge-danger"> 3 </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="app_todo.html">
                                            <i class="icon-rocket"></i> My Tasks
                                            <span class="badge badge-success"> 7 </span>
                                        </a>
                                    </li>
                                    <li class="divider"> </li>
                                     <li>
                                        <a href="page_user_lock_1.html">
                                            <i class="icon-lock"></i> 修改密码 </a>
                                    </li> 
                                    <li>
                                        <a href="javaScript:void(0)" id="loginOut">
                                            <i class="icon-logout"></i> 退出 </a>
                                    </li>
                                </ul>-->
                            </li>
                            <!-- 用户头像菜单栏 结束 -->
                            <!-- 用户头像旁边图标 -->
                            <li class="dropdown dropdown-quick-sidebar-toggler">
                                <a href="javascript:;" class="dropdown-toggle" id="loginOut">
                                    <i class="icon-logout"></i>
                                </a>
                            </li> 
                            <!-- END QUICK SIDEBAR TOGGLER -->
                        </ul>
                    </div>
                    <!-- END TOP NAVIGATION MENU -->
                </div>
                <!-- END HEADER INNER -->
            </div>
            <!-- 顶部内容开始 -->
            <!-- BEGIN HEADER & CONTENT DIVIDER -->
            <div class="clearfix"> </div>
            <!-- END HEADER & CONTENT DIVIDER -->
            <!-- BEGIN CONTAINER -->
            <div class="page-container">
                <!-- BEGIN SIDEBAR -->
                <div class="page-sidebar-wrapper">
                    <!-- BEGIN SIDEBAR -->
                    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                    <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                    <div class="page-sidebar navbar-collapse collapse">
                        <!-- 左边菜单内容开始 -->
                        <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
                        <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
                        <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
                        <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                        <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
                        <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                       <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; ">
                        <ul class="page-sidebar-menu  page-header-fixed  page-sidebar-menu-fixed" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200"  data-initialized="1" style="overflow: hidden; width: auto; ">
                            <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
                            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                            <li class="sidebar-toggler-wrapper hide">
                                <div class="sidebar-toggler">
                                    <span></span>
                                </div>
                            </li>
                            <!-- END SIDEBAR TOGGLER BUTTON -->
                            <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
                          
                            <!-- 第一级菜单项 -->
                            <li class="nav-item start active open">
                                <a href="javascript:;" class="nav-link nav-toggle">
                                    <i class="icon-home"></i>
                                    <span class="title">首页</span>
                                    <span class="selected"></span>
                                    <span class="arrow open"></span>
                                </a>
                                 <!-- 第二级菜单项 -->
                                <ul class="sub-menu">
                                    <li class="nav-item start active open">
                                        <a href="javaScript:HomePage.openIframePage('teacher/myUpfile/initListPage')" class="nav-link ">
                                            <i class="fa fa-file-powerpoint-o"></i>
                                            <span class="title">PPT管理</span>
                                            <span class="selected"></span>
                                        </a>
                                    </li>
                                     <li class="nav-item  ">
                                        <a href="javaScript:HomePage.openIframePage('teacher/myUpfile/init')" class="nav-link ">
                                           
                                            <i class="fa fa-cloud-upload"></i>
                                            <span class="title">上传PPT</span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                          <!--   <li class="heading">
                                <h3 class="uppercase">Features</h3>
                            </li> -->
                            <!-- <li class="nav-item  ">
                                <a href="javascript:;" class="nav-link nav-toggle">
                                    <i class="icon-settings"></i>
                                    <span class="title">系统设置</span>
                                    <span class="arrow"></span>
                                </a>
                                <ul class="sub-menu">
                                    <li class="nav-item  ">
                                        <a href="javaScript:HomePage.openIframePage('tally/role/initList')" class="nav-link ">
                                            <span class="title">角色</span>
                                        </a>
                                    </li>
                                    <li class="nav-item  ">
                                        <a href="javaScript:HomePage.openIframePage('tally/dict/initList')" class="nav-link ">
                                            <span class="title">业务字典</span>
                                        </a>
                                    </li>
                                    <li class="nav-item  ">
                                        <a href="javaScript:HomePage.openIframePage('teacher/myUpfile/init')" class="nav-link ">
                                            <span class="title">ppt转图片</span>
                                        </a>
                                    </li>
                                    
                                </ul>
                            </li> -->
                           
                        </ul>
                        <!-- END SIDEBAR MENU -->
                        <!-- END SIDEBAR MENU -->
                    </div>
                    </div>
                    <!-- 左边菜单DIV结束 -->
                </div>
                <!-- END SIDEBAR -->
                <!-- 页面内容开始 -->
                <div class="page-content-wrapper">
                    <!-- BEGIN CONTENT BODY -->
                    <div class="page-content">
                        <!-- BEGIN PAGE HEADER-->
                        <!-- 页面导航栏 开始 -->
                        <div class="page-bar">
                            <ul class="page-breadcrumb">
                                <li>
                                    <a href="javaScript:void(0)">首页</a>
                                    <i class="fa fa-angle-double-right"></i>
                                </li>
                                <li>
                                    <a href="javaScript:HomePage.openIframePage('teacher/myUpfile/initListPage')">PPT管理</a>
                                  <!--   <span >PPT</span> -->
                                </li>
                            </ul>
                           <!--  <div class="page-toolbar">
                                <div id="dashboard-report-range" class="pull-right tooltips btn btn-sm" data-container="body" data-placement="bottom" data-original-title="Change dashboard date range">
                                    <i class="icon-calendar"></i>&nbsp;
                                    <span class="thin uppercase hidden-xs"></span>&nbsp;
                                    <i class="fa fa-angle-down"></i>
                                </div>
                            </div> -->
                        </div>
                        <!-- 页面导航栏    结束 -->
                        
                       
                        
                        <div class="clearfix"></div>
                        
                        <div class="page-content-white">
                           <iframe id="content_iframe" src="teacher/myUpfile/initListPage" scrolling="auto" width="100%" height="88%" frameborder="0"></iframe>
                       
                        </div>
                       
                       
                       </div> 
                         
                       
                    <!-- END CONTENT BODY -->
                </div>
                <!-- 页面内容 结束 -->
                
                <!-- 顶部最右边图标 -->
               <!-- <a href="javascript:;" class="page-quick-sidebar-toggler"> 
                    <i class="icon-login"></i>
                </a> --> 
                
                
            </div>
            <!-- END CONTAINER -->
            <!-- 底部版本信息 开始 -->
            <!-- <div class="page-footer">
                <div class="page-footer-inner" > 2016 </div>
                <div class="scroll-to-top">
                    <i class="icon-arrow-up"></i>
                </div>
            </div> -->
            <!-- 底部版本信息 结束 -->
        </div>

    
    <jsp:include page="/WEB-INF/pages/commons/bottom.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath %>resources/pages/scripts/home/home_none.js"></script>
	<!--
    <script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script> 
    <script type="text/javascript" src="<%=basePath%>dwr/util.js"></script>
    <script type="text/javascript" src="<%=basePath%>dwr/interface/DwrMessagePush.js"></script>  
	-->
    
  </body>
</html>
