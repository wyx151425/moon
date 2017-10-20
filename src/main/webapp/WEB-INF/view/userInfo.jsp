<%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/29
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta charset="utf-8">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon"/>
    <title>个人信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-slider.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive-nav.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datalist.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pay.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquerysession.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
    <script>
        $(document).ready(function () {
            var userString = $.session.get("current-user");
            if (undefined === userString || null === userString || "" === userString) {
                window.location.href = "${pageContext.request.contextPath}/mvc/login";
            }
            var user = JSON.parse(userString);

            $("#name").val(user.name);
            $("#mobile-phone-number").text(user.mobilePhoneNumber);
            $("#work-experience").val(user.workExperience);
            $("#annual-salary").val(user.annualSalary);
            $("#graduated-from").val(user.graduatedFrom);
            $("#education").val(user.highestEducation);
            $("#team-position").val(user.teamPosition);
            $("#create-time").val(user.createTime);
            
            $("#submit-button").click(function () {
                $.ajax("${pageContext.request.contextPath}/user/info/update",
                    {
                        dataType: "json",
                        type: "post",
                        contentType: "application/json",
                        data: JSON.stringify(
                            {
                                id: user.id,
                                name: $("#name").val(),
                                workExperience: $("#work-experience").val(),
                                annualSalary: $("#annual-salary").val(),
                                graduatedFrom: $("#graduated-from").val(),
                                highestEducation: $("#education").val(),
                                teamPosition: $("#team-position").val()
                            }
                        ),
                        async: true,
                        success: function (data) {
                            if (1 === data) {
                                $("#msg-success-div").css("display", "block");
                                $("#msg-success").text(data.message);
                            } else {
                                $("#msg-error-div").css("display", "block");
                                $("#msg-error").text(data.message);
                            }
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
                <li class="on"><a href="${pageContext.request.contextPath}/mvc/userInfo">个人信息</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/teamManage">团队管理</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/passwordUpdate">密码修改</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="main-right container">
    <div class="lists box-sh order-table">
        <h5><b>请务必完善个人信息</b></h5>
        <ul class="file-dep">
        </ul>
    </div>

    <div class="mt order-table user-content">

        <form class="form-horizontal" id="user-form" role="form" action="" name="accountInfoForm"
              onsubmit="return false" method="post">

            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1">手机号码</label>
                <div class="col-sm-10 col-lg-5">
                    <span id="mobile-phone-number"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">真实姓名</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" id="name" placeholder="必填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">经验(/年)</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" id="work-experience" placeholder="不大于2位">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">年薪(/万)</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" id="annual-salary"  placeholder="不大于6位">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">毕业院校</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" id="graduated-from" placeholder="选填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">最高学历</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" id="education" placeholder="选填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">团队职务</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" id="team-position" placeholder="选填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">创建时间</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" id="create-time" class="form-control" disabled>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-1 col-sm-10 col-lg-5">
                    <button id="submit-button" type="button" class="btn bg-blue save-btn">保存</button>
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
