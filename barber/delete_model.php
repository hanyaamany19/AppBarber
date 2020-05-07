<?php
	include("config.php");

		$id = $_POST['id'];

		$query = "DELETE FROM model WHERE id='$id')";
		$sql = mysqli_query($db, $query);

		if($query){

		}else{
			die("Gagal menyimpan perubahan...");
		}
?>