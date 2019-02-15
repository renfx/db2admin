<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include "css.ftl">
    <title>管理后台</title>
</head>
<body class="layui-layout-body">

    <#include "js.ftl">
    <div class="layui-layout layui-layout-admin">
        <#include "header.ftl">

        <#include "navigation.ftl">

        <div class="layui-body">
             <div class="layui-tab app-container" lay-allowClose="true" lay-filter="tabs">
                <ul id="appTabs" class="layui-tab-title custom-tab"></ul>
                <div id="appTabPage" class="layui-tab-content"></div>
            </div>
        </div>

        <#include "footer.ftl">
    </div>
    <script src="/js/home.js" data-main="home"></script>
</body>
</html>