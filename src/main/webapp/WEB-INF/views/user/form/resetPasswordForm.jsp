<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Weazt | Reset your password</title>
</head>
<body>

<jsp:include page="../../components/header-navigation.jsp"></jsp:include>
<br/>
<br/>

<div id="mainBlk" class="main-content mx700">
	<div class="blk-b">
		<div class="panel-header">
			<h3 class="fx1-4">Reset your password?</h3>
		</div>
		<div id="serverResponse"></div>
		<div class="blk-content">
			<spring:url value="/request-reset-password" var="resetForm" htmlEscape="=true" />
			<form id="resetForm" action="${resetForm }" method="POST">
				<div class="modal-content">
					<table>
					
					<tr>
						<td>
							<p>New password</p>
							<input type="password" name="_np" />
						</td>
					</tr>
					<tr>
						<td>
							<p>Confirm password</p>
							<input type="password" name="_cp" />
						</td>
					</tr>
					</table>
				</div>
				<div class="modal-footer">
					<input type="hidden" name="_t" value="${tokenId}" />
					<button class="btn"><strong>Submit</strong></button>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="confirmBlk" class="main-content mx700" style="display: none">
	<div class="blk">
		<div class="blk-content">
			<spring:url value="/login" var="loginLink" htmlEscape="true"></spring:url>
			<p>Your Weazt password has been changed. <a class="link" href="${loginLink }">Click here</a> to sign in.</p>
		</div>
	</div>
</div>

<jsp:include page="../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	var $f = $("#resetForm");
	$f.on("submit", function(event){
		var _np = $f.find("input[name=_np]");
		var _cp = $f.find("input[name=_cp]");
		var serializedData = $f.serialize();
	    var urlForm = $f.attr("action");
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					success();
				} else {
					fail();
				}
			}, 
			error: function(data) {
			}
		}); 
		event.preventDefault();
	});

	function success() {
		$("#mainBlk").hide();
		$("#confirmBlk").show();
	}
	
	function fail() {
		var str = "<div class=\"blk-red blk-content\">Fail hard</div>";
		$r.html(str);
	}
});

</script>
</body>
</html>