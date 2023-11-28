<?php
date_default_timezone_set('Asia/Jakarta');
	include "koneksi_ip.php"
?>
<HTML>
<HEAD>
<TITLE>Menampilkan Daftar Penjualan</TITLE>

<script language="javascript">
function tanya() {
if (confirm ("Apakah Anda yakin akan menghapus penjualan ini ?")) {
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
					<h3 class="page-header"><i class="fa fa-laptop"></i> Transactions</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="index_admin.php">Home</a></li>
						<li><i class="fa fa-laptop"></i>Penjualan</li>						  	
					</ol>
				</div>
			</div>              
			  <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                              Daftar Penjualan
                          </header>
                          <form name="cari" method="POST">
							Tanggal : <input type="date" name="tgl1" > s/d <input type="date" name="tgl2">
							<input type="submit" name="cari" value="cari">
						  </form>
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>
                                 <th><i class="icon_profile"></i> No. Nota</th>
                                 <th><i class="icon_mail_alt"></i> Tgl. Beli</th>
                                 <th><i class="icon_pin_alt"></i>Username Konsumen</th>
                                 <th><i class="icon_mobile"></i> Total Belanja</th>
                                 <th><i class="icon_mobile"></i>Status</th>
								 <th><i class="icon_calendar"></i>No Resi</th>
								 <th><i class="icon_calendar"></i>Bukti Pembayaran</th>
								 <th><i class="icon_calendar"></i>Action</th>
                              </tr>



<?php
if(isset($_POST["cari"]))
{	//date('Y-m-d', strtotime($tanggal))
	$tgl1=date('Y-m-d',strtotime($_POST["tgl1"]));
	$tgl2=date('Y-m-d',strtotime($_POST["tgl2"]));
	//echo "$tgl1";
	$query = "SELECT * FROM penjualan where date(tgl_beli)>=date('$tgl1') and date(tgl_beli)<=date('$tgl2') order by tgl_beli desc";
	if($tgl1=="1970-01-01")
	{
		$query = "SELECT * FROM penjualan order by tgl_beli desc";
	}
}
	else
	{
    $query = "SELECT * FROM penjualan order by tgl_beli desc";
	}
  $sql = mysqli_query ($conn,$query);

  //echo "<a href='tambahbarang.php'>Add</a>";
 	while ($hasil = mysqli_fetch_array ($sql)) {
		$kode = $hasil['id_penjualan'];
		$tgl_beli = stripslashes ($hasil['tgl_beli']);
		$username = stripslashes ($hasil['username']);
		$total = $hasil['total'];
		$status = $hasil['Status'];
		$resi = $hasil['resi'];
		$bukti = $hasil['image_path'];
	//tampilkan barang
		echo "<tr>
		<td align='center'>$kode</td>
		<td align='left' >$tgl_beli</td>
		<td align='left'>$username</td>
		<td align='left'>$total</td>
		<td align='left'>$status</td>
		<td align='left'>$resi</td>
		<td align='center'><img src='$bukti' width='50' hight='50'></td>";
		?>
		<td style="text-align: center">
			<div class="btn-group">
				<a class="btn btn-success" href="<?php echo "index_admin.php?page=editjual&id=$kode"?>"><i class="icon_check_alt2"></i></a>
				<a class="btn btn-danger" onClick='return tanya()' href="<?php echo "index_admin.php?page=hapusjual&id=$kode"?>"><i class="icon_close_alt2"></i></a>
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
