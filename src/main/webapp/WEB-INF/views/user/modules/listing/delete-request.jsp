<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Listing - Weazt</title>

<style type="text/css">
.main-content {
	width: 700px;
}
</style>
</head>
<body>


<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>


<jsp:include page="../../components/user-navigation.jsp"></jsp:include>



<c:set value="${template.title}" var="productTitle" />
<c:set value="${template.image}" var="productImage" />
<c:set value="${template.category}" var="category" />

<div id="showRequest" class="main-content">
	<div class="col-wrapper">
		<div class="col">
			<div class="blk-b">
				<div class="panel-header yellow twhite">
					<h3><span class="bhi twhite"><i class="fa fa-trash"></i>Delete Listing</span></h3>
				</div>
				<div class="blk-content">
					<div class="card150 prefix-clear">
						<div class="card-face">
							<div class="card-holder">
								<img src="${productImage}">
							</div>
						</div>
						<div class="card-content">
							<div><h2 class="fnor">${productTitle }</h2></div>
							<p class="fontCategory"><i class="fa fa-tags"></i>&nbsp;${category }</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br/><br/>
		<div class="col">
			<div class="blk">
			<c:choose>
				<c:when test="${token.useable == false }">
					<div class="toCenter">
						<p><strong>The token is already expired!</strong></p>
						<br/>
						<p>To get a new token, please use your listing's manager to generate a new one.</p>
						<br/>
						<spring:url var="goBackLink" value="/user/listings/${token.product.id}" htmlEscape="true"/>
						<p><a class="blue-btn" href="${goBackLink }"><i class="fa fa-arrow-circle-left"></i>&nbsp;&nbsp;Go back</a></p>
					</div>
				</c:when>
				<c:otherwise>
				<spring:url var="deleteConfirmForm"  value="/user/listing/delete-confirm" htmlEscape="true" />
				<form id="deleteConfirmForm" action="${deleteConfirmForm }" method="POSt">
					
					<div class="blk">
						 <h2 class="ftrend">Are you sure want to delete this product?</h2>
						 <p>Please review the current stats of this product before clicking on <strong>Delete</strong> button. Once you click on the <strong>Delete</strong> button, the operation will not be undone.</p>
						 <br/>
					 
						<div class="blk-b" style="width: 500px;">
							<div class="blk-content">
								<div><h3 class="fnor"><strong>Current stats</strong></h3></div>
								<br/>								
								<div class="balance-list">
									<ul class="overviewListing">
									<c:forEach var="entry" items="${params}">
									 	<li>
									 		<p class="overviewListingTitle"><c:out value="${entry.key}"/></p>
									  		<h3 class="overviewListingStats"><c:out value="${entry.value}"/></h3>
										</li>
									</c:forEach>
									</ul>
								</div>
							</div>		
						</div>
						<%-- 
						<br/>
						<hr/>
						<br/>
						<h2 class="ftrend">Help us improve your experience</h2>
						<p>Please let us know the reason why you want to delete this item.</p>
						<br/>
						<select name="_quote">
							<option value="0">Please select...</option>
							<c:forEach var="quote" items="${quotes }">
								<option value="${quote.code}">${quote.quote}</option>
							</c:forEach>
						</select>
						 --%>
						<div class="blk-footer">
							<input type="hidden" name="_token" value="${token.token}" />
							<input type="hidden" name="_pid" value="${template.id}">
							<button class="btn"><strong>Delete Listing</strong></button>
						</div>
					</div>
				</form>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
</div>
<div id="requestResult" class="main-content" style="display:none;">
	<div class="col-wrapper">
		<div class="col">
			<div class="blk-b">
				<div class="panel-header">
					<h3><span class="bhi tgreen"><i class="fa fa-check"></i>Successful</span></h3>
				</div>
				<div class="blk-content">
					<p><span>We have received your request.</span> 
					   <span>This product <strong>[${template.id }]</strong> is delete from your listing.</span>
					</p>
				</div>
				<div class="blk-footer">
					<ul>
						<spring:url value="/user/listings" var="listingLink" htmlEscape="true"></spring:url>
						<li><a class="btn" href="${listingLink }"><i class="fa fa-chevron-left"></i>&nbsp;Back to My Listings</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>







<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">

$(document).ready(function() {
	
	$("#deleteConfirmForm").on("submit", function(event) {
		var flag = true;
		/* var $quote = $(this).find("select[name=_quote]");
		var quote = parseInt($quote.val()) | 0;
		if(quote === 0) {
			$quote.addClass("error");
			flag = false;
		} else {
			$quote.removeClass("error");
			flag = true;
		} */
		if(flag) {
			var urlForm = $(this).attr("action");
			var serializedData = $(this).serialize();
	 		$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				success: function(data){
					if(data.state) {
						$("#showRequest").hide();
						$("#requestResult").show();
					} else {
					}
				}, 
				error: function(data) {
				}
			}); 
		}
		event.preventDefault();
	});
	
});
</script>
</body>
</html>