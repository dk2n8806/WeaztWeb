<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>My Listings - Weazt</title>
<style type="text/css">


.editBox {
	display: none;
}

.editBox.active {
	display: block;
}


.editNav {
	padding: 10px 20px 10px 0px;
	border-right: 1px solid #dbdbdb;
}

.editNav ul {
	margin-bottom: 10px;
}

.editNav li {
	padding: 7px;
	display: block;
	cursor: default;
}

.editNav li:hover,
.editNav li.active {
	border-left: 3px solid #ff5a5f;
}

</style>
</head>
<body>


<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>

<c:set var="productTitle" value="${product.basicInfo.title}" />
<c:set value="${product.category.name }" var="category" />


<spring:url  var="newListingLink" value="/user/listings" htmlEscape="true"></spring:url>


<div class="main-content">	
	<div class="blk">
		<div class="blk-header">
			<h1 class="fnor">Listing Details</h1>&nbsp;&nbsp;<h3 class="fnor">/ <span>${productTitle }</span></h3>	
		</div>
	</div>	
	<div class="col-wrapper">
		<div class="col75" style="padding: 0;">	
			<div class="col-wrapper">
				<div class="col">
					<jsp:include page="modules/listingDetail/product.jsp"></jsp:include>
				</div>
				<div class="col-2">
			 		<jsp:include page="modules/listingDetail/overview.jsp"></jsp:include>
				</div>
				<div class="col-2">
					<jsp:include page="modules/listingDetail/subscription.jsp"></jsp:include>
				</div>
				<div class="col">
					<div class="blk-b">
						<div class="blk-header">
							<h2 class="fnor"><i class="fa fa-comments-o"></i>&nbsp;&nbsp;Subscriber reviews (${reviewSize })</h2>
						</div>
						<div class="blk-content">
							<c:choose>
								<c:when test="${reviewSize > 0 }">
									<div class="reviewList">
									<jsp:include page="../../../global/modules/productReviews.jsp"></jsp:include>
									</div>
								</c:when>
								<c:otherwise>
									<p>There is no review for the subscription at this moment!</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col25">
			
			<%-- 
			<jsp:include page="modules/listingDetail/productStatsBlk.jsp"></jsp:include>
			 --%>
			<jsp:include page="modules/listingDetail/nextOrderBundleBlk.jsp"></jsp:include>
			<jsp:include page="modules/listingDetail/lastOrderBundleBlk.jsp"></jsp:include>
		</div>
	</div>
</div>


		 

<jsp:include page="form/activeListingForm.jsp"></jsp:include>
<jsp:include page="form/deactiveListingForm.jsp"></jsp:include>
<jsp:include page="form/deleteListingForm.jsp"></jsp:include>

<jsp:include page="form/updateBasicInfoForm.jsp"></jsp:include>
<%-- <jsp:include page="form/updateSubscriptionForm.jsp"></jsp:include> --%>




<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>

<script type="text/javascript">

$(document).ready(function() {
	
	
	 
	function displayStatus() {
		var $a = $(".productStatusDisplay");
		var s = $a.data("status").toLowerCase();
		
		var str = "";
		if(s === "public") {
			str = "Public&nbsp;&nbsp;<i class=\"fa fa-globe\"></i>";
		} else if(s === "private") {
			str = "Private&nbsp;&nbsp;<i class=\"fa fa-lock\"></i>";
		} else {
			str = "Deleted&nbsp;&nbsp;<i class=\"fa fa-trash\"></i>";
		}
		
		$a.html(str);
		
	}; displayStatus();
	 
	 
	 $("#editGeneral").show();
	$(".editNav li").on("click", function(){
		var box = $(this).data("edit");
		$(".editBox").hide();
		$("#" + box).show();
		$(".editNav li").removeClass("active");
		$(this).addClass("active");
		$.fancybox.update();
	});
	
	$(".productTitle").each(function() {
		var $this = $(this);
		var data = $this.data("title");
		var url = $this.attr("href") + "?" + data.replace(/ /g,"_");
		$this.attr("href", url);
	});
	
	/* 
 	$(".selectFormat").each(function() {		
		var $a = $(this);
		var $b = $a.find("option");
		var data = $a.data("select").toString().toLowerCase();
		$b.each(function() {
			if($(this).val() === data) {
				$(this).prop("selected", true);
			}
		});
	});
	 */
 	
  	
  	;function fancyCall() {
  		$(".fancy-btn").fancybox({
  			padding: 0,
  			fitToView: false,
  	    	autoWidth: true, 
 		    width: "600", 
 		    maxWidth: "600", 
  		    minWidth: "600", 
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

 	;function toggleMbContent() {
 		$(".toggleTrigger, .toggleCirTrigger").on("click", function(event) {
 			$(this).siblings(".toggleContent").toggle();
 			event.preventDefault();
 		});

 		$(".toggleCloseBtn").on("click", function() {
 			$(this).parents(".toggleContent").toggle();
 		});
 	} toggleMbContent();
 	
 	$(document).mouseup(function (e)
 	{
 	    var container = $(".toggleContent");

 	    if (!container.is(e.target) // if the target of the click isn't the container...
 	        && container.has(e.target).length === 0) // ... nor a descendant of the container
 	    {
 	        container.hide();
 	    }
 	});


 	function init() {
 		$("#dbListingNav").addClass("active");
 		$("#overviewNav").addClass("active");
 	}; init();
});


</script>
</body>
</html>