<c:if test="${not empty reviews}">	
	<div class="col-wrapper">
		<div class="col">			
			<div class="blk-b">
				<div class="blk-header"><h3 class="mar-0"><strong>All Comments</strong></h3></div>
				<div class="blk-content review-wrapper">

				<c:forEach var="review" items="${reviews}">
					<c:set var="reviewId" value="${review.review.id }" />
					<c:set var="reviewContent" value="${review.review.content }" />
					<c:set var="reviewerId" value="${review.reviewerId }" />
					<c:set var="reviewerName" value="${review.reviewerName }" />
					<c:set var="reviewerImage" value="${review.reviewerImage }" />
					<c:set var="reviewedOn" value="${review.review.reviewedOn }" /> 
					<div class="review prefix-clear">
						<div class="reviewer">
							<div class="reviewer-img">
								<img src="<c:out value='${reviewerImage }' />" />
							</div>
						</div>
						<div class="review-content">
							<!-- <div class="review-flag menu-wrapper">
								<a><i class="fa fa-flag-o"></i></a>
								<div class="menu">
									<div class="menu-content mn150">
										<ul><li><a href="">Report this review</a></li></ul>
									</div>
								</div>
							</div> -->
							<div class="review-details">
								<p><c:out value="${reviewContent }"/></p>
							</div>
							
						<div class="review-info"><p>By&nbsp;<a class="link" href="#"><strong>${reviewerName}</strong></a>
							&nbsp;&#8226;&nbsp;<fmt:formatDate value="${reviewedOn}"/></p></div>
						</div>
					</div>
				</c:forEach>
				</div>
			</div>
		</div>
	</div>
	</c:if>