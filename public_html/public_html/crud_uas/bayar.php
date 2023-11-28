<?php
include_once "connection.php";

$ImageData = $_POST['image_data'];
$total = $_POST['total'];
$username = $_POST['username'];
$nama_baru = round(microtime(true));

$ImagePath = "uploads/$nama_baru.jpg";
$ServerURL = "crud_uas/$ImagePath";

$sql = "SELECT * FROM penjualan";
$check = mysqli_query($koneksi, $sql);
if(mysqli_num_rows($check) > 0){
    $InsertSQL = "UPDATE penjualan SET image_path = '$ServerURL', status='Lunas' WHERE username = '$username' AND total = '$total' AND status = 'Belum Bayar'";
    if(mysqli_query($koneksi, $InsertSQL)){
        file_put_contents($ImagePath,base64_decode($ImageData));
        echo "Bukti Pembayaran Berhasil Diupload.";
    }else{
        echo "Riwayat Pemesanan Tidak Ada.";
    }
}

?>