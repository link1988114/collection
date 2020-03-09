function createCheckBoxMatrix(row, col, ul, li_name,allctrl_id, valueArr) {
    var checkflag = 0;
    ul.innerHTML = "<li><input type=\"checkbox\" id=\""+allctrl_id+"\" value=\"all\" checked=\"checked\" onclick=\"changeall(this,\'" + li_name + "\')\"/>全部</li>";
    for (var i = 0; i < row; i++) {
        if (checkflag == 1) {
            break;
        }
        var node = document.createElement("li");
        var nodestr = "";
        for (var j = 0; j < col; j++) {
            if (i * col + j > valueArr.length - 1) {
                checkflag = 1;
                break;
            }
            nodestr += "<input type=\"checkbox\" checked=\"checked\" name=\"" + li_name + "\" value=\"" + valueArr[i * col + j] + "\"/>" + valueArr[i * col + j];
        }
        node.innerHTML = nodestr;
        ul.appendChild(node);
    }
}

function changeall(obj, name) {
    //alert(obj.checked);
    var s = document.getElementsByName(name);
    //alert(s.length);
    if (obj.checked == false) {
        for (var i = 0; i < s.length; i++) {
            s[i].checked = false;
        }
    }
    else {
        for (var i = 0; i < s.length; i++) {
            s[i].checked = true;
        }
    }
}

function isAllChecked(allCtlObj, name) {
    var s = document.getElementsByName(name);
    for (var i = 0; i < s.length; i++) {
        if (s[i].checked == false) {
            allCtlObj.checked = false;
            return;
        }
    }
    allCtlObj.checked = true;
}

function addCheck(allCtrlID, name) {
    var s = document.getElementsByName(name);
    var allctl = document.getElementById(allCtrlID);
    for (var i = 0; i < s.length; i++) {
        s[i].onclick = function () {
            isAllChecked(allctl, name);
        }
    }
}

function getValueArrStr(name) {
    var s = document.getElementsByName(name);
    var result = "";
    for (var i = 0; i < s.length; i++) {
        if (s[i].checked == true) {
            result += s[i].value + ",";
        }
    }
    return result;
}