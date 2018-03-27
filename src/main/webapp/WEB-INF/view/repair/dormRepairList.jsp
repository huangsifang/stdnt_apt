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

<shiro:hasPermission name="repair:create">
	<button class="btn btn-primary btn-md" data-toggle="modal" data-target="#repairModal">新增</button>
</shiro:hasPermission>
<c:if test="${not empty dormRepairList}">
	<div class="row" style="padding: 20px">
		<c:forEach items="${dormRepairList}" var="repair">
			<a href="${pageContext.request.contextPath}/repair/${repair.id}/record">
				<div class="col-sm-3">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="form-group row">
		                      <label class="col-sm-4 control-label">公寓号：</label>
		                      <div class="col-sm-8">
		                         <span>${repair.dormId}</span>
		                      </div>
		                   </div>
		                   <div class="form-group row">
		                      <label class="col-sm-4 control-label">申请者：</label>
		                      <div class="col-sm-8">
		                         <span>${repair.applicantName}</span>
		                      </div>
		                   </div>
		                   <div class="form-group row">
		                      <label class="col-sm-4 control-label">维修类型：</label>
		                      <div class="col-sm-8">
		                         <span>${repair.repairTypeName}</span>
		                      </div>
		                   </div>
		                   <div class="form-group row">
		                      <label class="col-sm-4 control-label">申请时间：</label>
		                      <div class="col-sm-8">
		                         <span><fmt:formatDate value="${repair.applyTime}" pattern="yyyy-MM-dd HH:mm" /></span>
		                      </div>
		                   </div>
		                   <div class="form-group row">
		                      <div class="col-sm-offset-4 col-sm-8">
		                         <span>
		                         	<c:if test="${repair.state == 0}">
					                	<td>未接单</td>
					                </c:if>
					                <c:if test="${repair.state == 1}">
					                	<td>已接单</td>
					                </c:if>
					                <c:if test="${repair.state == 2}">
					                	<td>已结束</td>
					                </c:if>
		                         </span>
		                      </div>
		                   </div>
		                   <div class="form-group row">
		                      <label class="col-sm-4 control-label">备注：</label>
		                      <div class="col-sm-8">
		                         <span>${repair.remark}</span>
		                      </div>
		                   </div>
						</div>
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
$(function() {
	$("#repairBtn").click(function() {
		$.ajax({
			type: "POST",
			datatype: "json",
			url: "repair/create",
			data: $("#repairForm").serializeArray(),
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				$('#repairModal').modal('hide');
				window.location.reload();
			},
			error: function() {
	        	alert('error');
	        }
		});
	});
});
</script>
</html>