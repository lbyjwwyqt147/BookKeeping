/***
 * 业务字典
 * @type 
 */
var Dict = {
    form : $("#dict-form"),
    validator : "",
    dataGrid : "",
    oldDictWord:"",
    oldEntityName:"",
    oldFieldName : "",
	/**
	 * 表单验证
	 */
	dictFormValidation :function() {
	 Dict.validator =  Dict.form.validate({
                errorElement: 'span',
                errorClass: 'help-block help-block-error',
                focusInvalid: false, 
                //focusCleanup:true,
                ignore: "",  
                rules: {
                    dictWord: {
                        maxlength: 4,
                        required: true,
                        numberLetter :true,
                        remote: {
						    url: "tally/dict/checkDictWord",     //后台处理程序
						    type: "post",               //数据发送方式
						    dataType: "json",           //接受数据格式   
						    data: {                     //要传递的数据
						        dictWord: function() {
						            return $.trim($("#dictWord").val());
						        },
						        parentCode: function() {
						            return $.trim($("#parentCode").val());
						        },
						        oldDictWord : function(){
						           return $.trim(Dict.oldDictWord);
						        }
						    }
						}
                    },
                    dictName: {
                    	maxlength: 15,
                        required: true,
                        chineseNumber: true
                    },
                    parentText: {
                        required: true
                    },
                    entityName: {
                        maxlength: 50
                    },
                    entityFieldName: {
                        maxlength: 50,
                        remote: {
						    url: "tally/dict/checkDictEntityAndFieldName",     //后台处理程序
						    type: "post",               //数据发送方式
						    dataType: "json",           //接受数据格式   
						    data: {                     //要传递的数据
						        entityName: function() {
						            return $.trim($("#entityName").val());
						        },
						        fieldName: function() {
						            return $.trim($("#entityFieldName").val());
						        },
						        oldEntityName : function(){
						           return $.trim(Dict.oldEntityName);
						        },
						        oldFieldName : function(){
						           return $.trim(Dict.oldFieldName);
						        }
						    }
						}
                    },
                    dictDescription: {
                        maxlength: 50
                    }
                    
                },
                
                messages:{ 
					dictWord:{ 
					   remote: "字典代码重复." 
					},
					entityFieldName:{ 
					   remote: "字段名称重复." 
					},
					parentText: {
                        required: "请选择左边的字典树作为上级."
                    }
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
                    if($("#isParent").val() == "1"){
                    	 $(".labelcustom").css({"color":"#27a4b0"});
	    	             $("#entityName").css({"border-color":"#27a4b0"});
	    	             $("#entityFieldName").css({"border-color":"#27a4b0"});
	    	             $(".facustom").removeClass("fa-warning");
	    	             
                    }else{
                    
                    }
                   
                },

                submitHandler: function (form) {
                    
                }
            });
	},
	
	/**
	 * 保存字典信息
	 */
	saveDict : function(){
	   commonUtil.inputTrim();	
	   if(Dict.form.validate().form()){
		   	$.ajax({
		      url : 'tally/dict/saveInfo',
		      data :$("#dict-form").serialize(),
		      type : "post",
		      dataType : 'json',
		      success : function(data){
		         if(data.success){
		         	//重置表单
		         	Dict.resetForm();
		         	//刷新树
		         	Dict.refreshTree();
		         	$("#dictDataGrid").bootstrapTable("refresh");
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
	   Dict.form[0].reset();
	   var entityName = $("#entityName");
	   var entityFieldName = $("#entityFieldName");
	   //实体名称移除验证规则
       entityName.rules("remove","required");
       //字段名称移除验证规则
       entityFieldName.rules("remove","required");
       //重置表单的验证状态
       commonUtil.resetIconForm("#dict-form");
	   
	},
	
	/**
	 * 初始化字典树
	 */
	initZtree : function(){
	    zTreeUtil.createZtree({
		    zTreeWidgetId : "dictTree",    //树控件div Id
		    zTreeWidgetInputValue : "0",  //树被选中的节点ID值
		    zTreeWidgetInputId : "",     //树被选中的节点ID
		    zTreeNodeIdValue : "0",      //节点值
		    searchInputId : "", //查询条件input ID
		    zTreeUrl : "tally/dict/dictTree",
		    onCloseTree : function(){
		       
		    },
		    onMouseUp : function(){
		    
		    },
		    onClick : function(event, treeId, treeNode){
		       var nodeName = treeNode.name;
		       var nodeId = treeNode.id;
	           $('#parentText').val(nodeName);
	           $('#parentCode').val(nodeId);
	           $("#codeValue").text(nodeId);
	           $("#dictDataGrid").bootstrapTable("refresh");
	           $("#dictWord").removeAttr("readonly");
	         
		    }
		});
	},
	
	/**
	 * 刷新树
	 */
	refreshTree : function(){
	    zTreeUtil.refreshTree();
	    /* var treeObj = $.fn.zTree.getZTreeObj("dictTree");
	     var nodes = treeObj.getSelectedNodes();
		 if (nodes.length>0) {
			treeObj.reAsyncChildNodes(nodes[0], "refresh");
		 }*/
	},
	
	/**
	 * 初始化表格
	 */
	initGrid : function(){
	    var dictGrid = $("#dictDataGrid");
	    Dict.dataGrid = dictGrid.bootstrapTable({
		    url: "tally/dict/dictList",              //请求后台的URL
		    mothed:"post",                           //请求方式
		    dataType: "json",
		    //toolbar : "#toolbar",                   //工具栏ID
		    striped: true,                          //是否显示行间隔色
		    cache: false,                           //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性
		    pagination: true,                       //是否显示分页
		    singleSelect: false,
		    onlyInfoPagination : false,
		    pageSize : 10,                          //每页的记录行数
		    pageNumber: 1,                          //初始化加载第一页，默认第一页
		    pageList: [5, 10, 20,50],               //可供选择的每页的行数
		    height: 460,                            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		   // data-locale:"zh-US" , //表格汉化
		    search: false,                         //显示搜索框
		    clickToSelect: true,                   //是否启用点击选中行
		    idField : "id",                        // 指定主键列
		    uniqueId: "id",                        //每一行的唯一标识，一般为主键列
		    sidePagination: "server",              //分页方式：client客户端分页，server服务端分页
		    columns: [
						   {
				              field: 'state',
				              checkbox: true,
				              align: 'center',
		                      valign: 'middle'
				           },{
		                      title: 'id',
		                      field: 'id',
		                      width : 1,
		                      align: 'center',
		                      valign: 'middle',
		                      visible : false
		                  }, 
		                  {
		                      title: '字典代码',
		                      field: 'dictWord',
		                      width : 10,
		                      align: 'center',
		                      valign: 'middle'
		                  }, 
		                  {
		                      title: '字典名称',
		                      field: 'dictName',
		                      width : 20,
		                      valign: 'middle'
		                  },
		                  {
		                      title: '实体名称',
		                      field: 'entityName',
		                      width : 20,
		                      valign: 'middle'
		                  },
		                  {
		                      title: '字段名称',
		                      field: 'entityFieldName',
		                      width : 20,
		                      valign: 'middle'
		                  },
		                  {
		                      title: '字典状态',
		                      field: 'stateText',
		                      align: 'center',
		                      width : 10,
		                      valign: 'middle',
		                      formatter:function(value,row,index){  
		                          return value;
		                      } 
		                  },
		                  {
		                      title: '操作',
		                      field: 'id',
		                      width : 20,
		                      align: 'center',
		                      formatter:function(value,row,index){  
		                      	  var rowJson = JSON.stringify(row);
		                      	  var j = '<a href="javaScript:void(0)" mce_href="javaScript:void(0)" onclick="Dict.updateState(\''+ row.id + '\',\'1001\')">激活</a> ';
		                      	  var s = '<a href="javaScript:void(0)" mce_href="javaScript:void(0)" onclick="Dict.updateState(\''+ row.id + '\',\'1002\')">锁定</a> '; 
		                          var e = "<a href=\"javaScript:void(0)\" mce_href=\"javaScript:void(0)\" onclick='Dict.updateDict("+ rowJson +")'>编辑</a> ";  
		                          var d = '<a href="javaScript:void(0)" mce_href="javaScript:void(0)" onclick="Dict.deleteDict(\''+ row.id +'\')">删除</a> ';
		                          if(row.isActivate == '1001'){
		                          	return e+s+d; 
		                          }else{
		                            return e+j+d; 
		                          }
		                           
		                    } 
		                  }
		              ],
		            queryParamsType: "limit",                             //参数格式,发送标准的RESTFul类型的参数请求
		            queryParams: function (params) {                      //参数
		               console.log(params);
				        return {
				        	pageSize: params.limit,                      //页面大小  
                            pageNumber: params.offset,                   //页码
                            sort: params.sort,                           //排序列名  
                            sortOrder: params.order,                     //排位命令（desc，asc）
				            pid: $.trim($('#codeValue').text()) == "" ? "1" : $.trim($('#codeValue').text())     //节点编号
				        }
				    }  
		              
		      });
	},
	
	/**
	 * 更新字典状态
	 * @param {} id  id
	 * @param {} state  状态值
	 */
	updateState : function(id,state){
	    
		$.ajax({
		    url : 'tally/dict/updateDictState',
		    data : { id : id, stateValue : state},
		    type : "post",
		    dataType : "json",
		    success : function(data){
		       if(data.success){
		       	    layer.msg(data.message, {icon: 1}); 
		         	$("#dictDataGrid").bootstrapTable("refresh");
		       }else{
		           layer.msg(data.message, {icon: 5}); 
		       }
		       
		    }
		})
		
	},
	
	/**
	 * 删除字典
	 * @param {} id  id
	 */
	deleteDict : function(id){
	  layer.open({
         title : '提示信息',
         content: '是否确定删除字典信息?',
         btn: ['确定', '取消'],
         yes: function(index, layero){
               //按钮【按钮一】的回调
         	    $.ajax({
				    url : 'tally/dict/deleteDicts',
				    data : { id : id},
				    type : "post",
				    dataType : "json",
				    success : function(data){
				        if(data.success){
				       	    layer.msg(data.message, {icon: 1}); 
				         	$("#dictDataGrid").bootstrapTable("refresh");
				       }else{
				           layer.msg(data.message, {icon: 5}); 
				       }
				    }
			   })
         },
         btn2: function(index, layero){
              //按钮【按钮二】的回调
         },
         cancel: function(){ 
             //右上角关闭回调
        }
      });     
		
	 
		
	},
	
	/**
	 * 更新字典  
	 * @param {} row   数据行
	 */
	updateDict : function(row){
	  //设置表单值
	  $("#zid").val(row.id);
	  $("#dictWord").attr("readonly","readonly");
	  $("#dictWord").val(row.dictWord);
	  $("#dictName").val(row.dictName);
	  $("#parentCode").val(row.parentCode);
	  $("#codeValue").text(row.parentCode);
	  $("#task").val("edit");
	  $("#isParent").val(row.isParent);
	  $("#parentText").val(row.parentText);
	  $("#entityName").val(row.entityName);
	  $("#entityFieldName").val(row.entityFieldName);
	  $("#dictDescription").val(row.dictDescription);
	  if(row.isActivate == "1001"){
	  	$("#isActivate1").trigger("click");
	  }else{
	  	$("#isActivate2").trigger("click");

	  }
	  Dict.oldDictWord = row.dictWord;
      Dict.oldEntityName = row.entityName;
      Dict.oldFieldName = row.entityFieldName;
	},
	
	/**
	 * 初始化 
	 */
	init : function(){
		//计算页面高度
		var bodyHeight = $(document.body).height()*0.75;
		$(".contentdiv").height(bodyHeight);
		Dict.initZtree();
		Dict.dictFormValidation();
		//保存事件
	    $("#dictSaveBtn").click(function(){
	         Dict.saveDict();
	    });
	    //重置事件
	    $("#dictResetBtn").click(function(){
	         Dict.resetForm();
	    });
	    //刷新树事件
	    $("#reloadTree").click(function(){
	         Dict.refreshTree();
	    });
	    //刷新表格事件
	    $("#reloadTabel").click(function(){
	         $("#dictDataGrid").bootstrapTable("refresh");
	    });

		Dict.initGrid();
	
	    $('#isParent', Dict.form).change(function () {
	    	    var entityName = $("#entityName");
	    	    var entityFieldName = $("#entityFieldName");
	    	    if($(this).val() == "1"){
	    	    	var fieldVild = {required:true,letter:true};
	    	    	//实体名称增加验证规则
	    	    	entityName.rules("add",fieldVild);
	    	    	//字段名称增加验证规则
	    	        entityFieldName.rules("add",fieldVild);
	    	    }else{
	    	    	//实体名称移除验证规则
	    	    	entityName.rules("remove","required");
	    	    	//字段名称移除验证规则
	    	        entityFieldName.rules("remove","required");
	    	        entityFieldName.removeAttr("aria-invalid");
	    	        entityName.removeAttr("aria-invalid");
	    	        entityFieldName.removeAttr("aria-describedby");
	    	        entityName.removeAttr("aria-describedby");
	    	        $(".facustom").removeClass("fa-warning");
	    	        $(".facustom").removeClass("fa-check");
	    	        $(".labelcustom").removeClass("has-success");
                    $(".labelcustom").removeClass("has-error");
	    	        
	    	    }
	    	    
	    	    //验证实体名称
                Dict.form.validate().element(entityName); 
                //验证字段名称
                Dict.form.validate().element(entityFieldName); 
                
                
                
        });
	}
}

$(function(){
    Dict.init();
   
	
})