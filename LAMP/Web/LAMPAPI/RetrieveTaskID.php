<?php

	$inData = getRequestInfo();

	$taskId = "";
	$numRows = 0;

	$conn = new mysqli("fdb21.awardspace.net", "2738589_webapp", "Webdev999", "2738589_webapp");
	if ($conn->connect_error)
	{
		returnWithError( $conn->connect_error );
	}
	else
	{
		$sql = "SELECT TaskID FROM Tasks where UserID='" . $inData["UserId"] . "' and Date='" . $inData["Date"] . "'";
		$result = $conn->query($sql);
		$numRows = $result->num_rows;
		if ($result->num_rows > 0)
		{
			$row = $result->fetch_assoc();
			$taskId .= " ";
			$taskId .= $row["TaskID"];
		}
		else
		{
			returnWithError( "No Records Found" );
		}
		$conn->close();
	}

	returnWithInfo($taskId, $numRows);

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
		$retValue = '{"TaskID":0",""NumRows":0","error":"' . $err . '"}';
		sendResultInfoAsJson( $retValue );
	}

	function returnWithInfo( $taskId, $numRows )
	{
		$retValue = '{"TaskID":"' . $taskId . ',"NumRows":"' . $numRows .'","error":""}';
		sendResultInfoAsJson( $retValue );
	}

?>
