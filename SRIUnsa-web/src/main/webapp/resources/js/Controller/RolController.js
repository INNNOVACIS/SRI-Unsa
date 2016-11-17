investigacionApp.controller('RolController', function($log, $scope, $location, $rootScope, $filter, 
    RolService, SharedService) {

    $scope.roles = [];
    $scope.rol = {};
	
    var getRolServiceSuccess = function(response){
    	$log.debug("Get Rol - Success");
    	console.log("Success :: ", response);
    	$scope.roles = response;

    };

    var getRolServiceError = function(response){
     	$log.debug("Get Rol - Error"); 
    };

    var registrarRolSuccess = function(response){
        
    	$log.debug("Registrar Rol - Success");
    	console.log("success :: ", response);
        
    	$scope.roles.push($scope.rol);
    	$scope.rol = {};
    };

    var registrarRolError = function(response){

    };

    var updateRolSuccess = function(response){
    	$log.debug("Update User - Success");
    	console.log("success :: ", response);
    	$scope.rol = response;
    };

    var updateRolError = function(response){

    };

    var deleteRolSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.rol = response;
    };

    var deleteRolError = function(response){

    };

    /********** CRUD ROLES ***********/

    $scope.getRoles = function(){
      	RolService.getRoles().then(getRolServiceSuccess, getRolServiceError);
    };

    $scope.registrarRol = function(){
    	console.log("Rol :: ", $scope.rol);
        //$scope.rol.nidRol = 2;
        //$scope.rol.SUserCreacion = "admin";
        //$scope.rol.SUserModificacion = "admin";
        //$scope.rol.SEstado = "A";
        console.log("Rol Completo :: ", $scope.rol);
	RolService.registrarRol($scope.rol).then(registrarRolSuccess, registrarRolError);
    };

    $scope.updateRol = function(){
    	
    	RolService.updateRol($scope.rol).then(updateRolSuccess, updateRolError);
    };

    $scope.deleteRol = function(rol){
    	$scope.rol = rol;
    	//$scope.rol.sEstado = "0";
    	RolService.deleteRol ($scope.rol).then(deleteRolSuccess. deleteRolError);
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