
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Subscriptions - Weazt</title>

</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>

<jsp:include page="../../components/user-navigation.jsp"></jsp:include>

 
 

<c:set var="subscriptionId" value="${subscription.id }" />
<c:set var="subscriptionStatus" value="${fn:toLowerCase(subscription.status) }" />

<c:set var="category"  value="${product.category}"/>
<c:set var="productTitle" value="${product.title}" />

<spring:url value="/user/subscriptions" var="mySubscriptionLink" htmlEscape="true"/>
<spring:url value="/user/subscriptions/${subscriptionId}" var="activeSubscriptionLink" htmlEscape="true"/>




 <div class="main-content">	
	<div class="col-wrapper">
		<div class="col">
			<div class="blk">
				<div class="blk-header">
					<h1 class="fnor">Subscription Details</h1>&nbsp;&nbsp;<h3 class="fnor">/ <span>${productTitle }</span></h3>
				</div>
			</div>
		</div>
	</div>
	<div class="col-wrapper">
		<div class="col75">
			<jsp:include page="modules/subscriptionDetail/productSubscriptionOverviewBlk.jsp"></jsp:include> 
			<jsp:include page="modules/subscriptionDetail/subscriberExperienceBlk.jsp"></jsp:include> 
			<div class="blk-b">
				<div class="blk-header">
					<h2 class="fnor"><i class="fa fa-comments-o"></i>&nbsp;&nbsp;Subscriber reviews (${reviewSize })</h2>
				</div>
				<div class="blk-content">
					<div class="reviewList">
						<jsp:include page="../../../global/modules/productReviews.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="col25">
			<jsp:include page="modules/subscriptionDetail/subscriptionOverviewBlk.jsp"></jsp:include> 	
			<jsp:include page="modules/subscriptionDetail/nextSubscriptionShipmentBlk.jsp"></jsp:include> 	
			
			<jsp:include page="modules/subscriptionDetail/latestSubscriptionShipmentBlk.jsp"></jsp:include> 	
	
		</div>
	</div>
</div>
 
 

<jsp:include page="form/dequeueSubscriptionForm.jsp"></jsp:include>
<jsp:include page="form/customizeDeliverFrequencyForm.jsp"></jsp:include>
 
 
 
 
 
 
<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">

$(document).ready(function() {	

	$(".btnTxt").each(function() {
		var $this = $(this);
		var a = $this.data("active");
		if(a == true) {
			$this.addClass("active");
		} 
		
	});
 	$(".btnTxt").on("click", function(event) {
		event.preventDefault();
   	 	var $this = $(this);
		var urlForm = $this.attr("href");
		$.ajax({
			url:urlForm,
			method: "POST",
			success: function(data){
				$this.toggleClass("active");
			}, 
			error: function(data) {
				alert("error");
			}, 
			complete: function(data) {
			}
 		});
		return false;
	}); 
	
	
	
	$("#reviewForm").on("submit", function(event){
		var $this = $(this);
		var $c = $this.find("textarea[name=_c]");
		var c = $c.val().trim() || "";
		var flag = (c.length | 0) > 10 ? true : false;
		
		var $btn = $this.find("button[type=submit]");
		if(flag) {
			var serializedData = $this.serialize();
	   	 	var urlForm = $this.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				beforeSend: function() {	
					$btn.attr("disabled", true);
					$btn.prepend("<span class=\"loader\"><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;</span>");
			    },
				success: function(data){
					if(data.state) {
						success();
					} else {
					}
				}, 
				error: function(data) {
					alert("error");
				}, 
				complete: function(data) {
					$btn.find(".loader").empty();
					$btn.attr("disabled", false);
				}
			});
		} 
		
		function success() {
			var str = "<div class=\"review\">"
					+ 	"<div class=\"reviewContent\">"
					+ 		"<div class=\"reviewer\">"
					+			"<div class=\"reviewerHolder\">"
					+				"<img src=\""+$this.data("ava")+"\">"
					+			"</div>"
					+		"</div>"
					+		"<div class=\"reviewInfo\">"
					+			"<p><a class=\"tturq\"><strong>" + $this.data("name") + "</strong></a>&nbsp;&nbsp;<span class=\"tip\">|&nbsp;&nbsp;now</span></p>"
					+			"<div class=\"reviewMsg\">"
					+				"<p>" + c + "</p>"
					+			"</div>"
					+		"</div>"
					+	"</div>"
					+"</div>";
			
			$(".reviewList").prepend(str);
			$c.val("");
		}
		event.preventDefault();
	});
	


	$(".review textarea").on('focus', function(){
		$(this).height("150");
	});
	
	
	;function toggleSetting() {
		$(".toggleTrigger, .toggleCirTrigger").on("click", function(event) {
			$(this).siblings(".toggleContent").toggle();
			event.preventDefault();
		});

	}; toggleSetting();
	
	$(document).mouseup(function (e)
	{
	    var container = $(".toggleContent");

	    if (!container.is(e.target) // if the target of the click isn't the container...
	        && container.has(e.target).length === 0) // ... nor a descendant of the container
	    {
	        container.hide();
	    }
	});
 	
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
 	
 	
 	

 	function init() {
 		$("#dbSubscriptionNav").addClass("active");
 		$("#overviewNav").addClass("active");
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