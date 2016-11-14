investigacionApp.controller('ActividadesGeneradasController', function($log, $scope, FondoConcursableService, 
    SemestreService, TipoInvestigacionService, ActividadesGeneradasService, EstructuraAreaInvestigacionService,
    TipoNivelService, EstructuraOrganizacionService) {
	
    $scope.pageDirectiva = {
        currentPage : 1,
        rango : 5,
        total : 12,
        filtro : {}
    };   
     
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
    
    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("Get tipoNivel - Success");    	
    	$scope.niveles = response;
        $scope.getEstructuraOrganizaciones();
    };

    var getTipoNivelServiceError = function(response){
     	$log.debug("Get TipoNivel - Error"); 
    };

    var getEstructuraOrganizacionServiceSuccess = function(response){
    	$log.debug("Get EstructuraOrganizacion - Success");
        
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
     	$log.debug("Get EstructuraOrganizacion - Error"); 
    };
    
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
    
    var getAreaInvestigacionServiceSuccess = function(response){
    	$log.debug("Get AreaInvestigacion - Success");
    	$scope.areaInvestigaciones = response;
    };

    var getAreaInvestigacionServiceError = function(response){
     	$log.debug("Get AreaInvestigacion - Error"); 
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
    
    $scope.getActividadById = function(actividad){
        TipoInvestigacionService.getInvestigacionesById(actividad.idactividadinvestigacion).then(getInvestigacionByIdSuccess, getInvestigacionByIdError);
    };
    
    $scope.getTipoInvestigacion = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getTipoInvestigacionSuccess, getTipoInvestigacionError);
    };
    
    $scope.getAllActividadesGeneradas = function(){
        ActividadesGeneradasService.getAllActividadesGeneradas().then(getAllActividadesGeneradasSuccess, getAllActividadesGeneradasError);
    };
    
    $scope.facultadChange = function(){
        $scope.departamento = {};
        $scope.escuela = {};
    };
    $scope.departamentoChange = function(){
        $scope.escuela = {};
    };
    
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
    
    var getFiltros = function(){
        var filtro = {
            nidTipoActividadInvestigacion : $scope.tipoInvestigacion === undefined ? "" : $scope.tipoInvestigacion.nidTipoActividadInvestigacion,
            sfacultad : $scope.facultad === undefined ? "" : $scope.facultad.snombreEstructuraOrganizacion,
            sdepartamento : $scope.departamento === undefined ? "" : $scope.departamento.snombreEstructuraOrganizacion,
            sescuela : $scope.escuela === undefined ? "" : $scope.escuela.snombreEstructuraOrganizacion,
            ssemestre : $scope.semestre === undefined ? "" : $scope.semestre.snombreSemestre,
            sfondoConcursable : $scope.fondo === undefined ? "" : $scope.fondo.snombreFondoConcursable
        };
        return filtro;
    };
    
    var paginacionActividadesSuccess = function(response){
        $scope.actividadesGeneradas = response.lista;
        $scope.controladorTotal = response.total;
    };
    var paginacionActividadesError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getFilas = function(pagina, rango){
        $scope.pageDirectiva.currentPage = pagina;
        $scope.pageDirectiva.rango = rango;
        $scope.pageDirectiva.filtro = getFiltros();
        console.log("filtro :: ", $scope.pageDirectiva);
        ActividadesGeneradasService.paginacionActividades($scope.pageDirectiva).then(paginacionActividadesSuccess, paginacionActividadesError);
    };
    
    $scope.filtrar = function() {
        $scope.getFilas(1, 5);
    };
    
        
    $scope.getListaTipoNivel();
    $scope.getEstructuraOrganizaciones();
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.getFilas(1, 5);
    $scope.getAreaInvestigaciones();
 
});
