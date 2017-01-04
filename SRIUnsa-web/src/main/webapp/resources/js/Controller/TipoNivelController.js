investigacionApp.controller('TipoNivelController',['$log', '$scope', 'ngToast', 'TipoNivelService', 'SharedService', 
function($log, $scope, ngToast, TipoNivelService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.listarTipoNivel = [];
    $scope.tipoNivel = {};
    
    /***************** Callback ****************/
    
    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("GetTipoNivel - Success");
    	console.log("Respuesta :: ", response);  
        $scope.tipoNivel = response;
    };
    var getTipoNivelServiceError = function(response){
     	$log.debug("GetTipoNivel - Error"); 
        console.log("Respuesta :: ", response);
    };

    var registrarTipoNivelSuccess = function(response){
    	$log.debug("RegistrarTipoNivel - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoNivelByPagina();
    	
        $scope.cancel();
        
        $("#popNuevoTipoNivel").modal('toggle');
    };
    var registrarTipoNivelError = function(response){
        $log.debug("RegistrarTipoNivel - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };
    
    var updateTipoNivelSuccess = function(response){
    	$log.debug("UpdateTipoNivel - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getTipoNivelByPagina();
        
        $scope.cancel();
        
        $("#popUpdateTipoNivel").modal('toggle');
    };
    var updateTipoNivelError = function(response){
        $log.debug("UpdateTipoNivel - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var deleteTipoNivelSuccess = function(response){
    	$log.debug("DeleteTipoNivel - Success");
    	console.log("Respuesta :: ", response);
        $scope.getTipoNivelByPagina();
        
        $scope.cancel();
    };
    var deleteTipoNivelError = function(response){
        $log.debug("DeleteTipoNivel - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    /***************** Servicios ****************/

    $scope.getListaTipoNivel = function(){
      	TipoNivelService.getListaTipoNivel().then(getTipoNivelServiceSuccess, getTipoNivelServiceError);
    };

    $scope.registrarTipoNivel = function(){
        $scope.submitted = true;
        if($scope.formRegistroTipoNivel.$valid){
            $scope.tipoNivel.suserCreacion = $scope.sharedService.nombreUsuario;
            $scope.tipoNivel.sestado = 'A';
            TipoNivelService.registrarTipoNivel($scope.tipoNivel).then(registrarTipoNivelSuccess,
                registrarTipoNivelError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro Semestre :: ", $scope.semestre);
            openNotice('Error al registrar!','danger');
        }
    };

    $scope.updateTipoNivel = function(){
        $scope.submitted = true;
        if($scope.formUpdateTipoNivel.$valid){
            $scope.tipoNivel.suserModificacion = $scope.sharedService.nombreUsuario;
            $scope.tipoNivel.sestado = 'A';
            TipoNivelService.updateTipoNivel($scope.tipoNivel).then(updateTipoNivelSuccess,
                updateTipoNivelError);
            openNotice('Actualizado!','success');
        }else {
            console.log("No se registro el tipo Nivel :: ", $scope.semestre);
            openNotice('Error al actualizar!','danger');
        }
    	
    };

    $scope.deleteTipoNivel = function(tipoNivel){
    	$scope.tipoNivel = tipoNivel;
        $scope.tipoNivel.suserModificacion = $scope.sharedService.nombreUsuario;
    	TipoNivelService.deleteTipoNivel($scope.tipoNivel).then(deleteTipoNivelSuccess, deleteTipoNivelError);
    };

    $scope.update = function(tipoNivel){
        angular.copy(tipoNivel, $scope.tipoNivel);
    	//$scope.tipoNivel = tipoNivel;
    };

    // Funcion que limpia el modelo del Semestre, ya que este es usado tanto para crear como para actualizar
    $scope.cancel = function(){
        $scope.tipoNivel = {};
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
        $scope.getTipoNivelByPagina();
    });
    
    /*********************************************/
    
    var getTipoNivelByPaginaSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.listarTipoNivel = [];
        $scope.listarTipoNivel = response.lista;
        $scope.total = response.total;
    };
    
    var getTipoNivelByPaginaError = function(response){
        $log.debug("Get paginacionUsuario - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getTipoNivelByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        TipoNivelService.getTipoNivelByPagina(objPagina).then(getTipoNivelByPaginaSuccess, getTipoNivelByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getTipoNivelByPagina();
    };
    
    $scope.getTipoNivelByPagina();
}]);