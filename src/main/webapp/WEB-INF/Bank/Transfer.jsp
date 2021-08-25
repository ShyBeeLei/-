<%--
  Created by IntelliJ IDEA.
  User: 86748
  Date: 2021/8/5
  Time: 22:22
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
                    <img src="${pageContext.request.contextPath}/static/img/AvA.JPG" class="layui-nav-img">
                    <span>顶碗大魔王</span>
                </a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-blue">
        <ul class="layui-nav layui-nav-tree layui-bg-blue">
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/MainMenu">查询余额</a></li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/Deposit">存款</a></li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/Withdrawals">取款</a></li>
            <li class="layui-nav-item layui-this"><a href="javascript:">转账</a></li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/">退出登录</a></li>
        </ul>
    </div>
    <div class="layui-body">
        <div class="layui-bg-gray" style="padding: 20px">
            <div class="layui-container">
                <div class="layui-row">
                    <div class="layui-col-md1 layui-col-md-offset2">
                        <label for="targetUser" class="layui-form-label">
                            用户名：
                        </label>

                    </div>
                    <div class="layui-col-md5">
                        <input id="targetUser" class="layui-input" placeholder="请输入收款方用户名"/>
                    </div>
                    <label id="result" class="layui-col-md2" style="font-size: 20px"></label>
                </div>
                <div class="layui-row">
                    <div class="layui-col-md1 layui-col-md-offset2">
                        <label for="money" class="layui-form-label" style="margin-top: 10px">
                            转账金额：
                        </label>
                    </div>
                    <div class="layui-col-md5">
                        <input id="money" class="layui-input" placeholder="请输入您要转账的金额" style="margin-top: 10px"/>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-md1 layui-col-md-offset2">
                        <label for="password" class="layui-form-label" style="margin-top: 10px">
                            确认登录：
                        </label>
                    </div>
                    <div class="layui-col-md5">
                        <input id="password" class="layui-input" type="password" placeholder="请输入您的密码"
                               style="margin-top: 10px"/>
                    </div>
                </div>
                <div class="layui-row">
                    <button class="layui-btn layui-col-md-offset3 layui-col-md5" id="TransferBtn"
                            style="margin-top: 10px">确认
                    </button>
                </div>
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
    let targetFlag;
    let layer = layui.layer;
    $(function () {
        $("#TransferBtn").click(function () {
            let target = $("#targetUser").val();
            let pass = $("#password").val();
            let amount = $("#money").val();
            if (targetFlag) {
                $.ajax(
                    {
                        url: "${pageContext.request.contextPath}/transfer",
                        data: {"targetUser": target, "password": pass, "amount": amount},
                        type: "POST",
                        success: function (result) {
                            layer.alert(result)
                        }
                    }
                )
            } else {
                layer.alert("目标用户不存在！")
            }


        })
        $("#targetUser").blur(function () {
            let target = $("#targetUser").val();

            if (target !== "") {
                $.ajax(
                    {
                        url: "${pageContext.request.contextPath}/verifyUsername",
                        data: "username=" + target,
                        type: "GET",
                        success: function (exist) {
                            if (exist === "true") {
                                $("#result").text("")
                                targetFlag = true;
                            } else {
                                $("#result").text("*用户不存在！").css("color", "red")
                                targetFlag = false;
                            }
                        }
                    }
                )
            } else {
                $("#result").text("")
                targetFlag = true;
            }
        })
    })
</script>
</body>
</html>