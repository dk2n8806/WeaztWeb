
<div id="headerBlk" class="prefix-clear">
	<div id="header-container" class="prefix-clear">
		<div id="headerNav">
			<ul>
			<c:choose>
				<c:when test="${not empty pageContext.request.userPrincipal}">
					<li>
						<c:set var="prinName"><sec:authentication property="principal.username"/></c:set>	
						<c:set var="prinAvaImg"><sec:authentication property="principal.avatar.imagePath.path"/></c:set>
						<spring:url value="/user/dashboard" var="dashboardLink" htmlEscape="true" />
																	
						<a id="avaIconHolder" href="${dashboardLink }">
							<i class="fa fa-user"></i>
							<span id="avaIcon"><img src="${prinAvaImg }"/></span>
						</a>
					</li>
				</c:when>
				<c:otherwise>	
					<li>
						<spring:url value="/login" var="loginLink" htmlEscape="true"/>
						<a class="acc-btn" href="${loginLink}">Login</a>
					</li>
					<li>
						<spring:url value="/register" var="registerLink" htmlEscape="true"/>
						<a class="acc-btn" href="${registerLink}">Register</a>
					</li>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
		
		<h1 id="hlogo">
			<spring:url value="/" var="baseLink" htmlEscape="true"/>
			<a href="${baseLink}">Weazt</a>
		</h1>
		

		
		<ul id="hdiscoverHolder">			
			<li id="hdiscoverMenu">
				<a id="hdiscover" href="#"><i class="fa fa-compass tgreen1"></i>&nbsp;&nbsp;<strong>Discover</strong></a>
				<div id="disWrapper">
				<div id="disDisplay">
						<div id="disDropdown">
							<ul>
								<li>
									<spring:url value="/shop/new-subscriptions" var="viewNewLink" htmlEscape="true"></spring:url>
									<h4><a href="${viewNewLink }">New subscriptions</a></h4>
								</li>
								
			 					<spring:url value="/category/coffee" 		var="coffeeLink" htmlEscape="true"></spring:url>
								<spring:url value="/category/tea" 			var="teaLink" htmlEscape="true"></spring:url>
								<spring:url value="/category/protein-drinks" 		var="proteinLink"  htmlEscape="true"/>
								<spring:url value="/category/sport+energy" 	var="sportDrinkLink"  htmlEscape="true"/>
								<spring:url value="/category/juices" 	var="juiceCatLink"  htmlEscape="true"/>
								<li><h4><a href="${coffeeLink}">Coffee</a></h4></li>
								<li><h4><a href="${juiceCatLink}">Juices</a></h4></li>
								<li><h4><a href="${teaLink}">Tea</a></h4></li>
								<li><h4><a href="${proteinLink}">Protein&nbsp;Drinks</a></h4></li>
								<li><h4><a href="${sportDrinkLink}">Sport&nbsp;&&nbsp;Energy</a></h4></li>
							</ul>
						</div>
					</div>
				</div>
			</li>
			
			
			
			
			
			
			
			
			
			
			<li id="hsearch">			
				<spring:url value="/search" var="searchForm" htmlEscape="true"></spring:url>
				<form id="header-search" action="${searchForm}" method="GET">
					<input type="text" name="ref" value="${searchString}"/>
					<c:if test="${not empty searchPage}">
						<input type="hidden" name="page" value="${searchPage}"/>
					</c:if>
					<button><i class="fa fa-search"></i></button>
				</form>
			</li>
		</ul>
	</div>
</div>






<div class="headerCatNav">
	<div>
		<ul >
			<li class="">
				<spring:url value="/shop/new-subscriptions" var="viewNewLink" htmlEscape="true"></spring:url>
				<a href="${viewNewLink }">New Subscriptions</a>
			</li>
			<li class="">
				<spring:url value="/user/recent-views" var="browingHistoryLink" htmlEscape="true"></spring:url>
				<a href="${browingHistoryLink}">Browsing history</a>
			</li>
			<li class="">
				<spring:url value="/user/subscriptions" var="subscriptionsLink" htmlEscape="true"></spring:url>
				<a href="${subscriptionsLink }">My subscriptions</a>
			</li>
		</ul>
	</div>
</div>







