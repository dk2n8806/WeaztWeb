

<c:set var="orderStatus" value="${fn:toLowerCase(order.orderBundle.status) }" />
<c:set var="orderId" value="${order.orderBundle.id }" />
<%-- <c:set var="productId" value="${order.orderBundle.product.id }" /> --%>
<c:set var="productTitle" value="${order.title }" />
<c:set var="productImage" value="${order.image }" />
<c:set var="productCategory" value="${order.category }" />
<c:set var="numberOfOrders" value="${order.orderBundle.noo}" />
<c:set var="orderAmount">
	<fmt:formatNumber value="${order.orderBundle.collectedAmount / 100 }" maxFractionDigits="2" minFractionDigits="2"/>
</c:set>
<c:set var="orderFee">
	<fmt:formatNumber value="${order.orderBundle.serviceFee /100}" maxFractionDigits="2" minFractionDigits="2" />
</c:set>
<c:set var="estimatedPayout">
	<fmt:formatNumber value="${orderAmount - orderFee}" maxFractionDigits="2" minFractionDigits="2" />
</c:set>
<spring:url value="/user/orders/transactions/${orderId }" var="orderDetailLink" htmlEscape="true" />
 <c:set var="orderedOn">
	<fmt:formatDate  value="${order.orderBundle.createdOn }"/>
</c:set>
<spring:url value="/user/listings/${productId }" var="productLink" htmlEscape="true" />


<div class="blk-b blkTwin">
	<div class="mainTwinBlk  ">
		<div class="blk-header">
			<h3 class="fontCap"><span class="bhi"><i class="fa fa-tags"></i></span>${productCategory }</h3>
		</div>
		<div class="blk-content">
			<div class="card150 prefix-clear">
				<div class="prefix-clear">
					<div class="card-face">
						<div class="card-holder">
							<img src="${productImage }" />
						</div>
					</div>
					<div class="card-content">	
						<h3 class="fnor">${productTitle }</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="sideTwinBlk blue2 twhite c-30">
		<div class="blk-content">
			<div class="balance-list">
				<ul>
					<li>
						<p class="statName">Status</p>
						<h3 class="statValue orderStatus" data-status="${orderStatus }"></h3>
						
					</li>
				</ul>
				<ul class="">
					<li>
						<p class="statName">Order number</p>
						<h3 class="statValue">#${orderId}</h3>
					</li>
					<li>
						<p class="statName">Date</p>
						<h3 class="statValue">
							<span>${orderedOn}</span>
						</h3>
					</li>
				</ul>
				<ul>
					<li>
						<p class="statName">No. Shipment</p>
						<h1 class="statValue">${numberOfOrders }</h1>
					</li>
					<li>
						<p class="statName">Collected Amount</p>
						<h1 class="statValue">$${orderAmount}</h1>
					</li>
				</ul>
				<ul>

					<li>
						<p class="statName">Service Fee</p>
						<h3 class="statValue">$${orderFee}</h3>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
