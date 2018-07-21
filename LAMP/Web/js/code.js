var urlBase = 'http://m4rks.site/LAMPAPI';
var extension = "php";

userId = 0;

//Arrays to store UI data
var innerHTMLforTasks  = [' ',' ',' ',' ',' '];
var day = [' ',' ',' ',' ',' '];
var date = [' ',' ',' ',' ',' '];
var fullDate = [];


function doRegister()
{

  console.log("Register");

  var firstName = document.getElementById("newFirstName").value; // Retrieve username
  var lastName = document.getElementById("newLastName").value; // Retrieve password
  var uName = document.getElementById("newUsername").value; // Retrieve username
  var pWord = document.getElementById("newPassword").value; // Retrieve password
  var cPass = document.getElementById("cPass").value;
  var email = document.getElementById("email").value;

  var invalidInput = "";
  
     
    if(!email.replace(/\s/g, '').length)
    {
           invalidInput = "Please enter an email address";
           console.log("No Email");
    } 
    if(cPass != pWord)
    {
           invalidInput = "Passwords do not match";
           console.log("Passwords do not match");
    }
    if(!pWord.replace(/\s/g, '').length)
    {
           invalidInput = "Please enter a password";
           console.log("No Password");
    }
    if(!uName.replace(/\s/g, '').length)
    {
           invalidInput = "Please enter a username";
           console.log("No Username");
    }  
    
    document.getElementById("accountCreationError").innerHTML = invalidInput;
  
  if(invalidInput == "")
  {
          var hashpass = sha1(pWord); // Encrypt the password

          // Convert to json string to pass to API
          var jsonPayload = '{"uName" : "' + uName + '","pWord" : "' + hashpass + '", "userId" : "' + userId + '", "firstName" : "' + firstName + '", "lastName" : "' + lastName + '", "email" : "' + email + '"}';
          var url = urlBase + '/Register.' + extension; // Call API code

          console.log(jsonPayload);

          var xhr = new XMLHttpRequest();
                  xhr.open("POST", url, true);
          xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
          try
          {
            xhr.onreadystatechange = function()
            {
              if (this.readyState == 4 && this.status == 200)
              {
                document.getElementById("accountCreationError").innerHTML = "User has been added!";
              }
            };
            xhr.send(jsonPayload);
          }
          catch(err)
          {
            document.getElementById("accountCreationError").innerHTML = "Unable to add user";
          }
  }
} //End of Register function

//Clear fields in the Create Account modal
function clearCreateAcctModal()
{
        
    firstName = document.getElementById("newFirstName").value = ""; 
    lastName = document.getElementById("newLastName").value = ""; 
    uName = document.getElementById("newUsername").value = ""; 
    pWord = document.getElementById("newPassword").value = "";
    cPass = document.getElementById("cPass").value = "";
    email = document.getElementById("email").value = "";
    document.getElementById("accountCreationError").innerHTML = "";

}


// Login function on the main screen
function doLogin()
{

  var uName = document.getElementById("username").value; // Takes in username from login
  var pWord = document.getElementById("password").value; // Takes in password
  
  if(!uName.replace(/\s/g, '').length)
  {
       console.log("No Username");
       return;
  }
  if(!pWord.replace(/\s/g, '').length)
  {
       console.log("No Password");
       return;
  }

  var hashpass = sha1(pWord); // Encrypt the password

  //document.getElementById("loginResult").innerHTML = "";

  var jsonPayload = '{"uName" : "' + uName + '", "pWord" : "' + hashpass + '"}';
  var url = urlBase + '/Login.' + extension;
  console.log(url);

  var xhr = new XMLHttpRequest();

  xhr.open("POST", url, false);
  xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");

  try
  {
    xhr.send(jsonPayload);
    
    
    var jsonObject = JSON.parse( xhr.responseText );

    userId = jsonObject.UserID;

    if( userId < 1 )
    {
       console.log("Incorrect Username/Password");
      //document.getElementById("loginResult").innerHTML = "Inncorrect Username/Password";
      return;
    }

    //Change to match variables in API
    login = jsonObject.UserName;
    
    // Go to home page (taskmanager.php)
    if(typeof(Storage)!=="undefined")
    {
      sessionStorage.setItem('userID', userId);
      sessionStorage.setItem('tempUsername', login);
      window.location.href = "/taskmanager.php";
    }
    else
    {
      console.log("ERROR: Session storage not supported");
    }
    
  }
  catch(err)
  {
    alert("Incorrect Username/Password");
  }
    
} // End of the doLogin function

function doTest()
{      
      //Reformat full Date to format that database will recognize
      var curDate = new Date();
      curDate = formatDate(curDate);
      console.log(curDate);
          
      // Convert to json string to pass to API
      var jsonPayload = '{"UserId" : "' + userId + '", "Date" : "' + curDate + '"}';
      var url = urlBase + '/RetrieveTaskID.' + extension;
      console.log(url);

      var xhr = new XMLHttpRequest();

      xhr.open("POST", url, false);
      xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");
  
      console.log(jsonPayload);

      try
      {
         xhr.send(jsonPayload);
         
         console.log("Sent!");
    
         var jsonObject = JSON.parse( xhr.responseText );

         console.log(jsonObject);
    
      }
      catch(err)
      {
         console.log("Retrieve Task error");
      }
}//End of doTest()

function formatDate(date) 
{
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

function displayInitial()
{
    userId = sessionStorage.getItem('userID');
    var displayLogin = "Logged in as " + sessionStorage.getItem('tempUsername');
    document.getElementById("loggedInAs").innerHTML = displayLogin;
    sessionStorage.removeItem('tempUsername');
   
    //Get current date
    fullDate[0] = new Date();
    displayDate(fullDate[0], 0);
    
    fullDate[1] = new Date();
    fullDate[1].setDate(fullDate[0].getDate()+1);
    displayDate(fullDate[1], 1);
    
    fullDate[2] = new Date();
    fullDate[2].setDate(fullDate[0].getDate()+2);
    displayDate(fullDate[2], 2);
    
    fullDate[3] = new Date();
    fullDate[3].setDate(fullDate[0].getDate()+3);
    displayDate(fullDate[3], 3);
    
    fullDate[4] = new Date();
    fullDate[4].setDate(fullDate[0].getDate()+4);
    displayDate(fullDate[4], 4);
    
    updateDaysAndDates();
      
    //console.log(document.getElementById("day1").innerHTML);
   
    //Call functions to display tasks for current day and next 4 days
    
    //doTest();
   
    doRetrieveDay(fullDate[0], 0);
    doRetrieveDay(fullDate[1], 1);
    doRetrieveDay(fullDate[2], 2);
    doRetrieveDay(fullDate[3], 3);
    doRetrieveDay(fullDate[4], 4);
    
    updateTasks();
}

//Begin displayDate function
function displayDate(objToday, position)
{
  weekday = new Array('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday');
  dayOfWeek = weekday[objToday.getDay()];
  dayOfMonth = ( objToday.getDate() < 10) ? '0' + objToday.getDate() : objToday.getDate();
  months = new Array('January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December');
  curMonth = months[objToday.getMonth()];
  curYear = objToday.getFullYear();
  
  day[position] = dayOfWeek;
  date[position] = curMonth + " " + dayOfMonth + ", " + curYear;
  
}

//Retrieve all the tasks for a specific day
function doRetrieveDay(fDate, position)
{
    var fullInnerHTML = '';
    
    fDate = formatDate(fDate);
    
    //API call: RetrieveTaskID
    var jsonPayload = '{"UserId" : "' + userId + '", "Date" : "' + fDate + '"}';
    var url = urlBase + '/RetrieveTaskID.' + extension;
    console.log(url);

    var xhr = new XMLHttpRequest();

    xhr.open("POST", url, false);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");
  
    console.log(jsonPayload);

    try
    {
       xhr.send(jsonPayload);
    
    
       var jsonObject = JSON.parse( xhr.responseText );

       console.log(jsonObject);
    
    }
    catch(err)
    {
       console.log("No tasks");
       fullInnerHTML = "<br><center>No tasks</center>";
    }
    
    
    //Create an array with task IDs
    var rows;
    var IdArray = [];
    
      try
      {
            rows = jsonObject.NumRows;
            IdArray 
            
            var string = jsonObject.TaskID;
            string = string.split(" ");

            for(var i = 0; i < rows; i++)
            {
              IdArray.push(string[i]);
            }
            
      }
      catch
      {
            rows = 0;
      }
       
    
    //Loop to string all tasks together 
    for(var i = 0; i < rows; i++)
    {
       var curInnerHTML = generateInnerHTML(IdArray[i]);
       fullInnerHTML += curInnerHTML;
    }
    
    innerHTMLforTasks[position] = fullInnerHTML;
    
}//End doRetrieveDay

function updateDaysAndDates()
{
    document.getElementById("day1").innerHTML = day[0];
    document.getElementById("day2").innerHTML = day[1];
    document.getElementById("day3").innerHTML = day[2];
    document.getElementById("day4").innerHTML = day[3];
    document.getElementById("day5").innerHTML = day[4];
         
    document.getElementById("date1").innerHTML = date[0];
    document.getElementById("date2").innerHTML = date[1];
    document.getElementById("date3").innerHTML = date[2];
    document.getElementById("date4").innerHTML = date[3];
    document.getElementById("date5").innerHTML = date[4];        
         
}

function updateTasks()
{
   document.getElementById("tasks1").innerHTML = innerHTMLforTasks[0];
   document.getElementById("tasks2").innerHTML = innerHTMLforTasks[1];
   document.getElementById("tasks3").innerHTML = innerHTMLforTasks[2];
   document.getElementById("tasks4").innerHTML = innerHTMLforTasks[3];
   document.getElementById("tasks5").innerHTML = innerHTMLforTasks[4];

}

function generateInnerHTML(taskId)
{   
    var taskName;
    
    //API call: RetrieveTaskInfo
    var jsonPayload = '{"UserId" : "' + userId + '", "taskId" : "' + taskId + '"}';
    var url = urlBase + '/RetrieveTaskInfo.' + extension;
    console.log(url);

    var xhr = new XMLHttpRequest();

    xhr.open("POST", url, false);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");
  
    console.log(jsonPayload);

    try
    {
       xhr.send(jsonPayload);
       
       console.log("Sent!");
    
       var jsonObject = JSON.parse( xhr.responseText );

       console.log(jsonObject);
       
       taskName = jsonObject.Title;
       
       var innerHTMLstring = "<a data-toggle=\"modal\" data-target=\"#viewTaskModal\" id=\"" + taskId + "\"><li class=\"list-group-item\" id=\"" + taskId + "\">" + taskName + "</li></a>";
       console.log(innerHTMLstring);
    
       return innerHTMLstring;
    
    }
    catch(err)
    {
       console.log("generateInnerHTML error");
    }
}


//Scroll right - display day after day in box 5 [index 4]
function doScrollRight()
{

   for(var i = 0; i <=3; i++)
   {
      innerHTMLforTasks[i] = innerHTMLforTasks[i+1];
      day[i] = day[i+1];
      date[i] = date[i+1];
      fullDate[i] = fullDate[i+1];
   }
   
   //Get date to be added
   fullDate[4] = new Date();
   fullDate[4].setDate(fullDate[3].getDate()+1);
   displayDate(fullDate[4],4);
   
   //Retrieve data for new day
   doRetrieveDay(fullDate[4], 4);
   
   console.log(day);
   console.log(date);
   console.log(fullDate);
   
   //Update
   updateDaysAndDates();
   updateTasks();

}//End of doScrollRight()

//Scroll left - display day before day in box 1 [index 0]
function doScrollLeft()
{
   for(var i = 3; i >=0; i--)
   {
      innerHTMLforTasks[i+1] = innerHTMLforTasks[i];
      day[i+1] = day[i];
      date[i+1] = date[i];
      fullDate[i+1] = fullDate[i];
   }
   
   //Get date to be added
   fullDate[0] = new Date();
   fullDate[0].setDate(fullDate[1].getDate()-1);
   displayDate(fullDate[0], 0);
   
   //Retrieve data for new day
   doRetrieveDay(fullDate[0], 0);
   
   console.log(day);
   console.log(date);
   console.log(fullDate);
   
   //Update
   updateDaysAndDates();
   updateTasks();
   
}//End of doScrollLeft()


$(document).on('show.bs.modal', '#viewTaskModal', function (e)
{

  console.log("New function");
  // do something...
      $("a").click(function(event) {
    
    var taskId = event.target.id;
    
    console.log(taskId);
        
        
    //Variable storage
    var taskName;
    var desc;
    var date;
    var startTime;
    var duration;
    var isComplete;
    
    //API call: RetrieveTaskInfo
    var jsonPayload = '{"UserId" : "' + userId + '", "taskId" : "' + taskId + '"}';
    var url = urlBase + '/RetrieveTaskInfo.' + extension;
    console.log(url);

    var xhr = new XMLHttpRequest();

    xhr.open("POST", url, false);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");
  
    console.log(jsonPayload);

    try
    {
       xhr.send(jsonPayload);
       
       console.log("Sent!");
    
       var jsonObject = JSON.parse( xhr.responseText );

       console.log(jsonObject);
       
       taskName = jsonObject.Title;
       desc = jsonObject.Description;
       date = jsonObject.Date;
       
       //Create modal innerHTML
       var taskNameInnerHTML = "<h2>" + taskName + "</h2>";
       var innerHTML = "<h4>" + desc + "<\h4>" + "<h4>" + date + "<\h4>";
       
       //Populate modal
       document.getElementById("taskName").innerHTML = taskNameInnerHTML;
       document.getElementById("detailsDisplay").innerHTML = innerHTML;

    }
    catch(err)
    {
       console.log("Modal population error -- API");
    }

    });
})

/*
//Display information in modal
$(document).ready(function() {
    $("a").click(function(event) {
    
    var taskId = event.target.id;
    
    console.log(taskId);
        
        
    //Variable storage
    var taskName;
    var desc;
    var date;
    var startTime;
    var duration;
    var isComplete;
    
    //API call: RetrieveTaskInfo
    var jsonPayload = '{"UserId" : "' + userId + '", "taskId" : "' + taskId + '"}';
    var url = urlBase + '/RetrieveTaskInfo.' + extension;
    console.log(url);

    var xhr = new XMLHttpRequest();

    xhr.open("POST", url, false);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");
  
    console.log(jsonPayload);

    try
    {
       xhr.send(jsonPayload);
       
       console.log("Sent!");
    
       var jsonObject = JSON.parse( xhr.responseText );

       console.log(jsonObject);
       
       taskName = jsonObject.Title;
       desc = jsonObject.Description;
       date = jsonObject.Date;
       
       //Create modal innerHTML
       var taskNameInnerHTML = "<h2>" + taskName + "</h2>";
       var innerHTML = "<h4>" + desc + "<\h4>" + "<h4>" + date + "<\h4>";
       
       //Populate modal
       document.getElementById("taskName").innerHTML = taskNameInnerHTML;
       document.getElementById("detailsDisplay").innerHTML = innerHTML;

    }
    catch(err)
    {
       console.log("Modal population error -- API");
    }

    //populate modal

    });
});
*/

// Begin doLogout function
function doLogout()
{

  console.log("Logging out!");
  sessionStorage.removeItem('userID');

  //Go back to the login page (index.php)


} // End doLogout function

// Select which element(s) are shown or hidden.
// hidden elements still remain on the page and can be targeted by other
// functions.
function hideOrShow(element, show=true, list=false){
  // Default is to show the element(s)
  var vis = "visible";
  var dis = "block";

  // To hide the element(s)
  if(!show){
    var vis = "hidden";
    var dis = "none";
  }

  // The element is a class, so there will be a list of elements
  if(list){
    var elementList = document.getElementsByClassName(element)
    for (i = 0; i < elementList.length; i++) {
        elementList[i].style.visibility = vis;
        elementList[i].style.display = dis;
    }
  }

  // element is identified by ID
  else{
    document.getElementById(element).style.visibility = vis;
    document.getElementById(element).style.display = dis;
  }
  document.getElementById("addButton").style.visibility = "visible";
} // End hideOrShow function

// Begind doShow function show a contact from the search function
// Select which screen is shown.
function doShow(pageName){

} // End doShow function

// Begin sha1 encryption method to encrypt the password, credit to Ashwin Ramaswami from stackexchange
function sha1(msg)
{
  function rotl(n,s) { return n<<s|n>>>32-s; };
  function tohex(i) { for(var h="", s=28;;s-=4) { h+=(i>>>s&0xf).toString(16); if(!s) return h; } };

  var H0=0x67452301, H1=0xEFCDAB89, H2=0x98BADCFE, H3=0x10325476, H4=0xC3D2E1F0, M=0x0ffffffff;
  var i, t, W=new Array(80), ml=msg.length, wa=new Array();
  msg += String.fromCharCode(0x80);
  while(msg.length%4) msg+=String.fromCharCode(0);
  for(i=0;i<msg.length;i+=4) wa.push(msg.charCodeAt(i)<<24|msg.charCodeAt(i+1)<<16|msg.charCodeAt(i+2)<<8|msg.charCodeAt(i+3));
  while(wa.length%16!=14) wa.push(0);
  wa.push(ml>>>29),wa.push((ml<<3)&M);
  for( var bo=0;bo<wa.length;bo+=16 ) {
    for(i=0;i<16;i++) W[i]=wa[bo+i];
    for(i=16;i<=79;i++) W[i]=rotl(W[i-3]^W[i-8]^W[i-14]^W[i-16],1);
    var A=H0, B=H1, C=H2, D=H3, E=H4;
    for(i=0 ;i<=19;i++) t=(rotl(A,5)+(B&C|~B&D)+E+W[i]+0x5A827999)&M, E=D, D=C, C=rotl(B,30), B=A, A=t;
    for(i=20;i<=39;i++) t=(rotl(A,5)+(B^C^D)+E+W[i]+0x6ED9EBA1)&M, E=D, D=C, C=rotl(B,30), B=A, A=t;
    for(i=40;i<=59;i++) t=(rotl(A,5)+(B&C|B&D|C&D)+E+W[i]+0x8F1BBCDC)&M, E=D, D=C, C=rotl(B,30), B=A, A=t;
    for(i=60;i<=79;i++) t=(rotl(A,5)+(B^C^D)+E+W[i]+0xCA62C1D6)&M, E=D, D=C, C=rotl(B,30), B=A, A=t;
    H0=H0+A&M;H1=H1+B&M;H2=H2+C&M;H3=H3+D&M;H4=H4+E&M;
  }
  return tohex(H0)+tohex(H1)+tohex(H2)+tohex(H3)+tohex(H4);
} // End sha1 function