
<c:forEach var="nos" items="${merNos}">
	<c:if test="${nos.getClass().name == 'com.common.entity.notification.OrderNotification'}">
		<c:set var="pid" value="${nos.product.id }" />
		<c:set var="size" value="${nos.size}" />
		<c:set var="createdOn">
			<fmt:formatDate value="${nos.createdOn }"/>
		</c:set>
		<div class="no">
			<spring:url var="orderLink" value="/user/orders" htmlEscape="true"></spring:url>
			<p>
				<span class="toRight tip">${createdOn }</span>
				<span class=""><i class="fa fa-paper-plane-o torange"></i></span>
				&nbsp;&nbsp;<span>You have <a class="link" href="${orderLink }">new order request</a></span>
			</p>
		</div>
	</c:if>
</c:forEach>

