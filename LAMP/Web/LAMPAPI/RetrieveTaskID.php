<?php

	class taskStruct
	{
		public $id;
		public $time;
	}

	function sortArray($array, $size)
	{
                $temp = new taskStruct();
		for ($i = 0; $i < $size - 1; $i++)
                {
                        echo($i);
                        echo($size);
                        for ($j = $i + 1; j < $size; $j++)
                        {
                                if ($array[i] > $array[j])
                                {
                                $temp = $array[j];
                                $array[j] = $array[i];
                                $array[i] = $temp;
                                }
                        }
                }
	}

	$inData = getRequestInfo();

	$taskId = "";
	$numRows = 0;

    $tasks = array();

	$date = $inData["Date"];
	$userId = $inData["UserId"];

	$conn = new mysqli("fdb21.awardspace.net", "2738589_webapp", "Webdev999", "2738589_webapp");
	if ($conn->connect_error)
	{
		returnWithError( $conn->connect_error );
	}
	else
	{
    	$sql = "SELECT TaskID, StartTime from Tasks WHERE Date LIKE '%" . $date . "%' and UserID='" . $userId . "'";
        $result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			while($row = $result->fetch_assoc())
			{
				$task = new taskStruct();
				$task->id   = $row["TaskID"];
				$task->time = $row["StartTime"];

				array_push($tasks, $task);

				$numRows++;
                        }

                        $temp = new taskStruct();
                        for ($i = 0; $i < $numRows - 1; $i++)
                        {
                                for ($j = $i + 1; $j < $numRows; $j++)
                                {
                                        if ($tasks[$i]->time > $tasks[$j]->time)
                                        {
                                                $temp = $tasks[$j];
                                                $tasks[$j] = $tasks[$i];
                                                $tasks[$i] = $temp;
                                        }
                                }
                        }

            for($i = 0; $i < $numRows; $i++)
            {
                    $taskId .= $tasks[$i]->id . " ";
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
