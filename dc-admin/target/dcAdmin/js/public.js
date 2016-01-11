Date.prototype.format = function(format)  
{  
/* 
* format="yyyy-MM-dd hh:mm:ss"; 
*/  
var o = {  
"M+" : this.getMonth() + 1,  
"d+" : this.getDate(),  
"h+" : this.getHours(),  
"m+" : this.getMinutes(),  
"s+" : this.getSeconds(),  
"q+" : Math.floor((this.getMonth() + 3) / 3),  
"S" : this.getMilliseconds()  
}  
  
if (/(y+)/.test(format))  
{  
format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4  
- RegExp.$1.length));  
}  
  
for (var k in o)  
{  
if (new RegExp("(" + k + ")").test(format))  
{  
format = format.replace(RegExp.$1, RegExp.$1.length == 1  
? o[k]  
: ("00" + o[k]).substr(("" + o[k]).length));  
}  
}  
return format;  
}  
function timestampFormat(time,format){
	if(time ==null || time == 0){
		return "";
	}
	var date=new Date(time);
	return date.format(format);
}
function getDate(strDate) {  
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,  
     function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');  
    return date;  
}
function getYYYMMDDDate(dateStr){
	if(dateStr == null){
		return new Date();
	}
	var dateTime = dateStr.split("-");
	if(dateTime.length == 3){
		var month = parseInt(dateTime[1]) -1;
		return new Date(dateTime[0],month,dateTime[2]);
	}
	var date=new Date(dateStr);
	date.setHours("00");
	return date;
}
function getYYYMMDate(dateStr){
	if(dateStr == null){
		return new Date();
	}
	var dateTime = dateStr.split("-");
	if(dateTime.length == 2){
		var month = parseInt(dateTime[1]) -1;
		return new Date(dateTime[0],month);
	}
	var date=new Date(dateStr);
	date.setHours("00");
	return date;
}
function getMonday( time,offset  ){
	   return  new Date( time - ((time.getDay() ||7) -1  - (offset||0) *7 )  *864E5  );  
}

Array.prototype.remove = function(s) {     
    for (var i = 0; i < this.length; i++) {     
        if (s == this[i])     
            this.splice(i, 1);     
    }     
}   
function Map() {
    /** 存放键的数组(遍历用到) */
    this.keys = new Array();
    /** 存放数据 */
    this.data = new Object();
    
    /**
     * 放入一个键值对
     * @param {String} key
     * @param {Object} value
     */
    this.put = function(key, value) {
        if(this.data[key] == null){
            this.keys.push(key);
        }
        this.data[key] = value;
    };
    
    /**
     * 获取某键对应的值
     * @param {String} key
     * @return {Object} value
     */
    this.get = function(key) {
        return this.data[key];
    };
    /**   
     * 删除一个键值对   
     * @param {String} key   
     */    
    this.remove = function(key) {     
        this.keys.remove(key);     
        this.data[key] = null;     
    };
    /**   
     * 清空 
     * @param {String} key   
     */    
    this.clear = function() {     
        this.keys = null;
        this.data = null;     
    };
    this.size = function() {
        return this.keys.length;
    };
    /**   
     * 遍历Map,执行处理函数   
     *    
     * @param {Function} 回调函数 function(key,value,index){..}   
     */    
    this.each = function(fn){     
        if(typeof fn != 'function'){     
            return;     
        }     
        var len = this.keys.length;     
        for(var i=0;i<len;i++){     
            var k = this.keys[i];     
            fn(k,this.data[k],i);     
        }     
    };
}

function pad(num, n) {  
    var len = num.toString().length;  
    while(len < n) {  
        num = "0" + num;  
        len++;  
    }  
    return num;  
}  

function xround(x, num){
    return Math.round(x * Math.pow(10, num)) / Math.pow(10, num) ;
}
function dateFormat(date) {
	var year = date.getFullYear();  //获取年
    var month = date.getMonth() + 1;    //获取月
    var day = date.getDate(); //获取日
    if(day<10){
    	day="0"+day;
    }
    if(month<10){
    	month="0"+month;
    }
    return year+"-"+month+"-"+day;
}
function jqConfirm(fn1, fn2) {
	return $( "#dialog-confirm" ).dialog({
		resizable: false,
		height:140,
		modal: true,
		buttons: {
			"确定": function() {
				var dlg = $(this).dialog("close");
                fn1 && fn1.call(dlg, true);
			},
			"取消": function() {
				var dlg = $(this).dialog("close");
                fn2 && fn2(dlg, false);
			}
		}
	});
}

function confirmPop(evt) {
    evt = $.event.fix(evt);
    var me = evt.target;
    if (me.confirmResult) {
      me.confirmResult = false;
      return true;
    }
    jqConfirm(function(e) {
      me.confirmResult = true;
      if (e) {
        me.click(evt);
      }
      return false;
    });
    return false;
}

function select_jqConfirm(fn1, fn2) {
	return $( "#dialog-confirm" ).dialog({
		resizable: true,
		height:300,
		width:600,
		modal: true,
		buttons: {
			"确定": function() {
				var dlg = $(this).dialog("close");
                fn1 && fn1.call(dlg, true);
			}
		}
	});
}

function select_confirmPop(evt) {
    evt = $.event.fix(evt);
    var me = evt.target;
    if (me.confirmResult) {
      me.confirmResult = false;
      return true;
    }
    select_jqConfirm(function(e) {
      me.confirmResult = true;
      if (e) {
        me.click(evt);
      }
      return false;
    });
    return false;
}
var index;
function alertDealPage(url){
	index=layer.open({
	    type : 2,
	    content: url,
	    area : ['1000px', '400px'],
	    maxmin:true
	});
	layer.full(index);
}
window.onresize=function() {
    layer.style(index,{
        width: $(window).width()+ 'px',
        left: '100px'
    });
};
function closeLayer(){
	layer.closeAll();
	query(currentPage);
}

function formatCurrency(num) {
	var sign = "";
	if (isNaN(num)) {
		num = 0;
	}
	if (num < 0) {
		sign = "-";
	}
	var strNum = num + "";
	var arr1 = strNum.split(".");
	var hasPoint = false;// 是否有小数部分
	var piontPart = "";// 小数部分
	var intPart = strNum;// 整数部分
	if (arr1.length >= 2) {
		hasPoint = true;
		piontPart = arr1[1];
		intPart = arr1[0];
	}

	var res = '';// 保存添加逗号的部分
	var intPartlength = intPart.length;// 整数部分长度
	var maxcount = Math.ceil(intPartlength / 3);// 整数部分需要添加几个逗号
	for (var i = 1; i <= maxcount; i++)// 每三位添加一个逗号
	{
		var startIndex = intPartlength - i * 3;// 开始位置
		if (startIndex < 0)// 开始位置小于0时修正为0
		{
			startIndex = 0;
		}
		var endIndex = intPartlength - i * 3 + 3;// 结束位置
		var part = intPart.substring(startIndex, endIndex) + ",";
		res = part + res;
	}
	res = res.substr(0, res.length - 1);// 去掉最后一个逗号
	if (hasPoint) {
		return "￥" + sign + res + "." + piontPart;
	} else {
		return "￥" + sign + res;
	}

}
//数字，不能为0开头
function checkNum(obj){
	var str = $(obj).val();
	str=str.replace(/[^\d]/g,'')
//    str = str.replace(/\b(0+)/gi,"");    //把开头的N个0替换成空
    $(obj).val(str);            //把值赋给input
}

//手机号校验
function CheckMobile(number) {
	var sVal1 = number;
	var sReg = "^1[0-9]{10}$";
	var reg = new RegExp(sReg, "i");
	if (!reg.test(sVal1)) {
		return true;
	}
}

//起始结束日期校验
function CheckDoubleDate(begDate, endDate, span) {
	var sVal1 = begDate;
	var sVal2 = endDate;
	var sReg = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
	var reg = new RegExp(sReg, "i");
	if (sVal1 == "") {
		alert("请填写开始日期");
		return true;
	}
	if (sVal2 == "") {
		alert("请填写结束日期");
		return true;
	}
	if (!reg.test(sVal1)) {
		alert("开始日期格式错误");
		//GoBack(field1);
		return true;
	}
	if (!reg.test(sVal2)) {
		alert("结束日期格式错误");
		//GoBack(field2);
		return true;
	}
	if (sVal1 > sVal2) {
		alert("开始日期不能大于结束日期");
		//GoBack(field1);
		return true;
	}
	if (sVal1 == "" || sVal1 == null) {
		sVal1 = "00000000";
	}
	if (sVal2 == "" || sVal2 == null) {
		sVal2 = "99999999";
	}
	if (span != 0) {
		if ((DateDiff(sVal2, sVal1) + 1) > span) {
			//GoBack(field1);
			alert("日期间隔不能大于"+span+"天");
			return true;
		}
	}
	return false;
}

//日期差
function DateDiff(sDate1, sDate2) {
    var aDate, oDate1, oDate2, iDays;
    aDate = sDate1.split("-");
    oDate1 = new Date(aDate[0], aDate[1] - 1, aDate[2]);
    aDate = sDate2.split("-");
    oDate2 = new Date(aDate[0], aDate[1] - 1, aDate[2]);
    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24);
    if ((oDate1 - oDate2) < 0) {
        return -iDays;
    }
    return iDays;
}
/**
 * 模拟表单提交发送post请求
 * @param to
 * @param p
 */
function postwith(to, p) {
	var myForm = document.createElement("form");
	myForm.method = "post";
	myForm.action = to;
	for ( var k in p) {
	var myInput = document.createElement("input");
	myInput.setAttribute("name", k);
	myInput.setAttribute("value", p[k]);
	myForm.appendChild(myInput);
	}
	document.body.appendChild(myForm);
	myForm.submit();
	document.body.removeChild(myForm);
}
