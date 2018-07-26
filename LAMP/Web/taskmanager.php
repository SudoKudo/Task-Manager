<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width = device-width, initial - scale = 1">

	<!-- Links -->
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/home.css">
	<link href='https://fonts.googleapis.com/css?family=Pacifico' rel= 'stylesheet' type='text/css'>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="icon" href="images/task_manager_icon.png">

	<!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/code.js"></script>

    <style type="text/css">
    	a:hover { text-decoration: none; cursor: pointer;}
    </style>

	<title>Task Manager</title>

</head>
	<body onload='displayInitial()' id="daysContainer">
			<div id="menubar">
			    <ul>		
	    			<li id="pageTitle" title="View-only website">Task Manager [Viewer]</li>
	    			<!-- Use id "loggedInAs" to print "Logged in as [username]" in the top right corner -->
	                <!-- Logout button -->
	                <li id="logoutButton"><span id="loggedInAs">Logged in as</span><a href="/"><span class="glyphicon glyphicon-log-out" style="color:white" title="Logout" onclick='doLogout()'></span></a></li>		                
			    </ul>
			</div>

			<!--The scroll buttons -->
			<button type="button" id= "scrollLeft" title="Back a day" class="glyphicon glyphicon-menu-left" onclick='doScrollLeft()'></button>
			<button type="button" id= "scrollRight" title="Forward a day" class="glyphicon glyphicon-menu-right" onclick='doScrollRight()'></button>
			<!-- -->

			<!--Days (Cards) -->
			<div id="visibleDays" class="container">

			  <!-- Block 1 -->
			  <div class="col-sm-2" style="width: 20%">
			    <div class="card">
			      <div class="card-header">
			      	<div class= "dayOfWeek">
			      		<center><div id= "day1">Monday</div></center>
			      	</div>    
				  </div>
			      <div class="card-body">
			      

				      <!--Edit the innerHTML of this ul to add and remove tasks in block 1 -->
				      <ul id="tasks1" class="list-group list-group-flush">
					    
				      </ul>

				  </div>

			      <div class="card-footer">
			      	<div class="date">
			      		<center><div id= "date1">June 18, 2018</div></center>
			      	</div>
			      </div>
			    </div>
			  </div>
			  <!--End of block 1 -->

			  <!-- Block 2 -->
			  <div class="col-sm-2" style="width: 20%">
			    <div class="card">
			      <div class="card-header">
			      	<div class= "dayOfWeek">
			      		<center><div id= "day2">Tuesday</div></center>
			      	</div>    
				  </div>
			      <div class="card-body">
			      

				      <!--Edit the innerHTML of this ul to add and remove tasks in block 1 -->
				      <ul id="tasks2" class="list-group list-group-flush">
					    
					    
				      </ul>

				  </div>

			      <div class="card-footer">
			      	<div class="date">
			      		<center><div id= "date2">June 19, 2018</div></center>
			      	</div>
			      </div>
			    </div>
			  </div>
			  <!--End of block 2 -->

			  <!-- Block 3 -->
			  <div class="col-sm-2" style="width: 20%">
			    <div class="card">
			      <div class="card-header">
			      	<div class= "dayOfWeek">
			      		<center><div id= "day3">Wednesday</div></center>
			      	</div>    
				  </div>
			      <div class="card-body">
			      

				      <!--Edit the innerHTML of this ul to add and remove tasks in block 1 -->
				      <ul id="tasks3" class="list-group list-group-flush">
					  	
				      </ul>

				  </div>

			      <div class="card-footer">
			      	<div class="date">
			      		<center><div id= "date3">June 20, 2018</div></center>
			      	</div>
			      </div>
			    </div>
			  </div>
			  <!--End of block 3 -->

			  <!-- Block 4 -->
			  <div class="col-sm-2" style="width: 20%">
			    <div class="card">
			      <div class="card-header">
			      	<div class= "dayOfWeek">
			      		<center><div id= "day4">Thursday</div></center>
			      	</div>    
				  </div>
			      <div class="card-body">
			      

				      <!--Edit the innerHTML of this ul to add and remove tasks in block 1 -->
				      <ul id="tasks4" class="list-group list-group-flush">			      		

				      </ul>

				  </div>

			      <div class="card-footer">
			      	<div class="date">
			      		<center><div id= "date4">June 21, 2018</div></center>
			      	</div>
			      </div>
			    </div>
			  </div>
			  <!--End of block 4 -->

			  <!-- Block 5 -->
			  <div class="col-sm-2" style="width: 20%">
			    <div class="card">
			      <div class="card-header">
			      	<div class= "dayOfWeek">
			      		<center><div id= "day5">Friday</div></center>
			      	</div>    
				  </div>
			      <div class="card-body">
			      

				      <!--Edit the innerHTML of this ul to add and remove tasks in block 1 -->
				      <ul id="tasks5" class="list-group list-group-flush">				      		

			              </ul>

				  </div>

			      <div class="card-footer">
			      	<div class="date">
			      		<center><div id= "date5">June 22, 2018</div></center>
			      	</div>
			      </div>
			    </div>
			  </div>
			  <!--End of block 5 -->
			  

			</div> <!--End of visibleDays-->


			<!-- View Task Modal -->
			<div class="container">
			  <!-- Modal -->
			  <div class="modal fade" id="viewTaskModal" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header" id="viewTaskHeader">
			          <button type="button" class="close" id="dismiss" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title" id="taskName">Task name</h4>
			        </div>
			        <!-- Modal body content-->
			        <div class="modal-body mx-3" id="detailsDisplay">

		                </div>
		            <!-- End Modal body content / Start of Modal footer content-->
		            <div class="modal-footer d-flex justify-content-center">
		            	<!-- Use this div to print an error to the Create New Account modal -->
		            	<div id="viewTaskModal" style="float:left"></div>
		                <p><span style="padding-right: 6px;" class= "glyphicon glyphicon-phone"></span>Use the mobile app to add, modify, and delete tasks</p>
		            </div>
		            <!-- End Modal footer content-->
			        </div>
			      </div>
			      <!-- End content-->
			    </div>
			  </div>		  
			</div>
			<!-- End View Task Modal -->

	</body>
</html>