<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
    #Induste{height: 50px;line-height: 50px;box-sizing: border-box;border: solid 1px #b9b9b9;padding-left: 20px;}
    #Induste ul li{list-style: none;float: left}
</style>
<div id="waterContentqwer">
    <div class="right_user" style="left:15px;top:50px">
        <div id="dataHistoryDiv">
            <div class="operate">
                <div style="text-align: left; margin-top: 5px;">
                    <div id="timeSearchArea" style="float: left;">
                        <span style="margin-left:10px;">起始时间：</span>
                        <input id="query_beginTime" class="Wdate" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
                               onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d %H:%m:%s\' }'})" />

                        <span>&nbsp;结束时间：</span>
                        <input id="query_endTime" class="Wdate" value="<fmt:formatDate value="${eTime}" pattern="yyyy-MM-dd HH:mm:ss" />"  type="text"
                               onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d %H:%m:%s'})"/>
                        <!-- <input type="text" id="query_cardCode" placeholder="请输入卡号"/> -->
                        <input hidden="hidden" id="deviceName" value="${deviceNamg}">
                        <input hidden="hidden" id="deviceport" value="${deviceport}">
                        <input type="button" class="btn-search" value="查 询" onclick="IndustSearch(1,1,1)">
                        <input type="button" class="btn-export-report" onclick="IndustReport(1,1)" value="报表导出" >
                    </div>
                </div>
            </div>
            <div class="right_user" style="left:5px;top:40px">
                <div id="Induste" align="left">
                    <span>该时段累计用水量：</span>
                    <span>${compData}</span>
                </div>
                <table cellspacing='0'  cellpadding='3' class='list-table'>
                    <thead>
                    <tr>
                        <th>设备名称</th>
                        <th>设备编码</th>
                        <th>端口号</th>
                        <th>瞬时流量</th>
                        <th>瞬时流速</th>
                        <th>流量百分比</th>
                        <th>流体电导比</th>
                        <th>正向累积值(m³)</th>
                        <th>反向累积值(m³)</th>
                        <th>上报时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty IndustrialDeviceList}">
                        <tr>
                            <td colspan="10">没有相关数据</td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty IndustrialDeviceList}">
                        <c:forEach var="item" items="${IndustrialDeviceList}" varStatus="vs">
                            <tr class="${vs.index%2==0?'singular':'double'}">
                                <td>${item.devicename==null?'--':item.devicename}</td>
                                <td>${item.devicecode==null?'--':item.devicecode}</td>
                                <td>${item.deviceport==null?'--':item.deviceport}</td>
                                <td>${item.instantflow==null?'--':item.instantflow}</td>
                                <td>${item.instantspeed==null?'--':item.instantspeed}</td>
                                <td>${item.flowpercent==null?'--':item.flowpercent}</td>
                                <td>${item.ratiopercent==null?'--':item.ratiopercent}</td>
                                <td>${item.positivetotal==null?'--':item.positivetotal}</td>
                                <td>${item.reversetotal==null?'--':item.reversetotal}</td>
                                <td>
                                    <c:if test="${item.createtime!=null}">
                                        <fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>


