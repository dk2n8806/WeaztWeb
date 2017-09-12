<spring:url var="accountSetting" 		value="/user/setting/account" htmlEscape="true" />
<spring:url var="profileSetting" 			value="/user/setting/profile" htmlEscape="true" />
<%-- <spring:url var="verificationSetting" 	value="/user/setting/verification" htmlEscape="true" /> --%>
<spring:url var="passwordSetting" 		value="/user/setting/security" htmlEscape="true" />
<spring:url var="merchantSetting" 		value="/user/setting/merchant" htmlEscape="true" />
<spring:url var="payoutNavLink" 			value="/user/payout" htmlEscape="true" />
<spring:url var="notificationNavLink" 	value="/user/notification" htmlEscape="true" />
<spring:url var="paymentNavLink" 		value="/user/payment" htmlEscape="true" />
<spring:url var="payoutNavLink" 			value="/user/payout" htmlEscape="true" />
<spring:url var="bookmarkNavLink" 	value="/user/bookmark" htmlEscape="true" />



<div class="blk">
	<ul id="settingNav" class="command-menu">
		<li id="settingAccountNav">
			<h4><a href="${accountSetting }">Account</a></h4>
		</li>
		<li id="settingProfileNav">
			<h4><a href="${profileSetting }">Private Profile</a></h4>
		</li>
		<li id="settingMerchantNav">
			<h4><a href="${merchantSetting }">My Business</a></h4>
		</li>
		<li id="settingPasswordNav">
			<h4><a href="${passwordSetting }">Password & Verification</a></h4>
		</li>
		<li id="bookmarkNav">
			<h4><a href="${bookmarkNavLink}">My Bookmarks</a></h4>
		</li>
		<%-- <li id="settingVerificationNav">
			<h4><a href="${verificationSetting }">Verification</a></h4>
		</li> --%>
		<%-- 
		<li id="settingNotificationNav">
			<h4><a href="${notificationNavLink}">Notifications</a></h4>
		</li>
		 --%>
		<li id="settingPaymentNav">
			<h4><a href="${paymentNavLink}">Payment Methods</a></h4>
		</li>
		<li id="settingPayoutNav">
			<h4><a href="${payoutNavLink}">Payout Preferences</a></h4>
		</li>
	</ul>
</div>




<script type="text/javascript">
$(document).ready(function() {
	
	$("#dbSettingNav").addClass("active");
});
</script>