investigacionApp.controller('usuariosController', function($log, $scope, $location, $rootScope, $filter, 
    UsuariosService, SharedService) {

    $scope.users = [];
    $scope.usuario = {};
    $scope.paginas = [];
//    $scope.buscar = "admin";
    
    
    var getNumeroPaginas = function(total) {
        
        var paginas = [];
        
        for(var i = 0; i < $scope.paginacion.rangoPaginas; i++) {
            var pagina = {};
            pagina.activo = false;
            pagina.numero = $scope.paginacion.paginaActual + i;
            paginas.push(pagina);
        }
        return paginas;
    };
    
    $scope.changePagina = function (paginaActual) {
        if(($scope.paginas[$scope.paginas.length - 1].numero - paginaActual.numero) < 1 ){
            $scope.paginas = [];
            if(paginaActual.numero + $scope.paginacion.rangoPaginas > $scope.paginacion.total / $scope.paginacion.rangoPaginas){
                $scope.paginacion.paginaActual = ($scope.paginacion.total / $scope.paginacion.rangoPaginas) - $scope.paginacion.rangoPaginas;
            } else {
                $scope.paginacion.paginaActual = paginaActual.numero;
            }
            $scope.paginas = getNumeroPaginas($scope.paginacion.total);
        } else {
            if((paginaActual.numero - $scope.paginas[0].numero) < 1 ){
                $scope.paginas = [];
                if(paginaActual.numero - $scope.paginacion.rangoPaginas <= 0){
                    $scope.paginacion.paginaActual = 1;
                } else {
                    $scope.paginacion.paginaActual = paginaActual.numero - $scope.paginacion.rangoPaginas;
                }
                $scope.paginas = getNumeroPaginas($scope.paginacion.total);
            }
        }
        angular.forEach($scope.paginas, function(value, key){
            if(value.numero === paginaActual.numero)
                value.activo = true;
            else
                value.activo = false;
        });
        console.log("mandamos la pagina actual :: ", paginaActual);
    };
	
    var getUsuarioServiceSuccess = function(response){
    	$log.debug("Get Usuario - Success");
    	console.log("Success :: ", response);
    	$scope.users = response.lista;
        $scope.paginacion.total = response.total;
//        $scope.users = $scope.paginacion.data;
        //$scope.paginas = getNumeroPaginas($scope.paginacion.total);
    };

    var getUsuarioServiceError = function(response){
     	$log.debug("Get Usuario - Error"); 
    };

    var registrarUsuarioSuccess = function(response){
        
    	$log.debug("Registrar Usuario - Success");
    	console.log("success :: ", response);
        
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
    	//$scope.usuario.sEstado = "0";
    	UsuariosService.deleteUsuario($scope.usuario).then(deleteUsuarioSuccess. deleteUsuarioError);
    };

    $scope.update = function(user){
    	$scope.usuario = user;
    };
    
    /** prueba paginacion **/
    
    $scope.pageDirectiva = {
        currentPage : 1,
        rango : 3,
        total : 12,
        filtro : {},
    };
    
    var paginacionUsuarioSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
    	console.log("Success Paginacion :: ", response);
    	$scope.users = response.lista;
        $scope.controladorTotal = response.total; 
    };
    
    var paginacionUsuarioError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getUsuariosByPagina = function(pagina, rango){
        $scope.pageDirectiva.filtro.susuarioLogin = $scope.buscar;
        $scope.pageDirectiva.currentPage = pagina;
        $scope.pageDirectiva.rango = rango;
        UsuariosService.paginacionUsuario($scope.pageDirectiva).then(paginacionUsuarioSuccess, paginacionUsuarioError);
    };
    
    
    $scope.getUsuariosByPagina(1, 5);
    
});