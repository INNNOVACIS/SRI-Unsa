investigacionApp.controller('UsuariosController',['$log', '$scope', 'UsuariosService', 'EstructuraOrganizacionService', 'SharedService', 
function($log, $scope, UsuariosService, EstructuraOrganizacionService, SharedService) {

    $scope.sharedService = SharedService;
    $scope.users = [];
    $scope.usuario = {};
    $scope.estados = ['A','I'];
    
    var getEstructuraOrganizacionServiceSuccess = function(response){
    	$log.debug("GetEstructuraOrganizacion - Success");
    	console.log("Respuesta :: ", response);
    	$scope.organizaciones = response;
    };
    var getEstructuraOrganizacionServiceError = function(response){
     	$log.debug("GetEstructuraOrganizacion - Error"); 
    	console.log("Respuesta :: ", response);
    };
    
    var registrarUsuarioSuccess = function(response){
    	$log.debug("Registrar Usuario - Success");
        console.log("Respuesta :: ", response.body);
    	$scope.usuario = {};
        $scope.getUsuariosByPagina();
    };
    var registrarUsuarioError = function(response){
        $log.debug("RegistrarUsuario - Error");
        console.log("Respuesta :: ", response);
    };

    var updateUsuarioSuccess = function(response){
    	$log.debug("UpdateUsuario - Success");
    	console.log("Respuesta :: ", response.body);
    	$scope.getUsuariosByPagina();
    };
    var updateUsuarioError = function(response){
        $log.debug("UpdateUsuario - Error");
    	console.log("Respuesta :: ", response);
    };

    var deleteUsuarioSuccess = function(response){
    	$log.debug("DeleteUsuario - Success");
        console.log("Respuesta :: ", response.body);
    	$scope.getUsuariosByPagina();
    };
    var deleteUsuarioError = function(response){
        $log.debug("DeleteUsuario - Error");
        console.log("Respuesta :: ", response);
    };

    /********** CRUD USUARIOS ***********/

    $scope.registrarUsuario = function(){
        var usuariopersona = {
            susuarioLogin : $scope.usuario.susuarioLogin,
            susuarioPassword : $scope.usuario.susuarioPassword,
            susuarioEmail : $scope.usuario.susuarioEmail,
            snombre : $scope.usuario.snombre,
            sapellido : $scope.usuario.sapellido,
            ndni : $scope.usuario.ndni,
            nidEstructuraOrganizacion : $scope.organizacion.nidPadre,
            nidDepartamento : $scope.organizacion.nidEstructuraOrganizacion,
            suserCreacion : $scope.sharedService.nombreUsuario,
            sestado : 'A'
        };
	UsuariosService.registrarUsuario(usuariopersona).then(registrarUsuarioSuccess, registrarUsuarioError);
    };

    $scope.updateUsuario = function(){
        var usuariopersona = {
            nidUsuario : $scope.usuario.nidUsuario,
            nidPersona : $scope.usuario.nidPersona,
            nidEstructuraOrganizacion : $scope.organizacion.nidPadre,
            nidDepartamento : $scope.organizacion.nidEstructuraOrganizacion,
            susuarioLogin : $scope.usuario.susuarioLogin,
            susuarioPassword : $scope.usuario.susuarioPassword,
            susuarioEmail : $scope.usuario.susuarioEmail,
            ndni : $scope.usuario.ndni,
            snombre : $scope.usuario.snombre,
            sapellido : $scope.usuario.sapellido,
            suserCreacion : $scope.usuario.suserCreacion,
            dfechaCreacion : $scope.usuario.dfechaCreacion,
            suserModificacion : $scope.sharedService.nombreUsuario,
            sestado : $scope.usuario.sestado
        };
    	UsuariosService.updateUsuario(usuariopersona).then(updateUsuarioSuccess, updateUsuarioError);
    };

    $scope.deleteUsuario = function(deleteUsuario){
        $scope.usuario = deleteUsuario;
    	var usuariopersona = {
            nidUsuario : $scope.usuario.nidUsuario,
            nidPersona : $scope.usuario.nidPersona,
            nidEstructuraOrganizacion : $scope.usuario.nidEstructuraOrganizacion,
            susuarioLogin : $scope.usuario.susuarioLogin,
            susuarioPassword : $scope.usuario.susuarioPassword,
            susuarioEmail : $scope.usuario.susuarioEmail,
            ndni : $scope.usuario.ndni,
            snombre : $scope.usuario.snombre,
            sapellido : $scope.usuario.sapellido,
            suserCreacion : $scope.usuario.suserCreacion,
            dfechaCreacion : $scope.usuario.dfechaCreacion,
            suserModificacion : $scope.sharedService.nombreUsuario,
            sestado : $scope.usuario.sestado
        };
    	UsuariosService.deleteUsuario(usuariopersona).then(deleteUsuarioSuccess, deleteUsuarioError);
    };

    $scope.update = function(updateUsuario){
    	$scope.usuario = updateUsuario;
        seleccionarDepartamento($scope.usuario.nidDepartamento);
    };
    
    $scope.Cerrar = function(){
        $scope.usuario = {};
    };
    
    $scope.getEstructuraOrganizaciones = function(){
      	EstructuraOrganizacionService.getEstructuraOrganizaciones().then(getEstructuraOrganizacionServiceSuccess, getEstructuraOrganizacionServiceError);
    };
    
    var seleccionarDepartamento = function(idDepartamento){
        angular.forEach($scope.organizaciones, function(value, key){
            if(value.nidEstructuraOrganizacion === idDepartamento){
                $scope.organizacion = value;
            }
        });
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
        $scope.getUsuariosByPagina();
        $scope.row = ($scope.currentPage - 1) * $scope.currentRango + 1;
    });
    
    /*********************************************/
    
    var paginacionUsuarioSuccess = function(response){
        $log.debug("Get paginacionUsuario - Success");
        console.log("Respuesta :: ", response);
        $scope.users = [];
        $scope.users = response.lista;
        $scope.total = response.total;
    };
    
    var paginacionUsuarioError = function(response){
        $log.debug("Get paginacionUsuario - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.getUsuariosByPagina = function(){
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        UsuariosService.paginacionUsuario(objPagina).then(paginacionUsuarioSuccess, paginacionUsuarioError);
    };
    
    $scope.clickBuscar = function(){
        $scope.getUsuariosByPagina();
    };
    
    $scope.getEstructuraOrganizaciones();
    $scope.getUsuariosByPagina();
 
 
    /******************* EXPORTAR ARCHIVOS *****************/
    
    var descargarPDFSuccess = function (response){
        $log.debug("descargarPDF - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var descargarPDFError = function (response){
        $log.debug("descargarPDF - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    var descargarExcelSuccess = function (response){
        $log.debug("descargarExcel - Success");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    var descargarExcelError = function (response){
        $log.debug("descargarExcel - Error");
        console.log("Respuesta :: ", response);
        $scope.loader = false;
    };
    
    $scope.descargarPDF = function(){
        console.log("Empezando descarga de PDF...");
        $scope.loader = true;
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        UsuariosService.descargarUsuariosPDF(objPagina).then(descargarPDFSuccess, descargarPDFError);
    };
    
    $scope.descargarExcel = function(){
        console.log("Empezando descarga de Excel...");
        $scope.loader = true;
        var objPagina = { currentPage : $scope.currentPage, rango : $scope.currentRango, total : $scope.total, filtro : $scope.buscar};
        UsuariosService.descargarUsuariosExcel(objPagina).then(descargarExcelSuccess, descargarExcelError);
    };
 
}]);