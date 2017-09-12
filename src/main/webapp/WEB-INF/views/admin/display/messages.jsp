<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Admin | Mingofy</title>
<style type="text/css">
.main-content {
	width: 700px;	
}
</style>
</head>
<body>

<jsp:include page="../../components/header-navigation.jsp"></jsp:include> 
<jsp:include page="../nav/adminNavigations.jsp"></jsp:include>
	
<br/>
<div class="main-content">
	<div class="col-wrapper">
		<div class="col">
			<h1 class="ftrend">Total Message: ${count }</h1>
		</div>
	</div>
	<div class="col-wrapper">
	
		<c:forEach var="message" items="${messages}">

			<c:set var="createdOn">
				<fmt:formatDate value="${message.createdOn }"/>
			</c:set>
			<c:set var="id" value="${message.id }" />
			<c:set var="accountId" value="${message.account.id }" />
			<c:set var="subject" value="${message.subject }" />
			<c:set var="message" value="${message.message }" />
	
			<div class="col">
				<div class="blk-b">
					<div class="panel-header navy twhite">
					
						<p class="toRight">${createdOn }</p>
						<p><strong>Subject:</strong></p>
						<p>${subject }</p>
					</div>
					<div class="blk-content">
						<p><strong>Detail:</strong></p>
						<p>${message }</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>


<script type="text/javascript">
$(document).ready(function() {
	var messages = ${jsons};
	
	$.each(messages, function(index, value) {
		console.log(index);
		
	});
});

</script>
</body>
</html>