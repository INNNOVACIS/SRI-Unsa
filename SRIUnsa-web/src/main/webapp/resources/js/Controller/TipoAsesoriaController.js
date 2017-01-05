investigacionApp.controller('TipoAsesoriaController',['$log', '$scope', 'ngToast', '$location', '$rootScope', '$filter', 
    'TipoAsesoriaService', 'SharedService', 
function($log, $scope, ngToast, $location, $rootScope, $filter, 
    TipoAsesoriaService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.asesorias = [];
    $scope.asesoria = {};
	
    /********** Servicios Callback **********/
        
    var getAsesoriaServiceSuccess = function(response){
    	$log.debug("Get Asesoria - Success");
        console.log("Respuesta :: ", response);
        $scope.asesoria = response;
    };
    var getAsesoriaServiceError = function(response){
     	$log.debug("Get Asesoria - Error"); 
        console.log("Respuesta :: ", response);
    };

    var registrarAsesoriaSuccess = function(response){
    	$log.debug("Registrar Asesoria - Success");
    	console.log("Respuesta :: ", response);
        $scope.getTipoAsesoriaByPagina();
    	
        $scope.cancel();
        
        $("#popNuevoAsesoria").modal('toggle');
    };
    var registrarAsesoriaError = function(response){
        $log.debug("Registrar Asesoria - Error");
        console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var updateAsesoriaSuccess = function(response){
    	$log.debug("Update Asesoria - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoAsesoriaByPagina();
        
        $scope.cancel();
        
        $("#popUpdateAsesoria").modal('toggle');
    };
    
    var updateAsesoriaError = function(response){
        $log.debug("Update Asesoria - Error");
        console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var deleteAsesoriaSuccess = function(response){
    	$log.debug("Delete Asesoria - Success");
    	console.log("Respuesta :: ", response);
        $scope.getTipoAsesoriaByPagina();
        
        $scope.cancel();
    };
    var deleteAsesoriaError = function(response){
        $log.debug("Delete Asesoria - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    /********** CRUD ASESORIAS ***********/

    $scope.getAsesorias = function(){
      	TipoAsesoriaService.getAsesorias().then(getAsesoriaServiceSuccess, getAsesoriaServiceError);
    };

    $scope.registrarAsesoria = function(){
        $scope.submitted = true;
        if($scope.formRegistroTipoAsesoria.$valid){
            $scope.asesoria.suserCreacion = $scope.sharedService.nombreUsuario;
            $scope.asesoria.sestado = 'A';
            TipoAsesoriaService.registrarAsesoria($scope.asesoria)
                .then(registrarAsesoriaSuccess, registrarAsesoriaError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro Tipo Asesoria :: ", $scope.semestre);
            openNotice('Error al registrar!','danger');
        }
    };

    $scope.updateAsesoria = function(){
        $scope.submitted = true;
        if($scope.formUpdateTipoAsesoria.$valid){
            $scope.asesoria.suserModificacion = $scope.sharedService.nombreUsuario;
            $scope.asesoria.sestado = 'A';
            TipoAsesoriaService.updateAsesoria($scope.asesoria)
                .then(updateAsesoriaSuccess, updateAsesoriaError);
            openNotice('Actualizado!','success');
        }else {
            console.log("No se registro Tipo Asesoria :: ", $scope.semestre);
            openNotice('Error al actualizar!','danger');
        }
    	
    };

    $scope.deleteAsesoria = function(asesoria){
    	$scope.asesoria = asesoria;
        $scope.asesoria.suserModificacion = $scope.sharedService.nombreUsuario;
    	TipoAsesoriaService.deleteAsesoria ($scope.asesoria).then(deleteAsesoriaSuccess, deleteAsesoriaError);
    };

    $scope.update = function(asesoria){
        angular.copy(asesoria, $scope.asesoria);
    	//$scope.asesoria = asesoria;
    };
    
    $scope.cancel = function(){
        $scope.asesoria = {};
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
        $scope.getTipoAsesoriaByPagina();
    });
    
    /*********************************************/
    
    var getTipoAsesoriaByPaginaSuccess = function(response){
        $log.debug("getTipoAsesoriaByPagina - Success");
        console.log("Respuesta :: ", response);
        $scope.asesorias = [];
        $scope.asesorias = response.lista;
        $scope.total = response.total;
    };
    
    var getTipoAsesoriaByPaginaError = function(response){
        $log.debug("getTipoAsesoriaByPagina - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getTipoAsesoriaByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        TipoAsesoriaService.getTipoAsesoriaByPagina(objPagina).then(getTipoAsesoriaByPaginaSuccess, getTipoAsesoriaByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getPrivilegiosByPagina();
    };
    
    $scope.getTipoAsesoriaByPagina();
}]);