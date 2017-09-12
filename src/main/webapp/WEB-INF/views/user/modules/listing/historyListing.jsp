<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Listings - Weazt</title>
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
			<jsp:include page="nav/listing_nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">	
			<div class="col-wrapper">
				<div class="col">		
					<div class="blk">
						<div class="blk-header">
							<h1 class="fnor">Deleted Products</h1>	
							<p>Total deleted: <strong>${size}</strong></p>
						</div>
					</div>				
				</div>
			</div>
			<jsp:include page="modules/listing/deleteItemList.jsp"></jsp:include>
			
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




<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">

$(document).ready(function() {
	/* 
	$(".title.productTitle").each(function() {
		var $this = $(this);
		var data = $this.text();
		var url = $this.attr("href") + "?" + data.replace(/ /g,"_");
		$this.attr("href", url);
	});
	 */
	 
	 
 	 ;function fancyCall() {
 		$(".fancy-btn").fancybox({
 			padding: 0,
 			fitToView: false,
 	    	autoWidth: true, 
 		    width: "700", 
 		    maxWidth: "700", 
 		    minWidth: "500", 
 		    maxHeigh: "90%",
 		    margin: [0, 0, 150, 0],
 	        openEffect: 'none',
 	        closeEffect: 'none',
 	        nextEffect: 'none',
 	        prevEffect: 'none',
 	        scrolling: 'no',
 	        nextSpeed: 0,
 	        prevSpeed: 0,
 	        preload: 3,
 			helpers: {
 				overlay : {
 					css : {
 						'background' : 'rgba(47, 44, 42, 0.80)'
 					},
 					closeClick: false
 				}
 			},
 			'beforeLoad': function() {
 			},
 			'afterClose': function() {
 			} 
 		});
 	}; fancyCall();

	
	
	
	
	;function displayOverviewData() {
		var $a = $("#overview");
		var s = $a.data("status");
		if(typeof s == 'undefined') {
			s = "active";
		}
		assignStatus(s.toLowerCase());
		function assignStatus(i) {
			var $a =  $("#" + i + "Tab");
			if($a.length == 1) {
				$a.addClass("active");
			} else {
				$("#allTab").addClass("active");
			}
		}
	};	displayOverviewData();
	 
	 

	;function overviewListing() {
		var $a = $("#overviewListing");
		var n = $a.data("num");
		var s = $a.data("status");
		var x = "listing";
		if(n > 2) {
			x += "s";
		}
		$a.html("You have <strong>" + n + " " + s + "</strong> " + x);
	}
	
	function init() {
		$("#dbListingNav").addClass("active");
		$("#deletedListingNav").addClass("active");
		overviewListing();
	}; init(); 
});


</script>
</body>
</html>