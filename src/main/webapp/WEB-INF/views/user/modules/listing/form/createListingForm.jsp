<div>
	<spring:url value="/user/form/request/create-product" var="newProductForm" htmlEscape="=true"></spring:url>
	<%-- 
	<a style="padding: 7px 40px;" class="secondary-btn fancy-btn twhite blue" href="${newProductForm }"><i class="fa fa-plus"></i>&nbsp;&nbsp;New Product</a>
	 --%>
	<a style="padding: 7px 40px;" class="fancy-btn blueBtn btn twhite" href="#newListing"><i class="fa fa-plus"></i>&nbsp;&nbsp;<strong>New Product</strong></a>
</div>




<div id="newListing" style="display: none;">
	<spring:url value="/user/create/listing" var="createListingForm" htmlEscape="true" />
	<form id="createListingForm" action="${createListingForm}" method="POST" enctype="multipart/form-data">
	<div class="modal-header">
		<h3 class="fnor">New Listing</h3>
	</div>
	<div id="basicBlk" style="display: none1; width: 650px;">
		<jsp:include page="../form/newListingModules/basicBlk.jsp"></jsp:include>
	</div>
	<div id="parcelBlk" style="display: none; width: 650px;">
		<jsp:include page="../form/newListingModules/parcelBlk.jsp"></jsp:include>
	</div>
	<div id="subscriptionBlk" style="display: none; width: 650px;">
		<jsp:include page="../form/newListingModules/subscriptionBlk.jsp"></jsp:include>
	</div>
	</form>
</div>




<script type="text/javascript">

$(document).ready(function() {
	
	$("#createListingForm").on("submit", function(event) {
		var $this = $(this);
		var $a = $this.find("#basicBlk");
		var $b = $this.find("#parcelBlk");
		var $c = $this.find("#subscriptionBlk");
		
		var a = basicHandler($a);
		var b = parcelHandler($b);
		var c = subscriptionHandler($c);
		
		if(a && b && c) {
			$this.submit();
			/* var serializedData = $this.serialize();
		    var urlForm = $this.attr("action");
			$.ajax({
				url:urlForm,
				data: serializedData,
				method: "POST",
				enctype: 'multipart/*',
                processData: false, 
                contentType:false,
				success: function(data){
					if(data.state) {
					} else {
					}
				}, 
				error: function(data) {
					alert("fail");
				}
			});  */
		}
		
		
		
		event.preventDefault();
	});
	
	$(".dirBtn").on("click", function(event) {
		var $this = $(this);
		var currBlk = $this.data("id");
		var nextBlk = $this.data("nid");
		var dir = $this.data("dir");	
		var $currBlk = $("#" + currBlk);
		var $nextBlk = $("#" + nextBlk);
		var flag = false;
		if(dir === "next"){
			if(currBlk === "basicBlk") {
				flag = basicHandler($currBlk);
			}
			if(currBlk === "parcelBlk") {
				flag = parcelHandler($currBlk);
			}
			if(currBlk === "subscriptionBlk") {
				flag = subscriptionHandler($currBlk);
			}
		} 
		if(dir ==="prev") {
			flag=true;
		}
		if(flag) {
			$currBlk.hide();
			$nextBlk.show();
			$.fancybox.update();
		}
		event.preventDefault();
	});
	
	function subscriptionHandler($a) {
		var $_save = $a.find("input[name=_saveRate]");
		var $_min = $a.find("input[name=_min]");
		var $_max = $a.find("input[name=_max]");
		var $_freq = $a.find("select[name=_freq]");
		var $_cost = $a.find("input[name=_shipping]");

		var _save = $_save.val();
		var _min = $_min.val();
		var _max = $_max.val();
		var _freq = $_freq.val();
		var _cost = $_cost.val();
		
		var fsave = ((parseFloat(_save) | 0) >= 0 && $.isNumeric(_save) ) ? true : false;
		var fmin = ((parseInt(_min) | 0) > 0 && Math.floor(_min) == _min && $.isNumeric(_min)) ? true : false;
		var fmax = ((parseInt(_max) | 0) > 0 && Math.floor(_max) == _max && $.isNumeric(_max)) ? true : false;
		var fcom = _max >= _min ? true : false;
		var ffreq = (parseFloat(_freq) | 0) > 3 && $.isNumeric(_freq) ? true : false;
		var fcost = (parseFloat(_cost) | 0) >= 0 && $.isNumeric(_cost) ? true : false;
		
		toggleError($_save, fsave);
		toggleError($_min, fmin);
		toggleError($_max, fmax);
		toggleError($_freq, ffreq);
		toggleError($_cost, fcost);
		
		if(!fcom) {
			toggleError($_min, false);
			toggleError($_max, false);	
		}
		
		return fsave && fmin && fmax && fcom && ffreq && fcost;
	}
	
	function parcelHandler($a) {
		var $_wei = $a.find("input[name=_weight]");
		var $_mun = $a.find("select[name=_mUnit]");
		var $_wid = $a.find("input[name=_width]");
		var $_hei = $a.find("input[name=_height]");
		var $_len = $a.find("input[name=_length]");
		var $_dun = $a.find("select[name=_dUnit]");
		
		var fwei = (parseFloat($_wei.val()) | 0) > 0 ? $.isNumeric($_wei.val()) : false;
		var fwid = (parseFloat($_wid.val()) | 0) > 0 ? $.isNumeric($_wid.val()) : false;
		var fhei = (parseFloat($_hei.val()) | 0) > 0 ? $.isNumeric($_hei.val()) : false;
		var flen = (parseFloat($_len.val()) | 0) > 0 ? $.isNumeric($_len.val()) : false;
		
		var fmun = ($_mun.val() !== "0") ? true : false;
		var fdun = ($_dun.val() !== "0") ? true : false;

		toggleError($_wei, fwei);
		toggleError($_wid, fwid);
		toggleError($_hei, fhei);
		toggleError($_len, flen);
		toggleError($_mun, fmun);
		toggleError($_dun, fdun);
		
		return fwei && fwid && fhei && flen && fmun && fdun;
	}
	
	function basicHandler($a) {
		var $_title = $a.find("input[name=_title]");
		var $_price = $a.find("input[name=_price]");
		var $_desc = $a.find("textarea[name=_description]");
		var $_cat = $a.find("select[name=_categoryId]");
		var $_image = $a.find("input[name=_image]");
		
		var ft = ($_title.val().length  > 0) ? true : false;
		var fp = (parseFloat($_price.val()) | 0) > 0 ? $.isNumeric($_price.val()) : false;
		var fd = ($_desc.val().length > 0) ? true : false;
		var fc = (parseInt($_cat.val()) | 0) > 0 ? true : false;
		var fi = ($_image.val().length > 0) ? true : false;
		
		toggleError($_title, ft);
		toggleError($_price, fp);
		toggleError($_desc, fd);
		toggleError($_cat, fc);
		toggleImageError($_image, fi);
		return ft && fp && fd && fc && fi;	
	}
	function toggleImageError($a, a) {
		if(!a) {
			$(".preview").css("background", "#f5d3d3");
		} else {
			$(".preview").css("background", "#edefed");
		}
		
	}

	
	function toggleError($a, a) {
		if(a) {$a.removeClass("error");}
		else {$a.addClass("error");}
	}
	
	/* $("#createListingForm").on("submit", function(event) {
		var $this = $(this);
		var serializedData = $this.serialize();
		var urlForm = $this.attr("action");
		$.ajax({
			url:urlForm,
			data: serializedData,
			method: "POST",
			success: function(data){
				if(data.state) {
					alert(data.result);
				} else {
					alert("fail");
				}
			}, 
			error: function(data) {
				alert("error");
			}
		});	 
		event.preventDefault();
	}); */
	
	
	
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
		$("#newListing input[type=file]").on("change", function(event) {
			var $preview_box = $($(this).parent().siblings(".preview"));
			handlerImageReview(event, $preview_box);
		});
	});



});
</script>