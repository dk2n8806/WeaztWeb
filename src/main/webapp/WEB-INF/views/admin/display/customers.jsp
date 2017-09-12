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
	<div class="main-content">
		<div class="col-wrapper">
			<div class="col toCenter">
				<div class="blk">
					<div class="blk-header">
						<h1 class="ftrend">Account: ${role }</h1>	
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="main-content">
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="../nav/adminNavigations.jsp"></jsp:include>
		</div>
		
		<div class="olymArena">
			<div class="col-wrapper">
					<div class="col">
						<div class="blk">
							<div class="blk-content">
								<div class="blkTabNav">
								<ul id="roleNav" class="inline" data-role=${role }>
									<c:forEach var="entity" items="${params }">
										<c:set var="key" value="${entity.key }" />
										<c:set var="value" value="${entity.value}" />
										<spring:url var="customerLink" value="?_role=${key}"  htmlEscape="tru"  />
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
										<th>Created on</th>
										<th>Id</th>
										<th>Detail</th>
										<th>Role</th>
										<th>Status</th>
									</tr>
								<c:forEach var="acc" items="${accounts}">
									<c:set var="accId" value="${acc.id}" />
									<c:set var="accUsername" value="${acc.username}" />
									<c:set var="accImage" value="${acc.avatar.imagePath.path}" />
									<%-- 
									<c:set var="accEmail" value="${fn:substringBefore(acc.email, '@')}" />
									 --%>
									<c:set var="accEmail" value="${acc.email}" />
									<c:set var="accRole" value="${fn:toLowerCase(acc.role)}" />
									<c:set var="accStatus" value="${fn:toLowerCase(acc.status)}" />
									<c:set var="accCreatedOn">
										<fmt:formatDate value="${acc.createdOn}"/>
									</c:set>
									<tr>
										<td>${accCreatedOn }</td>
										<td>${accId }</td>
										<td>
											<div class="blk-credit">
												<div class="credit-img">
													<img src="${accImage}"/>
												</div>
												<div class="credit-info">
													<h4 class="fnor"><strong>${accUsername}</strong></h4>
													<p>${accEmail}</p>
												</div>
											</div>
										</td>
										<td>${accRole }</td>
										<td>${accStatus }</td>
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

	$(".role").each(function() {
		var s = $(this).data("role").toLowerCase();
		if(s === "admin") {
			$(this).addClass("orange");
		} else if(s === "merchant") {
			$(this).addClass("purple");
		} else {
			$(this).addClass("blue");
		}
	});
	
	function setUpRoleNav() {
		var a = $("#roleNav").data("role");
		$("#" + a + "Tab").addClass("active");
	}; setUpRoleNav();
	 
	function init() {
		$("#customerNav").addClass("active");
	}; init();
});

</script>
</body>
</html>