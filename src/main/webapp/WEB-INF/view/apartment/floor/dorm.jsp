<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>寝室管理</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"></jsp:include>

<!-- 修改寝室费用模态框（Modal） -->
<div class="modal fade" id="dormFeeModal" tabindex="-1" role="dialog" aria-labelledby="dormFeeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
	        <form id="dormForm" method="post">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="dormFeeModalLabel">宿舍费用修改</h4>
	            </div>
	            <div class="modal-body">
	            	<div class="form-horizontal" role="form">
	            		<input type="number" name="dormId" value="${dorm.id}" hidden/>
	            		<div class="form-group">
							<label for="dormNo" class="col-sm-4 control-label">宿舍号：</label>
							<div class="col-sm-8">
								<input class="form-control" type="number" name="dormNo" value="${dorm.dormNo}" disabled/>
					    	</div>
						</div>
						<div class="form-group">
							<label for="floorId" class="col-sm-4 control-label">楼层：</label>
							<div class="col-sm-8">
								<input class="form-control" type="number" name="floorNo" value="${dorm.floorNo}" disabled/>
					    	</div>
						</div>
						<div class="form-group">
							<label for="fee" class="col-sm-4 control-label">费用/人：</label>
							<div class="col-sm-8">
								<input class="form-control" type="number" name="fee" value="${dorm.fee}"/>
					    	</div>
						</div>
					</div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="dormUpdateBtn">提交更改</button>
	            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 修改床位学生模态框（Modal） -->
<div class="modal fade" id="bedStdModal" tabindex="-1" role="dialog" aria-labelledby="bedStdModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
	        <form id="dormStdForm" method="post">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="bedStdModalLabel">床位学生修改</h4>
	            </div>
	            <div class="modal-body">
	            	<div class="form-horizontal" role="form">
	            		<input type="number" name="bedId" id="bedId" hidden/>
						<input type="number" name="dormId" value="${dorm.id}" id="dormId" hidden/>
	            		<div class="form-group">
							<label for="stdId" class="col-sm-4 control-label">学号：</label>
							<div class="col-sm-8">
								<input class="form-control" type="number" name="stdId" id="stdId"/>
					    	</div>
						</div>
						<div class="form-group">
							<label for="floorId" class="col-sm-4 control-label">姓名：</label>
							<div class="col-sm-8">
								<span id="stdName"></span>
					    	</div>
						</div>
					</div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="dormStdChangeBtn">提交更改</button>
	            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="container" style="margin-top:50px">
	<div class="row">
		<h4 class="col-sm-2">${apartId}号楼，${floorDormId}寝室</h4>
		<div class="col-sm-8">
			<button class="btn btn-default" onClick="addBed(${dormId},${apartId})">新增床位</button>
			<button class="btn btn-default" data-toggle="modal" data-target="#dormFeeModal">修改寝室费用</button>
			<button class="btn btn-danger" onClick="deleteDorm(${dormId},${apartId})">删除寝室</button>
		</div>
	</div>
	<div class="row" style="padding-top: 20px">
		<c:if test="${empty bedList}">
			<div class="panel panel-default">
			    <div class="panel-body" style="text-align:center">
			    	该寝室还未有任何学生
			    </div>
			</div>
		</c:if>
		<c:forEach items="${bedList}" var="bed">
			<div class="col-sm-3">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="form-group row">
	                      <label class="col-sm-4 control-label">床号：</label>
	                      <div class="col-sm-8">
	                         <span>${bed.bedId}</span>
	                      </div>
	                   </div>
	                   <div class="form-group row">
	                      <label class="col-sm-4 control-label">学号：</label>
	                      <div class="col-sm-8">
	                         <span>${bed.stdId}</span>
	                      </div>
	                   </div>
	                   <div class="form-group row">
	                      <label class="col-sm-4 control-label">姓名：</label>
	                      <div class="col-sm-8">
		                      <div class="pull-left">
		                         <span>${bed.stdName}&nbsp;</span>
		                      </div>
	                         <c:if test="${bed.stdId == dorm.leaderId && bed.stdId != 1}">
	                         	<div class="pull-left" style="margin-top:5px">
	                      	 		<span class="label label-info">寝室长</span>
	                      	 	</div>
	                      	 </c:if>
	                      </div>
	                   </div>
	                   <div class="form-group row">
	                      <div class="col-sm-8 col-sm-offset-4">
	                         <button class="btn btn-default" type="button" data-toggle="modal" data-target="#bedStdModal" onClick="changeBedStd(${bed.bedId}, ${bed.dormId}, ${bed.stdId}, '${bed.stdName}')"><i class="fa fa-edit"></i></button>
	                         <button class="btn btn-danger" onClick="deleteBed(${bed.bedId}, ${bed.dormId}, ${apartId})"><i class="fa fa-trash-o"></i></button>
	                         <c:if test="${bed.stdId != dorm.leaderId && bed.stdId != 1}">
	                         	<button style="margin-top:5px" class="btn btn-default" onClick="updateDormLeader(${bed.dormId}, ${bed.stdId}, '${bed.stdName}',${apartId})">设为寝室长</button>
	                      	 </c:if>
	                      </div>
	                   </div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
</body>
<script>
	function changeBedStd(bedId, dormId, stdId, stdName) {
		$("#bedId").val(bedId);
		$("#dormId").val(dormId);
		$("#stdId").val(stdId);
		$("#stdName").text(stdName);
	}
	
	function updateDormLeader(dormId, stdId, stdName, apartId) {
		swal({ 
			title: "确定将该学生设为寝室长吗", 
			text: stdName, 
			type: "info", 
			showCancelButton: true, 
			closeOnConfirm: false,
			confirmButtonText:"确定",
	        cancelButtonText:"取消"
		},
		function(){ 
			$.ajax({
				type: "POST",
				datatype: "json",
				url: getRootPath()+"/apartment/"+apartId+"/dorm/leader/update",
				data: {"dormId":dormId, "stdId":stdId},
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data == 'success') {
						swal({ 
							title: "成功！", 
							text: "修改成功", 
							type: "success"
						},
						function(){
							window.location.reload();
						});
					} else if(data == 'error') {
						swal("失败！", "修改失败", "error");
					}
				},
				error: function() {
					swal("失败！", "发生错误", "error");
		        }
			});
		});
	}
	
	function addBed(dormId, apartId) {
		$.ajax({
			type: "POST",
			datatype: "json",
			url: getRootPath()+"/apartment/"+apartId+"/dorm/bed/create",
			data: {"dormId":dormId},
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == 'success') {
					swal({ 
						title: "成功！", 
						text: "新增成功", 
						type: "success"
					},
					function(){
						window.location.reload();
					});
				} else if(data == 'error') {
					swal("失败！", "新增失败", "error");
				}
			},
			error: function() {
				swal("失败！", "发生错误", "error");
	        }
		});
	}
	
	function deleteBed(bedId, dormId, apartId) {
		swal({ 
			title: "确定删除该床位吗", 
			text: "同时将删除该床位关联的学生", 
			type: "info", 
			showCancelButton: true, 
			closeOnConfirm: false,
			confirmButtonText:"确定",
	        cancelButtonText:"取消"
		},
		function(){ 
			$.ajax({
				type: "POST",
				datatype: "json",
				url: getRootPath()+"/apartment/"+apartId+"/dorm/bed/delete",
				data: {"dormId":dormId, "bedId":bedId},
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data == 'success') {
						swal({ 
							title: "成功！", 
							text: "删除成功", 
							type: "success"
						},
						function(){
							window.location.reload();
						});
					} else if(data == 'error') {
						swal("失败！", "删除失败", "error");
					}
				},
				error: function() {
					swal("失败！", "发生错误", "error");
		        }
			});
		});
	}
	
	$(function() {
		$("#dormUpdateBtn").click(function() {
			var apartId = ${apartId};
			$.ajax({
				type: "POST",
				datatype: "text",
				url: getRootPath()+"/apartment/"+apartId+"/dorm/update",
				data: $("#dormForm").serializeArray(),
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data == 'success') {
						swal({ 
							title: "成功！", 
							text: "修改成功", 
							type: "success"
						},
						function(){
							window.location.reload();
						});
					} else if(data == 'error') {
						swal("失败！", "修改失败", "error");
						$('#dormFeeModal').modal('hide');
					}
				},
				error: function() {
					swal("失败！", "发生错误", "error");
		        }
			});
		});
		$("#dormStdChangeBtn").click(function() {
			var apartId = ${apartId};
			$.ajax({
				type: "POST",
				datatype: "text",
				url: getRootPath()+"/apartment/"+apartId+"/dorm/student/update",
				data: $("#dormStdForm").serializeArray(),
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data == 'success') {
						swal({ 
							title: "成功！", 
							text: "修改成功", 
							type: "success"
						},
						function(){
							window.location.reload();
						});
					} else if(data == 'error') {
						swal("失败！", "修改失败", "error");
						$('#dormStdModal').modal('hide');
					}
				},
				error: function() {
					swal("失败！", "发生错误", "error");
		        }
			});
		});
		$("#leaderId").change(function(){
			var leaderId = $("#leaderId").val();
			$.ajax({
				type: "GET",
				datatype: "json",
				url: getRootPath() + "/student/stdName",
				data: {stdId: leaderId},
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					$("#leaderName").text(data);
				},
				error: function() {
					swal("失败！", "发生错误", "error");
		        }
			});
		});
		$("#stdId").change(function(){
			var stdId = $("#stdId").val();
			$.ajax({
				type: "GET",
				datatype: "json",
				url: getRootPath() + "/student/stdName",
				data: {stdId: stdId},
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					$("#stdName").text(data);
				},
				error: function() {
					swal("失败！", "发生错误", "error");
		        }
			});
		});
    });
	
	function deleteDorm(dormId, apartId) {
		swal({ 
			title: "确定删除该寝室吗？", 
			text: "请确定该公寓下没有任何关联", 
			type: "info", 
			showCancelButton: true, 
			closeOnConfirm: false,
			confirmButtonText:"确定",
	        cancelButtonText:"取消"
		},
		function(){
			$.ajax({
				type: "POST",
				datatype: "json",
				url: getRootPath()+"/apartment/"+apartId+"/dorm/"+dormId+"/delete",
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data == 'errorScore') {
						swal("失败！", "该寝室下存在关联寝室得分，请先删除联系!", "warning");
					} else if(data == 'errorRepair') {
						swal("失败！", "该寝室下存在关联维修，请先删除联系!", "warning");
					} else if(data == 'errorBed') {
						swal("失败！", "该寝室下存在关联学生，请先删除联系!", "warning");
					} else if(data =='success') {
						swal({ 
							title: "成功！", 
							text: "删除成功", 
							type: "success"
						},
						function(){
							window.location = getRootPath() + "/apartment/"+${apartId}+"/floor";
						});
					} else if(data == 'error') {
						swal("失败！", "删除失败", "error");
					}
				},
				error: function() {
					swal("错误！", "发生错误", "error");
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