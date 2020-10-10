<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <%@ include file="/taglibs.jsp"%>

    <html>
    <head>
        <title>设备异常类型设置</title>
        <script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/waterIndicator.js"></script>
        <style type="text/css">
            .device{padding:0 20px 0 20px!important;}
            .device table thead td{height: 35px;}
            .device table tbody tr:nth-child(odd){background:#fff }
            .device table tbody tr:nth-child(even){background:#e6eff5 }
            table td{text-align: center;height: 32px;font-size: 14px;line-height: 32px;}
            table th {height:32px}
            input{margin: 0!important;width: 120px!important;}
            .el-dialog__header{background:#18B399;border-top-left-radius: 6px;border-top-right-radius: 6px;}
            .el-dialog__headerbtn i{color: #fff!important;}
            .el-dialog{border-radius: 6px;}
            .el-button--primary{background:#18B399; }
            .el-button--primary{border-color:#18B399; }
            .el-dialog__title{color: #fff!important;}
        </style>
    </head>
    <body>
    <div id="waterIndicator">
    <div class="operate">
        <ul>
            <li class="add" @click="addWarnInfo(0)">新增</li>
        </ul>
    </div>
    <div class="device">
        <table cellspacing='0' cellpadding='3' class='list-table'>
            <thead>
                <tr>
                    <th>水管区域名称</th>
                    <th>总用水指标</th>
                    <th>农业用水指标</th>
                    <th>绿化用水指标</th>
                    <th>生活用水指标</th>
                    <th>工业用水指标</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(wl,index) in waterList">
                    <td>{{wl.waterAreaName}}</td>
                    <td>{{wl.sumwater}}</td>
                    <td>{{wl.waterfarming}}</td>
                    <td>{{wl.watervirest}}</td>
                    <td>{{wl.waterlife}}</td>
                    <td>{{wl.waterindustry}}</td>
                    <td style="width:100px; padding-left:5px;">
                        <ul style="width:100px;">
                            <li class="edit" title="编辑" @click="editWarnInfo(wl)" data-id='${item.id}' style="position:relative">
                                <span style="position: absolute;top:8px;left:25px;width: 30px;">修改</span>
                            </li>
                        </ul>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <el-dialog :title="title" :visible.sync="dialogVisible" width="55%">
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
            <tr v-for="(tax,index) in pageData">
                <td>{{tax.waterAreaName}}</td>
                <td><input type="number" v-model="tax.sumwater" placeholder="总指标"/></td>
                <td><input type="number" v-model="tax.waterfarming" placeholder="农业用水指标"/></td>
                <td><input type="number" v-model="tax.watervirest" placeholder="绿化用水指标"/></td>
                <td><input type="number" v-model="tax.waterlife" placeholder="生活用水指标"/></td>
                <td><input type="number" v-model="tax.waterindustry" placeholder="工业用水指标"/></td>
            </tr>
            </tbody>
        </table>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="handleClick">确 定 提 交</el-button>
            </span>
    </el-dialog>
</div>
</body>
</html>