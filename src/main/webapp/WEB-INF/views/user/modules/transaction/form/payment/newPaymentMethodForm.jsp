
<div id="paymentBlk" class="col checkoutBlks"  style="display: none">
	<div class="modal-header">
		<h3>New Payment Method</h3>
	</div>
	<spring:url value="/user/create-new-payment" var="newPaymentForm" htmlEscape="true" />
	<form id="newPaymentForm" action="${newPaymentForm }" method="POST">
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
	<div class="modal-footer prefix-clear">
		<button id="formSubmit" type="submit" class="btn toRight"><strong>Add Card</strong></button>
	</div>
	</form>
</div>


<script type="text/javascript">
$(document).ready(function() {

	$("#newPaymentForm").on("submit", function(event) {
		var $this = $(this);
		var $response = $this.find(".serverResponse");

		var $_name = $this.find("._name");
		var $_number = $this.find("._number");
		var $_expm = $this.find("._expm");
		var $_expy = $this.find("._expy");
		var $_cvv = $this.find("._cvv");
		
		var aflag = ($_name.val().length < 5) ? false : true;
		var nflag = Stripe.card.validateCardNumber($_number.val());
		var eflag = Stripe.card.validateExpiry($_expm.val(), $_expy.val()); 
		var cflag = Stripe.card.validateCVC($_cvv.val());
		
		hightlightError($_name, aflag);
		hightlightError($_number, nflag);
		hightlightError($_expm, eflag);
		hightlightError($_expy, eflag);
		hightlightError($_cvv, cflag);
		
		
		
		
		if(aflag && nflag && eflag && cflag) {
			loadingHandler($response);
	
			var stripeResponseHandler = function(status, response) {
		      	if (response.error) {
			    	generalError($response, response.error.message);  
		      	} else {
			        var token = response.id;
			        var name = response.card.name;
			        var last4 = response.card.last4;
			        var expm = response.card.exp_month;
			        var expy = response.card.exp_year;
					
			        $this.append($('<input type="hidden" name="_tokenId" />').val(token));
					var serializedData = $this.serialize();
				    var urlForm = $this.attr("action");
					$.ajax({
						url:urlForm,
						data: serializedData,
						method: "POST",
						success: function(data){
							if(data.state) {
								//success(name, last4, expm, expy);
								/* $response.empty();
						    	$_name.val("");
								$_number.val("");
								$_cvv.val("");
								$_expm.prop('selectedIndex',0);
								$_expy.prop('selectedIndex',0);
								
								$.fancybox.close(); */
								location.reload();
							} else {
								//formError("<div class=\"blk-red blk-content\"><p>Invalid shipping info. Unable to save your shipping info.</p></div>");
							}
						}, 
						error: function(data) {
						}
					});   
		      	}
		    };
		    
			function stripeHandler() {
				Stripe.setPublishableKey("${stripeKey}");
				Stripe.card.createToken({
					name: $_name.val(),
				 	number: $_number.val(),
				  	cvc: $_cvv.val(),
				  	exp_month: $_expm.val(),
				  	exp_year: $_expy.val(),
				  	currency: 'usd'
				}, stripeResponseHandler); 
			};
	

			stripeHandler();
		} else {
			//alert(aflag + "\n" + nflag + "\n" + eflag + "\n" + cflag);
			generalError($response, "Please make sure all fields are correct");
		}
		event.preventDefault();
	
	});
	
	/* 
	stripeHanlder();
	
	
	
	 */
	
	function hightlightError($a, f) {
		if(f) {
			$a.removeClass("error");
		} else {
			$a.addClass("error");
		}
	}
  


    function loadingHandler($a) {
    	var str = "<div class=\"blk-content blk-green\"><p><i class=\"fa fa-spin fa-spinner\"></i>&nbsp;&nbsp;Verifying ...</p></div>";
    	$a.html(str);	
    	$.fancybox.update();
    }
    
    
    function errorHandler() {
    	$("#response").empty().removeClass().addClass("blk-red").append("<div class='blk-content'>Unable to create your new payment.</div>");
    	$.fancybox.update();
    }
    
    function successHandler() {
    	$("#response").empty().removeClass().addClass("blk-green").append("<div class='blk-content'>Successfully add a new payment.</div>");
    	$.fancybox.update();
    }
    
    function generalError($a, error) {
    	var str = "<div class=\"blk-content blk-red\"><p>" + error + "</p></div>";
    	$a.html(str);	
    	$.fancybox.update();
    }
  
	
	
	
});
</script>