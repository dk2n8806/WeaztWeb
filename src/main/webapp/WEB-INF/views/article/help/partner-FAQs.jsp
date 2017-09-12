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
			<div class=""><h1 class="ftrend">Partner FAQs</h1></div>
		</div>
	</div>
</div>

<div class="mainList white">
	<div class="main-content">
		<div>
			<ul class="topic-nav">
				<spring:url value="help" var="helpLink" htmlEscape="true" />
				<li><a href="${helpLink }">Help Center</a>&nbsp;/&nbsp;Partner FAQs</li>
			</ul>
		</div>
		<div class="faqList">		
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: How can I become Weazt's partner?</h3>
				<div class="topicAns">
					<p>Become Weazt's partner is easy. Weazt only need your business info and a product for sale.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: Where can I apply to become Weazt's partner?</h3>
				<div class="topicAns">
					<spring:url value="/partner"  var="partnerLink" htmlEscape="true"/>
					<p>It’s only take you less than a minute to complete <a class="link" href="${partnerLink }">Weazt's partner form.</a></p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: Is there any cost to become Weazt's partner?</h3>
				<div class="topicAns">
					<p>Not many. Becoming Weazt's partners is free. Listing on Weazt is also free. The only fee that you need to pay is the service fee for every successful sale.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: What is Weazt's service fee?</h3>
				<div class="topicAns">
					<p>For every successful sale, Weazt's will charge your account a service fee which include:</p>
					<br/>
					<ul>
						<li><p>&#8226;&nbsp;&nbsp;<strong>Shipping label fee:</strong> is varied, depending on your package's weight and sizes. </p></li>
						<li><p>&#8226;&nbsp;&nbsp;<strong>Transaction fee:</strong> Weazt will charge 6.99% on every successful shipments (including payment transaction fee).</p></li>
					</ul>
					<br/>
					<p>Average shipping label is $5.50.</p>
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