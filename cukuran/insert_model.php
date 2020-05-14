<?php

include("config.php");

$name = $_POST['name'];

$sql = "INSERT INTO model VALUES ( NULL,'$name')";
$query = mysqli_query($db , $sql);

// apakah query update berhasil ?
if ($query) {
  // code...
} else {
  // kalau gagal tampilkan pesan
  die("Gagal menyimpan perubahan");
}