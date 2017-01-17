investigacionApp.controller('ActividadesPendientesController',['$log', '$scope', '$location', 'FondoConcursableService', 'SRIUnsaConfig',
    'SemestreService', 'TipoInvestigacionService', 'ActividadesPendientesService', 'ArchivosService', 'EstructuraOrganizacionService', 'SharedService',
    'UsuarioFlujoService' ,function($log, $scope, $location, FondoConcursableService, SRIUnsaConfig,
    SemestreService, TipoInvestigacionService, ActividadesPendientesService, ArchivosService, EstructuraOrganizacionService, SharedService,
    UsuarioFlujoService ) {
    
    $scope.sharedService = SharedService;
    $scope.departamentos = [];
    $scope.loader = false;
    $scope.loadTable = false;
    
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
    
    var CreateAndGetUsuarioFlujoSuccess = function(response){
        $log.debug("CreateAndGetUsuarioFlujo - Success");
        console.log("Respuesta :: ", response);
    };
    var CreateAndGetUsuarioFlujoError = function(response){
        $log.debug("CreateAndGetUsuarioFlujo - Error");
        console.log("Respuesta :: ", response);
    };
    
    var descargarArchivoSuccess = function(response){
        $log.debug("descargarArchivo - Success");
        console.log("Respuesta :: ", response);
    };
    var descargarArchivoError = function(response){
        $log.debug("descargarArchivo - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getArchivosByIdActividadSuccess = function(response){
        $log.debug("getArchivosByIdActividad - Success");
        console.log("Respuesta :: ", response);
        angular.forEach(response, function(value, key){
            ArchivosService.descargarArchivo(value.id).then(descargarArchivoSuccess, descargarArchivoError);
        });
    };
    var getArchivosByIdActividadError = function(response){
        $log.debug("getArchivosByIdActividad - Error");
        console.log("Respuesta :: ", response);
    };
    
    /******************* Servicios *******************/

    $scope.getEstructuraOrganizaciones = function(){
      	EstructuraOrganizacionService.getEstructuraOrganizaciones().then(getEstructuraOrganizacionServiceSuccess, getEstructuraOrganizacionServiceError);
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
    $scope.CrearOrActualizarUsuarioFlujo = function(){
        var usuarioFlujo = {
            nidFlujoActor : SRIUnsaConfig.DIDE,
            nidUsuario : $scope.sharedService.usuarioLogin.idUsuario
        };
        UsuarioFlujoService.CreateAndGetUsuarioFlujo(usuarioFlujo).then(CreateAndGetUsuarioFlujoSuccess, CreateAndGetUsuarioFlujoError);
    };
    
    $scope.getArchivosByIdActividad = function(actividadPendiente){
        var idActividadInvestigacion = actividadPendiente.idactividadinvestigacion;
        ArchivosService.getArchivosByIdActividad(idActividadInvestigacion).then(getArchivosByIdActividadSuccess, getArchivosByIdActividadError);
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
            nidTipoActividadInvestigacion : ($scope.tipoInvestigacion === null || $scope.tipoInvestigacion === undefined) ? "" : $scope.tipoInvestigacion.nidTipoActividadInvestigacion,
            sfacultad : ( $scope.facultad === null || $scope.facultad === undefined) ? "" : $scope.facultad.snombreEstructuraOrganizacion, //$scope.sharedService.usuarioLogin.idFacultad,
            sdepartamento : ($scope.departamento === null || $scope.departamento === undefined) ? "" : $scope.departamento.snombreEstructuraOrganizacion,
            sescuela : ($scope.escuela === null || $scope.escuela === undefined) ? "" : $scope.escuela.snombreEstructuraOrganizacion,
            ssemestre : ($scope.semestre === null || $scope.semestre === undefined) ? "" : $scope.semestre.snombreSemestre,
            sfondoConcursable : ($scope.fondo === null || $scope.fondo === undefined) ? "" : $scope.fondo.snombreFondoConcursable
        };
        return filtro;    
    };
    
    var paginacionActividadesSuccess = function(response){
        $log.debug("paginacionActividadesPendientes - Success");
        console.log("Respuesta :: ", response);
        $scope.actividadesPendientes = response.lista;
        $scope.total = response.total;
        $scope.loadTable = false;
    };
    var paginacionActividadesError = function(response){
        $log.debug("paginacionActividadesPenedientes - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getActividades = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.usuarioLogin.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: SRIUnsaConfig.DIDE, 
                          filtro : getFiltros()};
        ActividadesPendientesService.paginacionActividades(objPagina).then(paginacionActividadesSuccess, paginacionActividadesError);
    };
    
    $scope.filtrar = function() {
        $scope.loadTable = true;
        setTimeout(function(){
            $scope.getActividades();
        }, 500);
    };
    
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.getEstructuraOrganizaciones();
    $scope.CrearOrActualizarUsuarioFlujo();
    
    $scope.getActividades();
    
    $scope.verActividadById = function(actividadPendiente){
        $scope.loader = true;
        $location.path("/actividad/Pendientes/"+ actividadPendiente.idactividadinvestigacion);
    };
    
    /*********** IMPRIMIR REPORTE ***********/
    
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
                          idUsuario: $scope.sharedService.usuarioLogin.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: SRIUnsaConfig.DIDE, 
                          filtro : getFiltros()};
        ActividadesPendientesService.descargarPDF(pagina).then(descargarPDFSuccess, descargarPDFError);
    };
    
    $scope.descargarExcel = function(){
        console.log("Empezando descarga de Excel...");
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.usuarioLogin.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: SRIUnsaConfig.DIDE, 
                          filtro : getFiltros()};
        ActividadesPendientesService.descargarExcel(pagina).then(descargarExcelSuccess, descargarExcelError);
    };
    
}]);
