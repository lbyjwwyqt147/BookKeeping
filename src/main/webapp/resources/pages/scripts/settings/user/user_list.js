var User = {

	userGrid : "",
    
	/**
	 * 初始化表格
	 */
	initGrid : function(){
	   User.userGrid = $("#userGrid");
	   User.userGrid.bootstrapTable({
		    url: "bookkeeping/user/findUserList",              //请求后台的URL
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
		                      title: '用户编号',
		                      field: 'userCode',
		                      width : 170,
		                      valign: 'middle'
		                     // editable: true
		                  }, 
		                  
		                  {
		                      title: '昵称',
		                      field: 'userNickname',
		                      valign: 'middle'
		                  },
		                  {
		                      title: '真实姓名',
		                      field: 'userRealname',
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
		                  },
		                  {
		                      title: '操作',
		                      field: 'caozuo',
		                      width : 170,
		                      align: 'center',
		                      valign: 'middle',
		                      formatter:function(value,row,index){  
		                      	    var j = '<a href="javaScript:void(0)" mce_href="javaScript:void(0)" class="btn btn-sm btn-outline red" onclick="User.addRole(\''+ row.id +'\')"><i class="fa fa-trash-o"></i> 角色</a> ';

		                            var d = '<a href="javaScript:void(0)" mce_href="javaScript:void(0)" class="btn btn-sm btn-outline red" onclick="User.deleteUser(\''+ row.id +'\')"><i class="fa fa-trash-o"></i> 删除</a> ';
		                            return j+d;
		                      	
		                      	     
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
				            userCode: ''  
				        }
				    }  
		              
		      });
		      
		     
	},
	
	/**
	 * 分配角色
	 */
	addRole : function(id){
	   layer.open({
		  type: 2,
		  title: '角色分配',
		  shadeClose: true,
		  shade: 0.1,
		  area: ['98%', '95%'],
		  content: 'bookkeeping/userRole/initList?userId='+id //iframe的url
		}); 
	},
	
	/**
	 * 刷新表格
	 */
	refreshGrid : function(){
	    $("#userGrid").bootstrapTable("refresh");
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
	       functionCode :  $("#functionCode").val(),
	       functionName : $("#functionName").val(),
	       task : task,
	       isActivate : $("#isActivate").val()
	   }
	   console.log(jsonObj);
	   $.ajax({
	       url : 'bookkeeping/function/saveInfo',
	       type : 'post',
	       dataType : 'json',
	       data : jsonObj,
	       success : function(data){
	       	   console.log(data);
	       	   if(data.success){
	       	      layer.msg(data.message, {icon: 1}); 
	         	  User.refreshGrid();
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
       var idss = new Array();
       idss.push(obj);
       console.log(idss);
	   User.userGrid.bootstrapTable('remove', {field: 'id', values: idss});
	},
	
	/**
	 * 删除
	 * @param {} code   角色代码值
	 */
	deleteUser : function(id){
		  layer.open({
	         title : '提示信息',
	         skin: 'layui-layer-lan',
	         content: '是否确定删除吗?',
	         btn: ['确定', '取消'],
	         yes: function(index, layero){
	               //按钮【按钮一】的回调
	         	    $.ajax({
					    url : 'bookkeeping/user/deletes',
					    data : { id : id},
					    type : "post",
					    dataType : "json",
					    success : function(data){
					        if(data.success){
					       	    layer.msg(data.message, {icon: 1}); 
					         	User.refreshGrid();
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
	   User.initGrid();
	}
}

$(function(){
   User.init();
})