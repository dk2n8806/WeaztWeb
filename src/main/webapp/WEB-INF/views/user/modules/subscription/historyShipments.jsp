<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Shipments - Weazt</title>
<style type="text/css">
.blk-report {
	background: #fafafa;
	border: 1px solid #dce0e0;
	-webkit-box-shadow: 0px 1px 1px 0px #e2e2e2;
	-moz-box-shadow: 0px 1px 1px 0px #e2e2e2;
	box-shadow: 0px 1px 1px 0px #e2e2e2;
}
.blk-report * {
	font-size: 12px !important;
}
.blk-report p {
	margin-bottom: 4px;
}



.mainPage {
	min-height: 300px;
}
</style>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>


<jsp:include page="../../components/user-navigation.jsp"></jsp:include>

 
 
 <div class="mainPage">
 <div class="main-content">	
	<div class="olym-wrapper">
		<div class="olymLeft">
			<div class="col-wrapper">
				<div class="col">
					<jsp:include page="nav/subscription-nav.jsp"></jsp:include>
				</div>
			</div>		
		</div>	
		<div class="olymArena">
		
			<div class="col-wrapper">
				<div class="col">
					<div class="blk">
						<div class="blk-header">
							<h1 class="fnor">Delivered Shipments</h1>
							<!-- <p>Here you can modify your shipment info.</p> -->
						</div>
					</div>
				</div>
			</div>
			
			
			
			<div class="col-wrapper">
				<c:forEach var="template" items="${templates }">
					<c:set var="productTitle" value="${template.productTitle }" />
					<c:set var="productImage" value="${template.productImage }" />
					<c:set var="category" value="${template.category }" />
					<c:set var="shippedOn">
						<fmt:formatDate value="${template.shipment.createdOn }"/>
					</c:set>
					<c:set var="trackingNumber" value="${template.shipment.label.labelAdapter.trackingNumber }" />
					<c:set var="trackingUrl" value="${template.shipment.label.labelAdapter.trackingUrl }" />
					
					<c:set var="receiver" value="${template.shipment.receiver.name }" />
					<c:set var="status" value="${fn:toLowerCase(template.shipment.status)}" />
					<c:set var="street" value="${template.shipment.receiver.street }" />
					<c:set var="city" value="${template.shipment.receiver.city }" />
					<c:set var="state" value="${template.shipment.receiver.state.state }" />
					<c:set var="zipcode" value="${template.shipment.receiver.zipcode }" />
			
				<div class="col-2">
					<div class="blk-b">
						<div class="blk-header">
						
							<div class="aboutInfo toRight">
								<!-- 
								<button><i class="fa fa-exclamation"></i></button>
								 -->
								<span class="fbtn shipmentStatus" data-status="${status }">${status}&nbsp;<i class="fa fa-sort-desc"></i></span>
								<div class="aboutInfoContent" style="width: 300px;">
									<div class="blk-report">
										<div class="blk-content">
											<p><span class="fontCategory">Name:</span>&nbsp;<strong>${receiver}</strong></p>
											<p><span class="fontCategory">Address:</span>&nbsp;${street}&nbsp;${city}&nbsp;${state}&nbsp;${zipcode}</p>
											<p><span class="fontCategory">Status:</span>&nbsp;<strong>${status}</strong></p>
											<p><span class="fontCategory">Tracking&nbsp;#:&nbsp;</span>
												&nbsp;<a class="link" target="_blank" href="${trackingUrl }">${trackingNumber }&nbsp;<i class="fa fa-external-link"></i></a>
											</p>
											<p><span class="fontCategory">Estimated delivery:</span>&nbsp;<span class="shipmentDate" data-date="${shippedOn }"></span></p>
											
										</div>
									</div>
								</div>
							</div>
							<h3 class="fontCap">
								<span class="bhi"><i class="fa fa-tags"></i></span>${category }
							</h3>
						</div>
						<div class="blk-content">						
							<div class="card100 prefix-clear">
								<div class="card-face">
									<div class="card-holder">
										<img src="${productImage }">
									</div>
								</div>
								<div class="card-content">
									<div><h3 class="fnor">${productTitle}</h3></div>
								</div>
							</div>
						</div>
						<div class="blk-footer">
							<p>Shipped on: <strong>${shippedOn }</strong></p>
						</div>
					</div>		
				</div>		
				</c:forEach>
				
				<div class="col toCenter">
					<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
					<br/><br/>
					<ul class="lmblk">
						<c:set var="p" value="${page.getOffset() }" />
						<c:set var="n" value="${page.getPageNumber() }" />
						<c:set var="size" value="${page.getPageSize()}" />
						 <c:if test="${n > 0}">
						 	<spring:url var="previousLink" value="${requestpath}?ref=${ref}&p=${n-1}&s=${size}" htmlEscape="true" />
						 	<li><p><a class="btn white" href="${previousLink }"><strong>Previous Page</strong></a></p></li>
						 </c:if>
						 	<li><p class=""><strong>Page ${n + 1}</strong></p></li>
						 <c:if test="${fn:length(templates) == size}">
						 	<spring:url var="nextLink" value="${requestpath}?ref=${ref}&p=${n+1}&s=${size}" htmlEscape="true" />
						 	<li><p><a class="btn white" href="${nextLink }"><strong>Next Page</strong></a></p></li>
						 </c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
</div>



<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {	

	
	function setupShipmentDate() {
		$(".shipmentDate").each(function() {
			var a = $(this).data("date");
			var f = formatDate(new Date(a), 3);
			var t = formatDate(new Date(a), 5);
		
			$(this).html(f + " - " + t);
			 
			function formatDate(a, b) {
				a.setDate(a.getDate() + b);
				return (a.getMonth() + 1) + '/' + a.getDate() + '/' +  a.getFullYear();
			}
		});
	}; setupShipmentDate();

	function setupOverview() {
		var $a = $("#overview");
		var c = $a.data("count");
		var str = "shipment";
		if(c > 1) {
			str += "s";
		}
		
		$a.html("Total count: <strong class=\"torange\">" + c + " " + str + "</strong>");
	}; setupOverview();
 	
	function setUpShipmentStatus() {
		$(".shipmentStatus").each(function() {
			var a = $(this).data("status");
			if(a === "shipped") {
				$(this).addClass("green");
			}
		});
	}; setUpShipmentStatus();
	
	
 	function init() {
 		
 		$("#dbSubscriptionNav").addClass("active");
 		$("#shipmentNav").addClass("active");
 	}; init();
 	
 	

});
</script>
				
</body>
</html>