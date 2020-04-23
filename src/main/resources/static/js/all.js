
$(function () {
    let userCondition = {}
    let sendFlag = false;


    $("#userTable").bootstrapTable({
        // 请求数据
        url: 'http://localhost:8291/space/user',
        method: 'get',
        queryParams: function (param) {
            return jQuery.extend({}, userCondition, param);
        },

        // 分页
        pagination: true, //设置为 true 会在表格底部显示分页条
        paginationLoop: true, //设置为 true 启用分页条无限循环的功能。
        sidePagination: 'server', //设置在哪里进行分页，可选值为 'client' 或者 'server'。
        pageSize: 10,
        pageList: [10, 15, 20, 25, 50],
        paginationHAlign: 'right', //分页条位置
        showPaginationSwitch: true, //显示数据条数框
        // onlyInfoPagination: true, //只显示总记录数
        pageNumber: 1 ,// 初始的页数

        //表格显示样式
        height: 500,
        striped: true,

        //排序
        sortName: 'id',
        sortOrder: 'asc',


        // 查询
        search: true, // m默认输入字符即开始搜索
        searchOnEnterKey: true, //enter后开始搜索  可选
        strictSearch: true, //精确搜索
        searchText: '',  //搜索框初始内容
        searchTimeOut: 5000, // 设置查询超时时间
        trimOnSearch: true, //忽略空格


        //扩展功能
        showRefresh: true,  //显示刷新按钮
        showToggle: true, // 显示视图切换按钮
        // showColumns: true, //隐藏某列下拉菜单



        locale: 'zh-CN',//进行本地化
        toolbar: '#toolbar',//关联工具栏
        //图标
        icons: { paginationSwitchDown: 'glyphicon-collapse-down icon-chevron-down',
            paginationSwitchUp: 'glyphicon-collapse-up icon-chevron-up',
            refresh: 'glyphicon-refresh icon-refresh',
            toggle: 'glyphicon-list-alt icon-list-alt',
            columns: 'glyphicon-th icon-th',
            detailOpen: 'glyphicon-plus icon-plus',
            detailClose: 'glyphicon-minus icon-minus'
        },
        iconsPrefix: "glyphicon",

        columns: [{
            checkbox: true
        },{
            field: 'id',
            title: 'ID'
        }, {
            field: 'realName',
            title: '姓名',
            formatter:function(value,row,index){
                return '<a style = "color:blue">' + value +'</a>'
            },
            events:{
                'click a':function(e, value, row, index){
                    console.log("aa");
                    findUserById(row.id)
                }
            }
        }, {
            field: 'name',
            title: '用户名'
        }],

    });

})

function saveUser() {
    let data = {"realName":  $("#username").val(),"name": $("#name").val(), "password": $("#password").val()
        ,"id": $("#id").val()};

    $.ajax('http://localhost:8291/space/user', {
        method: 'POST',
        dataType: 'json', //将要接受的数据类型
        data: JSON.stringify(data),
        cache: false,
        contentType: 'application/json; charset=utf-8',
        success: function (result, state, xhr) {
            $("#userModal").modal('toggle');
        }
    })

    $('#userTable').bootstrapTable('refresh');
}

function deleteUsers() {
    let selection = $('#userTable').bootstrapTable('getSelections');
    if (selection.length > 0) {
        $.ajax('http://localhost:8291/space/user',{
            type: 'delete',
            data: JSON.stringify(selection),
            contentType:"application/json",
            success: function (response) {
                $('#userTable').bootstrapTable('refresh');
            }
        })
    }

}

function findUserById(id) {
    $.ajax({
        url: 'http://localhost:8291/space/user/'+id,
        type: 'get',
        dataType: 'json',
        ccontentType: 'application/json;charset=utf-8',
        success: function (response) {
            let data = response;
             $("#username").val(response.name);
             $("#name").val(response.realName);
             $("#password").val(response.password);
             $("#id").val(response.id);

            $('#userModal').modal("show")

        }
    })
}



