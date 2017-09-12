<div class="blk">
	<div class="blk-content">	
		<div>
			<a class="toRight fancy-btn blk-s-btn" href="#updatePaymentBlk"><i class="fa fa-plus"></i>&nbsp;&nbsp;Change</a>
			<h2 class="fnor">
				<span class="bhi "><i class="fa fa-credit-card"></i></span>
				<span>Payment Method</span>
			</h2>
		</div>
		<p>Your account will not be charged only when the seller accepts your subscription request.</p>
		<p>If the seller declines your subscription request, no charge is made.</p>
		<br/><br/>
		<div class="blkPayment">
		<c:choose>
			<c:when test="${empty creditcard}">
				<div class="toCenter">
					<a class="btn plainBtn fancy-btn" href="#updatePaymentBlk">
						<i class="fa fa-pencil"></i>&nbsp;&nbsp;
						<span>Create new payment</span>
					</a>
				</div>
			</c:when>
			<c:otherwise>
				<c:set var="card" value="${creditcard.customerAdapter.cardAdapter }" />
				
				<c:set var="nameOnCard" value="${card.nameOnCard }" />
				<c:set var="last4" value="${card.last4 }" />
				<c:set var="expm" value="${card.expMonth }" />
				<c:set var="expy" value="${card.expYear }" />
				<!--
				 <p><strong>Default payment</strong></p>
 				<br/>
 				 -->
	 			<table class="overviewListing" style="width: 100%;">
	 				<tr>
	 					<td class="c-30">
	 						<p class="overviewListingTitle">Name on card</p>
							<h4 class="overviewListingStats">${nameOnCard }</h4>
	 					</td>
	 					<td class="c-30">
	 						<p class="overviewListingTitle">Last 4 digits</p>
						<h4 class="overviewListingStats">${last4}</h4>
	 					</td>
	 					<td>
	 						<p class="overviewListingTitle">Expiry Date</p>
						<h4 class="overviewListingStats">${expm} / ${expy }</h4>
	 					</td>
	 				</tr>
	 			</table>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
</div>

<div id="updatePaymentBlk" style="display: none">
	<spring:url value="/user/checkout/update-payment" var="updatePaymentForm" htmlEscape="true"/>
	<form id="updatePaymentForm" action="${updatePaymentForm}" method="POST">
	<div class="modal-header"><p>Update payment info</p></div>
	<div class="serverResponse"></div>
	<div class="modal-content">
		<table>
			<tr>
				<td colspan="4">
					<label><strong>Name on card</strong></label>
					<div class="input-txt">
						<input type="text" class="_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<label><strong>Card number</strong></label>
					<div class="input-txt">
						<input type="text" class="_number"/>
					</div>
				</td>
			</tr>
			<tr>
				<td class="c-30">
					<label><strong>CVV</strong></label>
					<div class="input-txt">
						<input class="_cvv" type="text"/>
					</div>
				</td>
				<td class="c-30">
					<label><strong>Exp Month</strong></label>
					<select class="_expm c-100">
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
					<select id="expy" class="_expy c-100">
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
	</div>
	<div class="prefix-clear modal-footer">
		<button type="submit" class="btn plainBtn"><strong>Update</strong></button>
	</div>
	</form>
</div>


<script type="text/javascript">
$(document).ready(function() {
	
	var $_paymentForm = $("#updatePaymentForm");
	var $_name = $_paymentForm.find("._name");
	var $_number = $_paymentForm.find("._number");
	var $_expm = $_paymentForm.find("._expm");
	var $_expy = $_paymentForm.find("._expy");
	var $_cvv = $_paymentForm.find("._cvv");
	var $response= $("#updatePaymentBlk .serverResponse");
	var $pBtn = $_paymentForm.find("button[type=submit]");
	
	
	$_paymentForm.on("submit", function(event) {
		var a = $_name.val().length > 5;
		var b = $_number.val().length == 16;
		var c = $_expm.val() != 0;
		var d = $_expy.val() != 0;
		var e = $_cvv.val().length == 3;

		displayError($_name, a);
		
		displayError($_number, b);
		displayError($_expm, c);
		displayError($_expy, d);
		displayError($_cvv, e);
		if(a && b && c && d && e) {
			$response.empty();
			stripeHandler();	
		} else {
			$response.html("<div class=\"blk-content blk-red\"><p>Please fill out missing fields</p></div>"); 
		} 
		event.preventDefault();
	});
	
	function displayError($a, a) {
		if(a) {
			$a.removeClass("error");
		} else {
			$a.addClass("error");
		}
	}
	function stripeHandler() {
		Stripe.setPublishableKey("${stripeKey}");
		Stripe.card.createToken({
			name: $_name.val(),
		 	number: $_number.val(),
		  	cvc: $_cvv.val(),
		  	exp_month: $_expm.val(),
		  	exp_year: $_expy.val()
		}, stripeResponseHandler);
	};
	
	var stripeResponseHandler = function(status, response) {
      	if (response.error) {
	    	$response.html("<div class=\"blk-content blk-red\"><p>" + response.error.message + "</p></div>"); 
	       	//$form.find("button[type=submit]").prop("disabled", false);
	      } else {
	        var token = response.id;
	        var name = response.card.name;
	        var last4 = response.card.last4;
	        var expm = response.card.exp_month;
	        var expy = response.card.exp_year;
			$_paymentForm.append($('<input type="hidden" name="_tokenId" />').val(token));

			$pBtn.prop("disabled", true);
			$pBtn.html("<strong><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;Updating</strong>");
			
			var serializedData = $_paymentForm.serialize();
		    var urlForm = $_paymentForm.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				success: function(data){
					if(data.state) {
						success(name, last4, expm, expy);
						
						
						$response.empty();
				    	$_name.val("");
						$_number.val("");
						$_cvv.val("");
						$_expm.prop('selectedIndex',0);
						$_expy.prop('selectedIndex',0);
						
						$.fancybox.close();
					} else {
						//formError("<div class=\"blk-red blk-content\"><p>Invalid shipping info. Unable to save your shipping info.</p></div>");
					}
				}, 
				error: function(data) {
				},
				complete:function(data) {
					$pBtn.prop("disabled", false);
					$pBtn.html("<strong>Update</strong>");
				}
			});   
	    }
      	
      	
      	
      
      	function success(name, last4, expm, expy) {
    		var str = "" //"<p><strong>Default Payment</strong></p><br/>"
    			+ 	"<table class=\"overviewListing\" style=\"width: 100%;\">"
    			+ 		"<tr>"
    			+			"<td class=\"c-30\">"
    			+ 				"<p class=\"overviewListingTitle\">Name on card</p>"
    			+ 				"<h4 class=\"overviewListingStats\">" + name + "</h4>"
    			+			"</td>"
    			+			"<td class=\"c-30\">"
    			+ 				"<p class=\"overviewListingTitle\">Last 4 digits</p>"
    			+ 				"<h4 class=\"overviewListingStats\">" 
    			+ 					"<span>"+ last4 +"</span>"
    			+ 				"</h4>"
    			+			"</td>"
    			+			"<td class=\"\">"
    			+ 				"<p class=\"overviewListingTitle\">Expiry Date</p>"
    			+ 				"<h4 class=\"overviewListingStats\">" 
    			+ 					"<span>"+ expm + " / " + expy +"</span>"
    			+ 				"</h4>"
    			+			"</td>"
    			+ 		"</tr>"
    			+ 	  "</table>"
    			; 
    			 
    		$(".blkPayment").html(str);
    	}; 
    	
    };
});
</script>