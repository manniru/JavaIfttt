 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ page import="org.wzz.ifttt.response.Member.Register"%>
 <!DOCTYPE html> 
<html> 
	<head> 
		<meta charset='utf-8'> 
		<meta http-equiv="X-UA-Compatible" content="chrome=1"> 
		<title>Plans &amp; Pricing - ifttt</title>  

		<link href="./stylesheet/index.css" media="screen" rel="stylesheet" type="text/css" /> 

		<script src="" type="text/javascript"></script>
  	</head> 
 
 	<body class="logged_out wider windows  env-production ">
  		<div id="header" class="true clearfix"> 
    		<div class="container" class="clearfix"> 
        		<a class="logo" href="./index.html">  
            		<img height="46" width="96" alt="ifttt" src="./pic/logo.png"> 
          		</a> 
   				<ul class="top-nav logged_out">
   					<li class="register">
   						<a href="./sup.jsp">Signup</a>
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
					<h1>Plans &amp; Pricing</h1>  
					<p>Join today and collaborate with the smartest developers in the world.</p>  
				</div>  
  
				<div class="plan hplan free">  
					<div class="price">  
						<span class="symbol">$</span><span class="amount">0</span><span class="duration">/month</span>  
					</div>  
					<a href="./free.jsp" class="button classy"><span>Create a free account</span></a>  
					<h3>Free for basic tasks with some limits</h3>  
					<p><strong>Unlimited</strong> basic tasks and <strong>unlimited</strong> friends</p>  
				</div>  
 
				<div class="plans-row">  
					<div class="plan personal leftmost">  
						<h3>  
							<div class="price">  
								<span class="symbol">$</span><span class="amount">4</span><span class="duration">/mo</span>  
							</div>  
  
							Micro 
						</h3>  
						<div class="rule"></div>  
						<ul class="bigpoints"><li><strong>5</strong>  Advance Tasks</li></ul>  
						<ul class="smallpoints"><li><strong>Unlimited</strong> basic tasks</li></ul>  
						<a href="/signup/micro" class="button classy personal-plan"><span>Create an account</span></a>  
					</div><!-- /.plan.personal.leftmost -->  
  
					<div class="plan personal middle">  
						<h3>  
							<div class="price">  
								<span class="symbol">$</span><span class="amount">6</span><span class="duration">/month</span>  
							</div>  
  
							Small 
						</h3>  
						<div class="rule"></div>  
						<ul class="bigpoints"><li><strong>10</strong>  Advance Tasks</li></ul>  
						<ul class="smallpoints"><li><strong>Unlimited</strong> basic tasks</li></ul>  
						<a href="/signup/small" class="button classy personal-plan"><span>Create an account</span></a>  
					</div><!-- /.plan.personal.middle -->  
  
					<div class="plan personal rightmost">  
						<h3>  
							<div class="price">  
								<span class="symbol">$</span><span class="amount">10</span><span class="duration">/month</span>  
							</div>  
  
							Medium 
						</h3>  
						<div class="rule"></div>  
						<ul class="bigpoints"><li><strong>20</strong> Advance Tasks</li></ul>  
						<ul class="smallpoints"><li><strong>Unlimited</strong> public repositories</li></ul>  
						<a href="/signup/medium" class="button classy personal-plan"><span>Create an account</span></a>  
					</div><!-- /.plan.personal.rightmost -->  
				</div><!-- /.plans.row --> 
  
				<div class="rule"></div> 
  
				<ul class="plans-features"><li class="intro">All tasks come with&hellip;</li><li>Email support</li></ul>  
  
				<div class="columns equacols bordered">  
					<div class="column main questions">  
  
						<h2>Confused about which plan to choose?</h2>  
						<p>Let us know what you need and we'll help you out.</p>  
  
						<form action="/contact" method="post"> 
				 
							<input type="hidden" name="form[subject]" value="Plans question">  
							<dl class="form"><dt><label>Your email address</label></dt><dd>  
									<input type="text" name="form[email]" value="">  
								</dd></dl>  
							<textarea name="form[comments]" rows = "6" cols = "66"></textarea>  
  
							<div class="form-actions">  
								<button type="submit" class="classy"><span>Send message</span></button>  
							</div>  
						</form>  
					</div><!-- /.column.main -->  
					 
					<div class="column secondary faqs">  
  
						<h2>What if I need a larger plan?</h2>  
						<p>  
							Contact us at <a href="mailto:joe1991@live.com">support@njuifttt.com</a>  
							to work something out. We also offer an installable version. 
						</p>  
  
						<h2>What&rsquo;s different about an pay account?</h2>  
						<p>  
							A ifttt pay account... 
						</p>  
 	 
						<h2>Why don't I see any disk space limits?</h2>  
						<p>  
							ifttt ... 
						</p>  
  
						<h2>What is a advanced task?</h2>  
						<p> 

						Advanced task...

						</p> 

 

					</div><!-- /.column.secondary -->  
				</div><!-- /.columns.equacols.bordered -->  
  
				<p class="read-it">Questions? Concerns? Contact us day or night at <a href="mailto:joe1991@live.com">support@njuifttt.com</a>.</p> 
			</div><!-- container -->  
		</div> <!-- site -->

    



    	<div id="footer">       
  			<div class="upper_footer">  
     			<div class="clearfix"> 
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
  		</div><!-- /.footer --> 
  	</body>
</html>	
  	
     			

 

	