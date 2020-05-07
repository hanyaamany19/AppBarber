<?php
	include("config.php");

		$name = $_POST['name'];
		$harga = $_POST['harga'];
		$keterangan = $_POST['keterangan'];

		$query = "INSERT INTO layanan (id, name, harga, keterangan) VALUES(null,'$name', '$harga', '$keterangan')";
		$sql = mysqli_query($db, $query);

		if($query){

		}else{
			die("Gagal menyimpan perubahan...");
		}
?>