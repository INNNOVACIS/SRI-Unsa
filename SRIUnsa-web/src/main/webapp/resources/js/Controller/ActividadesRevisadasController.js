investigacionApp.controller('ActividadesRevisadasController', function($log, $scope, ActividadesRevisadasService, 
    SemestreService, TipoInvestigacionService, FondoConcursableService, TipoNivelService, EstructuraOrganizacionService) {    
    
    $scope.pageDirectiva = {
        currentPage : 1,
        rango : 5,
        total : 12,
        filtro : {}
    };   
    
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
    
    /*********** Servicios Callback ***********/  
    
    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("Get tipoNivel - Success");
    	console.log("Success :: ", response);
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
        console.log("Estructura Organizacion :: ", $scope.estructuraOrganizaciones);
    };

    var getEstructuraOrganizacionServiceError = function(response){
     	$log.debug("Get EstructuraOrganizacion - Error"); 
    };
    
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
    
    /******************* Servicios *******************/
    
    $scope.getListaTipoNivel = function(){
      	TipoNivelService.getListaTipoNivel().then(getTipoNivelServiceSuccess, getTipoNivelServiceError);
    };

    $scope.getEstructuraOrganizaciones = function(){
      	EstructuraOrganizacionService.getEstructuraOrganizaciones().then(getEstructuraOrganizacionServiceSuccess, getEstructuraOrganizacionServiceError);
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
    
    $scope.facultadChange = function(){
        $scope.departamento = {};
        $scope.escuela = {};
    };
    $scope.departamentoChange = function(){
        $scope.escuela = {};
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
        $scope.actividadesRevisadas = response;
        console.log("success :: ", response);
    };
    var paginacionActividadesError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getFilas = function(pagina, rango){
        $scope.pageDirectiva.currentPage = pagina;
        $scope.pageDirectiva.rango = rango;
        $scope.pageDirectiva.filtro = getFiltros();
        console.log("filtro :: ", $scope.pageDirectiva);
        ActividadesRevisadasService.paginacionActividades($scope.pageDirectiva).then(paginacionActividadesSuccess, paginacionActividadesError);
    };
    
    $scope.filtrar = function() {
        $scope.getFilas(1);
    };
        
    $scope.getListaTipoNivel();
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.getFilas(1, 5);
    //$scope.getActividadesRevisadas();
});