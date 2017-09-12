<spring:url value="/user/listing/update-rate" var="updateRate" htmlEscape="true"></spring:url>
<form class="updateListingInfo" data-field="rate"  action="${updateRate}" method="post">
	<p><strong>Subscription Rate</strong></p>
	<p>Update subscription rate will change the subscription price to new value.</p>
	<br/>
	<input class="c-20" type="text" name="rate">&nbsp;&nbsp;<span>%</span>
	<br/><br/>
	<input type="hidden" value="${product.id}" name="_pid">
	<button type="submit" class="btn plainBtn"><strong>Update</strong></button>
</form>