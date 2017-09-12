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
					<h1 class="ftrend">Shipments</h1>	
						
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
		<c:forEach var="shipment" items="${shipments}">
	<!-- 	
	private AddressAdapter sender;
	private AddressAdapter receiver;
	private LabelAdapter labelAdapter;
	private ShipmentStatus status;
	 -->
			<c:set var="shipmentId" value="${shipment.id }"></c:set>
			<c:set var="createdOn">
				<fmt:formatDate value="${shipment.createdOn }"/>
			</c:set>
			<c:set var="status" value="${shipment.status }"></c:set>
			
			<div class="col-4">
				<div class="blk-b">
					<div class="panel-header status" data-status="${status }">${status }</div>
					<div class="blk-content">
					
						<ul class="overviewListing">
							<li class="prefix-clear">		
								<p class="tip toRight">#_<strong>${shipmenttId }</strong></p>
								<p class="overviewListingTitle">Shipment Id</p>
							</li>
						</ul>
						<hr/>
						<ul class="overviewListing">
							<li class="prefix-clear">		
								<p class="tip toRight">${status}</p>
								<p class="overviewListingTitle">Status</p>
							</li>
							<li class="prefix-clear">		
								<p class="tip toRight">${createdOn}</p>
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
		} else if(s === "shipped") {
			$(this).addClass("green");
		}
		$(this).addClass("twhite");
	});
	
	function setUpRoleNav() {
		var a = $("#statusNav").data("status");
		$("#" + a + "Tab").addClass("active");
	}; setUpRoleNav();
	 
	function init() {
		$("#shipmentNav").addClass("active");
	}; init();
});

</script>
</body>
</html>