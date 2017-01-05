investigacionApp.controller('PrivilegioController',['$log', '$scope', 'ngToast', 'PrivilegioService', 'SharedService', 
function($log, $scope, ngToast, PrivilegioService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.privilegios = [];
    $scope.privilegio = {};
	
    /********** Servicios Callback **********/
        
    var getPrivilegioServiceSuccess = function(response){
    	$log.debug("GetPrivilegio - Success");
        console.log("Respuesta :: ", response);
    	$scope.privilegio = response;
    };

    var getPrivilegioServiceError = function(response){
     	$log.debug("Get Privilegio - Error");
        console.log("Respuesta :: ", response);
    };

    var registrarPrivilegioSuccess = function(response){
    	$log.debug("RegistrarPrivilegio - Success");
        console.log("Respuesta :: ", response);
    	$scope.getPrivilegiosByPagina();
    	
        $scope.cancel();
        
        $("#popNuevoPrivilegio").modal('toggle');
    };

    var registrarPrivilegioError = function(response){
        $log.debug("Registrar Privilegio - Error");
        console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var updatePrivilegioSuccess = function(response){
    	$log.debug("UpdatePrivilegio - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getPrivilegiosByPagina();
        
        $scope.cancel();
        
        $("#popUpdatePrivilegio").modal('toggle');
    };

    var updatePrivilegioError = function(response){
        $log.debug("UpdatePrivilegio - Error");
        console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var deletePrivilegioSuccess = function(response){
    	$log.debug("DeletePrivilegio - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getPrivilegiosByPagina();
        
        $scope.cancel();
    };

    var deletePrivilegioError = function(response){
        $log.debug("DeletePrivilegio - Success");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    /********** CRUD PRIVILEGIOS ***********/

    $scope.getPrivilegios = function(){
      	PrivilegioService.getPrivilegios().then(getPrivilegioServiceSuccess, getPrivilegioServiceError);
    };

    $scope.registrarPrivilegio = function(){        
        $scope.submitted = true;
        if($scope.formRegistroPrivilegio.$valid){
            $scope.privilegio.suserCreacion = $scope.sharedService.nombreUsuario;
            $scope.privilegio.sestado = 'A';
            PrivilegioService.registrarPrivilegio($scope.privilegio).then(registrarPrivilegioSuccess, registrarPrivilegioError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro Semestre :: ", $scope.semestre);
            openNotice('Error al registrar!','danger');
        }
    };

    $scope.updatePrivilegio = function(){
        $scope.submitted = true;
        if($scope.formUpdatePrivilegio.$valid){
            $scope.privilegio.suserModificacion = $scope.sharedService.nombreUsuario;
            $scope.privilegio.sestado = 'A';
            PrivilegioService.updatePrivilegio($scope.privilegio).then(updatePrivilegioSuccess, updatePrivilegioError);
            openNotice('Actualizado!','success');
        }else {
            console.log("No se registro Semestre :: ", $scope.semestre);
            openNotice('Error al actualizar!','danger');
        }
    };

    $scope.deletePrivilegio = function(privilegio){
    	$scope.privilegio = privilegio;
    	PrivilegioService.deletePrivilegio ($scope.privilegio).then(deletePrivilegioSuccess, deletePrivilegioError);
    };

    $scope.update = function(privilegio){
        // Se realizo esta modificacion ya que la referencia ocasionaba que se 
        // cuando modificaba el texto del input tambien se modificara el campo
        // de la tabla. Esto debido a que se enviaba como referencia
    	angular.copy(privilegio, $scope.privilegio);
        //$scope.privilegio = privilegio;
    };

    // Funcion que limpia el modelo del Semestre, ya que este es usado tanto para crear como para actualizar
    $scope.cancel = function(){
        $scope.privilegio = {};
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
        $scope.getPrivilegiosByPagina();
    });
    
    /*********************************************/
    
    var getPrivilegiosByPaginaSuccess = function(response){
        $log.debug("getPrivilegiosByPagina - Success");
        $scope.privilegios = [];
        $scope.privilegios = response.lista;
        $scope.total = response.total;
    };
    
    var getPrivilegiosByPaginaError = function(response){
        $log.debug("getPrivilegiosByPagina - Success");
        console.log("Respuesta  :: ", response);
    };
    
    $scope.getPrivilegiosByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        PrivilegioService.getPrivilegiosByPagina(objPagina).then(getPrivilegiosByPaginaSuccess, getPrivilegiosByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getPrivilegiosByPagina();
    };
    
    $scope.getPrivilegiosByPagina();
}]);