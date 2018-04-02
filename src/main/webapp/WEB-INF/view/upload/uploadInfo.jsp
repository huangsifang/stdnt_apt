<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/public/css/uploadForm.css" rel="stylesheet">
	<style type="text/css">
	.fileForm {
		margin:10px;
	}
	.leftRightBottom {
		height:30px;
		border:1px solid #cdcdcd;
		border-top: 0
	}
	.right {
		height:25px;
		border:1px solid #cdcdcd;
		border-top: 0;
		border-left: 0;
		border-bottom: 0
	}
	.leftTop {
		height:25px;
		border:1px solid #cdcdcd;
		border-right: 0;
		border-bottom: 0
	}
	</style>
</head>
<body>
<jsp:include page="../navbar.jsp"></jsp:include>

<div style="margin:20px 50px">
	<div class="row">
		<div class="col-sm-6">
			<form action="upload/uploadInfoFromType" method="post" name="formCollege" id="formCollege" onsubmit="return validate(formCollege)" enctype="multipart/form-data" class="fileForm uploadForm pull-left">
				导入学院信息： 
				<a href="javascript:;" class="file">选择文件
				    <input type="file" name="filename" id="importCollegeFile" accept="xlsx" onchange="importFileFun(importCollegeFile, collegeFileName)"/>
				</a>
				<input class="fileName" id="collegeFileName" value="未选择文件" disabled/>
				<input type="hidden" name="filetype" value="college"/>
				<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
				<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
			</form>
		</div>
		<div class="col-sm-6">
			<form action="upload/uploadInfoFromType" method="post" name="formSpeYears" id="formSpeYears" onsubmit="return validate(formSpeYears)" enctype="multipart/form-data" class="fileForm uploadForm pull-left">
				导入学制信息： 
				<a href="javascript:;" class="file">选择文件
				    <input type="file" name="filename" id="importSpeYearsFile" accept="xlsx" onchange="importFileFun(importSpeYearsFile, speYearsFileName)"/>
				</a>
				<input class="fileName" id="speYearsFileName" value="未选择文件" disabled/>
				<input type="hidden" name="filetype" value="speYears"/>
				<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
				<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-offset-3 col-sm-6 leftRightBottom"></div>
	</div>
	<div class="row">
		<div class="col-sm-offset-3 col-sm-3 right"></div>
	</div>
	<div class="row">
		<div class="col-sm-offset-3 col-sm-3 leftTop"></div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<form action="upload/uploadInfoFromType" method="post" name="formSpeciality" id="formSpeciality" onsubmit="return validate(formSpeciality)" enctype="multipart/form-data" class="fileForm uploadForm pull-left">
				导入专业信息： 
				<a href="javascript:;" class="file">选择文件
				    <input type="file" name="filename" id="importSpecialityFile" accept="xlsx" onchange="importFileFun(importSpecialityFile, specialityFileName)"/>
				</a>
				<input class="fileName" id="specialityFileName" value="未选择文件" disabled/>
				<input type="hidden" name="filetype" value="speciality"/>
				<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
				<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
			</form>
		</div>
		<div class="col-sm-6">
			<form action="upload/uploadInfoFromType" method="post" name="formConsellor" id="formConsellor" onsubmit="return validate(formConsellor)" enctype="multipart/form-data" class="fileForm uploadForm pull-left">
				导入辅导员信息： 
				<a href="javascript:;" class="file">选择文件
				    <input type="file" name="filename" id="importConsellorFile" accept="xlsx" onchange="importFileFun(importConsellorFile, consellorFileName)"/>
				</a>
				<input class="fileName" id="consellorFileName" value="未选择文件" disabled/>
				<input type="hidden" name="filetype" value="consellor"/>
				<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
				<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-offset-3 col-sm-6 leftRightBottom"></div>
	</div>
	<div class="row">
		<div class="col-sm-offset-3 col-sm-3 right"></div>
	</div>
	<div class="row">
		<div class="col-sm-offset-3 col-sm-6">
			<form action="upload/uploadInfoFromType" method="post" name="formClass" id="formClass" onsubmit="return validate(formClass)" enctype="multipart/form-data" class="fileForm uploadForm pull-left">
				导入班级信息： 
				<a href="javascript:;" class="file">选择文件
				    <input type="file" name="filename" id="importClassFile" accept="xlsx" onchange="importFileFun(importClassFile, classFileName)"/>
				</a>
				<input class="fileName" id="classFileName" value="未选择文件" disabled/>
				<input type="hidden" name="filetype" value="class"/>
				<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
				<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-offset-3 col-sm-3 right"></div>
	</div>
	<div class="row">
		<div class="col-sm-offset-3 col-sm-3 leftTop"></div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<form action="upload/uploadInfoFromType" method="post" name="formStudent" id="formStudent" onsubmit="return validate(formStudent)" enctype="multipart/form-data" class="fileForm uploadForm pull-left">
				导入学生信息： 
				<a href="javascript:;" class="file">选择文件
				    <input type="file" name="filename" id="importStudentFile" accept="xlsx" onchange="importFileFun(importStudentFile, studentFileName)"/>
				</a>
				<input class="fileName" id="studentFileName" value="未选择文件" disabled/>
				<input type="hidden" name="filetype" value="student"/>
				<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
				<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
			</form>
		</div>
		<div class="col-sm-6">
			<form action="upload/uploadInfoFromType.do" method="post" name="formApart" id="formApart" onsubmit="return validate(formApart)" enctype="multipart/form-data"  class="fileForm uploadForm pull-left">
			     导入公寓信息： 
				<a href="javascript:;" class="file">选择文件
				    <input type="file" name="filename" id="importApartFile" accept="xlsx" onchange="importFileFun(importApartFile, apartFileName)"/>
				</a>
				<input class="fileName" id="apartFileName" value="未选择文件" disabled/>
				<input type="hidden" name="filetype" value="apartment"/>
				<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
				<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-offset-3 col-sm-6 leftRightBottom"></div>
	</div>
	<div class="row">
		<div class="col-sm-offset-3 col-sm-3 right"></div>
	</div>
	<div class="row">
		<div class="col-sm-offset-3 col-sm-6">
			<form action="upload/uploadInfoFromType" method="post" name="formStudentBed" id="formStudentBed" onsubmit="return validate(formStudentBed)" enctype="multipart/form-data" class="fileForm uploadForm pull-left">
				导入学生床位信息： 
				<a href="javascript:;" class="file">选择文件
				    <input type="file" name="filename" id="importStudentBedFile" accept="xlsx" onchange="importFileFun(importStudentBedFile, studentBedFileName)"/>
				</a>
				<input class="fileName" id="studentBedFileName" value="未选择文件" disabled/>
				<input type="hidden" name="filetype" value="studentBed"/>
				<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
				<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<form action="upload/uploadInfoFromType" method="post" name="formStaff" id="formStaff" onsubmit="return validate(formStaff)" enctype="multipart/form-data" class="fileForm uploadForm pull-left">
				导入工作人员信息： 
				<a href="javascript:;" class="file">选择文件
				    <input type="file" name="filename" id="importStaffFile" accept="xlsx" onchange="importFileFun(importStaffFile, staffFileName)"/>
				</a>
				<input class="fileName" id="staffFileName" value="未选择文件" disabled/>
				<input type="hidden" name="filetype" value="staff"/>
				<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
				<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
			</form>
		</div>
		<div class="col-sm-6">
			<form action="upload/uploadInfoFromType" method="post" name="formRepairman" id="formRepairman" onsubmit="return validate(formRepairman)" enctype="multipart/form-data" class="fileForm uploadForm pull-left">
				导入维修人员信息： 
				<a href="javascript:;" class="file">选择文件
				    <input type="file" name="filename" id="importRepairmanFile" accept="xlsx" onchange="importFileFun(importRepairmanFile, repairmanFileName)"/>
				</a>
				<input class="fileName" id="repairmanFileName" value="未选择文件" disabled/>
				<input type="hidden" name="filetype" value="repairman"/>
				<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
				<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
			</form>
		</div>
	</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/jquery.form.min.js" ></script> 
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
<script>
	$(function() {
		$(".uploadForm").ajaxForm({
			beforeSubmit: function(formData, jqForm, options) {
                return true; 
            },
			success: function(data){
				if(data == 'error') {
					swal("失败！", "新增失败", "error");
				} else if(data == 'errorEmpty'){
					swal("失败！", "请检查文件中内容是否有空", "error");
				} else if(data == 'errorNoFloor'){
					swal("失败！", "找不到对应楼层", "warning");
				} else if(data == 'errorNoDorm'){
					swal("失败！", "找不到对应寝室", "warning");
				} else {
					swal("成功！", data, "success");
				}
	        },
	        error: function() {
	        	swal("错误！", "发生错误", "error");
	        },
	        resetForm: true        // 成功提交后，重置所有的表单元素的值
		});
    });
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