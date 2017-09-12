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
						<h1 class="ftrend">Payouts</h1>	
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
								<c:forEach var="entity" items="${params}">
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
									<th>id</th>
									<th>Date</th>
									<th>Detail</th>
									<th>Amount</th>
									<th>Status</th>
									<th>Merchant</th>
								</tr>
								<c:forEach items="${payouts }" var="p">
								<c:set var="id" value="${p.id }" ></c:set>
								<c:set var="createdOn">
									<fmt:formatDate value="${p.createdOn }"/>
								</c:set>
								<c:set var="amount">
									<fmt:formatNumber value="${p.amount / 100}"></fmt:formatNumber>
								</c:set>
								<c:set var="status" value="${p.status}"></c:set>
								<c:set var="merchantId" value="${p.merchant.id }"></c:set>
								<tr>
									<td>${id }</td>
									<td>${createdOn }</td>
									<td>
									<c:choose>
										<c:when test="${p.getClass().name == 'com.common.entity.payout.SalePayout'}">
											<p><strong>Sale payout</strong><p>
											<p class="tip">Order: <strong>${p.orderBundle.id}</strong></p>
										</c:when>
										<c:otherwise>
											<p>Unknown Payment</p>
										</c:otherwise>
									</c:choose>
									</td>
									<td>$${amount }</td>
									<td class="status" data-status="${status}"></td>
									<td>${merchantId }</td>
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
		if(status == "pending") {
			str = "<i class=\"fa tgray fa-check-square-o\">&nbsp;pending</i>";
		} else if(status == "completed"){
			str = "<i class=\"fa tgreen fa-check-square-o\">&nbsp;completed</i>";
		} else if(status = "voided") {
			str = "<i class=\"fa tred fa-times\">&nbsp;voided</i>";
		}
		$this.html(str);
	});
	
	
	function setUpRoleNav() {
		var a = $("#statusNav").data("status");
		$("#" + a + "Tab").addClass("active");
	}; setUpRoleNav();
	 
	function init() {
		$("#payoutNav").addClass("active");
	}; init();
});

</script>
</body>
</html>