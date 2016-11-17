investigacionApp.controller('SemestreController', function($log, $scope, $location, $rootScope, $filter, 
    SemestreService, SharedService) {

    $scope.semestres = [];
    $scope.semestre = {};
	
    /********** Servicios Callback **********/
        
    var getSemestreServiceSuccess = function(response){
    	$log.debug("Get Semestre - Success");
    	$scope.semestres = response;
    };

    var getSemestreServiceError = function(response){
     	$log.debug("Get Semestre - Error"); 
    };

    var registrarSemestreSuccess = function(response){
        
    	$log.debug("Registrar Semestre - Success");
    	$scope.semestres.push($scope.semestre);
    	$scope.semestre = {};
    };

    var registrarSemestreError = function(response){
        $log.debug("Registrar Semestre - Error");
    };

    var updateSemestreSuccess = function(response){
    	$log.debug("Update Semestre - Success");
    	console.log("success :: ", response);
    	$scope.semestre = response;
    };

    var updateSemestreError = function(response){
        $log.debug("Update Semestre - Error");
    };

    var deleteSemestreSuccess = function(response){
    	$log.debug("Delete User - Success");
    	console.log("success :: ", response);
    	$scope.semestre = response;
    };

    var deleteSemestreError = function(response){

    };

    /********** CRUD SEMESTRES ***********/

    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };

    $scope.registrarSemestre = function(){
	SemestreService.registrarSemestre($scope.semestre).then(registrarSemestreSuccess, registrarSemestreError);
    };

    $scope.updateSemestre = function(){
    	SemestreService.updateSemestre($scope.semestre).then(updateSemestreSuccess, updateSemestreError);
    };

    $scope.deleteSemestre = function(semestre){
    	$scope.semestre = semestre;
    	SemestreService.deleteSemestre ($scope.semestre).then(deleteSemestreSuccess. deleteSemestreError);
    };

    $scope.update = function(semestre){
    	$scope.semestre = semestre;
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
        $log.debug("getSemestresByPaginaSuccess - Success");
        $scope.semestres = response.lista;
        $scope.total = response.total;
    };
    
    var getSemestresByPaginaError = function(response){
        console.log("getSemestresByPaginaError  :: ", response);
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