

$(document).ready(function() {

	$(".command-toggle").click(function(event) {
		var next = $(this).data("next");
		var $next = $("#" + next);
		if($next.length == 1) {
			$(".command-menu").hide();
			$next.show();
		} else {
			alert("someThing go worng");
		}
		event.preventDefault();
	});



	// Toggle favorite rating
	$("a.icon-btn, a.queueOrder-btn").click(function(event) {
		$(this).toggleClass("active");		
		event.preventDefault();
	});




	/*$("fieldset.rating").each(function() {
		var star = $(this).data("rating");
		$(this).empty().prepend(setStar(star));
	});

	function setStar(val) {
		var i = 10;
		var star = "";
		if(val == 0) {
			return;
		}
		while(i > 0) {
			if(i === val) {
				if((i % 2) === 0){
					star += "<input disabled type=\"radio\"><label></label>";
				} else {
					star += "<input disabled type=\"radio\"><label class=\"half\"></label>";				
				}
			} else {
				if((i % 2) === 0){
					star += "<input type=\"radio\"><label></label>";
				} else {
					star += "<input type=\"radio\"><label class=\"half\"></label>";				
				}
			}
			i--;
		}
		return star;
	};*/



	function resizeListing() {
		var $wrapper = $(".listing-wrapper");
		var $parent = $wrapper.parent();
		var w = $parent.width();
		var cons = 205;
		var p = Math.floor(w/250);

		$(".listing-wrapper").css({
			"margin" : "auto",
			"width" : Math.floor(w / 250) * 250
		});
	};
	resizeListing();
	$(window).resize(function() {
		resizeListing();
	});

});


/*
 * jQuery Shorten plugin 1.0.0
 *
 * Copyright (c) 2013 Viral Patel
 * http://viralpatel.net
 *
 * Dual licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 */
 
 (function($) {
    $.fn.shorten = function (settings) {
     
        var config = {
            showChars: 100,
            ellipsesText: "&nbsp;...",
            moreText: "&nbsp;Read more",
            lessText: "&nbsp;&nbsp;Show less"
        };
 
        if (settings) {
            $.extend(config, settings);
        }
         
        $(document).off("click", '.morelink');
         
        $(document).on({click: function () {
 
                var $this = $(this);
                if ($this.hasClass('less')) {
                    $this.removeClass('less');
                    $this.html(config.moreText);
                } else {
                    $this.addClass('less');
                    $this.html(config.lessText);
                }
                $this.parent().prev().toggle();
                $this.prev().toggle();
                return false;
            }
        }, '.morelink');
 
        return this.each(function () {
            var $this = $(this);
            if($this.hasClass("shortened")) return;
             
            $this.addClass("shortened");
            var content = $this.html();
            if (content.length > config.showChars) {
                var c = content.substr(0, config.showChars);
                var h = content.substr(config.showChars, content.length - config.showChars);
                var html = c + '<span class="moreellipses">' + config.ellipsesText + '</span><span class="morecontent"><span>' + h + '</span><a href="#" class="morelink">' + config.moreText + '</a></span>';
                $this.html(html);
                $(".morecontent span").hide();
            }
        });
         
    };
 
 })(jQuery);


 






/**************************************************************/
/******** Fix navigation **************************************/
//$(document).ready(function() {
//	"use strict";
	/*function fixTaskNav($_containerId) {
	    var div_top = $(_containerId).offset().top;
	   	var window_top = $(window).scrollTop();
	    onScroll(_containerId, window_top, div_top);
	  	$(window).bind('scroll', function() {
	   		window_top = $(window).scrollTop();
	        onScroll(_containerId, window_top, div_top);
		});
	};
	function onScroll(c, h1, h2) {
		var $con = $(c);
		if (h1 > h2) {
	    	$con.css({
	    		"position": "fixed",
	    		"top" : 0,
	    		"z-index": 5
	    		});
	    } else {
	    	$con.css("position", "relative")
	    		; 
	    }
	};

	//fixTaskNav($(".hoz-nav-wrapper"));	


	function resize() {
		var $_con = $(".hoz-nav-wrapper");
		var $_pa = $_con.parent();
		var w = $_pa.width();
		$_con.width(w);
	};*/
//	$(window).on("resize", resize);
//	resize();
//});






/*************************************************/
/********* Preview Image *************************/
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










/**************************************************/
/***** Handler ************************************/
function strHandler($txt) {
	var val = $txt.val().trim().length;
	if(val == 0) {
		return false;
	} else {
		return true;
	}
};
function numberHandler($box) {
	var val = $box.val();
	var regex = /^(?=.+)(?:[1-9]\d*|0)?(?:\.\d+)?$/;
	var flag = regex.test(val);
	if(flag) {
		return true;
	} else {
		return false;
	}
};

function numComHandler($c, v) {
	var val = parseFloat($c.val());
	if(val > v) {
		return true;
	}
	return false;
};
function selectHandler($sl) {
	var val = $sl.val();
	if(val == 0) {
		return false;
	} else {
		return true;
	}
};
function textareaHandler($tarea) {
	var val = $tarea.val().trim();
	if(val.length == 0) {
		return false;
	} else {
		return true;
	}
};

function minLengthHandler($c, len) {
	if($c.val().length < len) {
		return false;
	} else {
		return true;
	}
};

function toggleErrorBox($c, flag) {
	if(flag) {
		$c.removeClass("error");
	} else {
		$c.addClass("error");
	}
} 




function imageHandler($file) {
	var name = $file.val();
	//alert($file.siblings("label").length);
	if(name.length > 4) {
		$file.siblings("label").css("background", "#fff");
		return true;
	} else {
		$file.siblings("label").css("background", "#f5d3d3");
		return false;
	}
};

function initSelect($select) {
	var val = $select.data("selected");
	//alert(val);
	if(val === "")  {
		val="0";
	}
	/*var $option = $(select + " option[value=" + val + "]");
	$option.attr("selected", true);*/
	var $option = $select.find("option");
	$option.each(function() {
		var ref = $(this).val();
		if(val.toString() === ref.toString()) {
			$(this).attr("selected", true);
		}
	});
};







function validateEmail($email) { 
	var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return reg.test($email.val());
};








/********************************************************************/
/*********************************************************************/
/** AUTO FUNCTION */
$(function() {
	$("input[type=file]").on("change", function(event) {
		var $preview_box = $($(this).parent().siblings(".preview"));
		handlerImageReview(event, $preview_box);
	});
});





;function serverResponse(msgs, color){
	var response = "";
	for(var i = 0; i < msgs.length; i++) {
		response += "<p>" + msgs[i] + "</p>";
	}
	$("body").append("<div class=\"server-response\"><div class=\"server-content\">"+response+"</div></div>");
	$($("body").find(".server-content")).addClass(color);
	setTimeout(function() {
		$(".server-response").hide();
	}, 2500);
};

/*$(function() {
	function rescaleProductHolder() {
		var w = 250;
		var $a = $(".listing-wrapper");
		var $b = $(".listing-holder");

		var aW = $a.width();
		var bW = $b.width();
		
		var c = Math.floor(aW / w);
		var r = 0;
		if(c > 1){
			r = (aW - (c * w)) / c;
			w = w + r;
		} else {
			w = aW;
		}
		alert(aW + " " + w);
		$b.width(w);
	};
	rescaleProductHolder();
	$(window).on("resize", rescaleProductHolder);
});
*/






function validateCreditCardNumber($b) {
	var val = $b.val().trim();
	var visa 	 =  /^4[0-9]{12}(?:[0-9]{3})?$/;
	var master 	 =  /^5[1-5][0-9]{14}$/;
	var american = /^3[47][0-9]{13}$/;
	var discover = /^6(?:011|5[0-9]{2})[0-9]{12}$/;

	if(visa.test(val) || master.test(val) 
		|| american.test(val) || discover.test(val)) {
		$b.removeClass("error");
		return true;
	} else {
		$b.addClass("error");
		return false;
	}
};


function verifyCVV($b) {
	var val = $b.val().trim();
	var cvvRegex = /^[0-9]{3,4}$/;
	if(cvvRegex.test(val)) {
		$b.removeClass("error");
		return true;
	} else {
		$b.addClass("error");
		return false;
	}
};




























