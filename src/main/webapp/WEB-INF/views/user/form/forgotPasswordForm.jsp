<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Weazt | Forgot your password</title>
</head>
<body>

<jsp:include page="../../components/header-navigation.jsp"></jsp:include>
<br/>
<br/>
<div id="mainBlk" class="main-content mx700">
	<div class="blk-b">
		<div class="panel-header">
			<h3>Forgot your password?</h3>
		</div>
		<div id="serverResponse"></div>
		<div class="blk-content">
			<spring:url value="/request-password-token" var="forgotForm" htmlEscape="=true" />
			<form id="forgotForm" action="${forgotForm }" method="POST">
				<div class="modal-content">
					<p>Enter your email address and we'll send you a link to reset your password.</p>
					<input type="text" name="_email" />
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn"><strong>Submit</strong></button>
				</div>
			</form>
		</div>
	</div>
</div>

<div id="confirmBlk" style="display: none"  class="main-content mx700">
	<div class="blk-b">
		<div class="panel-header">
			<h3 class="fx0-2">OK!</h3>
		</div>
		<div class="blk-content">
			<p>Check <strong><span id="confirmEmail"></span></strong> for the confirmation email. 
				It will have a link to reset your password.</p>
		</div>
	</div>
</div>

<br/><br/><br/><br/><br/><br/><br/><br/>
<jsp:include page="../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	var emailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	
	var $f = $("#forgotForm");
	var $r = $("#serverResponse");
	$f.on("submit", function(event){
		var $email = $f.find("input[name=_email]");
		var $btn = $f.find("button[type=submit]");
		
	
		
		var eFlag = emailRegex.test($email.val()); //strHandler($email) && validateEmail($email);
		if(eFlag) {
			var serializedData = $f.serialize();
		    var urlForm = $f.attr("action");
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
						success($email.val());
					} else {
						fail("Sorry, there's no account associated with that email address");
					}
				}, 
				error: function(data) {
				},
				complete: function(data) {
					$btn.find(".loader").empty();
					$btn.attr("disabled", false);
				}
			}); 
			/* var serializedData = $f.serialize();
		    var urlForm = $f.attr("action");
		    
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
						success(email);
					} else {
						fail("Sorry, there's no account associated with that email address");
					}
				}, 
				error: function(data) {
				}, 
				complete: function(data) {
					alert("data" + data.state);
					$btn.find(".loader").empty();
					$btn.attr("disabled", false);
				}
			});  */
		} else {
			fail("Pleaes make sure your email is correct");	
		}
		event.preventDefault();
	});

	function success(email) {
		$("#mainBlk").hide();
		$("#confirmBlk").show();
		$("#confirmEmail").html(email);
	}
	
	function fail(str) {
		var str = "<div class=\"blk-red blk-content\">"+ str +"</div>";
		$r.html(str);
	}
	
});
</script>
</body>
</html>