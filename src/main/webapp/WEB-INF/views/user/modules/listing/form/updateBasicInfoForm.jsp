
<c:set var="productId" 			value="${product.id}" />
<%-- <c:set var="productTitle" 		value="${product.basicInfo.title}" />
<c:set var="productDescription" value="${product.basicInfo.description}" />
<c:set var="productImage" 		value="${product.basicInfo.image}" />
<c:set var="productPrice"><fmt:formatNumber value="${product.basicInfo.price / 100}" minFractionDigits="2" maxFractionDigits="2"/> </c:set>
<c:set var="productCategory" 	value="${product.category.name }" />
<c:set var="productCategoryId" 	value="${product.category.id }" />
<c:set var="productStatus" 		value="${fn:toLowerCase(product.status)}" />
<c:set var="productCreatedOn" 	value="${product.createdOn}" /> --%>

<div id="updateDescriptionForm" class="hide">
	<div class="modal-header">
		<h4>Update Item Info</h4>
	</div>
	
	
	<div class="modal-content">
		<table>
			<tr>
				<td class="c-30">		
					<div class="editNav">
						<p><strong>General</strong></p>			
						<ul>
							<li data-edit="editTitle"><span>Title</span></li>
							<!-- <li data-edit="editImage"><span>Image</span></li> -->
							<!-- <li data-edit="editCategory"><span>Category</span></li> -->
							<li data-edit="editPrice"><span>Price</span></li>
							<li data-edit="editDescription"><span>Description</span></li>
						</ul>
						<p><strong>Shipping</strong></p>			
						<ul>
							<li data-edit="editParcel"><span>Parcel</span></li>
							<li data-edit="editShipping"><span>Shipping</span></li>
						</ul>
						<p><strong>Subscription</strong></p>			
						<ul>
							<li data-edit="editRate"><span>Rate</span></li>
							<li data-edit="editShipment"><span>Shipment</span></li>
							<li data-edit="editFrequency"><span>Frequency</span></li>
						</ul>
					</div>
				</td>
				<td class="c-70">
					<div id="editGeneral" class="editBox">
						<p>Select section you want to update</p>
					</div>
					<div id="editTitle" class="editBox">
						<jsp:include page="newListingModules/updateTitleForm.jsp"></jsp:include>
					</div>
					<div id="editPrice" class="editBox">
						<jsp:include page="newListingModules/updatePriceForm.jsp"></jsp:include>
					</div>
					<div id="editDescription" class="editBox">
						<jsp:include page="newListingModules/updateDescriptionForm.jsp"></jsp:include>
					</div>
					<%-- <div id="editImage" class="editBox">
						<jsp:include page="newListingModules/updateImageForm.jsp"></jsp:include>
					</div> --%>
					<%-- <div id="editCategory" class="editBox">
						<jsp:include page="newListingModules/updateCategory.jsp"></jsp:include>
					</div> --%>
					<div id="editParcel" class="editBox">
						<jsp:include page="newListingModules/updateParcel.jsp"></jsp:include>
					</div>
					<div id="editShipping" class="editBox">
						<jsp:include page="newListingModules/updateShipping.jsp"></jsp:include>
					</div>
					<div id="editRate" class="editBox">
						<jsp:include page="newListingModules/updateRate.jsp"></jsp:include>
					</div>
					<div id="editShipment" class="editBox">
						<jsp:include page="newListingModules/updateShipment.jsp"></jsp:include>
					</div>
					<div id="editFrequency" class="editBox">
						<jsp:include page="newListingModules/updateFrequency.jsp"></jsp:include>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="modal-footer updateReload hide"> 
		<p>Click <a class="btn" href=""><strong>here&nbsp;&nbsp;<i class="fa fa-refresh"></i></strong></a> after you complete updating for immediate apply</p>
	</div>
</div>




<script type="text/javascript">
$(document).ready(function() {
	

	
	$(".updateListingInfo").on("submit", function(event) {
		var $this = $(this);
		var field = $this.data("field");
		var $btn =  $this.find("button[type=submit]");
		var flag  = false;
		if(field === "title") {
			flag = $this.find("input[name=title]").val().length > 0 ? true : false;	
		} 
		else if(field === "description") {
			flag = $this.find("textarea[name=description]").val().length > 0 ? true : false;
		}
		else if(field === "price") {
			flag = $this.find("input[name=price]").val() > 0 ? true : false;
		} 
		else if(field === "rate") {
			flag = $this.find("input[name=rate]").val().length > 0 ? true : false;
		} 
		else if(field === "shipment") {
			flag = $this.find("select[name=nos]").val() > 0 ? true : false;
		} 
		else if(field === "shipping") {
			var $a = $this.find("input[name=_type]:checked");
			var a = $a.val();
			if(a == "auto" || a == "free") {
				flag = true;
			} else if(a == "flat") {
				flag = $this.find("input[name=_cost]").val() > 0 ? true : false;
			}
		} 
		else if(field === "frequency") {
			flag = $this.find("select[name=frequency]").val() > 0 ? true : false;
		} 
		

		else if(field === "category"){
			flag = $this.find("select[name=_cId]").val() > 0 ? true : false;
		}
		else if(field === "parcel") {
			var flagA = $this.find("input[name=_weight]").val() > 0 ? true : false;
			var flagB = $this.find("input[name=_width]").val() > 0 ? true : false
					 && $this.find("input[name=_height]").val() > 0 ? true : false
					 && $this.find("input[name=_length]").val() > 0 ? true : false;
					 
			flag = flagA || flagB;
		}
		else if(field === "dimension") {
			flag = $this.find("input[name=_width]").val() > 0 
				&& $this.find("input[name=_height]").val() > 0 
				&& $this.find("input[name=_length]").val() > 0 ? true : false;
			
		}
		if(flag) {
			var serializedData = $this.serialize();
		    var urlForm = $this.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				beforeSend: function() {	
					$btn.attr("disabled", true);
					$btn.prepend("<span class=\"loader\"><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;</span>");
			    },
				success: function(data){
					if(data.state) {
						serverResponse(["Updated successfully"], "green");						
					} else {
						serverResponse([data.result], "red");
					}
				}, 
				error: function(data) {
					serverResponse(["[ERROR] Update request fail"], "red");
				},
				complete: function(data) {
					$btn.find(".loader").empty();
					$btn.attr("disabled", false);
					$(".updateReload").show();
					$.fancybox.update();
				}
			});
		}
		event.preventDefault();
	});
	
	/* 
	
	$("#updateDescriptionFormRequest").on("submit", function(event){
		var flag = basicHandler($(this));
		if(flag) {
			$(this).submit();
		}
		event.preventDefault();
	});
	

	
	function basicHandler($a) {
		var $_title = $a.find("input[name=_title]");
		var $_desc = $a.find("textarea[name=_description]");
		
		var ft = ($_title.val().length  > 0) ? true : false;
		var fd = ($_desc.val().length > 0) ? true : false;
		
		toggleError($_title, ft);
		toggleError($_desc, fd);
		return ft && fd;	
	}
	

	
	function toggleError($a, a) {
		if(a) {$a.removeClass("error");}
		else {$a.addClass("error");}
	}
	
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
		$("#updateDescriptionFormRequest input[type=file]").on("change", function(event) {
			var $preview_box = $($(this).parent().siblings(".preview"));
			handlerImageReview(event, $preview_box);
		});
	});
	
});
</script>
