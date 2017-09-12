<spring:url value="/about" var="aboutLink" htmlEscape="true"></spring:url>
<spring:url value="/help" var="helpLink" htmlEscape="true"></spring:url>
<spring:url value="/privacy" var="privacyLink" htmlEscape="true"></spring:url>
<spring:url value="/tos" var="tosLink" htmlEscape="true"></spring:url>
<spring:url value="/partner" var="partnerLink" htmlEscape="true"></spring:url>
<spring:url value="/contact_us" var="contactLink" htmlEscape="true"></spring:url>
	

<spring:url value="/help/shop_on_weazt" var="shopOnWeaztLink" htmlEscape="true"></spring:url>
	

<div id="footer" class="footer">
	<div class="footer-note">
		<jsp:useBean id="now" class="java.util.Date" />
		<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
		<span class="toRight p10">&copy; ${year} Weazt</span>
		<ul class="inline">
			<li><a href="${aboutLink }">About Us</a></li>
			<%-- <li><a href="${shopOnWeaztLink}">Shop on Weazt</a></li> --%>
			<li><a href="${partnerLink }">Selling on Weazt</a></li>
			<li><a href="${helpLink }">Help & Support</a></li>
			<li><a href="${tosLink }">Terms of Use</a></li>
			<li><a href="${privacyLink }">Privacy Policy</a></li>
			<li><a href="${contactLink }">Contact Us</a></li>
		</ul>
	</div>
</div>
