<!-- 	private int minSubscribe = 1;
	private int maxSubscribe = 3;
	private double percentSave = 0;
	private SubscriptionRate saveOption;
	/*private DeliverFreq frequency;*/
	private int frequency;
	 -->
	 
	 
	
<c:set var="productPrice">
	<fmt:formatNumber maxFractionDigits="2" value="${product.basicInfo.price / 100}"/>
</c:set>
<c:set value="${product.subscriptionInfo.nos}" var="shipment" />
<c:set value="${product.subscriptionInfo.frequency }" var="frequency" />
<c:set var="saveRate" >
	<fmt:formatNumber maxFractionDigits="2"
						value="${product.subscriptionInfo.percentSave / 100}" /> 
</c:set>
<c:set var="subscribePrice" >
	<fmt:formatNumber maxFractionDigits="2" 
					minFractionDigits="2"
					value="${productPrice - (productPrice * saveRate / 100)}"/>
</c:set>



<c:set  var="frequency">
	<fmt:formatNumber value="${product.subscriptionInfo.frequency / 7}" maxFractionDigits="0" />
</c:set>

<c:choose>
	<c:when test="${frequency == 1 }">
		<c:set var="freqTxt" value="weekly"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="freqTxt" value="every ${frequency} weeks"></c:set>
	</c:otherwise>
</c:choose>

<c:set value="${fn:toLowerCase(product.shippingInfo.type)}" var="shipmentType" />
<c:set var="shippingCost">
	<fmt:formatNumber value="${product.shippingInfo.shippingCost / 100}" maxFractionDigits="2"/>
</c:set>
<div class="blk-b blue2 twhite">
	<div class="blk-header">
		<p>
			<span class="bhi twhite"><i class="fa fa-retweet"></i></span>
			<strong class="">Subscription</strong>
		</p>
	</div>
	<div class="blk-content">
		<div class="toCenter">
			<p class="statName">Estimated</p>
			<h1 class="fnor">
				<span>$${subscribePrice }</span>
				<c:choose>
					<c:when test="${shipmentType == 'auto' }">				
						<span style="font-size: 18px;">&nbsp;+&nbsp;Varies</span>
					</c:when>
					<c:when test="${shipmentType == 'free' }">
					</c:when>
					<c:otherwise>		
						<span style="font-size: 18px;">&nbsp;+&nbsp;$${shippingCost}</span>
					</c:otherwise>
				</c:choose>	
			</h1>
			<p class="statName">per shipment</p>
		</div>
	</div>
	<div class="blk-footer">
		<div class="balance-list">
			<ul class="overviewListing">
				<li class="toCenter">
					<p class="statName">No. Shipment</p>
					<h4 class="statValue">${shipment}</h4>
				</li>
				<li class="toCenter">
					<p class="statName">Save Rate</p>
					<h4 class="statValue"><strong>${saveRate}%</strong></h4>
				</li>
				<li class="toCenter">
					<p class="statName">Frequency</p>
					<h4 class="statValue" style="text-transform: lowercase;">${freqTxt}</h4>
				</li>
			</ul>
		</div> 
	</div>
</div>