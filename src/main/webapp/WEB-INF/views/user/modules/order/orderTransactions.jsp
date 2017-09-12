<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Orders - Weazt</title>

<style type="text/css">
.mainPage {
	min-height: 300px;
}
</style>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>

<jsp:include page="../../components/user-navigation.jsp"></jsp:include>


<div class="mainPage">
<div class="main-content">	
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="nav/order_nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">		
			<div class="col-wrapper">
				<div class="col">	
					<div class="blk">
						<div class="blk-header">
							<h1 class="fnor">Order Transactions</h1>
						</div>
					</div>
				</div>
			</div>
			<div class="col-wrapper">
				<jsp:include page="modules/orderHistory/orderHistoryList.jsp"></jsp:include>
				
			</div>
		</div>
	</div>
</div>
</div>




<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function() {


	/* $(".status").each(function(){
		var $this = $(this);
		var s = $this.data("status");
		var str = "";
		if(s === "pending") {
			str = "<span class=\"\">Pending</span>";
		} 
		if(s === "completed") {
			str = "<span class=\"\"><i class=\"fa fa-check-circle\"></i></span>&nbsp;&nbsp;Completed";
		}
		$this.html(str);
	});
	 */
	
	;function displayOverviewData() {
		var $a = $("#overview");
		var s = $a.data("status");
		if(typeof s == 'undefined') {
			s = "all";
		}
		assignStatus(s.toLowerCase());
		function assignStatus(i) {
			var $a =  $("#" + i + "Tab");
			if($a.length == 1) {
				$a.addClass("active");
			} else {
				$("#allTab").addClass("active");
			}
		}
	};	displayOverviewData();
	 
	 
	
	$(".orderHeader").each(function() {
		var status = $(this).data("status");
		var date = $(this).data("date");
		
		var id = $(this).data("id");
		var str = "";
		if(status == "completed") {
			str = "<span class=\"tgreen bhi\"><i class=\"fa fa-paper-plane-o\"></i>" + status + "</span>";
		} else {
			str = "<span class=\"tred bhi\"><i class=\"fa fa-paper-plane-o\"></i>" + status + "</span>";
		}
		str += "&nbsp;|&nbsp;<span class=\"tip\">Transaction: <strong>"+id+"</strong></span>";
		str += "&nbsp;|&nbsp;<span class=\"tip\">On: <strong>"+ date+"</strong></span>";
		$(this).html(str);
	});
	
	function init() {
		$("#dbTaskNav").addClass("active");
		$("#orderHistoriesLink").addClass("active");
	}; init();
});
</script>
</body>
</html>