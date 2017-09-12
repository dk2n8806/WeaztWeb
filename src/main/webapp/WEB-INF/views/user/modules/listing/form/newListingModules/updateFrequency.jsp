<spring:url value="/user/listing/update-frequency" var="updateFrequency" htmlEscape="true"></spring:url>
<form class="updateListingInfo" data-field="frequency"  action="${updateFrequency}" method="post">
	<p><strong>Frequency</strong></p>
	<p>How frequently do you ship this item again to your customers?</p>
	<br/>
	<select name="frequency">
		<option value="0">Select...</option>
		<option value="7">Weekly</option>
		<option value="14">Every 2 weeks</option>
		<option value="21">Every 3 weeks</option>
		<option value="28">Every 4 weeks</option>
		<option value="35">Every 5 weeks</option>
		<option value="42">Every 6 weeks</option>
		<option value="49">Every 7 weeks</option>
		<option value="56">Every 8 weeks</option>
	</select>
	<br/><br/>
	<input type="hidden" value="${product.id}" name="_pid">
	<button type="submit" class="btn plainBtn"><strong>Update</strong></button>
</form>