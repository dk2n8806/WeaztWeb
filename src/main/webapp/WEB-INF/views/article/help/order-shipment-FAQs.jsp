<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Help Center | Weazt</title>
<style type="text/css">
.main-content {
	width: 800px;
}
</style>
<body>
<jsp:include page="../../components/header-navigation.jsp"></jsp:include>



<div style="background: #14A7A5;" class="mainList twhite">
	<div class="main-content toCenter">
		<div class="p40">
			<div class=""><h1 class="ftrend">Order & Shipment FAQs</h1></div>
		</div>
	</div>
</div>

<div class="mainList white">
	<div class="main-content">
		<div>
			<ul class="topic-nav">
				<spring:url value="help" var="helpLink" htmlEscape="true" />
				<li><a href="${helpLink }">Help Center</a>&nbsp;/&nbsp;Order & Shipment FAQs</li>
			</ul>
		</div>
		<div class="faqList">
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q. How do I process order requests?</h3>
				<div class="topicAns">
					<p>Here is how you can process your order requests</p>
					<br/>
					<ul class="pw20">
						<li><p>1. Go to your <strong>Orders.</strong></p></li>
						<li><p>2. Click on an item that you want to process orders.</p></li>
						<li><p>3. Review your order request.</p></li>
						<li><p>4. Click on <strong>Process Requests</strong> button.</p></li>
						<li><p>5. Click <strong>Process.</strong></p></li>
					</ul>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q. Can I cancel an order request?</h3>
				<div class="topicAns">
					<p>Short answer: Yes.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: How can I cancel an order request?</h3>
				<div class="topicAns">
					<p><strong>To cancel an order request of an item</strong></p>
					<ul class="p20">
						<li><p>1. Go to your <strong>Orders</strong></p></li>
						<li><p>2. Select the item that you want to cancel order request.</p></li>
						<li><p>3. Hover on a request and click on <strong>Delete</strong> button.</p></li>
						<li><p>4. Click <strong>Confirm.</strong></p></li>
					</ul>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: What happens if I cancel an order request?</h3>
				<div class="topicAns">
					<p>When you decide to delete an order request, Weazt will automatically do the following:</p>
					<br/>
					<ul>
						<li><p>-&nbsp;&nbsp;Termiate current shipment of the request.</p></li>
						<li><p>-&nbsp;&nbsp;Terminate all of the scheduled shipemts of the subscriber.</p></li>
						<li><p>-&nbsp;&nbsp;Cancel the customer's subscription.</p></li>
						<li><p>-&nbsp;&nbsp;Calculate and issue a refund to the customer.</p></li>
					</ul>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: Where can I download shipping labels?</h3>
				<div class="topicAns">
					<p>You can redownload and reprint as often as necessary, but only up to the Ship Date. To download shipping labels to your local for printing:</p>
					<ul class="p20">
						<li><p>1. Go to <strong>Orders</strong>&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i>&nbsp;&nbsp;<strong>Transactions</strong></p></li>
						<li><p>2. Select an order.</p></li>
						<li><p>3. Scroll down and click on <strong>Shipping Label</strong> button.</p></li>
					</ul>
					<p>A Shipping Label file will be downloaded to your local and available for printing. Open the file, print out and attach the label on the pacakge. Ship the package.</p>
				</div>
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
</body>
</html>