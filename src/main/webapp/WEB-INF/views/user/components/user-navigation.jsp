

<div class="main-container">
	<div id="dashboardHeaderNav" class="dashboardHeaderNav">
		<div class="dashboard-nav-wrapper">
			<%-- <div class="toRight dashboard-nav">
				<ul>
					<li>
						<spring:url var="adminLink" value="/testadmin/display/customers" htmlEscape="true"></spring:url>
						<a class="toRight" href="${adminLink }" >Test_Admin</a>
					</li>
				</ul>
			</div> --%>
			
			
			
			
			
			<div class="dashboard-nav">
				<ul class="sideDBNav">
					<c:set var="prinRole"><sec:authentication property="principal.role"/></c:set>
					<c:choose>
						<c:when test="${fn:toLowerCase(prinRole) == 'customer' }">
							<li>
								<spring:url var="partnerLink" value="/partner" htmlEscape="true"></spring:url>
								<a  href="${partnerLink}">Sell on Weazt</a>
							</li>
						</c:when>
						<c:when test="${fn:toLowerCase(prinRole) == 'admin' }">
							<li>
								<spring:url var="adminLink" value="/user/admin/display/accounts"></spring:url>
								<a  href="${adminLink}">admin</a>
							</li>
						</c:when>
					</c:choose>
					<li>
					
						<spring:url value="/logout" var="logoutForm" htmlEscape="true"/>
						<form action="${logoutForm}" method="POST">
							<button class="">Sign out&nbsp;&nbsp;<i class="fa fa-sign-out"></i></button>
						</form>
					</li>
				</ul>
				<ul class="mainDBNav">
					<li>
						<spring:url value="/user/dashboard" var="dashboardLink" htmlEscape="true" />
						<a id="dbDashboardNav" href="${dashboardLink}">Dashboard</a>
					</li>
					<li>
						<spring:url value="/user/subscriptions" var="subscriptionLink" htmlEscape="true" />
						<a id="dbSubscriptionNav" href="${subscriptionLink }">Subscriptions</a>
					</li>
					<li>
						<spring:url value="/user/listings" var="listingLink" htmlEscape="true" />
						<a id="dbListingNav" href="${listingLink }">Listings</a>
					</li>
					<li>
						<spring:url value="/user/orders" var="orderLink" htmlEscape="true" />
						<a id="dbTaskNav" href="${orderLink }">Orders</a>
					</li>
					<%-- <li>
						<spring:url value="/user/reports" var="reportLink" htmlEscape="true" />
						<a id="dbReportNav" href="${reportLink }">Reports</a>
					</li> --%>
					<li>
						<spring:url value="/user/setting/account" var="settingLink" htmlEscape="true" />
						<a id="dbSettingNav" href="${settingLink }">Account</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>