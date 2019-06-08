<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册 - layuiAdmin</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/login.css" media="all">
</head>
<body>


<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>layuiAdmin</h2>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username"
                       for="LAY-user-login-nickname"></label>
                <input type="text" name="nickname" id="userName" lay-verify="nickname" placeholder="用户名"
                       class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                       for="LAY-user-login-password"></label>
                <input type="password" name="password" id="password" lay-verify="pass" placeholder="密码"
                       class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                       for="LAY-user-login-repass"></label>
                <input type="password" name="repass" id="password1" lay-verify="required" placeholder="确认密码"
                       class="layui-input">
            </div>

            <div class="layui-form-item">
                <input type="checkbox" name="agreement" lay-skin="primary" title="同意用户协议" checked>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-reg-submit">注 册</button>
            </div>
            <div class="layui-trans layui-form-item layadmin-user-login-other">
                <label>社交账号注册</label>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>

                <a href="login" class="layadmin-user-jump-change layadmin-link layui-hide-xs">用已有帐号登入</a>
                <a href="login.html"
                   class="layadmin-user-jump-change layadmin-link layui-hide-sm layui-show-xs-inline-block">登入</a>
            </div>
        </div>
    </div>

    <div class="layui-trans layadmin-user-login-footer">
        <p>© 2018 <a href="http://www.layui.com/" target="_blank">layui.com</a></p>
    </div>

</div>

<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/common/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'user'], function () {
        var $ = layui.$
            , setter = layui.setter
            , admin = layui.admin
            , form = layui.form
            , router = layui.router();

        form.render();

        //提交
        form.on('submit(LAY-user-reg-submit)', function (obj) {
            var field = obj.field;

            //确认密码
            if (field.password !== field.repass) {
                return layer.msg('两次密码输入不一致');
            }

            //是否同意用户协议
            if (!field.agreement) {
                return layer.msg('你必须同意用户协议才能注册');
            }

            var userName = $('#userName').val();
            var password = $('#password').val();

            $.getJSON('/register', {
                userName: userName,
                password: password
            }, function (res) {
                if (res.success) {
                    layer.msg(res.msg, {time: 2000, icon: 1});
                    var int = self.setInterval(function () {
                        window.location.href = 'login';
                    }, 2000)
                }else {
                    layer.msg(res.msg, {time: 2000, icon: 2});
                }

            })

        });
    });
</script>
</body>
</html>