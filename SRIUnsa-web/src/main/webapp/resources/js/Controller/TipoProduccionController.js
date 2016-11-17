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
        $scope.getTipoProduccionByPagina();
    });
    
    /*********************************************/
    
    var getTipoProduccionByPaginaSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        $scope.tipoProducciones = response.lista;
        $scope.total = response.total;
    };
    
    var getTipoProduccionByPaginaError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getTipoProduccionByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        TipoProduccionService.getTipoProduccionByPagina(objPagina).then(getTipoProduccionByPaginaSuccess, getTipoProduccionByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getTipoProduccionByPagina();
    };
    
    $scope.getTipoProduccionByPagina();
});