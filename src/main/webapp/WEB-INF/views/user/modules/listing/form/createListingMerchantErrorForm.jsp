<div>
	<a style="padding: 7px 40px;" class="btn blueBtn fancy-btn" href="#newListingMerchantError"><i class="fa fa-plus"></i>&nbsp;&nbsp;Become merchant</a>

	<div id="newListingMerchantError" style="display: none">
		<div class="modal-content toCenter">
			<h2 class="tblue flead">Become Weazt's Merchant</h2>
			<p class="tgray">Selling for Weazt is fast and free - no commitment and long term contract</p>
			<br/><br/>
			<div>
				<spring:url value="/user/merchant/register" var="merchantRegisterForm" htmlEscape="true" />
				<a class="btn blueBtn" href="${merchantRegisterForm }">
					<span>Start Selling</span> <i class="fa fa-long-arrow-right"></i></a>
			</div>
		</div>
		<div class="modal-footer">
			<p class="tip">
				<i class="fa fa-book"></i>&nbsp;<strong>Guild:</strong>
				&nbsp;
				<a class="link" href="#">Become Weazt's merchant guide</a>
			</p>
		</div>
	</div>
</div>


