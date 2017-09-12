<div class="col-wrapper">
	<c:forEach var="order" items="${newOrders}">
		<c:set var="productId" value="${order.orderSnapshot.productId }" />
		<c:set var="productImage" value="${order.orderSnapshot.productImage }" />
		<c:set var="productTitle" value="${order.orderSnapshot.productTitle }" />
		<c:set var="productCategory" value="${order.orderSnapshot.productCategory }" />
		<c:set var="orderCount" value="${order.orderCount }" />
		<c:set var="totalRevenue"> 
			<fmt:formatNumber  value="${order.totalRevenue / 100}" maxFractionDigits="2"/>
		</c:set>
		<spring:url value="/user/orders/${productId}?_date=${_date}" var="orderLink"  htmlEscape="true"/>
		<spring:url value="/user/listings/${productId}" var="productLink"  htmlEscape="true"/>
		<spring:url value="/user/orders/merchant/request-order-product" var="productOrderRequest" htmlEscape="true"/>
		<div class="col-2">					
			<div class="blk-b task">
				<div class="blk-header">
					<a class="blkBtn" href="${orderLink }">Process&nbsp;&nbsp;<i class="fa fa-chevron-right"></i></a>
					<h3 class="taskQuote" data-count="${orderCount }"></h3>
				</div>
				<div class="blk-content">
					<div class="card100">
						<div class="prefix-clear">
							<div class="card-face">
								<div class="card-holder">
									<img src="${productImage }" />
								</div>
							</div>
							<div class="card-content">
								<h3 class="fnor"><a class="title" data-date="${_date}" href="${orderLink}">${productTitle}</a></h3>
							</div>
						</div>
					</div>
					<br/>
					<br/>
					<div class="balance-list1">
						<ul class="overviewListing">
							<li class="prefix-clear">
								<p style="font1-size: 14px;" class="statValue toRight fontCap">${productCategory}</p>
								<p class="statName">Category</p>
							</li>
							<li class="prefix-clear">
								<p style="font-1size: 14px;" class="statValue toRight">${orderCount}</p>
								<p class="statName">No. orders</p>
							</li>
							<li class="prefix-clear">
								<p style="font-1size: 14px;" class="statValue toRight">$${totalRevenue }</p>
								<p class="statName">Collected amount</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>				
	</c:forEach>
</div>
<script type="text/javascript">
$(document).ready(function() {

	$(".taskQuote").each(function() {
		var data = parseInt($(this).data("count"));
		icon = "";
		var order = "Subscriber";
		if(data > 1) {
			order += "s";
			icon = "<span class=\"bhi\"><i style=\"font-size: 12px;\" class=\"fa fa-users\"></i></span>";
		} else {
			icon = "<span class=\"bhi\"><i style=\"font-size: 12px;\" class=\"fa fa-user\"></i></span>";
		}
		$(this).html(icon + data + " " + order);
	});
});
</script>