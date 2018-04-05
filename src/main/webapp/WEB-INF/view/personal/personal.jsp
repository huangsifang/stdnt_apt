<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>个人中心</title>
    <style>
    	fieldset {
		  padding-bottom: 20px !important;
		  border-bottom: 1px dashed #eee !important;
		  margin-bottom: 20px !important;
		}
		fieldset.last-child,
		fieldset:last-child {
		  border-bottom: 0 !important;
		}
		fieldset .form-group {
		  margin-bottom: 0 !important;
		}
		.change_link{
			width: 100%;
            font-size: 16px ;
            text-align: right;
            border-top: 1px solid rgb(219, 229, 232);
            -webkit-border-radius: 0 0  5px 5px;
               -moz-border-radius: 0 0  5px 5px;
                    border-radius: 0 0  5px 5px;
            background: rgb(225, 234, 235);
            background: -moz-repeating-linear-gradient(-45deg, 
            rgb(247, 247, 247) , 
            rgb(247, 247, 247) 15px, 
            rgb(225, 234, 235) 15px, 
            rgb(225, 234, 235) 30px, 
            rgb(247, 247, 247) 30px
            );
            background: -webkit-repeating-linear-gradient(-45deg, 
            rgb(247, 247, 247) , 
            rgb(247, 247, 247) 15px, 
            rgb(225, 234, 235) 15px, 
            rgb(225, 234, 235) 30px, 
            rgb(247, 247, 247) 30px
            );
            background: -o-repeating-linear-gradient(-45deg, 
            rgb(247, 247, 247) , 
            rgb(247, 247, 247) 15px, 
            rgb(225, 234, 235) 15px, 
            rgb(225, 234, 235) 30px, 
            rgb(247, 247, 247) 30px
            );
            background: repeating-linear-gradient(-45deg, 
            rgb(247, 247, 247) , 
            rgb(247, 247, 247) 15px, 
            rgb(225, 234, 235) 15px, 
            rgb(206, 247, 232) 30px, 
            rgb(247, 247, 247) 30px
            );
        }
    </style>
</head>
<body>
<jsp:include page="../navbar.jsp"></jsp:include>

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

<div class="container">

	<div class="row" style="margin-top:20px">
		<div class="col-md-6">
			<div class="card">
				<fieldset class="change_link">
				    <div class="header">
				    	<h4 class="pull-left">基本信息</h4>
				    	<button type="button" class="btn btn-default pull-right" data-toggle="modal" data-target="#passwordModal" onClick="changePasswordUser(${user.id})">修改密码</button>
				    </div>
			    </fieldset>
			    <div class="content">
			    	<c:if test="${userId == 1}">
			    		<fieldset>
							<div class="form-group">
								<label class="col-sm-4 control-label">超级管理员</label>
							</div>
						</fieldset>
			    	</c:if>
			    	<c:if test="${userId != 1}">
				    	<fieldset>
							<div class="form-group">
								<label for="userId" class="col-sm-4 control-label">学号/工号：</label>
								<div class="col-sm-8">
									<span>${userId}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="userName" class="col-sm-4 control-label">姓名：</label>
								<div class="col-sm-8">
									<span>${userName}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="userSex" class="col-sm-4 control-label">性别：</label>
								<div class="col-sm-8">
									<c:if test="${userSex == 0}">
										<span>未知</span>
									</c:if>
									<c:if test="${userSex == 1}">
										<span>男</span>
									</c:if>
									<c:if test="${userSex == 2}">
										<span>女</span>
									</c:if>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="userTel" class="col-sm-4 control-label">手机号：</label>
								<div class="col-sm-8">
									<span>${userTel}</span>
							   	</div>
							</div>
						</fieldset>
					</c:if>
			    </div>
	  		</div>
	    </div>
	    
	    <c:if test="${student != null}">
		    <div class="col-md-6">
				<div class="card">
					<fieldset class="change_link">
					    <div class="header">
					    	<h4 class="pull-left">学生信息</h4>
					    </div>
				    </fieldset>
				    <div class="content">
						<fieldset>
							<div class="form-group">
								<label for="enterTime" class="col-sm-4 control-label">入学时间：</label>
								<div class="col-sm-8">
									<span>${student.enterTimeStr}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="isParty" class="col-sm-4 control-label">是否党员：</label>
								<div class="col-sm-8">
									<c:if test="${student.isParty()}">
										<span>是</span>
									</c:if>
									<c:if test="${!student.isParty()}">
										<span>否</span>
									</c:if>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="className" class="col-sm-4 control-label">班级：</label>
								<div class="col-sm-8">
									<c:if test="${student.className != null}">
										<span>${student.className}</span>
									</c:if>
									<c:if test="${student.className == null}">
										<span>未知</span>
									</c:if>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="apartName" class="col-sm-4 control-label">所属公寓：</label>
								<div class="col-sm-8">
									<c:if test="${student.apartName != null}">
										<span>${student.apartName}</span>
									</c:if>
									<c:if test="${student.apartName == null}">
										<span>未知</span>
									</c:if>
							   	</div>
							</div>
						</fieldset>
				    </div>
		  		</div>
		    </div>
	    </c:if>
	    
	    <c:if test="${staff != null}">
		    <div class="col-md-6">
				<div class="card">
					<fieldset class="change_link">
					    <div class="header">
					    	<h4 class="pull-left">管理员信息</h4>
					    </div>
				    </fieldset>
				    <div class="content">
						<fieldset>
							<div class="form-group">
								<label for="aparts" class="col-sm-4 control-label">所属公寓：</label>
								<div class="col-sm-8">
									<c:forEach items="${staff.aparts}" var="apart">
										<span>${apart.apartName}</span><br />
									</c:forEach>
							   	</div>
							</div>
						</fieldset>
				    </div>
		  		</div>
		    </div>
	    </c:if>
	    
	    <c:if test="${repairman != null}">
		    <div class="col-md-6">
				<div class="card">
					<fieldset class="change_link">
					    <div class="header">
					    	<h4 class="pull-left">维修信息</h4>
					    </div>
				    </fieldset>
				    <div class="content">
						<fieldset>
							<div class="form-group">
								<label for="repairTypes" class="col-sm-4 control-label">维修类型：</label>
								<div class="col-sm-8">
									<c:forEach items="${repairman.types}" var="type">
										<span>${type.typeName}</span><br />
									</c:forEach>
							   	</div>
							</div>
						</fieldset>
				    </div>
		  		</div>
		    </div>
	    </c:if>
	    
	</div>
</div>
</body>
<script>
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
function changePasswordUser(id) {
	$("#userIdPassword").val(id);
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