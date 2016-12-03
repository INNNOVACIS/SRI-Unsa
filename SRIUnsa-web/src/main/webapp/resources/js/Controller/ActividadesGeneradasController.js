investigacionApp.controller('ActividadesGeneradasController', function($log, $scope, $location, SharedService, FondoConcursableService, 
    SemestreService, TipoInvestigacionService, ActividadesGeneradasService, EstructuraAreaInvestigacionService,
    TipoNivelService, EstructuraOrganizacionService, SRIUnsaConfig) {
    
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

    var EnviarEmailSuccess = function(response){
        $log.debug("EnviarEmail - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
        var popUp = SharedService.popUp;
        var titulo = "Tu mensaje ha sido enviado.";
        var mensaje = "El recordatorio de la Actividad fue enviado con éxito.";
        var url = "/actividad/Generadas";

        var op1 = {open:true, txt:'Cerrar', fun:function(){
            popUp.irPopUp();
        }};
        popUp.showPopUp(titulo, mensaje, url, op1);
    };
    var EnviarEmailError = function(response){
        $log.debug("EnviarEmail - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
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
            nidTipoActividadInvestigacion : ($scope.tipoInvestigacion === null || $scope.tipoInvestigacion === undefined || $scope.tipoInvestigacion === "") ? "" : $scope.tipoInvestigacion.nidTipoActividadInvestigacion,
            sfacultad : ( $scope.facultad === null || $scope.facultad === undefined || $scope.facultad === "") ? "" : $scope.facultad.snombreEstructuraOrganizacion,
            sdepartamento : ($scope.departamento === null || $scope.departamento === undefined || $scope.departamento === "") ? "" : $scope.departamento.snombreEstructuraOrganizacion,
            sescuela : ($scope.escuela === null || $scope.escuela === undefined || $scope.escuela === "") ? "" : $scope.escuela.snombreEstructuraOrganizacion,
            ssemestre : ($scope.semestre === null || $scope.semestre === undefined || $scope.semestre === "") ? "" : $scope.semestre.snombreSemestre,
            sfondoConcursable : ($scope.fondo === null || $scope.fondo === undefined || $scope.fondo === "") ? "" : $scope.fondo.snombreFondoConcursable
        };
        return filtro;
    };
    
    var GetActividadesGeneradasSuccess = function(response){
        $log.debug("GetActividadesGeneradas - Success");
        console.log("Respuesta :: ", response);
        $scope.actividadesGeneradas = response.lista;
        $scope.total = response.total;
    };
    var GetActividadesGeneradasError = function(response){
        $log.debug("GetActividadesGeneradas - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getActividades = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.GetActividadesGeneradas(objPagina).then(GetActividadesGeneradasSuccess, GetActividadesGeneradasError);
    };
    
    $scope.filtrar = function() {
        $scope.getActividades();
    };
     
    $scope.getListaTipoNivel();
    $scope.getEstructuraOrganizaciones();
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.getAreaInvestigaciones();
 
    $scope.getActividades();
    
    $scope.verActividadById = function(actividadGenerada){
        $scope.loader = true;
        $location.path("/actividad/Generadas/" + actividadGenerada.idactividadinvestigacion);
    };
    $scope.updateActividadById = function(actividadGenerada){
        $scope.loader = true;
        $location.path("/actividad/Generadas/update/" + actividadGenerada.idactividadinvestigacion);
    };
    $scope.EnviarEmail = function(actividadGenerada){
        $scope.loader = true;     
        var actividadGeneral = {
            idUsuario : $scope.sharedService.idUsuario,
            idFlujoActorOrigen : SRIUnsaConfig.DOCE,
            idEstado : SRIUnsaConfig.CREADO,
            idPlanificacion : -1,
            actividadInvestigacion : {
                nidActividadInvestigacion : actividadGenerada.idactividadinvestigacion
            }
        };
        ActividadesGeneradasService.EnviarEmail(actividadGeneral).then(EnviarEmailSuccess, EnviarEmailError);
    };
});
