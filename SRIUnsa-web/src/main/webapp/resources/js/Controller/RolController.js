investigacionApp.controller('RolController', function($log, $scope, $location, $rootScope, $filter, 
    RolService, SharedService) {

    $scope.roles = [];
    $scope.rol = {};
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
	
    var getRolServiceSuccess = function(response){
    	$log.debug("Get Rol - Success");
    	console.log("Success :: ", response);
    	$scope.roles = response;
//        $scope.users = $scope.paginacion.data;
        $scope.paginas = getNumeroPaginas($scope.paginacion.total);
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

    $scope.getRoles();
});