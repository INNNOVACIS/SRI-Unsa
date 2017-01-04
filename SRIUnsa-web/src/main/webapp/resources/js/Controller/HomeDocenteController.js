investigacionApp.controller('HomeDocenteController', function($log, $scope, $location,  TipoInvestigacionService, SharedService,
    ActividadesGeneradasService, SRIUnsaConfig, SemestreService, UsuariosService) {

    $scope.sharedService = SharedService;
    $scope.sortType     = 'id'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("GetSemestre - Success");
        console.log("Respuesta :: ", response);
    	$scope.semestres = response;
        setPeriodo();
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
//        $scope.loadTable = false;
    };
    var GetActividadesGeneradasError = function(response){
        $log.debug("GetActividadesGeneradas - Error");
        console.log("Respuesta :: ", response);
//        $scope.loadTable = false;
    };
    
    var GetUsuarioHomeSuccess = function(response){
        $log.debug("GetUsuarioHome - Success");
        console.log("Respuesta :: ", response);
        sessvars.usuarioHome = response.body;
        $scope.sharedService.usuarioHome = response.body;
    };
    var GetUsuarioHomeError = function(response){
        $log.debug("GetUsuarioHome - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };
    $scope.GetTipoInvestigaciones = function(){
        TipoInvestigacionService.getInvestigaciones().then(GetTipoInvestigacionesSuccess, GetTipoInvestigacionesError);
    };
    $scope.GetUsuarioHome = function(idUsuario){
        UsuariosService.GetUsuarioHome(idUsuario).then(GetUsuarioHomeSuccess, GetUsuarioHomeError);
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
    
    var setPeriodo = function(){
        angular.forEach($scope.semestres, function(value, key){
            if(value.snombreSemestre === "2016-II Semestre II"){
                $scope.semestre = value;
            }
        });
        $scope.getActividades();
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
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.idDocente, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.GetActividadesGeneradas(objPagina).then(GetActividadesGeneradasSuccess, GetActividadesGeneradasError);
    };
    
    if($scope.sharedService.idUsuarioRegistrar === -1){
        console.log("Vicerector o Director de Unidad :: ", $scope.sharedService.idUsuarioRegistrar);
    } else {
        console.log("Docente a Registrar :: ", $scope.sharedService.idUsuarioRegistrar);
        $scope.GetUsuarioHome($scope.sharedService.idUsuarioRegistrar);
    }
    
    $scope.getSemestres();
    $scope.GetTipoInvestigaciones();
});