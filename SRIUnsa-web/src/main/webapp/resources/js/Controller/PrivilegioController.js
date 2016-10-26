investigacionApp.controller('PrivilegioController', function($log, $scope, $location, $rootScope, $filter, 
    PrivilegioService, SharedService) {

    $scope.privilegios = [];
    $scope.privilegio = {};
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
	
    /********** Servicios Callback **********/
        
    var getPrivilegioServiceSuccess = function(response){
    	$log.debug("Get Privilegio - Success");
    	$scope.privilegios = response;
        $scope.paginas = getNumeroPaginas($scope.paginacion.total);
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

    $scope.getPrivilegios();
});