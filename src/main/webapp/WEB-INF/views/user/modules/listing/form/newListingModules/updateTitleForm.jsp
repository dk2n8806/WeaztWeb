<spring:url value="/user/listing/update-title" var="updateTitle" htmlEscape="true"></spring:url>
<form class="updateListingInfo" data-field="title"  action="${updateTitle }" method="post">
	<p><strong>Title</strong></p>
	<p>Enter new title that you think people will use in search for this item</p>
	<br/>
	<input name="title" type="text" />
	<br/><br/>
	<input type="hidden" value="${product.id}" name="_pid">
	<button type="submit" class="btn plainBtn"><strong>Update</strong></button>
</form>