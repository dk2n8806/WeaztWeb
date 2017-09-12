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
						<h1 class="ftrend">Products</h1>	
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
								<c:forEach var="entity" items="${counts }">
									<c:set var="key" value="${entity.key }" />
									<c:set var="value" value="${entity.value}" />
									<spring:url var="customerLink" value="?_ref=${key}"  htmlEscape="true"  />
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
									<th class="c-10">id</th>
									<th class="c-40">Detail</th>
									<th class="c-50"></th>
								</tr>
								<c:forEach items="${products }" var="p">
								<c:set var="id" value="${p.id }" />
								<c:set var="createdOn">
									<fmt:formatDate value="${p.createdOn }"/>
								</c:set>
								<c:set var="title" value="${p.basicInfo.title }" />
								<c:set var="price" value="${p.basicInfo.price / 100 }" />
								<c:set var="image" value="${p.displayImage.path }" />
								<c:set var="shippingCost" value="${p.shippingInfo.shippingCost / 100}"/>
								<c:set var="shippingType" value="${p.shippingInfo.type}"/>
								<c:set var="nos" value="${p.subscriptionInfo.nos }"/>
								<c:set var="rate" value="${p.subscriptionInfo.percentSave / 100 }"/>
								<c:set var="freq" value="${p.subscriptionInfo.frequency / 7 }"/>
								<c:set var="status" value="${p.status }" />
								<c:set var="merchantId" value="${p. merchant.id}" />
								<c:set var="categoryId" value="${p.category.id}" />
								
							
								<tr>
									<td>${id }</td>
									<td>
										<div class="prefix-clear">
											<div class="card100 prefix-clear">
												<div class="card-face">
													<div class="card-holder"><img src="${image}"/></div>
												</div>
												<div class="card-content">
													<p><strong>${title }</strong></p>
													<p>Price: $${price }</p>
												</div>
											</div>
										</div>
									</td>
									<td>
										<div class="balance-list">
											<ul class="inline">
												<li>Shipping type: <strong>${shippingType }</strong></li>
												<li>Shipping Cost: <strong>$${shippingCost}</strong></li>
											</ul>
											<ul class="inline">
												<li>rate: <strong>${rate}%</strong></li>
												<li>Subscription: <strong class="subscriptionCost" data-save="${rate }" data-price="${price }"></strong></li>
											</ul>
											<ul>
												<li>nos: <strong>${nos}</strong></li>
												<li>freq: <strong>${freq} week</strong></li>
											</ul>
											<hr/>
											<ul>
												<li>Merchant:<br/>${merchantId }</li>
												<li>Category:<br/>${categoryId }</li>
												<li>Status:<br/>${status }</li>
												<li>Created on:<br/>${createdOn}</li>
												
											</ul>
										</div> 
										
									</td>
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
	
	$(".subscriptionCost").each(function() {
		var price = $(this).data("price");
		var rate = $(this).data("save");
		var cost = price - price * rate / 100;
		$(this).html("$" + cost.toFixed(2));
	});

	$(".status").each(function() {
		var s = $(this).data("status").toLowerCase();
		if(s === "deactive") {
			$(this).addClass("purple");
		} else if(s === "deleted") {
			$(this).addClass("red");
		} else {
			$(this).addClass("blue");
		}
	});
	
	function setUpRoleNav() {
		var a = $("#statusNav").data("status");
		$("#" + a + "Tab").addClass("active");
	}; setUpRoleNav();
	 
	function init() {
		$("#productNav").addClass("active");
	}; init();
});

</script>
</body>
</html>