
<spring:url var="manageShippingLink" value="/user/setting/account/shipping" htmlEscape="true" />
<div class="blk-b">
	<div class="panel-header">
		<h3><span>Address Shipping</span></h3>		
	</div>
	<div class="blk-content">	
		<p class="tgr11ay">Create and manage your shipping info. When the sellers process your subscriptions, they will ship to your primary address.</p>
		<br/>
		<div class="balance-list">	
			<ul>
				<c:if test="${not empty shipping}">
				<c:set var="id" value="${shipping.id}" />
				<c:set var="isActive" value="${shipping.active}" />
				<c:set var="isPrimary" value="${shipping.primary}" />
				<c:set var="name" value="${shipping.address.name }" />
				<c:set var="stage" value="${shipping.address.state }" />
				<c:set var="street" value="${shipping.address.street }" />
				<c:set var="city" value="${shipping.address.city }" />
				<c:set var="state" value="${shipping.address.state.state }" />
				<c:set var="zipcode" value="${shipping.address.zipcode }" />
				<li>
					<div  class="box-border" style="width: 300px;">
						<div class="blk-content">
							<span class="fbtn toRight green"><i class="fa fa-check-circle"></i>&nbsp;&nbsp;primary</span>
							<ul class="overviewListing">
								<li>
									<p class="overviewListingTitle">Receiver</p>
									<h4 class="overviewListingStats">${name}</h4>
								</li>
							</ul>
							<ul class="overviewListing">
								<li>
									<p class="overviewListingTitle">Shipping Address</p>
									<h4 class="overviewListingStats">
										${street}
										<br/>
										${city},&nbsp;${state}&nbsp;${zipcode}
									</h4>
								</li>
							</ul>
						</div>
					</div>
				</li>
				</c:if>
				
				<li>
					<div class="p20"><a class="btn plainBtn" href="${manageShippingLink }"><strong>Manage Shipping Info</strong>&nbsp;&nbsp;<i class="fa fa-chevron-right"></i></a></div>
				</li>
			</ul>		
		
		</div>
	</div>
</div>