investigacionApp.controller('RelacionDocentesActivosInactivosController',['$log', '$scope', 'SharedService', 'FondoConcursableService', 
    'SemestreService', 'TipoInvestigacionService', 'EstructuraAreaInvestigacionService', 'TipoNivelService', 
    'EstructuraOrganizacionService', 'RelacionDocentesActivosInactivosService', 'SRIUnsaConfig', 'UsuariosService', 
    function($log, $scope, SharedService, FondoConcursableService, SemestreService, TipoInvestigacionService, 
    EstructuraAreaInvestigacionService, TipoNivelService, EstructuraOrganizacionService, 
    RelacionDocentesActivosInactivosService, SRIUnsaConfig, UsuariosService) {	
    
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
        $scope.semestre = verificarSemestre($scope.semestres);
        $scope.GetDocenteActivos();
        $scope.GetTotalDocentesActivosInactivos();
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
    
    var descargarPDFSuccess = function(response){
        $log.debug("descargarPDF - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var descargarPDFError = function(response){
        $log.debug("descargarPDF - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    var descargarExcelSuccess = function(response){
        $log.debug("descargarExcel - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var descargarExcelError = function(response){
        $log.debug("descargarExcel - Error");
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
        $scope.loader = true;
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };
    $scope.getTipoInvestigacion = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getTipoInvestigacionSuccess, getTipoInvestigacionError);
    };
    $scope.descargarPDF = function(){
        $scope.loader = true;
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.usuarioLogin.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        UsuariosService.descargarPdfDocentesActivosInactivos(objPagina).then(descargarPDFSuccess, descargarPDFError);
    };
    $scope.descargarExcel = function(){
        $scope.loader = true;
        var objPagina = 
                { 
                    currentPage : $scope.currentPage, 
                    rango : $scope.currentRango, 
                    total : $scope.total,
                    idUsuario: $scope.sharedService.usuarioLogin.idUsuario, 
                    idEstado: SRIUnsaConfig.CREADO, 
                    idFlujoActor: "", 
                    filtro : getFiltros()
                };
        UsuariosService.descargarExcelDocentesActivosInactivos(objPagina).then(descargarExcelSuccess, descargarExcelError);
    };
    
    $scope.facultadChange = function(){
        $scope.departamento = {};
        $scope.escuela = {};
    };
    $scope.departamentoChange = function(){
        $scope.escuela = {};
    };
    
    var verificarSemestre = function(semestres){
        var currentDate = new Date();
        var semestre = {};
        angular.forEach(semestres, function(value, key){
            if(value.dinicioSemestre < currentDate && value.dfinSemestre > currentDate){
                semestre = value;
            }
        });
        return semestre;
    };
    
    var GetTotalDocentesActivosInactivosSuccess = function(response){
        $log.debug("GetTotalDocentesActivosInactivosFiltro - Success");
        console.log("Respuesta :: ", response);
        $scope.docentes = response.body;
    };
    var GetTotalDocentesActivosInactivosError = function(response){
        $log.debug("GetTotalDocentesActivosInactivos - Error");
        console.log("Respuesta :: ", response.body);
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
        if($scope.semestre != null && $scope.semestre != undefined) {
            $scope.GetDocenteActivos();
            $scope.GetTotalDocentesActivosInactivos();
        }
        
        $scope.row = ($scope.currentPage - 1) * $scope.currentRango + 1;
    });
    
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
    
    var GetActividadesByDocenteSuccess = function(response){
        $log.debug("GetActividadByDocente - Success");
        console.log("Respuesta :: ", response);
        $scope.actividadesDocentes = response.lista;
        $scope.total = response.total;
        $scope.loader = false;
    };
    var GetActividadesByDocenteError = function(response){
        $log.debug("GetActividadByDocente - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    $scope.GetDocenteActivos = function(){
        $scope.loader = true;
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        RelacionDocentesActivosInactivosService.GetDocentesActivosInactivos(objPagina).then(GetActividadesByDocenteSuccess, GetActividadesByDocenteError);
    };
    
    
    
    
    
    $scope.GetTotalDocentesActivosInactivos = function(){
        var request = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        RelacionDocentesActivosInactivosService.GetTotalDocentesActivosInactivos(request).then(GetTotalDocentesActivosInactivosSuccess, GetTotalDocentesActivosInactivosError);
    };
    
    
    
    
    
    $scope.filtrar = function() {
        $scope.GetDocenteActivos();
        $scope.GetTotalDocentesActivosInactivos();
    };
    
    $scope.getListaTipoNivel();
    $scope.getEstructuraOrganizaciones();
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.getAreaInvestigaciones();
    
}]);
