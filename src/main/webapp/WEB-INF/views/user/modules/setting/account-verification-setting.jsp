
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Account - Weazt</title>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>

	
<div class="main-content">
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="nav/setting-nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">
			<div class="col-wrapper">
				<div class="col">
										
										
					<c:set var="email"><sec:authentication property="principal.email"/></c:set>
					<c:set var="hasVerified"><sec:authentication property="principal.hasVerified"/></c:set>
					<div class="blk-b">
						<div class="panel-header">
							<h3 class="ftrend">
								 <!-- <span class="bhi twhite"><i class="fa blue fa-check"></i></span> -->
								 <span>Email Verification</span>
							</h3>
						</div>
						<div class="response"></div>
						<div class="blk-content">
							<div class="balance-list">
								<ul>
									<li>							
										<div class="p10">
											<p>Please verify your email address by clicking the link in the message we sent to: 
												<strong>${email }</strong>
											</p>
										</div>
										<hr/>
										<div class="p10">
											<p>Can't find email address? Check your spam folder or click the below button to resend the confirmation email</p>
											<br/><br/>
											<spring:url var="verificationForm" value="/user/request-verification-token" htmlEscape="true"></spring:url>
											<form id="verificationForm" action="${verificationForm }" method="POST">
												<button type="submit" class="btn"><strong>Resend verification code</strong></button>
											</form>
										</div>
									</li>
									
							<c:choose>
								<c:when test="${hasVerified == true }">			
								<li class="toCenter">
									<h2 class="ftrend"><span style="display: block; padding: 7px 15px;" class="bhi green twhite"><i class="fa fa-check"></i> Verified Email</span></h2>
								</li>
								</c:when>
							</c:choose>	
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>







<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function() {
	var $response = $(".response");
	$("#verificationForm").on("submit", function(event) {
		$response.empty();
		var $btn = $(this).find("button[type=submit]");
		var serializedData = $(this).serialize();
		var urlForm = $(this).attr("action");
		
		$btn.prepend("<span class=\"loader\"><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;</span>");
		$btn.prop("disabled", true);
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					success($response);
				} else {
					error($response);
				}
			}, 
			error: function(data) {
			},
			complete: function(data) {
				$btn.find(".loader").empty();
				$btn.prop("disabled", false);
	        }
		});	 
		event.preventDefault();	
	});

	function success($f) {
		var str = "<div class=\"blk-green\">"
				+ 	"<div class=\"blk-header toCenter\">"
				+ 		"<p>We have sent a verification code to your email. Please check your inbox.</p>"
				+ 	"</div>"
				+ "</div>";
		
		$f.html(str);
	};

	function error($f) {
		var str = "<div class=\"blk-red\">"
				+ 	"<div class=\"blk-header toCenter\">"
				+ 		"<p>[ERROR] Unable to send a verification code!</p>"
				+ 	"</div>"
				+ "</div>";
		
		$f.html(str);
	};
	
	
	function init() {
		$("#settingVerificationNav").addClass("active");
	}; init();
});
</script>
</body>
</html>