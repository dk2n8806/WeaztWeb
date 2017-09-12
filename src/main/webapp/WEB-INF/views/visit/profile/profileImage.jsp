
<c:set value="${merchant.merchantImage}" var="image"/>

	
<c:set var="username" value="${merchant.merchantName}"/>
<c:set var="business" value="${merchant.merchantBusiness}" />
<c:set var="website" value="${merchant.merchantWebsite}" />

<c:set var="city" value="${merchant.address.city}" />
<c:set var="state" value="${fn:toLowerCase(merchant.address.state)}" />
<c:set var="zipcode" value="${merchant.address.country}" />
<c:set var="location">
	${city},&nbsp;<span class="fontCap">${state}</span>,&nbsp;${zipcode }
</c:set>

<c:set var="phone" value="${merchant.phone.phoneNumber }" />
	
	
<c:if test="${not empty merchant}">
</c:if>
<div class="blk">
	<div class="user-profile-holder">
		<div class="user-profile-ava">
			<img src="${image}"/>
		</div>
	</div>
</div>

<div class="blk-b">
	<div class="blk-header"><p><strong>Business Info</strong></p></div>
	<div class="blk-content">	
		<ul class="overviewListing">
			<li>
				<p class="overviewListingTitle"><i class="fa fa-building-o"></i>&nbsp;&nbsp;Business</p>
				<h3 class="overviewListingStats">${business}</h3>
			</li>
			<li>
				<p class="overviewListingTitle"><i class="fa fa-map-marker"></i>&nbsp;&nbsp;Location</p>
				<h3 class="overviewListingStats">${location}</h3>
			</li>
			<%-- <li>
				<p class="overviewListingTitle"><i class="fa fa-phone"></i>&nbsp;&nbsp;Phone</p>
				<h3 class="overviewListingStats">${phone }</h3>
			</li> --%>
		</ul>
	</div>
</div>




