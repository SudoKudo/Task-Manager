<?php
	$inData = getRequestInfo();
        
    $userID = $inData["userID"];
	$Title = $inData["title"];
    $description = $inData["description"];
    $dateCreated = $inData["dateCreated"];
    $duration = $inData["duration"];
	$startTime = $inData["startTime"];
    $priority = $inData["priority"];
	$date = $inData["date"];
	$completion = $inData["completion"];
	$conn = new mysqli("fdb21.awardspace.net", "2738589_webapp", "Webdev999", "2738589_webapp");
	
	if ($conn->connect_error) 
	{
		returnWithError( $conn->connect_error );
	} 
	else
	{
        //User prepare statement to protect against sql injection attacks
		$stmt = $conn->prepare("INSERT INTO Tasks (UserID, Title, Description, Duration, StartTime, Priority, Date, IsComplete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        $stmt->bind_param("sssssssss", $userID, $Title, $description, $duration, $startTime, $priority, $date, $completion);
                
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