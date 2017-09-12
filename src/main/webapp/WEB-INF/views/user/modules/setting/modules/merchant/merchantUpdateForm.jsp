				
<%-- <c:set var="companyName"><sec:authentication property="principal.merchant.businessName"/></c:set>
<c:set var="companyWebsite"><sec:authentication property="principal.merchant.businessUrl"/></c:set>
<c:set var="street" value="${contact.addressAdapter.address.street }" />
<c:set var="city" value="${contact.addressAdapter.address.city }" />
<c:set var="state" value="${contact.addressAdapter.address.state.state }" />
<c:set var="zipcode" value="${contact.addressAdapter.address.zipcode }" />
<c:set var="phone" value="${contact.phone.phoneNumber}" />
 --%>


<c:set var="business"><sec:authentication property="principal.merchant.businessName"/></c:set>
<c:set var="website"><sec:authentication property="principal.merchant.websiteUrl"/></c:set>

<c:set var="phone" value="${profile.phone.number}"></c:set>
<c:set var="id" value="${profile.address.id}" />
<c:set var="name" value="${profile.address.name }" />
<c:set var="stage" value="${profile.address.state }" />
<c:set var="street" value="${profile.address.street }" />
<c:set var="city" value="${profile.address.city }" />
<c:set var="state" value="${profile.address.state.state }" />
<c:set var="zipcode" value="${profile.address.zipcode }" />
<spring:url var="updateMerchantInfo" value="/user/update-merchant-account-info" htmlEscape="true"/>
<form id="updateMerchantInfo" method="POST" action="${updateMerchantInfo }">
<div class="blk-b">
	<div class="panel-header">
		<h3>
			<span>Your Business</span>
		</h3>
	</div>
	<div id="displayBlk" class="p10"></div>
	<div class="blk-content modal-content">
		<p>Your business infomation is displayed publicly and used for searching, shipping, and analyzing. Make sure your business information appears correctly is the first essential step to grow your business.</p>
		<br/>
		<table class="p10">
			<tr>
				<td class="c-20"><label><strong>Business name</strong></label></td>
				<td class="c-80">
					<input name="_company" type="text" value="${business}" placeholder="Business Name">
				</td>
			</tr>
			<tr>
				<td><label><strong>Website</strong></label></td>
				<td>
					<input name="_website" value="${website }" type="text" placeholder="Your website">
				</td>
			</tr>
			<tr>
				<td><label><strong>Phone</strong></label></td>
				<td>
					<input name="_phone" value="${phone }" type="text" placeholder="Phone"/>
				<p class="tip"></p>
			</td>
		</tr>
		<tr>
			<td><label><strong>Address</strong></label></td>
			<td>
				<div>
				<input value="${street }" type="text" name="_street" placeholder="Street">
				</div><br/>
				<input value="${city }" class="c-30" type="text" name="_city" placeholder="City">
				<select id="addressState" class="selectFormat c-30" data-select="${state}" name="_state">
					<jsp:include page="../../../../../global/modules/address.jsp"></jsp:include>
				</select>
				<input value="${zipcode }" class="c-30" type="text" name="_zipcode" placeholder="Zipcode">
				</td>
			</tr>
		</table>
	</div>
	<div class="blk-footer prefix-clear">
		<button class="toRight plainBtn btn"><strong>Update</strong></button>
	</div>
</div>
</form>



<script type="text/javascript">
$(document).ready(function() {

	
	
	var $form = $("#updateMerchantInfo");

	var $displayBlk = $form.find("#displayBlk");
	$displayBlk.hide();
	$form.on("submit", function(event){
		var companyFlag = companyInfo();
		var addressFlag = addressInfo();
		var phoneFlag = phoneInfo();
		
		 if(companyFlag && addressFlag && phoneFlag) {
			var serializedData = $(this).serialize();
			var urlForm = $(this).attr("action");
			$.ajax({
 				url:urlForm,
 				data: serializedData,
 				method: "POST",
 				success: function(data){
 					if(data.state) {
 						showConfirm(["Updated successfuly"]);
 					} else {
 						showError(flage, ["Unable to complete update"]);
 					}
 				}, 
 				error: function(data) {
					showError(flage, ["Unable to complete update"]);
 				}
 			});	  
		} else {
			showError(false, ["Invalid input value"]);
		}
		event.preventDefault();
	});
	

	;function showConfirm(msg) {
		$displayBlk.empty().removeClass();
		$displayBlk.addClass("blk-green")
					.addClass("p10")
					.append("<p>").append(msg).append("</p>");
		$displayBlk.show();
	}
	;function showError(flag, err) {
		$displayBlk.empty().removeClass();
		
		if(flag) {
			$displayBlk.hide();
		} else {
			$displayBlk.addClass("blk-red").addClass("p10");
			for(var i = 0, l = err.length; i < l; i++ ) {
			    $displayBlk.append("<p>").append("<i class=\"fa tred fa-exclamation-triangle\"></i>&nbsp;&nbsp;").append(err[i]).append("</p>");
			}
			$displayBlk.show();
		}
	}
	
	
	
	
	;function companyInfo() {
		var $_a = $form.find("input[name=_company]");
		var a = $_a.val().length >= 3;
		highlightError($_a, a);
		return a;
	}
	
	;function phoneInfo() {
		var $_a = $form.find("input[name=_phone]");
		var a = $_a.val().length >= 10;
		highlightError($_a, a);
		return a;
	}

	;function addressInfo() {
		var $_a = $form.find("input[name=_street]");
		var $_b = $form.find("input[name=_city]");
		var $_c = $form.find("select[name=_state]");
		var $_d = $form.find("input[name=_zipcode]");
		
		var a = $_a.val().length > 0;
		var b = $_b.val().length > 0;
		var c = $_c.val() !== "0";
		var d = $_d.val().length > 0;

		highlightError($_a, a);
		highlightError($_b, b);
		highlightError($_c, c);
		highlightError($_d, d);
		
		return a && b && c && d;
	}


	function highlightError($a, a) {
		if(a) {
			$a.removeClass("error");
		} else {
			$a.addClass("error");
		}
	}
});
</script>