<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Weazt Sign In</title>

<style type="text/css">

</style>
</head>
<body>

<jsp:include page="../../components/header-navigation.jsp"></jsp:include>

<br/>
<div class="main-content mx500">
	<div class="toCenter">
		<div class="weazt-logo"></div><br/>
		<h1 class="fnor">Welcome Back</h1>
	</div>
	<br/><br/>
	<div class="blk-b">
		<div class="p10 modal-content">
			<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null }">
				<div class="blk-red blk-content">
					<div><h3 class="fnor"><strong><i class="fa tred fa-exclamation-triangle"></i>&nbsp;&nbsp;There was a problem</strong></h3></div>
					<p>Your email or password incorrect. Please try again.</p>
				</div>
				<br/><br/>
			</c:if>
			<form:form id="loginForm" method="post" modelAttribute="newUser">
				<div id="error"></div>
				<table class="p10">
					<tr>
						<td>
							<label for="log-e"><strong>Email</strong></label>
							<form:input path="email" id="log-e"  type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<label for="log-p"><strong>Password</strong></label>
							<form:input path="password" id="log-p" type="password" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="prefix-clear">
							<br/><br/>
							<input type="hidden" name="${_csr.parameterName}" value="${_csrf.token}" />
							<div><button class="btn flatGreenBtn c-100" type="submit"><strong>Login</strong></button></div>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
		<br/>
		<div class="blk-footer">
			<spring:url var="forgetpasswordLink" value="/forgot_password" htmlEscape="true" />
			<p class=""><a class="link" href="${forgetpasswordLink}">Forgot your password?</a></p>
		
		</div>
	</div>	
	<spring:url var="registerLink" value="/register" htmlEscape="true" />
	<p class="">Need a Weazt account?&nbsp;<a class="link" href="${registerLink}"><strong>Create an account&nbsp;&nbsp;<i class="fa fa-caret-right"></i></strong></a></p>
		
	<%-- <div class="toCenter">
		<br/><br/>
		<ul class="inline">
			<spring:url value="/about" var="aboutPage" htmlEscape="true" />
			<spring:url value="/privacy" var="privacyPage" htmlEscape="true" />
			<spring:url value="/tos" var="tosPage" htmlEscape="true" />
			<li><a class="pw10 link" href="${aboutPage}" target="_blank">About</a></li>
			<li><a class="pw10 link" href="${privacyPage}" target="_blank">Privacy</a></li>
			<li><a class="pw10 link" href="${tosPage}" target="_blank">Term of Services</a></li>
		</ul>
	</div>  --%>
</div>
<jsp:include page="../../components/footer-navigation.jsp"></jsp:include> 

<script type="text/javascript">
$(document).ready(function() {
	var emailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	
	$("#loginForm").on("submit", function(event) {
		var $this = $(this);
		var $email = $($this.find("input[name=email]"));
		var $password = $($this.find("input[name=password]"));

		var eFlag = emailRegex.test($email.val()); //strHandler($email) && validateEmail($email);
		var pFlag = $password.val().length >= 6 ? true : false; //strHandler($password) && minLengthHandler($password, 6);
		toggleErrorBox($email, eFlag);
		toggleErrorBox($password, pFlag);
		

		if(eFlag && pFlag) {
			$this.submit();
		} else {
			$this.find("#error").empty().append(
				"<div class=\"ph20\"><div class=\"blk-red p10\">"
				+	"<div><h3 class=\"fnor\"><i class=\"fa tred fa-exclamation-triangle\"></i>&nbsp;&nbsp;There was an error with your request</h3><p>Please complete the highlight fields</p></div>"
				+ "</div></div>").show();
		}
		
		event.preventDefault();
	});
});
</script>
</body>
</html>