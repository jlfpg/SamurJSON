<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrInstalacionEsperado = array();

$arrEsperado["peticion"] = "add";

$arrInstalacionEsperado["direccion"] = "piruleta (Un string)";
$arrInstalacionEsperado["telefono"] = "91122474 (Un string)";
$arrInstalacionEsperado["nombre"] = "Lorenzo (Un string)";



$arrEsperado["instalacionAnnadir"] = $arrInstalacionEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){
	
	$auxCorrecto = false;
	
	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["instalacionAnnadir"])){
		
		$auxInstalacion = $recibido["instalacionAnnadir"];
		if(isset($auxInstalacion["direccion"]) && isset($auxInstalacion["telefono"]) && isset($auxInstalacion["nombre"])){
			$auxCorrecto = true;
		}
		
	}
	
	
	return $auxCorrecto;
	
}
