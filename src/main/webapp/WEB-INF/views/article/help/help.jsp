<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Help Center | Weazt</title>

<style type="text/css">
.main-content {
	width: 1000px;
}


</style>

</head>
<body>
<jsp:include page="../../components/header-navigation.jsp"></jsp:include>

<div style="background: #14A7A5;" class="mainList twhite">
	<div class="main-content toCenter">
		<div class="p40">
			<div class=""><h1 class="ftrend">How Can We Help?</h1></div>
		</div>
	</div>
</div>

<div class="mainList white">
	<div class="main-content">
		<div>
			<ul class="topic-nav">
				<spring:url value="/help" var="helpCenterLink" htmlEscape="true"></spring:url>
				<li><a href="${helpCenterLink }">Help Center</a></li>
				<!-- <li><a href="#">Getting Started</a></li>
				<li><a href="#">Subscriber Guidelines</a></li>
				<li><a href="#">Seller Guidelines</a></li> -->
			</ul>
		</div>
		<div class="p40">	
			<spring:url value="/help/account-FAQs" var="helpAccountLink" htmlEscape="true"></spring:url>
			<spring:url value="/help/subscription-FAQs" var="helpSubscriptionLink" htmlEscape="true"></spring:url>
			<spring:url value="/help/payment-refund-FAQs" var="helpPaymentLink" htmlEscape="true"></spring:url>
			<spring:url value="/help/partner-FAQs" var="helpSellerLink" htmlEscape="true"></spring:url>
			<spring:url value="/help/listing-FAQs" var="helpListingLink" htmlEscape="true"></spring:url>
			<spring:url value="/help/order-shipment-FAQs" var="helpOrderLink" htmlEscape="true"></spring:url>
			<spring:url value="/help/payout-FAQs" var="helpPayoutLink" htmlEscape="true"></spring:url>
					
			<div class="topic-grid">
				<ul>
					<li>
						<a href="${helpAccountLink }">
							<h3 class="ftrend">
								<span><i class="fa fa-user"></i></span>
								<span>Account</span>
							</h3>
						</a>
					</li>
					<li>
						<a href="${helpSubscriptionLink}">
							<h3 class="ftrend">
								<span><i class="fa fa-retweet"></i></span>
								<span>Subscriptions</span>
							</h3>
						</a>
					</li>
					<li>
						<a href="${helpPaymentLink}">
							<h3 class="ftrend">
								<span><i class="fa fa-credit-card"></i></span>
								<span>Payments & Refunds</span>
							</h3>
						</a>
					</li>
					<li>
						<a href="${helpSellerLink}">
							<h3 class="ftrend">
								<span><i class="fa fa-building-o"></i></span>
								<span>Weazt's Partner</span>
							</h3>
						</a>
					</li>
					<li>
						<a href="${helpListingLink}">
							<h3 class="ftrend">
								<span><i class="fa fa-file-text"></i></span>
								<span>Listings</span>
							</h3>
						</a>
					</li>
					<li>
						<a href="${helpOrderLink}">
							<h3 class="ftrend">
								<span><i class="fa fa-cubes"></i></span>
								<span>Orders & Shipments</span>
							</h3>
						</a>
					</li>
					<li>
						<a href="${helpPayoutLink}">
							<h3 class="ftrend">
								<span><i class="fa fa-dollar"></i></span>
								<span>Payouts</span>
							</h3>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>

<spring:url value="/contact_us" var="contactLink" htmlEscape="true"></spring:url>
<div class="main-content">
	<div class="p40">
		<div class="toCenter">
			<h2 class="ftrend">Can't find your answer?</h2>
			<p class="tgray">We're here to help. Get in touch and we'll get back to you as soon as we can. <a class="link" href="${contactLink }">Contact us&nbsp;&nbsp;<i class="fa fa-caret-right"></i></a></p>
		</div>
	</div>
</div>





<jsp:include page="../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	function init() {
		$("#commandPrimary li").eq(1).addClass("active");
	}; init();
});
</script>
</body>
</html>