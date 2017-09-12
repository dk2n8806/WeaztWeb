<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>My Listings - Weazt</title>

<style type="text/css">

.mainPage {
	min-height: 300px;
}


.lmblk li {
	display: inline-block;
	vertical-align: middle;
	margin: 20px;
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
				<jsp:include page="nav/listing_nav.jsp"></jsp:include>
			</div>
			<div class="olymArena">	
				<div class="col-wrapper">
					<div class="col">
						<div class="blk">
							<div class="blk-header">
								<h1 class="fnor">My Listings</h1>		
								<p><span id="overviewListing" data-num="${size}" data-status="public" ></span></p>
				
							</div>
						</div>
					</div>
				</div>
				<jsp:include page="modules/listing/productListing.jsp"></jsp:include>
				
				<div class="col-wrapper">
					<div class="col toCenter">
						<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
						<br/><br/>
						<ul class="lmblk">
							<c:set var="p" value="${page.getOffset() }" />
							<c:set var="n" value="${page.getPageNumber() }" />
							<c:set var="size" value="${page.getPageSize()}" />
							 <c:if test="${n > 0}">
							 	<spring:url var="previousLink" value="${requestpath}?ref=${ref}&p=${n-1}&s=${size}" htmlEscape="true" />
							 	<li><p><a class="btn white" href="${previousLink }"><strong>Previous Page</strong></a></p></li>
							 </c:if>
							 	<li><p class=""><strong>Page ${n + 1}</strong></p></li>
							 <c:if test="${fn:length(products) == size}">
							 	<spring:url var="nextLink" value="${requestpath}?ref=${ref}&p=${n+1}&s=${size}" htmlEscape="true" />
							 	<li><p><a class="btn white" href="${nextLink }"><strong>Next Page</strong></a></p></li>
							 </c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<%-- <script type="text/javascript" src='<c:out value="/resources/js/jquery.fancybox.js"></c:out>'></script> --%>
<script type="text/javascript">

$(document).ready(function() {
	function init() {
		$("#dbListingNav").addClass("active");
		//$("#commandPrimary").find("li").eq(0).addClass("active");
		$("#publicListingNav").addClass("active");
		buildProductLink();
		overviewListing();
	}; init(); 


	
	
	 

	;function overviewListing() {
		var $a = $("#overviewListing");
		var n = $a.data("num");
		var s = $a.data("status");
		var x = "product";
		if(n > 1) {
			x += "s";
		}
		$a.html("You have <strong>" + n + " " + s + " " + x + "</strong>. The product in this field is visible publicly and eligible to be subscribed by everyone.");
	}
	
});


</script>
</body>
</html>