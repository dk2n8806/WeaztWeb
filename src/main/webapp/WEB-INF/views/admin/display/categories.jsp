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
						<h1 class="ftrend">Category</h1>	
					</div>
					<div class="blk-content">
						<a class="btn fancy-btn flatBlueBtn" href="#createCategoryFrom">Create</a>
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
								<ul id="statusNav" class="inline" data-status="${gid }">
									<c:forEach var="entity" items="${groups}">
										<c:set var="key" value="${entity.id }" />
										<c:set var="value" value="${entity.getName()}" />
										<spring:url var="customerLink" value="?gid=${key}"  htmlEscape="tru"  />
										<li><a id="${key}Tab" class="btn" href="${customerLink }">${value}</a></li>
									</c:forEach>
								</ul>
								</div>
							</div>
						</div>
						<div class="blk-b">
							<div class="blk-content">
								<table class="display">
									<tr class="purple twhite">
									<!-- 	<th>Created on</th> -->
										<th class="c-20">Id</th>
										<th class="c-40">Category</th>
										<th class="c-20">Group Id</th>
										<th class="c-20">Group</th>
										<th>Status</th>
									</tr>
									<c:forEach var="cat" items="${categories}">
										<c:set var="group" value="${cat.group }" />
										
										<c:set var="id" value="${cat.id }"/>
										<c:set var="createdOn">
											<fmt:formatDate value="${cat.createdOn }"/>
										</c:set>
										<c:set var="name" value="${cat.name }" />
										<c:set var="status" value="${cat.active}" />
										<c:set var="groupId" value="${group.id }"/>
										<c:set var="groupName" value="${group.name }"/>
									<tr>
										<%-- <td>${createdOn }</td> --%>
										<td><strong class="tred">${id }</strong></td>
										<td class="fontCap">${name }</td>
										<td><strong>${groupId}</strong></td>
										<td>${groupName }</td>
										<td>
											<spring:url var="toggleCat" value="/user/admin/toggle-category?cid=${id }" htmlEscape="true" />
											<a id="cat${id }" class="status toggleStatus btn plainBtn" data-id="${id }" data-status="${status }" href="${toggleCat }"></a>
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



<div id="createCategoryFrom" style="display: none">
	<div class="modal-header">
		<p>Update shipping info</p>
	</div>
	<div class="modal-content">
		<spring:url value="/user/admin/create-category" var="createCatForm" htmlEscape="true"></spring:url>
		<form id="catForm" action="${createCatForm }" method="post">
		<table class="c-100">
				<tr>
					<td>
						<label>New Name</label>
						<input type="text" name="name">
					</td>
				</tr>
				<tr>
					<td>
						<label>Group</label>
						<input type="text" name="groupName">
					</td>
				</tr>
				<tr>
					<td>
					<button type="submit" class="btn plainBtn">Submit</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
 
<script type="text/javascript">
$(document).ready(function() {
	
	$("#catForm").on("submit", function(event) {
		var $this = $(this);
		var serializedData = $this.serialize();
	    var urlForm = $this.attr("action");
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				serverResponse(["done"], "green");
			}, 
			error: function(data) {
			},
			complete: function(data) {
	        }
		});   
		event.preventDefault();
	});
	
	
	$(".toggleStatus").on("click", function(event) {
		var id = $(this).data("id");
		var $id = $("#cat" + id);
		var s = $id.data("status");
		$.ajax({
			url: $(this).attr("href"),
			method: "GET",
			beforeSend: function() {	
		    },
			success: function(data){
			}, 
			error: function(data) {
			},
			complete: function(data) {
				$id.data("status", !s);
				setStatus($id);
			}
		}); 
		event.preventDefault();
		return false;
	});

	$(".status").each(function(){
		setStatus($(this));
	});
	

	function setStatus($this) {
		var s = $this.data("status");
		var str = "";
		if(s) {
			str = "<i class=\"fa tgreen fa-check\">&nbsp;&nbsp;active</i>";
		} else {
			str = "<i class=\"fa tpurple fa-check\">&nbsp;&nbsp;deactive</i>";
		}
		$this.html(str);
	}
	
	function setUpRoleNav() {
		var a = $("#statusNav").data("status");
		$("#" + a + "Tab").addClass("active");
	}; setUpRoleNav();
	 
	function init() {
		$("#categoryNav").addClass("active");
	}; init();
	
	
	

	 
	 
	 
	;function fancyCall() {
		$(".fancy-btn").fancybox({
			padding: 0,
			fitToView: false,
	    	autoWidth: true, 
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