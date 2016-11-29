investigacionApp.controller('TipoNivelController', function($log, $scope, TipoNivelService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.listarTipoNivel = [];
    $scope.tipoNivel = {};
    
    /***************** Callback ****************/
    
    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("GetTipoNivel - Success");
    	console.log("Respuesta :: ", response);    	
    };
    var getTipoNivelServiceError = function(response){
     	$log.debug("GetTipoNivel - Error"); 
        console.log("Respuesta :: ", response);
    };

    var registrarTipoNivelSuccess = function(response){
    	$log.debug("RegistrarTipoNivel - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoNivelByPagina();
    	$scope.tipoNivel = {};
    };
    var registrarTipoNivelError = function(response){
        $log.debug("RegistrarTipoNivel - Error");
    	console.log("Respuesta :: ", response);
    };
    
    var updateTipoNivelSuccess = function(response){
    	$log.debug("UpdateTipoNivel - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoNivelByPagina();
    };
    var updateTipoNivelError = function(response){
        $log.debug("UpdateTipoNivel - Error");
    	console.log("Respuesta :: ", response);
    };

    var deleteTipoNivelSuccess = function(response){
    	$log.debug("DeleteTipoNivel - Success");
    	console.log("Respuesta :: ", response);
        $scope.getTipoNivelByPagina();
    };
    var deleteTipoNivelError = function(response){
        $log.debug("DeleteTipoNivel - Error");
    	console.log("Respuesta :: ", response);
    };

    /***************** Servicios ****************/

    $scope.getListaTipoNivel = function(){
      	TipoNivelService.getListaTipoNivel().then(getTipoNivelServiceSuccess, getTipoNivelServiceError);
    };

    $scope.registrarTipoNivel = function(){
    	$scope.tipoNivel.suserCreacion = $scope.sharedService.nombreUsuario;
        $scope.tipoNivel.sestado = 'A';
        TipoNivelService.registrarTipoNivel($scope.tipoNivel).then(registrarTipoNivelSuccess, registrarTipoNivelError);
    };

    $scope.updateTipoNivel = function(){
    	$scope.tipoNivel.suserModificacion = $scope.sharedService.nombreUsuario;
        $scope.tipoNivel.sestado = 'A';
    	TipoNivelService.updateTipoNivel($scope.tipoNivel).then(updateTipoNivelSuccess, updateTipoNivelError);
    };

    $scope.deleteTipoNivel = function(tipoNivel){
    	$scope.tipoNivel = tipoNivel;
        $scope.tipoNivel.suserModificacion = $scope.sharedService.nombreUsuario;
    	TipoNivelService.deleteTipoNivel($scope.tipoNivel).then(deleteTipoNivelSuccess, deleteTipoNivelError);
    };

    $scope.update = function(tipoNivel){
    	$scope.tipoNivel = tipoNivel;
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
        $scope.getTipoNivelByPagina();
    });
    
    /*********************************************/
    
    var getTipoNivelByPaginaSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.listarTipoNivel = [];
        $scope.listarTipoNivel = response.lista;
        $scope.total = response.total;
    };
    
    var getTipoNivelByPaginaError = function(response){
        $log.debug("Get paginacionUsuario - Error");
        console.log("Respuesta :: ", response);
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