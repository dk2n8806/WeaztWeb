

<table class="style p10">
	<tr>
		<th class="c-20">Date</th>
		<th class="c-50">Details</th>
		<th class="c-20">Amount</th>
		<th class="c-20">Status</th>
	</tr>
<!-- 
	private Subscription subscription;
	private Account account;
	private boolean isRequested; -->
	
	<c:forEach var="payment" items="${payments}">
		<c:set var="paymentId" value="${payment.id}"/>
		<c:set var="paymentStatus" value="${fn:toLowerCase(payment.status)}"/>
		<c:set var="paymentAmount">
			<fmt:formatNumber value="${payment.amount / 100}" maxFractionDigits="2"/>
		</c:set>
		<c:set var="paymentCreatedOn" value="${payment.createdOn}"/>
		<c:set var="t" value="${payment.transaction }" />

		<tr class="overview" data-status="${paymentStatus }">
			<td><p><fmt:formatDate value="${paymentCreatedOn}"/> </p></td>
			<td>
				<p class="description"></p>
				<p style="font-size: 13px;" class="tip">Payment number: <strong>#${paymentId }</strong></p>
				
				<c:if test="${not empty t}">
					<p class="tip">Transaction No: ${t.id }</p>
					<p class="tip">processed on: ${t.createdOn }</p>
				</c:if>
			</td>
			<td><p class="tgreen">$${paymentAmount }</p></td>
			<td class="status" data-status="${paymentStatus }" ></td>
			
		</tr>
	</c:forEach>
</table>



<script type="text/javascript">
$(document).ready(function() {	
	$(".overview").each(function(){
		var $this = $(this);
		var $desc = $this.find(".description");
		var $status = $this.find(".status");
		var s = $this.data("status");
		
		setDescription($desc, s);
		setStatus($status, s);
	});
	
	function setDescription($a, s) {
		var str = "";
		if(s === "pending") {
			str = "<p><strong>Subscription payment</strong></p>";
		} else if(s === "completed") {
			str = "<p><strong>Subscription payment</strong></p>";
		} else if(s === "voided") {
			str = "<p><strong>Canceled subscription payment</strong></p>";
		}
		$a.html(str);
	}
	
	function setStatus($a, s) {
		var str = "";
		if(s === 'pending'){
			str = "<p><i class=\"fa tgray fa-ellipsis-h\">&nbsp;&nbsp;Pending</i></p>"
		} else if(s === 'completed') {
			str = "<p><i class=\"fa tgreen fa-check-square-o tgreen\">&nbsp;&nbsp;Completed</i></p>"
		} else if(s === 'voided') {
			str = "<p><i class=\"fa tred fa-times\">&nbsp;&nbsp;Voided</i></p>"
		}
		$a.html(str);
	}
});
</script>



