investigacionApp.controller('EstructuraAreaInvestigacionController', function($log, $scope, $location, $rootScope, $filter, 
    EstructuraAreaInvestigacionService, SharedService) {

    $scope.areaInvestigaciones = [];
    $scope.areaInvestigacion = {};
	
    /********** Servicios Callback **********/
        
    var getAreaInvestigacionServiceSuccess = function(response){
    	$log.debug("Get AreaInvestigacion - Success");
    	$scope.areaInvestigaciones = response;
    };

    var getAreaInvestigacionServiceError = function(response){
     	$log.debug("Get AreaInvestigacion - Error"); 
    };

    var registrarAreaInvestigacionSuccess = function(response){
        
    	$log.debug("Registrar AreaInvestigacion - Success");
    	$scope.areaInvestigaciones.push($scope.areaInvestigacion);
    	$scope.areaInvestigacion = {};
    };

    var registrarAreaInvestigacionError = function(response){
        $log.debug("Registrar AreaInvestigacion - Error");
    };

    var updateAreaInvestigacionSuccess = function(response){
    	$log.debug("Update AreaInvestigacion - Success");
    	console.log("success :: ", response);
    	$scope.areaInvestigacion = response;
    };

    var updateAreaInvestigacionError = function(response){
        $log.debug("Update AreaInvestigacion - Error");
    };

    var deleteAreaInvestigacionSuccess = function(response){
    	$log.debug("Delete AreaInvestigacion - Success");
    	console.log("success :: ", response);
    	$scope.areaInvestigacion = response;
    };

    var deleteAreaInvestigacionError = function(response){

    };

    /********** CRUD AreaInvestigaciones ***********/

    $scope.getAreaInvestigaciones = function(){
      	EstructuraAreaInvestigacionService.getAreaInvestigaciones().then(getAreaInvestigacionServiceSuccess, getAreaInvestigacionServiceError);
    };

    $scope.registrarAreaInvestigacion = function(){
	EstructuraAreaInvestigacionService.registrarAreaInvestigacion($scope.areaInvestigacion).then(registrarAreaInvestigacionSuccess, registrarAreaInvestigacionError);
    };

    $scope.updateAreaInvestigacion = function(){
    	
    	EstructuraAreaInvestigacionService.updateAreaInvestigacion($scope.areaInvestigacion).then(updateAreaInvestigacionSuccess, updateAreaInvestigacionError);
    };

    $scope.deleteAreaInvestigacion = function(areaInvestigacion){
    	$scope.areaInvestigacion = areaInvestigacion;
    	EstructuraAreaInvestigacionService.deleteAreaInvestigacion ($scope.areaInvestigacion).then(deleteAreaInvestigacionSuccess. deleteAreaInvestigacionError);
    };

    $scope.update = function(areaInvestigacion){
    	$scope.areaInvestigacion = areaInvestigacion;
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
        $scope.getAreaInvestigacionByPagina();
    });
    
    /*********************************************/
    
    var getAreaInvestigacionByPaginaSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        $scope.areaInvestigaciones = response.lista;
        $scope.total = response.total;
    };
    
    var getAreaInvestigacionByPaginaError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getAreaInvestigacionByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        EstructuraAreaInvestigacionService.getAreaInvestigacionByPagina(objPagina).then(getAreaInvestigacionByPaginaSuccess, getAreaInvestigacionByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getAreaInvestigacionByPagina();
    };
    
    $scope.getAreaInvestigacionByPagina();
});