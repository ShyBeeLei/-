<%--
  Created by IntelliJ IDEA.
  User: 86748
  Date: 2021/8/1
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="com.cx.bank.model.UserBean" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登陆界面</title>
    <style>
        html {
            height: 100%;
            width: 100%;
            background-color: #1fc8db;
            background-image: linear-gradient(141deg, #9fb8ad 0%, #1fc8db 51%, #2cb5e8 75%);
            opacity: 0.75;
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

        .loginBox .loginBtn {
            background-image: -moz-linear-gradient(to bottom, blue, #85CFEE);
            border: 1px solid #98CCE7;
            border-radius: 20px;
            box-shadow: inset rgba(255, 255, 255, 0.6) 0 1px 1px, rgba(0, 0, 0, 0.1) 0 1px 1px;
            color: #444;
            /*登录*/
            cursor: pointer;
            float: right;
            font: bold 13px Arial;
            padding: 10px 50px;
        }

        .loginBox .loginBtn:HOVER {
            background-color: #0095e8;
            color: white;
        }

        .loginBox input#remember {
            vertical-align: middle;
        }

        .loginBox label[for="remember"] {
            font: 11px Arial;
        }

        .loginBox label[for="loginAsAdmin"] {
            font: 11px Arial;
        }

        .loginBox input#loginAsAdmin {
            vertical-align: middle;
        }

        .loginBox .register {
            font: bold 13px Arial;
            color: rgba(8, 72, 210, 0.53);
        }

        .loginBox .register:hover {
            color: #0848d2;
        }

    </style>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    String username = "", password = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                username = cookie.getValue();
            } else if ("password".equals(cookie.getName())) {
                password = cookie.getValue();
            }
        }
    } else {
        username = " ";
        password = " ";
    }
%>
<div class="wrapper">
    <form action="LoginServlet" method="post">
        <div class="loginBox">
            <div class="loginBoxCenter">
                <p><label for="username">用户名：</label></p>
                <!--autofocus 规定当页面加载时按钮应当自动地获得焦点。 -->
                <!-- placeholder提供pa可描述输入字段预期值的提示信息-->
                <p><input type="text" id="username" name="username" class="loginInput" autofocus="autofocus"
                          required="required" value="<%=username%>"
                          autocomplete="off" placeholder="请输入用户名"/></p>
                <!-- required 规定必需在提交之前填写输入字段-->
                <p><label for="password">密码：</label></p>
                <p><input type="password" id="password" name="password" class="loginInput" required="required"
                          placeholder="请输入密码" value="<%=password%>"/></p>
                <input id="loginAsAdmin" type="checkbox" name="loginAsAdmin"/>
                <label for="loginAsAdmin">管理员登录</label>
                <input id="remember" type="checkbox" name="remember"/>
                <label for="remember">记住登录状态</label>
            </div>
            <div class="loginBoxButtons">
                <button class="loginBtn">登录</button>
                <div><a class="register" href="Register.jsp"> 新用户注册</a></div>
            </div>
        </div>
    </form>
</div>
</body>
</html>