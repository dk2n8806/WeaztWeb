<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Admin | Mingofy</title>
</head>
<body>

<jsp:include page="../../components/header-navigation.jsp"></jsp:include> 
<jsp:include page="../nav/adminNavigations.jsp"></jsp:include>
	
<div class="main-content">
	<div class="col-wrapper">
		<div class="col">
			<div class="blk">
				<div class="blk-content">	
					<h1 class="ftrend">Orders</h1>	
						
					<div class="blkTabNav">
					<ul id="statusNav" class="inline" data-status="${status}">
						<c:forEach var="entity" items="${counts }">
							<c:set var="key" value="${entity.key }" />
							<c:set var="value" value="${entity.value}" />
							<spring:url var="customerLink" value="?_ref=${key}"  htmlEscape="tru"  />
							<li><a id="${key}Tab" class="btn" href="${customerLink }">${value}&nbsp;${key}</a></li>
						</c:forEach>
					</ul>
					</div>				
				</div>
			</div>
		</div>
	</div>
</div> 
<br/><br/>
<div class="main-content">
	<div class="col-wrapper">
		<c:forEach var="order" items="${orders }">
			<c:set var="oId" value="${order.id }" />
			<c:set var="iId" value="${order.product.id }" />	
			<c:set var="pId" value="${order.payout.id }" />	
			<c:set var="createdOn">
				<fmt:formatDate  value="${order.createdOn }" />
			</c:set>
			<c:set var="amount" >
				<fmt:formatNumber  value="${order.collectedAmount /100}"/>
			</c:set>
			<c:set var="fee">
				<fmt:formatNumber value="${order.serviceFee /100 }" />
			</c:set>
			<c:set var="status" value="${order.status }" />
			<c:set var="number" value="${order.numberOfOrder }" />
			<div class="col-4">
				<div class="blk-b">
						<div class="panel-header status" data-status="${status}"><h3>${status }</h3></div>
					<div class="blk-content">
						<ul class="overviewListing">
							<li>
								<p class="overviewListingStats toRight"><span class="tip">#_</span>${oId}</p>
								<p class="overviewListingTitle">Order Id</p>
							</li>
							<li>
								<p class="overviewListingStats toRight"><span class="tip">#_</span>${iId}</p>
								<p class="overviewListingTitle">Product Id</p>
							</li>
							<li>
								<p class="overviewListingStats toRight"><span class="tip">#_</span>${pId}</p>
								<p class="overviewListingTitle">Payout Id</p>
							</li>
						</ul>
						<hr/>
						<ul class="overviewListing">
							<li>
								<p class="overviewListingStats toRight">${number }</p>
								<p class="overviewListingTitle">Number Of Order</p>
							</li>
							<li>
								<p class="overviewListingStats toRight">$${amount }</p>
								<p class="overviewListingTitle">Amount</p>
							</li>
							<li>
								<p class="overviewListingStats toRight">$${fee }</p>
								<p class="overviewListingTitle">Fee</p>
							</li>
							<li>
								<p class="overviewListingStats toRight">${status }</p>
								<p class="overviewListingTitle">Status</p>
							</li>
							<li>
								<p class="overviewListingStats toRight">${createdOn }</p>
								<p class="overviewListingTitle">Created On</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
		
		</c:forEach>
	</div>
</div>


<script type="text/javascript">
$(document).ready(function() {

	$(".status").each(function() {
		var s = $(this).data("status").toLowerCase();
		if(s === "pending") {
			$(this).addClass("blue");
		} else if(s === "completed") {
			$(this).addClass("green");
		} else if(s === "removed") {
			$(this).addClass("red");
		}
		$(this).addClass("twhite");
	});
	
	
	function setUpRoleNav() {
		var a = $("#statusNav").data("status");
		$("#" + a + "Tab").addClass("active");
	}; setUpRoleNav();
	 
	function init() {
		$("#orderNav").addClass("active");
	}; init();
});

</script>
</body>
</html>