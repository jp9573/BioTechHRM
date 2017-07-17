<?php
	require "Connection.php";

	$qry = "select Id,Username,Password,Gender from TblLogin;";
	$JsonArray = array();
	$result = mysqli_query($conn,$qry);
	if(mysqli_num_rows($result) > 0) {		
		$JsonArray["Packages"] = array();
		while($row = mysqli_fetch_array($result)) {		
			$package = array();
			$package['Id'] = $row['Id'];
			$package['Username'] = $row['Username'];
			$package['Password'] = $row['Password'];
			$package['Gender'] = $row['Gender'];
			array_push($JsonArray['Packages'], $package);
		}				
		echo json_encode($JsonArray);
	} else {
		echo "You have not correct privileges";
	}
?>	