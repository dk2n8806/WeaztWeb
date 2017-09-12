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
						<h1 class="ftrend">Payment</h1>	
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
							<spring:url var="processPayment" value="/user/admin/charge-payments" htmlEscape="" />
							<div class="toRight">
							<form id="chargePaymentBtn" action="${processPayment }" method="POST">
								<button class="btn plainBtn">Go</button>
							</form>
							</div>
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
									<th>Created on</th>
									<th>Detail</th>
									<th>Status</th>
									<th>Requested</th>
								</tr>
								<c:forEach var="p" items="${payments }">
								<c:set var="id" value="${p.id }" />
								<c:set var="status" value="${p.status }" />
								<c:set var="createdOn">
									<fmt:formatDate value="${p.createdOn }"/>
								</c:set>
								<tr>
									<td>${id }</td>
									<td>${createdOn }</td>
									<td>
									<c:choose>
										<c:when test="${p.getClass().name == 'com.common.entity.payment.SubscriptionPayment'}">
											<p><strong>Subscription payment</strong><p>
											<p class="tip">Account: <strong>${p.account.id }</strong></p>
											<p class="tip">Subscription: <strong>${p.subscription.id}</strong></p>
										</c:when>
										<c:otherwise>
											<p>Unknown Payment</p>
										</c:otherwise>
									</c:choose>
									</td>
									<td class="status" data-status="${p.status }"></td>
									<td class="request" data-request="${p.requested }"></td>
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

	$("#chargePaymentBtn").on("submit", function(event){
	    var urlForm = $this.attr("action");
		$.ajax({
			url: urlForm,
			method: "POST",
			success :function(data) {
				alert("success");
			},
			error: function() {
				alert("faile");
			}
		});
		event.preventDefault();
	});
	
	$(".status").each(function(){
		var $this = $(this);
		var status = $this.data("status").toLowerCase();
		var str = "";
		if(status == "pending") {
			str = "<i class=\"fa tgray fa-check-square-o\">&nbsp;pending</i>";
		} else if(status == "completed"){
			str = "<i class=\"fa tgreen fa-check-square-o\">&nbsp;completed</i>";
		} else if(status = "voided") {
			str = "<i class=\"fa tred fa-times\">&nbsp;voided</i>";
		}
		$this.html(str);
	})
	
	$(".request").each(function(){
		var $this = $(this);
		var request = $this.data("request");
		var str = "";
		if(request) {
			str = "<i class=\"fa tgreen fa-check-square-o\">&nbsp;requested</i>";
		} else {
			str = "<i class=\"fa tgray fa-ellipsis-h\">&nbsp;requesting</i>";
		}
		
		$this.html(str);
	})
	
	function setUpRoleNav() {
		var a = $("#statusNav").data("status");
		$("#" + a + "Tab").addClass("active");
	}; setUpRoleNav();
	 
	function init() {
		$("#paymentNav").addClass("active");
	}; init();
});

</script>
</body>
</html>