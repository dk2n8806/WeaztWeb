<c:set var="productId" value="${product.id}" />
<%-- 
<c:set var="max" value="${product.subscriptionInfo.maxSubscribe}" />
 --%>
<c:set var="shipment" value="${product.subscriptionInfo.nos}" />
<c:set var="save">
	<fmt:formatNumber value="${product.subscriptionInfo.percentSave / 100}" maxFractionDigits="2"/>
</c:set>
<c:set var="frequency">
	<fmt:formatNumber  value="${product.subscriptionInfo.frequency / 7}" maxFractionDigits="0" />
</c:set>


<spring:url var="checkoutForm" value="/user/request/checkout-product" htmlEscape="true" />

<div class="blk">
	<div class="blk-content">
		<h2 class="fnor">
			<span class="bhi "><i class="fa fa-retweet"></i></span>
			<span>Subscription Option</span>
		</h2>
	</div>
	<form id="checkoutForm" method="POST" action="${checkoutForm}">
	<div class="modal-content">				
		<div class="response"></div>
		<table>
			<tr>
				<td>
					<input type="hidden" value=${productId } name="_pid"/>
					<input id="promoCode" type="hidden" value="" name="promo"/>
				</td>
			</tr>
			<tr>
				<td class="c-40">
					<p><strong>Delivery frequency</strong></p>
					<p>When do you want this product deliver to your font door again?</p>
					<p id="common" data-freq="${frequency }"></p>
				</td>
				<td class="c-60">
					<div class="pw20">
						<%-- 
						<span class="fx1-1">every</span>
						<input class="mx100 " type="text" name="_freq" value="${frequency }">
						<span class="fx1-1">days</span>
						 --%>
						<select name="_freq">
							<option value="0">Select ...</option>
							<option value="7">Weekly</option>
							<option value="14">Every 2 weeks</option>
							<option value="21">Every 3 weeks</option>
							<option value="28">Every 4 weeks</option>
							<option value="35">Every 5 weeks</option>
							<option value="42">Every 6 weeks</option>
							<option value="49">Every 7 weeks</option>
							<option value="56">Every 8 weeks</option>
						</select>
					</div>
				</td>
			</tr>
			<tr><td></td></tr>
			<tr><td></td></tr>
			<tr>
				<td>
					<p><strong>Number of shipments</strong></p>
					<p>Select number of shipments you want subscribe to</p>
				</td>
				<td>
					<div class="pw10">
						<select id="numberOfSubscription" style="width: 130px;" name="_nos" data-shipment="${shipment }">
							<!-- <option value="2">2 shipments</option>
							<option value="3">3 shipments</option>
							<option value="4">4 shipments</option>
							<option value="5">5 shipments</option>
							<option value="6">6 shipments</option>
							<option value="7">7 shipments</option>
							<option value="8">8 shipments</option>
							<option value="9">9 shipments</option> -->
							<script type="text/javascript">
							$(document).ready(function() {
								var buildOptions = function() {
									var $b = $("#numberOfSubscription");
									var max = parseFloat($b.data("shipment")) | 0;
									var i = 2;
									while(i <= 9) {
										if(i == max) {
											$b.append("<option selected value=\"" + i + "\" class=\"\">" + i  + " shipments</option>");			
										} else {
											$b.append("<option value=\"" + i + "\" class=\"\">" + i  + " shipments</option>");			
										}
										i++;
									}	
								}; buildOptions();
								
							});
							</script>
						</select>
						<span id="tracker"></span>
						 <h3 class="ftrend">Select <strong><span class="torange">${shipment} shipments</span></strong> or more and get <strong class="torange">${save}% discount</strong></h3>
					</div>
				</td>
				
			</tr>
		</table>
	</div>
	<br/>
	<div class="modal-footer prefix-clear">
		<div>
			<button class="btn p10 c-100 flatGreenBtn" type="submit" ><strong>Send Your Request&nbsp;&nbsp;<i class="fa fa-paper-plane-o"></i></strong></button>
		</div>
	</div>
	
	</form>
</div>

 
 
<script type="text/javascript">
$(document).ready(function() {
	
	var $checkoutForm = $("#checkoutForm");
	$checkoutForm.on("submit", function(event) {
		var $this = $(this);
		var $_freq = $this.find("select[name=_freq]");
		var $_nos = $this.find("select[name=_nos]");
		var $_response = $this.find(".response");

		var $btn = $this.find("button[type=submit]");
		
		var freq = parseInt($_freq.val());
		var nos = parseInt($_nos.val());
		
		var aflag = ($.isNumeric(freq) &&  (freq >= 7));
		var bflag = (nos > 0);
		if(freq < 7) {
			$(".freqError").show();
		} else {
			$(".freqError").hide();
		} 
		highLightError($_freq, aflag);
		highLightError($_nos, bflag);
		

		if(aflag && bflag) {
			$btn.prop("disabled", true);
			$btn.html("<strong><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;Sending Request...&nbsp;&nbsp;<i class=\"fa fa-paper-plane-o\"></i></strong>");
			
			var serializedData = $this.serialize();
		    var urlForm = $this.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				success: function(data){
					if(data.state) {
						window.location = "${pageContext.request.contextPath}" + data.result;
					} else {
						$_response.html("<div class=\"blk-red blk-content\"><p><i class=\"fa tred fa-exclamation-triangle\"></i>&nbsp;&nbsp;" + data.result + "</p></div>");
					}
				}, 
				error: function(data) {
				},
				complete: function(data) {
					$btn.html("<strong>Send Request&nbsp;&nbsp;<i class=\"fa fa-paper-plane-o\"></i></strong>");
					$btn.prop("disabled", false);
		        }
			}); 
		}
			event.preventDefault();
	});
	
	function highLightError($a, a) {
		if(a) {
			$a.removeClass("error");
		} else {
			$a.addClass("error");
		}
	}
});
</script>