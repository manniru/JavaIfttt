function sendMessage() {
	var reciever = document.getElementById("user_message_recieve").value;
	var message = document.getElementById("user_message_content").value;
	var url = "servlet/SendMessage";
	alert(reciever + "," +message);
	msgRequest.open("POST", url, true);
	msgRequest.onreadystatechange = updateMsg;
	msgRequest.setRequestHeader("Content-Type",
	                           "application/x-www-form-urlencoded");
	msgRequest.send("reciever=" + escape(reciever) +
	               "&message=" + escape(message));
}

function updateMsg(){
	if (msgRequest.readyState == 4) {
		if (msgRequest.status == 200) {
		    var xmlDoc = msgRequest.responseXML;
		    alert(xmlDoc);
		    var msg = xmlDoc.getElementsByTagName("msg")[0];
		    var reciever = msg.firstChild.firstChild.nodeValue;
		    var content = msg.lastChild.firstChild.nodeValue;
		    alert(reciever +","+ content);
		}
	}
	else {
		var message = request.getResponseHeader("Status");
	    if ((message == null) || (message.length <= 0)) {
	    	alert("Error! Request status is " + request.status);
	    } 
	    else {
	        alert(message);
	    }
	}
}