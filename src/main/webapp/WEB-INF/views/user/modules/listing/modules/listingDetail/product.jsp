
<c:set var="productId" 			value="${product.id}" />
<c:set var="productTitle" 		value="${product.basicInfo.title}" />
<c:set var="productDescription" value="${product.basicInfo.description}" />
<c:set var="productImage" 		value="${product.displayImage.path}" />
<c:set var="productPrice"><fmt:formatNumber value="${product.basicInfo.price}" maxFractionDigits="2"/> </c:set>
<c:set var="productCategory" 	value="${product.category.name }" />
<c:set var="productStatus" 		value="${fn:toLowerCase(product.status)}" />
<c:set var="productCreatedOn" 	value="${product.createdOn}" />

<div class="blk-b">	
	<div class="blk-header prefix-clear">
		<ul class="toggleMenuList toRight">
			<li class="toggleMenu">
				<button class="toggleTrigger ">Status:&nbsp;<strong class="fontCap productStatusDisplay" data-status="${productStatus}"></strong></button>
				<div class="toggleContent">
					<div class="blk-b">
						<ul class="toggleContentList ph10">
							<li>
								<a href="#activeListing" class="fancy-btn">
									<h4 class="mar-0"><i class="fa fa-globe"></i>&nbsp;&nbsp;Public</h4>
									<span class="tip">Visible to every one.</span>
								</a>
							</li>
							<li>
								<a href="#deactiveQueue" class="fancy-btn">
									<h4 class="mar-0"><i class="fa fa-lock"></i>&nbsp;&nbsp;Private</h4>
									<span class="tip">Only visibble to subscribers.</span>
								</a>
							</li>
							<li>
								<a href="#deleteQueue" class="fancy-btn">
									<h4 class="mar-0"><i class="fa fa-trash"></i>&nbsp;&nbsp;Delete</h4>
									<span class="tip">Request to delete this item.</span>
								</a>
							</li>
						</ul>
					</div>
				</div>
			</li>
			
			
			<li class="toggleMenu">
				<a class="toggleCirTrigger fancy-btn" href="#updateDescriptionForm"><i class="fa fa-cogs"></i></a>
			</li>
		</ul>
		<h3 class="fontCap"><span class="bhi"><i class="fa fa-tags"></i></span>${productCategory }</h3>
	</div>
	<div class="blk-content">
		<div class="card150 prefix-clear">
			<div class="card-face">
				<div class="card-holder">
					<img src="${productImage}">
				</div>
			</div>
			<div class="card-content">
				<div><h2 class="fnor">${productTitle }</h2></div>
				<br/>
				<div class="gallery-blk">			
					<div class="thumbnail-blk">
						<ul>
							<c:forEach items="${product.gallery}" var="thumb">
							<c:set var="thumbnail" value="${thumb.imagePath.path}"/>
							<li>
								<img src="${thumbnail }"/>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="blk-content">
		<p><strong>Description</strong></p>
		<p class="">${productDescription }</p>
	</div>
</div>



