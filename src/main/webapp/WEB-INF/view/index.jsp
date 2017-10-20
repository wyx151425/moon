<%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/29
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta charset="utf-8">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png"
          type="image/x-icon"/>
    <title>王振琦 - 专享博客</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mglobal.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive-nav.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mbase.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mindex.css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>

</head>
<body>

<div class="header nobg" id="header">
    <div class="banxin">
        <a href="" class="logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="王振琦" class="logo1" title="儒墨未来团队 - 王振琦"/>
            <img src="${pageContext.request.contextPath}/images/logo1.png" alt="王振琦" class="logo2" title="儒墨未来团队 - 王振琦"/>
        </a>
        <div class="header-right" id="min-nav">
            <ul class="nav" id="nav">
                <li class="on"><span></span><a href="${pageContext.request.contextPath}/mvc/index">主页</a></li>
                <li class=""><span></span><a href="#intro_self">简介</a></li>
                <li class=""><span></span><a href="#intro_production">作品</a></li>
            </ul>
        </div>
    </div>
</div>

<div id="team" class="section1 section1-min-hidden">
    <div>
        <br/><br/><br/><br/><br/><br/><br/>
        <br/><br/><br/><br/><br/><br/><br/>
        <h1 class="text-center">不忘初心&nbsp;&nbsp;感动人心</h1>
        <br/>
        <div class="more">
            <a href="${pageContext.request.contextPath}/mvc/login">登录</a>
        </div>
    </div>
</div>

<div id="intro_self" class="section2">
    <div class="section2-main-top banxin">
        <div class="main-left">
            <strong>王振琦</strong>
            <p>儒墨未来团队 创始人与负责人，曲阜师范大学软件学院本科在校学生。熟悉C++、Java语言和轻量级Java Web框架，擅长Android + Spring Boot开发。</p>
            <p>参与大学生创新训练校级项目一项；主持过食遇、漫香阁两款Android应用的开发工作，独立开发了个人Android漫画册Nemo。</p>
            <p>获得过第十四届齐鲁大学生软件大赛二等奖；在曲阜师范大学校级评奖评优中获得过“三好学生”、“优秀学生干部”等称号。</p>
        </div>
        <div class="main-right">
            <img src="${pageContext.request.contextPath}/images/wzq.jpg"/>
        </div>
    </div>
</div>

<div id="intro_production" class="section3">
    <div class="section3-main-top">
        <p class="text-center"><strong>个人作品</strong></p>
        <div class="banxin exhibition">
            <ul>
                <li>
                    <img src="${pageContext.request.contextPath}/images/china.png" alt="食遇"/>
                    <span>齐鲁大学生软件大赛Android作品 - 食遇</span>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath}/images/fragrance.png" alt="漫香阁"/>
                    <span>2015-2016学年下学期Android作品 - 漫香阁</span>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath}/images/nemo.png" alt="Nemo"/>
                    <span>个人Android漫画册作品 - Nemo</span>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="record"><span>©</span> 2016 - 2017  儒墨未来团队 王振琦</div>
</body>
</html>
