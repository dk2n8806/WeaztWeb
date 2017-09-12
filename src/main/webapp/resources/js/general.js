
;function serverResponse(msgs, color, min){
	var response = "";

	if(!$.isNumeric(min) || (min < 2500)) {
		min = 3500;
	}
	for(var i = 0; i < msgs.length; i++) {
		response += "<p>" + msgs[i] + "</p>";
	}
	$("body").append("<div class=\"server-response\"><div class=\"server-content\">"+response+"</div></div>");
	$($("body").find(".server-content")).addClass(color);
	setTimeout(function() {
		$(".server-response").hide();
	}, min);
};


function buildProductLink () {
	$(".productLink").each(function() {
		var $this = $(this);
		var data = $this.data("title");
		var url = $this.attr("href") + "?" + data.replace(/ /g,"-");
		$this.attr("href", url);
	});
};
