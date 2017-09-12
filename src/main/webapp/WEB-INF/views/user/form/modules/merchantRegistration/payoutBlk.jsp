<div class="breadcrumb-wrapper p20 main-container">
	<div class="twhite">
		<div class="prefix-clear">
			<div class="breadcrumb toRight flatBlue">
				<a class="active"><i class="fa fa-building-o"></i>&nbsp;&nbsp;Business</a>
				<a class="active"><i class="fa fa-credit-card"></i>&nbsp;&nbsp;Payment</a>
			</div>
			<h2 class="ftrend">Payment Info</h2>
			<p class="tgray">Let us know how to deposite your payouts.</p>
		</div>
	</div>
</div>
<div class="blk-b"> 	
	<br/>
	<div class="blk-content">		
		<table class="p10">
			<tr>
				<td colspan="4">
					<label><strong>Name on the card</strong></label>
					<div>
						<input type="text" class="cname"/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<label><strong>Debit Card Number</strong></label>
					<div>
						<input type="text" class="cnumber"/>
					</div>
				</td>
			</tr>
			<tr>
				<td class="c-30">
					<label><strong>CVV</strong></label>
					<div>
						<input class="cvv" type="text"/>
					</div>
				</td>
				<td class="c-30">
					<label><strong>Exp Month</strong></label>
					<select class="expm c-100">
						<option value="0">Expiration Month</option>
						<option value="1">1  - January</option>
						<option value="2">2  - February</option>
						<option value="3">3  - March</option>
						<option value="4">4  - April</option>
						<option value="5">5  - May</option>
						<option value="6">6  - June</option>
						<option value="7">7  - Junly</option>
						<option value="8">8  - August</option>
						<option value="9">9  - September</option>
						<option value="10">10 - October</option>
						<option value="11">11 - November</option>
						<option value="12">12 - December</option>
					</select>
				</td>
	
				<td class="c-30">
					<label><strong>Exp Year</strong></label>
					<select id="expy" class="expy c-100">
						<option value="0">Expiration Year</option>
						<script type="text/javascript">
						$(document).ready(function() {
							var year = new Date().getFullYear();
							var i = 0;
							while(i < 10) {
								$("#expy").append("<option>" + (year + i) + "</option>");
								i++;
							}
						});
						</script>
					</select>
				</td>
			</tr>
		</table>						
		<br/>		
		<br/>
		<br/>
		<div class="col-wrapper">
			<div class="col-2">
				<div class="blk">
					<button class="merchantBtn p10  backBtn c-100 plainBtn btn"><strong><i class="fa fa-long-arrow-left"></i>&nbsp;Back</strong></button>
				</div>
			</div>
			<div class="col-2">
				<div class="blk">
					<button type="submit" class="merchantBtn p10 c-100 blueBtn btn"><strong>Submit</strong></button>
				</div>
			</div>
			<div class="col">
				<br/>
				<div class="blk toCenter">
					<spring:url value="/tos" var="merchantTerm" htmlEscape="true" />
					<p>By submit the form you have read and agreed to the terms of the <a class="link" href="${merchantTerm }" target="_blank">Weazt Merchant</a></p>
				</div>
			</div>
		</div>
	</div>
</div>