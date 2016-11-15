investigacionApp.controller('ActividadesPendientesController', function($log, $scope, FondoConcursableService, 
    SemestreService, TipoInvestigacionService, ActividadesPendientesService, TipoNivelService, EstructuraOrganizacionService) {
        
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
            }else{
                $scope.panelGenerados = false;
                $scope.panelVer = false;
                $scope.panelEditar = true;
            }
        }
    };
    
    /******************* Servicios Callback *******************/
    
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
    
    var getInvestigacionByIdSuccess = function(response){
        console.log("getInvestigacionByIdSuccess :: ", response);
        $scope.actividadPendienteVista = response;
    };
    
    var getInvestigacionByIdError = function(response){
        console.log("getInvestigacionByIdError :: ", response);
    };
    
    var getAllActividadesPendientessSuccess = function(response){
        $scope.actividadesPendientes = response;
        console.log("succcess :: ", response);
    };
    
    var getAllActividadesPendientesError = function(response){
        console.log("error :: ", response);
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
    
    $scope.getAllActividadesPendientes = function(){
        ActividadesPendientesService.getAllActividadesPendientes().then(getAllActividadesPendientessSuccess, getAllActividadesPendientesError);
    };
    
    $scope.facultadChange = function(){
        $scope.departamento = {};
        $scope.escuela = {};
    };
    $scope.departamentoChange = function(){
        $scope.escuela = {};
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
        $scope.getActividades();
    });
    
    /*********************************************/
    
    var getFiltros = function(){
        var filtro = {
            nidTipoActividadInvestigacion : ($scope.tipoInvestigacion === null || $scope.tipoInvestigacion === undefined) ? "" : $scope.tipoInvestigacion.nidTipoActividadInvestigacion,
            sfacultad : ( $scope.facultad === null || $scope.facultad === undefined) ? "" : $scope.facultad.snombreEstructuraOrganizacion,
            sdepartamento : ($scope.departamento === null || $scope.departamento === undefined) ? "" : $scope.departamento.snombreEstructuraOrganizacion,
            sescuela : ($scope.escuela === null || $scope.escuela === undefined) ? "" : $scope.escuela.snombreEstructuraOrganizacion,
            ssemestre : ($scope.semestre === null || $scope.semestre === undefined) ? "" : $scope.semestre.snombreSemestre,
            sfondoConcursable : ($scope.fondo === null || $scope.fondo === undefined) ? "" : $scope.fondo.snombreFondoConcursable
        };
        return filtro;    
    };
    
    var paginacionActividadesSuccess = function(response){
        $scope.actividadesPendientes = response.lista;
        $scope.total = response.total;
    };
    var paginacionActividadesError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getActividades = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : getFiltros()};
        ActividadesPendientesService.paginacionActividades(objPagina).then(paginacionActividadesSuccess, paginacionActividadesError);
    };
    
    $scope.filtrar = function() {
        $scope.getActividades();
    };
    
    $scope.getListaTipoNivel();
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    
    $scope.getActividades();
});
