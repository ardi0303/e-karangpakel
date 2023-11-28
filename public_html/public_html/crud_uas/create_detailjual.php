<?php
include_once('connection.php');
$ambil=mysqli_query($koneksi,"SELECT no_nota FROM mjual ORDER BY no_nota DESC limit 1");
$row=mysqli_fetch_row($ambil);
$no_nota=$row[0];
$kode=$_POST['kode'];
$ambil=mysqli_query($koneksi,"SELECT harga,harga_beli FROM barang WHERE kd_brg='$kode'");
$row=mysqli_fetch_row($ambil);
$harga=$row[0];
$hargabeli=$row[1];
$jumlah=$_POST['jumlah'];
$total_harga=$harga*$jumlah;
$insert = "INSERT INTO detailjual(no_nota,kd_brg,hrg_brg,hrg_beli,jml_brg) 
VALUES('$no_nota','$kode','$harga','$hargabeli','$jumlah')";
$exeinsert = mysqli_query($koneksi,$insert);
$response = array();
if($exeinsert)
{
$response['code'] =1;
$response['message'] = "Success ! Detail pemesanan dibuat";
}
else
{
$response['code'] =0;
$response['message'] = "Failed ! Detail pemesanan gagal dibuat";
}
echo json_encode($response);

?>