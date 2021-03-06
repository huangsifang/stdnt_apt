<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>公寓寝室维修</title>
</head>
<body>
<jsp:include page="../navbar.jsp"></jsp:include>

<div class="container">

	<div class="card">
		<div class="header">
			<ul class="nav nav-tabs" id="apartUl">
				<c:forEach items="${apartList}" var="apart">
					<li><a href="${pageContext.request.contextPath}/repair/apart/${apart.apartId}">${apart.apartName}</a></li>
			    </c:forEach>
			</ul>
		</div>
	
		<div class="content table-responsive">
			<table class="table">
			    <thead>
			        <tr>
			            <th>寝室号</th>
			            <th>申请者</th>
			            <th>维修类型</th>
			            <th>申请时间</th>
			            <th>备注</th>
			            <th>状态</th>
			            <th>操作</th>
			        </tr>
			    </thead>
			    <tbody>
			    	<c:if test="${empty repairList}">
			    		<tr>
			    			<td colspan="7" style="text-align:center">该公寓没有任何维修信息！</td>
			    		</tr>
			    	</c:if>
			        <c:forEach items="${repairList}" var="repair">
			            <tr>
			                <td>${repair.dormNo}</td>
			                <td>${repair.applicantName}</td>
			                <td>${repair.repairTypeName}</td>
			                <td><fmt:formatDate value="${repair.applyTime}" pattern="yyyy-MM-dd HH:mm" /></td>
			                <td style="width:100px;"><p style="width:100px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis"><a href="#" class="tooltip-test" data-toggle="tooltip" title="${repair.remark}">${repair.remark}</a></p></td>
			                <c:if test="${repair.state == 0}">
			                	<td><span class="label label-warning">未接单</span></td>
			                </c:if>
			                <c:if test="${repair.state == 1}">
			                	<td><span class="label label-info">已接单</span></td>
			                </c:if>
			                <c:if test="${repair.state == 2}">
			                	<td><span class="label label-success">已结束</span></td>
			                </c:if>
			                <c:if test="${repair.state == 3}">
			                	<td><span class="label label-default">已取消</span></td>
			                </c:if>
			                <td>
			                	<c:if test="${repair.state != 3}">
				                	<shiro:hasPermission name="repair:view">
				                		<a href="${pageContext.request.contextPath}/repair/${repair.id}/record">查看</a>
				                    </shiro:hasPermission>
			                    </c:if>
			                </td>
			            </tr>
			        </c:forEach>
			    </tbody>
			</table>
			<c:if test="${allCount != 0}">
				<ul class="pagination tablePage">
				    <li><a href="${pageContext.request.contextPath}/repair/apart/${apartId}?start=${start-10}">&laquo;</a></li>
				    <c:forEach begin="0" end="${allCount-1}" var="item" step="10">
				    	<li value="${item/10+1}"><a href="${pageContext.request.contextPath}/repair/apart/${apartId}?start=${item}"><fmt:formatNumber type="number" value="${item/10+1}" maxFractionDigits="0"/></a></li>
				    </c:forEach>
				    <li><a href="${pageContext.request.contextPath}/repair/apart/${apartId}?start=${start+10}">&raquo;</a></li>
				</ul>
			</c:if>
		</div>
	</div>
</div>
</body>
<script>
	$(function() {
		var nowApartId = Number('${apartId}')-1;
		$("#apartUl li").eq(nowApartId).addClass("active");
		
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
	});
</script>
</html>