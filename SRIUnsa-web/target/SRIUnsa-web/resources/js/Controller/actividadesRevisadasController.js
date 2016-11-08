investigacionApp.controller('actividadesRevisadasController', function($log, $scope, ActividadesRevisadasService, 
    SemestreService, TipoInvestigacionService, FondoConcursableService) {    
    
    $scope.panelGenerados = true;
    $scope.panelVer = false;
    $scope.panelEditar = false;
    $scope.actividadRevisada= {};

    $scope.panelChange = function(panel, actividadRevisada){
        if(panel === 1){
            $scope.panelGenerados = true;
            $scope.panelVer = false;
            $scope.panelEditar = false;
        }else{
            if(panel === 2){
                $scope.panelGenerados = false;
                $scope.panelVer = true;
                $scope.panelEditar = false;
                $scope.actividadRevisada = actividadRevisada;
                $scope.getActividadById(actividadRevisada);
            }else{
                $scope.panelGenerados = false;
                $scope.panelVer = false;
                $scope.panelEditar = true;
            }
        }
    };
    
    var getFiltroSuccess = function(response){
       console.log("success :: ", response);
    };
    var getFiltroError = function(response){
       console.log("Error :: " , response);
    };
    
    
    $scope.filtrar = function(){
        var filtro = {
            tipoInvestigacion : $scope.actividad.nombre,
            facultad : $scope.facultad.nombre,
            escuela : $scope.escuela.nombre,
            semestre : $scope.semestre.nombre,
            fondo : $scope.fondo.nombre
        };
        console.log("filtrar :: ", filtro);
        ActividadesRevisadasService.Filtrar(filtro).then(getFiltroSuccess, getFiltroError);
    };
    
    /*********** Servicios Get All ***********/  
    
    var getFondoServiceSuccess = function(response){
    	$log.debug("Get Fondo - Success");
    	$scope.fondos = response;
    };

    var getFondoServiceError = function(response){
     	$log.debug("Get Fondo - Error"); 
    };
    
    var getActividadesRevisadasSuccess = function(response){
        $scope.actividadesRevisadas = response;
        console.log("succcess :: ", response);
    };
    
    var getActividadesRevisadasError = function(response){
        console.log("error :: ", response);
    };
    
    var getTipoInvestigacionSuccess = function(response){
    	$log.debug("Get Investigacion - Success");
        console.log("Response Investigacion :: ", response);
    	$scope.tipoInvestigaciones = response;
    };
    
    var getTipoInvestigacionError = function(response){
     	$log.debug("Get Investigacion - Error");
        console.log("Error Response Investigacion :: ", response);
    };
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("Get Semestre - Success");        
    	$scope.semestres = response;
    };
    
    var getSemestreServiceError = function(response){
     	$log.debug("Get Semestre - Error");
        console.log("Error Response Semestre :: ", response);
    };
    
    var getInvestigacionByIdSuccess = function(response){
        console.log("getInvestigacionByIdSuccess :: ", response);
        $scope.actividadRevisadaVista = response;
    };
    
    var getInvestigacionByIdError = function(response){
        console.log("getInvestigacionByIdError :: ", response);
    };
    
    
    $scope.getFondos = function(){
      	FondoConcursableService.getFondos().then(getFondoServiceSuccess, getFondoServiceError);
    };
    
    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };
    
    $scope.getActividadById = function(actividad){
        TipoInvestigacionService.getInvestigacionesById(actividad.idactividadinvestigacion).then(getInvestigacionByIdSuccess, getInvestigacionByIdError);
    };
    
    $scope.getTipoInvestigacion = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getTipoInvestigacionSuccess, getTipoInvestigacionError);
    };
    
    $scope.getActividadesRevisadas = function(){
        ActividadesRevisadasService.getInvestigaciones().then(getActividadesRevisadasSuccess, getActividadesRevisadasError);
    };
    
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.getActividadesRevisadas();
});