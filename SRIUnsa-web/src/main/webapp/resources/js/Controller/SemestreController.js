investigacionApp.controller('SemestreController', function($log, $scope, ngToast, SemestreService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.semestres = [];
    $scope.semestre = {};
	
    /********** Servicios Callback **********/
    
    // CallBack Get
    var getSemestreServiceSuccess = function(response){
    	$log.debug("Get Semestre - Success");
    	console.log("Respuesta :: ", response);
        $scope.semestre = response;
    };
    var getSemestreServiceError = function(response){
     	$log.debug("Get Semestre - Error");
        console.log("Respuesta :: ", response);
    };
    
    // CallBack Registrar
    var registrarSemestreSuccess = function(response){
    	$log.debug("Registrar Semestre - Success");
        console.log("Respuesta :: ", response);
    	$scope.getSemestresByPagina();
    	$scope.semestre = {};
    };
    var registrarSemestreError = function(response){
        $log.debug("Registrar Semestre - Error");
        console.log("Respuesta :: ", response);
        $scope.semestre = {};
    };
    
    // CallBack Registrar
    var updateSemestreSuccess = function(response){
    	$log.debug("Update Semestre - Success");
    	console.log("Respuesta :: ", response);
        $scope.getSemestresByPagina();
        $scope.semestre = {};
    };
    var updateSemestreError = function(response){
        $log.debug("Update Semestre - Error");
        console.log("Respuesta :: ", response);
        $scope.semestre = {};
    };
    
    // CallBack Delete
    var deleteSemestreSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("Respuesta :: ", response);
        $scope.getSemestresByPagina();
        $scope.semestre = {};

    };
    var deleteSemestreError = function(response){
        $log.debug("Delete User - Error");
    	console.log("Respuesta :: ", response);
        $scope.semestre = {};
    };

    /********** CRUD SEMESTRES ***********/

    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };

    $scope.registrarSemestre = function() {
        $scope.submitted = true;
        if($scope.formRegistroSemestre.$valid){
            $scope.semestre.suserCreacion = $scope.sharedService.nombreUsuario;
            $scope.semestre.sestado = 'A';
            SemestreService.registrarSemestre($scope.semestre).then(registrarSemestreSuccess, registrarSemestreError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro Semestre :: ", $scope.semestre);
            openNotice('Error al registrar!','danger');
            $scope.cancel();
        }
    };

    // 
    $scope.updateSemestre = function(){
        $scope.submitted = true;
        if($scope.formRegistroSemestre.$valid){
            $scope.semestre.suserModificacion = $scope.sharedService.nombreUsuario;
            $scope.semestre.sestado = 'A';
            SemestreService.updateSemestre($scope.semestre).then(updateSemestreSuccess, updateSemestreError);
            openNotice('Actualizado!','success');
        }else {
            console.log("No se registro Semestre :: ", $scope.semestre);
            openNotice('Error al actualizar!','danger');
            $scope.cancel();
        }
        
    };
        
    $scope.deleteSemestre = function(semestre){
    	$scope.semestre = semestre;
        $scope.semestre.suserModificacion = $scope.sharedService.nombreUsuario;
    	SemestreService.deleteSemestre ($scope.semestre).then(deleteSemestreSuccess, deleteSemestreError);
    };
    
    $scope.update = function(semestre){
    	$scope.semestre = semestre;
    };
    
    
    $scope.cancel = function(){
        $scope.semestre = {};
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
        $scope.getSemestresByPagina();
    });
    
    /*********************************************/
    
    var getSemestresByPaginaSuccess = function(response){
        $log.debug("getSemestresByPagina - Success");
        console.log("Respuesta :: ", response);
        $scope.semestres = [];
        $scope.semestres = response.lista;
        $scope.total = response.total;
    };
    
    var getSemestresByPaginaError = function(response){
        $log.debug("getSemestresByPagina - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getSemestresByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        SemestreService.getSemestresByPagina(objPagina).then(getSemestresByPaginaSuccess, getSemestresByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getSemestresByPagina();
    };
    
    $scope.getSemestresByPagina();
});