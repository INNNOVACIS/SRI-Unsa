    investigacionApp.controller('HomeController', function($log, $scope, $location, SharedService, SRIUnsaConfig, DetalleInvestigacionService,
    HomeService, TipoInvestigacionService, SemestreService, TipoAsesoriaService, TipoProduccionService, ProcesoFlujoService,
    EstructuraAreaInvestigacionService, FondoConcursableService, TipoNivelService, EstructuraOrganizacionService, FlujoAristaService,
    UsuarioFlujoService, FileUploader) {
    
    $scope.sharedService = SharedService;
    $scope.tipoInvestigaciones = [];
    $scope.estructuraOrganizaciones = [];
    $scope.semestres = [];
    $scope.loader = false;
    $scope.modal = { open: false, close: true };
    $scope.mensajeSuccess = false;
    $scope.mensajeError = false;
    $scope.descripcion = "";
    $scope.nombreInvestigacion = "";
    $scope.duracionInvestigacion = 0;

    /***************** CallBack *******************/
    
    var GetTipoInvestigacionesSuccess = function(response){
        $log.debug("GetTipoInvestigaciones - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigaciones = response;
    };
    var GetTipoInvestigacionesError = function(response){
        $log.debug("GetTipoInvestigaciones - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetEstructuraOrganizacionesSuccess = function(response){
        $log.debug("GetEstructuraOrganizaciones - Success");
        
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
        console.log("Respuesta :: ", $scope.estructuraOrganizaciones);
    };
    var GetEstructuraOrganizacionesError = function(response){
        $log.debug("GetEstructuraOrganizaciones - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetTipoNivelesSuccess = function(response){
        $log.debug("GetTipoNiveles - Success");
        console.log("Respuesta :: ", response);
        $scope.niveles = response;
        $scope.GetEstructuraOrganizaciones();
    };
    var GetTipoNivelesError = function(response){
        $log.debug("GetTipoNiveles - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetSemestresSuccess = function(response){
        $log.debug("GetSemestres - Success");
        console.log("Respuesta :: ", response);
        $scope.semestres = response;
    };
    var GetSemestresError = function(response){
        $log.debug("GetSemestres - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetAreaInvestigacionesSuccess = function(response){
        $log.debug("GetAreaInvestigaciones - Success");
        console.log("Respuesta :: ", response);
        $scope.areaInvestigaciones = response;
    };
    var GetAreaInvestigacionesError = function(response){
        $log.debug("GetAreaInvestigaciones - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetFondosSuccess = function(response){
        $log.debug("GetFondos - Success");
        console.log("Respuesta :: ", response);
        $scope.fondos = response;
    };
    var GetFondosError = function(response){
        $log.debug("GetFondos - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetTipoAsesoriasSuccess = function(response){
        $log.debug("GetFondos - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoAsesorias = response;
    };
    var GetTipoAsesoriasError = function(response){
        $log.debug("GetFondos - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetTipoProduccionesSuccess = function(response){
        $log.debug("GetTipoProducciones - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoProducciones = response;
    };
    var GetTipoProduccionesError = function(response){
        $log.debug("GetTipoProducciones - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetUsuarioFlujoSuccess = function(response){
        $log.debug("GetUsuarioFlujo - Success");
        console.log("Respuesta :: ", response);
        $scope.idUsuarioFlujo = response;
    };
    var GetUsuarioFlujoError = function(response){
        $log.debug("GetUsuarioFlujo - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetFlujoAristaSuccess = function(response){
        $log.debug("GetFlujoArista - Success");
        console.log("Respuesta :: ", response);
        $scope.idFlujoArista = response.nidArista;
    };
    var GetFlujoAristaError = function(response){
        $log.debug("GetFlujoArista - Error");
        console.log("Respuesta :: ", response);
    };
    
    var RegistrarProcesoFlujoSuccess = function(response){
        $log.debug("RegistrarProcesoFlujo - Success");
        console.log("Respuesta :: ", response);
        $scope.idProcesoFlujo = response;
        $scope.registrarInvestigacion();
    };
    var RegistrarProcesoFlujoError = function(response){
        $log.debug("RegistrarProcesoFlujo - Error");
        console.log("Respuesta :: ", response);
    };
    
    var RegistrarInvestigacionSuccess = function(response){
        addPlanificacion(response);
        uploader.uploadAll();
        $scope.idActividadInvestigacion = response;
        $scope.RegistrarDetalleInvestigacion();
    };
    var RegistrarInvestigacionError = function(response){
        $log.debug(response);
        $scope.mensajeError = true;
        $scope.message = response;
        $scope.loader = false;
    };
    
    var RegistrarDetalleInvestigacionSuccess = function(response){
        $log.debug("RegistrarDetalleInvestigacion - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
        $scope.openCloseModal(true,false);
    };
    var RegistrarDetalleInvestigacionError = function(response){
        $log.debug("RegistrarDetalleInvestigacion - Error");
        console.log("Respuesta :: ", response);  
    };
    
    /***************** Servicios ******************/
    
    $scope.GetTipoInvestigaciones = function(){
        TipoInvestigacionService.getInvestigaciones().then(GetTipoInvestigacionesSuccess, GetTipoInvestigacionesError);
    };
    $scope.GetEstructuraOrganizaciones = function(){
        EstructuraOrganizacionService.getEstructuraOrganizaciones().then(GetEstructuraOrganizacionesSuccess, GetEstructuraOrganizacionesError);
    };
    $scope.GetTipoNiveles = function(){
        TipoNivelService.getListaTipoNivel().then(GetTipoNivelesSuccess, GetTipoNivelesError);
    };
    $scope.GetSemestres = function(){
        SemestreService.getSemestres().then(GetSemestresSuccess, GetSemestresError);
    };
    $scope.GetAreaInvestigaciones = function(){
        EstructuraAreaInvestigacionService.getAreaInvestigaciones().then(GetAreaInvestigacionesSuccess, GetAreaInvestigacionesError);
    };
    $scope.GetFondos = function(){
        FondoConcursableService.getFondos().then(GetFondosSuccess, GetFondosError);
    };
    $scope.GetTipoAsesorias = function(){
        TipoAsesoriaService.getAsesorias().then(GetTipoAsesoriasSuccess, GetTipoAsesoriasError);
    };
    $scope.GetTipoProducciones = function(){
        TipoProduccionService.getListaTipoProduccion().then(GetTipoProduccionesSuccess, GetTipoProduccionesError);
    };
    $scope.GetUsuarioFlujo = function(){
        var usuarioFlujo = {
            nidFlujoActor : SRIUnsaConfig.DOCE,
            nidUsuario : $scope.sharedService.idUsuario
        };
        UsuarioFlujoService.CreateAndGetUsuarioFlujo(usuarioFlujo).then(GetUsuarioFlujoSuccess, GetUsuarioFlujoError);
    };
    $scope.GetFlujoArista = function(){
        FlujoAristaService.GetFlujoAristaByIdOrigenIdEstado(SRIUnsaConfig.DOCE, SRIUnsaConfig.CREADO).then(GetFlujoAristaSuccess, GetFlujoAristaError);
    };
    
    /************ Registrar Actividad de Investigacion ****************/
    
    $scope.registrarInvestigacion = function(){
        $scope.actividadInvestigacion = {
            nidTipoActividadInvestigacion : $scope.tipoInvestigacion.nidTipoActividadInvestigacion,
            nhoras : $scope.duracionInvestigacion,
            sritipoProduccion : $scope.tipoProduccion === undefined ? "" : $scope.tipoProduccion.snombreTipoProduccion,
            sfondoConcursable : $scope.fondo === undefined ? "" : $scope.fondo.snombreFondoConcursable,
            stipoAsesoria : $scope.tipoAsesoria === undefined ? "" : $scope.tipoAsesoria.snombreTipoAsesoria,
            ssemestre : $scope.semestre.snombreSemestre,
            sfacultad : $scope.facultad.snombreEstructuraOrganizacion,
            sescuela : $scope.escuela.snombreEstructuraOrganizacion,
            sdepartamento : $scope.departamento.snombreEstructuraOrganizacion,
            sareaInvestigacion: $scope.areaInvestigacion.sNombre,
            ssubAreaInvestigacion : $scope.subareaInvestigacion.sNombre,
            sdisciplina : $scope.disciplinaInvestigacion.sNombre,
            stipoLabor : $scope.tipoLabor === undefined ? "" : $scope.tipoLabor.nombre,
            snombreActividadInvestigacion : $scope.nombreInvestigacion,
            sdescripcionActividad : $scope.descripcion
        };
        HomeService.registrarInvestigacion($scope.actividadInvestigacion).then(RegistrarInvestigacionSuccess, RegistrarInvestigacionError);
    };
    
    $scope.registrarProcesoFlujo = function(isValid){
        if(isValid){
            $scope.loader = true;
            scrollTop();
            var procesoflujo = {
                nidUsuarioFlujo : $scope.idUsuarioFlujo,
                nidArista : $scope.idFlujoArista
            };
            ProcesoFlujoService.RegistrarProcesoFlujo(procesoflujo).then(RegistrarProcesoFlujoSuccess, RegistrarProcesoFlujoError);
        }
    };
    
    $scope.RegistrarDetalleInvestigacion = function(){
        var detalleInvestigacion = {
            nidActividadInvestigacion : $scope.idActividadInvestigacion,
            nidProcesoFlujo : $scope.idProcesoFlujo
        };
        DetalleInvestigacionService.RegistrarDetalleInvestigacion(detalleInvestigacion).then(RegistrarDetalleInvestigacionSuccess, RegistrarDetalleInvestigacionError);
    };
    
    
    $scope.GetTipoInvestigaciones();
    $scope.GetTipoNiveles();
    $scope.GetSemestres();
    $scope.GetAreaInvestigaciones();
    $scope.GetFondos();
    $scope.GetTipoAsesorias();
    $scope.GetTipoProducciones();
    $scope.GetUsuarioFlujo();
    $scope.GetFlujoArista();
    
    /*********** Funciones Utilitarias ************/
    
    $scope.changeTipoActividad = function(tipoActividad){
        $scope.mostrarActividad = [false, false, false, false]; //case1 , case2, case3, case4
        switch(tipoActividad.toUpperCase()) {
            case "INVESTIGACION FORMATIVA":
                $scope.mostrarActividad = [true, false, false, false];
                break;
            case "ASESORIA DE TESIS":
                $scope.mostrarActividad = [false, true, false, false];
                break;
            case "INVESTIGACIONES BÁSICAS Y APLICADAS":
                $scope.mostrarActividad = [false, false, true, false];
                break;
            case "PRODUCCIÓN INTELECTUAL":
                $scope.mostrarActividad = [false, false, false, true];
                break;
            default:
                $scope.mostrarActividad = [false, false, false, false];
        };
    };
    
    $scope.registrarNuevaActividad = function(){
        limpiarCampos();
    };
    $scope.irActividadesGeneradas = function(){
        $scope.loader = true;
        $location.path("/actividadesGeneradas");
    };
    
    $scope.openCloseModal = function(open, close) {
        $scope.modal = { open: open, close: close };
    };
    
    var scrollTop = function(){
        $('html,body').animate({
            scrollTop: $("#container").offset().top - 100
        }, 800);
    };
    
    /********** FILE UPLOAD **********/
    
    $scope.files = [];

    var homeServiceSuccess = function(response) {        
        $log.debug(response);
    };

    var homeServiceError = function(response) {
        $log.debug(response);
    };
    
    $scope.uploadFile = function(){
        var file = $scope.archivo;
        var formData = new FormData();
        formData.append('file', file);
        HomeService.sendFile(formData, true).then(homeServiceSuccess, homeServiceError);
    };
    
    var uploader = $scope.uploader = new FileUploader({
        url: SRIUnsaConfig.SRIUnsaUrlServicio + '/files/subirArchivos'
    });
    
    uploader.filters.push({
        name: 'customFilter',
        fn: function(item , options) {
            return this.queue.length < 10;
        }
    });

    $scope.uploadAll = function(){
        addPlanificacion();
        uploader.uploadAll();
    };
    var addPlanificacion = function(idPlanificacion){
        angular.forEach(uploader.queue, function(value, key) {
            console.log(value.file.name);
            value.formData.push({
                idPlanificacion: idPlanificacion
            });
        });
    };
    
    // CALLBACKS

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
        //console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
        //console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        //console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
        //console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        //console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
        //console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        //console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        //console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        //console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        //console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
        //console.info('onCompleteAll');
    };

});