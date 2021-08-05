<div class="navbar">
<!--  <div class="header"> -->
	<img class="logo" src="resources/images/logo.JPG">
  	<div class="header-right">
  	<% if(((Boolean)session.getAttribute("is_admin")) == true) { %>
  		<div class="header">
		    <a class="active" href="<%=request.getContextPath()%>/welcome">Register User</a>
		    <a href="<%=request.getContextPath()%>/viewAllUsers">View Users</a>
		    <div id="dropdown" class="right">
		    	Welcome ${firstname} (Administrator)
		    	<form class="dropdown-menu" id="logOut" action="logout" method="post">
					<button type="submit" class="btn" id="logOut" name="logOut">LogOut</button>
				</form>
		    </div>
	    </div>
	<% } else { %>
		<div class="header">
			<a class="active" href="<%=request.getContextPath()%>/accounts">Account</a>
			<a href="<%=request.getContextPath()%>/bills">Pay Bill</a>
		    <a href="<%=request.getContextPath()%>/transactions">Transaction</a>
		    <div id="dropdown" class="right">
		    	Welcome ${firstname}
			    <form class="dropdown-menu" id="logOut" action="logout" method="post">
					<button type="submit" class="btn" id="logOut" name="logOut">LogOut</button>
				</form>
			</div>
	    </div>
	<% } %>
 	</div>
</div>