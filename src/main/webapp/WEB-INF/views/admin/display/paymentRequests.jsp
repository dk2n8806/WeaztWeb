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
					<h1 class="ftrend">Payment Requests</h1>	
						
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
		<c:forEach var="request" items="${requests}">
			<c:set var="requestId" value="${request.id }"></c:set>
			<c:set var="paymentId" value="${request.payment.id }"></c:set>
			<c:set var="status" value="${request.status }"></c:set>
			<c:set var="createdOn">
				<fmt:formatDate value="${request.createdOn }"/>
			</c:set>
			
			
			<div class="col-4">
				<div class="blk-b">
					<div class="panel-header status twhite" data-status="${status }"><h3>${status }</h3></div>
					<div class="blk-content">
						<ul class="overviewListing">
							<li>
								<p class="overviewListingStats toRight"><span class="tip">#_</span>${requestId}</p>
								<p class="overviewListingTitle">Request Id</p>
							</li>
							<li>
								<p class="overviewListingStats toRight"><span class="tip">#_</span>${paymentId}</p>
								<p class="overviewListingTitle">Subscription Id</p>
							</li>
						</ul>
						<hr/>
						<ul class="overviewListing">
							<li>
								<p class="overviewListingStats toRight">${status}</p>
								<p class="overviewListingTitle">Status</p>
							</li>
							<li>
								<p class="overviewListingStats toRight">${createdOn}</p>
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
		if(s === "new") {
			$(this).addClass("blue");
		} else if(s === "done") {
			$(this).addClass("green");
		}
	});
	
	function setUpRoleNav() {
		var a = $("#statusNav").data("status");
		$("#" + a + "Tab").addClass("active");
	}; setUpRoleNav();
	 
	function init() {
		$("#paymentRequestNav").addClass("active");
	}; init();
});

</script>
</body>
</html>