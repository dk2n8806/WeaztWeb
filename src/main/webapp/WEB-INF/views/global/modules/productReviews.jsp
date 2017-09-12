

<c:forEach items="${reviews}" var="review">
<c:set var="reviewer" value="${review.reviewerName }"></c:set>
<c:set var="reviewerAva" value="${review.reviewerImage}"></c:set>
<c:set var="reviewId" value="${review.review.id}"></c:set>
<c:set var="content" value="${review.review.content}"></c:set>
<c:set var="reviewedOn" >
	<fmt:formatDate value="${review.review.createdOn }"/>
</c:set>
<div class="review">
	<div class="reviewContent">
		<div class="reviewer">
			<div class="reviewerHolder">
				<img src="${reviewerAva }">
			</div>
		</div>
		<div class="reviewInfo">
			<p><a class="tturq"><strong>${reviewer}</strong></a>&nbsp;&nbsp;<span class="tip">|&nbsp;&nbsp;${reviewedOn }</span></p>
			<div class="reviewMsg">
				<p>${content }</p>
			</div>
			<br/>
			<div class="reviewRating">
				<p>Was this review helpful to you? <a class="reviewBtn">yes</a>&nbsp;&nbsp;<a class="reviewBtn">no</a></p>
			</div>
		</div>
	</div>
</div>

</c:forEach>

<script type="text/javascript">

$(document).ready(function() {

	$(".reviewBtn").on("click", function(event) {
		var $this = $(this);
		var $a = $this.closest(".reviewRating");
		$a.html("<p class=\"tgreen\"><i class='fa fa-check'></i>&nbsp;Thank for your feedback.</p>")
		event.preventDefault();
	});
});
</script>