/***
 * 角色管理
 * @type 
 */
var Role = {
    
	/**
	 * 表格数据
	 */
	initGrid : function(){
	    var grid = new Datatable();
        var table = $('#roleGrid');
        grid.init({
            src: $("#roleGrid"),
            onSuccess: function (grid, response) {
                $("#roleGrid_paginate").parent().remove();
                console.log("加载完成");
            },
            onError: function (grid) {
               
            },
            onDataLoad: function(grid) {
                
            },
            "dom" : "aaaaaa",
            loadingMessage: '数据加载中...',
            dataTable: { 

                "bStateSave": false, 
                "ordering": true,
                "lengthMenu": [
                    [10, 20, 50, 100, 150, -1],
                    [10, 20, 50, 100, 150, "All"] 
                ],
                "pageLength": 10, 
                "ajax": {
                    "url": "tally/role/roleList" 
                },
                columns: [
                            {
			                 "sClass": "text-center",
			                 "data": "id",
			                 "render": function (data, type, full, meta) {
			                     return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkboxes"  value="' + data + '" /><span></span></label>';
			                 },
			                 "bSortable": false
			                },
					        { data: 'id' },
					        { data: 'name' },
					        { data: 'column1' }
					    ],
                "order": [
                    [1, "asc"]
                ]
            }
        });
        
     
        table.find('.group-checkable').change(function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery("tbody tr .checkboxes").each(function () {
            	console.log(checked);
                if (checked) {
                    $(this).prop("checked", true);
                    $(this).parents('tr').addClass("active");
                } else {
                    $(this).prop("checked", false);
                    $(this).parents('tr').removeClass("active");
                }
            });
        });

        table.on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('tr').toggleClass("active");
        });
	},
	
	/**
	 * 初始化页面内容
	 */
	init : function(){
	    Role.initGrid();
	}
}

$(function(){
   Role.init();
})