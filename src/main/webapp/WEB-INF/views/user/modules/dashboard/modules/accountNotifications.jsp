
<c:forEach var="nos" items="${accNos}">
	<c:if test="${nos.getClass().name == 'com.common.entity.notification.SubscribedNotification'}">
		<c:set var="sid" value="${nos.subscription.id }" />
		<spring:url var="subscriptionLink" value="/user/subscriptions/${sid}" htmlEscape="true" />
		<div class="no">
			<p>
				<a class="link toRight" href="${subscriptionLink }">View Details</a>
				<span class=""><i class="fa fa-retweet tgreen"></i></span>
				&nbsp;&nbsp;<span>Your subscription is being process</span>
			</p>
		</div>
	</c:if>
	<c:if test="${nos.getClass().name == 'com.common.entity.notification.UnsubscribedNotification'}">
		<c:set var="sid" value="${nos.subscription.id }" />
		<div class="no">
			<p>
				<span class=""><i class="fa fa-times torange"></i></span>
				&nbsp;&nbsp;<span>A subscription has been unsubscribed</span>
			</p>
		</div>
	</c:if>
	<c:if test="${nos.getClass().name == 'com.common.entity.notification.AccountVerificationNotification'}">
		<div class="no">
			<spring:url var="verifiedLink" value="/user/setting/verification" htmlEscape="true"/>
			<p>
				<span>We have send an email to verify your account. Please follow the instruction in the email. If you have not receive verification email, please click <a class="link" href="${verifiedLink }">here</a> and we will send you a new one.</span>
			</p>
		</div>
	</c:if>
</c:forEach>


