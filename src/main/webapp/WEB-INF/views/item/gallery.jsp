<div class="blk">
	<div class="blk-content">
		<c:set var="pImage" value="${product.displayImage.path}" />
		<div class="gallery-blk">
			<div class="main-blk">
				<span class="gallery"><img src='<c:out value="${pImage}"/>'/></span>
			</div>						
			<div class="thumbnail-blk">
				<ul>
					<li class="active">
						<img src="${pImage }"/>
					</li>
					<c:forEach items="${product.gallery}" var="thumb">
					<c:set var="thumbnail" value="${thumb.imagePath.path}"/>
					<li>
						<img src="${thumbnail }"/>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>	
		<%-- <hr/>				
		<div class="p10">
			<p><strong>Love this item? Subscribe now!</strong></p>
			<p class="pepleRecommeded" data-recommended="${recommends}"></p>
		</div>
		 --%>
	</div>
</div>


<script type="text/javascript">

$(document).ready(function() {
	/* $(".pepleRecommeded").each(function() {
		var data = $(this).data("recommended");
		if($.type(data) === "number") {
			if(data > 0) { 
				var str = "person";
				if(data > 1) {
					str = " people";
				} 
				$(this).append("<i class=\"fa tblue fa-thumbs-o-up\"></i>&nbsp;" + data + " " + str + " recommended this item");
			}
		}
	});
	 */
	$(".thumbnail-blk li").click(function(){
		if(!$(this).hasClass("active")){
			$(".thumbnail-blk li").removeClass("active");
			$(this).addClass("active");
			$(".gallery img").attr("src", $(this).find("img").attr("src"));
		}
	});
});
</script>