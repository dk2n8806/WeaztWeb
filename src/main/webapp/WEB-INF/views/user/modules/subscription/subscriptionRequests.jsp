
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Subscriptions - Weazt</title>
<style type="text/css">

.mainPage {
	min-height: 300px;
}


.lmblk li {
	display: inline-block;
	vertical-align: middle;
	margin: 20px;
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
							<h1 class="fnor">Subscription Requests</h1>
							<p><span id="overview" data-num="${size}" ></span></p>
						</div>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when  test="${fn:length(subscriptions) > 0}">	
					<div class="col-wrapper">
				
						<c:forEach var="subscription" items="${subscriptions }">
							<c:set var="subscriptionId" value="${subscription.subscriptionId}" />
							<c:set var="subscribedPrice">
								<fmt:formatNumber  value="${subscription.subscriptionValue / 100}" maxFractionDigits="2"></fmt:formatNumber>
							</c:set>
							<c:set var="subscribedStatus" value="${subscription.status}" />
							 
							<c:set var="productImage" value="${subscription.productImage}" />
							<c:set var="productTitle" value="${subscription.productTitle}" />
							<c:set var="category" value="${subscription.category}" />
							<c:set var="min" value="${subscription.hadShipped}" />
							<c:set var="max" value="${subscription.nos}" />
							<c:set var="scheduledOn"><fmt:formatDate value="${subscription.scheduledOn }"/></c:set>
						
							<spring:url var="subscriptionDetailLink" value="/user/subscriptions/${subscriptionId }" htmlEscape="true" />
							<spring:url var="favoriteToggle" value="/user/subscription/favorite/toggle?_subscriptionId=${subscriptionId}" htmlEscape="true" />
							<spring:url var="starToggle" value="/user/subscription/star/toggle?_subscriptionId=${subscriptionId}" htmlEscape="true" />
							 
							<div id="s${subscriptionId}"  class="col-2">
								<div class="blk-b">
									<div class="blk-header">
										<div class="toRight">
										<spring:url value="/user/subscription/generate-unsubscribe-token" var="requestUnsubscribeTokenForm" htmlEscape="true" />
											<form class="requestUnsubscribeTokenForm" action="${requestUnsubscribeTokenForm}" method="POST">
												<input type="hidden" name="_sid" value="${subscriptionId}">
												<button class="link" type="submit"><i class="fa fa-times"></i>&nbsp;cancel request</button>
											</form>
											</div>
										<h3 class="fontCap"><span class="bhi"><i class="fa fa-tags"></i></span>${category }</h3>
									</div>
									<div class="blk-content">
										<div class="card100 prefix-clear">
											<div class="card-face">
												<div class="card-holder">
													<img src="${productImage }">
												</div>
											</div>
											<div class="card-content">
												<div><h3 class="fnor"><a>${productTitle}</a></h3></div>
											</div>
										</div>
									</div>
									<%-- 
									<div class="blk-footer prefix-clear">
										<p>requested on: <strong>${scheduledOn }</strong></p>
									</div>
									 --%>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:when>
			</c:choose>
							
			
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
					 <c:if test="${fn:length(subscriptions) == size}">
					 	<spring:url var="nextLink" value="${requestpath}?ref=${ref}&p=${n+1}&s=${size}" htmlEscape="true" />
					 	<li><p><a class="btn white" href="${nextLink }"><strong>Next Page</strong></a></p></li>
					 </c:if>
				</ul>
			</div>
		</div>
	</div>
	
</div>
 
 </div>

 


<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">

$(document).ready(function() {	

	$(".requestUnsubscribeTokenForm").on("submit", function(event) {
		var $this = $(this);
		var serializedData = $this.serialize();
		var urlForm = $this.attr("action");
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					//alert("success");
					var url = "${pageContext.request.contextPath}" + data.result;
					$(location).attr("href", url);
				} else {
					alert("fail");
				}
			}, 
			error: function(data) {
				alert("error");
			}
		});	 
		event.preventDefault();
	});
	
	
	
	
 	function init() {
 		$("#dbSubscriptionNav").addClass("active");
 		$("#subscriptionRequestNav").addClass("active");
		overview();
 	}; init();


	;function overview() {
		var $a = $("#overview");
		var n = $a.data("num");
		var x = "subscription request";
		if(n > 1) {
			x += "s";
		}
		$a.html("You have  <strong>" + n + " " + x + "</strong>. Once the sellers process your requests, a subscription will be created and the product will be delivered periodically.");
	}
 	

});
</script>

</body>
</html>