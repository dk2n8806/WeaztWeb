<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Weazt Registration</title>

<style type="text/css">

</style>
</head>

<body>

<jsp:include page="../../components/header-navigation.jsp"></jsp:include>

<br/>
<div class="main-content mx500">
	<div class="toCenter">
		<div class="weazt-logo"></div><br/>
		<h1 class="fnor">Get started with a free account</h1>
	</div>
	<br/><br/>
	<div class="blk-b">
		<div class="blk-content modal-content">
			<c:if test="${not empty errorMsg }">		
				<div class="blk-red blk-content">
					<p>Error while register a new account or Email has already taken.</p>
					<p>Please follow the instrucion below to create your account.</p>
					<ul class="p10">
						<li>&#8226;&nbsp;Your username shoudl only contain alphabet letters or numbers</li>
						<li>&#8226;&nbsp;Your email should contain alphabet letters and numbers.</li>
						<li>&#8226;&nbsp;Make sure your email is correct format</li>
						<li>&#8226;&nbsp;Your password should be 8-15 long</li>
						<li>&#8226;&nbsp;Your password should only contain at least 1 letter and 1 number</li>
					</ul>
				</div>
				<br/><br/>
			</c:if>
			<spring:url value="/register" var="register" htmlEscape="true" />
			<form:form id="registerForm" method="post" action="${register}" modelAttribute="newUser">
				<div id="error" class="hide"></div>
				<table class="p10">
					<tr>
						<td>
							<label for="reg-e"><strong>Your username</strong></label>
							<form:input path="username" autocomplete="off" id="reg-u" type="text"/>
							<p id="reg-u-err" class="error hide"><i class="fa fa-exclamation-triangle txtr"></i> &nbsp;<span class="txtr">Username is required!</span></p>
						</td>
					</tr>
					<tr>
						<td>
							<label for="reg-e"><strong>Email</strong></label>
							<form:input path="email" autocomplete="off" id="reg-e" type="text"/>
						</td>
					</tr>
					<tr>
						<td>
							<label for="reg-p"><strong>Password</strong></label>
							<input name="password" autocomplete="off" id="reg-p" type="password"/>
						</td>
					</tr>
					<tr class="hide">
						<td>
							<input class="toLeft" type="checkbox" checked />
							<span class="tip">&nbsp;Remember me!</span>
						</td>
					</tr>
					<tr>
						<td>	
							<br/>
							<br/>
							<button class="btn c-100 flatGreenBtn" type="submit"><strong>Register</strong></button>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
		<div class="blk-footer">
			<spring:url value="/privacy" var="privacyPage" htmlEscape="true" />
			<spring:url value="/tos" var="tosPage" htmlEscape="true" />
			<p>By creating an account, you agree to our  <a class="link" href="${tosPage}" target="_blank">Terms of Service</a> and <a class="link" href="${privacyPage }" target="_blank">Privacy Policy.</a></p>		
		</div>
	</div>
	<div class="prefix-clear">
		<spring:url value="/login" var="loginLink" htmlEscape="true" />
		<p class="toRight">Already have an account? <a class="link" href="${loginLink}"><strong class="">Login&nbsp;&nbsp;<i class="fa fa-caret-right"></i></strong></a></p>
	</div>
</div>





<jsp:include page="../../components/footer-navigation.jsp"></jsp:include>

<script type="text/javascript" src='<c:url value="/resources/js/util.js" />'></script>

<script type="text/javascript">
$(document).ready(function() {
	var regex = new RegExp("^[a-zA-Z0-9]+$");
	var emailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	$("#registerForm").on("submit", function(event) {
		var $this = $(this);
		var $username = $($this.find("input[name=username]"));
		var $email = $($this.find("input[name=email]"));
		var $password = $($this.find("input[name=password]"));
		var $btn = $this.find("button[type=submit]");

		
		
		var username = $username.val().trim();
		var email = $email.val().trim();
		var password = $password.val().trim();
		
		var uFlag =  (username.length >= 4) && regex.test(username) ? true : false;//strHandler($username) && minLengthHandler($username, 4);
		var eFlag =  emailRegex.test(email); //strHandler($email) && validateEmail($email);
		var pFlag =  password.length >= 6 ? true : false; //false; //strHandler($password) && minLengthHandler($password, 6);
 
		
		
		toggleErrorBox($username, uFlag);
		toggleErrorBox($email, eFlag);
		toggleErrorBox($password, pFlag);
		

		if(uFlag && eFlag && pFlag) {
			var serializedData = $this.serialize();
		    var urlForm = $this.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				beforeSend: function() {	
					$btn.attr("disabled", true);
					$btn.prepend("<span class=\"loader\"><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;</span>");
			    },
				success: function(data){
					if(data.state) {
						window.location.href = "shop";
					} else {
						$this.find("#error").empty().append("<div class=\"ph20\"><div class=\"blk-red p10\"><p><i class=\"fa tred fa-exclamation-triangle\"></i>&nbsp;&nbsp;Error! An account has already registerred with this email.</p></div></div>").show();				
					}
				}, 
				error: function(data) {
					serverResponse(["[ERROR] service fail"], "red");
				},
				complete: function(data) {
					$btn.find(".loader").empty();
					$btn.attr("disabled", false);
				}
			}); 
		} else {
			$this.find("#error").empty().append(
				"<div class=\"ph20\"><div class=\"blk-red p10\">"
				+	"<div><h3 class=\"fnor\"><strong><i class=\"fa tred fa-exclamation-triangle\"></i>&nbsp;&nbsp;There was a problem.</strong></h3></div>"
				+ 	"<ul class=\"p10\">"
				+ 		"<li>&#8226;&nbsp;&nbsp;Your username should be at least 4 letters</li>"
				+ 		"<li>&#8226;&nbsp;&nbsp;Your username must contain only letters and numbers</li>"
				+		"<li>&#8226;&nbsp;&nbsp;Make sure you enter correct email addresss</li>"
				+		"<li>&#8226;&nbsp;&nbsp;Your password should be at least 6 letters</li>"
				+	"</ul>"
				+ "</div></div>").show();
		}
		
		event.preventDefault();
	});
	
});
</script>

</body>
</html>