
<div class="blk">
	<div class="blk-content">
		<div>
			<a class="toRight fancy-btn blk-s-btn" href="#updateReceiverAddress">
				<i class="fa fa-plus"></i>&nbsp;&nbsp;Change
			</a>
			<h2  class="fnor">
				<span class="bhi "><i class="fa fa-truck"></i></span>
				<span>Shipping Address</span>
			</h2>
		</div>
		<p>Your subscriptions will be shipped to a default shipping that you saved in your account.</p>
		<br/><br/>
		<div id="blkShipping">
		<c:choose>
			<c:when test="${not empty address}">
				<c:set var="name" value="${address.name}" />
				<c:set var="street" value="${address.street}" />
				<c:set var="city" value="${address.city}" />
				<c:set var="state" value="${address.state.state}" />
				<c:set var="zipcode" value="${address.zipcode}" />
				<!-- 
				<p><strong>Default address</strong></p>
	 			<br/>
	 			 -->
	 			<table class="overviewListing" style="width: 100%;">
	 				<tr>
	 					<td class="c-20">
	 						<p class="overviewListingTitle">name</p>
	 					</td>
	 					<td class="c-80">
	 						<h4 class="overviewListingStats">${name }</h4>
	 					</td>
	 				</tr>
	 				<tr>
	 					<td>
	 						<p class="overviewListingTitle">Address</p>
	 					</td>
	 					<td>
							<h4 class="overviewListingStats">
								<span>${street}</span>
								<br/><span>${city},&nbsp;${state}&nbsp;${zipcode}</span>
							</h4>
	 					</td>
	 				</tr>
	 			</table>
			</c:when>
			<c:otherwise>
				<div class="toCenter">
					<a class="btn plainBtn fancy-btn" href="#updateReceiverAddress">
							<i class="fa fa-pencil"></i>&nbsp;&nbsp;
						<span>Create new address</span>
					</a>
				</div>
			</c:otherwise>
		</c:choose>
		</div>
	</div>	
</div>




<div id="updateReceiverAddress" style="display: none">
	<div class="modal-header">
		<p>Update shipping info</p>
	</div>
	<div class="formResponse"></div>
	<spring:url value="/user/checkout/update-shipping" var="updateShipping" htmlEscape="true"/>
	<form id="updateShippingForm" class="updateShippingForm a" method="POST" action="${updateShipping}">
	<div class="modal-content">
		<table class="c-100">
			<tr>
				<td colspan="6">
					<label><strong>Name</strong></label>
					<div class="input-txt">
						<input autocomplete="off" class="_name" type="text" name="_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<label><strong>Address</strong></label>
					<div class="input-txt">
						<input  autocomplete="off" class="_street" type="text" name="_street"/>
					</div>
				</td>
			</tr>
			<tr>
				<td class="c-30" colspan="2">
					<label><strong>City</strong></label>		
					<input autocomplete="off" class="_city" name="_city" type="text"/>
				</td>
				<td class="c-30" colspan="2">
					<label><strong>State</strong></label>
					<select name="_state" class="_state c-100">	
						<jsp:include page="../../../global/modules/address.jsp"></jsp:include>
					</select>
				</td>
				<td class="c-30" colspan="2">
					<label><strong>Zipcode</strong></label>
					<input autocomplete="off" class="_zipcode" name="_zipcode" type="text"/>
				</td>
			</tr>
			<tr>
				<td><input type="hidden" name="_pid" value="${product.id}"/></td>
			</tr>
		</table>
	</div>
	<div class="modal-footer">
		<button class="btn plainBtn" type="submit"><strong>Update</strong></button>
	</div>
	</form>
</div>

