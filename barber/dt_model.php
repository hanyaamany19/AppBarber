<?php

include("config.php");

if($_SERVER['REQUEST_METHOD']=='GET') {

$query = "SELECT * FROM model";
$result = array();
$sql = mysqli_query($db, $query);
 
while($row = mysqli_fetch_array($sql)){
    array_push($result, array('id' => $row['id'],
    'name' => $row['name'],
    'photo' => $row['photo']
));
}
echo json_encode($result);
  mysqli_close($db); 
}
?>