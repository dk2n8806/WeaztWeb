<spring:url var="paymentMethodLink" value="/user/payment" htmlEscape="true" />
<spring:url var="paymentHistoryLink" value="/user/payment/transactions" htmlEscape="true" />


<div class="blk prefix-clear">
	<div class="blkTabNav">
		<ul>
			<li><a id="paymentMethodNav" href="${paymentMethodLink}"><i class="fa fa-credit-card" aria-hidden="true"></i>&nbsp;&nbsp;Payment methods</a></li>
			<li><a id="paymentHistoryNav" href="${paymentHistoryLink}"><i class="fa fa-exchange" aria-hidden="true"></i>&nbsp;&nbsp;Transactions</a></li>
		</ul>
	</div>
</div>
