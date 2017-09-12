
<div class="blk-b">
	<div class="blk-header">
		<h3 style="margin: 0px;">How is your experience about this subscription?</h3>
		<p>Sharing your experience about this subscription with the world and your rating will help us serve you better.</p>
	</div>
	<div class="blk-content">
		<c:set var="prinName"><sec:authentication property="principal.username"/></c:set>
	 	<c:set var="prinAvaImg"><sec:authentication property="principal.avatar.imagePath.path"/></c:set>
	
		<div class="review">
			<div class="reviewContent">
				<div class="reviewer">
					<div class="reviewerHolder">
						<img src="${prinAvaImg}">
					</div>
				</div>
				<spring:url value="/user/subscription/write-new-review" var="subscriberReview" htmlEscape="true"></spring:url>
				<form id="reviewForm" data-ava=${prinAvaImg } data-name="${prinName }" action="${subscriberReview }" method="POST">
				<div class="reviewInfo">
					<div class="reviewMsg">
						<div><textarea name="_c" style="padding: 10px;" placeholder="Your experience ..."></textarea></div>
						<input type="hidden" name="_sid" value="${subscription.id }">
						<br/>
						<div>
							<button type="submit" class="btn flatGreenBtn">Comment</button>
						</div>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>