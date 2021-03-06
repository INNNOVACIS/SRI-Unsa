    investigacionApp.controller('HomeController',['$log', '$scope', '$location', 'SharedService', 'SRIUnsaConfig',
    'HomeService', 'TipoInvestigacionService', 'SemestreService', 'TipoAsesoriaService', 'TipoProduccionService',
    'EstructuraAreaInvestigacionService', 'FondoConcursableService', 'TipoInvestigadorService',
    'ActividadesGeneradasService', 'PlantillaDocumentoService', 'FileUploader', '$sce', 
    function($log, $scope, $location, SharedService, SRIUnsaConfig,
    HomeService, TipoInvestigacionService, SemestreService, TipoAsesoriaService, TipoProduccionService,
    EstructuraAreaInvestigacionService, FondoConcursableService, TipoInvestigadorService,
    ActividadesGeneradasService, PlantillaDocumentoService, FileUploader, $sce) {
    
    $scope.sharedService = SharedService;
    $scope.disabled = true;
    $scope.tipoInvestigaciones = [];
    $scope.estructuraOrganizaciones = [];
    $scope.semestres = [];
    $scope.loader = false;
    $scope.modal = { open: false, close: true };
    $scope.mensajeSuccess = false;
    $scope.mensajeError = false;
    $scope.descripcion = "";
    $scope.nombreInvestigacion = "";
    $scope.totalColaboradores = "0 seleccionados";
    $scope.colaboradores = [];
    $scope.submitted = false;
    $scope.tipoMonedas = ["Soles", "Dolares"];
    $scope.facultad = {};
    $scope.departamento = {};
    $scope.director = {};
    
    $scope.facultad.snombreEstructuraOrganizacion = $scope.sharedService.usuarioHome[0].nombreFacultad;
    $scope.departamento.snombreEstructuraOrganizacion = $scope.sharedService.usuarioHome[0].nombreDepartamento;
    $scope.director.nidPersona = $scope.sharedService.usuarioHome[0].idPersonaDirector;
    $scope.responsable = $scope.sharedService.usuarioHome[0].nombre + " " + $scope.sharedService.usuarioHome[0].apellido;
    $scope.directores = $scope.sharedService.usuarioHome;
    $scope.director.snombre = $scope.sharedService.usuarioHome[0].nombreDirector;
    $scope.director.sapellido = $scope.sharedService.usuarioHome[0].apellidoDirector;
    
    $scope.unidadTiempo = [
        { label: 'Días', value: 1 },
        { label: 'Meses', value: 30 },
        { label: 'Años', value: 365 }
    ]; 

    console.log("USUARIOHOME :: ", $scope.sharedService.usuarioHome);

    /***************** CallBack *******************/
    
    var GetTipoInvestigacionesSuccess = function(response){
        $log.debug("GetTipoInvestigaciones - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigaciones = response;
        angular.forEach($scope.tipoInvestigaciones, function(value, key){
            if(value.nidTipoActividadInvestigacion === $scope.sharedService.tipoInvestigacion.nidTipoActividadInvestigacion){
                $scope.tipoInvestigacion = value;
                $scope.changeTipoActividad($scope.tipoInvestigacion.snombreActividadInvestigacion);
            }
        });
    };
    var GetTipoInvestigacionesError = function(response){
        $log.debug("GetTipoInvestigaciones - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetSemestresSuccess = function(response){
        $log.debug("GetSemestres - Success");
        console.log("Respuesta :: ", response);
        $scope.semestres = response;
        $scope.semestre = verificarSemestre($scope.semestres);
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
        $scope.tipoAsesoria = $scope.tipoAsesorias[0];
    };
    var GetTipoAsesoriasError = function(response){
        $log.debug("GetFondos - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetTipoProduccionesSuccess = function(response){
        $log.debug("GetTipoProducciones - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoProducciones = response;
        setTipoProduccion($scope.tipoProducciones);
    };
    var GetTipoProduccionesError = function(response){
        $log.debug("GetTipoProducciones - Error");
        console.log("Respuesta :: ", response);
    };
    
    var RegistrarInvestigacionSuccess = function(response){
        $log.debug("RegistrarInvestigacion - Success");
        console.log("Respuesta :: ", response);
        addPlanificacion(response.body.idPlanificacion);
        if(uploader.queue.length === 0){            
            $scope.loader = false;
            $scope.openCloseModal(true,false);
            
        } else {
            uploader.uploadAll();
            uploader2.uploadAll();
        }
        /*COMENTARIO POR WILFREDO , no se envia email cuando se registrar una*/
        //$scope.EnviarEmail(response.body.actividadInvestigacion.nidActividadInvestigacion);
    };
    var RegistrarInvestigacionError = function(response){
        $log.debug(response);
        $scope.mensajeError = true;
        $scope.message = response;
        $scope.loader = false;
    };

    var EnviarEmailSuccess = function(response){
        $log.debug("EnviarEmail - Success");
        console.log("Respuesta :: ", response);
    };
    var EnviarEmailError = function(response){
        $log.debug("EnviarEmail - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetPlantillaDocumentoByFacultadSuccess = function(response){
        $log.debug("GetPlantillaDocumentoByFacultad - Success");
        console.log("Respuesta :: ", response);
        $scope.plantillaDocumento = response.body;
        $scope.campos = "";
        var buildCampos = "";
        var formGroupInicio = '<div class="form-group">';
        var formGroupFin = '</div>';
        angular.forEach(response.body, function(value, key){
            if((key) % 2 === 0 ){
                if(buildCampos === ""){
                    buildCampos = formGroupInicio + value.splantilla;
                } else {
                    buildCampos = buildCampos + formGroupFin + formGroupInicio + value.splantilla;
                }
            } else {
                buildCampos = buildCampos + value.splantilla;
            }
            if(value.stipo === "combobox"){
                generarOpciones(value);
            }
        });
        
        buildCampos = buildCampos + formGroupFin;
        
        $scope.campos = $sce.trustAsHtml(buildCampos);
    };
    var GetPlantillaDocumentoByFacultadError = function(response){
        $log.debug("GetPlantillaDocumentoByFacultad - Error");
        console.log("Respuesta :: ", response);
    };
    
    var GetTipoInvestigadorSuccess = function(response){
        $log.debug("GetTipoInvestigador - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigadores = response;
    };
    var GetTipoInvestigadorError = function(response){
        $log.debug("GetTipoInvestigador - Error");
        console.log("Respuesta :: ", response);
    };
    
    /***************** Servicios ******************/
    
    $scope.GetTipoInvestigaciones = function(){
        TipoInvestigacionService.getInvestigaciones().then(GetTipoInvestigacionesSuccess, GetTipoInvestigacionesError);
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
    $scope.GetTipoInvestigador = function(){
        TipoInvestigadorService.GetTipoInvestigador().then(GetTipoInvestigadorSuccess, GetTipoInvestigadorError);
    };

    $scope.AgregarColaborador = function(){
        if(!isRepetido($scope.colaboradores, $scope.persona)){
            $scope.colaboradores.push($scope.persona);
            $scope.colaborador = {};
            getTotalColaboradores();
        }
    };
    $scope.DeleteColaborador = function(colaborador){
        var index = -1;
        angular.forEach($scope.colaboradores, function(valor, key){
            if(valor.nidPersona === colaborador.nidPersona){
                index = key;
            }
        });
        $scope.colaboradores.splice(index, 1);
        getTotalColaboradores();
    };
    var generarOpciones = function(value){
        $scope[value.sopciones] = value.sdata.split(",");
    };
    var isRepetido = function(lista, objeto){
        var repetido = false;
        angular.forEach(lista, function(valor, key){
            if(valor.nidPersona === objeto.nidPersona){
                repetido = true;
            }
        });
        return repetido;
    };
    
    $scope.changeFacultad = function(facultad){
        $scope.departamentos = [];
        $scope.escuelas = [];
        angular.forEach($scope.estructuraOrganizaciones, function(value, key){
            if(value.nidPadre === facultad.nidEstructuraOrganizacion){
                $scope.departamentos.push(value);
                $scope.escuelas.push(value);
            }
        });
        
        var estructuraOrganizacion = {
            nidEstructuraOrganizacion : facultad.nidEstructuraOrganizacion,
            nidTipoNivel : facultad.nidTipoNivel,
            snombreEstructuraOrganizacion : facultad.snombreEstructuraOrganizacion,
            nidPadre : facultad.nidPadre,
            snivel : facultad.snivel
        };
        PlantillaDocumentoService.GetPlantillaDocumentoByFacultad(estructuraOrganizacion).then(GetPlantillaDocumentoByFacultadSuccess, GetPlantillaDocumentoByFacultadError);
    };
    
    
    $scope.changeArea = function(area){
        $scope.subAreaInvestigaciones = [];
        angular.forEach($scope.areaInvestigaciones, function(value, key){
            if(value.nIdPadre === area.nidEstructura){
                $scope.subAreaInvestigaciones.push(value);
            }
        });
    };
    $scope.changeSubArea = function(subArea){
        $scope.disciplinaInvestigaciones = [];
        angular.forEach($scope.areaInvestigaciones, function(value, key){
            if(value.nIdPadre === subArea.nidEstructura){
                $scope.disciplinaInvestigaciones.push(value);
            }
        });
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
    
    /************ Registrar Actividad de Investigacion ****************/
    
    $scope.registrarInvestigacion = function(isValid){
//        if(isValid && uploader.queue.length !== 0 && $scope.descripcion.length != 0){
            $scope.loader = true;
            $scope.sharedService.scrollTop();
            console.log("docente ====> ", $scope.sharedService.docente);
            console.log("docente ====> ", $scope.sharedService.usuarioLogin);
            var actividadGeneral = {
                idUsuario : $scope.sharedService.docente !== undefined ? $scope.sharedService.docente.nidUsuario : $scope.sharedService.usuarioLogin.idUsuario,
                idFlujoActorOrigen : SRIUnsaConfig.DOCE,
                idEstado : SRIUnsaConfig.CREADO,
                idPlanificacion : -1,
                codigoActor : SRIUnsaConfig.codeDOCE,
                colaboradores : $scope.colaboradores === undefined ? [] : $scope.colaboradores,
                plantillaDocumentoActividad : getValoresPlantilla(),
                actividadInvestigacion : {
                    nidResponsable : $scope.sharedService.docente !== undefined  ? $scope.sharedService.docente.nidPersona : $scope.sharedService.usuarioLogin.idPersona,
                    nidTipoActividadInvestigacion : $scope.tipoInvestigacion.nidTipoActividadInvestigacion,
                    nhoras : $scope.duracionInvestigacion,
                    sritipoProduccion : $scope.tipoProduccion === undefined ? "" : $scope.tipoProduccion.snombreTipoProduccion,
                    sfondoConcursable : $scope.fondo === undefined ? "" : $scope.fondo.snombreFondoConcursable,
                    stipoAsesoria : $scope.tipoAsesoria === undefined ? "" : $scope.tipoAsesoria.snombreTipoAsesoria,
                    ssemestre : $scope.semestre.snombreSemestre,
                    sfacultad : $scope.facultad.snombreEstructuraOrganizacion,
                    sescuela : $scope.escuela === undefined ? "" : $scope.escuela.snombreEstructuraOrganizacion,
                    sdepartamento : $scope.departamento.snombreEstructuraOrganizacion,
                    sareaInvestigacion: $scope.areaInvestigacion === undefined ? "" : $scope.areaInvestigacion.sNombre,
                    ssubAreaInvestigacion : $scope.subAreaInvestigacion === undefined ? "" : $scope.subAreaInvestigacion.sNombre,
                    sdisciplina : $scope.disciplinaInvestigacion.sNombre,
                    stipoLabor : $scope.tipoLabor === undefined ? "" : $scope.tipoLabor.nombre,
                    snombreActividadInvestigacion : $scope.nombreInvestigacion,
                    dfechaRegistro : $scope.fechaRegistro,
                    dfechaAceptacion : $scope.fechaAceptacion,
                    dfechaFin : $scope.fechaFin,
                    sdescripcionActividad : $scope.descripcion,
                    nidDirector : $scope.director.nidPersona,
                    snombreAsesorado : $scope.nombreAsesorado,                    
                    slineaInvestigacion : $scope.lineaInvestigacion,
                    snombreCurso : $scope.nombreCurso,
                    snumeroContrato : $scope.numeroContrato,
                    snombrePublicacion : $scope.nombrePublicacion,
                    sestadoProduccion : $scope.estadoProduccion,
                    sduracionProyecto : $scope.duracion * $scope.modelUnidadTiempo.value,
                    scodigo : $scope.codigo,
                    splazoEjecucion : $scope.plazoEjecucion,
                    suserCreacion : $scope.sharedService.usuarioLogin.nombreUsuario,
                    suserModificacion : $scope.sharedService.usuarioLogin.nombreUsuario,
                    sestado : 'A'
                }
            };
            HomeService.registrarInvestigacion(actividadGeneral).then(RegistrarInvestigacionSuccess, RegistrarInvestigacionError);
//        } else {
//            $scope.sharedService.scrollTop();
//            $scope.adjuntoValidacion = true;
//            $scope.descripcionValidacion = true;
//            $scope.submitted = true;
//            angular.forEach($scope.plantillaDocumento, function(value,key){
//                console.log($scope[value.smodel]);
//            });
//        }
    };
    
    $scope.openModalConfirmacionRegistro = function(formActividad, formDescripcion, formAdjuntos){
        if($scope.mostrarActividad[3] && $scope.realizado){
            formAdjuntos = 1;
        }
        
        if(formActividad && formDescripcion && formAdjuntos !== 0){
            $('#modalConfirmacionRegistro').modal('show');
        } else {
            $scope.sharedService.scrollTop();
            $scope.adjuntoIsValido = true;
            $scope.submitted = true;
        }
        if($scope.mostrarActividad[3] && $scope.realizado){
            $scope.adjuntoIsValido = false;
        }

    };
    
    $scope.$watch('uploader.queue.length', function() {
       $scope.adjuntoIsValido = false;
    });
    
//    $scope.$watch('descripcion', function() {
//       $scope.descripcionValidacion = false;
//    });
    
    var getValoresPlantilla = function(){
        var plantillaDocumentoActividades = [];
        angular.forEach($scope.plantillaDocumento, function(value, key){
            var plantillaDocumentoActividad = {
                nidPlantillaDocumento : value.nidPlantillaDocumento,
                svalor : $scope[value.smodel]
            };
            plantillaDocumentoActividades.push(plantillaDocumentoActividad);
        });
        return plantillaDocumentoActividades;
    };
    
    $scope.EnviarEmail = function(idActividadGenerada){   
        var actividadGeneral = {
            idUsuario : $scope.sharedService.usuarioLogin.idUsuario,
            idFlujoActorOrigen : SRIUnsaConfig.DOCE,
            idEstado : SRIUnsaConfig.CREADO,
            idPlanificacion : -1,
            codigoActor : SRIUnsaConfig.codeDOCE,
            colaboradores : [],
            plantillaDocumentoActividad : [],
            actividadInvestigacion : {
                nidActividadInvestigacion : idActividadGenerada
            }
        };
        ActividadesGeneradasService.EnviarEmail(actividadGeneral).then(EnviarEmailSuccess, EnviarEmailError);
    };
    
    
    $scope.GetTipoInvestigaciones();
    $scope.GetSemestres();
    $scope.GetAreaInvestigaciones();
    $scope.GetFondos();
    $scope.GetTipoAsesorias();
//    $scope.GetTipoProducciones();
    $scope.GetTipoInvestigador();
    
    /*********** Funciones Utilitarias ************/
    
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
                $scope.mostrarAyuda = false;
                break;
            case "ASESORIA DE TESIS":
                $scope.mostrarActividad = [false, true, false, false];
                $scope.descripcionLabel = "Resumen de Tesis";
                $scope.tituloLabel = "Título de Tesis";
                $scope.adjuntar = "Adjuntar Resolución";
                $scope.adjuntarOtros = "Otros Medios (Plan de Tesis, Avances, Otros)(Opcional)";
                $scope.showDescripcion = false;
                $scope.mostrarAyuda = false;
//                $scope.descripcion = "vacio";
                break;
            case "INVESTIGACIONES BASICAS Y APLICADAS":
                $scope.mostrarActividad = [false, false, true, false];
                $scope.descripcionLabel = "Resumen de Investigación";
                $scope.tituloLabel = "Título del Proyecto de Investigación";
                $scope.adjuntar = "Adjuntar Contrato";
                $scope.adjuntarOtros = "Adjuntar Ficha de Postulación";
                $scope.showDescripcion = true;
                $scope.mostrarAyuda = true;
                break;
            case "PRODUCCION INTELECTUAL":
                $scope.mostrarActividad = [false, false, false, true];
                $scope.descripcionLabel = "Resumen";
                $scope.tituloLabel = "Título";
                $scope.adjuntar = "Adjuntar Planificación";
                $scope.adjuntarOtros = "Adjuntar Libro o Artículo (Opcional)";
                $scope.showDescripcion = false;
                $scope.mostrarAyuda = false;
                $scope.GetTipoProducciones();
//                setTipoProduccion($scope.tipoProducciones);
                break;
            default:
                $scope.mostrarActividad = [false, false, false, false];
                $scope.descripcionLabel = "Resumen";
                $scope.tituloLabel = "Nombre";
                $scope.adjuntar = "Adjuntar";
                $scope.adjuntarOtros = "Adjuntar Otros";
                $scope.showDescripcion = true;
                $scope.mostrarAyuda = false;
        };
    };
    
    $scope.changeEstadoProduccion = function(){        
        $scope.realizado = false;
        $scope.ejecucion = false;
        $scope.pendiente = false;
        switch($scope.estadoProduccion) {
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
                break;
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
    $scope.changeTipoProduccion = function(tipoProduccion){
        $scope.showVerificacion = true;
        if(tipoProduccion.snombreTipoProduccion.toUpperCase() === "ARTICULO"){
            $scope.fechaTipoProduccion = "Fecha de Aceptación";
            $scope.labelCodigo = "DOI o URL";
            $scope.labelNombrePublicacion = "Nombre de la Revista";
        } else {
            $scope.fechaTipoProduccion = "Fecha de Publicación";
            $scope.labelCodigo = "ISBN";
            $scope.labelNombrePublicacion = "Nombre de la Editorial";
        }
    };
    
    var setTipoProduccion = function(tipoProducciones){
        angular.forEach(tipoProducciones, function(value, key){
            if(value.snombreTipoProduccion.toUpperCase() === "ARTICULO"){
                $scope.tipoProduccion = value;
            }
        });
        $scope.changeTipoProduccion($scope.tipoProduccion);
        $scope.estadoProduccion = "REALIZADO";
        $scope.changeEstadoProduccion();
    };
    
    var getTotalColaboradores = function(){
        var total = $scope.colaboradores ===  undefined ? 0 : $scope.colaboradores.length;
        $scope.totalColaboradores = total + " seleccionados";
//        return total + " seleccionados";
    };
    
    $scope.openCloseModal = function(open, close) {
        $scope.modal = { open: open, close: close };
    };
    
    var limpiarCampos = function(){
        $scope.duracionInvestigacion = 0;
        $scope.tipoProduccion = {};
        $scope.fondo = {};
        $scope.tipoAsesoria = {};
        $scope.escuela = {};
        $scope.areaInvestigacion = {};
        $scope.subAreaInvestigacion = {};
        $scope.disciplinaInvestigacion = {};
        $scope.tipoLabor = {};
        $scope.nombreInvestigacion = " ";
        $scope.descripcion = "";
        $scope.campos = "";
        $scope.lineaInvestigacion = "";
        $scope.colaboradores = [];
        $scope.colaborador = {};
        $scope.fechaInicio = "";
        $scope.fechaFin = "";
        $scope.avance = "";
        $scope.submitted = false;
        $scope.nombreAsesorado = "";
        $scope.cuiAsesorado = "";
        $scope.lineaInvestigacion = "";
        $scope.nombreCurso = "";
        $scope.numeroContrato = "";
        $scope.montoFinanciamiento = "";
        $scope.codigo = "";
        $scope.nombrePublicacion = "";
        $scope.fechaAceptacion = "";
        $scope.plazoEjecucion = "";
        
        uploader.clearQueue();
        uploader2.clearQueue();
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
        angular.forEach(uploader2.queue, function(value, key) {
            console.log(value.file.name);
            value.formData.push({
                idPlanificacion: idPlanificacion
            });
        });
    };
    
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
        $scope.openCloseModal(true,false);
    };
     uploader2.onCompleteAll = function() {
        console.info('onCompleteAll');
        $scope.loader = false;
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