<c:set var="productTitle" value="${product.basicInfo.title }" />
<c:set var="productPrice">
	<fmt:formatNumber  value="${product.basicInfo.price / 100}" maxFractionDigits="2" />
</c:set>
<c:set var="productImage" value="${product.displayImage.path }" />
<c:set var="productCategory" value="${product.category.name }" />
<c:set var="shippingType" value="${fn:toLowerCase(product.shippingInfo.type)}" />

<c:set var="taxRate" >
	<fmt:formatNumber value="0" maxFractionDigits="2" />
</c:set>
<c:set var="shippingCost" >
	<fmt:formatNumber value="${shippingCost / 100}" maxFractionDigits="2" />
</c:set>
<c:set var="save">
	<fmt:formatNumber value="${product.subscriptionInfo.percentSave / 100}" maxFractionDigits="2"/>
</c:set>
<c:set var="subscribePrice">
	<fmt:formatNumber value="${productPrice - (productPrice * save) / 100 }" maxFractionDigits="2"/>
</c:set>


<c:set var="min" value="${product.subscriptionInfo.nos}" />
		
<div class="blk-b">
	<div class="blk-header">
		<p><strong class="fontCap"><span class="bhi"><i class="fa fa-tags"></i></span><span>${productCategory}</span></strong></p>
		<br/>
		<div><h3 class="fnor">${productTitle}</h3></div>
		<br/>
	</div>
	<div class="story-box">
		<img src="${productImage }" />
	</div>
	<br/>
	<br/>
	<div id="pdata" data-price="${productPrice }" data-save="${save}" data-trate="${taxRate}" data-min="${min }" data-shipment="${min }" data-stype="${shippingType }" data-scost="${shippingCost}" class="blk-footer prefix-clear">
		<div class="p10">
			<div>
				<h3 class="fnor">
					<span class="toRight" id="retailPrice" data-amount="${productPrice }">$${productPrice }</span>
					<span style="font-size: 14px;" class="tgray2">Price</span>
				</h3>
			</div>
			<hr/>
			<div>
				<h3 class="fnor">
					<span id="subscriptionPrice" class="toRight">$${subscribePrice}</span>
					<span style="font-size: 14px;" class="tgray2">Subscription Price</span>
				</h3>
			</div>
			<div>
				<h3 class="fnor">
					<span class="toRight" id="saveRate" data-percent="${save}">${save}%&nbsp;<i class="fa fa-caret-down"></i></span>
					<span style="font-size: 14px;" class="tgray2">Save Rate</span>
				</h3>
			</div>
			<hr/>
			<div>
				<h3 class="fnor">
					<c:choose>
						<c:when test="${shippingType == 'free' }">
							<span class="toRight torange">Free</span>
						</c:when>
						<c:otherwise>
							<span class="toRight" id="shippingRate">$${shippingCost}</span>
						</c:otherwise>
					</c:choose>
					<span style="font-size: 14px;" class="tgray2">Shipping</span>
				</h3>
			</div>
			<div>
				<h3 class="fnor">
					<span class="toRight" id="taxRate" data-percent="${taxRate}">${taxRate}%</span>
					<span style="font-size: 14px;" class="tgray2">Estimated tax</span>
				</h3>
			</div>
			<hr>
			<div>
				<h3 class="fnor">
					<strong><span class="toRight" id="subTotal"></span></strong>
					<span style="font-size: 14px;" class="tgray2">Sub Total</span>
				</h3>
			</div>
			<div>
				<h3 class="fnor">
					<span class="toRight"><strong id="selectedShipment" data-min="${min }">${min }</strong></span>
					<span style="font-size: 14px;" class="tgray2">No. shipment</span>
				</h3>
			</div>
		</div>
	</div>
	
	
	<div class="panel-footer">
		<h2 class="fnor">
			<span class="toRight" id="totalAmount"></span>
			<span>Total</span>
		</h2>
		<div class="prefix-clear">
			<p id="note"></p>
		</div>
	</div>
</div>
