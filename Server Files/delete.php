<?php
	require "Connection.php";
	$id = "5";
	$qry = "delete from TblLogin where id='$id';";
	if($conn->query($qry) === TRUE) {
		echo mysqli_affected_rows($conn);
	} else {
		echo "Data deletion failed";
	}
?>