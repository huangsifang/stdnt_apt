<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/table.css" rel="stylesheet">
	<style>
		.dormBrand {
			padding:10px;
			border-radius:50%;
			margin:10px;
			background-color:#eee
		}
	</style>
</head>
<body>
<div class="pull-right">欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a></div>

<c:if test="${not empty msg}">
	<script>alert("${msg}")</script>
</c:if>

<!-- 修改楼层寝室模态框（Modal） -->
<div class="modal fade" id="floorModal" tabindex="-1" role="dialog" aria-labelledby="floorModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
	        <form id="dormBedNumForm" method="post">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="floorModalLabel">公寓修改</h4>
	            </div>
	            <div class="modal-body">
	            	<!-- <form id="dormBedNumForm" method="post">
						宿舍数量：<input id="dormNum" type="number" name="dormNum"/><br />
						<input id="currentDormNum" type="number" name="currentDormNum" hidden/>
						床位数量/宿舍：<input type="number" name="aDormBedNum" value="4"/><br />
						费用/床：<input type="number" name="dormFee" value="1200"/><br />
						<button type="submit">提交</button>
					</form> -->
	            	<div class="form-horizontal" role="form">
	            		<div class="form-group">
							<label for="dormNum" class="col-sm-4 control-label">宿舍数量：</label>
							<div class="col-sm-8">
								<input class="form-control" type="number" id="dormNum" name="dormNum"/>
					    	</div>
						</div>
						<input id="floorId" type="number" hidden/>
						<input id="currentDormNum" type="number" name="currentDormNum" hidden/>
						<div class="form-group">
							<label for="aDormBedNum" class="col-sm-4 control-label">床位数量/宿舍：</label>
							<div class="col-sm-8">
								<input class="form-control" type="number" name="aDormBedNum" value="4"/>
					    	</div>
						</div>
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
<div class="pull-left">
	<button class="btn btn-default" onClick="addFloor(${apartId},${floorNum})">新增一层楼</button>
</div>
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
                	<span>${floor.dormNum}</span>
                	<button type="button" class="btn btn-default" data-toggle="modal" data-target="#floorModal" onClick="showNumForm(${floor.id},${floor.dormNum})">修改</button>
                </td>
                <td style="font-size:1em">
                	<c:forEach items="${floor.dormList}" var="dorm">
                		<span class="dormBrand">
                			<a href="${pageContext.request.contextPath}/apartment/dorm/${dorm.id}">${dorm.dormNo}</a>
                		</span>
                	</c:forEach>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script> 
<script src="${pageContext.request.contextPath}/public/js/bootstrap.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
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
		closeOnConfirm: false
	},
	function(){
		$.ajax({
			type: "POST",
			datatype: "json",
			data: {"floorNum":floorNum},
			url: getRootPath() + "/apartment/"+apartId+"/floor/create",
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == "新增成功!") {
					window.location.reload();
					swal("成功！", data, "success");
				} else if(data == "新增失败!") {
					swal("失败！", data, "error");
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
			if(data == "新增成功") {
				swal({ 
					title: "成功！", 
					text: data, 
					type: "success"
				},
				function(){
					window.location.reload();
				});
			} else if(data == "新增失败") {
				swal("失败！", data, "error");
				$('#floorModal').modal('hide');
			} else if(data == "没有任何新增宿舍或床位") {
				swal("失败！", data, "warning");
				$('#floorModal').modal('hide');
			}
		},
		error: function() {
			swal("失败！", "发生错误", "error");
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