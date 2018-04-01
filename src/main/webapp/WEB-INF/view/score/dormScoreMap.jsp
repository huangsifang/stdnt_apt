<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>寝室得分</title>
</head>
<body>
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<div style="margin:20px 50px">
	
	    <!--定义页面图表容器-->
	    <!-- 必须制定容器的大小（height、width） -->
	    <div id="main" style="height: 400px; border: 1px solid #ccc; padding: 10px;"></div>
	</div>
	<script src="<c:url value='/public/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/public/js/echarts.common.min.js'/>"></script>
    <script>
        var siteArray = new Array();
        var xArray = new Array();
        var i = 0;
        var year = "";
        var day= "";
        
        <c:forEach var="item" items="${oneDormScores}">
            siteArray[i]= new Object();
            year = "${item.createTime}".slice(-4);
            day = "${item.createTime}".slice(4, -18);
            siteArray[i].name = day + " " + year;
            siteArray[i].value = ${item.score};
            xArray.push(day + " " + year);
            i++;
        </c:forEach>
        
        $().ready(function() {
            var myChart = echarts.init(document.getElementById('main'));
            //图表显示提示信息
            myChart.showLoading();
            //定义图表options
            var options = {
                title: {
                    text: "分数趋势图",
                    subtext: "hznu"
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ["${floorDormNo}"]
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: false
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        data: xArray
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        splitArea: { show: true }
                    }
                ],
                series : [  
                    {  
                        name: "${floorDormNo}",  
                        type: 'line',
                        data : siteArray
                          
                    }
                ]
            };

            myChart.hideLoading();
            myChart.setOption(options);

        });
    </script>
</body>
</html>