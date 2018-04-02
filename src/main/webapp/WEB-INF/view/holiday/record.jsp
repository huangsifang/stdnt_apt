<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/table.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../navbar.jsp"></jsp:include>

<div style="margin:20px 50px">
	
	<ul class="nav nav-tabs" id="apartUl">
		<c:forEach items="${apartList}" var="apart">
			<li><a href="${pageContext.request.contextPath}/holiday/${holiId}/apart/${apart.apartId}/record">${apart.apartName}</a></li>
	    </c:forEach>
	</ul>
	
	<table class="table">
	    <thead>
	        <tr>
	            <th>假期名</th>
	            <th>学生学号</th>
	            <th>学生姓名</th>
	            <th>公寓号</th>
	            <th>留校&返家</th>
	            <th>是否外出</th>
	            <th>外出地址</th>
	            <th>是否返校</th>
	            <th>操作</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${empty recordList}">
				<tr>
					<td colspan="9" style="text-align:center">此公寓该假期还未添加任何记录！</td>
				</tr>
			</c:if>
	        <c:forEach items="${recordList}" var="record">
	            <tr>
	                <td>${record.holiName}</td>
	                <td>${record.stdId}</td>
	                <td>${record.stdName}</td>
	                <td>${record.apartId}</td>
	                <td>${record.homeOrSchool}</td>
	                <td>${record.isOutStr}</td>
	                <td>${record.address}</td>
	                <td>
	                	<c:if test="${record.inHome}">
		                	<c:if test="${record.hasSign}">
								<span class="badge" style="background-color:#52a4db">已返校</span>
							</c:if>
							<c:if test="${!record.hasSign}">
								<span class="badge" style="background-color:#ee9e7e">未返校</span>
							</c:if>
						</c:if>
	                </td>
	                <td>
	                    <shiro:hasPermission name="record:delete">
	                    	<button class="btn btn-danger btn-md" type="button" onClick="deleteRecord(${record.holiId},${record.stdId})">删除</button>
	                    </shiro:hasPermission>
	                </td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
	<div class="pull-right" style="padding:20px">
		<span>共${allCount}条记录，</span>
		<span>公寓共${apartStdNum}学生</span>
	</div>
	<c:if test="${allCount != 0}">
		<ul class="pagination tablePage">
		    <li><a href="${pageContext.request.contextPath}/holiday/${holiId}/apart/${apartId}/record?start=${start-10}">&laquo;</a></li>
		    <c:forEach begin="0" end="${allCount-1}" var="item" step="10">
		    	<li value="${item/10+1}"><a href="${pageContext.request.contextPath}/holiday/${holiId}/apart/${apartId}/record?start=${item}"><fmt:formatNumber type="number" value="${item/10+1}" maxFractionDigits="0"/></a></li>
		    </c:forEach>
		    <li><a href="${pageContext.request.contextPath}/holiday/${holiId}/apart/${apartId}/record?start=${start+10}">&raquo;</a></li>
		</ul>
	</c:if>
</div>

</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/bootstrap.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
<script>
$(function() {
	var nowApartId = Number('${apartId}')-1;
	$("#apartUl li").eq(nowApartId).addClass("active");
	
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
function deleteRecord(holiId, stdId) {
	swal({ 
		title: "确定删除吗？", 
		text: "删除后该学生可以重新选择", 
		type: "warning",
		showCancelButton: true, 
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "确定删除！", 
		cancelButtonText: "取消",
		closeOnConfirm: false
	},
	function(isConfirm){
		if(isConfirm) {
			$.ajax({
				type: "POST",
				datatype: "json",
				data: {"holiId":holiId, "stdId":stdId},
				url: getRootPath() + "/holiday/record/delete",
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data != "success") {
						swal("成功！", "删除成功", "success");
					} else {
						swal("失败！", "删除失败！", "error");
					}
				},
				error: function() {
					swal("失败！", "发生错误！", "error");
		        }
			});
		} else { 
			swal("取消！", "删除被取消:)","info"); 
		}
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