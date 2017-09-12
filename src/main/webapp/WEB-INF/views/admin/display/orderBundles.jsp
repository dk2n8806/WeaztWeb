<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title></title>
<style type="text/css">
.mainList {
	margin-bottom: 50px;
}
</style>
</head>
<body>
<jsp:include page="../../components/header-navigation.jsp"></jsp:include> 


<div class="mainList gray">
	<div class="main-content1">
		<div class="col-wrapper">
			<div class="col toCenter">
				<div class="blk">
					<div class="blk-header">
						<h1 class="ftrend">Order Bundles</h1>	
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="main-content1">
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="../nav/adminNavigations.jsp"></jsp:include>
		</div>
		<div class="olymArena">
			<div class="col-wrapper">
				<div class="col">
					<div class="blk">
						<div class="blk-header">
							<div class="blkTabNav">
							<ul id="statusNav" class="inline" data-status="${status}">
								<c:forEach var="entity" items="${params }">
									<c:set var="key" value="${entity.key }" />
									<c:set var="value" value="${entity.value}" />
									<spring:url var="customerLink" value="?_s=${key}"  htmlEscape="true"  />
									<li><a id="${key}Tab" class="btn" href="${customerLink }">${value}&nbsp;${key}</a></li>
								</c:forEach>
							</ul>
							</div>	
						</div>
					</div>
					<div class="blk-b">
						<div class="blk-content">
							<table class="display">
								<tr class="purple twhite">
									<th>Id</th>
									<th>Created on</th>
									<th>Detail</th>
									<th>status</th>
									<th>Product </th>
								</tr>
								<c:forEach items="${orders}" var="s">
								
								
	
	
								<c:set var="id" value="${s.id}"/>
								<c:set var="createdOn"><fmt:formatDate value="${s.createdOn }"/></c:set>
								<c:set var="noo" value="${s.noo}"/>
								<c:set var="amount"><fmt:formatNumber value="${s.collectedAmount / 100 }"/></c:set>
								<c:set var="status" value="${s.status }" />
								<c:set var="productId" value="${s.product.id}"/>
								<tr>
									<td>${id }</td>
									<td>${createdOn }</td>
									<td>
										<ul>
											<li>Total Charge: <strong>$${amount}</strong></li>
											<li>Nos: <strong>${noo}</strong></li>
										</ul>
									</td>
									<td class="status" data-status="${status }"></td>
									<td>${productId }</td>
								</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {

	$(".status").each(function(){
		var $this = $(this);
		var status = $this.data("status").toLowerCase();
		var str = "";
		if(status == "pending") {
			str = "<i class=\"fa tgray fa-ellipsis-h\">&nbsp;&nbsp;"+status+"</i>";
		} else if(status == "completed"){
			str = "<i class=\"fa tgreen fa-check-square-o\">&nbsp;&nbsp;"+status+"</i>";
		} 
		$this.html(str);
	});
	
	function setUpRoleNav() {
		var a = $("#statusNav").data("status");
		$("#" + a + "Tab").addClass("active");
	}; setUpRoleNav();
	 
	function init() {
		$("#orderNav").addClass("active");
	}; init();
});

</script>
</body>
</html>