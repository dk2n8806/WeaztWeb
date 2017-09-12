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
			<div class=""><h1 class="ftrend">Payout FAQs</h1></div>
		</div>
	</div>
</div>

<div class="mainList white">
	<div class="main-content">
		<div>
			<ul class="topic-nav">
				<spring:url value="help" var="helpLink" htmlEscape="true" />
				<li><a href="${helpLink }">Help Center</a>&nbsp;/&nbsp;Payout FAQs</li>
			</ul>
		</div>
		<div class="faqList">
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: What is Payout method?</h3>
				<div class="topicAns">
					<p>When you completed an order, Weazt will create a payout to you account. In order for Weazt to release and deposit the payouts from your account to your bank, you must create a default payout method.</p>
					<br/>
					<p><strong>Create a payout method</strong></p>
					<br/>
					<p>You can create and store as many payout methods as your want on your account. To create a payout method:</p>
					<ul class="p20">
						<li><p>1. Go to your <strong>Account</strong>&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i>&nbsp;&nbsp;<strong>Payout.</strong></p></li>
						<li><p>2. Click on <strong>new payout method.</strong></p></li>
						<li><p>3. Enter your debit card info.</p></li>
						<li><p>4. Click <strong>Save.</strong></p></li>
					</ul>
					<p><strong>Set a default payout method</strong></p>
					<p>Hover on a payout method, then click on <strong>Default</strong> button to set that payout as default.</p>
					<br/>
					<p><strong>Delete a payout method</strong></p>
					<p>Hover on a payout method and click on <strong>Delete</strong> button to remove the payout method from your account.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: When do I receive my earning?</h3>
				<div class="topicAns">
					<p>Weazt calculates your earnings every Sunday and release the final total on Monday, so you can expect to see a deposit from Weazt to your bank on the Wednesday.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: Where can I find my earning balance?</h3>
				<div class="topicAns">
					<p>Go to your <strong>Account</strong>&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i>&nbsp;&nbsp;<strong>Payout</strong>&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i>&nbsp;&nbsp;<strong>Transactions</strong> to view your earning balance.</p>
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