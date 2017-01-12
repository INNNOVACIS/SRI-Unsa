investigacionApp.controller('ActividadesGeneradasController',['$log', '$scope', '$location', 'SharedService', 'FondoConcursableService', 
    'SemestreService', 'TipoInvestigacionService', 'ActividadesGeneradasService', 'EstructuraAreaInvestigacionService',
    'EstructuraOrganizacionService', 'SRIUnsaConfig' ,function($log, $scope, $location, SharedService, FondoConcursableService, 
    SemestreService, TipoInvestigacionService, ActividadesGeneradasService, EstructuraAreaInvestigacionService,
    EstructuraOrganizacionService, SRIUnsaConfig) {
    
    $scope.sharedService = SharedService;
    $scope.loader = false;
    $scope.loadTable = false;
    $scope.departamentos = [];
    
    $scope.sortType     = 'id'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    
    /******************* Callback Function *******************/

    var getEstructuraOrganizacionServiceSuccess = function(response){
    	$log.debug("GetEstructuraOrganizacion - Success");
        console.log("Respuesta :: ", response);
        angular.forEach(response, function(value, key) {
            if($scope.sharedService.usuarioLogin.idFacultad === value.nidPadre){
                $scope.departamentos.push(value);
            }
        });
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
        $scope.row = ($scope.currentPage - 1) * $scope.currentRango + 1;
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
        $scope.loadTable = false;
    };
    var GetActividadesGeneradasError = function(response){
        $log.debug("GetActividadesGeneradas - Error");
        console.log("Respuesta :: ", response);
        $scope.loadTable = false;
    };
    
    $scope.getActividades = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.usuarioLogin.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.GetActividadesGeneradas(objPagina).then(GetActividadesGeneradasSuccess, GetActividadesGeneradasError);
    };
    
    $scope.filtrar = function() {
        $scope.loadTable = true;
        setTimeout(function(){
            $scope.getActividades();
        }, 500);
    };
     
    $scope.getEstructuraOrganizaciones();
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
 
    $scope.getActividades();
    
    $scope.verActividadById = function(actividadGenerada){
        $scope.sharedService.scrollTop();
        $scope.loader = true;
        $location.path("/actividad/Generadas/" + actividadGenerada.idactividadinvestigacion);
    };
    $scope.updateActividadById = function(actividadGenerada){
        $scope.sharedService.scrollTop();
        $scope.loader = true;
        $location.path("/actividad/Generadas/update/" + actividadGenerada.idactividadinvestigacion);
    };
    $scope.EnviarEmail = function(actividadGenerada){
        $scope.loader = true;     
        var actividadGeneral = {
            idUsuario : $scope.sharedService.usuarioLogin.idUsuario,
            idFlujoActorOrigen : SRIUnsaConfig.DOCE,
            idEstado : SRIUnsaConfig.CREADO,
            idPlanificacion : -1,
            actividadInvestigacion : {
                nidActividadInvestigacion : actividadGenerada.idactividadinvestigacion
            }
        };
        ActividadesGeneradasService.EnviarEmail(actividadGeneral).then(EnviarEmailSuccess, EnviarEmailError);
    };
    
    /******************* EXPORTAR ARCHIVOS *******************/
    
    var descargarPDFSuccess = function (response){
        $log.debug("descargarPDF - Success");
        console.log("Respuesta :: ", response);
    };
    var descargarPDFError = function (response){
        $log.debug("descargarPDF - Error");
        console.log("Respuesta :: ", response);
    };
    
    var descargarExcelSuccess = function (response){
        $log.debug("descargarExcel - Success");
        console.log("Respuesta :: ", response);
    };
    var descargarExcelError = function (response){
        $log.debug("descargarExcel - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.descargarPDF = function(){
        console.log("Empezando descarga de PDF...");
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.usuarioLogin.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.descargarPDF(pagina).then(descargarPDFSuccess, descargarPDFError);
    };
    
    $scope.descargarExcel = function(){
        console.log("Empezando descarga de Excel...");
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.usuarioLogin.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.descargarExcel(pagina).then(descargarExcelSuccess, descargarExcelError);
    };
    
}]);
