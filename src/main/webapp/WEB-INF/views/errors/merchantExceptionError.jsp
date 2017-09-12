<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../base/headerMetaLink.jsp"></jsp:include>
<title>Weazt</title>
</head>
<style type="text/css">
.fa-check-circle {
	font-size: 14px;
	color: #16a085;
}
</style>

<body>


<jsp:include page="../components/header-navigation.jsp"></jsp:include>

<div class="ph40">
	<div class="main-content toCenter">
		<div class="blk p40">
			<h3>ACCESS DENIED</h3>
			<h1>Unavailable Resource</h1>
			<p>[ERROR] This resource intends for Weazt's partner only!</p>
			<br/>
			<spring:url value="/partner" var="partnerLink" htmlEscape="true"></spring:url>
			<p><a class="btn flatBlueBtn" href="${partnerLink }">Weazt's Partner&nbsp;<i class="fa fa-long-arrow-right"></i></a></p>
			<br/><br/>
			<spring:url value="/help" var="helpLink" htmlEscape="true"></spring:url>
			<spring:url value="/contact_us" var="contactLink" htmlEscape="true"></spring:url>
			<p>If you still have troubles to access this resource, please visit our <a class="link" href="${helpLink }">help pages</a> or <a class="link" href="${contactLink }">contact us</a></p>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	
});

</script>

</body>
</html>