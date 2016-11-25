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
		                      valign: 'middle',
		                      formatter:function(value,row,index){ 
		                      	  var valueText = value;
		                      	  switch(value){
	                      	          case  "100000" :
	                      	             valueText = '<input type="text" class="form-control" name="roleCode" id="roleCode">';
	                      	             break;
		                      	  }
		                          return valueText;
		                      } 
		                  }, 
		                  
		                  {
		                      title: '角色名称',
		                      field: 'roleName',
		                      valign: 'middle',
		                      formatter:function(value,row,index){ 
		                      	  var valueText = value;
		                      	  switch(value){
	                      	          case  "100000" :
	                      	             valueText = '<input type="text" class="form-control" name="roleName" id="roleName">';
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
		                      width : 150,
		                      align: 'center',
		                      valign: 'middle',
		                      formatter:function(value,row,index){  
		                      	if(value == "100000"){
		                      		var b = '<a href="javaScript:void(0)" mce_href="javaScript:void(0)" class="btn btn-sm btn-outline green" onclick="Role.deleteRow(this)"><i class="fa fa-check"></i> 保存</a> ';
		                      		var d = '<a href="javaScript:void(0)" mce_href="javaScript:void(0)" class="btn btn-sm btn-outline red" onclick="Role.deleteRow(this)"><i class="fa fa-trash-o"></i> 删除</a> ';
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
	 * 移除行
	 */
	deleteRow : function(obj){
	   console.log(obj);
	   Role.roleGrid.bootstrapTable('remove', {field: 'id', values: '0'});
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
	   Role.initGrid();
	}
}


$(function(){
   Role.init();
})