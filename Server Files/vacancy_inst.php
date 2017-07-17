<? php
	require "Conection.php"
	$specilisation = $_POST['specilisation'];
	$inst_list = mysqli_query("select inst_name from inst_table;");
	jsonArray = array();
	jasonArray['packages'] = array();
	while($row = mysqli_fetch_array($inst_list))
	{
		$inst_name = $row['inst_name']
		$result = mysqli_query("select add(strength) as strength from hr_table where inst_name = '$inst_name' and specialisation = '$specilisation';");
		$result1 = mysqli_query("select count(empe_id) as filled from empe_table where inst_name = '$inst_name' and specilisation = '$specilisation';");
		$row = mysqli_fetch_array($result);
		$row1 = mysqli_fetch_array($result1);
		$package['inst_name'] = $inst_name;
		
		$package['strength'] = $row['strength'];
		$package['filled'] = $row['filled'];
		$package['vacancy'] = $row['strength']-$row['filled'];
		push_array($jsonArray['packages'],package);
	}
	echo json_encode($jsonArray);
