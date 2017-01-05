investigacionApp.controller('TipoProduccionController',['$log', '$scope', 'ngToast', 'TipoProduccionService', 'SharedService', 
function($log, $scope, ngToast, TipoProduccionService, SharedService) {
        
    $scope.sharedService = SharedService;
    $scope.tipoProducciones = [];
    $scope.tipoProduccion = {};
	
    var getTipoProduccionServiceSuccess = function(response){
    	$log.debug("GetTipoProduccion - Success");
    	console.log("Respuesta :: ", response);    
        $scope.tipoProduccion = response;
    };
    var getTipoProduccionServiceError = function(response){
     	$log.debug("GetTipoProduccion - Error"); 
        console.log("Respuesta :: ", response);
    };

    var registrarTipoProduccionSuccess = function(response){
    	$log.debug("RegistrarTipoProduccion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoProduccionByPagina();
        
        $scope.cancel();
        
        $("#popNuevoTipoProduccion").modal('toggle');
    };
    var registrarTipoProduccionError = function(response){
        $log.debug("RegistrarTipoProduccion - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var updateTipoProduccionSuccess = function(response){
    	$log.debug("UpdateTipoProduccion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoProduccionByPagina();
        
        $scope.cancel();
        
        $("#popUpdateTipoProduccion").modal('toggle');
    };
    var updateTipoProduccionError = function(response){
        $log.debug("UpdateTipoProduccion - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var deleteTipoProduccionSuccess = function(response){
    	$log.debug("DeleteTipoProduccion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoProduccionByPagina();
        
        $scope.cancel();
    };
    var deleteTipoProduccionError = function(response){
        $log.debug("DeleteTipoProduccion - Error");
    	console.log("Respuesta :: ", response);
        $scope.cancel();
    };

    /********** CRUD TIPO PRODUCCION ***********/

    $scope.getListaTipoProduccion = function(){
      	TipoProduccionService.getListaTipoProduccion().then(getTipoProduccionServiceSuccess, getTipoProduccionServiceError);
    };

    $scope.registrarTipoProduccion = function(){
        $scope.submitted = true;
        if($scope.formRegistroTipoProduccion.$valid){
            $scope.tipoProduccion.suserCreacion = $scope.sharedService.nombreUsuario;
            $scope.tipoProduccion.sestado = 'A';
            TipoProduccionService.registrarTipoProduccion($scope.tipoProduccion)
                .then(registrarTipoProduccionSuccess, registrarTipoProduccionError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro Tipo Produccion :: ", $scope.semestre);
            openNotice('Error al registrar!','danger');
        }
    };

    $scope.updateTipoProduccion = function(){
        $scope.submitted = true;
        if($scope.formUpdateTipoProduccion.$valid){
            $scope.tipoProduccion.suserModificacion = $scope.sharedService.nombreUsuario;
            $scope.tipoProduccion.sestado = 'A';
            TipoProduccionService.updateTipoProduccion($scope.tipoProduccion)
                .then(updateTipoProduccionSuccess, updateTipoProduccionError);
            openNotice('Actualizado!','success');
        }else {
            console.log("No se registro Tipo Produccion :: ", $scope.semestre);
            openNotice('Error al actualizar!','danger');
        }
    };

    $scope.deleteTipoProduccion = function(tipoProduccion){
    	$scope.tipoProduccion = tipoProduccion;
        $scope.tipoProduccion.suserModificacion = $scope.sharedService.nombreUsuario;
    	TipoProduccionService.deleteTipoProduccion($scope.tipoProduccion)
                .then(deleteTipoProduccionSuccess, deleteTipoProduccionError);
    };

    $scope.update = function(tipoProduccion){
        angular.copy(tipoProduccion, $scope.tipoProduccion);
    	//$scope.tipoProduccion = tipoProduccion;
    };

    $scope.cancel = function(){
        $scope.tipoProduccion = {};
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
        $scope.getTipoProduccionByPagina();
    });
    
    /*********************************************/
    
    var getTipoProduccionByPaginaSuccess = function(response){
        $log.debug("Get paginacionTipoProduccion - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoProducciones = [];
        $scope.tipoProducciones = response.lista;
        $scope.total = response.total;
    };
    
    var getTipoProduccionByPaginaError = function(response){
        $log.debug("Get paginacionTipoProduccion - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getTipoProduccionByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        TipoProduccionService.getTipoProduccionByPagina(objPagina).then(getTipoProduccionByPaginaSuccess, getTipoProduccionByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getTipoProduccionByPagina();
    };
    
    $scope.getTipoProduccionByPagina();
}]);