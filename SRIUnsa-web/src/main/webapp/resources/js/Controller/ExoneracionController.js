investigacionApp.controller('ExoneracionController',['$log', '$scope', 'ngToast', 'ExoneracionService', 'SharedService', 
function($log, $scope, ngToast, ExoneracionService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.listarExoneracion = [];
    $scope.exoneracion = {};
    
    /***************** Callback ****************/
    
    var getExoneracionServiceSuccess = function(response){
    	$log.debug("GetExoneracion - Success");
    	console.log("Respuesta :: ", response);  
        $scope.exoneracion = response;
    };
    var getExoneracionServiceError = function(response){
     	$log.debug("GetExoneracion - Error"); 
        console.log("Respuesta :: ", response);
    };

    var registrarExoneracionSuccess = function(response){
    	$log.debug("RegistrarExoneracion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getExoneracionByPagina();
    	
        $scope.cancel();
        
        $("#popNuevoExoneracion").modal('toggle');
    };
    var registrarExoneracionError = function(response){
        $log.debug("RegistrarExoneracion - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };
    
    var updateExoneracionSuccess = function(response){
    	$log.debug("UpdateExoneracion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getExoneracionByPagina();
        
        $scope.cancel();
        
        $("#popUpdateExoneracion").modal('toggle');
    };
    var updateExoneracionError = function(response){
        $log.debug("UpdateExoneracion - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var deleteExoneracionSuccess = function(response){
    	$log.debug("DeleteExoneracion - Success");
    	console.log("Respuesta :: ", response);
        $scope.getExoneracionByPagina();
        
        $scope.cancel();
    };
    var deleteExoneracionError = function(response){
        $log.debug("DeleteExoneracion - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    /***************** Servicios ****************/

    $scope.getListaExoneracion = function(){
      	ExoneracionService.getListaExoneracion().then(getExoneracionServiceSuccess, getExoneracionServiceError);
    };

    $scope.registrarExoneracion = function(){
        $scope.submitted = true;
        if($scope.formRegistroExoneracion.$valid){
            $scope.exoneracion.suserCreacion = $scope.sharedService.usuarioLogin.nombre;
            $scope.exoneracion.suserModificacion = $scope.sharedService.usuarioLogin.nombre;
            $scope.exoneracion.sestado = 'A';
            ExoneracionService.registrarExoneracion($scope.exoneracion).then(registrarExoneracionSuccess,
                registrarExoneracionError);
            openNotice('Registrado!','success');
        }else {
            openNotice('Error al registrar!','danger');
        }
    };

    $scope.updateExoneracion = function(){
        $scope.submitted = true;
        if($scope.formUpdateExoneracion.$valid){
            $scope.exoneracion.suserModificacion = $scope.sharedService.usuarioLogin.nombre;
            $scope.exoneracion.sestado = 'A';
            ExoneracionService.updateExoneracion($scope.exoneracion).then(updateExoneracionSuccess,
                updateExoneracionError);
            openNotice('Actualizado!','success');
        }else {
            openNotice('Error al actualizar!','danger');
        }
    	
    };

    $scope.deleteExoneracion = function(exoneracion){
    	$scope.exoneracion = exoneracion;
        $scope.exoneracion.suserModificacion = $scope.sharedService.usuarioLogin.nombre;
    	ExoneracionService.deleteExoneracion($scope.exoneracion).then(deleteExoneracionSuccess, deleteExoneracionError);
    };

    $scope.update = function(exoneracion){
        angular.copy(exoneracion, $scope.exoneracion);
    };

    // Funcion que limpia el modelo del Semestre, ya que este es usado tanto para crear como para actualizar
    $scope.cancel = function(){
        $scope.exoneracion = {};
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
        $scope.getExoneracionByPagina();
    });
    
    /*********************************************/
    
    var getExoneracionByPaginaSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.listarExoneracion = [];
        $scope.listarExoneracion = response.lista;
        $scope.total = response.total;
    };
    
    var getExoneracionByPaginaError = function(response){
        $log.debug("Get paginacionUsuario - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getExoneracionByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        ExoneracionService.getExoneracionByPagina(objPagina).then(getExoneracionByPaginaSuccess, getExoneracionByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getExoneracionByPagina();
    };
    
    $scope.getExoneracionByPagina();
}]);