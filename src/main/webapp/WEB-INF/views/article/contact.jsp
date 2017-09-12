<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../base/headerMetaLink.jsp"></jsp:include>
<title>Contact Us | Weazt</title>
<style type="text/css">
.main-content {
	width: 1000px;
}
</style>
</head>
<body>
<jsp:include page="../components/header-navigation.jsp"></jsp:include>


<div class="purple1 twhite main-container">
	<div class="main-content toCenter">
		<div class="p40">
			<h1 class="ftrend">Contact Us</h1>
		</div>
	</div>
</div>
 <br/>
<div class="main-content">	
	<div class="olym-wrapper">
		<div class="olymLeft">			
			<jsp:include page="nav/aboutNav.jsp"></jsp:include>
		</div>
		<div class="olymArena">	
			<div class="col-wrapper">
				<div class="col">
					<div class="blk">
						<div class="blk-content">
						<div><h1 class="fnor">Ask a Question</h1></div>
						<div><p>Send us an message and receive personal assitance from a Weazt Support Agent</p></div>				
						</div>
					</div>
						<c:choose>
						<c:when test="${not empty pageContext.request.userPrincipal}">
							<c:set var="prinName"><sec:authentication property="principal.username"/></c:set>
							<c:set var="prinEmail"><sec:authentication property="principal.email"/></c:set>
							<c:set var="prinId"><sec:authentication property="principal.id"/></c:set>
						
							<spring:url value="/contact_us/send-message" var="contactForm" htmlEscape="true"></spring:url>
							<div style="width: 600px;">
							<form id="contactForm" action="${contactForm}" method="POST">
							<div class="response"></div>
								<div class="modal-content">
									<table class="p10">
										<tr>
											<td colspan="2">
												<label><strong>Subject</strong></label>
												<input type="text" name="_s"  />
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<label><strong>Category</strong></label>
												<select name="_sc">
													<option value="0">Select category best describes your question</option>
													<c:forEach var="c" items="${categories }">
													<option value="${c.code }">${c.category }</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<label><strong>Please provide as many detail as possible.</strong></label>
												<textarea name="_m" ></textarea>
											</td>
										</tr>
									</table>
									<br/><br/>
									<button type="submit" class="btn flatBlueBtn">Submit</button>
								</div>
							</form>
							</div>
						</c:when>
						<c:otherwise>	
						<div class="blk-content">
							<p>Please log in to send a message. We can usually process your request more quickly if you log in.</p>
							<br/>
							<p>If you don't have a Weazt's account, you can email us directly at support@weazt.com.</p>
							<br/>
							<br/>
							<spring:url value="/login" var="loginLink" htmlEscape="true"></spring:url>
							<a class="flatGreenBtn btn twhite" href="${loginLink}">Login to contact us</a>
								
						</div>
						</c:otherwise>
					</c:choose>
				</div>
			
				
			</div>
		</div>
	</div>
</div>





<jsp:include page="../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	$("#contactForm").on("submit", function(event) {
		var $this = $(this);
		var $s = $($this.find("input[name=_s]"));
		var $c = $($this.find("select[name=_sc]"));
		var $m = $($this.find("textarea[name=_m]"));
		var $res = $($this.find(".response"));
		var $btn = $($this.find("button[type=submit]"));
		
		
		var s = ($s.val().length > 0) ? true : false;
		var c = ($c.val() > 0) ? true : false;
		var m = ($m.val().length > 0) ? true : false;
		
		displayError($s, s);
		displayError($m, m);
		displayError($c, c);
		
		
		if(c && s && m) {
			$btn.prop('disabled', true);
			$btn.prepend("<span class=\"loader\"><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;</span>");	
			var serializedData = $this.serialize();
		    var urlForm = $this.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				success: function(data){
					if(data.state) {
						successHandler($res);
					} else {
						errorHandler($res);
					}
				}, 
				error: function(data) {
				},
				complete: function (data) {
					$btn.prop('disabled', false);
					$btn.find(".loader").empty();
				}
			});  
		}
		event.preventDefault();
	});

	function successHandler($res) {
		var str = "";
		str += "<div class=\"blk-green p20\">We have received your message and will contact you shortly.</dib>";
		$res.html(str);
	}

	function errorHandler($res) {
		var str = "";
		str += "<div class=\"blk-red p20\">[ERROR] Unable to send the message.</dib>";
		$res.html(str);
	}
	
	function displayError($a, a) {
		if(a) {
			$a.removeClass("error");
		} else {
			$a.addClass("error");
		}
	}


	function validateEmail($email) { 
		var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return reg.test($email.val());
	};

	function init() {
		$("#commandPrimary li").eq(4).addClass("active");
	}; init();
});
</script>
</body>
</html>