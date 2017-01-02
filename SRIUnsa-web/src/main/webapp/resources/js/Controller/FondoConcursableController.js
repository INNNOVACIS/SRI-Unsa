investigacionApp.controller('FondoConcursableController', function($log, $scope, $location, $rootScope, $filter, 
    FondoConcursableService) {

    $scope.fondos = [];
    $scope.fondo = {};
	
    /********** Servicios Callback **********/

    var registrarFondoSuccess = function(response){
    	$log.debug("RegistrarFondo - Success");
        console.log("Respuesta :: ", response);
    	$scope.getFondosByPagina();
    };
    var registrarFondoError = function(response){
        $log.debug("RegistrarFondo - Error");
        console.log("Respuesta :: ", response);
    };

    var updateFondoSuccess = function(response){
    	$log.debug("Update Fondo - Success");
    	console.log("Respuesta :: ", response);
    	$scope.fondo = response;
    };
    var updateFondoError = function(response){
        $log.debug("UpdateFondo - Error");
    };

    var deleteFondoSuccess = function(response){
    	$log.debug("DeleteFondo - Success");
    	console.log("Respuesta :: ", response);
    	$scope.fondo = response;
    };
    var deleteFondoError = function(response){
        $log.debug("DeleteFondo - Error");
    	console.log("Respuesta :: ", response);
    };

    /********** CRUD FONDOS ***********/

    $scope.registrarFondo = function(){
	FondoConcursableService.registrarFondo($scope.fondo).then(registrarFondoSuccess, registrarFondoError);
    };

    $scope.updateFondo = function(){
    	
    	FondoConcursableService.updateFondo($scope.fondo).then(updateFondoSuccess, updateFondoError);
    };

    $scope.deleteFondo = function(fondo){
    	$scope.fondo = fondo;
    	FondoConcursableService.deleteFondo ($scope.fondo).then(deleteFondoSuccess, deleteFondoError);
    };

    $scope.update = function(fondo){
    	$scope.fondo = fondo;
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
        $scope.getFondosByPagina();
        $scope.row = ($scope.currentPage - 1) * $scope.currentRango + 1;
    });
    
    var getFondoServiceSuccess = function(response){
        $log.debug("getFondoService - Success");
        console.log("Respuesta :: ", response);
        $scope.fondos = [];
        $scope.fondos = response.lista;
        $scope.total = response.total;
    };
    
    var getFondoServiceError = function(response){
        $log.debug("getFondoService - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getFondosByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        FondoConcursableService.getFondosByPagina(objPagina).then(getFondoServiceSuccess, getFondoServiceError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getUsuariosByPagina();
    };

    $scope.getFondosByPagina();
});