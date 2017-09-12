
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>My Bookmarks - Weazt</title>
<style type="text/css">

.mainPage {
	min-height: 300px;
}
</style>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>

 
 
 <div class="mainPage">
 <div class="main-content">	
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="nav/setting-nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">
			<div class="col-wrapper">
				<div class="col">
					<div class="blk">
						<div class="blk-header">
							<div><h2 class="fnor"><span class="bhi"><i class="fa fa-bookmark"></i></span>My Bookmark</h2></div>
							<p>You can bookmark any product for conveniently revisit</p>
						</div>
					</div>
				</div>
				<c:forEach var="t" items="${templates }">
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
					<spring:url value="/user/bookmark/remove?productId=${pid }" var="removeBookmark" htmlEscape="true" />
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
										<div><h3 class="fnor">${title }</h3></div>
										
										<!-- <p class="toRight"><strong>$${price }</strong></p> -->
										<p class="fontCategory"><i class="fa fa-tags"></i>&nbsp;${category }</p>
										
										<br/>
										<p>
											<a class="link" href="${productLink}">View product</a>
											&nbsp;&#8226;&nbsp;
											<a class="link removeBk" data-pid="${pid }" href="${removeBookmark}">Unmarked</a>
										</p>
									</div>
								</div>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
</div>
</div>
 

 
 
<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">

$(document).ready(function() {	

	$(".removeBk").on("click", function(event){
		var pid = $(this).data("pid");
		$("#bk" + pid ).remove();
		event.preventDefault();
		$.ajax({
	        url: $(this).attr('href') , 
	        success: function(response) {
	        }, 
			error: function(data) {
			},
			complete: function(data) {
			}
	     });
	     return false; 
	});
 	
 	function init() {
 		$("#bookmarkNav").addClass("active");
 	}; init();
 	
});
</script>

</body>
</html>