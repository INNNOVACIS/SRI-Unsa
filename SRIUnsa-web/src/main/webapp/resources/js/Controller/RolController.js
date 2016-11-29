investigacionApp.controller('RolController', function($log, $scope, RolService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.roles = [];
    $scope.rol = {};
	
    var getRolServiceSuccess = function(response){
    	$log.debug("GetRol - Success");
    	console.log("Respuesta :: ", response);
    };
    var getRolServiceError = function(response){
     	$log.debug("Get Rol - Error");
        console.log("Respuesta :: ", response);
    };

    var registrarRolSuccess = function(response){
    	$log.debug("RegistrarRol - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getRolesByPagina();
    	$scope.rol = {};
    };

    var registrarRolError = function(response){
        $log.debug("RegistrarRol - Error");
    	console.log("Respuesta :: ", response);
        $scope.getRolesByPagina();
    };

    var updateRolSuccess = function(response){
    	$log.debug("UpdateUser - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getRolesByPagina();
    };

    var updateRolError = function(response){
        $log.debug("UpdateUser - Error");
    	console.log("Respuesta :: ", response);
    };

    var deleteRolSuccess = function(response){
    	$log.debug("DeleteUser - Success");
    	console.log("Respuesta :: ", response);
    	$scope.rol = response;
    };

    var deleteRolError = function(response){
        $log.debug("DeleteUser - Error");
    	console.log("Respuesta :: ", response);
    };

    /********** CRUD ROLES ***********/

    $scope.getRoles = function(){
      	RolService.getRoles().then(getRolServiceSuccess, getRolServiceError);
    };

    $scope.registrarRol = function(){
        $scope.rol.suserCreacion = $scope.sharedService.nombreUsuario;
        $scope.rol.sestado = 'A';
	RolService.registrarRol($scope.rol).then(registrarRolSuccess, registrarRolError);
    };

    $scope.updateRol = function(){
        $scope.rol.suserModificacion = $scope.sharedService.nombreUsuario;
        $scope.rol.sestado = 'A';
    	RolService.updateRol($scope.rol).then(updateRolSuccess, updateRolError);
    };

    $scope.deleteRol = function(rol){
    	$scope.rol = rol;    	
    	RolService.deleteRol ($scope.rol).then(deleteRolSuccess, deleteRolError);
    };

    $scope.update = function(rol){
    	$scope.rol = rol;
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
        $scope.getRolesByPagina();
    });
    
    /*********************************************/
    
    var getRolesByPaginaSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        $scope.roles = [];
        $scope.roles = response.lista;
        $scope.total = response.total;
    };
    
    var getRolesByPaginaError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getRolesByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        RolService.getRolesByPagina(objPagina).then(getRolesByPaginaSuccess, getRolesByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getRolesByPagina();
    };
    
    $scope.getRolesByPagina();
});