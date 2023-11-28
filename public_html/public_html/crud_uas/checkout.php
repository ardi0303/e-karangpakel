<?php
include_once "connection.php";

$total = $_POST['total'];
$username = $_POST['username'];
$imagepath = '';
$status = 'Belum Bayar';
$MasukSQL = "INSERT INTO penjualan (image_path, username, total, status, resi) values ('$imagepath', '$username', '$total', '$status', '')";
if(mysqli_query($koneksi, $MasukSQL)){
    echo "Silahkan bayar pesanan";
}else{
    echo "Please Try Again";
}

if(isset($username)){
    $add = mysqli_query($koneksi, "DELETE FROM cart WHERE username = '$username' ");
}else{
echo "Please Try Again";}


mysqli_close($koneksi);
?>