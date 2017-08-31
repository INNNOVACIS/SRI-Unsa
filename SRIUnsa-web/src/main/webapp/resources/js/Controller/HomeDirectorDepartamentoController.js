investigacionApp.controller('HomeDirectorDepartamentoController',['$log', '$scope', 'UsuariosService', '$location', 
    'SharedService', 'HomeDirectorDepartamentoService', 'SRIUnsaConfig', 'ActividadesGeneradasService', 'SemestreService',
    function($log, $scope, UsuariosService, $location, SharedService, HomeDirectorDepartamentoService, 
    SRIUnsaConfig, ActividadesGeneradasService, SemestreService) {

    $scope.sharedService = SharedService;
    $scope.users = [];
    $scope.usuario = {};
    $scope.estados = ['A','I'];
    $scope.idTipoInvestigacion = 0;
    $scope.idDepartamento = 0;
    $scope.currentTipo = 0;
    $scope.currentDepartamento = false;
    
    var GetTotalActivosInactivosHomeDepartamentoSuccess = function(response){
        $log.debug("GetTotalActivosInactivosHomeDepartamento - Success");
        console.log("Respuesta :: ", response);
        GetPorcentaje(response.body[0]);
        $scope.docentes = response.body[0];
    };
    var GetTotalActivosInactivosHomeDepartamentoError = function(response){
        $log.debug("GetTotalActivosInactivosHomeDepartamento - Error");
        console.log("Respuesta :: ", response.body);
    };
    
    var GetTotalActividadesByTipoActividadDepartamentoSuccess = function(response){
        $log.debug("GetTotalActividadesByTipoActividadDepartamento - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigaciones = response.body;
    };
    var GetTotalActividadesByTipoActividadDepartamentoError = function(response){
        $log.debug("GetTotalActividadesByTipoActividadDepartamento - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetTotalActivosInactivosByDepartamentoSuccess = function(response){
        $log.debug("GetTotalActivosInactivosByDepartamento - Success");
        console.log("Respuesta :: ", response);
        $scope.docentesDepartamentos = response.body;
        angular.forEach($scope.docentesDepartamentos, function(value, key){
            value.porcentajeActivo = GetPorcentajeDepartamento(value.ntotal, value.nactivos);
            value.porcentajeInactivo = GetPorcentajeDepartamento(value.ntotal, value.ninactivos);
        });
    };
    var GetTotalActivosInactivosByDepartamentoError = function(response){
        $log.debug("GetTotalActivosInactivosByDepartamento - Error");
        console.log("Respuesta :: ", response);
    };
    
    var enviarInformeDepartamentoSuccess = function(response){
        $log.debug("enviarInformeDepartamento - Success");
        console.log("Respuesta :: ", response);
        $('#modalEnvioExitoso').modal('show');
        $scope.loader = false;
    };
    var enviarInformeDepartamentoError = function(response){
        $log.debug("enviarInformeDepartamento - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("GetSemestre - Success");
        console.log("Respuesta :: ", response);
    	$scope.semestres = response;
        $scope.semestre = verificarSemestre($scope.semestres);
    };
    var getSemestreServiceError = function(response){
     	$log.debug("GetSemestre - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };
    
    $scope.GetTotalActivosInactivosByDepartamento = function(idDepartamento, idTipoInvestigacion, idSemestre){
        HomeDirectorDepartamentoService.GetTotalActivosInactivosHomeDepartamento(idDepartamento, idTipoInvestigacion, idSemestre)
                    .then(GetTotalActivosInactivosByDepartamentoSuccess, GetTotalActivosInactivosByDepartamentoError);
    };
    $scope.GetTotalActivosInactivosHomeDepartamento = function(idDepartamento, idTipoInvestigacion, idSemestre){
        HomeDirectorDepartamentoService.GetTotalActivosInactivosHomeDepartamento(idDepartamento, idTipoInvestigacion, idSemestre)
                    .then(GetTotalActivosInactivosHomeDepartamentoSuccess, GetTotalActivosInactivosHomeDepartamentoError);
    };
        
    $scope.GetTotalActividadesByTipoActividadDepartamento = function(idDepartamento, idSemestre){
        HomeDirectorDepartamentoService.GetTotalActividadesByTipoActividadDepartamento(idDepartamento, idSemestre)
                    .then(GetTotalActividadesByTipoActividadDepartamentoSuccess, GetTotalActividadesByTipoActividadDepartamentoError);
    };

    $scope.registrar = function(usuario){
        console.log("USUARIO :: ", usuario);
        sessvars.docente = usuario;
        sessvars.idDocente = usuario.nidUsuario;
        $scope.sharedService.idDocente = sessvars.idDocente;
        $scope.sharedService.docente = sessvars.docente;
        
        /*
         * Asignamos el id del docente para registrar su actividad
         * 
         * */
        sessvars.idUsuarioRegistrar = sessvars.idDocente;
//        sessvars.idUsuarioDirector = $scope.sharedService.usuarioLogin.idUsuario;
        $scope.sharedService.idUsuarioRegistrar = sessvars.idUsuarioRegistrar;
//        $scope.sharedService.idUsuarioDirector = sessvars.idUsuarioDirector;
        
        
        $location.path("/homedocente");
    };
    
    $scope.setTipoInvestigacion = function(tipoInvestigacion){
        if($scope.currentTipo === tipoInvestigacion.nidTipoActividadInvestigacion){
            $scope.currentTipo = 0;
            $scope.idTipoInvestigacion = 0;
        } else {
            $scope.currentTipo = tipoInvestigacion.nidTipoActividadInvestigacion;
            $scope.idTipoInvestigacion = tipoInvestigacion.nidTipoActividadInvestigacion;
        }
        
        $scope.sharedService.tipoInvestigacion = tipoInvestigacion;
        $scope.getUsuariosByPagina();
        $scope.GetTotalActivosInactivosByDepartamento($scope.sharedService.usuarioLogin.idDepartamento, $scope.idTipoInvestigacion);
        
    };
    
    $scope.filtrarDepartamento = function(docenteDepartamento){
        if(docenteDepartamento.nidEstructuraOrganizacion === $scope.currentDepartamento){
            $scope.currentDepartamento = 0;
            $scope.idDepartamento = 0;
        } else {
            $scope.currentDepartamento = docenteDepartamento.nidEstructuraOrganizacion;
            $scope.idDepartamento = docenteDepartamento.nidEstructuraOrganizacion;
        }
        $scope.getUsuariosByPagina();
    };
    
    var GetPorcentaje = function(docentes){
        var total = docentes.nactivos + docentes.ninactivos;
        $scope.activo = (docentes.nactivos * 100 / total) + "%";
        $scope.inactivo = (docentes.ninactivos * 100 / total) + "%";
    };
    
    var GetPorcentajeDepartamento = function(total, valor){
        var porcentaje = "50%";
        if(total !== 0 ){
            porcentaje = (valor * 100 / total) + "%";
        } else {
            
        }
        return porcentaje;
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
    
    $scope.rangoPaginas = [50,100];
    $scope.currentPage = 1;
    $scope.currentRango = $scope.rangoPaginas[0];
    $scope.maxSize = 50;
    $scope.total = 0;

    $scope.numPages = function () {
      return Math.ceil($scope.total / $scope.currentRango);
    };

    $scope.$watch('currentPage + currentRango', function() {
        $scope.getUsuariosByPagina();
        $scope.row = ($scope.currentPage - 1) * $scope.currentRango + 1;
        console.log("FILA-INICIO :: ", $scope.idFila);
    });
    
    var paginacionUsuarioSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.users = [];
        $scope.users = response.lista;
        $scope.total = response.total;
    };
    
    var paginacionUsuarioError = function(response){
        $log.debug("Get paginacionUsuario - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getUsuariosByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar, 
                          idFacultad : $scope.sharedService.usuarioLogin.idFacultad, idDepartamento : $scope.sharedService.usuarioLogin.idDepartamento, 
                          idTipoInvestigacion : $scope.idTipoInvestigacion, idSemestre: $scope.idSemestre};
        UsuariosService.GetUsuariosColor(objPagina).then(paginacionUsuarioSuccess, paginacionUsuarioError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getUsuariosByPagina();
    };
    
    $scope.enviarInforme = function(){
        $scope.loader = true;
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.usuarioLogin.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.enviarInformeDepartamento(pagina).then(enviarInformeDepartamentoSuccess, enviarInformeDepartamentoError);
    };
    
    $scope.changeSemestre = function(semestre){
        $scope.semestre = semestre;
        
        $scope.GetTotalActivosInactivosByDepartamento($scope.sharedService.usuarioLogin.idDepartamento, 0, $scope.semestre.nidSemestre);
        $scope.GetTotalActividadesByTipoActividadDepartamento($scope.sharedService.usuarioLogin.idDepartamento, $scope.semestre.nidSemestre);
        $scope.GetTotalActivosInactivosHomeDepartamento($scope.sharedService.usuarioLogin.idDepartamento, 0, $scope.semestre.nidSemestre);
        
    };
       
    
    $scope.getUsuariosByPagina();
    $scope.GetTotalActivosInactivosByDepartamento($scope.sharedService.usuarioLogin.idDepartamento, 0, 0);
    $scope.GetTotalActivosInactivosHomeDepartamento($scope.sharedService.usuarioLogin.idDepartamento, 0, 0);
    $scope.GetTotalActividadesByTipoActividadDepartamento($scope.sharedService.usuarioLogin.idDepartamento, 0);
    $scope.getSemestres();
    
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
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar, 
                          idFacultad : $scope.sharedService.usuarioLogin.idFacultad, idDepartamento : $scope.sharedService.usuarioLogin.idDepartamento, 
                          idTipoInvestigacion : $scope.idTipoInvestigacion};
        UsuariosService.descargarHomeDirectorDepartamentoPDF(objPagina).then(descargarPDFSuccess, descargarPDFError);
    };
    
    $scope.descargarExcel = function(){
        $scope.loader = true;
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar, 
                          idFacultad : $scope.sharedService.usuarioLogin.idFacultad, idDepartamento : $scope.sharedService.usuarioLogin.idDepartamento, 
                          idTipoInvestigacion : $scope.idTipoInvestigacion};
        UsuariosService.descargarHomeDirectorDepartamentoExcel(objPagina).then(descargarExcelSuccess, descargarExcelError);
    };
    
}]);
