investigacionApp.controller('EstructuraOrganizacionController',['$log', '$scope', '$location', '$rootScope', '$filter', 
    'EstructuraOrganizacionService', 'TipoNivelService', 'SharedService', function($log, $scope, $location, $rootScope, $filter, 
    EstructuraOrganizacionService, TipoNivelService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.estructuraOrganizaciones = [];
    $scope.estructuraOrganizacion = {};
	
    /********** Servicios Callback **********/

    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("GetTipoNivel - Success");
    	console.log("Respuesta :: ", response);
    	$scope.niveles = response;
    };

    var getTipoNivelServiceError = function(response){
     	$log.debug("GetTipoNivel - Error");
    	console.log("Respuesta :: ", response);
    };

    var getEstructuraOrganizacionServiceSuccess = function(response){
    	$log.debug("GetEstructuraOrganizacion - Success");
    	console.log("Respuesta :: ", response);        
    	$scope.organizaciones = response;
    };
    var getEstructuraOrganizacionServiceError = function(response){
     	$log.debug("GetEstructuraOrganizacion - Error"); 
    	console.log("Respuesta :: ", response);
    };

    var registrarEstructuraOrganizacionSuccess = function(response){   
    	$log.debug("RegistrarEstructuraOrganizacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.estructuraOrganizaciones.push($scope.estructuraOrganizacion);
    	$scope.estructuraOrganizacion = {};
        $scope.getEstructuraOrganizacionByPagina();
    };
    var registrarEstructuraOrganizacionError = function(response){
        $log.debug("RegistrarEstructuraOrganizacion - Error");
        console.log("Respuesta :: ", response);
    };

    var updateEstructuraOrganizacionSuccess = function(response){
    	$log.debug("UpdateEstructuraOrganizacion - Success");
        console.log("Respuesta :: ", response);
    	$scope.estructuraOrganizacion = response;
    };
    var updateEstructuraOrganizacionError = function(response){
        $log.debug("UpdateEstructuraOrganizacion - Error");
        console.log("Respuesta :: ", response);
    };
    
    var deleteEstructuraOrganizacionSuccess = function(response){
    	$log.debug("DeleteEstructuraOrganizacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getEstructuraOrganizacionByPagina();
    };

    var deleteEstructuraOrganizacionError = function(response){
        $log.debug("DeleteEstructuraOrganizacion - Error");
    	console.log("Respuesta :: ", response);
    };

    /********** CRUD EstructuraOrganizaciones ***********/

    $scope.getListaTipoNivel = function(){
      	TipoNivelService.getListaTipoNivel().then(getTipoNivelServiceSuccess, getTipoNivelServiceError);
    };

    $scope.getEstructuraOrganizaciones = function(){
      	EstructuraOrganizacionService.getEstructuraOrganizaciones().then(getEstructuraOrganizacionServiceSuccess, getEstructuraOrganizacionServiceError);
    };

    $scope.registrarEstructuraOrganizacion = function(){
        var estructura = {
            nidPadre : $scope.dependencia === {} ? 0 : $scope.dependencia.nidEstructuraOrganizacion,
            nidTipoNivel : $scope.nivel.nidTipoNivel,
            snivel : $scope.nivel.snombreTipoNivel.toUpperCase(),
            snombreEstructuraOrganizacion : $scope.nombre,
            suserCreacion : $scope.sharedService.nombreUsuario,
            sestado : "A"
        };
        
	EstructuraOrganizacionService.registrarEstructuraOrganizacion(estructura).then(registrarEstructuraOrganizacionSuccess, registrarEstructuraOrganizacionError);
    };

    $scope.updateEstructuraOrganizacion = function(){
    	
    	EstructuraOrganizacionService.updateEstructuraOrganizacion($scope.estructuraOrganizacion).then(updateEstructuraOrganizacionSuccess, updateEstructuraOrganizacionError);
    };

    $scope.deleteEstructuraOrganizacion = function(estructuraOrganizacion){
        delete estructuraOrganizacion.dependencia;
        console.log(estructuraOrganizacion);
    	EstructuraOrganizacionService.deleteEstructuraOrganizacion ($scope.estructuraOrganizacion).then(deleteEstructuraOrganizacionSuccess, deleteEstructuraOrganizacionError);
    };

    $scope.update = function(estructuraOrganizacion){
    	$scope.estructuraOrganizacion = estructuraOrganizacion;
        $scope.nivel = getNivel($scope.estructuraOrganizacion.nidTipoNivel);
        $scope.changeNivel($scope.nivel);
        $scope.dependencia = getDependencia($scope.estructuraOrganizacion.nidPadre);
        $scope.nombre = estructuraOrganizacion.snombreEstructuraOrganizacion;
    };

    $scope.getListaTipoNivel();
    
    var getNivel = function(valor){
        var nivel = {};
        angular.forEach($scope.niveles, function(value, key){
            if(value.nidTipoNivel === valor){
                nivel = value;
            }
        });
        return nivel;
    };
    
    var getDependencia = function(valor){
        var dependencia = {};
        angular.forEach($scope.organizaciones, function(value, key){
            if(value.nidEstructuraOrganizacion === valor){
                dependencia = value;
            }
        });
        return dependencia;
    };
    
    var getNombreDependecia = function(estructura){
        var nombre = "";
        angular.forEach($scope.organizaciones, function(value, key){
            if(estructura.nidPadre === value.nidEstructuraOrganizacion){
                nombre = value.snombreEstructuraOrganizacion;
            }
        });
        if(nombre === ""){ nombre = "Facultad" }
        return nombre;
    };
    
    $scope.changeNivel = function(nivel){
        console.log("changeNivel :: ", nivel)
        $scope.dependencia = {};
        if(nivel.snombreTipoNivel.toUpperCase() !== "FACULTAD"){
            $scope.showDependencia = true;
            $scope.dependencias = [];
            angular.forEach($scope.organizaciones, function(value, key){
                if(value.snivel === 'FACULTAD'){
                    $scope.dependencias.push(value);
                }
            });
        } else {
            $scope.dependencias = [];
            $scope.showDependencia = false;
        }
    };
    
     /**************** PAGINACION *****************/
    
    $scope.rangoPaginas = [5,10,20,100];
    $scope.currentPage = 1;
    $scope.currentRango = $scope.rangoPaginas[0];
    $scope.maxSize = 5;
    $scope.total = 0;

    $scope.numPages = function () {
      return Math.ceil($scope.total / $scope.currentRango);
    };

    $scope.$watch('currentPage + currentRango', function() {
        $scope.getEstructuraOrganizacionByPagina();
    });
    
    var getEstructuraOrganizacionByPaginaSuccess = function(response){
        $log.debug("getEstructuraOrganizacionByPaginaSuccess - Success");
        console.log("Respuestas :: ", response);
        $scope.estructuraOrganizaciones = [];
        angular.forEach(response.lista, function(value, key){
            value.dependencia = getNombreDependecia(value);
        });
        $scope.estructuraOrganizaciones = response.lista;
        $scope.total = response.total;
    };
    
    var getEstructuraOrganizacionByPaginaError = function(response){
        $log.debug("getEstructuraOrganizacionByPaginaSuccess - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getEstructuraOrganizacionByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        EstructuraOrganizacionService.getEstructuraOrganizacionByPagina(objPagina).then(getEstructuraOrganizacionByPaginaSuccess, getEstructuraOrganizacionByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getEstructuraOrganizacionByPagina();
    };
    
    $scope.getEstructuraOrganizaciones();
    $scope.getEstructuraOrganizacionByPagina();
    
    /******************* EXPORTAR ARCHIVOS *******************/
    
    var descargarPDFSuccess = function (response){
        $log.debug("descargarPDF - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var descargarPDFError = function (response){
        $log.debug("descargarPDF - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    var descargarExcelSuccess = function (response){
        $log.debug("descargarExcel - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var descargarExcelError = function (response){
        $log.debug("descargarExcel - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    $scope.descargarPDF = function(){
        console.log("Empezando descarga de PDF...");
        $scope.loader = true;
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        EstructuraOrganizacionService.descargarPDF(pagina).then(descargarPDFSuccess, descargarPDFError);
    };
    
    $scope.descargarExcel = function(){
        console.log("Empezando descarga de Excel...");
        $scope.loader = true;
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        EstructuraOrganizacionService.descargarExcel(pagina).then(descargarExcelSuccess, descargarExcelError);
    };
    
}]);