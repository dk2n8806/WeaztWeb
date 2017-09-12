
<c:set var="productPrice">
	<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${product.basicInfo.price }"/>
</c:set>
<c:set value="${product.category.name }" var="category" />
<c:set value="${fn:toLowerCase(product.status)}" var="productStatus" />

<c:set var="productPrice"><fmt:formatNumber value="${product.basicInfo.price / 100}" maxFractionDigits="2"/> </c:set>

<c:set var="weight">
	<fmt:formatNumber value="${parcel.parcelAdapter.weight.weight }"  />
</c:set>
<c:set var="wunit"  value="${fn:toLowerCase(parcel.parcelAdapter.weight.unit)}" />
<c:set var="length">
	<fmt:formatNumber value="${parcel.parcelAdapter.measurement.length }"  />
</c:set>
<c:set var="width">
	<fmt:formatNumber value="${parcel.parcelAdapter.measurement.width}"  />
</c:set>
<c:set var="height">
	<fmt:formatNumber value="${parcel.parcelAdapter.measurement.height }"  />
</c:set>
<c:set var="munit"  value="${fn:toLowerCase(parcel.parcelAdapter.measurement.unit)}" />

<div class="blk-b blue2 twhite">
	<div class="blk-header">
		<p>
			<span class="bhi twhite"><i class="fa fa-file-text-o"></i></span>
			<strong class="">Overview</strong>
		</p>
	</div>
	<div class="blk-content">
		<div class="balance-list1">
			<ul class="overviewListing">
				<li class="prefix-clear">
					<h3 class="statValue toRight fontCap">${category}</h3>
					<p class="statName">Category</p>
				</li>
				<li class="prefix-clear">
					<h3 class="statValue toRight">$${productPrice }</h3>
					<p class="statName">Price</p>
				</li>
			</ul>
			<hr/>
			<ul class="overviewListing">
				<li class="prefix-clear">
					<p class="statName">Weight</p>
					<p>
						<span><strong>W:</strong>&nbsp;${weight}</span>
						<span style="text-transform: lowercase;">${wunit}</span>
					</p>
				</li>
				<li class="prefix-clear">
					<p class="statName">Measurement</p>
					<p>
						<span><strong>L:</strong>&nbsp;${length }</span>
						<span style="text-transform: lowercase;">${munit}</span>
						&nbsp;
						<span><strong>W:</strong>&nbsp;${width}</span>
						<span style="text-transform: lowercase;">${munit}</span>
						&nbsp;
						<span><strong>H:</strong>&nbsp;${height}</span>
						<span style="text-transform: lowercase;">${munit}</span>
					</p>
				</li>
			</ul>
		</div>
	</div>
</div>