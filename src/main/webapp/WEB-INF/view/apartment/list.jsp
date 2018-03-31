<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/uploadForm.css" rel="stylesheet">
</head>
<body>

<!-- 修改公寓模态框（Modal） -->
<div class="modal fade" id="apartModal" tabindex="-1" role="dialog" aria-labelledby="apartModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="apartModalLabel">公寓修改</h4>
            </div>
            <div class="modal-body">
            	<form id="apartForm" method="post" class="form-horizontal" role="form">
            		<div class="form-group">
						<label for="apartId" class="col-sm-3 control-label">寝室号：</label>
						<div class="col-sm-8">
							<input class="form-control" type="number" id="apartId" name="apartId" disabled/>
				    	</div>
					</div>
					<div class="form-group">
						<label for="apartName" class="col-sm-3 control-label">寝室名：</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" id="apartName" name="apartName"/>
				    	</div>
					</div>
					<div class="form-group" id="staff">
						<div class="col-sm-3 control-label">
							<div><label for="apartName">管理员：</label></div>
							<button class="btn btn-default" id="addStaff" type="button">增加</button>
						</div>
						<div class="col-sm-8" id="staffInfo"></div>
					</div>
				</form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="apartChangeBtn">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 新增公寓模态框（Modal） -->
<div class="modal fade" id="apartAddModal" tabindex="-1" role="dialog" aria-labelledby="apartAddModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="apartAddModalLabel">公寓新增</h4>
            </div>
            <div class="modal-body">
            	<form id="apartAddForm" method="post" class="form-horizontal" role="form">
            		<div class="form-group">
						<label for="apartId" class="col-sm-3 control-label">寝室号：</label>
						<div class="col-sm-8">
							<input class="form-control" type="number" name="apartId"/>
				    	</div>
					</div>
					<div class="form-group">
						<label for="apartName" class="col-sm-3 control-label">寝室名：</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="apartName"/>
				    	</div>
					</div>
					<div class="form-group">
						<label for="floorNum" class="col-sm-3 control-label">楼层数：</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="floorNum"/>
				    	</div>
					</div>
					<div class="form-group">
						<label for="aFloorDormNum" class="col-sm-3 control-label">寝室数/层：</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="aFloorDormNum"/>
				    	</div>
					</div>
					<div class="form-group">
						<label for="aDormBedNum" class="col-sm-3 control-label">床位数/寝室：</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="aDormBedNum"/>
				    	</div>
					</div>
					<div class="form-group">
						<label for="aStdYearFee" class="col-sm-3 control-label">费用/人：</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="aStdYearFee"/>
				    	</div>
					</div>
				</form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="apartAddBtn">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div style="margin:20px 50px">
	<div class="pull-right">欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a></div>
	<shiro:hasPermission name="apartment:create">
		<div class="pull-left" style="padding:20px">
			<button class="btn btn-default" type="button" data-toggle="modal" data-target="#apartAddModal">公寓新增</button>
		</div>
	    <form action="${pageContext.request.contextPath}/uploadInfo/uploadInfoFromType.do" method="post" name="formApart" id="formApart" onsubmit="return validate(formApart)" enctype="multipart/form-data"  class="fileForm uploadForm pull-left">
		     导入公寓信息： 
			<a href="javascript:;" class="file">选择文件
			    <input type="file" name="filename" id="importApartFile" accept="xlsx" onchange="importFileFun(importApartFile, apartFileName)"/>
			</a>
			<input class="fileName" id="apartFileName" value="未选择文件" disabled/>
			<input type="hidden" name="filetype" value="apartment"/>
			<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
			<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
		</form>
	</shiro:hasPermission>
	
	<table class="table">
	    <thead>
	        <tr>
	            <th>公寓号</th>
	            <th>公寓名</th>
	            <th>楼层数</th>
	            <th>宿舍数</th>
	            <th>管理员</th>
	            <th>操作</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach items="${apartList}" var="apart">
	            <tr>
	                <td>${apart.apartId}</td>
	                <td>${apart.apartName}</td>
	                <td><a href="apartment/${apart.apartId}/floor">${apart.floorNum}</a></td>
	                <td>${apart.dormNum}</td>
	                <td>
	                	<c:forEach items="${apart.staffs}" var="staff">
	                		${staff.staffId}:${staff.staffName}<br />
	                	</c:forEach>
	                </td>
	                <td>
	                    <shiro:hasPermission name="apartment:update">
	                    	<button class="btn btn-default" type="button" data-toggle="modal" data-target="#apartModal" onClick="updateApart(${apart.apartId},'${apart.apartName}','${apart.staffsStr}')">修改</button>
	                    </shiro:hasPermission>
	
	                    <shiro:hasPermission name="apartment:delete">
	                    	<button class="btn btn-danger" onClick="deleteApart(${apart.apartId})">删除</button>
	                    </shiro:hasPermission>
	                </td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
</div>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script> 
<script src="${pageContext.request.contextPath}/public/js/bootstrap.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
<script>
	var staffNum = 0;
	$(function() {
		$(".uploadForm").ajaxForm({
			beforeSubmit: function(formData, jqForm, options) {
	            return true; 
	        },
			success: function(data){
				if(data == 'errorEmpty') {
					swal("失败！", "请确认文件内容没有空缺", "error");
				} else if(data == 'error') {
					swal("失败！", "导入失败......", "error");
				} else {
					swal("成功！", data, "success");
				}
	        },
	        error: function() {
	        	swal("错误！", "发生错误", "error");
	        },
	        resetForm: true        // 成功提交后，重置所有的表单元素的值
		});
		$("#addStaff").click(function() {
			$("#staffInfo").append("<div style='margin-bottom:20px'><div class='col-sm-6'><input class='form-control' type='number' name='staffId' id='staffId"+staffNum+"' onChange='changeStaff("+staffNum+")'></div><span id='staffName"+staffNum+"'></span></div>");
		});
		$("#apartChangeBtn").click(function() {
			var apartId = $("#apartId").val();
			$.ajax({
				type: "POST",
				datatype: "text",
				url: "apartment/"+apartId+"/update",
				data: $("#apartForm").serializeArray(),
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data == 'success') {
						swal("成功！", "修改成功", "success");
						$('#apartModal').modal('hide');
					} else {
						swal("成功！", "修改失败", "error");
					}
				},
				error: function() {
					swal("错误！", "发生错误", "error");
		        }
			});
		});
		$("#apartAddBtn").click(function() {
			$.ajax({
				type: "POST",
				datatype: "text",
				url: "apartment/create",
				data: $("#apartAddForm").serializeArray(),
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data == 'success') {
						swal("成功！", "新增成功", "success");
						$('#apartAddModal').modal('hide');
					} else {
						swal("失败！", "新增失败", "error");
					}
				},
				error: function() {
					swal("错误！", "发生错误", "error");
		        }
			});
		});
	});
	function changeStaff(i) {
		var staffId = $("#staffId"+i).val();
		$.ajax({
			type: "GET",
			datatype: "json",
			url: getRootPath() + "/staff/staffName",
			data: {staffId: staffId},
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == "") {
					swal("错误！", "未找到该职工!", "error");
				}
				$("#staffName"+i).text(data);
			},
			error: function() {
				swal("错误！", "发生错误", "error");
	        }
		});
	}
	function updateApart(apartId, apartName, staffsStr) {
		$("#apartId").val(apartId);
		$("#apartName").val(apartName);
		var staffs = staffsStr.split(",");
		var len = staffs.length;
		staffNum = len;
		$("#staffInfo").text("");
		for(var i=0; i<len; i++) {
			var index = staffs[i].indexOf(':');
			var staffId = staffs[i].slice(0, index);
			var staffName = staffs[i].slice(index+1, staffs[i].length);
			$("#staffInfo").append("<div style='margin-bottom:20px'><div class='col-sm-6'><input class='form-control' type='number' name='staffId' id='staffId"+i+"' onChange='changeStaff("+i+")' value="+staffId+"></div><span id='staffName"+i+"'>"+staffName+"</span>&nbsp;&nbsp;<button class='btn btn-default' type='button' onClick='deleteStaff("+apartId+","+staffId+")'>删除</button></div>");
		}
	}
	function deleteApart(apartId) {
		$.ajax({
			type: "POST",
			datatype: "json",
			url: "apartment/"+apartId+"/delete",
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == 'errorStaff') {
					swal("失败！", "该公寓下存在关联管理员，请先删除联系!", "warning");
				} else if(data == 'errorScore') {
					swal("失败！", "该公寓下存在关联寝室得分，请先删除联系!", "warning");
				} else if(data == 'errorHoliday') {
					swal("失败！", "该公寓下存在关联假期记录，请先删除联系!", "warning");
				} else if(data == 'errorRepair') {
					swal("失败！", "该公寓下存在关联维修，请先删除联系!", "warning");
				} else if(data == 'errorBed') {
					swal("失败！", "该公寓下存在关联学生，请先删除联系!", "warning");
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
	}
	function deleteStaff(apartId, staffId) {
		$.ajax({
			type: "POST",
			datatype: "json",
			url: "apartment/staff/delete",
			data: {apartId: apartId, staffId: staffId},
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == 'success') {
					swal("成功！", "删除成功", "success");
					$('#apartModal').modal('hide');
				} else {
					swal("失败！", "删除失败", "error");
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
	function importFileFun(fileID, fileNameID) {
		var fileName = $(fileID).val();
		if(fileName==undefined || fileName=="")
			$(fileNameID).val("未选择文件");
		else
			$(fileNameID).val(fileName);
	}
	function validate(formID) {
		if (formID.filename.value == "") {
			swal("失败", "请选择要上传的文件", "error");
			return false;
		}
	}
</script> 
</html>