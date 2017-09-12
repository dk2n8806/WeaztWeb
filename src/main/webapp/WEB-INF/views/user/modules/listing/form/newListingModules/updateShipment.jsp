<spring:url value="/user/listing/update-shipment" var="updateShipment" htmlEscape="true"></spring:url>
<form class="updateListingInfo" data-field="shipment"  action="${updateShipment}" method="post">
	<p><strong>Number of Shipment</strong></p>
	<p>What is the minimum number of shipment that your customers need to select to qualify subscription rate?</p>
	<br/>
	<select name="nos">
		<option value="0">Select ...</option>
		<option value="2">2 shipments</option>
		<option value="3">3 shipments</option>
		<option value="4">4 shipments</option>
		<option value="5">5 shipments</option>
		<option value="6">6 shipments</option>
		<option value="7">7 shipments</option>
		<option value="8">8 shipments</option>
		<option value="9">9 shipments</option>
	</select>
	<br/><br/>
	<input type="hidden" value="${product.id}" name="_pid">
	<button type="submit" class="btn plainBtn"><strong>Update</strong></button>
</form>