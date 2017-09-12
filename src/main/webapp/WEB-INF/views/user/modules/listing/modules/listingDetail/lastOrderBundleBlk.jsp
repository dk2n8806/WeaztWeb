
<c:choose>
	<c:when test="${not empty orderBundleCollection }">
		<c:set var="size" value="${orderBundleCollection.size }" />
			<c:forEach items="${orderBundleCollection.orderBundles }" var="o">
			<c:set var="id" value="${o.id }" />
			<c:set var="amount" >
				<fmt:formatNumber value="${o.collectedAmount / 100 }" minFractionDigits="2" />
			</c:set>
			<c:set var="createdOn">
				<fmt:formatDate value="${o.createdOn }"/>
			</c:set>
			<c:set var="no" value="${o.noo }" />
			<c:set var="status" value="${fn:toLowerCase(o.status)}" />
			<spring:url var="orderBundleLink" value="/user/orders/transactions/${id }" htmlEscape="true"/>
			
			
			
			<fmt:formatDate  value="${o.createdOn}" pattern="dd" var="day"  />
			<fmt:formatDate  value="${o.createdOn}" pattern="MMM" var="month"  />
			<fmt:formatDate  value="${o.createdOn}" pattern="yyyy" var="year"  />


			<c:choose>
				<c:when test="${status == 'completed' }">
					 <c:set var="chc" value="green" />
				</c:when>
				<c:when test="${status == 'pending' }">
					<c:set var="chc" value="yellow" />
				</c:when>
			</c:choose>

		<div class="blk-b">
			<div class="blk-header">
				<div class="date toRight">
					<div class="month ${chc }">
						<p>${month }</p>
					</div>
					<div class="day">
						<p>${day }</p>
					</div>
				</div>
				<h3>Recent order bundle stats</h3>
				<p class="tip">processed on <strong style="color: #515151;">(${createdOn})</strong></p>
			</div>
			<div class="blk-content">
				<div class="balance-list">
					<ul class="">
						<li>
							<h1 class="statValue">${no }</h1>
							<p class="statName">No. orders</p>
						</li>
						<li>
							<h1 class="statValue">$${amount}</h1>
							<p class="statName">Collected Amount</p>
						</li>
					</ul>
				</div>
			</div>
			<c:if test="${status == 'pending' }">
			<div class="blk-footer yellow">
					<p><i class="torange fa fa-exclamation-triangle"></i>&nbsp;You are in the process of completing this order bundle.</p>
					<p><a href="${orderBundleLink }" class="link"><strong><i class="fa fa-hand-o-right"></i>&nbsp;&nbsp;Resume and continue the process.</strong></a></p>
			</div>	
			</c:if>
			<!-- <div class="blk-footer">
				<p><a class="link">View similar order history&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i></a></p>
			</div>
			 -->
		</div>
			</c:forEach>
	</c:when>
</c:choose>