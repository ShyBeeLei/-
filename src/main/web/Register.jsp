<%--
  Created by IntelliJ IDEA.
  User: 86748
  Date: 2021/8/3
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>新用户注册</title>
    <style>
        html {
            background-color: #2cb5e8;
        }

        .wrapper {
            align-content: center;
            margin: 140px 0 140px auto;
            width: auto;
        }

        .loginBox {
            background-color: #F0F4F6;
            /*上divcolor*/
            border: 1px solid #BfD6E1;
            border-radius: 5px;
            color: #444;
            font: 14px 'Microsoft YaHei', '微软雅黑';
            margin: 0 auto;
            width: 388px
        }

        .loginBox .loginBoxCenter {
            border-bottom: 1px solid #DDE0E8;
            padding: 24px;
        }

        .loginBox .loginBoxCenter p {
            margin-bottom: 10px
        }

        .loginBox .loginBoxButtons {
            /*background-color: #F0F4F6;*/
            /*下divcolor*/
            border-top: 0px solid #FFF;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
            line-height: 28px;
            overflow: hidden;
            padding: 20px 24px;
            vertical-align: center;
            filter: alpha(Opacity=80);
            -moz-opacity: 0.5;
            opacity: 0.5;
        }

        .loginBox .loginInput {
            border: 1px solid #D2D9dC;
            border-radius: 2px;
            color: #444;
            font: 12px 'Microsoft YaHei', '微软雅黑';
            padding: 8px 14px;
            margin-bottom: 8px;
            width: 310px;
        }

        .loginBox .loginInput:FOCUS {
            border: 1px solid #B7D4EA;
            box-shadow: 0 0 8px #B7D4EA;
        }

        .loginBox .registerBtn {
            background-image: -moz-linear-gradient(to bottom, blue, #85CFEE);
            border: 1px solid #98CCE7;
            border-radius: 20px;
            box-shadow: inset rgba(255, 255, 255, 0.6) 0 1px 1px, rgba(0, 0, 0, 0.1) 0 1px 1px;
            color: #444;
            width: 100%;
            /*登录*/
            cursor: pointer;
            float: right;
            font: bold 13px Arial;
            padding: 10px 50px;
        }

        .loginBox .registerBtn:HOVER {
            background-color: #0095e8;
            color: white;
        }


    </style>
</head>
<body>
<div class="wrapper">
    <form action="RegisterServlet" method="post">
        <div class="loginBox">
            <div class="loginBoxCenter">
                <p><label for="username">用户名：</label></p>
                <!--autofocus 规定当页面加载时按钮应当自动地获得焦点。 -->
                <!-- placeholder提供可描述输入字段预期值的提示信息-->
                <p><input type="text" id="username" name="username" class="loginInput" autofocus="autofocus"
                          required="required"
                          autocomplete="off" placeholder="请输入用户名"/></p>
                <label id="result"></label>
                <!-- required 规定必需在提交之前填写输入字段-->
                <p><label for="password">密码：</label></p>
                <p><input type="password" id="password" name="password" class="loginInput" required="required"
                          placeholder="请输入密码"/></p>
                <p><label for="confirmPassword">确认密码：</label></p>
                <p><input type="password" id="confirmPassword" name="password" class="loginInput" required="required"
                          placeholder="请再输入一次密码"/></p>
                <label id="passwordCheck"></label>
            </div>
            <div class="loginBoxButtons">
                <button class="registerBtn">注册</button>
            </div>
        </div>
    </form>
</div>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#username").blur(function () {
            var value = $("#username").val();
            // 使用ajax发送请求
            if (value !== "") {
                $.ajax(
                    {
                        url: "RegisterServlet",
                        data: "username=" + value,
                        type: "GET",
                        success: function (result) {
                            console.log(result)
                            if (result === "false") {
                                $("#result").text("")
                            } else {
                                $("#result").text("*用户名重复！").css("color", "red")
                            }
                        }
                    })
            } else {
                $("#result").text("")
            }
        });
        $("#confirmPassword").blur(function () {
                var password = $("#password").val();
                var confirmPassword = $("#confirmPassword").val();
                if (password != null && confirmPassword != null) {
                    if (password !== confirmPassword) {
                        $("#passwordCheck").text("*两次输入的密码不一致！请重新输入").css("color", "red")
                    } else {
                        $("#passwordCheck").text("")
                    }
                } else {
                    $("#passwordCheck").text("")
                }
            }
        )
    })
</script>
</body>
</html>