
<div class="main-content">
	<div class="blk-b">
		<div class="blk-header">
			<h1 class="fnor">Package & Shipping</h1>
		</div>
		
		<div class="modal-content blk-content">		
			<table class="p10">
				<tr>
					<td class="c-30">						
						<label><strong>Weight</strong></label>
						<p class="tip">Estimated weight of the package that uses for shipping.</p>
					</td>
					<td class="c-70">
						
						<ul class="inline">
							<li style="margin-right: 5px;">
								<label><strong>Weight</strong></label>
								<input name="_weight" class="mx100" type="text" />
							</li>
							<li style="margin-right: 5px;">
								<label><strong>Unit</strong></label>
								 <select name="_mUnit">
									<option value="g">g</option>
									<option selected value="oz">oz</option>
									<option value="lb">lb</option>
									<option value="kg">kg</option>
								</select> 
							</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td>						
						<label><strong>Measurement</strong></label>
						<p class="tip">The dimension of the package that uses for shipping.</p>
					</td>
					<td>
						<ul class="inline">
							<li style="margin-right: 5px;">
								<label><strong>Width</strong></label>
								<input name="_width" class="mx100" type="text" />
							</li>
							<li style="margin-right: 5px;">
								<label><strong>Height</strong></label>
								<input name="_height" class="mx100"type="text" />
							</li>
							<li style="margin-right: 5px;">
								<label><strong>Length</strong></label>
								<input name="_length" class="mx100" type="text" />
							</li>
							<li style="margin-right: 5px;">
								<label><strong>Unit</strong></label>
								<select name="_dUnit">
									<option value="cm">cm</option>
									<option selected value="in">in</option>
									<option value="ft">ft</option>
									<option value="nm">mm</option>
									<option value="m">m</option>
									<option value="yd">yd</option>
								</select>
							</li>
						</ul>
						
					</td>
				</tr>
				<tr>
					<td><label><strong>Shipping method</strong></label></td>
					<td>
						<div class="p10">
							<input style="float: left; margin-right: 10px;" id="freeType" type="radio" value="free" name="_shipType" />
							<label   for="freeType"><strong>Free Shipping</strong></label>
							<p class="tip">Provide free of charge on shipping</p>
						</div>
						<div class="p10">
							<input style="float: left; margin-right: 10px;" id="autoType" type="radio" value="auto" name="_shipType" />
							<label for="autoType"><strong>Automatical calculate</strong></label>
							<p class="tip">Allow Weazt to auto calculate shipping cost at checkout</p>
						</div>
						<div class="p10">
							<input  style="float: left; margin-right: 10px;" id="flatType" type="radio" value="flat" name="_shipType" />
							<label for="flatType"><strong>Flat rate shipping</strong></label>
							<p class="tip">The specified flat rate that will always be charged for shipping the item</p>
							<br/>
							<div>
								<span>$</span>
								<input placeholder="0.00" class="mx100" name="_shipping" type="text">
								<span>/ shipment</span>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<br/>
		<div class="panel-footer prefix-clear">
			<a class="btn nextBtn toRight" data-curr="#shippingBlk" data-next="#subscriptionBlk" href="#"><strong>Next</strong>&nbsp;<i class="fa fa-long-arrow-right"></i></a>
			<a class="btn backBtn" data-curr="#shippingBlk" data-next="#basicBlk"><strong>Back</strong></a>
		</div>
	</div>
</div>





