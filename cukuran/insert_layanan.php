<?php

include("config.php");

$nama = $_POST['nama'];
$harga = $_POST['harga'];
$keterangan = $_POST['keterangan'];

$sql = "INSERT INTO layanan VALUES ( NULL,'$nama' , '$harga', '$keterangan')";
$query = mysqli_query($db , $sql);

// apakah query update berhasil ?
if ($query) {
  // code...
} else {
  // kalau gagal tampilkan pesan
  die("Gagal menyimpan perubahan");
}