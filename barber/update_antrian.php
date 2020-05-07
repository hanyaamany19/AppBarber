<?php
	include("config.php");

		$name = $_POST['name'];
		$no_antrian = $_POST['no_antrian'];
		$model = $_POST['model'];
		$layanan = $_POST['layanan'];
		$time = $_POST['time'];

		$sql = "UPDATE antrian SET name='$name', no_antrian='$no_antrian', model='$model', layanan='$layanan', time='$time' WHERE id='$id'";
		$query = mysqli_query($db, $sql);

		if($query){

		}else{
			die("Gagal menyimpan perubahan...");
		}
?>