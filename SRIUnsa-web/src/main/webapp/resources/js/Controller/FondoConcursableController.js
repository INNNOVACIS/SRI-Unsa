investigacionApp.controller('FondoConcursableController', function($log, $scope, $location, $rootScope, $filter, 
    FondoConcursableService) {

    $scope.fondos = [];
    $scope.fondo = {};
	
    /********** Servicios Callback **********/
        
    var getFondoServiceSuccess = function(response){
    	$log.debug("GetFondo - Success");
        console.log("Respuesta :: ", response);
    	$scope.fondos = response;
    };
    var getFondoServiceError = function(response){
     	$log.debug("GetFondo - Error");
        console.log("Respuesta :: ", response);
    };

    var registrarFondoSuccess = function(response){
    	$log.debug("RegistrarFondo - Success");
        console.log("Respuesta :: ", response);
    	$scope.fondos.push($scope.fondo);
    	$scope.fondo = {};
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
        $scope.getUsuariosByPagina();
        $scope.row = ($scope.currentPage - 1) * $scope.currentRango + 1;
    });
    
    var paginacionUsuarioSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.users = [];
        $scope.users = response.lista;
        $scope.total = response.total;
    };
    
    var paginacionUsuarioError = function(response){
        $log.debug("Get paginacionUsuario - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getUsuariosByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
//        UsuariosService.paginacionUsuario(objPagina).then(paginacionUsuarioSuccess, paginacionUsuarioError);
        FondoConcursableService.getFondos().then(getFondoServiceSuccess, getFondoServiceError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getUsuariosByPagina();
    };


    $scope.getFondos();
});