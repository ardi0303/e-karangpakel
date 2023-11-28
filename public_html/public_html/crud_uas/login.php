<?php
include_once "connection.php";

$username = $_POST['username'];
$password = MD5($_POST['password']);

$sql = "SELECT * FROM konsumen WHERE username = '$username' AND password = '$password'";
$result = array();

$response = mysqli_query($koneksi, $sql);
if(mysqli_num_rows($response)==1){
	$result['status'] = 'success';
	echo json_encode($result);
	mysqli_close($koneksi);
}else{
	$result['status'] = 'error';
	echo json_encode($result);
	mysqli_close($koneksi);
}

?>