investigacionApp.controller('TipoNivelController', function($log, $scope, $location, $rootScope, $filter, 
    TipoNivelService, SharedService) {

    $scope.listarTipoNivel = [];
    $scope.tipoNivel = {};
	
    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("Get tipoNivel - Success");
    	console.log("Success :: ", response);
    	$scope.listarTipoNivel = response;
    }

    var getTipoNivelServiceError = function(response){
     	$log.debug("Get TipoNivel - Error"); 
    }

    var registrarTipoNivelSuccess = function(response){
    	$log.debug("Registrar TipoNivel - Success");
    	console.log("success :: ", response);
    	$scope.listarTipoNivel.push($scope.tipoNivel);
    	$scope.tipoNivel = {};
    }

    var registrarTipoNivelError = function(response){

    }

    var updateTipoNivelSuccess = function(response){
    	$log.debug("Update User - Success");
    	console.log("success :: ", response);
    	$scope.tipoNivel = response;
    }

    var updateTipoNivelError = function(response){

    }

    var deleteTipoNivelSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.tipoNivel = response;
    }

    var deleteTipoNivelError = function(response){

    }

    /********** CRUD TIPO PRODUCCION ***********/

    $scope.getListaTipoNivel = function(){
      	TipoNivelService.getListaTipoNivel().then(getTipoNivelServiceSuccess, getTipoNivelServiceError);
    }

    $scope.registrarTipoNivel = function(){
    	console.log("TipoNivel :: ", $scope.tipoNivel);
		TipoNivelService.registrarTipoNivel($scope.tipoNivel).then(registrarTipoNivelSuccess, registrarTipoNivelError);
    }

    $scope.updateTipoNivel = function(){
    	
    	TipoNivelService.updateTipoNivel($scope.tipoNivel).then(updateTipoNivelSuccess, updateTipoNivelError);
    }

    $scope.deleteTipoNivel = function(tipoNivel){
    	$scope.tipoNivel = tipoNivel;
    	TipoNivelService.deleteTipoNivel($scope.tipoNivel).then(deleteTipoNivelSuccess. deleteTipoNivelError);
    }

    $scope.update = function(tipoNivel){
    	$scope.tipoNivel = tipoNivel;
    }

    $scope.getListaTipoNivel();
});