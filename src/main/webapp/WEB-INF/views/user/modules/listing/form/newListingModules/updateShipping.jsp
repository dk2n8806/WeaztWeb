<spring:url value="/user/listing/update-shipping" var="updateShipping" htmlEscape="true"></spring:url>
<form class="updateListingInfo" data-field="shipping"  action="${updateShipping}" method="post">
	<p><strong>Shipping</strong></p>
	<p>Enter new shipping option you want to provide to your new subscribers</p>
	<br/>
	<div class="p10">
		<input style="float: left; margin-right: 10px;" id="freeType" type="radio" value="free" name="_type" />
		<label   for="freeType"><strong>Free Shipping</strong></label>
		<p class="tip">Provide free of charge on shipping</p>
	</div>
	<div class="p10">
		<input style="float: left; margin-right: 10px;" id="autoType" type="radio" value="auto" name="_type" />
		<label for="autoType"><strong>Automatical calculate</strong></label>
		<p class="tip">Allow Weazt to auto calculate shipping cost at checkout</p>
	</div>
	<div class="p10">
		<input  style="float: left; margin-right: 10px;" id="flatType" type="radio" value="flat" name="_type" />
		<label for="flatType"><strong>Flat rate shipping</strong></label>
		<p class="tip">The specified flat rate that will always be charged for shipping the item</p>
		<br/>
		<div>
			<span>$</span>
			<input placeholder="0.00" class="mx100" name="_cost" type="text">
			<span>/ shipment</span>
		</div>
	</div>
	<br/>
	<input type="hidden" value="${product.id}" name="_pid">
	<button type="submit" class="btn plainBtn"><strong>Update</strong></button>
</form>