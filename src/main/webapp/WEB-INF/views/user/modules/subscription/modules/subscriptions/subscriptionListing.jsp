<c:choose>
	<c:when  test="${fn:length(subscriptions) > 0}">	
		<div class="col-wrapper">
	
			<c:forEach var="subscription" items="${subscriptions }">
				<c:set var="subscriptionId" value="${subscription.subscriptionId}" />
				<c:set var="subscribedPrice">
					<fmt:formatNumber  value="${subscription.subscriptionValue / 100}" maxFractionDigits="2"></fmt:formatNumber>
				</c:set>
				<c:set var="subscribedStatus" value="${fn:toLowerCase(subscription.status)}" />
				 
				<c:set var="productImage" value="${subscription.productImage}" />
				<c:set var="productTitle" value="${subscription.productTitle}" />
				<c:set var="category" value="${subscription.category}" />
				<c:set var="min" value="${subscription.hadShipped}" />
				<c:set var="max" value="${subscription.nos}" />
				<c:set var="scheduledOn"><fmt:formatDate value="${subscription.scheduledOn }"/></c:set>
				<%-- <c:set var="merchantId" value="${subscription.merchantId}" />
				<c:set var="merchantImage" value="${subscription.merchantImage}" />
				<c:set var="merchantName" value="${subscription.merchantName}" />
				<c:set var="merchantCompany" value="${subscription.merchantCompany}" />
				
				
				<spring:url var="merchantLink" value="/show/merchants?id=${merchantId }&p=${merchantName }&b=${merchantCompany }" htmlEscape="true" />
				 --%>
				<spring:url var="subscriptionDetailLink" value="/user/subscriptions/${subscriptionId }" htmlEscape="true" />
				<spring:url var="favoriteToggle" value="/user/subscription/favorite/toggle?_subscriptionId=${subscriptionId}" htmlEscape="true" />
				<spring:url var="starToggle" value="/user/subscription/star/toggle?_subscriptionId=${subscriptionId}" htmlEscape="true" />
				 
				 
								 
				<fmt:formatDate  value="${subscription.scheduledOn}" pattern="dd" var="day"  />
				<fmt:formatDate  value="${subscription.scheduledOn}" pattern="MMM" var="month"  />
				<fmt:formatDate  value="${subscription.scheduledOn}" pattern="yyyy" var="year"  />
				 <div class="col-2">
						<div class="blk-b">
							<div class="blk-header">
								
								<div class="toRight">
									<ul class="inline">
										<li>
											<div class="date date-small">
												<div class="month orange">
													<p>${month }</p>
												</div>
												<div class="day">
													<p>${day }</p>
												</div>
											</div>
										</li>
									</ul>
								</div>
								<h3 class="fontCap"><span class="bhi"><i class="fa fa-tags"></i>${category }</span></h3>
							</div>
							<div class="blk-content">
								<div class="card100">
									<div class="prefix-clear">
										<div class="card-face">
											<div class="card-holder">
												<img src="${productImage }" />
											</div>
										</div>
										<div class="card-content">
											<div>
											<h3 class="fnor"><a class="title" href="${subscriptionDetailLink}"><span>${productTitle }</span></a></h3>
											</div>
										</div>
									</div>
								</div>
							</div>
							<%-- <div class="blk-footer prefix-clear">
								<p class="toRight">Next Deliver: <strong>${scheduledOn}</strong></p>
							</div> --%>
						</div>
					</div>
				 
				 
				 
				<%-- <div id="s${subscriptionId}"  class="col-4">
					<div class="blk-b">
						<div class="story-box">
							<a class="title subscriptionLink" href="${subscriptionDetailLink}"><img src="${productImage }" /></a>
						</div>
						<div class="blk-content">
							<div class="story-title">
								<p><a class="title subscriptionLink" href="${subscriptionDetailLink}">${productTitle }</a></p>
							</div>	
						</div>
						<div class="blk-footer">
							<ul class="overviewListing">
								<li>
									<p>
										<i class="fa fa-tags"></i>
										&nbsp;
										<span class="fontCap">${category}</span>
									</p>
								</li>
								<li>	
									<p>
										<span>
											<i class="fa fa-check-circle"></i>
											&nbsp;
											<span class="tgreen" style="text-transform: capitalize;">${fn:toLowerCase(subscribedStatus)}</span>
										</span>
									</p>
								</li>
								<li>
									<p>
										<i class="fa fa-retweet"></i>
										&nbsp;
										<span><strong>${min}</strong> out of <strong>${max}</strong></span>
									</p>
								</li>
								<li>
									<p>
										<span><i class="fa fa-calendar"></i></span>
										&nbsp;
										<span>${scheduledOn }</span>
									</p>
								</li>
							</ul>
						
						</div>
					</div>
				</div> --%>
			</c:forEach>
		</div>
	</c:when>
</c:choose>


<script type="text/javascript">

$(document).ready(function() {	
	
	$(".title.subscriptionLink").each(function() {
		var $this = $(this);
		var data = $this.text();
		var url = $this.attr("href") + "?" + data.replace(/ /g,"-");
		$this.attr("href", url);
	});
	

});
</script>