investigacionApp.controller('EstructuraAreaInvestigacionController', function($log, $scope, $location, $rootScope, $filter, 
    EstructuraAreaInvestigacionService, SharedService) {

    $scope.areaInvestigaciones = [];
    $scope.areaInvestigacion = {};
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
        
    var getAreaInvestigacionServiceSuccess = function(response){
    	$log.debug("Get AreaInvestigacion - Success");
    	$scope.areaInvestigaciones = response;
        $scope.paginas = getNumeroPaginas($scope.paginacion.total);
    };

    var getAreaInvestigacionServiceError = function(response){
     	$log.debug("Get AreaInvestigacion - Error"); 
    };

    var registrarAreaInvestigacionSuccess = function(response){
        
    	$log.debug("Registrar AreaInvestigacion - Success");
    	$scope.areaInvestigaciones.push($scope.areaInvestigacion);
    	$scope.areaInvestigacion = {};
    };

    var registrarAreaInvestigacionError = function(response){
        $log.debug("Registrar AreaInvestigacion - Error");
    };

    var updateAreaInvestigacionSuccess = function(response){
    	$log.debug("Update AreaInvestigacion - Success");
    	console.log("success :: ", response);
    	$scope.areaInvestigacion = response;
    };

    var updateAreaInvestigacionError = function(response){
        $log.debug("Update AreaInvestigacion - Error");
    };

    var deleteAreaInvestigacionSuccess = function(response){
    	$log.debug("Delete AreaInvestigacion - Success");
    	console.log("success :: ", response);
    	$scope.areaInvestigacion = response;
    };

    var deleteAreaInvestigacionError = function(response){

    };

    /********** CRUD AreaInvestigaciones ***********/

    $scope.getAreaInvestigaciones = function(){
      	EstructuraAreaInvestigacionService.getAreaInvestigaciones().then(getAreaInvestigacionServiceSuccess, getAreaInvestigacionServiceError);
    };

    $scope.registrarAreaInvestigacion = function(){
	EstructuraAreaInvestigacionService.registrarAreaInvestigacion($scope.areaInvestigacion).then(registrarAreaInvestigacionSuccess, registrarAreaInvestigacionError);
    };

    $scope.updateAreaInvestigacion = function(){
    	
    	EstructuraAreaInvestigacionService.updateAreaInvestigacion($scope.areaInvestigacion).then(updateAreaInvestigacionSuccess, updateAreaInvestigacionError);
    };

    $scope.deleteAreaInvestigacion = function(areaInvestigacion){
    	$scope.areaInvestigacion = areaInvestigacion;
    	EstructuraAreaInvestigacionService.deleteAreaInvestigacion ($scope.areaInvestigacion).then(deleteAreaInvestigacionSuccess. deleteAreaInvestigacionError);
    };

    $scope.update = function(areaInvestigacion){
    	$scope.areaInvestigacion = areaInvestigacion;
    };

    $scope.getAreaInvestigaciones();
});