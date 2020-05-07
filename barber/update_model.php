<?php
	include("config.php");

		$name = $_POST['name'];
		$photo = $_POST['photo'];

		$sql = "UPDATE model SET name='$name', photo='$photo' WHERE id='$id'";
		$query = mysqli_query($db, $sql);

		if($query){

		}else{
			die("Gagal menyimpan perubahan...");
		}
?>