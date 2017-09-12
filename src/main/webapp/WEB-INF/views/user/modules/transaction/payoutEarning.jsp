<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Payout - Weazt</title>

<style type="text/css">

.el {
	border : 1px solid #dbdbdb;
}

.el li {
	width: 100%;
	border-bottom: 1px solid #fafafa;
}

.el li:last-child {
	border-bottom: none;
}

.et {
	background: #dedede;
	padding: 10px;
	font-size: 16px;
	font-weight: bold;
}

.el tr {
	border-bottom: 1px solid #dbdbdb;
}


.ec {
	display: none;
}

</style>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>


<!-- 		model.addAttribute("totalBalance", balance);
		model.addAttribute("earnedBalance", earned);
		model.addAttribute("pendingBalance", pending);
		model.addAttribute("serviceFee", serviceFee);
		model.addAttribute("size", size);
		model.addAttribute("payouts", payouts);
		model.addAttribute("interval", dateInterval); -->
		
<c:set var="totalBalance">
	<fmt:formatNumber value="${totalBalance / 100}" maxFractionDigits="2"/>
</c:set>
<c:set var="pendingBalance">
	<fmt:formatNumber value="${pendingBalance / 100}" maxFractionDigits="2"/>
</c:set>

<c:set var="netBalance">
	<fmt:formatNumber value="${(earnedBalance - serviceFee) / 100}" maxFractionDigits="2"/>
</c:set>
<c:set var="totalFee">
	<fmt:formatNumber value="${serviceFee / 100}" maxFractionDigits="2"/>
</c:set>
<c:set var="fromDate">
	<fmt:formatDate value="${interval.getFrom() }"/>
</c:set>
<c:set var="toDate">
	<fmt:formatDate value="${interval.getTo() }"/>
</c:set>
	
<div class="main-content">
	<div class="olym-wrapper">
		<div class="olymLeft">	
			<jsp:include page="../setting/nav/setting-nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">
			<div class="col-wrapper">			
				<div class="col">
					<jsp:include page="nav/payoutNav.jsp"></jsp:include>
				</div>
				<div class="col">
					<div class="blk-b">	
						<div class="panel-header">
							<h3><span>Your Earnings</span></h3>
						</div>
						<div class="blk-content">
							<div class="prefix-clear">
								<div class="toRight">
									<ul class="inline">
										<li class="menu-wrapper">
											<span class="btn"><span><i class="fa fa-calendar"></i></span>&nbsp;&nbsp;Current statement&nbsp;&nbsp;<i class="fa fa-caret-down"></i></span>
											<div class="menu">
												
												<ul class="menu-content" style=" overflow-y: scroll; max-height: 200px;">
													<spring:url value="/user/payout/earnings" var="currStaLink" htmlEscape="true"></spring:url>
													<li><a href="${currStaLink}">Current statement</a></li>
												</ul>
											</div>
										</li>
									</ul>
								</div>
								<br/>
								<p>Here is your earning from <strong class="tblue">${payoutSize} order bundles</strong> by <strong class="tblue">${toDate }</strong></p>
							</div>
							<br/>
							<ul class="el">
								<li>
									<jsp:include page="modules/payout/earningListing.jsp" />
								</li>
								<li>
									<jsp:include page="modules/payout/labelServiceFee.jsp" />
								</li>
								<li>
									<c:set var="saleFee">
										<fmt:formatNumber value="${salesFee / 100 }" maxFractionDigits="2" />
									</c:set>
									<div class="et">
										<span class="toRight">-$${saleFee }</span>
										<p>Commission Fee</p>
									</div>
								</li>
							</ul>
							<br/>
							<div>
								<c:set var="estimatedBalance">
									<fmt:formatNumber value="${estimatedBalance / 100}" maxFractionDigits="2"></fmt:formatNumber>
								</c:set>
								<h2 class="fnor c-100"><span class="toRight">$${estimatedBalance}</span>Estimated Balance</h2>
							</div>
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
	
	$(".status").each(function(){
		var status = $(this).data("status").toLowerCase();
		var str = status;
		if(status === "pending") {
			str = "<span class=\"tgray\"><i class=\"fa  fa-check-circle\"></i>&nbsp;&nbsp;Pending</span>";
		} else if(status === "deposited") {
			str = "<span class=\"tgreen\"><i class=\"fa fa-check-circle\"></i>&nbsp;&nbsp;Deposited</span>";
		}
		$(this).html(str);
	});
	
	$(".statOverview").each(function() {
		var s = $(this).data("size");
		var t  = $(this).data("total");
		var n = $(this).data("name");
		if(s > 1) {
			n += "s";
		}
		$(this).html("Show " + s + " of <strong class=\"tblue\">" + t + " " + n + "</strong>");
	});
	
	
	var date  = new Date("${interval.getFrom() }");
	var month = date.getMonth();
	var year = date.getFullYear();
	var i = 0;
	var months = ["January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
	
	while(i < 12) {
		var url = "${pageContext.request.contextPath}/user/payout/earnings?_m=" + (i + 1) + "&_y=" + year;
		var str = "<li><a href=\""+ url +"\">" + months[i] + "</a></li>";
		$("#month").append(str);
		i++;
	}
	
	i = new Date().getFullYear();
	var k = 0;
	while(k++ < 10) {
		var url = "${pageContext.request.contextPath}/user/payout/earnings?_m=" + (month + 1) + "&_y=" + i;
		var str = "<li><a href=\""+ url +"\">" + i + "</a></li>";
		$("#year").append(str);
		i--;
	}
	$("#monthDisplay").html(months[month]);
	$("#yearDisplay").html(year);
	
	
	
	
	
	
	
	
	

	$(".et").on("click", function() {
		var $a = $(this).siblings(".ec");
		$a.toggle();
	});
	
	
	
 	function init() {
		$("#payoutEarningNav").addClass("active");
 		$("#settingPayoutNav").addClass("active");
 	}; init();
});
</script>
</body>
</html>