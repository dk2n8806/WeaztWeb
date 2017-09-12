<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<jsp:include page="../base/headerMetaLink.jsp"></jsp:include>
<title>Weazt</title>
</head>
<body>


<jsp:include page="../components/header-navigation.jsp"></jsp:include>

<div class="ph40">
	<div class="main-content toCenter">
		<div class="p40">
			<h3>ERROR 404</h3>
			<h1>Unavailable Resource</h1>
			<br/>
			<spring:url value="/shop" var="discoverLink" htmlEscape="true"></spring:url>
			<p><a class="btn super-btn" href="${discoverLink }">Back to Shop&nbsp;<i class="fa fa-long-arrow-right"></i></a></p>
		</div>
	</div>
</div>


</body>
</html>