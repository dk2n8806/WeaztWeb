
	
<c:forEach var="order" items="${orders}">
<c:set var="orderStatus" value="${fn:toLowerCase(order.orderBundle.status) }" />
<c:set var="orderId" value="${order.orderBundle.id }" />
<c:set var="productId" value="${order.orderBundle.product.id }" />
<c:set var="productTitle" value="${order.title }" />
<c:set var="productImage" value="${order.image }" />
<c:set var="productCategory" value="${order.category }" />
<c:set var="numberOfOrders" value="${order.orderBundle.noo}" />
<c:set var="orderAmount">
	<fmt:formatNumber value="${order.orderBundle.collectedAmount / 100 }" maxFractionDigits="2" minFractionDigits="2"/>
</c:set>
<spring:url value="/user/orders/transactions/${orderId }" var="orderDetailLink" htmlEscape="true" />
 <c:set var="orderedOn">
	<fmt:formatDate  value="${order.orderBundle.createdOn }"/>
</c:set>

<div class="col">
	<div class="blk-b blkTwin">
		<div class="mainTwinBlk">
			<div class="blk-header">
			
				<c:choose>
					<c:when test="${orderStatus == 'pending' }">
						<a class="blkBtn  yellow1" href="${orderDetailLink }"><i class="fa fa-hand-o-right"></i>&nbsp;&nbsp;Complete Pending</a>
				
					</c:when>
					<c:otherwise>		
						<a class="blkBtn fontCap white" style="color: green !important;" href="${orderDetailLink }">
							<i class="fa  fa-check-circle"></i>&nbsp;&nbsp;${orderStatus }
						</a>
			
					</c:otherwise>
				</c:choose>
				
				<h3 class="fontCap"><span class="bhi"><i class="fa fa-tags"></i></span>${productCategory }</h3>
			</div>
			<div class="blk-content">
				<div class="card100 prefix-clear">
					<div class="prefix-clear">
						<div class="card-face">
							<div class="card-holder">
								<img src="${productImage }" />
							</div>
						</div>
						<div class="card-content ">
							<div><h3 class="fnor"><a class="title"  href="${orderDetailLink }">${productTitle }</a></h3></div>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="sideTwinBlk c-30 blue2 twhite">
			<div class="blk-content">
				<div class="balance-list">
					<%-- <ul class="">
						<li>
							<p class="statName">Status</p>
							<h4 class="statValue status" data-status="${orderStatus }"></h4>
						</li>
					</ul> --%>
					<ul>
						<li>
							<p class="statName">No. Shipments</p>
							<h1 class="statValue">${numberOfOrders}</h1>
						</li>
						<li>
							<p class="statName">Total Collected</p>
							<h1 class="statValue">$${orderAmount}</h1>
						</li>
					</ul>
					<ul>
						<li>
							<p class="statName">Process on</p>
							<h4 class="statValue">${orderedOn}</h4>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
</c:forEach>

