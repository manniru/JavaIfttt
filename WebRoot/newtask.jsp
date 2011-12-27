<!DOCTYPE html> 
<html> 
  	<head> 
    	<meta charset='utf-8'> 
    	<meta http-equiv="X-UA-Compatible" content="chrome=1"> 
        	<title>Create a New Task - ifttt</title> 
 	
 		<!-- 
    	<meta content="authenticity_token" name="csrf-param" /> 
		<meta content="d+Yt2XaRDfbSj0m2ROzA87MN2rgj3i6wKoK2UCjq4U4=" name="csrf-token" /> 
 		-->
    	<link href="./stylesheet/login.css" media="screen" rel="stylesheet" type="text/css" /> 
    	
 
    	<script src="" type="text/javascript"></script>   
		<script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/prototype/1/prototype.js'></script>
		<script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/scriptaculous/1/scriptaculous.js'></script>
		<script type='text/javascript' src='./lightview/js/lightview.js'></script>
		<script type='text/javascript' src='./lightview/js/scriptaculous.js?load=effects'></script>
		<script type='text/javascript' src='./lightview/js/effects.js'></script>
		 <script type="text/javascript" src='./script/newtask.js'> </script>

		<link rel="stylesheet" type="text/css" href="./lightview/css/lightview.css" />

		<!--  a window
		<script language="javascript">
			function openWin(u, w, h) {
				var l = (screen.width - w) / 2;
				var t = (screen.height - h) / 2;
				var s = 'width=' + w + ', height=' + h + ', top=' + t + ', left=' + l;
				s += ', toolbar=no, scrollbars=no, menubar=no, location=no, resizable=no';
				open(u, 'oWin', s);
			}
	</script>
	-->
	<script language="JavaScript">
		function checklen() {
		}
	</script>
  	</head> 
 
 
  	<body class="logged_in  windows  env-production " onLoad="addOnClickHandlers();">            
      	<div id="header" class="true clearfix"> 
        	<div class="container" class="clearfix"> 
          		<a class="logo" href="./usermain"> 
            		<img height="46" width="96" alt="ifttt" src="./pic/logo.png">
          		</a>   
				<div class="topsearch"> 
					<ul class="top-nav"> 
          				<li class="explore"><a href="/explore">Explore</a></li> 
        				<li><a href="/help">Help</a></li> 
      				</ul> 
    			</div> 	
  					<div id="userbox"> 
    				<div id="user"> 
      					<a href="./admin">
      						<img height="20" src="" width="20" /></a> 
      					<a href="/mzd" class="name">mzd</a> 
    				</div> 
    				<ul id="user-links"> 
      					<li> 
        					<a href="/inbox/notifications" id="notifications" class="tooltipped downwards" title="Notifications"> 
          						<span class="icon">Notifications</span> 
        					</a> 
      					</li> 
      					<li><a href="/account" id="settings" class="tooltipped downwards" title="Account Settings"><span class="icon">Account Settings</span></a></li> 
      					<li><a href="/logout" id="logout" class="tooltipped downwards" title="Log Out"><span class="icon">Log Out</span></a></li> 
    				</ul> 
  				</div> <!-- /.userbox -->
        	</div> 
      	</div> <!-- header --> 

         
            
 
		<div class="site"><div class="container"><div class="pagehead"> 
  			<h1> 
    			Create a New Task
  			</h1> 
		</div> 

		<a href="./lightview/example/images/blue.jpg" class='lightview' rel='gallery[myset]'><img src="./lightview/example/images/blue_thumb.jpg" width="72" height="72"

		alt="" /></a>

		<a href="./lightview/example/images/yellow.jpg" class='lightview' rel='gallery[myset]'><img src="./lightview/example/images/yellow_thumb.jpg" width="72" height="72"

		alt="" /></a>

		<a href='http://www.baidu.com' rel='iframe' title='baidu :: 打开BAIDU :: width: 800, height: 600'
			class='lightview' >baidu</a>
		<br>


		<!--<a href='#ajaxform' class='lightview signup-button' title=' :: :: topclose: true, autosize: true' >Show Form</a>-->
		<!-- this part is not display when the window load -->
			<form id="ajax_this_form" style="display:none;">
　　　				<p>This form </p>
       			<div class="logos" id="this_logo">
					<img alt="time" height="65" src="./pic/time.jpg" width="65" type="submit">
    				<img alt="weibo" height="81" src="./pic/sina_weibo.png" width="172" type="submit">
    				<img alt="gmail" height="68" src="./pic/gmail_logo.jpg" width="155" type="submit">
					
				</div>
				<input value="post" id="ajaxSubmit" type="submit">
			</form>

			<form id="ajax_that_form" style="display:none;">
　　　			<p>That form </p>
				<div class="logos" id="that_logo">
    				<img alt="weibo" height="81" src="./pic/sina_weibo.png" width="172">
    				<img alt="gmail" height="68" src="./pic/gmail_logo.jpg" width="155">
				</div>
				<input value="post" id="ajaxSubmit" type="submit">
			</form>
			

			<!--  
			<div class="fieldgroup"> 
    							<div class="fields" id="user_signup_fields"> -->



		<!-- this part is not display when the window load -->
		
		<div class="columns accountcols"> 
			<form action="./CreateTask.jsp" method="post">
  				<div class="main"> 
					<div style="margin:0;padding:0;display:inline">
						<input name="authenticity_token" type="hidden" value="d+Yt2XaRDfbSj0m2ROzA87MN2rgj3i6wKoK2UCjq4U4=" />
					</div> 
					  	<%
  	 				System.out.println("NEW TASK AUTHCODE = " +  request.getParameter("authcode"));
  	 				 %>
					<div>
						<input name="authcode" type="text" value="<%=request.getParameter("authcode")%>" style="display:none">
					</div>
      				<dl class="form"> 
    					<dt><label error="false" for="task_name" style="width:39em">Task Name</label></dt> 
    					<dd><input error="false" id="task_name" name="task[name]" size="30" style="width:39em" type="text" /></dd></dl> 
 
      				<dl class="form"> 
        				<dt><label for="task_description">Description</label> <small style="font-weight:normal">(optional)</small></dt> 
        				<dd><input id="task_description" name="task[description]" size="30" style="width:39em" type="text" /></dd> 
      				</dl> 
      				<div class="signup-entice">
    					<a href="#ajax_this_form" class="lightview signup-button" title=' :: :: topclose: true, autosize: true'>
							<strong>this</strong> Choose a this <em>trigger!</em></a>

						<a href="#ajax_that_form" class="lightview signup-button" title=' :: :: topclose: true, autosize: true'>
							<strong>that</strong> <em>Action</em> that you want ! </a>
 					</div> 
 					
 					<div class="rule"></div>
 					
      				<p>Want a advanced task ?</p>
 
 
      				<p class="checkbox"> 
       	 				<label> 
            				<a href="/account/billing" class="action">Upgrade your plan to create advanced tasks!</a> 
       		 			</label> 
      				</p> 
 
 
      				<div class="form-actions"> 
        				<button type="submit" class="classy primary js-oneclick" data-afterclick="Creating Repository…"> 
          					<span>Create Task</span> 
        				</button> 
      				</div> 	
				</div> 
 
  				<div class="sidebar"> 
    				<div class="fieldgroup" id="this_fieldgroup"> 
      					<h2>this</h2> 
      					<div class="note">love me, please
							<input id="this_type" name="this[type]" value="ops" style="display:none">
							<input id="that_type" name="that[type]" value="ops" style="display:none">
							<div class="logos" id="right_this_weibo_logo" style="display:none">
    							<img alt="weibo" height="81" src="./pic/sina_weibo.png" width="172">
							</div>
							<div class="logos" id="right_this_gmail_logo" style="display:none">
    							<img alt="gmail" height="68" src="./pic/gmail_logo.jpg" width="155">
							</div>
							<div class="logos" id="right_this_time_logo" style="display:none">
    							<img alt="time" height="65" src="./pic/time.jpg" width="65" type="submit">
							</div>

							<!-- time event form -->
							<div id="time_event_form" style="display:none">
								<dl class="form"> 
        							<dt><label for="time_event_date">Time</label></dt> 
        							<dd><input id="time_event_date" name="time_event[date]" size="8" style="width:15em" type="text" /></dd> 
      							</dl>
							</div>

							<!-- weibo event form -->
							<div id="weibo_event_form" style="display:none">
								<dl class="form"> 
        							<dt><label for="weibo_event_name">Weibo Username</label></dt> 
        							<dd><input id="weibo_event_name" name="weibo_event[name]" size="8" style="width:15em" type="text" /></dd> 
      							</dl> 
								<dl class="form"> 
        							<dt><label for="weibo_event_keyword">Weibo Password</label></dt> 
        							<dd><input id="weibo_event_keyword" name="weibo_event[keyword]" size="5" style="width:15em" type="text" /></dd> 
      							</dl>
							</div>

							<!-- gmail event form  -->
							<div id="gmail_event_form" style="display:none">
								<dl class="form"> 
        							<dt><label for="gmail_event_name">Gmail Username</label></dt> 
        							<dd><input id="gmail_event_name" name="gmail_event[name]" size="8" style="width:15em" type="text" /></dd> 
      							</dl> 
								<dl class="form"> 
        							<dt><label for="gmail_event_password">Gmail Password</label></dt> 
        							<dd><input id="gmail_event_password" name="gmail_event[password]" size="5" style="width:15em" type="password" /></dd> 
      							</dl>
							</div>

						</div>
      				</div> <!-- this_fieldgroup -->
					<div class="rule"></div>
					<div id="that_fieldgroup" class="fieldgroup">
						<h2>that</h2> 
						<div class="node">Don't love him
							<div class="logos" id="right_that_weibo_logo" style="display:none">
    							<img alt="weibo" height="81" src="./pic/sina_weibo.png" width="172">
							</div>
							<div class="logos" id="right_that_gmail_logo" style="display:none">
    							<img alt="gmail" height="68" src="./pic/gmail_logo.jpg" width="155">
							</div>
							<!-- weibo event form -->
							<div id="weibo_action_form" style="display:none">
								<dl class="form"> 
        							<dt><label for="weibo_action_name">Weibo Username</label></dt> 
        							<dd><input id="weibo_action_name" name="weibo_action[name]" size="8" style="width:15em" type="text" /></dd> 
      							</dl> 
								<dl class="form"> 
        							<dt><label for="weibo_action_keyword">Weibo Password</label></dt> 
        							<dd><input id="weibo_action_keyword" name="weibo_action[keyword]" size="5" style="width:15em" type="password" /></dd> 
      							</dl>
      							<dl class="form"> 
        							<dt><label for="weibo_action_data">Weibo Content</label></dt> 
        							<dd><input id="weibo_action_data" name="weibo_action[data]" size="5" style="width:15em" type="text" /></dd> 
      							</dl>
							</div>

							<!-- gmail event form  -->
							<div id="gmail_action_form" style="display:none">
								<dl class="form"> 
        							<dt><label for="gmail_action_name">Gmail Username</label></dt> 
        							<dd><input id="gmail_action_name" name="gmail_action[name]" size="8" style="width:15em" type="text" /></dd> 
      							</dl> 
								<dl class="form"> 
        							<dt><label for="gmail_action_password">Gmail Password</label></dt> 
        							<dd><input id="gmail_action_password" name="gmail_action[password]" size="5" style="width:15em" type="password" /></dd> 
      							</dl>
								<dl class="form"> 
        							<dt><label for="gmail_action_receive">Receive</label></dt> 
        							<dd><input id="gmail_action_receive" name="gmail_action[receive]" size="5" style="width:15em" type="text" /></dd> 
      							</dl>
      							<dl class="form"> 
        							<dt><label for="gmail_action_data">GMail Content</label></dt> 
        							<dd><input id="gmail_action_data" name="gmail_action[data]" size="5" style="width:15em" type="text" /></dd> 
      							</dl>
							</div>
						</div> <!-- /.that note -->
					</div>
  				</div><!-- /.sidebar --> 
			</form>  
		</div><!-- /.columns.accountcols --></div></div> 

    
    	<!-- footer --> 
    	<div id="footer" > 
      		<div class="upper_footer"> 
     			<div class="container" class="clearfix"> 
       				<!--[if IE]><h4 id="blacktocat_ie">GitHub Links</h4><![endif]--> 
       				<![if !IE]><h4 id="blacktocat">ifttt Links</h4><![endif]> 
      					<ul class="footer_nav"> 
         					<h4>ifttt</h4> 
         					<li><a href="/about">About</a></li> 
         					<li><a href="/features">Features</a></li> 
         					<li><a href="/contact">Contact &amp; Support</a></li> 
         					<li><a href="/training">Training</a></li> 
       					</ul> 
 
 
       					<ul class="footer_nav"> 
         					<h4>Documentation</h4> 
         					<li><a href="/help">ifttt Help</a></li> 
         					<li><a href="/developer">Developer</a></li> 
       					</ul>     
     			</div><!-- /.site --> 
  			</div><!-- /.upper_footer --> 
  			<div class="rule"></div>
			<div class="lower_footer">
  				<div class="container">
    				<div align = "center" id="legal">
     			 		<ul>
          					<a href="./privacy.html">Privacy</a>  
      					</ul>
      					<p>© 2011 <span id="_rrt">TWW</span> All rights reserved.</p>
   	 				</div>
  			 	</div>
			</div><!-- ./lower_footer -->
    	</div><!-- /#footer --> 
  	</body> 
</html> 
<div id="facebox" style="top: 62.9px; display: none; left: 435px; ">       
	<div class="popup">         
		<table>           
			<tbody>             
				<tr><td class="tl"></td><td class="b"></td><td class="tr"></td></tr>             
				<tr><td class="b"></td>
				<td class="body">
					<div class="content" style="display: block; ">
						<div id="messages" style="">
							<h3>1 Messages</h3>
							<p> <strong>Sometime u dont know</strong> by Admin<br>
          						sb<small><!-- <a href="#" class="remove-link" title="Remove message">Remove</a> --></small>
							</p>
						</div>
					</div>
					<div class="footer" style="display: block; ">                   
						<a href="#" class="close">                     
							<img src="resources/images/icons/cross.png" title="close" class="close_image">                  
						</a>                 
					</div>               
				</td>               
				<td class="b"></td>             
				</tr>             
				<tr>               
					<td class="bl"></td><td class="b"></td><td class="br"></td>             
				</tr>           
			</tbody>         
		</table>       
	</div>     
</div>