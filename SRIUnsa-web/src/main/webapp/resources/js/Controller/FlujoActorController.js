investigacionApp.controller('FlujoActorController',['$log', '$scope', 'FlujoActorService', 'SharedService', 
    function($log, $scope, FlujoActorService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.actores = [];
    $scope.actor = {};
	
    var getActoresServiceSuccess = function(response){
    	$log.debug("Get Actores - Success");
    	console.log("Respuesta :: ", response);    	
    };
    var getActoresServiceError = function(response){
     	$log.debug("Get Actores - Error"); 
        console.log("Respuesta :: ", response);
    };

    var registrarActorSuccess = function(response){
    	$log.debug("Registrar Actores - Success");
    	console.log("Respuesta :: ", response);
        $scope.getActoresByPagina();
    	$scope.actor = {};
    };
    var registrarActorError = function(response){
        $log.debug("Registrar Actores - Error");
        console.log("Respuesta :: ", response);
    };
    
    var updateActorSuccess = function(response){
    	$log.debug("Update Actor - Success");
    	console.log("Respuesta :: ", response);
        $scope.getActoresByPagina();
    };
    var updateActorError = function(response){
        $log.debug("Update Actor - Error");
        console.log("Respuesta :: ", response);
    };

    var deleteActorSuccess = function(response){
    	$log.debug("DeleteActor - Success");
    	console.log("Respuesta :: ", response);
        $scope.getActoresByPagina();
    };

    var deleteActorError = function(response){
        $log.debug("DeleteActor - Error");
        console.log("Respuesta :: ", response);
    };

    /********** CRUD ROLES ***********/

    $scope.getActores = function(){
      	FlujoActorService.getActores().then(getActoresServiceSuccess, getActoresServiceError);
    };

    $scope.registrarActor = function(){
        $scope.actor.suserCreacion = $scope.sharedService.nombreUsuario;
        $scope.actor.sestado = 'A';
	FlujoActorService.registrarActor($scope.actor).then(registrarActorSuccess, registrarActorError);
    };

    $scope.updateActor = function(){
        $scope.actor.suserModificacion = $scope.sharedService.nombreUsuario;
        $scope.actor.sestado = 'A';
    	FlujoActorService.updateActor($scope.actor).then(updateActorSuccess, updateActorError);
    };

    $scope.deleteActor = function(actor){
    	$scope.actor = actor;
        $scope.actor.suserModificacion = $scope.sharedService.nombreUsuario;
    	FlujoActorService.deleteRol ($scope.actor).then(deleteActorSuccess, deleteActorError);
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
        console.log("Respuesta :: ", response);
        $scope.actores = [];
        $scope.actores = response.lista;
        $scope.total = response.total;
    };
    
    var getActoresByPaginaError = function(response){
        $log.debug("Get Pagincion Actores - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getActoresByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        FlujoActorService.getActoresByPagina(objPagina).then(getActoresByPaginaSuccess, getActoresByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getActoresByPagina();
    };
    
    $scope.getActoresByPagina();
}]);