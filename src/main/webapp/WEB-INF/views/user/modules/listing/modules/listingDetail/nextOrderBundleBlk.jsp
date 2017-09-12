
<%@ page import="java.util.Date" %>
<c:set var="date" value="<%=new Date(new Date().getTime() + 7*60*60*24*1000)%>"/>
<c:set var="scheduledDate">
	<fmt:formatDate value="${date}"/>
</c:set>

<fmt:formatDate  value="${date}" pattern="dd" var="day"  />
<fmt:formatDate  value="${date}" pattern="MMM" var="month"  />
<fmt:formatDate  value="${date}" pattern="yyyy" var="year"  />

<c:choose>
	<c:when test="${orderIntentRequestSize > 1}">
		<c:set var="str" value="orders" />
	</c:when>
	<c:otherwise>
		<c:set var="str" value="order" />
	</c:otherwise>
</c:choose>

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
		<h3 style="margin-left: 70px;">Scheduled orders</h3>
		<p class="tip" style="margin-left: 70px;">(scheduled for 7 days)</p>
	</div>
	<div class="blk-content">
		<p>You will approximately process  at least <strong class="tgreen">${orderIntentRequestSize}&nbsp;${str }</strong>  in <strong>${scheduledDate}</strong></p>
	</div>
</div>