<div class="blk-b">
	<div class="blk-content">
		<p><strong>Sales Tax</strong></p>
		<p>The Amazon Payments Manage Taxes feature provides you with a means to determine and apply sales tax to your transactions. With the Manage Taxes feature, you can specify, on a state-by-state basis, the tax rate to apply on purchases that are delivered to that state. This feature also allows you to choose whether to apply sales tax to shipping and handling.

The tax rate that you specify will be applied to purchases at checkout and is determined based on the state to which the shipment will be sent.</p>
		<c:set var="taxRate">
			<fmt:formatNumber  value="${taxRate.taxRate / 100}" maxFractionDigits="2"/>
		</c:set>
		<spring:url value="/user/update-merchant-tax" var="taxForm" htmlEscape="true"></spring:url>
		<form id="taxForm" method="POST" action="${taxForm }">
		<div class="response"></div>
		<div class="modal-content">
			<ul>
				<li>
					<input value="${taxRate }" name="_rate" type="text"/>
				</li>
				<li>
					<button type="submit" class="btn"><strong>Update</strong></button>
				</li>
			</ul>
		</div>
		</form>
	</div>
</div>


<script type="text/javascript">
$(document).ready(function() {

	
	$("#taxForm").on("submit", function(event){
		var serializedData = $(this).serialize();
		var urlForm = $(this).attr("action");
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					alert("good");
				} else {
					alert("fail");
				}
			}, 
			error: function(data) {
				alert("something wrong");
			}
		});	  
		event.preventDefault();
	});

	;function showConfirm($displayBlk, msg) {
		$displayBlk.empty().removeClass();
		$displayBlk.addClass("blk-green")
					.addClass("p10")
					.append("<p>").append(msg).append("</p>");
		$displayBlk.show();
	}
	;function showError($displayBlk, flag, err) {
		$displayBlk.empty().removeClass();
		
		if(flag) {
			$displayBlk.hide();
		} else {
			$displayBlk.addClass("blk-red").addClass("p10");
			for(var i = 0, l = err.length; i < l; i++ ) {
			    $displayBlk.append("<p>").append("<i class=\"fa tred fa-exclamation-triangle\"></i>&nbsp;&nbsp;").append(err[i]).append("</p>");
			}
			$displayBlk.show();
		}
	}
	
});
</script>