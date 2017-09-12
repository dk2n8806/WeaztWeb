<spring:url var="generalFAQsLink" value="/help" htmlEscape="true" />
<spring:url var="accountFAQsLink" value="/help/account-FAQs" htmlEscape="true" />
<spring:url var="subscriberFAQsLink" value="/help/subscriber-FAQs" htmlEscape="true" />
<spring:url var="sellerFAQsLink" value="/help/seller-FAQs" htmlEscape="true" />

<div class="col-wrapper helpNav">
	<div class="col-2">
		<div class="blk">
			<a class="btn" href="${generalFAQsLink }">
				<strong>General Questions</strong>
				<br/>
				<span class="tgray">5 questions</span>
			</a>
		</div>
	</div>
	<div class="col-2">
		<div class="blk">
			<a class="btn" href="${accountFAQsLink }">
				<strong>Account</strong>
				<br/>
				<span class="tgray">7 questions</span>
			</a>
		</div>
	</div>
	<div class="col-2">
		<div class="blk">
			<a class="btn" href="${subscriberFAQsLink }">
				<strong>Subscriber Guidelines</strong>
				<br/>
				<span class="tgray">9 questions</span>
			</a>
		</div>
	</div>
	<div class="col-2">
		<div class="blk">
			<a class="btn" href="${sellerFAQsLink }">
				<strong>Seller Guidelines</strong>
				<br/>
				<span class="tgray">15 questions</span>
			</a>
		</div>
	</div>
</div>