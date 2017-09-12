
<div class="blk">
	<div class="blk-content">
		<div>
			<h2 class="fnor">
				<span class="bhi "><i class="fa fa-bullhorn"></i></span>
				<span>Promotion</span>
			</h2>
		</div>
		<!-- 
		<p>Copy and paste code <strong class="tgreen">WEZNOW10</strong> to get <strong class="tgreen">10%</strong> additional discount</p>
		 -->
		<spring:url value="/user/checkout/update-promotion" var="updatePromotionForm" htmlEscape="true"/>
		<form id="updatePromotionForm" action="${updatePromotionForm}" method="POST">
		<div class="modal-content">
			<ul class="inline promo">
				<li><label><strong>Enter promotion code</strong></label></li>
				<li><input type="text" name="code"></li>
				<li><button type="submit" class="btn"><strong>Apply</strong></button></li>
			</ul>
			<div class="serverResponse"></div>
		</div>
		</form>
		<p><i>*Promotion is applicable with subscription price only</i></p>
	</div>
</div>