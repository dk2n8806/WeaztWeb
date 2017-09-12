
<div id="completeTaskForm" class="hide">
<div class="modal-header">
	<p><strong>Complete Process</strong></p>
</div>
<spring:url value="/user/orders/accept/account" var="customerOrderRequest" htmlEscape="true"></spring:url>
<form id="orderRequestForm" action="${customerOrderRequest }" method="POST" >	
	<div class="serverResponse" style="display: none">
		<div class="blk-green p20"><i class="fa fa-spinner fa-spin"></i>&nbsp;&nbsp;Generating shipping labels...</div>
	</div>		
	<div class="serverResponseBlk"></div>			
	<div class="blk-content">
		<p>When you are ready for shipping the orders, click on the <strong>Complete</strong> button to finish the process and begin your shipping process. </p>
		<input type="hidden" name="_pid" value="${product.id }"/>
		<input type="hidden" name="_date" value="${_date}"/>
	</div>
	<div class="modal-footer">
		<button type="submit" class="btn plainBtn"><strong>Complete</strong></button>	
	</div>
</form>
</div>



<script type="text/javascript">
$(document).ready(function() {

	var $acceptForm = $("#orderRequestForm");
	$acceptForm.on("submit", function(event){
		var $this = $(this);
		var $btn = $this.find("button[type=submit]");
		var serializedData = $this.serialize();
		var urlForm = $this.attr("action");
		
		/* $this.find(".serverResponse").show(); */
		$btn.prop("disabled", true);
		$btn.prepend("<span class=\"loader\"><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;</span>");
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					var id = data.result;
					/* successAcceptFormHandler($this); */
						setTimeout(function(){
							location.href = "${pageContext.request.contextPath}" + id;
						}, 1000);
				} else {
					$this.find(".serverResponse").hide();
					failAcceptFormHandler($this, "unable to generate orders...");
				}
			}, 
			error: function(data) {
				$this.find(".serverResponse").hide();
				failAcceptFormHandler($this, "Error");
			},
			complete: function(data) {
				$btn.removeClass("loader");
				$btn.prop("disabled", false);
	        }
		});
		event.preventDefault();
	});
	
	function successAcceptFormHandler($b) {
		var $a = $b.find(".serverResponseBlk");
		var str = 	"<div class=\"blk-green\">"
			+ 		"<div class=\"blk-content\">"
			+			"<p>Successful request</p>"
			+ 			"<p></p>"
			+		"</div>"
			+ 	"</div>";
		$a.html(str);
	}
	

	function failAcceptFormHandler($b, msg) {
		var $a = $b.find(".serverResponseBlk");
		var str = "<div class=\"blk-red\"><div class=\"blk-content\">"+msg+"</div></div>";
		$a.html(str);
	}
	
	
});
</script>