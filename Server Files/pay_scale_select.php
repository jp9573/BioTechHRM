<?php
	require "Connection.php";

	$qry = "select da,hra,ma,ta from pay_scale;";
	$JsonArray = array();
	$result = mysqli_query($conn,$qry);
	if(mysqli_num_rows($result) > 0) {		
		$JsonArray["Packages"] = array();
		while($row = mysqli_fetch_array($result)) {		
			$package = array();
			$package['da'] = $row['da'];
			$package['hra'] = $row['hra'];
			$package['ma'] = $row['ma'];
			$package['ta'] = $row['ta'];
			array_push($JsonArray['Packages'], $package);
		}				
		echo json_encode($JsonArray);
	} else {
		echo "You have not correct privileges";
	}
?>	