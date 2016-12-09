/***
 * 角色管理
 * @type 
 */
var Role = {
	roleGrid : "",
    
	/**
	 * 初始化表格
	 */
	initGrid : function(){
	   Role.roleGrid = $("#roleGrid");
	   Role.roleGrid.bootstrapTable({
		    url: "bookkeeping/role/roleList",              //请求后台的URL
		    mothed:"POST",                                         //请求方式
		    dataType: "json",
		    //toolbar : "#toolbar",                   //工具栏ID
		    striped: true,                          //是否显示行间隔色
		    cache: false,                           //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性
		    pagination: true,                       //是否显示分页
		    singleSelect: true,
		    onlyInfoPagination : false,
		    pageSize : 10,                          //每页的记录行数
		    pageNumber: 1,                          //初始化加载第一页，默认第一页
		    pageList: [5, 10, 20,50],               //可供选择的每页的行数
		    height: $(window).height()/1.25,                            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
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
		                      title: '代码值',
		                      field: 'roleCode',
		                      width : 170,
		                      valign: 'middle',
		                     // editable: true,
		                      formatter:function(value,row,index){ 
		                      	  var valueText = value;
		                      	  switch(value){
	                      	          case  "100000" :
	                      	             valueText = '<input type="text" class="form-control" name="roleCode" id="roleCode" maxlength="10">';
	                      	             break;
		                      	  }
		                          return valueText;
		                      } 
		                  }, 
		                  
		                  {
		                      title: '角色名称',
		                      field: 'roleName',
		                      valign: 'middle',
		                   /*   editable: {  
				                    type: 'text',  
				                    title: '角色名称',  
				                    validate: function (value) {  
				                        value = $.trim(value);  
				                        if (!value) {  
				                            return 'This field is required';  
				                        }  
				                        if (!/^\$/.test(value)) {  
				                            return 'This field needs to start width $.'  
				                        }  
				                        var data = $table.bootstrapTable('getData'),  
				                            index = $(this).parents('tr').data('index');  
				                        console.log(data[index]);  
				                        return '';  
				                    }  
				              },*/
		                      formatter:function(value,row,index){ 
		                      	  var valueText = value;
		                      	  switch(value){
	                      	          case  "100000" :
	                      	             valueText = '<input type="text" class="form-control" name="roleName" id="roleName" maxlength="15">';
	                      	             break;
		                      	  }
		                          return valueText;
		                      } 
		                  },
		                  {
		                      title: '状态',
		                      field: 'isActivate',
		                      align: 'center',
		                      width : 100,
		                      valign: 'middle',
		                     // editable: true,
		                      formatter:function(value,row,index){ 
		                      	  var statusText = "激活";
		                      	  switch(value){
		                      	      case  "1001" :
		                      	         statusText = "激活";
		                      	         break;
	                      	          case  "1002" :
	                      	             statusText = "锁定";
	                      	             break;
	                      	          case  "100000" :
	                      	             statusText = '<select class="form-control js-example-data-array" name="isActivate" id="isActivate" >'+
                                                       '<option value="1001">激活</option>'+
                                                       '<option value="1002">锁定</option>'+
                                              '</select>';
	                      	             break;
		                      	  }
		                          return statusText;
		                      } 
		                  },
		                  {
		                      title: '操作',
		                      field: 'caozuo',
		                      width : 170,
		                      align: 'center',
		                      valign: 'middle',
		                      formatter:function(value,row,index){  
		                      	if(value == "100000"){
		                      		var rowJson = JSON.stringify(row);
		                      		console.log(rowJson);
		                      		var b = '<a href="javaScript:void(0)" mce_href="javaScript:void(0)" class="btn btn-sm btn-outline green" onclick="Role.saveRow(\'add\')"><i class="fa fa-check"></i> 保存</a> ';
		                      		var d = '<a href="javaScript:void(0)" mce_href="javaScript:void(0)" class="btn btn-sm btn-outline red" onclick="Role.deleteRow(\''+ row.id +'\')"><i class="fa fa-trash-o"></i> 删除</a> ';
		                            return b+d;
		                      	}else{
		                            var d = '<a href="javaScript:void(0)" mce_href="javaScript:void(0)" class="btn btn-sm btn-outline red" onclick="Role.deleteRole(\''+ row.roleCode +'\')"><i class="fa fa-trash-o"></i> 删除</a> ';
		                            return d;
		                      	}
		                      	     
		                    } 
		                  }
		              ],
		            queryParamsType: "limit",                             //参数格式,发送标准的RESTFul类型的参数请求
		            queryParams: function (params) {  
		            	 var pageindex = 0;
		            	 if(params.offset != 0){
		            	   pageindex = params.offset/params.limit
		            	 }
		            	 pageindex+= 1
		            	//参数
				        return {
				        	limit: params.limit,                      //页面大小  
                            pageNum: pageindex,                   //页码
                            sort: params.sort,                           //排序列名  
                            sortOrder: params.order,                     //排位命令（desc，asc）
				            roleCode: ''  
				        }
				    }  
		              
		      });
		      
		     
	},
	
	/**
	 * 添加行
	 */
	addRow : function(){
	   Role.roleGrid.bootstrapTable('insertRow', {
                index: 1,
                row: {
                	state: '',
                    id: 0,
                    roleCode: '100000',
                    roleName:'100000',
                    isActivate :'100000',
                    caozuo:'100000'
                }
        }); 
	},
	
	/**
	 * 属性表格
	 */
	refreshGrid : function(){
	    $("#roleGrid").bootstrapTable("refresh");
	},
	
	/**
	 * 保存行数据
	 * @param {} obj  行数据
	 */
	saveRow : function(task){
	/*   var roleCodeVild = {required:true,numberLetter:true};
	   var roleNameVild = {required:true,chineseLetter:true};
	   $("#roleCode").rules("add",roleCodeVild);
	   $("#roleName").rules("add",roleNameVild);*/
	   var jsonObj = {
	       roleCode :  $("#roleCode").val(),
	       roleName : $("#roleName").val(),
	       task : task,
	       isActivate : $("#isActivate").val()
	   }
	   console.log(jsonObj);
	   $.ajax({
	       url : 'bookkeeping/role/saveInfo',
	       type : 'post',
	       dataType : 'json',
	       data : jsonObj,
	       success : function(data){
	       	   console.log(data);
	       	   if(data.success){
	       	      layer.msg(data.message, {icon: 1}); 
	         	  Role.refreshGrid();
	           }else{
	              layer.msg(data.message, {icon: 5}); 
	           }
	       },
	       error : function(){
	         layer.msg("网络出现异常.", {icon: 5});
	       }
	   })
	},
	
	/**
	 * 移除行
	 */
	deleteRow : function(obj){
	   console.log(obj);
	   var ids = $.map(Role.roleGrid.bootstrapTable('getSelections'), function (row) {
                return row.id;
            });
       console.log(ids);
       var idss = new Array();
       idss.push(obj);
       console.log(idss);
	   Role.roleGrid.bootstrapTable('remove', {field: 'id', values: idss});
	},
	
	/**
	 * 删除
	 * @param {} code   角色代码值
	 */
	deleteRole : function(code){
		  layer.open({
	         title : '提示信息',
	         skin: 'layui-layer-lan',
	         content: '是否确定删除角色吗?',
	         btn: ['确定', '取消'],
	         yes: function(index, layero){
	               //按钮【按钮一】的回调
	         	    $.ajax({
					    url : 'bookkeeping/role/deletes',
					    data : { code : code},
					    type : "post",
					    dataType : "json",
					    success : function(data){
					        if(data.success){
					       	    layer.msg(data.message, {icon: 1}); 
					         	Role.refreshGrid();
					        }else{
					            layer.msg(data.message, {icon: 5}); 
					        }
					    },
					    error : function(){
					       layer.msg("网络出现异常.", {icon: 5});
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
	 * 初始化字典树
	 */
	initZtree : function(){
	    zTreeUtil.createZtree({
		    zTreeWidgetId : "modulesTree",    //树控件div Id
		    zTreeWidgetInputValue : "0",  //树被选中的节点ID值
		    zTreeWidgetInputId : "",     //树被选中的节点ID
		    zTreeNodeIdValue : "0",      //节点值
		    check : true,
		    searchInputId : "", //查询条件input ID
		    zTreeUrl : "bookkeeping/modules/modulesTree",
		    onCloseTree : function(){
		       
		    },
		    onMouseUp : function(){
		    
		    },
		    onClick : function(event, treeId, treeNode){
		       var nodeName = treeNode.name;
		       var nodeId = treeNode.id;
	           
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
	 * 分配角色模块
	 */
	saveModules : function(){
		//获取选择的角色
	  	var json = JSON.stringify(Role.roleGrid.bootstrapTable('getSelections'));
        var jsonObj = eval(json);
	   	var roleIdArray = new Array();
	   	var roleCodeArray = new Array();
	    
	   	$.each(jsonObj,function(i,v){
	   		roleIdArray.push(v.id);
	   		roleCodeArray.push(v.roleCode);
	   	});
	   	
	   	var moduleId = "0";
	   	var moduleCodeArray = new Array();
	   	
	   	//获取选中的菜单
	   	var nodes = zTreeUtil.getSelectNodes();
	   	console.log(nodes);
	   	$.each(nodes,function(i,v){
	   		moduleCodeArray.push(v.id);
	   	});
	   	
	    $.ajax({
					    url : 'bookkeeping/roleModule/saveInfo',
					    data : { 
					             roleId : roleIdArray.toString(),
					             roleCode : roleCodeArray.toString(),
					             moduleId : moduleId,
					             moduleCode : moduleCodeArray.toString()
					    },
					    type : "post",
					    dataType : "json",
					    success : function(data){
					        if(data.success){
					       	    layer.msg(data.message, {icon: 1}); 
					         	Role.refreshTree();
					        }else{
					            layer.msg(data.message, {icon: 5}); 
					        }
					    },
					    error : function(){
					       layer.msg("网络出现异常.", {icon: 5});
					    }
				   })
	},
	
	/**
	 * 初始化
	 */
	init : function(){
	   Role.initGrid();
	   Role.initZtree();
	}
}


$(function(){
   Role.init();
})