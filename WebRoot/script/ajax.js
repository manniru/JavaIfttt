function createRequest() {
	try {
		var request = new XMLHttpRequest();
	} catch (trymicrosoft) {
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (othermicrosoft) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (failed) {
				request = null;
			}
		}	
	}
	if (request == null) {
		alert("Error creating request object!");
	}
	else{
		return request;
	}
}

function getSendRequest(request, url, backfunc) {
	request.onreadystatechange = backfunc;
	request.open("GET", url, true);
	request.send(null);
}