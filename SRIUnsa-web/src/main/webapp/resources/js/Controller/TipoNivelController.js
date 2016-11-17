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
        $scope.getTipoNivelByPagina();
    });
    
    /*********************************************/
    
    var getTipoNivelByPaginaSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        $scope.listarTipoNivel = response.lista;
        $scope.total = response.total;
    };
    
    var getTipoNivelByPaginaError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getTipoNivelByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        TipoNivelService.getTipoNivelByPagina(objPagina).then(getTipoNivelByPaginaSuccess, getTipoNivelByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getTipoNivelByPagina();
    };
    
    $scope.getTipoNivelByPagina();
});