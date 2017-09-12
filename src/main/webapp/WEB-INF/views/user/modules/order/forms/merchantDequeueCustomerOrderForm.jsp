
<div id="dequeueRequest" class="hide">
	<spring:url value="/user/orders/dequeue/customer" var="merchantDequeueCustomerRequest" htmlEscape="true"/>
	<form id="merchantDequeueCustomerRequestForm" action="${merchantDequeueCustomerRequest }" method="POST">	
		<div class="modal-header">
			<p><strong>Are you sure want to cancel this order?</strong></p>
		</div>
		<div class="serverResponseBlk"></div>
		<div class="modal-content">
			<p>Cancel the order will terminate the current and future shipments related to the subscription. You may not undone this operation.</p>	
		</div>
		<div class="modal-footer">
			<input type="hidden" name="_oid" value="0">
			<button type="submit" class="plainBtn btn"><strong>Cancel order</strong></button>
			<br/>
		</div>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function() {
	var $dequeueForm = $("#merchantDequeueCustomerRequestForm");
	$(".dequeueRequest.fancy-btn").on("click", function(event) {
		var oid = $(this).data("oid");
		$dequeueForm.find("input[name=_oid]").val(oid);
		event.preventDefault();
	});
	
	$dequeueForm.on("submit", function(event) {
		var val = parseInt($dequeueForm.find("input[name=_oid]").val()) | 0;
		var $btn = $dequeueForm.find("button[type=submit]");
		
		if(val === 0) {
			failAcceptFormHandler($dequeueForm, "Unable to delete the order!");
		} else {
			$btn.prop("disabled", true);
			$btn.prepend("<span class=\"spinner\"><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;</span>");
			
			var serializedData = $dequeueForm.serialize();
			var urlForm = $dequeueForm.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				success: function(data){
					if(data.state) { 
						successAcceptFormHandler(val);
					} else {
						failAcceptFormHandler($dequeueForm, "Unable to delete the order!");
					} 
				}, 
				error: function(data) {
				},
				complete: function(data) {
					$btn.removeClass("spinner");
					$btn.prop("disabled", false);
		        }
			});  
			
		}
		event.preventDefault();
	});
	

	function successAcceptFormHandler(id) {
		$("#order" + id).remove();
		var total = 0;
		$(".revenue").each(function(){
			total += $(this).data("revenue");
		});
		$("#totalRevenue").html(total.toFixed(2));
		
		var count = $("#totalCount").text() - 1;
		$(".count").html(count);
		
		$.fancybox.close();
	}
	

	function failAcceptFormHandler($b, msg) {
		var $a = $b.find(".serverResponseBlk");
		var str = "<div class=\"blk-red\"><div class=\"blk-content\">"+msg+"</div></div>";
		$a.html(str);
	}
	
});
</script>