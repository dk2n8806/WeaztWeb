<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>My Listings - Weazt</title>

<style type="text/css">
.galleryDisplay li {
	display: inline-block;
	margin-bottom: 20px;
}
</style>
</head>
<body>


<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>
<jsp:include page="../../components/user-navigation.jsp"></jsp:include>

<div class="main-content">	
	<div class="olym-wrapper">
		<div class="olymLeft">
			<jsp:include page="nav/listing_nav.jsp"></jsp:include>
		</div>
		<div class="olymArena">	
			<div class="col-wrapper">
				<div class="col">		
					<div class="blk prefix-clear">
						<div class="blkTabNav">
							<spring:url value="/user/listings/${productId}" var="productLink" htmlEscape="true"></spring:url>
							<spring:url value="/user/listings/photo/${productId}" var="galleryLink" htmlEscape="true"></spring:url>
							<ul>
								<li>
									<a class="productTitle" href="${productLink}" data-title="${productTitle }">
										<i class="fa fa-file-text-o"></i>
										&nbsp;Overview
									</a>
								</li>
								<li>
									<a class="active productTitle" href="${galleryLink}" data-title="${productTitle }">
										<i class="fa fa-photo"></i>
										&nbsp;Gallery
									</a>
								</li>
								<%-- 
								<li>
									<a class="productTitle" href="${galleryLink}" data-title="${productTitle }">
										<i class="fa fa-truck"></i>
										&nbsp;Shipments
									</a>
								</li>
								<li>
									<a class="productTitle" href="${galleryLink}" data-title="${productTitle }">
										<i class="fa fa-users"></i>
										&nbsp;Customers
									</a>
								</li>
								<li>
									<a class="productTitle" href="${galleryLink}" data-title="${productTitle }">
										<i class="fa fa-line-chart"></i>
										&nbsp;Chart
									</a>
								</li>
								<li>
									<a class="productTitle" href="${galleryLink}" data-title="${productTitle }">
										<i class="fa fa-cogs"></i>
										&nbsp;Configure
									</a>
								</li>
								 --%>
							</ul>
						</div>
					</div>			
				</div>
				<br/>
				<br/>
				<div class="col75">
					<div class="blk-b">
						<div class="panel-header prefix-clear">
							<a class="toRight tblue fancy-btn" href="#uploadMainForm"><i class="fa fa-pencil"></i>&nbsp;&nbsp;Change Display</a>	
							<h3>
								<!-- 
								<span class="bhi"><i class="fa fa-photo"></i></span>
								 -->
								<span class=""><strong>Item's Photos</strong></span>
							</h3>
						</div>
						<div class="blk-content">
							<p>Create more thumbnails to show the item's best features</p>
							<br/>
							<br/>
							<ul class="galleryDisplay">
							<c:forEach var="photo" items="${gallery}">
								<c:set var="photoId" value="${photo.id}" />
								<c:set var="photoImage" value="${photo.image }" />
								<li id="photo${photoId }">
									<div class="holder150">
										<img src="${photoImage }"/>
									</div>
									<br/>
									<spring:url value="/user/listing/delete-gallery-photo" var="deletePhoto" htmlEscape="true" />
									<form class="deletePhotoForm" action="${deletePhoto }" method="POST">
										<input type="hidden" name="pid" value=${template.productId }>
										<input type="hidden" name="gid" value=${photoId}>
										<button class="fbtn fbtn-red"><i class="fa fa-trash"></i>&nbsp;&nbsp;remove</button>
									</form>
								</li>
							</c:forEach>
							</ul>
						</div>
						<div class="panel-footer toCenter">
							<a class="btn fancy-btn" href="#uploadPhotoForm"><i class="fa fa-plus"></i>&nbsp;&nbsp;Upload Thumbnails</a>
						</div>
					</div>
				</div>
				<div class="col25">
					
					<c:set var="id" value="${template.productId }" />
					<c:set var="title" value="${template.productTitle }"/>
					<c:set var="price"><fmt:formatNumber value="${template.productPrice / 100}" maxFractionDigits="2" /></c:set>
					<c:set var="category" value="${template.category }" />
					<c:set var="imagePath" value="${template.productImage }"></c:set>
		
					<div class="blk-b">
						<div class="story-wrapper">
							<div class="story-box">
								<a href="${productLink}" title="${imagePath }"><img src="<c:out value="${imagePath }" />" /></a>
							</div>
							<div class="blk-content">
								<p class="story-title">${title}</p>
								<ul class="overviewListing">
									<li>
										<p class="overviewListingTitle">${category }</p>
										<h4 class="overviewListingStats">$${price}</h4>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<div id="uploadMainForm" style="display: none;">
	<spring:url value="/user/listing/change-main-photo" var="uploadPhotoForm" htmlEscape="true"></spring:url>
	<form id="changeMainPhotoForm" action="${uploadPhotoForm}" method="POST" enctype="multipart/form-data">
	<div class="modal-header">
		<p>Update Display Photo</p>
	</div>
	<div class="modal-content">
		<div class="upload-file" style="display: block; position: relative; width: 300px; height: 225px;">	
			<div class="trigger">
				<label for="mainImg">
					<i class="fa fa-photo"></i>
					<br/>
				</label>
				<input  id="mainImg" name="_image" type="file"/>
			</div>
			<div class="preview">
				<div class="sec-red"></div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<input type="hidden"  value="${template.productId}" name="_pid">
		<button type="submit" class="btn"><strong>Save</strong></button>
	</div>
	</form>
</div>
	




<div id="uploadPhotoForm" style="display: none;">
	<spring:url value="/user/listing/upload-gallery-photo" var="uploadPhotoForm" htmlEscape="true"></spring:url>
	<form id="uploadPhotoForm" action="${uploadPhotoForm}" method="POST" enctype="multipart/form-data">
	<div class="modal-header">
		<p>You can upload all 8 photos as a time</p>
	</div>
	<div class="modal-content">
		<div class="response hide">
			<div class="p10 blk-green"><span><i class="fa fa-spinner fa-spin"></i>&nbsp;&nbsp;Uploading</span></div>
		</div>
		<br/>
		<div class="upload-file" style="display: inline-block; position: relative; width: 150px; height: 150px;">	
			<div class="trigger">
				<label for="img">
					<i class="fa fa-photo"></i>
					<br/>
				</label>
				<input  id="img" name="_files" type="file"/>
			</div>
			<div class="preview">
				<div class="sec-red"></div>
			</div>
		</div> 
		<div class="upload-file" style="display: inline-block; position: relative; width: 150px; height: 150px;">	
			<div class="trigger">
				<label for="img1">
					<i class="fa fa-photo"></i>
					<br/>
				</label>
				<input  id="img1" name="_files" type="file"/>
			</div>
			<div class="preview">
				<div class="sec-red"></div>
			</div>
		</div> 
		<div class="upload-file" style="display: inline-block; position: relative; width: 150px; height: 150px;">	
			<div class="trigger">
				<label for="img2">
					<i class="fa fa-photo"></i>
					<br/>
				</label>
				<input  id="img2" name="_files" type="file"/>
			</div>
			<div class="preview">
				<div class="sec-red"></div>
			</div>
		</div> 
		<div class="upload-file" style="display: inline-block; position: relative; width: 150px; height: 150px;">	
			<div class="trigger">
				<label for="img4">
					<i class="fa fa-photo"></i>
					<br/>
				</label>
				<input  id="img4" name="_files" type="file"/>
			</div>
			<div class="preview">
				<div class="sec-red"></div>
			</div>
		</div> 
		
		<div></div>
		
		<div class="upload-file" style="display: inline-block; position: relative; width: 150px; height: 150px;">	
			<div class="trigger">
				<label for="img5">
					<i class="fa fa-photo"></i>
					<br/>
				</label>
				<input  id="img5" name="_files" type="file"/>
			</div>
			<div class="preview">
				<div class="sec-red"></div>
			</div>
		</div> 
		<div class="upload-file" style="display: inline-block; position: relative; width: 150px; height: 150px;">	
			<div class="trigger">
				<label for="img6">
					<i class="fa fa-photo"></i>
					<br/>
				</label>
				<input  id="img6" name="_files" type="file"/>
			</div>
			<div class="preview">
				<div class="sec-red"></div>
			</div>
		</div> 
		<div class="upload-file" style="display: inline-block; position: relative; width: 150px; height: 150px;">	
			<div class="trigger">
				<label for="img7">
					<i class="fa fa-photo"></i>
					<br/>
				</label>
				<input  id="img7" name="_files" type="file"/>
			</div>
			<div class="preview">
				<div class="sec-red"></div>
			</div>
		</div> 
		<div class="upload-file" style="display: inline-block; position: relative; width: 150px; height: 150px;">	
			<div class="trigger">
				<label for="img8">
					<i class="fa fa-photo"></i>
					<br/>
				</label>
				<input  id="img8" name="_files" type="file"/>
			</div>
			<div class="preview">
				<div class="sec-red"></div>
			</div>
		</div> 
	</div>
	<div class="modal-footer">
		<input type="hidden"  value="${template.productId}" name="pid">
		<button type="submit" class="btn"><strong>Upload</strong></button>
	</div>
	</form>
</div>

<script type="text/javascript">

$(document).ready(function() {
	
	$(".deletePhotoForm").on("submit", function(event){
		var id = $(this).find("input[name=gid]").val();
		var $a = $("#photo" + id);
		
		if($a.length == 1) {
			var urlForm = $(this).attr("action");
			var serializedData = $(this).serialize();
	 		$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				success: function(data){
					if(data.state) {
						$a.remove();
					} else {
						alert("fail");
					}
				}, 
				error: function(data) {
					alert("error");
				}
			});
		} else {
			alert("Something went wrong");
		}
		event.preventDefault();
	});
	
	$("#uploadPhotoForm").on("submit", function(event){
		var $this = $(this);
		var $btn = $this.find("button[type=submit]");
		var $res = $this.find(".response");

		var flag = false;
		$this.find("input[name=_files]").each(function(){
			if($(this).val().length > 0) {
				flag = true;
			}
		});
		
		if(flag) {
			//$btn.prepend("<span><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;</span>");
			$res.show();
			$btn.prop('disabled', true);
			$this.submit();
			
		} else {
			alert("Please select an image before uploading");
		}
		event.preventDefault();
	});
	
	$(".productTitle").each(function() {
		var $this = $(this);
		var data = $this.data("title");
		var url = $this.attr("href") + "?" + data.replace(/ /g,"_");
		$this.attr("href", url);
	});
	
	/* 
 	$(".selectFormat").each(function() {		
		var $a = $(this);
		var $b = $a.find("option");
		var data = $a.data("select").toString().toLowerCase();
		$b.each(function() {
			if($(this).val() === data) {
				$(this).prop("selected", true);
			}
		});
	});
	 */

	function handlerImageReview(evt, $_preview_box) {
		var files = evt.target.files; // FileList object
		for (var i = 0, f; f = files[i]; i++) {
		    if (!f.type.match('image.*')) {
		    	alert("Please select an image");
		        continue;
	      	}
	      	var reader = new FileReader();
			reader.onload = (function(theFile) {
	        	return function(e) {
	        		$(imgFormat(e.target.result)).appendTo($_preview_box);
		        };
		      })(f);
	      	reader.readAsDataURL(f);
	    }

	    function imgFormat(src) {
			var str = "<img src=\"" + src + "\">";
			return str;
		};
	};


	
	$(function() {
		$("input[type=file]").on("change", function(event) {
			var $preview_box = $($(this).parent().siblings(".preview"));
			handlerImageReview(event, $preview_box);
		});
	});
  	
  	;function fancyCall() {
  		$(".fancy-btn").fancybox({
  			padding: 0,
  			fitToView: false,
  	    	autoWidth: true, 
 		    width: "700", 
 		    maxWidth: "700", 
  		    minWidth: "500", 
  		    maxHeigh: "90%",
  		    margin: [0, 0, 150, 0],
  	        openEffect: 'none',
  	        closeEffect: 'none',
  	        nextEffect: 'none',
  	        prevEffect: 'none',
  	        scrolling: 'no',
  	        nextSpeed: 0,
  	        prevSpeed: 0,
  	        preload: 3,
  			helpers: {
  				overlay : {
  					css : {
  						'background' : 'rgba(47, 44, 42, 0.80)'
  					},
  					closeClick: false
  				}
  			},
  			'beforeLoad': function() {
  			},
  			'afterClose': function() {
  			} 
  		});
  	}; fancyCall();

 /* 	;function toggleMbContent() {
 		$(".mbBtn, .mbToggle").on("click", function(event) {
 			$(this).siblings(".mbContent").toggle();
 			event.preventDefault();
 		});

 		$(".mbCloseBtn").on("click", function() {
 			$(this).parents(".mbContent").toggle();
 		});
 	} toggleMbContent();
 	
 	$(document).mouseup(function (e)
 	{
 	    var container = $(".mbContent");

 	    if (!container.is(e.target) // if the target of the click isn't the container...
 	        && container.has(e.target).length === 0) // ... nor a descendant of the container
 	    {
 	        container.hide();
 	    }
 	});

 */
 	function init() {
 		$("#dbListingNav").addClass("active");
 		$("#commandPrimary").find("li").eq(0).addClass("active");
 	}; init();
});


</script>
</body>
</html>