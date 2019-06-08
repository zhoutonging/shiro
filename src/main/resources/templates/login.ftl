<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登入 - layuiAdmin</title>
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
                       for="LAY-user-login-username"></label>
                <input type="text" name="userName" id="userName" lay-verify="required" placeholder="用户名"
                       class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                       for="LAY-user-login-password"></label>
                <input type="password" name="password" id="password" lay-verify="required"
                       placeholder="密码" class="layui-input">
            </div>
            <#--<div class="layui-form-item">-->
            <#--<div class="layui-row">-->
            <#--<div class="layui-col-xs7">-->
            <#--<label class="layadmin-user-login-icon layui-icon layui-icon-vercode"-->
            <#--for="LAY-user-login-vercode"></label>-->
            <#--<input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required"-->
            <#--placeholder="图形验证码" class="layui-input">-->
            <#--</div>-->
            <#--<div class="layui-col-xs5">-->
            <#--<div style="margin-left: 10px;">-->
            <#--<img src="https://www.oschina.net/action/user/captcha" class="layadmin-user-login-codeimg"-->
            <#--id="LAY-user-get-vercode">-->
            <#--</div>-->
            <#--</div>-->
            <#--</div>-->
            <#--</div>-->
            <div class="layui-form-item" style="margin-bottom: 20px;">
                <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
                <a href="forget.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？</a>
            </div>
            <div >
                <input type="button" class="layui-btn layui-btn-fluid" id="login" value="登录">
            </div>
            <div class="layui-trans layui-form-item layadmin-user-login-other">
                <label>社交账号登入</label>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>
                <a href="reg" class="layadmin-user-jump-change layadmin-link">注册帐号</a>

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
        base: '/static/common/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'user'], function () {
        var $ = layui.$, setter = layui.setter, admin = layui.admin, form = layui.form, router = layui.router(),
            search = router.search;

        // form.render();

        //提交

        $('#login').click(function () {

            var userName = $('#userName').val();
            var password = $('#password').val();

            $.getJSON('/userLogin', {
                userName: userName,
                password: password
            }, function (res) {
                if (res.success) {
                    layer.msg(res.msg, {time: 2000, icon: 1});
                    var int = self.setInterval(function () {
                        window.location.href = 'index';
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