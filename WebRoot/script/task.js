function deleteTask(id, authCode) {
	//var authCode = document.getElementById("authcode").value;
	var opnums = "delete";
	//alert(id);
	var taskid = document.getElementById("taskid" + id).value;
	//alert(authCode + " : "  + taskid);
	var url = "servlet/TaskOps";
	deleteTaskRequest.open("POST", url, false); 
	//deleteTaskRequest.onreadystatechange = updateTasks;
	deleteTaskRequest.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	deleteTaskRequest.send("taskid=" + escape(taskid) + 
						"&ops=" + escape(opnums) + 
						"&authcode=" + escape(authCode));
	
	var task = document.getElementById("Task" + id);
	task.style.display = 'none';
	location.href = "./usermain.jsp?authcode=" + authCode; 
}

function stopTask(id, authCode) {
	
	//var authCode = document.getElementById("authcode").value;
	var opnums = "stop";
	//alert(id);
	var taskid = document.getElementById("taskid" + id).value;
	//alert(authCode + " : "  + taskid);
	var url = "servlet/TaskOps";
	stopTaskRequest.open("POST", url, false); 
	//deleteTaskRequest.onreadystatechange = updateTasks;
	stopTaskRequest.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	stopTaskRequest.send("taskid=" + escape(taskid) + 
						"&ops=" + escape(opnums) + 
						"&authcode=" + escape(authCode));
	
	var task = document.getElementById("Task" + id);
	var task_state = document.getElementById("task_state"+id);
	task_state.firstChild.nodeValue = "UNACTIVE : STOP";
	//task.style.display = 'none';
	//location.href = "./usermain.jsp?authcode=" + authCode; 
}

function runTask(id, authCode) {
	
	//var authCode = document.getElementById("authcode").value;
	var opnums = "run";
	//alert(id);
	var taskid = document.getElementById("taskid" + id).value;
	//alert(authCode + " : "  + taskid);
	var url = "servlet/TaskOps";
	runTaskRequest.open("POST", url, false); 
	//deleteTaskRequest.onreadystatechange = updateTasks;
	runTaskRequest.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	runTaskRequest.send("taskid=" + escape(taskid) + 
						"&ops=" + escape(opnums) + 
						"&authcode=" + escape(authCode));
	
	var task = document.getElementById("Task" + id);
	var task_state = document.getElementById("task_state"+id);
	task_state.firstChild.nodeValue = "ACTIVE : RUN";
	//task.style.display = 'none';
	//location.href = "./usermain.jsp?authcode=" + authCode; 
}