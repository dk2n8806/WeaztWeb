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
			<div class=""><h1 class="ftrend">Payment & Refund FAQs</h1></div>
		</div>
	</div>
</div>

<div class="mainList white">
	<div class="main-content">
		<div>
			<ul class="topic-nav">
				<spring:url value="help" var="helpLink" htmlEscape="true" />
				<li><a href="${helpLink }">Help Center</a>&nbsp;/&nbsp;Payment & Refund FAQs</li>
			</ul>
		</div>
		<div class="faqList">
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: How am I charged for my subscription?</h3>
				<div class="topicAns">
					<p>You are only charged one time for any established subscription. At checkout, Weazt will create and hold your payment. When the seller accepts your subscriptionr request, Weazt will capture your payment, and it is the only time you are be charge for the subscription.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: What payment methods does Weazt accept?</h3>
				<div class="topicAns">
					<p>Currently, we support all major debit and credit cards.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: Can I create more than one payment methods?</h3>
				<div class="topicAns">
					<p>Yes, you can create as many payment methods as you want; however, Weazt only will ask you to maintain one default method. </p>
					<br/>
					<p><strong>To create a new payment method</strong></p>
					<ul class="p20">
						<li><p>1. Go to your <strong>Account</strong>&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i>&nbsp;&nbsp;<strong>Payment.</strong></p></li></p></li>
						<li><p>2. Click on <strong>New payment method.</strong></p></li>
						<li><p>3. Enter your new debit/credit card info.</p></li>
						<li><p>4. Click <strong>Save.</strong></p></li>
					</ul>
					<p><strong>To set a default payment method</strong></p>
					<p>Hover over a payment method and click <strong>Default.</strong></p>
					<br/>
					<p><strong>To delete a payment method</strong></p>
					<p>Hover over a payment method and click <strong>Delete.</strong></p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: Where can I find my payment transactions?</h3>
				<div class="topicAns">
					<p>Go to your <strong>Account</strong>&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i>&nbsp;&nbsp;<strong>Payment</strong>&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i>&nbsp;&nbsp;<strong>Transactions</strong> to display your payment transactions.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: I canceled a subscription and want my refund back?</h3>
				<div class="topicAns">
					<p>When you cancel a subscription, Weazt will automatically calculate and issue a refund to your account.</p>
					<br/>
					<p>If you need further help, email us. We are gladly to assist you. </p>
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


</body>
</html>