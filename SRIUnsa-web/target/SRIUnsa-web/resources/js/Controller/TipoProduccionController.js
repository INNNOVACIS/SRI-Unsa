investigacionApp.controller('TipoProduccionController', function($log, $scope, $location, $rootScope, $filter, 
    TipoProduccionService, SharedService) {

    $scope.tipoProducciones = [];
    $scope.tipoProduccion = {};
	
    var getTipoProduccionServiceSuccess = function(response){
    	$log.debug("Get TipoProduccion - Success");
    	console.log("Success :: ", response);
    	$scope.tipoProducciones = response;
    }

    var getTipoProduccionServiceError = function(response){
     	$log.debug("Get TipoProduccion - Error"); 
    }

    var registrarTipoProduccionSuccess = function(response){
    	$log.debug("Registrar TipoProduccion - Success");
    	console.log("success :: ", response);
    	$scope.tipoProducciones.push($scope.tipoProduccion);
    	$scope.tipoProduccion = {};
    }

    var registrarTipoProduccionError = function(response){

    }

    var updateTipoProduccionSuccess = function(response){
    	$log.debug("Update User - Success");
    	console.log("success :: ", response);
    	$scope.tipoProduccion = response;
    }

    var updateTipoProduccionError = function(response){

    }

    var deleteTipoProduccionSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.tipoProduccion = response;
    }

    var deleteTipoProduccionError = function(response){

    }

    /********** CRUD TIPO PRODUCCION ***********/

    $scope.getListaTipoProduccion = function(){
      	TipoProduccionService.getListaTipoProduccion().then(getTipoProduccionServiceSuccess, getTipoProduccionServiceError);
    }

    $scope.registrarTipoProduccion = function(){
    	console.log("TipoProduccion :: ", $scope.tipoProduccion);
		TipoProduccionService.registrarTipoProduccion($scope.tipoProduccion).then(registrarTipoProduccionSuccess, registrarTipoProduccionError);
    }

    $scope.updateTipoProduccion = function(){
    	
    	TipoProduccionService.updateTipoProduccion($scope.tipoProduccion).then(updateTipoProduccionSuccess, updateTipoProduccionError);
    }

    $scope.deleteTipoProduccion = function(tipoProduccion){
    	$scope.tipoProduccion = tipoProduccion;
    	TipoProduccionService.deleteTipoProduccion($scope.tipoProduccion).then(deleteTipoProduccionSuccess. deleteTipoProduccionError);
    }

    $scope.update = function(tipoProduccion){
    	$scope.tipoProduccion = tipoProduccion;
    }

    $scope.getListaTipoProduccion();
});