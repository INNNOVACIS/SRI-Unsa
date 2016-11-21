    investigacionApp.controller('homeController', function($log, $scope, $location, SharedService,
    HomeService, TipoInvestigacionService, SemestreService, TipoAsesoriaService, TipoProduccionService, 
    EstructuraAreaInvestigacionService, FondoConcursableService, TipoNivelService, EstructuraOrganizacionService, FileUploader) {
    
    /*
     * Parametros
     */
    $scope.sharedService = SharedService;
    $scope.loader = false;
    $scope.modal = { open: false, close: true };
    $scope.mensajeSuccess = false;
    $scope.mensajeError = false;
    
    $scope.facultad = {id : 0, nombre : ""};
    $scope.departamento = {id : 0, nombre : ""};
    $scope.escuela = {id : 0, nombre : ""};
    $scope.tipoInvestigador = {id : 0, nombre : ""};
    $scope.tipoLabor = {id : 0, nombre : ""};
    $scope.colaborador = {id : 0, nombre : ""};
    $scope.descripcion = "";
    $scope.nombreInvestigacion = "";
    $scope.duracionInvestigacion = 0;
        
    /*********** Servicios Get All ***********/   
    
    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("Get tipoNivel - Success");
    	console.log("Success :: ", response);
    	$scope.niveles = response;
        $scope.getEstructuraOrganizaciones();
    };

    var getTipoNivelServiceError = function(response){
     	$log.debug("Get TipoNivel - Error"); 
    };

    var getEstructuraOrganizacionServiceSuccess = function(response){
    	$log.debug("Get EstructuraOrganizacion - Success");
        
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
        console.log("Estructura Organizacion :: ", $scope.estructuraOrganizaciones);
    };

    var getEstructuraOrganizacionServiceError = function(response){
     	$log.debug("Get EstructuraOrganizacion - Error"); 
    };
    
    
    var getFondoServiceSuccess = function(response){
    	$log.debug("Get Fondo - Success");
    	$scope.fondos = response;
    };

    var getFondoServiceError = function(response){
     	$log.debug("Get Fondo - Error"); 
    };
    
    var getAreaInvestigacionServiceSuccess = function(response){
    	$log.debug("Get AreaInvestigacion - Success");
    	$scope.areaInvestigaciones = response;
    };

    var getAreaInvestigacionServiceError = function(response){
     	$log.debug("Get AreaInvestigacion - Error"); 
    };
    
    var getInvestigacionServiceSuccess = function(response){
    	$log.debug("Get Investigacion - Success");
        console.log("Response Investigacion :: ", response);
    	$scope.tipoInvestigaciones = response;
    };
    
    var getInvestigacionServiceError = function(response){
     	$log.debug("Get Investigacion - Error");
        console.log("Error Response Investigacion :: ", response);
    };
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("Get Semestre - Success");        
    	$scope.semestres = response;
    };
    
    var getSemestreServiceError = function(response){
     	$log.debug("Get Semestre - Error");
        console.log("Error Response Semestre :: ", response);
    };
    
    var getAsesoriaServiceSuccess = function(response){
    	$log.debug("Get Asesoria - Success");
    	$scope.tipoAsesorias = response;
    };
    
    var getAsesoriaServiceError = function(response){
     	$log.debug("Get Asesoria - Error"); 
    };
    
    var getTipoProduccionServiceSuccess = function(response){
    	$log.debug("Get TipoProduccion - Success");
    	$scope.tipoProducciones = response;
    };

    var getTipoProduccionServiceError = function(response){
     	$log.debug("Get TipoProduccion - Error");
        console.log("Error TipoProduccion :: ", response);
    };
    
    
    
    $scope.getListaTipoNivel = function(){
      	TipoNivelService.getListaTipoNivel().then(getTipoNivelServiceSuccess, getTipoNivelServiceError);
    };

    $scope.getEstructuraOrganizaciones = function(){
      	EstructuraOrganizacionService.getEstructuraOrganizaciones().then(getEstructuraOrganizacionServiceSuccess, getEstructuraOrganizacionServiceError);
    };
    
    $scope.getFondos = function(){
      	FondoConcursableService.getFondos().then(getFondoServiceSuccess, getFondoServiceError);
    };
    $scope.getAreaInvestigaciones = function(){
      	EstructuraAreaInvestigacionService.getAreaInvestigaciones().then(getAreaInvestigacionServiceSuccess, getAreaInvestigacionServiceError);
    };
    $scope.getTipoProducciones = function(){
      	TipoProduccionService.getListaTipoProduccion().then(getTipoProduccionServiceSuccess, getTipoProduccionServiceError);
    };
    $scope.getAsesorias = function(){
      	TipoAsesoriaService.getAsesorias().then(getAsesoriaServiceSuccess, getAsesoriaServiceError);
    };
    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };
    $scope.getInvestigaciones = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getInvestigacionServiceSuccess, getInvestigacionServiceError);
    };
    
    $scope.getListaTipoNivel();
    $scope.getFondos();
    $scope.getAreaInvestigaciones();
    $scope.getTipoProducciones();
    $scope.getAsesorias();
    $scope.getSemestres();
    $scope.getInvestigaciones();
       
    $scope.facultadChange = function(){
        $scope.departamento = {};
        $scope.escuela = {};
    };
    $scope.departamentoChange = function(){
        $scope.escuela = {};
    };
    
    /*********** Obj JSON ***********/
    
    $scope.actividadInvestigacion = {};
    
    var registrarInvestigacionSuccess = function(response){
        addPlanificacion(response);
        uploader.uploadAll();
        $scope.loader = false;
        $log.debug(response);
//        $scope.mensajeSuccess = true;
//        $scope.message = "id de Registro " + response;
        
        $scope.openCloseModal(true,false);
        
    };
    
    var registrarInvestigacionError = function(response){
        $log.debug(response);
        $scope.mensajeError = true;
        $scope.message = response;
        $scope.loader = false;
    };
    
    $scope.registrarInvestigacion = function(){
        $scope.loader = true;
        scrollTop();
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
        console.log("JSON :: ", $scope.actividadInvestigacion); 
        
        HomeService.registrarInvestigacion($scope.actividadInvestigacion).then(registrarInvestigacionSuccess, registrarInvestigacionError);
    };

    $scope.revisarActividad = function(){
        
    };
    $scope.registrarNuevaActividad = function(){
        limpiarCampos();
    };
    $scope.irActividadesGeneradas = function(){
        $scope.loader = true;
        $location.path("/actividadesGeneradas");
    };

    // START example DataPicker
    $scope.dt = new Date(2020, 5, 22);

    $scope.open = function() {
        $scope.status.opened = true;
    };

    $scope.format = 'dd-MMMM-yyyy';

    $scope.status = {
        opened: false
    };
    // END example DataPicker

    $scope.Actividad = '';
    $scope.actividad1_show = false;
    $scope.actividad2_show = false;
    $scope.actividad3_show = false;
    $scope.actividad4_show = false;


    $scope.tipoInvestigadores = [
        {id : 1, nombre : 'Principal'},
        {id : 2, nombre : 'Co-Investigador'}
    ];
    
    $scope.tipoLabores = [
        {id : 1, nombre : 'Lectiva'},
        {id : 2, nombre : 'No Lectiva'}
    ];

    $scope.actividadChange = function(seleccionado){
    	$scope.Actividad = seleccionado;
    	switch ($scope.Actividad) {
            case 'Investigación Formativa':
                $scope.actividad1_show = true;
                    $scope.actividad2_show = false;
                    $scope.actividad3_show = false;
                    $scope.actividad4_show = false;
                break;
            case 'Asesoria de Tesis':
                $scope.actividad1_show = false;
                    $scope.actividad2_show = true;
                    $scope.actividad3_show = false;
                    $scope.actividad4_show = false;
                break;
            case 'Investigaciones Básicas y Aplicadas':
                $scope.actividad1_show = false;
                    $scope.actividad2_show = false;
                    $scope.actividad3_show = true;
                    $scope.actividad4_show = false;
                break;
            case 'Producción Intelectual':
                $scope.actividad1_show = false;
                    $scope.actividad2_show = false;
                    $scope.actividad3_show = false;
                    $scope.actividad4_show = true;
                break;
        }
    	console.log("ng-change actividad ::", $scope.Actividad);
        console.log("ng-change tipo :: ", $scope.tipo);
        console.log("ng-change tipo investigacion:: ", $scope.tipoInvestigacion);
    };
    
    
    
    /********** funciones utilitarias **********/
    
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
        $scope.duracionInvestigacion = '';
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
        $scope.nombreInvestigacion = "";
        $scope.descripcion = "";
        $scope.actividadInvestigacion = {};
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
        url: 'http://192.168.1.30:8080/SRIUnsa-web/rest/files/subirArchivos'
    });
    
    // FILTERS

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

    //console.info('uploader', uploader);
    
    
    $scope.click = function(test){
        console.log("click :: ", test);
        console.log("click :: ", $scope.tipo);
        console.log("click :: ", $scope.tipoInvestigacion);
    };
});