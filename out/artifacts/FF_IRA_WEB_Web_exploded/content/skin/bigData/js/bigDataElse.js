//列表自动滚动
function scrollNews() {
    var $news = $('#deviceList table');
    var $lineHeight = $news.find('tr:first').height();
    $news.animate({ 'marginTop': -$lineHeight + 'px' }, 1000, function () {
        $news.css({ margin: 0 }).find('tr:first').appendTo($news);
    });
}
var delay = 2000;
scrollTimer = setInterval(function () {
    scrollNews();scrollNewe();
}, delay);


//列表自动滚动
function scrollNewe() {
    var $news = $('#warnTable table');
    var $lineHeight = $news.find('tr:first').height();
    $news.animate({ 'marginTop': -$lineHeight + 'px' }, 1000, function () {
        $news.css({ margin: 0 }).find('tr:first').appendTo($news);
    });
}

redSumInterval = setInterval(function () {
	redSumMethods();
}, 10000);

function redSumMethods(){
	var array = new Array("工业用水,waterIndustry","生活用水,waterLife","绿化用水,waterVirest","农业用水,waterFarming");
	redSumType(array[inval]);
}

function redSumType(sign){
	var waterTypeName = sign.split(',')[0];
	var ruleType = sign.split(',')[1];
	switch (waterTypeName) {
	case "工业用水":
		inval = 1;
		break;
	case "生活用水":
		inval = 2;
		break;
	case "绿化用水":
		inval = 3;
		break;
	case "农业用水":
		inval = 0;
		break;
	}
	bigData.redSum.waterTypeName = waterTypeName;//该类型用水名称
	bigData.redSum.waterTypetarget = bigData.converSion(bigData.querySumRedVal,ruleType);//该类型用水的总指标
	bigData.redSum.waterTypeSum = bigData.dataConver(bigData.querySumRedData[ruleType])+'m3';
	if(bigData.redSum.waterTypetarget==0){

		bigData.redSum.waterTypeRate = 0;
	}else{
		if(bigData.querySumRedData[ruleType]=='undefined'){
			bigData.querySumRedData[ruleType] = 0;
		}
		bigData.redSum.waterTypeRate = bigData.querySumRedData[ruleType]/(bigData.redSum.waterTypetarget*10000);
		//setTimeout(function(){bigData.redSum.waterTypeRate = bigData.querySumRedData[ruleType]/(bigData.redSum.waterTypetarget*10000);},500);
	}
	echartsMethods("saleRedLine",'saleRedLine');
	echartsMethods("saleSum",'saleSum');
	 var $news = $('#queRump table');
	    var $lineHeight = $news.find('tr:first').height();
	    $news.animate({ 'marginTop': 0 + 'px' }, 0, function () {
	        $news.css({ margin: 0 }).find('tr:first').appendTo($news);
	    });
}






