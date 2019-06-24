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

    .layui-icon-layer:before {
        content: "\e656"
    }

    .layui-icon-triangle-d:before {
        content: ""
    }

    .layui-icon-file:before {
        content: "\e66e"
    }
</style>
<body>

<!--添加主分类-->
<div id="addIndustry" class="x-body none" style="display: none;margin-top: 20px">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="addIndustryName" class="layui-form-label"><span class="x-red">*</span>权限名称</label>
            <div class="layui-input-inline">
                <input type="text" id="roleName" name="roleName" required="" lay-verify="required"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-top: 40px;">
            <div class="layui-input-block">
                <button class="layui-btn" lay-filter="edit" lay-submit="" id="add">提交</button>
                <a class="layui-btn" lay-filter="edit" id="cancelAdd">取消</a>
            </div>
        </div>
    </form>
</div>

<!--添加子权限-->
<div id="addSon" class="x-body none" style="display: none;margin-top: 20px">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="addIndustryName" class="layui-form-label"><span class="x-red">*</span>权限名称</label>
            <div class="layui-input-inline">
                <input type="text" id="roleNameSon" name="roleNameSon" required="" lay-verify="required"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="addIndustryName" class="layui-form-label"><span class="x-red">*</span>URL</label>
            <div class="layui-input-inline">
                <input type="text" id="url" name="url" required="" lay-verify="required"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-top: 40px;">
            <div class="layui-input-block">
                <button class="layui-btn" lay-filter="edit" lay-submit="" id="addSonSubmit">提交</button>
                <a class="layui-btn" lay-filter="edit" id="cancelSon">取消</a>
            </div>
        </div>
    </form>
</div>

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
                        <button class="layui-btn" data-type="getCheckData">添加权限分类</button>
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
                layer.open({
                    title: "添加权限",
                    type: 1,
                    content: $("#addSon"),
                    area: ['380px', '300px'],
                    cancel: function () {
                        $('#addSon').css("display", "none");
                    }
                });

                $('#cancelSon').click(function () {
                    layer.closeAll();
                    $('#addISon').css("display", "none");
                });

                $('#addSonSubmit').click(function () {
                    var bossId = data.id;
                    var name = $('#roleNameSon').val();
                    var url = $('#url').val();

                    $.get('/permission/saveSon', {name: name, bossId: bossId, url: url}, function (res) {
                        if (res.code == 0) {
                            layer.msg(res.msg, {time: 2000, icon: 1});
                            var int = self.setInterval(function () {
                                layer.closeAll();
                                renderTable();
                            }, 2000)
                        } else {
                            layer.msg(res.msg, {time: 2000, icon: 2});
                        }
                    });
                    console.log(data)
                });

            } else if (layEvent === 'del') {

            } else if (layEvent === 'edit') {

            }
        });

        var $ = layui.$, active = {
            getCheckData: function () {
                layer.open({
                    title: "添加权限分类",
                    type: 1,
                    content: $("#addIndustry"),
                    area: ['380px', '200px'],
                    cancel: function () {
                        $('#addIndustry').css("display", "none");
                    }
                });

                $('#cancelAdd').click(function () {
                    layer.closeAll();
                    $('#addIndustry').css("display", "none");
                });

                //添加权限分类
                $('#add').click(function () {
                    var name = $('#roleName').val();
                    if (name == '') {
                        layer.msg('权限分类不能为空', {time: 2000, icon: 2});
                        return;
                    }
                    $.get('/permission/save', {name: name}, function (res) {
                        if (res.code == 0) {
                            layer.msg(res.msg, {time: 2000, icon: 1});
                            var int = self.setInterval(function () {
                                layer.closeAll();
                                renderTable();
                            }, 2000)
                        } else {
                            layer.msg(res.msg, {time: 2000, icon: 2});
                        }
                    });
                });
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