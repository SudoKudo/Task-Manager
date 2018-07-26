<?php

	$inData = getRequestInfo();

	$taskId = $inData["taskId"];
	$title = "";

	$conn = new mysqli("fdb21.awardspace.net", "2738589_webapp", "Webdev999", "2738589_webapp");
	if ($conn->connect_error)
	{
		returnWithError( $conn->connect_error );
	}
	else
	{
		//Get task name
		$sql = "SELECT Title FROM Tasks WHERE TaskID='" . $inData["taskId"] . "'";
		$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			$row = $result->fetch_assoc();
               $title = $row["Title"];
		}
		else
		{
			returnWithError( "No Records Found" );
		}

		//Delete
		$sql = "DELETE FROM Tasks WHERE UserName='" . $taskId . "'";
		$result = $conn->query($sql);

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
		$retValue = '{"TaskID":0,"error":"' . $err . '"}';
		sendResultInfoAsJson( $retValue );
	}

	function returnWithInfo( $username, $password, $id )
	{
		$retValue = '{"Deleted":' . $title . '","error":""}';
		sendResultInfoAsJson( $retValue );
	}

?>