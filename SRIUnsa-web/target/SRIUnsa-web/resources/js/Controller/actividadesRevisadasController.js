investigacionApp.controller('actividadesRevisadasController', function($scope, ActividadesRevisadasService) {    
    
    $scope.panelGenerados = true;
    $scope.panelVer = false;
    $scope.panelEditar = false;
    $scope.actividad= "primer valor";

    $scope.panelChange = function(panel){
            if(panel === 1){
                    $scope.panelGenerados = true;
                    $scope.panelVer = false;
                    $scope.panelEditar = false;
            }else{
                    if(panel === 2){
                            $scope.panelGenerados = false;
                            $scope.panelVer = true;
                            $scope.panelEditar = false;
                    }else{
                            $scope.panelGenerados = false;
                            $scope.panelVer = false;
                            $scope.panelEditar = true;
                    }
            }
    };
    
    var getFiltroSuccess = function(response){
       console.log("success :: ", response);
    };
    var getFiltroError = function(response){
       console.log("Error :: " , response);
    };
    
    $scope.getInvestigaciones = function(){
        //ActividadesRevisadasService.getActividadesRevisadas().then(getActividadServiceSuccess, getActividadServiceError);
    };
    
    $scope.filtrar = function(){
        var filtro = {
            tipoInvestigacion : $scope.actividad.nombre,
            facultad : $scope.facultad.nombre,
            escuela : $scope.escuela.nombre,
            semestre : $scope.semestre.nombre,
            fondo : $scope.fondo.nombre
        };
        console.log("filtrar :: ", filtro);
        ActividadesRevisadasService.Filtrar(filtro).then(getFiltroSuccess, getFiltroError);
    };
    
    var getInvestigacionSuccess = function(response){
        $scope.actividadesRevisadas = response;
        console.log("succcess :: ", response);
    };
    
    var getInvestigacionError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getActividadesRevisadas = function(){
        ActividadesRevisadasService.getInvestigaciones().then(getInvestigacionSuccess, getInvestigacionError);
    };
    
    $scope.getActividadesRevisadas();
});