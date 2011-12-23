<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ page import="org.wzz.ifttt.response.Member.Message"%>
<!DOCTYPE html> 
<html> 
  	<head> 
		<meta charset='utf-8'> 
		<meta http-equiv="X-UA-Compatible" content="chrome=1"> 
		<title>Send your Message</title>  

		<link href="./stylesheet/index.css" media="screen" rel="stylesheet" type="text/css" /> 

		<script src="./script/ajax.js" type="text/javascript"></script>
		<script src="./script/signup_check.js" type="text/javascript"></script>
		<script src="./script/validation-utils.js" type="text/javascript"></script>
		<script language="javascript" type="text/javascript">
			var nameRequest = createRequest();
			var nameErrorRequest = createRequest();
			var emailRequest = createRequest();
			var emailErrorRequest = createRequest();
			var passwordRequest = createRequest();
			var passwordConfirmRequest = createRequest();
		</script>
		
  	</head> 
 
 	<body class="logged_out wider windows  env-production " onLoad="document.forms[0].reset();">
  		<div id="header" class="true clearfix"> 
    		<div class="container" class="clearfix"> 
        		<a class="logo" href="./index.html">  
            		<img height="46" width="96" alt="ifttt" src="./pic/logo.png"> 
          		</a> 
   				<ul class="top-nav logged_out">
   					<li class="register">
   						<a href="./sup.html">Signup</a>
   					</li>
   					<li class="explore">
   						<a href="/explore">Explore ifttt</a>
   					</li>
   					<li class="features">
   						<a href="/features">Features</a>
   					</li>
   					<li class="login">
   						<a href="./login.html">Login</a>
   					</li>
   				</ul> 
    		</div> 
    	</div><!-- body.header --> 
 
        <div class="site"> 
          	<div class="container"> 
            	<div class="pagehead"> 
    				<h1>Send your Message</h1> 
    				<ul class="pagehead-actions"> 
      					<!-- <li><a href="./login.html" class="minibutton btn-log-in-to-an-existing-account "><span><span class="icon"></span>Log in to an existing account</span></a></li> --> 
    				</ul> 
				</div> 
 
  				<div class="plan hplan final free"> 
          			<div class="price"> 
        				<span class="symbol">$</span><span class="amount">0</span><span class="duration">/month</span> 
      				</div> 
 
    				<h3>You are sending a message</h3> 
  				</div> 
 
				<div class="columns equacols bordered"> 
  					<div class="column main"> 
    					<noscript> 
      						<div class="fieldgroup">
        						<div class="error">
          							<p>
            						Ifttt does not support browsers with JavaScript disabled.
            						Please whitelist njuifttt.com in your javascript blocker.<br>
           							We promise we'll behave.
          							</p>
        						</div>
      						</div>
    					</noscript> 
						<form action="SendMessage_Success.jsp" id="sendmessage_form" method="post">
							<div style="margin:0;padding:0;display:inline">
								<input name="authenticity_token" type="hidden" value="vDEZVd40UJEPD73U0V0OBDuDbiJVVgEwuMpghnVUeHo=" />
							</div> 
  							<h2 class="subdued"> 
    							Send A Message
  							</h2> 
  							<div class="fieldgroup"> 
    							<div class="fields" id="user_sendmessage_fields"> 
      								<dl class="form autocheck" id="user_signup_userid" style="display:none"> 
    									<dt><label  name="authcode">AuthCode</label></dt> 
    									<dd><input  id="authcode" name="authcode" size="30" type="text" value="<%= request.getParameter("authcode")%>" /></dd></dl>
    								<dl class="form autocheck" id="user_receiver"> 
    									<dt><label autocapitalize="off" error="false" for="user_recieve" name="user[receive]">Receiver</label></dt> 
    									<dd><input autocapitalize="off" error="false" id="user_recieve" name="user[receive]" size="30" type="text" /></dd></dl>
      								<dl class="form autocheck" id="user_message"> 
    									<dt><label autocapitalize="off" error="false" for="user_message" name="user[message]">Message</label></dt> 
    									<dd><input autocapitalize="off" error="false" id="user_message" name="user[message]" size="30" type="text" /></dd></dl>
    							</div> 
  							</div>  
  							<input id="user_plan" name="user[plan]" type="hidden" value="free" /> 
  							<input id="org_plan" name="org_plan" type="hidden" /> 
  							<div class="fieldgroup" style="display:none"> 
    							<div class="fields"> 
      								<dl class="form"> 
    								<dt><label error="false" for="user_coupon" name="user[coupon]">Coupon</label></dt> 
   	 								<dd><input error="false" id="user_coupon" name="user[coupon]" size="30" type="text" /></dd></dl> 
    							</div> 
 							</div> 
 								 
  							<div class="form-warning"> 
    							<p>By clicking on "Send" below, you are agreeing to the
    							<a href="/terms" target="_blank">Terms of Service</a> and the
    							<a href="/privacy" target="_blank">Privacy Policy</a>.</p> 
  							</div> 
  							<div class="form-actions"> 
   			 						<button type="submit" class="classy primary js-oneclick" id="signup_button" ><span>Send</span></button> 
  								</div> 
						</form> 
					</div><!-- /.column.main --> 
 			 	<div class="column secondary"> 
      				<div class="featured-brands"> 
        				<p><img alt="Facebook, EMI, Yahoo! and 37Signals" src="./pic/logos.jpg" height="50" weight="400"/></p> 
        				<p>You're joining to use web smartly</p> 
      				</div> 
      				
      				<ul class="selling-points"> 
        				<li>I'm cute</li> 
      				</ul> 
 
  				</div><!-- /.column.sidebar --> 
			</div> 
 
          </div> 
        </div>    
		
		<!-- footer --> 
    	<div id="footer" > 
      		<div class="upper_footer"> 
     			<div class="container" class="clearfix"> 
       				<!--[if IE]><h4 id="blacktocat_ie">ifttt Links</h4><![endif]--> 
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
      					<p>脗漏 2011 <span id="_rrt">TWW</span> All rights reserved.</p>
   	 				</div>
  			 	</div>
  			 	<dl class="form autocheck error" style="display:none" id="user_signup_username_error">
    				<dt><label autocapitalize="off" check-url="/signup_check/username" error="false" for="user_login" name="user[login]">Username</label></dt>
    				<dd><input autocapitalize="off" check-url="/signup_check/username" error="false" id="user_login_error" name="user[login]" size="30" type="text" style="background-image: url(https://github.com/images/modules/ajax/error.png); " onChange="checkUsernameError()"></dd><dd class="error">Username is a reserved word, is already taken.</dd></dl>
      			
      			<dl class="form autocheck error" id="user_signup_email_error" style="display:none">
    				<dt><label autocapitalize="off" check-url="/signup_check/email" for="user_email" hint="We promise we won't share your email with anyone." name="user[email]">Email Address</label></dt>
    				<dd><input autocapitalize="off" check-url="/signup_check/email" hint="We promise we won't share your email with anyone." id="user_email_error" name="user[email]" size="30" type="text" style="background-image: url(https://github.com/images/modules/ajax/error.png); " onChange="checkEmailError()"><p class="note" style="display: none; ">We promise we won't share your email with anyone.</p></dd><dd class="error">Email is invalid or already taken</dd></dl> 
      			<dl class="form autocheck error" id="user_signup_password_error" style="display:none"> 
    				<dt><label check-url="/signup_check/password" error="false" for="user_password" hint="Must contain one lowercase letter, one number, and be at least 7 characters long" name="user[password]">Password</label></dt> 
    				<dd><input check-url="/signup_check/password" error="false" hint="Must contain one lowercase letter, one number, and be at least 7 characters long" id="user_password" name="user[password]" size="30" type="password" /><p class="note">Must contain one lowercase letter, one number, and be at least 7 characters long</p></dd></dl>  
    			
      			<dl class="form autocheck error" id="user_signup_confirm_password_error" style="display:none"> 
    				<dt><label error="false" for="user_password_confirmation">Confirm Password</label></dt> 
    				<dd><input error="false" id="user_password_confirmation_error" name="user[password_confirmation]" size="30" type="password" onChange="checkConfirmPasswordError()"/><p class="note">Doesn't match the password</p></dd></dl> 
			</div><!-- ./lower_footer -->
    	</div><!-- /#footer --> 
  	</body> 
</html> 
