<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<jsp:include page="../base/headerMetaLink.jsp"></jsp:include>
<title>${merchant.merchantName}'s profile - Weazt</title>
<style type="text/css">
.noList .no {
	padding: 7px;
	border-bottom: 1px solid #c7c7c7;
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
<jsp:include page="../components/header-navigation.jsp"></jsp:include>
<br/>
<div class="main-content">
	<div class="blk-container blkl">
		<div class="sblk">
			<div class="col-wrapper">
				<div class="col">
					<jsp:include page="profile/profileImage.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<div class="mblk">
			<div class="col-wrapper">
				<div class="col">
					<jsp:include page="profile/profileInfo.jsp"></jsp:include>
				</div>
				<jsp:include page="profile/product.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {

	 ;function fancyCall() {
		$(".fancy-btn").fancybox({
			padding: 0,
			fitToView: false,
	    	autoWidth: true, 
		    width: "700", 
		    maxWidth: "700", 
		    minWidth: "500", 
		    maxHeigh: "90%",
		    margin: [0, 0, 150, 0],
	        openEffect: 'none',
	        closeEffect: 'none',
	        nextEffect: 'none',
	        prevEffect: 'none',
	        scrolling: 'no',
	        nextSpeed: 0,
	        prevSpeed: 0,
	        preload: 3,
			helpers: {
				overlay : {
					css : {
						'background' : 'rgba(47, 44, 42, 0.80)'
					},
					closeClick: false
				}
			},
			'beforeLoad': function() {
			},
			'afterClose': function() {
			} 
		});
	}; fancyCall();

	
});
</script>
</body>
</html>