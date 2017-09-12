
	<c:forEach var="t" items="${bookmarks }">
		<c:set var="title" 			value="${t.title }" />
		<c:set var="image" 		value="${t.image}" />
		<c:set var="category" 	value="${t.category}" />
		<c:set var="pid" value="${t.bookmark.product.id }" />
		<c:set var="createdOn">
			<fmt:formatDate  value="${t.bookmark.createdOn }" />
		</c:set>
		<c:set var="price">
			<fmt:formatNumber maxFractionDigits="2" value="${t.price / 100 }" />
		</c:set>
		<spring:url value="/shop/product/${pid }" var="productLink" htmlEscape="true" />
		
		<div class="col-2" id="bk${pid }">
			<div class="blk-b">
				<div class="blk-content">
						<div class="card100 prefix-clear">
							<div class="card-face">
								<div class="card-holder">
									<img src="${image }">
								</div>
							</div>
							<div class="card-content prefix-clear">
								<div><h3 class="fnor"><a class="title" href="${productLink }">${title }</a></h3></div>
								<!-- <p class="toRight"><strong>$${price }</strong></p> -->
								<p class="fontCategory"><i class="fa fa-tags"></i>&nbsp;${category }</p>
							</div>
						</div>
				</div>
			</div>
		</div>
	</c:forEach>
