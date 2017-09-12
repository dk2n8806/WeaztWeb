<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Payment - Weazt</title>

<style type="text/css">

</style>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>


<c:set var="fromDate">
	<fmt:formatDate value="${dateInterval.getFrom() }"/>
</c:set>
<c:set var="toDate">
	<fmt:formatDate value="${dateInterval.getTo() }"/>
</c:set>
<div class="main-content">
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="../setting/nav/setting-nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">
			<div class="col-wrapper">	
				<div class="col">
					<jsp:include page="nav/paymentNav.jsp" />
				</div>
				<div class="col">
					<div class="blk-b">
						<div class="panel-header">
							<h3><span>Payment transactions</span></h3>
						</div>
						<div class="blk-content modal-content">		
							<div class="prefix-clear">
								<ul class="inline toRight">
									<li class="menu-wrapper">
										<a class="btn">Month:&nbsp;<strong id="monthDisplay"></strong>&nbsp;&nbsp;<i class="fa fa-caret-down"></i></a>
										<div class="menu">
											<ul id="month" class="menu-content" style=" overflow-y: scroll; height: 150px;"></ul>
										</div>
									</li>
									<li class="menu-wrapper">
										<a class="btn">Year:&nbsp;<strong id="yearDisplay"></strong>&nbsp;&nbsp;<i class="fa fa-caret-down"></i></a>
										<div class="menu">
											<ul id="year" class="menu-content" style=" overflow-y: scroll; height: 150px;">
											</ul>
										</div>
									</li>
								</ul>
								<br/>
								<p>Here, you can view your payments of <strong class="tblue">${fromDate}</strong> - <strong class="tblue">${toDate}</strong></p>
						
							</div>
							<br/>
							<jsp:include page="modules/payment/transactionHistoryListing.jsp" />	
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>	






<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	var date  = new Date("${dateInterval.getFrom() }");
	var month = date.getMonth();
	var year = date.getFullYear();
	var i = 0;
	var months = ["January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
	
	while(i < 12) {
		var url = "${pageContext.request.contextPath}/user/payment/transactions?_m=" + (i + 1) + "&_y=" + year;
		var str = "<li><a href=\""+ url +"\">" + months[i] + "</a></li>";
		$("#month").append(str);
		i++;
	}
	
	i = new Date().getFullYear();
	while(i > 2000) {
		var url = "${pageContext.request.contextPath}/user/payment/transactions?_m=" + (month + 1) + "&_y=" + i;
		var str = "<li><a href=\""+ url +"\">" + i + "</a></li>";
		$("#year").append(str);
		i--;
	}
	$("#monthDisplay").html(months[month]);
	$("#yearDisplay").html(year);
	
	function init() {
		$("#paymentHistoryNav").addClass("active");
		$("#settingPaymentNav").addClass("active");
	}; init();
});
</script>
</body>


</html>