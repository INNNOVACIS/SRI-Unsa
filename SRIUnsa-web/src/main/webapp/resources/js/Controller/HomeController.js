    investigacionApp.controller('HomeController', function($log, $scope, $location, SharedService, SRIUnsaConfig,
    HomeService, TipoInvestigacionService, SemestreService, TipoAsesoriaService, TipoProduccionService,
    EstructuraAreaInvestigacionService, FondoConcursableService, TipoNivelService, EstructuraOrganizacionService,
    UsuariosService, ActividadesGeneradasService, FileUploader) {
    
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
    $scope.colaboradores = [];

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
    
    var RegistrarInvestigacionSuccess = function(response){
        $log.debug("RegistrarInvestigacion - Success");
        console.log("Respuesta :: ", response);
        addPlanificacion(response.body.idPlanificacion);
        uploader.uploadAll();
        $scope.EnviarEmail(response.body.actividadInvestigacion.nidActividadInvestigacion);
    };
    var RegistrarInvestigacionError = function(response){
        $log.debug(response);
        $scope.mensajeError = true;
        $scope.message = response;
        $scope.loader = false;
    };
    
    var GetUsuariosSuccess = function(response){
        $log.debug("GetUsuarios - Success");
        console.log("Respuesta :: ", response);
        $scope.usuarios = response;
    };
    var GetUsuariosError = function(response){
        $log.debug("GetUsuarios - Error");
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
    $scope.GetUsuarios = function(){
      	UsuariosService.getUsuarios().then(GetUsuariosSuccess, GetUsuariosError);
    };
    
    $scope.AgregarColaborador = function(){
        if(!isRepetido($scope.colaboradores, $scope.usuario)){
            $scope.colaboradores.push($scope.usuario);
        }
    };
    $scope.DeleteColaborador = function(colaborador){
        var index = -1;
        angular.forEach($scope.colaboradores, function(valor, key){
            if(valor.nidUsuario === colaborador.nidUsuario){
                index = key;
            }
        });
        $scope.colaboradores.splice(index, 1);
    };
    var isRepetido = function(lista, objeto){
        var repetido = false;
        angular.forEach(lista, function(valor, key){
            if(valor.nidUsuario === objeto.nidUsuario){
                repetido = true;
            }
        });
        return repetido;
    };
    
    /************ Registrar Actividad de Investigacion ****************/
    
    $scope.registrarInvestigacion = function(isValid){
        if(isValid){
            $scope.loader = true;
            scrollTop();
            var actividadGeneral = {
                idUsuario : $scope.sharedService.idUsuario,
                idFlujoActorOrigen : SRIUnsaConfig.DOCE,
                idEstado : SRIUnsaConfig.CREADO,
                idPlanificacion : -1,
                actividadInvestigacion : {
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
                    sdescripcionActividad : $scope.descripcion,
                    suserCreacion : $scope.sharedService.nombreUsuario,
                    sestado : 'A'
                }
            };
            HomeService.registrarInvestigacion(actividadGeneral).then(RegistrarInvestigacionSuccess, RegistrarInvestigacionError);
        } else {
//            scrollTop();
//            $scope.openCloseModal(true,false);
            console.log("campos por validar");
        }
    };
    
    $scope.EnviarEmail = function(idActividadGenerada){   
        var actividadGeneral = {
            idUsuario : $scope.sharedService.idUsuario,
            idFlujoActorOrigen : SRIUnsaConfig.DOCE,
            idEstado : SRIUnsaConfig.CREADO,
            idPlanificacion : -1,
            actividadInvestigacion : {
                nidActividadInvestigacion : idActividadGenerada
            }
        };
        ActividadesGeneradasService.EnviarEmail(actividadGeneral).then(EnviarEmailSuccess, EnviarEmailError);
    };
    
    
    $scope.GetTipoInvestigaciones();
    $scope.GetTipoNiveles();
    $scope.GetSemestres();
    $scope.GetAreaInvestigaciones();
    $scope.GetFondos();
    $scope.GetTipoAsesorias();
    $scope.GetTipoProducciones();
    $scope.GetUsuarios();
    
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
        $scope.openCloseModal(false, true);
    };
    $scope.irActividadesGeneradas = function(){
        $scope.loader = true;
        $location.path("/actividad/Generadas");
    };
    
    $scope.openCloseModal = function(open, close) {
        $scope.modal = { open: open, close: close };
    };
    
    var scrollTop = function(){
        $('html,body').animate({
            scrollTop: $("#container").offset().top - 100
        }, 800);
    };
    
    var limpiarCampos = function(){
        $scope.tipoInvestigacion = {};
        $scope.duracionInvestigacion = 0;
        $scope.tipoProduccion = {};
        $scope.fondo = {};
        $scope.tipoAsesoria = {};
        $scope.semestre = {};
        $scope.facultad = {};
        $scope.escuela = {};
        $scope.departamento = {};
        $scope.areaInvestigacion = {};
        $scope.subareaInvestigacion = {};
        $scope.disciplinaInvestigacion = {};
        $scope.tipoLabor = {};
        $scope.nombreInvestigacion = " ";
        $scope.descripcion = "";
        uploader.clearQueue();
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
//
//    $scope.uploadAll = function(){
//        addPlanificacion();
//        uploader.uploadAll();
//    };
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
//        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
//        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
//        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
//        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
//        console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
//        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
//        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
//        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
//        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
        console.info('onCompleteAll');
        $scope.loader = false;
        $scope.openCloseModal(true,false);
    };

});