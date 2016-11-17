investigacionApp.controller('TipoInvestigacionController', function($log, $scope, $location, $rootScope, $filter, 
    TipoInvestigacionService, SharedService) {

    $scope.investigaciones = [];
    $scope.investigacion = {};
    
	
    /********** Servicios Callback **********/
        
    var getInvestigacionServiceSuccess = function(response){
    	$log.debug("Get Investigacion - Success");
    	$scope.investigaciones = response;
    };

    var getInvestigacionServiceError = function(response){
     	$log.debug("Get Investigacion - Error"); 
    };

    var registrarInvestigacionSuccess = function(response){
        
    	$log.debug("Registrar Investigacion - Success");
    	$scope.investigaciones.push($scope.investigacion);
    	$scope.investigacion = {};
    };

    var registrarInvestigacionError = function(response){
        $log.debug("Registrar Investigacion - Error");
    };

    var updateInvestigacionSuccess = function(response){
    	$log.debug("Update Investigacion - Success");
    	console.log("success :: ", response);
    	$scope.investigacion = response;
    };

    var updateInvestigacionError = function(response){
        $log.debug("Update Investigacion - Error");
    };

    var deleteInvestigacionSuccess = function(response){
    	$log.debug("Delete Investigacion - Success");
    	console.log("success :: ", response);
    	$scope.investigacion = response;
    };

    var deleteInvestigacionError = function(response){

    };

    /********** CRUD INVESTIGACIONES ***********/

    $scope.getInvestigaciones = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getInvestigacionServiceSuccess, getInvestigacionServiceError);
    };

    $scope.registrarInvestigacion = function(){
	TipoInvestigacionService.registrarInvestigacion($scope.investigacion).then(registrarInvestigacionSuccess, registrarInvestigacionError);
    };

    $scope.updateInvestigacion = function(){
    	
    	TipoInvestigacionService.updateInvestigacion($scope.investigacion).then(updateInvestigacionSuccess, updateInvestigacionError);
    };

    $scope.deleteInvestigacion = function(investigacion){
    	$scope.investigacion = investigacion;
    	TipoInvestigacionService.deleteInvestigacion ($scope.investigacion).then(deleteInvestigacionSuccess. deleteInvestigacionError);
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
        $log.debug("Get paginacionUsuario - Success");
        $scope.investigaciones = response.lista;
        $scope.total = response.total;
    };
    
    var getTipoInvestigacionByPaginaError = function(response){
        console.log("error :: ", response);
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