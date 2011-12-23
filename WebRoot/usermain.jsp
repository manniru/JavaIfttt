<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ page import=" org.wzz.ifttt.response.Member.Login,org.wzz.ifttt.response.Member.Task"%>
<!DOCTYPE html> 
<html> 
  	<head> 
    	<base href="<%=basePath%>">
    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
    	<meta charset='utf-8'> 
    	<meta http-equiv="X-UA-Compatible" content="chrome=1">
        <title>admin's Profile - ifttt</title> 

   		<link href="./stylesheet/index.css" media="screen" rel="stylesheet" type="text/css" /> 
   		<script src="" type="text/javascript"></script> 
     
  		<link href="/mzd.atom" rel="alternate" title="atom" type="application/atom+xml" />  
  	</head> 
	<body class="logged_in page-profile mine windows  env-production ">
		<%
			long authcode=0;
			Login login = new Login();
			System.out.println("AUTH CODE = " + request.getParameter("authcode"));
			if(request.getParameter("authcode")!=null)	{
				System.out.println("***GET AUTHCODE***");
				if(!Login.isLogin(Long.parseLong(request.getParameter("authcode")))) { %>
					<jsp:forward page="IFTTT_Login_Error.jsp"></jsp:forward>
				<% }
				else authcode = Long.parseLong(request.getParameter("authcode"));
			}
			else {
				if (request.getParameter("login").equals("System")&& request.getParameter("password").equals("Root")) {
					%>
  						<jsp:forward page="IFTTT_Manager.jsp"></jsp:forward>
  					<%
  				}
  				System.out.println("NOT LOGIN");
  				login.setMemberId(request.getParameter("login"));
  				login.setPassword(request.getParameter("password"));
  				login.setLoginHash(login.login());
  				if (login.getUserName() == null) { %>
  					<jsp:forward page="IFTTT_Login_Error.jsp"></jsp:forward>
  				<%
  				} 
  				authcode = login.getLoginHash();
  			}
   		  	Task task = new Task();
			
   		  	task.setTaskCount();
   		  	task.getMemberTask(authcode);
   		  	%>
  		
  		
	    <div id="header" class="true clearfix"> 
        	<div class="container" class="clearfix"> 
          		<a class="logo" href="./ifttt"> 
            		<img height="46" width="96" alt="ifttt" src="./pic/logo.png"> 
         		</a> 
 				
 				<div class="topsearch"> 
					<form action="/search" id="top_search_form" method="get">        
						<a href="/search" class="advanced-search tooltipped downwards" title="Advanced Search">Advanced Search</a> 
        					<div class="search placeholder-field js-placeholder-field"> 
          						<label class="placeholder" for="global-search-field">Search</label> 
          							<input type="text" class="search my_repos_autocompleter" id="global-search-field" name="q" results="5" /> <input type="submit" value="Search" class="button" /> 
        					</div> 
        					<input type="hidden" name="type" value="Everything" /> 
       						<input type="hidden" name="repo" value="" /> 
        					<input type="hidden" name="langOverride" value="" /> 
        					<input type="hidden" name="start_value" value="1" /> 
					</form>      
					<ul class="top-nav"> 
          				<li class="explore"><a href="/explore">Explore</a></li> 
        				<li><a href="/help">Help</a></li> 
      				</ul> 
    			</div> <!-- /.topsearch --> 
 
 				<div id="userbox"> 
    				<div id="user"> 
      					<a href="./admin">
      						<img height="20" src="" width="20" /></a> 
      					<a href="https://github.com/mzd" class="name">mzd</a> 
    				</div> 
    				<ul id="user-links"> 
      					<li> 
        					<a href="/inbox/notifications" id="notifications" class="tooltipped downwards" title="Notifications"> 
          						</span> 
        					</a> 
      					</li> 
      					<li><a href="/account" id="settings" class="tooltipped downwards" title="Account Settings"><span class="icon">Account Settings</span></a></li> 
      					<li><a href="/logout" id="logout" class="tooltipped downwards" title="Log Out"><span class="icon">Log Out</span></a></li> 
    				</ul> 
  				</div> <!-- /.userbox -->
        	</div> 
      	</div> <!-- header --> 
  
        <div class="site"> 
          	<div class="container"> 
				<div class="pagehead userpage" data-name="admin"> 
  					<h1 class="avatared"> 
      					<span class="tooltipped downwards" title="Change your avatar at gravatar.com">
      						<a href="http://gravatar.com/emails/">
      							<img height="48" src="https://secure.gravatar.com/avatar/16074080e71c3880ef54b974decebed6?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="48" />
      						</a>
      					</span> 
    					<%=login.getUsernameByHashcode(authcode)%>
    					<em>(<%=login.selectUserName(authcode)%>)</em> 
  					</h1> 
  					<ul class="pagehead-actions"> 
        				<li class="text">This is you!</li> 
        				<li><a href="ModifyData.jsp?authcode=<%=authcode %>" class="minibutton btn-editprofile"><span>Edit Your Profile</span></a></li> 
  					</ul> 
  					
  					<div class="rule"></div> 
  					
				</div><!-- /.pagehead --> 
 
  				<div class="columns profilecols"> 
    				<div class="first vcard"> 
      					<dl><dt>Name</dt><dd class="fn"><%=login.selectUserName(authcode)%></dd></dl> 

      					<dl><dt>Member Since</dt><dd>Oct 08, 2010</dd></dl>
      					<dl><dt>Level</dt><dd class="fn"><%=login.getLevel(login.getUsernameByHashcode(authcode))%></dd></dl>
      					<dl><dt>Integral</dt><dd class="fn"><%=login.getIntegral(authcode)%></dd></dl> 
    				</div><!-- /.first --> 
    				<div class="last"> 
      					<ul class="stats"> 
        				<li> 
          				<a href="/admin/tasks"> 
            				<strong><%=task.getTaskCount()%></strong> 
            				<span>tasks</span> 
          				</a> 
        				</li> 
        				<li> 
          				<a href="/admin/friends"> 
            				<strong>2</strong> 
            				<span>friends</span> 
          				</a> 
        				</li> 
      					</ul> 

    				</div><!-- /.last --> 
	  			</div><!-- /.columns.profilecols --> 
 
  				<div class="rule"></div> 
 
  				<div class="columns profilecols js-repo-filter"> 
    				<div class="first"> 
      					<h2> 
         					Tasks <em>(<%=task.getTaskCount()%>)</em> 
      					</h2>
      					<a href="./newtask.jsp?authcode=<%=authcode %>"><button type="button" class="classy primary js-oneclick" name="submit">Create A New Task</button></a>
 
      					<div class="filter-bar"> 
        					<div class="placeholder-field js-placeholder-field"> 
          						<label class="placeholder" for="your-repos-filter" style="display: block; ">Find a repositorya</label> 
          						<input type="text" id="your-repos-filter" class="filter_input"> 
        					</div> 
        					<ul class="repo_filterer"> 
        					<%int i=0;%>
          						<li class="all_repos"><a class="repo_filter filter_selected" rel="active, unactive" onclick="<%for(int j=0;j<task.getTaskCount();j++) {%>document.getElementById('Task<%=j%>').style.display='inline';document.getElementById('control_button').style.display='inline';<%}%>">All Tasks</a></li> 
            					<li><a href="#" class="repo_filter" rel="active">Active</a></li> 
            					<li><a href="#" class="repo_filter" rel="unactive">Unactive</a></li> 
        					</ul> 
      					</div>
      					<%
       						while(i<task.getTaskCount()) {
       					%>
						<ul id="Task<%=i%>" class="repositories repo_list" style="display: none">
							<li class="public source">
								<ul class="repo-stats">
									<li><!-- get Tasks --></li>
								</ul>
								<h3>
									<a>Test Task<%=i %></a>
								</h3>
								<div class="body">
									<p class="description">
										<!--  get Isruntask -->
									</p>

									<p class="updated-at">
										Last updated
										<time class="js-relative-date"
											datetime="2011-12-18T22:47:11-08:00"
											title="2011-12-18 22:47:11">
											December 18, 2011
										</time>
									</p>
									<p class="description">
									</p>

									<div class="participation-graph disabled">
										<canvas class="bars"
											data-source="/mzd/JavaIfttt/graphs/participation" height="20"
											width="416"></canvas>
										<img alt="legend" class="legend"
											src="https://a248.e.akamai.net/assets.github.com/images/modules/dashboard/dossier/participation_legend.png?1284681402" />
									</div>
								</div>
								<a href="ModifyTask.jsp?authcode=<%=authcode%>"><button type="submit" class="classy primary js-oneclick" id="modify_button<%=i %>"><span>Modify</span></button></a>
								<a href="Run.jsp?authcode=<%=authcode%>"><button type="submit" class="classy primary js-oneclick" id="run_button<%=i %>"><span>Run</span></button></a>
								<a href="Stop.jsp?authcode=<%=authcode%>"><button type="submit" class="classy primary js-oneclick" id="stop_button<%=i %>"><span>Stop</span></button></a>
								<a href="DeleteTask.jsp?authcode=<%=authcode%>"><button type="submit" class="classy primary js-oneclick" id="delete_button<%=i %>"><span>Delete</span></button></a>
								<!-- /.body -->
							</li>
						
						</ul>
						<%
							i++;
							}
						%>
						<div id="control_button" style="display:none">
						</div>
						</form>
    				</div><!-- /.first --> 
    				<div class="last"> 
      					<h2>Public Activity <a href="/mzd.atom"><img alt="feed" src="https://a248.e.akamai.net/assets.github.com/images/icons/feed.png?1284681402" /></a></h2> 
        					<div class="news public_news"> 
            					<div class="alert push">
            						<div class="body">
            							<div class="title"> 
  											<a href="/mzd">mzd</a> 
  											<span>modify</span> the event of <a href="/mzd/tast1">mzd/task1</a> 
  											<time class="js-relative-date" datetime="2011-12-19T06:47:12Z" title="2011-12-19 06:47:12">December 19, 2011</time> 
										</div> 
										<div class="details"> 
											
										</div> <!-- details --> 
									</div><!-- body -->
								</div> 

 
  
            				<div class="alert follow"><div class="body"><div class="title"> 
  								<a href="/mzd">mzd</a> <span>started following</span> <a href="/xzjh">xzjh</a> 
  								<time class="js-relative-date" datetime="2011-12-18T17:14:24Z" title="2011-12-18 17:14:24">December 18, 2011</time> 
							</div></div></div> 
            				<div class="alert create">
            					<div class="body">
            					<div class="title"> 
  								<a href="/mzd">mzd</a> 
  								<span>created</span> 
  									a task <a href="/mzd/JavaIfttt/tree/master">tasktest</a>  
  								<time class="js-relative-date" datetime="2011-12-18T17:00:31Z" title="2011-12-18 17:00:31">December 18, 2011</time> 
								</div> 
							</div> 
            
            
            
        
        					</div> 
    				</div><!-- /.last --> 
  				</div><!-- /.columns.profilecols --> 
  			</div> 
        </div> 
    
    	<!-- footer --> 
    	<div id="footer" > 
      		<div class="upper_footer"> 
     			<div class="container" class="clearfix"> 
       				<!--[if IE]><h4 id="blacktocat_ie">GitHub Links</h4><![endif]--> 
       				<![if !IE]><h4 id="blacktocat">GitHub Links</h4><![endif]> 
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
      					<p>Â© 2011 <span id="_rrt">TWW</span> All rights reserved.</p>
   	 				</div>
  			 	</div>
			</div><!-- ./lower_footer -->
    	</div><!-- /#footer --> 
  	</body> 
</html> 
      
  