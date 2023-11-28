<?php
include "koneksi_ip.php";
if (isset($_GET['id'])) {
$kode = $_GET['id'];
} else {
die ("Error. No Id Selected! ");
}
$query = "SELECT * FROM penjualan WHERE id_penjualan='$kode'";
$sql = mysqli_query ($conn,$query);
$hasil = mysqli_fetch_array ($sql);
$kode = $hasil['id_penjualan'];
$status= $hasil['Status'];
$resi = $hasil['resi'];

//proses edit barang
if (isset($_POST['Edit'])) {
	$kode = $_POST['hidjual'];
	$status = $_POST['status'];
	$resi = $_POST['resi'];
//update barang
$query = "UPDATE penjualan SET Status='$status', resi='$resi' WHERE id_penjualan ='$kode'"; //3
$sql = mysqli_query ($conn,$query);
echo "<meta http-equiv='refresh' content='0;URL=index_admin.php?page=displayjual'>";
}
if (isset($_POST['Reset'])) {
echo "<meta http-equiv='refresh' content='0;URL=index_admin.php?page=displayjual'>";
}
// 5

?>
<html>
<head><title>Edit Status dan Resi</title>
</head>
<body>
<FORM ACTION="" METHOD="POST" NAME="input" enctype="multipart/form-data">
<table cellpadding="0" cellspacing="0" border="0" width="700">
<tr>
<td align="center" colspan="2"><h2>Update Status dan Resi</h2></td>
</tr>
<tr>
<td width="200">Kode Barang</td>
<td>: <?php echo $kode ?></td>
</tr>
<tr>
<td>Status</td>
<td>: <input type="text" name="status" size="30" value="<?php echo $status ?>"></td>
</tr>
<tr>
<td>Resi</td>
<td>: <input type="text" name="resi" size="10" value="<?php echo $resi?>"></td>
</tr>

<td>&nbsp;</td>
<td>&nbsp;&nbsp;
<input type="hidden" name="hidjual" value="<?=$kode?>">
<input type="submit" name="Edit" value="Edit Penjualan">&nbsp;
<input type="submit" name="Reset" value="Cancel"></td>
</tr>
</table>
</FORM>
</body>
</html>