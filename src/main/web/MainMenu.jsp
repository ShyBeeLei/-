<%--
  Created by IntelliJ IDEA.
  User: 86748
  Date: 2021/8/1
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="//unpkg.com/layui@2.6.8/dist/css/layui.css">
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
            <li class="layui-nav-item layui-this"><a href="javascript:">查询余额</a></li>
            <li class="layui-nav-item"><a href="Deposit.jsp">存款</a></li>
            <li class="layui-nav-item"><a href="Withdrawals.jsp">取款</a></li>
            <li class="layui-nav-item"><a href="Transfer.jsp">转账</a></li>
            <li class="layui-nav-item"><a href="LogIn.jsp">退出登录</a></li>
        </ul>
    </div>
    <div class="layui-body">
        <div class="layui-bg-gray" style="padding: 20px">
            <div class="layui-container">
                <div class="layui-col-md1 layui-col-md-offset2">
                    <label id="loginVerify" for="inquiryPassword" class="layui-form-label">
                        登录验证：
                    </label>
                </div>
                <div class="layui-col-md5">
                    <input name="inquiryPassword" id="inquiryPassword" class="layui-input" placeholder="请输入您的密码"
                           required="required"/>
                </div>
                <button id="inquiryBtn" class="layui-btn" style="margin-left: 10px;" onclick="showBalance()">提交查询
                </button>
            </div>
        </div>
    </div>
    <div class="layui-footer">
        广告位招租
    </div>
</div>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="//unpkg.com/layui@2.6.8/dist/layui.js"></script>
<script>
    $(function () {
        $("#inquiryPassword").blur(function () {
            var pass = $("#inquiryPassword").val();

            $.ajax(
                {
                    url: "InquiryServlet",
                    data: "inquiryPassword=" + pass,
                    type: "GET",
                    success: function (result) {
                        window.data = result;
                    }
                }
            )
        })
    })

    function showBalance() {
//页面层
        layui.layer.open({
            title: '查询余额',
            type: 1,
            id: 'inquiryPanel',
            skin: 'layui-layer-rim', //加上边框
            area: ['300px', '150px'], //宽高
            content: '<label class="layui-col-lg-offset3">' + window.data + '</label>',
            btn: '好的',
            btnAlign: 'c',
            shade: 0,
        });
    }
</script>
</body>
</html>
