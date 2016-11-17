investigacionApp.controller('ActividadGeneradaController', function($log, $scope, $routeParams, $location, TipoInvestigacionService, 
    ArchivosService) {
    
    $scope.loader = false;
    $scope.modal = { open: false, close: true };
    $scope.mensaje = {titulo: "", contenido: ""};
    $scope.idActividad = $routeParams.ID;
    
    /******************* Servicios Callback *******************/
    
    
    var getInvestigacionByIdSuccess = function(response){
        console.log("getInvestigacionByIdSuccess :: ", response);
        $scope.actividadGeneradaVista = response;
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
    
    var descargarArchivoSuccess = function(response){
        $log.debug("Descargar Archivo - Success");
    };
    
    var descargarArchivoError = function(response){
        $log.debug("Descargar Archivo - Error");
        console.log("Descargar Archivo :: ", response);
    };
    
    /******************* Servicios *******************/

    
    $scope.getActividadById = function(idActividad){
        TipoInvestigacionService.getInvestigacionesById(idActividad).then(getInvestigacionByIdSuccess, getInvestigacionByIdError);
    };
    
//    $scope.getTipoInvestigacion = function(){
//      	TipoInvestigacionService.getInvestigaciones().then(getTipoInvestigacionSuccess, getTipoInvestigacionError);
//    };
    
    $scope.getArchivosByIdActividad = function(idActividad){
        $scope.loader = true;
      	ArchivosService.getArchivosByIdActividad(idActividad).then(getArchivoByIdActividadSuccess, getArchivoByIdActividadError);
    };
    
    $scope.descargarArchivo = function(archivo){
        ArchivosService.descargarArchivo(archivo.id).then(descargarArchivoSuccess, descargarArchivoError);
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
    $scope.irBandeja = function(){
        $scope.openCloseModal(false,true);
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
    
    $scope.getActividadById($scope.idActividad);
});
