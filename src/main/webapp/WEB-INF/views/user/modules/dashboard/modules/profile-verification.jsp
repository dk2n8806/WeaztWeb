
<c:set var="prinVerified"><sec:authentication property="principal.hasVerified"/></c:set>
<div class="blk-b">
	<div class="panel-header">
		<h3>Verifications</h3>
	</div>
	<div class="blk-content">
		<div class="ph10">
		<c:choose>
			<c:when test="${prinVerified == false }">
				<spring:url value="/user/setting/verification" var="verifyLink" htmlEscape="true"></spring:url>
				<p class="toCenter"><a class="link" href="${verifyLink}">Add verification</a></p>
			</c:when>	
			<c:otherwise>
				<p class="tgreen"><span class="bhi tgreen"><i class="fa fa-check"></i></span>Verified email</p>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
</div>