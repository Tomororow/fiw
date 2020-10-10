var waterIndicator = new Vue({
    el:'#waterIndicator',
    data:{
        name:'dede',
        pageData:[],
        waterList:[],
        title:'',
        dialogVisible:false,
    },
    methods:{
        /**
         * 新增或修改
         * @param sign
         */
        addWarnInfo:function(sign){
            var _this = this;
            debugger
            _this.title = '新增水管区用水指标';
            if(sign==0){
                $.post("parameterManage/waterIndicatorData.do",{sign:sign},function(data){
                    debugger
                    if(data!=null){
                        _this.pageData = JSON.parse(data);
                    }
                });
            }
            _this.dialogVisible = true;
        },
        /**
         * 修改单条数据
         * @param row
         */
        editWarnInfo:function(row){
            var _this = this;
            _this.pageData = [];
            _this.title = '修改【'+row.waterAreaName+'】水管区用水指标';
            debugger
            _this.pageData[0] = row;
            _this.dialogVisible = true;
        },
        /**
         * 提交
         */
        handleClick:function(){
            var _this = this;
            debugger
            var flag = false;
            for(var i=0;i<_this.pageData.length;i++){
                if(_this.pageData[i].sumwater==""||_this.pageData[i].sumwater==null){
                    _this.messageClick("请输入"+_this.pageData[i].waterAreaName+"总用水量指标","error");
                    return;
                }else if(_this.pageData[i].waterfarming==""||_this.pageData[i].waterfarming==null){
                    _this.messageClick("请输入"+_this.pageData[i].waterAreaName+"农业用水指标","error");
                    return;
                }else if(_this.pageData[i].watervirest==""||_this.pageData[i].watervirest==null){
                    _this.messageClick("请输入"+_this.pageData[i].waterAreaName+"绿化用水指标","error");
                    return;
                }else if(_this.pageData[i].waterlife==""||_this.pageData[i].waterlife==null){
                    _this.messageClick("请输入"+_this.pageData[i].waterAreaName+"生活用水指标","error");
                    return;
                }else if(_this.pageData[i].waterindustry==""||_this.pageData[i].waterindustry==null){
                    _this.messageClick("请输入"+_this.pageData[i].waterAreaName+"工业用水指标","error");
                    return;
                }else{
                    flag = true;

                }
            }
            if(flag){
                $.post("parameterManage/indicatorData.do",{sign:0,addForm:JSON.stringify(_this.pageData)},function(data){
                    debugger
                    if(data==1){
                        _this.messageClick("数据已录入","success");
                        _this.vityMethods();
                        _this.dialogVisible = false;
                    }
                });
            }
        },
        vityMethods:function(){
            var _this = this;
            $.post("parameterManage/indicatorData.do",{sign:2,addForm:""},function(data){
                debugger
                if(data!=""||data!=null){
                    _this.waterList = JSON.parse(data);
                }
            });
        },
        /**
         * 消息提示的公共方法
         */
        messageClick:function(content,sign){
            var _this = this;
            _this.$message({
                showClose: true,
                message: content,
                type: sign
            });
        },
    },
    mounted:function(){
        var _this = this;
        _this.vityMethods();
    },
    created:function(){
        var _this = this;
    },
});
