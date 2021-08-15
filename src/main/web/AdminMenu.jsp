<%--
  Created by IntelliJ IDEA.
  User: 86748
  Date: 2021/8/5
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="//unpkg.com/layui@2.6.8/dist/css/layui.css" media="all">
<html>
<head>
    <title>枝江银行</title>
    <style>
        html {
            height: 100%;
            width: 100%;
            background-image: linear-gradient(141deg, #9199be 0%, #345bb9 51%, #7836e3 75%);
            opacity: 0.75;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header layui-bg-cyan">
        <div class="layui-logo">SOULBANK</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:">
                    <img src="AvA.JPG" class="layui-nav-img">
                    <span>顶碗大魔王</span>
                </a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-blue">
        <ul class="layui-nav layui-nav-tree layui-bg-blue">
            <li class="layui-nav-item layui-this"><a href="javascript:">管理用户</a></li>
            <li class="layui-nav-item"><a href="LogIn.jsp">退出登录</a></li>
        </ul>
    </div>
    <div class="layui-body">
        <div class="layui-bg-gray" style="padding: 20px">
            <div class="layui-container">
                <div class="layui-col-md1">
                    <label for="userId" class="layui-form-label">
                        用户名：
                    </label>
                </div>
                <div class="layui-col-md9">
                    <input id="userId" class="layui-input" placeholder="请输入您要操作的用户"/>
                </div>
                <button data-type="reload" class="layui-btn" type="button" id="search" style="margin-left: 10px;">提交查询
                </button>
            </div>
        </div>
        <table class="layui-hide" id="user" lay-filter="user"></table>
    </div>
    <div class="layui-footer">
        广告位招租
    </div>
</div>


<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="activate">激活</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="freeze">冻结</a>
</script>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="//unpkg.com/layui@2.6.8/dist/layui.js"></script>
<script>
    layui.use('table', function () {
        var table = layui.table

        //第一个实例
        table.render({
            elem: '#user'
            , height: 650
            , url: 'AdminServlet' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'userId', title: '用户编号', width: '25%', sort: true}
                , {field: 'username', title: '用户名', width: '40%', sort: true}
                , {field: 'userFlag', title: '用户状态', width: '25%', sort: true}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: '10%'}
            ]]
        });
        var $ = layui.$, active = {
            reload: function () {
                var demoReload = $('#userId');
                //执行重载
                table.reload('user', {
                    where: {
                        key: {
                            username: demoReload.val()
                        }
                    }, page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            }
        };

        $('#search').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        table.on('tool(user)', function (obj) {
            var data = obj.data;
            var flag;
            var status;
            if (data.userFlag === 1) {
                status = "activate"
                flag = 0
            } else {
                status = "freeze"
                flag = 1
            }
            if (obj.event === status) {
                layer.alert('请勿重复操作!', {
                    time: 20000
                    , btn: '好的'
                })
            } else {
                $.ajax({
                    url: "AdminServlet"
                    , data: {"targetUser": data.userId, "userStatues": data.userFlag}
                    , type: "POST"
                    , success: function (result) {
                        window.data = result;
                    }
                })
                setTimeout(message, 200)

                function message() {
                    layer.alert(window.data, function (index) {
                        obj.update({
                            userFlag: flag
                        })
                        layer.close(index)
                    });
                }
            }
        });
    });
</script>
</body>
</html>
