
<c:set var="scheduledOn" >
	<fmt:formatDate value="${subscription.scheduledOn}"/>
</c:set>


<fmt:formatDate  value="${subscription.scheduledOn}" pattern="dd" var="day"  />
<fmt:formatDate  value="${subscription.scheduledOn}" pattern="MMM" var="month"  />
<fmt:formatDate  value="${subscription.scheduledOn}" pattern="yyyy" var="year"  />


<div class="blk-b">
	<div class="blk-header prefix-clear">
		<div class="toLeft">
			<div class="date">
				<div class="month orange">
					<p><i class="fa fa-calendar-check-o"></i>&nbsp;&nbsp;${month }</p>
				</div>
				<div class="day">
					<p>${day }</p>
				</div>
			</div>
		</div>
		<h3 style="margin-left: 70px;">Scheduled shipment</h3>
	</div>
	<div class="blk-content">
		<p>Your next shipment is scheduled to be delivered on <strong>${scheduledOn }</strong>.</p>
	</div>
</div>