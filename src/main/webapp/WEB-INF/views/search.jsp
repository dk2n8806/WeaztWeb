<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="base/headerMetaLink.jsp"></jsp:include>
<title>Weazt | Subscribe anything for your lifestyle</title>
</head>
<body>

<jsp:include page="components/header-navigation.jsp"></jsp:include>
<jsp:include page="components/banner/shopBenefitOverviewBanner.jsp"></jsp:include>



<div class="main-content">	
	<div class="col-wrapper">			
		
		<div class="col prefix-clear ph20">
			<h3 class="fnor">result for: <span id="searchResult" class="tgreen">"${searchString }"</span></h3>
		</div>
		<c:forEach items="${templates}" var="product" varStatus="indexStatus">
			<c:set var="id" value="${product.id }" />
			<c:set var="title" value="${product.title }"/>
			<c:set var="price"><fmt:formatNumber value="${product.price / 100}" maxFractionDigits="2" /></c:set>
			<c:set var="category" value="${product.category }" />
			<c:set var="imagePath" value="${product.image }"></c:set>
			<c:set var="toggleId" value="toogle${id}"/>
			<spring:url var="productLink" value="/shop/product/${id}" htmlEscape="true" />
			<spring:url var="productLink" value="/shop/product/${id}" htmlEscape="true" />
			<div class="col-5">
				<div class="blk-b">
					<div class="story-wrapper">
						<div class="story-box">
							<a href="${productLink}" title="${imagePath }"><img src="<c:out value="${imagePath }" />" /></a>
						</div>
						<div class="blk-content">
							<p class="story-title">
								<a class="title productLink" data-title="${title }" href="${productLink}">${title}</a>
							</p>
							<ul class="overviewListing">
								<li>
									<p class="overviewListingTitle">${category }</p>
									<h4 class="overviewListingStats">$${price}</h4>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>			
		</c:forEach>	
	</div>
</div>





<jsp:include page="components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {

	function init() {
		buildProductLink();
	}; init();
	
	

});

</script>
</body>
</html>