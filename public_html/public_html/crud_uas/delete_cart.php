<?php
include "connection.php";

$kdCart = $_POST['kdCart'];


if(isset($kdCart)){
    $add = mysqli_query($koneksi, "DELETE FROM cart WHERE kd_cart = '$kdCart' ");

    if($add){
        $pesan = "Data Berhasil Dihapus";
    }
    else {
        $pesan = "Data Gagal Dihapus";
    }

    echo json_encode(array(
        'status' => $pesan
    ));
}

?>