var Login = function() {
	
	/**
	 * 用户登录
	 */
    var handleLogin = {
         loginUser : "",
         loginPwd : "",
         securityCode : "",
         registType : "10",
    	 /**
    	  * 验证登录表单
    	  */
         validateLoginForm : function(){
	         //获取登录用户名
	    	handleLogin.loginUser = $("#login_user").val();
	    	//获取密码
	    	handleLogin.loginPwd = $("#login_pwd").val();
	    	//是否验证成功
	    	var isValidate = true;
	    	if($.trim(handleLogin.loginUser) == "" && $.trim(handleLogin.loginPwd) != ""){
	    	   handleLogin.alertMessage("#login-bootstrap_alerts","请输入帐号.","danger","",20);
	    	   $("#login_user").focus();
	    	   isValidate = false;
	    	}else if($.trim(handleLogin.loginPwd) == "" && $.trim(handleLogin.loginUser) != "" ){
	    		isValidate = false;
	    		$("#login_pwd").focus();
	    		handleLogin.alertMessage("#login-bootstrap_alerts","请输入密码.","danger","",20);
	    	}else if($.trim(handleLogin.loginUser) == "" && $.trim(handleLogin.loginPwd) == ""){
	    		isValidate = false;
	    		$("#login_user").focus();
	    		handleLogin.alertMessage("#login-bootstrap_alerts","请输入登录帐号和密码.","danger","",20);
	    	}else{
	    	   $('.custom-alerts').remove();
	    	}
	    	return isValidate;
         },
         
         /**
          * 用户登录事件
          */
         userLogin : function(){
            //判断是否表单验证成功,进行登录操作
         	if(handleLogin.validateLoginForm()){
         	    $.ajax({
         	      url : "bookkeeping/user/userLogin",
         	      data :{
         	            'login_user' : handleLogin.loginUser,
         	            'login_pwd' : handleLogin.loginPwd,
         	            'securityCode' : handleLogin.securityCode

         	      },
         	      dataType : "json",
         	      type : "post",
         	      success : function(data, textStatus){
         	         if(data.success){
         	         	  jQuery('.login-form')[0].reset();
         	         	  
         	         	  handleLogin.alertMessage("#login-bootstrap_alerts","登录成功.","success","",5);
         	               $('#login-bootstrap_alerts').bind('closed.bs.alert', function () {
							  
					       });
					       //跳转止首页
					       window.location.href="bookkeeping/home/initHome";
					       
         	         }else{
         	           handleLogin.alertMessage("#login-bootstrap_alerts",data.message,"danger","",20);
         	         }
         	      },
         	      error : function(){
         	          handleLogin.alertMessage("#login-bootstrap_alerts","网络链接失败.","warning","",20);
         	      }

         	   });
         	}
         
         },
         
         /**
          * 隐藏登录验证提示信息
          */
         loginErrorMessageHide : function(){
            $('.custom-alerts').remove();
         },
         /**
		  * 提示信息 
		  * @param {} msg  提示信息
		  * @param {} type 提示类型
		  * @param {} icon 图标
		  */
		 alertMessage : function(container,msg,type,icon,second){
		    App.alert({ 
		        container: container,                    //信息显示位置div 
		        type: type,                              //类型  success、danger、warning、info
		        message : msg,                           //显示的信息
		        closeInSeconds : second != ""  ? second : 5,                      //5秒后自动关闭提示框
		        close: true,                             //显示关闭按钮
		        icon: icon                               //图标  
	        });
		 },
         init : function (){
         	  $("#login_user").focus();
         	
	          $('.login-form input').keypress(function(e) {
	            if (e.which == 13) {
	                handleLogin.userLogin();
	                return false;
	            }
	          });
	          
             
	          /**
	           * 登录点击事件
	           */
	         $("#login-btn").click(function(e){
	             handleLogin.userLogin();
	         }) 
	         //底部版本信息内容
	         commonUtil.pageMsgUitl.setPageBottomMsg("copyright");
         }
         
    };
    
    /**
     * 找回密码
     */
    var handleForgetPassword = function() {
        $('.forget-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
            	 forget_user: {
                    required: true
                },
                forget_email: {
                    required: true,
                    email: true
                }
            },

            messages: {
            	forget_user: {
                    required: "请输入要找回密码的用户名."
                },
                forget_email: {
                    required: "请输入注册时的邮箱地址."
                }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit   

            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function(form) {
                form.submit();
            }
        });

        $('.forget-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.forget-form').validate().form()) {
                    $('.forget-form').submit();
                }
                return false;
            }
        });
        
        /**
         * 点击“忘记密码”事件
         */
        jQuery('#forget-password').click(function() {
        	//隐藏登录验证提示信息
        	handleLogin.loginErrorMessageHide();
        	//隐藏登录页面
            jQuery('.login-form').hide();
            //显示找回密码页面
            jQuery('.forget-form').show();
            
        });

        /**
         * 找回密码页面中点击"上一步"事件
         */
        jQuery('#back-btn').click(function() {
        	//登录页面显示
            jQuery('.login-form').show();
            //找回密码页面隐藏
            jQuery('.forget-form').hide();
            //将找回密码页面中的form表单数据重置
            jQuery('.forget-form')[0].reset();
            //重置找回密码form表单验证为初始状态
            jQuery('.has-error .input-icon>i').css({"color":"#ccc"});
            jQuery('.forget-form').validate().resetForm();
        });

    };

    /**
     * 注册
     */
    var handleRegister = function() {

        $('.register-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                userNickname: {
                    required: true,
                    rangelength:[2,10],
                    userName:true
                },
                userEmail: {
                    required: true,
                    email: true,
                    rangelength:[4,30],
                    remote:{
			            url: "bookkeeping/user/checkAccounts",
			            type: "post",
			            dataType: 'json',
				        data: {
				            'userEmail': function(){return $.trim($('input[name="userEmail"]').val());}
		                }
		           }
                },
                loginUser: {
                    required: true,
                    rangelength:[4,20],
                    loginAccount:true,
                    remote:{
			            url: "bookkeeping/user/checkAccounts",
			            type: "post",
			            dataType: 'json',
				        data: {
				            'loginUser': function(){return $.trim($('input[name="loginUser"]').val());}
		                }
		           }
                },
                loginPwd: {
                    required: true,
                    rangelength:[6,16]
                },
                affirmLoginPwd: {
                    equalTo: "#register_password"
                },
                securityCode: {
                    required: true
                },
                tnc: {
                    required: true
                }
            },

            messages: { // custom messages for radio buttons and checkboxes
                tnc: {
                    required: "请同意服务条款."
                },
               nickname: {
                    required: "请填写长度在2到10之间的昵称.",
                    rangelength:"请填写长度在2到10之间的昵称."
                },
                email: {
                    required: "请填写电子邮箱，邮箱用于找回密码.",
                    rangelength:"请填写长度在4到20之间的邮箱地址.",
                    remote:"邮箱已经被注册."
                    
                },
                userName: {
                    required: "请填写帐号,帐号由字母、数字、下划线组成.",
                    rangelength:"请填写长度在4到20之间的帐号.",
                    remote : "帐号已经被注册."
                },
                password: {
                    required: "请填写登录密码.",
                    rangelength:"请填写长度在6到16之间的密码."
                },
                affirmLoginPwd: {
                    equalTo: "两次密码输入不一致."
                },
                securityCode: {
                    required: "请填写验证码."
                }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit   

            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
               
                //验证码错误时控制“获取验证码”按钮的位置    
                if($("#userSecurityCode-error") != null  && $("#userSecurityCode").attr("aria-invalid")== true ){
                    $("#getUserSecurityCode").css({"margin-top":"-29px"});
                }else if($("#userSecurityCode-error") != null && $("#userSecurityCode").attr("aria-required") == true){
                	$("#getUserSecurityCode").css({"margin-top":"0px"});
                }else if(typeof($("#userSecurityCode").attr("aria-invalid"))=="undefined") {
                	$("#getUserSecurityCode").css({"margin-top":"-29px"});
                }else{
                    $("#getUserSecurityCode").css({"margin-top":"0px"});
                }    
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
                //验证码正确时控制“获取验证码”按钮的位置    
                if(label.attr("id") == "userSecurityCode-error"){
                	$("#getUserSecurityCode").css({"margin-top":"0px"});
                }
            },

            errorPlacement: function(error, element) {
                if (element.attr("name") == "tnc") { // insert checkbox errors after the container                  
                    error.insertAfter($('#register_tnc_error'));
                } else if (element.closest('.input-icon').size() === 1) {
                    error.insertAfter(element.closest('.input-icon'));
                }else {
                    error.insertAfter(element);
                }
            },

            submitHandler: function(form) {
                
            }
        });

        /**
         * 提交注册表单
         */
        function registerFormAjaxSubmit(){
        	commonUtil.inputTrim();
        	$("register-submit-btn").attr("disabled","disabled");
            $.ajax({
                  url : "bookkeeping/user/saveUser",
         	      data :$(".register-form").serialize(),
         	      dataType : "json",
         	      type : "post",
         	      success : function(data, textStatus){
         	      	 $("register-submit-btn").removeAttr("disabled");
         	         if(data.success){
         	         	//重置注册页面form
                        jQuery('.register-form')[0].reset();
         	            handleLogin.alertMessage("#register-bootstrap_alerts","注册帐号成功.","success","",10);
         	            
         	        /*    $('#register-bootstrap_alerts').on('closed.bs.alert', function () {
         	            	 alert("sdf");
							 $("#register-back-btn").trigger("click");	
					    });*/
         	            $("#register-back-btn").trigger("click");	
         	         }else{
         	         	handleLogin.alertMessage("#register-bootstrap_alerts","注册帐号失败.","danger","",10);
         	         }
         	      },
         	      error : function(){
         	      	   $("register-submit-btn").removeAttr("disabled");
         	           handleLogin.alertMessage("#register-bootstrap_alerts","网络链接失败.","warning","",10);
         	      }
            });
        }
        
        $('.register-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.register-form').validate().form()) {
                    $('.register-form').submit();
                }
                return false;
            }
        });
        
        

        /**
         * 点击"注册"按钮事件
         */
        jQuery('#register-btn').click(function() {
        	//隐藏登录验证提示信息
        	handleLogin.loginErrorMessageHide();
        	//登录页面隐藏
            jQuery('.login-form').hide();
            $(".content").css({"margin-top":"3%"});
            //注册页面显示
            jQuery('.register-form').show();
            
        });

        /**
         * 注册页面中"上一步"事件
         */
        jQuery('#register-back-btn').click(function() {
        	$(".content").css({"margin-top":"8%"});
        	//登录页面显示
            jQuery('.login-form').show();
            //注册页面隐藏
            jQuery('.register-form').hide();
            //重置注册页面form
            jQuery('.register-form')[0].reset();
            //重置注册form表单验证为初始状态
            jQuery('.has-error .input-icon>i').css({"color":"#ccc"});
            jQuery('.register-form').validate().resetForm();
            $("#getUserSecurityCode").css({"margin-top":"0px"});
            $('.custom-alerts').remove();
        });
        
        /**
         * 注册页事件
         */
        jQuery('#register-submit-btn').click(function() {
        	if(jQuery('.register-form').validate().form()){
        		registerFormAjaxSubmit();
        	}
        });
        
    };

    return {
        //main function to initiate the module
        init: function() {

            handleLogin.init();
            handleForgetPassword();
            handleRegister();

        }

    };

}();

jQuery(document).ready(function() {
    Login.init();
});