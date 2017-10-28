investigacionApp.controller('ModuloExoneracionController',['$log', '$scope', 'ngToast', 'ModuloExoneracionService', 'SharedService', 'SemestreService',
function($log, $scope, ngToast, ModuloExoneracionService, SharedService, SemestreService) {

    $scope.sharedService = SharedService;
    $scope.listarExoneracion = [];
    $scope.exoneracion = {};
   

    // Funcion que limpia el modelo del Semestre, ya que este es usado tanto para crear como para actualizar
    $scope.cancel = function(){
        $scope.exoneracion = {};
        $scope.docenteexoneracion = {};
        $scope.eliminarexoneracion = {};
        $scope.docente = "";
    };
    
    $scope.view = function(docenteexoneracion){
        $scope.docenteexoneracion = docenteexoneracion;
    };
    
    $scope.seleccionarEliminacionExoneracion = function(eliminarexoneracion){
        $scope.eliminarexoneracion = eliminarexoneracion;
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
    
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("GetSemestre - Success");
        console.log("Respuesta :: ", response);
    	$scope.semestres = response;
        $scope.semestre = verificarSemestre($scope.semestres);
        $scope.semestreRegistrar = $scope.semestre;
    };
    var getSemestreServiceError = function(response){
     	$log.debug("GetSemestre - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };
    
    var deleteExoneracionSuccess = function(response){
        $scope.getListaUsuarioExoneracion();
        $scope.cancel();
        openNotice('Eliminación correcta!','success');
    };
    var deleteExoneracionError = function(response){
        $log.debug("GetExoneraciones - Error");
        console.log("Respuesta :: ", response);
    };
    $scope.deleteExoneracion = function(){
        ModuloExoneracionService.DeleteExoneracion($scope.eliminarexoneracion.nidUsuarioExoneracion).then(deleteExoneracionSuccess, deleteExoneracionError);
    };
    
    
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
        if(response.body.nidUsuario == 0){
            openNotice('El docente no puede tener más de una exoneración por semestre!','danger');
        } else {
            openNotice('Registrado!','success');
        }
        
    };
    var registrarExoneracionError = function(response){
        $scope.cancel();
        openNotice('Error al registrar!','danger');
    };
    $scope.registrarExoneracion = function(){
        $scope.submitted = true;
        if($scope.formRegistroExoneracion.$valid){
            var usuarioExoneracion = {};
            usuarioExoneracion.nidExoneracion = $scope.exoneracion.nidExoneracion;
            usuarioExoneracion.nidUsuario = $scope.docente.nidPersona;
            usuarioExoneracion.nidSemestre = $scope.semestreRegistrar.nidSemestre;
            usuarioExoneracion.suserCreacion = $scope.sharedService.usuarioLogin.nombre;
            usuarioExoneracion.suserModificacion = $scope.sharedService.usuarioLogin.nombre;
            usuarioExoneracion.dfechaCreacion = new Date();
            usuarioExoneracion.dfechaModificacion = new Date();
            usuarioExoneracion.sestado = 'A';
            
            ModuloExoneracionService.RegistrarUsuarioExoneracion(
                    usuarioExoneracion)
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
    
    $scope.changeSemestre = function(semestre){
        $scope.semestre = semestre;
        
    };
    
    var verificarSemestre = function(semestres){
        var currentDate = new Date();
        var semestre = {};
        angular.forEach(semestres, function(value, key){
            if(value.dinicioSemestre < currentDate && value.dfinSemestre > currentDate){
                semestre = value;
            }
        });
        return semestre;
    };
    
    $scope.getSemestres();
    $scope.getDocentes();
    $scope.getExoneraciones();
    $scope.getListaUsuarioExoneracion();
}]);