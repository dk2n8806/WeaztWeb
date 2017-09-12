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
			<div class=""><h1 class="ftrend">Subscription FAQs</h1></div>
		</div>
	</div>
</div>

<div class="mainList white">
	<div class="main-content">
		<div>
			<ul class="topic-nav">
				<spring:url value="help" var="helpLink" htmlEscape="true" />
				<li><a href="${helpLink }">Help Center</a>&nbsp;/&nbsp;Subscription FAQs</li>
			</ul>
		</div>
		<div class="faqList">
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: How do I subscribe  to an item?</h3>
				<div class="topicAns">
					<br/>
					<p><strong>Step 1</strong></p>
					<p>If you're just browsing, try the categories by click on the "Discover" on the top left corner of the page.</p>
					<br/>
					<p>If you have a specific idea of what you want, use the search field to find your item.</p>
					<br/>
					<p><strong>Step 2</strong></p>
					<p>Click on the item that you search for. It will take you to a new page so that you can find out more about the item and the seller's offer for subscribing the item.</p>
					<br/>
					<p>Click on the <strong>Subscribe Now</strong> button.</p>

					<br/>
					<p><strong>Step 3</strong></p>
					<p>Add your <strong>shipping address</strong> and set up your <strong>payment detail.</strong></p>
					<br/>
					<p><strong>Step 4</strong></p>
					<p>Select number of shipment that you want, then click on <strong>Send Request</strong> to notify the seller and complete your checkout.</p>
					<br/>
					<p><i>Please note that updating your shipping address will redirect other items to be shipped to that address.</i></p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: why does my subscription require approval?</h3>
				<div class="topicAns">
					<p>At checkout, when you click on Send Request, Weazt will notify the seller about the request, but also create a payment on the item for you. The seller then will review your request. If the seller accept your request, Weazt will automatically capture the payment and charge your account. Otherwise, Weazt will void the payment and no charge will be made to your account if the seller reject your request.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: How do I cancel a subscription?</h3>
				<div class="topicAns">
					<p>If you are not satisfied with the item, you can choose to cancel the subscription.</p>
					<ul class="p20">
						<li><p>1. Go to your <strong>Subscriptions.</strong></p></li>
						<li><p>2. Select the item you want to cancel.</p></li>
						<li><p>3. On the new page, click on the <strong>status icon</strong> then select <strong>Unsubscribe</strong> option.</p></li>
						<li><p>4. Click <strong>Confirm.</strong></p></li>
					</ul>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: What happens if I cancel a subscription?</h3>
				<div class="topicAns">
					<p>When you cancel a subscription, Weazt will calculate and issue a refund to your account depending on the subscription current stage and terminate all the scheduled shipments.</p>
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