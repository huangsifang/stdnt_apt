<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form id="QueryForm" action="uploadInfoFromType.do" method="post" enctype="multipart/form-data" onsubmit="return check();">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的学院文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input id="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input id="file_type" class="form-control" name="filetype" value="college" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" id="excel_button" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
	
	<form id="QueryForm" action="uploadInfoFromType.do" method="post" enctype="multipart/form-data" onsubmit="return check();">
		<div class="row">
		　　	<div class="col-sm-6" style="width: 50%;">
		   		<div class="box box-primary">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">请选择上传的班级文件:</h3>
		                <div class="box-body">
		　　　　　　　　　　　　  	<input id="excel_file" class="form-control" type="file" name="filename" accept="xlsx" size="80" />
							<input id="file_type" class="form-control" name="filetype" value="class" hidden/>
		                </div>
		                <span class="box-title"><c:if test="${msg !=''}">${msg}</c:if></span>
		 				<input class="btn btn-primary pull-right" id="excel_button" type="submit" value="导入" />
					</div>
				</div>
			</div>
       </div>
	</form>
</body>
</html>