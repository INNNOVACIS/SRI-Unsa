investigacionApp.controller('usuariosController', function($log, $scope, $location,
    UsuariosService) {

    $scope.users = [];
    $scope.usuario = {}; 
	
    var getUsuarioServiceSuccess = function(response){
    	$log.debug("Get Usuario - Success");    	
    };

    var getUsuarioServiceError = function(response){
     	$log.debug("Get Usuario - Error"); 
    };

    var registrarUsuarioSuccess = function(response){
    	$log.debug("Registrar Usuario - Success");
    	$scope.users.push($scope.usuario);
    	$scope.usuario = {};
    };

    var registrarUsuarioError = function(response){

    };

    var updateUsuarioSuccess = function(response){
    	$log.debug("Update User - Success");
    	console.log("success :: ", response);
    	$scope.usuario = response;
    };

    var updateUsuarioError = function(response){

    };

    var deleteUsuarioSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.usuario = response;
    };

    var deleteUsuarioError = function(response){

    };

    /********** CRUD USUARIOS ***********/

    $scope.getUsuarios = function(){
      	UsuariosService.getUsuarios().then(getUsuarioServiceSuccess, getUsuarioServiceError);
    };

    $scope.registrarUsuario = function(){
    	console.log("Usuario :: ", $scope.usuario);
        //$scope.usuario.nidUsuario = 2;
        //$scope.usuario.SUserCreacion = "admin";
        //$scope.usuario.SUserModificacion = "admin";
        //$scope.usuario.SEstado = "A";
        console.log("Usuario Completo :: ", $scope.usuario);
	UsuariosService.registrarUsuario($scope.usuario).then(registrarUsuarioSuccess, registrarUsuarioError);
    };

    $scope.updateUsuario = function(){
    	UsuariosService.updateUsuario($scope.usuario).then(updateUsuarioSuccess, updateUsuarioError);
    };

    $scope.deleteUsuario = function(user){
    	$scope.usuario = user;
    	UsuariosService.deleteUsuario($scope.usuario).then(deleteUsuarioSuccess. deleteUsuarioError);
    };

    $scope.update = function(user){
    	$scope.usuario = user;
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
    });
    
    /*********************************************/
    
    var paginacionUsuarioSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        $scope.users = response.lista;
        $scope.total = response.total;
    };
    
    var paginacionUsuarioError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getUsuariosByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : {susuarioLogin : $scope.buscar}};
        UsuariosService.paginacionUsuario(objPagina).then(paginacionUsuarioSuccess, paginacionUsuarioError);
    };
    
    $scope.getUsuariosByPagina();
 
});