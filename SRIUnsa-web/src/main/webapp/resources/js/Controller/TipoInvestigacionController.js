investigacionApp.controller('TipoInvestigacionController', function($log, $scope, ngToast, $location, $rootScope, $filter, 
    TipoInvestigacionService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.investigaciones = [];
    $scope.investigacion = {};
	
    /********** Servicios Callback **********/
        
    var getInvestigacionServiceSuccess = function(response){
    	$log.debug("GetInvestigacion - Success");
        console.log("Respuesta :: ", response);
        $scope.investigacion = response;
    };
    var getInvestigacionServiceError = function(response){
     	$log.debug("GetInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };

    var registrarInvestigacionSuccess = function(response){
    	$log.debug("Registrar Investigacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoInvestigacionByPagina();
        
        $("#popNuevoInvestigacion").modal('toggle');
        
        $scope.cancel();
    };
    var registrarInvestigacionError = function(response){
        $log.debug("RegistrarInvestigacion - Error");
        console.log("Respuesta :: ", response);
        $scope.investigacion = {};
    };

    var updateInvestigacionSuccess = function(response){
    	$log.debug("UpdateInvestigacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoInvestigacionByPagina();
        
        $("#popUpdateInvestigacion").modal('toggle');
        
        $scope.cancel();
    };
    var updateInvestigacionError = function(response){
        $log.debug("UpdateInvestigacion - Error");
        console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var deleteInvestigacionSuccess = function(response){
    	$log.debug("Delete Investigacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoInvestigacionByPagina();
        
        $scope.cancel();
    };
    var deleteInvestigacionError = function(response){
        $log.debug("DeleteInvestigacion - Success");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    /********** CRUD INVESTIGACIONES ***********/

    $scope.getInvestigaciones = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getInvestigacionServiceSuccess, getInvestigacionServiceError);
    };

    $scope.registrarInvestigacion = function(){
        $scope.submitted = true;
        if($scope.formRegistroTipoInvestigacion.$valid){
            $scope.investigacion.suserCreacion = $scope.sharedService.nombreUsuario;
            $scope.investigacion.sestado = 'A';
            TipoInvestigacionService.registrarInvestigacion($scope.investigacion).then(registrarInvestigacionSuccess, registrarInvestigacionError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro Semestre :: ", $scope.semestre);
            openNotice('Error al registrar!','danger');
        }
        
    };

    $scope.updateInvestigacion = function(){
        $scope.submitted = true;
        if($scope.formUpdateTipoInvestigacion.$valid){
            $scope.investigacion.suserModificacion = $scope.sharedService.nombreUsuario;
            $scope.investigacion.sestado = 'A';
            TipoInvestigacionService.updateInvestigacion($scope.investigacion).then(updateInvestigacionSuccess, updateInvestigacionError);
            openNotice('Actualizado!','success');
        }else {
            console.log("No se registro el tipo de investigacion :: ", $scope.semestre);
            openNotice('Error al actualizar!','danger');
        }
    };

    $scope.deleteInvestigacion = function(investigacion){
    	$scope.investigacion = investigacion;
    	TipoInvestigacionService.deleteInvestigacion ($scope.investigacion).then(deleteInvestigacionSuccess, deleteInvestigacionError);
    };

    $scope.update = function(investigacion){
        angular.copy(investigacion, $scope.investigacion);
    	//$scope.investigacion = investigacion;
    };

    // Funcion que limpia el modelo del Semestre, ya que este es usado tanto para crear como para actualizar
    $scope.cancel = function(){
        $scope.investigacion = {};
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
        $scope.getTipoInvestigacionByPagina();
    });
    
    /*********************************************/
    
    var getTipoInvestigacionByPaginaSuccess = function(response){
        $log.debug("GetPaginacionInvestigacion - Success");
        console.log("Respuesta :: ", response);
        $scope.investigaciones = [];
        $scope.investigaciones = response.lista;
        $scope.total = response.total;
    };
    
    var getTipoInvestigacionByPaginaError = function(response){
        $log.debug("GetPaginacionInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getTipoInvestigacionByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        TipoInvestigacionService.getTipoInvestigacionByPagina(objPagina).then(getTipoInvestigacionByPaginaSuccess, getTipoInvestigacionByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getTipoInvestigacionByPagina();
    };
    
    $scope.getTipoInvestigacionByPagina();
});