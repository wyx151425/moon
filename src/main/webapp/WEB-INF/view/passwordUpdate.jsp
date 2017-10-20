<%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/30
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta charset="utf-8">
    <title>密码修改</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png"
          type="image/x-icon"/>
    <link rel="shortcut icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/images/favicon.png"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-slider.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive-nav.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datalist.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pay.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquerysession.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var userString = $.session.get("current-user");
            <%--if (undefined === userString || null === userString || "" === userString) {--%>
                <%--window.location.href = "${pageContext.request.contextPath}/mvc/login";--%>
            <%--}--%>
            var user = JSON.parse(userString);

            $("#update-button").click(function () {
                var promptView = $("#prompt");
                promptView.text("");

                var oldPassword = $("#old-password").val();
                var newPassword = $("#new-password").val();
                var repeatNewPassword = $("#repeat-new-password").val();

//                if (!(user.password === oldPassword)) {
//                    promptView.text("请输入正确的原密码");
//                    return;
//                }
//
//                if (!(newPassword === repeatNewPassword)) {
//                    promptView.text("新密码两次输入不相同");
//                    return;
//                }
//
//                if (newPassword.length < 6) {
//                    promptView.text("请输入正确格式的新密码");
//                    return;
//                }

                // 之前是在请求url后面贴参数的post请求
                $.ajax("${pageContext.request.contextPath}/user/password/update",
                    {
                        dataType: "json",
                        type: "post",
                        contentType: "application/json",
                        data: JSON.stringify(
                            {
                                id: user.id,
                                oldPassword: oldPassword,
                                newPassword: newPassword
                            }
                        ),
                        async: true,
                        success: function (data) {
                            $("#msg-success-div").css("display", "block");
                            $("#msg-success").text("更新成功");
                            user.password = data.password;
                        },
                        error: function (data) {
                            $("#msg-error-div").css("display", "block");
                            $("#msg-error").text(JSON.parse(data.responseText).message);
                        }
                    }
                );
            });

            $("#positive-button").click(function () {
                $("#msg-success-div").css("display", "none");
                $("#msg-success").text("");
            });

            $("#navigate-button").click(function () {
                $("#msg-error-div").css("display", "none");
                $("#msg-error").text("");
            });
        });
    </script>
</head>
<body>

<div id="nav" class="sidenav">
    <ul>
        <li><a href="${pageContext.request.contextPath}/mvc/index">首页</a></li>
        <li><a href="${pageContext.request.contextPath}/mvc/team">团队</a></li>
        <li class="on"><a href="${pageContext.request.contextPath}/mvc/userInfo">个人</a></li>
    </ul>
</div>

<div class="main-left">
    <div>
        <div>
            <div>
                <p class="biaoti pd1"><b>个人管理</b></p>
            </div>
            <ul class="subnav">
                <li><a href="${pageContext.request.contextPath}/mvc/userInfo">个人信息</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/team">团队管理</a></li>
                <li class="on"><a href="${pageContext.request.contextPath}/mvc/passwordUpdate">密码修改</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="main-right container">
    <div class="lists order-table user-pswd">
        <form id="user-form" action=""
              method="post" class="form-horizontal" role="form" name="OnlinePasswordModifyForm" onsubmit="return false">
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">原密码：</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="password" class="form-control" placeholder="必填" id="old-password"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">新密码：</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="password" class="form-control" placeholder="必填" id="new-password"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd mt13 col-lg-1">确认新密码：</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="password" class="form-control" placeholder="必填" id="repeat-new-password"/>
                    <p id="prompt" class="tips"></p>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-1 col-sm-10 col-lg-5">
                    <button type="button" class="btn bg-blue save-btn" id="update-button">确定</button>
                </div>
            </div>
        </form>
    </div>

    <div class="footer">
        <div class="footer-left pull-left">
            <a href="${pageContext.request.contextPath}/mvc/index">
                <img src="${pageContext.request.contextPath}/images/logo.png" alt="王振琦"/>
            </a>
        </div>
        <div class="footer-right pull-right">
            <a href="">2014级软件工程三班王振琦</a>
        </div>
    </div>
</div>
<div id="msg-success-div" class="alert alert-success alert-dismissable" style="display: none;">
    <button id="positive-button" type="button" class="close" aria-hidden="true">&times;</button>
    <div id="msg-success"></div>
</div>

<div id="msg-error-div" class="alert alert-danger alert-dismissable" style="display: none;">
    <button id="navigate-button" type="button" class="close" aria-hidden="true">&times;</button>
    <div id="msg-error"></div>
</div>
</body>
</html>
