<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>公寓值班表</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"></jsp:include>

<!-- 新增值班人员模态框（Modal） -->
<div class="modal fade" id="staffModal" tabindex="-1" role="dialog" aria-labelledby="staffModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
	        <form id="staffRotaForm" method="post">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="staffModalLabel">新增值班人员</h4>
	            </div>
	            <div class="modal-body">
	            	<div class="form-horizontal" role="form">
	            		<input type="number" id="week" name="week" hidden/>
	            		<div class="form-group">
							<label for="staffIds" class="col-sm-4 control-label">值班人员：</label>
							<div class="col-sm-8">
								<select multiple class="form-control" name="staffIds" id="staffIds">
									<c:forEach items="${staffList}" var="staff">
										<option value="${staff.staffId}">${staff.staffName}</option>
									</c:forEach>
							    </select>
					    	</div>
						</div>
					</div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onClick="addStaffRota()">提交更改</button>
	            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="container">
	<div class="card">
		<div class="header">
			<h4>${apartName}值班表</h4>
		</div>
		<div class="content table-responsive">
			<table class="table">
			    <thead>
			        <tr>
			            <th>时间</th>
			            <th>值班人员</th>
			            <th>操作</th>
			        </tr>
			    </thead>
			    <tbody>
			        <c:forEach items="${rotaList}" var="rota">
			            <tr>
			            	<c:if test="${rota.week == 1}">
			            		<td>星期一</td>
			            	</c:if>
			            	<c:if test="${rota.week == 2}">
			            		<td>星期二</td>
			            	</c:if>
			            	<c:if test="${rota.week == 3}">
			            		<td>星期三</td>
			            	</c:if>
			            	<c:if test="${rota.week == 4}">
			            		<td>星期四</td>
			            	</c:if>
			            	<c:if test="${rota.week == 5}">
			            		<td>星期五</td>
			            	</c:if>
			            	<c:if test="${rota.week == 6}">
			            		<td>星期六</td>
			            	</c:if>
			            	<c:if test="${rota.week == 7}">
			            		<td>星期日</td>
			            	</c:if>
			                <td>
				                <c:forEach items="${rota.staffs}" var="staff">
				                	<div style="margin:5px 0">
					                	${staff.staffId}:${staff.staffName}——${staff.staffTel}
					                	<button class="btn btn-danger" onClick="deleteStaffRota(${rota.apartId},${staff.staffId},${rota.week})"><i class="fa fa-trash-o"></i></button>
				                	</div>
				                </c:forEach>
			                </td>
			                <td>
			                	<button class="btn btn-default" data-toggle="modal" data-target="#staffModal" onClick="showStaffModal(${rota.week})">新增</button>
			                </td>
			            </tr>
			        </c:forEach>
			    </tbody>
			</table>
		</div>
	</div>
</div>
</body>
<script>
function deleteStaffRota(apartId, staffId, week) {
	swal({ 
		title: "确定删除吗？", 
		text: "", 
		type: "warning",
		showCancelButton: true, 
		confirmButtonColor: "#DD6B55",
		cancelButtonText: "取消",
		confirmButtonText: "确定删除！", 
		closeOnConfirm: false
	},
	function(){
		$.ajax({
			type: "POST",
			datatype: "json",
			url: getRootPath() + "/apartment/"+apartId+"/week/"+week+"/staffRota/"+staffId+"/delete",
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == "success") {
					swal("成功！", "删除成功", "success");
				} else {
					swal("失败！", "删除失败", "error");
				}
			},
			error: function() {
				swal("错误！", "发生错误", "error");
	        }
		});
	});
}
function showStaffModal(week) {
	$("#week").val(week);
}
function addStaffRota() {
	$.ajax({
		type: "POST",
		datatype: "json",
		url: getRootPath() + "/apartment/${apartId}/staffRota/create",
		data: $("#staffRotaForm").serializeArray(),
		contentType: "application/x-www-form-urlencoded",
		success: function(data) {
			if(data == "success") {
				swal({ 
					title: "成功！", 
					text: "新增成功", 
					type: "success"
				},
				function(){
					window.location.reload();
				});
			} else if(data == "errorExist") {
				swal("失败！", "不需要重复添加值班人员", "warning");
				$('#staffModal').modal('hide');
			} else {
				swal("失败！", "新增失败", "error");
				$('#staffModal').modal('hide');
			}
		},
		error: function() {
			swal("错误！", "发生错误", "error");
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