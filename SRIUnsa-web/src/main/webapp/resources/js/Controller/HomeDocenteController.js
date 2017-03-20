investigacionApp.controller('HomeDocenteController',['$log', '$scope', '$location',  'TipoInvestigacionService', 'SharedService',
    'ActividadesGeneradasService', 'SRIUnsaConfig', 'SemestreService', 'UsuariosService', 'ngToast', '$localStorage'
,function($log, $scope, $location,  TipoInvestigacionService, SharedService,
    ActividadesGeneradasService, SRIUnsaConfig, SemestreService, UsuariosService, ngToast, $localStorage) {

    $scope.sharedService = SharedService;
    $scope.sortType     = 'id'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("GetSemestre - Success");
        console.log("Respuesta :: ", response);
    	$scope.semestres = response;
        $scope.semestre = verificarSemestre($scope.semestres);
//        setPeriodo();
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
    
    var GetActividadesGeneradasHomeDocenteSuccess = function(response){
        $log.debug("GetActividadesGeneradasHomeDocente - Success");
        console.log("Respuesta :: ", response);
        $scope.actividadesGeneradas = [];
        $scope.actividadesGeneradas = response.lista;
        $scope.total = response.total;
        $scope.loader = false;
//        $scope.loadTable = false;
    };
    var GetActividadesGeneradasHomeDocenteError = function(response){
        $log.debug("GetActividadesGeneradasHomeDocente - Error");
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
        $('#modalNotificacion').modal('show');
    };
    
    var enviarInformeSuccess = function(response){
        $log.debug("enviarInforme - Success");
        console.log("Respuesta :: ", response);
        openNotice('Envio exitoso!','success');
        $('#modalEnvioExitoso').modal('show');
        $scope.loader = false;
    };
    var enviarInformeError = function(response){
        $log.debug("enviarInforme - Error");
        console.log("Respuesta :: ", response);
        
        openNotice('Error al enviar el informe!','danger');
        $scope.loader = false;
    };
    
    var imprimirInformeSuccess = function(response){
        $log.debug("imprimirInforme - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var imprimirInformeError = function(response){
        $log.debug("imprimirInforme - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    var EliminarActividadGeneradaSuccess = function(response){
        $log.debug("EliminarActividadGenerada - Success");
        console.log("Respuesta :: ", response);
        $scope.getActividades();
    };
    var EliminarActividadGeneradaError = function(response){
        $log.debug("EliminarActividadGenerada - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };
    $scope.GetTipoInvestigaciones = function(){
        TipoInvestigacionService.getInvestigaciones().then(GetTipoInvestigacionesSuccess, GetTipoInvestigacionesError);
    };
    $scope.GetUsuarioHome = function(idUsuario, idUsuarioDirector){
        UsuariosService.GetUsuarioHome(idUsuario, idUsuarioDirector).then(GetUsuarioHomeSuccess, GetUsuarioHomeError);
    };
 
    $scope.goHome = function(tipoInvestigacion){
        sessvars.tipoInvestigacion = tipoInvestigacion;
        $scope.sharedService.tipoInvestigacion = sessvars.tipoInvestigacion;
        $location.path("/home");
    };
    
    $scope.enviarInforme = function(){
        $scope.loader = true;
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.docente.nidUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.enviarInforme(pagina).then(enviarInformeSuccess, enviarInformeError);
    };
    
    $scope.imprimirInforme = function(){
        $scope.loader = true;
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.docente.nidUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.imprimirInforme(pagina).then(imprimirInformeSuccess, imprimirInformeError);
    };
    
    $scope.changeSemestre = function(semestre){
        $scope.semestre = semestre;
        $scope.getActividades();
    };
    
//    var setPeriodo = function(){
//        angular.forEach($scope.semestres, function(value, key){
//            if(value.snombreSemestre === "2016-II Semestre II"){
//                $scope.semestre = value;
//            }
//        });
//         $scope.getActividades();
//    };
    
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
    
    $scope.verActividadById = function(actividadGenerada){
        $scope.sharedService.scrollTop();
        $scope.loader = true;
        $location.path("/actividad/Generadas/" + actividadGenerada.idactividadinvestigacion);
    };
    
    $scope.EliminarActividadGenerada = function(){
        $scope.loader = true;
        ActividadesGeneradasService.EliminarActividadGenerada($scope.idActividad).then(EliminarActividadGeneradaSuccess, EliminarActividadGeneradaError);
    };
    
    $scope.eliminar = function(idActividad){
        $scope.idActividad = idActividad;
        console.log("Eliminar actividad :: ", idActividad);
    };
    
    $scope.updateActividadById = function(actividadGenerada){
        $scope.sharedService.scrollTop();
        $scope.loader = true;
        $location.path("/actividad/Generadas/update/" + actividadGenerada.idactividadinvestigacion);
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
                          idUsuario: $scope.sharedService.docente.nidUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.GetActividadesGeneradasHomeDocente(objPagina).then(GetActividadesGeneradasHomeDocenteSuccess, GetActividadesGeneradasHomeDocenteError);
    };
    
    
    /***************************** VERIFICA SI INGRESO COMO DIRECTOR UNIDAD O COMO DOCENTE *******************************/
    
    if($scope.sharedService.idUsuarioRegistrar === -1){
        $scope.sharedService.docente = {};
        $scope.sharedService.docente.nidUsuario = $scope.sharedService.usuarioLogin.idUsuario;
        $scope.sharedService.docente.nidPersona = $scope.sharedService.usuarioLogin.idPersona;
        $scope.sharedService.docente.snombre = $scope.sharedService.usuarioLogin.nombre;
        $scope.sharedService.docente.sapellido = $scope.sharedService.usuarioLogin.apellido;
        $localStorage.docente = $scope.sharedService.docente;
        $scope.GetUsuarioHome($scope.sharedService.docente.nidUsuario, 0);
    } else {
        if($scope.sharedService.idUsuarioRegistrar === "" || $scope.sharedService.idUsuarioRegistrar === undefined){
            $scope.sharedService.idUsuarioRegistrar = $scope.sharedService.usuarioLogin.idUsuario;
            $scope.sharedService.docente = {};
            $scope.sharedService.docente.nidUsuario = $scope.sharedService.usuarioLogin.idUsuario;
            $scope.sharedService.docente.nidPersona = $scope.sharedService.usuarioLogin.idPersona;
            $scope.sharedService.docente.snombre = $scope.sharedService.usuarioLogin.nombre;
            $scope.sharedService.docente.sapellido = $scope.sharedService.usuarioLogin.apellido;
            $localStorage.docente = $scope.sharedService.docente;
        }
        $scope.GetUsuarioHome($scope.sharedService.idUsuarioRegistrar, $scope.sharedService.usuarioLogin.idUsuario);
    }
    
    $scope.getSemestres();
    $scope.GetTipoInvestigaciones();
    
    
    /******************* EXPORTAR ARCHIVOS *****************/
    
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
                          idUsuario: $scope.sharedService.docente.nidUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.descargarHomeDocentePDF(pagina).then(descargarPDFSuccess, descargarPDFError);
    };
    
    $scope.descargarExcel = function(){
        console.log("Empezando descarga de Excel...");
        var pagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.docente.nidUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: "", 
                          filtro : getFiltros()};
        ActividadesGeneradasService.descargarHomeDocenteExcel(pagina).then(descargarExcelSuccess, descargarExcelError);
    };
    
    /**************** NOTIFICACIONES *****************/
    
    $scope.cerrar = function(){
        $('#modalNotificacion').modal('hide');
        setTimeout(function(){
            $scope.$apply(function(){ 
                $location.path($scope.sharedService.crearActividadHome);
            });
        }, 500);
    };
    
    var openNotice = function (text, type) {
        ngToast.create({
            className: type,
            content: '<span class="alert-link">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + text +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'
        });
    };
    
    
}]);
