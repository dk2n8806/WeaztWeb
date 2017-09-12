<spring:url value="/user/listing/update-category" var="updateCategory" htmlEscape="true"></spring:url>
<form class="updateListingInfo" data-field="category"  action="${updateCategory}" method="post">
	<p><strong>Category</strong></p>
	<p>New category</p>
	<br/>
	<select name="_cId">
	<jsp:include page="../../../../../global/modules/categoryList.jsp"></jsp:include>
	</select>
	<br/><br/>
	<input type="hidden" value="${product.id}" name="_pid">
	<button type="submit" class="btn"><strong>Update</strong></button>
</form>