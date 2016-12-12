investigacionApp.controller('PlantillaDocumentoController', function($log, $scope, EstructuraOrganizacionService, 
    PlantillaDocumentoService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.tipos = ["int", "string", "combobox"];
    $scope.plantillaDocumentos = [];
    $scope.plantillaDocumento = {};

    var RegistrarPlantillaDocumentoSuccess = function(response){
    	$log.debug("RegistrarPlantillaDocumento - Success");
        console.log("Respuesta :: ", response.body);
    	$scope.plantillaDocumento = {};
        $scope.GetPlantillaDocumento();
    };
    var RegistrarPlantillaDocumentoError = function(response){
        $log.debug("RegistrarPlantillaDocumento - Error");
        console.log("Respuesta :: ", response);
    };

    var ActualizarPlantillaDocumentoSuccess = function(response){
    	$log.debug("ActualizarPlantillaDocumento - Success");
    	console.log("Respuesta :: ", response.body);
    	$scope.GetPlantillaDocumento();
    };
    var ActualizarPlantillaDocumentoError = function(response){
        $log.debug("ActualizarPlantillaDocumento - Error");
    	console.log("Respuesta :: ", response);
    };

    var EliminarPlantillaDocumentoSuccess = function(response){
    	$log.debug("EliminarPlantillaDocumento - Success");
        console.log("Respuesta :: ", response.body);
    	$scope.GetPlantillaDocumento();
    };
    var EliminarPlantillaDocumentoError = function(response){
        $log.debug("EliminarPlantillaDocumento - Error");
        console.log("Respuesta :: ", response);
    };
    
    var getEstructuraOrganizacionServiceSuccess = function(response){
    	$log.debug("GetEstructuraOrganizacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.organizaciones = response;
    };
    var getEstructuraOrganizacionServiceError = function(response){
     	$log.debug("GetEstructuraOrganizacion - Error"); 
    	console.log("Respuesta :: ", response);
    };

    /********** CRUD USUARIOS ***********/

    $scope.RegistrarPlantillaDocumento = function(){
        var plantillaDocumento = {
            setiqueta : $scope.plantillaDocumento.setiqueta,
            smodel : $scope.plantillaDocumento.smodel,
            sopciones : $scope.plantillaDocumento.sopciones === undefined ? "" : $scope.plantillaDocumento.sopciones,
            stipo : $scope.plantillaDocumento.stipo,
            sfacultad : $scope.facultad.snombreEstructuraOrganizacion,
            splantilla : generarPlantilla($scope.plantillaDocumento.stipo, $scope.plantillaDocumento.smodel, $scope.plantillaDocumento.setiqueta, $scope.plantillaDocumento.sopciones),
            sdata : $scope.plantillaDocumento.sdata === undefined ? "" : $scope.plantillaDocumento.sdata.replace(/\n/g, ","),
            suserCreacion : $scope.sharedService.nombreUsuario,
            sestado : 'A'
        };
	PlantillaDocumentoService.RegistrarPlantillaDocumento(plantillaDocumento).then(RegistrarPlantillaDocumentoSuccess, RegistrarPlantillaDocumentoError);
    };
    
    $scope.getEstructuraOrganizaciones = function(){
      	EstructuraOrganizacionService.getEstructuraOrganizaciones().then(getEstructuraOrganizacionServiceSuccess, getEstructuraOrganizacionServiceError);
    };

    $scope.ActualizarPlantillaDocumento = function(){
        $scope.plantillaDocumento.sfacultad = $scope.facultad.snombreEstructuraOrganizacion;
        $scope.plantillaDocumento.suserModificacion = $scope.sharedService.nombreUsuario;
    	PlantillaDocumentoService.ActualizarPlantillaDocumento($scope.plantillaDocumento).then(ActualizarPlantillaDocumentoSuccess, ActualizarPlantillaDocumentoError);
    };

    $scope.EliminarPlantillaDocumento = function(plantillaDocumento){
    	$scope.plantillaDocumento = plantillaDocumento;
    	PlantillaDocumentoService.EliminarPlantillaDocumento($scope.plantillaDocumento).then(EliminarPlantillaDocumentoSuccess, EliminarPlantillaDocumentoError);
    };

    $scope.update = function(plantillaDocumento){
    	$scope.plantillaDocumento = plantillaDocumento;
        $scope.facultad = GetFacultad($scope.plantillaDocumento.sfacultad);
    };
    
    $scope.Cerrar = function(){
        $scope.plantillaDocumento = {};
    };
    
    $scope.changeTipo = function(tipo){
        if(tipo === "combobox"){
            $scope.textArea = true;
        } else {
            $scope.textArea = false;
        }
    };
    
    var GetFacultad = function(valor){
        var facultad = {};
        angular.forEach($scope.organizaciones, function(value, key){
            if(value.snombreEstructuraOrganizacion === valor){
                facultad = value;
            }
        });
        return facultad;
    };
    
    var generarPlantilla = function(tipo, ngModel, etiqueta, opciones){
        var plantillaDocumento = "";
        switch(tipo.toUpperCase()) {
            case "INT":
                plantillaDocumento = '<label class="control-label col-md-2" >' + etiqueta + '</label>'
                                + '<div class="col-md-4" > '
                                +'       <input ng-model="' + ngModel + '" type="numeric" class="form-control" >'
                                +' </div>';
                break;
            case "STRING":
                plantillaDocumento = '<label class="control-label col-md-2" >' + etiqueta + '</label>'
                                + '<div class="col-md-4" > '
                                +'       <input ng-model="' + ngModel + '" type="text" class="form-control" >'
                                +' </div>';
                break;
            case "COMBOBOX":
                plantillaDocumento = '<label class="control-label col-md-2" >' + etiqueta + '</label>'
                                + '<div class="col-md-4" > '
                                +'       <select ng-model="' + ngModel + '" class="form-control" ng-options="opt as opt for opt in ' + opciones + '" ></select>'
                                +' </div>';
                break;
            default:
                
        };
        return plantillaDocumento;
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
        $scope.GetPlantillaDocumento();
    });
    
    /*********************************************/
    
    var GetPlantillaDocumentoSuccess = function(response){
        $log.debug("GetPlantillaDocumentoSuccess - Success");
        console.log("Respuesta :: ", response);
        $scope.plantillaDocumentos = [];
        $scope.plantillaDocumentos = response.lista;
        $scope.total = response.total;
    };
    
    var GetPlantillaDocumentoError = function(response){
        $log.debug("GetPlantillaDocumentoError - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.GetPlantillaDocumento = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        PlantillaDocumentoService.GetPlantillaDocumento(objPagina).then(GetPlantillaDocumentoSuccess, GetPlantillaDocumentoError);
    };
    
    $scope.clickBuscar = function(){
        $scope.GetPlantillaDocumento();
    };
    
    $scope.GetPlantillaDocumento();
    $scope.getEstructuraOrganizaciones();
 
});