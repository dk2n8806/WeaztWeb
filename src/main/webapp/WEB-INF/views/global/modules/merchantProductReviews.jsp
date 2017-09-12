

<c:forEach items="${reviews}" var="review">
<c:set var="reviewer" value="${review.reviewerName }"></c:set>
<c:set var="reviewerAva" value="${review.reviewerImage}"></c:set>
<c:set var="reviewId" value="${review.review.id}"></c:set>
<c:set var="content" value="${review.review.content}"></c:set>
<c:set var="reviewedOn" >
	<fmt:formatDate value="${review.review.createdOn }"/>
</c:set>
<c:set var="reviewProductId" value="${review.productId }" />
<c:set var="reviewProductTitle" value="${review.productTitle}" />
<c:set var="reviewProductImage" value="${review.productImage}" />
<c:set var="reviewProductCategory" value="${review.category}" />


<spring:url value="/shop/product/${reviewProductId}" var="productLink" htmlEscape="true" />
			
<div class="review">
	<div class="reviewContent">
		<div class="reviewer">
			<div class="reviewerHolder">
				<img src="${reviewerAva }">
			</div>
		</div>
		<div class="reviewInfo">
			<p><a class="tblue"><strong>${reviewer}</strong></a>&nbsp;&nbsp;<span class="tip">|&nbsp;&nbsp;${reviewedOn }</span></p>
			<div class="reviewMsg">
				<p class="more">${content }</p>
			</div>
			
			<div class="reviewProduct">
				<div class="productImg">
					<div class="productHolder"><img src="${reviewProductImage }"/></div>
				</div>
				<div class="productInfo">
					<p class="fontCategory"><i class="fa fa-tags"></i>&nbsp;&nbsp;${reviewProductCategory }</p>
					<p class="review-product-title" >	<a class="title productLink" data-title="${reviewProductTitle}" href="${productLink }">${reviewProductTitle}</a></p>
				</div>
			</div>
		</div>
	</div>
</div>

</c:forEach>