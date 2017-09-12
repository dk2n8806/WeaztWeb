<div id="serverResponse"></div>

<ul class="inline">
	<c:forEach var="stripe" items="${stripes}">
		<c:set var="p" value="${stripe.customerAdapter.cardAdapter }" />
		<c:set var="pid" value="${stripe.id }" />
		<c:set var="pStatus" value="${stripe.status }" />
		<c:set var="pLast4" value="${p.last4}"/>
		<c:set var="pExpM" value="${p.expMonth}"/>
		<c:set var="pExpY" value="${p.expYear}"/>
		<c:set var="pbrand" value="${p.brand}"/>
		<c:set var="pfunding" value="${p.funding}"/>

		<spring:url var="deletePayment" value="/user/delete-a-payment" htmlEscape="true"/>
		<spring:url var="setPaymentDefault" value="/user/set-default-a-payment" htmlEscape="true"/>
		<li id="${pid}Row">
			<div class="cube">
				<div class="cubeHeader">
					<form class="setPaymentDefault toRight" action="${setPaymentDefault}" method="POST">
						<input type="hidden" value="${pid }" name="_id">
						<button class="queueOrder-btn status" data-status="${pStatus }" ></button>
					</form>
					<p class="tip">Expire on: <strong>${pExpM}/${pExpY}</strong></p>
				</div>
				<div class="cubeContent">
					<h2>${pbrand }</h2>
				</div>
				<div class="cubeFooter">
					<span class="toRight"></span>
					<form class="deletePayment toRight" action="${deletePayment}" method="POST">
						<input type="hidden" value="${pid }" name="_id">
						<button class="tgray"><i class="fa fa-trash"></i></button>	
					</form>
					<h3>X-${pLast4}</h3>
				</div>
			</div>
		</li>
	</c:forEach> 
</ul>

<script type="text/javascript">
$(document).ready(function() {
	$(".status").each(function() {
		var status = $(this).data("status").toLowerCase();
		setStatus($(this), status);
	});
	
	function setStatus($a, a) {
		var icon = "<i class=\"fa fa-check\"></i>";
		var str = "";
		if(a === "default") {
			$a.addClass("active");
			str = "&nbsp;<span>Default</span>";
		} else {
			$a.removeClass("active");
		}
		$a.html(icon + str);
	}
	
	$("form.setPaymentDefault").on("submit", function(event){
		var serializedData = $(this).serialize();
		var urlForm = $(this).attr("action");
		var rowId = $(this).find("input[name=_id]").val();
		var $rowId = $("#" + rowId + "Row");
		
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					setStatus($(".status"), "active");
					setStatus($rowId.find(".status"), "default");
				} else {
					failHandler("Unable to set the card as default");
				}
			}, 
			error: function(data) {
				failHandler("Internal error");
			}
		});
		event.preventDefault();
	});
	
	$("form.deletePayment").on("submit", function(event){
		var serializedData = $(this).serialize();
		var urlForm = $(this).attr("action");
		var rowId = $(this).find("input[name=_id]").val();
		var $rowId = $("#" + rowId + "Row");
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					$rowId.remove();
				} else {
					failHandler("Unable to delete the card");
				}
			}, 
			error: function(data) {
				failHandler("Internal error");
			}
		});
		event.preventDefault();
	});
	
	var $res = $("#serverResponse");
	function successHandler(msg){
		var str = "<br/><br/><div class=\"blk-green blk-content\">"+msg+"</div><br/><br/>";
		$res.html(str);
	}
	
	function failHandler(msg){
		var str = "<br/><div class=\"blk-red blk-content\">"+msg+"</div><br/><br/>";
		$res.html(str);
	}
	
	
});
</script>