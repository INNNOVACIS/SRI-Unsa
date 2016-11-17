investigacionApp.controller('EstructuraOrganizacionController', function($log, $scope, $location, $rootScope, $filter, 
    EstructuraOrganizacionService, TipoNivelService, SharedService) {

    $scope.estructuraOrganizaciones = [];
    $scope.estructuraOrganizacion = {};
	
    /********** Servicios Callback **********/

    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("Get tipoNivel - Success");
    	console.log("Success :: ", response);
    	$scope.niveles = response;
    };

    var getTipoNivelServiceError = function(response){
     	$log.debug("Get TipoNivel - Error"); 
    };

    var getEstructuraOrganizacionServiceSuccess = function(response){
    	$log.debug("Get EstructuraOrganizacion - Success");
        
        angular.forEach(response, function(superior, key) {
            angular.forEach(response, function(value, key) {
                if(superior.nidPadre == value.nidEstructuraOrganizacion){
                    superior.nombrePadre = value.snombreEstructuraOrganizacion;
                }
            });
            angular.forEach($scope.niveles, function(nivel, key) {
                if(superior.nidTipoNivel == nivel.nidTipoNivel){
                    superior.nombreNivel = nivel.snombreTipoNivel;
                }
            });
        });
        
    	$scope.estructuraOrganizaciones = response;
    };

    var getEstructuraOrganizacionServiceError = function(response){
     	$log.debug("Get EstructuraOrganizacion - Error"); 
    };

    var registrarEstructuraOrganizacionSuccess = function(response){
        
    	$log.debug("Registrar EstructuraOrganizacion - Success");
    	$scope.estructuraOrganizaciones.push($scope.estructuraOrganizacion);
    	$scope.estructuraOrganizacion = {};
    };

    var registrarEstructuraOrganizacionError = function(response){
        $log.debug("Registrar EstructuraOrganizacion - Error");
    };

    var updateEstructuraOrganizacionSuccess = function(response){
    	$log.debug("Update EstructuraOrganizacion - Success");
    	console.log("success :: ", response);
    	$scope.estructuraOrganizacion = response;
    };

    var updateEstructuraOrganizacionError = function(response){
        $log.debug("Update EstructuraOrganizacion - Error");
    };

    var deleteEstructuraOrganizacionSuccess = function(response){
    	$log.debug("Delete EstructuraOrganizacion - Success");
    	console.log("success :: ", response);
    	$scope.estructuraOrganizacion = response;
    };

    var deleteEstructuraOrganizacionError = function(response){

    };

    /********** CRUD EstructuraOrganizaciones ***********/

    $scope.getListaTipoNivel = function(){
      	TipoNivelService.getListaTipoNivel().then(getTipoNivelServiceSuccess, getTipoNivelServiceError);
    }

    $scope.getEstructuraOrganizaciones = function(){
      	EstructuraOrganizacionService.getEstructuraOrganizaciones().then(getEstructuraOrganizacionServiceSuccess, getEstructuraOrganizacionServiceError);
    };

    $scope.registrarEstructuraOrganizacion = function(){

        $scope.estructuraOrganizacion.nidPadre = $scope.superior.nidEstructuraOrganizacion;
        $scope.estructuraOrganizacion.nidTipoNivel = $scope.nivel.nidTipoNivel;
        
	EstructuraOrganizacionService.registrarEstructuraOrganizacion($scope.estructuraOrganizacion).then(registrarEstructuraOrganizacionSuccess, registrarEstructuraOrganizacionError);
    };

    $scope.updateEstructuraOrganizacion = function(){
    	
    	EstructuraOrganizacionService.updateEstructuraOrganizacion($scope.estructuraOrganizacion).then(updateEstructuraOrganizacionSuccess, updateAreaInvestigacionError);
    };

    $scope.deleteEstructuraOrganizacion = function(estructuraOrganizacion){
    	$scope.estructuraOrganizacion = estructuraOrganizacion;
    	EstructuraOrganizacionService.deleteEstructuraOrganizacion ($scope.estructuraOrganizacion).then(deleteEstructuraOrganizacionSuccess. deleteAreaInvestigacionError);
    };

    $scope.update = function(estructuraOrganizacion){
    	$scope.estructuraOrganizacion = estructuraOrganizacion;
    };

    $scope.getListaTipoNivel();
    
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
    
    /*********************************************/
    
    var getEstructuraOrganizacionByPaginaSuccess = function(response){
        $log.debug("getEstructuraOrganizacionByPaginaSuccess - Success");
        $scope.estructuraOrganizaciones = response.lista;
        $scope.total = response.total;
    };
    
    var getEstructuraOrganizacionByPaginaError = function(response){
        console.log(" getEstructuraOrganizacionByPaginaError :: ", response);
    };
    
    $scope.getEstructuraOrganizacionByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        EstructuraOrganizacionService.getEstructuraOrganizacionByPagina(objPagina).then(getEstructuraOrganizacionByPaginaSuccess, getEstructuraOrganizacionByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getEstructuraOrganizacionByPagina();
    };
    
    $scope.getEstructuraOrganizacionByPagina();
});