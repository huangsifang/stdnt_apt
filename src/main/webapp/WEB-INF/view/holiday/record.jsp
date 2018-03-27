<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
</head>
<body>

欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a>

<c:if test="${not empty msg}">
    <div>${msg}</div>
</c:if>

<ul class="nav nav-tabs">
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
                <td>${record.holiId}</td>
                <td>${record.stdId}</td>
                <td>${record.stdName}</td>
                <td>${record.apartId}</td>
                <td>${record.homeOrSchool}</td>
                <td>${record.isOutStr}</td>
                <td>${record.address}</td>
                <td>
                	<c:if test="${record.inHome}">
	                	<c:if test="${record.hasSign}">
							已返校
						</c:if>
						<c:if test="${!record.hasSign}">
							未返校
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
	<span>共${recordNum}条记录，</span>
	<span>公寓共${apartStdNum}学生</span>
</div>

</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/bootstrap.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
<script>
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
					if(data != "") {
						swal("删除！", data, "success");
					} else {
						swal("删除！", "删除失败！", "error");
					}
				},
				error: function() {
		        	alert('error');
		        }
			});
		} else { 
			swal("取消！", "删除被取消:)","error"); 
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