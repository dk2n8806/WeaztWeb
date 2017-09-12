<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title></title>
<style type="text/css">
.mainList {
	margin-bottom: 50px;
}
</style>
</head>
<body>
<jsp:include page="../../components/header-navigation.jsp"></jsp:include> 


<div class="mainList gray">
	<div class="main-content1">
		<div class="col-wrapper">
			<div class="col toCenter">
				<div class="blk">
					<div class="blk-header">
						<h1 class="ftrend">Order Intents</h1>	
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="main-content1">
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="../nav/adminNavigations.jsp"></jsp:include>
		</div>
		<div class="olymArena">
			<div class="col-wrapper">
				<div class="col">
					<div class="blk">
						<div class="blk-header">
							<div class="blkTabNav">
							<ul id="statusNav" class="inline" data-status="${status}">
								<c:forEach var="entity" items="${params }">
									<c:set var="key" value="${entity.key }" />
									<c:set var="value" value="${entity.value}" />
									<spring:url var="customerLink" value="?_s=${key}"  htmlEscape="true"  />
									<li><a id="${key}Tab" class="btn" href="${customerLink }">${value}&nbsp;${key}</a></li>
								</c:forEach>
							</ul>
							</div>	
						</div>
					</div>
					<div class="blk-b">
						<div class="blk-content">
							<table class="display">
								<tr class="purple twhite">
									<th>id</th>
									<th>Created On</th>
									<th>Status</th>
									<th>Subscription</th>
									<th>Scheduled Date</th>
								</tr>
								<c:forEach var="o" items="${orderIntents }">
								<c:set var="id" value="${o.id }" />
								<c:set var="status" value="${o.status }" />
								<c:set var="subscriptionId" value="${o.subscription.id}" />
								<c:set var="scheduledDate">
									<fmt:formatDate value="${o.scheduledDate }"/>
								</c:set>
								<c:set var="createdOn">
									<fmt:formatDate value="${o.createdOn}"/>
								</c:set>
								<tr>
									<td>${id }</td>
									<td>${createdOn }</td>
									<td class="status" data-status="${status }"></td>
									<td>Subscription <strong>${subscriptionId}</strong></td>
									<td>${scheduledDate }</td>
								</tr>
								
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {

	$(".status").each(function(){
		var $this = $(this);
		var status = $this.data("status").toLowerCase();
		var str = "";
		if(status == "requesting") {
			str = "<i class=\"fa tgray fa-ellipsis-h\">&nbsp;&nbsp;"+status+"</i>";
		} else if(status == "completed"){
			str = "<i class=\"fa tgreen fa-check-square-o\">&nbsp;&nbsp;"+status+"</i>";
		} else if(status = "canceled") {
			str = "<i class=\"fa tred fa-times\">&nbsp;&nbsp;"+status+"</i>";
		}
		$this.html(str);
	});
	
	
	
	function setUpRoleNav() {
		var a = $("#statusNav").data("status").toLowerCase();
		$("#" + a + "Tab").addClass("active");
	}; setUpRoleNav();
	 
	function init() {
		$("#orderIntentNav").addClass("active");
	}; init();
});

</script>
</body>
</html>