<?php
include "connection.php";

$username = $_POST['username'];
if($_POST['action'] = 'tampil_data'){

$query = mysqli_query($koneksi, "SELECT * FROM cart WHERE username = '$username'");
$array = [];
while($row = mysqli_fetch_array($query)){
    array_push($array, [
        'kd_cart' => $row['kd_cart'],
        'username' => $row['username'],
        'nm_brg' => $row['nm_brg'],
        'jml_beli' => $row['jml_beli']
    ]);
}
echo json_encode(['Databarang' => $array]);
}