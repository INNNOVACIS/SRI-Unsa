investigacionApp.controller('ActividadesRevisadasMasivasController',['$log', '$scope', '$location', 'ActividadesGeneradasService', 
    'SemestreService', 'TipoInvestigacionService', 'FondoConcursableService', 'EstructuraOrganizacionService', 'SharedService',
    'SRIUnsaConfig', 'UsuarioFlujoService', 'ActividadesRevisadasMasivasService', 'ActividadesPendientesService' ,function($log, $scope, $location, ActividadesGeneradasService, 
    SemestreService, TipoInvestigacionService, FondoConcursableService, EstructuraOrganizacionService, SharedService,
    SRIUnsaConfig, UsuarioFlujoService, ActividadesRevisadasMasivasService, ActividadesPendientesService ) {

    $scope.sharedService = SharedService;
    $scope.loader = false;
    $scope.showDetalle = false;
    $scope.departamentos = [];
    
    /*********** Servicios Callback ***********/  

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
     	$log.debug("GetFondo - Error"); 
        console.log("Respuesta :: ", response);
    };
    
    var getTipoInvestigacionSuccess = function(response){
    	$log.debug("GetTipoInvestigacion - Success");
        console.log("Respuesta :: ", response);
    	$scope.tipoInvestigaciones = response;
    };
    var getTipoInvestigacionError = function(response){
     	$log.debug("GetTipoInvestigacion - Error");
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
    
    var CreateAndGetUsuarioFlujoSuccess = function(response){
        $log.debug("CreateAndGetUsuarioFlujo - Success");
        console.log("Respuesta :: ", response);
    };
    var CreateAndGetUsuarioFlujoError = function(response){
        $log.debug("CreateAndGetUsuarioFlujo - Error");
        console.log("Respuesta :: ", response);
    };
    
    var aprobarActividadesSuccess = function(response){
        $log.debug("AprobarActividades - Success");
        console.log("Respuesta :: ", response);
        $scope.GetActividadesRevisadasMasivas();
        angular.forEach(response.body,function(value, key){
            $scope.EnviarEmail(value.actividadInvestigacion.nidActividadInvestigacion);
        });
        $scope.sharedService.scrollTop();
        setTimeout(function(){
            $scope.$apply(function(){ 
                var popUp = SharedService.popUp;
                var titulo = "Aprobación Exitosa!";
                var mensaje = "Las Actividades de Investigación se aprobaron correctamente.";
                var url = "/actividadesRevisadasMasivas";
                
                var op1 = {open:true, txt:'Ir a Bandeja', fun:function(){
                    popUp.irPopUp();
                }};
                popUp.showPopUp(titulo, mensaje, url, op1);
                $scope.loader = false;
            });
        }, 1000);
    };
    var aprobarActividadesError = function(response){
        $log.debug("AprobarActividades - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetCabeceraMasivaSuccess = function(response){
        $log.debug("GetCabeceraMasiva - success");
        console.log("Respuesta :: ", response);
        $scope.cabeceras = response.body;
    };
    var GetCabeceraMasivaError = function(response){
        $log.debug("GetCabeceraMasiva - Error");
        console.log("Respuesta :: ", response);
    };
    
    var EnviarEmailSuccess = function(response){
        $log.debug("EnviarEmail - Success");
        console.log("Respuesta :: ", response);
    };
    var EnviarEmailError = function(response){
        $log.debug("EnviarEmail - Error");
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
            nidUsuario : $scope.sharedService.idUsuario
        };
        UsuarioFlujoService.CreateAndGetUsuarioFlujo(usuarioFlujo).then(CreateAndGetUsuarioFlujoSuccess, CreateAndGetUsuarioFlujoError);
    };
    
    $scope.aprobarActividades = function(){
        $scope.loader = true;
        var actividadesGenerales = [];
        angular.forEach($scope.actividadesRevisadas, function(value, key){
            if(value.seleccionado !== undefined && value.seleccionado === true){
                var actividadGeneral = {
                    idUsuario : $scope.sharedService.usuarioLogin.idUsuario,
                    idFlujoActorOrigen : SRIUnsaConfig.DIUN,
                    idEstado : SRIUnsaConfig.REVISADO,
                    idPlanificacion : -1,
                    codigoActor : SRIUnsaConfig.codeDIUN,
                    actividadInvestigacion : {
                        nidActividadInvestigacion : value.idactividadinvestigacion                        
                    }
                };
                actividadesGenerales.push(actividadGeneral);
            }
        });
        ActividadesRevisadasMasivasService.AprobarActividades(actividadesGenerales).then(aprobarActividadesSuccess, aprobarActividadesError);
    };
    
    $scope.seleccionarTodo = function(seleccionar){
        angular.forEach($scope.actividadesRevisadas, function(value, key){
            if(seleccionar === true){
                value.seleccionado = true;
            } else {
                value.seleccionado = false;
            }
        });
    };
    
    $scope.GetCabeceraMasiva = function(){
        var id = $scope.sharedService.usuarioLogin.idUsuario;
        ActividadesRevisadasMasivasService.GetCabeceraMasiva(id).then(GetCabeceraMasivaSuccess, GetCabeceraMasivaError);
    };
    
    $scope.EnviarEmail = function(idActividadGenerada){   
        var actividadGeneral = {
            idUsuario : $scope.sharedService.usuarioLogin.idUsuario,
            idFlujoActorOrigen : SRIUnsaConfig.DOCE,
            idEstado : SRIUnsaConfig.CREADO,
            idPlanificacion : -1,
            colaboradores : [],
            plantillaDocumentoActividad : [],
            actividadInvestigacion : {
                nidActividadInvestigacion : idActividadGenerada
            }
        };
        ActividadesGeneradasService.EnviarEmail(actividadGeneral).then(EnviarEmailSuccess, EnviarEmailError);
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
        $scope.GetActividadesRevisadasMasivas();
    });
    
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
    
    var GetActividadesRevisadasSuccess = function(response){
        $log.debug("GetActividadesRevisadas - Success");
        console.log("Respuesta :: ", response);
        $scope.actividadesRevisadas = [];
        $scope.actividadesRevisadas = response.lista;
        $scope.total = response.total;
    };
    var GetActividadesRevisadasError = function(response){
        $log.debug("GetActividadesRevisadas - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.GetActividadesRevisadasMasivas = function(){
//        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
//                          idUsuario: $scope.sharedService.usuarioLogin.idUsuario, idEstado: SRIUnsaConfig.REVISADO, idFlujoActor: SRIUnsaConfig.DIUN, 
//                          filtro : getFiltros()};
//        ActividadesRevisadasMasivasService.GetActividadesRevisadasMasivas(objPagina).then(GetActividadesRevisadasSuccess, GetActividadesRevisadasError);
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.usuarioLogin.idUsuario, idEstado: SRIUnsaConfig.CREADO, idFlujoActor: SRIUnsaConfig.DIDE, 
                          filtro : getFiltros()};
        ActividadesPendientesService.paginacionActividades(objPagina).then(GetActividadesRevisadasSuccess, GetActividadesRevisadasError);
    };
    
    $scope.filtrar = function() {
        $scope.GetActividadesRevisadasMasivas();
    };
        
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.CrearOrActualizarUsuarioFlujo();
    $scope.getEstructuraOrganizaciones();
    $scope.GetActividadesRevisadasMasivas();
    
    $scope.verActividadById = function(actividadRevisada){
        $scope.loader = true;
        $location.path("/actividad/Revisadas/"+ actividadRevisada.idactividadinvestigacion);
    };
    
    
    /********** Paginacion DetalleMasiva **********/
   
    
    $scope.showDetalle = false;
    
    var GetDetalleMasivaSuccess = function(response){
        $log.debug("GetDetalleMasiva - Success");
        console.log("Respuesta :: ", response);
        $scope.detalleMasivas = response.lista;
        $scope.showDetalle = true;
//        $scope.total = response.total;
        
    };
    var GetDetalleMasivaError = function(response){
        $log.debug("GetDetalleMasiva - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.GetDetalleMasiva = function(id){
        $scope.detalleMasivas = [];
        var filtro = {
            nidTipoActividadInvestigacion : id
        };
        var paginaDetalle = { currentPage : 1, rango : 100, total : 100,
                          idUsuario: $scope.sharedService.usuarioLogin.idUsuario, idEstado: SRIUnsaConfig.REVISADO, idFlujoActor: SRIUnsaConfig.DIUN, 
                          filtro : filtro};
        ActividadesRevisadasMasivasService.GetDetalleMasiva(paginaDetalle).then(GetDetalleMasivaSuccess, GetDetalleMasivaError);
    };

}]);