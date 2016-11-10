investigacionApp.controller('ActividadesGeneradasController', function($log, $scope, FondoConcursableService, 
    SemestreService, TipoInvestigacionService, ActividadesGeneradasService, EstructuraAreaInvestigacionService) {
	
    $scope.panelGenerados = true;
    $scope.panelVer = false;
    $scope.panelEditar = false;

    $scope.panelChange = function(panel, actividadGenerada){
        if(panel === 1){
            $scope.panelGenerados = true;
            $scope.panelVer = false;
            $scope.panelEditar = false;
        }else{
            if(panel === 2){
                $scope.panelGenerados = false;
                $scope.panelVer = true;
                $scope.panelEditar = false;
                $scope.actividadGenerada = actividadGenerada;
                $scope.getActividadById(actividadGenerada);
            }else{
                $scope.panelGenerados = false;
                $scope.panelVer = false;
                $scope.panelEditar = true;
                $scope.actividadGenerada = actividadGenerada;
                $scope.getActividadById(actividadGenerada);
            }
        }
    };
    
    /******************* Servicios Callback *******************/
    
    var getFondoServiceSuccess = function(response){
    	$log.debug("Get Fondo - Success");
    	$scope.fondos = response;
    };

    var getFondoServiceError = function(response){
     	$log.debug("Get Fondo - Error", response); 
    };
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("Get Semestre - Success");        
    	$scope.semestres = response;
    };
    
    var getSemestreServiceError = function(response){
     	$log.debug("Get Semestre - Error");
        console.log("Error Response Semestre :: ", response);
    };
    
    var getTipoInvestigacionSuccess = function(response){
    	$log.debug("Get Investigacion - Success");
        console.log("Respuesta Investigacion :: ", response);
    	$scope.tipoInvestigaciones = response;
    };
    
    var getTipoInvestigacionError = function(response){
     	$log.debug("Get Investigacion - Error");
        console.log("Error Response Investigacion :: ", response);
    };
    
    var getInvestigacionByIdSuccess = function(response){
        console.log("getInvestigacionByIdSuccess :: ", response);
        $scope.actividadGeneradaVista = response;
        editarActividad(response);
    };
    
    var getInvestigacionByIdError = function(response){
        console.log("getInvestigacionByIdError :: ", response);
    };
    
    var getAllActividadesGeneradasSuccess = function(response){
        $scope.actividadesGeneradas = response;
        console.log("succcess :: ", response);
    };
    
    var getAllActividadesGeneradasError = function(response){
        console.log("error :: ", response);
    };
    
    /******************* Servicios *******************/
    
    var getAreaInvestigacionServiceSuccess = function(response){
    	$log.debug("Get AreaInvestigacion - Success");
    	$scope.areaInvestigaciones = response;
    };

    var getAreaInvestigacionServiceError = function(response){
     	$log.debug("Get AreaInvestigacion - Error"); 
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
    
    $scope.getActividadById = function(actividad){
        TipoInvestigacionService.getInvestigacionesById(actividad.idactividadinvestigacion).then(getInvestigacionByIdSuccess, getInvestigacionByIdError);
    };
    
    $scope.getTipoInvestigacion = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getTipoInvestigacionSuccess, getTipoInvestigacionError);
    };
    
    $scope.getAllActividadesGeneradas = function(){
        ActividadesGeneradasService.getAllActividadesGeneradas().then(getAllActividadesGeneradasSuccess, getAllActividadesGeneradasError);
    };
    
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.getAllActividadesGeneradas();
    $scope.getAreaInvestigaciones();
    
    /************ Editar Actividad Investigacion ***************/
    
    var editarActividad = function(actividad){
        $scope.semestre = $scope.semestres[seleccionarSemestre(actividad.ssemestre)];
        $scope.tipoInvestigacion = seleccionarTipoInvestigacion(actividad.nidTipoActividadInvestigacion);
        $scope.areaInvestigacion = seleccionarArea(actividad.sareaInvestigacion);
        $scope.subareaInvestigacion = seleccionarArea(actividad.ssubAreaInvestigacion);
        $scope.disciplinaInvestigacion = seleccionarArea(actividad.sdisciplina);
    };
    
    var seleccionarSemestre = function(nombre){
        var respuesta = -1;
        angular.forEach($scope.semestres, function(obj, key) {
            if(obj.snombreSemestre === nombre){
                respuesta = key;
            }
        });
        return respuesta;
    };
    
    var seleccionarTipoInvestigacion = function(idTipoActividad){
        var respuesta = null;
        angular.forEach($scope.tipoInvestigaciones, function(obj, key){
            if(obj.nidTipoActividadInvestigacion === idTipoActividad){
                respuesta = obj;
            }
        });
        return respuesta;
    };
    
    var seleccionarArea = function(nombre){
        var respuesta = null;
        angular.forEach($scope.areaInvestigaciones, function(obj, key){
            if(obj.sNombre === nombre){
                respuesta = obj;
            }
        });
        return respuesta;
    };
    
//    var seleccionarSubArea = function(nombre){
//        var respuesta = null;
//        angular.forEach($scope.areaInvestigaciones, function(obj, key){
//            if(obj.sNombre === nombre){
//                respuesta = obj;
//            }
//        });
//        return respuesta;
//    };
//    
//    var seleccionarDisciplina = function(nombre){
//        var respuesta = null;
//        angular.forEach($scope.areaInvestigaciones, function(obj, key){
//            if(obj.sNombre === nombre){
//                respuesta = obj;
//            }
//        });
//        return respuesta;
//    };
    
});
