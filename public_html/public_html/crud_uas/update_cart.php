<?php
include "connection.php";

$nmBrg = $_POST['nmBrg'];
$jmlBeli = $_POST['jmlBeli'];

if(isset($nmBrg) && $nmBrg == $_POST['nmBrg']){
    $add = mysqli_query($koneksi, "UPDATE cart SET 
                                        jml_beli = '$jmlBeli' WHERE nm_brg = '$nmBrg' ");

    if($add){
        $pesan = "Data berhasil Diupdate";
    }
    else {
        $pesan = "Data gagal Diupdate";
    }

    echo json_encode(array(
        'status' => $pesan
    ));
}

?>