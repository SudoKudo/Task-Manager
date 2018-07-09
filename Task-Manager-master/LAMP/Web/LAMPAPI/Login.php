<?php

	$inData = getRequestInfo();

	$id = 0;
	$userName = "";
	$password = "";

	$conn = new mysqli("fdb21.awardspace.net", "2738589_webapp", "Webdev999", "2738589_webapp");
	if ($conn->connect_error)
	{
		returnWithError( $conn->connect_error );
	}
	else
	{
		$sql = "SELECT UserID,UserName,Password FROM UserInfo where UserName='" . $inData["uName"] . "' and Password='" . $inData["pWord"] . "'";
		$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			$row = $result->fetch_assoc();
			$userName = $row["UserName"];
			$password = $row["Password"];
			$id = $row["UserID"];
		}
		else
		{
			returnWithError( "No Records Found" );
		}
		$conn->close();
	}

	returnWithInfo($userName, $password, $id );

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
		$retValue = '{"UserID":0,"UserName":"","Password":"","error":"' . $err . '"}';
		sendResultInfoAsJson( $retValue );
	}

	function returnWithInfo( $username, $password, $id )
	{
		$retValue = '{"UserID":' . $id . ',"UserName":"' . $username . '","Password":"' . $password . '","error":""}';
		sendResultInfoAsJson( $retValue );
	}

?>