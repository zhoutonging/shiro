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
<style>
    ::-webkit-scrollbar {
        display: none;
    }
    .layui-icon-layer:before{content:"\e656"}
    .layui-icon-triangle-d:before{content:""}
    .layui-icon-file:before{content:"\e66e"}
</style>
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
                        <button class="layui-btn" data-type="getCheckData">添加权限</button>
                        <button class="layui-btn" data-type="getOpen">全部展开</button>
                        <button class="layui-btn" data-type="getClose">全部折叠</button>
                    </div>

                    <table class="layui-hide" id="test-table-page" lay-filter="test-table-operate"></table>

                    <script type="text/html" id="test-table-operate-barDemo">
                        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">设置子集</a>
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
        base: '/static/common/layuiadmin/modules/'
    }).extend({
        treetable: 'treetable/treetable'
    }).use(['layer', 'table', 'treetable'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var layer = layui.layer;
        var treetable = layui.treetable;
        var form = layui.form;

        // 渲染表格
        var renderTable = function () {
            layer.load(2);
            treetable.render({
                treeColIndex: 1,
                treeSpid: 0,
                treeIdName: 'id',
                treePidName: 'bossId',
                treeDefaultClose: true,
                treeLinkage: false,
                elem: '#test-table-page',
                url: '/permission/findAll',
                page: false,
                cols: [[
                    {type: 'numbers', title: '序号', align: 'center'},
                    {field: 'desc', title: '权限描述', align: 'center'},
                    {field: 'url', title: '权限路径', align: 'center'},
                    {align: 'center', title: '操作', fixed: 'right', toolbar: '#test-table-operate-barDemo'}
                ]],
                done: function () {
                    layer.closeAll('loading');
                }
            });
        };

        renderTable();

        table.on('tool(test-table-operate)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            var tr = obj.tr;

            if (layEvent === 'detail') {
                layer.msg("设置子集");
                console.log(123)
            } else if (layEvent === 'del') {

            } else if (layEvent === 'edit') {

            }
        });

        var $ = layui.$, active = {
            //添加角色
            getCheckData: function () {
                layer.msg("添加权限");

            },
            getOpen: function () {
                treetable.expandAll('#test-table-page');

            },
            getClose: function () {
                treetable.foldAll('#test-table-page');

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