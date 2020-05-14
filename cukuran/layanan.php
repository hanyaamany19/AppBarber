<?php

include("config.php");

$sql = "SELECT * FROM layanan";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('id' => $row['id'],
    'nama' => $row['nama'],
    'harga' => $row['harga'],
    'keterangan' => $row['keterangan']
));
}

echo json_encode(array("result" => $result));

?>