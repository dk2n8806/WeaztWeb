
<c:set var="customerId"><sec:authentication property="principal.id"/></c:set>

<c:if test="${not empty profile }">
	<c:set var="name"  value="${profile.address.name }"/>
	<c:set var="street"  value="${profile.address.street }"/>
	<c:set var="city" 	 value="${profile.address.city }"/>
	<c:set var="state" 	 value="${profile.address.state.state }"/>
	<c:set var="zipcode" value="${profile.address.zipcode }"/>
	
	
	<c:set var="gender"  value="${profile.gender.code }"/>
	
	<c:set var="phone" value="${profile.phone.phoneNumber }"/>
	
	<c:set var="date" value="${profile.birthDate.date }"/>
	<c:set var="month" value="${profile.birthDate.month }"/>
	<c:set var="year" value="${profile.birthDate.year }"/>
</c:if>



<spring:url var="updateAccountInfo" value="/user/update-profile" htmlEscape="true"></spring:url>
<form id="updateAccountInfo" method="POST" action="${updateAccountInfo }">
<div class="blk-b">
	<div class="panel-header">
		<h3 class="ftrend">
			<!-- <span class="bhi twhite"><i class="fa blue fa-user"></i></span> -->
			<span>Private Profile</span>
		</h3>
	</div>
	<div id="displayBlk" class="p10"></div>
	<div class="blk-content modal-content">	
		<p class="tgray11">
			<span><i class="fa fa-lock"></i></span>
			<span>Your profile will never be shared with others.</span>
			<span>We use it for communication and make the app more custom for you.</span>
		</p>
		<br/>
		<table class="p10">
			<tr>
				<td class="c-20"><label><strong>Your name</strong></label></td>
				<td class="c-80">
					<input name="_name" type="text" placeholder="Full name" value="${name}"/>
				</td>
			</tr>
			<tr>
				<td><label><strong>Gender</strong></label></td>
				<td>
					<select class="selectFormat" data-select="${gender }" name="_gender">
						<option value="0">Select Gender</option>
						<option value="male">Male</option>
						<option value="female">Female</option>
						<option value="other">Prefer not say</option>
					</select>
					<!-- 
					<p class="tip"><i class="fa fa-lock"></i>&nbsp;We use this data for analysis and never share it with others.</p>
					 -->
				</td>
			</tr>
			<tr>
				<td><label><strong>Phone</strong></label></td>
				<td>
					<input name="_phone" value="${phone }" type="text" placeholder="Phone"/>
					<!-- 
					<p class="tip"><i class="fa fa-lock"></i>&nbsp;We use this data for analysis and never share it with others.</p>
					 -->
				</td>
			</tr>
			<tr>
				<td>
					<label><strong>Address</strong></label>
				</td>
				<td>
					<div>
						<input value="${street }" type="text" name="_street" placeholder="Street">
					</div>
					<br/>
					<input value="${city }" class="c-30" type="text" name="_city" placeholder="City">
					<select id="addressState" class="selectFormat" data-select="${state}" name="_state" class="c-30">
						<jsp:include page="../../../../../global/modules/address.jsp"></jsp:include>
					</select>
					<input value="${zipcode }" class="c-20" type="text" name="_zipcode" placeholder="Zipcode">
					<!-- 
					<p class="tip">
						<span>We only share your address to the merchant when they process your order.</span>
					</p>
					 -->
				</td>
			</tr>
			<tr>
				<td><label><strong>Birthday</strong></label></td>					
				<td>
					<select  class="selectFormat" data-select="${month }" name="_month" id="bthMonth" class="c-30">
						<option value="0">Month</option>
						<script type="text/javascript">
							var i = 13;
							var $a = $("#bthMonth");
							var date = $a.data("select");
							while(i-- > 1) {
								if(date == i) {
									$a.append("<option selected value='"+i+"'>"+ i + "</option");
								} else  {
									$a.append("<option value='"+i+"'>"+ i + "</option");
								}
							}
						</script>
					</select>
					<select class="selectFormat" data-select="${date }" name="_date" id="bthDate" class="c-30">
						<option value="0">Date</option>
						<script type="text/javascript">
							var i = 32;
							var $a = $("#bthDate");
							var date = $a.data("select");
							while(i-- > 1) {
								if(date == i) {
									$a.append("<option selected value='"+i+"'>"+ i + "</option");
								} else  {
									$a.append("<option value='"+i+"'>"+ i + "</option");
								}
							}
						</script>
					</select>
					<select class="selectFormat" data-select="${year}" id="bthYear" name="_year" class="c-30">
						<option value="0">Year</option>
						<script type="text/javascript">
							var date = new Date();
							var i = date.getFullYear();
							var m = i - 100;
							var $a = $("#bthYear");
							var date = $a.data("select");
							while(i-- > m) {
								if(date == i) {
									$a.append("<option selected value='"+i+"'>"+ i + "</option");
								} else  {
									$a.append("<option value='"+i+"'>"+ i + "</option");
								}
							}
						</script>
					</select>
					<!-- 
					<p class="tip"><i class="fa fa-lock"></i>&nbsp;We use this data for analysis and never share it with others.</p>
					 -->
				</td>
			</tr>
		</table>
	</div>
	<div class="blk-footer prefix-clear">
		<button class="plainBtn toRight btn"><strong>Update</strong></button>
	</div>
</div>
</form>


<script type="text/javascript">
$(document).ready(function() {
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
	
	

	var $displayBlk = $("#displayBlk");
	$displayBlk.hide();

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
			    $displayBlk.append("<p>").append("<i class=\"fa tred fa-exclamation-triangle\"></i>&nbsp;").append(err[i]).append("</p>");
			}
			$displayBlk.show();
		}
	}
	
	
	
	
	
	var $form = $("#updateAccountInfo");
	$form.on("submit", function(event) {
		var accFlag = accountInfo();
		var addFlag = addressInfo();
		var birFlag = birthDayInfo();
		
		
		if(accFlag && addFlag && birFlag) {
			var serializedData = $(this).serialize();
			var urlForm = $(this).attr("action");
			$.ajax({
 				url:urlForm,
 				data: serializedData,
 				method: "POST",
 				success: function(data){
 					if(data.state) {
 						showConfirm(["Successful update account"]);
 					} else {
 						serverResponse(["Sorry, server error. Please retry to complete the tasks"], "red");
 					}
 				}, 
 				error: function(data) {
 					serverResponse(["Something is going wrong"], "red");
 				}
 			});	   
		} else {
			showError(false, ["Invalid request - please fill out necessary fields"]);
		}
		event.preventDefault();
	});
	
	;function accountInfo() {
		var $_username = $form.find("input[name=_username]");
		var $_email = $form.find("input[name=_email]");
		var flag = false;
		if($_username.val() !== "" && $_email.val() != "") {
			$_username.removeClass("error");
			$_email.removeClass("error");
			flag = true;
		} else {
			if($_username.val() === "") {
				$_username.addClass("error");
			}
			if($_email.val() === "") {
				$_email.addClass("error");
			}
			flag = false;
		} 
		return flag;
	}
	
	;function addressInfo() {
		var $_street = $form.find("input[name=_street]");
		var $_city = $form.find("input[name=_city]");
		var $_state = $form.find("select[name=_state]");
		var $_zipcode = $form.find("input[name=_zipcode]");
		
		var aFlag = false;
		var bFlag = false;
		var cFlag = false;
		var dFlag = false;

		if($_street.val() === "") {		aFlag = false;
		} else {						aFlag = true;
		}

		if($_city.val() === "") {	bFlag = false;
		} else {					bFlag = true;
		}
		if($_state.val() === "0") {	cFlag = false;
		} else {					cFlag = true;
		}

		if($_zipcode.val() === "") {	dFlag = false;
		} else {						dFlag = true;
		}
		//alert(aFlag + " " + bFlag + " " + cFlag);

		if((aFlag && bFlag && cFlag && dFlag) 
			|| (!aFlag && !bFlag && !cFlag && !dFlag)) {	
			$_street.removeClass("error");	
			$_city.removeClass("error");	
			$_state.removeClass("error");	
			$_zipcode.removeClass("error");
			return true;
		} else {
			if(!aFlag) {	$_street.addClass("error");
			} else {		$_street.removeClass("error");
			}
			if(!bFlag) {	$_city.addClass("error");
			} else {		$_city.removeClass("error");
			}		
			if(!cFlag) {	$_state.addClass("error");
			} else {		$_state.removeClass("error");
			}
			if(!dFlag) {	$_zipcode.addClass("error");
			} else {		$_zipcode.removeClass("error");
			}

		} 
		return false;
	}

	
	;function birthDayInfo() {
		var $_date = $form.find("select[name=_date]");
		var $_month = $form.find("select[name=_month]");
		var $_year = $form.find("select[name=_year]");
		
		var aFlag = false;
		var bFlag = false;
		var cFlag = false;

		if($_date.val() === "0") {		aFlag = false;
		} else {						aFlag = true;
		}

		if($_month.val() === "0") {	bFlag = false;
		} else {					bFlag = true;
		}
		if($_year.val() === "0") {	cFlag = false;
		} else {					cFlag = true;
		}

		//alert(aFlag + " " + bFlag + " " + cFlag);
		if((aFlag && bFlag && cFlag) 
			|| (!aFlag && !bFlag && !cFlag)) {	
			$_date.removeClass("error");	
			$_month.removeClass("error");	
			$_year.removeClass("error");
			return true;
		} else {
			if(!aFlag) {	$_date.addClass("error");
			} else {		$_date.removeClass("error");
			}
			if(!bFlag) {	$_month.addClass("error");
			} else {		$_month.removeClass("error");
			}		
			if(!cFlag) {	$_year.addClass("error");
			} else {		$_year.removeClass("error");
			}			
		}
		return false;	
	}
});
</script>