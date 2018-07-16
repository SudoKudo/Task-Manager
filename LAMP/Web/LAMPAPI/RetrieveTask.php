<?php

	$inData = getRequestInfo();

	$id = 0;
    $title = ;
    $description = ;
    $duration = ;
    $date = ;
    $isComplete = ;

	$conn = new mysqli("fdb21.awardspace.net", "2738589_webapp", "Webdev999", "2738589_webapp");
	if ($conn->connect_error)
	{
		returnWithError( $conn->connect_error );
	}
	else
	{
		$sql = "SELECT Title, Description, Duration, 'Date', IsComplete, UserID FROM Tasks where TaskID='" . $inData["TaskId"] . "'";
		$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			$row = $result->fetch_assoc();
			$id = $row["UserID"];
            $title = $row["Title"];
            $description = $row["Description"];
            $duration = $row["Duration"];
            $date = $row["Date"];
            $isComplete = $row["IsComplete"];

		}
		else
		{
			returnWithError( "No Records Found" );
		}
		$conn->close();
	}

	returnWithInfo($id, $title, $description, $duration, $date, $isComplete);

	function getRequestInfo()
	{
		return json_decode(file_get_contents('php://input'), true);
	}

	function sendResultInfoAsJson( $obj )
	{
		header('Content-type: application/json');
		echo $obj;
	}

	function returnWithError( $err )
	{
		$retValue = '{"UserID":0,"Title":"","Description":"","Duration":"","Date":"","isComplete":"","error":"' . $err . '"}';
		sendResultInfoAsJson( $retValue );
	}

	function returnWithInfo($id, $title, $description, $duration, $date, $isComplete)
	{
		$retValue = '{"UserID":' . $id . ',"Title":"' . $title . '","Description":"' . $description . '","Duration":"' . $duration . '","Date":"' . $date . '","isComplete":"' . $isComplete . '","error":""}';
		sendResultInfoAsJson( $retValue );
	}

?>
