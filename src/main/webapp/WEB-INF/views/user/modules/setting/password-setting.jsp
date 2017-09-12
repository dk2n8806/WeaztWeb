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
			
				<div class="col75">
										
										
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
												<button type="submit" class="btn plainBtn"><strong>Resend verification code</strong></button>
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
			
					
				<div class="col75">
					<div class="blk-b">
						<spring:url var="updateAccountPasswordForm" value="/user/update-account-password" htmlEscape="true"/>
						<form id="passwordForm" action="${updateAccountPasswordForm}" method="POST">
						
						<div class="panel-header">
							<h3><span>Password</span></h3>
						</div>
						<div id="displayBlk" class="p10"></div>
						<div class="blk-content modal-content">
							<p>Change your password or recover your current one.</p>
							<br/>
							<table class="p10">
								<tr>
									<td class="c-20"><strong>Password</strong></td>
									<td class="c-40"><input type="password" name="_old" /></td> 
								</tr>
								<tr>
									<td><strong>New password</strong></td>
									<td><input type="password" name="_new" /></td> 
								</tr>
								<tr>
									<td><strong>Verify password</strong></td>
									<td><input type="password" name="_con" /></td> 
								</tr>
							</table>
						</div>
						
						<div class="blk-footer prefix-clear">
							<spring:url var="forgotPassword" value="/forgot_password" htmlEscape=""></spring:url>
							<a class="link" href="${forgotPassword }">Forgot Password</a>
							<button class="toRight plainBtn btn"><strong>Update</strong></button>
						</div>
						
						</form>
					</div>
				</div>
				
				
			</div>
		</div>
	</div>
</div>


<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript" src='<c:out value="/resources/js/util.js"></c:out>'></script>
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
				+ 	"<div class=\"blk-header\">"
				+ 		"<p>Unable to send a verification code! Please try again</p>"
				+ 	"</div>"
				+ "</div>";
		
		$f.html(str);
	};
	
	
	
	
	
	var $displayBlk = $("#displayBlk");
	$displayBlk.hide();
	$("#passwordForm").on("submit", function(event){
		var $a = $(this).find("input[name=_old]");
		var $b = $(this).find("input[name=_new]");
		var $c = $(this).find("input[name=_con]");
		
		var flag = false;
		var err = [];
		
		if($a.val() === "") {
			flag = false;
			err.push("You mush enter your current password in order to change it");
		} else if($a.val().length < 8) {
			flag = false;
			err.push("Invalid old password");
		} else if($b.val() === "") {
			flag =false;
			err.push("Your new password cannot be empty");
		} else if($b.val().length < 8) {
			flag = false;
			err.push("Your new password is too short");
		} else if($b.val() != $c.val()) {
			flag = false;
			err.push("New and confirm password doesn't match");
		} else {
			flag = true;
		}
		showError(flag, err);
		
		if(flag) {
			var serializedData = $(this).serialize();
			var urlForm = $(this).attr("action");
			$.ajax({
 				url:urlForm,
 				data: serializedData,
 				method: "POST",
 				success: function(data){
 					if(data.state) {
 						showConfim("Your password has been changed");
					} else {
						showError(false, ["Sorry, you password isn't correct"]);
 						serverResponse(["Sorry, server error. Please retry to complete the tasks"], "red");
 					}
 				}, 
 				error: function(data) {
 					serverResponse(["Something is going wrong"], "red");
 				},
 				complete: function() {
 				}
 			});
			$a.val("");
			$b.val("");
			$c.val("");	  
		}
		
		
		event.preventDefault();
	});
	
	;function showConfim(msg) {
		$displayBlk.empty().removeClass();
		$displayBlk.addClass("blk-green")
					.addClass("p10")
					.append("<p>").append(msg).append("</p>");
		$displayBlk.show();
	}
	;function showError(flag, err) {
		$displayBlk.empty().removeClass();
		
		if(flag) {
			$displayBlk.hide();
		} else {
			$displayBlk.addClass("blk-red").addClass("p10");
			for(var i = 0, l = err.length; i < l; i++ ) {
			    $displayBlk.append("<p>").append("<i class=\"fa tred fa-exclamation-triangle\"></i>&nbsp;").append(err[i]).append("</p>");
			}
			$displayBlk.show();
		}
	}
	
	

	$("#settingPasswordNav").addClass("active");
	$("#dbSettingNav").addClass("active");
});
</script>
</body>
</html>