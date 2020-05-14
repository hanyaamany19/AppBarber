<?php

include("config.php");

$sql = "SELECT * FROM model";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('id' => $row['id'],
    'name' => $row['name']
));
}

echo json_encode(array("result" => $result));

?>