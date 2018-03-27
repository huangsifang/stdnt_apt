<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <style>
    	.scoreMap {
    		height: 400px;
    		border: 1px solid #ccc;
    		padding: 10px;
    	}
    	.scoreWord {
    		height: 400px;
    		border: 1px solid #ccc;
    		padding: 10px;
    		margin: 0 auto;
    		text-align: center;
    		line-height: 400px;
    	}
    </style>
</head>
<body>

欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a>

<c:if test="${not empty msg}">
    <div>${msg}</div>
</c:if>

<shiro:hasPermission name="score:create">
    <form id="dormScoreForm" action="" method="post">
    	<c:if test="${not empty apartId}">
    		公寓号：<input type="number" name="apartId" value="${apartId}" /><br />
    	</c:if>
    	<c:if test="${empty apartId}">
    		公寓号：<input type="number" name="apartId" /><br />
    	</c:if>
    	寝室号：<input type="number" name="floorDormId" /><br />
    	分数：<input type="number" name="score" /><br />
    	<button type="button" id="scoreSubmitBtn">提交</button>
    </form>
</shiro:hasPermission>

<form id="dormFindForm" method="get">
	<c:if test="${not empty apartId}">
		公寓号：<input type="number" id="apartId" value="${apartId}" /><br />
	</c:if>
	<c:if test="${empty apartId}">
		公寓号：<input type="number" id="apartIdOther" /><br />
	</c:if>
	寝室号：<input type="number" id="floorDormId" /><br />
	<button type="button" id="dormFindBtn">查找</button>
</form>

<ul class="nav nav-tabs">
	<c:forEach items="${apartList}" var="apart">
		<li><a href="${pageContext.request.contextPath}/score/${apart.apartId}">${apart.apartName}</a></li>
    </c:forEach>
</ul>

<div class="row" style="padding: 20px">
	<c:forEach items="${newScoreList}" var="score">
		<a href="${pageContext.request.contextPath}/score/${score.apartId}/dorm/${score.floorDormNo}">
			<div class="col-sm-3">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="form-group row">
	                      <label class="col-sm-6 control-label">公寓号：</label>
	                      <div class="col-sm-6">
	                         <span>${score.apartId}</span>
	                      </div>
	                   </div>
	                   <div class="form-group row">
	                      <label class="col-sm-6 control-label">寝室号：</label>
	                      <div class="col-sm-6">
	                         <span>${score.floorDormNo}</span>
	                      </div>
	                   </div>
	                   <div class="form-group row">
	                      <label class="col-sm-6 control-label">分数：</label>
	                      <div class="col-sm-6">
	                         <span>${score.score}</span>
	                      </div>
	                   </div>
					</div>
				</div>
			</div>
		</a>
	</c:forEach>
</div>

<c:if test="${not empty apartId}">
	<div class="row">
		<div class="col-sm-6">
			<c:if test="${empty apartDormScore}">
				<div class="scoreWord">
					该公寓未有任何得分记录
				</div>
			</c:if>
			<c:if test="${not empty apartDormScore}">
				<div id="scoreMap" class="scoreMap"></div>
			</c:if>
		</div>
		<div class="col-sm-6">
			<c:if test="${empty apartDormOneDayScore}">
				<div class="scoreWord">
					该公寓今日未有任何得分记录
				</div>
			</c:if>
			<c:if test="${not empty apartDormOneDayScore}">
				<div id="dayScoreMap" class="scoreMap"></div>
			</c:if>
		</div>
	</div>
</c:if>
<c:if test="${empty apartId}">
	<div id="scoreMap"></div>
	<div id="dayScoreMap"></div>
</c:if>

</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="<c:url value='/public/js/echarts.common.min.js'/>"></script>
<script>
$(function() {
	$("#scoreSubmitBtn").click(function() {
		$.ajax({
			type: "POST",
			datatype: "text",
			url: getRootPath() + "/score/create",
			data: $("#dormScoreForm").serializeArray(),
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				alert(data);
			},
			error: function() {
	        	alert('error');
	        }
		});
	});
	$("#dormFindBtn").click(function() {
		var apartId = $("#apartId").val();
		if(!apartId) {
			apartId = $("#apartIdOther").val();
		}
		var floorDormId = $("#floorDormId").val();
		$("#dormFindForm").attr("action", getRootPath()+"/score/"+apartId+"/dorm/"+floorDormId);
		$("#dormFindForm").submit();
	});
});
function getRootPath() {//获得根目录
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath);
	var prePath = strFullPath.substring(0, pos);
	var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
	return (prePath + postPath);
}

var gradeList = ['A:(90-100)', 'B:(80-90)', 'C:(60-80)', 'D:(0-60)'];
var scoreList = new Array();
var dayScoreList = new Array();
var i=0;
<c:forEach var="item" items="${apartDormScore}">
	scoreList[i]= new Object();
    var grade = "${item.grade}";
    switch(grade) {
    case "A":
    	scoreList[i].name = grade + ":(90-100)";
    	break;
    case "B":
    	scoreList[i].name = grade + ":(80-90)";
    	break;
    case "C":
    	scoreList[i].name = grade + ":(60-80)";
    	break;
    case "D":
    	scoreList[i].name = grade + ":(0-60)";
    	break;
    }
    scoreList[i].value = ${item.count};
    i++;
</c:forEach>

var j=0;
<c:forEach var="item" items="${apartDormOneDayScore}">
dayScoreList[j]= new Object();
var grade = "${item.grade}";
switch(grade) {
case "A":
	dayScoreList[j].name = grade + ":(90-100)";
	break;
case "B":
	dayScoreList[j].name = grade + ":(80-90)";
	break;
case "C":
	dayScoreList[j].name = grade + ":(60-80)";
	break;
case "D":
	dayScoreList[j].name = grade + ":(0-60)";
	break;
}
dayScoreList[j].value = ${item.count};
j++;
</c:forEach>

$().ready(function() {
    //图表显示提示信息
    if(i != 0) {
    	var myChart = echarts.init(document.getElementById('scoreMap'));
	    myChart.showLoading();
	    //定义图表options
		var options = {
		    title : {
		        text: '公寓所有寝室得分分布图',
		        subtext: '${apartId}号楼',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        type: 'scroll',
		        orient: 'vertical',
		        right: 10,
		        top: 20,
		        bottom: 20,
		        data: gradeList
		    },
		    series : [
		        {
		            name: '等级',
		            type: 'pie',
		            radius : '55%',
		            center: ['40%', '50%'],
		            data: scoreList,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		myChart.hideLoading();
		myChart.setOption(options);
    }
    if(j != 0) {
    	var myChartDay = echarts.init(document.getElementById('dayScoreMap'));
	    myChartDay.showLoading();
		var dayOptions = {
		    title : {
		        text: '公寓今日寝室得分分布图',
		        subtext: '${apartId}号楼',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        type: 'scroll',
		        orient: 'vertical',
		        right: 10,
		        top: 20,
		        bottom: 20,
		        data: gradeList
		    },
		    series : [
		        {
		            name: '等级',
		            type: 'pie',
		            radius : '55%',
		            center: ['40%', '50%'],
		            data: dayScoreList,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		myChartDay.hideLoading();
		myChartDay.setOption(dayOptions);
    }
});
</script>
</html>