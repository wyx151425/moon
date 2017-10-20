<%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/29
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta charset="utf-8">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon"/>
    <title>团队管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-slider.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive-nav.css?"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datalist.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pay.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquerysession.js"></script>

    <script>
        $(document).ready(function () {
            var userString = $.session.get("current-user");
            if (undefined === userString || null === userString || "" === userString) {
                window.location.href = "${pageContext.request.contextPath}/mvc/login";
            }
            var user = JSON.parse(userString);

            loadData(user.id);

            $("#positive-button").click(function () {
                $("#msg-success-div").css("display", "none");
                $("#msg-success").text("");
            });

            $("#navigate-button").click(function () {
                $("#msg-error-div").css("display", "none");
                $("#msg-error").text("");
            });
        });

        function loadData(id) {
            $.ajax("${pageContext.request.contextPath}/member/list?id=" + id,
                {
                    dataType: "json",
                    type: "get",
                    contentType: "application/json",
                    async: true,
                    success: function (data) {
                        if (1 === data.status) {
                            var tBody = $("#table-body");
                            var _html = '';
                            $.each(data.memberList, function () {
                                _html +=
                                    '<tr member-id="' + this.id + '">' +
                                    '<td>' + this.mobilePhoneNumber + '</td>' +
                                    '<td>' + this.name + '</td>' +
                                    '<td>' + this.createTime + '</td>' +
                                    '<td>' +
                                    '<a class="btn btn-link" href="${pageContext.request.contextPath}/mvc/memberInfo?id=' + this.id + '">编辑</a>' +
                                    '<button type="button" class="btn btn-link btn-del" onclick="deleteMember(' + this.id + ')">删除</button>' +
                                    '</td>' +
                                    '</tr>';
                            });
                            tBody.html(_html);
                        }
                    }
                }
            );
        }

        function deleteMember(id) {
            $.ajax("${pageContext.request.contextPath}/member/delete?id=" + id,
                {
                    dataType: "json",
                    type: "post",
                    contentType: "application/json",
                    success: function (data) {
                        if (1 === data.status) {
//                            $("#msg-success-div").css("display", "block");
//                            $("#msg-success").text(data.message);
                            window.location.href = "${pageContext.request.contextPath}/mvc/teamManage";
                        } else {
                            $("#msg-error-div").css("display", "block");
                            $("#msg-error").text(data.message);
                        }
                    }
                }
            );
        }

//        $("#table-body").on("click", "tr>td", function () {
//            var id = $(this).attr("member-id");
//        });
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
                <li class="on"><a href="${pageContext.request.contextPath}/mvc/teamManage">团队管理</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/passwordUpdate">密码修改</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="main-right container">
    <div class="main-right container">
        <div class="lists box-sh order-table">
            <div class="main-right-bottom">
                <div class="table-responsive">
                    <table id="member-list-table" class="table table-hover table-bordered">
                        <thead id="table-head">
                        <tr class="active">
                            <td>手机号</td>
                            <td>姓名</td>
                            <td>加入时间</td>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <tbody id="table-body"></tbody>
                    </table>
                </div>
                <div class="main-right-top">
                    <form action="${pageContext.request.contextPath}/mvc/team" method="get" class="main-right-cotent">
                        <a href="${pageContext.request.contextPath}/mvc/memberInvite"
                           class="save-btn pull-right  btn mt10 bg-blue">
                            新增成员
                        </a>
                    </form>
                </div>
            </div>
        </div>
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

<div id="msg-error_div" class="alert alert-danger alert-dismissable" style="display: none;">
    <button id="navigate-button" type="button" class="close" aria-hidden="true">&times;</button>
    <div id="msg-error"></div>
</div>
</body>
</html>
