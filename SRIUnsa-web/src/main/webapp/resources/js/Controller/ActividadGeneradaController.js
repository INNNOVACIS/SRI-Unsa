investigacionApp.controller('ActividadGeneradaController', function($log, $scope, $routeParams, $location, TipoInvestigacionService, 
    ArchivosService, ActividadesPendientesService, SharedService, SRIUnsaConfig) {
    
    $scope.sharedService = SharedService;
    $scope.loader = false;
    $scope.idActividad = $routeParams.ID;
    $scope.revisado = false;
    $scope.generado = false;
    $scope.pendiente = false;
    
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
    
    /******************* Servicios Callback *******************/
    
    var getTipoInvestigacionSuccess = function(response){
        $log.debug("GetTipoInvestigacion - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigaciones = response;
        BuscarTipoInvestigacion($scope.actividadGeneradaVista.nidTipoActividadInvestigacion);
    };
    var getTipoInvestigacionError = function(response){
        $log.debug("GetTipoInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getInvestigacionByIdSuccess = function(response){
        $log.debug("GetInvestigacionById - Success");
        console.log("Respuesta :: ", response);
        $scope.actividadGeneradaVista = response.body.actividadInvestigacion;
        $scope.getTipoInvestigacion();
        $scope.getArchivosByIdActividad($scope.actividadGeneradaVista.nidActividadInvestigacion);
    };
    var getInvestigacionByIdError = function(response){
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
        setTimeout(function(){
            $scope.$apply(function(){ 
                var popUp = SharedService.popUp;
                var titulo = "Aprobación Exitosa!";
                var mensaje = "La Actividad de Investigación se aprobó correctamente.";
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
    var AprobarActividadError = function(response){
        $log.debug("AprobarActividad - Error");
        console.log("Respuesta :: ", response);
    };
    
    /******************* Servicios *******************/

    
    $scope.getActividadById = function(idActividad){
        TipoInvestigacionService.getInvestigacionesById(idActividad).then(getInvestigacionByIdSuccess, getInvestigacionByIdError);
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
    
    $scope.AprobarActividad = function(){
        $scope.loader = true;
        var actividadGeneral = {
            idUsuario : $scope.sharedService.idUsuario,
            idFlujoActorOrigen : SRIUnsaConfig.DIDE,
            idEstado : SRIUnsaConfig.REVISADO,
            idPlanificacion : -1,
            actividadInvestigacion : {
                nidActividadInvestigacion : $scope.idActividad
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
    
    /************ Funciones Utilitarias ************/
    var BuscarTipoInvestigacion = function(idTipoInvestigacion){
        angular.forEach($scope.tipoInvestigaciones, function(valor, key){
            if(valor.nidTipoActividadInvestigacion === idTipoInvestigacion){
                $scope.nombreTipoActividad = valor.snombreActividadInvestigacion;
            }
        });
    };
    
    $scope.irBandejaRevisados = function(){
        $scope.loader = true;
        if($scope.generado){ $location.path("/actividad/Generadas"); }
        if($scope.revisado){ $location.path("/actividad/Revisadas"); }
    };
    
    $scope.getActividadById($scope.idActividad);
});
