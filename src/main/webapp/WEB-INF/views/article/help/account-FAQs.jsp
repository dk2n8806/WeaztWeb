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
			<div class=""><h1 class="ftrend">Account FAQs</h1></div>
		</div>
	</div>
</div>

<div class="mainList white">
	<div class="main-content">
		<div>
			<ul class="topic-nav">
				<spring:url value="help" var="helpLink" htmlEscape="true" />
				<li><a href="${helpLink }">Help Center</a>&nbsp;/&nbsp;Account FAQs</li>
			</ul>
		</div>
		<div class="faqList">
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: I forgot password. How do I reset my password?</h3>
				<div class="topicAns">
					<p><strong>To reset your password:</strong></p>
					<ul class="p10">
						<li><p>1. Click on <strong>Account</strong>&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i>&nbsp;&nbsp;<strong>Password</strong></p></li>
						<li><p>2. Click on <strong>reset password</strong></p></li>
					</ul>
					<p>Weazt will send you an email to reset your password. Follow the instruction in the email and your password will be resetted.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: How do I change my password?</h3>
				<div class="topicAns">
					<p>Your password can be change in the Password Setting of your Account.</p>
					<br/>
					<p><strong>To change your password:</strong></p>
					<ul class="p10">
						<li><p>1. Click on <strong>Account</strong>&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i>&nbsp;&nbsp;<strong>Password</strong></p></li>
						<li><p>2. Enter a new password and click on <strong>Save</strong></p></li>
					</ul>
					<p>Weazt will send you an email to reset your password. Follow the instruction in the email and your password will be reseted.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: How can I close my account?</h3>
				<div class="topicAns">
					<p>Please note that close your account will not free up your email address and business name for re-use on a new account.</p>
					<br/>
					<p><strong>To close your account:</strong></p>
					<ul class="p20">
						<li><p>1. Go to your <strong>Account</strong></p></li>
						<li><p>2. Scroll down to the end of <strong><i>Closing Account Section</i></strong> and click on the <strong>Close Account</strong>.</p></li>
					</ul>
					<p>Weazt will send you an email to confirm the action. Click on the confirm link to finalize closing your account.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: Can I reopen my account?</h3>
				<div class="topicAns">
					<p>Yes, just send us an email and we will reopen your account.</p>
				</div>
			</div>
			<div class="topicBox">
				<h3 class="topicQ ftrend">Q: What is the best way to contact Weazt?</h3>
				<div class="topicAns">
					<p>By email. We&#8217;ve found that by starting with email enables us to provide the fastest response. You can send an email to us at <strong>support@weazt.com</strong> or through our contact page.</p>
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