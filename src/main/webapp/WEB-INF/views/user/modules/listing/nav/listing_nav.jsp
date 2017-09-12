
<div id="commandPrimary" class="command-menu">
	<ul>
		<li id="publicListingNav">
			<spring:url var="publicListingLink" value="/user/listings" htmlEscape="true"></spring:url>
			<h4><a href="${publicListingLink }">My Listings</a></h4>
		</li>
		<li id="privateListingNav">
		<spring:url var="privateListingLink" value="/user/listings/private" htmlEscape="true"></spring:url>
			<h4><a href="${privateListingLink}">Private Listings</a></h4>
		</li>
		<li id="deletedListingNav">
			<spring:url var="historyListingLink" value="/user/listings/deleted" htmlEscape="true"></spring:url>
			<h4><a href="${historyListingLink }">Deleted Products</a></h4>
		</li> 
	</ul>
</div>
<br/>	
<br/>	
<spring:url var="newListingLink" value="/user/merchant/request/create-product" htmlEscape="true"></spring:url>
<a class="flatBlueBtn"  href="${newListingLink}"><i class="fa fa-plus"></i>&nbsp;&nbsp;New Listing</a>
							