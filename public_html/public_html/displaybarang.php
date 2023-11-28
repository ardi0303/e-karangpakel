<?php
	include "koneksi_ip.php"
?>
<HTML>
<HEAD>
<TITLE>Menampilkan Daftar Barang</TITLE>

<script language="javascript">
function tanya() {
if (confirm ("Apakah Anda yakin akan menghapus barang ini ?")) {
	return true;
} else {
	return false;
}
}
</script>
</HEAD>
<BODY>
<div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-laptop"></i> Masters</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="index_admin.php">Home</a></li>
						<li><i class="fa fa-laptop"></i>Barang</li>						  	
					</ol>
				</div>
			</div>   
			<td>
				<div class="btn-group">
					<a class="btn btn-primary" href="<?php echo "index_admin.php?page=tambahbarang"?>"><i class="icon_plus_alt2"></i></a>
				</div>
			</td>     
			<br>
			<br>      
			  <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                              Daftar Barang
                          </header>
						
                          
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>
                                 <th style="text-align:center"><i class="icon_profile"></i> Kode</th>
                                 <th style="text-align:center"><i class="icon_mail_alt"></i> Nama Barang</th>
								 <th style="text-align:center"><i class="icon_calendar"></i> Harga</th>
								 <th style="text-align:center"><i class="icon_calendar"></i> Deskripsi</th>
								 <th style="text-align:center"><i class="icon_calendar"></i> Stok</th>
								 <th style="text-align:center"><i class="icon_profile"></i> Gambar</th>
                                 <th style="text-align:center"><i class="icon_cogs"></i> Action</th>
                              </tr>



<?php
    $query = "SELECT * FROM barang order by kd_brg";
  $sql = mysqli_query ($conn,$query);
  //echo "<a href='tambahbarang.php'>Add</a>";
 	while ($hasil = mysqli_fetch_array ($sql)) {
		$kode = $hasil['kd_brg'];
		$nama = stripslashes ($hasil['nm_brg']);
		$harga = stripslashes ($hasil['harga']);
		$deskripsi = $hasil['deskripsi'];
		$stok= $hasil['stok'];
		$gambar=$hasil['gambar'];
	//tampilkan barang
		echo "<tr>
		<td align='center'>$kode</td>
		<td align='center' >$nama</td>
		<td align='center'>$harga</td>
		<td align='center'>$deskripsi</td>
		<td align='center'>$stok</td>
		<td align='center'><img src='uploads/$gambar' width='50' hight='50'></td>";
		?>
		<td style="text-align: center">
			<div class="btn-group">
				<a class="btn btn-success" href="<?php echo "index_admin.php?page=editbarang&id=$kode"?>"><i class="icon_check_alt2"></i></a>
				<a class="btn btn-danger" onClick='return tanya()' href="<?php echo "index_admin.php?page=hapusbarang&id=$kode"?>"><i class="icon_close_alt2"></i></a>
			</div>
		</td>
		</tr>
	        <?php } ?>
		</tbody>
                        </table>
                      </section>
                  </div>
              </div>
		
		
</BODY>
</HTML>
