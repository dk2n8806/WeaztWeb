
<spring:url value="/about" 		var="aboutLink" 	htmlEscape="true"></spring:url>
<spring:url value="/help" 		var="helpLink" 		htmlEscape="true"></spring:url>
<spring:url value="/privacy" 	var="privacyLink" 	htmlEscape="true"></spring:url>
<spring:url value="/tos" 		var="tosLink" 		htmlEscape="true"></spring:url>
<spring:url value="/contact_us" 	var="contactLink" 	htmlEscape="true"></spring:url>
<div id="commandPrimary" class="command-menu">
<ul>
	<li><h4><a href="${aboutLink }">About Us</a></h4></li>
	<li><h4><a href="${helpLink }">Help Center</a></h4></li>
	<li><h4><a href="${privacyLink }">Privacy Policy</a></h4></li>
	<li><h4><a href="${tosLink }">Term of Service</a></h4></li>
	<li><h4><a href="${contactLink }">Contact Us</a></h4></li>
</ul>
</div>