<spring:url value="/user/listing/update-description" var="updateDescription" htmlEscape="true"></spring:url>
<form class="updateListingInfo" data-field="description"  action="${updateDescription}" method="post">
	<p><strong>Description</strong></p>
	<p>Feed us more details about this item's finest features</p>
	<br/>
	<textarea rows="" cols="" name="description"></textarea>
	<br/><br/>
	<input type="hidden" value="${product.id}" name="_pid">
	<button type="submit" class="btn plainBtn"><strong>Update</strong></button>
</form>