<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>我的假期记录</title>
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

<div class="container">

	<div class="row" style="margin-top:20px">
		<div class="col-md-offset-3 col-md-6">
			<div class="card">
				<fieldset class="change_link">
				    <div class="header">
				    	<h4 class="pull-left">我的假期信息</h4>
				    	<c:if test="${stdRecord.inHome}">
							<c:if test="${!hasSign}">
								<button type="button" class="btn btn-default pull-right" onClick="sign(${stdRecord.holiId})">签到</button>
							</c:if>
							<c:if test="${hasSign}">
								<button type="button" class="btn btn-default pull-right">已签到</button>
							</c:if>
						</c:if>
				    </div>
			    </fieldset>
			    <div class="content">
					<form id="recordForm" action="" method="post">
						<fieldset>
							<div class="form-group">
								<label for="holiId" class="col-sm-4 control-label">假期名：</label>
								<div class="col-sm-8">
									<span>${stdRecord.holiName}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="holiId" class="col-sm-4 control-label">公寓名：</label>
								<div class="col-sm-8">
									<span>${stdRecord.apartName}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="holiId" class="col-sm-4 control-label">留校|返家：</label>
								<div class="col-sm-8">
									<span>${stdRecord.homeOrSchool}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="startTime" class="col-sm-4 control-label">开始时间：</label>
								<div class="col-sm-8">
									<span>${stdRecord.startTime}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="endTime" class="col-sm-4 control-label">结束时间：</label>
								<div class="col-sm-8">
									<span>${stdRecord.endTime}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<c:if test="${!stdRecord.inHome}">
								<div class="form-group">
									<label for="holiId" class="col-sm-4 control-label">是否外出：</label>
									<div class="col-sm-8">
										<span>${stdRecord.isOutStr}</span>
								   	</div>
								</div>
							</c:if>
						</fieldset>
						<c:if test="${stdRecord.inHome}">
							<fieldset>
								<div class="form-group" id="addressGroup">
									<label for="address" class="col-sm-4 control-label">地址：</label>
									<div class="col-sm-8">
										<span>${stdRecord.address}</span>
								   	</div>
								</div>
							</fieldset>
						</c:if>
					</form>
			    </div>
	  		</div>
	    </div>
	</div>
</div>
</body>
<script>
function sign(holiId) {
	$.ajax({
		type: "POST",
		datatype: "json",
		url: getRootPath() + "/holiday/" + holiId + "/sign",
		contentType: "application/x-www-form-urlencoded",
		success: function(data) {
			if(data == "success") {
				swal("成功！", "签到成功", "success");
			} else if(data == 'errorHasSign') {
				swal("失败！", "您已签到过，无需重复签到！", "warning");
			} else if(data == 'error') {
				swal("失败！", "签到失败", "error");
			}
		},
		error: function() {
			swal("失败！", "发生错误", "error");
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