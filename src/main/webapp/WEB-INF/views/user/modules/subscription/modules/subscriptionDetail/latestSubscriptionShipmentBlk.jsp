<c:forEach var="template" items="${historyShipments }">

	<c:set var="productTitle" value="${template.productTitle }" />
	<c:set var="productImage" value="${template.productImage }" />
	<c:set var="category" value="${template.category }" />
	<c:set var="shippedOn">
		<fmt:formatDate value="${template.shipment.createdOn }"/>
	</c:set>
	<c:set var="shipmentId" value="${template.shipment.id }" />
	<c:set var="trackingNumber" value="${template.shipment.label.labelAdapter.trackingNumber }" />
	<c:set var="trackingUrl" value="${template.shipment.label.labelAdapter.trackingUrl }" />
	
	<c:set var="receiver" value="${template.shipment.receiver.name }" />
	<c:set var="status" value="${fn:toLowerCase(template.shipment.status)}" />
	<c:set var="street" value="${template.shipment.receiver.street }" />
	<c:set var="city" value="${template.shipment.receiver.city }" />
	<c:set var="state" value="${template.shipment.receiver.state.state }" />
	<c:set var="zipcode" value="${template.shipment.receiver.zipcode }" />
	
	
	
<fmt:formatDate  value="${template.shipment.createdOn }" pattern="dd" var="day"  />
<fmt:formatDate  value="${template.shipment.createdOn }" pattern="MMM" var="month"  />

<div class="blk-b">
	<div class="blk-header prefix-clear">
		<div class="toRight">
			<div class="date">
				<div class="month green">
					<p>${month }</p>
				</div>
				<div class="day">
					<p>${day }</p>
				</div>
			</div>
		</div>
		<h3>Recent shipment</h3>
		<p class="tip">Shipped on <strong style="color: #515151;">${shippedOn }</strong></p>
		<br/>
		<div class="balance-list">
			<ul>
				<li>
					<p class="statName">Shipment No.</p>
					<h3 class="statValue">#${shipmentId}</h3>
				</li>
			</ul>
			<ul>
				<li>
					<p class="statName">Status</p>
					<c:choose>
						<c:when test="${status == 'shipped' }">
							<h3 class="statValue tgreen fontCap"><i style="font-size: 13px;" class="fa fa-check-circle"></i>&nbsp;&nbsp;${status}</h3>
						</c:when>
						<c:otherwise>
							<h3 class="statValue torange fontCap"><i style="font-size: 11px;" class="fa fa-check-circle"></i>&nbsp;&nbsp;${status}</h3>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
			<ul>
				<li>
					<p class="statName">Tracking Number</p>
					<h3 class="statValue"><a class="link" target="_blank" href="${trackingUrl }">${trackingNumber}&nbsp;&nbsp;<i class="fa fa-external-link"></i></a></h3>
				</li>
			</ul>
		</div>
	</div>
</div>
</c:forEach>