investigacionApp.controller('FlujoActorController', function($log, $scope, FlujoActorService) {

    $scope.actores = [];
    $scope.actor = {};
	
    var getActoresServiceSuccess = function(response){
    	$log.debug("Get Actores - Success");
    	console.log("Success :: ", response);
    	$scope.actores = response;
    };

    var getActoresServiceError = function(response){
     	$log.debug("Get Actores - Error :: ", response ); 
    };

    var registrarActorSuccess = function(response){
    	$log.debug("Registrar Actores - Success");
    	$scope.actores.push($scope.actor);
    	$scope.actor = {};
    };

    var registrarActorError = function(response){
        $log.debug("Registrar Actores - Error :: ", response);
    };

    var updateActorSuccess = function(response){
    	$log.debug("Update Actor - Success");
    	$scope.actor = response;
    };

    var updateActorError = function(response){
        $log.debug("Update Actor - Error :: ", response);
    };

    var deleteActorSuccess = function(response){
    	$log.debug("Delete Actor - Success");
    	$scope.actor = response;
    };

    var deleteActorError = function(response){
        $log.debug("Delete Actor - Error :: ", response);
    };

    /********** CRUD ROLES ***********/

    $scope.getActores = function(){
      	FlujoActorService.getActores().then(getActoresServiceSuccess, getActoresServiceError);
    };

    $scope.registrarActor = function(){
	FlujoActorService.registrarActor($scope.actor).then(registrarActorSuccess, registrarActorError);
    };

    $scope.updateActor = function(){
    	FlujoActorService.updateActor($scope.actor).then(updateActorSuccess, updateActorError);
    };

    $scope.deleteActor = function(actor){
    	$scope.actor = actor;
    	FlujoActorService.deleteRol ($scope.actor).then(deleteActorSuccess. deleteActorError);
    };

    $scope.update = function(actor){
    	$scope.actor = actor;
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
        $scope.getActoresByPagina();
    });
    
    /*********************************************/
    
    var getActoresByPaginaSuccess = function(response){
        $log.debug("Get Paginacion Actores - Success");
        $scope.actores = response.lista;
        $scope.total = response.total;
    };
    
    var getActoresByPaginaError = function(response){
        console.log("Get Pagincion Actores - Error :: ", response);
    };
    
    $scope.getActoresByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        FlujoActorService.getActoresByPagina(objPagina).then(getActoresByPaginaSuccess, getActoresByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getActoresByPagina();
    };
    
    $scope.getActoresByPagina();
});