function getParam(str) {
    var url = location.search;
    var Request = new Object();
    if (url.indexOf("?") != -1) {
        varstr = url.substr(1);
        strs = varstr.split("&");
        for (var i = 0; i < strs.length; i++) {
            Request[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return Request[str];
}

function encodeUTF8(str){
	var temp = "",rs = "";
	for( var i=0 , len = str.length; i < len; i++ ){
		temp = str.charCodeAt(i).toString(16);
		rs  += "\\u"+ new Array(5-temp.length).join("0") + temp;
	}
	return rs;
 }
 function decodeUTF8(str){
	return str.replace(/(\\u)(\w{4}|\w{2})/gi, function($0,$1,$2){
		return String.fromCharCode(parseInt($2,16));
	}); 
 } 
