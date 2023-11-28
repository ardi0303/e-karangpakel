<?php
include "connection.php";
if($_POST['action'] = 'tampil_data'){
$query = mysqli_query($koneksi, "SELECT * FROM barang");
$array = [];
while($row = mysqli_fetch_array($query)){
    array_push($array, [
        'kd_brg' => $row['kd_brg'],
        'nm_brg' => $row['nm_brg'],
        'harga' => $row['harga'],
        'deskripsi' => $row['deskripsi'],
        'stok' => $row['stok'],
        'gambar' => "https://ppb13790.000webhostapp.com/uploads/".$row['gambar']
    ]);
}
echo json_encode(['Databarang' => $array]);
}
