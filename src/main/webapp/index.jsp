<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="/WEB-INF/pages/commons/head.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/assets/pages/css/login.css">
 
  </head>
  
  <body class=" login">
        <!--  LOGO 图片开始 -->
        <%-- <div class="logo">
            <a href="index.html">
                <img src="<%=basePath %>resources/assets/pages/img/logo-big.png" alt="" /> </a>
        </div> --%>
        <!--  LOGO 图片结束 -->
        <!--  登录组件开始 -->
        <div class="content" style="margin-top: 8%;">
            <!-- 登录表单开始   j_spring_security_check-->
            <form class="login-form" action="" method="post">
                <h3 class="form-title font-green">系统登录</h3>
                <!-- 提示信息div -->
		        <div id="login-bootstrap_alerts" style="margin-top: 5px;"> </div>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">帐号</label>
                    <div class="input-icon">
                        <i class="fa fa-user" style="margin-top:14px;"></i>
                        <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="帐号" name="login_user" id="login_user"/> 
                    </div>
                </div>
                <div class="form-group">
                       <label class="control-label visible-ie8 visible-ie9">密码</label>
                       <div class="input-icon">
                          <i class="fa fa-lock fa-fw" style="margin-top: 15px;"></i>  
               		      <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="login_pwd" id="login_pwd"/> 
                       </div>
                </div>
                <div class="form-group" style="margin-bottom:0px;">
                    <label class="rememberme check mt-checkbox mt-checkbox-outline" style="margin-left: 0px;">
                        <input type="checkbox" name="remember" id="remember" value="15" />15天内免登录
                        <span></span>
                    </label>
                    <a href="javascript:;" id="forget-password" class="forget-password" style="margin-top:0px;">忘记密码?</a>
                </div>
                <div class="form-actions" style="padding-top: 0px;margin-top:0px;">
                    <button type="button" id="login-btn" class="btn blue uppercase" style="width:48%;">登录系统</button>
                    <button type="button" id="register-btn" class="btn green uppercase"  style="width:49%;">注册账户</button>
                </div>
            <!--     <div class="login-options">
                    <h4>其他登录方式</h4>
                    <ul class="social-icons">
                        <li>
                            <a class="social-icon-color facebook" data-original-title="facebook" href="javascript:;"></a>
                        </li>
                        <li>
                            <a class="social-icon-color twitter" data-original-title="Twitter" href="javascript:;"></a>
                        </li>
                        <li>
                            <a class="social-icon-color googleplus" data-original-title="Goole Plus" href="javascript:;"></a>
                        </li>
                        <li>
                            <a class="social-icon-color linkedin" data-original-title="Linkedin" href="javascript:;"></a>
                        </li>
                    </ul>
                </div> -->
              
            </form>
            <!-- 登录表单结束 -->
            
            <!-- 找回密码表单开始-->
            <form class="forget-form" action="" method="post">
                <h3 class="font-green">忘记密码 ?</h3>
                <p style="margin-bottom: 5px;margin-top: 10px;"> 输入您的用户名和邮件地址重新设置您的密码. </p>
                <div class="form-group">
                    <div class="input-icon">
                        <i class="fa fa-user" style="margin-top:14px;"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="帐号" name="forget_user" /> 
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-icon">
                        <i class="fa fa-envelope" style="margin-top:14px;"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="邮箱地址" name="forget_email" /> 
                    </div>
                </div>
                <div class="form-actions">
                    <button type="button" id="back-btn" class="btn green btn-outline"><i class="fa fa-arrow-circle-left" style="padding-right: 4px;"></i>登录</button>
                    <button type="submit" class="btn btn-success uppercase pull-right"><i class="fa fa-arrow-circle-right" style="padding-right: 4px;"></i>提交</button>
                </div>
            </form>
            <!-- 找回密码表单结束 -->
            
            <!-- 注册表单开始 -->
            <form class="register-form" action="" method="post">
                <h3 class="font-green">注册帐号</h3> 
                <!-- 提示信息div -->
		        <div id="register-bootstrap_alerts" style="margin-top: 5px;"> </div>
                <p class="hint"> 输入您的帐号信息: </p>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">昵称</label>
                    <div class="input-icon">
                        <i class="fa fa-male" style="margin-top:14px;"></i>
                        <input class="form-control placeholder-no-fix" type="text" placeholder="昵称" name="userNickname" /> 
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">帐号</label>
                    <div class="input-icon">
                        <i class="fa fa-user" style="margin-top:14px;"></i>
                        <input class="form-control placeholder-no-fix" type="text" placeholder="帐号" name="loginUser" /> 
                    </div>
                </div>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">密码</label>
                    <div class="input-icon">
                        <i class="fa fa-lock fa-fw" style="margin-top:15px;"></i>
                        <input class="form-control placeholder-no-fix" type="password" placeholder="密码" id="register_password" name="loginPwd" /> 
                    </div>
                </div>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">确认密码</label>
                    <div class="input-icon">
                        <i class="fa fa-lock fa-fw" style="margin-top:15px;"></i>
                        <input class="form-control placeholder-no-fix" type="password" placeholder="确认密码" name="affirmLoginPwd" /> 
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">邮箱</label>
                    <div class="input-icon">
                        <i class="fa fa-envelope" style="margin-top:14px;"></i>
                        <input class="form-control placeholder-no-fix" type="text" placeholder="邮箱" name="userEmail" /> 
                    </div>
                </div>
               <div class="form-group">
                   <label class="control-label visible-ie8 visible-ie9">验证码</label>
                   <div style="display: table;">   
                        <div class="input-icon">
                       		 <i class="fa fa-key" style="margin-top:15px;" id="fa_securityCode"></i>
                       		 <input type="hidden" value="add" name="task" >
                       		 <input type="hidden" value="80" name="registType" >
                       		 <input class="form-control placeholder-no-fix" type="text" placeholder="验证码" name="securityCode"  id="userSecurityCode"/> 
                        </div>

                        <span class="input-group-btn">
                             <button id="getUserSecurityCode" class="btn btn-success" type="button" style="height:43px;">
                             <i class="fa fa-hand-o-right" /></i> 获取验证码</button>
                        </span>
                        
                   </div>
                </div>
                <div class="form-group margin-top-20 ">
                    <label class="mt-checkbox mt-checkbox-outline" id="tkCheckbox">
                        <input type="checkbox" name="tnc" checked="checked" /> 我同意
                        <a href="javascript:;">服务条款 </a> &
                        <a href="javascript:;">隐私政策 </a>
                        <span></span>
                    </label>
                    <div id="register_tnc_error"> </div>
                </div>
                <div class="form-actions" style=" margin-top: 0px;  padding-top: 0px;">
                    <button type="button" id="register-back-btn" class="btn green btn-outline" ><i class="fa fa-arrow-circle-left" style="padding-right: 4px;"></i>已有帐号</button>
                    <button type="button" id="register-submit-btn" class="btn btn-success uppercase pull-right"><i class="fa fa-arrow-circle-right" style="padding-right: 4px;"></i>立即注册</button>
                </div>
            </form>
        </div>
     
     <div class="copyright"> 2016. </div>
        
    <jsp:include page="/WEB-INF/pages/commons/bottom.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath %>resources/assets/pages/scripts/login.js"></script>
  </body>
</html>
