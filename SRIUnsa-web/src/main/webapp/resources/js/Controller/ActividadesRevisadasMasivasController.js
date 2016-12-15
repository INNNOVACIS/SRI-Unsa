investigacionApp.controller('ActividadesRevisadasMasivasController', function($log, $scope, $location, ActividadesRevisadasService, 
    SemestreService, TipoInvestigacionService, FondoConcursableService, TipoNivelService, EstructuraOrganizacionService, SharedService,
    SRIUnsaConfig, UsuarioFlujoService, ActividadesRevisadasMasivasService ) {

    $scope.sharedService = SharedService;
    $scope.loader = false;
    $scope.showDetalle = false;
    
    /*********** Servicios Callback ***********/  
    
    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("GetTipoNivel - Success");
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
    
    $scope.facultadChange = function(){
        $scope.departamento = {};
        $scope.escuela = {};
    };
    $scope.departamentoChange = function(){
        $scope.escuela = {};
    };
    
    $scope.aprobarActividades = function(){
        $scope.loader = true;
        var actividadesGenerales = [];
        angular.forEach($scope.actividadesRevisadas, function(value, key){
            if(value.seleccionado !== undefined && value.seleccionado === true){
                var actividadGeneral = {
                    idUsuario : $scope.sharedService.idUsuario,
                    idFlujoActorOrigen : SRIUnsaConfig.DIUN,
                    idEstado : SRIUnsaConfig.REVISADO,
                    idPlanificacion : -1,
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
        var id = $scope.sharedService.idUsuario;
        ActividadesRevisadasMasivasService.GetCabeceraMasiva(id).then(GetCabeceraMasivaSuccess, GetCabeceraMasivaError);
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
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total,
                          idUsuario: $scope.sharedService.idUsuario, idEstado: SRIUnsaConfig.REVISADO, idFlujoActor: SRIUnsaConfig.DIUN, 
                          filtro : getFiltros()};
        ActividadesRevisadasMasivasService.GetActividadesRevisadasMasivas(objPagina).then(GetActividadesRevisadasSuccess, GetActividadesRevisadasError);
    };
    
    $scope.filtrar = function() {
        $scope.GetActividadesRevisadasMasivas();
    };
        
    $scope.getListaTipoNivel();
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.CrearOrActualizarUsuarioFlujo();
    
    $scope.getActividades();
    
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
                          idUsuario: $scope.sharedService.idUsuario, idEstado: SRIUnsaConfig.REVISADO, idFlujoActor: SRIUnsaConfig.DIUN, 
                          filtro : filtro};
        ActividadesRevisadasMasivasService.GetDetalleMasiva(paginaDetalle).then(GetDetalleMasivaSuccess, GetDetalleMasivaError);
    };

});