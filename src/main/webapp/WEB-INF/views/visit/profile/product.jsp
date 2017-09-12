

<div class="col">
	<div class="blk">
		<div class="blk-header">
			<h1 class="ftrend">
				<!-- 
				<span class="bhi twhite"><i class="fa purple fa-tags"></i></span>
				 -->
				<span>My Listings</span>
			</h1>
		</div>
	</div>
</div>
<c:forEach items="${products}" var="product" varStatus="indexStatus">
	<c:set var="id" value="${product.productId }" />
	<c:set var="title" value="${product.productTitle }"/>
	<c:set var="price"><fmt:formatNumber value="${product.productPrice / 100}" maxFractionDigits="2" /></c:set>
	<c:set var="category" value="${product.category }" />
	<c:set var="imagePath" value="${product.productImage }"></c:set>
	<c:set var="toggleId" value="toogle${id}"/>
	<spring:url var="productLink" value="/shop/product/${id}" htmlEscape="true" />
	<div class="col-4">
		<div class="blk-b">
			<div class="story-wrapper">
				<div class="story-box">
					<a href="${productLink}" title="${imagePath }"><img src="<c:out value="${imagePath }" />" /></a>
				</div>
				<div class="blk-content">
					<p class="story-title">
						<a class="title productLink" href="${productLink}">${title}</a>
					</p>
					<ul class="overviewListing">
						<li>
							<p class="overviewListingTitle">${category }</p>
							<h4 class="overviewListingStats">$${price}</h4>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>			
</c:forEach>	
