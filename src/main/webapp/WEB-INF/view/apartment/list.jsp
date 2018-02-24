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
                    <shiro:hasPermission name="apartment:update">
                        <a href="apartment/${apart.apartId}/update">修改</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="apartment:delete">
                        <a href="apartment/${apart.apartId}/delete">删除</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

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