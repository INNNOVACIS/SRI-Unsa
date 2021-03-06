investigacionApp.controller('ActividadesDocenteController',['$log', '$scope', '$location',  'TipoInvestigacionService', 'SharedService',
    'ActividadesGeneradasService', 'SRIUnsaConfig', 'SemestreService'
    ,function($log, $scope, $location,  TipoInvestigacionService, SharedService,
    ActividadesGeneradasService, SRIUnsaConfig, SemestreService) {

    $scope.sharedService = SharedService;
    $scope.sortType     = 'id'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    $scope.nombreCompleto = $scope.sharedService.docente.snombre + " " + $scope.sharedService.docente.sapellido;
    $scope.loader = true;
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("GetSemestre - Success");
        console.log("Respuesta :: ", response);
    	$scope.semestres = response;
        $scope.semestre = verificarSemestre($scope.semestres);
        $scope.getActividades();
    };
    var getSemestreServiceError = function(response){
     	$log.debug("GetSemestre - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetTipoInvestigacionesSuccess = function(response){
        $log.debug("GetTipoInvestigaciones - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigaciones = response;
    };
    var GetTipoInvestigacionesError = function(response){
        $log.debug("GetTipoInvestigaciones - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetActividadesGeneradasSuccess = function(response){
        $log.debug("GetActividadesGeneradas - Success");
        console.log("Respuesta :: ", response);
        $scope.actividadesGeneradas = [];
        $scope.actividadesGeneradas = response.lista;
        $scope.total = response.total;
        $scope.loader =  false;
    };
    var GetActividadesGeneradasError = function(response){
        $log.debug("GetActividadesGeneradas - Error");
        console.log("Respuesta :: ", response);
        $scope.loader =  false;
    };
    
    $scope.getSemestres = function(){
        $scope.loader = true;
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };
    $scope.GetTipoInvestigaciones = function(){
        TipoInvestigacionService.getInvestigaciones().then(GetTipoInvestigacionesSuccess, GetTipoInvestigacionesError);
    };
 
    $scope.goHome = function(tipoInvestigacion){
        sessvars.tipoInvestigacion = tipoInvestigacion;
        $scope.sharedService.tipoInvestigacion = sessvars.tipoInvestigacion;
        $location.path("/home");
    };
    
    $scope.changeSemestre = function(semestre){
        $scope.semestre = semestre;
        $scope.getActividades();
    };
    
    $scope.verActividadById = function(actividadGenerada){
        $scope.sharedService.scrollTop();
        $scope.loader = true;
        $location.path("/actividad/Generadas/" + actividadGenerada.idactividadinvestigacion);
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
//        $scope.row = ($scope.currentPage - 1) * $scope.currentRango + 1;
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
    
    $scope.getActividades = function(){
        $scope.loader = true;
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.docente.nidUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.GetActividadesGeneradas(objPagina).then(GetActividadesGeneradasSuccess, GetActividadesGeneradasError);
    };
    
    $scope.getSemestres();
    $scope.GetTipoInvestigaciones();
    
    /******************* EXPORTAR ARCHIVOS *****************/
    
    var descargarPDFSuccess = function (response){
        $log.debug("descargarPDF - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var descargarPDFError = function (response){
        $log.debug("descargarPDF - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    var descargarExcelSuccess = function (response){
        $log.debug("descargarExcel - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var descargarExcelError = function (response){
        $log.debug("descargarExcel - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    $scope.descargarPDF = function(){
        $scope.loader = true;
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.idDocente, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.descargarPDF(pagina).then(descargarPDFSuccess, descargarPDFError);
    };
    
    $scope.descargarExcel = function(){
        $scope.loader = true;
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.idDocente, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.descargarExcel(pagina).then(descargarExcelSuccess, descargarExcelError);
    };
    
}]);