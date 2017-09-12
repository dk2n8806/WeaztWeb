<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="base/headerMetaLink.jsp"></jsp:include>
<title>${product.basicInfo.title} | Weazt</title>
<style type="text/css">
.profile-holder {
	position: relative;
}
.profile-ava {
	border-radius: 50%;
}

table.style td {
  border: 1px solid #dbdbdb;
  padding: 10px;
  width: 50%;
}




.morecontent span {
    display: none;
}
.morelink {
    display: inline-block;
}

.sobox {
	border-radius: 5px;
	margin: 30px 0px;
	border: 1px solid #2ecc71;
	padding: 15px 25px;
	background: rgba(46, 204, 113, 0.2);
}

.proList li {
	display: inline-block;
	margin: 2px;
	font-size: 13px;
	/*border-radius: 100px;
	border: 1px solid #c7c7c7;*/
	color: #16a085
	border-radius: 2px;
	font-size: 14px;
	color: #14cb73;
	line-height: 1.4;
	padding: 10px 25px 11px;
	background: #e1f9ef;
}

.mainList {
	background: #fff;
	margin-bottom: 10px;
}

.mainList .col-2 {
	padding: 20px 0px;
}
/*
.sobox {
	margin: 20px 0px;
	border-radius: 3px;
	border: 1px solid #a1d9a9;
	background: #bff7c7;
	padding: 2px;
}
*/
</style>
</head>
<body>
<jsp:include page="components/header-navigation.jsp"></jsp:include> 
<jsp:include page="components/banner/shopBenefitOverviewBanner.jsp"></jsp:include>



<c:set var="pId" value="${product.id }" />
<c:set var="pImage" value="${product.displayImage.path}" />
<c:set var="pTitle" value="${product.basicInfo.title }" />
<c:set var="pDescription" value="${product.basicInfo.description }" />
<c:set var="pCategory" value="${product.category.name }" />
<c:set var="cid" value="${product.category.id }" />
<c:set var="cName" value="${product.category.name}" />
<c:set var="pPrice"><fmt:formatNumber  value="${product.basicInfo.price /100}" maxFractionDigits="2" /></c:set>
<c:set var="renewable" value="true"/>
<c:set var="customerizable" value="true"/>
<c:set var="cancelable" value="true"/>


<spring:url value="/shop/new-subscriptions" var="newSubLink" htmlEscape="true"></spring:url>
<spring:url value="/category/${pCategory }" var="catLink" htmlEscape="true"></spring:url>
			
<br/>
<div class="mainList1">		
	<div class="main-content">
		<div class="col-wrapper">
			<div class="col">
				<div class="blk-b">
					<div class="header_breadcrumb">
						<ul class="c_breadcrumb">
							<spring:url value="${header.referer}" var="backLink" htmlEscape="true"></spring:url>
							<li><a class="link" href="${backLink }"><i class="fa fa-angle-double-left"></i>&nbsp;&nbsp;Back</a></li>
							<li>/</li>
							<li><a class="link fontCap" href="${catLink }">${pCategory } subscriptions</a></li>
							<li>/</li>
							<li><a>${pTitle }</a></li>
						</ul>
					</div>
					<div class="col-wrapper">
						<div class="col-2">
							<jsp:include page="item/gallery.jsp"></jsp:include>
						</div>
						<div class="col-2">
							<jsp:include page="item/overview.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="main-content">
	<div class="col-wrapper">
		<div class="col75">
			<div class="blk-b">
				<div class="blk-header">
				
					<div class="toRight">
						<p><strong>Have you subscribed to this product?</strong></p>
						<spring:url value="/user/subscriptions" var="mySubscriptionLink" htmlEscape="true"></spring:url>
						<p><a class="link" href="${mySubscriptionLink}">Share your experience about your subscription&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i></a></p>
					</div>
					<h2 class="fnor"><i class="fa fa-comments-o"></i>&nbsp;&nbsp;Subscriber reviews&nbsp;(${reviewSize })</h2>
				</div>
				<div class="blk-content">
					<c:choose>
						<c:when test="${fn:length(reviews) > 0}">
							<div class="reviewList">
							<jsp:include page="global/modules/productReviews.jsp"></jsp:include>
							</div>
						</c:when>
						<c:otherwise>
							<p>There is no reviews for this product at this moment!</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>
<br/>


<div class="main-content">
	<div class="col-wrapper">
		<div class="col">
			<div class="blk">
				<div><h2 class="fnor">Similar subscriptions you may like</h2></div>
			</div>
		</div>
		<jsp:include page="item/similarItem.jsp"></jsp:include>
	</div>
</div>
 
 
 
 
 
 

<jsp:include page="components/footer-navigation.jsp"></jsp:include>
<script type="text/javascript">

$(document).ready(function() {
	function init() {
		buildProductLink();
	}; init();
	

	$(".bookmark").click(function(event){
		$(this).toggleClass("active");
		event.preventDefault();
		$.ajax({
	        url: $(this).attr('href') , 
	        success: function(response) {
	        }, 
			error: function(data) {
			},
			complete: function(data) {
			}
	     });
	     return false; 
	});
	
	
	
	function a($a) {
		var a = $a.data("total");
		var msg = $a.data("msg");
		var str = "product";
		if(a > 1){
			str += "s";
		}	
		str = "<span class=\"tblue\">" + a + "&nbsp;&nbsp" + str + "&nbsp;&nbsp;<i class=\"fa fa-angle-double-right\"></i></span>";
		str = "<strong>"+msg+"</strong><br/>" + str;
		$a.html(str);
	};
	$(".merchantTotal").each(function(){
		a($(this));
	});
	
	
	
	
	
	
	function displayReadmore() {		
		var showChar = 200;  // How many characters are shown by default
		var ellipsestext = "...";
		var moretext = "read more";
		var lesstext = "show less";
		
		$('.more').each(function() {
		    var content = $(this).html();
			var length = $(this).data("length") | 0;
			if(length != 0) {
				showChar = length;
			}
		    if(content.length > showChar) {
		        var c = content.substr(0, showChar);
		        var h = content.substr(showChar, content.length - showChar);
		        var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span><span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a href="" class="morelink">' + moretext + '</a></span>';
		        $(this).html(html);
		    }
		
		});
		
		$(".morelink").click(function(){
		    if($(this).hasClass("less")) {
		        $(this).removeClass("less");
		        $(this).html(moretext);
		    } else {
		        $(this).addClass("less");
		        $(this).html(lesstext);
		    }
		    $(this).parent().prev().toggle();
		    $(this).prev().toggle();
		    return false;
		});
	}; displayReadmore();
	
});


</script>

</body>
</html>