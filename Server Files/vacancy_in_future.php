<? php
	require "Connection.php";
	$spec = $_POST['specilisation'];
	$months = $_POST['months'];
	$inst_list=mysqli_qurey("slect inst_name from inst_table");
	jsonArray = array();
	jsonArray['packages'] = array();
	while($row = mysqli_fetch_array($inst_list))
	{
		$inst_name = $row['inst_name'];
		$result = mysqli_query("select count(empe_id) as vacancy from empe_table where DATEADD(dob,year,58) <= DATEADD(now(),month,$months) and inst_name = '$inst_name' and specilisation = '$spec';");
		$data = mysqli_fetch_array($result);
		package = array();
		package['inst_name'] = $inst_name;
		package['specification'] = $spec;
		package['vacancy'] = data[vacancy];
		push_array(jsonArray['packages'],package);
		echo json_encode($jsonArray);
	}
?>
