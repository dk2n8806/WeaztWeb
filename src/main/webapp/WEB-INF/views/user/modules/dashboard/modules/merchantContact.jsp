
<c:set var="merchantCompany"><sec:authentication property="principal.merchant.companyName"/></c:set>
<c:set var="merchantWebsite"><sec:authentication property="principal.merchant.companyWebsite"/></c:set>
<c:set var="merchantJoinedOn"><sec:authentication property="principal.merchant.joinedOn"/></c:set>
<c:set var="addressCity" value="${merchantContact.address.city}"/>
<c:set var="addressState" value="${merchantContact.address.state.state }"/>
<c:set var="addressZipcode" value="${merchantContact.address.zipcode }"/>
<c:set var="phoneNumber" value="${merchantContact.phone.phoneNumber }"/>

<hr/>
<br/>
<%-- <spring:url value="/user/setting/merchant" var="editMerchantProfile" htmlEscape="true"/>
<a class="blkBtn" href="${editMerchantProfile}">Update&nbsp;&nbsp;<i class="fa fa-pencil"></i></a>
 --%>
 <ul class="overviewListing">
	<li>
		<p class="overviewListingTitle">Business</p>
		<h3 class="overviewListingStats tblue">${merchantCompany }</h3>
	</li>
	<li>
		<p class="overviewListingTitle">Location</p>
		<p class="overviewListingStats">${addressCity},&nbsp;${addressState}&nbsp;${addressZipcode}</p>
	</li>
	<li>
		<p class="overviewListingTitle">Phone</p>
		<p class="overviewListingStats">${phoneNumber }</p>
	</li>
	<li>
		<p class="overviewListingTitle">Website</p>
		<p class="overviewListingStats"><a class="link" target="_blank" href="${merchantWebsite}">${merchantWebsite}&nbsp;<i class="fa fa-external-link"></i></a></p>
	</li>
</ul>
<br/>
<p class="tip">Became merchant on <span class="dateFormat" data-date="${merchantJoinedOn}"></span></p>


