investigacionApp.controller('UsuariosController', function($log, $scope, UsuariosService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.users = [];
    $scope.usuario = {};
    $scope.estados = ['A','I'];

    var registrarUsuarioSuccess = function(response){
    	$log.debug("Registrar Usuario - Success");
        console.log("Respuesta :: ", response.body);
    	$scope.usuario = {};
        $scope.getUsuariosByPagina();
    };
    var registrarUsuarioError = function(response){
        $log.debug("RegistrarUsuario - Error");
        console.log("Respuesta :: ", response);
    };

    var updateUsuarioSuccess = function(response){
    	$log.debug("UpdateUsuario - Success");
    	console.log("Respuesta :: ", response.body);
    	$scope.getUsuariosByPagina();
    };
    var updateUsuarioError = function(response){
        $log.debug("UpdateUsuario - Error");
    	console.log("Respuesta :: ", response);
    };

    var deleteUsuarioSuccess = function(response){
    	$log.debug("DeleteUsuario - Success");
        console.log("Respuesta :: ", response.body);
    	$scope.getUsuariosByPagina();
    };
    var deleteUsuarioError = function(response){
        $log.debug("DeleteUsuario - Error");
        console.log("Respuesta :: ", response);
    };

    /********** CRUD USUARIOS ***********/

    $scope.registrarUsuario = function(){
        var usuario = {
            susuarioLogin : $scope.usuario.susuarioLogin,
            susuarioPassword : $scope.usuario.susuarioPassword,
            susuarioEmail : $scope.usuario.susuarioEmail,
            suserCreacion : $scope.sharedService.nombreUsuario,
            sestado : 'A'
        };
	UsuariosService.registrarUsuario(usuario).then(registrarUsuarioSuccess, registrarUsuarioError);
    };

    $scope.updateUsuario = function(){
        var usuario = {
            nidUsuario : $scope.usuario.nidUsuario,
            susuarioLogin : $scope.usuario.susuarioLogin,
            susuarioEmail : $scope.usuario.susuarioEmail,
            susuarioPassword : $scope.usuario.susuarioPassword,
            suserCreacion : $scope.usuario.suserCreacion,
            dfechaCreacion : $scope.usuario.dfechaCreacion,
            suserModificacion : $scope.sharedService.nombreUsuario,
            sestado : $scope.usuario.sestado
        };
    	UsuariosService.updateUsuario(usuario).then(updateUsuarioSuccess, updateUsuarioError);
    };

    $scope.deleteUsuario = function(user){
    	$scope.usuario = user;
    	UsuariosService.deleteUsuario($scope.usuario).then(deleteUsuarioSuccess, deleteUsuarioError);
    };

    $scope.update = function(user){
    	$scope.usuario = user;
    };
    
    $scope.Cerrar = function(){
        $scope.usuario = {};
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
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : {susuarioLogin : $scope.buscar}};
        UsuariosService.paginacionUsuario(objPagina).then(paginacionUsuarioSuccess, paginacionUsuarioError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getUsuariosByPagina();
    };
    
    $scope.getUsuariosByPagina();
 
});