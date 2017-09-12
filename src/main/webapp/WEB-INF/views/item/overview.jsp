
<c:set var="pId" value="${product.id }" />

<c:set var="pTitle" value="${product.basicInfo.title }" />
<c:set var="pDescription" value="${product.basicInfo.description }" />
<c:set var="pPrice"><fmt:formatNumber  value="${product.basicInfo.price /100}" maxFractionDigits="2" /></c:set>
	

<c:set var="pCategory" value="${product.category.name }" />
<c:set var="pCId" value="${product.category.id }" />

<c:set var="pDeliver">
	<fmt:formatNumber maxFractionDigits="0" value="${product.subscriptionInfo.frequency / 7}" />
</c:set>
<c:set var="pShipment" value="${product.subscriptionInfo.nos}" />

<c:set var="percentSave">
	<fmt:formatNumber  value="${product.subscriptionInfo.percentSave /100 }" maxFractionDigits="2" />
</c:set>

<c:set var="subscribePrice">
	<fmt:formatNumber value="${pPrice - ( pPrice * percentSave / 100 )}"  maxFractionDigits="2"/>
</c:set>


<c:set var="shippingType" value="${fn:toLowerCase(product.shippingInfo.type) }" />
<c:set var="shippingCost">
	<fmt:formatNumber  value="${product.shippingInfo.shippingCost / 100}" />
</c:set>

<div class="blk">
	<div class="blk-content">
		<div class="blk-header">
			<spring:url var="bookmarkLink" value="/user/bookmark/add?productId=${pId}"  htmlEscape="true" />
			<a class="wishlist toRight bookmark btn" href="${bookmarkLink }">&nbsp;&nbsp;Bookmark</a>
			<h3 class="fontCap"><strong class="bhi"><i class="fa fa-tags"></i>${pCategory }</strong></h3>
		</div>
		<div class="ph20"><h1 class="fnor"><strong>${pTitle}</strong></h1></div>
		<!-- <div><h3 class="fnor"><strong>About the product</strong></h3></div>	 -->
		<div><p class="more" data-length="300">${pDescription}</p></div>
	</div>
	<div class="blk-content">
		<div><h2 class="fnor"><i>Price:</i></h2>&nbsp;&nbsp;<h1 style="font-size: 32px;" class="fnor tgreen"><strong>$20.00</strong></h1></div>
		<br/>
		<spring:url value="/user/checkout" var="checkoutForm" htmlEscape="true"/>
		<form action="${checkoutForm}" method="POST">
			<input type="hidden" value="${pId }" name="_pid"/>
			<button style="text-transform: uppercase;"  class="flatGreenBtn c-100 btn" type="submit">Subscribe Now!</button>
		</form>
	</div>
	<div class="blk-content">
		<ul class="proList">
			<c:if test="${shippingType == 'free' }">
				<li><span class="p10"><span>Free Shipping</span></span></li>
			</c:if>
			<li>Get <strong>${percentSave }% off</strong> instantly when select ${pShipment } shipments or more</li>
		</ul>
	</div>
</div>

