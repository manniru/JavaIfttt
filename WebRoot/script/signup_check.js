
function checkUsername() {
	var username = document.getElementById("user_login").value;
	//if (validateUsername(username) == false) {
		//return ;
	//}
	//alert(username);
	var url = "servlet/UsernameCheck?username=" + escape(username);
	//alert(url);
	getSendRequest(nameRequest, url, nameUpdatePage);
}

function nameUpdatePage() {
	if (nameRequest.readyState == 4) {
		if (nameRequest.status == 200) {
			/* Get the response from the server */
			var ok = nameRequest.responseText;
			if (ok[0] == "0") {
				var signupFieldsEl = document.getElementById("user_signup_fields");
				var signupUsernameEl = document.getElementById("user_signup_username");
				var signupUsernameErrorEl = document.getElementById("user_signup_username_error");
				var signupEmailEl = document.getElementById("user_signup_email");
				var username = document.getElementById("user_login_error");
				
				signupUsernameEl.style.display="none";
				//signupFieldsEl.removeChild(signupUsernameEl);
				signupFieldsEl.insertBefore(signupUsernameErrorEl, signupEmailEl);
				signupUsernameErrorEl.style.display="inline";
				username.value = ok.substring(1, ok.len);
				
			}
			
		} else
			alert("Error! Request status is " + request.status);
	}
	//nameRequest = createRequest();
}

function checkUsernameError() {
	var username = document.getElementById("user_login_error").value;
	//if (validateUsername(username) == false) {
		//return ;
	//}
	var url = "servlet/UsernameCheck?username=" + escape(username);
	getSendRequest(nameErrorRequest, url, nameErrorUpdatePage);
}

function nameErrorUpdatePage() {
	if (nameErrorRequest.readyState == 4) {
		if (nameErrorRequest.status == 200) {
			/* Get the response from the server */
			var ok = nameErrorRequest.responseText;
			if (ok[0] == "1") {
				//var signupFieldsEl = document.getElementById("user_signup_fields");
				
				var signupUsernameEl = document.getElementById("user_signup_username");
				var signupUsernameErrorEl = document.getElementById("user_signup_username_error");
				//var signupEmailEl = document.getElementById("user_signup_email");
				var username = document.getElementById("user_login");
				
				signupUsernameErrorEl.style.display="none";
				signupUsernameEl.style.display="inline";
				//signupFieldsEl.removeChild(signupUsernameErrorEl);
				//signupFieldsEl.insertBefore(signupUsernameEl, signupEmailEl);
				username.value = ok.substring(1, ok.len);
				
			}
			
		} else
			alert("Error! Request status is " + request.status);
	}
	//nameErrorRequest = new createRequest();
}
function checkEmail() {
	var email = document.getElementById("user_email").value;
//	if (validateEmail(email) == false) {
//		return ;
//	}
	var url = "servlet/EmailCheck?email=" + escape(email);
	getSendRequest(emailRequest, url, emailUpdatePage);
}
function emailUpdatePage() {
	if (emailRequest.readyState == 4) {
		if (emailRequest.status == 200) {
			/* Get the response from the server */
			var ok = emailRequest.responseText;
			if (ok[0] == "0") {
				var signupFieldsEl = document.getElementById("user_signup_fields");
				var signupEmailEl = document.getElementById("user_signup_email");
				var signupEmailErrorEl = document.getElementById("user_signup_email_error");
				var signupPasswordEl = document.getElementById("user_signup_password");
				var email = document.getElementById("user_email_error");
				
				signupEmailEl.style.display="none";
				//signupFieldsEl.removeChild(signupUsernameEl);
				signupFieldsEl.insertBefore(signupEmailErrorEl, signupPasswordEl);
				signupEmailErrorEl.style.display="inline";
				email.value = ok.substring(1, ok.len);
				
			}
			
		} else
			alert("Error! Request status is " + request.status);
	}
	//nameRequest = createRequest();
}

function checkEmailError() {
	var email = document.getElementById("user_email_error").value;
	//if (validateUsername(username) == false) {
		//return ;
	//}
	var url = "servlet/EmailCheck?email=" + escape(email);
	getSendRequest(emailErrorRequest, url, emailErrorUpdatePage);
}

function emailErrorUpdatePage() {
	if (emailErrorRequest.readyState == 4) {
		if (emailErrorRequest.status == 200) {
			/* Get the response from the server */
			var ok = emailErrorRequest.responseText;
			if (ok[0] == "1") {
				//var signupFieldsEl = document.getElementById("user_signup_fields");
				var signupEmailEl = document.getElementById("user_signup_email");
				var signupEmailErrorEl = document.getElementById("user_signup_email_error");
				var email = document.getElementById("user_email");				
				
								
				signupEmailErrorEl.style.display="none";
				signupEmailEl.style.display="inline";
				//signupFieldsEl.removeChild(signupUsernameErrorEl);
				//signupFieldsEl.insertBefore(signupUsernameEl, signupEmailEl);
				email.value = ok.substring(1, ok.len);			
			}
			
		} else
			alert("Error! Request status is " + request.status);
	}
	
}
function checkPassword() {
	var password = document.getElementById("user_password");
	if (validatePassword(password) == false) {
		return ;
	}
	var url = "" + escape(password);
	getSendRequest(passwordRequest, url, passwordUpdatePage);
}

function checkConfirmPassword() {
	var password = document.getElementById("user_password").value;
	var passwordConfirm = document.getElementById("user_password_confirmation").value;
	if (password != passwordConfirm) {
		var signupFieldsEl = document.getElementById("user_signup_fields");
		var signupPasswordConfirmEl = document.getElementById("user_signup_confirm_password");
		var signupPasswordConfirmErrorEl = document.getElementById("user_signup_confirm_password_error");
		var confirmPassword = document.getElementById("user_password_confirmation_error");
		var signupRuleEl = document.getElementById("rule_signup_up");
		
		signupPasswordConfirmEl.style.display="none";
		//signupFieldsEl.removeChild(signupUsernameEl);
		signupFieldsEl.insertBefore(signupPasswordConfirmErrorEl, signupRuleEl);
		signupPasswordConfirmErrorEl.style.display="inline";
		confirmPassword.value = passwordConfirm;
	}
}

function checkConfirmPasswordError() {
	var password = document.getElementById("user_password").value;
	var passwordConfirm = document.getElementById("user_password_confirmation").value;
	if (password == passwordConfirm) {
		var signupPasswordConfirmEl = document.getElementById("user_signup_confirm_password");
		var signupPasswordConfirmErrorEl = document.getElementById("user_signup_confirm_password_error");
		var confirmPassword = document.getElementById("user_password_confirmation");
		
		signupPasswordConfirmErrorEl.style.display="none";
		signupPasswordConfirmEl.style.display="inline";
		confirmPassword.value = passwordConfirm;
	}
}