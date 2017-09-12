
<spring:url value="/user/subscriptions" var="activeSubscriptionLink" htmlEscape="true"/>
<spring:url value="/user/subscriptions/requests" var="subscriptionRequestLink" htmlEscape="true"/>
<spring:url value="/user/unsubscriptions" var="unsubscriptionsLink" htmlEscape="true"/>
<spring:url value="/user/subscriptions/schedules" var="scheduledLink" htmlEscape="true"/>
<spring:url value="/user/subscriptions/shipments" var="shipmentLink" htmlEscape="true"/>


<div id="commandPrimary" class="command-menu">
	<ul>
		<li id="subscriptionNav">
			<h4><a href="${activeSubscriptionLink}">My Subscriptions</a></h4>
		</li>
		<li id="subscriptionRequestNav">
			<h4><a href="${subscriptionRequestLink}">Open Requests</a></h4>
		</li>
		<%-- 
		<li id="scheduledNav">
			<h4><a href="${scheduledLink}">Next Schedules</a></h4>
		</li> 
		--%>
		<li id="shipmentNav">
			<h4><a href="${shipmentLink}">Shipments</a></h4>
		</li>
		<li id="unsubscriptionsLink">
			<h4><a href="${unsubscriptionsLink}">Unsubscriptions</a></h4>
		</li>
	</ul>
</div>