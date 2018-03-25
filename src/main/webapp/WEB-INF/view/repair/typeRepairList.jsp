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

<ul class="nav nav-tabs">
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
			此类型没有任何维修申请！
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
                	<td>未接单  <button onClick="takeOrder(${repair.id},${repair.repairType})">接单</button></td>
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

</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
<script>
function takeOrder(repairId, repairType) {
	swal({ 
		title: "确定接单吗？", 
		text: "接单后将无法取消", 
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
				if(data == "") {
					swal("删除！", "您没有权限接受该类维修!", "error");
				} else if(data == "接单成功!") {
					swal("成功！", data, "success");
				} else if(data == "接单失败!") {
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