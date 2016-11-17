investigacionApp.controller('FondoConcursableController', function($log, $scope, $location, $rootScope, $filter, 
    FondoConcursableService, SharedService) {

    $scope.fondos = [];
    $scope.fondo = {};
	
    /********** Servicios Callback **********/
        
    var getFondoServiceSuccess = function(response){
    	$log.debug("Get Fondo - Success");
    	$scope.fondos = response;
    };

    var getFondoServiceError = function(response){
     	$log.debug("Get Fondo - Error"); 
    };

    var registrarFondoSuccess = function(response){
        
    	$log.debug("Registrar Fondo - Success");
    	$scope.fondos.push($scope.fondo);
    	$scope.fondo = {};
    };

    var registrarFondoError = function(response){
        $log.debug("Registrar Fondo - Error");
    };

    var updateFondoSuccess = function(response){
    	$log.debug("Update Fondo - Success");
    	console.log("success :: ", response);
    	$scope.fondo = response;
    };

    var updateFondoError = function(response){
        $log.debug("Update Fondo - Error");
    };

    var deleteFondoSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.fondo = response;
    };

    var deleteFondoError = function(response){

    };

    /********** CRUD FONDOS ***********/

    $scope.getFondos = function(){
      	FondoConcursableService.getFondos().then(getFondoServiceSuccess, getFondoServiceError);
    };

    $scope.registrarFondo = function(){
	FondoConcursableService.registrarFondo($scope.fondo).then(registrarFondoSuccess, registrarFondoError);
    };

    $scope.updateFondo = function(){
    	
    	FondoConcursableService.updateFondo($scope.fondo).then(updateFondoSuccess, updateFondoError);
    };

    $scope.deleteFondo = function(fondo){
    	$scope.fondo = fondo;
    	FondoConcursableService.deleteFondo ($scope.fondo).then(deleteFondoSuccess. deleteFondoError);
    };

    $scope.update = function(fondo){
    	$scope.fondo = fondo;
    };

    $scope.getFondos();
});