<?php
include "koneksi_ip.php";
if (isset($_GET['id'])) {
$kode = $_GET['id'];
} else {
die ("Error. No Id Selected! ");
}
$query = "SELECT * FROM konsumen WHERE id='$kode'";
$sql = mysqli_query ($conn,$query);
$hasil = mysqli_fetch_array ($sql);
$kode = $hasil['id'];
$username = stripslashes ($hasil['username']);
$name = $hasil['name'];

//proses edit konsumen
if (isset($_POST['Edit'])) {
	$kode = $_POST['hidkonsumen'];
	$username = addslashes (strip_tags ($_POST['username']));
	$name = $_POST['name'];
	
//update konsumen
$query = "UPDATE konsumen SET username='$username', name='$name' WHERE id='$kode'"; //3
$sql = mysqli_query ($conn,$query);
if ($sql) {
	echo "<h2><font color=blue>konsumen telah berhasil diedit</font></h2>";
} else {
	echo "<h2><font color=red>konsumen gagal diedit</font></h2>";
}
echo "<meta http-equiv='refresh' content='0;URL=index_admin.php?page=displaykonsumen'>";
}
if (isset($_POST['Reset'])) {
echo "<meta http-equiv='refresh' content='0;URL=index_admin.php?page=displaykonsumen'>";
}

?>
<html>
<head><title>Edit Konsumen</title>
</head>
<body>
<FORM ACTION="" METHOD="POST" NAME="input" enctype="multipart/form-data">
<table cellpadding="0" cellspacing="0" border="0" width="700">
<tr>
<td align="center" colspan="2"><h2>Update Konsumen</h2></td>
</tr>
<tr>
<td width="200">ID Konsumen</td>
<td>: <?php echo $kode ?></td>
</tr>
<tr>
<td>Username</td>
<td>: <input type="text" name="username" size="30" value="<?php echo $username ?>"></td>
</tr>
<tr>
<td>Name</td>
<td>: <input type="text" name="name" size="10" value="<?php echo $name?>"></td>
</tr>

<tr>
<td>&nbsp;</td>
<td>&nbsp;&nbsp;
<input type="hidden" name="hidkonsumen" value="<?=$kode?>">
<input type="submit" name="Edit" value="Edit Konsumen">&nbsp;
<input type="submit" name="Reset" value="Cancel"></td>
</tr>
</table>
</FORM>
</body>
</html>