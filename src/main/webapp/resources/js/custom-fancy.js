

$(document).ready(function() {
	"use strict";
	$(".fancy-btn").fancybox({
		padding: 0,
		fitToView: false,
    	autoWidth: true, 
	    maxWidth: "90%", 
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

});