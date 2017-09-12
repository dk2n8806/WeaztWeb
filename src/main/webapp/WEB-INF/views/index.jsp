<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="base/headerMetaLink.jsp"></jsp:include>
<title>Weazt | Subscribe anything for your lifestyle</title>
<style type="text/css">
.mainList {
	padding: 40px 0px;
	border-bottom: none;
}


#footer {
	margin-top: 0 !important;
}
</style>
</head>
<body>

<jsp:include page="components/header-navigation.jsp"></jsp:include>
 
<!-- 
<div class="mainList bannerColor">
	<div class="main-content twhite toCenter">
		<div><h1 class="ftrend">Subscribe today, save right away!</h1></div>
	</div>
</div> 
 -->


      
<jsp:include page="components/banner/shopBenefitOverviewBanner.jsp"></jsp:include>


<div class="mainList"> 
<c:forEach items="${categories }" var="list">
	<c:set var="listName" value="${list.key }"/>
	<c:set var="productList" value="${list.value }" />
	<c:if test="${fn:length(productList) > 0 }">
		<div class="main-content">	
			<div class="col-wrapper">			
				<div class="col prefix-clear ph20">
					<spring:url value="/category/" var="viewNewLink" htmlEscape="true"></spring:url>
					<h1 class="fnor fontCap">${listName } Subscriptions</h1>
					&nbsp;&nbsp;
					<span><a class="searchCategory link" data-cat="${listName}" href="${viewNewLink}">view more</a></span>
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
			</div>
		</div> 
	</c:if>
	<br/>
</c:forEach>
</div>















<%-- 
<div class="mainList">
	<div class="bannerColor p40 twhite">
		<div class="toCenter">
			<div><h1 class="ftrend fshadow">Selling on Weazt is simple, secure, and proactive</h1></div>
			
			<br/>
			<spring:url value="/partner" htmlEscape="true" var="partnerLink"></spring:url>
			<p><a class="btn twhite" href="${partnerLink}"><strong>Start Selling Subscriptions!</strong></a></p>
		</div>
	</div>
</div>
 --%>







<%-- 
<spring:url value="/shop/new-subscriptions" var="viewNewLink" htmlEscape="true"></spring:url>
<c:if test="${fn:length(products) > 0 }">
<div class="mainList">
	 <div class="main-content">	
		<div class="col-wrapper">			
			<div class="col prefix-clear ph20">
				<h2 class="fnor fontCap">New Subscriptions</h2>
				&nbsp;&nbsp;
				<span><a class="link" href="${viewNewLink}">view more</a></span>
		
			</div>
			
			<c:forEach items="${products}" var="product" varStatus="indexStatus">
			<c:set var="id" value="${product.id }" />
			<c:set var="title" value="${product.title }"/>
			<c:set var="price"><fmt:formatNumber value="${product.price / 100}" maxFractionDigits="2" /></c:set>
			<c:set var="category" value="${product.category }" />
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
		</div>
	</div> 
</div>
</c:if>
 --%>







<br/><br/><br/>
 <div class="footerNewLetter turq twhite toCenter">
 	<div class="p40">
 		<h2 class="ftrend">Get the latest subscriptions and special offers delivered straight to your inbox!</h2>
 		<br/>
	 	<div class="newLetter">
	 		<spring:url value="/subscribe-new-letter" var="subscribeEmailForm"></spring:url>
	 		<form id="newLetterForm" class="email" action="${subscribeEmailForm }" method="POST">
	 			<button type="submit">Subscribe</button>
	 			<input type="text" name="_se" placeholder="Your email address">
	 		</form>
	 	</div>
	 </div>
 </div>

<jsp:include page="components/footer-navigation.jsp"></jsp:include>

<%-- <jsp:include page="components/footer-navigation.jsp"></jsp:include> --%>

<script type="text/javascript">
$(document).ready(function() {
	function init() {
		buildProductLink();
	}; init();
	

	var emailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	$("#newLetterForm").on("submit", function(event) {
		var $this = $(this);
		var $btn = $this.find("button[type=submit]");
		var $email = $this.find("input[name=_se]");
		var eFlag =  emailRegex.test($email.val());
		if(eFlag > 0) {
			serverResponse(["Thanks for signing up!"],  "turq");
			var serializedData = $this.serialize();
		    var urlForm = $this.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				beforeSend: function() {	
					$btn.attr("disabled", true);
			    },
				success: function(data){
				}, 
				error: function(data) {
				},
				complete: function(data) {
					$btn.attr("disabled", false);
				}
			}); 
		}
		event.preventDefault();
		return false;
	});
	
	
	
	$(".searchCategory").each(function(){
		var $this = $(this);
		var cat = $this.data("cat").replace(/\s+/g, '-').toLowerCase();
		var href = $this.attr("href");
		$this.attr("href", href + cat);
	});
});

</script>
</body>
</html>