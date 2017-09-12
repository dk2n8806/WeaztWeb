
<c:set var="subscriptionStatus" value="${fn:toLowerCase(subscription.status) }" />

<c:set value="${product.title}" var="productTitle" />
<c:set value="${product.image}" var="productImage" />
<c:set value="${product.category}" var="category" />
<c:set value="${subscription.hadShipped }" var="hadReceived" />

<c:set value="${subscription.nos}" var="max" />
<c:set var="frequency">
	<fmt:formatNumber value="${subscription.frequency / 7}" ></fmt:formatNumber>
</c:set >
<c:set var="scheduledOn" >
	<fmt:formatDate value="${subscription.scheduledOn}"/>
</c:set>
<c:set var="subscriptionPrice" >
	<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${subscription.perShipment.subscriptionValue / 100}"></fmt:formatNumber>
</c:set>
<div class="blk">
	<div class="balance-list">
		<ul>
			<li class="prefix-clear">
				<p class="statName">Category</p>
				<h3 class="statValue fontCap">${category}</h3>
			</li>
		</ul>
		<ul>
			<li class="prefix-clear">
				<p class="statName">Subscription</p>
				<h1 class="statValue"><strong>$${subscriptionPrice }</strong></h1>
			</li>
		</ul>
		<ul>
			<li class="prefix-clear">
				<p class="statName">Shipments</p>
				<h3 class="statValue">${max}</h3>
			</li>
			<li class="prefix-clear">
				<p class="statName">Delivered</p>
				<h3 class="statValue">${hadReceived }</h3>
			</li>
		</ul>
		<ul>
			<li class="prefix-clear">
				<p class="statName">Frequency</p>
				<h3 class="statValue" id="deliverFreq" data-freq="${frequency }">${frequency }</h3>
			</li>
		</ul>
	</div>
</div>