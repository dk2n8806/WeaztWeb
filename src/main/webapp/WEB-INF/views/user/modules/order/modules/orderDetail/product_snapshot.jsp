<c:if test="${not empty product }">
	<c:set var="productId" value="${product.productId }" />
	<c:set var="productTitle" value="${product.productTitle }" />
	<c:set var="productImage" value="${product.productImage }" />
	<c:set var="productCategory" value="${product.category }" />
	<c:set var="productPrice" value="${product.productPrice }" />
</c:if>

<c:set var="revenue" >
	<fmt:formatNumber value="${totalRevenue / 100 }" maxFractionDigits="2" />
</c:set>
<c:set var="aveRevenue">
	<fmt:formatNumber value="${(totalRevenue / totalCount)/ 100 }" maxFractionDigits="2" />
</c:set>

<div class="blk-b">
	<%-- <div class="panel-header">
		<h3 class="tblue"><span class="bhi twhite"><i class="fa fa-tag blue"></i></span>${productCategory}</h3>
	</div>	 --%>				
	<div class="story-box">
		<img src="${productImage }" />
	</div>
	<div class="blk-content">
		<h2 class="story-title">
			<a class="title" href="listing-detail.html">${productTitle}</a>
		</h2>
		<%-- <div class="story-info prefix-clear">
			<p class="tgray">${productCategory }</p>
			<p class="fx1-1">$<fmt:formatNumber maxFractionDigits="2" value="${productPrice }"/></p>
		</div> --%>
	</div>
	<div class="panel-footer prefix-clear">
		<div class="p20">
			<div>
				<h3 class="fnor">
					<span class="toRight">${totalCount }</span>
					<span>Number Of Orders</span>
				</h3>
			</div>
			<div>
				<h3 class="fnor">
					<span class="toRight">$${aveRevenue }</span>
					<span>Average</span>
				</h3>
			</div>
			<br/>
			<hr>
			<br/>
			<div>
				<h2 class="fnor">
					<span class="toRight"><strong>$${revenue }</strong></span>
					<span>Revenue</span>
				</h2>
			</div>
			
		</div>
	</div>
</div>		