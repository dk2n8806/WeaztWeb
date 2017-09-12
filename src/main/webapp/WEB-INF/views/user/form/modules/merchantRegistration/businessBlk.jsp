
<div class="modal-header">
		<h1 class="fnor">Let's Work together</h1>
</div>
<div class="modal-content">					
	<div class="form-error hide ph20 blk-red">
		<p><i class="fa fa-exclamation-circle"></i>&nbsp;&nbsp;Please let us know more about your business by complete the highlight fields below.</p>
	</div>
	<div>
		<table>
			<tr>
				<td class="c-30"><label><strong>Business name</strong></label></td>
				<td class="c-70"><input class="businessName" name="_bname" type="text"/></td>
			</tr>
			<tr>
				<td><label><strong>Business Address</strong></label></td>
				<td><input class="businessAddress" name="_baddress" type="text" /></td>
			</tr>
			<tr>
				<td><label><strong>City</strong></label></td>
				<td><input name="_bcity" type="text" /></td>
			</tr>
			<tr>
				<td><label><strong>State</strong></label></td>
				<td>
					<select name="_bstate" class="c-100">
						<jsp:include page="../../../../global/modules/address.jsp"></jsp:include>
					</select>
				</td>
			</tr>
			<tr>
				<td><label><strong>ZIP Code</strong></label></td>
				<td><input class="businessZipcode" name="_bzipcode" type="text" /></td>
			</tr>
			<tr>
				<td><label><strong>Phone</strong></label></td>
				<td><input name="_bphone" type="text"/></td>
			</tr>
		</table>
	</div>
</div>
<div class="modal-footer">
	<button type="submit" class="btn c-100 flatGreenBtn">Submit</button>
</div>