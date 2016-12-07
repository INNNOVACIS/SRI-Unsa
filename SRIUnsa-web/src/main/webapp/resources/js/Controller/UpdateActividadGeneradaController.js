investigacionApp.controller('UpdateActividadGeneradaController', function($log, $scope, $location, $routeParams, FondoConcursableService, 
    SemestreService, TipoInvestigacionService, ActividadesGeneradasService, EstructuraAreaInvestigacionService,
    TipoNivelService, EstructuraOrganizacionService, ArchivosService, SharedService, FileUploader, SRIUnsaConfig) {
    
    $scope.loader = false;
    $scope.idActividad = $routeParams.ID;

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
    
    var getInvestigacionByIdSuccess = function(response){
        $log.debug("GetInvestigacionById - Success");
        console.log("Respuesta :: ", response);
        $scope.actividadGeneradaVista = response.body;
        editarActividad(response.body);
        $scope.getArchivosByIdActividad($scope.actividadGeneradaVista.nidActividadInvestigacion);
    };
    var getInvestigacionByIdError = function(response){
        $log.debug("GetInvestigacionById - Success");
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
        $log.debug("getArchivoByIdActividad - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;  
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
    
    $scope.getActividadById = function(idActividad){
        TipoInvestigacionService.getInvestigacionesById(idActividad).then(getInvestigacionByIdSuccess, getInvestigacionByIdError);
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
    
    /************ Editar Actividad Investigacion ***************/
    
    var editarActividad = function(actividad){
        $scope.actividadGeneradaVista.semestre = $scope.semestres[seleccionarSemestre(actividad.ssemestre)];
        $scope.actividadGeneradaVista.tipoInvestigacion = seleccionarTipoInvestigacion(actividad.nidTipoActividadInvestigacion);
        $scope.actividadGeneradaVista.areaInvestigacion = seleccionarArea(actividad.sareaInvestigacion);
        $scope.actividadGeneradaVista.subareaInvestigacion = seleccionarArea(actividad.ssubAreaInvestigacion);
        $scope.actividadGeneradaVista.disciplinaInvestigacion = seleccionarArea(actividad.sdisciplina);
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
    
        
    $scope.getListaTipoNivel();
    $scope.getEstructuraOrganizaciones();
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.getAreaInvestigaciones();
 
    $scope.getActividadById($scope.idActividad);
    
    $scope.actualizarActividad = function() {
        $scope.loader = true;
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
});
