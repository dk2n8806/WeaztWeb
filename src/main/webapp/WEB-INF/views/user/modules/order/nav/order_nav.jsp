
<div id="commandSub" class="command-menu">
	<ul>
		<li id="ordersProcessLink">
			<spring:url var="queuedOrderLink" value="/user/orders" htmlEscape="true"></spring:url>
			<h4><a href="${queuedOrderLink }">Order Bundles</a></h4>
		</li>
		<li id="orderHistoriesLink">
			<spring:url var="historyOrdersLink" value="/user/orders/transactions" htmlEscape="true"></spring:url>
			<h4><a  href="${historyOrdersLink }">Transactions</a></h4>
		</li>
	</ul>
</div>
