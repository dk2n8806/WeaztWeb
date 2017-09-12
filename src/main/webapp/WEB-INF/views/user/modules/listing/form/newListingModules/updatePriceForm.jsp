<spring:url value="/user/listing/update-price" var="updatePrice" htmlEscape="true"></spring:url>
					
<form class="updateListingInfo" data-field="price" action="${updatePrice}" method="post">
	<p><strong>Price</strong></p>
	<p>Update item's price will change the subscription price to new value.</p>
	<br/>
	<input name="price" type="text" />
	<br/><br/>
	<input type="hidden" value="${product.id}" name="_pid">
	<button type="submit" class="btn plainBtn"><strong>Update</strong></button>
	<!-- 
	<br/>
	<div>
		<p class="tip">*Update this field will not change the value subscriptions of your current subscribers.</p>
	</div>
	 -->
</form>