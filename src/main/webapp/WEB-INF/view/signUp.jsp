<%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/29
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta charset="utf-8">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mglobal.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive-nav.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mbase.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>

    <title>用户注册</title>

    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script>
        $(document).ready(function () {

            var nameView = $("#name");
            var namePrompt = $("#namePrompt");

            var mobilePhoneNumberView = $("#mobilePhoneNumber");
            var mobilePhoneNumberPrompt = $("#mobilePhoneNumberPrompt");

            var passwordView = $("#password");
            var passwordPrompt = $("#passwordPrompt");

            var name = nameView.val();
            var mobilePhoneNumber = mobilePhoneNumberView.val();
            var password = passwordView.val();

            var errorView = $("#error");

            nameView.focus(function () {
                namePrompt.addClass("changebgimg");
                errorView.text("");
            });
            nameView.blur(function () {
                namePrompt.removeClass("changebgimg");
            });

            mobilePhoneNumberView.focus(function () {
                mobilePhoneNumberPrompt.addClass("changebgimg");
                errorView.text("");
            });
            mobilePhoneNumberView.blur(function () {
                mobilePhoneNumberPrompt.removeClass("changebgimg");
            });

            passwordView.focus(function () {
                passwordPrompt.addClass("changebgimg");
                mobilePhoneNumber = mobilePhoneNumberView.val();
                if ("" === mobilePhoneNumber || 11 !== mobilePhoneNumber.length) {
                    errorView.text("请输入正确格式的手机号码");
                }
            });
            passwordView.blur(function () {
                passwordPrompt.removeClass("changebgimg");
            });

            $("#sign-up-button").click(function () {

                name = nameView.val();
                mobilePhoneNumber = mobilePhoneNumberView.val();
                password = passwordView.val();

                if ("" === name || name.length < 2) {
                    errorView.text("请输入正确格式的姓名");
                    nameView.focus();
                    return;
                }

                if ("" === mobilePhoneNumber || 11 !== mobilePhoneNumber.length) {
                    errorView.text("请输入正确格式的手机号码");
                    mobilePhoneNumberView.focus();
                    return;
                }

                if ("" === password || password.length < 6) {
                    errorView.text("请输入正确格式的密码");
                    mobilePhoneNumberView.focus();
                    return;
                }

                errorView.text("注册信息校验中...");

                $.ajax("${pageContext.request.contextPath}/user/signUp",
                    {
                        dataType: "json",
                        type: "post",
                        contentType: "application/json",
                        data: JSON.stringify(
                            {
                                name: name,
                                mobilePhoneNumber: mobilePhoneNumber,
                                password: password
                            }
                        ),
                        async: true,
                        success: function (data) {
                            if (1 === data.status) {
                                errorView.text(data.message);
                                window.location.href = "${pageContext.request.contextPath}/mvc/login";
                            } else {
                                errorView.text(data.message);
                            }
                        }
                    }
                );
            });
        });
    </script>
</head>
<body>

<div class="header nobg" id="header">
    <div class="banxin">
        <a href="index.jsp" class="logo"><img
                src="${pageContext.request.contextPath}/images/logo.png" alt="王振琦" class="logo1"
                title="儒墨未来团队 - 王振琦"/></a>
        <div class="header-right" id="min-nav">
            <div class="login-register">
                <a class="" href="${pageContext.request.contextPath}/mvc/login">登录</a>
                <a class="" href="${pageContext.request.contextPath}/mvc/signUp">注册</a>
            </div>
            <ul class="nav" id="nav">
                <li class=""><span></span><a href="${pageContext.request.contextPath}/mvc/index">首页</a></li>
                <li class=""><span></span><a href="">产品</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="section1">
    <div class="main">
        <p class="biaoti">注 册</p>
        <form name="signUpForm" method="POST" action="" onsubmit="return false"
              id="user-form-register">
            <div class="form-group username">
                <span id="namePrompt"></span>
                <input type="text" class="form-control nameInput"
                       id="name" placeholder="真实姓名"/>
            </div>
            <div class="form-group phone-number">
                <span id="mobilePhoneNumberPrompt"></span>
                <input type="text" id="mobilePhoneNumber"
                       class="form-control" placeholder="手机号码"/>
            </div>
            <div class="form-group pswd">
                <span id="passwordPrompt"></span>
                <input type="password" class="form-control pwInput"
                       id="password" placeholder="密码"/>
            </div>
            <p id="error" class="tips"></p>
            <p class="pub-hintsBoxError" style="display:none;"></p>
            <div class="form-group">
                <button type="button" class="btn btn-default form-control registerClick"
                        id="sign-up-button">注册</button>
            </div>
        </form>
        <p class="agreement">
            点击注册，即同意王振琦的<a href="">《用户注册协议》</a>
        </p>
    </div>
</div>
<div class="record"><span>©</span> 2016 - 2017  儒墨未来团队 王振琦</div>
</body>
</html>
