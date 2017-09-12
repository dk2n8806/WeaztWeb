<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>Account - Weazt</title>

</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>


	
	
<div class="main-content">
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="nav/setting-nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">
			<div class="col-wrapper">
				<div class="col">
					<jsp:include page="modules/account/accountInfo.jsp"></jsp:include>
				</div>	
				<div class="col">
					<jsp:include page="modules/account/shippingInfo.jsp"></jsp:include>
				</div>		
			</div>
		</div>
	</div>
</div>

<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
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

	

	
	
	$("#settingAccountNav").addClass("active");
	$("#dbSettingNav").addClass("active");
});
</script>
</body>
</html>