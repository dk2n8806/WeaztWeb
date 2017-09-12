
<c:set var="username" value="${merchant.merchantName}"/>
<c:set var="business" value="${merchant.merchantBusiness}" />
<c:set var="website" value="${merchant.merchantWebsite}" />

<c:set var="city" value="${merchant.address.city}" />
<c:set var="state" value="${fn:toLowerCase(merchant.address.state)}" />
<c:set var="zipcode" value="${merchant.address.country}" />
<c:set var="address">
	${city},&nbsp;<span class="fontCap">${state}</span>,&nbsp;${zipcode }
</c:set>

<c:set var="phone" value="${merchant.phone.phoneNumber }" />

<div class="blk">
	<div class="blk-header prefix-clear">
		<!-- 
		<a class="btn toRight fancy-btn tgray" href="#reportMerchant"><i class="fa fa-flag"></i>&nbsp;&nbsp;Report this merchant</a>
 		-->
		<h1 class="ftrend">
			<span>Hey, I'm ${username}</span>
		</h1>	
	</div>
</div>

<div id="reportMerchant" style="display: none;">
	<div class="modal-header">
		<p>Are you sure want to report this merchant</p>
	</div>
	<div class="modal-content">
		<p>If you think there is an approximate activities about this merchant, please let us know.</p>
		<br/>
		<table class="p10">
			<tr>
				<td>
					<button class="btn p10 c-70"><strong>This merchant shouldnot be here</strong></button>
				</td>
			</tr>
			<tr>
				<td>
					<button class="btn p10 c-70"><strong>The products are not good quality</strong></button>
				</td>
			</tr>
			<tr>
				<td>
					<p><span class="btn p10 c-70"><strong>Other</strong></span></p>
					<div class="ph20">
						<label><strong>Please describe the reason</strong></label>
						<textarea rows="" cols=""></textarea>
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>

