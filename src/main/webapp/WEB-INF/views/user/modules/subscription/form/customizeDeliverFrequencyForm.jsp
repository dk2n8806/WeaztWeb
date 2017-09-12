
<div id="updateDeliver" style="display: none">
	<div class="modal-header">
		<p><strong>Update shipping frequency</strong></p>
	</div>
	<spring:url value="/user/subscription/update/deliver-freq" var="updateDeliverForm" htmlEscape="true"/>
	<form id="updateDeliverForm" action="${updateDeliverForm}" method="POST">
	<div class="modal-content">
		<div><label></label></div>
		<p>Let us know how often you will want this product ship to your front door</p>
		<br/>
		
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
		<input type="hidden" name="_sid" value="${subscription.id}">
		&nbsp;&nbsp;<span class="freqError tip" style="display: none;"><span class="tred">*Value must be >= 7</span></span>
	</div>
	<div class="modal-footer">
		<button type="submit" class="btn plainBtn"><strong>Update</strong></button>
	</div>
	</form>
</div>


<script type="text/javascript">
$(document).ready(function() {	
	
	function displayFrequency($a) {
		var val = $a.data("freq");
		var str = "";
		if(val == 1) {
			str += "weekly";
		}
		if(val > 1) {
			str = "every " + val +  " weeks";
		}
		$a.html(str); 
		
	}; displayFrequency($("#deliverFreq"));
	
	 
	$("#updateDeliverForm").on("submit", function(event) {
		var $this = $(this);
		var $_freq = $this.find("select[name=_freq]");
		var freq = parseInt($_freq.val()) | 0;
		
		var flag = (Math.floor(freq) === freq) && (freq >= 7);
		if(flag) {
			$_freq.removeClass("error");
		} else {
			$_freq.addClass("error");
		} 
	 	if(flag) {
	 		$(".freqError").hide();
			var serializedData = $this.serialize();
			var urlForm = $this.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				success: function(data){
					if(data.state) {
						var $a = $("#deliverFreq");
						$a.data("freq", freq / 7);
						displayFrequency($a);
						$.fancybox.close();
					} else {
					}
				}, 
				error: function(data) {
				}
			}); 
	 	} else  {
	 		$(".freqError").show();
	 	}
		event.preventDefault();
	});
});
</script>