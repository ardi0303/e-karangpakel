<?php
include_once "connection.php";

$name = $_POST['name'];
$username = $_POST['username'];
$password = md5($_POST['password']);

$sql = "SELECT * FROM konsumen WHERE username = '$username'";
$check = mysqli_query($koneksi, $sql);
if(mysqli_num_rows($check)){
	echo "User sudah ada! Silahkan Login";
}else{
	$insertdata = "INSERT INTO konsumen(username, name, password) VALUES ('$username', '$name', '$password')";
	if(mysqli_query($koneksi, $insertdata)){
		echo "User berhasil registrasi";
	}else{
		echo "registration Fail: ERROR";
	} 
}
?>	