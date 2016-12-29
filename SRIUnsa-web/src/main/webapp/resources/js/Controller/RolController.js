investigacionApp.controller('RolController', function($log, $scope, ngToast, RolService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.roles = [];
    $scope.rol = {};
	
    var getRolServiceSuccess = function(response){
    	$log.debug("GetRol - Success");
    	console.log("Respuesta :: ", response);
        $scope.rol = response;
    };
    var getRolServiceError = function(response){
     	$log.debug("Get Rol - Error");
        console.log("Respuesta :: ", response);
    };

    var registrarRolSuccess = function(response){
    	$log.debug("RegistrarRol - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getRolesByPagina();
    	
        $scope.cancel();
        
        $("#popNuevoRol").modal('toggle');
    };

    var registrarRolError = function(response){
        $log.debug("RegistrarRol - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var updateRolSuccess = function(response){
    	$log.debug("UpdateUser - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getRolesByPagina();
        
        $scope.cancel();
        
        $("#popUpdateRol").modal('toggle');
    };

    var updateRolError = function(response){
        $log.debug("UpdateUser - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var deleteRolSuccess = function(response){
    	$log.debug("DeleteUser - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getRolesByPagina();
        
        $scope.cancel();
    };

    var deleteRolError = function(response){
        $log.debug("DeleteUser - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    /********** CRUD ROLES ***********/

    $scope.getRoles = function(){
      	RolService.getRoles().then(getRolServiceSuccess, getRolServiceError);
    };

    $scope.registrarRol = function(){
        $scope.submitted = true;
        if($scope.formRegistroRol.$valid){
            $scope.rol.suserCreacion = $scope.sharedService.nombreUsuario;
            $scope.rol.sestado = 'A';
            RolService.registrarRol($scope.rol).then(registrarRolSuccess, registrarRolError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro Semestre :: ", $scope.semestre);
            openNotice('Error al registrar!','danger');
        }
        
        
    };

    $scope.updateRol = function(){
        $scope.submitted = true;
        if($scope.formUpdateRol.$valid) {
            $scope.rol.suserModificacion = $scope.sharedService.nombreUsuario;
            $scope.rol.sestado = 'A';
            RolService.updateRol($scope.rol).then(updateRolSuccess, updateRolError);
            openNotice('Actualizado!','success');
        } else {
            console.log("No se registro el Rol :: ", $scope.semestre);
            openNotice('Error al actualizar!','danger');
        }
    };

    $scope.deleteRol = function(rol){
    	$scope.rol = rol;    	
    	RolService.deleteRol ($scope.rol).then(deleteRolSuccess, deleteRolError);
    };

    $scope.update = function(rol){
        angular.copy(rol, $scope.rol);
    	//$scope.rol = rol;
    };
    
    // Funcion que limpia el modelo del Semestre, ya que este es usado tanto para crear como para actualizar
    $scope.cancel = function(){
        $scope.rol = {};
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
        $scope.getRolesByPagina();
    });
    
    /*********************************************/
    
    var getRolesByPaginaSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        $scope.roles = [];
        $scope.roles = response.lista;
        $scope.total = response.total;
    };
    
    var getRolesByPaginaError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getRolesByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        RolService.getRolesByPagina(objPagina).then(getRolesByPaginaSuccess, getRolesByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getRolesByPagina();
    };
    
    $scope.getRolesByPagina();
});