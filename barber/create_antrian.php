<?php
	include("config.php");

		$name = $_POST['name'];
		$no_antrian = $_POST['no_antrian'];
		$model = $_POST['model'];
		$layanan = $_POST['layanan'];
		$time = $_POST['time'];

		$query = "INSERT INTO antrian (id, name, no_antrian, model, layanan, time) VALUES(null,'$name', '$no_antrian', '$model', '$layanan', '$time')";
		$sql = mysqli_query($db, $query);

		if($query){

		}else{
			die("Gagal menyimpan perubahan...");
		}
?>