
<div class="col-wrapper">
<c:forEach items="${products}" var="product">
		<c:set var="id" value="${product.id }" />
		<c:set var="title" value="${product.title }"/>
		<c:set var="price"><fmt:formatNumber value="${product.price / 100}" maxFractionDigits="2" /></c:set>
		<c:set var="category" value="${product.category }" />
		<c:set var="imagePath" value="${product.image }"></c:set>
		<spring:url var="productLink" htmlEscape="true" value="/user/listings/${id}" />
		<div class="col-4">
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
</div>


<script type="text/javascript">
$(document).ready(function() {

	$(".title.productTitle").each(function() {
		var $this = $(this);
		var data = $this.text();
		var url = $this.attr("href") + "?" + data.replace(/ /g,"_");
		$this.attr("href", url);
	});
});
</script> 
