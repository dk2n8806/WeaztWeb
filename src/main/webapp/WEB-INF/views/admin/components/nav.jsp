
<spring:url value="/user/subscriptions" var="activeSubscriptionLink" htmlEscape="true"/>
<div id="commandPrimary" class="command-menu">
	<ul>
		<li>
			<spring:url value="/testadmin/display/customers" var="displayCustomersLink" htmlEscape="true" />
			<a href="${displayCustomersLink}">Customers</a>
		</li>
		<li>
			<spring:url value="/testadmin/display/order_intents" var="displayOrderIntentsLink" htmlEscape="true" />
			<a href="${displayOrderIntentsLink}">OrderIntents</a>
		</li>
		<li>
			<spring:url value="/testadmin/display/orders" var="displayOrdersLink" htmlEscape="true" />
			<a href="${displayOrdersLink}">Orders</a>
		</li>
		<li>
			<spring:url value="/testadmin/display/order_details" var="displayorderDetailsLink" htmlEscape="true" />
			<a href="${displayorderDetailsLink}">Order Trasactions</a>
		</li>
		<li>
			<spring:url value="/testadmin/display/payments" var="displayPaymentsLink" htmlEscape="true" />
			<a href="${displayPaymentsLink}">Payments</a>
		</li>
		<li>
			<spring:url value="/testadmin/display/payment_requests" var="displayPaymentRequestsLink" htmlEscape="true" />
			<a href="${displayPaymentRequestsLink}">Payment Requests</a>
		</li>
		<li>
			<spring:url value="/testadmin/display/payouts" var="displayPayoutsLink" htmlEscape="true" />
			<a href="${displayPayoutsLink}">Payouts</a>
		</li>
		<li>
			<spring:url value="/testadmin/display/payout_requests" var="displayPayoutRequestsLink" htmlEscape="true" />
			<a href="${displayPayoutRequestsLink}">Payout Requests</a>
		</li>
		<li>
			<spring:url value="/testadmin/display/products" var="displayProductsLink" htmlEscape="true" />
			<a href="${displayProductsLink}">Products</a>
		</li>
		<li>
			<spring:url value="/testadmin/display/shipments" var="displayShipmentsLink" htmlEscape="true" />
			<a href="${displayShipmentsLink}">Shipments</a>
		</li>
		<li>
			<spring:url value="/testadmin/display/subscriptions" var="displaySubscriptionsLink" htmlEscape="true" />
			<a href="${displaySubscriptionsLink}">Subscriptions</a>
		</li>
		<li>
			<spring:url value="/testadmin/display/unsubscribe_requests" var="requestLink" htmlEscape="true" />
			<a href="${requestLink}">Unsubscribe_Requests</a>
		</li>
	</ul>
</div>