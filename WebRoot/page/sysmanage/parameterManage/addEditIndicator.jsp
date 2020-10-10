<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
    <title>用水指标新增修改页面</title>
    <script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/waterIndicator.js"></script>
    <style>
        #waterIndicator{padding:0 15px 0 15px}
        table{border-color: #c0ccda}
        table td{text-align: center;height: 32px;font-size: 14px;}
        table th {height:32px}
    </style>
</head>
<body>
<div id="waterIndicator">
    <table width="100%" border="1">
        <thead>
            <tr>
                <th>水管区域名称</th>
                <th>总用水指标(万/m³)</th>
                <th>农业用水指标(万/m³)</th>
                <th>绿化用水指标(万/m³)</th>
                <th>生活用水指标(万/m³)</th>
                <th>工业用水指标(万/m³)</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${list}" varStatus="vs">
                <tr class="${vs.index%2==0?'singular':'double'}">
                    <td>${vs.index}</td>
                    <td><input type="number" v-model="waterList[0].sumwater" placeholder="总指标"/></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>