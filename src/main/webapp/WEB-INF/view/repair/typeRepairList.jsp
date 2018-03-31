<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/table.css" rel="stylesheet">
</head>
<body>
<div style="margin:20px 50px">
	<div class="pull-right">欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a></div>

	<ul class="nav nav-tabs" id="typeUl">
		<c:forEach items="${allType}" var="type">
			<li><a href="${pageContext.request.contextPath}/repair/type/${type.typeId}">${type.typeName}</a></li>
	    </c:forEach>
	</ul>
	<table class="table">
	    <thead>
	        <tr>
	        	<th>维修类型</th>
	        	<th>公寓名</th>
	            <th>寝室号</th>
	            <th>申请者</th>
	            <th>申请时间</th>
	            <th>备注</th>
	            <th>状态</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${empty repairByTypeList}">
				<tr>
					<td colspan="7" style="text-align:center">此类型没有任何维修申请！</td>
				</tr>
			</c:if>
	        <c:forEach items="${repairByTypeList}" var="repair">
	            <tr>
	            	<td>${repair.repairTypeName}</td>
	            	<td>${repair.apartName}</td>
	                <td>${repair.dormNo}</td>
	                <td>${repair.applicantName}</td>
	                <td><fmt:formatDate value="${repair.applyTime}" pattern="yyyy-MM-dd HH:mm" /></td>
	                <td>${repair.remark}</td>
	                <c:if test="${repair.state == 0}">
	                	<td>未接单  <button class="btn btn-default" onClick="takeOrder(${repair.id},${repair.repairType})">接单</button></td>
	                </c:if>
	                <c:if test="${repair.state == 1}">
	                	<td>已接单</td>
	                </c:if>
	                <c:if test="${repair.state == 2}">
	                	<td>已结束</td>
	                </c:if>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
	<ul class="pagination tablePage">
	    <li><a href="${pageContext.request.contextPath}/repair/type/${typeId}?start=${start-10}">&laquo;</a></li>
	    <c:forEach begin="0" end="${allCount-1}" var="item" step="10">
	    	<li value="${item/10+1}"><a href="${pageContext.request.contextPath}/repair/type/${typeId}?start=${item}"><fmt:formatNumber type="number" value="${item/10+1}" maxFractionDigits="0"/></a></li>
	    </c:forEach>
	    <li><a href="${pageContext.request.contextPath}/repair/type/${typeId}?start=${start+10}">&raquo;</a></li>
	</ul>
</div>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
<script>
$(function() {
	var nowTypeId = Number('${typeId}')-1;
	$("#typeUl li").eq(nowTypeId).addClass("active");
	
	var start = Number('${start}');
	var pageNum = start/10+1;
	$(".tablePage li").eq(pageNum).addClass("active");
	if(start <= 0) {
		$(".tablePage li").eq(0).find("a").attr("href","#");
		$(".tablePage li").eq(0).addClass("disabled");
	}
	if(start+10 >= Number('${allCount}')){
		$(".tablePage li").eq(-1).find("a").attr("href","#");
		$(".tablePage li").eq(-1).addClass("disabled");
	}
});
function takeOrder(repairId, repairType) {
	swal({ 
		title: "确定接单吗？", 
		text: "接单后请尽快前往", 
		type: "info", 
		showCancelButton: true, 
		closeOnConfirm: false
	},
	function(){
		$.ajax({
			type: "POST",
			datatype: "json",
			data: {"repairType": repairType},
			url: getRootPath() + "/repair/"+repairId+"/record/create",
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == "success") {
					swal("成功！", "接单成功", "success");
				} else if(data == "error") {
					swal("失败！", "接单失败", "error");
				} else if(data == "errorNoPower") {
					swal("失败！", "您没有权限接受该类维修!", "error");
				}
			},
			error: function() {
				swal("失败！", "发生错误", "error");
	        }
		});
	});
}

function getRootPath() {//获得根目录
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath);
	var prePath = strFullPath.substring(0, pos);
	var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
	return (prePath + postPath);
}
</script>
</html>