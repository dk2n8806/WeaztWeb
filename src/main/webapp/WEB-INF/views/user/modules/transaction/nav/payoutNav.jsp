<spring:url var="payoutMethodLink" value="/user/payout" htmlEscape="true" />
<spring:url var="payoutEarningLink" value="/user/payout/earnings" htmlEscape="true" />



<div class="blk prefix-clear">
	<div class="blkTabNav">
		<ul>
			<li><a id="payoutMethodNav" href="${payoutMethodLink}"><i class="fa fa-credit-card" aria-hidden="true"></i>&nbsp;&nbsp;Payout methods</a></li>
			<li><a id="payoutEarningNav" href="${payoutEarningLink}"><i class="fa fa-usd" aria-hidden="true"></i>&nbsp;&nbsp;Earnings</a></li>
		</ul>
	</div>
</div>
