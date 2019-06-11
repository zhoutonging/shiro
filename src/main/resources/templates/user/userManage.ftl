<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-card layadmin-header">
    <div class="layui-breadcrumb" lay-filter="breadcrumb">
        <a lay-href="">主页</a>
        <a><cite>组件</cite></a>
        <a><cite>数据表格</cite></a>
        <a><cite>开启分页</cite></a>
    </div>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="layui-btn-group test-table-operate-btn" style="margin-bottom: 10px;">
                        <button class="layui-btn" data-type="getCheckData">添加用户</button>
                    </div>

                    <table class="layui-hide" id="test-table-page" lay-filter="test-table-operate"></table>

                    <script type="text/html" id="test-table-operate-barDemo">
                        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/common/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'table'], function () {
        var admin = layui.admin
            , table = layui.table;

        table.render({
            elem: '#test-table-page',
            url: '/user/findAll',
            page: true,
            height: 'full-100',
            cellMinWidth: 80,
            cols: [[
                {type: 'numbers', title: '序号', align: 'center'},
                {field: 'userName', title: '用户名', align: 'center'},
                {field: 'password', title: '密码', align: 'center'},
                {field: 'salt', title: '加密盐', align: 'center'},
                {align: 'center', title: '操作', fixed: 'right', toolbar: '#test-table-operate-barDemo'}
            ]]
        });

        //监听工具条
        table.on('tool(test-table-operate)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {

                layer.msg(data + ' 的查看操作');

            } else if (obj.event === 'del') {

                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });

            } else if (obj.event === 'edit') {

                layer.alert('编辑行：<br>' + JSON.stringify(data))

            }
        });

        var $ = layui.$, active = {
            //添加角色
            getCheckData: function () {
                layer.msg("添加用户");
                console.log(123)
            }
        };

        $('.test-table-operate-btn .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>