investigacionApp.controller('TipoAsesoriaController', function($log, $scope, $location, $rootScope, $filter, 
    TipoAsesoriaService, SharedService) {

    $scope.asesorias = [];
    $scope.asesoria = {};
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
        
    var getAsesoriaServiceSuccess = function(response){
    	$log.debug("Get Asesoria - Success");
    	$scope.asesorias = response;
        $scope.paginas = getNumeroPaginas($scope.paginacion.total);
    };

    var getAsesoriaServiceError = function(response){
     	$log.debug("Get Asesoria - Error"); 
    };

    var registrarAsesoriaSuccess = function(response){
        
    	$log.debug("Registrar Asesoria - Success");
    	$scope.asesorias.push($scope.asesoria);
    	$scope.asesoria = {};
    };

    var registrarAsesoriaError = function(response){
        $log.debug("Registrar Asesoria - Error");
    };

    var updateAsesoriaSuccess = function(response){
    	$log.debug("Update Asesoria - Success");
    	console.log("success :: ", response);
    	$scope.asesoria = response;
    };

    var updateAsesoriaError = function(response){
        $log.debug("Update Asesoria - Error");
    };

    var deleteAsesoriaSuccess = function(response){
    	$log.debug("Delete Asesoria - Success");
    	console.log("success :: ", response);
    	$scope.asesoria = response;
    };

    var deleteAsesoriaError = function(response){

    };

    /********** CRUD ASESORIAS ***********/

    $scope.getAsesorias = function(){
      	TipoAsesoriaService.getAsesorias().then(getAsesoriaServiceSuccess, getAsesoriaServiceError);
    };

    $scope.registrarAsesoria = function(){
	TipoAsesoriaService.registrarAsesoria($scope.asesoria).then(registrarAsesoriaSuccess, registrarAsesoriaError);
    };

    $scope.updateAsesoria = function(){
    	
    	TipoAsesoriaService.updateAsesoria($scope.asesoria).then(updateAsesoriaSuccess, updateAsesoriaError);
    };

    $scope.deleteAsesoria = function(asesoria){
    	$scope.asesoria = asesoria;
    	TipoAsesoriaService.deleteAsesoria ($scope.asesoria).then(deleteAsesoriaSuccess. deleteAsesoriaError);
    };

    $scope.update = function(asesoria){
    	$scope.asesoria = asesoria;
    };

    $scope.getAsesorias();
});