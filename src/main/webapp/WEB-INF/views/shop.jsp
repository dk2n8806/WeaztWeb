<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="base/headerMetaLink.jsp"></jsp:include>
<title>Weazt | Subscribe anything for your lifestyle</title>

<style type="text/css">
.lmblk li {
	display: inline-block;
	vertical-align: middle;
	margin: 20px;
}


.mainList {
	padding: 40px 0px;
	border-bottom: none;
}

</style>
</head>
<body>

<jsp:include page="components/header-navigation.jsp"></jsp:include>
<jsp:include page="components/banner/shopBenefitOverviewBanner.jsp"></jsp:include>

 <br/>
<c:forEach items="${products }" var="list">
	<c:set var="listName" value="${list.key }"/>
	<c:set var="productList" value="${list.value }" />
	
	<c:if test="${fn:length(productList) > 0 }">
		<div class="main-content">	
			<div class="col-wrapper">			
				<div class="col prefix-clear ph20">
					<h1 class="fnor fontCap">${searchQuery}</h1>
				</div> 
				<c:forEach items="${productList}" var="product" varStatus="indexStatus">
				
				<c:set var="id" value="${product.id }" />
				<c:set var="title" value="${product.title }"/>
				<c:set var="price"><fmt:formatNumber value="${product.price / 100}" maxFractionDigits="2" /></c:set>
				<c:set var="category" value="${product.category}" />
				<c:set var="imagePath" value="${product.image }"></c:set>
				<c:set var="toggleId" value="toogle${id}"/>
				<spring:url var="productLink" value="/shop/product/${id}" htmlEscape="true" />
				<div class="col-5">
					<div class="blk-b">
						<div class="story-wrapper">
							<div class="story-box">
								<a href="${productLink}" class="productLink"  data-title="${title }"><img src="<c:out value="${imagePath }" />" /></a>
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
				<div class="col toCenter">
				<c:set var="req" value="${pageContext.request}" />
				<c:set var="baseURL" value="${fn:replace(req.requestURL, req.requestURI, '')}" />
				<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}"/>
				<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
				<c:set var="pageUrl" value="${ baseURL }${ requestPath }${ not empty params?'?'+=params:'' }"/>
					<br/><br/>
					<ul class="lmblk">
						<c:set var="p" value="${page.getOffset() }" />
						<c:set var="n" value="${page.getPageNumber() }" />
						<c:set var="size" value="${page.getPageSize()}" />
						 <c:if test="${n > 0}">
						 	<spring:url var="previousLink" value="${requestpath}?p=${n-1}&s=${size}" htmlEscape="true" />
						 	<li><p><a class="btn white" href="${previousLink }"><strong>Previous Page</strong></a></p></li>
						 </c:if>
						 	<li><p class=""><strong>Page ${n + 1}</strong></p></li>
						 <c:if test="${fn:length(productList) == size}">
						 	<spring:url var="nextLink" value="${requestpath}?p=${n+1}&s=${size}" htmlEscape="true" />
						 	<li><p><a class="btn white" href="${nextLink }"><strong>Next Page</strong></a></p></li>
						 </c:if>
					</ul>
				</div>
			</div>
		</div> 

	</c:if>
</c:forEach>






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