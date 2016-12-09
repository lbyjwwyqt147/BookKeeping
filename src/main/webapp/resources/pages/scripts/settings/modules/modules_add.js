var ModuleForm = {
     form : $("#module-form"),
     validator : "",
	 formValidation : function(){
	   ModuleForm.validator =  ModuleForm.form.validate({
                errorElement: 'span',
                errorClass: 'help-block help-block-error',
                focusInvalid: false, 
                //focusCleanup:true,
                ignore: "",  
                rules: {
                    moduleName: {
                        required: true/*,
                        chineseLetter :true*/
                    },
                    nodeLevel: {
                        required: true/*,
                        integer: true*/
                    },
                    moduleUrl: {
                        required: true
                        
                    }
                    
                },
                
                messages:{ 
					
                },
                
                invalidHandler: function (event, validator) {             
                   
                },

                errorPlacement: function (error, element) { 
                    var icon = $(element).parent('.input-icon').children('i');
                    icon.removeClass('fa-check').addClass("fa-warning");  
                    icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
                },

                highlight: function (element) { 
                    $(element)
                        .closest('.form-group').removeClass("has-success").addClass('has-error'); 
                },

                unhighlight: function (element) { 
                    
                },

                success: function (label, element) {
                    var icon = $(element).parent('.input-icon').children('i');
                    $(element).closest('.form-group').removeClass('has-error').addClass('has-success'); 
                    icon.removeClass("fa-warning").addClass("fa-check");
                    
                   
                },

                submitHandler: function (form) {
                    
                }
            });
	},
	
	/**
	 * 保存信息
	 */
	saveModules : function(){
	   commonUtil.inputTrim();	
	   if(ModuleForm.form.validate().form()){
		   	$.ajax({
		      url : 'bookkeeping/modules/saveInfo',
		      data :$("#module-form").serialize(),
		      type : "post",
		      dataType : 'json',
		      success : function(data){
		         if(data.success){
		         	//重置表单
		         	ModuleForm.resetForm();
		         	//刷新树
		         	//ModuleForm.refreshTree();
		         	//$("#ModuleFormDataGrid").bootstrapTable("refresh");
		         }else{
		            layer.msg(data.message, {icon: 5}); 
		         }
		      }
		   });
	   }
	   
	},
	
	/**
	 * 重置表单
	 */
	resetForm : function(){
	   ModuleForm.form[0].reset();
	},
	
	/**
	 * 初始化 
	 */
	init : function(){
		//计算页面高度
		//var bodyHeight = $(document.body).height()*0.75;
		//$(".contentdiv").height(bodyHeight);
		ModuleForm.formValidation();
		//保存事件
	    $("#moduleFormSaveBtn").click(function(){
	         ModuleForm.saveModules();
	    });
	    //重置事件
	    $("#moduleFormResetBtn").click(function(){
	         ModuleForm.resetForm();
	    });
	  
	}
}

$(function(){
    ModuleForm.init();
   
	
})