<c:set var="userImage"><sec:authentication property="principal.imagePath"/></c:set>
				
<div class="blk-b">
	<div class="panel-header">
		<h3><span class="bhi"><i class="fa fa-user"></i></span>Profile photo</h3>
	</div>
	<div class="blk-content">
		<br/>
		<div class="col-wrapper">
			<div class="col25">
				<div class="user-profile-holder">
					<div class="user-profile-ava">
						<img src="${userImage }" />
					</div>
				</div>
			</div>
			<div class="col75 toCenter">
				<br/><br/><br/>
				<div><a class="fancy-btn btn" href="#uploadProfileForm"><strong><i class="fa fa-camera"></i>&nbsp;&nbsp;Change Profile photo</strong></a></div>
			</div>
		</div>
	</div>
</div>
