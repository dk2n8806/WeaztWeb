<c:set value="${totalSubscriptions}" var="totalSubscriptions"/>

<c:set var="prinName"><sec:authentication property="principal.username"/></c:set>
<c:set var="prinId"><sec:authentication property="principal.id"/></c:set>
<c:set var="prinAvaImg"><sec:authentication property="principal.avatar.imagePath.path"/></c:set>
<c:set var="prinJoinedOn"><sec:authentication property="principal.createdOn"/></c:set>

<c:if test="${not empty merchant}">
	<c:set var="merchantCompany" value="${merchant.businessName }"/>
</c:if>
<div class="blk-b">
	<div class="user-profile-holder">
		<div class="user-profile-ava">
			<img src="${prinAvaImg }"/>
		</div>
		<br/>
		<div class="user-profile-info">
			<div><h1 class="ftrend"><strong>${prinName }</strong></h1></div>
			<h3 class="profile-merchant fnor tgray" data-company="${merchantCompany }">${merchantCompany }</h3>	
		</div>
	</div>
	<div class="blk-content">
		<div class="toCenter">
			<spring:url value="/user/setting/account" var="editAccountProfile" htmlEscape="true"/>
			<ul class="p10">
				<li><p><a class="link" href="${editAccountProfile }">Edit Profile</a></p></li>
			</ul>
		</div>
		<%-- <p class="tip" >Joined <span class="dateFormat" data-date="${prinJoinedOn}"></span>  </p>
		

		<c:if test="${not empty merchantContact}">
			<jsp:include page="merchantContact.jsp"></jsp:include>
		</c:if> --%>
	</div>
</div>
<!-- 
<script>
$("document").ready(function(){
	var $a = $(".profile-merchant");
	var b = $a.data("company");
	if(b !== "") {
		$a.append("@" + b);
	}
});
</script>
 -->
 
 