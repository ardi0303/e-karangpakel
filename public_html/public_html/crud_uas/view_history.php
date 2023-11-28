<?php
include "connection.php";

date_default_timezone_set('Asia/Jakarta');

$username = $_POST['username'];
if($_POST['action'] = 'tampil_data'){

$query = mysqli_query($koneksi, "SELECT * FROM penjualan WHERE username = '$username'");
$array = [];
while($row = mysqli_fetch_array($query)){
    array_push($array, [
        'id_penjualan' => $row['id_penjualan'],
        'tgl_beli' => $row['tgl_beli'],
        'username' => $row['username'],
        'total' => $row['total'],
        'Status'=> $row['Status'],
        'resi' => $row['resi']
    ]);
}
echo json_encode(['Databarang' => $array]);
}