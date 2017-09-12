<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../base/headerMetaLink.jsp"></jsp:include>
<title>Email verification | Weazt</title>
<style type="text/css">
	
.main-content {
	width: 500px;
}
</style>
</head>
<body>
<jsp:include page="../components/header-navigation.jsp"></jsp:include>
<br/>
<div class="main-content">
	<c:choose>
		<c:when test="${success == true }">
			<div class="blk toCenter">
				<div class="blk-header">
					<h2 class="ftrend">
						<span class="bhi twhite"><i class="fa green fa-check"></i></span>
						<span class="tgreen">Your email has been verified!</span>
					</h2>
				</div>
				<div class="blk-content">
					<spring:url var="shopLink" value="/shop"  htmlEscape="true" />
					<p>	<a class="btn" href="${shopLink }">Continue shopping <i class="fa fa-long-arrow-right"></i></a></p>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="blk-b">
				<div class="panel-header">
					<h3>
						<span class="bhi tred"><i class="fa fa-times"></i></span>
						<span>Unable to verify your account</span>
					</h3>
				</div>
				<div class="response"></div>
				<div class="blk-content">
					<p>We are unable to verify your account this time because of either the token has been used or expired</p>
				</div>
				<div class="blk-footer">
						
					<spring:url var="verificationForm" value="/user/request-verification-token" htmlEscape="true"></spring:url>
					<form id="verificationForm" action="${verificationForm }" method="POST">
						<button class="btn"><strong>Resend code</strong></button>
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<script type="text/javascript">

$(document).ready(function() {
	var $f = $("#verificationForm");
	$f.on("submit", function(event) {
		var serializedData = $(this).serialize();
		var urlForm = $(this).attr("action");
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					success($(".response"));
				} else {
				}
			}, 
			error: function(data) {
			}
		});	 
		event.preventDefault();	
	});

	function success($f) {
		var str = "<div class=\"blk-green\">"
				+ 	"<div class=\"blk-header toCenter\">"
				+ 		"<p>We have sent a verification-code to your email. Please check your inbox.</p>"
				+ 	"</div>"
				+ "</div>";
		
		$f.html(str);
	}
	
});
</script>
</body>
</html>