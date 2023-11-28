<?php
$servername = "localhost";
$username = "id20094050_ardi13790";
$password = "z{2)Hz{eL{w?cigb";
$dbname = "id20094050_dbjual"; 
// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn)
  {
  die("Failed to connect to MySQL: " . mysqli_connect_error());
  }
  //echo "Connected successfully";
?>