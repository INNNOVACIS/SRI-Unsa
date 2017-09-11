investigacionApp.controller('CategoriaDocenteController',['$log', '$scope', 'ngToast', 'CategoriaDocenteService', 'SharedService', 
function($log, $scope, ngToast, CategoriaDocenteService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.listarCategoriaDocente = [];
    $scope.categoriaDocente = {};
    
    /***************** Callback ****************/
    
    var getCategoriaDocenteServiceSuccess = function(response){
    	$log.debug("GetCategoriaDocente - Success");
    	console.log("Respuesta :: ", response);  
        $scope.categoriaDocente = response;
    };
    var getCategoriaDocenteServiceError = function(response){
     	$log.debug("GetCategoriaDocente - Error"); 
        console.log("Respuesta :: ", response);
    };

    var registrarCategoriaDocenteSuccess = function(response){
    	$log.debug("RegistrarCategoriaDocente - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getCategoriaDocenteByPagina();
    	
        $scope.cancel();
        
        $("#popNuevoCategoriaDocente").modal('toggle');
    };
    var registrarCategoriaDocenteError = function(response){
        $log.debug("RegistrarCategoriaDocente - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };
    
    var updateCategoriaDocenteSuccess = function(response){
    	$log.debug("UpdateCategoriaDocente - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getCategoriaDocenteByPagina();
        
        $scope.cancel();
        
        $("#popUpdateCategoriaDocente").modal('toggle');
    };
    var updateCategoriaDocenteError = function(response){
        $log.debug("UpdateCategoriaDocente - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    var deleteCategoriaDocenteSuccess = function(response){
    	$log.debug("DeleteCategoriaDocente - Success");
    	console.log("Respuesta :: ", response);
        $scope.getCategoriaDocenteByPagina();
        
        $scope.cancel();
    };
    var deleteCategoriaDocenteError = function(response){
        $log.debug("DeleteCategoriaDocente - Error");
    	console.log("Respuesta :: ", response);
        
        $scope.cancel();
    };

    /***************** Servicios ****************/

    $scope.getListaCategoriaDocente = function(){
      	CategoriaDocenteService.getListaCategoriaDocente().then(getCategoriaDocenteServiceSuccess, getCategoriaDocenteServiceError);
    };

    $scope.registrarCategoriaDocente = function(){
        $scope.submitted = true;
        if($scope.formRegistroCategoriaDocente.$valid){
            $scope.categoriaDocente.suserCreacion = $scope.sharedService.nombreUsuario;
            $scope.categoriaDocente.sestado = 'A';
            CategoriaDocenteService.registrarCategoriaDocente($scope.categoriaDocente).then(registrarCategoriaDocenteSuccess,
                registrarCategoriaDocenteError);
            openNotice('Registrado!','success');
        }else {
            console.log("No se registro Semestre :: ", $scope.semestre);
            openNotice('Error al registrar!','danger');
        }
    };

    $scope.updateCategoriaDocente = function(){
        $scope.submitted = true;
        if($scope.formUpdateCategoriaDocente.$valid){
            $scope.categoriaDocente.suserModificacion = $scope.sharedService.nombreUsuario;
            $scope.categoriaDocente.sestado = 'A';
            CategoriaDocenteService.updateCategoriaDocente($scope.categoriaDocente).then(updateCategoriaDocenteSuccess,
                updateCategoriaDocenteError);
            openNotice('Actualizado!','success');
        }else {
            console.log("No se registro el tipo Nivel :: ", $scope.semestre);
            openNotice('Error al actualizar!','danger');
        }
    	
    };

    $scope.deleteCategoriaDocente = function(categoriaDocente){
    	$scope.categoriaDocente = categoriaDocente;
        $scope.categoriaDocente.suserModificacion = $scope.sharedService.nombreUsuario;
    	CategoriaDocenteService.deleteCategoriaDocente($scope.categoriaDocente).then(deleteCategoriaDocenteSuccess, deleteCategoriaDocenteError);
    };

    $scope.update = function(categoriaDocente){
        angular.copy(categoriaDocente, $scope.categoriaDocente);
    	//$scope.categoriaDocente = categoriaDocente;
    };

    // Funcion que limpia el modelo del Semestre, ya que este es usado tanto para crear como para actualizar
    $scope.cancel = function(){
        $scope.categoriaDocente = {};
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
        $scope.getCategoriaDocenteByPagina();
    });
    
    /*********************************************/
    
    var getCategoriaDocenteByPaginaSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.listarCategoriaDocente = [];
        $scope.listarCategoriaDocente = response.lista;
        $scope.total = response.total;
    };
    
    var getCategoriaDocenteByPaginaError = function(response){
        $log.debug("Get paginacionUsuario - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getCategoriaDocenteByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        CategoriaDocenteService.getCategoriaDocenteByPagina(objPagina).then(getCategoriaDocenteByPaginaSuccess, getCategoriaDocenteByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getCategoriaDocenteByPagina();
    };
    
    $scope.getCategoriaDocenteByPagina();
}]);