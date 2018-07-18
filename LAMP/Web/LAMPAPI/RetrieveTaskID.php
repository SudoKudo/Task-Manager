<?php

	$inData = getRequestInfo();

	$taskId = "";
	$numRows = 0;

	$date = "2018-07-17";
	$userID = 138;

	$conn = new mysqli("fdb21.awardspace.net", "2738589_webapp", "Webdev999", "2738589_webapp");
	if ($conn->connect_error)
	{
		returnWithError( $conn->connect_error );
	}
	else
	{
		$sql = "SELECT TaskID from Tasks WHERE Date LIKE '%" . $date . "%' and UserID='" . $userID . "'";
        $result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			while($row = $result->fetch_assoc())
			{
   				if( $numRows > 0 )
                {
                	$taskId .= " ";
                }

                $numRows++;
                $taskId .= $row["TaskID"];
        	}

			$taskId = '"' . $taskId . '"';
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
		$retValue = '{"TaskID":"","NumRows":0,"error":"' . $err . '"}';
		sendResultInfoAsJson( $retValue );
	}

	function returnWithInfo( $taskId, $numRows )
	{
		$retValue = '{"TaskID":' . $taskId . ',"NumRows":"' . $numRows .'","error":""}';
		sendResultInfoAsJson( $retValue );
	}

?>
