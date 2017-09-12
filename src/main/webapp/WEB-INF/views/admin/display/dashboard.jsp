<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Admin | Mingofy</title>
<style type="text/css">
.square {
	padding: 5px 10px;
	color: #fff;
	background: #008bbb;
	border-radius: 5px;
}

.main-content {
	width: 800px;
}
</style>
</head>
<body>
<jsp:include page="../../components/header-navigation.jsp"></jsp:include> 
<br/><br/>



<div class="main-content">
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="../nav/adminNavigations.jsp"></jsp:include>
		</div>
		<div class="olymArena">
			<div class="col-wrapper">
				<c:forEach var="entity" items="${entities}">
					<c:set var="key" value="${entity.key }" />
					<c:set var="value" value="${entity.value}" />
					<div class="col-4">
						<div class="blk-b">
							<div class="p10">
								<h4 class="ftrend">
									<strong><span class="square">${value}</span></strong>
									&nbsp;&nbsp;
									<span class="fontCap">${key}</span>
								</h4>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$(".square").each(function() {
		var a = parseFloat($(this).text()) | 0;
		if( a > 0) {
			$(this).css({background: "red"});
		}
	});
});

</script>
</body>
</html>