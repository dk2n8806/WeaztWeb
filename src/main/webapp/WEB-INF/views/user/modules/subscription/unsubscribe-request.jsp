
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Subscriptions - Weazt</title>
<style type="text/css">
.main-content {
	width: 700px;
}
</style>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>


<jsp:include page="../../components/user-navigation.jsp"></jsp:include>

 
 
 
 <div id="mainContent" class="main-content" style="display: none1;">	
	<div class="col-wrapper">
		<div class="col">
			<div class="blk-b">
				<div class="panel-header yellow twhite">
					<h3>
						<span class="twhite bhi"><i class="fa fa-times"></i></span>
						<span class="">Unusbsribe Request</span>
					</h3>
				</div>
				<div class="blk-content">					
					<c:set value="${product.title}" var="productTitle" />
					<c:set value="${product.image}" var="productImage" />
					<c:set value="${product.category}" var="category" />
					
					<div class="card150 prefix-clear">
						<div class="card-face">
							<div class="card-holder">
								<img src="${productImage }">
							</div>
						</div>
						<div class="card-content">
							<div><h2 class="fnor">${productTitle }</h2></div>
							<p class="fontCategory"><i class="fa fa-tags"></i>&nbsp;${category }</p>
						</div>
					</div>
				</div>
			</div>
				
				
			<c:choose>
				<c:when test="${token.useable == false }">
					<div class="panel-footer toCenter p20">
						<p><strong>The token is already expired!</strong></p>
						<br/>
						<p>To get a new token, please go back and use to subscription manager to generate a new one.</p>
						<br/>
						<spring:url var="goBackLink" value="/user/subscriptions/${token.subscription.id}" htmlEscape="true"/>
						<p><a class="blue-btn" href="${goBackLink }"><i class="fa fa-arrow-circle-left"></i>&nbsp;&nbsp;Go back</a></p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="modal-content">			
						<spring:url value="/user/request/unsubscribe/subscription" var="unsubscribeRequestForm" htmlEscape="true"/>
						<form id="unsubscribeRequestForm" action="${unsubscribeRequestForm }" method="POST">
							
							 <h2 class="ftrend">Do you want to cancel this subscription?</h2>	
							 <p>Once you cancel this subscription, we will create a refund based on the remain shipments.</p>			
							 <p><i>(This operation cannot be undone)</i></p>
							 <br/>
							<div class="blk-b" style="width: 600px;">
								<div class="panel-header">
									<h3>Refund overview</h3>
								</div>
								<div class="blk-content">
									<br/>
									<p>
										<span class="fontCategory">Status:</span>
										&nbsp;<strong><span class="fontLower">${status}</span></strong>
									</p>
									<c:forEach var="entity" items="${params}">
										<c:set var="key" value="${entity.key }" />
										<c:set var="value" value="${entity.value}" />
										<p>
											<span class="fontCategory">${key}:</span>
											&nbsp;<strong><span>${value}</span></strong>
										</p>
									</c:forEach>
								</div>						
							</div>
							<br/>
							<%-- 
							<hr/>
							<br/>
							<h2 class="ftrend">Help us improve your experience</h2>
							<p>Please help Weazt's to make your experience more enjoyable by selecting one of the reason below:</p>
						
							<br/>
							<select name="_quote">
								<option value="-1">Please select...</option>
								<c:forEach items="${templates }" var="template">
									<option value="${template.code }">${template.quote}</option>
								</c:forEach>
							</select>
							<br/><br/><br/> 
							--%>
							<input type="hidden" name="_sid" value="${token.subscription.id }">
							<input type="hidden" name="_token" value="${token.token }">
							<div class="prefix-clear">
								<button type="submit" class="btn"><strong>Cancel subscription</strong></button>
							</div>
						</form>
					</div>
				</c:otherwise>
			</c:choose>			
		</div>
	</div>
</div>
 
 
<div id="successResponse" class="main-content" style="display: none">
	<div class="col-wrapper">
		<div class="col">
			<div class="blk-b">
				<div class="panel-header">
					<h3><span class="tgreen bhi"><i class="fa fa-check"></i>Successful</span></h3>
				</div>
				<div class="blk-content">
					<p><strong>The item is now 'UNSUBSCRIBED'.</strong></p>
					<br/>
					<p>We have received and process your unsubscribe request. Please allow 3-5 days for us to process your refund.</p>
				</div>
				<div class="blk-footer">
					<spring:url value="/user/subscriptions" var="backToSubscriptionLink" htmlEscape="true"></spring:url>
					<ul class="inline">
						<li><a class="btn" href="${backToSubscriptionLink}"><i class="fa fa-chevron-left"></i>&nbsp;&nbsp;Back to My Subscriptions</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
  
<div id="errorResponse" class="main-content" style="display: none">
	<div class="col-wrapper">
		<div class="col">
			<div class="blk-b">
				<div class="panel-header">
					<h3><span class="torange bhi"><i class="fa fa-exclamation-triangle"></i>Unsubscribe Error</span></h3>
				</div>
				<div class="blk-content">
					<p>The operations couldn't be completed. Please reload the page and try again</p>
					<br/>
					<a class="reloadBtn btn" href="#">Reload page</a>
				</div>
				<div class="blk-footer">
					<p class="tip">If the problem still continue, please contact us.</p>
				</div>
			</div>
		</div>
	</div>
</div>
 

 
<jsp:include page="form/dequeueSubscriptionForm.jsp"></jsp:include>




<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">

$(document).ready(function() {	
	$(".reloadBtn").click(function(event) {
	    location.reload();
		event.preventDefault();
	});
	
	$("#unsubscribeRequestForm").on("submit", function(event) {
		var $this = $(this);
		/*
		var $err = $this.find(".errorForm");
		$err.empty();
		var $_quote = $this.find("select[name=_quote]");
		var $_opt = $this.find("input[name=_opt]:radio:checked");
		var _quote = parseInt($_quote.val()) || 0; 
		var _opt = $_opt.val() || "";
		var flag = false;
		if(_opt === "") {
			errorHanlder($err, "Please select an unsubsribe option");
			flag = false;
		} else {
			$err.empty();
			flag = true;
		}
		if(_quote === -1) {
			$_quote.addClass("error");
			flag = false;
		} else {
			$_quote.removeClass("error");
			flag = true;
		}
	 	if(flag) {
			
	 	} else  {
	 	}  */
	 	
	 	var serializedData = $this.serialize();
		var urlForm = $this.attr("action");
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					$("#mainContent").hide();
					$("#successResponse").show();
				} else {
					$("#mainContent").hide();
					$("#errorResponse").show();
				}
			}, 
			error: function(data) {
			}
		}); 
		event.preventDefault();
	});
	 
	function errorHanlder($a, msg) {
		var str = "<div class=\"blk-red\"><div class=\"blk-content\">"+ msg + "</div></div>";		
		$a.html(str);
	}
});
</script>

</body>
</html>