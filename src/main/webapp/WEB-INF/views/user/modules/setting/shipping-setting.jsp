<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Account - Weazt</title>

<style type="text/css">
.olym-wrapper {
	min-height: 300px;
}
</style>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>


	
	
<div class="main-content">
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="nav/setting-nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">
			<div class="blk">
				<div class="blk-header prefix-clear">
					<a class="btn toRight fancy-btn" href="#createShipping"><strong><i class="fa fa-plus"></i>&nbsp;&nbsp;New address</strong></a>
					
					<h2 class="fnor">Manage Shipping Address</h2>
					<p>Make sure your primary shipping info is always up-to-date. Your subscription shipments will be delivered to the primary address.</p>
				</div>
			</div>
			<ul class="col-wrapper">
				<c:forEach var="shipping" items="${shippings}">
				<c:set var="id" value="${shipping.id}" />
				<c:set var="isActive" value="${shipping.active}" />
				<c:set var="isPrimary" value="${shipping.primary}" />
				<c:set var="name" value="${shipping.address.name }" />
				<c:set var="street" value="${shipping.address.street }" />
				<c:set var="city" value="${shipping.address.city }" />
				<c:set var="state" value="${shipping.address.state.state }" />
				<c:set var="zipcode" value="${shipping.address.zipcode }" />
				<li id="blk${id }" class="col-3">
					<div  class="blk-b">
						<div class="blk-content">
							<ul class="toRight inline">
								<li>
									<spring:url  value="/user/make-primary" var="setPrimary" htmlEscape="true"></spring:url>
									<form class="setPrimary" action=${setPrimary } method="POST">
										<input type="hidden" name="id" value="${id }" />
										<button class="isPrimary fbtn" data-primary="${isPrimary }"><i class="fa fa-check-circle"></i>&nbsp;&nbsp;primary</button>
									</form>
								</li>
								<li></li>
								<li>
									<spring:url  value="/user/delete-shipping" var="deleteShipping" htmlEscape="true"></spring:url>
									<form class="deleteShipping" action="${deleteShipping }" method="POST">
										<input type="hidden" name="id" value="${id }" />
										<button class="tgray" style="padding: 5px;"><i class="fa fa-trash"></i></button>
									</form>
								</li>
							</ul>
							<ul class="overviewListing">
								<li>
									<p class="overviewListingTitle">Name</p>
									<h4 class="overviewListingStats">${name}</h4>
								</li>
							</ul>
							<ul class="overviewListing">
								<li>
									<p class="overviewListingTitle">Address</p>
									<h4 class="overviewListingStats">
										${street}
										<br/>
										${city},&nbsp;${state}&nbsp;${zipcode}
									</h4>
								</li>
							</ul>
						</div>
					</div>
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
</div>

<br/><br/>
<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<div id="createShipping" style="display: none;">
	<spring:url value="/user/create-shipping" var="createShippingForm" htmlEscape="true" />
	<form id="createShippingForm" action="${createShippingForm }" method="POST">
	<div class="modal-header">
		<p><strong>New Address</strong></p>
	</div>
	<div class="formResponse"></div>
	<div class="modal-content">
		<table class="c-100">
			<tr>
				<td colspan="6">
					<label><strong>Name</strong></label>
					<div class="input-txt">
						<input autocomplete="off" class="_name" type="text" name="_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<label><strong>Address</strong></label>
					<div class="input-txt">
						<input  autocomplete="off" class="_street" type="text" name="_street"/>
					</div>
				</td>
			</tr>
			<tr>
				<td class="c-30" colspan="2">
					<label><strong>City</strong></label>		
					<input autocomplete="off" class="_city" name="_city" type="text"/>
				</td>
				<td class="c-30" colspan="2">
					<label><strong>State</strong></label>
					<select name="_state" class="_state c-100">	
						<jsp:include page="../../../global/modules/address.jsp"></jsp:include>
					</select>
				</td>
				<td class="c-30" colspan="2">
					<label><strong>Zipcode</strong></label>
					<input autocomplete="off" class="_zipcode" name="_zipcode" type="text"/>
				</td>
			</tr>
		</table>
	</div>
	<div class="modal-footer">
		<button class="btn plainBtn">Save</button>
	</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function() {

	$(".isPrimary").each(function(){
		if($(this).data("primary")) {
			$(this).addClass("green");
		}
	});
	
	$("form.setPrimary").on("submit", function(event) {
		var $this = $(this);
		var $btn = $this.find("button");
		var serializedData = $this.serialize();
	    var urlForm = $this.attr("action");
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					$(".isPrimary").removeClass("green");
					$btn.addClass("green");
				} else {
				}
			}, 
			error: function(data) {
			}
		});    
		event.preventDefault();
	});
	
	
	$("form.deleteShipping").on("submit", function(event) {
		var $this = $(this);
		var id = $this.find("input[name=id]").val();
		var serializedData = $this.serialize();
	    var urlForm = $this.attr("action"); 
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					$("#blk" + id).empty();
				} else {
					alert(data.result);
				}
			}, 
			error: function(data) {
				alert("error");
			}
		});     
		event.preventDefault();
	});

	
	
	
	$("form#createShippingForm").on("submit", function(event) {
		var $this = $(this);
		var $_name = $this.find("input[name=_name]");
		var $_street = $this.find("input[name=_street]");
		var $_city = $this.find("input[name=_city]");
		var $_state = $this.find("select[name=_state]");
		var $_zipcode = $this.find("input[name=_zipcode]");

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
			var serializedData = $this.serialize();
		    var urlForm = $this.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				success: function(data){
					if(data.state) {
					    location.reload();
					} else {
						formError("<div class=\"blk-red blk-content\"><p>Invalid shipping info. Please provide a valid address.</p></div>");
					}
				}, 
				error: function(data) {
				}
			});   
		} else  {
			formError("<div class=\"blk-red blk-content\"><p>Please fill out the missing fields. </p></div>");
		} 
		/* 
		*/
		

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
		var str = "<ul class=\"overviewListing\">"
			+ 		"<li>"
			+ 			"<p class=\"overviewListingTitle\">Name</p>"
			+ 			"<h4 class=\"overviewListingStats\">" + name + "</h4>"
			+ 		"</li>"
			+ 		"<li>"
			+ 			"<p class=\"overviewListingTitle\">Address</p>"
			+ 			"<h4 class=\"overviewListingStats\">" 
			+ 				"<span>"+street+"</span>"
			+ 				"<br/><span>" + city + ",&nbsp;" + state + "&nbsp;" + zipcode + "</span>"
			+ 			"</h4>"
			+ 		"</li>"
			 + 	  "</ul>"
			; 
			 
		$(".blkShipping").html(str);
	}; 
	
	
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

	

	
	
	$("#settingAccountNav").addClass("active");
	$("#dbSettingNav").addClass("active");
});
</script>
</body>
</html>