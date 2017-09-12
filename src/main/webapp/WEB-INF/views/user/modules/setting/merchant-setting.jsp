<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Account - Weazt</title>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>

<div class="navy twhite">
	<div class="p20">
		<h1 class="fnor">My Business</h1>
	</div>
</div>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>
<c:set var="prinMerchant"><sec:authentication property="principal.merchant"/></c:set>
					
<div class="main-content">
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="nav/setting-nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">
			
			<div class="col-wrapper">
			<c:choose>
				<c:when test="${not empty prinMerchant}">
				<div class="col">
				<jsp:include page="modules/merchant/merchantUpdateForm.jsp"></jsp:include>
				</div>
				<%-- 
				<div class="col">
					<jsp:include page="modules/merchant/taxForm.jsp"></jsp:include>
				</div> 
				--%>
				</c:when>
				<c:otherwise>
				<div class="col75">
					<jsp:include page="modules/merchant/merchantNotExist.jsp"></jsp:include>
				</div>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
</div>












<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
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
	
	/* var $form = $("#updateMerchantInfo");
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
 					alert("something wrong");
 				}
 			});	   
		} else {
			showError(false, ["Please make sure highlight inputs aren't empty"]);
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
			    $displayBlk.append("<p>").append("<i class=\"fa tred fa-exclamation-triangle\"></i>&nbsp;").append(err[i]).append("</p>");
			}
			$displayBlk.show();
		}
	}
	
	
	
	
	;function companyInfo() {
		var $_company = $form.find("input[name=_company]");
		if($_company.val() === ""
				|| $_company.val().length < 3) {
			$_company.addClass("error");
			return false;
		} else {
			$_company.removeClass("error");
			return true;
		}
	}
	
	;function phoneInfo() {
		var $_phone = $form.find("input[name=_phone]");
		if($_phone.val() === ""
				|| $_phone.val().length < 6) {
			$_phone.addClass("error");
			return false;
		} else {
			$_phone.removeClass("error");
			return true;
		}
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

		if(aFlag && bFlag && cFlag && dFlag)  {	
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
 */
	$("#settingMerchantNav").addClass("active");
	$("#dbSettingNav").addClass("active");
});
</script>
</body>
</html>