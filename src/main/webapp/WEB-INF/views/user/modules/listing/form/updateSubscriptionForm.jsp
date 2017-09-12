	
<c:set var="productPrice">
	<fmt:formatNumber maxFractionDigits="2" value="${product.basicInfo.price / 100}" minFractionDigits="2" />
</c:set>
<c:set value="${product.subscriptionInfo.minSubscribe }" var="min" />
<c:set value="${product.subscriptionInfo.maxSubscribe }" var="max" />
<c:set value="${product.subscriptionInfo.frequency }" var="frequency" />
<c:set value="${fn:toLowerCase(product.subscriptionInfo.saveOption)}" var="saveType" />
<c:set var="saveRate" >
	<fmt:formatNumber maxFractionDigits="2"
						value="${product.subscriptionInfo.percentSave / 100}" /> 
</c:set>
<c:set var="subscribePrice" >
	<fmt:formatNumber maxFractionDigits="2" value="${productPrice - (productPrice * saveRate / 100)}"/>
</c:set>

<c:set value="${product.subscriptionInfo.frequency }" var="frequency" />
<c:set var="shippingCost">
	<fmt:formatNumber value="${product.shippingInfo.shippingCost / 100}" minFractionDigits="2" maxFractionDigits="2"/>
</c:set>

<div id="updateSubscriptionForm" class="hide">
	<div class="modal-header">
		<p>Update Subscription Info</p>
	</div>
	<spring:url value="/user/listing/update-subscription-info" var="updateSubscriptionForm" htmlEscape="true"/>
	<form id="updateSubscriptionForm" class="productForms" method="POST" action="${updateSubscriptionForm}">
		<div class="modal-content">
			<table class="p10">
				<tr>
					<td class="c-30">
						<label><strong>Save Rate</strong></label>
					</td>
					<td class="c-70">
						<ul class="inline">
							<li>
								<input placeholder="Save rate" value="${saveRate }" class="mx100" name="_rate" type="text">
								<span>%</span>
								<p class="tip">Enter the rate you will offer to the customer for each subscription.</p>
							</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td>
						<label><strong>Shipments</strong></label>
					</td>
					<td>
						<div class="inline">
							<ul>
								<li>
									<input placeholder="Min" class="mx100" value="${min }" name="_min" type="text">
								</li>
								<li>&nbsp;</li>
								<li>
									<input placeholder="Max" class="mx100" value="${max }" name="_max" type="text">
								</li>
							</ul>
						</div>
						<p class="tip">Enter the minimum and maximum subscriptions your customer will be able to subscribe. Read more about <a class="link" href="">"Weazt's Subscription Method"</a></p>
					</td>
				</tr>
				<tr>
					<td><label><strong>Frequency</strong></label></td>
					<td>
						<select class="selectFormat" data-select=${frequency } name="_freq" >
							<option value="0">Select</option>
							<option value="7">Weekly (7 days)</option>
							<option value="15">Bi-monthly (15 days)</option>
							<option value="30">Monthly (30 days)</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label><strong>Shipping rates</strong></label></td>
					<td>
						<span>$</span>
						<input placeholder="0.00" value="${shippingCost }" class="mx100" name="_shipping" type="text">
						<span>/ shipment</span>
						<p class="tip">Flat rate </p>
					</td>
				</tr>
			</table>			
		</div>
		<div class="modal-footer prefix-clear">
			<input type="hidden" name="_pid" value="${productId}" />
			<button class="toRight plainBtn btn"><strong>Update</strong></button>
		</div>
	</form>
</div>




<script type="text/javascript">
$(document).ready(function() {
	$("#updateSubscriptionForm").on("submit", function(event){
		
		var flag = basicHandler($(this));
		if(flag) {
			$(this).submit();
		}
		event.preventDefault();
	});
	

	
	function basicHandler($a) {
		var $_save = $a.find("input[name=_rate]");
		var $_min = $a.find("input[name=_min]");
		var $_max = $a.find("input[name=_max]");
		var $_freq = $a.find("select[name=_freq]");
		var $_cost = $a.find("input[name=_shipping]");

		var _save = $_save.val();
		var _min = $_min.val();
		var _max = $_max.val();
		var _freq = $_freq.val();
		var _cost = $_cost.val();
		
		var fsave = ((parseFloat(_save) | 0) >= 0 && $.isNumeric(_save) ) ? true : false;
		var fmin = (_min % 1 === 0);
		var fmax = (_max % 1 === 0);
		var fcom = _max >= _min ? true : false;
		var ffreq = (parseFloat(_freq) | 0) > 3 && $.isNumeric(_freq) ? true : false;
		var fcost = (parseFloat(_cost) | 0) >= 0 && $.isNumeric(_cost) ? true : false;
		
		toggleError($_save, fsave);
		toggleError($_min, fmin);
		toggleError($_max, fmax);
		toggleError($_freq, ffreq);
		toggleError($_cost, fcost);
		
		if(!fcom) {
			toggleError($_min, false);
			toggleError($_max, false);	
		}
		
		return fsave && fmin && fmax && fcom && ffreq && fcost;
	}
	

	
	function toggleError($a, a) {
		if(a) {$a.removeClass("error");}
		else {$a.addClass("error");}
	}
	
	

	
});
</script>
