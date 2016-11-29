investigacionApp.controller('PrivilegioController', function($log, $scope, PrivilegioService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.privilegios = [];
    $scope.privilegio = {};
	
    /********** Servicios Callback **********/
        
    var getPrivilegioServiceSuccess = function(response){
    	$log.debug("GetPrivilegio - Success");
        console.log("Respuesta :: ", response);
    	$scope.privilegios = response;
    };

    var getPrivilegioServiceError = function(response){
     	$log.debug("Get Privilegio - Error");
        console.log("Respuesta :: ", response);
    };

    var registrarPrivilegioSuccess = function(response){
    	$log.debug("RegistrarPrivilegio - Success");
        console.log("Respuesta :: ", response);
    	$scope.getPrivilegiosByPagina();
    	$scope.privilegio = {};
    };

    var registrarPrivilegioError = function(response){
        $log.debug("Registrar Privilegio - Error");
        console.log("Respuesta :: ", response);
    };

    var updatePrivilegioSuccess = function(response){
    	$log.debug("UpdatePrivilegio - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getPrivilegiosByPagina();
    };

    var updatePrivilegioError = function(response){
        $log.debug("UpdatePrivilegio - Error");
        console.log("Respuesta :: ", response);
    };

    var deletePrivilegioSuccess = function(response){
    	$log.debug("DeletePrivilegio - Success");
    	console.log("Respuesta :: ", response);
    	$scope.privilegio = response;
    };

    var deletePrivilegioError = function(response){
        $log.debug("DeletePrivilegio - Success");
    	console.log("Respuesta :: ", response);
    };

    /********** CRUD PRIVILEGIOS ***********/

    $scope.getPrivilegios = function(){
      	PrivilegioService.getPrivilegios().then(getPrivilegioServiceSuccess, getPrivilegioServiceError);
    };

    $scope.registrarPrivilegio = function(){
        $scope.privilegio.suserCreacion = $scope.sharedService.nombreUsuario;
        $scope.privilegio.sestado = 'A';
	PrivilegioService.registrarPrivilegio($scope.privilegio).then(registrarPrivilegioSuccess, registrarPrivilegioError);
    };

    $scope.updatePrivilegio = function(){
    	$scope.privilegio.suserModificacion = $scope.sharedService.nombreUsuario;
        $scope.privilegio.sestado = 'A';
    	PrivilegioService.updatePrivilegio($scope.privilegio).then(updatePrivilegioSuccess, updatePrivilegioError);
    };

    $scope.deletePrivilegio = function(privilegio){
    	$scope.privilegio = privilegio;
    	PrivilegioService.deletePrivilegio ($scope.privilegio).then(deletePrivilegioSuccess, deletePrivilegioError);
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
        $log.debug("getPrivilegiosByPagina - Success");
        $scope.privilegios = [];
        $scope.privilegios = response.lista;
        $scope.total = response.total;
    };
    
    var getPrivilegiosByPaginaError = function(response){
        $log.debug("getPrivilegiosByPagina - Success");
        console.log("Respuesta  :: ", response);
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