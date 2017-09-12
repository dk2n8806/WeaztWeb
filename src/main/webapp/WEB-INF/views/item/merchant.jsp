
<c:set var="merchantId" value="${merchant.merchantId}"/>
<c:set var="merchantName" value="${merchant.merchantName}"/>
<c:set var="merchantImage" value="${merchant.merchantImage}"/>
<c:set var="merchantEmail" value="${merchant.merchantEmail}"/>
<c:set var="merchantBusiness" value="${merchant.merchantBusiness}"/>
<c:set var="merchantWebsite" value="${merchant.merchantWebsite}"/>
<spring:url value="/show/merchants?id=${merchantId}&ref=${merchantName}&ref1=${merchantBusiness }" var="merchantLink" htmlEscape="true"></spring:url>

<div class="blk">
	<div class="blk-content">
		<div class="card100 prefix-clear">
			<div class="card-face">
				<div class="card-holder card-cir">
					<img src="${merchantImage }">
				</div>
			</div>
			<div class="card-content prefix-clear">
				<div class="toRight">
					<ul class="catNav">
						<c:forEach items="${otherTemplates}" var="product">
						<spring:url var="productLink" value="/shop/product/${product.productId}" htmlEscape="true" />
						<li>				
							<a href="${productLink }" data-title="${product.productTitle}" class="catFace productTitle">
							    <img src="${product.productImage }">
							    <%-- <span class="catContent">
							    	<span class="catTitle fontCap">${product.category }</span>
							    </span> --%>
							</a>
						</li>						
						</c:forEach>
						<li>
							<h3 class="fnor"><a class="fb-btn" href="${merchantLink }" data-name>Discover more&nbsp;&nbsp;<i class="fa fa-long-arrow-right"></i></a></h3>
						</li>
					</ul>	
				</div>
				<h2 class="fnor"><a href="${merchantLink }"><strong>${merchantName }</strong></a></h2>
				<p class="tgray">${merchantBusiness}</p>
			</div>
		</div>
	</div>
</div>