<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Payment - Weazt</title>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>

<div class="main-content">
	<div class="olym-wrapper">
		<div class="olymLeft">	
			<jsp:include page="../setting/nav/setting-nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">
			<div class="col-wrapper">	
				<div class="col">
					<jsp:include page="nav/paymentNav.jsp"></jsp:include> 
				</div>
				<div class="col">
					<div class="blk-b">
						<div class="panel-header">
							<h3><span>Payment Methods</span></h3>
						</div>
						<div class="blk-content prefix-clear">
							<p>Here are the payment methods stored in your the Weazt account</p>
							<br/>	
							<div class="prefix-clear">
								<a class="plain-btn toRight fancy-btn btn" href="#paymentBlk">
									<i class="fa fa-plus"></i>&nbsp;&nbsp;<strong>New payment method</strong>
								</a>
								<h3 class="fnor"><strong>Credit & Debit Cards</strong></h3>
							</div>
							<hr/>
						</div>
						<div class="blk-content modal-content">	
							<jsp:include page="modules/payment/paymentlisting.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>	

<jsp:include page="form/payment/newPaymentMethodForm.jsp"></jsp:include>





<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript" src="https://js.stripe.com/v1/"></script>

<script type="text/javascript">
$(document).ready(function() {

	function init() {
		$("#paymentMethodNav").addClass("active");
		$("#settingPaymentNav").addClass("active");
	}; init();
	
	
	
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
 	
});
</script>
</body>
</html>