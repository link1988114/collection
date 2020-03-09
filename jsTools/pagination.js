var Pagination = {
    createNew: function () {
        var pagination = {};

        pagination.containerObj = new Object();
        pagination.navObj = new Object();
        pagination.data = { data: [] };
        pagination.pageItems = 2;
        pagination.navLength = 5;
        pagination.maxPage = 0;
        pagination.currPage = 1;

        //////////////////////////////////////////////////////////////////////////////////

        pagination.initial = function (containerObj, navObj, data, pageItems, navLength) {
            pagination.containerObj = containerObj;
            pagination.navObj = navObj;
            pagination.data = data;
            pagination.pageItems = pageItems;
            pagination.navLength = navLength <= 0 ? 1: navLength;
            pagination.maxPage = Math.ceil(pagination.data.data.length / pagination.pageItems);
            if (this.maxPage < this.navLength) {
                this.navLength = this.maxPage;
            }

            pagination.currPage = 1;
        }

        //////////////////////////////////////////////////////////////////////////////////

        pagination.getTotalPage = function () {
            return Math.ceil(pagination.data.data.length / pagination.pageItems);
        }

        pagination.getPageData = function (pageNo) {
            var start = (pageNo - 1) * pagination.pageItems;
            var end = pageNo * pagination.pageItems > pagination.data.data.length ? pagination.data.data.length : pageNo * pagination.pageItems;
            var partialData = {
                data: []
            };
            for (var i = start; i < end; i++) {
                partialData.data.push(pagination.data.data[i]);
            }
            return partialData;
        }

        //////////////////////////////////////////////////////////////////////////////////

        pagination.updateView = function(pageNo) {
            var pageData = pagination.getPageData(pageNo);

            pagination.createView(pageData);
            pagination.createNavigation(pageNo);
        }

        pagination.createView = function (pageData) {
            var htmlstr = "<tr id=\"hint_table_body\">" +
                                "<th>车牌</th>" +
                                "<th>姓名</th>" +
                                "<th>出单时间</th>" +
                                "<th>发展人</th>" +
                      "</tr>";

            for (var i = 0; i < pageData.data.length; i++) {
                htmlstr += "<tr>" +
                                        "<td align=\"center\" width=\"60\">" + pageData.data[i].chepai + "<br></td>" +
                                        "<td align=\"center\" width=\"60\">" + pageData.data[i].xingming + "</td>" +
                                        "<td align=\"center\" width=\"65\">" + pageData.data[i].xunjiashijian + "</td>" +
                                        "<td align=\"center\">" + pageData.data[i].khjl + "</td>" +
                                    "</tr>";
            }

            try {
                this.containerObj.innerHTML = htmlstr;
            }
            catch (e) {
                var div = document.createElement("div");
                div.innerHTML = "<table>"+htmlstr+"</table>";
                this.containerObj.replaceChild(div.firstChild.firstChild, this.containerObj.firstChild);
            }
            
        }

        pagination.createNavigation = function (pageNo) {
            var half = Math.floor(pagination.navLength / 2);
            var currIndex = 0;
            pagination.currPage = pageNo;

            var resultArr = new Array();
            for (var i = pageNo - half; i < pageNo; i++) {
                resultArr.push(i);
            }
            currIndex = resultArr.length;
            for (var i = pageNo; i < pageNo + (pagination.navLength - half) ; i++) {
                resultArr.push(i);
            }
            if (resultArr[0] <= 0) {
                currIndex = pageNo - 1;
                for (i = 0; i < resultArr.length; i++) {
                    resultArr[i] = i + 1;
                }
            }
            if (resultArr[resultArr.length - 1] > pagination.maxPage) {
                currIndex = pageNo - (pagination.maxPage - pagination.navLength) - 1;
                for (i = 0; i < resultArr.length; i++) {
                    resultArr[i] = pagination.maxPage - pagination.navLength + 1 + i;
                }
            }

            var htmlstr = ""
            for(i=0;i<resultArr.length;i++){
                if(i==currIndex){
                    htmlstr += "<li class=\"currpage\" onclick=\"updateView("+resultArr[i]+")\">"+resultArr[i]+"</li>";
                }
                else{
                    htmlstr += "<li onclick=\"updateView(" + resultArr[i] + ")\">" + resultArr[i] + "</li>";
                }
            }

            pagination.navObj.innerHTML = htmlstr;
        }

        pagination.gotoPage = function (pageNo) {
            pagination.currPage = Number(pageNo);
            if (pagination.currPage <= 0) {
                pagination.currPage = 1;
            }
            if (pagination.currPage >= pagination.maxPage) {
                pagination.currPage = pagination.maxPage;
            }
            
            pagination.updateView(pagination.currPage);
        }

        pagination.next = function() {
            pagination.currPage++;
            if (pagination.currPage >= pagination.maxPage) {
                pagination.currPage = pagination.maxPage;
            }
            pagination.updateView(pagination.currPage);
        }

        pagination.pre = function() {
            pagination.currPage--;
            if (pagination.currPage <= 1) {
                pagination.currPage = 1;
            }
            pagination.updateView(pagination.currPage);
        }

        //////////////////////////////////////////////////////////////////////////////////

        return pagination;
    }
};