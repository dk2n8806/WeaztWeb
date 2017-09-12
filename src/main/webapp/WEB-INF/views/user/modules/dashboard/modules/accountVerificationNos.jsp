<c:set var="prinVerified"><sec:authentication property="principal.hasVerified"/></c:set>

<c:if test="${prinVerified == false }">
<div class="no">
	<spring:url var="verifiedLink" value="/user/setting/security" htmlEscape="true"/>
	<p>
		<span>We have send an email to verify your account. Please follow the instruction in the email. If you have not received verification email, please click <a class="link" href="${verifiedLink }">here</a> and we will send you a new one.</span>
	</p>
</div>
</c:if>