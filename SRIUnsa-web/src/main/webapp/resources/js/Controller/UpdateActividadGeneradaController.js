investigacionApp.controller('UpdateActividadGeneradaController', function($log, $scope, $location, $routeParams, FondoConcursableService, 
    SemestreService, TipoInvestigacionService, EstructuraAreaInvestigacionService, HomeService, PersonasService, PlantillaDocumentoService,
    TipoNivelService, EstructuraOrganizacionService, ArchivosService, SharedService, FileUploader, SRIUnsaConfig, $sce) {
    
    $scope.loader = true;
    $scope.idActividad = $routeParams.ID;
    $scope.sharedService = SharedService;

    /******************* Servicios Callback *******************/
    
    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("GettipoNivel - Success");
        console.log("Respuesta :: ", response);
    	$scope.niveles = response;
        $scope.getEstructuraOrganizaciones();
    };
    var getTipoNivelServiceError = function(response){
     	$log.debug("Get TipoNivel - Error");
        console.log("Respuesta :: ", response);
    };

    var getEstructuraOrganizacionServiceSuccess = function(response){
    	$log.debug("Get EstructuraOrganizacion - Success");
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
     	$log.debug("Get EstructuraOrganizacion - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getFondoServiceSuccess = function(response){
    	$log.debug("Get Fondo - Success");
        console.log("Respuesta :: ", response);
    	$scope.fondos = response;
    };
    var getFondoServiceError = function(response){
     	$log.debug("Get Fondo - Error", response);
        console.log("Respuesta :: ", response);
    };
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("Get Semestre - Success");
        console.log("Respuesta :: ", response);
    	$scope.semestres = response;
    };
    var getSemestreServiceError = function(response){
     	$log.debug("Get Semestre - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getTipoInvestigacionSuccess = function(response){
    	$log.debug("Get Investigacion - Success");
        console.log("Respuesta :: ", response);
    	$scope.tipoInvestigaciones = response;
    };
    var getTipoInvestigacionError = function(response){
     	$log.debug("Get Investigacion - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getActividadInvestigacionByIdSuccess = function(response){
        $log.debug("getActividadInvestigacionById - Success");
        console.log("Respuesta :: ", response);
        
        $scope.plantillaDocumentoActividades = response.body.plantillaDocumentoActividad;
        $scope.actividadGeneradaVista = response.body.actividadInvestigacion;
        $scope.colaboradores = response.body.colaboradores;
        
        editarActividad(response.body.actividadInvestigacion);
        $scope.changeTipoActividad($scope.actividadGeneradaVista.tipoInvestigacion.snombreActividadInvestigacion);
        $scope.facultadChange();
        $scope.getArchivosByIdActividad($scope.actividadGeneradaVista.nidActividadInvestigacion);
        console.log("ActividadGeneradaModificada :::::: ", $scope.actividadGeneradaVista);
    };
    var getActividadInvestigacionByIdError = function(response){
        $log.debug("getActividadInvestigacionById - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;  
    };
    
    var getArchivoByIdActividadSuccess = function(response){
        $log.debug("getArchivoByIdActividad - Success");
        console.log("Respuesta :: ", response);
        $scope.archivos = response;
    };
    
    var getArchivoByIdActividadError = function(response){
        $log.debug("getArchivoByIdActividad - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getAreaInvestigacionServiceSuccess = function(response){
    	$log.debug("Get AreaInvestigacion - Success");
        console.log("Respuesta :: ", response);
    	$scope.areaInvestigaciones = response;
    };
    var getAreaInvestigacionServiceError = function(response){
     	$log.debug("Get AreaInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };
    
    var descargarArchivoSuccess = function(response){
        $log.debug("DescargarArchivo - Success");
        console.log("Respuesta :: ", response);
    };
    var descargarArchivoError = function(response){
        $log.debug("DescargarArchivo - Error");
        console.log("Respuesta :: ", response);
    };
    
    var actualizarActividadInvestigacionSuccess = function(response){
        $log.debug("ActualizarActividadInvestigacion - Success");
        console.log("Respuesta :: ", response);
        scrollTop();
        setTimeout(function(){
            $scope.$apply(function(){ 
                var popUp = SharedService.popUp;
                var titulo = "Actualización Exitosa!";
                var mensaje = "La Actividad de Investigación se actualizo correctamente.";
                var url = "/actividad/Generadas";
                var op1 = {open:true, txt:'Ir a Bandeja', fun:function(){
                    popUp.irPopUp();
                }};
                popUp.showPopUp(titulo, mensaje, url, op1);
                $scope.loader = false;
            });
        }, 1000);
    };
    var actualizarActividadInvestigacionError = function(response){
        $log.debug("ActualizarActividadInvestigacion - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    var GetPersonasSuccess = function(response){
        $log.debug("GetPersonas - Success");
        console.log("Respuesta :: ", response);
        $scope.personas = response;
    };
    var GetPersonasError = function(response){
        $log.debug("GetPersonas - Error");
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
        setValoresPlantilla($scope.plantillaDocumentoActividades);
        $scope.loader = false;
    };
    var GetPlantillaDocumentoByFacultadError = function(response){
        $log.debug("GetPlantillaDocumentoByFacultad - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    /******************* Servicios *******************/
    
    $scope.getListaTipoNivel = function(){
      	TipoNivelService.getListaTipoNivel().then(getTipoNivelServiceSuccess, getTipoNivelServiceError);
    };

    $scope.getEstructuraOrganizaciones = function(){
      	EstructuraOrganizacionService.getEstructuraOrganizaciones().then(getEstructuraOrganizacionServiceSuccess, getEstructuraOrganizacionServiceError);
    };
    
    $scope.getAreaInvestigaciones = function(){
      	EstructuraAreaInvestigacionService.getAreaInvestigaciones().then(getAreaInvestigacionServiceSuccess, getAreaInvestigacionServiceError);
    };
    
    $scope.getFondos = function(){
      	FondoConcursableService.getFondos().then(getFondoServiceSuccess, getFondoServiceError);
    };
    
    $scope.getSemestres = function(){
      	SemestreService.getSemestres().then(getSemestreServiceSuccess, getSemestreServiceError);
    };
    
    $scope.getArchivosByIdActividad = function(idActividad){
      	ArchivosService.getArchivosByIdActividad(idActividad).then(getArchivoByIdActividadSuccess, getArchivoByIdActividadError);
    };
    
    $scope.getTipoInvestigacion = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getTipoInvestigacionSuccess, getTipoInvestigacionError);
    };

    $scope.descargarArchivo = function(archivo){
        ArchivosService.descargarArchivo(archivo.id).then(descargarArchivoSuccess, descargarArchivoError);
    };
    $scope.facultadChange = function(){
        $scope.departamento = {};
        $scope.escuela = {};
    };
    $scope.departamentoChange = function(){
        $scope.escuela = {};
    };
    
    $scope.GetPersonas = function(){
      	PersonasService.getPersonas().then(GetPersonasSuccess, GetPersonasError);
    };
    
    $scope.facultadChange = function(){
        var estructuraOrganizacion = {
            snombreEstructuraOrganizacion : $scope.actividadGeneradaVista.sfacultad
        };
        PlantillaDocumentoService.GetPlantillaDocumentoByFacultad(estructuraOrganizacion).then(GetPlantillaDocumentoByFacultadSuccess, GetPlantillaDocumentoByFacultadError);
    };
      
    $scope.getActividadInvestigacionById = function(idActividad){
        TipoInvestigacionService.getInvestigacionesById(idActividad).then(getActividadInvestigacionByIdSuccess, getActividadInvestigacionByIdError);
    };
    
    /************ Editar Actividad Investigacion ***************/
    $scope.AgregarColaborador = function(){
        if(!isRepetido($scope.colaboradores, $scope.persona)){
            $scope.colaboradores.push($scope.persona);
            $scope.colaborador = {};
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
    };
    
    
    var editarActividad = function(actividad){
        $scope.actividadGeneradaVista.semestre = $scope.semestres[seleccionarSemestre(actividad.ssemestre)];
        $scope.actividadGeneradaVista.tipoInvestigacion = seleccionarTipoInvestigacion(actividad.nidTipoActividadInvestigacion);
        $scope.actividadGeneradaVista.areaInvestigacion = seleccionarArea(actividad.sareaInvestigacion);
        $scope.actividadGeneradaVista.subareaInvestigacion = seleccionarArea(actividad.ssubAreaInvestigacion);
        $scope.actividadGeneradaVista.disciplinaInvestigacion = seleccionarArea(actividad.sdisciplina);
        $scope.actividadGeneradaVista.responsable =  seleccionarResponsable(actividad.nidResponsable);
    };
    
    var seleccionarSemestre = function(nombre){
        var respuesta = -1;
        angular.forEach($scope.semestres, function(obj, key) {
            if(obj.snombreSemestre === nombre){
                respuesta = key;
            }
        });
        return respuesta;
    };
    
    var seleccionarTipoInvestigacion = function(idTipoActividad){
        var respuesta = null;
        angular.forEach($scope.tipoInvestigaciones, function(obj, key){
            if(obj.nidTipoActividadInvestigacion === idTipoActividad){
                respuesta = obj;
            }
        });
        return respuesta;
    };
    
    var seleccionarArea = function(nombre){
        var respuesta = null;
        angular.forEach($scope.areaInvestigaciones, function(obj, key){
            if(obj.sNombre === nombre){
                respuesta = obj;
            }
        });
        return respuesta;
    };
    
    var seleccionarResponsable = function(idResponsable){
        var respuesta = null;
        angular.forEach($scope.personas, function(value, key){
            if(value.nidPersona === idResponsable){
                respuesta = value.snombre + " " + value.sapellido;
            }
        });
        return respuesta;
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
    var getValoresPlantilla = function(){
        var plantillaDocumentoActividades = [];
        angular.forEach($scope.plantillaDocumentoActividades, function(plantillaDocumentoActividad,key){
            angular.forEach($scope.plantillaDocumento, function(value, key){
                if(plantillaDocumentoActividad.nidPlantillaDocumento === value.nidPlantillaDocumento){
                    var plantilla = {
                        nidPlantillaDocumentoActividad : plantillaDocumentoActividad.nidPlantillaDocumentoActividad,
                        nidPlantillaDocumento : value.nidPlantillaDocumento,
                        svalor : $scope[value.smodel],
                        dfechaCreacion : plantillaDocumentoActividad.dfechaCreacion,
                        suserCreacion : plantillaDocumentoActividad.suserCreacion,
                        sestado : "A"
                    };
                    plantillaDocumentoActividades.push(plantilla);
                }
            });
        });
        return plantillaDocumentoActividades;
    };
    var setValoresPlantilla = function(plantillaDocumentoActividades){
        angular.forEach(plantillaDocumentoActividades, function(plantillaDocumentoActividad,key){
            angular.forEach($scope.plantillaDocumento, function(value, key){
                if(plantillaDocumentoActividad.nidPlantillaDocumento === value.nidPlantillaDocumento){
                    $scope[value.smodel] = plantillaDocumentoActividad.svalor;
                }
            });
        });
    };
    
    $scope.getListaTipoNivel();
    $scope.getEstructuraOrganizaciones();
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.getAreaInvestigaciones();
    $scope.GetPersonas();
 
    setTimeout(function(){
        $scope.getActividadInvestigacionById($scope.idActividad);
    }, 1000);
    
    $scope.actualizarActividadInvestigacion = function() {
        $scope.loader = true;
        scrollTop();
        var actividadGeneral = {
            idUsuario : $scope.sharedService.idUsuario,
            idFlujoActorOrigen : SRIUnsaConfig.DOCE,
            idEstado : SRIUnsaConfig.CREADO,
            idPlanificacion : -1,
            colaboradores : $scope.colaboradores === undefined ? [] : $scope.colaboradores,
            plantillaDocumentoActividad : getValoresPlantilla(),
            actividadInvestigacion : {
                nidActividadInvestigacion : $scope.actividadGeneradaVista.nidActividadInvestigacion,
                nidResponsable : $scope.actividadGeneradaVista.nidResponsable,
                nidTipoActividadInvestigacion : $scope.actividadGeneradaVista.tipoInvestigacion.nidTipoActividadInvestigacion,
                nhoras : $scope.actividadGeneradaVista.nhoras,
                sritipoProduccion : $scope.actividadGeneradaVista.tipoProduccion === undefined ? "" : $scope.actividadGeneradaVista.tipoProduccion,
                sfondoConcursable : $scope.actividadGeneradaVista.snombreFondoConcursable,
                stipoAsesoria : $scope.actividadGeneradaVista.tipoAsesoria === undefined ? "" : $scope.actividadGeneradaVista.tipoAsesoria,
                ssemestre : $scope.actividadGeneradaVista.ssemestre,
                sfacultad : $scope.actividadGeneradaVista.sfacultad,
                sescuela : $scope.actividadGeneradaVista.sescuela,
                sdepartamento : $scope.actividadGeneradaVista.sdepartamento,
                sareaInvestigacion: $scope.actividadGeneradaVista.sareaInvestigacion,
                ssubAreaInvestigacion : $scope.actividadGeneradaVista.ssubAreaInvestigacion,
                sdisciplina : $scope.actividadGeneradaVista.sdisciplina,
                stipoLabor : $scope.actividadGeneradaVista.stipoLabor === undefined ? "" : $scope.actividadGeneradaVista.stipoLabor,
                snombreActividadInvestigacion : $scope.actividadGeneradaVista.snombreActividadInvestigacion,
                sdescripcionActividad : $scope.actividadGeneradaVista.sdescripcionActividad,
                suserCreacion : $scope.actividadGeneradaVista.suserCreacion,
                suserModificacion : $scope.sharedService.nombreUsuario,
                sestado : 'A'
            }
        };
        
        console.log("actualizacion data :::::  ", actividadGeneral);
        HomeService.actualizarInvestigacion(actividadGeneral).then(actualizarActividadInvestigacionSuccess, actualizarActividadInvestigacionError);
    };
    
    /************ Funciones Utilitarias ************/
    
    $scope.uploadFile = function(){
        var file = $scope.archivo;
        var formData = new FormData();
        formData.append('file', file);
//        HomeService.sendFile(formData, true).then(homeServiceSuccess, homeServiceError);
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
    
    $scope.irBandeja = function(){
        $scope.loader = true;
        $location.path("/actividad/Generadas");
    };
    
    var scrollTop = function(){
        $('html,body').animate({
            scrollTop: $("#container").offset().top - 100
        }, 800);
    };
    
    $scope.totalColaboradores = function(){
        return $scope.colaboradores ===  undefined ? 0 : $scope.colaboradores.length;
    };
    
    $scope.changeTipoActividad = function(tipoActividad){
        $scope.mostrarActividad = [false, false, false, false]; //case1 , case2, case3, case4
        switch(tipoActividad.toUpperCase()) {
            case "INVESTIGACION FORMATIVA":
                $scope.mostrarActividad = [true, false, false, false];
                break;
            case "ASESORIA DE TESIS":
                $scope.mostrarActividad = [false, true, false, false];
                break;
            case "INVESTIGACIONES BASICAS Y APLICADAS":
                $scope.mostrarActividad = [false, false, true, false];
                break;
            case "PRODUCCION INTELECTUAL":
                $scope.mostrarActividad = [false, false, false, true];
                break;
            default:
                $scope.mostrarActividad = [false, false, false, false];
        };
    };
    
});
