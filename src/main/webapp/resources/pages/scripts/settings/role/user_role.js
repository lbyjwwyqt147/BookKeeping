/***
 * 用户角色管理
 * @type 
 */
var UserRole = {
	noRoleGrid : "",
    yesRoleGrid : "",
	/**
	 * 初始化未分配表格
	 */
	initNoGrid : function(){
	   UserRole.noRoleGrid = $("#noRoleGrid");
	   UserRole.noRoleGrid.bootstrapTable({
		    url: "bookkeeping/role/roleList",              //请求后台的URL
		    mothed:"POST",                                         //请求方式
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
		                      valign: 'middle' 
		                  }, 
		                  
		                  {
		                      title: '角色名称',
		                      field: 'roleName',
		                      valign: 'middle'
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
	                      	        
		                      	  }
		                          return statusText;
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
	 * 初始化已分配表格
	 */
	initYesGrid : function(){
	   UserRole.yesRoleGrid = $("#yesRoleGrid");
	   UserRole.yesRoleGrid.bootstrapTable({
		    url: "bookkeeping/userRole/findList",              //请求后台的URL
		    mothed:"post",                                         //请求方式
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
		                      valign: 'middle'
		                  }, 
		                  
		                  {
		                      title: '角色名称',
		                      field: 'roleName',
		                      valign: 'middle'
		                   
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
		                      	  }
		                          return statusText;
		                      } 
		                  }
		              ],
		            queryParamsType: "limit",                             //参数格式,发送标准的RESTFul类型的参数请求
		            queryParams: function (params) { 
		            	 var userId = $("#userId").val();
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
				            userId: userId
				        }
				    }  
		              
		      });
		      
		     
	},
	
	/**
	 * 添加行
	 */
	addRow : function(){
		
	   	var json = JSON.stringify(UserRole.noRoleGrid.bootstrapTable('getSelections'));
        var jsonObj = eval(json);
	   	var roleIdArray = new Array();
	   	var roleCodeArray = new Array();
	   
	   	$.each(jsonObj,function(i,v){
	   		roleIdArray.push(v.id);
	   		roleCodeArray.push(v.roleCode);
	   		UserRole.yesRoleGrid.bootstrapTable('insertRow', {
                index: i,
                row: {
                	state: '',
                    id: i,
                    roleCode: v.roleCode,
                    roleName:v.roleName,
                    isActivate :'1001'
                }
           });
	   	});
	   	
	   	//保存数据
	   	UserRole.saveRow(roleIdArray.toString(),roleCodeArray.toString());
	    
	},
	
	/**
	 * 刷新未分配表格
	 */
	refreshGrid : function(){
	    $("#noRoleGrid").bootstrapTable("refresh");
	},
	
   /**
	 * 刷新已分配表格
	 */
	refreshYesGrid : function(){
	    $("#yesRoleGrid").bootstrapTable("refresh");
	},
	
	/**
	 * 保存行数据
	 * @param {} obj  行数据
	 */
	saveRow : function(roleId,roleCode){
	   var userId = $("#userId").val();
	   var jsonObj = {
	       userId :  userId,
	       roleId : roleId,
	       roleCode : roleCode,
	       isActivate : '1001'
	   }
	   console.log(jsonObj);
	   $.ajax({
	       url : 'bookkeeping/userRole/saveInfo',
	       type : 'post',
	       dataType : 'json',
	       data : jsonObj,
	       success : function(data){
	       	   console.log(data);
	       	   if(data.success){
	       	      layer.msg(data.message, {icon: 1}); 
	         	  UserRole.refreshGrid();
	         	  UserRole.refreshYesGrid();
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
	   var ids = $.map(UserRole.noRoleGrid.bootstrapTable('getSelections'), function (row) {
                return row.id;
            });
       console.log(ids);
       var idss = new Array();
       idss.push(obj);
       console.log(idss);
	   UserRole.noRoleGrid.bootstrapTable('remove', {field: 'id', values: idss});
	},
	
	/**
	 * 删除
	 * @param {} code   角色代码值
	 */
	deleteUserRole : function(code){
		  layer.open({
	         title : '提示信息',
	         skin: 'layui-layer-lan',
	         content: '是否确定删除角色吗?',
	         btn: ['确定', '取消'],
	         yes: function(index, layero){
	               //按钮【按钮一】的回调
	         	    $.ajax({
					    url : 'bookkeeping/UserRole/deletes',
					    data : { code : code},
					    type : "post",
					    dataType : "json",
					    success : function(data){
					        if(data.success){
					       	    layer.msg(data.message, {icon: 1}); 
					         	UserRole.refreshGrid();
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
	 * 初始化
	 */
	init : function(){
	   UserRole.initNoGrid();
	   UserRole.initYesGrid();
	}
}


$(function(){
   UserRole.init();
})