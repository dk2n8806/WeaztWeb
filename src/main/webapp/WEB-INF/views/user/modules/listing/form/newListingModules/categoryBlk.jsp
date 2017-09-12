
<div class="main-content">
	<div class="blk-b">
		<div class="blk-header">
			<h1 class="fnor">Category</h1>
			<p>Browse for your product's category. If you do not see your product's category listed above, you may want to check out our categories.</p>
		</div>
		<div class="modal-content">
			<div class="balance-list">
				<ul>
					<li><a class="btn mainCat" href="#foodBers">Beverages</a></li>
					<!-- <li><a class="btn mainCat" href="#hb">Health & Beauty</a></li>
					<li><a class="btn mainCat" href="#household">Household Products</a></li> -->
				</ul>
			</div>
			<br/><br/><br/>
			<div id="foodBers" class="subCatSelect">
				<%-- <ul class="inline subCatList">
					<li><h3>Food&nbsp;&nbsp;</h3></li>
					<c:forEach var="cat" items="${food}">
						<li><a class="btn plainBtn fontCap subCat" data-value="${cat.id }">${cat.name }</a></li>
					</c:forEach>
				</ul>
				<br/> --%>
				<ul class="inline subCatList">
					<li><h3>Berverages&nbsp;&nbsp;</h3></li>
					<c:forEach var="cat" items="${beverage }">
						<li><a class="btn plainBtn fontCap subCat" data-value="${cat.id }">${cat.name }</a></li>
					</c:forEach>
				</ul>
			</div>
			<div id="hb" class="subCatSelect">
				<ul class="inline subCatList">
					<li><h3>Health & Beauty&nbsp;&nbsp;</h3></li>
					<c:forEach var="cat" items="${hb}">
						<li><a class="btn plainBtn fontCap subCat" data-value="${cat.id }">${cat.name }</a></li>
					</c:forEach>
				</ul>
			</div>
			<div id="household" class="subCatSelect">
				<ul class="inline subCatList">
					<li><h3>Household Products&nbsp;&nbsp;</h3></li>
					<c:forEach var="cat" items="${house}">
						<li><a class="btn plainBtn fontCap subCat" data-value="${cat.id }">${cat.name }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<br/>
		<div class="panel-footer prefix-clear">

			<input type="hidden" name="_category" value="0">
			<a class="btn nextBtn toRight" data-curr="#categoryBlk" data-next="#imageBlk" href="#"><strong>Next</strong>&nbsp;<i class="fa fa-long-arrow-right"></i></a>
			<p class="ph5"><i>*You will not able update the product's category once it publishes</i></p>
		</div>
	</div>
</div>
