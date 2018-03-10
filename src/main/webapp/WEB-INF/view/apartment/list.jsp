<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
</head>
<body>

欢迎[<shiro:principal/>]登录成功！<a href="logout">退出</a>

<c:if test="${not empty msg}">
    <div>${msg}</div>
</c:if>

<shiro:hasPermission name="apartment:create">
    <a href="apartment/create">公寓新增</a><br/>
</shiro:hasPermission>

<form class="uploadForm" action="${pageContext.request.contextPath}/uploadInfo/uploadInfoFromType.do" method="post" enctype="multipart/form-data">
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
	 				<input class="btn btn-primary pull-right" class="excel_button" type="submit" value="导入" />
				</div>
			</div>
		</div>
	</div>
</form>

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
                    	<button type="button" onClick="updateApart(${apart.apartId},'${apart.apartName}','${apart.staffsStr}')">修改</button>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="apartment:delete">
                        <a href="apartment/${apart.apartId}/delete">删除</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<form id="apartForm" method="post">
	寝室号：<input id="apartId" type="number" name="apartId" disabled/><br />
	寝室名：<input id="apartName" type="text" name="apartName"/><br />
	<span id="staff">管理员：<button id="addStaff" type="button">增加</button></span><div id="staffInfo"></div>
	<button type="button" id="apartChangeBtn">提交</button>
</form>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/jquery.form.min.js" ></script> 
<script>
	var staffNum = 0;
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
		$("#addStaff").click(function() {
			$("#staffInfo").append("<input type='number' name='staffId' id='staffId"+staffNum+"' onChange='changeStaff("+staffNum+")'><span id='staffName"+staffNum+"'></span><br />");
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
					alert(data);
				},
				error: function() {
		        	alert('error');
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
					alert("未找到该职工");
				}
				$("#staffName"+i).text(data);
			},
			error: function() {
	        	alert('error');
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
			$("#staffInfo").append("<input type='number' name='staffId' id='staffId"+i+"' onChange='changeStaff("+i+")' value="+staffId+"><span id='staffName"+i+"'>"+staffName+"</span><button type='button' onClick='deleteStaff("+apartId+","+staffId+")'>删除</button><br />");
		}
	}
	function deleteStaff(apartId, staffId) {
		$.ajax({
			type: "POST",
			datatype: "json",
			url: "apartment/staff/delete",
			data: {apartId: apartId, staffId: staffId},
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				alert(data);
			},
			error: function() {
	        	alert('error');
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