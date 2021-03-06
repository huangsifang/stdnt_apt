<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>楼层管理</title>
	<style>
		.badge {
			font-size: 1.2em !important;
			margin: 10px;
			background-color:#cdcdcd !important;
		}
		.badge:hover {
			background-color:#777;
		}
		a:hover {
			text-decoration: none;
		}
	</style>
</head>
<body>
<jsp:include page="../../navbar.jsp"></jsp:include>

<!-- 修改楼层寝室模态框（Modal） -->
<div class="modal fade" id="floorModal" tabindex="-1" role="dialog" aria-labelledby="floorModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
	        <form id="dormBedNumForm" method="post">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="floorModalLabel">楼层宿舍修改</h4>
	            </div>
	            <div class="modal-body">
	            	<div class="form-horizontal" role="form">
	            		<div class="form-group">
							<label for="dormNum" class="col-sm-4 control-label">宿舍数量：</label>
							<div class="col-sm-8">
								<input class="form-control" type="number" id="dormNum" name="dormNum"/>
					    	</div>
						</div>
						<input id="floorId" type="number" hidden/>
						<input id="currentDormNum" type="number" name="currentDormNum" hidden/>
						<!-- <div class="form-group">
							<label for="aDormBedNum" class="col-sm-4 control-label">床位数量/宿舍：</label>
							<div class="col-sm-8">
								<input class="form-control" type="number" name="aDormBedNum" value="4"/>
					    	</div>
						</div> -->
						<div class="form-group">
							<label for="dormFee" class="col-sm-4 control-label">费用/床：</label>
							<div class="col-sm-8">
								<input class="form-control" type="number" name="dormFee" value="1200"/>
					    	</div>
						</div>
					</div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onClick="updateFloorDorm(${apartId})">提交更改</button>
	            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="container">
	<div class="card">
		<div class="header">
			<h4 class="pull-left">公寓楼层寝室表</h4>
			<button class="btn btn-default pull-right" onClick="addFloor(${apartId},${floorNum})">新增一层楼</button>
		</div>
		<div class="content table-responsive">
			<table class="table">
			    <thead>
			        <tr>
			            <th>楼层号</th>
			            <th>宿舍数量</th>
			            <th>宿舍</th>
			        </tr>
			    </thead>
			    <tbody>
			        <c:forEach items="${floorList}" var="floor">
			            <tr>
			                <td>${floor.floorNo}</td>
			                <td>
			                	<span>${floor.dormNum}</span><br />
			                	<button type="button" class="btn btn-default" style="margin-top:10px" data-toggle="modal" data-target="#floorModal" onClick="showNumForm(${floor.id},${floor.dormNum})"><i class="fa fa-edit"></i></button>
			                	<button type="button" class="btn btn-danger" style="margin-top:10px" onClick="deleteFloor(${apartId}, ${floor.id})"><i class="fa fa-trash-o"></i></button>
			                </td>
			                <td style="font-size:1em">
			                	<c:forEach items="${floor.dormList}" var="dorm">
			                		<a href="${pageContext.request.contextPath}/apartment/${apartId}/dorm/${dorm.id}">
				                		<span class="badge">
				                			${dorm.dormNo}
				                		</span>
			                		</a>
			                	</c:forEach>
			                </td>
			            </tr>
			        </c:forEach>
			    </tbody>
			</table>
		</div>
	</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery.form.min.js" ></script> 
<script>
function showNumForm(floorId, dormNum) {
	$("#floorId").val(floorId);
	//$("#dormBedNumForm").attr("action",floorId+"/dorm/create");
	$("#dormNum").val(dormNum);
	$("#currentDormNum").val(dormNum);
}
function addFloor(apartId, floorNum) {
	var floorNo = floorNum+1;
	swal({ 
		title: "确定增加一层楼吗？", 
		text: "增加第"+floorNo+"层楼", 
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
			data: {"floorNum":floorNum},
			url: getRootPath() + "/apartment/"+apartId+"/floor/create",
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
				} else if(data == "error") {
					swal("失败！", "新增失败", "error");
				}
			},
			error: function() {
				swal("失败！", "发生错误", "error");
	        }
		});
	});
}
function updateFloorDorm(apartId) {
	var floorId = $("#floorId").val();
	$.ajax({
		type: "POST",
		datatype: "json",
		data: $("#dormBedNumForm").serializeArray(),
		url: getRootPath() + "/apartment/"+apartId+"/"+floorId+"/dorm/create",
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
			} else if(data == "error") {
				swal("失败！", "新增失败", "error");
				$('#floorModal').modal('hide');
			} else if(data == "errorEmpty") {
				swal("失败！", "没有任何新增宿舍或床位", "warning");
				$('#floorModal').modal('hide');
			}
		},
		error: function() {
			swal("失败！", "发生错误", "error");
        }
	});
}
function deleteFloor(apartId, floorId) {
	swal({ 
		title: "确定删除该楼层吗？", 
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
			url: "floor/"+floorId+"/delete",
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == 'errorScore') {
					swal("失败！", "该楼层下存在关联寝室得分，请先删除联系!", "warning");
				} else if(data == 'errorRepair') {
					swal("失败！", "该楼层下存在关联维修，请先删除联系!", "warning");
				} else if(data == 'errorBed') {
					swal("失败！", "该楼层下存在关联学生，请先删除联系!", "warning");
				} else if(data =='success') {
					swal("成功！", "删除成功", "success");
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