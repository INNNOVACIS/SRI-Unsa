investigacionApp.controller('TipoProduccionController', function($log, $scope, $location, $rootScope, $filter, 
    TipoProduccionService, SharedService) {

	$scope.listaTipoProduccion = [];
	$scope.TipoProducion = {};
	
	var getTipoProduccionServiceSuccess = function(response){
    	$log.debug("Get TipoProduccion - Success");
    	console.log("Success :: ", response);
    	$scope.listaTipoProduccion = response;
    }

    var getTipoProduccionServiceError = function(response){
     	$log.debug("Get TipoProduccion - Error"); 
    }

    var registrarTipoProduccionSuccess = function(response){
    	$log.debug("Registrar TipoProduccion - Success");
    	console.log("success :: ", response);
    	$scope.listaTipoProduccion.push(response);
    	$scope.TipoProducion = {};
    }

    var registrarTipoProduccionError = function(response){

    }

    var updateTipoProduccionSuccess = function(response){
    	$log.debug("Update User - Success");
    	console.log("success :: ", response);
    	$scope.TipoProducion = response;
    }

    var updateTipoProduccionError = function(response){

    }

    var deleteTipoProduccionSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.TipoProducion = response;
    }

    var deleteTipoProduccionError = function(response){

    }

    /********** CRUD TIPO PRODUCCION ***********/

    $scope.getListaTipoProduccion = function(){
      	TipoProduccionService.getListaTipoProduccion().then(getTipoProduccionServiceSuccess, getTipoProduccionServiceError);
    }

    $scope.registrarTipoProduccion = function(){
    	console.log("TipoProduccion :: ", $scope.TipoProducion);
		TipoProduccionService.registrarTipoProduccion($scope.TipoProducion).then(registrarTipoProduccionSuccess, registrarTipoProduccionError);
    }

    $scope.updateTipoProduccion = function(){
    	
    	TipoProduccionService.updateTipoProduccion($scope.TipoProducion).then(updateTipoProduccionSuccess, updateTipoProduccionError);
    }

    $scope.deleteTipoProduccion = function(user){
    	$scope.TipoProducion = user;
    	$scope.TipoProducion.estado = "I";
    	TipoProduccionService.deleteTipoProduccion($scope.TipoProducion).then(deleteTipoProduccionSuccess. deleteTipoProduccionError);
    }

    $scope.update = function(tipoproduccion){
    	$scope.TipoProducion = tipoproduccion;
    }

    $scope.getListaTipoProduccion();
});