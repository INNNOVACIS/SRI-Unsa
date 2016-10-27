investigacionApp.controller('FondoConcursableController', function($log, $scope, $location, $rootScope, $filter, 
    FondoConcursableService, SharedService) {

    $scope.fondos = [];
    $scope.fondo = {};
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
        
    var getFondoServiceSuccess = function(response){
    	$log.debug("Get Fondo - Success");
    	$scope.fondos = response;
        $scope.paginas = getNumeroPaginas($scope.paginacion.total);
    };

    var getFondoServiceError = function(response){
     	$log.debug("Get Fondo - Error"); 
    };

    var registrarFondoSuccess = function(response){
        
    	$log.debug("Registrar Fondo - Success");
    	$scope.fondos.push($scope.fondo);
    	$scope.fondo = {};
    };

    var registrarFondoError = function(response){
        $log.debug("Registrar Fondo - Error");
    };

    var updateFondoSuccess = function(response){
    	$log.debug("Update Fondo - Success");
    	console.log("success :: ", response);
    	$scope.fondo = response;
    };

    var updateFondoError = function(response){
        $log.debug("Update Fondo - Error");
    };

    var deleteFondoSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.fondo = response;
    };

    var deleteFondoError = function(response){

    };

    /********** CRUD FONDOS ***********/

    $scope.getFondos = function(){
      	FondoConcursableService.getFondos().then(getFondoServiceSuccess, getFondoServiceError);
    };

    $scope.registrarFondo = function(){
	FondoConcursableService.registrarFondo($scope.fondo).then(registrarFondoSuccess, registrarFondoError);
    };

    $scope.updateFondo = function(){
    	
    	FondoConcursableService.updateFondo($scope.fondo).then(updateFondoSuccess, updateFondoError);
    };

    $scope.deleteFondo = function(fondo){
    	$scope.fondo = fondo;
    	FondoConcursableService.deleteFondo ($scope.fondo).then(deleteFondoSuccess. deleteFondoError);
    };

    $scope.update = function(fondo){
    	$scope.fondo = fondo;
    };

    $scope.getFondos();
});