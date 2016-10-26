investigacionApp.controller('TipoInvestigacionController', function($log, $scope, $location, $rootScope, $filter, 
    TipoInvestigacionService, SharedService) {

    $scope.investigaciones = [];
    $scope.investigacion = {};
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
        
    var getInvestigacionServiceSuccess = function(response){
    	$log.debug("Get Investigacion - Success");
    	$scope.investigaciones = response;
        $scope.paginas = getNumeroPaginas($scope.paginacion.total);
    };

    var getInvestigacionServiceError = function(response){
     	$log.debug("Get Investigacion - Error"); 
    };

    var registrarInvestigacionSuccess = function(response){
        
    	$log.debug("Registrar Investigacion - Success");
    	$scope.investigaciones.push($scope.investigacion);
    	$scope.investigacion = {};
    };

    var registrarInvestigacionError = function(response){
        $log.debug("Registrar Investigacion - Error");
    };

    var updateInvestigacionSuccess = function(response){
    	$log.debug("Update Investigacion - Success");
    	console.log("success :: ", response);
    	$scope.investigacion = response;
    };

    var updateInvestigacionError = function(response){
        $log.debug("Update Investigacion - Error");
    };

    var deleteInvestigacionSuccess = function(response){
    	$log.debug("Delete Investigacion - Success");
    	console.log("success :: ", response);
    	$scope.investigacion = response;
    };

    var deleteInvestigacionError = function(response){

    };

    /********** CRUD INVESTIGACIONES ***********/

    $scope.getInvestigaciones = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getInvestigacionServiceSuccess, getInvestigacionServiceError);
    };

    $scope.registrarInvestigacion = function(){
	TipoInvestigacionService.registrarInvestigacion($scope.investigacion).then(registrarInvestigacionSuccess, registrarInvestigacionError);
    };

    $scope.updateInvestigacion = function(){
    	
    	TipoInvestigacionService.updateInvestigacion($scope.investigacion).then(updateInvestigacionSuccess, updateInvestigacionError);
    };

    $scope.deleteInvestigacion = function(investigacion){
    	$scope.investigacion = investigacion;
    	TipoInvestigacionService.deleteInvestigacion ($scope.investigacion).then(deleteInvestigacionSuccess. deleteInvestigacionError);
    };

    $scope.update = function(investigacion){
    	$scope.investigacion = investigacion;
    };

    $scope.getInvestigaciones();
});