investigacionApp.controller('UsuarioRolController', function($log, $scope, UsuarioRolService, RolService) {

    $scope.usuarioRoles = [];
    $scope.usuarioRol = {};
    $scope.roles = [];
    $scope.rol = {};
    
    /************ Callbacks *************/
    
    var getRolServiceSuccess = function(response){
        console.log("GetRol Success :: ", response);
        $scope.roles = response;
    };
    var getRolServiceError = function(response){
        console.log("GetRol - Error :: ", response);
    };
    var updateUsuarioRolSuccess = function(response){
        $log.debug("Update UsuarioRol - Success");
        actualizarVista();
        console.log(response);
    };
    var updateUsuarioRolError = function(response){
        $log.debug("Update UsuarioRol - Error");
        console.log(response);
    };

    /********** CRUD USUARIOS ***********/

    $scope.getRoles = function(){
      	RolService.getRoles().then(getRolServiceSuccess, getRolServiceError);
    };

    $scope.update = function(usuarioRol){
    	$scope.usuarioRol = usuarioRol;
        $scope.rol = seleccionarRol(usuarioRol.idRol);
    };
    
    $scope.actualizarUsuarioRol = function (){
        var usuarioRol = {nidUsuarioRol:  $scope.usuarioRol.nidUsuarioRol, nidUsuario: $scope.usuarioRol.idUsuario, nidRol: $scope.rol.nidRol};
        UsuarioRolService.updateUsuarioRol(usuarioRol).then(updateUsuarioRolSuccess, updateUsuarioRolError);
    };
    
    var actualizarVista = function(){
        $scope.usuarioRol.nombreRol = $scope.rol.snombreRol;
    };
    
    var seleccionarRol = function(idRol){
        var respuesta = null;
        angular.forEach($scope.roles, function(obj, key){
            if(obj.nidRol === idRol){
                respuesta = obj;
            }
        });
        return respuesta;
    };
    
    /**************** PAGINACION *****************/
    
    $scope.rangoPaginas = [5,10,20,100];
    $scope.currentPage = 1;
    $scope.currentRango = $scope.rangoPaginas[0];
    $scope.maxSize = 5;
    $scope.total = 0;
    $scope.buscar = "";

    $scope.numPages = function () {
      return Math.ceil($scope.total / $scope.currentRango);
    };

    $scope.$watch('currentPage + currentRango', function() {
        $scope.getUsuariosRolByPagina();
    });
    
    /*********************************************/
    
    var getUsuariosRolByPaginaSuccess = function(response){
        $log.debug("Get UsuariosRol - Success");
        $scope.usuarioRoles = response.lista;
        $scope.total = response.total;
    };
    
    var getUsuariosRolByPaginaError = function(response){
        console.log("Get UsuariosRol - Error :: ", response);
    };
    
    $scope.getUsuariosRolByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        UsuarioRolService.getUsuariosRolByPagina(objPagina).then(getUsuariosRolByPaginaSuccess, getUsuariosRolByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getUsuariosRolByPagina();
    };
    
    $scope.getRoles();
    $scope.getUsuariosRolByPagina();
 
});