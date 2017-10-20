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

    <title>用户登录</title>

    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquerysession.js"></script>
    <script>
        $(document).ready(function () {

            var mobilePhoneNumberView = $("#mobile-phone-number");
            var mobilePhoneNumberPrompt = $("#mobile-phone-number-prompt");

            var passwordView = $("#password");
            var passwordPrompt = $("#password-prompt");

            var mobilePhoneNumber = mobilePhoneNumberView.val();
            var password = passwordView.val();

            var errorView = $("#error");

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

            $("#login-button").click(function () {

                mobilePhoneNumber = mobilePhoneNumberView.val();
                password = passwordView.val();

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

                errorView.text("登录信息校验中...");

                $.ajax("${pageContext.request.contextPath}/user/login",
                    {
                        dataType: "json",
                        type: "post",
                        contentType: "application/json",
                        data: JSON.stringify(
                            {
                                mobilePhoneNumber: mobilePhoneNumber,
                                password: password
                            }
                        ),
                        async: true,
                        success: function (data) {
                            errorView.text("登录成功");
                            alert(JSON.stringify(data));
                            $.session.set("current-user", JSON.stringify(data));
                            window.location.href = "${pageContext.request.contextPath}/mvc/team";
                        },
                        error: function (data) {
                            alert(JSON.stringify(data));
                            errorView.text(JSON.parse(data.responseText).message);
                        }
                    }
                );
            });
        });
    </script>
</head>
<body>
<div class="header" id="header">
    <div class="banxin">
        <a href="index.jsp" class="logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="王振琦"
                 class="logo1" title="儒墨未来团队 - 王振琦"/>
        </a>
        <div class="header-right" id="min-nav">
            <div class="login-register">
                <a class="" href="${pageContext.request.contextPath}/mvc/login">登录</a>
                <a class="on" href="${pageContext.request.contextPath}/mvc/signUp">注册</a>
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

        <p class="biaoti"><b>登 录</b></p>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="mail">

                <form>
                    <div class="form-group phone-number">
                        <span id="mobile-phone-number-prompt"></span>
                        <input type="text" id="mobile-phone-number" name="mobilePhoneNumber"
                               class="form-control" placeholder="手机号码"/>
                    </div>
                    <div class="form-group pswd">
                        <span id="password-prompt"></span>
                        <input type="password" class="form-control pwInput"
                               name="password" id="password" Placeholder="密码"/>
                    </div>

                    <div class="form-group">
                        <div class="checkbox pull-left nomr">
                            <label>
                                <input type="checkbox" class="checkbox remember"
                                       value="1"/>保存信息
                            </label>
                        </div>
                        <a href="" class="pull-right forget">忘记密码？</a>
                    </div>

                    <p id="error" class="tips"></p>

                    <div class="form-group">
                        <button type="button" class="btn btn-default form-control" id="login-button">登录
                        </button>
                    </div>

                </form>

                <div class="agreement">
                    没有账号？<a href="${pageContext.request.contextPath}/mvc/signUp">免费注册&gt;&gt;</a>
                </div>

            </div>
        </div>
    </div>
</div>
<div class="record"><span>©</span> 2016 - 2017 儒墨未来团队 王振琦</div>
</body>
</html>