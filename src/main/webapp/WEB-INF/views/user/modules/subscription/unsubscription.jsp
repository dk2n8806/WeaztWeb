
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Subscriptions - Weazt</title>
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
			<div class="col-wrapper">
				<div class="col">
					<jsp:include page="nav/subscription-nav.jsp"></jsp:include>
				</div>
			</div>		
		</div>
		<div class="olymArena">
			<div class="col-wrapper">
				<div class="col">
					<div class="blk">
						<div class="blk-header">
							<h1 class="fnor">Unsubscriptions</h1>
						</div>
					</div>
				</div>
			</div>
			<div class="col-wrapper">
				<%-- <div class="col">
					<div class="blk">
						<h2 class="fnor">Unsubscriptions</h2>
						<p>Total count: <strong>${size}</strong></p>
					</div>
				</div>
					<br/><br/>
					 --%>
				<c:forEach var="template" items="${templates}">	
					<c:set value="${template.productId}" var="productId" />
					<c:set value="${template.productTitle}" var="productTitle" />
					<c:set value="${template.productImage}" var="productImage" />	
					<c:set value="${template.category }" var="category" />
					
					
					<spring:url var="productLink" value="/shop/product/${productId}" htmlEscape="true"></spring:url>
				
					<c:set var="createdOn">
						<fmt:formatDate value="${template.unsubscription.createdOn}"/>
					</c:set>
				<div class="col-2">
					<div class="blk-b">
						<div class="blk-header">
							<a class="blkBtn" href="${productLink }">Re-subscribe</a>
							<%-- <p>Unsubscribed on: <strong>${createdOn}</strong></p> --%>
							<h3 class="fontCap"><span class="bhi"><i class="fa fa-tags"></i></span>${category }</h3>
						</div>
						<div class="blk-content">	
							<div class="card100 prefix-clear">
								<div class="card-face">
									<div class="card-holder">
										<img src="${productImage }">
									</div>
								</div>
								<div class="card-content">	
									<div><h3 class="fnor">${productTitle }</h3></div>
									<%-- <p class="fontCategory"><i class="fa fa-tags"></i>&nbsp;&nbsp;${category }</p> --%>
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

	$(".status").each(function(){
		var s = $(this).data("status");
		if(s === "unsubscribed") {
			$(this).addClass("torange");
		} else {
			$(this).addClass("tgreen");
		}
	});
	
	
 	
 	
 	
 	function init() {
 		$("#dbSubscriptionNav").addClass("active");
 		$("#unsubscriptionsLink").addClass("active");
 	}; init();
 	
 	

 	;function displayOverview() {
 		var $a = $("#overview"); 		
 		displayTab($a.data("status"));
 		function displayTab(v) {
 			//alert(v);
 		 	$("#" + v.toLowerCase() + "Tab").addClass("active");
 		}
 	};
 	displayOverview();
 	

});
</script>

</body>
</html>