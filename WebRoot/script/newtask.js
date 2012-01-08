function addOnClickHandlers() {
	addThisOnClickHandlers();
	addThatOnClickHandlers();
}

function addThisOnClickHandlers() {
	var thisDiv = document.getElementById("this_logo");
	var events = thisDiv.getElementsByTagName("img");
	events[0].onclick = addTimeEventToRight;
	events[1].onclick = addWeiboEventToRight;
	events[2].onclick = addGmailEventToRight;
	var thisTypeEl = document.getElementById("this_type");
	thisTypeEl.value = "ops";
}

function addThatOnClickHandlers() {
	var thatDiv = document.getElementById("that_logo");
	var actions = thatDiv.getElementsByTagName("img");
	actions[0].onclick = addWeiboActionToRight;
	actions[1].onclick = addGmailActionToRight;
	var thatTypeEl = document.getElementById("that_type");
	thatTypeEl.value = "ops";
}

function addTimeEventToRight() {
	var imgElement = this;
	var thisTypeEl = document.getElementById("this_type");
	var timeEventLogo = document.getElementById("right_this_time_logo");
	var timeEventForm = document.getElementById("time_event_form");
	var ajaxThisForm = document.getElementById("ajax_this_form");
	if (thisTypeEl.value === "gmail-receive") {
		var gmailEventLogo = document.getElementById("right_this_gmail_logo");
		var gmailEventForm = document.getElementById("gmail_event_form");
		gmailEventLogo.style.display = 'none';
		gmailEventForm.style.display = 'none';
	}
	else if (thisTypeEl.value === "weibo-update") {
		var weiboEventLogo = document.getElementById("right_this_weibo_logo");
		var weiboEventForm = document.getElementById("weibo_event_form");
		weiboEventLogo.style.display = 'none';
		weiboEventForm.style.display = 'none';
	}
	timeEventLogo.style.display = 'inline';
	timeEventForm.style.display = 'inline';
	thisTypeEl.value = "time-after";
	//ajaxThisForm.submit();

}

function addWeiboEventToRight() {
	var imgElement = this;
	var thisTypeEl = document.getElementById("this_type");
	var weiboEventLogo = document.getElementById("right_this_weibo_logo");
	var weiboEventForm = document.getElementById("weibo_event_form");
	var ajaxThisForm = document.getElementById("ajax_this_form");
	//alert(thisTypeEl.value);
	if(thisTypeEl.value === "time-after") {
		//alert("thistype == " + thisTypeEl.value );
		var timeEventLogo = document.getElementById("right_this_time_logo");
		var timeEventForm = document.getElementById("time_event_form");
		timeEventLogo.style.display = 'none';
		timeEventForm.style.display = 'none';
	}
	else if(thisTypeEl.value === "gmail-receive") {
		var gmailEventLogo = document.getElementById("right_this_gmail_logo");
		var gmailEventForm = document.getElementById("gmail_event_form");
		gmailEventLogo.style.display = 'none';
		gmailEventForm.style.display = 'none';
	}
	weiboEventLogo.style.display = 'inline';
	weiboEventForm.style.display = 'inline';
	thisTypeEl.value = "weibo-update";
	//ajaxThisForm.submit();
}

function addGmailEventToRight() {
	var imgElement = this;
	var thisTypeEl = document.getElementById("this_type");
	var gmailEventLogo = document.getElementById("right_this_gmail_logo");
	var gmailEventForm = document.getElementById("gmail_event_form");
	var ajaxThisForm = document.getElementById("ajax_this_form");
	//alert(thisTypeEl.value);
	if(thisTypeEl.value === "time-after") {
		var timeEventLogo = document.getElementById("right_this_time_logo");
		var timeEventForm = document.getElementById("time_event_form");
		timeEventLogo.style.display = 'none';
		timeEventForm.style.display = 'none';
	}
	else if (thisTypeEl.value === "weibo-update") {
		var weiboEventLogo = document.getElementById("right_this_weibo_logo");
		var weiboEventForm = document.getElementById("weibo_event_form");
		weiboEventLogo.style.display = 'none';
		weiboEventForm.style.display = 'none';
	}
	gmailEventLogo.style.display = 'inline';
	gmailEventForm.style.display = 'inline';
	thisTypeEl.value = "gmail-receive";
	//ajaxThisForm.submit();
}


function addWeiboActionToRight() {
	var imgElement = this;
	var thatTypeEl = document.getElementById("that_type");
	var weiboActionLogo = document.getElementById("right_that_weibo_logo");
	var weiboActionForm = document.getElementById("weibo_action_form");
	//alert(thatTypeEl.value);
	if(thatTypeEl.value === "gmail-send") {
		var gmailActionLogo = document.getElementById("right_that_gmail_logo");
		var gmailActionForm = document.getElementById("gmail_action_form");
		gmailActionLogo.style.display = 'none';
		gmailActionForm.style.display = 'none';
	}
	weiboActionLogo.style.display = 'inline';
	weiboActionForm.style.display = 'inline';
	thatTypeEl.value = "weibo-update";
}

function addGmailActionToRight() {
	var imgElement = this;
	var thatTypeEl = document.getElementById("that_type");
	var gmailActionLogo = document.getElementById("right_that_gmail_logo");
	var gmailActionForm = document.getElementById("gmail_action_form");
	//alert(thatTypeEl.value);
	if(thatTypeEl.value === "weibo-update") {
		var weiboActionLogo = document.getElementById("right_that_weibo_logo");
		var weiboActionForm = document.getElementById("weibo_action_form");
		weiboActionLogo.style.display = 'none';
		weiboActionForm.style.display = 'none';
	}
	gmailActionLogo.style.display = 'inline';
	gmailActionForm.style.display = 'inline';
	thatTypeEl.value = "gmail-send";
}

function addToRight() {
	var imgElement = this;
	var thisTypeEl = document.getElementById("this_type");
	var thatTypeEl = document.getElementById("that_type");
	var rightWeiboEl = document.getElementById("right_weibo_logo");
	//var thisForm = document.getElementById("ajax_this_form");
	var timeEventForm = document.getElementById("time_event_form");
	var weiboEventForm = document.getElementById("weibo_event_form");

	rightWeiboEl.style.display = 'inline';
	//timeEventForm.style.display = 'inline';
	
	weiboEventForm.style.display = 'inline';
	thisTypeEl.value = 'time-after';
	thatTypeEl.value = 'weibo-update';
}