<?php
	require "Connection.php";
	$username = $_POST['UserName'];
	$password = $_POST['Password'];
	$gender = $_POST['Gender'];
	$qry = "insert into TblLogin(UserName,Password,Gender) values('$username','$password','$gender');";
	if($conn->query($qry) === TRUE) {
		echo mysqli_affected_rows($conn);
	} else {
		echo "Data not inserted";
	}
?>