<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>用户管理</title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="../navbar.jsp"></jsp:include>
	
<div class="container">
	
	<!-- 新增修改用户模态框（Modal） -->
	<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="userModalLabel">新增用户</h4>
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
						<div class="form-group" id="roleIdsForm">
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
							<div class="form-group" id="speciIdForm">
								<label for="classId" class="col-sm-3 control-label">专业：</label>
								<div class="col-sm-8">
									<select class="form-control" name="speciId" id="speciId">
										<c:forEach items="${speciList}" var="speci">
								    		<option value="${speci.speciId}">${speci.speciName}</option>
								    	</c:forEach>
								    </select>
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
							<div class="form-group" id="repairTypeForm">
								<label for="typeId" class="col-sm-3 control-label">维修类型：</label>
								<div class="col-sm-8">
									<select multiple class="form-control" name="typeIds" id="typeIds">
										<c:forEach items="${allTypeList}" var="type">
								    		<option value="${type.typeId}">${type.typeName}</option>
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
	
	<div class="card">
		<div class="header">
			<c:if test="${not empty roleList}">
			<ul class="nav nav-tabs" id="roleUl">
			</c:if>
				<c:forEach items="${roleList}" var="role">
					<li><a href="${pageContext.request.contextPath}/user/role/${role.id}">${role.description}</a></li>
			    </c:forEach>
			    <shiro:hasPermission name="user:create">
					<button class="btn btn-default pull-right" data-toggle="modal" data-target="#userModal" onClick="addUser()">用户新增</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="userStudent:create">
					<h4 class="pull-left" style="padding-left:10px">学生信息表</h4>
					<button class="btn btn-default pull-right" data-toggle="modal" data-target="#userModal" onClick="addStudent()">学生新增</button>
				</shiro:hasPermission>
			<c:if test="${not empty roleList}">
			</ul>
			</c:if>
		</div>
		<div class="content table-responsive">
			<table class="table">
			    <thead>
			        <tr>
			            <th>用户名</th>
			            <th>姓名</th>
			            <c:if test="${!user.isContainRoleId(1)}">
			            <th>角色</th>
			            </c:if>
			            <th>操作</th>
			        </tr>
			    </thead>
			    <tbody>
			    	<c:if test="${empty userList}">
			    		<tr>
			    			<td colspan="3" style="text-align:center">还没有任何用户！</td>
			    		</tr>
			    	</c:if>
			        <c:forEach items="${userList}" var="user">
			            <tr>
			                <td>${user.username}</td>
			                <c:if test="${!user.isContainRoleId(1)}">
			                <td>${user.name}</td>
			                </c:if>
			                <td>
			                <c:forEach items="${user.roles}" var="role">
			                	${role.description}<br />
			                </c:forEach>
			                </td>
			                <td>
			                    <shiro:hasPermission name="user:update">
			                    	<c:if test="${!user.isContainRoleId(1)}">
			                    		<button class="btn btn-default" data-toggle="modal" data-target="#userModal" onClick="updateUser(${user.id}, ${user.username}, ${user.roleIds})"><i class="fa fa-edit"></i></button>
			                    	</c:if>
			                    </shiro:hasPermission>
			                    <c:if test="${user.isContainRoleId(4)}">
			                    	<shiro:hasPermission name="userStudent:update">
				                    	<c:if test="${!user.isContainRoleId(1)}">
				                    		<button class="btn btn-default" data-toggle="modal" data-target="#userModal" onClick="updateUser(${user.id}, ${user.username}, ${user.roleIds})"><i class="fa fa-edit"></i></button>
				                    	</c:if>
			                    	</shiro:hasPermission>
			                    </c:if>
			
			                    <shiro:hasPermission name="user:delete">
			                    	<button class="btn btn-danger" onClick="deleteUser(${user.id},${user.username},'${user.roleIdsStr}')"><i class="fa fa-trash-o"></i></button>
			                    </shiro:hasPermission>
			                    
			                    <c:if test="${user.isContainRoleId(4)}">
			                    	<shiro:hasPermission name="userStudent:update">
				                    	<button class="btn btn-danger" onClick="deleteUser(${user.id},${user.username},'${user.roleIdsStr}')"><i class="fa fa-trash-o"></i></button>
			                    	</shiro:hasPermission>
			                    </c:if>
			
			                    <shiro:hasPermission name="user:update">
			                    	<button class="btn btn-warning" data-toggle="modal" data-target="#passwordModal" onClick="changePasswordUser(${user.id})"><i class="fa fa-unlock"></i>&nbsp;&nbsp;改密</button>
			                    </shiro:hasPermission>
			                    
			                    <c:if test="${user.isContainRoleId(4)}">
			                    	<shiro:hasPermission name="userStudent:update">
				                    	<button class="btn btn-warning" data-toggle="modal" data-target="#passwordModal" onClick="changePasswordUser(${user.id})"><i class="fa fa-unlock"></i>&nbsp;&nbsp;改密</button>
			                    	</shiro:hasPermission>
			                    </c:if>
			                </td>
			            </tr>
			        </c:forEach>
			    </tbody>
			</table>
			<c:if test="${allCount != 0}">
				<ul class="pagination tablePage">
				    <li><a href="${pageContext.request.contextPath}/user/role/${roleId}?start=${start-10}">&laquo;</a></li>
				    <c:forEach begin="0" end="${allCount-1}" var="item" step="10">
				    	<li value="${item/10+1}"><a href="${pageContext.request.contextPath}/user/role/${roleId}?start=${item}"><fmt:formatNumber type="number" value="${item/10+1}" maxFractionDigits="0"/></a></li>
				    </c:forEach>
				    <li><a href="${pageContext.request.contextPath}/user/role/${roleId}?start=${start+10}">&raquo;</a></li>
				</ul>
			</c:if>
		</div>
	</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/public/js/bootstrap-datetimepicker.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/bootstrap-datetimepicker.zh-CN.js" ></script>
<script>
	$(function() {
		var start = Number('${start}');
		var pageNum = start/10+1;
		$(".tablePage li").eq(pageNum).addClass("active");
		if(start <= 0) {
			$(".tablePage li").eq(0).find("a").attr("href","#");
			$(".tablePage li").eq(0).addClass("disabled");
		}
		if(start+10 >= Number('${allCount}')){
			$(".tablePage li").eq(-1).find("a").attr("href","#");
			$(".tablePage li").eq(-1).addClass("disabled");
		}
		
		var nowRoleId = Number('${roleId}')-1;
		$("#roleUl li").eq(nowRoleId).addClass("active");
		$("#userInfo").hide();
		$("#userBtn").click(function() {
			var url = "";
			if($("#operationType").val() == 'add') {
				url = getRootPath() + "/user/create";
			} else {
				var userId = $("#userId").val();
				url = getRootPath() + "/user/"+userId+"/update";
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
					swal("错误！", "发生错误", "error");
		        }
			});
		});
		
		$("#passwordBtn").click(function() {
			var userId = $("#userIdPassword").val();
			$.ajax({
				type: "POST",
				datatype: "json",
				url: getRootPath() + "/user/"+userId+"/changePassword",
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
					swal("错误！", "发生错误", "error");
		        }
			});
		});
		
		$("#roleIds").change(function(){
			var roleIds = $("#roleIds").val();
			if(roleIds == 1) {
				$("#userInfo").hide();
			} else {
				$("#userInfo").show();
				$("#hiredateForm").hide();
				$("#isPartyForm").hide();
				$("#speciIdForm").hide();
				$("#classIdForm").hide();
				$("#repairTypeForm").hide();
				if(roleIds.indexOf("2") != -1) {
					$("#hiredateForm").show();
				}
				if(roleIds.indexOf("4") != -1) {
					$("#hiredateForm").show();
					$("#isPartyForm").show();
					$("#speciIdForm").show();
					$("#classIdForm").show();
				}
				if(roleIds.indexOf("5") != -1) {
					$("#repairTypeForm").show();
				}
			}
		});
		
		$("#speciId").change(function(){
			var speciId = $("#speciId").val();
			$.ajax({
				type: "GET",
				datatype: "json",
				url: getRootPath() + "/class/"+speciId,
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					$("#classId").text('');
					for(var i=0; i<data.length; i++) {
						$("#classId").append("<option value="+data[i].classId+">"+data[i].className+"</option>");
					}
				},
				error: function() {
					swal("错误！", "发生错误", "error");
		        }
			});
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
		$("#userModalLabel").text("新增用户");
		$("#operationType").val("add");
		$("#username").val('');
		$("#roleIds option").attr("selected",false);
		$('#usernameForm').show(); 
		$("#passwordForm").show();
		$("#userInfo").hide();
		$("#hiredateForm").hide();
		$("#isPartyForm").hide();
		$("#speciIdForm").hide();
		$("#classIdForm").hide();
		$("#repairTypeForm").hide();
	}
	function addStudent() {
		$("#userModalLabel").text("新增学生");
		$("#operationType").val("add");
		$("#username").val('');
		$("#roleIds option").attr("selected",false);
		$("#roleIds").val(4);
		$("#roleIdsForm").hide();
		$('#usernameForm').show(); 
		$("#passwordForm").show();
		$("#userInfo").show();
		$("#hiredateForm").show();
		$("#isPartyForm").show();
		$("#speciIdForm").show();
		$("#classIdForm").show();
		$("#repairTypeForm").hide();
	}
	function updateUser(id, username, roleIds) {
		$("#userModalLabel").text("修改用户");
		$("#operationType").val("update");
		$("#userId").val(id);
		$("#username").val(username);
		$('#usernameForm').hide();
		$("#passwordForm").hide();
		$("#repairTypeForm").hide();
		$("#roleIds option").attr("selected",false);
		roleIds.forEach(function(item, index, array) {
			$("#roleIds").find("option[value='"+item+"']").attr("selected",true);
		});
		if(roleIds.indexOf(1) == -1) {
			$("#userInfo").show();
			$("#hiredateForm").hide();
			$("#isPartyForm").hide();
			$("#speciIdForm").hide();
			$("#classIdForm").hide();
		} else {
			$("#userInfo").hide();
		}
		if(roleIds.indexOf(2) != -1) {
			$("#hiredateForm").show();
			$.ajax({
				type: "GET",
				datatype: "json",
				url: getRootPath() + "/staff/"+username,
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					$("#name").val(data.staffName);
					var sex = "male";
					if(data.staffSex == 2) {
						sex = "female";
					}
					$(":radio[name='sex'][value='" + sex + "']").prop("checked", "checked");
					$("#tel").val(data.staffTel);
					console.log(data.hiredate);
					$("#enterDate").val(data.hiredateStr);
				},
				error: function() {
					swal("错误！", "发生错误", "error");
		        }
			});
		}
		if(roleIds.indexOf(3) != -1) {
			$.ajax({
				type: "GET",
				datatype: "json",
				url: getRootPath() + "/consellor/"+username,
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
					swal("错误！", "发生错误", "error");
		        }
			});
		}
		if(roleIds.indexOf(4) != -1) {
			$("#hiredateForm").show();
			$("#isPartyForm").show();
			$("#speciIdForm").show();
			$("#classIdForm").show();
			$.ajax({
				type: "GET",
				datatype: "json",
				url: getRootPath() + "/student/"+username,
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					$("#name").val(data.stdName);
					var sex = "male";
					if(data.stdSex == 2) {
						sex = "female";
					}
					$(":radio[name='sex'][value='" + sex + "']").prop("checked", "checked");
					$("#tel").val(data.stdTel);
					$("#enterDate").val(data.enterTimeStr);
					if(data.party) {
						$("#isParty").attr('checked', true);
						$("#isPartyHidden").val("true");
					} else {
						$("#isParty").removeAttr('checked');
						$("#isPartyHidden").val("false");
					}
					$("#speciId").val(data.speciId);
					var classId = data.classId;
					$.ajax({
						type: "GET",
						datatype: "json",
						url: getRootPath() + "/class/"+data.speciId,
						contentType: "application/x-www-form-urlencoded",
						success: function(data) {
							$("#classId").text('');
							for(var i=0; i<data.length; i++) {
								$("#classId").append("<option value="+data[i].classId+">"+data[i].className+"</option>");
							}
							$("#classId").find("option[value='"+classId+"']").attr("selected",true);
						},
						error: function() {
							swal("错误！", "发生错误", "error");
				        }
					});
				},
				error: function() {
					swal("错误！", "发生错误", "error");
		        }
			});
		}
		if(roleIds.indexOf(5) != -1) {
			$("#repairTypeForm").show();
			$.ajax({
				type: "GET",
				datatype: "json",
				url: getRootPath() + "/repair/repairman/"+username,
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					$("#name").val(data.repairmanName);
					var sex = "male";
					if(data.repairmanSex == 2) {
						sex = "female";
					}
					$(":radio[name='sex'][value='" + sex + "']").prop("checked", "checked");
					$("#tel").val(data.repairmanTel);
					$("#typeIds option").attr("selected",false);
					data.typeIds.forEach(function(item, index, array) {
						$("#typeIds").find("option[value='"+item+"']").attr("selected",true);
					});
				},
				error: function() {
					swal("错误！", "发生错误", "error");
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
				url: getRootPath() + "/user/"+userId+"/delete",
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
					swal("错误！", "发生错误", "error");
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