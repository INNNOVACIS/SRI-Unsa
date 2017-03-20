investigacionApp.controller('UpdateActividadGeneradaController',['$log', '$scope', '$location', '$routeParams', 'FondoConcursableService', 
    'SemestreService', 'TipoInvestigacionService', 'EstructuraAreaInvestigacionService', 'HomeService', 'PersonasService', 'PlantillaDocumentoService',
    'TipoNivelService', 'EstructuraOrganizacionService', 'ArchivosService', 'SharedService', 'FileUploader', 'SRIUnsaConfig', '$sce', 
function($log, $scope, $location, $routeParams, FondoConcursableService, 
    SemestreService, TipoInvestigacionService, EstructuraAreaInvestigacionService, HomeService, PersonasService, PlantillaDocumentoService,
    TipoNivelService, EstructuraOrganizacionService, ArchivosService, SharedService, FileUploader, SRIUnsaConfig, $sce) {
    
    $scope.sharedService = SharedService;
    $scope.sharedService.scrollTop();
    $scope.loader = false;
    $scope.idActividad = $routeParams.ID;
    $scope.revisado   = false;
    $scope.generado   = false;
    $scope.estadoPendiente  = false;
    $scope.vicerector = false;
    $scope.idArchivoAdjunto = 0;
    

    $scope.generado = true;

    
    /******************* CALLBACK FUNCTION *******************/
    
    var actualizarActividadInvestigacionSuccess = function(response){
        $scope.loader = false;
        $log.debug("ActualizarActividadInvestigacion - Success");
        console.log("Respuesta :: ", response);
        addPlanificacion($scope.updateInvestigacion.idPlanificacion);
        if(uploader.queue.length === 0){            
            $scope.loader = false;
            $scope.openCloseModal(true,false);
            $scope.getActividadById($scope.idActividad);
        } else {
            uploader.uploadAll();
            uploader2.uploadAll();
        }
    };
    var actualizarActividadInvestigacionError = function(response){
        $scope.loader = false;
        $log.debug("ActualizarActividadInvestigacion - Error");
        console.log("Respuesta :: ", response);
        $scope.getActividadById($scope.idActividad);
    };
    
    var getTipoInvestigacionSuccess = function(response){
        $log.debug("GetTipoInvestigacion - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigaciones = response;
        BuscarTipoInvestigacion($scope.actividadInvestigacion.nidTipoActividadInvestigacion);
    };
    var getTipoInvestigacionError = function(response){
        $log.debug("GetTipoInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getActividadByIdSuccess = function(response){
        $log.debug("getActividadById - Success");
        console.log("Respuesta :: ", response);
        $scope.updateInvestigacion = response.body;
        $scope.actividadInvestigacion = response.body.actividadInvestigacion;
        
//        $scope.actividadInvestigacion.s_fechaRegistro = $scope.sharedService.dateToString($scope.actividadInvestigacion.dfechaRegistro);
//        $scope.actividadInvestigacion.s_fechaFin = $scope.sharedService.dateToString($scope.actividadInvestigacion.dfechaFin);
//        $scope.actividadInvestigacion.s_fechaAceptacion = $scope.actividadInvestigacion.dfechaAceptacion;
         
        if($scope.actividadInvestigacion.sritipoProduccion !== null){
            $scope.changeTipoProduccion($scope.actividadInvestigacion.sritipoProduccion);
            $scope.changeEstadoProduccion($scope.actividadInvestigacion.sestadoProduccion);
        }
        
        setResponsableDirector(response.body.actividadInvestigacion, response.body.colaboradores);
        $scope.getTipoInvestigacion();
        $scope.getArchivosByIdActividad($scope.actividadInvestigacion.nidActividadInvestigacion);
    };
    var getActividadByIdError = function(response){
        $log.debug("GetInvestigacionById - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;  
    };
    
    var getArchivoByIdActividadSuccess = function(response){
        $log.debug("getArchivoByIdActividad - Success");
        console.log("Respuesta :: ", response);
        $scope.archivos = response;
        setTimeout(function(){
            $scope.$apply(function(){
                $scope.loader = false;
            });
        }, 1000);
    };
    var getArchivoByIdActividadError = function(response){
        $log.debug("getArchivoByIdActividad - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;  
    };
    
    var descargarArchivoSuccess = function(response){
        $log.debug("descargarArchivo - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var descargarArchivoError = function(response){
        $log.debug("descargarArchivo - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    var eliminarAdjuntoSuccess = function(response) {
        $log.debug("eliminarArchivo - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
        $scope.getActividadById($scope.idActividad);
    };
    var eliminarAdjuntoError = function(response) {
        $log.debug("eliminarArchivo - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
        $scope.getActividadById($scope.idActividad);
    }
    
    
    /******************* LLAMADA A LOS SERVICIOS *******************/

    $scope.getActividadById = function(idActividad){
        TipoInvestigacionService.getInvestigacionesById(idActividad).then(getActividadByIdSuccess, getActividadByIdError);
    };
    
    $scope.getTipoInvestigacion = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getTipoInvestigacionSuccess, getTipoInvestigacionError);
    };
    
    $scope.getArchivosByIdActividad = function(idActividad){
        $scope.loader = true;
      	ArchivosService.getArchivosByIdActividad(idActividad).then(getArchivoByIdActividadSuccess, getArchivoByIdActividadError);
    };
    
    $scope.descargarArchivo = function(archivo){
        $scope.loader = true;
        ArchivosService.descargarArchivo(archivo.id).then(descargarArchivoSuccess, descargarArchivoError);
    };
    
    $scope.eliminarAdjuntoRow = function(archivo) {
        $scope.idArchivoAdjunto = archivo.id;
    };
    
    $scope.eliminarAdjunto = function(){
       ArchivosService.eliminarArchivo($scope.idArchivoAdjunto).then(eliminarAdjuntoSuccess, eliminarAdjuntoError);
    };
    
    /******************** ACCIONES DEL FORMULARIO APROBAR O RECHAZAR ACTIVIDAD **********************/
    
    $scope.actualizarActividadInvestigacion = function() {
        $scope.loader = true;
        $scope.sharedService.scrollTop();
        
        delete $scope.actividadInvestigacion.s_fechaAceptacion;
        delete $scope.actividadInvestigacion.s_fechaFin
        delete $scope.actividadInvestigacion.s_fechaRegistro;
        
        $scope.updateInvestigacion.actividadInvestigacion = $scope.actividadInvestigacion;
        console.log("ACTUALIZACION :::::::::::: ", $scope.updateInvestigacion);
        HomeService.actualizarInvestigacion($scope.updateInvestigacion)
                .then(actualizarActividadInvestigacionSuccess, actualizarActividadInvestigacionError);
    };
   
    
    /************ FUNCIONES UTILITARIAS ************/

    var addPlanificacion = function(idPlanificacion){
        angular.forEach(uploader.queue, function(value, key) {
            console.log(value.file.name);
            value.formData.push({
                idPlanificacion: idPlanificacion
            });
        });
        angular.forEach(uploader2.queue, function(value, key) {
            console.log(value.file.name);
            value.formData.push({
                idPlanificacion: idPlanificacion
            });
        });
    };

    var setResponsableDirector = function(actividadInvestigacion, colaboradores){
        angular.forEach(colaboradores, function(value, key){
            if(actividadInvestigacion.nidResponsable === value.nidPersona){
                $scope.responsable = value.snombre + " " + value.sapellido;
            }
            if(actividadInvestigacion.nidDirector === value.nidPersona){
                $scope.director = value.snombre + " " + value.sapellido;
            }
        });
    };

    var BuscarTipoInvestigacion = function(idTipoInvestigacion){
        angular.forEach($scope.tipoInvestigaciones, function(valor, key){
            if(valor.nidTipoActividadInvestigacion === idTipoInvestigacion){
                $scope.nombreTipoActividad = valor.snombreActividadInvestigacion;
            }
        });
        $scope.changeTipoActividad($scope.nombreTipoActividad);
    };
    
    $scope.changeTipoProduccion = function(tipoProduccion){
        if(tipoProduccion.toUpperCase() === "ARTICULO"){
            $scope.showVerificacion = true;
            $scope.fechaTipoProduccion = "Fecha de Aceptación";
            $scope.labelCodigo = "DOI o URL";
            $scope.labelNombrePublicacion = "Nombre de la Revista";
        } 
        if (tipoProduccion.toUpperCase() === "TEXTO" || tipoProduccion.toUpperCase() === "LIBRO" ){
            $scope.showVerificacion = true;
            $scope.fechaTipoProduccion = "Fecha de Publicación";
            $scope.labelCodigo = "ISBN";
            $scope.labelNombrePublicacion = "Nombre de la Editorial";
        }
    };
    
    $scope.changeEstadoProduccion = function(estadoProduccion){        
        $scope.realizado = false;
        $scope.ejecucion = false;
        $scope.pendiente = false;
        switch(estadoProduccion) {
            case "REALIZADO":
                $scope.realizado = true;
                $scope.ejecucion = false;
                $scope.pendiente = false;
                

                $scope.adjuntar = "Adjuntar Libro o Artículo";
                $scope.adjuntarOtros = "Adjuntar Libro o Artículo (Opcional)";
                $scope.pendienteAdjunto = false;
                break;
            case "EJECUCION":
                $scope.realizado = false;
                $scope.ejecucion = true;
                $scope.pendiente = false;
                
                
                $scope.adjuntar = "Adjuntar Planificación";
                $scope.adjuntarOtros = "Adjuntar avance de Libro o Artículo (Opcional)";
                $scope.pendienteAdjunto = true;
                break;
            case "PENDIENTE":
                $scope.realizado = false;
                $scope.ejecucion = false;
                $scope.pendiente = true;
                
                $scope.adjuntar = "Adjuntar Planificación";
                $scope.adjuntarOtros = "Adjuntar Libro o Artículo (Opcional)";
                $scope.pendienteAdjunto = false;
                $scope.actividadInvestigacion.sestadoProduccion = "POR REALIZAR"; //SOLO PARA FINES DE VISUALIZACIÓN
                break;
        };
    };
    
    $scope.irBandejaRevisados = function(){
        $scope.loader = true;
        if($scope.generado && !$scope.vicerector){ $location.path("/actividad/Generadas"); }
        if($scope.revisado){ $location.path("/actividad/Revisadas"); }
        if($scope.vicerector){ $location.path("/actividadesDocente"); }
    };
    
    $scope.changeTipoActividad = function(tipoActividad){
        $scope.mostrarActividad = [false, false, false, false]; //case1 , case2, case3, case4
        $scope.realizado = true;
        $scope.ejecucion = true;
        $scope.pendiente = true;
        $scope.pendienteAdjunto = true;
        $scope.mostrarAyuda = false;
        switch(tipoActividad.toUpperCase()) {
            case "INVESTIGACION FORMATIVA":
                $scope.mostrarActividad = [true, false, false, false];
                $scope.descripcionLabel = "Breve descripción de la Actividad Formativa";
                $scope.tituloLabel = "Nombre del curso o Asignatura";
                $scope.adjuntar = "Adjuntar Sílabo del Curso";
                $scope.adjuntarOtros = "Adjuntar Resultados de Investigación";
                $scope.showDescripcion = true;
                break;
            case "ASESORIA DE TESIS":
                $scope.mostrarActividad = [false, true, false, false];
                $scope.descripcionLabel = "Resumen de Tesis";
                $scope.tituloLabel = "Título de Tesis";
                $scope.adjuntar = "Adjuntar Resolución";
                $scope.adjuntarOtros = "Otros Medios (Plan de Tesis, Avances, Otros)";
                $scope.showDescripcion = false;
                break;
            case "INVESTIGACIONES BASICAS Y APLICADAS":
                $scope.mostrarActividad = [false, false, true, false];
                $scope.descripcionLabel = "Resumen de Investigación";
                $scope.tituloLabel = "Título del Proyecto de Investigación";
                $scope.adjuntar = "Adjuntar Contrato";
                $scope.adjuntarOtros = "Adjuntar Ficha de Postulación";
                $scope.showDescripcion = true;
                break;
            case "PRODUCCION INTELECTUAL":
                $scope.mostrarActividad = [false, false, false, true];
                $scope.descripcionLabel = "Resumen";
                $scope.tituloLabel = "Título";
                $scope.adjuntar = "Adjuntar Planificación";
                $scope.adjuntarOtros = "Adjuntar Libro o Artículo (Opcional)";
                $scope.showDescripcion = false;
                $scope.pendienteAdjunto = false;
                break;
            default:
                $scope.mostrarActividad = [false, false, false, false];
                $scope.descripcionLabel = "Resumen";
                $scope.tituloLabel = "Nombre";
                $scope.adjuntar = "Adjuntar";
                $scope.adjuntarOtros = "Adjuntar Otros";
                $scope.showDescripcion = true;
        };
    };
    
    $scope.getActividadById($scope.idActividad);
    
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
    
    $scope.descargarPDF = function(){
        console.log("Empezando descarga de PDF...");
        $scope.loader = true;
        TipoInvestigacionService.descargarPDF($scope.idActividad).then(descargarPDFSuccess, descargarPDFError);
    };


    /********** FILE UPLOAD **********/
    
    $scope.files = [];
    var uploader = $scope.uploader = new FileUploader({
        url: SRIUnsaConfig.SRIUnsaUrlServicio + '/files/subirArchivos'
    });
    var uploader2 = $scope.uploader2 = new FileUploader({
        url: SRIUnsaConfig.SRIUnsaUrlServicio + '/files/subirArchivos'
    });
    uploader.filters.push({
        name: 'enforceMaxFileSize',
        fn: function(item) {
            return this.queue.length < 10;
        }
    });
    console.log("filtro :: ", uploader.filters);
    uploader2.filters.push({
        name: 'customFilter2',
        fn: function(item , options) {
            return this.queue.length < 10;
        }
    });

    // CALLBACKS

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.log("Error fileitem  -----> ", fileItem);
        console.log("Error response  -----> ", response);
        console.log("Error status  -----> ", status);
        console.log("Error headers  -----> ", headers);
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
        console.info('onCompleteAll');
        $scope.loader = false;
        $scope.getActividadById($scope.idActividad);
        $scope.openCloseModal(true,false);
    };
     uploader2.onCompleteAll = function() {
        console.info('onCompleteAll');
        $scope.loader = false;
        $scope.getActividadById($scope.idActividad);
        $scope.openCloseModal(true,false);
    };
    
    
    
    /********** DataPicker ************/
    
    $scope.today = function() {
        $scope.fechaRegistro = new Date();
        $scope.fechaShowRegistro = $scope.sharedService.dateToString($scope.fechaRegistro);
    };
    $scope.today();

    $scope.inlineOptions = {
      customClass: getDayClass,
      minDate: new Date()
    };

    $scope.dateOptions = {
      dateDisabled: disabled,
      formatYear: "yy",
      maxDate: new Date(2020, 5, 22),
      minDate: new Date(),
      startingDay: 1,
      showWeeks:false,
      showButtonBar:false
    };

    // Disable weekend selection
    function disabled(data) {
      var date = data.date,
        mode = data.mode;
      return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
    }

    $scope.toggleMin = function() {
      $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
      $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
    };

    $scope.toggleMin();

    $scope.openAceptacion = function() {
      $scope.popupAceptacion.opened = true;
    };
    $scope.openFin = function() {
      $scope.popupFin.opened = true;
    };
    $scope.openRegistro = function() {
      $scope.popupRegistro.opened = true;
    };

    $scope.setDate = function(year, month, day) {
      $scope.dtRegistro = new Date(year, month, day);
    };

    $scope.formats = ['dd/MMMM/yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[0];
    $scope.altInputFormats = ['M!/d!/yyyy'];

    
    $scope.popupAceptacion = {
      opened: false
    };
    
    $scope.popupFin = {
      opened: false
    };
    
    $scope.popupRegistro = {
      opened: false
    };

    function getDayClass(data) {
        var date = data.date;
        var mode = data.mode;
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0,0,0,0);
            for (var i = 0; i < $scope.events.length; i++) {
                var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);
                if (dayToCheck === currentDay) {
                    return $scope.events[i].status;
                }
            }
        }
        return '';
    }
    
    
}]);
