


<div class="main-content">
	<div class="blk-b">
		<div class="blk-header">
			<ul class="toggleMenuList toRight">
				<li class="toggleMenu">
					<span>Calculator</span>&nbsp;&nbsp;
					<span class="toggleCirTrigger"><i class="fa tblue fa-calculator"></i></span>
					<div class="toggleContent">
						<div class="blk-b">
							<div class="panel-header">
								<h3><span id="calPrice" class="toRight"></span>Original Price</h3>
							</div>
							<ul class="toggleContentList">
								<li>
									<ul id="sopts" data-price="0">
									</ul>
								</li>
							</ul>
							<div class="panel-footer">
							<p class="tip"><strong>Formula:</strong> original_price * (100 - save_rate) / 100</p>
							</div>
						</div>
					</div>
				</li>
			</ul>
			<h1 class="fnor">Subscription Option</h1>
		</div>
		
		<div class="modal-content blk-content">		
			<table class="p10">
				<tr>
					<td class="c-30">
						<label><strong>Save Rate</strong></label>
						<p class="tip">A percentage of discount that you offer to customers for subscribing the item.</p>
					</td>
					<td class="c-70">
						<ul class="inline">
							<li>
								<input placeholder="Save rate" class="mx100" name="_saveRate" type="text">
								<span>%</span>
							</li>
							<li style="margin-left: 200px;">	
								<!-- <div class="box-border p10">
									<p class="tip">Original Price: $10</p>
									<p class="tip">Save rate: 5%</p>
									<p class="tip">Subscribe Price: $9.5</p>
								</div> -->
							</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td>
						<label><strong>Shipments</strong></label>
						<p class="tip">Condition to qualify customers for save_rate discount.</p>
					</td>
					<td>
						<select name="_shipment" >
							<option value="0">Select...</option>
							<option value="2">2 shipments</option>
							<option value="3">3 shipments</option>
							<option value="4">4 shipments</option>
							<option value="5">5 shipments</option>
							<option value="6">6 shipments</option>
							<option value="7">7 shipments</option>
							<option value="8">8 shipments</option>
							<option value="9">9 shipments</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label><strong>Frequency</strong></label>
						<p class="tip">The most common frequency that you expect customers will need the item again.</p>
					</td>
					<td>
						<select name="_freq" >
							<option value="0">Select...</option>
							<option value="7">Weekly</option>
							<option value="14">Every 2 weeks</option>
							<option value="21">Every 3 weeks</option>
							<option value="28">Every 4 weeks</option>
							<option value="35">Every 5 weeks</option>
							<option value="42">Every 6 weeks</option>
							<option value="49">Every 7 weeks</option>
							<option value="56">Every 8 weeks</option>
						</select>
						<p class="tip"><i>*Customers may choose different delivery frequency for their need</i></p>
					</td>
				</tr>
				
			</table>
		</div>
		<br/>
		<div class="panel-footer prefix-clear">
			<button class="toRight btn flatBlueBtn" type="submit"><strong>Publish</strong></button>
			<a class="btn backBtn" data-curr="#subscriptionBlk" data-next="#shippingBlk"><strong>Back</strong></a>
		</div>
	</div>
</div>
