investigacionApp.controller('RelacionDocentesController', function($log, $scope, SharedService, FondoConcursableService, 
    SemestreService, TipoInvestigacionService, EstructuraAreaInvestigacionService, TipoNivelService, EstructuraOrganizacionService) {	
    
    $scope.sharedService = SharedService;
    $scope.loader = false;
    
    /******************* Servicios Callback *******************/
    
    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("GettipoNivel - Success");
        console.log("Respuesta :: ", response);
    	$scope.niveles = response;
        $scope.getEstructuraOrganizaciones();
    };
    var getTipoNivelServiceError = function(response){
     	$log.debug("GetTipoNivel - Error"); 
        console.log("Respuesta :: ", response);
    };

    var getEstructuraOrganizacionServiceSuccess = function(response){
    	$log.debug("GetEstructuraOrganizacion - Success");
        console.log("Respuesta :: ", response);
        angular.forEach(response, function(superior, key) {
            angular.forEach(response, function(value, key) {
                if(superior.nidPadre === value.nidEstructuraOrganizacion){
                    superior.nombrePadre = value.snombreEstructuraOrganizacion;
                }
            });
            angular.forEach($scope.niveles, function(nivel, key) {
                if(superior.nidTipoNivel === nivel.nidTipoNivel){
                    superior.nombreTipoNivel = nivel.snombreTipoNivel;
                }
            });
        });
    	$scope.estructuraOrganizaciones = response;
    };
    var getEstructuraOrganizacionServiceError = function(response){
     	$log.debug("GetEstructuraOrganizacion - Error"); 
        console.log("Respuesta :: ", response);
    };
    
    var getFondoServiceSuccess = function(response){
    	$log.debug("GetFondo - Success");
        console.log("Respuesta :: ", response);
    	$scope.fondos = response;
    };
    var getFondoServiceError = function(response){
     	$log.debug("GetFondo - Error", response);
        console.log("Respuesta :: ", response);
    };
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("GetSemestre - Success");
        console.log("Respuesta :: ", response);
    	$scope.semestres = response;
    };
    var getSemestreServiceError = function(response){
     	$log.debug("GetSemestre - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getTipoInvestigacionSuccess = function(response){
    	$log.debug("GetInvestigacion - Success");
        console.log("Respuesta :: ", response);
    	$scope.tipoInvestigaciones = response;
    };   
    var getTipoInvestigacionError = function(response){
     	$log.debug("GetInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getAreaInvestigacionServiceSuccess = function(response){
    	$log.debug("GetAreaInvestigacion - Success");
        console.log("Respuesta :: ", response);
    	$scope.areaInvestigaciones = response;
    };
    var getAreaInvestigacionServiceError = function(response){
     	$log.debug("GetAreaInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };
    
    /******************* Servicios *******************/
    
    $scope.getListaTipoNivel = function(){
      	TipoNivelService.getListaTipoNivel().then(getTipoNivelServiceSuccess, getTipoNivelServiceError);
    };
    $scope.getEstructuraOrganizaciones = function(){
      	EstructuraOrganizacionService.getEstructuraOrganizaciones().then(getEstructuraOrganizacionServiceSuccess, getEstructuraOrganizacionServiceError);
    };   
    $scope.getAreaInvestigaciones = function(){
      	EstructuraAreaInvestigacionService.getAreaInvestigaciones().then(getAreaInvestigacionServiceSuccess, getAreaInvestigacionServiceError);
    };   
    $scope.getFondos = function(){
      	FondoConcursableService.getFondos().then(getFondoServiceSuccess, getFondoServiceError);
    };   
    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };
    $scope.getTipoInvestigacion = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getTipoInvestigacionSuccess, getTipoInvestigacionError);
    };
    
    $scope.facultadChange = function(){
        $scope.departamento = {};
        $scope.escuela = {};
    };
    $scope.departamentoChange = function(){
        $scope.escuela = {};
    };
    
    $scope.getListaTipoNivel();
    $scope.getEstructuraOrganizaciones();
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.getAreaInvestigaciones();
    
});
