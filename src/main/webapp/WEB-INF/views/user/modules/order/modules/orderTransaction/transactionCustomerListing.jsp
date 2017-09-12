	
<c:forEach var="template" items="${templates}">
	<c:set var="receiver" value="${template.shipment.receiver.name }" />
	
	<c:set var="status" value="${fn:toLowerCase(template.shipment.status)}" />
	<c:set var="amount">
		<fmt:formatNumber value="${template.transaction.total / 100}" maxFractionDigits="2" minFractionDigits="2" />
	</c:set>
	<c:set var="street" value="${template.shipment.receiver.street }" />
	<c:set var="city" value="${template.shipment.receiver.city }" />
	<c:set var="state" value="${template.shipment.receiver.state.state }" />
	<c:set var="zipcode" value="${template.shipment.receiver.zipcode }" />
	
	
	
	
	
	<c:set var="labelFee">
		<fmt:formatNumber value="${template.shipment.recommendedShipment.rate.amount / 100 }" maxFractionDigits="2" />
	</c:set>
	<c:set var="labelProvider" value="${template.shipment.recommendedShipment.rate.provider }" />
	<c:set var="labelProviderImg" value="${template.shipment.recommendedShipment.rate.providerImage }" />
	<c:set var="labelServiceLevel" value="${template.shipment.recommendedShipment.rate.serviceLevel }" />
	
	
	
	<spring:url var="downloadLink" value="/download-label?_oid=${template.transaction.id }" htmlEscape="true"/>
	<%-- 
	
	
	
	 --%>
<div class="blk-b">
	<div class="blk-content prefix-clear">
		<table class="trans" style="width: 100%">
			<tr>
				<td class="c-20" rowspan="2">
					<h3 class="ftrend"><span class="bhi"><i class="fa fa-user"></i></span>Recipient</h3>
				</td>
				<td class="c-20">
					<p class="statName">Name</p>
					<p class="statValue">${receiver}</p>
				</td>
				<td class="c-40">
					<p class="statName">Address</p>
					<p class="statValue">${street }</p>
					<p class="statValue"><span>${city}, ${state}&nbsp;${zipcode }</span></p>
				</td>
				
				<td class="c-20">
					<p class="statName">Collected</p>
					<h1 class="statValue tgreen"><strong>$<span>${amount}</span></strong></h1>
					<p class="statValue"><span id="fbtn${template.transaction.id}" class="shipmentStatus" data-status="${status }"></span></p>
				</td>
			</tr>
		</table>
		<br/><br/>
		<table class="trans" style="width: 100%">
			<tr>
				<td class="c-20">
					<div><h3 class="ftrend">Shipping Label</h3></div>
					
				</td>
				<td class="c-40">
					<p><strong>Recommended Label</strong></p>
					<br/>
					<p class="tip">
						<strong>Provider:</strong>&nbsp;${labelProvider}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img src="<c:out value="${labelProviderImg }" />">
					</p>
					<p class="tip"><strong>Label fee:</strong>&nbsp;<span>$${labelFee }</span></p>
					<p class="tip"><strong>Service level:</strong> ${labelServiceLevel }</p>
				</td>
				<td class="c-40">
					
				
					<div class="">
						<c:choose>
							<c:when test="${status == 'pending' }">
								<c:set var="recommendedLabel" value="Purchase & download label"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="recommendedLabel" value="Re-download label" />
							</c:otherwise>
						</c:choose>
						<div class="p10">
							<p class="">
								<a class="btn plainBtn c-100 dll" data-id="${template.transaction.id}" href="${downloadLink }">
									<i class="fa fa-download"></i>&nbsp;&nbsp;<strong>${recommendedLabel }</strong>
								</a>
							</p>
							<!-- <br/>
							<p class=""><a class="tblue" href="#">Input tracking number</a></p>
							 -->
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>
</c:forEach>
	
	
	
	
<script type="text/javascript">
$(document).ready(function() {
	$(".shipmentStatus").each(function() {
		setStatus($(this));
	});
	
	function setStatus($a) {
		var s = $a.data("status");
		var str = "";
		if(s === "shipped") {
			str = "<span class=\"tgreen\"><i class=\"fa fa-check-circle\"></i>&nbsp;&nbsp;Completed</span>";
		} else {
			str = "<span class=\"torange\"><i class=\"fa fa-check-circle\"></i>&nbsp;&nbsp;Pending</span>";
		}
		$a.html(str);
	}
	
	$(".dll").on("click", function(){
		var id = $(this).data("id");
		var $id = $("#fbtn" + id);
		$id.data("status", "shipped");
		setStatus($id);
	});
});
</script>