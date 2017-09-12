
<c:set var="feeTotalAmount">
	<fmt:formatNumber value="${feeTotalAmount / 100 }" maxFractionDigits="2" />
</c:set>

<div class="et">
	<span class="toRight">-$${feeTotalAmount}&nbsp;&nbsp;<i class="fa fa-caret-down"></i></span>
	<p>Service fee</p>
</div>
<div class="ec modal-content style">
	<div class="prefix-clear">
	<h3 class="statOverview fnor" data-size="${fn:length(fees)}" data-total="${feeSize }" data-name="service fee"></h3>
	</div>
	<br/>
	<table class="p10">
		<tr>
			<th class="c-20">Date</th>
			<th class="c-40">Description</th>
			<th class="c-20">Fee</th>
			<th class="c-20">Status</th>
		</tr>
		<!-- 
			private Merchant merchant;
	private int amount;
	private FeeStatus status; -->
		
		<c:forEach var="fee" items="${fees}">
			<c:set var="id" value="${fee.id }" />
			<c:set var="status" value="${fee.status}" />
			<c:set var="createdOn">
				<fmt:formatDate value="${fee.createdOn }"/>
			</c:set>
			<c:set var="amount" >
				<fmt:formatNumber value="${fee.amount / 100 }"></fmt:formatNumber>
			</c:set>
			<c:choose>
				<c:when test="${fee.getClass().name == 'com.common.entity.payout.LabelFee'}">
					<c:set var="description" value="Purchased shipping label" />
				</c:when>
			</c:choose>
			
			<tr style="border-bottom: 0">
				<td><p>${createdOn}</p></td>
				<td>
					<p>${description }</p>
				</td>
				<td><p>$${amount}</p></td>
				<td><p class="status" data-status="${status}"></p></td>
			</tr>
		</c:forEach>
	</table>
</div>