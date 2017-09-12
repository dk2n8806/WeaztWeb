
			<div id="commandPrimary" class="command-menu">
				<ul>
					<li>
						<spring:url value="/user/admin/display" var="dashboardLink" htmlEscape="true" />
						<h4><a id="" class="torange" href="${dashboardLink}"><i class="fa torange fa-home"></i>&nbsp;&nbsp;Dashboard</a></h4>
					</li>
					<li>
						<spring:url value="/user/admin/messages-center" var="messageCenterLink" htmlEscape="true" />
						<h4><a id="" class="torange" href="${messageCenterLink}"><i class="fa torange fa-home"></i>&nbsp;&nbsp;Message Center</a></h4>
					</li>
					<li id="customerNav" >
						<spring:url value="/user/admin/display/accounts" var="customersLink" htmlEscape="true" />
						<h4><a href="${customersLink}">Account</a></h4>
					</li>
				</ul>
				<hr/>
				<ul>
					<li  id="categoryNav">
						<spring:url value="/user/admin/display/categories" var="categoriesLink" htmlEscape="true" />
						<h4><a href="${categoriesLink}">Category</a></h4>
					</li>
				</ul>
				<hr/>
				<ul>
					<li id="productNav">
						<spring:url value="/user/admin/display/products" var="productsLink" htmlEscape="true" />
						<h4><a  href="${productsLink}">Products</a></h4>
					</li>
				</ul>
				<hr/>
				<ul>
					<li id="subscriptionNav">
						<spring:url value="/user/admin/display/subscriptions" var="subscriptionsLink" htmlEscape="true" />
						<h4><a href="${subscriptionsLink}">Subscriptions</a></h4>
					</li>
				</ul>
				<hr/>
				<ul>
					<li id="orderNav" >
						<spring:url value="/user/admin/display/orders" var="ordersLink" htmlEscape="true" />
						<h4><a href="${ordersLink}">Orders</a></h4>
					</li>
					<li id="orderIntentNav" >
						<spring:url value="/user/admin/display/order_intents" var="orderIntentsLink" htmlEscape="true" />
						<h4><a href="${orderIntentsLink}">OrderIntents</a></h4>
					</li>
				</ul>
				<hr/>
				<ul>
					<li  id="paymentNav" >
						<spring:url value="/user/admin/display/payments" var="paymentLink" htmlEscape="true" />
						<h4><a href="${paymentLink}">Payments</a></h4>
					</li>
					<li  id="paymentRequestNav">
						<spring:url value="/user/admin/display/payment_requests" var="paymentRequestLink" htmlEscape="true" />
						<h4><a href="${paymentRequestLink}">PaymentRequests</a></h4>
					</li>
				</ul>
				<hr/>
				<ul>
					<li  id="payoutNav">
						<spring:url value="/user/admin/display/payouts" var="payoutLink" htmlEscape="true" />
						<h4><a href="${payoutLink}">Payouts</a></h4>
					</li>
					<li id="shipmentNav">
						<spring:url value="/user/admin/display/shipments" var="shipmentLink" htmlEscape="true" />
						<h4><a  href="${shipmentLink}">Shipments</a></h4>
					</li>
				</ul>
			</div>