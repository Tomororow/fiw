/**
 * 公用的ztree
 * @param id tree id
 * @param url 请求的路径
 * @param fun 执行的方法
 * 
 */
var ztreeFun = function ztreeInit(id, url, fun, completeCallBack) {
	debugger
	var setting = {
		view : {
			nameIsHTML : true
		},
		// 获取json数据
		async : {
			enable : true,
			url : url,
			autoParam : [ "id", "pId", "name" ]
		},
		data : {
			key : {
				title : "name"
			},
			simpleData : {
				enable : true,
				idKey : "id", // id
				pIdKey : "pId", // 父id
				rootId : 0
			}
		},
		// 回调函数
		callback : {
			onClick : function nodeClick(event, treeId, treeNode, clickFlag) {
				if (fun != null) {
					// 通过点击左侧获取右侧内容
					fun(treeNode);
				}
			},
			onAsyncSuccess : function onAsyncSuccess(event, treeId, treeNode,
					msg) {
				debugger
				// 异步加载成功回调函数
				var treeObj = $.fn.zTree.getZTreeObj(treeId);
				// 展开第一父节点
				var cNodes = treeObj.getNodes()[0].children;
				if(cNodes!=null){
					if (cNodes.length > 0) {
						treeObj.expandNode(cNodes[0], true, false, true);
					}
				}
				if (completeCallBack) {
					completeCallBack(event, treeId, treeNode);
				} else {

					var nodes = treeObj.getNodes();
					if (nodes.length > 0) {
						treeObj.selectNode(nodes[0]);
						// 默认点击第一个节点
						treeObj.setting.callback.onClick(null,
								treeObj.setting.treeId, nodes[0]);
					}
				}
			}
		}
	};
	return $.fn.zTree.init(id, setting);
};

$(".treeOpt div.operate div.selection").click(function() {
	if ($(this).children("ul").is(":hidden")) {
		$(this).children("ul").show();
	} else {
		$(this).children("ul").hide();
	}
});
$(".treeOpt div.operate div.selection").bind("mouseleave", function() {
	$(this).children("ul").hide();
});
