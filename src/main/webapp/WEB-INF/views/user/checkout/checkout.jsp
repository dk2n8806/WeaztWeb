<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Checkout - Weazt</title>

<style type="text/css">

.promo li {
	vertical-align: middle;
}
</style>
</head>

<body>
<%-- 
<jsp:include page="../../components/header-navigation.jsp"></jsp:include>
 --%>
 

<div id="headerBlk" class="">
	<div id="header-container" class="prefix-clear">
		<h1 id="hlogo">
			<spring:url value="/" var="baseLink" htmlEscape="true"/>
			<a href="${baseLink}">Weazt</a><a><span style="text-shadow: none; font-weight: 300;">&nbsp;/&nbsp; Checkout</span></a>
		</h1>
	</div>
</div>
<br/><br/>
<div class="main-content">
<c:set var="productTitle" value="${product.basicInfo.title }" />
<c:set var="productCategory" value="${product.category.name }" />


	<div class="blk-container blkr">
		<div class="mblk" style="width: 70%;">
			<jsp:include page="modules/shippingBlk.jsp"></jsp:include>  
			<hr/>
			<jsp:include page="modules/paymentBlk.jsp"></jsp:include>
			<hr/>
			<jsp:include page="modules/promotionBlk.jsp"></jsp:include>
			<hr/>
			<jsp:include page="modules/subscriptionBlk.jsp"></jsp:include> 
			<div class="col">
				<div class="blk">
					<div class="blk-content toCenter">
						<spring:url var="helpPage" value="/help"></spring:url>
						<spring:url var="contactPage" value="/contact_us" />
						<p class="tgray">If you have any question, check out our <a class="link" target="_blank" href="${helpPage }">help pages</a> or <a class="link" target="_blank" href="${contactPage }">contact us</a></p>
					</div>
				</div>
			</div>
		</div>
		<div class="sblk" style="width: 30%;">
			<div class="col-wrapper">
				<div class="col">
					<jsp:include page="modules/checkoutProduct.jsp"></jsp:include>		
				</div>
				
			</div>
			
		</div>
	</div>
</div>
<%-- 
<jsp:include page="../../components/footer-navigation.jsp"></jsp:include>
 --%>
<script type="text/javascript" src="https://js.stripe.com/v1/"></script>
<script type="text/javascript">
$(document).ready(function() {
	

	var promoFlag = false;
	$("form#updatePromotionForm").on("submit", function(event) {
		var $this = $(this);
		var $c = $this.find("input[name=code]");
		var $btn = $this.find("button[type=submit]");
		var $res = $this.find(".serverResponse");
		if($c.val() == '') {
			serverResponse(["No promotion code"], "yellow");
		} else {
			var serializedData = $this.serialize();
		    var urlForm = $this.attr("action");
		    var s = 0;
		    var code = "";
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				success: function(data){
					if(data.state) {
						promoFlag = true;
						s = data.result.value / 100;
						code = data.result.code;
						$res.html("<div class='tgreen'><i class=\"fa fa-check-circle\"></i>&nbsp;&nbsp;You just got "+s+"% off on this subscription</div>");		
					
					} else {
						s = 0;
						$res.html("<div class='tred'><i class='fa fa-exclamation-triangle'></i>&nbsp;&nbsp;[Error] you have already used the code on another subscription.</div>");
					}
				}, 
				error: function(data) {
					s = 0;
				},
				complete: function(data) {
					if(promoFlag) {
						$btn.prop("disabled", true);
						$c.prop("disabled", true);
						var $p = $("#pdata");
						var a = $p.data("save");
						$p.data("save", a + s);
						$("#promoCode").val(code);

						calTotal();
					} else {
						$btn.prop("disabled", false);
					}

		        }
			});    
		}
		event.preventDefault();
		return false;
	});
	
	
	
	var a = $("#common").data("freq");
	var s = "weekly";
	if(a > 1) {
		s = a + " weeks";
	}
	$("#common").html("Most selected: <strong>" + s + "</strong>");
	
	
	$("#numberOfSubscription").on("change", function(){
		var n = parseInt($(this).val()) | 0;
		var $ss = $("#selectedShipment");
		$ss.html(n);
		$("#pdata").data("shipment", n);
		calTotal();
	});
	

	function calTotal() { 
		var $p = $("#pdata");
		var $note = $("#note");
		var $tracker = $("#tracker");
		var _price 	= parseFloat($p.data("price")) * 100;
		var _save 	= parseFloat($p.data("save"));
		var _sType = $p.data("stype");
		var _sCost = $p.data("scost") * 100;
		var _tRate = $p.data("trate");
		var _shipment = $p.data("shipment");
		var _min = $p.data("min");
		console.log("_price: " + _price);
		console.log("_save: " + _save);
		console.log("_sType: " + _sType);
		console.log("_sCost: " + _sCost);
		console.log("_tRate: " + _tRate);
		console.log("_min: " + _min);
		console.log("_shipment: " + _shipment);
		console.log($note.length);
		
		var _sprice = 0;
		if(_shipment < _min) {
			$("#retailPrice").css({"text-decoration" : "none", "color" : "#16a085"});
			$("#subscriptionPrice, #saveRate").css({"text-decoration" : "line-through", "color" : "#525252"});
			_sprice = _price;
			$note.html("<span class=\"toRight tgray\">(Calculated with regular price)</span>");
			$tracker.html("<span>&nbsp;<i style=\"font-size: 16px;\" class=\"fa tgray fa-check-circle\"></i></span>");
		} else {
			$("#retailPrice").css({"text-decoration" : "line-through", "color" : "#525252"});
			$("#subscriptionPrice, #saveRate").css({"text-decoration" : "none", "color" : "#16a085"});
			_sprice = _price - (_price * _save / 100);
			$note.html("<span class=\"toRight tgray\">(Calculated with subscription price)</span>");
			$tracker.html("<span>&nbsp;<i style=\"font-size: 16px;\" class=\"fa tgreen fa-check-circle\"></i></span>");
		}
		
		var $ta = $("#totalAmount");
		var $st = $("#subTotal");
		var totalPerUnit = _sprice + _sCost + (_sprice * _tRate);
		$st.html("$" + ((totalPerUnit) / 100).toFixed(2));
		$ta.html("<strong class=\"tgreen\">$" + ((totalPerUnit * _shipment) / 100).toFixed(2) + "</strong>");
	}; calTotal();
	
	
 
	
	$("form#updateShippingForm").on("submit", function(event) {
		var $this = $(this);
		var $_name = $this.find("input[name=_name]");
		var $_street = $this.find("input[name=_street]");
		var $_city = $this.find("input[name=_city]");
		var $_state = $this.find("select[name=_state]");
		var $_zipcode = $this.find("input[name=_zipcode]");
		var $btn = $this.find("button[type=submit]");
		
		var _name = $_name.val();
		var _street = $_street.val();
		var _city = $_city.val();
		var _state = $_state.val();
		var _zipcode = $_zipcode.val();
		
		
		var nf = checkInput($_name, _name);
		var sf = checkInput($_street, _street);
		var cf = checkInput($_city, _city);
		var tf = checkSelect($_state, _state);
		var zf = checkInput($_zipcode, _zipcode);
		if(nf && sf && cf && tf && zf) {
			$btn.prop("disabled", true);
			$btn.html("<strong><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;Updating...</strong>");
			
			var serializedData = $this.serialize();
		    var urlForm = $this.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				success: function(data){
					if(data.state) {
						var result = data.result / 100;
						$("#shippingRate").html("$" + result);
						$("#pdata").data("scost", result);
						calTotal();
						success(_name, _street, _city, _state.toUpperCase(), _zipcode);
				    	$_name.val("");
						$_street.val("");
						$_city.val("");
						$_zipcode.val("");
						$_state.prop('selectedIndex',0);
						
						$.fancybox.close();
					} else {
						formError("<div class=\"blk-red blk-content\"><p>Invalid shipping info. Unable to save your shipping info.</p></div>");
					}
				}, 
				error: function(data) {
				},
				complete: function(data) {
					$btn.html("<strong>Update</strong>");
					$btn.prop("disabled", false);
		        }
			});   
		} else  {
			formError("<div class=\"blk-red blk-content\"><p>Please fill out the missing fields. </p></div>");
		} 

		event.preventDefault();
	});
	function checkInput($a, a) {
		if(a.length > 0) {
			$a.removeClass("error");
			return true;
		} else {
			$a.addClass("error");
			return false;
		}
	}
	function checkSelect($a, a) {
		if(a != "0") {
			$a.removeClass("error");
			return true;
		} else {
			$a.addClass("error");
			return false;
		}
	}

	function formError(str) {
		$(".formResponse").html(str);
	}
	
	
	function success(name, street, city, state, zipcode) {
		var str = "" //"<p><strong>Default address</strong></p><br/>"
			+ 		"<table class=\"overviewListing\" style=\"width: 100%;\">"
			+ 		"<tr>"
			+ 			"<td class=\"c-20\"><p class=\"overviewListingTitle\">Name</p></td>"
			+ 			"<td class=\"c-80\"><h4 class=\"overviewListingStats\">" + name + "</h4></td>"
			+ 		"</tr>"
			+ 		"<tr>"
			+ 			"<td><p class=\"overviewListingTitle\">Address</p></td>"
			+ 			"<td><h4 class=\"overviewListingStats\">" 
			+ 				"<span>"+street+"</span>"
			+ 				"<br/><span>" + city + ",&nbsp;" + state + "&nbsp;" + zipcode + "</span>"
			+ 			"</h4></td>"
			+ 		"</tr>"
			 + 	  "</table>"
			; 
			 
		$("#blkShipping").html(str);
	}; 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
	;function fancyCall() {
		$(".fancy-btn").fancybox({
			padding: 0,
			fitToView: false,
	    	autoWidth: true, 
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
});
</script>
</body>
</html>