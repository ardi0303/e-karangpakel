<?php
include "koneksi_ip.php";
if (isset($_GET['id'])) {
$kode = $_GET['id'];
} else {
die ("Error. NO Id Selected! ");
}
?>
<html>
<head><title>Delete Penjualan</title>
</head>
<body>

<?php
//proses delete barang
if (!empty($kode) && $kode != "") {
$query = "DELETE FROM penjualan WHERE id_penjualan='$kode'";
$sql = mysqli_query ($conn,$query);
if ($sql) {
echo "<h2><font color=blue>Penjualan telah berhasil dihapus</font></h2>";
} else {
echo "<h2><font color=red>Penjualan gagal dihapus</font></h2>";
}
echo "Klik <a href='index_admin.php?page=displayjual'>di sini</a> untuk kembali ke halaman display barang";
} else {
die ("Access Denied");
}
?>
</body>
</html>