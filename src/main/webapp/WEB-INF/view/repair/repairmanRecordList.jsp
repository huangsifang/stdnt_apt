<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
</head>
<body>

欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a>

<table class="table">
    <thead>
        <tr>
        	<th>公寓名</th>
            <th>寝室号</th>
            <th>维修类型</th>
            <th>申请者</th>
            <th>申请手机号</th>
            <th>申请时间</th>
            <th>备注</th>
            <th>接单时间</th>
            <th>维修时间</th>
            <th>状态</th>
        </tr>
    </thead>
    <tbody>
    	<c:if test="${empty myRepairRecordList}">
			您还没有接受任何维修！
		</c:if>
        <c:forEach items="${myRepairRecordList}" var="record">
            <tr>
            	<td>${record.repair.apartName}</td>
                <td>${record.repair.dormNo}</td>
                <td>${record.repair.repairTypeName}</td>
                <td>${record.repair.applicantName}</td>
                <td>${record.repair.applicantTel}</td>
                <td><fmt:formatDate value="${record.repair.applyTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                <td>${record.repair.remark}</td>
                <td><fmt:formatDate value="${record.acceptTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                <td>
                	<c:if test="${not empty record.repairTime}">
                		<fmt:formatDate value="${record.repairTime}" pattern="yyyy-MM-dd HH:mm" />
                	</c:if>
                </td>
                <c:if test="${record.state == 1}">
                	<td>已接单  <button onClick="finishOrder(${record.repairId})">确认已维修</button></td>
                </c:if>
                <c:if test="${record.state == 2}">
                	<td>已结束</td>
                </c:if>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
<script>
function finishOrder(repairId) {
	swal({ 
		title: "确定已完成该维修吗？", 
		text: "确定后将无法取消", 
		type: "info", 
		showCancelButton: true, 
		closeOnConfirm: false
	},
	function(){
		$.ajax({
			type: "POST",
			datatype: "json",
			url: getRootPath() + "/repair/"+repairId+"/record/finish",
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == "") {
					swal("失败！", "您没有权限接受该记录!", "error");
				} else if(data == "处理成功!") {
					swal("成功！", data, "success");
				} else if(data == "处理失败!") {
					swal("成功！", data, "error");
				}
			},
			error: function() {
	        	alert('error');
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