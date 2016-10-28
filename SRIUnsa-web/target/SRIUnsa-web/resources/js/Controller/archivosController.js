investigacionApp.controller('archivosController', function($log, $scope, $location, $rootScope, $filter, 
    ArchivosService, SharedService) {

	$scope.files = [];
	$scope.file = {};
	
    var getArchivoServiceSuccess = function(response){
    	$log.debug("Get Archivo - Success");
    	$scope.files = response;
        console.log("Success :: ", $scope.files);
    }

    var getArchivoServiceError = function(response){
     	$log.debug("Get Archivo - Error");
        console.log("Success :: ", response);
    }
    
    var descargarArchivoSuccess = function(response){
        $log.debug("Descargar Archivo - Success");
    }
    
    var descargarArchivoError = function(response){
        $log.debug("Descargar Archivo - Error");
        console.log("Descargar Archivo :: ", response);
    }
    
    var subirArchivoSuccess = function(response){
        $log.debug("Subir Archivo - Success");
    }
    
    var subirArchivoError = function(response){
        $log.debug("Subir Archivo - Error");
    }

    /********** CRUD ARCHIVOS ***********/

    $scope.getArchivos = function(){
      	ArchivosService.getArchivos().then(getArchivoServiceSuccess, getArchivoServiceError);
    }

    $scope.updateArchivo = function(){
    	var file = $scope.fileDirectiva;
        console.log("archivo ======> ", file);
        var formData = new FormData();
        formData.append('file', file);
//        formData.append('idArchivo',$scope.file.idArchivo);

        ArchivosService.subirArchivo(formData).then(subirArchivoSuccess, subirArchivoError);
    }
    
    $scope.descargarArchivo = function(archivo){
        ArchivosService.descargarArchivo(archivo.id).then(descargarArchivoSuccess, descargarArchivoError);
    }
    
    $scope.copiarArchivo = function(file){
    	$scope.file = file;
    }

    $scope.getArchivos();
});