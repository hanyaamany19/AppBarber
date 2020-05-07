<?php

include("config.php");

if($_SERVER['REQUEST_METHOD']=='GET') {

$sql = "SELECT * FROM antrian";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('id' => $row['id'],
    'name' => $row['name'],
    'no_antrian' => $row['no_antrian'],
    'model' => $row['model'],
    'layanan' => $row['layanan'],
    'time' => $row['time']
));
}
echo json_encode($result);
  mysqli_close($db); 
}
?>