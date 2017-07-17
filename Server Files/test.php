<?php
	require "Connection.php";
	$class_id = 'A';
	$inst_id = 'inst_1';
	$years = '5';
	//$today = date("Y/m/d");
	//$end = date('Y-m-d', strtotime('+60 years'));
	$qry = "select empe_name from empe_table where (DATEADD(year,5,now()) >= DATEADD(year,60,doj));";
	
	$result = $conn->query($qry);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
    }
} else {
    echo "0 results";
}
?>