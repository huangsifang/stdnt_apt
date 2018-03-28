<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/table.css" rel="stylesheet">
</head>
<body>

<div style="margin:20px 50px">
	<div class="pull-right">欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a></div>
	
	<c:if test="${not empty msg}">
	    <div>${msg}</div>
	</c:if>
	
	<!-- 新增公寓模态框（Modal） -->
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
	            		<div class="form-group">
							<label for="username" class="col-sm-3 control-label">用户名：</label>
							<div class="col-sm-8">
								<input class="form-control" type="number" id="username" name="username"/>
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
					</form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="userBtn">提交更改</button>
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
	                    	<button class="btn btn-default" data-toggle="modal" data-target="#userModal" onClick="updateUser(${user.id}, ${user.username}, ${user.roleIds})">修改</button>
	                    </shiro:hasPermission>
	
	                    <shiro:hasPermission name="user:delete">
	                        <a href="user/${user.id}/delete">删除</a>
	                    </shiro:hasPermission>
	
	                    <shiro:hasPermission name="user:update">
	                        <a href="user/${user.id}/changePassword">改密</a>
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
	$(function() {
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
	});
	function addUser() {
		$("#operationType").val("add");
		$("#username").val('');
		$("#roleIds option").attr("selected",false);
		$('#username').removeAttr("disabled"); 
		$("#passwordForm").show();
	}
	function updateUser(id, username, roleIds) {
		$("#operationType").val("update");
		$("#userId").val(id);
		$("#username").val(username);
		$("#username").attr("disabled","disabled");
		$("#passwordForm").hide();
		$("#roleIds option").attr("selected",false);
		roleIds.forEach(function(item, index, array) {
			$("#roleIds").find("option[value='"+item+"']").attr("selected",true);
		});
	}
</script>
</html>