<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>我的寝室维修</title>
    <style type="text/css">
    .emptyWord {
   		height: 400px;
   		border: 1px solid #ccc;
   		padding: 10px;
   		margin: 0 auto;
   		text-align: center;
   		line-height: 400px;
   	}
   	.panel a {
   		color: gray !important;
   	}
   	.panel a:hover {
   		color: #6BAAA8 !important;
   	}
    </style>
</head>
<body>
<jsp:include page="../navbar.jsp"></jsp:include>

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

<div class="container">
	
	<c:if test="${not empty error}">
		<div class="panel" style="margin-top:20px">
			<div class="panel-body">
				<span>您还未加入任何寝室，请联系公寓管理员</span>
			</div>
		</div>
	</c:if>
	<c:if test="${empty error}">
		<shiro:hasPermission name="repair:create">
			<button style="margin-top:20px;margin-left:20px" class="btn btn-default btn-md" data-toggle="modal" data-target="#repairModal">寝室报修</button>
		</shiro:hasPermission>
		<c:if test="${not empty dormRepairList}">
			<div class="row" style="padding: 20px">
				<c:forEach items="${dormRepairList}" var="repair">
					<a href="${pageContext.request.contextPath}/repair/${repair.id}/record">
						<div class="col-md-4 col-sm-6">
							<div class="panel panel-default">
								<div class="panel-body" style="height:290px;padding-top:25px">
									<!-- <div class="form-group row">
				                      <label class="col-sm-4 control-label">公寓号：</label>
				                      <div class="col-sm-8">
				                         <span>${repair.dormId}</span>
				                      </div>
				                   </div> -->
				                   <div class="form-group row">
				                      <label class="col-sm-5 control-label">申请者：</label>
				                      <div class="col-sm-7">
				                         <span>${repair.applicantName}</span>
				                      </div>
				                   </div>
				                   <div class="form-group row">
				                      <label class="col-sm-5 control-label">维修类型：</label>
				                      <div class="col-sm-7">
				                         <span>${repair.repairTypeName}</span>
				                      </div>
				                   </div>
				                   <div class="form-group row">
				                      <label class="col-sm-5 control-label">申请时间：</label>
				                      <div class="col-sm-7">
				                         <span><fmt:formatDate value="${repair.applyTime}" pattern="yyyy-MM-dd HH:mm" /></span>
				                      </div>
				                   </div>
				                   <div class="form-group row">
				                      <label class="col-sm-5 control-label">备注：</label>
				                      <div class="col-sm-7">
				                         <a href="#" class="tooltip-test" data-toggle="tooltip" title="${repair.remark}">
				                         	<p style="width: 100%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">${repair.remark}</p>
				                         </a>
				                      </div>
				                   </div>
				                   <div class="form-group row">
				                      <div class="col-sm-offset-5 col-sm-7">
				                         <span>
				                         	<c:if test="${repair.state == 0}">
							                	<td><span class="label label-warning">未接单</span></td>
							                </c:if>
							                <c:if test="${repair.state == 1}">
							                	<td><span class="label label-info">已接单</span></td>
							                </c:if>
							                <c:if test="${repair.state == 2}">
							                	<td><span class="label label-success">已结束</span></td>
							                </c:if>
				                         </span>
				                      </div>
				                   </div>
								</div>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${empty dormRepairList}">
			<div class="emptyWord">
				<span>您所在的寝室还没有任何维修信息</span>
			</div>
		</c:if>
	</c:if>
</div>
</body>
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
				if(data == 'success') {
					swal({ 
						title: "成功", 
						text: "新增成功", 
						type: "success"
					},
					function(){
						window.location.reload();
					});
				} else if(data == 'errorNoDorm') {
					swal("失败！", "您还未加入任何寝室", "error");
					$('#repairModal').modal('hide');
				} else {
					swal("失败！", "新增失败", "error");
					$('#repairModal').modal('hide');
				}
			},
			error: function() {
				swal("失败！", "发生错误", "error");
	        }
		});
	});
});
</script>
</html>