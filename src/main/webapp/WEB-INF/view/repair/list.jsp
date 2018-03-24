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

<!-- 维修新增修改模态框（Modal） -->
<div class="modal fade" id="repairModal" tabindex="-1" role="dialog" aria-labelledby="repairModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="repairModalLabel">新增维修</h4>
            </div>
            <div class="modal-body">
            	<shiro:hasPermission name="repair:create">
				    <form id="repairForm" action="" method="post" class="form-horizontal" role="form">
				    	<div class="form-group">
							<label for="repairType" class="col-sm-3 control-label">维修类型：</label>
							<div class="col-sm-8">
								<select class="form-control" name="repairType">
									<c:forEach items="${allType}" var="type">
										<option value="${type.typeId}">${type.typeName}</option>
									</c:forEach>
								</select>
					    	</div>
						</div>
						<div class="form-group">
							<label for="holiName" class="col-sm-3 control-label">备注：</label>
							<div class="col-sm-8">
								<textarea class="form-control" rows="3" cols="20" name="remark"></textarea>
					    	</div>
						</div>
				    </form>
				</shiro:hasPermission>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="repairBtn">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<c:if test="${not empty repairList}">
	<ul class="nav nav-tabs">
		<c:forEach items="${apartList}" var="apart">
			<li><a href="${pageContext.request.contextPath}/repair/${apart.apartId}">${apart.apartName}</a></li>
	    </c:forEach>
	</ul>
	
	<table class="table">
	    <thead>
	        <tr>
	            <th>寝室号</th>
	            <th>申请者</th>
	            <th>维修类型</th>
	            <th>申请时间</th>
	            <th>备注</th>
	            <th>状态</th>
	            <th>操作</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach items="${repairList}" var="repair">
	            <tr>
	                <td>${repair.dormId}</td>
	                <td>${repair.applicantName}</td>
	                <td>${repair.repairTypeName}</td>
	                <td><fmt:formatDate value="${repair.applyTime}" pattern="yyyy-MM-dd HH:mm" /></td>
	                <td>${repair.remark}</td>
	                <c:if test="${repair.state == 0}">
	                	<td>未接单</td>
	                </c:if>
	                <c:if test="${repair.state == 1}">
	                	<td>已接单</td>
	                </c:if>
	                <c:if test="${repair.state == 2}">
	                	<td>已结束</td>
	                </c:if>
	                <td>
	                	<shiro:hasPermission name="repair:view">
	                    	<a href="repair/${repair.id}/record">查看</a>
	                    </shiro:hasPermission>
	                </td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
</c:if>
<shiro:hasPermission name="repair:create">
	<button class="btn btn-primary btn-md" data-toggle="modal" data-target="#repairModal">新增</button>
</shiro:hasPermission>
<c:if test="${not empty dormRepairList}">
	<div class="row">
		<c:forEach items="${dormRepairList}" var="repair">
			<a href="${pageContext.request.contextPath}/repair/${repair.id}/record">
				<div class="col-sm-3 panel panel-default">
					<div class="panel-body">
					<label for="dormId">公寓号：${repair.dormId}</label><br />
					<label for="applicantId">申请者：${repair.applicantName}</label><br />
					<label for="repairType">维修类型：${repair.repairTypeName}</label><br />
					<label for="applyTime">申请时间：<fmt:formatDate value="${repair.applyTime}" pattern="yyyy-MM-dd HH:mm" /></label><br />
					<label for="state">
						<c:if test="${repair.state == 0}">
		                	<td>未接单</td>
		                </c:if>
		                <c:if test="${repair.state == 1}">
		                	<td>已接单</td>
		                </c:if>
		                <c:if test="${repair.state == 2}">
		                	<td>已结束</td>
		                </c:if>
	                </label><br />
					<label for="remark">备注：${repair.remark}</label>
					</div>
				</div>
			</a>
		</c:forEach>
	</div>
</c:if>

</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/bootstrap.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
<script>
$("#repairBtn").click(function() {
	$.ajax({
		type: "POST",
		datatype: "json",
		url: "repair/create",
		data: $("#repairForm").serializeArray(),
		contentType: "application/x-www-form-urlencoded",
		success: function(data) {
			alert(data);
			$('#repairModal').modal('hide');
		},
		error: function() {
        	alert('error');
        }
	});
});
</script>
</html>