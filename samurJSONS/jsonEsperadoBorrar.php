<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrInstalacionEsperado = array();

$arrEsperado["peticion"] = "del";

$arrInstalacionEsperado["codparque"] = "1 (Un int)";



$arrEsperado["instalacionBorrar"] = $arrInstalacionEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){
	
	$auxCorrecto = false;
	
	if(isset($recibido["peticion"]) && $recibido["peticion"] ="del" && isset($recibido["instalacionBorrar"])){
		
		$auxInstalacion = $recibido["instalacionBorrar"];
		if(isset($auxInstalacion["codparque"])){
			$auxCorrecto = true;
		}
		
	}
	
	
	return $auxCorrecto;
	
}
