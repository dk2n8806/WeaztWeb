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
						<h1 class="ftrend">Subscriptions</h1>	
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
								<c:forEach var="entity" items="${counts }">
									<c:set var="key" value="${entity.key }" />
									<c:set var="value" value="${entity.value}" />
									<spring:url var="customerLink" value="?_ref=${key}"  htmlEscape="true"  />
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
									<th>Id</th>
									<th>Created on</th>
									<th>Detail</th>
									<th>status</th>
									<th>Product </th>
									<th>Account</th>
								</tr>
								<c:forEach items="${subscriptions }" var="s">
							<!-- 	
	private Product product;
	private Account account;
	private int nos;
	private int frequency;
	private ValuePerShipment perShipment;
	private int totalCharge;
	private int hadShipped;
	private SubscriptionStatus status;
	private Date scheduledOn; -->
								
								<c:set var="id" value="${s.id}"/>
								<c:set var="createdOn"><fmt:formatDate value="${s.createdOn }"/></c:set>
								<c:set var="scheduledOn"><fmt:formatDate value="${s.scheduledOn }"/></c:set>
								<c:set var="shipped" value="${s.hadShipped}"/>
								<c:set var="nos" value="${s.nos}"/>
								<c:set var="freq" value="${s.frequency }"/>
								<c:set var="totalCharge"><fmt:formatNumber value="${s.totalCharge / 100 }"/></c:set>
								<c:set var="status" value="${s.status }" />
								<c:set var="productId" value="${s.product.id}"/>
								<c:set var="accountId" value="${s.account.id }"/>
								<tr>
									<td>${id }</td>
									<td>${createdOn }</td>
									<td>
										<ul>
											<li>Total Charge: <strong>$${totalCharge }</strong></li>
											<li class="tip">Nos: <strong>${nos }</strong>&nbsp;&nbsp;&nbsp;&nbsp;Shipped: <strong>${shipped }</strong> </li>
											<li class="tip">Freq: <strong>${freq } days</strong></li>
											<li class="tip">scheduled on: ${scheduledOn }</li>
										</ul>
									</td>
									<td class="status" data-status="${status }"></td>
									<td>${productId }</td>
									<td>${accountId}</td>
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
		if(status == "subscribing") {
			str = "<i class=\"fa tgray  fa-ellipsis-h\">&nbsp;&nbsp;"+ status +"</i>";
		} else if(status == "subscribed"){
			str = "<i class=\"fa tgreen fa-check-square-o\">&nbsp;&nbsp;"+ status +"</i>";
		} else if(status = "unsubscribed") {
			str = "<i class=\"fa tred fa-times\">&nbsp;&nbsp;"+ status +"</i>";
		}
		$this.html(str);
	})
	
	
	function setUpRoleNav() {
		var a = $("#statusNav").data("status");
		$("#" + a + "Tab").addClass("active");
	}; setUpRoleNav();
	 
	function init() {
		$("#subscriptionNav").addClass("active");
	}; init();
});

</script>
</body>
</html>