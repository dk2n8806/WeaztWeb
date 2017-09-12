<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../../base/headerMetaLink.jsp"></jsp:include>
<title>New Listing - Weazt</title>
<style type="text/css">

.main-content {
	width: 900px;
}

.mainCat {
	width: 100%;
	padding: 15px;
	font-size: 16px;
}

.subCatSelect  {
	display: none;
}

.subCatSelect li {
	vertical-align: middle;
}

#sopts {
	padding: 20px 20px;
}

#sopts li {
	padding: 5px 0px;
}

#sopts li:first-child, 
#sopts li:nth-child(2) {
	border-bottom: none;
}


.toggleMenuList:hover .toggleContent {
	display: block;
}


</style>
</head>
<body>
<jsp:include page="../../../components/header-navigation.jsp"></jsp:include>


<div class="breadcrumb-wrapper blue2 p40 main-container">
	<div class="main-content toCen1ter">
		<div class="twhite">
			<div class="prefix-clear">
				<div class="breadcrumb toRight flatBlue">
					<a id="categoryBlkTag" class="active"><i class="fa fa-check-circle"></i>&nbsp;&nbsp;Category</a>
					<a id="imageBlkTag"><i class="fa fa-check-circle"></i>&nbsp;&nbsp;Photos</a>
					<a id="basicBlkTag"><i class="fa fa-check-circle"></i>&nbsp;&nbsp;Basic</a>
					<a id="shippingBlkTag"><i class="fa fa-check-circle"></i>&nbsp;&nbsp;Package</a>
					<a id="subscriptionBlkTag"><i class="fa fa-check-circle"></i>&nbsp;&nbsp;Subscripiton</a>
				</div>
				<h1 class="fnor">List a new item</h1>
			</div>
		</div>
	</div>
</div>
<br/>


<div>
<spring:url value="/user/create/listing" var="createListingForm" htmlEscape="true" />
<form id="createListingForm" action="${createListingForm}" method="POST" enctype="multipart/form-data">

<div id="categoryBlk" class="itemBlk" style="display: none1;">
	<jsp:include page="form/newListingModules/categoryBlk.jsp"></jsp:include>
</div>
<div id="imageBlk" class="itemBlk" style="display: none;">
	<jsp:include page="form/newListingModules/imageBlk.jsp"></jsp:include>
</div>
<div id="basicBlk" class="itemBlk" style="display: none;">
	<jsp:include page="form/newListingModules/basicBlk.jsp"></jsp:include>
</div>
<div id="shippingBlk" class="itemBlk" style="display: none;">
	<jsp:include page="form/newListingModules/parcelBlk.jsp"></jsp:include>
</div>
<div id="subscriptionBlk" class="itemBlk" style="display: none;">
	<jsp:include page="form/newListingModules/subscriptionBlk.jsp"></jsp:include>
</div>
</form>
</div>
<%-- <br/><br/>
<jsp:include page="modules/listing/productListing.jsp"></jsp:include>


<script type="text/javascript" src='<c:out value="/resources/js/jquery.fancybox.js"></c:out>'></script>

 --%>
 
<%--  
<jsp:include page="../../../components/footer-navigation.jsp"></jsp:include>
 --%>
<script type="text/javascript">

$(document).ready(function() {



	var $itemForm = $("#createListingForm");
	$(".nextBtn").on("click", function(e) {
		var $this = $(this);
		var n = $this.data("next");
		var c = $this.data("curr");
		var $n = $(n);
		var flag = false;
		var error = "";
		//alert($n.length + " "  + n);
		if(c === "#categoryBlk") {
			flag = formCategoryHandler();
			if(!flag){
				error = "Please select a category for the first";
			} 
		}
		else if(c === "#imageBlk") {
			flag = formImageHandler();
			if(!flag) {
				error = "Please upload your product's main photo";
			}
		}
		else if(c === "#basicBlk") {
			flag = formBasicHandler();
			if(!flag) {
				error = "Please complete the highlighted fields";
			}
		}
		else if(c === "#shippingBlk") {
			flag = formShippingHandler();
			if(!flag) {
				error = "Please complete the highlighted fields";
			}
		}


		if(flag) {
			$(".itemBlk").hide();
			$n.show();
			$(n + "Tag").addClass("active");
		} else {
			serverResponse([error], "red");
		}
		e.preventDefault();
	});

	$(".backBtn").on("click", function(e) {
		var $this = $(this);
		var n = $this.data("next");
		var c = $this.data("curr");
		var $n = $(n);
		if($n.length == 1) {
			$(".itemBlk").hide();
			$n.show();
			$(c + "Tag").removeClass("active");
		}
		e.preventDefault();
	});



	$itemForm.on("submit", function(e) {
		var $btn = $itemForm.find("button[type=submit]");
		var a = formCategoryHandler();
		var b = formImageHandler();
		var c = formBasicHandler();
		var d = formShippingHandler();
		var f = formSubscriptionHandler();
		

		if(a && b && c && d && f) {
			$btn.attr("disabled", true);
			var t = $btn.find(".loader").length == 0 ? true : false;
			if(t) {
				$btn.prepend("<span class=\"loader\"><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;</span>");	
			}
			$(this).submit();
		} else {
			$btn.find(".loader").empty();
			$btn.attr("disabled", false);
			serverResponse(["Invalid input! Please make sure you enter correct valie"], "red");
		}
		e.preventDefault();
	});



/***********************************************************/
/************************** BLK HANDLER    *****************/
/***********************************************************/


	function formCategoryHandler() {
		var $c = $itemForm.find("input[name=_category]");
		var c = $c.val() | 0;
		if(c != 0) {
			return true;	
		}
		return false;
	}

	function formImageHandler() {
		var $i = $itemForm.find("input[name=_image]");
		var $a = $("#imagePreview");
		var v = $i.val();
		if(v.length != 0) {
			$a.css("background", "#edefed");
			return true;
		} 
		$a.css("background", "#f5d3d3");
		return false;

	}


	function formBasicHandler() {

		var $title = $itemForm.find("input[name=_title]");
		var $price = $itemForm.find("input[name=_price]");
		var $description = $itemForm.find("textarea[name=_description]");
	
		var tFlag = $title.val().length > 0 ? true : false;
		var pFlag = $price.val() > 0 ? true : false;
		var dFlag = $description.val().length > 0 ? true : false;
		toggleErrorBox($title, tFlag);
		toggleErrorBox($price, pFlag);
		toggleErrorBox($description, dFlag);

		if(tFlag && pFlag && dFlag) {
			return true;
		}
		return false;
	}


	function formShippingHandler() {
		var $_wei = $itemForm.find("input[name=_weight]");
		var $_mun = $itemForm.find("select[name=_mUnit]");
		var $_wid = $itemForm.find("input[name=_width]");
		var $_hei = $itemForm.find("input[name=_height]");
		var $_len = $itemForm.find("input[name=_length]");
		var $_dun = $itemForm.find("select[name=_dUnit]");
		var $_type = $itemForm.find("input[name=_shipType]:checked");
		var $_cost = $itemForm.find("input[name=_shipping]");


		var fwei = $_wei.val() > 0 ? true : false;
		var fwid = $_wid.val() > 0 ? true : false;
		var fhei = $_hei.val() > 0 ? true : false;
		var flen = $_len.val() > 0 ? true : false;
		
		var fmun = ($_mun.val() !== "0") ? true : false;
		var fdun = ($_dun.val() !== "0") ? true : false;
		
		var _type = $_type.val();
		var _cost = $_cost.val();
		var fcost = false;
		
		if(_type === "free" || _type === "auto") {
			fcost = true;
		} else {
			fcost = _cost > 0 ? true : false;
		}


		toggleErrorBox($_wei, fwei);
		toggleErrorBox($_wid, fwid);
		toggleErrorBox($_hei, fhei);
		toggleErrorBox($_len, flen);
		toggleErrorBox($_mun, fmun);
		toggleErrorBox($_dun, fdun);
		toggleErrorBox($_cost, fcost);		

		return fwei && fwid && fhei && flen && fmun && fdun && fcost;
	}


	function formSubscriptionHandler() {
		var $_save 		= $itemForm.find("input[name=_saveRate]");
		var $_shipment 	= $itemForm.find("select[name=_shipment]");
		var $_freq 		= $itemForm.find("select[name=_freq]");
	
		
		
		var fsave = $_save.val() > 0 ? true : false;
		var fship = $_shipment.val() >= 2 ? true : false;
		var ffreq = $_freq.val() >= 7 ? true : false;
		
		
		toggleErrorBox($_save, fsave);
		toggleErrorBox($_shipment, fship);
		toggleErrorBox($_freq, ffreq);
		
		return fsave && fship && ffreq;

	}

/***********************************************************/
/***********************************************************/


	function toggleErrorBox($a, a) {
		if(a) {
			$a.removeClass("error");
		} else {
			$a.addClass("error");
		}
	}


	function categoryHandler() {
		var $sub = $(".subCat");
		$sub.on("click", function(e) {
			$sub.removeClass("active");
			$(this).addClass("active");
			var v = $(this).data("value") | 0;
			if(v > 0) {
				var $c = $itemForm.find("input[name=_category]");
				$c.val(v);
			}
			e.preventDefault();
		});
	}; categoryHandler();



	function showSubCat() {
		var $main = $(".mainCat");
		var $sub = $(".subCatSelect");
		$main.on("click", function(e) {
			$main.removeClass("plainBtn active");
			$sub.hide();
			var $t = $($(this).attr("href"));
			$(this).addClass("plainBtn active");
			$t.show();
			var $c = $itemForm.find("input[name=_category]");
			$c.val(0);
			e.preventDefault();
		});
	}; showSubCat();



	/************* Calculate subscription price ************/

	$itemForm.find("input[name=_price]").keyup(function(){
		var a = $(this).val();
		if(a > 0) {
			$("#calPrice").html("$" +  a);
			$("#sopts").data("price", a);
			calSubscriptionPrice();
		}
	});
	function calSubscriptionPrice() {
		var $a = $("#sopts");
		var p = $a.data("price");

		var str = "<li><span class=\"toRight\">Subscription Price</span><span>Save Rate</span></li>"
		var i = 0;
		while(i <= 50) {
			var s = p * (100 - i) / 100;
			str += "<li><span class=\"toRight\">$"+s+"</span><span>" + i + "%</span></li>"
			i += 5;
		}

		$a.html(str);
	}; calSubscriptionPrice();


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* 
	
	$("#createListingForm").on("submit", function(event) {
		var $this = $(this);
		var $a = $this.find("#basicBlk");
		var $b = $this.find("#parcelBlk");
		var $c = $this.find("#subscriptionBlk");
		var $btn = $this.find("button[type=submit]");
		
		var a = basicHandler($a);
		var b = parcelHandler($b);
		var c = subscriptionHandler($c);
		
		if(a && b && c) {
			$btn.prop("disabled", true);
			if($btn.find(".loader").length == 0) {
				$btn.prepend("<span class=\"loader\"><i class=\"fa fa-spinner fa-spin\"></i>&nbsp;&nbsp;</span>");
			}
			$this.submit();
		} else {
			$btn.find(".loader").empty();
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
		var $_shipment = $a.find("select[name=_shipment]");
		var $_freq = $a.find("select[name=_freq]");

		var _save = $_save.val();
		var _shipment = $_shipment.val();
		var _freq = $_freq.val();
		
		
		var fsave = ((parseFloat(_save) | 0) >= 0 && $.isNumeric(_save) ) ? true : false;
		var fship = (parseFloat(_shipment) | 0) >= 2 && $.isNumeric(_shipment) ? true : false;
		var ffreq = (parseFloat(_freq) | 0) > 3 && $.isNumeric(_freq) ? true : false;
		
		
		toggleError($_save, fsave);
		toggleError($_shipment, fship);
		toggleError($_freq, ffreq);
		
		 return fsave && fship && ffreq;
	}
	
	function parcelHandler($a) {
		var $_wei = $a.find("input[name=_weight]");
		var $_mun = $a.find("select[name=_mUnit]");
		var $_wid = $a.find("input[name=_width]");
		var $_hei = $a.find("input[name=_height]");
		var $_len = $a.find("input[name=_length]");
		var $_dun = $a.find("select[name=_dUnit]");
		var $_type = $a.find("input[name=_shipType]:checked");
		var $_cost = $a.find("input[name=_shipping]");
		
		var fwei = (parseFloat($_wei.val()) | 0) > 0 ? $.isNumeric($_wei.val()) : false;
		var fwid = (parseFloat($_wid.val()) | 0) > 0 ? $.isNumeric($_wid.val()) : false;
		var fhei = (parseFloat($_hei.val()) | 0) > 0 ? $.isNumeric($_hei.val()) : false;
		var flen = (parseFloat($_len.val()) | 0) > 0 ? $.isNumeric($_len.val()) : false;
		
		var fmun = ($_mun.val() !== "0") ? true : false;
		var fdun = ($_dun.val() !== "0") ? true : false;
		
		var _type = $_type.val();
		var _cost = $_cost.val();
		var fcost = false;
		
		if(_type === "free" || _type === "auto") {
			fcost = true;
		} else {
			fcost = (parseFloat(_cost) | 0) >= 0 && $.isNumeric(_cost) ? true : false;
		}

		toggleError($_wei, fwei);
		toggleError($_wid, fwid);
		toggleError($_hei, fhei);
		toggleError($_len, flen);
		toggleError($_mun, fmun);
		toggleError($_dun, fdun);
		toggleError($_cost, fcost);		
		
		return fwei && fwid && fhei && flen && fmun && fdun && fcost;
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
		$("#createListingForm input[type=file]").on("change", function(event) {
			var $preview_box = $($(this).parent().siblings(".preview"));
			handlerImageReview(event, $preview_box);
		});
	});



});
</script>


<!-- 
<script type="text/javascript">

$(document).ready(function() {


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

	
	
	
	
	;function displayOverviewData() {
		var $a = $("#overview");
		var s = $a.data("status");
		if(typeof s == 'undefined') {
			s = "active";
		}
		assignStatus(s.toLowerCase());
		function assignStatus(i) {
			var $a =  $("#" + i + "Tab");
			if($a.length == 1) {
				$a.addClass("active");
			} else {
				$("#allTab").addClass("active");
			}
		}
	};	displayOverviewData();
	 
	 

	;function overviewListing() {
		var $a = $("#overviewListing");
		var n = $a.data("num");
		var s = $a.data("status");
		var x = "listing";
		if(n > 2) {
			x += "s";
		}
		$a.html("You have <strong>" + n + " " + s + "</strong> " + x);
	}
	
	function init() {
		$("#dbListingNav").addClass("active");
		$("#commandPrimary").find("li").eq(0).addClass("active");
		overviewListing();
	}; init(); 
});


</script>
 -->
</body>
</html>