
<c:set var="payoutTotalAmount">
	<fmt:formatNumber value="${payoutTotalAmount / 100 }" maxFractionDigits="2" />
</c:set>

<div class="et">
	<span class="toRight">$${payoutTotalAmount }&nbsp;&nbsp;<i class="fa fa-caret-down"></i></span>
	<p>Sales</p>
</div>
<div class="ec modal-content style">
	<div class="prefix-clear">
	<h3 class="statOverview fnor" data-size="${fn:length(payouts)}" data-total="${payoutSize }" data-name="sale bundle"></h3>
	</div>
	<br/>
	<table class="p10">
		<tr>
			<th class="c-20">Date</th>
			<th class="c-40">Description</th>
			<th class="c-20">Collected</th>
			<th class="c-20">Status</th>
		</tr>
		
		<c:forEach var="payout" items="${payouts}">
			<c:set var="payoutId" value="${payout.id}"/>
			<c:set var="payoutStatus" value="${fn:toLowerCase(payout.status)}"/>
			<c:set var="payoutAmount"> 
				<fmt:formatNumber  value="${payout.amount / 100}" maxFractionDigits="2"/>
			</c:set>
			<c:set var="payoutCreatedOn">
				<fmt:formatDate  value="${payout.createdOn}"/>
			</c:set>
			
			<c:choose>
				<c:when test="${payout.getClass().name == 'com.common.entity.payout.SalePayout'}">
					<c:set var="payoutDescription" value="Subscription sales" />
				</c:when>
			</c:choose>
			<%-- 
			<c:set var="transaction" value="${payout.transaction }" />
			<c:if test="${not empty transaction}">
				<c:set var="transactionId" value="${transaction.id}" />
				<c:set var="transactionOn">
					<fmt:formatDate value="${transaction.createdOn }"/>
				</c:set>
				<c:set var="commissionRate">
					<fmt:formatNumber value="${transaction.commissionRate / 10000 }" />
				</c:set>
				<c:set var="depositedAmount">
					$<fmt:formatNumber value="${payoutAmount * (1 - commissionRate)}" maxFractionDigits="2" />
				</c:set>
			</c:if>
			 --%>
			<tr style="border-bottom: 0">
				<td><p>${payoutCreatedOn}</p></td>
				<td>
					<p>${payoutDescription }</p>
				</td>
				<td><p>$${payoutAmount }</p></td>
				
				<td class="status" data-status="${payoutStatus }"></td>
			</tr>
		</c:forEach>
	</table>
</div>


