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
    <style type="text/css">
    #lookBtn a{color:#fff!important}
    #lookBtn a:hover{text-decoration:none!important;color:#fff}
    </style>
</head>
<body>
<jsp:include page="../navbar.jsp"></jsp:include>

<!-- 假期新增修改模态框（Modal） -->
<div class="modal fade" id="holidayModal" tabindex="-1" role="dialog" aria-labelledby="holidayModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="holidayModalLabel">新增假期</h4>
            </div>
            <div class="modal-body">
            	<shiro:hasPermission name="holiday:create">
				    <form id="holidayForm" action="" method="post" class="form-horizontal" role="form">
				    	<div class="form-group">
							<label for="holiId" class="col-sm-3 control-label">假期号：</label>
							<div class="col-sm-8">
								<input type="number" class="form-control" id="holiId" name="holiId" placeholder="请输入假期号">
					    	</div>
						</div>
						<div class="form-group">
							<label for="holiName" class="col-sm-3 control-label">假期名：</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="holiName" name="holiName" placeholder="请输入假期名">
					    	</div>
						</div>
						<div class="form-group">
							<label for="startTime" class="col-sm-3 control-label">开始时间：</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="startTime" name="startTime">
					    	</div>
						</div>
						<div class="form-group">
							<label for="endTime" class="col-sm-3 control-label">结束时间：</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="endTime" name="endTime">
					    	</div>
						</div>
				    </form>
				</shiro:hasPermission>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="holidayBtn">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 假期登记模态框（Modal） -->
<div class="modal fade" id="recordModal" tabindex="-1" role="dialog" aria-labelledby="recordModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="recordModalLabel">登记</h4>
            </div>
            <div class="modal-body">
            	<shiro:hasPermission name="record:create">
				    <form id="recordForm" action="" method="post" class="form-horizontal" role="form">
				    	<div class="form-group">
							<div class="col-sm-offset-3 col-sm-8">
								<label class="radio-inline">
							        <input type="radio" name="recordOptionsRadios" id="homeRadio" value="home" checked> 返家
							    </label>
							    <label class="radio-inline">
							        <input type="radio" name="recordOptionsRadios" id="schoolRadio"  value="school"> 留校
							    </label>
						    </div>
						</div>
				    	<div class="form-group">
							<label for="holiId" class="col-sm-3 control-label">假期号：</label>
							<div class="col-sm-8">
								<input type="number" class="form-control" id="recordHoliId" name="recordHoliId" disabled>
					    	</div>
						</div>
						<div class="form-group">
							<label for="holiId" class="col-sm-3 control-label">公寓号：</label>
							<div class="col-sm-8">
								<input type="number" class="form-control" id="apartId" name="apartId" value="${apartId}">
					    	</div>
						</div>
						<div class="form-group">
							<label for="startTime" class="col-sm-3 control-label">开始时间：</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="recordStartTime" name="recordStartTime" readonly>
					    	</div>
						</div>
						<div class="form-group">
							<label for="endTime" class="col-sm-3 control-label">结束时间：</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="recordEndTime" name="recordEndTime" readonly>
					    	</div>
						</div>
						<div class="form-group" id="isOutGroup" hidden>
					    	<div class="col-sm-offset-3 col-sm-8">
					      		<div class="checkbox">
					        		<label>
					          			<input type="checkbox" name="isOut">是否外出
					        		</label>
					      		</div>
					    	</div>
					  	</div>
						<div class="form-group" id="addressGroup">
							<label for="address" class="col-sm-3 control-label">地址：</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="address" name="address" placeholder="请输入外出地址">
					    	</div>
						</div>
				    </form>
				</shiro:hasPermission>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="recordBtn">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div style="margin:20px 50px">
	<shiro:hasPermission name="holiday:create">
		<button class="btn btn-default btn-md" data-toggle="modal" data-target="#holidayModal" onClick="createHoliday()">新增</button>
	</shiro:hasPermission>
	
	<table class="table">
	    <thead>
	        <tr>
	            <th>假期号</th>
	            <th>假期名</th>
	            <th>开始时间</th>
	            <th>结束时间</th>
	            <th>操作</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${empty holidayList}">
				<tr>
					<td colspan="5" style="text-align:center">还未添加任何假期！</td>
				</tr>
			</c:if>
	        <c:forEach items="${holidayList}" var="holiday">
	            <tr>
	                <td>${holiday.holiId}</td>
	                <td>${holiday.holiName}</td>
	                <td>${holiday.startTime}</td>
	                <td>${holiday.endTime}</td>
	                <td>
	                    <shiro:hasPermission name="holiday:update">
	                    	<button class="btn btn-default btn-md" data-toggle="modal" data-target="#holidayModal" type="button" onClick="updateHoliday(${holiday.holiId},'${holiday.holiName}','${holiday.startTime}','${holiday.endTime}')">修改</button>
	                    </shiro:hasPermission>
	
	                    <shiro:hasPermission name="holiday:delete">
	                    	<button class="btn btn-danger btn-md" type="button" onClick="deleteHoliday(${holiday.holiId})">删除</button>
	                    </shiro:hasPermission>
	                    
	                    <shiro:hasPermission name="record:create">
	                    	<c:if test="${!holiday.hasSign}">
	                    		<button class="btn btn-primary btn-md" data-toggle="modal" data-target="#recordModal" type="button" onClick="holidayRecord(${holiday.holiId},'${holiday.startTime}','${holiday.endTime}')">登记</button>
	                    	</c:if>
	                    	<c:if test="${holiday.hasSign}">
	                    		<button class="btn btn-primary btn-md" id="lookBtn"><a href="holiday/${holiday.holiId}/std/record">查看</a></button>
	                    	</c:if>
	                    </shiro:hasPermission>
	                    
	                    <shiro:hasPermission name="record:view">
	                    	<button class="btn btn-primary btn-md" id="lookBtn"><a href="holiday/${holiday.holiId}/apart/record">查看</a></button>
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
var type = 1;
function createHoliday() {
	$("#holidayModalLabel").text("新增假期");
	$("#holiId").removeAttr("disabled");
	type = 1;
}
function updateHoliday(holiId, holiName, startTime, endTime) {
	$("#holiId").val(holiId);
	$("#holiName").val(holiName);
	$("#startTime").val(startTime);
	$("#endTime").val(endTime);
	$("#holidayModalLabel").text("修改假期");
	$("#holiId").attr("disabled","disabled");
	type = 2;
}
function deleteHoliday(holiId) {
	swal({ 
		title: "确定删除吗？", 
		text: "你将无法恢复该数据！", 
		type: "warning",
		showCancelButton: true, 
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "确定删除！", 
		cancelButtonText: "取消",
		closeOnConfirm: false
	},
	function(isConfirm){
		if(isConfirm) {
			$.ajax({
				type: "POST",
				datatype: "json",
				url: "holiday/" + holiId + "/delete",
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					if(data == 'success') {
						swal("成功！", "删除成功", "success");
					} else if(data == 'error') {
						swal("失败！", "删除失败", "error");
					}
				},
				error: function() {
					swal("失败！", "发生错误", "error");
		        }
			});
		} else { 
			swal("取消！", "删除被取消:)","info"); 
		}
	});
}
function holidayRecord(holiId, startTime, endTime) {
	$("#recordHoliId").val(holiId);
	$("#recordStartTime").val(startTime);
	$("#recordEndTime").val(endTime);
}
$(function() {
	$("#homeRadio").click(function() {
		$("#addressGroup").show();
		$("#isOutGroup").hide();
	});
	$("#schoolRadio").click(function() {
		$("#addressGroup").hide();
		$("#isOutGroup").show();
	});
	$("#holidayBtn").click(function() {
		var holiId = $("#holiId").val();
		var url = "";
		if(type==1) {
			url = "holiday/create";
		} else if(type==2) {
			url = "holiday/" + holiId + "/update";
		}
		$.ajax({
			type: "POST",
			datatype: "json",
			url: url,
			data: $("#holidayForm").serializeArray(),
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == 'success') {
					swal("成功！", "修改成功", "success");
					$('#holidayModal').modal('hide');
				} else if(data == 'error') {
					swal("失败！", "修改失败", "error");
				}
			},
			error: function() {
				swal("失败！", "发生错误", "error");
	        }
		});
	});
	$("#recordBtn").click(function() {
		var holiId = $("#recordHoliId").val();
		$.ajax({
			type: "POST",
			datatype: "json",
			url: "holiday/" + holiId + "/record/create",
			data: $("#recordForm").serializeArray(),
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == 'success') {
					swal("成功！", "新增成功", "success");
					$('#recordModal').modal('hide');
				} else if(data == 'errorExist') {
					swal("失败！", "您已登记过!", "warning");
					$('#recordModal').modal('hide');
				} else if(data == 'error') {
					swal("失败！", "新增失败", "error");
				}
			},
			error: function() {
				swal("失败！", "发生错误", "error");
	        }
		});
	});
});

//日期插件初始化      
$('#startTime').datetimepicker({
	language:  'zh-CN', 
	format:'yyyy-mm-dd', 
	weekStart: 1, /*以星期一为一星期开始*/
	todayBtn:  1,
	autoclose: 1, 
	minView:2, /*精确到天*/
	pickerPosition: "bottom-left" 
}).on("changeDate",function(ev){  //值改变事件
	//选择的日期不能大于第二个日期控件的日期
	if(ev.date){
		$("#endTime").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
	}else{
		$("#endTime").datetimepicker('setStartDate',null);
	}
});
 $('#endTime').datetimepicker({
	language:  'zh-CN', 
	format:'yyyy-mm-dd', 
	weekStart: 1, /*以星期一为一星期开始*/
	todayBtn:  1,
	autoclose: 1, 
	minView:2, /*精确到天*/
	pickerPosition: "bottom-left"
}).on("changeDate",function(ev){
	//选择的日期不能小于第一个日期控件的日期
	if(ev.date){
		$("#startTime").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
	}else{
		$("#startTime").datetimepicker('setEndDate',new Date());
	}
});
 $('#recordStartTime').datetimepicker({
	language:  'zh-CN', 
	format:'yyyy-mm-dd', 
	weekStart: 1, /*以星期一为一星期开始*/
	todayBtn:  1,
	autoclose: 1, 
	minView:2, /*精确到天*/
	pickerPosition: "bottom-left" 
}).on("changeDate",function(ev){  //值改变事件
	//选择的日期不能大于第二个日期控件的日期
	if(ev.date){
		$("#recordEndTime").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
	}else{
		$("#recordEndTime").datetimepicker('setStartDate',null);
	}
});
 $('#recordEndTime').datetimepicker({
	language:  'zh-CN', 
	format:'yyyy-mm-dd', 
	weekStart: 1, /*以星期一为一星期开始*/
	todayBtn:  1,
	autoclose: 1, 
	minView:2, /*精确到天*/
	pickerPosition: "bottom-left"
}).on("changeDate",function(ev){
	//选择的日期不能小于第一个日期控件的日期
	if(ev.date){
		$("#recordStartTime").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
	}else{
		$("#recordStartTime").datetimepicker('setEndDate',new Date());
	}
});
</script>
</html>