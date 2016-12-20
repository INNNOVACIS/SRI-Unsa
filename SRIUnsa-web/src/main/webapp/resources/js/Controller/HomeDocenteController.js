investigacionApp.controller('HomeDocenteController', function($log, $scope, $location,  TipoInvestigacionService, SharedService) {

    $scope.sharedService = SharedService;
    
    var GetTipoInvestigacionesSuccess = function(response){
        $log.debug("GetTipoInvestigaciones - Success");
        console.log("Respuesta :: ", response);
        $scope.tipoInvestigaciones = response;
    };
    var GetTipoInvestigacionesError = function(response){
        $log.debug("GetTipoInvestigaciones - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.GetTipoInvestigaciones = function(){
        TipoInvestigacionService.getInvestigaciones().then(GetTipoInvestigacionesSuccess, GetTipoInvestigacionesError);
    };
 
    $scope.goHome = function(tipoInvestigacion){
//    sessvars.tipoInvestigacion = tipoInvestigacion;
    $scope.sharedService.tipoInvestigacion = tipoInvestigacion;
        $location.path("/home");
    };
    
    $scope.GetTipoInvestigaciones();
});