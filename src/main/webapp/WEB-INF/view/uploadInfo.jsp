<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form class="uploadForm" action="uploadInfoFromType" method="post" enctype="multipart/form-data">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的学院文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input class="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input class="file_type" class="form-control" name="filetype" value="college" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" class="excel_button" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
	
	<form class="uploadForm" action="uploadInfoFromType" method="post" enctype="multipart/form-data">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的学制文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input class="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input class="file_type" class="form-control" name="filetype" value="speYears" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" class="excel_button" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
	
	<form class="uploadForm" action="uploadInfoFromType" method="post" enctype="multipart/form-data">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的专业文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input class="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input class="file_type" class="form-control" name="filetype" value="speciality" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" class="excel_button" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
	
	<form class="uploadForm" action="uploadInfoFromType" method="post" enctype="multipart/form-data">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的辅导员文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input class="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input class="file_type" class="form-control" name="filetype" value="consellor" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" class="excel_button" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
	
	<form class="uploadForm" action="uploadInfoFromType" method="post" enctype="multipart/form-data">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的班级文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input class="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input class="file_type" class="form-control" name="filetype" value="class" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" class="excel_button" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
	
	<form class="uploadForm" action="uploadInfoFromType" method="post" enctype="multipart/form-data">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的学生文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input class="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input class="file_type" class="form-control" name="filetype" value="student" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" class="excel_button" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
	
	<form class="uploadForm" action="uploadInfoFromType" method="post" enctype="multipart/form-data">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的学生床位文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input class="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input class="file_type" class="form-control" name="filetype" value="studentBed" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" class="excel_button" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
	
	<form class="uploadForm" action="uploadInfoFromType" method="post" enctype="multipart/form-data">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的工作人员文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input class="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input class="file_type" class="form-control" name="filetype" value="staff" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" class="excel_button" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
	
	<form class="uploadForm" action="uploadInfoFromType" method="post" enctype="multipart/form-data">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的维修人员文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input class="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input class="file_type" class="form-control" name="filetype" value="repairman" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" class="excel_button" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
	
	<form class="uploadForm" action="uploadInfoFromType" method="post" enctype="multipart/form-data">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的公寓文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input class="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input class="file_type" class="form-control" name="filetype" value="apartment" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/jquery.form.min.js" ></script> 
<script>
	$(function() {
		$(".uploadForm").ajaxForm({
			beforeSubmit: function(formData, jqForm, options) {
                return true; 
            },
			success: function(data){
				alert(data);
	        },
	        error: function() {
	        	alert('error');
	        },
	        resetForm: true        // 成功提交后，重置所有的表单元素的值
		});
    });
</script> 
</html>