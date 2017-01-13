investigacionApp.controller('ActividadGeneradaController',['$log', '$scope',
    '$routeParams', '$location', 'TipoInvestigacionService', 
    'ArchivosService', 'ActividadesPendientesService', 'SharedService', 
    'ActividadesGeneradasService' ,'SRIUnsaConfig',
    function($log, $scope, $routeParams, $location, TipoInvestigacionService, 
        ArchivosService, ActividadesPendientesService, SharedService, ActividadesGeneradasService,
        SRIUnsaConfig) {
    
    $scope.sharedService = SharedService;
    $scope.sharedService.scrollTop();
    $scope.loader = false;
    $scope.idActividad = $routeParams.ID;
    $scope.revisado   = false;
    $scope.generado   = false;
    $scope.pendiente  = false;
    $scope.vicerector = false;
    
    var estado = $routeParams.ESTADO;
    
    if(estado === "Revisadas" ){
        $scope.revisado = true;
    }
    if(estado === "Generadas"){
        $scope.generado = true;
    }
    if(estado === "Pendientes"){
        $scope.pendiente = true;
    }
    if($scope.sharedService.locationHome === "/homeVicerector"){
        $scope.vicerector = true;
    }
    
    /******************* CALLBACK FUNCTION *******************/
    
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
        $scope.actividadInvestigacion = response.body.actividadInvestigacion;
        $scope.actividadInvestigacion.dfechaRegistro = $scope.sharedService.dateToString($scope.actividadInvestigacion.dfechaRegistro);
        $scope.actividadInvestigacion.dfechaFin = $scope.sharedService.dateToString($scope.actividadInvestigacion.dfechaFin);
        $scope.actividadInvestigacion.dfechaAceptacion = $scope.sharedService.dateToString($scope.actividadInvestigacion.dfechaAceptacion);
        
        $scope.changeTipoProduccion($scope.actividadInvestigacion.sritipoProduccion);
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
    };
    var descargarArchivoError = function(response){
        $log.debug("descargarArchivo - Error");
        console.log("Respuesta :: ", response);
    };
    
    var AprobarActividadSuccess = function(response){
        $log.debug("AprobarActividad - Success");
        console.log("Respuesta :: ", response.body);
        $scope.sharedService.scrollTop();
        $scope.EnviarEmail(response.body.actividadInvestigacion.nidActividadInvestigacion);
        setTimeout(function(){
            $scope.$apply(function(){ 
                var popUp = SharedService.popUp;
                var titulo = "Aprobación Exitosa!";
                var mensaje = "La Actividad de Investigación se aprobó correctamente.";
                var url = "";
                if($scope.revisado)  { url = "/actividad/Revisadas"; }
                if($scope.generado)  { url = "/actividad/Generadas"; }
                if($scope.pendiente) { url = "/actividad/Pendientes";}
                if($scope.vicerector){ url = "/actividadesDocente";}
                
                var op1 = {open:true, txt:'Ir a Bandeja', fun:function(){
                    popUp.irPopUp();
                }};
                popUp.showPopUp(titulo, mensaje, url, op1);
                $scope.loader = false;
            });
        }, 1000);
    };
    var AprobarActividadError = function(response){
        $log.debug("AprobarActividad - Error");
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
        ArchivosService.descargarArchivo(archivo.id).then(descargarArchivoSuccess, descargarArchivoError);
    };
    
    /******************** ACCIONES DEL FORMULARIO APROBAR O RECHAZAR ACTIVIDAD **********************/
    
    $scope.AprobarActividad = function(){
        $scope.loader = true;
        var actividadGeneral = {
            idUsuario : $scope.sharedService.usuarioLogin.idUsuario,
            idFlujoActorOrigen : SRIUnsaConfig.DIUN,
            idEstado : SRIUnsaConfig.REVISADO,
            idPlanificacion : -1,
            codigoActor : SRIUnsaConfig.codeDIUN,
            actividadInvestigacion : {
                nidActividadInvestigacion : $scope.idActividad,
                suserCreacion : $scope.actividadInvestigacion.suserCreacion
            }
        };
        ActividadesPendientesService.AprobarActividad(actividadGeneral).then(AprobarActividadSuccess, AprobarActividadError);
    };
    
    $scope.RechazarActividad = function(){
        $scope.loader = true;
        $scope.sharedService.scrollTop();
        setTimeout(function(){
            $scope.$apply(function(){ 
                var popUp = SharedService.popUp;
                var titulo = "La Actividad se Rechazo con Éxito!";
                var mensaje = "La Actividad paso a estado de revisión.";
                var url = "";
                if($scope.revisado) { url = "/actividad/Revisadas"; }
                if($scope.generado) { url = "/actividad/Generadas"; }
                if($scope.pendiente){ url = "/actividad/Pendientes"; }
                
                var op1 = {open:true, txt:'Ir a Bandeja', fun:function(){
                    popUp.irPopUp();
                }};
                popUp.showPopUp(titulo, mensaje, url, op1);
                $scope.loader = false;
            });
        }, 1000);
    };
    
    $scope.EnviarEmail = function(idActividadGenerada){   
        var actividadGeneral = {
            idUsuario : $scope.sharedService.usuarioLogin.idUsuario,
            idFlujoActorOrigen : SRIUnsaConfig.DIDE,
            idEstado : SRIUnsaConfig.REVISADO,
            idPlanificacion : -1,
            colaboradores : [],
            plantillaDocumentoActividad : [],
            actividadInvestigacion : {
                nidActividadInvestigacion : idActividadGenerada
            }
        };
        ActividadesGeneradasService.EnviarEmail(actividadGeneral).then(EnviarEmailSuccess, EnviarEmailError);
    };
    
    /************ FUNCIONES UTILITARIAS ************/

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
            $scope.fechaTipoProduccion = "Fecha de Aceptacion";
            $scope.labelCodigo = "DOI";
            $scope.labelNombrePublicacion = "Nombre de la Revista";
        } 
        if (tipoProduccion.toUpperCase() === "TEXTO" || tipoProduccion.toUpperCase() === "LIBRO" ){
            $scope.showVerificacion = true;
            $scope.fechaTipoProduccion = "Fecha de Publicacion";
            $scope.labelCodigo = "ISBN";
            $scope.labelNombrePublicacion = "Nombre de la Editorial";
        }
    };
    
    $scope.irBandejaRevisados = function(){
        $scope.loader = true;
        if($scope.generado && !$scope.vicerector){ $location.path("/actividad/Generadas"); }
        if($scope.revisado){ $location.path("/actividad/Revisadas"); }
        if($scope.vicerector){ $location.path("/actividadesDocente"); }
    };
    
    $scope.changeTipoActividad = function(tipoActividad){
        $scope.mostrarActividad = [false, false, false, false]; //case1 , case2, case3, case4
        switch(tipoActividad.toUpperCase()) {
            case "INVESTIGACION FORMATIVA":
                $scope.mostrarActividad = [true, false, false, false];
                $scope.descripcionLabel = "Breve descripcion de la Actividad Formativa";
                $scope.tituloLabel = "Nombre del curso o Asignatura";
                $scope.adjuntar = "Adjuntar Sílabo del Curso";
                $scope.adjuntarOtros = "Adjuntar Resultados de Investigación";
                $scope.showDescripcion = true;
                break;
            case "ASESORIA DE TESIS":
                $scope.mostrarActividad = [false, true, false, false];
                $scope.descripcionLabel = "Resumen de Tesis";
                $scope.tituloLabel = "Titulo de Tesis";
                $scope.adjuntar = "Adjuntar Resolución";
                $scope.adjuntarOtros = "Otros Medios (Plan de Tesis, Avances, Otros)";
                $scope.showDescripcion = false;
                break;
            case "INVESTIGACIONES BASICAS Y APLICADAS":
                $scope.mostrarActividad = [false, false, true, false];
                $scope.descripcionLabel = "Resumen de Investigacion";
                $scope.tituloLabel = "Titulo del Proyecto de Investigacion";
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
        TipoInvestigacionService.descargarPDF($scope.idActividad).then(descargarPDFSuccess, descargarPDFError);
    };
    
    $scope.descargarExcel = function(){
        console.log("Empezando descarga de Excel...");
        TipoInvestigacionService.descargarExcel($scope.idActividad).then(descargarExcelSuccess, descargarExcelError);
    };
}]);
