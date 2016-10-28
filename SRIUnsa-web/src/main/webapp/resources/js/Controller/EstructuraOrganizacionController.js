investigacionApp.controller('EstructuraOrganizacionController', function($log, $scope, $location, $rootScope, $filter, 
    EstructuraOrganizacionService, TipoNivelService, SharedService) {

    $scope.estructuraOrganizaciones = [];
    $scope.estructuraOrganizacion = {};
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

    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("Get tipoNivel - Success");
    	console.log("Success :: ", response);
    	$scope.niveles = response;
    };

    var getTipoNivelServiceError = function(response){
     	$log.debug("Get TipoNivel - Error"); 
    };

    var getEstructuraOrganizacionServiceSuccess = function(response){
    	$log.debug("Get EstructuraOrganizacion - Success");
        
        angular.forEach(response, function(superior, key) {
            angular.forEach(response, function(value, key) {
                if(superior.nidPadre == value.nidEstructuraOrganizacion){
                    superior.nombrePadre = value.snombreEstructuraOrganizacion;
                }
            });
            angular.forEach($scope.niveles, function(nivel, key) {
                if(superior.nidTipoNivel == nivel.nidTipoNivel){
                    superior.nombreNivel = nivel.snombreTipoNivel;
                }
            });
        });
        
    	$scope.estructuraOrganizaciones = response;
        $scope.paginas = getNumeroPaginas($scope.paginacion.total);
    };

    var getEstructuraOrganizacionServiceError = function(response){
     	$log.debug("Get EstructuraOrganizacion - Error"); 
    };

    var registrarEstructuraOrganizacionSuccess = function(response){
        
    	$log.debug("Registrar EstructuraOrganizacion - Success");
    	$scope.estructuraOrganizaciones.push($scope.estructuraOrganizacion);
    	$scope.estructuraOrganizacion = {};
    };

    var registrarEstructuraOrganizacionError = function(response){
        $log.debug("Registrar EstructuraOrganizacion - Error");
    };

    var updateEstructuraOrganizacionSuccess = function(response){
    	$log.debug("Update EstructuraOrganizacion - Success");
    	console.log("success :: ", response);
    	$scope.estructuraOrganizacion = response;
    };

    var updateEstructuraOrganizacionError = function(response){
        $log.debug("Update EstructuraOrganizacion - Error");
    };

    var deleteEstructuraOrganizacionSuccess = function(response){
    	$log.debug("Delete EstructuraOrganizacion - Success");
    	console.log("success :: ", response);
    	$scope.estructuraOrganizacion = response;
    };

    var deleteEstructuraOrganizacionError = function(response){

    };

    /********** CRUD EstructuraOrganizaciones ***********/

    $scope.getListaTipoNivel = function(){
      	TipoNivelService.getListaTipoNivel().then(getTipoNivelServiceSuccess, getTipoNivelServiceError);
    }

    $scope.getEstructuraOrganizaciones = function(){
      	EstructuraOrganizacionService.getEstructuraOrganizaciones().then(getEstructuraOrganizacionServiceSuccess, getEstructuraOrganizacionServiceError);
    };

    $scope.registrarEstructuraOrganizacion = function(){

        $scope.estructuraOrganizacion.nidPadre = $scope.superior.nidEstructuraOrganizacion;
        $scope.estructuraOrganizacion.nidTipoNivel = $scope.nivel.nidTipoNivel;
        
	EstructuraOrganizacionService.registrarEstructuraOrganizacion($scope.estructuraOrganizacion).then(registrarEstructuraOrganizacionSuccess, registrarEstructuraOrganizacionError);
    };

    $scope.updateEstructuraOrganizacion = function(){
    	
    	EstructuraOrganizacionService.updateEstructuraOrganizacion($scope.estructuraOrganizacion).then(updateEstructuraOrganizacionSuccess, updateAreaInvestigacionError);
    };

    $scope.deleteEstructuraOrganizacion = function(estructuraOrganizacion){
    	$scope.estructuraOrganizacion = estructuraOrganizacion;
    	EstructuraOrganizacionService.deleteEstructuraOrganizacion ($scope.estructuraOrganizacion).then(deleteEstructuraOrganizacionSuccess. deleteAreaInvestigacionError);
    };

    $scope.update = function(estructuraOrganizacion){
    	$scope.estructuraOrganizacion = estructuraOrganizacion;
    };

    $scope.getListaTipoNivel();
    $scope.getEstructuraOrganizaciones();
});