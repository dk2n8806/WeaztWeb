<c:forEach items="${similarTemplates}" var="product" varStatus="indexStatus">
	<c:set var="id" value="${product.id }" />
	<c:set var="title" value="${product.title }"/>
	<c:set var="price"><fmt:formatNumber value="${product.price / 100}" maxFractionDigits="2" /></c:set>
	<c:set var="category" value="${product.category }" />
	<c:set var="imagePath" value="${product.image }"></c:set>
	<c:set var="toggleId" value="toogle${id}"/>
	<spring:url var="productLink" value="/shop/product/${id}" htmlEscape="true" />
	<div class="col-5">
		<div class="blk-b">
			<div class="story-wrapper">
				<div class="story-box">
					<a href="${productLink}" class="productLink"  data-title="${title }"><img src="<c:out value="${imagePath }" />" /></a>
				</div>
				<div class="blk-content">
					<p class="story-title">
						<a class="title productLink" data-title="${title }" href="${productLink}">${title}</a>
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