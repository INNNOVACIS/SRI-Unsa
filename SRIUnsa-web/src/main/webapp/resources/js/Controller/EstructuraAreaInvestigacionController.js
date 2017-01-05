investigacionApp.controller('EstructuraAreaInvestigacionController',['$log', '$scope', '$location', '$rootScope', '$filter', 
    'EstructuraAreaInvestigacionService', 'SharedService', function($log, $scope, $location, $rootScope, $filter, 
    EstructuraAreaInvestigacionService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.areaInvestigaciones = [];
    $scope.areaInvestigacion = {};
    $scope.areas = [];
    $scope.subAreas = [];
    $scope.disciplinas = [];
    $scope.niveles = ["Area", "Sub Area", "Disciplina"];
	
    /********** Servicios Callback **********/
        
    var getAreaInvestigacionServiceSuccess = function(response){
    	$log.debug("Get AreaInvestigacion - Success");
        console.log("Respuesta :: ", response);
    	$scope.areaInvestigaciones = response;
        getAreaSubAreaDisciplina($scope.areaInvestigaciones);
    };

    var getAreaInvestigacionServiceError = function(response){
     	$log.debug("Get AreaInvestigacion - Error"); 
        console.log("Respuesta :: ", response);
    };

    var registrarAreaInvestigacionSuccess = function(response){
    	$log.debug("Registrar AreaInvestigacion - Success");
        console.log("Respuesta :: ", response);
    	$scope.getAreaInvestigacionByPagina();
    };
    var registrarAreaInvestigacionError = function(response){
        $log.debug("Registrar AreaInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };

    var updateAreaInvestigacionSuccess = function(response){
    	$log.debug("Update AreaInvestigacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.getAreaInvestigacionByPagina();
    };

    var updateAreaInvestigacionError = function(response){
        $log.debug("Update AreaInvestigacion - Error");
        console.log("Respuesta :: ", response);
    };

    var deleteAreaInvestigacionSuccess = function(response){
    	$log.debug("DeleteAreaInvestigacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.areaInvestigacion = response;
    };

    var deleteAreaInvestigacionError = function(response){
        $log.debug("DeleteAreaInvestigacion - Error");
    	console.log("Respuesta :: ", response);
    };

    /********** CRUD AreaInvestigaciones ***********/

    $scope.getAreaInvestigaciones = function(){
      	EstructuraAreaInvestigacionService.getAreaInvestigaciones().then(getAreaInvestigacionServiceSuccess, getAreaInvestigacionServiceError);
    };

    $scope.registrarAreaInvestigacion = function(){
        var areaInvestigacion = {
            nIdPadre : getIdPadre(),            
            sNivel : $scope.nivel,
            sNombre : $scope.nombre,
            sestado : "A",
            suserCreacion : $scope.sharedService.nombreUsuario,
            suserModificacion : $scope.sharedService.nombreUsuario
        };
	EstructuraAreaInvestigacionService.registrarAreaInvestigacion(areaInvestigacion).then(registrarAreaInvestigacionSuccess, registrarAreaInvestigacionError);
    };

    $scope.updateAreaInvestigacion = function(){
        var areaInvestigacion = {
            nIdPadre : getIdPadre(),            
            sNivel : $scope.nivel,
            sNombre : $scope.nombre,
            nidEstructura : $scope.areaInvestigacion.nidEstructura,
            sestado : "A",
            suserCreacion : $scope.sharedService.nombreUsuario,
            suserModificacion : $scope.sharedService.nombreUsuario
        };
    	EstructuraAreaInvestigacionService.updateAreaInvestigacion(areaInvestigacion).then(updateAreaInvestigacionSuccess, updateAreaInvestigacionError);
    };

    $scope.deleteAreaInvestigacion = function(areaInvestigacion){
    	$scope.areaInvestigacion = areaInvestigacion;
    	EstructuraAreaInvestigacionService.deleteAreaInvestigacion ($scope.areaInvestigacion).then(deleteAreaInvestigacionSuccess, deleteAreaInvestigacionError);
    };

    $scope.update = function(areaInvestigacion){
        $scope.areaInvestigacion = areaInvestigacion;
        $scope.nombre = areaInvestigacion.sNombre;
        $scope.nivel = areaInvestigacion.sNivel;
        $scope.changeNivel(areaInvestigacion.sNivel);
        seleccionarEstructuraAreaOrganizacion(areaInvestigacion);
    	
    };
    
    $scope.changeNivel = function(nivel){
        switch(nivel.toUpperCase()){
            case "AREA" :
                $scope.showArea = false;
                $scope.showSubArea = false;
                $scope.showDisciplina = false;
                break;
            case "SUB AREA" :
                $scope.showArea = true;
                $scope.showSubArea = false;
                $scope.showDisciplina = false;
                break;
            case "DISCIPLINA" :
                $scope.showArea = false;
                $scope.showSubArea = true;
                $scope.showDisciplina = false;
                break;
            default:
        }
    };
    
    $scope.cancel = function(){
        $scope.nombre = "";
        $scope.nivel = "";
        $scope.area = {};
        $scope.subArea = {};
    };
    
    var seleccionarEstructuraAreaOrganizacion = function(estructura){
        if(estructura.sNivel.toUpperCase() === "SUB AREA"){
            angular.forEach($scope.areas, function(value, key){
                if(value.nidEstructura === estructura.nIdPadre){
                    $scope.area = value;
                }
            });
        }
        if(estructura.sNivel.toUpperCase() === "DISCIPLINA"){
            angular.forEach($scope.subAreas, function(value, key){
                if(value.nidEstructura === estructura.nIdPadre){
                    $scope.subArea = value;
                }
            });
        }
    };
    
    var getAreaSubAreaDisciplina = function(areaOrganizaciones){
        angular.forEach(areaOrganizaciones, function(value, key){
            switch(value.sNivel.toUpperCase()){
                case "AREA" :
                    $scope.areas.push(value);
                    break;
                case "SUB AREA" :
                    $scope.subAreas.push(value);
                    break;
                case "DISCIPLINA" :
                    $scope.disciplinas.push(value);
                    break;
                default:
            }
        });
    };
    
    var getIdPadre = function(){
        var padre = 0;
        if($scope.area !== undefined &&  $scope.area !==  {}){
            padre = $scope.area.nidEstructura;
        }
        if($scope.subArea !== undefined &&  $scope.subArea !==  {}){
            padre = $scope.subArea.nidEstructura;
        }
        return padre;
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
        $scope.getAreaInvestigacionByPagina();
    });
    
    /*********************************************/
    
    var getAreaInvestigacionByPaginaSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.areaInvestigaciones = [];
        $scope.areaInvestigaciones = response.lista;
        $scope.total = response.total;
    };
    
    var getAreaInvestigacionByPaginaError = function(response){
        console.log("error :: ", response);
    };
    
    $scope.getAreaInvestigacionByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        EstructuraAreaInvestigacionService.getAreaInvestigacionByPagina(objPagina).then(getAreaInvestigacionByPaginaSuccess, getAreaInvestigacionByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getAreaInvestigacionByPagina();
    };
    
    $scope.getAreaInvestigaciones();
    $scope.getAreaInvestigacionByPagina();
}]);