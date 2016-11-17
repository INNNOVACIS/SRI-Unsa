investigacionApp.controller('PrivilegioController', function($log, $scope, $location, $rootScope, $filter, 
    PrivilegioService, SharedService) {

    $scope.privilegios = [];
    $scope.privilegio = {};
	
    /********** Servicios Callback **********/
        
    var getPrivilegioServiceSuccess = function(response){
    	$log.debug("Get Privilegio - Success");
    	$scope.privilegios = response;
    };

    var getPrivilegioServiceError = function(response){
     	$log.debug("Get Privilegio - Error"); 
    };

    var registrarPrivilegioSuccess = function(response){
        
    	$log.debug("Registrar Privilegio - Success");
    	$scope.privilegios.push($scope.privilegio);
    	$scope.privilegio = {};
    };

    var registrarPrivilegioError = function(response){
        $log.debug("Registrar Privilegio - Error");
    };

    var updatePrivilegioSuccess = function(response){
    	$log.debug("Update Privilegio - Success");
    	console.log("success :: ", response);
    	$scope.privilegio = response;
    };

    var updatePrivilegioError = function(response){
        $log.debug("Update Privilegio - Error");
    };

    var deletePrivilegioSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.privilegio = response;
    };

    var deletePrivilegioError = function(response){

    };

    /********** CRUD PRIVILEGIOS ***********/

    $scope.getPrivilegios = function(){
      	PrivilegioService.getPrivilegios().then(getPrivilegioServiceSuccess, getPrivilegioServiceError);
    };

    $scope.registrarPrivilegio = function(){
	PrivilegioService.registrarPrivilegio($scope.privilegio).then(registrarPrivilegioSuccess, registrarPrivilegioError);
    };

    $scope.updatePrivilegio = function(){
    	
    	PrivilegioService.updatePrivilegio($scope.privilegio).then(updatePrivilegioSuccess, updatePrivilegioError);
    };

    $scope.deletePrivilegio = function(privilegio){
    	$scope.privilegio = privilegio;
    	PrivilegioService.deletePrivilegio ($scope.privilegio).then(deletePrivilegioSuccess. deletePrivilegioError);
    };

    $scope.update = function(privilegio){
    	$scope.privilegio = privilegio;
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
        $scope.getPrivilegiosByPagina();
    });
    
    /*********************************************/
    
    var getPrivilegiosByPaginaSuccess = function(response){
        $log.debug("getPrivilegiosByPaginaError - Success");
        $scope.privilegios = response.lista;
        $scope.total = response.total;
    };
    
    var getPrivilegiosByPaginaError = function(response){
        console.log("getPrivilegiosByPaginaError  :: ", response);
    };
    
    $scope.getPrivilegiosByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        PrivilegioService.getPrivilegiosByPagina(objPagina).then(getPrivilegiosByPaginaSuccess, getPrivilegiosByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getPrivilegiosByPagina();
    };
    
    $scope.getPrivilegiosByPagina();
});