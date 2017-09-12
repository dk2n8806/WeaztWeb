<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<jsp:include page="../../base/headerMetaLink.jsp"></jsp:include>
<title>Confirm Checkout | Weazt</title>

</head>

<body>

<jsp:include page="../../components/header-navigation.jsp"></jsp:include>


<c:set var="prinName"><sec:authentication property="principal.username"/></c:set>
<c:set var="merchantName" value="${merchant.businessName}" />


<c:set var="oId" value="${order.id}" />
<c:set var="sId" value="${template.subscriptionId}" />

<c:set var="requestedOn">
	<fmt:formatDate value="${order.createdOn }"/>
</c:set>

<spring:url var="subscriptionLink" value="/user/subscriptions/${sId}" htmlEscape="true"></spring:url>
<!-- 

	private Long subscriptionId;
	private String productTitle;
	private String productImage;
	private String category;
	private int subscriptionValue;
	private SubscriptionStatus status;
	private int hadShipped;
	private int nos;
	private Date scheduledOn; -->
<c:set var="title" value="${template.productTitle }" />
<c:set var="image" value="${template.productImage}" />
<br/><br/>
<div class="main-content" style="width: 700px;">
	<div class="col-wrapper">
		<div class="col">
			<div class="blk-b">
				<div class="panel-header">
					<h3>
						<span class="bhi tgreen"><i class="fa fa-paper-plane-o"></i></span>
						<span class="tgreen">Thank you</span>
					</h3>
				</div>
				<div class="blk-content">
					
					<div class="card150 prefix-clear">
						<div class="prefix-clear">
							<div class="card-face">
								<div class="card-holder">
									<img src="${image }" />
								</div>
							</div>
							<div class="card-content">	
								<h3><strong>Hi, ${prinName }. </strong></h3>
								<%-- 
								<p>Thanks for your subscription on <strong><span class="tturq">${title}</span></strong></p>
								<br/>
								 --%>
								<p>We have successfully sent your subscription request to the seller.	<%-- to <span><strong>${merchantName }</strong></span> --%></p>
								<br/>
								<p><strong>Your order number is ${oId}</strong></p>
								<p>(sent on ${requestedOn})</p>
								<br/>
								<p>Mean while, you can view your subscription detail <strong><a class="link" href="${subscriptionLink}">here</a></strong></p>
					
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<%-- 
<div class="main-content">
	<div class="blk-b"> 
		<div class="blk-content">
			<!-- <div class="blk-green toCenter">
				<h3 class="flead">Your subscription request has been sent.</h3>
			</div> -->
			<div class="toCenter">
				<h4><strong>Hi, <span class="fontCap">${prinName }</span>. Thanks for your subscription to @<span class="fontCap">${merchantName }</span></strong></h4>
				<p>Your subscription is processing. Our squad of hyper-intelligent octopuses successfully sent your order to @<span class="fontCap">${merchantName }</span>.</p>
				<p>Here is a summary of your order.</p>
				<br>
				<h3 class="fnor">Your order number is <strong>${orderId} </strong>
						&nbsp;(placed on <fmt:formatDate value="${orderCreatedOn}"/>)
				</h3>
			</div>
			<div class="modal-content">
				<div class="p20">
					<table class="style p20">
						<tr>
							<th colspan="2">Order Infomation</th>
						</tr>
						<tr>
							<td colspan="2">							
								<div class="p20">
									<p>Order placed on <fmt:formatDate value="${orderCreatedOn}"/></p>
									<div class="col-wrapper">
										<div class="col-2">
											<h3>Shipping address</h3>
											<p>${subscriberName }</p>
											<p>${addressStreet }</p>
											<p>${addressCity},&nbsp;${addressState }&nbsp;${addressZipcode }</p>
										</div>
										<div class="col-2">
											<h3>Customer Receipt</h3>
											<p>Confirmation Code: xxxxx</p>
											<p>Tue, Semptember 30, 2014</p>
											<p>Receipt: #xxxxxxx</p>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="c-70">
								<div class="card100 p120">
									<div class="card prefix-clear">
										<div class="card-face">
											<div class="card-holder">
												<img src="${productImage }" />
											</div>
										</div>
										<div class="card-content">
											<p><strong>${productTitle }</strong></p>
											<p class="tgray">${productCategory }</p>
										</div>
									</div>
								</div>
							</td>
							<td class="c-30">
								<p><span class="toRight">$123</span>Sub total</p>
								<p><span class="toRight">$123</span>Sub total</p>
								<hr/>
								<p><span class="toRight">$123</span><strong>Total</strong></p>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
 --%>

</body>
</html>