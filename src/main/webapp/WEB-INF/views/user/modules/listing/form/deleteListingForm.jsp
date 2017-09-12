
<div id="deleteQueue" class="hide">
	<div class="modal-header">
		<p><strong>Are you sure you want to delete this product?</strong></p>
	</div>
	<div class="modal-content">
		<p><strong>Delete this product will terminate all of your recurrent orders.</strong></p>
		<br/>
		<p>Please keep in mind that deleted product can't be retrieved. If you just don't want to accept any new subscribers, consider to switch the product to private instead.</p>
		<br/>
		<p>Click on the button below to continue deleting this product</p>
	</div>
	<div class="modal-footer">
		<spring:url value="/user/listing/delete-request" var="deleteProductForm" htmlEscape="true"/>
		<form id="deleteListingFormRequest" data-status="delete" class="productForms" method="POST" action="${deleteProductForm }">
			<input type="hidden" name="_pid" value="${product.id}" />
			<button class="btn plainBtn"><strong>Delete request</strong>&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>
		</form>
	</div>
</div>



<script type="text/javascript">

$(document).ready(function() {
	
	$("#deleteListingFormRequest").on("submit", function(event) {
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