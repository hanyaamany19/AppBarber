<?php
	include("config.php");

		$name = $_POST['name'];
		$photo = $_POST['photo'];

		$query = "INSERT INTO model (id, name, photo) VALUES(null,'$name', '$photo')";
		$sql = mysqli_query($db, $query);

		if($query){

		}else{
			die("Gagal menyimpan perubahan...");
		}
?>