
<c:set var="username"><sec:authentication property="principal.username"/></c:set>
<c:set var="email"><sec:authentication property="principal.email"/></c:set>
<c:set var="userImage"><sec:authentication property="principal.avatar.imagePath.path"/></c:set>
	
<div class="blk-b">
	<div class="panel-header">
		 <h3><span>Public Info</span></h3>
	</div>
	<div class="blk-content">
		<div class="balance-list">
		<ul>
			<li>
				<div class="toLeft" style="width: 235px; height: 235px; margin-right: 50px;">
					<div class="user-profile-holder">
						<div class="user-profile-ava">
							<img src="${userImage }" />
						</div>
					</div>
				</div>
				<p>Your username, email, and avatar's portrait are publicly in Weazt, we uses it for communication purposes.</p>
				<br/>
				<br/>
				<p><span class="tgray">Username:</span>&nbsp;<strong id="username">${username }</strong></p>
				<p><span class="tgray">Email:</span>&nbsp;<strong id="email">${email }</strong></p>
				<br/>
				<br/>
				<br/>
				<a class="btn fancy-btn plainBtn" href="#updateAccountForm"><i class="fa fa-pencil"></i>&nbsp;&nbsp;Update account</a>
				<a class="fancy-btn btn plainBtn" href="#uploadProfileFormFancy"><i class="fa fa-camera"></i>&nbsp;&nbsp;Change avatar</a>
			</li>
		</ul>
		</div>
	</div>
</div>


<div id="updateAccountForm" style="display: none">
	<spring:url value="/user/update-account" var="updateAccount" htmlEscape="true"></spring:url>
	<form id="accountUpdateForm" action="${updateAccount }" method="POST">
	<div class="modal-header">
		<h3>Update username and email</h3>
		<p>Enter new username or email address.</p>
	</div>
	<div class="responseBlk">
	</div>
	<div class="modal-content">
		<table>
			<tr>
				<td>
					<label><strong>Username</strong></label>
					<input type="text" name="_username" value="${username }"/>
				</td>
			</tr>
			<tr>
				<td>
					<label><strong>Email</strong></label>
					<input type="text" name="_email" value="${email }"/>
				</td>
			</tr>
		</table>
	</div>
	<div class="modal-footer">
		<button type="submit" class="plainBtn btn"><strong>Update</strong></button>
	</div>
	</form>
</div>



<div id="uploadProfileFormFancy" style="display: none;">
	<spring:url value="/user/update-profile-image" var="uploadProfileForm" htmlEscape="true"></spring:url>
	<form id="uploadProfileForm" action="${uploadProfileForm}" method="POST" enctype="multipart/form-data">
	<div class="modal-header">
		<h3>Update Your Avatar</h3>
		<p>Let upload the best photo of yourself!</p>
	</div>
	<div class="modal-content toCenter">
		<div class="upload-file" style="display: inline-block; position: relative; width: 250px; height: 250px;">	
			<div class="trigger">
				<label for="img">
					<i class="fa fa-photo"></i>
					<br/>
				</label>
				<input  id="img" name="_image" type="file"/>
			</div>
			<div class="preview">
				<div class="sec-red"></div>
			</div>
		</div> 
		<br/><br/>
		<button type="submit" class="btn plainBtn"><strong>Upload photo</strong></button>
	</div>
	</form>
</div>


<script type="text/javascript">
$(document).ready(function() {
	
	var uRegex = new RegExp("^[a-zA-Z0-9]+$");
	var emailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	
	
	$("#accountUpdateForm").on("submit", function(event) {
		var $this = $(this);
		var $u = $this.find("input[name=_username]");
		var $e = $this.find("input[name=_email]");
		var $res = $this.find(".responseBlk");
		$res.empty();
		

		var username = $u.val().trim();
		var email = $e.val().trim();
		
		var uFlag =  (username.length >= 4) && uRegex.test(username) ? true : false;//strHandler($username) && minLengthHandler($username, 4);
		var eFlag =  emailRegex.test(email); //strHandler($email) && validateEmail($email);

		
		toggleError($u, uFlag);
		toggleError($e, eFlag);
		if(uFlag && eFlag) {
			var serializedData = $(this).serialize();
			var urlForm = $(this).attr("action");
			$.ajax({
 				url:urlForm,
 				data: serializedData,
 				method: "POST",
 				success: function(data){
 					if(data.state) {
 						$("#username").html($u.val());
 						$("#email").html($e.val());
 						$.fancybox.close();
 					} else {
 						errResponse($res, "Unable to update your account info");
 					}
 				}, 
 				error: function(data) {
 					errResponse($res, "Internale error - please try again or contact us to resolve the issue.");
 				}
 			});	  
		}
		event.preventDefault();
	});
	

	function validateEmail($email) { 
		var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return reg.test($email.val());
	};

	
	function errResponse($a, a) {
		var str = "";
		str = "<div class=\"blk-red blk-content\">"+ a +"</div>";
		$a.append(str);
	}
	
	
	function toggleError($a, a) {
		if(a) {
			$a.removeClass("error");
		} else {
			$a.addClass("error");
		}
	}
	
	
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
		$("input[type=file]").on("change", function(event) {
			var $preview_box = $($(this).parent().siblings(".preview"));
			handlerImageReview(event, $preview_box);
		});
	});
});
</script>