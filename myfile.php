<?php
if($_SERVER['REQUEST_METHOD']== 'POST')
{
	$image = $_POST['image'];
	$name = $_POST['name'];
	
	$id = rand(100,1000);
	$imageName = "img"."_".$name.$id;
	
	$path = "upload/$imageName.png";
	
	file_put_contents($path,base64_decode(image));
	
	each "http://   $path";
}
	
?>