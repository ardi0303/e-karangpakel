<?php
include_once('connection.php');
$username =$_POST['username'];
$total=$_POST['total'];
$pembayaran=$_POST['pembayaran'];
$kembalian=$_POST['kembalian'];
$tanggal=date("Y-m-d");
$insert = "INSERT INTO mjual(kd_kons,tgl_jual,total_biaya,pembayaran,kembalian) VALUES('$username','$tanggal','$total','$pembayaran','$kembalian')";
$exeinsert = mysqli_query($koneksi,$insert);
$response = array();
if($exeinsert)
{
$response['code'] =1;
$response['message'] = "Success ! Pemesanan dibuat";
}
else
{
$response['code'] =0;
$response['message'] = "Failed ! Pemesanan gagal dibuat";
}
echo json_encode($response);

?>