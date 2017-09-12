
<div id="deactiveQueue" class="hide">
	<div class="modal-header">
		<p><strong>Make Private</strong></p>
	</div>
	<div class="toCenter1">
		<div class="modal-content">
			<!-- 
			<p><strong>Allow only your subscribers to see this item</strong></p>
			<br/>
			 -->
			<p>You will not receive new subscribe requests from potential customers.
			   <br/>But, you still continue processing remaining orders.
			</p>
		</div>
		<div class="modal-footer">
			<spring:url value="/user/listing/deactive-request" var="deactiveProductForm" htmlEscape="true"/>
			<form id="deactiveListingFormRequest" data-status="deactive" class="productForms" method="POST" action="${deactiveProductForm }">
				<input type="hidden" name="_pid" value="${product.id}" />
				<button class="btn plainBtn"><strong>Private</strong></button>
			</form>
		</div>
	</div>
</div>



<script type="text/javascript">

$(document).ready(function() {
	
	$("#deactiveListingFormRequest").on("submit", function(event) {
 		var urlForm = $(this).attr("action");
		var serializedData = $(this).serialize();
		//var status = $(this).data("status");
 		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				$.fancybox.close();
				if(data.state) {
					$(".productStatusDisplay").html("<span class=\"\">Private&nbsp;&nbsp;<i class=\"fa fa-lock\"></i></span>");
				} else {
				}
			}, 
			error: function(data) {
			}
		});
		event.preventDefault();
	});
});
</script>