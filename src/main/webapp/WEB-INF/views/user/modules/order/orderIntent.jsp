<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Orders - Weazt</title>

<style type="text/css">

.mainPage {
	min-height: 300px;
}
</style>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>


	
<spring:url value="/user/orders" var="orderTasksLink" htmlEscape="true"/>
<div class="mainPage">
<div class="main-content">	
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="nav/order_nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">		
			<div class="col-wrapper">
				<div class="col">	
					<div class="blk">
						<div class="blk-header">
							<h1 class="fnor">Order Bundles</h1>
							<p id="overviewHeader" data-customer="${totalCount }" data-product="${fn:length(newOrders)}"></p>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="modules/order/orderListing.jsp"></jsp:include>
		</div>
	</div>
</div>
</div>




<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {

	/* var buildUrl = function(base, key, value) {
	    var sep = (base.indexOf('?') > -1) ? '&' : '?';
	    return base + sep + key + '=' + value;
	};
	 */
	 
	 
	 
	 
	//alert($(".orderLinkBuilder").length);
	/* $(".orderLinkBuilder").each(function() {
		var date = $(this).data("date").toLowerCase();
		var url = $(this).attr("href");
		
		if(date !== "") {
			url = buildUrl(url, "_date", date);
		}
		$(this).attr("href", url);
	});

	
	 */
	
	 /* 
	 
	;function setTaskCalendarSelect() {
		var $a = $("#taskCalendarSelect");
		var a = $a.data("date");
		var $b = $a.find("#taskCalendarSelectTitle");
		if(a === "2days") {
			$b.html("2-Days Tasks");
		} else if(a === "3days") {
			$b.html("3-Days Tasks");
		} else if(a === "1week"){
			$b.html("1-Week Tasks");
		} else {
			$b.html("Today's Tasks");
		}
	};  setTaskCalendarSelect();

	 */
	
	
	

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
 	
	

	function init() {
		$("#dbTaskNav").addClass("active");
		$("#ordersProcessLink").addClass("active");
		renderOverivewHeader();
	}; init();
	
	function renderOverivewHeader() {
		var $a = $("#overviewHeader");
		var c = $a.data("customer");
		var p = $a.data("product");
		var str = "";
		var ORDER = "order request";
		var PRODUCT = "product";
		if(c > 1) {
			ORDER += "s";
		}
		if(p > 1) {
			PRODUCT += "s";
		}
		str += "You have <strong>" + c + "</strong> " + ORDER; 
		str += " <span>for</span> ";
		str += "<strong>" + p + "</strong> " + PRODUCT;
		//str += " <span>. Select an product to continue processing order(s)</span>";
		$a.html(str);
	}
});



</script>

</body>
</html>