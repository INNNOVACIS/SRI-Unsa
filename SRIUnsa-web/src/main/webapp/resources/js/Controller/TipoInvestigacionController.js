investigacionApp.controller('TipoInvestigacionController', function($log, $scope, $location, $rootScope, $filter, 
    TipoInvestigacionService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.investigaciones = [];
    $scope.investigacion = {};
	
    /********** Servicios Callback **********/
        
    var getInvestigacionServiceSuccess = function(response){
    	$log.debug("GetInvestigacion - Success");
        console.log("Respuesta :: ", response);
    };
    var getInvestigacionServiceError = function(response){
     	$log.debug("GetInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };

    var registrarInvestigacionSuccess = function(response){
    	$log.debug("Registrar Investigacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoInvestigacionByPagina();
        $scope.investigacion = {};
    };
    var registrarInvestigacionError = function(response){
        $log.debug("RegistrarInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };

    var updateInvestigacionSuccess = function(response){
    	$log.debug("UpdateInvestigacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoInvestigacionByPagina();
    };
    var updateInvestigacionError = function(response){
        $log.debug("UpdateInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };

    var deleteInvestigacionSuccess = function(response){
    	$log.debug("Delete Investigacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoInvestigacionByPagina();
    };
    var deleteInvestigacionError = function(response){
        $log.debug("DeleteInvestigacion - Success");
    	console.log("Respuesta :: ", response);
    };

    /********** CRUD INVESTIGACIONES ***********/

    $scope.getInvestigaciones = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getInvestigacionServiceSuccess, getInvestigacionServiceError);
    };

    $scope.registrarInvestigacion = function(){
        $scope.investigacion.suserCreacion = $scope.sharedService.nombreUsuario;
        $scope.investigacion.sestado = 'A';
	TipoInvestigacionService.registrarInvestigacion($scope.investigacion).then(registrarInvestigacionSuccess, registrarInvestigacionError);
    };

    $scope.updateInvestigacion = function(){
        $scope.investigacion.suserModificacion = $scope.sharedService.nombreUsuario;
        $scope.investigacion.sestado = 'A';
    	TipoInvestigacionService.updateInvestigacion($scope.investigacion).then(updateInvestigacionSuccess, updateInvestigacionError);
    };

    $scope.deleteInvestigacion = function(investigacion){
    	$scope.investigacion = investigacion;
    	TipoInvestigacionService.deleteInvestigacion ($scope.investigacion).then(deleteInvestigacionSuccess, deleteInvestigacionError);
    };

    $scope.update = function(investigacion){
    	$scope.investigacion = investigacion;
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
        $scope.getTipoInvestigacionByPagina();
    });
    
    /*********************************************/
    
    var getTipoInvestigacionByPaginaSuccess = function(response){
        $log.debug("GetPaginacionInvestigacion - Success");
        console.log("Respuesta :: ", response);
        $scope.investigaciones = [];
        $scope.investigaciones = response.lista;
        $scope.total = response.total;
    };
    
    var getTipoInvestigacionByPaginaError = function(response){
        $log.debug("GetPaginacionInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getTipoInvestigacionByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        TipoInvestigacionService.getTipoInvestigacionByPagina(objPagina).then(getTipoInvestigacionByPaginaSuccess, getTipoInvestigacionByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getTipoInvestigacionByPagina();
    };
    
    $scope.getTipoInvestigacionByPagina();
});