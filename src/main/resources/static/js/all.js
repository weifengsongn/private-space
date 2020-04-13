$("table").bootstrapTable({
    search: true,
    pagination:true, //设置为 true 会在表格底部显示分页条
    paginationLoop:true, //设置为 true 启用分页条无限循环的功能。
    sidePagination:'client', //设置在哪里进行分页，可选值为 'client' 或者 'server'。
    pageSize:1,
    pageList:[10,15,20,25,50],
    paginationHAlign:'right', //分页条位置
    showPaginationSwitch:true, //显示数据条数框
    locale: 'zh-CN',//进行本地化
    toolbar:'#toolbar',
    columns: [{
        field: 'id',
        title: 'Item ID'
    }, {
        field: 'name',
        title: '名字记录'
    }, {
        field: 'price',
        title: 'Item Price'
    }], data: [{
        id: 1,
        name: 'Item 1',
        price: '$1'
    }, {
        id: 2,
        name: 'Item 2',
        price: '$2'
    }
    ]
})
