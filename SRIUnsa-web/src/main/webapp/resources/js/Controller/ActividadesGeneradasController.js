investigacionApp.controller('ActividadesGeneradasController', function($log, $scope, FondoConcursableService, 
    SemestreService, TipoInvestigacionService, ActividadesGeneradasService, EstructuraAreaInvestigacionService,
    TipoNivelService, EstructuraOrganizacionService, ArchivosService) {
    
    $scope.loader = false;
    $scope.modal = { open: false, close: true };
    $scope.mensaje = {titulo: "", contenido: ""};
    $scope.panelGenerados = true;
    $scope.panelVer = false;
    $scope.panelEditar = false;

    $scope.panelChange = function(panel, actividadGenerada){
        $scope.loader = true;
        if(panel === 1){
            $scope.panelGenerados = true;
            $scope.panelVer = false;
            $scope.panelEditar = false;
            $scope.loader = false;
            $scope.openCloseModal(false, true);
        }else{
            if(panel === 2){
                $scope.panelGenerados = false;
                $scope.panelVer = true;
                $scope.panelEditar = false;
                $scope.actividadGenerada = actividadGenerada;
                $scope.getActividadById(actividadGenerada);
            }else{
                $scope.panelGenerados = false;
                $scope.panelVer = false;
                $scope.panelEditar = true;
                $scope.actividadGenerada = actividadGenerada;
                $scope.getActividadById(actividadGenerada);
            }
        }
    };
    
    /******************* Servicios Callback *******************/
    
    var getTipoNivelServiceSuccess = function(response){
    	$log.debug("Get tipoNivel - Success");    	
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
    };

    var getEstructuraOrganizacionServiceError = function(response){
     	$log.debug("Get EstructuraOrganizacion - Error"); 
    };
    
    var getFondoServiceSuccess = function(response){
    	$log.debug("Get Fondo - Success");
    	$scope.fondos = response;
    };

    var getFondoServiceError = function(response){
     	$log.debug("Get Fondo - Error", response); 
    };
    
    var getSemestreServiceSuccess = function(response){
    	$log.debug("Get Semestre - Success");        
    	$scope.semestres = response;
    };
    
    var getSemestreServiceError = function(response){
     	$log.debug("Get Semestre - Error");
        console.log("Error Response Semestre :: ", response);
    };
    
    var getTipoInvestigacionSuccess = function(response){
    	$log.debug("Get Investigacion - Success");
        console.log("Respuesta Investigacion :: ", response);
    	$scope.tipoInvestigaciones = response;
    };
    
    var getTipoInvestigacionError = function(response){
     	$log.debug("Get Investigacion - Error");
        console.log("Error Response Investigacion :: ", response);
    };
    
    var getInvestigacionByIdSuccess = function(response){
        console.log("getInvestigacionByIdSuccess :: ", response);
        $scope.actividadGeneradaVista = response;
        editarActividad(response);
        $scope.getArchivosByIdActividad($scope.actividadGeneradaVista.nidActividadInvestigacion);
    };
    
    var getInvestigacionByIdError = function(response){
        console.log("getInvestigacionByIdError :: ", response);
        $scope.loader = false;  
    };
    
    var getArchivoByIdActividadSuccess = function(response){
        console.log("getArchivoByIdActividadSuccess :: ", response);
        $scope.archivos = response;
        setTimeout(function(){
            $scope.$apply(function(){
                $scope.loader = false;
            });
        }, 1000);
    };
    
    var getArchivoByIdActividadError = function(response){
        console.log("getArchivoByIdActividadError :: ", response);
        $scope.loader = false;  
    };
    
    var getAllActividadesGeneradasSuccess = function(response){
        $scope.actividadesGeneradas = response;
        console.log("succcess :: ", response);
    };
    
    var getAllActividadesGeneradasError = function(response){
        console.log("error :: ", response);
    };
    
    var getAreaInvestigacionServiceSuccess = function(response){
    	$log.debug("Get AreaInvestigacion - Success");
    	$scope.areaInvestigaciones = response;
    };

    var getAreaInvestigacionServiceError = function(response){
     	$log.debug("Get AreaInvestigacion - Error"); 
    };
    
    var descargarArchivoSuccess = function(response){
        $log.debug("Descargar Archivo - Success");
    };
    
    var descargarArchivoError = function(response){
        $log.debug("Descargar Archivo - Error");
        console.log("Descargar Archivo :: ", response);
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
    
    $scope.getActividadById = function(actividad){
        TipoInvestigacionService.getInvestigacionesById(actividad.idactividadinvestigacion).then(getInvestigacionByIdSuccess, getInvestigacionByIdError);
    };
    
    $scope.getArchivosByIdActividad = function(idActividad){
      	ArchivosService.getArchivosByIdActividad(idActividad).then(getArchivoByIdActividadSuccess, getArchivoByIdActividadError);
    };
    
    $scope.getTipoInvestigacion = function(){
      	TipoInvestigacionService.getInvestigaciones().then(getTipoInvestigacionSuccess, getTipoInvestigacionError);
    };
    
    $scope.getAllActividadesGeneradas = function(){
        ActividadesGeneradasService.getAllActividadesGeneradas().then(getAllActividadesGeneradasSuccess, getAllActividadesGeneradasError);
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
        $scope.getActividades();
    });
    
    /*********************************************/
    
    var getFiltros = function(){
        var filtro = {
            nidTipoActividadInvestigacion : ($scope.tipoInvestigacion === null || $scope.tipoInvestigacion === undefined || $scope.tipoInvestigacion === "") ? "" : $scope.tipoInvestigacion.nidTipoActividadInvestigacion,
            sfacultad : ( $scope.facultad === null || $scope.facultad === undefined || $scope.facultad === "") ? "" : $scope.facultad.snombreEstructuraOrganizacion,
            sdepartamento : ($scope.departamento === null || $scope.departamento === undefined || $scope.departamento === "") ? "" : $scope.departamento.snombreEstructuraOrganizacion,
            sescuela : ($scope.escuela === null || $scope.escuela === undefined || $scope.escuela === "") ? "" : $scope.escuela.snombreEstructuraOrganizacion,
            ssemestre : ($scope.semestre === null || $scope.semestre === undefined || $scope.semestre === "") ? "" : $scope.semestre.snombreSemestre,
            sfondoConcursable : ($scope.fondo === null || $scope.fondo === undefined || $scope.fondo === "") ? "" : $scope.fondo.snombreFondoConcursable
        };
        return filtro;
    };
    
    var paginacionActividadesSuccess = function(response){
        $log.debug("Get Actividades - Success");
        $scope.actividadesGeneradas = response.lista;
        $scope.total = response.total;
    };
    var paginacionActividadesError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getActividades = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : getFiltros()};
        ActividadesGeneradasService.paginacionActividades(objPagina).then(paginacionActividadesSuccess, paginacionActividadesError);
    };
    
    $scope.filtrar = function() {
        $scope.getActividades();
    };
    
        
    $scope.getListaTipoNivel();
    $scope.getEstructuraOrganizaciones();
    $scope.getFondos();
    $scope.getSemestres();
    $scope.getTipoInvestigacion();
    $scope.getAreaInvestigaciones();
 
    $scope.getActividades();
    
    $scope.actualizarActividad = function() {
        $scope.loader = true;
        scrollTop();
        setTimeout(function(){
            $scope.$apply(function(){
                $scope.loader = false;
                $scope.mensaje = {titulo: "Actualización Exitosa!", contenido: "La Actividad de Investigación se actualizo correctamente."};
                $scope.openCloseModal(true,false);
            });
        }, 1000);
    };
    
    $scope.aprobarActividad = function(){
        $scope.loader = true;
        scrollTop();
        setTimeout(function(){
            $scope.$apply(function(){
                $scope.loader = false;
                $scope.mensaje = {titulo: "Aprobación Exitosa!", contenido: "La Actividad de Investigación se aprobó correctamente."};
                $scope.openCloseModal(true,false);
            });
        }, 1000);
    };
    
    /************ Funciones Utilitarias ************/
    $scope.openCloseModal = function(open, close) {
        $scope.modal = { open: open, close: close };
    };
    
    var scrollTop = function(){
        $('html,body').animate({
            scrollTop: $("#container").offset().top - 100
        }, 800);
    };
});
