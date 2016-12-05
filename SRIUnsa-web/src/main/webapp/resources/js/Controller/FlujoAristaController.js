investigacionApp.controller('FlujoAristaController', function($log, $scope, FlujoAristaService, FlujoActorService, EstadoService) {

    $scope.flujoActores = [];

    /************************ Callback **************************/

    var getFlujoActoresSuccess = function(response){
        $log.debug("GetActores - Success");
        console.log("Respuesta :: ", response);
        $scope.flujoActores = response;
    };
    
    var getFlujoActoresError = function(response){
        $log.debug("GetActores - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getEstadosSuccess = function(response){
        $log.debug("GetEstados - Success");
        console.log("Respuesta :: ", response);
        $scope.estados = response.body;
    };
    
    var getEstadosError = function(response){
        $log.debug("GetEstados - Error");
        console.log("Respuesta :: ", response);
    };
    
    var RegistrarFlujoAristaSuccess = function(response){
        $log.debug("RegistrarFlujoArista - Success");
        console.log("Respuesta :: ", response);
        if(response !== -1){
            $scope.getFlujoAristaByPagina(); /*actualizacion la paginacion*/
        }
    };
    
    var RegistrarFlujoAristaError = function(response){
        $log.debug("RegistrarFlujoArista - Error");
        console.log("Respuesta :: ", response);  
    };
    
    var ActualizarFlujoAristaSuccess = function(response){
        $log.debug("ActualizarFlujoArista - Success");
        console.log("Respuesta :: ", response);
        if(response === true){
            $scope.getFlujoAristaByPagina(); /*actualizacion la paginacion*/
        }
    };
    
    var ActualizarFlujoAristaError = function(response){
        $log.debug("ActualizarFlujoArista - Success");
        console.log("Respuesta :: ", response);
    };
    
    var EliminarFlujoAristaSuccess = function(response){
        $log.debug("EliminarFlujoArista - Success");
        console.log("Respuesta :: ", response);
        if(response === true){
            $scope.getFlujoAristaByPagina(); /*actualizacion la paginacion*/
        }
    };
    
    var EliminarFlujoAristaError = function(response){
        $log.debug("EliminarFlujoArista - Error");
        console.log("Respuesta :: ", response); 
    };
    
    /************************ Servicios **************************/
    
    $scope.getFlujoActores = function(){
        FlujoActorService.getActores().then(getFlujoActoresSuccess, getFlujoActoresError);
    };
    
    $scope.getEstados = function(){
        EstadoService.getEstados().then(getEstadosSuccess, getEstadosError);
    };
    
    $scope.RegistrarFlujoArista = function(){
        var flujoArista = {
            sidFlujoActorDestino : $scope.flujoActorDestino.nidFlujoActor,
            sidFlujoActorOrigen : $scope.flujoActorOrigen.nidFlujoActor,
            snombreArista : $scope.flujoAristaNombre,
            nidEstado : $scope.estado.nidEstado, 
            sflujo : 'SRI',
            sestado: 'A'
        };
        FlujoAristaService.RegistrarFlujoArista(flujoArista).then(RegistrarFlujoAristaSuccess, RegistrarFlujoAristaError);
    };
    
    $scope.ActualizarFlujoArista = function(){
        var flujoArista = {
            nidArista : $scope.flujoAristaActor.nidArista,
            sidFlujoActorDestino : $scope.flujoActorDestino.nidFlujoActor,
            sidFlujoActorOrigen : $scope.flujoActorOrigen.nidFlujoActor,
            snombreArista : $scope.flujoAristaActor.snombreArista,
            nidEstado : $scope.estadoFlujo.nidEstado, 
            sflujo : 'SRI',
            sestado: 'A'
        };
        FlujoAristaService.ActualizarFlujoArista(flujoArista).then(ActualizarFlujoAristaSuccess, ActualizarFlujoAristaError);
    };
    
    $scope.EliminarFlujoArista = function(flujoAristaEliminar){
        $scope.flujoAristaActor = flujoAristaEliminar;
        var flujoArista = {
            nidArista : $scope.flujoAristaActor.nidArista,
            sidFlujoActorDestino : $scope.flujoAristaActor.nidActorDestino,
            sidFlujoActorOrigen : $scope.flujoAristaActor.nidActorOrigen,
            snombreArista : $scope.flujoAristaActor.snombreArista,
            sflujo : $scope.flujoAristaActor.sflujo,
            sestado: 'I'
        };
        FlujoAristaService.EliminarFlujoArista(flujoArista).then(EliminarFlujoAristaSuccess, EliminarFlujoAristaError);
    };
    
    $scope.actualizar = function(flujoAristaActor){
        console.log($scope.flujoArista);
    	$scope.flujoAristaActor = flujoAristaActor;
        $scope.flujoActorOrigen = seleccionarActor(flujoAristaActor.nidActorOrigen);
        $scope.flujoActorDestino = seleccionarActor(flujoAristaActor.nidActorDestino);
        $scope.estadoFlujo = seleccionarEstado(flujoAristaActor.nidEstado);
    };
    
    var seleccionarActor = function(idActor){
        var respuesta = null;
        angular.forEach($scope.flujoActores, function(obj, key){
            if(obj.nidFlujoActor === idActor){
                respuesta = obj;
            }
        });
        return respuesta;
    };
    
    var seleccionarEstado = function(idEstado){
        var respuesta = null;
        angular.forEach($scope.estados, function(obj, key){
            if(obj.nidEstado === idEstado){
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

    $scope.numPages = function () {
      return Math.ceil($scope.total / $scope.currentRango);
    };

    $scope.$watch('currentPage + currentRango', function() {
        $scope.getFlujoAristaByPagina();
    });
    
    /*********************************************/
    
    var getFlujoAristaByPaginaSuccess = function(response){
        $log.debug("getFlujoAristaByPagina - Success");
        console.log("Response :: ", response);
        $scope.flujoAristas = [];
        $scope.flujoAristas = response.lista;
        $scope.total = response.total;
    };
    
    var getFlujoAristaByPaginaError = function(response){
        $log.debug("getFlujoAristaByPagina - Error");
        console.log("Response :: ", response);
    };
    
    $scope.getFlujoAristaByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        FlujoAristaService.getFlujoAristaByPagina(objPagina).then(getFlujoAristaByPaginaSuccess, getFlujoAristaByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getActoresByPagina();
    };
    
    $scope.getEstados();
    $scope.getFlujoActores();
    $scope.getFlujoAristaByPagina();
});