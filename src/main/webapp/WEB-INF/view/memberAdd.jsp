<%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/29
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta charset="utf-8">
    <title>新增成员</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-slider.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive-nav.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datalist.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pay.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/zTreeStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-switch.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquerysession.js"></script>
    <script>
        $(document).ready(function () {
            var userString = $.session.get("current-user");
            if (undefined === userString || null === userString || "" === userString) {
                window.location.href = "${pageContext.request.contextPath}/mvc/login";
            }
            var user = JSON.parse(userString);

            $("#submit-button").click(function () {
                $.ajax("${pageContext.request.contextPath}/member/save",
                    {
                        dataType: "json",
                        type: "post",
                        contentType: "application/json",
                        data: JSON.stringify(
                            {
                                name: $("#name").val(),
                                leader:
                                    {
                                        id: user.id,
                                    },
                                mobilePhoneNumber: $("#mobile-phone-number").val(),
                                workExperience: $("#work-experience").val(),
                                annualSalary: $("#annual-salary").val(),
                                graduatedFrom: $("#graduated-from").val(),
                                highestEducation: $("#education").val(),
                                teamPosition: $("#team-position").val()
                            }
                        ),
                        async: true,
                        success: function (data) {
                            if (1 === data.status) {
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
        <li class="on"><a href="${pageContext.request.contextPath}/mvc/team">团队</a></li>
        <li><a href="${pageContext.request.contextPath}/mvc/userInfo">个人</a></li>
    </ul>
</div>

<div class="main-left">
    <div>
        <div>
            <div>
                <p class="biaoti pd1"><b>团队管理</b></p>
            </div>
            <ul class="subnav">
                <li class="on"><a href="${pageContext.request.contextPath}/mvc/teamManage">团队管理</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="main-right container">
    <div class="lists box-sh return-finace">
        <a class="glyphicon glyphicon-arrow-left" href="${pageContext.request.contextPath}/mvc/team"></a>
        <span>新增成员</span>
    </div>

    <div class="mt order-table">
        <form class="form-horizontal info-edit" role="form">
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">真实姓名</label>
                <div class="col-sm-6">
                    <input type="text" id="name" name="name" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">手机号码</label>
                <div class="col-sm-6">
                    <input type="text" id="mobile-phone-number" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">经验(/年)</label>
                <div class="col-sm-6">
                    <input type="text" id="work-experience" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">年薪(/万)</label>
                <div class="col-sm-6">
                    <input type="text" id="annual-salary" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">毕业院校</label>
                <div class="col-sm-6">
                    <input type="text" id="graduated-from" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">最高学历</label>
                <div class="col-sm-6">
                    <input type="text" id="education" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">团队职务</label>
                <div class="col-sm-6">
                    <input type="text" id="team-position" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-lg-offset-1 col-sm-10 ">
                    <button id="submit-button" type="button" class="btn bg-blue save-btn">添加</button>
                </div>
            </div>
        </form>
    </div>

    <div id="treeNodes" class=""></div>

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
    <button id="positive-button" type="button" class="close" aria-hidden="true">&times;
    </button>
    <div id="msg-success"></div>
</div>

<div id="msg-error-div" class="alert alert-danger alert-dismissable" style="display: none;">
    <button id="navigate-button" type="button" class="close" aria-hidden="true">&times;
    </button>
    <div id="msg-error"></div>
</div>
</body>
</html>

