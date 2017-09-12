<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Dashboard - Weazt</title>
<style type="text/css">
.noList .no {
	padding: 10px;
	border-bottom: 1px solid #f3f3f3;
}
/* 
.noList .no .fa {
	font-size: 10px;
}
.noList .no p {
	font-size: 13px;
} */
.noList .no:last-child {
	border-bottom: 1px solid transparent;
}
</style>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>

<jsp:include page="../../components/user-navigation.jsp"></jsp:include>
<div class="main-content">
	<div class="blk-container blkl">
		<div class="sblk">
			<div class="col-wrapper">
				<div class="col">
					<jsp:include page="modules/main-profile.jsp"></jsp:include>
				</div>
				<div class="col">
					<jsp:include page="modules/profile-verification.jsp"/>
				</div>
			</div>
		</div>
		<div class="mblk">
			<div class="col-wrapper">
				<div class="col">
					<div class="blk-b">
						<div class="panel-header">
							<h3><span>Notifications</span>
							</h3>
						</div>
						<div class="blk-content">
							<div class="noList">
								<jsp:include page="modules/merchantOrderNos.jsp"></jsp:include>
								<jsp:include page="modules/accountVerificationNos.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<c:if test="${not empty bookmarks }">
			<div class="col-wrapper">
				<div class="col">
					<div class="blk">
						<div class="blk-header">
							<spring:url value="/user/bookmark" var="bookmarkLink" htmlEscape="true"></spring:url>
							<h2 class="fnor">Recent Bookmarks</h2>
							&nbsp;&nbsp;
							<span><a class="link"   href="${bookmarkLink }">all bookmarks</a></span>
						</div>
					</div>		
				</div>
				<jsp:include page="modules/bookmarks.jsp"></jsp:include>
			</div>
			</c:if>
		</div>
	</div>
</div>



<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	
	function checkNotification() {
		var $a = $(".noList");
		if($a.children().length == 0) {
			$a.html("<p>You have no activity at this time!</p>");
		}
	}; checkNotification();
	
	
	var months = [ "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" ];

	$(".dateFormat").each(function() {
		var val = $(this).data("date");
		var date = new Date(val);
		$(this).html(months[date.getMonth()] + " " + date.getDate() + ", " + date.getFullYear());
	});
	
	$("#dbDashboardNav").addClass("active");
});
</script>
</body>
</html>