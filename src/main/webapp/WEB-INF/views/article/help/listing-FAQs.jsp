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
			<div class=""><h1 class="ftrend">Listing FAQs</h1></div>
		</div>
	</div>
</div>

<div class="mainList white">
	<div class="main-content">
		<div>
			<ul class="topic-nav">
				<spring:url value="help" var="helpLink" htmlEscape="true" />
				<li><a href="${helpLink }">Help Center</a>&nbsp;/&nbsp;Listing FAQs</li>
			</ul>
		</div>
		<div class="faqList">
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: Is there a limit for posting items on Weazt?</h3>
				<div class="topicAns">
					<p>Posting items on Weazt are easy and always free.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: When will I be charged by using Weazt?</h3>
				<div class="topicAns">
					<p>You only pay when you make a sale.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: How can I list my items on Weazt?</h3>
				<div class="topicAns">
					<p>Listing an item on Weazt is easy and fast. Before listing an item, you may need to have a photo of the item, and know how you want to make it a subscription.</p>
					<br/>
					<p><strong>To access New Listing page</strong></p>
					<ul class="p20">
						<li><p>- Go to your <strong>Listings</strong></p></li>
						<li><p>- Click on <strong>New Listing</strong> button to redirect to a new page.</p></li>
					</ul>
					<br/>
					<p><strong>To upload your item</strong></p>
					<ul class="p20">
						<li><p>1. Enter the item's basic info -- describe general info about the item.</p></li>
						<li><p>2. Enter the item's shipping info -- let us know how you want to handle shipping.</p></li>
						<li><p>3. Enter the item's subscription info -- define how you want to make the item to a subscription.</p></li>
						<li><p>4. Click <strong>Submit</strong> to publish the item.</p></li>
					</ul>
					<p>Once the item is published, it is available immediately on our search engine and allows customers to subscribe. </p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: What is a listing's status?</h3>
				<div class="topicAns">
					<p>Your item will be always assigned to one of the status:</p>

					<ul class="p20">
						<li><p>&#8226;&nbsp;&nbsp;<strong>Public</strong> -- the item is displayed publicly and everyone is able to subscribe it.</p></li>
						<li><p>&#8226;&nbsp;&nbsp;<strong>Private</strong> -- the item is not allowed for subscribing but still available to Weazt so that you can keep processing scheduled shipments.</p></li>
						<li><p>&#8226;&nbsp;&nbsp;<strong>Deleted</strong> -- the item is terminated and removed from our search engine. </p></li>
					</ul>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: How can I update my item’s status?</h3>
				<div class="topicAns">
					<p>You can switch from one status to another whenever you want.</p>
					<br/>
					<p><strong>To update your item status:</strong></p>
					<ul class="p20">
						<li><p>1. Go to your <strong>Listings.</strong></p></li>
						<li><p>2. Select one of your items</p></li>
						<li><p>3. Click on <strong>status menu.</strong></p></li>
						<li><p>4. Select the status that you want for the item.</p></li>
						<li><p>5. Click <strong>Confirm</strong></p></li>
					</ul>
					<p>Please note that you won’t be able to update the item’s status after it is setted to <strong>Deleted.</strong></p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: Can I update my listing?</h3>
				<div class="topicAns">
					<p>Yes, you can edit your listing except for category and the item’s displayed image.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: What happens if I delete a listing?</h3>
				<div class="topicAns">
					<p>After you delete a listing, Weazt will automatically do the following:</p>
					<ul class="p20">
						<li><p>&#8226;&nbsp;&nbsp;Cancel all of your order requests on the item.</p></li>
						<li><p>&#8226;&nbsp;&nbsp;Terminate all of your scheduled shipments.</p></li>
						<li><p>&#8226;&nbsp;&nbsp;Cancel your customers' subscription.</p></li>
						<li><p>&#8226;&nbsp;&nbsp;Notify your customers about the cancellation.</p></li>
						<li><p>&#8226;&nbsp;&nbsp;Calculate and refund your customers.</p></li>
					</ul>
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