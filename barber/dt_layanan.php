<?php

include("config.php");

if($_SERVER['REQUEST_METHOD']=='GET') {

$sql = "SELECT * FROM layanan";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('id' => $row['id'],
    'name' => $row['name'],
    'harga' => $row['harga'],
    'keterangan' => $row['keterangan'],
));
}
echo json_encode($result);
  mysqli_close($db); 
}
?>