<?php
include_once "connection.php";

$username = $_POST['username'];
$nm_brg = $_POST['nm_brg'];
$jml_beli = $_POST['jml_beli'];

$sql = "SELECT * FROM cart WHERE username = '$username' AND nm_brg = '$nm_brg'";
$check = mysqli_query($koneksi, $sql);
if(mysqli_num_rows($check)){
	$updatecart = "UPDATE cart SET jml_beli = jml_beli+'$jml_beli' WHERE username = '$username' AND nm_brg = '$nm_brg'";
    if(mysqli_query($koneksi, $updatecart)){
        echo "Keranjang diupdate";
    }
}else{
	$insertdata = "INSERT INTO cart(username, nm_brg, jml_beli) VALUES ('$username', '$nm_brg', '$jml_beli')";
	if(mysqli_query($koneksi, $insertdata)){
		echo "Produk masuk keranjang";
	}else{
		echo "Error";
	} 
}


$add = mysqli_query($koneksi, "UPDATE barang SET stok = stok-1 WHERE nm_brg = '$nm_brg' ");


?>	