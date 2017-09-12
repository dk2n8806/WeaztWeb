<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Orders - Weazt</title>

<style type="text/css">
.breakdown li {
	padding: 20px;
}
</style>

</head>

<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>




<c:set var="productId" value="${product.id}" />
<c:set var="productTitle" value="${product.title }" />
<c:set var="productImage" value="${product.image }" />
<c:set var="productCategory" value="${product.category }" />
<c:set var="productPrice" value="${product.price }" />

<c:set var="fromDate">
	<fmt:formatDate value="${dateInterval.getFrom() }"/>
</c:set>
<c:set var="toDate">
	<fmt:formatDate value="${dateInterval.getTo() }"/>
</c:set>
<c:set var="totalRevenue">
	<fmt:formatNumber maxFractionDigits="2" value="${totalRevenue / 100 }" minFractionDigits="2" />
</c:set>
<spring:url value="/user/orders/${productId }" var="orderTasksLink" htmlEscape="true"/>
<spring:url value="/user/listings/${productId }" var="productPageLink" htmlEscape="true"/>
<div class="main-content">	
	<div class="col-wrapper">
		<div class="col">	
			<div class="blk">
				<div class="blk-header">
					<h1 class="fnor">Manage Order Requests <%-- / <span>${productTitle }</span> --%></h1>
				</div>
			</div>
		</div>
	</div>
	<div class="col-wrapper">
		<div class="col75">	
			<div class="blk-b">
				<div class="blk-header">
					<a target="_blank" class="toRight link" href="${productPageLink}">View product&nbsp;&nbsp;<i class="fa fa-external-link"></i></a>
					<h3 class="fontCap"><span class="bhi"><i class="fa fa-tags"></i></span>${productCategory }</h3>
				</div>
				<div class="blk-content">
					<div class="card150">
						<div class="prefix-clear">
							<div class="card-face">
								<div class="card-holder">
									<img src="${productImage }" />
								</div>
							</div>
							<div class="card-content">
								<div><h2 class="fnor">${productTitle}</h2></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col25">
			<div class="blk">
				<div class="blk-header">
					<h3><strong>Current bundle stats</strong></h3>
				</div>
				<div class="p10">
					<div class="balance-list">
						<ul>
							<li class="prefix-clear">
								<p class="statName">No. Requests</p>
								<h1  class="statValue">${totalCount}</h1>
							</li>
							<li>
								<p class="statName">Total Collect</p>
								<h1 class="statValue">$${totalRevenue }</h1>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-wrapper">
		<div class="col">
			<div class="blk">
				<div class="blk-header">
					<h2 class="fnor">Complete Order Bundle</h2>
					<p>Review and manage your subscribers. Click on <strong>Complete</strong> button to generate bundle transaction.</p>
				</div>
			</div>
		</div>
		<div class="col">
			<jsp:include page="modules/orderDetail/customerOrderListing.jsp"></jsp:include>
		</div>
	</div>
</div>




<jsp:include page="forms/merchantDequeueCustomerOrderForm.jsp"></jsp:include>
<jsp:include page="forms/merchantAcceptOrderForm.jsp"></jsp:include>



<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	
	
	var now = new Date();
	$(".scheduledDate").each(function() {
		var $this = $(this);
		var date = new Date($this.data("date"));
		var diff = date - now;
		var days = Math.floor(diff / 1000 / 60 / 60 / 24);
		//var diff = date.getDate() - now.getDate();
		console.log("date: " + date);
		console.log("now: " + now);
		console.log("diff: " + diff);
		var str = "day";
		var color = "";
		if(days >= 0) {
			color = "tgreen";
			if(days > 1) {
				str += "s";
			}
		 	days = "+" + days;
		} else {
			color = "torange";
			if(days < -1) {
				str += "s";
			}
		}
		
		$(this).append("&nbsp;&nbsp;<span class=\"tip\"><span class=\"" + color +"\">(" + days + " " + str + ")</span></span>");
	});
	
	

	$(".taskQuote").each(function() {
		var data = parseInt($(this).data("count"));
		icon = "";
		var order = "Subscriber";
		if(data > 1) {
			order += "s";
			icon = "<span class=\"bhi\"><i style=\"font-size: 12px;\" class=\"fa fa-users\"></i></span>";
		} else {
			icon = "<span class=\"bhi\"><i style=\"font-size: 12px;\" class=\"fa fa-user\"></i></span>";
		}
		$(this).html(icon + data + " " + order);
	});
	
	
	
	
	
	 ;function striggerMenu() {
	 	var $a = $(".trigger-btn");
	 	$a.on("click", function(event) {
	 		$(this).toggleClass("active");
	 		$($(this).siblings(".trigger-menu")).toggle();
	 	});
	 }; striggerMenu();

	;function fancyCall() {
		$(".fancy-btn").fancybox({
			padding: 0,
			fitToView: false,
	    	autoWidth: true, 
		    maxWidth: "500", 
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

	;function init() {
		$("#dbTaskNav, #ordersProcessLink").addClass("active");
	}; init();
 	
	
	;function toggleSetting() {
		$(".mbBtn").on("click", function(event) {
			$(this).siblings(".mbContent").toggle();
			event.preventDefault();
		});

		$(".mbCloseBtn").on("click", function() {
			$(this).parent().parent().parent(".mbContent").toggle();
		});
	}; toggleSetting();
	
	$(document).mouseup(function (e)
	{
	    var container = $(".mbContent");

	    if (!container.is(e.target) // if the target of the click isn't the container...
	        && container.has(e.target).length === 0) // ... nor a descendant of the container
	    {
	        container.hide();
	    }
	});
});

</script>






</body>
</html>