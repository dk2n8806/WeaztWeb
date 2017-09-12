

<c:if test="${orderSize > 0 }">
<div class="no">
	<spring:url var="orderLink" value="/user/orders" htmlEscape="true"></spring:url>
	<a class="link toRight" href="${orderLink }">process order request&nbsp;&nbsp;<i class="fa fa-angle-double-right"></i></a>
	<p>
		<span class="toRight tip">${createdOn }</span>
		<span class=""><i class="fa fa-cubes"></i></span>
		&nbsp;&nbsp;
		<span>You have  <span><a class="link" href="${orderLink }">${orderSize}&nbsp;order</a></span>.</span>
	</p>
</div>
</c:if>