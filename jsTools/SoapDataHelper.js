function CreateSoapPostData(namespace,method,argArr) {
    var postdata = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    postdata += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
    postdata += "<soap:Body>";
    postdata += "<tns:" + method + "  xmlns:tns=\"" + namespace + "\">";
    for (var i = 0; i < argArr.length; i++) {
        temp = "<arg" + i + ">" + argArr[i] + "</arg" + i + ">";
        postdata += temp;
    }
    postdata += "</tns:" + method + ">";
    postdata += "</soap:Body>";
    postdata += "</soap:Envelope>";
    //alert(postdata);
    return postdata;
}
