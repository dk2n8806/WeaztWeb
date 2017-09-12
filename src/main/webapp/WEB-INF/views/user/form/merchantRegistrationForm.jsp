<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Weazt's partner</title>
<style type="text/css">
.main-content {

}

.balance-list1  {
	margin-top: 5px;
}
.balance-list1 li {
	text-align: left;
	font-size: 14px;
	padding: 7px 20px;
}


.ablk {
	float: left;
}

.bblk {
	margin-left: 100px;
}

.main-content {
	width: 700px;
	margin: auto;
	padding: 40px;

}

/* 
body {
	background: -webkit-linear-gradient(45deg, #008cc9,#009ea5);
	background: -moz-linear-gradient(45deg, #008cc9,#009ea5);
	background: -o-linear-gradient(45deg, #008cc9,#009ea5);
	background: linear-gradient(45deg, #008cc9,#009ea5);
	background-color: #008CC9;
	background-attachment: fixed;
	font-family: "Helvetica Neue", Helvetica, FreeSans, "Liberation Sans", Helmet, Arial, sans-serif;
} */
</style>
</head>
<body>

<jsp:include page="../../components/header-navigation.jsp"></jsp:include>




<div id="mainBlk" style="display: none1;">
	<div class="twhite blue toCenter">
		<div class="main-content">
			<div><h1 class="fnor fshadow"><span class="bhi"><i style="border-color: #fff; color: #fff" class="fa fa-building-o"></i></span>Let's Work Together</h1></div>
			<br/>
			<div><h3 class="fnor">Just let us know your business information such as name and <br/> location and we get you up and running</h3></div>
		</div>
	</div>
	<div class="main-content">
		<spring:url value="/user/merchant/new-apply" var="merchantForm" htmlEscape="true" />
		<form id="merchantRegisterForm" method="post" action="${merchantForm}">
			<jsp:include page="modules/merchantRegistration/businessBlk.jsp"></jsp:include>
		</form>
	</div>
</div>






<div id="successBlk" style="display: none;">
	<div class="purple twhite">
		<div class="main-content">
			<div class="blk">
				<div class="blk-header">
					<h2 class="ftrend">
						<span class="tgreen1" style="font-size: 40px;"><i class="fa fa-check-circle"></i></span>
						&nbsp;&nbsp;<span style="font-size: 25px;">Congratulations! You are now able to sell on Weazt.</span>
					</h2>
				</div>
			</div>
		</div>
	</div>
	<spring:url value="/user/merchant/request/create-product" var="createListingPage" htmlEscape="true"/>
	<spring:url value="/user/payout" var="createPayoutPage" htmlEscape="true"/>
						
	<div class="main-content p40">
		<div class="col-wrapper">
			<div class="col">
				<h1 class="fnor">Steps you may want to take</h1>
				<p>You are now one step away from create a recurring stream of revenue.</p>
			</div>
			<br/><br/>
			<br/><br/>
			<div class="col-2">
				<div class="blk">
					<p><strong>Create first listing</strong></p>
					<p>Listing is free, so stock up many subscriptions as you want</p>
				</div>
			</div>
			<div class="col-2 ">
				<div class="blk toCenter">
						<p><a style="margin: auto;" class="btn plainBtn" href="${createListingPage }"><strong>Upload Listing&nbsp;<i class="fa fa-long-arrow-right"></i></strong></a></p>
				</div>
			</div>
			
			<br/><br/>
			<div class="col-2">
				<div class="blk">
					<p><strong>Create payout method</strong></p>
						<p>Let's us know where we should deposit your earning</p>
				</div>
			</div>
			<div class="toCenter col-2">
				<div class="blk">
						<p class="toCenter"><a class="btn plainBtn" href="${createPayoutPage }"><strong>Create Payout&nbsp;<i class="fa fa-long-arrow-right"></i></strong></a></p>
				</div>
			</div>
		</div>
	</div>
	
		
</div>


<script type="text/javascript" src="https://js.stripe.com/v1/"></script>
<script type="text/javascript">

$(document).ready(function() {

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
						formSuccess();				// Successful proccessed form
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
		event.preventDefault();
	});
	
	
	function formSuccess() {
		$("#mainBlk").hide();
		$("#successBlk").show();
	}
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
	};

	
	function toggleErrorBox($c, flag) {
		if(flag) {
			$c.removeClass("error");
		} else {
			$c.addClass("error");
		}
	}
	
});


</script>
</body>
</html>