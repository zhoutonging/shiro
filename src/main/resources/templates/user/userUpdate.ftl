<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>编辑用户</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body" style="padding: 15px;">
            <div class="layui-form " action="" lay-filter="component-form-group">
                <blockquote class="layui-elem-quote layui-text">
                        <span class="layui-breadcrumb">
                          <span>请填写用户基本信息</span>
                        </span>
                </blockquote>
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="userName" lay-verify="required" autocomplete="off" placeholder="请输入用户名"
                               class="layui-input" id="name">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">旧密码</label>
                    <div class="layui-input-inline">
                        <input type="text" name="oldPassword" autocomplete="off"
                               placeholder="请输入旧密码"
                               class="layui-input" id="oldPassword">
                    </div>
                    <span><i style="color: red">*</i>如果不填写则代表不修改密码</span>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" lay-verify="pass" placeholder="请输入密码" autocomplete="off"
                               class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password1" lay-verify="pass" placeholder="请确认密码" autocomplete="off"
                               class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
                </div>

                <div class="layui-card layui-form-item" lay-filter="component-form-element">
                    <blockquote class="layui-elem-quote layui-text">
                        <span class="layui-breadcrumb">
                          <span>请选择用户角色</span>
                        </span>
                    </blockquote>
                    <div class="layui-col-md12" id="roleList" style="margin-top: 20px"></div>
                </div>

                <div class="layui-form-item layui-layout-admin">
                    <div class="layui-input-block">
                        <div class="layui-footer" style="left: 0;">
                            <button class="layui-btn" lay-submit="" lay-filter="component-form-demo1">立即提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script>
    //接收父页面传值
    var userInfo;

    function child(data) {
        userInfo = data;
    }

    layui.config({
        base: '/static/common/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'form', 'laydate'], function () {
        var $ = layui.$, admin = layui.admin, element = layui.element, layer = layui.layer, laydate = layui.laydate,
            form = layui.form;

        form.render(null, 'component-form-group');

        laydate.render({
            elem: '#LAY-component-form-group-date'
        });

        //赋值
        $('#name').val(userInfo.name);

        //查询用户所拥有的角色
        var roleLists = [];
        $.get('/role/findByUserId', {userId: userInfo.id}, function (res) {
            roleLists = res.data;
        });

        //查询角色列表
        $.get('/role/findAll', function (res) {
            var data = res.data;
            var html = [];
            $.each(data, function (index, item) {
                //如果用户拥有该角色则默认选中
                for (var i = 0; i < roleLists.length; i++) {
                    if (roleLists[i].id == item.id) {
                        html += '<input type="checkbox" checked   name="role" id=' + item.id + ' title=' + item.desc + '>';
                        return;
                    }

                }
                html += '<input type="checkbox"    name="role" id=' + item.id + ' title=' + item.desc + '>';
                return;
            });


            $("#roleList").html(html);

            form.render();
        });


        var index = parent.layer.getFrameIndex(window.name);

        form.on('submit(component-form-demo1)', function (data) {

            if (data.field.password != data.field.password1) {
                layer.msg('两次密码输入不一致', {time: 2000, icon: 2});
                return;
            }

            var roles = new Array();
            $("input[name='role']:checked").each(function () {
                roles.push(this.id);
            });

            var oldPassword = $('#oldPassword').val();

            $.post('/user/modifyUserById', {
                id: userInfo.id,
                roles: JSON.stringify(roles),
                name: data.field.userName,
                password: data.field.password,
                oldPassword: oldPassword,
                salt: userInfo.salt
            }, function (res) {
                if (res.code == 0) {
                    layer.msg(res.msg, {time: 2000, icon: 1});
                    var int = self.setInterval(function () {
                        parent.layer.close(index);
                        parent.location.reload();
                    }, 2000)
                } else {
                    layer.msg(res.msg, {time: 2000, icon: 2});
                }
            });
        });
    });
</script>
</body>
</html>
