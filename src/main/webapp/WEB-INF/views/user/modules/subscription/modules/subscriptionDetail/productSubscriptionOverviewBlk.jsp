
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
 
<div class="blk-b">
	
	<div class="blkTwin">
		<div class="mainTwinBlk c-70">
			<div class="blk-header prefix-clear">
				<ul class="toggleMenuList toRight">
					<%-- <li class="toggleMenu">
						<button class="toggleTrigger">Status:&nbsp;<strong class="fontCap productStatusDisplay" data-status="${subscriptionStatus}">${subscriptionStatus }&nbsp;&nbsp;<i class="fa fa-sort-desc"></i></strong></button>
						<div class="toggleContent">
							<div class="blk-b">
								<ul class="toggleContentList ph10">
								</ul>
							</div>
						</div>
					</li> --%>
					<li class="toggleMenu">
						<button class="toggleCirTrigger"><i class="fa fa-cogs"></i>
						</button>
						<div class="toggleContent">
							<div class="blk-b">
								<ul class="toggleContentList ph10">
									<li>
										<a href="#updateDeliver" class="fancy-btn">
											<h4 class="mar-0"><i class="fa fa-truck"></i>&nbsp;&nbsp;Shipping</h4>
											<span class="tip">Update delivery frequency</span>
										</a>
									</li>
									<li>
										<a href="#unsubscribe" class="fancy-btn">
											<h4 class="mar-0"><i class="fa fa-times"></i>&nbsp;&nbsp;Unsubscribe</h4>
											<span class="tip">Cancel this subscription</span>
										</a>
									</li>
								</ul>
							</div>
						</div>
					</li>
				</ul>
				
				<ul class="inline">
					<spring:url value="/user/subscription/toggle-rating?s=${subscription.id}&w=f" var="toggleFLink" htmlEscape="true"></spring:url>
					<li class="pw5">
						<a class="btnTxt favoriteBtn" data-active="${subscription.rating.favorited }" href="${toggleFLink }">
							<span class="icon"></span>
							<span class="txt"></span>
						</a>
					</li>
					<spring:url value="/user/subscription/toggle-rating?s=${subscription.id}&w=r" var="toggleRLink" htmlEscape="true"></spring:url>
					<li class="pw5">
						<a class="btnTxt recommendBtn" data-active="${subscription.rating.recommended}" href="${toggleRLink }">
							<span class="icon"></span>
							<span class="txt"></span>
						</a>
					</li>
				</ul>
				<%-- <h3><span class="bhi"><i class="fa fa-tags"></i></span>${category }</h3> --%>
			</div>
			<div class="blk-content">
				
				<div class="card150 prefix-clear">
					<div class="card-face">
						<div class="card-holder">
							<img src="${productImage }">
						</div>
					</div>
					<div class="card-content">
						<div><h2 class="fnor">${productTitle }</h2></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

