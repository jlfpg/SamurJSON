<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrInstalacionEsperado = array();

$arrEsperado["peticion"] = "add";

$arrInstalacionEsperado["codparque"] = "1 (Un int)";
$arrInstalacionEsperado["direccion"] = "piruleta (Un string)";
$arrInstalacionEsperado["telefono"] = "91122474 (Un string)";
$arrInstalacionEsperado["nombre"] = "Lorenzo (Un string)";



$arrEsperado["instalacionUpdate"] = $arrInstalacionEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){
	
	$auxCorrecto = false;
	
	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["instalacionUpdate"])){
		
		$auxInstalacion = $recibido["instalacionUpdate"];
		if(isset($auxInstalacion["codparque"]) && isset($auxInstalacion["direccion"]) && isset($auxInstalacion["telefono"]) && isset($auxInstalacion["nombre"])){
			$auxCorrecto = true;
		}
		
	}
	
	
	return $auxCorrecto;
	
}
