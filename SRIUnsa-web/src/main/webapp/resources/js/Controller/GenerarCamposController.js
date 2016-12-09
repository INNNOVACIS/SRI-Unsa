investigacionApp.controller('GenerarCamposController', function($log, $scope, UsuariosService, EstructuraOrganizacionService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.tipos = ["int", "string", "combobox"];
    $scope.usuario = {};
    $scope.estados = ['A','I'];

    var registrarCamposSuccess = function(response){
    	$log.debug("RegistrarCampos - Success");
        console.log("Respuesta :: ", response.body);
    	$scope.usuario = {};
        $scope.getUsuariosByPagina();
    };
    var registrarCamposError = function(response){
        $log.debug("RegistrarCampos - Error");
        console.log("Respuesta :: ", response);
    };

    var updateCamposSuccess = function(response){
    	$log.debug("UpdateCampos - Success");
    	console.log("Respuesta :: ", response.body);
    	$scope.getUsuariosByPagina();
    };
    var updateCamposError = function(response){
        $log.debug("UpdateCampos - Error");
    	console.log("Respuesta :: ", response);
    };

    var deleteCamposSuccess = function(response){
    	$log.debug("DeleteCampos - Success");
        console.log("Respuesta :: ", response.body);
    	$scope.getUsuariosByPagina();
    };
    var deleteCamposError = function(response){
        $log.debug("DeleteCampos - Error");
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

    $scope.registrarCampo = function(){
        var campos = {
            etiqueta : $scope.etiqueta,
            modelo : $scope.modelo,
            tipo : $scope.tipo,
            facultad : $scope.facultad.snombreEstructuraOrganizacion,
            plantilla : generarPlantilla($scope.tipo, $scope.modelo, $scope.etiqueta),
            opciones : $scope.opciones.split(/\n/),
            suserCreacion : $scope.sharedService.nombreUsuario,
            sestado : 'A'
        };
        console.log("Registrar Campos :: ", campos);
//	UsuariosService.registrarUsuario(campos).then(registrarCamposSuccess, registrarCamposError);
    };
    
    $scope.getEstructuraOrganizaciones = function(){
      	EstructuraOrganizacionService.getEstructuraOrganizaciones().then(getEstructuraOrganizacionServiceSuccess, getEstructuraOrganizacionServiceError);
    };

    $scope.updateCampos = function(){
        var campos = {
            nidUsuario : $scope.usuario.nidUsuario,
            susuarioLogin : $scope.usuario.susuarioLogin,
            susuarioEmail : $scope.usuario.susuarioEmail,
            susuarioPassword : $scope.usuario.susuarioPassword,
            suserCreacion : $scope.usuario.suserCreacion,
            dfechaCreacion : $scope.usuario.dfechaCreacion,
            suserModificacion : $scope.sharedService.nombreUsuario,
            sestado : $scope.usuario.sestado
        };
    	UsuariosService.updateUsuario(campos).then(updateCamposSuccess, updateCamposError);
    };

    $scope.deleteCampos = function(user){
    	$scope.usuario = user;
    	UsuariosService.deleteUsuario($scope.usuario).then(deleteCamposSuccess, deleteCamposError);
    };

    $scope.update = function(user){
    	$scope.usuario = user;
    };
    
    $scope.Cerrar = function(){
        $scope.usuario = {};
    };
    
    $scope.changeTipo = function(tipo){
        if(tipo === "combobox"){
            $scope.textArea = true;
        } else {
            $scope.textArea = false;
        }
    };
    
    var generarPlantilla = function(tipo, ngModel, etiqueta){
        var plantilla = "";
        switch(tipo.toUpperCase()) {
            case "INT":
                plantilla = '<div class="form-group">' 
                                + '<label class="control-label col-md-2" >' + etiqueta + '</label>'
                                + '<div class="col-md-4" > '
                                +'       <input ng-model="' + ngModel + '" type="numeric" class="form-control" >'
                                +' </div>'
                          + '</div>';
                break;
            case "STRING":
                plantilla = '<div class="form-group">' 
                                + '<label class="control-label col-md-2" >' + etiqueta + '</label>'
                                + '<div class="col-md-4" > '
                                +'       <input ng-model="' + ngModel + '" type="text" class="form-control" >'
                                +' </div>'
                          + '</div>';
                break;
            case "COMBOBOX":
                plantilla = '<div class="form-group">' 
                                + '<label class="control-label col-md-2" >' + etiqueta + '</label>'
                                + '<div class="col-md-4" > '
                                +'       <select ng-model="' + ngModel + '" type="text" class="form-control" ></select>'
                                +' </div>'
                          + '</div>';
                break;
            default:
                
        };
        return plantilla;
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
        $scope.getCamposByPagina();
    });
    
    /*********************************************/
    
    var getCamposByPaginaSuccess = function(response){
        $log.debug("getCamposByPaginaSuccess - Success");
        console.log("Respuesta :: ", response);
        $scope.users = [];
        $scope.users = response.lista;
        $scope.total = response.total;
    };
    
    var getCamposByPaginaError = function(response){
        $log.debug("getCamposByPaginaError - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getCamposByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        UsuariosService.paginacionUsuario(objPagina).then(getCamposByPaginaSuccess, getCamposByPaginaError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getCamposByPagina();
    };
    
    $scope.getCamposByPagina();
    $scope.getEstructuraOrganizaciones();
 
});