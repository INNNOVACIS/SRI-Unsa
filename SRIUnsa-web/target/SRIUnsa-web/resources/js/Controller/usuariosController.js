investigacionApp.controller('usuariosController', function($log, $scope, $location, $rootScope, $filter, 
    UsuariosService, SharedService) {

    $scope.users = [];
    $scope.usuario = {};
    $scope.paginas = [];
    
    $scope.paginacion = {
        total : 1000,
        paginaActual : 1,
        rango : 6,
        rangoPaginas: 10,
        data: [
            {id : 1, nombre : "Ali David", usuario : "Alicito", clave : "12345"},
            {id : 2, nombre : "Miluska A", usuario : "Alicito", clave : "12345"},
            {id : 3, nombre : "David Mal", usuario : "Alicito", clave : "12345"},
            {id : 4, nombre : "Monica Hu", usuario : "Alicito", clave : "12345"},
            {id : 5, nombre : "Nolberto ", usuario : "Alicito", clave : "12345"},
            {id : 6, nombre : "Andres Ca", usuario : "Alicito", clave : "12345"}
        ]
    };
    
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
            if(value.numero == paginaActual.numero)
                value.activo = true;
            else
                value.activo = false;
        });
        console.log("mandamos la pagina actual :: ", paginaActual);
    };
	
    var getUsuarioServiceSuccess = function(response){
    	$log.debug("Get Usuario - Success");
    	console.log("Success :: ", response);
    	$scope.users = response;
//        $scope.users = $scope.paginacion.data;
        $scope.paginas = getNumeroPaginas($scope.paginacion.total);
    };

    var getUsuarioServiceError = function(response){
     	$log.debug("Get Usuario - Error"); 
    };

    var registrarUsuarioSuccess = function(response){
    	$log.debug("Registrar Usuario - Success");
    	console.log("success :: ", response);
    	$scope.users.push(response);
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

    $scope.getUsuarios();
});