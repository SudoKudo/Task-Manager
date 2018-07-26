<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Links -->
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/login-page.css">
	<link href='https://fonts.googleapis.com/css?family=Pacifico' rel= 'stylesheet' type='text/css'>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="icon" href="images/task_manager_icon.png">

	<!-- Scripts -->  
        <script src="js/code.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<style type="text/css">
	body
	{
		font-size: 150%;
		font-family: Helvetica, sans-serif;
		background-image: url('css/simple.jpg');
		background-size: auto;
		background-repeat: no-repeat;
	}
	.jumbotron
	{		
		/* Color and opacity*/
		background: rgba(33,32,63,0);
		border-color: rgba(33,32,63,1);
		border-style:solid;
		color: white;
		padding-top: 3.5%;
		padding-bottom: 5%;
		/*background: rgba(112,121,179,0.65);<--- more purple*/
	}	
	.tab-content
	{
		border-left: 1px solid #ddd;
		border-right: 1px solid #ddd;
		border-bottom: 1px solid #ddd;
		padding: 10px;
	}
	.nav-tabs
	{
		margin-bottom: 0;
	}
	</style>

	<title>Task Manager</title>

</head>
	<body>
		<div id= "loginBox" class="container">

			<div class="jumbotron">
				<center>
					<p id="websiteTitle">Task Manager</p>
					<div id= "loginForm">
					  <div class="form-group">
					    <input type="text" class="form-control" id="username" placeholder= "Username" style="width: 100%;">
					  </div>
					  <div class="form-group">
					    <input type="password" class="form-control" id="password" placeholder="Password" style="width: 100%;">
					  </div>

					  <button type="submit" id= "loginButton" class="btn btn-default" onclick='doLogin()'>
					  	<span style="padding-right: 6px;" class= "glyphicon glyphicon-log-in"></span>Login</button>

					  <br><br>
					  <button type="button" id= "createNew" class = "btn btn-link btn-lg" data-toggle="modal" data-target="#createAccountModal" onclick='clearCreateAcctModal()'>Create New Account</button>
                                          <!-- Password reset feature removed from website
                                          <button type="button" id= "reset" class = "btn btn-link btn-lg" data-toggle="modal" data-target="#forgotPassword">Forgot password?</button> 
                                          --> 

					</div>
				</center>
			</div>

			<!-- Create New Account Modal -->
			<div class="container">
			  <!-- Modal -->
			  <div class="modal fade" id="createAccountModal" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header" id="createAccountHeader">
			          <button type="button" class="close" id="dismiss" onclick='clearCreateAcctModal()' data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Create New Account</h4>
			        </div>
			        <!-- Modal body content-->
			        <div class="modal-body mx-3">
		                <div class="column" id= "firstName">
		                    <input type="text" class="form-control" id="newFirstName" placeholder= "First Name">
		                </div>

		                <div class="column" id="lastName">
		                	<input type="text" class="form-control" id="newLastName" placeholder= "Last Name">
		                </div>

		                <div class="form-group">
					    <input type="text" class="form-control" id="newUsername" placeholder= "*Username" style="width: 100%;">
					    </div>

					    <div class="form-group">
					    	<input type="password" class="form-control" id="newPassword" placeholder="*Password" style="width: 100%;">
					    </div>

					    <div class="form-group">
					    	<input type="password" class="form-control" id="cPass" placeholder="*Confirm Password" style="width: 100%;">
					    </div>

					    <div class="form-group">
					    	<input type="email" class="form-control" id="email" placeholder="*Email" style="width: 100%;">
					    </div>

		            </div>
		            <!-- End Modal body content / Start of Modal footer content-->
		            <div class="modal-footer d-flex justify-content-center">
		            	<!-- Use this div to print an error to the Create New Account modal -->
		            	<div id="accountCreationError" style="float:left"></div>
		                <button type= "submit" class="btn btn-default" id="createAccountBtn" onclick='doRegister()'>Create Account</button>
		            </div>
		            <!-- End Modal footer content-->
			        </div>
			      </div>
			      <!-- End content-->
			    </div>
			  </div>		  
			</div>
			<!-- End of Create New Account Modal -->

            <!-- Password reset feature removed from website -->
			<!-- Forgot Password Modal -->
			<div class="container">
			  <!-- Modal -->
			  <div class="modal fade" id="forgotPassword" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header" id="createAccountHeader">
			          <button type="button" class="close" id="dismiss" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Password Reset</h4>
			        </div>
			        <!-- Modal body content-->
			        <div class="modal-body mx-3">
		                <div class="column" id= "firstName">
		                    <input type="text" class="form-control" id="firstNameFP" placeholder= "*First Name">
		                </div>

		                <div class="column" id="lastName">
		                	<input type="text" class="form-control" id="lastNameFP" placeholder= "*Last Name">
		                </div>

		                <div class="form-group">
					    	<input type="text" class="form-control" id="UsernameFP" placeholder= "*Username" style="width: 100%;">
					    </div>

					    <div class="form-group">
					    	<input type="email" class="form-control" id="emailFP" placeholder="*Email" style="width: 100%;">
					    </div>

		            </div>
		            <!-- End Modal body content / Start of Modal footer content-->
		            <div class="modal-footer d-flex justify-content-center">
		            	<!-- Use this div to print an error to the Create New Account modal -->
		            	<div id="forgotPasswordError" style="float:left"></div>
		                <button type= "submit" class="btn btn-default">Send email</button>
		            </div>
		            <!-- End Modal footer content-->
			        </div>
			      </div>
			      <!-- End content-->
			    </div>
			  </div>		  
			</div>
			<!-- End of Forgot Password Modal -->

	</body>
</html>