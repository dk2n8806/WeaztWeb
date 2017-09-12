<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightslider/1.1.6/css/lightslider.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightslider/1.1.6/js/lightslider.js"></script>

<title>Weazt's Partner</title>

<style type="text/css">
.sellingChanel {
	display: inline-block;
	width: 700px;
	padding: 20px 130px;
	border-radius: 5px;
}
.sellingChanel i {
	font-size: 13px;
}

.sellingChanel p.lb {
	font-size: 14px;
	margin: 5px;
}
.main-content {
	padding: 50px 0px;
}

.mainList {
	/*border-bottom: none;*/
}

.main-content p {
	font-size: 16px;
}


.iconDisplay .fa {
	color: #fff !important;
	border-color: #1abc9c  !important;
	background: #1abc9c;
}

.pika {
	position: relative;
}

.chu {
	position: absolute;
	right: 10px;
	top: 256px;
	width: 45%;
	z-index: 10;
}
.les {
}

.card {
	width: 55%;
	margin-bottom: 40px;
}
.card > * {
	display: inline-block;
	vertical-align: middle;
}

.cardIcon {
	width: 100px;
}

.cardContent {
	margin-left: 20px;
}

.cardIcon .iconDisplay > .fa {
  width: 70px;
  height:70px;
  line-height:70px;
  font-size: 30px !important;
}

.btn {
	padding: 13px 60px;
	font-size: 18px;
	font-weight: 500;
}

.slider li {
	display: table;
}
 
.slider li .col-2 {
	display: table-cell;
	padding: 0px 10px;
}

#footer {
	margin-top: 0px;
}
</style>
</head>
<body>
<jsp:include page="../../components/header-navigation.jsp"></jsp:include>




<%-- 
	<div class="mainList">
		<div class="pika bannerColor">
			<div class="main-content">
				<div class="chu">
				
			<c:choose>
				<c:when test="${not empty pageContext.request.userPrincipal}">
					<spring:url value="/user/merchant/new-apply" var="merchantForm" htmlEscape="true" />
					<form id="merchantRegisterForm" method="post" action="${merchantForm}">
						<jsp:include page="modules/merchantRegistration/businessBlk.jsp"></jsp:include>
					</form>
				</c:when>
				<c:otherwise>
					<div class="blk-b"> 	
						<div class="panel-header toCenter">
							<h3>You've not logged into Weazt yet!</h3>
						</div>
						<div class="blk-content">
							<spring:url value="/login" var="loginLink" htmlEscape="true"></spring:url>
							<a class="btn c-100 flatGreenBtn" href="${loginLink }">Login</a>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
				</div>
				<div class="les twhite toCenter">
					<div><h1 style="font-size: 36px;" class="fnor fshadow">The place for you to meet people <br/> who'll love your products</h1></div>
				</div>
			</div>
		</div>
		<div class="main-content" style="padding: 0px;">
			<div style="padding: 40px;">
				<div class="">
					<h2 class="fnor">Unlock The Weazt's Power For Your Business</h2>
				</div>
				<br/><br/>
				<div class="card">
					<div class="cardIcon">
						<span class="iconDisplay"><i class="fa fa-users"></i></span>
					</div>
					<div class="cardContent">
						<p><strong>Build loyal customers</strong> and get your brand<br/>discovered by right prospective customers</p>
					</div>
				</div>
		
				<div class="card">
					<div class="cardIcon">
						<span class="iconDisplay"><i class="fa fa-line-chart"></i></span>
					</div>
					<div class="cardContent">
						<p><strong>Grow your business</strong> with predictable<br/>and reliable revenue stream</p>
					</div>
				</div>
		
				<div class="card">
					<div class="cardIcon">
						<span class="iconDisplay"><i class="fa fa-building-o"></i></span>
					</div>
					<div class="cardContent">
						<p><strong>Discover tools</strong> to manage and scale your<br/>business easily and effectively</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	
 --%>	
	
	
	

<div class="banner">
	<div class="bannerImg bannerBlur">
		<img src="https://docs.google.com/drawings/d/1_XRS1FC00DK4mxJtgAovGfY9kAxi3Gp1fIk7FpB5B1E/pub?w=1500&h=300" src="">
	</div>
	<div class="bannerContent toCenter">
		<div style="margin: 40px;">
			<div><h1 style="font-size: 48px;" class="fnor tnavy fshadow">Sell Confidently.</h1></div>
			<br/>
			<div><h2 class="fnor" style="color: #e1e1e1;">One platform to easily start and build your recurring revenue stream - a place to meet<br/>customers who'll love your products</h2></div>

		</div>
	</div>
</div>
<div class="mainList" style="background: #2b5797;">
	<div class="main-content toCenter">
		<div><h2 class="fnor tgray1">We develop a next step to enhance your selling experience.</h2></div>
		<br/><br/>
		<div>
			<a class="btn flatGreenBtn fancy-btn"  href="#merchantForm">Become Our Partner!</a>
		</div>
	</div>
</div>

<div class="mainList toCenter">
	<div class="main-content">
		<div class="col-wrapper">
			<div class="col">
				<div class="blk">
					<div class="blk-header">
					<div><h1 style="font-size: 42px;" class="fnor">Weazt empower partners</h1></div>
					</div>
				</div>
			</div>
		</div>
		<br/><br/>
		<div class="col-wrapper">
			<div class="col-3">
				<div class="blk">
					<div class="blk-content">
						<div><h2 class="fnor"><strong>Quality Customers</strong></h2></div>
						
						<div class="p20">
							<span class="iconDisplay"><i class="fa fa-users"></i></span>
						</div>
						<p>Our customers are collectors of loyalty to the products they love</p>
					</div>
				</div>
			</div>
			<div class="col-3">
				<div class="blk">
					<div class="blk-content">
						<div><h2 class="fnor"><strong>Increase sales</strong></h2></div>
						<div class="p20">
							<span class="iconDisplay"><i class="fa fa-line-chart"></i></span>
						</div>
						<p>Grow your revenue on top of a predictable and reliable recurring revenue stream</p>
					</div>
				</div>
			</div>
			<div class="col-3">
				<div class="blk">
					<div class="blk-content">
						<div><h2 class="fnor"><strong>Powerful tools</strong></h2></div>
						
						<div class="p20">
							<span class="iconDisplay"><i class="fa fa-wrench"></i></span>
						</div>
						<p>Our tools and services make it easy for you to manage and promote your business</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- <div class="mainList white">
	<div class="main-content toCenter">
		<div class="banner banner_h100">
			<div class="bannerImg">
				<img class="fix" data-src="https://docs.google.com/drawings/d/1Gr_qcZDZmJSKy-QbJJSDldsvXj1vO6FETEnQQtK7EMc/pub?w=960&h=960" src="">
			</div>
		</div>
	</div>
</div>
-->

<div class="mainList white">
	<div class="main-content">
		<ul id="lightSlider1" class="slider">
			<li class="col-wrapper">
				<div class="col-2">
					<div class="blk">
						<div class="blk-content">
							<div><h1 style="font-size: 32px;" class="fnor">New way to grow</h1></div>
							<br/>
							<p>Never worry of getting your next sales, we have you covered. Our marketplace is one-stop destination for you to reach and connect to people who'll love your products. Our services will constantly promote your products to the right prospective customers, create your recurring stream revenues and grow your business.</p>
						</div>
					</div>
				</div>
				<div class="col-2">
					<div class="blk">
						<div class="blk-content">
							<div class="banner bannerCorner banner_h75">
								<div class="bannerImg">
									<img src="https://docs.google.com/drawings/d/1XIGvyc-eKJSr-2uLYQA99uiUYpzLqjv27jFMS50f4GA/pub?w=960&h=720">
								</div>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li class="col-wrapper">
				<div class="col-2">
					<div class="blk">
						<div class="blk-content">
							<div class="banner bannerCorner banner_h75">
								<div class="bannerImg">
									<img src="https://docs.google.com/drawings/d/1n-ymMwtCnCtiX8cUjDBO-qSvXaosMewY0grQcarbiY0/pub?w=960&h=720">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-2">
					<div class="blk">
						<div class="blk-content">
							<div><h1 style="font-size: 32px;" class="fnor">Boost your productivity</h1></div>
							<br/>
							<p>Use our order bundle manager modify and manage your recurring orders ahead of time. The service enables you to schedule your recurring orders, manage your order products proactively, optimize your processing time, and more.</p>
						</div>
					</div>
				</div>
			</li>


			<li class="col-wrapper">
				<div class="col-2">
					<div class="blk">
						<div class="blk-content">
							<div><h1 style="font-size: 32px;" class="fnor">Ship like a pro</h1></div>
							<br/>
							<p>Easily to view your customer's shipping information and date they expect to receive your products in the Shiping pane. Weazt calculates and recommends the right shipping labels to meet your customer's delivery expectation; so that your products will be delivered to your customer's doorstep right when they need it. </p>
						</div>
					</div>
				</div>
				<div class="col-2">
					<div class="blk">
						<div class="blk-content">
							<div class="banner bannerCorner banner_h75">
								<div class="bannerImg">
									<img src="https://docs.google.com/drawings/d/1z_M1LmpwZZrR91uJUigAZaXkYNG3OQZhI5U21bbXVOg/pub?w=960&h=720">
								</div>
							</div>
						</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>





<div class="mainList">
	<div class="main-content">
		<div class="col-wrapper toCenter">
			<div class="col">
				<div><h1 style="font-size: 42px;" class="fnor tnavy">How Weazt Works</h1></div>
			</div>
			<br/><br/><br/>
			<div class="col-4">
				<div class="pw5">
					<h2 class="fnor"><strong>Listing</strong></h2>
					<div class="p20">
						<span class="iconDisplay"><i class="fa fa-file-text-o"></i></span>
					</div>
					<p>Add your products on the Weazt's catalog; and it will be available through multiple channels</p>
				</div>
			</div>
			<div class="col-4">
				<div class="pw5">
					<h2 class="fnor"><strong>Selling</strong></h2>
					<div class="p20">
						<span class="iconDisplay"><i class="fa fa-paper-plane-o"></i></span>
					</div>
					<p>Customers will see and subscribe your products subscription-value rather than the retail-price</p>
				</div>
			</div>
			<div class="col-4">
				<div class="pw5">
					<h2 class="fnor"><strong>Shipping</strong></h2>
					<div class="p20">
						<span class="iconDisplay"><i class="fa fa-cubes"></i></span>
					</div>
					<p>Use our order bundle manager to process multiple orders and shipments at once</p>
				</div>
			</div>
			<div class="col-4">
				<div class="pw5">
					<h2 class="fnor"><strong>Get Paid</strong></h2>
					<div class="p20">
						<span class="iconDisplay"><i class="fa fa-dollar"></i></span>
					</div>
					<p>Weazt deposits payment into your bank every week and notifies you when payment is sent</p>
				</div>
			</div>
		</div>
	</div>
</div> 

<div class="mainList" style="background: #2b5797; border-bottom: none;">
	<div class="main-content toCenter">
		<div><h1 class="fnor tgray1">Ready to take the next step to enhance your selling experience?</h1></div>


		<br/><br/>

		<div>
			<a class="btn flatGreenBtn fancy-btn"  href="#merchantForm">Become Our Partner!</a>
		</div>
		<br/><br/>
		<div class="col-wrapper toCenter">
			<div class="sellingChanel navy twhite">
				<div>
					<h2 class="fnor shadow">Only pay for the products you sell</h2>
				</div>
				<br/>
				<p class="tgray1">You receive payments for your sales and we deduct <br/>a reasonable referral fee</p>
				<div class="ph20">
				<hr/>
				<div class="col-wrapper">
					<div class="col">						
						<ul>
							<li><h3 class="ftrend"><i class="fa tgreen fa-check-circle"></i>&nbsp;&nbsp;No credit card require</h3></li>
							<li><h3 class="ftrend"><i class="fa tgreen fa-check-circle"></i>&nbsp;&nbsp;No monthly fee</h3></li>
							<li><h3 class="ftrend"><i class="fa tgreen fa-check-circle"></i>&nbsp;&nbsp;No listing fee</h3></li>
							<li><h3 class="ftrend"><i class="fa tgreen fa-check-circle"></i>&nbsp;&nbsp;Weekly auto deposited</h3></li>
						</ul>
					</div>
				</div>
				<hr/>
				</div>
				<p class="tgray"><i>6.99% commission + service fee</i></p>
			</div>
		</div>
	</div>
</div>





<div id="merchantForm" style="display: none;">
		<c:choose>
			<c:when test="${not empty pageContext.request.userPrincipal}">
					<c:set var="prinRole"><sec:authentication property="principal.role"/></c:set>
					<c:choose>
						<c:when test="${fn:toLowerCase(prinRole) == 'customer' }">
							
							<spring:url value="/user/merchant/new-apply" var="merchantForm" htmlEscape="true" />
							<form id="merchantRegisterForm" method="post" action="${merchantForm}">
								<jsp:include page="modules/merchantRegistration/businessBlk.jsp"></jsp:include>
							</form>
						</c:when>
						<c:otherwise>
						<div class="modal-content">
								<h3>Error, account privilege denied!</h3>
						</div>
						</c:otherwise>
						</c:choose>
			</c:when>
			<c:otherwise>
				
				<div class="modal-header">
						<h3>You've not logged into Weazt yet!</h3>
				</div>
				<div class="modal-content">
					<p>Please login to your account to continue the process. If you don't have an account yet, you can create a free one.</p>
					<br/><br/>
					<spring:url value="/login" var="loginLink" htmlEscape="true"></spring:url>
					<p><a class="btn flatGreenBtn" href="${loginLink }">Login</a></p>
				</div>
			</c:otherwise>
		</c:choose>
</div>



<jsp:include page="../../components/footer-navigation.jsp"></jsp:include>




<script type="text/javascript">

$(document).ready(function() {


	$("#lightSlider").lightSlider({
		item: 1,
		auto: true,
		slideMargin:0,
		loop: true,
		speed: 1500,
		pause: 5000,
		pauseOnHover: true,
		controls: false
	}); 
	
	
	
	
	//var $btn = $("#merchantRegisterForm").find("button[type=submit]");
	$("#merchantRegisterForm").on("submit", function(event) {
		var $this = $(this);
		var flag = businessInfoHandler($this);
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
						window.location.replace("${pageContext.request.contextPath}" + data.result);
					} else {
						serverResponse(["We are unable to create merchant account, please try again or contact us if the problem continues to happen"], "red");
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
		event.preventDefault();
	});
	
	
	function businessInfoHandler($c) {
		var $name = $($c.find("input[name=_bname]"));
		var $addr = $($c.find("input[name=_baddress]"));
		var $zipc = $($c.find("input[name=_bzipcode]"));
		var $city = $($c.find("input[name=_bcity]"));
		var $state = $($c.find("select[name=_bstate]"));
		var $phone = $($c.find("input[name=_bphone]"));

		var nFlag = $name.val().length > 3 ? true : false;
		var aFlag = $addr.val().length > 3 ? true : false;
		var zFlag = $zipc.val().length > 3 ? true : false;
		var cFlag = $city.val().length > 3 ? true : false;
		var sFlag = $state.val() != "0" ? true : false;
		var pFlag = $phone.val().length == 10 ? true : false;

		toggleErrorBox($name, nFlag);
		toggleErrorBox($addr, aFlag);
		toggleErrorBox($zipc, zFlag);
		toggleErrorBox($city, cFlag);
		toggleErrorBox($state, sFlag);
		toggleErrorBox($phone, pFlag);

		return (nFlag && aFlag && zFlag && cFlag && sFlag && pFlag);
		
		return false;
	};

	
	function toggleErrorBox($c, flag) {
		if(flag) {
			$c.removeClass("error");
		} else {
			$c.addClass("error");
		}
	}
	
	
	

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
  	
  	
});


</script>
</body>
</html>