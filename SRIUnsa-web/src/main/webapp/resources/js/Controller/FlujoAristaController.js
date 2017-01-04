investigacionApp.controller('FlujoAristaController',['$log', '$scope', 'ngToast', 'FlujoAristaService', 'FlujoActorService', 'EstadoService', function($log, $scope, ngToast, FlujoAristaService, FlujoActorService, EstadoService) {

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
        $scope.cancel();
        
        // Cerrando el modal con Jquery
        $("#modalNuevo").modal('toggle');
    };
    
    var RegistrarFlujoAristaError = function(response){
        $log.debug("RegistrarFlujoArista - Error");
        console.log("Respuesta :: ", response);  
        $scope.cancel();       
    };
    
    var ActualizarFlujoAristaSuccess = function(response){
        $log.debug("ActualizarFlujoArista - Success");
        console.log("Respuesta :: ", response);
        if(response === true){
            $scope.getFlujoAristaByPagina(); /*actualizacion la paginacion*/
        }
        $scope.cancel();
        
        // Cerrando el modal con Jquery
        $("#modalActualizar").modal('toggle');
    };
    
    var ActualizarFlujoAristaError = function(response){
        $log.debug("ActualizarFlujoArista - Success");
        console.log("Respuesta :: ", response);
        $scope.cancel();
    };
    
    var EliminarFlujoAristaSuccess = function(response){
        $log.debug("EliminarFlujoArista - Success");
        console.log("Respuesta :: ", response);
        if(response === true){
            $scope.getFlujoAristaByPagina(); /*actualizacion la paginacion*/
        }
        $scope.cancel();
    };
    
    var EliminarFlujoAristaError = function(response){
        $log.debug("EliminarFlujoArista - Error");
        console.log("Respuesta :: ", response); 
        $scope.cancel();
    };
    
    /************************ Servicios **************************/
    
    $scope.getFlujoActores = function(){
        FlujoActorService.getActores().then(getFlujoActoresSuccess, getFlujoActoresError);
    };
    
    $scope.getEstados = function(){
        EstadoService.getEstados().then(getEstadosSuccess, getEstadosError);
    };
    
    $scope.RegistrarFlujoArista = function(){
        $scope.submitted = true;
        if($scope.formRegistrarFlujoArista.$valid
                && $scope.formRegistrarFlujoArista.flujoActorOrigen.$valid
                && $scope.formRegistrarFlujoArista.flujoActorDestino.$valid
                && $scope.formRegistrarFlujoArista.estado.$valid) {
            var flujoArista = {
                sidFlujoActorDestino : $scope.flujoActorDestino.nidFlujoActor,
                sidFlujoActorOrigen : $scope.flujoActorOrigen.nidFlujoActor,
                snombreArista : $scope.flujoAristaNombre,
                nidEstado : $scope.estado.nidEstado, 
                sflujo : 'SRI',
                sestado: 'A'
            };
            FlujoAristaService.RegistrarFlujoArista(flujoArista).then(RegistrarFlujoAristaSuccess, RegistrarFlujoAristaError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro  :: ", $scope.flujoActores);
            openNotice('Error al registrar!','danger');
            //$scope.cancel();
        }
    };
    
    $scope.ActualizarFlujoArista = function(){
        $scope.submitted = true;
        if($scope.formUpdateFlujoArista.$valid) {
            var flujoArista = {
                nidArista : $scope.flujoAristaActor.nidArista,
                sidFlujoActorDestino : $scope.flujoActorDestino.nidFlujoActor,
                sidFlujoActorOrigen : $scope.flujoActorOrigen.nidFlujoActor,
                snombreArista : $scope.flujoAristaActor.snombreArista,
                nidEstado : $scope.estadoFlujo.nidEstado, 
                sflujo : 'SRI',
                sestado: 'A'
            };
            FlujoAristaService.ActualizarFlujoArista(flujoArista).then(ActualizarFlujoAristaSuccess,
                ActualizarFlujoAristaError);
            openNotice('Actualizado!','success');
        }else {
            console.log("Error al actualizar Flujo Arista  :: ", $scope.flujoActores);
            openNotice('Error al actualizar!','danger');
            //$scope.cancel();
        }
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
        FlujoAristaService.EliminarFlujoArista(flujoArista).then(EliminarFlujoAristaSuccess,
            EliminarFlujoAristaError);
    };
    
    // Establecer los datos del formulario de actualizacion para su visualizacion 
    // y posterior actualizacion
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
    
    // Limpia los datos del formulario de registro y actualizacion
    // Les ponemos null a los seleccionables porque '{}' cuenta como un objeto seleccionado
    $scope.cancel = function(){
        $scope.flujoAristaNombre = null;
        $scope.flujoActorOrigen = null;
        $scope.flujoActorDestino = null;
        $scope.estado = null;
    };   
    
    /**************** NOTIFICACIONES *****************/
    var openNotice = function (text, type) {
        ngToast.create({
            className: type,
            content: '<span class="alert-link">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + text +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'
        });
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
}]);