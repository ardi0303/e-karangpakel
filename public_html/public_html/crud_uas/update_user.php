<?php
include_once "connection.php";

$username = $_POST['username'];
$password = md5($_POST['password']);
$oldUsername = $_POST['oldUsername'];

$sql = "SELECT * FROM konsumen WHERE username = '$oldUsername'";
$check = mysqli_query($koneksi, $sql);
if(mysqli_num_rows($check) > 0){
    $result = "UPDATE konsumen SET username = '$username', password = '$password' WHERE username = '$oldUsername'";
    if(mysqli_query($koneksi, $result)){
        echo "Update User Sukses";
    }else{
        echo "Update error";
    }
}else{
    echo "Username tidak ditemukan";
}
    



?>