investigacionApp.controller('ModuloExoneracionController',['$log', '$scope', 'ngToast', 'ModuloExoneracionService', 'SharedService', 
function($log, $scope, ngToast, ModuloExoneracionService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.listarExoneracion = [];
    $scope.exoneracion = {};
    
    /***************** Callback ****************/
    
//    var getModuloExoneracionServiceSuccess = function(response){
//    	$log.debug("GetExoneracion - Success");
//    	console.log("Respuesta :: ", response);  
//        $scope.exoneracion = response;
//    };
//    var getModuloExoneracionServiceError = function(response){
//     	$log.debug("GetExoneracion - Error"); 
//        console.log("Respuesta :: ", response);
//    };
//
//    
//    
//    
//
//    
//
//    /***************** Servicios ****************/
//
//    $scope.getListaExoneracion = function(){
//      	ModuloExoneracionService.getListaExoneracion().then(getModuloExoneracionServiceSuccess, getModuloExoneracionServiceError);
//    };

    

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
    
    
    
    
    
    
    
    
    
    
    /////////////////////////////////////////////////////////////////////
    
    
    $scope.rangoPaginas = [5,10,20,100];
    $scope.currentPage = 1;
    $scope.currentRango = $scope.rangoPaginas[0];
    $scope.maxSize = 5;
    $scope.total = 0;
    
    
    
    var getDocentesServiceSuccess = function(response){
        $log.debug("GetDocentes - Success");
        console.log("Respuesta :: ", response);
        $scope.docentes = response;
    };
    var getDocentesServiceError = function(response){
        $log.debug("GetDocentes - Error");
        console.log("Respuesta :: ", response);
    };
    $scope.getDocentes = function(){
        ModuloExoneracionService.getDocentes().then(getDocentesServiceSuccess, getDocentesServiceError);
    };
    
    
    var getExoneracionesServiceSuccess = function(response){
        $log.debug("GetExoneraciones - Success");
        console.log("Respuesta :: ", response);
        $scope.exoneraciones = response.body;
    };
    var getExoneracionesServiceError = function(response){
        $log.debug("GetExoneraciones - Error");
        console.log("Respuesta :: ", response);
    };
    $scope.getExoneraciones = function(){
        ModuloExoneracionService.getExoneraciones().then(getExoneracionesServiceSuccess, getExoneracionesServiceError);
    };
    
    
    var registrarExoneracionSuccess = function(response){
    	$scope.getListaUsuarioExoneracion();
        $scope.cancel();
        $("#popNuevoExoneracion").modal('toggle');
        openNotice('Registrado!','success');
    };
    var registrarExoneracionError = function(response){
        $scope.cancel();
        openNotice('Error al registrar!','danger');
    };
    $scope.registrarExoneracion = function(){
        $scope.submitted = true;
        if($scope.formRegistroExoneracion.$valid){
            $scope.exoneracion.suserCreacion = $scope.sharedService.usuarioLogin.nombre;
            $scope.exoneracion.suserModificacion = $scope.sharedService.usuarioLogin.nombre;
            $scope.exoneracion.sestado = 'A';
            
            ModuloExoneracionService.RegistrarUsuarioExoneracion(
                    $scope.docente.nidPersona, 
                    $scope.exoneracion.nidExoneracion)
                .then(
                    registrarExoneracionSuccess,
                    registrarExoneracionError);
        }else {
            openNotice('Error al registrar!','danger');
        }
    };
    
    
    var getListaUsuarioExoneracionSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.listarExoneracion = [];
        $scope.listarExoneracion = response.lista;
        $scope.total = response.total;
    };
    var getListaUsuarioExoneracionError = function(response){
        $log.debug("Get paginacionUsuario - Error");
        console.log("Respuesta :: ", response);
    };
    $scope.getListaUsuarioExoneracion = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        ModuloExoneracionService.getListaUsuarioExoneracion(objPagina).then(getListaUsuarioExoneracionSuccess, getListaUsuarioExoneracionError);
    };
    $scope.clickBuscar = function(){
        $scope.getListaUsuarioExoneracion();
    };
    $scope.numPages = function () {
      return Math.ceil($scope.total / $scope.currentRango);
    };
    $scope.$watch('currentPage + currentRango', function() {
        $scope.getListaUsuarioExoneracion();
    });
    
    $scope.getDocentes();
    $scope.getExoneraciones();
    $scope.getListaUsuarioExoneracion();
}]);