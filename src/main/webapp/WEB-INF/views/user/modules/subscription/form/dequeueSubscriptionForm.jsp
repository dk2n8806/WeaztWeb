

<div id="unsubscribe" style="display: none">
	<div class="modal-header">
		<p><strong>Request to cancel</strong></p>
	</div>					
	<div class="modal-content">
		<p><strong>Are you sure want to unsubscribe this item?</strong></p>
		<br/>
		<p>Once this product is unsubscribed, you will not receive any shipments about this product in the future. We will terminate your remaining shipments and calculate your refund.</p>
	</div>
	<div class="modal-footer">
		<spring:url value="/user/subscription/generate-unsubscribe-token" var="requestUnsubscribeTokenForm" htmlEscape="true" />
		<form id="requestUnsubscribeTokenForm" action="${requestUnsubscribeTokenForm}" method="POST">
			<input type="hidden" name="_sid" value="${subscription.id }">
			<button class="btn plainBtn" type="submit"><strong>Unsubscribe</strong></button>
		</form>
	</div>
</div>

<script type="text/javascript">

$(document).ready(function() {	
	$("#requestUnsubscribeTokenForm").on("submit", function(event) {
		var $this = $(this);
		var serializedData = $this.serialize();
		var urlForm = $this.attr("action");
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					//alert("success");
					var url = "${pageContext.request.contextPath}" + data.result;
					$(location).attr("href", url);
				} else {
					alert("fail");
				}
			}, 
			error: function(data) {
				alert("error");
			}
		});	 
		event.preventDefault();
	});
});
</script>