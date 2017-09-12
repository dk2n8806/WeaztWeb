

<c:set var="productId" value="${product.id}" />
<c:set var="productTitle" value="${product.title }" />
<c:set var="productImage" value="${product.image }" />
<c:set var="productCategory" value="${product.category }" />
<c:set var="productPrice" value="${product.price }" />


<div class="blk-b">
	<div class="blk-header">
		<a class="blkBtn red fancy-btn" href="#completeTaskForm"><i class="fa fa-hand-o-right"></i>&nbsp;&nbsp;Complete order bundle process</a>		
		<h3 class="taskQuote" data-count="${totalCount }">
			<span class="bhi"><i class="fa  fa-users"></i></span>
		</h3>
	</div>
	<div class="blk-content modal-content">
		<table class="p10">
			<tr>
				<th class="c-20"></th>
				<th class="c-40"></th>
				<th class="c-20"></th>
				<th class="c-20"></th>
			</tr>
		<c:forEach items="${customers}" var="customer">
			<c:set var="orderId" value="${customer.orderIntent.id }"/>
			<c:set var="orderStatus" value="${customer.orderIntent.status }"/>
			<c:set var="customerProductId" value="${customer.product.id}" />
			<c:set var="customerSubscriptionId" value="${customer.subscriptionId}" />
			<c:set var="customerName" value="${customer.customerName}" />
			<c:set var="customerImage" value="${customer.customerImage}" />
			<c:set var="customerSubscribedPrice">
				<fmt:formatNumber value="${customer.subscriptionCost / 100}" maxFractionDigits="2"/>
			</c:set>
			<c:set var="customerSubscriptionStatus" value="${fn:toLowerCase(customer.subscriptionStatus)}" />
			<c:set var="customerSubscriptionScheduledOn">
				<fmt:formatDate value="${customer.scheduledOn}" />
			</c:set>
			<tr id="order${orderId}">
				<td>
					<p class="overviewListingTitle" style="text-transform: none;">Scheduled on</p>
					<h4 class="overviewListingStats scheduledDate" data-date="${customerSubscriptionScheduledOn}">${customerSubscriptionScheduledOn}</h4>
				</td>
				<td>
					<div class="blk-credit prefix-clear">
						<div class="credit-img">
							<img src="${customerImage}"/>
						</div>
						<div class="credit-info">
							<p style="font-size: 13px; color: #8899a6; letter-spacing: 0.02em;">${customerSubscriptionStatus}&nbsp;by</p>
							<h3 class="fnor"><strong>${customerName }</strong></h3>
						</div>
					</div>
				</td>
				<td>
					<p class="overviewListingTitle" style="text-transform: none;">Amount</p>
					<h4 class="overviewListingStats revenue" data-revenue="${customerSubscribedPrice}">$${customerSubscribedPrice }</h4>
				</td>
				<td>
					<a style="color: #c7c7c7" class="fancy-btn fbtn fbtn-red dequeueRequest" data-oid="${orderId }" data-sid="${customerSubscriptionId}" href="#dequeueRequest"><i class="fa fa-times"></i>&nbsp;Cancel</a>
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</div>
 