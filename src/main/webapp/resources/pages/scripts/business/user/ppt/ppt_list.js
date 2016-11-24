/**
 * ppt 
 * @type 
 */
var PPT = {
	/**
	 * 初始化表格
	 */
	initGrid : function(){
	    var pptGrid = $("#pptDataGrid");
	   pptGrid.bootstrapTable({
		    url: "teacher/myUpfile/getUploadFileListWeb",              //请求后台的URL
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
		                      title: 'ppt名称',
		                      field: 'fileName',
		          
		                      valign: 'middle'
		                  }, 
		                  
		                  {
		                      title: '上传日期',
		                      field: 'createTime',
		                      width : 170,
		                      valign: 'middle',
		                      formatter:function(value,row,index){  
		                          return commonUtil.dateUtil.DatePattern(value,"yyyy-MM-dd hh:mm:ss");
		                      } 
		                  },
		                  {
		                      title: '状态',
		                      field: 'editStatus',
		                      align: 'center',
		                      width : 15,
		                      valign: 'middle',
		                      formatter:function(value,row,index){ 
		                      	  var statusText = "未编辑";
		                      	  switch(value){
		                      	      case  "10" :
		                      	         statusText = "未编辑";
		                      	         break;
	                      	          case  "20" :
	                      	             statusText = "正在编辑";
	                      	             break;
                      	         	  case  "30" :
                      	                 statusText = "编辑完成";
                      	                 break;   
		                      	  }
		                          return statusText;
		                      } 
		                  },
		                  {
		                      title: '操作',
		                      field: 'id',
		                      width : 20,
		                      align: 'center',
		                      valign: 'middle',
		                      formatter:function(value,row,index){  
		                          var d = '<a href="javaScript:void(0)" mce_href="javaScript:void(0)" onclick="PPT.deletePPT(\''+ row.fileId +'\')">删除</a> ';
		                          return d;   
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
				        	pageSize: params.limit,                      //页面大小  
                            pageNum: pageindex,                   //页码
                            sort: params.sort,                           //排序列名  
                            sortOrder: params.order,                     //排位命令（desc，asc）
				            memberId: ''  //用户编号
				        }
				    }  
		              
		      });
		      
		     
	},
	
	/**
	 * 属性表格
	 */
	refreshGrid : function(){
	    $("#pptDataGrid").bootstrapTable("refresh");
	},
	
	/**
	 * 删除
	 * @param {} fileId   ppt编号
	 */
	deletePPT : function(fileId){
		  layer.open({
	         title : '提示信息',
	         content: '是否确定删除ppt文件吗?',
	         btn: ['确定', '取消'],
	         yes: function(index, layero){
	               //按钮【按钮一】的回调
	         	    $.ajax({
					    url : 'teacher/myUpfile/deletePPTandImages',
					    data : { fileId : fileId},
					    type : "post",
					    dataType : "json",
					    success : function(data){
					        if(data.success){
					       	    layer.msg(data.message, {icon: 1}); 
					         	PPT.refreshGrid();
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
	
	init : function(){
	   PPT.initGrid();
	   $("#uploadPpta").click(function(){
	     window.location.href="teacher/myUpfile/init";
	   })
	}
}

$(function(){
   PPT.init();
})