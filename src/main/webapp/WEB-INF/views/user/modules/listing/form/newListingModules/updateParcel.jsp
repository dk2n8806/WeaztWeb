<spring:url value="/user/listing/update-parcel" var="updateParcel" htmlEscape="true"></spring:url>
<form class="updateListingInfo" data-field="parcel"  action="${updateParcel}" method="post">
	<p><strong>Parcel's Weight</strong></p>
	<p>Enter new weight or new dimension of the item</p>
	<br/>
	<ul class="inline">
		<li>
			<label><strong>Weight</strong></label>
			<input name="_weight" type="text" />
		</li>
		<li>
			<label><strong>Unit</strong></label>
			 <select name="_mUnit">
				<option value="g">g</option>
				<option selected value="oz">oz</option>
				<option value="lb">lb</option>
				<option value="kg">kg</option>
			</select> 
		</li>
	</ul>
	<br/><hr/><br/>
	<ul class="inline">
		<li>
			<label><strong>length</strong></label>
			<input name="_length" type="text" />
		</li>
		<li>
			<label><strong>Width</strong></label>
			<input name="_width" type="text" />
		</li>
		<li>
			<label><strong>Height</strong></label>
			<input name="_height" type="text" />
		</li>
		<li>
			<label><strong>Unit</strong></label>
			<select name="_dUnit">
				<option value="cm">cm</option>
				<option selected value="in">in</option>
				<option value="ft">ft</option>
				<option value="nm">mm</option>
				<option value="m">m</option>
				<option value="yd">yd</option>
			</select>
		</li>
	</ul>
	<br/><br/>
	<input type="hidden" value="${product.id}" name="_pid">
	<button type="submit" class="btn plainBtn"><strong>Update</strong></button>
</form>