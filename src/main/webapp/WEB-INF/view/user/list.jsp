<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/table.css" rel="stylesheet">
</head>
<body>

<div style="margin:20px 50px">
	<div class="pull-right">欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a></div>
	
	<c:if test="${not empty msg}">
	    <div>${msg}</div>
	</c:if>
	
	<!-- 新增修改用户模态框（Modal） -->
	<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="userModalLabel">用户新增</h4>
	            </div>
	            <div class="modal-body">
	            	<form id="userForm" method="post" class="form-horizontal" role="form">
	            		<input type="text" id="operationType" value="add" hidden/>
	            		<input type="number" id="userId" hidden/>
	            		<div id="usernameForm">
		            		<div class="form-group">
								<label for="username" class="col-sm-3 control-label">用户名：</label>
								<div class="col-sm-8">
									<input class="form-control" type="number" id="username" name="username"/>
						    	</div>
							</div>
						</div>
						<div class="form-group" id="passwordForm">
							<label for="password" class="col-sm-3 control-label">密码：</label>
							<div class="col-sm-8">
								<input class="form-control" type="password" name="password"/>
					    	</div>
						</div>
						<div class="form-group">
							<label for="roleIds" class="col-sm-3 control-label">角色列表：</label>
							<div class="col-sm-8">
								<select multiple class="form-control" name="roleIds" id="roleIds">
									<c:forEach items="${roleList}" var="role">
										<option value="${role.id}">${role.description}</option>
									</c:forEach>
							    </select>
					    	</div>
						</div>
						<div id="userInfo">
							<div class="form-group">
								<label for="password" class="col-sm-3 control-label">姓名：</label>
								<div class="col-sm-8">
									<input class="form-control" type="text" name="name" id="name"/>
						    	</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-8">
									<label class="radio-inline">
								        <input type="radio" name="sex" value="male" checked> 男
								    </label>
								    <label class="radio-inline">
								        <input type="radio" name="sex" value="female"> 女
								    </label>
							    </div>
						    </div>
							<div class="form-group">
								<label for="password" class="col-sm-3 control-label">手机号码：</label>
								<div class="col-sm-8">
									<input class="form-control" type="text" name="tel" id="tel"/>
						    	</div>
							</div>
							<div class="form-group" id="hiredateForm">
								<label for="hiredate" class="col-sm-3 control-label">入职/入学时间：</label>
								<div class="col-sm-8">
									<input class="form-control" type="text" name="hiredate" id="enterDate"/>
						    	</div>
							</div>
							<div class="form-group" id="isPartyForm">
								<div class="col-sm-offset-3 col-sm-8">
									<div class="checkbox">
								        <label>
								        	<input type="text" name="isParty" id="isPartyHidden" hidden/>
								        	<input type="checkbox" id="isParty">是否党员
								        </label>
								  	</div>
						    	</div>
							</div>
							<div class="form-group" id="classIdForm">
								<label for="classId" class="col-sm-3 control-label">班级：</label>
								<div class="col-sm-8">
									<select class="form-control" name="classId" id="classId">
										<c:forEach items="${classList}" var="speciClass">
								    		<option value="${speciClass.classId}">${speciClass.className}</option>
								    	</c:forEach>
								    </select>
						    	</div>
							</div>
						</div>
					</form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="userBtn">提交更改</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	
	<!-- 修改密码模态框（Modal） -->
	<div class="modal fade" id="passwordModal" tabindex="-1" role="dialog" aria-labelledby="passwordModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="passwordModalLabel">用户新增</h4>
	            </div>
	            <div class="modal-body">
	            	<form id="chnagePasswordForm" method="post" class="form-horizontal" role="form">
	            		<input type="number" id="userIdPassword" hidden/>
	            		<div class="form-group">
							<label for="newPassword" class="col-sm-3 control-label">新密码：</label>
							<div class="col-sm-8">
								<input class="form-control" type="password" id="newPassword" name="newPassword"/>
					    	</div>
						</div>
					</form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="passwordBtn">提交更改</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	
	<shiro:hasPermission name="user:create">
		<button class="btn btn-default pull-left" data-toggle="modal" data-target="#userModal" onClick="addUser()">用户新增</button>
	</shiro:hasPermission>
	
	<table class="table">
	    <thead>
	        <tr>
	            <th>用户名</th>
	            <th>角色列表</th>
	            <th>操作</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach items="${userList}" var="user">
	            <tr>
	                <td>${user.username}</td>
	                <td>${user.roleIdsStr}</td>
	                <td>
	                    <shiro:hasPermission name="user:update">
	                    	<c:if test="${user.username != 'admin'}">
	                    		<button class="btn btn-default" data-toggle="modal" data-target="#userModal" onClick="updateUser(${user.id}, ${user.username}, ${user.roleIds})">修改</button>
	                    	</c:if>
	                    </shiro:hasPermission>
	
	                    <shiro:hasPermission name="user:delete">
	                    	<button class="btn btn-danger" onClick="deleteUser(${user.id},${user.username},'${user.roleIdsStr}')">删除</button>
	                    </shiro:hasPermission>
	
	                    <shiro:hasPermission name="user:update">
	                    	<button class="btn btn-warning" data-toggle="modal" data-target="#passwordModal" onClick="changePasswordUser(${user.id})">改密</button>
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
<script src="${pageContext.request.contextPath}/public/js/bootstrap-datetimepicker.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/bootstrap-datetimepicker.zh-CN.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
<script>
	$(function() {
		$("#userInfo").hide();
		$("#userBtn").click(function() {
			var url = "";
			if($("#operationType").val() == 'add') {
				url = "user/create";
			} else {
				var userId = $("#userId").val();
				url = "user/"+userId+"/update";
			}
			$.ajax({
				type: "POST",
				datatype: "json",
				url: url,
				data: $("#userForm").serializeArray(),
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data == "success") {
						swal("成功！", "操作成功", "success");
						$('#userModal').modal('hide');
					} else {
						swal("失败！", "操作失败", "error");
					}
				},
				error: function() {
					swal("错误！", "发送错误", "error");
		        }
			});
		});
		
		$("#passwordBtn").click(function() {
			var userId = $("#userIdPassword").val();
			$.ajax({
				type: "POST",
				datatype: "json",
				url: "user/"+userId+"/changePassword",
				data: $("#chnagePasswordForm").serializeArray(),
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data == "success") {
						swal("成功！", "修改成功", "success");
						$('#passwordModal').modal('hide');
					} else {
						swal("失败！", "修改失败", "error");
					}
				},
				error: function() {
					swal("错误！", "发送错误", "error");
		        }
			});
		});
		
		$("#roleIds").change(function(){
			var roleIds = $("#roleIds").val();
			if(roleIds == 1) {
				$("#userInfo").hide();
			} else {
				$("#userInfo").show();
				if(roleIds.indexOf("2") != -1) {
					$("#hiredateForm").show();
					$("#isPartyForm").hide();
					$("#classIdForm").hide();
				} else if(roleIds.indexOf("4") != -1) {
					$("#hiredateForm").show();
					$("#isPartyForm").show();
					$("#classIdForm").show();
				} else {
					$("#hiredateForm").hide();
					$("#isPartyForm").hide();
					$("#classIdForm").hide();
				}
			}
		});
	});
	$("#isParty").change(function() {
		if($("#isParty").prop("checked")) {
			$("#isPartyHidden").val("true");
		} else {
			$("#isPartyHidden").val("false");
		}
	});
	function addUser() {
		$("#operationType").val("add");
		$("#username").val('');
		$("#roleIds option").attr("selected",false);
		$('#usernameForm').show(); 
		$("#passwordForm").show();
	}
	function updateUser(id, username, roleIds) {
		$("#operationType").val("update");
		$("#userId").val(id);
		$("#username").val(username);
		$('#usernameForm').hide();
		$("#passwordForm").hide();
		$("#roleIds option").attr("selected",false);
		roleIds.forEach(function(item, index, array) {
			$("#roleIds").find("option[value='"+item+"']").attr("selected",true);
		});
		if(roleIds.indexOf(1) == -1) {
			$("#userInfo").show();
			$("#hiredateForm").hide();
			$("#isPartyForm").hide();
			$("#classIdForm").hide();
		} else {
			$("#userInfo").hide();
		}
		if(roleIds.indexOf(2) != -1) {
			$("#hiredateForm").show();
			$.ajax({
				type: "GET",
				datatype: "json",
				url: "staff/"+username,
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					$("#name").val(data.staffName);
					var sex = "male";
					if(data.staffSex == 2) {
						sex = "female";
					}
					$(":radio[name='sex'][value='" + sex + "']").prop("checked", "checked");
					$("#tel").val(data.staffTel);
					$("#enterDate").val(data.hiredate.substring(0,10));
				},
				error: function() {
					swal("错误！", "发送错误", "error");
		        }
			});
		}
		if(roleIds.indexOf(3) != -1) {
			$.ajax({
				type: "GET",
				datatype: "json",
				url: "consellor/"+username,
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					$("#name").val(data.consellName);
					var sex = "male";
					if(data.consellSex == 2) {
						sex = "female";
					}
					$(":radio[name='sex'][value='" + sex + "']").prop("checked", "checked");
					$("#tel").val(data.consellTel);
				},
				error: function() {
					swal("错误！", "发送错误", "error");
		        }
			});
		}
		if(roleIds.indexOf(4) != -1) {
			$("#hiredateForm").show();
			$("#isPartyForm").show();
			$("#classIdForm").show();
			$.ajax({
				type: "GET",
				datatype: "json",
				url: "student/"+username,
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					$("#name").val(data.stdName);
					var sex = "male";
					if(data.stdSex == 2) {
						sex = "female";
					}
					$(":radio[name='sex'][value='" + sex + "']").prop("checked", "checked");
					$("#tel").val(data.stdTel);
					$("#enterDate").val(data.enterTime.substring(0,10));
					if(data.party) {
						$("#isParty").attr('checked', true);
						$("#isPartyHidden").val("true");
					} else {
						$("#isParty").removeAttr('checked');
						$("#isPartyHidden").val("false");
					}
					$("#classId").val(data.classId);
				},
				error: function() {
					swal("错误！", "发送错误", "error");
		        }
			});
		}
		if(roleIds.indexOf(5) != -1) {
			$.ajax({
				type: "GET",
				datatype: "json",
				url: "repair/repairman/"+username,
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					$("#name").val(data.repairmanName);
					var sex = "male";
					if(data.repairmanSex == 2) {
						sex = "female";
					}
					$(":radio[name='sex'][value='" + sex + "']").prop("checked", "checked");
					$("#tel").val(data.repairmanTel);
				},
				error: function() {
					swal("错误！", "发送错误", "error");
		        }
			});
		}
	}
	function deleteUser(userId, username, roleIdsStr) {
		swal({ 
			title: "确定删除吗？", 
			text: "你将无法恢复该用户！", 
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
				url: "user/"+userId+"/delete",
				data: {"username":username,"roleIdsStr":roleIdsStr},
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data == "success") {
						swal("成功！", "删除成功", "success");
					} else {
						swal("失败！", "删除失败", "error");
					}
				},
				error: function() {
					swal("错误！", "发送错误", "error");
		        }
			});
		});
	}
	function changePasswordUser(id) {
		$("#userIdPassword").val(id);
	}
	$('#enterDate').datetimepicker({
		language:  'zh-CN', 
		format:'yyyy-mm-dd', 
		weekStart: 1, /*以星期一为一星期开始*/
		todayBtn:  1,
		autoclose: 1, 
		minView:2, /*精确到天*/
		pickerPosition: "bottom-left"
	}).on("changeDate",function(ev){
		
	});
</script>
</html>