<?php

	$inData = getRequestInfo();

	$taskId = 0;

	$conn = new mysqli("fdb21.awardspace.net", "2738589_webapp", "Webdev999", "2738589_webapp");
	if ($conn->connect_error)
	{
		returnWithError( $conn->connect_error );
	}
	else
	{
		$sql = "SELECT TaskID FROM Tasks where UserID='" . $inData["UserId"] . "' and Date='" . $inData["Date"] . "'";
		$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			$row = $result->fetch_assoc();
			$taskId = $row["UserID"];
		}
		else
		{
			returnWithError( "No Records Found" );
		}
		$conn->close();
	}

	returnWithInfo($taskId);

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
		$retValue = '{"UserID":0,"FirstName":"","LastName":"","Address1":"","Address2":"","City":"","State":"","Zip":"","PhoneNumber":"","Email":"","error":"' . $err . '"}';
		sendResultInfoAsJson( $retValue );
	}

	function returnWithInfo($taskId)
	{
		$retValue = '{"TaskID":"' . $taskId. '"}';
		sendResultInfoAsJson( $retValue );
	}

?>
