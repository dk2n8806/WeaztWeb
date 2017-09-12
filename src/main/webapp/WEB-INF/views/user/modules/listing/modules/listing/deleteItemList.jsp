
<div class="col-wrapper">
<c:forEach items="${templates}" var="template">
		<c:set var="title" value="${template.title }"/>
		<c:set var="image" value="${template.image}"/>
		<c:set var="category" value="${template.category }"/>
		<c:set var="createdOn">
			<fmt:formatDate value="${template.product.createdOn }"/>
		</c:set>
		<spring:url var="productLink" htmlEscape="true" value="/user/listings/${productId }" />
		<div class="col-2">
			<div class="blk-b">	
				<div class="blk-header">
					<h3 class="fontCap"><span class="bhi"><i class="fa fa-tags"></i>${category }</span></h3>
				</div>
				<div class="blk-content">
					<div class="card100 prefix-clear">
						<div class="card-face">
							<div class="card-holder">
								<img src="${image}">
							</div>
						</div>
						<div class="card-content">
							<div><h3 class="fnor">${title}</h3></div>
						</div>
					</div>
				</div>
				<div class="blk-footer">
					<p>Deleted on: <strong>${createdOn}</strong></p>		
				</div>
			</div>
							
			
		</div>
	</c:forEach>
</div>


