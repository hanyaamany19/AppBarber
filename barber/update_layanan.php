<?php
	include("config.php");

		$name = $_POST['name'];
		$harga = $_POST['harga'];
		$keterangan = $_POST['keterangan'];

		$sql = "UPDATE layanan SET name='$name', harga='$harga', keterangan='$keterangan' WHERE id='$id'";
		$query = mysqli_query($db, $sql);

		if($query){

		}else{
			die("Gagal menyimpan perubahan...");
		}
?>