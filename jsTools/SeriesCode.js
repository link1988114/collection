function seriesCode(rule_input, from_input, to_input) {
    var numLength = rule_input.length - rule_input.replace(/\*/g, "").length;

    var placemark = "";
    for (var i = 0; i < numLength; i++) {
        placemark += "0";
    }

    var seriesArr = new Array();
    for (var i = from_input; i <= to_input; i++) {
        var value = placemark + i;
        value = value.substr(value.length - numLength, value.length);

        var temp = "";
        var index = 0;
        for (var j = 0; j < rule_input.length; j++) {
            if (rule_input.charAt(j) == "*") {
                temp += value.charAt(index);
                index++;
            }
            else {
                temp += rule_input.charAt(j);
            }
        }
        temp += Math.floor(Math.random() * 10); //random ending
        seriesArr.push(temp);
    }

    return seriesArr;
}

function rndCode(totalitems, type, length) {
    var resultarr = new Array();
    var pool = new Array();
    //acsii   0~9 : 48~57   a~z : 97~122   A~Z : 65~90

    if (type == "num") {
        for (var i = 48; i <= 57; i++) {
            pool.push(String.fromCharCode(i));
        }
    }
    if (type == "alphabet") {
        for (var i = 97; i <= 122; i++) {
            pool.push(String.fromCharCode(i));
        }
    }
    if (type == "Alphabet") {
        for (var i = 65; i <= 90; i++) {
            pool.push(String.fromCharCode(i));
        }
    }
    if (type == "numalphabet") {
        for (var i = 48; i <= 57; i++) {
            pool.push(String.fromCharCode(i));
        }
        for (var i = 97; i <= 122; i++) {
            pool.push(String.fromCharCode(i));
        }
    }
    if (type == "numAlphabet") {
        for (var i = 48; i <= 57; i++) {
            pool.push(String.fromCharCode(i));
        }
        for (var i = 65; i <= 90; i++) {
            pool.push(String.fromCharCode(i));
        }
    }
    if (type == "Alphabetalphabet") {
        for (var i = 97; i <= 122; i++) {
            pool.push(String.fromCharCode(i));
        }
        for (var i = 65; i <= 90; i++) {
            pool.push(String.fromCharCode(i));
        }
    }
    if (type == "numAlphabetalphabet") {
        for (var i = 48; i <= 57; i++) {
            pool.push(String.fromCharCode(i));
        }
        for (var i = 97; i <= 122; i++) {
            pool.push(String.fromCharCode(i));
        }
        for (var i = 65; i <= 90; i++) {
            pool.push(String.fromCharCode(i));
        }
    }

    for (var i = 0; i < totalitems; i++) {
        var temp = "";
        for (var j = 0; j < length; j++) {
            var pick = pool[Math.floor(Math.random() * pool.length)];
            temp += pick;
        }
        resultarr.push(temp);
    }

    return resultarr;
}