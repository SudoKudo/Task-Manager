<?php
	$inData = getRequestInfo();
        
    $username = $inData["uName"];
    $password = $inData["pWord"];
    $email = $inData["email"];
    $firstName = $inData["firstName"];
    $lastName = $inData["lastName"];

	$conn = new mysqli("fdb21.awardspace.net", "2738589_webapp", "Webdev999", "2738589_webapp");
	
	if ($conn->connect_error) 
	{
		returnWithError( $conn->connect_error );
	} 
	else
	{
        //User prepare statement to protect against sql injection attacks
		$stmt = $conn->prepare("INSERT INTO UserInfo (UserName,Password, Email, FirstName, LastName) VALUES (?, ?, ?, ?, ?)");
        $stmt->bind_param("sssss", $username, $password, $email, $firstName, $lastName);
                
        $stmt->execute();
                
        $result = $stmt->get_result();
               
		if( $result != TRUE )
		{
			returnWithError( $conn->error );
		}
		$conn->close();
	}
	
	returnWithError("");
	
	function getRequestInfo()
	{
		return json_decode(file_get_contents('php://input'), true);
	}
	function sendResultInfoAsJson( $obj )
	{
		header('Content-Type: application/json');
                echo json_encode($obj);
	}
	
	function returnWithError( $err )
	{
		$retValue = '{"error":"' . $err . '"}';
		sendResultInfoAsJson( $retValue );
	}
	
?>