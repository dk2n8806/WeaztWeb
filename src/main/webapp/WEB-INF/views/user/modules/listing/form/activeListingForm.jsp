
<div id="activeListing" class="hide">
	<div class="modal-header">
		<p><strong>Make Public</strong></p>
	</div>
	<div class="1toCenter">
		<div class="modal-content">
			<p><strong>Allow everyone to search and subscribe to this item.</strong></p>
			<!-- <br/>
			<p>The item will be publicly available for subscribe.</p> -->
		</div>
		<div class="modal-footer">
			<spring:url value="/user/listing/active-request" var="activeProductForm" htmlEscape="true"/>
			<form id="activeListingFormRequest" class="productForms" method="POST" action="${activeProductForm }">
				<input type="hidden" name="_pid" value="${product.id}" />
				<button class="btn plainBtn"><strong>Public</strong></button>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">

$(document).ready(function() {
	
	$("#activeListingFormRequest").on("submit", function(event) {
 		var urlForm = $(this).attr("action");
		var serializedData = $(this).serialize();
 		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				$.fancybox.close();
				if(data.state) {
					$(".productStatusDisplay").html("<span class=\"\">Public&nbsp;&nbsp;<i class=\"fa fa-globe\"></i></span>");
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