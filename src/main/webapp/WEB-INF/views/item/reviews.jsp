<div class="blk-b">
	<!-- <div class="blk-header prefix-clear">
		<h3><span class="blk-header-icon"><i class="material-icons">&#xE8AF;</i></span>Reviews</h3>
	</div> -->
	<div class="blk-content">
		<div class="softLine">
			<span><strong>Review on: </strong></span>
			<a class="link active" href="#">All products</a>
			<span>|</span>
			<a class="link" href="#">The product</a>
		</div>
		<br/>
		<br/>
		<div class="review-wrapper">
		<c:forEach var="review" items='${reviews}'>
			<c:set var="reviewId" value="${review.review.id }"/>
			<c:set var="reviewContent" value="${review.review.content }" />
			<c:set var="reviewOn" value="${review.review.reviewedOn }" />
			<c:set var="reviewProductId" value="${review.productId }" />								
			<c:set var="reviewProductTitle" value="${review.productTitle }" />
			<c:set var="reviewProductImage" value="${review.productImage }"/>
			
			<c:set var="reviewCustomerId" value="${review.reviewerId }" />
			<c:set var="reviewCustomerName" value="${review.reviewerName }" />
			<c:set var="reviewCustomerImage" value="${review.reviewerImage }" />
			<spring:url var="reviewProductLink" value="/shop/product/${reviewProductId }" htmlEscape="true" />
		
			<div class="review">										
				<div class="reviewer">
					<div class="reviewer-img">
						<img src="<c:out value='${reviewCustomerImage }' />" />
					</div>
				</div>
				<div class="review-details">
					<div class="review-content">								
						<p><c:out value="${reviewContent}" /></p>
						<div class="review-footer prefix-clear">
							<div class="review-product">
								<div class="review-product-img">
									<img src="<c:out value='${reviewProductImage }' />" />
								</div>
								<div class="review-product-content">
									<h4 class="fnor"><strong><a class="title" href="${reviewProductLink }"><c:out value="${reviewProductTitle }" /></a></strong></h4>
								</div>
							</div>
						</div>
						<div class="review-info">
							<p>By&nbsp;<a class="link" href="#"><strong>${reviewCustomerName}</strong></a>&nbsp;on <fmt:formatDate type='date' pattern='yyyy-MM-dd' value="${reviewOn }" /></p>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>								
		</div>
	</div>
</div>