<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Orders - Weazt</title>
<style type="text/css">
.trans {
	width: 100%;
}

.trans td {
	vertical-align: top;
}
</style>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>

<jsp:include page="../../components/user-navigation.jsp"></jsp:include>



<c:set var="productId" value="${order.orderBundle.product.id }" />
<c:set var="productTitle" value="${order.title }" />
<spring:url value="/user/listings/${productId }" var="productLink" htmlEscape="true" />

<div class="main-content">	
	<div class="col-wrapper">
		<div class="col">
			<div class="blk">
				<div class="blk-header">
					<h1 class="fnor">Order Bundle Transaction Details</h1>
					<h3 class="fnor">/ ${productTitle }</h3>
				</div>
			</div>		
		</div>
		<div class="col">
			<jsp:include page="modules/orderTransaction/transactionListing.jsp"></jsp:include>
		</div>
		<div class="col">
			<div class="blk">
				<div class="blk-header">
					<h2 class="fnor">Shipment Details</h2>
					<p>Purchase a recommended shipping label or use your own label and input the tracking number to complete the shipments.</p>
				</div>
			</div>		
		</div>
		<div class="col75">
			<jsp:include page="modules/orderTransaction/transactionCustomerListing.jsp"></jsp:include>
		</div>
	</div>
</div>

<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	/* 
	$(".productTitle").each(function() {
		var $this = $(this);
		var data = $this.data("title");
		var url = $this.attr("href") + "?" + data.replace(/ /g,"_");
		$this.attr("href", url);
	});
	 */
	

	$(".orderStatus").each(function(){
		var $this = $(this);
		var s = $this.data("status");
		var str = "";
		if(s === "pending") {
			str = "<span class=\"\">Pending</span>";
		} 
		else if(s === "completed") {
			str = "<span class=\"tgreen\"><i class=\"fa fa-check-circle\"></i></span>&nbsp;&nbsp;Completed";
		}
		$this.html(str);
	});
	
	
	
	function init() {
		$("#dbTaskNav").addClass("active");
		$("#orderHistoriesLink").addClass("active");
	}; init();
});
</script>
</body>
</html>