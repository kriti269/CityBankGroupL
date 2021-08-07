<div class="navbar">
<!--  <div class="header"> -->
	<img class="logo" src="<%=request.getContextPath()%>/resources/images/logo.JPG">
  	<div class="header-right">
  	<% if(((Boolean)session.getAttribute("is_admin")) == true) { %>
  		<div class="header">
		    <a class="${param.selected == 'Register User' ? 'active' : '' }" href="<%=request.getContextPath()%>/welcome">Register User</a>
		    <a class="${param.selected == 'Add Account' ? 'active' : '' }" href="<%=request.getContextPath()%>/addAccount">Add Account</a>
		    <a class="${param.selected == 'View Users' ? 'active' : '' }" href="<%=request.getContextPath()%>/viewAllUsers">View Users</a>
		    <div id="dropdown" class="right">
		    	Welcome <%= session.getAttribute("login_id") %> (Administrator)
		    	<form class="dropdown-menu" id="logOut" action="logout" method="post">
					<button type="submit" class="btn" id="logOut" name="logOut">LogOut</button>
				</form>
		    </div>
	    </div>
	<% } else { %>
		<div class="header">
			<a class="${param.selected == 'Account' ? 'active' : '' }" href="<%=request.getContextPath()%>/getUserAccounts">Account</a>
			<a class="${param.selected == 'Pay Bill' ? 'active' : '' }" href="<%=request.getContextPath()%>/paybills">Pay Bill</a>
		    <a class="${param.selected == 'Transaction' ? 'active' : '' }" href="<%=request.getContextPath()%>/viewAllTransactions">Transaction</a>
		    <div id="dropdown" class="right">
		    	Welcome <%= session.getAttribute("login_id") %>
			    <form class="dropdown-menu" id="logOut" action="logout" method="post">
					<button type="submit" class="btn" id="logOut" name="logOut">LogOut</button>
				</form>
			</div>
	    </div>
	<% } %>
 	</div>
</div>